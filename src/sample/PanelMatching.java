package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PanelMatching extends JPanel{
    JComboBox<String> leftSeasonChooser;
    JComboBox<String> leftLeagueChooser;
    JComboBox<String> leftTeamChooser;
    JComboBox<String> leftTeamAllOrHomeOrAway;
    JComboBox<String> leftLastOrFullSeason;
    JPanel leftTeamPanel;
    JPanel leftInfoPanel;

    JComboBox<String> rightSeasonChooser;
    JComboBox<String> rightLeagueChooser;
    JComboBox<String> rightTeamChooser;
    JComboBox<String> rightTeamAllOrHomeOrAway;
    JComboBox<String> rightLastOrFullSeason;
    JPanel rightTeamPanel;
    JPanel rightInfoPanel;

    final Font font15 = new Font("Arial", Font.BOLD, 15);
    final Font font16 = new Font("Arial", Font.BOLD, 16);
    final Font font18 = new Font("Arial", Font.BOLD, 18);
    final Font font20 = new Font("Arial", Font.BOLD, 20);
    int heightOfTable = 0;

    public PanelMatching() {
        this.setLayout(new GridLayout(1,2));
        final String path = "database/";
        String curSeason = Settings.getDefaultSeason();

        ////////////////////////////////////////////ЛЕВАЯ ПАНЕЛЬ
        leftTeamPanel = new JPanel(new BorderLayout());
        leftTeamPanel.setBorder(BorderFactory.createTitledBorder(""));

        JPanel leftPanelChoosers = new JPanel(new GridLayout(1, 0, 2, 2));
        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        leftSeasonChooser = new JComboBox<>(seasonList);
        //seasonChooser.setPreferredSize(new Dimension((int) (130 * procWIDTH), 30));
        leftPanelChoosers.add(leftSeasonChooser);

        JFileChooser leftFileChooser = new JFileChooser(path + curSeason + "/leagues");
        String[] leftDirectoryList = leftFileChooser.getCurrentDirectory().list();
        ArrayList<String> leftLeagueList = new ArrayList<>();
        leftLeagueList.add("Выберите лигу");
        for (String aDirectoryList : leftDirectoryList) leftLeagueList.add(aDirectoryList.replace(".txt", ""));
        String[] leftListOfLeagues = new String[leftLeagueList.size()];
        for (int i = 0; i < leftListOfLeagues.length; i++)
            leftListOfLeagues[i] = leftLeagueList.get(i);
        leftLeagueChooser = new JComboBox<>(leftListOfLeagues);
        leftPanelChoosers.add(leftLeagueChooser);

        String fileNameLeft = path + curSeason + "/leagues/" + leftLeagueChooser.getSelectedItem() + ".txt";
        String[] listLeft = {"Выберите команду"};
        if (!fileNameLeft.contains("ыберите")) {
            listLeft = Main.readTxtFile(fileNameLeft);
        }
        leftTeamChooser = new JComboBox<>(listLeft);
        leftTeamChooser.setEnabled(false);
        leftPanelChoosers.add(leftTeamChooser);

        String[] leftAllOrHomeOrAway = {"Все матчи", "Дома", "На выезде"};
        leftTeamAllOrHomeOrAway = new JComboBox<>(leftAllOrHomeOrAway);
        leftPanelChoosers.add(leftTeamAllOrHomeOrAway);

        String[] leftLastOrFullSeasonString = {"Весь сезон", "Последние 3", "Последние 4", "Последние 5", "Последние 6", "Последние 7", "Последние 8", "Последние 9", "Последние 10"};
        leftLastOrFullSeason = new JComboBox<>(leftLastOrFullSeasonString);
        leftPanelChoosers.add(leftLastOrFullSeason);

        final JButton leftButtonShowInfo = new JButton("Отобразить!");
        leftButtonShowInfo.setFont(font15);
        leftButtonShowInfo.setMargin(new Insets(0, 0, 0, 0));
        leftPanelChoosers.add(leftButtonShowInfo);

        leftTeamPanel.add(leftPanelChoosers, BorderLayout.NORTH);

        leftInfoPanel = new JPanel();
        leftTeamPanel.add(leftInfoPanel, BorderLayout.CENTER);

        this.add(leftTeamPanel);

        leftSeasonChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leftSeasonChooser.setFocusable(false);

                String team = String.valueOf(leftTeamChooser.getSelectedItem());
                String league = String.valueOf(leftLeagueChooser.getSelectedItem());

                String pathToLeaguesList = path + leftSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser leftFileChooser = new JFileChooser(pathToLeaguesList);
                String[] leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length + 1];
                leftDirectoryList[0] = "Выберите лигу";
                for (int i = 1; i < leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i - 1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(leftDirectoryList);
                leftLeagueChooser.setModel(modelH);

                for (int i = 0; i < leftDirectoryList.length; i++) {
                    if (leftDirectoryList[i].equals(league))
                        leftLeagueChooser.setSelectedItem(league);
                }

                String pathToTeamsList = path + leftSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leftLeagueChooser.getSelectedItem() + ".txt";
                String[] listLeft = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listLeft = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listLeft);
                leftTeamChooser.setModel(modelH);
                leftTeamChooser.setEnabled(false);

                pathToTeamsList = path + leftSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Teams/";
                leftFileChooser = new JFileChooser(pathToTeamsList);
                leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length + 1];
                leftDirectoryList[0] = "Выберите команду";
                for (int i = 1; i < leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i - 1].replace(".xml", "");
                for (int i = 0; i < leftDirectoryList.length; i++) {
                    if (leftDirectoryList[i].equals(team)) {
                        leftTeamChooser.setSelectedItem(team);
                        leftTeamChooser.setEnabled(true);
                    }
                }
                leftLeagueChooser.setFocusable(true);
            }
        });

        leftLeagueChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = leftLeagueChooser.getSelectedIndex();
                String pathToLeaguesList = path + leftSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser leftFileChooser = new JFileChooser(pathToLeaguesList);
                String[] leftDirectoryList = new String[leftFileChooser.getCurrentDirectory().list().length+1];
                leftDirectoryList[0] = "Выберите лигу";
                for (int i=1; i<leftDirectoryList.length; i++)
                    leftDirectoryList[i] = leftFileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(leftDirectoryList);
                leftLeagueChooser.setModel(modelH);
                leftLeagueChooser.setSelectedIndex(index);
                leftLeagueChooser.setFocusable(false);

                leftTeamChooser.setEnabled(true);
                String pathToTeamsList = path + leftSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leftLeagueChooser.getSelectedItem() + ".txt";
                String[] listLeft = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listLeft = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listLeft);
                leftTeamChooser.setModel(modelH);
                leftTeamAllOrHomeOrAway.setFocusable(false);
            }
        });

        leftTeamChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leftTeamChooser.setFocusable(false);
                leftTeamAllOrHomeOrAway.setFocusable(true);
            }
        });

        leftTeamAllOrHomeOrAway.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leftTeamAllOrHomeOrAway.setFocusable(false);
            }
        });

        leftButtonShowInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftInfoPanel != null) {
                    leftTeamPanel.remove(leftInfoPanel);
                }
                leftInfoPanel = refreshData((String) leftLeagueChooser.getSelectedItem(),
                        (String) leftTeamChooser.getSelectedItem(),
                        (String) leftTeamAllOrHomeOrAway.getSelectedItem(),
                        (String) leftSeasonChooser.getSelectedItem(),
                        (String) leftLastOrFullSeason.getSelectedItem()
                );

                leftTeamPanel.add(leftInfoPanel);
                leftTeamPanel.revalidate();
                leftButtonShowInfo.setFocusable(false);
            }
        });

        ////////////////////////////////////////////ПРАВАЯ ПАНЕЛЬ
        rightTeamPanel = new JPanel(new BorderLayout());
        rightTeamPanel.setBorder(BorderFactory.createTitledBorder(""));

        JPanel rightPanelChoosers = new JPanel();
        rightPanelChoosers.setLayout(new GridLayout(1, 0, 2, 2));

        rightSeasonChooser = new JComboBox<>(seasonList);
