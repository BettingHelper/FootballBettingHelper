package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends JFrame {
    String currentVersion;
    String newestVersion;

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
                    settings.downloadSettings(sn);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (settings.checkKey(sn)){
                if (new File("settings").list().length==0)
                    settings.downloadSettings(sn);

                //String ID = Settings.getIDBySerialNumber(sn);
                //String user = Settings.getUserBySerialNumber(sn);

                /*if(Files.isWritable(path)){
                    flagPath = true;
                }*/
                int width = Integer.parseInt(settings.getWindowResolution().split("x")[0]);
                int height = Integer.parseInt(settings.getWindowResolution().split("x")[1]);

                setBounds(0, 0, width, height);
                super.setMinimumSize(new Dimension(1320, 720));
                String tabs[] = {"О программе", "Сравнение команд", "Статистика команды", "Противостояние",
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

                String resultOfSync = settings.toRefreshDatabase(windowWithProgressBar, numberOfAccount);

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
                PanelMatchCenter panelMatchCenter = new PanelMatchCenter(panelMatching, panelOneTeamStats, panelConfrontation, panelReferee, panelTablesByLeague, panelTrends, panelBeforeAfter);
                PanelCalculator panelCalculator = new PanelCalculator();

                jtp.addTab(tabs[0], panelStart);
                add(jtp);
                jtp.addTab(tabs[9], panelMatchCenter);
                add(jtp);
                jtp.addTab(tabs[1], panelMatching);
                add(jtp);
                jtp.addTab(tabs[2], panelOneTeamStats);
                add(jtp);
                jtp.addTab(tabs[3], panelConfrontation);
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
                jtp.addTab(tabs[11], panelCalculator);
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
                String user = Settings.getUserBySerialNumber(sn);
                int n = Settings.getNumberOfAccount();
                if (!currentVersion.equals(newestVersion)){
                    try {
                        FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/versions/HockeyBettingHelper.exe", "HockeyBettingHelper.exe");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
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

    void writeFile() throws FileNotFoundException {
        try {
            FileOutputStream fos = new FileOutputStream("");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < 10; i++) {
                String str = String.valueOf(i);
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String[] readTxtFile(String fileName){
        String resultS = "Выберите команду\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return resultS.split("\n");
    }

    public static String[] getListOfRefs(String fileName){
        String resultS = "Выберите арбитра\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return resultS.split("\n");
    }

    public static String[] readTxtFileForLeagues(String fileName){
        String resultS = "Выберите лигу\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return resultS.split("\n");
    }
}
