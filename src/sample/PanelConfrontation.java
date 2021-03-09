package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelConfrontation extends JPanel{
    //int WIDTH;
    //int DEFWIDTH = 1600;
    //double procWIDTH;
    //int HEIGHT;
    //int DEFHEIGHT = 1000;
    //int graphicWidth = 1250;
    //int graphicHeight = 410;
    //double procHEIGHT;
    JScrollPane scrollPane;
    JComboBox<String> seasonCB;
    JComboBox<String> leagueChooser;
    JComboBox<String> teamChooserHome;
    JComboBox<String> teamChooserAway;
    JComboBox<String> allOrHomeAwayComboBox;
    JButton buttonShow;

    public PanelConfrontation(){
        this.setLayout(new BorderLayout());
        //WIDTH = width;
        //HEIGHT = height;
        //procWIDTH = WIDTH / (double) DEFWIDTH;
        //procHEIGHT = HEIGHT / (double) DEFHEIGHT;
        //if (procWIDTH == 1)
        //    this.graphicWidth = 1300;
        String curSeason = Settings.getDefaultSeason();
        final String path = "database/";

        ////////////////////////////////////////////ПАНЕЛЬ
        JPanel panelChoosers = new JPanel(new GridLayout(1, 0, 5, 5));

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonCB = new JComboBox<>(seasonList);
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
        panelChoosers.add(leagueChooser);

        String fileNameHome = path + curSeason + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] listHome = {"Выберите команду"};
        if (!fileNameHome.contains("ыберите")) {
            listHome = Main.readTxtFile(fileNameHome);
        }
        teamChooserHome = new JComboBox<>(listHome);
        teamChooserHome.setEnabled(false);
        panelChoosers.add(teamChooserHome);

        String fileNameAway = path + curSeason + "/" + "leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] listAway = {"Выберите команду"};
        if (!fileNameAway.contains("ыберите")) {
            listAway = Main.readTxtFile(fileNameAway);
        }
        teamChooserAway = new JComboBox<>(listAway);
        teamChooserAway.setEnabled(false);
        panelChoosers.add(teamChooserAway);

        String[] allOrHomeAway = {"Все матчи", "На поле хозяев"};
        allOrHomeAwayComboBox = new JComboBox<>(allOrHomeAway);
        panelChoosers.add(allOrHomeAwayComboBox);

        buttonShow = new JButton("Отобразить!");
        Font fontForButton = new Font("", 0, 18);
        buttonShow.setFont(fontForButton);
        panelChoosers.add(buttonShow);
        this.add(panelChoosers, BorderLayout.NORTH);

        final JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        scrollPane = new JScrollPane();
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(infoPanel);

        seasonCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seasonCB.setFocusable(false);

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

            }
        });

        teamChooserHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooserHome.setFocusable(false);
            }
        });

        teamChooserAway.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamChooserAway.setFocusable(false);
            }
        });

        allOrHomeAwayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allOrHomeAwayComboBox.setFocusable(false);
            }
        });

        buttonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoPanel.removeAll();
                JScrollPane panel = refreshData((String) leagueChooser.getSelectedItem(),
                        (String) teamChooserHome.getSelectedItem(),
                        (String) teamChooserAway.getSelectedItem(),
                        (String) allOrHomeAwayComboBox.getSelectedItem()
                );
                infoPanel.add(panel);
                infoPanel.revalidate();
                buttonShow.setFocusable(false);
            }
        });
    }

    public JScrollPane refreshData(String leagueName, final String homeTeamName, final String awayTeamName, final String allOrHomeAway){
        this.setCursor(Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        JScrollPane scrollPane;
        JPanel container = new JPanel(new VerticalLayout());
        if ((!leagueName.contains("Выберите"))&&(!homeTeamName.contains("Выберите"))&&(!awayTeamName.contains("Выберите"))){
            final Selector selector = new Selector();
            selector.getConfrontationList(leagueName, homeTeamName, awayTeamName, allOrHomeAway);

            if (selector.listOfMatches.size()>0){
                int matches = selector.listOfMatches.size();

                String teamStats = "Матчей: " + String.valueOf(matches);
                JLabel label = new JLabel(teamStats);
                Font fontLabel = new Font("Arial", Font.BOLD, 15);
                label.setFont(fontLabel);
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setBorder(new EmptyBorder(10, 10, 0, 0));
                container.add(label);

                String[] colHeads = {"№" , "Сезон", "Матч", "Счет", "Тотал", "xG"};
                Object[][] data = new Object[selector.listOfMatches.size()][colHeads.length];
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    data[i][0] = String.valueOf(i+1);
                    data[i][1] = selector.listForConfrontation.get(i);
                    data[i][2] = selector.listOfMatches.get(i).homeTeam + " - " + selector.listOfMatches.get(i).awayTeam;
                    data[i][3] = selector.listOfMatches.get(i).homeScore + " : " + selector.listOfMatches.get(i).awayScore
                            + "  ( " + selector.listOfMatches.get(i).homeScore1T + " : " + selector.listOfMatches.get(i).awayScore1T + "  |  "
                            + selector.listOfMatches.get(i).homeScore2T + " : " + selector.listOfMatches.get(i).awayScore2T + " )";
                    data[i][4] = String.valueOf(selector.listOfMatches.get(i).homeScore + selector.listOfMatches.get(i).awayScore);
                    if (selector.listOfMatches.get(i).homeXG == 0.0 && selector.listOfMatches.get(i).awayXG == 0.0)
                        data[i][5] = "No info";
                    else
                        data[i][5] = String.valueOf(MyMath.round(selector.listOfMatches.get(i).homeXG, 2)) + " : " + String.valueOf(MyMath.round(selector.listOfMatches.get(i).awayXG, 2));
                }

                JTable table = new JTable(data, colHeads);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setEnabled(false);
                table.getTableHeader().setFont(fontLabel);
                table.setFont(fontLabel);
                table.setRowHeight(25);
                table.getColumnModel().getColumn(0).setPreferredWidth(50);
                table.getColumnModel().getColumn(1).setPreferredWidth(100);
                table.getColumnModel().getColumn(2).setPreferredWidth(350);
                table.getColumnModel().getColumn(3).setPreferredWidth(200);
                table.getColumnModel().getColumn(4).setPreferredWidth(100);
                table.getColumnModel().getColumn(5).setPreferredWidth(100);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for (int r=0; r<colHeads.length; r++)
                    table.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);

                JPanel tablePanel = new JPanel();
                tablePanel.setLayout(new BorderLayout());
                tablePanel.add(table, BorderLayout.CENTER);
                tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

                JButton[] arrOfButton = new JButton[selector.listOfMatches.size()];
                JPanel panelButtons = new JPanel(new VerticalLayout());
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    arrOfButton[i] = new JButton("Открыть окно матча");
                    arrOfButton[i].setPreferredSize(new Dimension(150, table.getRowHeight()));
                    panelButtons.add(arrOfButton[i]);

                    final int finalI = i;
                    arrOfButton[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            WindowMatchStats window = new WindowMatchStats(selector.listOfMatches.get(finalI));
                            window.setVisible(true);
                        }
                    });

                    tablePanel.add(panelButtons, BorderLayout.EAST);
                }

                container.add(tablePanel);

                String bets = "Варианты ставок и их проходимость в очных встречах команд:";
                final JLabel label2 = new JLabel(bets);
                label2.setFont(fontLabel);
                label2.setBorder(new EmptyBorder(10, 10, 0, 0));
                container.add(label2);

                String[] colHeads2 = {"Ставка" , "Победа " + homeTeamName, "X", "Победа " + awayTeamName, "П1", "П2", "ТБ(2.5)", "ТМ(2.5)", "Обе забьют", "Обе забьют - нет"};
                Object[][] data2 = new Object[1][colHeads2.length];
                int pHome = 0;
                int x = 0;
                int pAway = 0;
                int p1 = 0;
                int p2 = 0;
                int tb25 = 0;
                int oz = 0;
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    if (selector.listOfMatches.get(i).homeScore == selector.listOfMatches.get(i).awayScore)
                        x++;
                    if (selector.listOfMatches.get(i).homeScore > selector.listOfMatches.get(i).awayScore){
                        p1++;
                        if (selector.listOfMatches.get(i).homeTeam.equals(homeTeamName))
                            pHome++;
                        else
                            pAway++;
                    }
                    if (selector.listOfMatches.get(i).homeScore < selector.listOfMatches.get(i).awayScore){
                        p2++;
                        if (selector.listOfMatches.get(i).homeTeam.equals(homeTeamName))
                            pAway++;
                        else
                            pHome++;
                    }
                    if (selector.listOfMatches.get(i).homeScore + selector.listOfMatches.get(i).awayScore > 2.5)
                        tb25++;
                    if (selector.listOfMatches.get(i).homeScore * selector.listOfMatches.get(i).awayScore > 0)
                        oz++;
                }
                data2[0][0] = "Заход и %";
                data2[0][1] = pHome + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) pHome / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][2] = x + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) x / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][3] = pAway + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) pAway / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][4] = p1 + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) p1 / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][5] = p2 + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) p2 / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][6] = tb25 + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) tb25 / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][7] = selector.listOfMatches.size() - tb25 + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) (selector.listOfMatches.size() - tb25) / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][8] = oz + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) oz / selector.listOfMatches.size() * 100, 1) + "%";
                data2[0][9] = selector.listOfMatches.size() - oz + "/" + selector.listOfMatches.size() + "  " + MyMath.round((double) (selector.listOfMatches.size() - oz) / selector.listOfMatches.size() * 100, 1) + "%";

                JTable table2 = new JTable(data2, colHeads2);
                table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table2.setEnabled(false);
                table2.getTableHeader().setFont(fontLabel);
                table2.setFont(fontLabel);
                table2.setRowHeight(25);
                table2.getColumnModel().getColumn(0).setPreferredWidth(90);
                table2.getColumnModel().getColumn(1).setPreferredWidth(150);
                table2.getColumnModel().getColumn(2).setPreferredWidth(90);
                table2.getColumnModel().getColumn(3).setPreferredWidth(150);
                table2.getColumnModel().getColumn(4).setPreferredWidth(90);
                table2.getColumnModel().getColumn(5).setPreferredWidth(90);
                table2.getColumnModel().getColumn(6).setPreferredWidth(90);
                table2.getColumnModel().getColumn(7).setPreferredWidth(90);
                table2.getColumnModel().getColumn(8).setPreferredWidth(150);
                table2.getColumnModel().getColumn(9).setPreferredWidth(150);
                for (int r=0; r<colHeads2.length; r++)
                    table2.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);
                JPanel tablePanel2 = new JPanel();
                tablePanel2.setLayout(new BorderLayout());
                tablePanel2.add(table2, BorderLayout.CENTER);
                tablePanel2.add(table2.getTableHeader(), BorderLayout.NORTH);

                container.add(tablePanel2);

                String params = "Статистика очных матчей команд:";
                final JLabel label3 = new JLabel(params);
                label3.setFont(fontLabel);
                label3.setBorder(new EmptyBorder(10, 10, 0, 0));
                container.add(label3);

                String[] colHeads3 = {"№" , "Сезон", "Матч", "Владение", "Удары", "Уд. в створ", "Угловые", "Офсайды", "Фолы", "ЖК", "КК"};
                Object[][] data3 = new Object[selector.listOfMatches.size()][colHeads3.length];
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    data3[i][0] = String.valueOf(i+1);
                    data3[i][1] = selector.listForConfrontation.get(i);
                    data3[i][2] = Team.getShortName(selector.listOfMatches.get(i).homeTeam) + " - " + Team.getShortName(selector.listOfMatches.get(i).awayTeam);
                    data3[i][3] = selector.listOfMatches.get(i).homeBallPossession + " : " + selector.listOfMatches.get(i).awayBallPossession;
                    data3[i][4] = selector.listOfMatches.get(i).homeShots + " : " + selector.listOfMatches.get(i).awayShots;
                    data3[i][5] = selector.listOfMatches.get(i).homeShotsOnTarget + " : " + selector.listOfMatches.get(i).awayShotsOnTarget;
                    data3[i][6] = selector.listOfMatches.get(i).homeCorners + " : " + selector.listOfMatches.get(i).awayCorners;
                    data3[i][7] = selector.listOfMatches.get(i).homeOffsides + " : " + selector.listOfMatches.get(i).awayOffsides;
                    data3[i][8] = selector.listOfMatches.get(i).homeFouls + " : " + selector.listOfMatches.get(i).awayFouls;
                    data3[i][9] = selector.listOfMatches.get(i).homeYellowCards + " : " + selector.listOfMatches.get(i).awayYellowCards;
                    data3[i][10] = selector.listOfMatches.get(i).homeRedCards + " : " + selector.listOfMatches.get(i).awayRedCards;
                }

                JTable table3 = new JTable(data3, colHeads3);
                table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table3.setEnabled(false);
                table3.getTableHeader().setFont(fontLabel);
                table3.setFont(fontLabel);
                table3.setRowHeight(25);
                table3.getColumnModel().getColumn(0).setPreferredWidth(50);
                table3.getColumnModel().getColumn(1).setPreferredWidth(100);
                table3.getColumnModel().getColumn(2).setPreferredWidth(110);
                table3.getColumnModel().getColumn(3).setPreferredWidth(110);
                table3.getColumnModel().getColumn(4).setPreferredWidth(110);
                table3.getColumnModel().getColumn(5).setPreferredWidth(110);
                table3.getColumnModel().getColumn(6).setPreferredWidth(110);
                table3.getColumnModel().getColumn(7).setPreferredWidth(110);
                table3.getColumnModel().getColumn(8).setPreferredWidth(110);
                table3.getColumnModel().getColumn(9).setPreferredWidth(110);
                table3.getColumnModel().getColumn(10).setPreferredWidth(110);
                for (int r=0; r<colHeads3.length; r++)
                    table3.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);
                JPanel tablePanel3 = new JPanel();
                tablePanel3.setLayout(new BorderLayout());
                tablePanel3.add(table3, BorderLayout.CENTER);
                tablePanel3.add(table3.getTableHeader(), BorderLayout.NORTH);

                container.add(tablePanel3);

                String params1T = "Статистика 1-ых таймов очных матчей команд:";
                final JLabel label1T = new JLabel(params1T);
                label1T.setFont(fontLabel);
                label1T.setBorder(new EmptyBorder(10, 10, 0, 0));
                container.add(label1T);

                String[] colHeads1T = {"№" , "Сезон", "Матч", "Владение", "Удары", "Уд. в створ", "Угловые", "Офсайды", "Фолы", "ЖК", "КК"};
                Object[][] data1T = new Object[selector.listOfMatches.size()][colHeads1T.length];
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    data1T[i][0] = String.valueOf(i+1);
                    data1T[i][1] = selector.listForConfrontation.get(i);
                    data1T[i][2] = Team.getShortName(selector.listOfMatches.get(i).homeTeam) + " - " + Team.getShortName(selector.listOfMatches.get(i).awayTeam);
                    data1T[i][3] = selector.listOfMatches.get(i).homeBallPossession1T + " : " + selector.listOfMatches.get(i).awayBallPossession1T;
                    data1T[i][4] = selector.listOfMatches.get(i).homeShots1T + " : " + selector.listOfMatches.get(i).awayShots1T;
                    data1T[i][5] = selector.listOfMatches.get(i).homeShotsOnTarget1T + " : " + selector.listOfMatches.get(i).awayShotsOnTarget1T;
                    data1T[i][6] = selector.listOfMatches.get(i).homeCorners1T + " : " + selector.listOfMatches.get(i).awayCorners1T;
                    data1T[i][7] = selector.listOfMatches.get(i).homeOffsides1T + " : " + selector.listOfMatches.get(i).awayOffsides1T;
                    data1T[i][8] = selector.listOfMatches.get(i).homeFouls1T + " : " + selector.listOfMatches.get(i).awayFouls1T;
                    data1T[i][9] = selector.listOfMatches.get(i).homeYellowCards1T + " : " + selector.listOfMatches.get(i).awayYellowCards1T;
                    data1T[i][10] = "-";
                }

                JTable table1T = new JTable(data1T, colHeads1T);
                table1T.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table1T.setEnabled(false);
                table1T.getTableHeader().setFont(fontLabel);
                table1T.setFont(fontLabel);
                table1T.setRowHeight(25);
                table1T.getColumnModel().getColumn(0).setPreferredWidth(50);
                table1T.getColumnModel().getColumn(1).setPreferredWidth(100);
                table1T.getColumnModel().getColumn(2).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(3).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(4).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(5).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(6).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(7).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(8).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(9).setPreferredWidth(110);
                table1T.getColumnModel().getColumn(10).setPreferredWidth(110);
                for (int r=0; r<colHeads1T.length; r++)
                    table1T.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);
                JPanel tablePanel1T = new JPanel();
                tablePanel1T.setLayout(new BorderLayout());
                tablePanel1T.add(table1T, BorderLayout.CENTER);
                tablePanel1T.add(table1T.getTableHeader(), BorderLayout.NORTH);

                container.add(tablePanel1T);

                String params2T = "Статистика 2-ых таймов очных матчей команд:";
                final JLabel label2T = new JLabel(params2T);
                label2T.setFont(fontLabel);
                label2T.setBorder(new EmptyBorder(10, 10, 0, 0));
                container.add(label2T);

                String[] colHeads2T = {"№" , "Сезон", "Матч", "Владение", "Удары", "Уд. в створ", "Угловые", "Офсайды", "Фолы", "ЖК", "КК"};
                Object[][] data2T = new Object[selector.listOfMatches.size()][colHeads2T.length];
                for (int i=0; i<selector.listOfMatches.size(); i++){
                    data2T[i][0] = String.valueOf(i+1);
                    data2T[i][1] = selector.listForConfrontation.get(i);
                    data2T[i][2] = Team.getShortName(selector.listOfMatches.get(i).homeTeam) + " - " + Team.getShortName(selector.listOfMatches.get(i).awayTeam);
                    data2T[i][3] = selector.listOfMatches.get(i).homeBallPossession2T + " : " + selector.listOfMatches.get(i).awayBallPossession2T;
                    data2T[i][4] = selector.listOfMatches.get(i).homeShots2T + " : " + selector.listOfMatches.get(i).awayShots2T;
                    data2T[i][5] = selector.listOfMatches.get(i).homeShotsOnTarget2T + " : " + selector.listOfMatches.get(i).awayShotsOnTarget2T;
                    data2T[i][6] = selector.listOfMatches.get(i).homeCorners2T + " : " + selector.listOfMatches.get(i).awayCorners2T;
                    data2T[i][7] = selector.listOfMatches.get(i).homeOffsides2T + " : " + selector.listOfMatches.get(i).awayOffsides2T;
                    data2T[i][8] = selector.listOfMatches.get(i).homeFouls2T + " : " + selector.listOfMatches.get(i).awayFouls2T;
                    data2T[i][9] = selector.listOfMatches.get(i).homeYellowCards2T + " : " + selector.listOfMatches.get(i).awayYellowCards2T;
                    data2T[i][10] = "-";
                }

                JTable table2T = new JTable(data2T, colHeads2T);
                table2T.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table2T.setEnabled(false);
                table2T.getTableHeader().setFont(fontLabel);
                table2T.setFont(fontLabel);
                table2T.setRowHeight(25);
                table2T.getColumnModel().getColumn(0).setPreferredWidth(50);
                table2T.getColumnModel().getColumn(1).setPreferredWidth(100);
                table2T.getColumnModel().getColumn(2).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(3).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(4).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(5).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(6).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(7).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(8).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(9).setPreferredWidth(110);
                table2T.getColumnModel().getColumn(10).setPreferredWidth(110);
                for (int r=0; r<colHeads2T.length; r++)
                    table2T.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);
                JPanel tablePanel2T = new JPanel();
                tablePanel2T.setLayout(new BorderLayout());
                tablePanel2T.add(table2T, BorderLayout.CENTER);
                tablePanel2T.add(table2T.getTableHeader(), BorderLayout.NORTH);

                container.add(tablePanel2T);

            } else {
                container.setLayout(null);
                container.setPreferredSize(new Dimension(700, 730));

                String labelText = "В базе нет данных о личных встречах данных команд.";
                final JLabel label = new JLabel(labelText);
                label.setLocation(10, 0);
                label.setSize(new Dimension(700, 25));
                Font fontLabel = new Font("Arial", Font.BOLD, 15);
                label.setFont(fontLabel);
                container.add(label);
            }
        } else {
            container.setLayout(null);
            container.setPreferredSize(new Dimension(700, 730));

            final JLabel label = new JLabel("Задайте все условия поиска. Лига и/или команда не заданы.");
            label.setLocation(10, 0);
            label.setSize(new Dimension(700, 25));
            Font fontLabel = new Font("Arial", Font.BOLD, 15);
            label.setFont(fontLabel);
            container.add(label);

        }
        this.setCursor(Cursor.getDefaultCursor());
        scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );
        return scrollPane;
    }

    public void setFilters(String league, String homeTeam, String awayTeam){
        buttonShow.setEnabled(false);
        String season = Settings.getCurrentSeasonInLeague(league);
        seasonCB.setSelectedItem("Сезон " + season);
        leagueChooser.setSelectedItem(league);
        teamChooserHome.setSelectedItem(homeTeam);
        teamChooserAway.setSelectedItem(awayTeam);
        buttonShow.setEnabled(true);
    }
}



