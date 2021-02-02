package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class PanelOneTeamStats extends JPanel{
    final Font font15 = new Font("Arial", Font.BOLD, 15);
    final Font font16 = new Font("Arial", Font.BOLD, 16);
    final Font font18 = new Font("Arial", Font.BOLD, 18);
    final Font font20 = new Font("Arial", Font.BOLD, 20);

    int graphicHeight = 350;
    int indexForParameterChooser = 0;
    int indexForIndexChooser = 0;
    int indexForValueChooser = 0;
    double valueForSlider = 0;
    double minSliderValue = 0;
    double maxSliderValue = 0;
    double stepSlider = 0.5;
    JLabel leftValue;
    JLabel rightValue;
    JLabel bottomValue;
    JSlider slider = new JSlider(0,10,0);
    JComboBox<String> seasonChooser;
    JComboBox<String> leagueChooser;
    JPanel teamPanel;
    JPanel infoPanel;

    public PanelOneTeamStats(){
        this.setLayout(new BorderLayout());
        String curSeason = Settings.getDefaultSeason();
        final String path = "database/";

        ////////////////////////////////////////////ПАНЕЛЬ
        JPanel panelChoosers = new JPanel(new GridLayout(1, 0, 5, 5));

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonChooser = new JComboBox<>(seasonList);
//        seasonChooser.setPreferredSize(new Dimension((int) (130 * procWIDTH), 30));
        panelChoosers.add(seasonChooser);

        JFileChooser fileChooser = new JFileChooser(path + curSeason + "/leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> leagueList = new ArrayList<>();
        leagueList.add("Выберите лигу");
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        String[] listOfLeagues = new String[leagueList.size()];
        for (int i = 0; i < listOfLeagues.length; i++)
            listOfLeagues[i] = leagueList.get(i);
        leagueChooser = new JComboBox<>(listOfLeagues);
        panelChoosers.add(leagueChooser);

        String fileName = path + curSeason + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] list = {"Выберите команду"};
        if (!fileName.contains("ыберите")) {
            list = Main.readTxtFile(fileName);
        }
        final JComboBox<String> teamChooser = new JComboBox<>(list);
        teamChooser.setEnabled(false);
        panelChoosers.add(teamChooser);

        String[] allOrHomeOrAway = {"Все матчи", "Дома", "На выезде"};
        final JComboBox<String> teamAllOrHomeOrAway = new JComboBox<>(allOrHomeOrAway);
        panelChoosers.add(teamAllOrHomeOrAway);

        String[] lastOrFullSeasonString = {"Весь сезон", "Последние 3", "Последние 4", "Последние 5", "Последние 6", "Последние 7", "Последние 8", "Последние 9", "Последние 10"};
        final JComboBox<String> lastOrFullSeason = new JComboBox<>(lastOrFullSeasonString);
        panelChoosers.add(lastOrFullSeason);

        final JButton buttonShowInfo = new JButton("Отобразить!");
        buttonShowInfo.setFont(font18);
        panelChoosers.add(buttonShowInfo);

        this.add(panelChoosers, BorderLayout.NORTH);

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        teamPanel = new JPanel();
        infoPanel.add(teamPanel, BorderLayout.CENTER);

        this.add(infoPanel);

        seasonChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seasonChooser.setFocusable(false);

                String team = String.valueOf(teamChooser.getSelectedItem());
                String league = String.valueOf(leagueChooser.getSelectedItem());

                String pathToLeaguesList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser fileChooser = new JFileChooser(pathToLeaguesList);
                String[] directoryList = new String[fileChooser.getCurrentDirectory().list().length + 1];
                directoryList[0] = "Выберите лигу";
                for (int i = 1; i < directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i - 1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList);
                leagueChooser.setModel(modelH);

                for (int i = 0; i < directoryList.length; i++) {
                    if (directoryList[i].equals(league))
                        leagueChooser.setSelectedItem(league);
                }

                String pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] list = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    list = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(list);
                teamChooser.setModel(modelH);
                teamChooser.setEnabled(false);

                pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Teams/";
                fileChooser = new JFileChooser(pathToTeamsList);
                directoryList = new String[fileChooser.getCurrentDirectory().list().length + 1];
                directoryList[0] = "Выберите команду";
                for (int i = 1; i < directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i - 1].replace(".xml", "");
                for (int i = 0; i < directoryList.length; i++) {
                    if (directoryList[i].equals(team)) {
                        teamChooser.setSelectedItem(team);
                        teamChooser.setEnabled(true);
                    }
                }
                leagueChooser.setFocusable(true);
            }
        });

        leagueChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = leagueChooser.getSelectedIndex();
                String pathToLeaguesList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser leftFileChooser = new JFileChooser(pathToLeaguesList);
                String[] leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length+1];
                leftDirectoryList[0] = "Выберите лигу";
                for (int i=1; i<leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(leftDirectoryList);
                leagueChooser.setModel(modelH);
                leagueChooser.setSelectedIndex(index);
                leagueChooser.setFocusable(false);

                teamChooser.setEnabled(true);
                String pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listRight = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listRight = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listRight);
                teamChooser.setModel(modelH);
                teamAllOrHomeOrAway.setFocusable(false);

            }
        });

        teamChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooser.setFocusable(false);
                teamAllOrHomeOrAway.setFocusable(true);
            }
        });

        teamAllOrHomeOrAway.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamAllOrHomeOrAway.setFocusable(false);
            }
        });

        buttonShowInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (teamPanel != null) {
                    infoPanel.remove(teamPanel);
                }
                teamPanel = refreshData((String) leagueChooser.getSelectedItem(),
                        (String) teamChooser.getSelectedItem(),
                        (String) teamAllOrHomeOrAway.getSelectedItem(),
                        (String) seasonChooser.getSelectedItem(),
                        (String) lastOrFullSeason.getSelectedItem()
                );
                infoPanel.add(teamPanel);
                infoPanel.revalidate();
                buttonShowInfo.setFocusable(false);
            }
        });

    }

    public JPanel refreshData(String leagueName, final String teamName, final String allOrHomeOrAway, String season, final String lastOrFullSeason){
        this.setCursor(Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        JPanel result = new JPanel(new BorderLayout());
        final JScrollPane scrollPane;
        final Settings settings = Settings.getSettingsFromFile();
        season = season.replace("Сезон ", "");
        if ((!leagueName.contains("Выберите"))&&(!teamName.contains("Выберите"))){
            final Selector selector = new Selector();
            ArrayList<String> tournamentTable = League.getLeagueFromFile(leagueName, season).tournamentTable;
            selector.getListOfMatches(leagueName, teamName, allOrHomeOrAway, season, lastOrFullSeason);
            selector.getPList(selector.listOfMatches, teamName);

            if (selector.listOfMatches.size()>0){
//                JPanel allInfoPanel = new JPanel(new BorderLayout());
                JPanel allInfoPanel = new JPanel(new VerticalLayout());

                final JPanel container = new JPanel();
                container.setLayout(null);
                int otstup = 0;
                int matches = selector.listOfMatches.size();
                int wins = (int) Double.parseDouble(selector.pList.get(0).get(1));
                int draws = (int) Double.parseDouble(selector.pList.get(1).get(1));
                int loses = (int) Double.parseDouble(selector.pList.get(2).get(1));
                int points = (int) Double.parseDouble(selector.pList.get(3).get(1));
                String teamStats = "Матчей: " + String.valueOf(matches) + ";    Побед: " + String.valueOf(wins) + ";    Ничьих: "
                        + String.valueOf(draws) + ";    Поражений: " + String.valueOf(loses) +";    Набрано очков: " + String.valueOf(points) + ";";
                final JLabel label = new JLabel(teamStats);
                label.setBackground(new Color(238, 238, 238));
                label.setBorder(BorderFactory.createEmptyBorder());
                label.setLocation(5, otstup);
                otstup += 25;
                label.setSize(new Dimension(600, 25));
                label.setFont(font15);
                label.setOpaque(false);
                container.add(label);

                int xGmatches = (int) Double.parseDouble(selector.pList.get(17).get(1));
                double xWins = Double.parseDouble(selector.pList.get(18).get(1));
                double xDraws = Double.parseDouble(selector.pList.get(19).get(1));
                double xLoses = Double.parseDouble(selector.pList.get(20).get(1));
                double xPoints = MyMath.round(Double.parseDouble(selector.pList.get(21).get(1)), 3);
                String teamStatsxG = "xGMatches: " + String.valueOf(xGmatches) + ";   xWins: " + String.valueOf(xWins) + ";   wDraws: "
                        + String.valueOf(xDraws) + ";   xLoses: " + String.valueOf(xLoses) +";   xPoints: " + String.valueOf(xPoints) + ";";
                final JLabel label2 = new JLabel(teamStatsxG);
                label2.setBackground(new Color(238, 238, 238));
                label2.setBorder(BorderFactory.createEmptyBorder());
                label2.setLocation(5, otstup);
                otstup += 25;
                label2.setSize(new Dimension(600, 25));
                label2.setOpaque(false);
                label2.setFont(font15);
                container.add(label2);

                int goalsS = (int) Math.round(selector.listOfMatches.size() * Double.parseDouble(selector.pList.get(4).get(1)));
                int goalsC = (int) Math.round(selector.listOfMatches.size() * Double.parseDouble(selector.pList.get(4).get(2)));
                int diff = goalsS - goalsC;
                String teamGoals = "Голов забито:   " + String.valueOf(goalsS) + ";                 Голов пропущено:   " + String.valueOf(goalsC) +
                        ";                   Разница:   " + String.valueOf(diff) + ";";
                final JLabel label3 = new JLabel(teamGoals);
                label3.setBackground(new Color(238, 238, 238));
                label3.setBorder(BorderFactory.createEmptyBorder());
                label3.setLocation(5, otstup);
                otstup += 25;
                label3.setSize(new Dimension(600, 25));
                label3.setFont(font15);
                label3.setOpaque(false);
                container.add(label3);
                String forma = selector.pList.get(16).get(1);
                if (settings.form.equals("rightToLeft")){
                    String s = "";
                    for (int j=forma.length()-1; j>=0; j--){
                        s = s + forma.charAt(j);
                    }
                    forma = s;
                }
                final Dimension defFormaLocation = new Dimension(65,78);
                for (int i=0; i<forma.length(); i++){
                    JLabel imageLabel = null;
                    if (forma.substring(i, i+1).equals("W")){
                        imageLabel = new JLabel(new ImageIcon("images/win.png"));
                    }
                    if (forma.substring(i, i+1).equals("D")){
                        imageLabel = new JLabel(new ImageIcon("images/draw.png"));
                    }
                    if (forma.substring(i, i+1).equals("L")){
                        imageLabel = new JLabel(new ImageIcon("images/lose.png"));
                    }
                    imageLabel.setLocation(defFormaLocation.width + 25*i, defFormaLocation.height);
                    imageLabel.setSize(20,20);
                    final JLabel finalImageLabel = imageLabel;
                    imageLabel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int numberOfMatch = (finalImageLabel.getX() - defFormaLocation.width)/25;
                            if (settings.form.equals("rightToLeft"))
                                numberOfMatch = selector.listOfMatches.size() - numberOfMatch - 1;
                            if (selector.listOfMatches.size() > 20)
                                numberOfMatch = numberOfMatch + selector.listOfMatches.size() - 20;
                            WindowMatchStats window = new WindowMatchStats(selector.listOfMatches.get(numberOfMatch));
                            window.setVisible(true);
                        }
                        @Override
                        public void mousePressed(MouseEvent e) {
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            setCursor(Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
                            int numberOfMatch = (finalImageLabel.getX() - defFormaLocation.width)/25;
                            if (settings.form.equals("rightToLeft"))
                                numberOfMatch = selector.listOfMatches.size() - numberOfMatch - 1;
                            if (selector.listOfMatches.size() > 20)
                                numberOfMatch = numberOfMatch + selector.listOfMatches.size() - 20;
                            String s = "<html>   " + selector.listOfMatches.get(numberOfMatch).homeTeam + " - " + selector.listOfMatches.get(numberOfMatch).awayTeam + "<br>"
                                    + "   " + selector.listOfMatches.get(numberOfMatch).homeScore + ":" + selector.listOfMatches.get(numberOfMatch).awayScore + "</html>";
                            finalImageLabel.setToolTipText(s);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            setCursor(Cursor.getDefaultCursor());
                        }
                    });
                    container.add(imageLabel);
                }
                String teamForma = "Форма:";
                final JLabel label4 = new JLabel(teamForma);
                label4.setLocation(5, otstup);
                otstup += 40;
                label4.setSize(new Dimension(500, 25));
                label4.setFont(font15);
                container.add(label4);

                int horAlignment = 370;
                String sources = "Сайт / Твиттер / Травмы / Погода / Transfermarkt:";
                final JLabel labelSources = new JLabel(sources);
                labelSources.setLocation(5,otstup);
                otstup += 45;
                labelSources.setSize(new Dimension(horAlignment, 25));
                labelSources.setFont(font15);
                container.add(labelSources);

                final JButton buttonSite = new JButton(new ImageIcon("images/www.png"));
                if (Team.getWebsite(teamName).equals("website"))
                    buttonSite.setEnabled(false);
                buttonSite.setLocation(horAlignment, otstup-50);
                buttonSite.setSize(32,32);
                container.add(buttonSite);

                JButton buttonTwitter = new JButton(new ImageIcon("images/twitter.png"));
                if (Team.getTwitter(teamName).equals("twitter"))
                    buttonTwitter.setEnabled(false);
                buttonTwitter.setLocation(horAlignment + 32 + 5, otstup-50);
                buttonTwitter.setSize(32,32);
                container.add(buttonTwitter);

                JButton buttonMedicine = new JButton(new ImageIcon("images/medicine.png"));
                if (Team.getTransferMarkt(teamName).equals("medicine"))
                    buttonMedicine.setEnabled(false);
                buttonMedicine.setLocation(horAlignment + (32 + 5) * 2, otstup-50);
                buttonMedicine.setSize(32,32);
                container.add(buttonMedicine);

                JButton buttonWeather = new JButton(new ImageIcon("images/weather.png"));
                if (Team.getWeather(teamName).equals("weather"))
                    buttonWeather.setEnabled(false);
                buttonWeather.setLocation(horAlignment + (32 + 5) * 3, otstup-50);
                buttonWeather.setSize(32, 32);
                container.add(buttonWeather);

                JButton buttonTransferMarkt = new JButton(new ImageIcon("images/transfermarkt.png"));
                if (Team.getTransferMarkt(teamName).equals("transferMarkt"))
                    buttonTransferMarkt.setEnabled(false);
                buttonTransferMarkt.setLocation(horAlignment + (32 + 5)*4, otstup-50);
                buttonTransferMarkt.setSize(32,32);
                container.add(buttonTransferMarkt);

                otstup -= 10;

                JButton buttonTrends = new JButton("Тренды");
                buttonTrends.setLocation(5, otstup);
                buttonTrends.setFont(font15);
                buttonTrends.setSize(100, 30);
                container.add(buttonTrends);

                JButton buttonStatsByTimes = new JButton("Диаграммы по таймам");
                buttonStatsByTimes.setLocation(125, otstup);
                buttonStatsByTimes.setFont(font15);
                buttonStatsByTimes.setSize(220, 30);
                container.add(buttonStatsByTimes);

                JButton buttonNotice = new JButton("Моя заметка");
                buttonNotice.setLocation(370, otstup);
                buttonNotice.setFont(font15);
                buttonNotice.setSize(150, 30);
                container.add(buttonNotice);

                otstup += 35;

                String teamSrednie = "";
                if (lastOrFullSeason.contains("Весь сезон")){
                    if (allOrHomeOrAway.contains("Все")){
                        teamSrednie = "Показатели всех игр " + teamName + " в сезоне " + season;
                    }
                    if (allOrHomeOrAway.contains("Дома")){
                        teamSrednie = "Показатели всех домашних игр " + teamName + " в сезоне " + season;
                    }
                    if (allOrHomeOrAway.contains("На выезде")){
                        teamSrednie = "Показатели всех гостевых игр " + teamName + " в сезоне " + season;
                    }
                } else {
                    if (allOrHomeOrAway.contains("Все")){
                        teamSrednie = "Показатели последних " + matches + " игр " + teamName + " в сезоне " + season;
                    }
                    if (allOrHomeOrAway.contains("Дома")){
                        teamSrednie = "Показатели последних " + matches + " домашних игр " + teamName + " в сезоне " + season;
                    }
                    if (allOrHomeOrAway.contains("На выезде")){
                        teamSrednie = "Показатели последних " + matches + " гостевых игр " + teamName + " в сезоне " + season;
                    }
                }

                final JLabel label5 = new JLabel(teamSrednie);
                label5.setLocation(5, otstup);
                otstup += 25;
                label5.setSize(new Dimension(500, 25));
                label5.setFont(font15);
                container.add(label5);

                boolean goalsByTimes = settings.showGoalsBy15Min;

                String[] colHeads = {"Параметр" ,teamName, "Соперник", "Тотал", "Разница"};
                String[] params = {"Голы (сред)", "xG (сред.)", "Реализация", "Голы в 1T и доля в %", "Голы в 2T и доля в %",
                        "Голы 1'-15' и доля в %", "Голы 16'-30' и доля в %", "Голы 31'-45+' и доля в %", "Голы 46'-60' и доля в %", "Голы 61'-75' и доля в %", "Голы 76'-90+' и доля в %",
                        "Владение", "Ударов всего", "Удары в створ", "Удары мимо", "Блокировано ударов",
                        "Угловые", "Офсайды", "Фолы", "Отборы", "Желтые карточки", "Красные карточки"};
                Object[][] data;
                int rowCount = params.length;
                double real = MyMath.round(Double.parseDouble(selector.pList.get(4).get(1)) / Double.parseDouble(selector.pList.get(5).get(1)), 2);
                double realOp = MyMath.round(Double.parseDouble(selector.pList.get(4).get(2)) / Double.parseDouble(selector.pList.get(5).get(2)), 2);
                if (Double.parseDouble(selector.pList.get(5).get(1)) == 0.0)
                    real = 0;
                if (Double.parseDouble(selector.pList.get(5).get(2)) == 0.0)
                    realOp = 0;

                if (goalsByTimes){
                    data = new Object[][]{
                            {"  " + params[0], selector.pList.get(4).get(1), selector.pList.get(4).get(2), MyMath.round(Double.parseDouble(selector.pList.get(4).get(1)) + Double.parseDouble(selector.pList.get(4).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(4).get(1)) - Double.parseDouble(selector.pList.get(4).get(2)) ,2)},
                            {"  " + params[1], selector.pList.get(5).get(1), selector.pList.get(5).get(2), MyMath.round(Double.parseDouble(selector.pList.get(5).get(1)) + Double.parseDouble(selector.pList.get(5).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(5).get(1)) - Double.parseDouble(selector.pList.get(5).get(2)) ,2)},
                            {"  " + params[2], real, realOp, "-", "-"},
                            {"  " + params[3],  (int) Double.parseDouble(selector.pList.get(22).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(22).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(22).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(22).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[4],  (int) Double.parseDouble(selector.pList.get(23).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(23).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(23).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(23).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[5],  (int) Double.parseDouble(selector.pList.get(24).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(24).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(24).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(24).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[6],  (int) Double.parseDouble(selector.pList.get(25).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(25).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(25).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(25).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[7],  (int) Double.parseDouble(selector.pList.get(26).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(26).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(26).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(26).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[8],  (int) Double.parseDouble(selector.pList.get(27).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(27).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(27).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(27).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[9],  (int) Double.parseDouble(selector.pList.get(28).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(28).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(28).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(28).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[10], (int) Double.parseDouble(selector.pList.get(29).get(1)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(29).get(1)) / goalsS, 1) + "%)", (int) Double.parseDouble(selector.pList.get(29).get(2)) + " (" + MyMath.round(100 * Double.parseDouble(selector.pList.get(29).get(2)) / goalsC, 1) + "%)", "-", "-"},
                            {"  " + params[11], selector.pList.get(6).get(1) , selector.pList.get(6).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(6).get(1) ) + Double.parseDouble(selector.pList.get(6).get(2) ) ,2), MyMath.round(Double.parseDouble(selector.pList.get(6).get(1) ) - Double.parseDouble(selector.pList.get(6).get(2) ), 2)},
                            {"  " + params[12], selector.pList.get(7).get(1) , selector.pList.get(7).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(7).get(1) ) + Double.parseDouble(selector.pList.get(7).get(2) ) ,2), MyMath.round(Double.parseDouble(selector.pList.get(7).get(1) ) - Double.parseDouble(selector.pList.get(7).get(2) ), 2)},
                            {"  " + params[13], selector.pList.get(8).get(1) , selector.pList.get(8).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(8).get(1) ) + Double.parseDouble(selector.pList.get(8).get(2) ) ,2), MyMath.round(Double.parseDouble(selector.pList.get(8).get(1) ) - Double.parseDouble(selector.pList.get(8).get(2) ), 2)},
                            {"  " + params[14], selector.pList.get(9).get(1) , selector.pList.get(9).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(9).get(1) ) + Double.parseDouble(selector.pList.get(9).get(2) ) ,2), MyMath.round(Double.parseDouble(selector.pList.get(9).get(1) ) - Double.parseDouble(selector.pList.get(9).get(2) ), 2)},
                            {"  " + params[15], selector.pList.get(12).get(1), selector.pList.get(12).get(2), MyMath.round(Double.parseDouble(selector.pList.get(12).get(1)) + Double.parseDouble(selector.pList.get(12).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(12).get(1)) - Double.parseDouble(selector.pList.get(12).get(2)), 2)},
                            {"  " + params[16], selector.pList.get(10).get(1), selector.pList.get(10).get(2), MyMath.round(Double.parseDouble(selector.pList.get(10).get(1)) + Double.parseDouble(selector.pList.get(10).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(10).get(1)) - Double.parseDouble(selector.pList.get(10).get(2)), 2)},
                            {"  " + params[17], selector.pList.get(11).get(1), selector.pList.get(11).get(2), MyMath.round(Double.parseDouble(selector.pList.get(11).get(1)) + Double.parseDouble(selector.pList.get(11).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(11).get(1)) - Double.parseDouble(selector.pList.get(11).get(2)), 2)},
                            {"  " + params[18], selector.pList.get(13).get(1), selector.pList.get(13).get(2), MyMath.round(Double.parseDouble(selector.pList.get(13).get(1)) + Double.parseDouble(selector.pList.get(13).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(13).get(1)) - Double.parseDouble(selector.pList.get(13).get(2)), 2)},
                            {"  " + params[19], selector.pList.get(73).get(1), selector.pList.get(73).get(2), MyMath.round(Double.parseDouble(selector.pList.get(73).get(1)) + Double.parseDouble(selector.pList.get(73).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(73).get(1)) - Double.parseDouble(selector.pList.get(73).get(2)), 2)},
                            {"  " + params[20], selector.pList.get(14).get(1), selector.pList.get(14).get(2), MyMath.round(Double.parseDouble(selector.pList.get(14).get(1)) + Double.parseDouble(selector.pList.get(14).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(14).get(1)) - Double.parseDouble(selector.pList.get(14).get(2)), 2)},
                            {"  " + params[21], selector.pList.get(15).get(1), selector.pList.get(15).get(2), MyMath.round(Double.parseDouble(selector.pList.get(15).get(1)) + Double.parseDouble(selector.pList.get(15).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(15).get(1)) - Double.parseDouble(selector.pList.get(15).get(2)), 2)}
                    };
                } else{
                    data = new Object[][]{
                            {"  " + params[0], selector.pList.get(4).get(1), selector.pList.get(4).get(2), MyMath.round(Double.parseDouble(selector.pList.get(4).get(1)) + Double.parseDouble(selector.pList.get(4).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(4).get(1)) - Double.parseDouble(selector.pList.get(4).get(2)),2)},
                            {"  " + params[1], selector.pList.get(5).get(1), selector.pList.get(5).get(2), MyMath.round(Double.parseDouble(selector.pList.get(5).get(1)) + Double.parseDouble(selector.pList.get(5).get(2)) ,2), MyMath.round(Double.parseDouble(selector.pList.get(5).get(1)) - Double.parseDouble(selector.pList.get(5).get(2)),2)},
                            {"  " + params[2], real, realOp, "-", "-"},
                            {"  " + params[11], selector.pList.get(6).get(1) , selector.pList.get(6).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(6).get(1) ) + Double.parseDouble(selector.pList.get(6).get(2) ),2), MyMath.round(Double.parseDouble(selector.pList.get(6).get(1) ) - Double.parseDouble(selector.pList.get(6).get(2) ),2)},
                            {"  " + params[12], selector.pList.get(7).get(1) , selector.pList.get(7).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(7).get(1) ) + Double.parseDouble(selector.pList.get(7).get(2) ),2), MyMath.round(Double.parseDouble(selector.pList.get(7).get(1) ) - Double.parseDouble(selector.pList.get(7).get(2) ),2)},
                            {"  " + params[13], selector.pList.get(8).get(1) , selector.pList.get(8).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(8).get(1) ) + Double.parseDouble(selector.pList.get(8).get(2) ),2), MyMath.round(Double.parseDouble(selector.pList.get(8).get(1) ) - Double.parseDouble(selector.pList.get(8).get(2) ),2)},
                            {"  " + params[14], selector.pList.get(9).get(1) , selector.pList.get(9).get(2) , MyMath.round(Double.parseDouble(selector.pList.get(9).get(1) ) + Double.parseDouble(selector.pList.get(9).get(2) ),2), MyMath.round(Double.parseDouble(selector.pList.get(9).get(1) ) - Double.parseDouble(selector.pList.get(9).get(2) ),2)},
                            {"  " + params[15], selector.pList.get(12).get(1), selector.pList.get(12).get(2), MyMath.round(Double.parseDouble(selector.pList.get(12).get(1)) + Double.parseDouble(selector.pList.get(12).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(12).get(1)) - Double.parseDouble(selector.pList.get(12).get(2)),2)},
                            {"  " + params[16], selector.pList.get(10).get(1), selector.pList.get(10).get(2), MyMath.round(Double.parseDouble(selector.pList.get(10).get(1)) + Double.parseDouble(selector.pList.get(10).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(10).get(1)) - Double.parseDouble(selector.pList.get(10).get(2)),2)},
                            {"  " + params[17], selector.pList.get(11).get(1), selector.pList.get(11).get(2), MyMath.round(Double.parseDouble(selector.pList.get(11).get(1)) + Double.parseDouble(selector.pList.get(11).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(11).get(1)) - Double.parseDouble(selector.pList.get(11).get(2)),2)},
                            {"  " + params[18], selector.pList.get(13).get(1), selector.pList.get(13).get(2), MyMath.round(Double.parseDouble(selector.pList.get(13).get(1)) + Double.parseDouble(selector.pList.get(13).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(13).get(1)) - Double.parseDouble(selector.pList.get(13).get(2)),2)},
                            {"  " + params[19], selector.pList.get(73).get(1), selector.pList.get(73).get(2), MyMath.round(Double.parseDouble(selector.pList.get(73).get(1)) + Double.parseDouble(selector.pList.get(73).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(73).get(1)) - Double.parseDouble(selector.pList.get(73).get(2)),2)},
                            {"  " + params[20], selector.pList.get(14).get(1), selector.pList.get(14).get(2), MyMath.round(Double.parseDouble(selector.pList.get(14).get(1)) + Double.parseDouble(selector.pList.get(14).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(14).get(1)) - Double.parseDouble(selector.pList.get(14).get(2)),2)},
                            {"  " + params[21], selector.pList.get(15).get(1), selector.pList.get(15).get(2), MyMath.round(Double.parseDouble(selector.pList.get(15).get(1)) + Double.parseDouble(selector.pList.get(15).get(2)),2), MyMath.round(Double.parseDouble(selector.pList.get(15).get(1)) - Double.parseDouble(selector.pList.get(15).get(2)),2)}
                    };
                    rowCount = rowCount - 8;
                }


                JTable table = new JTable(data, colHeads);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setEnabled(false);
                table.getTableHeader().setFont(font15);
                table.setFont(font15);
                table.setRowHeight(25);
                Renderer renderer = new Renderer(0);
                renderer.setHorizontalAlignment(JLabel.CENTER);

                for (int i=1; i<table.getColumnCount();i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                }

                table.getColumnModel().getColumn(0).setPreferredWidth(180);

                JPanel tablePanel = new JPanel();
                tablePanel.setLayout(new BorderLayout());
                tablePanel.add(table);
                tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

                tablePanel.setSize(580, (rowCount + 1) *table.getRowHeight() - 3);
                tablePanel.setLocation(10, otstup);
                otstup += (rowCount + 1) *table.getRowHeight() - 3;
                container.add(tablePanel);

                if (Settings.isWhoScoredLeague(leagueName)){
                    // Вставка таблицы корреляционных показателей
                    String corrLabelText = "Таблица корреляций статистических показателей " + teamName;
                    final JLabel labelCorr = new JLabel(corrLabelText);
                    labelCorr.setLocation(610, 190);
                    labelCorr.setSize(650, 25);
                    labelCorr.setFont(font15);
                    container.add(labelCorr);

                    JButton buttonCorrInfo = new JButton();
                    buttonCorrInfo.setIcon(new ImageIcon("images/info.png"));
                    buttonCorrInfo.setLocation(610 + 643, 190);
                    buttonCorrInfo.setSize(20, 20);
                    container.add(buttonCorrInfo);

                    buttonCorrInfo.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String textCorr = "<html>    В таблице выводятся коэффициенты корреляции Пирсона (линейные коэффициенты корреляции). <br>" +
                                    "   Они лежат в пределах [-1; 1] и показывают статистическую зависимость одной величины от другой,<br>" +
                                    "   то есть, проще говоря, насколько один параметр зависит от другого.<br>" +
                                    "<br>" +
                                    "   Например: Если значение коэффициента корреляции между владением и угловыми высоко,<br>" +
                                    "   то при невысоком показателе владения команда подает меньше угловых, а при высоком - больше.<br>" +
                                    "   Если же значение коэффициента отрицательное, то при невысоком показателе владения<br>" +
                                    "   команда подает больше угловых.<br>" +
                                    "<br>" +
                                    "   Данная информация может помочь вам при анализе показателей команд.<br>" +
                                    "</html>";


                            PopupWindow windowCorr = new PopupWindow(textCorr);
                            windowCorr.setSize(900, 300);
                            windowCorr.setLocation(500, 120);
                            windowCorr.setVisible(true);
                        }
                    });

                    JPanel panelCorrSP = new JPanel(new BorderLayout());
                    panelCorrSP.setSize(675, 350);
                    panelCorrSP.setLocation(600, 210);
                    JTable panelCorr = TableMaker.getTableCorrelation(teamName, selector.listOfMatches, selector.pList);
                    panelCorrSP.add(panelCorr);
                    panelCorrSP.add(panelCorr.getTableHeader(), BorderLayout.NORTH);
                    container.add(panelCorrSP);
                }


                //Вставка просмотрщика параметров
                final JPanel paramsPanel = new JPanel(new VerticalLayout());
                paramsPanel.setBorder(BorderFactory.createTitledBorder(""));
                JPanel selectorsPanel = new JPanel(new GridLayout(1, 0, 5, 5));

                String[] paramsList = Parameters.getParamsList();
                final JComboBox<String> paramsChooser = new JComboBox<>(paramsList);
                paramsChooser.setFont(font15);
                selectorsPanel.add(paramsChooser);

                String[] indexList = {"Выберите тип ставки"};
                final JComboBox<String> indexChooser = new JComboBox<>(indexList);
                indexChooser.setFont(font15);
                selectorsPanel.add(indexChooser);
                paramsPanel.add(selectorsPanel);

                JPanel panelSlider = new JPanel(new BorderLayout());

                leftValue = new JLabel("");
                leftValue.setFont(font15);
                leftValue.setPreferredSize(new Dimension(40, 40));
                leftValue.setHorizontalAlignment(SwingConstants.CENTER);
                panelSlider.add(leftValue, BorderLayout.WEST);
                rightValue = new JLabel("");
                rightValue.setFont(font15);
                rightValue.setPreferredSize(new Dimension(40, 40));
                rightValue.setHorizontalAlignment(SwingConstants.CENTER);
                panelSlider.add(rightValue, BorderLayout.EAST);
                bottomValue = new JLabel("");
                bottomValue.setFont(font15);
                bottomValue.setPreferredSize(new Dimension(40, 40));
                bottomValue.setHorizontalAlignment(SwingConstants.CENTER);
                panelSlider.add(bottomValue, BorderLayout.SOUTH);


                slider.setPaintLabels(false);
                slider.setMajorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setEnabled(false);
                panelSlider.add(slider);
                panelSlider.setBorder(new EmptyBorder(10, 0, 0, 0));
                paramsPanel.add(panelSlider);

                paramsPanel.setSize(670, 170);
                paramsPanel.setLocation(610, 10);
                container.add(paramsPanel);
                container.setPreferredSize(new Dimension(700, otstup));