//        rightSeasonChooser.setPreferredSize(new Dimension((int) (130 * procWIDTH), 30));
        rightPanelChoosers.add(rightSeasonChooser);

        JFileChooser rightFileChooser = new JFileChooser(path + curSeason + "/leagues");
        String[] rightDirectoryList = rightFileChooser.getCurrentDirectory().list();
        ArrayList<String> rightLeagueList = new ArrayList<>();
        rightLeagueList.add("Выберите лигу");
        for (String aDirectoryList : rightDirectoryList) rightLeagueList.add(aDirectoryList.replace(".txt", ""));
        String[] rightListOfLeagues = new String[rightLeagueList.size()];
        for (int i = 0; i < rightListOfLeagues.length; i++)
            rightListOfLeagues[i] = rightLeagueList.get(i);
        rightLeagueChooser = new JComboBox<>(rightListOfLeagues);
//        rightLeagueChooser.setPreferredSize(new Dimension((int) (150 * procWIDTH), 30));
        rightPanelChoosers.add(rightLeagueChooser);

        String fileNameRight = path + curSeason + "/leagues/" + rightLeagueChooser.getSelectedItem() + ".txt";
        String[] listRight = {"Выберите команду"};
        if (!fileNameRight.contains("ыберите")) {
            listRight = Main.readTxtFile(fileNameRight);
        }
        rightTeamChooser = new JComboBox<>(listRight);
