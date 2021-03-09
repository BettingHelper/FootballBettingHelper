package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;

public class PanelBeforeAfter extends JPanel{
    final Font font15 = new Font("Arial", Font.BOLD, 15);
    final Font font18 = new Font("Arial", Font.BOLD, 18);
    final Font font20 = new Font("Arial", Font.BOLD, 20);

    //JScrollPane leftScrollPane;
    //JScrollPane rightScrollPane;
    JComboBox<String> seasonChooser;
    JComboBox<String> leagueChooser;
    JComboBox<String> teamChooser;
    JComboBox<String> teamAllOrHomeOrAway;
    JButton buttonShowInfo;
    JPanel leftTeamPanel;
    JPanel rightTeamPanel;
    JPanel leftInfoPanel;
    JPanel rightInfoPanel;

    JPanel sliderPanel;
    String leagueName;
    String teamName;
    String allOrHomeOrAway;
    String lastOrFullSeason;
    String season;

    JSlider slider = new JSlider(0,10,0);
    Hashtable<Integer, JLabel> labels;
    JPanel panelTableWithBound = new JPanel();
    JButton buttonShowBeforeAfterStats;
    final JPanel panelScrolls;

    public PanelBeforeAfter() {
//        this.setLayout(new VerticalLayout());
        this.setLayout(new BorderLayout());

        final JPanel allInfoPanel = new JPanel(new BorderLayout());
        panelScrolls = new JPanel(new GridLayout(1, 0 , 0, 0));

        final String path = "database/";
        String curSeason = Settings.getDefaultSeason();

        JPanel panelChoosers = new JPanel(new GridLayout(1, 0, 5, 5));
        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonChooser = new JComboBox<>(seasonList);
        //seasonChooser.setPreferredSize(new Dimension((int) (130 * procWIDTH), 30));
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
        String[] listOfTeams = {"Выберите команду"};
        if (!fileName.contains("ыберите")) {
            listOfTeams = Main.readTxtFile(fileName);
        }
        teamChooser = new JComboBox<>(listOfTeams);
        teamChooser.setEnabled(false);
        panelChoosers.add(teamChooser);

        String[] allOrHomeOrAwayList = {"Все матчи", "Дома", "На выезде"};
        teamAllOrHomeOrAway = new JComboBox<>(allOrHomeOrAwayList);
        panelChoosers.add(teamAllOrHomeOrAway);

        Font fontForButton = new Font("", 0, 16);
        buttonShowInfo = new JButton("Отобразить!");
        buttonShowInfo.setFont(fontForButton);
        buttonShowInfo.setMargin(new Insets(0, 0, 0, 0));
        panelChoosers.add(buttonShowInfo);

        this.add(panelChoosers, BorderLayout.NORTH);

        final JPanel panelWithSlider = new JPanel(new BorderLayout());
        sliderPanel = new JPanel();
        panelWithSlider.add(sliderPanel);
        allInfoPanel.add(panelWithSlider, BorderLayout.NORTH);

        leftTeamPanel = new JPanel(new BorderLayout());
        leftTeamPanel.setBorder(BorderFactory.createTitledBorder(""));
        leftInfoPanel = new JPanel();
        leftTeamPanel.add(leftInfoPanel, BorderLayout.CENTER);

        rightTeamPanel = new JPanel(new BorderLayout());
        rightTeamPanel.setBorder(BorderFactory.createTitledBorder(""));
        rightInfoPanel = new JPanel();
        rightTeamPanel.add(rightInfoPanel, BorderLayout.CENTER);

        panelScrolls.add(leftTeamPanel);
        panelScrolls.add(rightTeamPanel);

        allInfoPanel.add(panelScrolls);
        this.add(allInfoPanel);

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
                String[] listOfTeams = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listOfTeams = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listOfTeams);
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
                JFileChooser fileChooser = new JFileChooser(pathToLeaguesList);
                String[] directoryList = new String[fileChooser.getCurrentDirectory().list().length+1];
                directoryList[0] = "Выберите лигу";
                for (int i=1; i<directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList);
                leagueChooser.setModel(modelH);
                leagueChooser.setSelectedIndex(index);
                leagueChooser.setFocusable(false);