//                allInfoPanel.add(container, BorderLayout.NORTH);
                allInfoPanel.add(container);
                //////////////////////////////Конец вставки просмотрщика по параметрам

                ////////////////////////Таблица сводная по параметрам
                if (settings.pivotTable){
                    allInfoPanel.add(TableMaker.getPivotTable(teamName, selector, tournamentTable));
                }

                ////////////////////////ГРАФИКИ И ТАБЛИЦЫ ВСТАВЛЯЮ ТУТ
                //JPanel graphAndTables = new JPanel(new BorderLayout());

                final Graphic graphic;
                if (settings.showGraphics){
                    graphic = new Graphic(1, teamName);
                    JPanel panelG = graphic.getGraphics(teamName, allOrHomeOrAway, lastOrFullSeason, selector, tournamentTable);
                    allInfoPanel.add(panelG);
                } else {
                    graphic = new Graphic(1, teamName);
                    JPanel panelTables = graphic.getTablesWithStats(teamName, allOrHomeOrAway, selector, tournamentTable);
                    allInfoPanel.add(panelTables);
                }


                paramsChooser.setSelectedIndex(indexForParameterChooser);
                String[] list = Parameters.getIndex((String) paramsChooser.getSelectedItem());
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(list);
                indexChooser.setModel(modelH);
                indexChooser.setSelectedIndex(indexForIndexChooser);
                slider.setValue(indexForValueChooser);
                final double[] sliderParams = Parameters.getValues((String) paramsChooser.getSelectedItem(), (String) indexChooser.getSelectedItem());
                stepSlider = sliderParams[2];

                if (sliderParams[2] > 0){
                    minSliderValue = sliderParams[0];
                    stepSlider = sliderParams[2];
                    leftValue.setText(String.valueOf(sliderParams[0]));
                    rightValue.setText(String.valueOf(sliderParams[1]));
                    bottomValue.setText("Выбрано значение: " + String.valueOf(valueForSlider));
                    getParamsPanel(paramsPanel, teamName, selector, paramsChooser, indexChooser, valueForSlider);
                    slider.setMajorTickSpacing(1);
                    slider.setPaintTicks(true);
                    slider.setEnabled(true);
                    int numberOfVariants = (int) ((sliderParams[1] - sliderParams[0]) / sliderParams[2] + 1);
                    slider.setMaximum(numberOfVariants-1);
                }
                ////////////////////////ГРАФИКИ И ТАБЛИЦЫ БОЛЬШЕ НЕ ВСТАВЛЯЮ

                scrollPane = new JScrollPane(allInfoPanel);
                scrollPane.setVerticalScrollBar( new JScrollBar() {
                    public int getUnitIncrement( int direction ) {
                        return 50;
                    }
                } );


                paramsChooser.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        indexForParameterChooser = paramsChooser.getSelectedIndex();
                        String[] list = Parameters.getIndex((String) paramsChooser.getSelectedItem());
                        DefaultComboBoxModel modelH = new DefaultComboBoxModel(list);
                        indexChooser.setModel(modelH);
                        indexForIndexChooser = indexChooser.getSelectedIndex();
                        slider.setEnabled(false);
                        slider.setValue(0);
                        indexForValueChooser = 0;
                        bottomValue.setText("");
                        leftValue.setText("");
                        rightValue.setText("");

                        paramsChooser.setFocusable(false);
                    }
                });

                indexChooser.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        indexForIndexChooser = indexChooser.getSelectedIndex();
                        double[] sliderParams = Parameters.getValues((String) paramsChooser.getSelectedItem(), (String) indexChooser.getSelectedItem());
                        minSliderValue = sliderParams[0];
                        valueForSlider = sliderParams[0];
                        stepSlider = sliderParams[2];
                        slider.setEnabled(true);
                        slider.setValue(0);
                        indexForValueChooser = 0;
                        int numberOfVariants = (int) ((sliderParams[1] - sliderParams[0]) / sliderParams[2] + 1);
                        slider.setMaximum(numberOfVariants-1);
                        leftValue.setText(String.valueOf(sliderParams[0]));
                        rightValue.setText(String.valueOf(sliderParams[1]));
                        bottomValue.setText("Выбрано значение: " + String.valueOf(valueForSlider));

                        getParamsPanel(paramsPanel, teamName, selector, paramsChooser, indexChooser, valueForSlider);
                        indexChooser.setFocusable(false);
                    }
                });

                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        valueForSlider = minSliderValue + stepSlider*slider.getValue();
                        bottomValue.setText("Выбрано значение: " + String.valueOf(valueForSlider));
                        indexForValueChooser = slider.getValue();
                        getParamsPanel(paramsPanel, teamName, selector, paramsChooser, indexChooser, valueForSlider);
                    }
                });

                buttonSite.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String website = Team.getWebsite(teamName);

                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI uri = null;
                        try {
                            uri = new URI("https://www." + website);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            desktop.browse(uri);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                buttonTwitter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String twitter = Team.getTwitter(teamName);

                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI uri = null;
                        try {
                            uri = new URI("https://www.twitter.com/" + twitter);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            desktop.browse(uri);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                buttonMedicine.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String tm = Team.getMedicine(teamName);

                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI uri = null;
                        try {
                            uri = new URI("https://www.transfermarkt.ru/" + tm + "/sperrenundverletzungen/verein/" + tm);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            desktop.browse(uri);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                buttonWeather.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String city = Team.getWeather(teamName);

                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI uri = null;
                        try {
                            uri = new URI("https://www.gismeteo.ru/" + city);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            desktop.browse(uri);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                buttonTransferMarkt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String tm = Team.getTransferMarkt(teamName);

                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI uri = null;
                        try {
                            uri = new URI("https://www.transfermarkt.ru/jumplist/spielplan/verein/" + tm);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            desktop.browse(uri);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });



                final String finalSeason = season;
                buttonTrends.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        WindowTrendsOfTeam wtt = new WindowTrendsOfTeam(teamName, allOrHomeOrAway, finalSeason, lastOrFullSeason, selector);
                        wtt.setVisible(true);
                    }
                });

                buttonStatsByTimes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        WindowWithDiagrams wwd = new WindowWithDiagrams(teamName, allOrHomeOrAway, finalSeason, lastOrFullSeason, selector);
                        wwd.setVisible(true);
                    }
                });

                buttonNotice.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String resultS = "";
                        try {
                            File fileDir = new File("notices/" + teamName + ".txt");

                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                            new FileInputStream(fileDir), "UTF-8"));
                            String str;
                            while (((str = in.readLine()) != null)) {
                                resultS += str + "\n";
                            }
                            in.close();

                        } catch (IOException ignored){
                        }
                        final JFrame windowNotice = new JFrame("Заметка о команде " + teamName);
                        windowNotice.setLayout(new BorderLayout());
                        windowNotice.setSize(400, 300);
                        windowNotice.setLocation(100, 100);

                        final JTextArea textArea = new JTextArea(resultS);
                        textArea.setFont(font15);
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
                        scrollPane.add(textArea);
                        windowNotice.add(scrollPane);

                        final JButton saveNotice = new JButton("Сохранить!");
                        saveNotice.setFont(font15);
                        saveNotice.setEnabled(false);
                        windowNotice.add(saveNotice, BorderLayout.SOUTH);

                        windowNotice.setVisible(true);

                        textArea.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                saveNotice.setEnabled(true);
                                saveNotice.setText("Сохранить!");
                            }
                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                saveNotice.setEnabled(true);
                                saveNotice.setText("Сохранить!");
                            }
                            @Override
                            public void changedUpdate(DocumentEvent e) {
                            }
                        });

                        saveNotice.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                FileWriter fr;
                                BufferedWriter br;
                                File file = new File("notices/" + teamName + ".txt");
                                try {
                                    //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
                                    fr = new FileWriter(file,false);
                                    br = new BufferedWriter(fr);
                                    //теперь мы можем использовать метод write или метод append
                                    br.write(textArea.getText());

                                    br.close();
                                    fr.close();
                                    saveNotice.setText("Данные сохранены!");
                                    saveNotice.setEnabled(false);
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        });

                        windowNotice.addWindowListener(new WindowListener() {
                            @Override
                            public void windowOpened(WindowEvent e) {

                            }
                            @Override
                            public void windowClosing(WindowEvent e) {
                                if (saveNotice.isEnabled()){
                                    final JFrame windowExit = new JFrame("Внимание!");
                                    windowExit.setLayout(new BorderLayout());
                                    windowExit.setSize(500, 500);
                                    windowExit.setLocation(100, 100);

                                    JLabel label = new JLabel("Сохранить внесенные изменения?");
                                    label.setFont(font15);
                                    windowExit.add(label, BorderLayout.NORTH);

                                    JPanel panelButtons = new JPanel(new GridLayout(1,2,3,0));
                                    JButton buttonYes = new JButton("ДА");
                                    buttonYes.setFont(font15);
                                    panelButtons.add(buttonYes);
                                    JButton buttonNo = new JButton("НЕТ");
                                    buttonNo.setFont(font15);
                                    panelButtons.add(buttonNo);
                                    windowExit.add(panelButtons);
                                    windowExit.setVisible(true);
                                    windowExit.pack();

                                    buttonYes.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            saveNotice.doClick();
                                            windowNotice.dispose();
                                            windowExit.dispose();
                                        }
                                    });

                                    buttonNo.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            windowNotice.dispose();
                                            windowExit.dispose();
                                        }
                                    });

                                } else {

                                }
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
                });

                JPanel searchPanel = new JPanel(new GridLayout(1, 3, 5, 5));
                JButton buttonUp = new JButton("Вверх");
                buttonUp.setFont(font18);
                searchPanel.add(buttonUp);

                final JTextField searchTextField = new JTextField("Перейти к:");
                searchTextField.setFont(font18);
                searchPanel.add(searchTextField);

                JButton buttonDown = new JButton("Вниз");
                buttonDown.setFont(font18);
                searchPanel.add(buttonDown);

                searchPanel.setEnabled(false);
                result.add(searchPanel, BorderLayout.SOUTH);

                buttonUp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
                    }
                });

                buttonDown.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
                    }
                });

                DocumentListener dl = new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        String text = searchTextField.getText();

                        if (!text.equals("")){
                            int indexOfFoundGraphic = 0;
                            int containerHeight = container.getHeight();
                            for (String s : graphic.graphicTitles){
                                if (s.toUpperCase().contains(text.toUpperCase().trim())){
                                    indexOfFoundGraphic = graphic.graphicTitles.indexOf(s);
                                    break;
                                }
                            }
                            int resHeight;
                            if (settings.showGraphics){
                                resHeight = containerHeight + indexOfFoundGraphic*graphic.graphicHeight;
                            } else {
                                resHeight = containerHeight;
                                for (int i=0; i< indexOfFoundGraphic; i++)
                                    resHeight += graphic.heights.get(i);
                            }
                            scrollPane.getVerticalScrollBar().setValue(resHeight);
                        }

                    }

                    public void removeUpdate(DocumentEvent e) {
                        insertUpdate(e);
                    }

                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("Was change...");
                    }
                };

                searchTextField.getDocument().addDocumentListener(dl);

                searchTextField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        searchTextField.selectAll();
                    }
                    @Override
                    public void focusLost(FocusEvent e) {

                    }
                });

            } else {
                JPanel container = new JPanel(new BorderLayout());

                String labelText = "";
                if (allOrHomeOrAway.contains("Все")){
                    labelText = "   В сезоне " + season + " команда " + teamName + " не провела ни одного матча.";
                }
                if (allOrHomeOrAway.contains("Дома")){
                    labelText = "   В сезоне " + season + " команда " + teamName + " не провела ни одного матча на своем поле.";
                }
                if (allOrHomeOrAway.contains("На выезде")){
                    labelText = "   В сезоне " + season + " команда " + teamName + " не провела ни одного матча на выезде.";
                }
                final JLabel label = new JLabel(labelText);
                label.setLocation(10, 0);
                label.setPreferredSize(new Dimension(500, 25));
                label.setFont(font15);
                container.add(label, BorderLayout.NORTH);

                scrollPane = new JScrollPane(container);
                scrollPane.setVerticalScrollBar( new JScrollBar() {
                    public int getUnitIncrement( int direction ) {
                        return 50;
                    }
                } );
            }
        } else {
            JPanel container = new JPanel(new BorderLayout());

            final JLabel label = new JLabel("   Задайте все условия поиска. Лига и/или команда не заданы.");
            label.setPreferredSize(new Dimension(500, 25));
            label.setFont(font15);
            container.add(label, BorderLayout.NORTH);

            scrollPane = new JScrollPane(container);
            scrollPane.setVerticalScrollBar(new JScrollBar() {
                public int getUnitIncrement(int direction) {
                    return 50;
                }
            });
        }
        this.setCursor(Cursor.getDefaultCursor());
        result.add(scrollPane);

        return result;
    }

    private void getParamsPanel(JPanel paramsPanel, String teamName, Selector selector, JComboBox paramsChooser, JComboBox indexChooser, double valueOfSlider){
        if (paramsPanel.getComponentCount()>2){
            paramsPanel.remove(2);
        }
        final JTable tableByParams = Parameters.getTableByParams(teamName, selector.listOfMatches, (String) (paramsChooser.getSelectedItem()), (String) (indexChooser.getSelectedItem()), valueOfSlider);
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);
        tablePanel.setSize(600,44);
        tablePanel.setLocation(0,40);

        tableByParams.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableByParams.setEnabled(false);
        tableByParams.getTableHeader().setFont(font15);
        tableByParams.setFont(font15);
        tableByParams.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0; i<tableByParams.getColumnCount(); i++){
            tableByParams.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(tableByParams), BorderLayout.CENTER);
        tablePanel.add(tableByParams.getTableHeader(), BorderLayout.NORTH);

        tableByParams.setSize(200, 180);
        tableByParams.setLocation(5, 5);

        tablePanel.add(tableByParams);
        paramsPanel.add(tablePanel);
        paramsPanel.revalidate();
    }

    public void setFilters(String league){
        String season = Settings.getCurrentSeasonInLeague(league);
        seasonChooser.setSelectedItem("Сезон " + season);
        leagueChooser.setSelectedItem(league);
    }
}