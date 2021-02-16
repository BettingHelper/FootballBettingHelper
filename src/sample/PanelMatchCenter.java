package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class PanelMatchCenter extends JPanel{
    JScrollPane scrollPane;
    int dayCode;
    JPanel panelHeader;
    JPanel panelWithJSP;
    JButton buttonLeft;
    JButton buttonRight;
    JLabel labelDate;
    JScrollPane jsp;
    PanelMatching panelMatching;
    PanelOneTeamStats panelOneTeamStats;
    PanelConfrontation panelConfrontation;
    PanelReferee panelReferee;
    PanelTablesByLeague panelTablesByLeague;
    PanelTrends panelTrends;
    PanelBeforeAfter panelBeforeAfter;

    public PanelMatchCenter(PanelMatching panelMatching, PanelOneTeamStats panelOneTeamStats, PanelConfrontation panelConfrontation, PanelReferee panelReferee,
                            PanelTablesByLeague panelTablesByLeague, PanelTrends panelTrends, PanelBeforeAfter panelBeforeAfter){
        this.setLayout(new BorderLayout());

        this.panelMatching = panelMatching;
        this.panelOneTeamStats = panelOneTeamStats;
        this.panelConfrontation = panelConfrontation;
        this.panelReferee = panelReferee;
        this.panelTablesByLeague = panelTablesByLeague;
        this.panelTrends = panelTrends;
        this.panelBeforeAfter = panelBeforeAfter;

        Settings settings = Settings.getSettingsFromFile();

        ////////////////////////////////////////////ПАНЕЛЬ
        panelHeader = new JPanel(new BorderLayout());

        buttonLeft = new JButton(new ImageIcon("images/left-arrow.png"));
        buttonLeft.setEnabled(false);
        panelHeader.add(buttonLeft, BorderLayout.WEST);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("GMT"));
        ldt = ldt.plusHours(12 - settings.localTime); // 12 - индекс гринвича в комбобоксе
        final int hourDifference = 11 - settings.localTime; // 11 - индекс в комбобоксе среднеевропейского времени, от которого считаает WS
        String day = String.valueOf(ldt.getDayOfMonth());
        String month = String.valueOf(ldt.getMonthValue());
        String year = String.valueOf(ldt.getYear());
        if (day.length()<2)
            day = "0" + day;
        if (month.length()<2)
            month = "0" + month;
        String dateS = day + "." + month + "." + year;
        dayCode = Integer.parseInt(year)*10000 + Integer.parseInt(month)*100 + Integer.parseInt(day);

        Font font = new Font("", Font.BOLD, 20);
        labelDate = new JLabel(dateS);
        labelDate.setFont(font);
        labelDate.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(labelDate);

        buttonRight = new JButton(new ImageIcon("images/right-arrow.png"));

        int n = Settings.getNumberOfAccount();
        int nextDay = Settings.getNextDayCode(dayCode);
        String nameOfFile = nextDay + ".txt";
        File file = Settings.downloadDayFile(n, nameOfFile);

        buttonRight.setEnabled(file.exists() && file.length() != 0);

        panelHeader.add(buttonRight, BorderLayout.EAST);
        this.add(panelHeader, BorderLayout.NORTH);

        panelWithJSP = new JPanel(new BorderLayout());
        fillData(dayCode, hourDifference);
        this.add(panelWithJSP);



        buttonLeft.addActionListener(e -> {
            int newDayCode = Settings.getPreviousDayCode(dayCode);
            String day1 = String.valueOf(newDayCode).substring(6,8);
            String month1 = String.valueOf(newDayCode).substring(4,6);
            String year1 = String.valueOf(newDayCode).substring(0,4);

            File file1 = Settings.downloadDayFile(Settings.getNumberOfAccount(), Settings.getPreviousDayCode(newDayCode) + ".txt");
            buttonLeft.setEnabled(file1.exists() && file1.length() != 0);
            labelDate.setText(day1 + "." + month1 + "." + year1);
            dayCode = Integer.parseInt(year1)*10000 + Integer.parseInt(month1)*100 + Integer.parseInt(day1);

            file1 = Settings.downloadDayFile(Settings.getNumberOfAccount(), Settings.getNextDayCode(newDayCode) + ".txt");
            buttonRight.setEnabled(file1.exists() && file1.length() != 0);

            fillData(dayCode, hourDifference);

        });

        buttonRight.addActionListener(e -> {
            int newDayCode = Settings.getNextDayCode(dayCode);
            String day12 = String.valueOf(newDayCode).substring(6, 8);
            String month12 = String.valueOf(newDayCode).substring(4,6);
            String year12 = String.valueOf(newDayCode).substring(0,4);
            File file12 = Settings.downloadDayFile(Settings.getNumberOfAccount(), Settings.getNextDayCode(newDayCode) + ".txt");
            buttonRight.setEnabled(file12.exists() && file12.length() != 0);
            labelDate.setText(day12 + "." + month12 + "." + year12);
            dayCode = Integer.parseInt(year12)*10000 + Integer.parseInt(month12)*100 + Integer.parseInt(day12);
            file12 = Settings.downloadDayFile(Settings.getNumberOfAccount(), Settings.getPreviousDayCode(newDayCode) + ".txt");
            buttonLeft.setEnabled(file12.exists() && file12.length() != 0);
            fillData(dayCode, hourDifference);

        });

    }

    private void fillData(int dayCode, int hourDifference){
        JPanel panelWithInfo = new JPanel(new VerticalLayout());
        Font font = new Font("", Font.BOLD, 20);
        ArrayList<String> list = Settings.getListForMatchCenter(String.valueOf(dayCode), hourDifference);
        int index = 0;
        String currentLeague = "";

        while (index < list.size()){
            if (list.get(index).split("\\*").length > 1){
                final JPanel panelMatch = new JPanel(new BorderLayout());
                JLabel labelTime = new JLabel(list.get(index).split("\\*")[0]);

                final String homeTeam = list.get(index).split("\\*")[1];
                final String awayTeam = list.get(index).split("\\*")[2];

                labelTime.setFont(font);
                labelTime.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                labelTime.setHorizontalAlignment(SwingConstants.CENTER);
                panelMatch.add(labelTime, BorderLayout.WEST);

                JLabel labelMatch = new JLabel(homeTeam + "  vs  " + awayTeam);
                labelMatch.setFont(font);
                labelMatch.setHorizontalAlignment(SwingConstants.CENTER);
                panelMatch.add(labelMatch);

                JPanel panelButtons = new JPanel(new GridLayout(1, 0, 5, 5));
                JButton buttonStats = new JButton("Задать значения фильтрам");
                buttonStats.setFont(font);
                JButton buttonTrends = new JButton("Тренды");
                buttonTrends.setFont(font);
                panelButtons.add(buttonStats);
                panelButtons.add(buttonTrends);
                panelButtons.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 3));
                panelMatch.add(panelButtons, BorderLayout.EAST);

                /*buttonStats.addActionListener(e -> {

                });*/

                final String finalCurrentLeague = currentLeague;
                buttonTrends.addActionListener(e -> {
                    Settings settings = Settings.getSettingsFromFile();

                    Selector selectorHT = new Selector();
                    Selector selectorAT = new Selector();
                    String allOrHA = "Все матчи";

                    if (settings.trendsHA){
                        selectorHT.getListOfMatches(finalCurrentLeague, homeTeam, "Дома", Settings.getCurrentSeasonInLeague(finalCurrentLeague), "Весь сезон");
                        selectorAT.getListOfMatches(finalCurrentLeague, awayTeam, "На выезде", Settings.getCurrentSeasonInLeague(finalCurrentLeague), "Весь сезон");
                        allOrHA = "Дом - выезд";
                    } else {
                        selectorHT.getListOfMatches(finalCurrentLeague, homeTeam, "Все матчи", Settings.getCurrentSeasonInLeague(finalCurrentLeague), "Весь сезон");
                        selectorAT.getListOfMatches(finalCurrentLeague, awayTeam, "Все матчи", Settings.getCurrentSeasonInLeague(finalCurrentLeague), "Весь сезон");
                    }

                    selectorHT.getPList(selectorHT.listOfMatches, homeTeam);
                    selectorAT.getPList(selectorAT.listOfMatches, awayTeam);

                    WindowTrendsForTwoTeams wttt = new WindowTrendsForTwoTeams(homeTeam, awayTeam, Settings.getCurrentSeasonInLeague(finalCurrentLeague),
                            allOrHA, "Весь сезон", selectorHT, selectorAT);
                    wttt.setVisible(true);
                });

                buttonStats.addActionListener(e -> {
                    FiltersThread filtersThread = new FiltersThread(panelMatching, panelOneTeamStats, panelConfrontation, panelReferee, panelTablesByLeague,
                            panelTrends, panelBeforeAfter, finalCurrentLeague, homeTeam, awayTeam);
                    filtersThread.start();

//                    panelMatching.setFilters(finalCurrentLeague, homeTeam, awayTeam);
//                    panelOneTeamStats.setFilters(finalCurrentLeague);
//                    panelConfrontation.setFilters(finalCurrentLeague, homeTeam, awayTeam);
//                    panelReferee.setFilters(finalCurrentLeague);
//                    panelTablesByLeague.setFilters(finalCurrentLeague);
//                    panelTrends.setFilters(finalCurrentLeague);
//                    panelBeforeAfter.setFilters(finalCurrentLeague);
                });

                /*buttonStats.addActionListener(e -> {
                    panelMatching.setFilters(finalCurrentLeague, homeTeam, awayTeam);
                    panelOneTeamStats.setFilters(finalCurrentLeague);
                    panelConfrontation.setFilters(finalCurrentLeague, homeTeam, awayTeam);
                    panelReferee.setFilters(finalCurrentLeague);
                    panelTablesByLeague.setFilters(finalCurrentLeague);
                    panelTrends.setFilters(finalCurrentLeague);
                    panelBeforeAfter.setFilters(finalCurrentLeague);
                });*/

                panelMatch.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                JPanel outerPanel = new JPanel(new BorderLayout());
                outerPanel.add(panelMatch);
                outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                panelWithInfo.add(outerPanel);
                index++;
            } else{
                currentLeague = list.get(index);
                JLabel labelTime = new JLabel(list.get(index));
                labelTime.setFont(font);
                labelTime.setHorizontalAlignment(SwingConstants.CENTER);
                labelTime.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
                panelWithInfo.add(labelTime);
                index++;
            }
        }
        if (panelWithJSP != null)
            panelWithJSP.removeAll();
        jsp = new JScrollPane(panelWithInfo);
        jsp.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );
        panelWithJSP.add(jsp);
        panelWithJSP.revalidate();
    }

}



