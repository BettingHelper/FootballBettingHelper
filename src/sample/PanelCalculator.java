package sample;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCalculator extends JPanel{
    JComboBox<String> seasonCB;
    JComboBox<String> leagueChooser;
    JComboBox<String> teamChooserHome;
    JComboBox<String> teamChooserAway;
    JButton buttonShow;

    JPanel dataPanel;

    double slider_HT_1_Value = 0;
    double slider_HT_2_Value = 0;
    double slider_HT_3_Value = 0;
    double slider_HT_4_Value = 0;
    double slider_AT_1_Value = 0;
    double slider_AT_2_Value = 0;
    double slider_AT_3_Value = 0;
    double slider_AT_4_Value = 0;
    double minSliderValue = 0;
    double maxSliderValue = 20;
    double stepSlider = 1;
    JSlider slider_HT_1 = new JSlider(0,20,0);
    JSlider slider_HT_2 = new JSlider(0,20,0);
    JSlider slider_HT_3 = new JSlider(0,20,0);
    JSlider slider_HT_4 = new JSlider(0,20,0);
    JSlider slider_AT_1 = new JSlider(0,20,0);
    JSlider slider_AT_2 = new JSlider(0,20,0);
    JSlider slider_AT_3 = new JSlider(0,20,0);
    JSlider slider_AT_4 = new JSlider(0,20,0);
    SliderSettings sliderSettings;
    CalculatorThread calculatorThread;
    String leagueName = "";
    String season = "";
    String homeTeam = "";
    String awayTeam = "";

    String lastCalculatedLeague = "";
    String lastCalculatedSeason = "";
    String lastCalculatedHomeTeam = "";
    String lastCalculatedAwayTeam = "";

    Selector selectorHT_All;
    double[][] dataArrayThis_HT_All;
    double[][] dataArrayOpponent_HT_All;
    double[][] dataArrayTotal_HT_All;
    String[] arrayOpponents_HT_All;

    Selector selectorAT_All;
    double[][] dataArrayThis_AT_All;
    double[][] dataArrayOpponent_AT_All;
    double[][] dataArrayTotal_AT_All;
    String[] arrayOpponents_AT_All;

    Selector selectorHT_Home;
    double[][] dataArrayThis_HT_Home;
    double[][] dataArrayOpponent_HT_Home;
    double[][] dataArrayTotal_HT_Home;
    String[] arrayOpponents_HT_Home;

    Selector selectorAT_Away;
    double[][] dataArrayThis_AT_Away;
    double[][] dataArrayOpponent_AT_Away;
    double[][] dataArrayTotal_AT_Away;
    String[] arrayOpponents_AT_Away;

    Selector selectorConfAll_HT;
    double[][] dataArrayThis_ConfAll_HT;
    double[][] dataArrayOpponent_ConfAll_HT;
    double[][] dataArrayTotal_ConfAll_HT;
    String[] arrayOpponents_ConfAll_HT;

    Selector selectorConfHomeField_HT;
    double[][] dataArrayThis_ConfHomeField_HT;
    double[][] dataArrayOpponent_ConfHomeField_HT;
    double[][] dataArrayTotal_ConfHomeField_HT;
    String[] arrayOpponents_ConfHomeField_HT;

    Selector selectorConfAll_AT;
    double[][] dataArrayThis_ConfAll_AT;
    double[][] dataArrayOpponent_ConfAll_AT;
    double[][] dataArrayTotal_ConfAll_AT;
    String[] arrayOpponents_ConfAll_AT;

    Selector selectorConfHomeField_AT;
    double[][] dataArrayThis_ConfHomeField_AT;
    double[][] dataArrayOpponent_ConfHomeField_AT;
    double[][] dataArrayTotal_ConfHomeField_AT;
    String[] arrayOpponents_ConfHomeField_AT;

    League currentLeague;
    ArrayList<League> arrayLeaguesAll;

    public PanelCalculator(){
        this.setLayout(new BorderLayout());
        String curSeason = Settings.getDefaultSeason();
        sliderSettings = SliderSettings.getSliderSettingsFromFile();
        final String path = "database/";

        ////////////////////////////////////////////ПАНЕЛЬ
        JPanel headPanel = new JPanel(new BorderLayout());

        JPanel panelChoosers = new JPanel(new GridLayout(1, 0, 5, 5));

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonCB = new JComboBox<>(seasonList);
        season = listOfSeasons.get(0);
        panelChoosers.add(seasonCB);

        final JFileChooser fileChooser = new JFileChooser(path + curSeason + "/leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> leagueList = new ArrayList<>();
        leagueList.add("Выберите лигу");
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        String[] listOfLeagues = new String[leagueList.size()];
        for (int i = 0; i < listOfLeagues.length; i++)
            listOfLeagues[i] = leagueList.get(i);
        leagueChooser = new JComboBox<>(listOfLeagues);
        leagueName = listOfLeagues[0];
        panelChoosers.add(leagueChooser);

        String fileNameHome = path + curSeason + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] listHome = {"Выберите команду"};
        if (!fileNameHome.contains("ыберите")) {
            listHome = Main.readTxtFile(fileNameHome);
        }
        teamChooserHome = new JComboBox<>(listHome);
        teamChooserHome.setEnabled(false);
        homeTeam = listHome[0];
        panelChoosers.add(teamChooserHome);

        String fileNameAway = path + curSeason + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] listAway = {"Выберите команду"};
        if (!fileNameAway.contains("ыберите")) {
            listAway = Main.readTxtFile(fileNameAway);
        }
        teamChooserAway = new JComboBox<>(listAway);
        teamChooserAway.setEnabled(false);
        awayTeam = listAway[0];
        panelChoosers.add(teamChooserAway);
        headPanel.add(panelChoosers, BorderLayout.NORTH);

        JPanel panelSliders = new JPanel(new GridLayout(0, 2, 5, 5));

        Font font15 = new Font("", Font.BOLD, 15);

        //***************** Слайдеры для хозяев
        JPanel homeTeamSlidersPanel = new JPanel(new GridLayout(0 , 1, 5, 5));
        TitledBorder border_homeTeamSlidersPanel = BorderFactory.createTitledBorder("Хозяева");
        border_homeTeamSlidersPanel.setTitleFont(font15);
        homeTeamSlidersPanel.setBorder(border_homeTeamSlidersPanel);
        slider_HT_1.setValue(sliderSettings.slider_HT_1_Value);
        slider_HT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_1.setMajorTickSpacing(1);
        slider_HT_1.setPaintTicks(true);
        JPanel slider_HT_1_Panel = new JPanel(new BorderLayout());
        slider_HT_1_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_HT_1 = new JLabel("Приоритет 1");
        head_HT_1.setHorizontalAlignment(SwingConstants.CENTER);
        head_HT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_HT_1.setFont(font15);
        JLabel left_HT_1 = new JLabel("Все игры");
        left_HT_1.setFont(font15);
        left_HT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel right_HT_1 = new JLabel("Последние игры");
        right_HT_1.setFont(font15);
        right_HT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_1_Panel.add(head_HT_1, BorderLayout.NORTH);
        slider_HT_1_Panel.add(slider_HT_1);
        slider_HT_1_Panel.add(left_HT_1, BorderLayout.WEST);
        slider_HT_1_Panel.add(right_HT_1, BorderLayout.EAST);
        homeTeamSlidersPanel.add(slider_HT_1_Panel);

        slider_HT_2.setValue(sliderSettings.slider_HT_2_Value);
        slider_HT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_2.setMajorTickSpacing(1);
        slider_HT_2.setPaintTicks(true);
        JPanel slider_HT_2_Panel = new JPanel(new BorderLayout());
        slider_HT_2_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_HT_2 = new JLabel("Приоритет 2");
        head_HT_2.setHorizontalAlignment(SwingConstants.CENTER);
        head_HT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_HT_2.setFont(font15);
        JLabel left_HT_2 = new JLabel("Все игры");
        left_HT_2.setFont(font15);
        left_HT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel right_HT_2 = new JLabel("Только дом или выезд");
        right_HT_2.setFont(font15);
        right_HT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_2_Panel.add(head_HT_2, BorderLayout.NORTH);
        slider_HT_2_Panel.add(slider_HT_2);
        slider_HT_2_Panel.add(left_HT_2, BorderLayout.WEST);
        slider_HT_2_Panel.add(right_HT_2, BorderLayout.EAST);
        homeTeamSlidersPanel.add(slider_HT_2_Panel);

        slider_HT_3.setValue(sliderSettings.slider_HT_3_Value);
        slider_HT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_3.setMajorTickSpacing(1);
        slider_HT_3.setPaintTicks(true);
        JPanel slider_HT_3_Panel = new JPanel(new BorderLayout());
        slider_HT_3_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_HT_3 = new JLabel("Приоритет 3");
        head_HT_3.setHorizontalAlignment(SwingConstants.CENTER);
        head_HT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_HT_3.setFont(font15);
        JLabel left_HT_3 = new JLabel("Текущий сезон");
        left_HT_3.setFont(font15);
        left_HT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel right_HT_3 = new JLabel("Личные встречи");
        right_HT_3.setFont(font15);
        right_HT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_3_Panel.add(head_HT_3, BorderLayout.NORTH);
        slider_HT_3_Panel.add(slider_HT_3);
        slider_HT_3_Panel.add(left_HT_3, BorderLayout.WEST);
        slider_HT_3_Panel.add(right_HT_3, BorderLayout.EAST);
        homeTeamSlidersPanel.add(slider_HT_3_Panel);

        slider_HT_4.setValue(sliderSettings.slider_HT_4_Value);
        slider_HT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_4.setMajorTickSpacing(1);
        slider_HT_4.setPaintTicks(true);
        JPanel slider_HT_4_Panel = new JPanel(new BorderLayout());
        slider_HT_4_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_HT_4 = new JLabel("Приоритет 4");
        head_HT_4.setHorizontalAlignment(SwingConstants.CENTER);
        head_HT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_HT_4.setFont(font15);
        JLabel left_HT_4 = new JLabel("Как создает команда");
        left_HT_4.setFont(font15);
        left_HT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel right_HT_4 = new JLabel("Как позволяет создавать");
        right_HT_4.setFont(font15);
        right_HT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_HT_4_Panel.add(head_HT_4, BorderLayout.NORTH);
        slider_HT_4_Panel.add(slider_HT_4);
        slider_HT_4_Panel.add(left_HT_4, BorderLayout.WEST);
        slider_HT_4_Panel.add(right_HT_4, BorderLayout.EAST);
        homeTeamSlidersPanel.add(slider_HT_4_Panel);

        panelSliders.add(homeTeamSlidersPanel);
        //&&&&&&&&&&&&&&&& Слайдеры для хозяев

        //***************** Слайдеры для гостей
        JPanel awayTeamSlidersPanel = new JPanel(new GridLayout(0 , 1, 5, 5));
        TitledBorder border_awayTeamSlidersPanel = BorderFactory.createTitledBorder("Гости");
        border_awayTeamSlidersPanel.setTitleFont(font15);
        awayTeamSlidersPanel.setBorder(border_awayTeamSlidersPanel);
        slider_AT_1.setValue(sliderSettings.slider_AT_1_Value);
        slider_AT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_1.setMajorTickSpacing(1);
        slider_AT_1.setPaintTicks(true);
        JPanel slider_AT_1_Panel = new JPanel(new BorderLayout());
        slider_AT_1_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_AT_1 = new JLabel("Приоритет 1");
        head_AT_1.setHorizontalAlignment(SwingConstants.CENTER);
        head_AT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_AT_1.setFont(font15);
        JLabel left_AT_1 = new JLabel("Все игры");
        left_AT_1.setFont(font15);
        left_AT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel rigAT_AT_1 = new JLabel("Последние игры");
        rigAT_AT_1.setFont(font15);
        rigAT_AT_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_1_Panel.add(head_AT_1, BorderLayout.NORTH);
        slider_AT_1_Panel.add(slider_AT_1);
        slider_AT_1_Panel.add(left_AT_1, BorderLayout.WEST);
        slider_AT_1_Panel.add(rigAT_AT_1, BorderLayout.EAST);
        awayTeamSlidersPanel.add(slider_AT_1_Panel);

        slider_AT_2.setValue(sliderSettings.slider_AT_2_Value);
        slider_AT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_2.setMajorTickSpacing(1);
        slider_AT_2.setPaintTicks(true);
        JPanel slider_AT_2_Panel = new JPanel(new BorderLayout());
        slider_AT_2_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_AT_2 = new JLabel("Приоритет 2");
        head_AT_2.setHorizontalAlignment(SwingConstants.CENTER);
        head_AT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_AT_2.setFont(font15);
        JLabel left_AT_2 = new JLabel("Все игры");
        left_AT_2.setFont(font15);
        left_AT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel rigAT_AT_2 = new JLabel("Только дом или выезд");
        rigAT_AT_2.setFont(font15);
        rigAT_AT_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_2_Panel.add(head_AT_2, BorderLayout.NORTH);
        slider_AT_2_Panel.add(slider_AT_2);
        slider_AT_2_Panel.add(left_AT_2, BorderLayout.WEST);
        slider_AT_2_Panel.add(rigAT_AT_2, BorderLayout.EAST);
        awayTeamSlidersPanel.add(slider_AT_2_Panel);

        slider_AT_3.setValue(sliderSettings.slider_AT_3_Value);
        slider_AT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_3.setMajorTickSpacing(1);
        slider_AT_3.setPaintTicks(true);
        JPanel slider_AT_3_Panel = new JPanel(new BorderLayout());
        slider_AT_3_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_AT_3 = new JLabel("Приоритет 3");
        head_AT_3.setHorizontalAlignment(SwingConstants.CENTER);
        head_AT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_AT_3.setFont(font15);
        JLabel left_AT_3 = new JLabel("Текущий сезон");
        left_AT_3.setFont(font15);
        left_AT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel rigAT_AT_3 = new JLabel("Личные встречи");
        rigAT_AT_3.setFont(font15);
        rigAT_AT_3.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_3_Panel.add(head_AT_3, BorderLayout.NORTH);
        slider_AT_3_Panel.add(slider_AT_3);
        slider_AT_3_Panel.add(left_AT_3, BorderLayout.WEST);
        slider_AT_3_Panel.add(rigAT_AT_3, BorderLayout.EAST);
        awayTeamSlidersPanel.add(slider_AT_3_Panel);

        slider_AT_4.setValue(sliderSettings.slider_AT_4_Value);
        slider_AT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_4.setMajorTickSpacing(1);
        slider_AT_4.setPaintTicks(true);
        JPanel slider_AT_4_Panel = new JPanel(new BorderLayout());
        slider_AT_4_Panel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        JLabel head_AT_4 = new JLabel("Приоритет 4");
        head_AT_4.setHorizontalAlignment(SwingConstants.CENTER);
        head_AT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        head_AT_4.setFont(font15);
        JLabel left_AT_4 = new JLabel("Как создает команда");
        left_AT_4.setFont(font15);
        left_AT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel rigAT_AT_4 = new JLabel("Как позволяет создавать");
        rigAT_AT_4.setFont(font15);
        rigAT_AT_4.setBorder(new EmptyBorder(5, 5, 5, 5));
        slider_AT_4_Panel.add(head_AT_4, BorderLayout.NORTH);
        slider_AT_4_Panel.add(slider_AT_4);
        slider_AT_4_Panel.add(left_AT_4, BorderLayout.WEST);
        slider_AT_4_Panel.add(rigAT_AT_4, BorderLayout.EAST);
        awayTeamSlidersPanel.add(slider_AT_4_Panel);

        panelSliders.add(awayTeamSlidersPanel);
        //&&&&&&&&&&&&&&&& Слайдеры для гостей


        headPanel.add(panelSliders);

        buttonShow = new JButton("Отобразить!");
        Font fontForButton = new Font("", 0, 18);
        buttonShow.setFont(fontForButton);
        buttonShow.setEnabled(false);
        headPanel.add(buttonShow, BorderLayout.SOUTH);

        this.add(headPanel, BorderLayout.NORTH);

        dataPanel = new JPanel(new BorderLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder(""));

        //JLabel labelTotalHandicapHeader = new JLabel("Расчетное значение форы:");
        //labelTotalHandicapHeader.setHorizontalAlignment(SwingConstants.CENTER);
        //dataPanel.add(labelTotalHandicapHeader);

        this.add(dataPanel);

        seasonCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seasonCB.setFocusable(false);
                season = seasonCB.getSelectedItem().toString().replace("Сезон ", "");

                String pathToLeaguesList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser fileChooser = new JFileChooser(pathToLeaguesList);
                String[] directoryList = new String[fileChooser.getCurrentDirectory().list().length + 1];
                directoryList[0] = "Выберите лигу";
                for (int i = 1; i < directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i - 1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList);
                leagueChooser.setModel(modelH);

                String pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listRight = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listRight = Main.readTxtFile(pathToTeamsList);
                }

                DefaultComboBoxModel modelH2;
                modelH = new DefaultComboBoxModel(listRight);
                modelH2 = new DefaultComboBoxModel(listRight);
                teamChooserHome.setModel(modelH);
                teamChooserAway.setModel(modelH2);

                leagueChooser.setFocusable(true);
            }
        });

        leagueChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leagueName = (String) leagueChooser.getSelectedItem();
                int index = leagueChooser.getSelectedIndex();
                String pathToLeaguesList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser fileChooser = new JFileChooser(pathToLeaguesList);
                String[] directoryList = new String[fileChooser.getCurrentDirectory().list().length+1];
                directoryList[0] = "Выберите лигу";
                for (int i=1; i<directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList);
                DefaultComboBoxModel modelH2;
                leagueChooser.setModel(modelH);
                leagueChooser.setSelectedIndex(index);
                leagueChooser.setFocusable(false);

                teamChooserHome.setEnabled(true);
                teamChooserAway.setEnabled(true);
                String pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
                String[] listRight = {"Выберите команду"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listRight = Main.readTxtFile(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listRight);
                modelH2 = new DefaultComboBoxModel(listRight);
                teamChooserHome.setModel(modelH);
                teamChooserAway.setModel(modelH2);
                checkFilters();

            }
        });

        teamChooserHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooserHome.setFocusable(false);
                homeTeam = (String) teamChooserHome.getSelectedItem();
                checkFilters();
            }
        });

        teamChooserAway.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooserAway.setFocusable(false);
                awayTeam = (String) teamChooserAway.getSelectedItem();
                checkFilters();
            }
        });

        buttonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorThread = new CalculatorThread(season, leagueName, homeTeam, awayTeam,
                        (PanelCalculator) buttonShow.getParent().getParent(),
                        slider_HT_1.getValue(), slider_HT_2.getValue(), slider_HT_3.getValue(), slider_HT_4.getValue(),
                        slider_AT_1.getValue(), slider_AT_2.getValue(), slider_AT_3.getValue(), slider_AT_4.getValue());
                calculatorThread.start();

            }
        });
    }

    public void setFilters(String league, String homeTeam, String awayTeam){
        String season = Settings.getCurrentSeasonInLeague(league);
        seasonCB.setSelectedItem("Сезон " + season);
        leagueChooser.setSelectedItem(league);
        teamChooserHome.setSelectedItem(homeTeam);
        teamChooserAway.setSelectedItem(awayTeam);
    }

    public void checkFilters(){
        if (leagueName != null && !leagueName.contains("Выберите")
                && homeTeam != null && !homeTeam.contains("Выберите")
                && awayTeam != null && !awayTeam.contains("Выберите")
                && !homeTeam.equals(awayTeam)){
            buttonShow.setEnabled(true);
        }
    }
}