                teamChooser.setEnabled(true);
                String pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listOfTeams = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listOfTeams = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listOfTeams);
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
                if (sliderPanel != null) {
                    panelWithSlider.removeAll();
                }

                leagueName = (String) leagueChooser.getSelectedItem();
                teamName = (String) teamChooser.getSelectedItem();
                allOrHomeOrAway = (String) teamAllOrHomeOrAway.getSelectedItem();
                season = (String) seasonChooser.getSelectedItem();

                sliderPanel = getSliderPanel();

                panelWithSlider.add(sliderPanel);
                panelWithSlider.revalidate();
                allInfoPanel.revalidate();
                buttonShowInfo.setFocusable(false);
            }
        });

    }

    public JPanel getSliderPanel(){
        JPanel result = new JPanel(new VerticalLayout());
        this.setCursor(Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        season = season.replace("Сезон ", "");

        if ((!leagueName.contains("Выберите"))&&(!teamName.contains("Выберите"))){
            Selector selector = new Selector();
            selector.getListOfMatches(leagueName, teamName, allOrHomeOrAway, season, "Весь сезон");
            //selector.getPList(selector.listOfMatches, teamName);

            if (selector.listOfMatches.size()>0){
                int matches = selector.listOfMatches.size();

                String whereWereMatches = allOrHomeOrAway;
                if (allOrHomeOrAway.contains("Все"))
                    whereWereMatches = "";
                String textLabel = "В сезоне " + season + " команда " + teamName + " провела " + whereWereMatches + " " + matches + " игр.";
                JLabel labelHeader = new JLabel(textLabel);
                labelHeader.setFont(font18);
                labelHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
                result.add(labelHeader);

                JLabel labelSetBound = new JLabel("Задайте границу для отображения статистики «До–после»");
                labelSetBound.setFont(font18);
                labelSetBound.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                labelSetBound.setHorizontalAlignment(SwingConstants.CENTER);
                result.add(labelSetBound);

                slider = new JSlider(0, matches-1);
                slider.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));

                labels = new Hashtable<>();
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    if (selector.listOfMatches.get(i).homeTeam.equals(teamName)) {
                        labels.put(i, new JLabel(Team.getShortName(selector.listOfMatches.get(i).awayTeam)));
                    }
                    else{
                        labels.put(i, new JLabel(Team.getShortName(selector.listOfMatches.get(i).homeTeam)));
                    }
                }
                slider.setLabelTable(labels);
                slider.setPaintLabels(true);
                slider.setMajorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setEnabled(true);
                result.add(slider);

                result.add(panelTableWithBound);

                buttonShowBeforeAfterStats = new JButton("Отобразить!");
                buttonShowBeforeAfterStats.setFont(font18);

                result.add(buttonShowBeforeAfterStats);

                setTableWithBound();

                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setTableWithBound();
                    }
                });

                buttonShowBeforeAfterStats.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        if (leftTeamPanel != null) {
//                            leftTeamPanel.remove(leftInfoPanel);
                            panelScrolls.remove(leftTeamPanel);
                        }
                        leftTeamPanel = showBeforeAfterStats("0-" + String.valueOf(slider.getValue()));
                        panelScrolls.add(leftTeamPanel);
                        panelScrolls.revalidate();

                        if (rightTeamPanel != null) {
//                            rightTeamPanel.remove(rightInfoPanel);
                            panelScrolls.remove(rightTeamPanel);
                        }
                        rightTeamPanel = showBeforeAfterStats(String.valueOf(slider.getValue()+1) + "-" + String.valueOf(slider.getMaximum()));
                        panelScrolls.add(rightTeamPanel);
                        panelScrolls.revalidate();
                        setCursor(Cursor.getDefaultCursor());

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
                label.setFont(font18);
                container.add(label, BorderLayout.NORTH);

            }
        } else {
            JPanel container = new JPanel(new BorderLayout());

            final JLabel label = new JLabel("   Задайте все условия поиска. Лига и/или команда не заданы.");
            label.setPreferredSize(new Dimension(500, 25));
            label.setFont(font18);
            container.add(label, BorderLayout.NORTH);

        }
        this.setCursor(Cursor.getDefaultCursor());

        return result;

    }

    public void setTableWithBound(){
        panelTableWithBound.setLayout(new GridLayout(1, 0, 5, 5));
        panelTableWithBound.removeAll();

        int totalMatches = slider.getMaximum()+1;
        int bound = slider.getValue();

        String leftText = "";
        String rightText = "";
        if (bound == slider.getMaximum()){
            leftText = "Справа от границы нет матчей";
            buttonShowBeforeAfterStats.setEnabled(false);
        } else {
            leftText = (bound+1) + " матчей. От " + labels.get(0).getText() + " до " + labels.get(bound).getText();
            rightText = (totalMatches - (bound+1)) + " матчей. От " + labels.get(bound+1).getText() + " до " + labels.get(totalMatches-1).getText();
            buttonShowBeforeAfterStats.setEnabled(true);
        }

        JLabel labelBefore = new JLabel(leftText);
        labelBefore.setFont(font18);
        labelBefore.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        labelBefore.setHorizontalAlignment(SwingConstants.CENTER);
        panelTableWithBound.add(labelBefore);

        JLabel labelAfter = new JLabel(rightText);
        labelAfter.setFont(font18);
        labelAfter.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        labelAfter.setHorizontalAlignment(SwingConstants.CENTER);
        panelTableWithBound.add(labelAfter);

        panelTableWithBound.revalidate();

    }

    public JPanel showBeforeAfterStats(String range){
        final Selector selector = new Selector();
        JPanel result = new JPanel(new BorderLayout());
        selector.getListOfMatches(leagueName, teamName, allOrHomeOrAway, season, range);
        ArrayList<String> tournamentTable = League.getLeagueFromFile(leagueName, season).tournamentTable;
        final JScrollPane scrollPane;
        final Settings settings = Settings.getSettingsFromFile();

        if (selector.listOfMatches.size()>0){
            final Font fontLabel = new Font("Arial", Font.BOLD, 15);
            JPanel leftInfo = new JPanel(new VerticalLayout());
            selector.getPList(selector.listOfMatches, teamName);
            final JPanel container = new JPanel();
            container.setLayout(null);
            int otstup = 0;

            if (!Settings.isWhoScoredLeague(leagueName)){
                JLabel labelAttention = new JLabel("ВНИМАНИЕ! Данные по этой лиге могут быть не в полном объеме.");
                labelAttention.setFont(font18);
                labelAttention.setForeground(Color.RED);
                labelAttention.setLocation(10, otstup);
                labelAttention.setSize(new Dimension(650, 25));
                otstup += 30;
                container.add(labelAttention);
            }

            int matches = selector.listOfMatches.size();
            int wins = (int) Double.parseDouble(selector.pList.get(0).get(1));
            int draws = (int) Double.parseDouble(selector.pList.get(1).get(1));
            int loses = (int) Double.parseDouble(selector.pList.get(2).get(1));
            int points = (int) Double.parseDouble(selector.pList.get(3).get(1));
            String teamStats = "Матчей: " + String.valueOf(matches) + ";    Побед: " + String.valueOf(wins) + ";    Ничьих: "
                    + String.valueOf(draws) + ";    Поражений: " + String.valueOf(loses) +";    Набрано очков: " + String.valueOf(points) + ";\n";
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
            String teamStatsxG = "xGMatches: " + String.valueOf(xGmatches) + ";   xWins: " + String.valueOf(xWins) + ";   wDraws: "
                    + String.valueOf(xDraws) + ";   xLoses: " + String.valueOf(xLoses) +";   xPoints: " + String.valueOf(xPoints) + ";";
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
            String teamGoals = "Голов забито:   " + String.valueOf(goalsS) + ";                 Голов пропущено:   " + String.valueOf(goalsC) +
                    ";                   Разница:   " + String.valueOf(diff) + ";";
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
                String s = "";
                for (int j=forma.length()-1; j>=0; j--){
                    s = s + forma.charAt(j);
                }
                forma = s;
            }
            final Dimension defFormaLocation = new Dimension(70, otstup);
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
            int localOtstup = otstup;
            otstup += 35;
            labelSources.setSize(new Dimension(600, 25));
            labelSources.setFont(font15);
            container.add(labelSources);

            int horAlignment = 375;
            final JButton buttonSite = new JButton(new ImageIcon("images/www.png"));
            if (Team.getWebsite(teamName).equals("website"))
                buttonSite.setEnabled(false);
            buttonSite.setLocation(horAlignment, localOtstup - 5);
            buttonSite.setSize(32,32);
            container.add(buttonSite);

            JButton buttonTwitter = new JButton(new ImageIcon("images/twitter.png"));
            if (Team.getTwitter(teamName).equals("twitter"))
                buttonTwitter.setEnabled(false);
            buttonTwitter.setLocation(horAlignment + 32 + 5, localOtstup - 5);
            buttonTwitter.setSize(32,32);
            container.add(buttonTwitter);

            JButton buttonMedicine = new JButton(new ImageIcon("images/medicine.png"));
            if (Team.getTransferMarkt(teamName).equals("medicine"))
                buttonMedicine.setEnabled(false);
            buttonMedicine.setLocation(horAlignment + (32 + 5) * 2, localOtstup - 5);
            buttonMedicine.setSize(32,32);
            container.add(buttonMedicine);

            JButton buttonWeather = new JButton(new ImageIcon("images/weather.png"));
            if (Team.getWeather(teamName).equals("weather"))
                buttonWeather.setEnabled(false);
            buttonWeather.setLocation(horAlignment + (32 + 5) * 3, localOtstup - 5);
            buttonWeather.setSize(32, 32);
            container.add(buttonWeather);

            JButton buttonTransferMarkt = new JButton(new ImageIcon("images/transfermarkt.png"));
            if (Team.getTransferMarkt(teamName).equals("transferMarkt"))
                buttonTransferMarkt.setEnabled(false);
            buttonTransferMarkt.setLocation(horAlignment + (32 + 5)*4, localOtstup - 5);
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
            if (allOrHomeOrAway.contains("Все")){
                if (range.split("-")[0].equals("0")){
                    teamSrednie = "Показатели игр " + teamName + " в сезоне " + season + ". ДО разделения";
                } else {
                    teamSrednie = "Показатели игр " + teamName + " в сезоне " + season + ". ПОСЛЕ разделения";
                }

            }
            if (allOrHomeOrAway.contains("Дома")){
                teamSrednie = "Показатели домашних игр " + teamName + " в сезоне " + season + ". ДО разделения";
            }
            if (allOrHomeOrAway.contains("На выезде")){
                teamSrednie = "Показатели гостевых игр " + teamName + " в сезоне " + season + ". ПОСЛЕ разделения";
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
            table.getTableHeader().setFont(fontLabel);
            table.setFont(fontLabel);

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

            tablePanel.setSize(530, (rowCount + 1) *table.getRowHeight() - 3);
            tablePanel.setLocation(10, otstup);
            otstup += (rowCount + 1) *table.getRowHeight();
            container.add(tablePanel);

            container.setPreferredSize(new Dimension(500, otstup));
            leftInfo.add(container);


            ////////////////////////Таблица сводная по параметрам
            if (settings.pivotTable){
                leftInfo.add(TableMaker.getPivotTable(teamName, selector, tournamentTable));
            }

            ////////////////////////ГРАФИКИ ВСТАВЛЯЮ ТУТ
            final Graphic graphic;
            if (settings.showGraphics){
                graphic = new Graphic(0, teamName);
                JPanel panelG = graphic.getGraphics(teamName, allOrHomeOrAway, lastOrFullSeason, selector, tournamentTable);
                //panelG.setPreferredSize(new Dimension(300,1000));
                leftInfo.add(panelG);
            } else {
                graphic = new Graphic(0, teamName);
                JPanel panelTables = graphic.getTablesWithStats(teamName, allOrHomeOrAway, selector, tournamentTable);
                leftInfo.add(panelTables);
            }

            ////////////////////////ГРАФИКИ БОЛЬШЕ НЕ ВСТАВЛЯЮ

            scrollPane = new JScrollPane(leftInfo);
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
                    WindowTrendsOfTeam wtt = new WindowTrendsOfTeam(teamName, allOrHomeOrAway, finalSeason, "Весь сезон", selector);
                    wtt.setVisible(true);
                }
            });

            buttonDiagrams.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WindowWithDiagrams wwd = new WindowWithDiagrams(teamName, allOrHomeOrAway, finalSeason, "Весь сезон", selector);
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
                        int resHeight =(int) (containerHeight + (double) indexOfFoundGraphic / (double) graphic.graphicTitles.size() * (scrollPane.getVerticalScrollBar().getMaximum() - containerHeight));
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
            JLabel label = new JLabel(labelText);
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

        this.setCursor(Cursor.getDefaultCursor());
        result.add(scrollPane);

        return result;


    }

    public void setFilters(String league){
        buttonShowInfo.setEnabled(false);
        String season = Settings.getCurrentSeasonInLeague(league);
        seasonChooser.setSelectedItem("Сезон " + season);
        leagueChooser.setSelectedItem(league);
        teamAllOrHomeOrAway.setSelectedItem("Дома");
        buttonShowInfo.setEnabled(true);
    }
}