//        rightTeamChooser.setPreferredSize(new Dimension((int) (170 * procWIDTH), 30));
        rightTeamChooser.setEnabled(false);
        rightPanelChoosers.add(rightTeamChooser);

        String[] rightAllOrHomeOrAway = {"Все матчи", "Дома", "На выезде"};
        rightTeamAllOrHomeOrAway = new JComboBox<>(rightAllOrHomeOrAway);
//        rightTeamAllOrHomeOrAway.setPreferredSize(new Dimension((int) (150 * procWIDTH), 30));
        rightPanelChoosers.add(rightTeamAllOrHomeOrAway);

        String[] rightLastOrFullSeasonString = {"Весь сезон", "Последние 3", "Последние 4", "Последние 5", "Последние 6", "Последние 7", "Последние 8", "Последние 9", "Последние 10"};
        rightLastOrFullSeason = new JComboBox<>(rightLastOrFullSeasonString);
//        rightLastOrFullSeason.setPreferredSize(new Dimension((int) (150 * procWIDTH), 30));
        rightPanelChoosers.add(rightLastOrFullSeason);

        final JButton rightButtonShowInfo = new JButton("Отобразить!");
//        rightButtonShowInfo.setPreferredSize(new Dimension((int) (150 * procWIDTH), 30));
        rightButtonShowInfo.setFont(font15);
        rightButtonShowInfo.setMargin(new Insets(0, 0, 0, 0));
        rightPanelChoosers.add(rightButtonShowInfo);

        rightTeamPanel.add(rightPanelChoosers, BorderLayout.NORTH);

        rightInfoPanel = new JPanel();
        rightTeamPanel.add(rightInfoPanel, BorderLayout.CENTER);

        this.add(rightTeamPanel);

        rightSeasonChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rightSeasonChooser.setFocusable(false);

                String team = String.valueOf(rightTeamChooser.getSelectedItem());
                String league = String.valueOf(rightLeagueChooser.getSelectedItem());

                String pathToLeaguesList = path + rightSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser rightFileChooser = new JFileChooser(pathToLeaguesList);
                String[] rightDirectoryList = new String[rightFileChooser.getCurrentDirectory().list().length + 1];
                rightDirectoryList[0] = "Выберите лигу";
                for (int i = 1; i < rightDirectoryList.length; i++)
                    rightDirectoryList[i] = rightFileChooser.getCurrentDirectory().list()[i - 1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(rightDirectoryList);
                rightLeagueChooser.setModel(modelH);

                for (int i = 0; i < rightDirectoryList.length; i++) {
                    if (rightDirectoryList[i].equals(league))
                        rightLeagueChooser.setSelectedItem(league);
                }

                String pathToTeamsList = path + rightSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + rightLeagueChooser.getSelectedItem() + ".txt";
                String[] listRight = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listRight = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listRight);
                rightTeamChooser.setModel(modelH);
                rightTeamChooser.setEnabled(false);

                pathToTeamsList = path + rightSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Teams/";
                rightFileChooser = new JFileChooser(pathToTeamsList);
                rightDirectoryList = new String[rightFileChooser.getCurrentDirectory().list().length + 1];
                rightDirectoryList[0] = "Выберите команду";
                for (int i = 1; i < rightDirectoryList.length; i++)
                    rightDirectoryList[i] = rightFileChooser.getCurrentDirectory().list()[i - 1].replace(".xml", "");
                for (int i = 0; i < rightDirectoryList.length; i++) {
                    if (rightDirectoryList[i].equals(team)) {
                        rightTeamChooser.setSelectedItem(team);
                        rightTeamChooser.setEnabled(true);
                    }
                }
                rightLeagueChooser.setFocusable(true);
            }
        });

        rightLeagueChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = rightLeagueChooser.getSelectedIndex();
                String pathToLeaguesList = path + rightSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser rightFileChooser = new JFileChooser(pathToLeaguesList);
                String[] rightDirectoryList = new String[rightFileChooser.getCurrentDirectory().list().length+1];
                rightDirectoryList[0] = "Выберите лигу";
                for (int i=1; i<rightDirectoryList.length; i++)
                    rightDirectoryList[i] = rightFileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(rightDirectoryList);
                rightLeagueChooser.setModel(modelH);
                rightLeagueChooser.setSelectedIndex(index);
                rightLeagueChooser.setFocusable(false);

                rightTeamChooser.setEnabled(true);
                String pathToTeamsList = path + rightSeasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + rightLeagueChooser.getSelectedItem() + ".txt";
                String[] listRight = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listRight = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listRight);
                rightTeamChooser.setModel(modelH);
                rightTeamAllOrHomeOrAway.setFocusable(false);

            }
        });

        rightTeamChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rightTeamChooser.setFocusable(false);
                rightTeamAllOrHomeOrAway.setFocusable(true);
            }
        });

        rightTeamAllOrHomeOrAway.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rightTeamAllOrHomeOrAway.setFocusable(false);
            }
        });

        rightButtonShowInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rightInfoPanel != null) {
                    rightTeamPanel.remove(rightInfoPanel);
                }
                rightInfoPanel = refreshData((String) rightLeagueChooser.getSelectedItem(),
                        (String) rightTeamChooser.getSelectedItem(),
                        (String) rightTeamAllOrHomeOrAway.getSelectedItem(),
                        (String) rightSeasonChooser.getSelectedItem(),
                        (String) rightLastOrFullSeason.getSelectedItem()
                );

                rightTeamPanel.add(rightInfoPanel);
                rightTeamPanel.revalidate();
                rightButtonShowInfo.setFocusable(false);
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
            ArrayList<String> tournamentTable = League.getLeagueFromFile(leagueName, season).tournamentTable;
            final Selector selector = new Selector();
            selector.getListOfMatches(leagueName, teamName, allOrHomeOrAway, season, lastOrFullSeason);
            selector.getPList(selector.listOfMatches, teamName);

            if (selector.listOfMatches.size()>0){
//                JPanel allInfoPanel = new JPanel(new BorderLayout());
                JPanel allInfoPanel = new JPanel(new VerticalLayout());

                final JPanel container = new JPanel();
                container.setLayout(null);
                int otstup = 0;

                if (!Settings.isWhoScoredLeague(leagueName)){
                    JLabel labelAttention = new JLabel("ВНИМАНИЕ! Данные по данной лиге могут быть не в полном объеме.");
                    labelAttention.setFont(font18);
                    labelAttention.setForeground(Color.RED);
                    labelAttention.setLocation(10, otstup);
                    otstup += 30;
                    container.add(labelAttention);
                }


                int matches = selector.listOfMatches.size();
                int wins = (int) Double.parseDouble(selector.pList.get(0).get(1));
                int draws = (int) Double.parseDouble(selector.pList.get(1).get(1));
                int loses = (int) Double.parseDouble(selector.pList.get(2).get(1));
                int points = (int) Double.parseDouble(selector.pList.get(3).get(1));
                String teamStats = "Матчей: " + matches + ";    Побед: " + wins + ";    Ничьих: "
                        + draws + ";    Поражений: " + loses +";    Набрано очков: " + points + ";\n";
                final JTextField label = new JTextField(teamStats);
                label.setBackground(new Color(238, 238, 238));
                label.setLocation(10, otstup);
                label.setBorder(BorderFactory.createEmptyBorder());
                otstup += 25;
                label.setSize(new Dimension(600, 25));
                label.setFont(font15);
                container.add(label);

                int xGmatches = (int) Double.parseDouble(selector.pList.get(17).get(1));
                double xWins = Double.parseDouble(selector.pList.get(18).get(1));
                double xDraws = Double.parseDouble(selector.pList.get(19).get(1));
                double xLoses = Double.parseDouble(selector.pList.get(20).get(1));
                double xPoints = MyMath.round(Double.parseDouble(selector.pList.get(21).get(1)), 2);
                String teamStatsxG = "xGMatches: " + xGmatches + ";   xWins: " + xWins + ";   wDraws: "
                        + xDraws + ";   xLoses: " + xLoses +";   xPoints: " + xPoints + ";";
                final JTextField label2 = new JTextField(teamStatsxG);
                label2.setBackground(new Color(238, 238, 238));
                label2.setLocation(10, otstup);
                label2.setBorder(BorderFactory.createEmptyBorder());
                otstup += 25;
                label2.setSize(new Dimension(600, 25));
                label2.setFont(font15);
                container.add(label2);

                int goalsS = (int) Math.round(selector.listOfMatches.size() * Double.parseDouble(selector.pList.get(4).get(1)));
                int goalsC = (int) Math.round(selector.listOfMatches.size() * Double.parseDouble(selector.pList.get(4).get(2)));
                int diff = goalsS - goalsC;
                String teamGoals = "Голов забито:   " + goalsS + ";                 Голов пропущено:   " + goalsC +
                        ";                   Разница:   " + diff + ";";
                final JTextField label3 = new JTextField(teamGoals);
                label3.setBackground(new Color(238, 238, 238));
                label3.setLocation(10, otstup);
                label3.setBorder(BorderFactory.createEmptyBorder());
                otstup += 25;
                label3.setSize(new Dimension(600, 25));
                label3.setFont(font15);
                container.add(label3);

                String forma = selector.pList.get(16).get(1);
                if (settings.form.equals("rightToLeft")){
                    StringBuilder s = new StringBuilder();
                    for (int j=forma.length()-1; j>=0; j--){
                        s.append(forma.charAt(j));
                    }
                    forma = s.toString();
                }
                final Dimension defFormaLocation = new Dimension(70,78);
                for (int i=0; i<forma.length(); i++){
                    JLabel imageLabel = null;
                    if (forma.charAt(i) == 'W'){
                        imageLabel = new JLabel(new ImageIcon("images/win.png"));
                    }
                    if (forma.charAt(i) == 'D'){
                        imageLabel = new JLabel(new ImageIcon("images/draw.png"));
                    }
                    if (forma.charAt(i) == 'L'){
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
                label4.setLocation(10, otstup);
                otstup += 30;
                label4.setSize(new Dimension(370, 25));
                label4.setFont(font15);
                container.add(label4);

                String sources = "Сайт / Твиттер / Травмы / Погода / Transfermarkt:";
                final JLabel labelSources = new JLabel(sources);
                labelSources.setLocation(10,otstup);
                otstup += 35;
                labelSources.setSize(new Dimension(600, 25));
                labelSources.setFont(font15);
                container.add(labelSources);

                int horAlignment = 375;
                final JButton buttonSite = new JButton(new ImageIcon("images/www.png"));
                if (Team.getWebsite(teamName).equals("website"))
                    buttonSite.setEnabled(false);
                buttonSite.setLocation(horAlignment, 100);
                buttonSite.setSize(32,32);
                container.add(buttonSite);

                JButton buttonTwitter = new JButton(new ImageIcon("images/twitter.png"));
                if (Team.getTwitter(teamName).equals("twitter"))
                    buttonTwitter.setEnabled(false);
                buttonTwitter.setLocation(horAlignment + 32 + 5, 100);
                buttonTwitter.setSize(32,32);
                container.add(buttonTwitter);

                JButton buttonMedicine = new JButton(new ImageIcon("images/medicine.png"));
                if (Team.getTransferMarkt(teamName).equals("medicine"))
                    buttonMedicine.setEnabled(false);
                buttonMedicine.setLocation(horAlignment + (32 + 5) * 2, 100);
                buttonMedicine.setSize(32,32);
                container.add(buttonMedicine);

                JButton buttonWeather = new JButton(new ImageIcon("images/weather.png"));
                if (Team.getWeather(teamName).equals("weather"))
                    buttonWeather.setEnabled(false);
                buttonWeather.setLocation(horAlignment + (32 + 5) * 3, 100);
                buttonWeather.setSize(32, 32);
                container.add(buttonWeather);

                JButton buttonTransferMarkt = new JButton(new ImageIcon("images/transfermarkt.png"));
                if (Team.getTransferMarkt(teamName).equals("transferMarkt"))
                    buttonTransferMarkt.setEnabled(false);
                buttonTransferMarkt.setLocation(horAlignment + (32 + 5)*4, 100);
                buttonTransferMarkt.setSize(32,32);
                container.add(buttonTransferMarkt);

                JButton buttonTrends = new JButton("Тренды");
                buttonTrends.setLocation(10, otstup);
                buttonTrends.setFont(font15);
                buttonTrends.setSize(100, 30);
                container.add(buttonTrends);

                final JButton buttonDiagrams = new JButton("Диаграммы показателей");
                buttonDiagrams.setLocation(130, otstup);
                buttonDiagrams.setFont(font15);
                buttonDiagrams.setSize(220, 30);
                container.add(buttonDiagrams);

                JButton buttonNotice = new JButton("Моя заметка");
                buttonNotice.setLocation(370, otstup);
                buttonNotice.setFont(font15);
                buttonNotice.setSize(150, 30);
                container.add(buttonNotice);

                otstup += 30;


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
                label5.setLocation(10, otstup);
                otstup += 25;
                label5.setSize(new Dimension(600, 25));
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
//                renderer.setHorizontalAlignment(JLabel.CENTER);

                for (int i=1; i<table.getColumnCount();i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                }

                table.getColumnModel().getColumn(0).setPreferredWidth(180);

                JPanel tablePanel = new JPanel();
                tablePanel.setLayout(new BorderLayout());
                tablePanel.add(table);
                tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

                tablePanel.setSize(600, (rowCount + 1) *table.getRowHeight() - 3);
                tablePanel.setLocation(10, otstup);
                otstup += (rowCount + 1) *table.getRowHeight();
                container.add(tablePanel);

                container.setPreferredSize(new Dimension(500, otstup));
//                allInfoPanel.add(container,BorderLayout.NORTH);
                allInfoPanel.add(container);


                ////////////////////////Таблица сводная по параметрам
                if (settings.pivotTable){
                    allInfoPanel.add(TableMaker.getPivotTable(teamName, selector, tournamentTable));
                }

                ////////////////////////ГРАФИКИ ВСТАВЛЯЮ ТУТ
                final Graphic graphic;
                final JPanel panelG;
                if (settings.showGraphics){
                    graphic = new Graphic(0, teamName);
                    panelG= graphic.getGraphics(teamName, allOrHomeOrAway, lastOrFullSeason, selector, tournamentTable);
                    //panelG.setPreferredSize(new Dimension(300,1000));
                    allInfoPanel.add(panelG);
                } else {
                    graphic = new Graphic(0, teamName);
                    panelG = graphic.getTablesWithStats(teamName, allOrHomeOrAway, selector, tournamentTable);
                    allInfoPanel.add(panelG);
                }

                ////////////////////////ГРАФИКИ БОЛЬШЕ НЕ ВСТАВЛЯЮ

                scrollPane = new JScrollPane(allInfoPanel);
                scrollPane.setVerticalScrollBar( new JScrollBar() {
                    public int getUnitIncrement( int direction ) {
                        return 50;
                    }
                } );

                buttonSite.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String website = Team.getWebsite(teamName);

                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI uri = null;
                        try {
                            uri = new URI("http://www." + website);
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
                            uri = new URI("http://www.twitter.com/" + twitter);
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
                            uri = new URI("http://www.transfermarkt.ru/" + tm + "/sperrenundverletzungen/verein/" + tm);
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
                            uri = new URI("http://www.gismeteo.ru/" + city);
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
                            uri = new URI("http://www.transfermarkt.ru/jumplist/spielplan/verein/" + tm);
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

                buttonDiagrams.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        WindowWithDiagrams wwd = new WindowWithDiagrams(teamName, allOrHomeOrAway, finalSeason, lastOrFullSeason, selector);
                        wwd.setVisible(true);
                    }
                });

                buttonNotice.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        StringBuilder resultS = new StringBuilder();
                        try {
                            File fileDir = new File("notices/" + teamName + ".txt");

                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
                            String str;
                            while (((str = in.readLine()) != null)) {
                                resultS.append(str).append("\n");
                            }
                            in.close();

                        } catch (IOException ignored){
                        }
                        final JFrame windowNotice = new JFrame("Заметка о команде " + teamName);
                        windowNotice.setLayout(new BorderLayout());
                        windowNotice.setSize(400, 300);
                        windowNotice.setLocation(100, 100);

                        final JTextArea textArea = new JTextArea(resultS.toString());
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
                                Writer fstream = null;
                                BufferedWriter out = null;

                                FileWriter fr;
                                BufferedWriter br;
                                File file = new File("notices/" + teamName + ".txt");
                                try {
                                    //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
//                                    fr = new FileWriter(file,false);
                                    fstream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                                    br = new BufferedWriter(fstream);
                                    br.write(textArea.getText());

                                    br.close();
                                    fstream.close();
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
                                heightOfTable = panelG.getHeight() / graphic.graphicTitles.size();
                                resHeight = containerHeight + indexOfFoundGraphic*heightOfTable;
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
                Font fontLabel = new Font("Arial", Font.BOLD, 15);
                label.setFont(fontLabel);
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
            Font fontLabel = new Font("Arial", Font.BOLD, 15);
            label.setFont(fontLabel);
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

    public void setFilters(String league, String homeTeam, String awayTeam){
        String season = Settings.getCurrentSeasonInLeague(league);
        leftSeasonChooser.setSelectedItem("Сезон " + season);
        rightSeasonChooser.setSelectedItem("Сезон " + season);
        leftLeagueChooser.setSelectedItem(league);
        rightLeagueChooser.setSelectedItem(league);
        leftTeamChooser.setSelectedItem(homeTeam);
        rightTeamChooser.setSelectedItem(awayTeam);
        leftTeamAllOrHomeOrAway.setSelectedItem("Дома");
        rightTeamAllOrHomeOrAway.setSelectedItem("На выезде");
        leftLastOrFullSeason.setSelectedItem("Весь сезон");
        rightLastOrFullSeason.setSelectedItem("Весь сезон");
    }
}