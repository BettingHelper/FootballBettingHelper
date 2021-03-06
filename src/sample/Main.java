package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends JFrame {
    String currentVersion;
    String newestVersion;
    boolean flagCheckKey = false;

    public Main() throws Exception{
        super("Football Betting Helper");

        java.util.Locale.setDefault(new java.util.Locale("ru","RU"));
        getInputContext().selectInputMethod( java.util.Locale.getDefault());

        final Settings settings = Settings.getSettingsFromFile();

        String sn = null;
        boolean flagPath = false;
        File file = new File("logs/log.txt");
        Path path = Paths.get(file.getAbsolutePath().split("FootballBettingHelper")[0]);

        if(Files.isWritable(path)){
            flagPath = true;
        }

        PopupWindow window = null;
        WindowWithProgressBar windowWithProgressBar;
        int numberOfAccount = Settings.getNumberOfAccount();
        if (!flagPath) {
            window = new PopupWindow("<html>   Папка " + path + " ограничена в правах!<br>" +
                    "Пути решения проблемы:<br>" +
                    "1. Перенесите программу в другой каталог<br>" +
                    "2. Раздайте права на эту папку<br>" +
                    "3. Запускайте программу от имени администратора.</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        } else{
            try {
                sn = Settings.getSerialNumber();
                System.out.println("My ID is: " + sn);
                if (settings.ip == null || settings.ip.length() == 0){
                    Settings.downloadSettings(sn);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Settings.checkKey(sn)){
                flagCheckKey = true;
                if (new File("settings").list().length==0)
                    Settings.downloadSettings(sn);

                int width = Integer.parseInt(settings.getWindowResolution().split("x")[0]);
                int height = Integer.parseInt(settings.getWindowResolution().split("x")[1]);

                setBounds(0, 0, width, height);
                super.setMinimumSize(new Dimension(1320, 720));
                String[] tabs = {"О программе", "Сравнение команд", "Статистика команды", "Противостояние",
                            "Статистика судьи", "Таблицы по лиге", "Тренды", "Настройки", "Поддержка",
                            "Матч-центр", "До-после", "Калькулятор"};

                JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
                        JTabbedPane.SCROLL_TAB_LAYOUT);
                jtp.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
                    @Override
                    protected int calculateTabHeight(int tabPlacement, int tabIndex,
                                                     int fontHeight) {
                        return 40;
                    }

                    @Override
                    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects,
                                            int tabIndex, Rectangle iconRect, Rectangle textRect) {
                        rects[tabIndex].height = 35;
                        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
                    }
                });
                windowWithProgressBar = new WindowWithProgressBar("  Подождите, выполняется синхронизация базы данных");
                windowWithProgressBar.setVisible(true);

                String resultOfSync = Settings.toRefreshDatabase(windowWithProgressBar, numberOfAccount);

                if (resultOfSync.contains("Fail")){
                    windowWithProgressBar.setVisible(false);
                    windowWithProgressBar = new WindowWithProgressBar("<html>   База не была синхронизирована<br>" +
                            "Причина: " + resultOfSync.split(": ")[1] + ".</html>");
                    windowWithProgressBar.setVisible(true);
                } else if (resultOfSync.contains("Success")){
                    windowWithProgressBar.setVisible(false);
                    window = new PopupWindow("База была синхронизирована успешно!");
                    window.setVisible(true);
                }
                window.setAlwaysOnTop(true);

                PanelStart panelStart = new PanelStart();
                PanelSettings panelSettings = new PanelSettings();
                PanelMatching panelMatching = new PanelMatching();
                PanelConfrontation panelConfrontation = new PanelConfrontation();
                PanelOneTeamStats panelOneTeamStats = new PanelOneTeamStats();
                PanelReferee panelReferee = new PanelReferee();
                PanelTablesByLeague panelTablesByLeague = new PanelTablesByLeague();
                PanelTrends panelTrends = new PanelTrends();
                PanelSupport panelSupport = new PanelSupport();
                PanelBeforeAfter panelBeforeAfter = new PanelBeforeAfter();
                PanelCalculator panelCalculator = new PanelCalculator();
                PanelMatchCenter panelMatchCenter = new PanelMatchCenter(panelMatching, panelOneTeamStats, panelConfrontation, panelReferee, panelTablesByLeague, panelTrends, panelBeforeAfter, panelCalculator);

//                YC_Calculator panelYC = new YC_Calculator();

                jtp.addTab(tabs[0], panelStart);
//                jtp.addTab(tabs[0], panelYC);
                add(jtp);
                jtp.addTab(tabs[9], panelMatchCenter);
                add(jtp);
                jtp.addTab(tabs[1], panelMatching);
                add(jtp);
                jtp.addTab(tabs[2], panelOneTeamStats);
                add(jtp);
                jtp.addTab(tabs[3], panelConfrontation);
                add(jtp);
                jtp.addTab(tabs[11], panelCalculator);
                add(jtp);
                jtp.addTab(tabs[4], panelReferee);
                add(jtp);
                jtp.addTab(tabs[5], panelTablesByLeague);
                add(jtp);
                jtp.addTab(tabs[6], panelTrends);
                add(jtp);
                jtp.addTab(tabs[10], panelBeforeAfter);
                add(jtp);
                jtp.addTab(tabs[8], panelSupport);
                add(jtp);
                jtp.addTab(tabs[7], panelSettings);
                add(jtp);

                jtp.setSelectedIndex(0);
                currentVersion = panelStart.currentVersion;
                newestVersion = panelStart.newestVersion;


            } else {
                WindowWithID windowWithID = new WindowWithID("<html>  Вы не зарегистрированы или у вас кончилась подписка!<br>" +
                        "Обратитесь к администратору.</html>");
                windowWithID.setVisible(true);
                windowWithID.setAlwaysOnTop(true);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того, чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процессах
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent event) {
                int h = Main.getFrames()[0].getHeight();
                int w = Main.getFrames()[0].getWidth();
                Settings.setWindowResolution(w, h);
                String sn = null;
                try {
                    sn = Settings.getSerialNumber();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int n = Settings.getNumberOfAccount();
                if (flagCheckKey){
                    if (!currentVersion.equals(newestVersion)){
                        try {
                            FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/versions/FootballBettingHelper.exe", "FootballBettingHelper.exe");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

                System.exit(0);
            }
            @Override
            public void windowClosed(WindowEvent e) {
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public static void main(String[] args) throws Exception {
        try {
            Main app = new Main();
            app.setVisible(true);

        } catch (Exception e) {
            WindowWithID windowWithID = new WindowWithID("<html>   Что-то пошло не так: <br>" +
                    "   Причина: " + e.getMessage() + "<br>" +
                    "Нажмите кнопку 'Справка' или обратитесь к администратору.</html>");
            windowWithID.setVisible(true);
            windowWithID.setAlwaysOnTop(true);
        }

    }

    public static String[] readTxtFile(String fileName){
        StringBuilder resultS = new StringBuilder("Выберите команду\n");
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS.append(str).append("\n");
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return resultS.toString().split("\n");
    }

    public static String[] getListOfRefs(String fileName){
        StringBuilder resultS = new StringBuilder("Выберите арбитра\n");
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS.append(str).append("\n");
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return resultS.toString().split("\n");
    }

    public static String[] readTxtFileForLeagues(String fileName){
        StringBuilder resultS = new StringBuilder("Выберите лигу\n");
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS.append(str).append("\n");
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return resultS.toString().split("\n");
    }
}
