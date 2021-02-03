package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelReferee extends JPanel{
    double procHEIGHT;
    RefereesThread refereesThread;
    JScrollPane scrollPane;
    JComboBox<String> seasonCB;
    JComboBox<String> leagueChooser;

    JLabel leftValue;
    JLabel rightValue;
    JLabel bottomValue;
    int indexForParameterChooser = 0;
    int indexForIndexChooser = 0;
    int indexForValueChooser = 0;
    double valueForSlider = 0;
    double minSliderValue = 0;
    double maxSliderValue = 0;
    double stepSlider = 0.5;
    JSlider slider = new JSlider(0,10,0);

    final Font font15 = new Font("Arial", Font.BOLD, 15);
    final Font font16 = new Font("Arial", Font.BOLD, 16);
    final Font font18 = new Font("Arial", Font.BOLD, 18);
    final Font font20 = new Font("Arial", Font.BOLD, 20);

    public PanelReferee(){
        this.setLayout(new BorderLayout());
        final String path = "database/";
        final String curSeason = Settings.getDefaultSeason();

        ////////////////////////////////////////////ПАНЕЛЬ
        JPanel panelChoosers = new JPanel(new GridLayout(1, 0, 5, 5));

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonCB = new JComboBox<>(seasonList);
        panelChoosers.add(seasonCB);

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

        String fileRefName = path + leagueChooser.getSelectedItem() +"/Referees/Referees.txt";
        String[] listForCB = {"Выберите арбитра"};
        if (!fileRefName.contains("ыберите")) {
            listForCB = Main.getListOfRefs(fileRefName);
        }
        final JComboBox<String> refChooser = new JComboBox<>(listForCB);
        refChooser.setEnabled(false);
        panelChoosers.add(refChooser);

        String[] lastOrFullSeasonString = {"Весь сезон", "Последние 3", "Последние 4", "Последние 5", "Последние 6", "Последние 7", "Последние 8", "Последние 9", "Последние 10", "Последние 15", "Последние 20"};
        final JComboBox<String> lastOrFullSeason = new JComboBox<>(lastOrFullSeasonString);
        panelChoosers.add(lastOrFullSeason);

        final JButton buttonShowInfo = new JButton("Отобразить!");
        Font fontForButton = new Font("", 0, 18);
        buttonShowInfo.setFont(fontForButton);
        panelChoosers.add(buttonShowInfo);

        this.add(panelChoosers, BorderLayout.NORTH);

        final JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        this.add(infoPanel);

        leagueChooser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int index = leagueChooser.getSelectedIndex();
                String pathToLeaguesList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser rightFileChooser = new JFileChooser(pathToLeaguesList);
                String[] rightDirectoryList = new String[rightFileChooser.getCurrentDirectory().list().length+1];
                rightDirectoryList[0] = "Выберите лигу";
                for (int i=1; i<rightDirectoryList.length; i++)
                    rightDirectoryList[i] = rightFileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(rightDirectoryList);
                leagueChooser.setModel(modelH);
                leagueChooser.setSelectedIndex(index);
                leagueChooser.setFocusable(false);

                refChooser.setEnabled(true);
                String pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + leagueChooser.getSelectedItem() + "/Referees/Referees.txt";
                String[] listRight = {"Выберите судью"};
                if (!pathToTeamsList.contains("ыберите")) {
                    listRight = Main.getListOfRefs(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(listRight);
                refChooser.setModel(modelH);
            }
        });

        refChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refChooser.setFocusable(false);
            }
        });

        seasonCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seasonCB.setFocusable(false);

                String ref = String.valueOf(refChooser.getSelectedItem());
                String league = String.valueOf(leagueChooser.getSelectedItem());

                String pathToLeaguesList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
                JFileChooser fileChooser = new JFileChooser(pathToLeaguesList);
                String[] directoryList = new String[fileChooser.getCurrentDirectory().list().length+1];
                directoryList[0] = "Выберите лигу";
                for (int i=1; i<directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i-1].replace(".txt", "");
                DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList);
                leagueChooser.setModel(modelH);

                for (int i=0; i<directoryList.length; i++){
                    if (directoryList[i].equals(league))
                        leagueChooser.setSelectedItem(league);
                }

                String pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Referees/Referees.txt";
                String[] list = {"Выберите судью"};
                if (!pathToTeamsList.contains("ыберите")) {
                    list = Main.getListOfRefs(pathToTeamsList);
                }
                modelH = new DefaultComboBoxModel(list);
                refChooser.setModel(modelH);

                pathToTeamsList = path + seasonCB.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Referees/";
                fileChooser = new JFileChooser(pathToTeamsList);
                directoryList = new String[fileChooser.getCurrentDirectory().list().length+1];
                directoryList[0] = "Выберите судью";
                for (int i=1; i<directoryList.length; i++)
                    directoryList[i] = fileChooser.getCurrentDirectory().list()[i-1].replace(".xml", "");
                for (int i=0; i<directoryList.length; i++){
                    if (directoryList[i].equals(ref)){
                        refChooser.setSelectedItem(ref);
                        refChooser.setEnabled(true);
                    }
                }
                leagueChooser.setFocusable(true);
            }
        });

        buttonShowInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scrollPane != null) {
                    infoPanel.remove(scrollPane);
                }
                scrollPane = refreshRefData((String) leagueChooser.getSelectedItem(),
                        (String) refChooser.getSelectedItem(),
                        (String) seasonCB.getSelectedItem(),
                        (String) lastOrFullSeason.getSelectedItem()
                );
                infoPanel.add(scrollPane);
                infoPanel.revalidate();
                buttonShowInfo.setFocusable(false);

            }
        });
    }

    public JScrollPane refreshRefData(final String leagueName, final String refName, final String season, final String lastOrFullSeason){
        this.setCursor(Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        JScrollPane scrollPane;
        if ((!leagueName.contains("Выберите"))&&(!refName.contains("Выберите"))){

            final Selector selector = new Selector();
            selector.getRefListOfMatches(leagueName, refName, season.replace("Сезон ", ""), lastOrFullSeason);

            if (selector.listOfMatches.size()>0){
                JPanel allInfoPanel = new JPanel(new BorderLayout());

                JPanel container = new JPanel(new VerticalLayout());
                int matches = selector.listOfMatches.size();
                League league = League.getLeagueFromFile(leagueName, season.replace("Сезон ", ""));
                double averageYC = MyMath.round((league.homeYellowCards + league.awayYellowCards) / (double) league.matchesPlayed, 2);
                double averageYC1T = MyMath.round((league.homeYellowCards1T + league.awayYellowCards1T) / (double) league.matchesPlayed, 2);
                double averageYC2T = MyMath.round((league.homeYellowCards2T + league.awayYellowCards2T) / (double) league.matchesPlayed, 2);
                double averageHomeYC = MyMath.round(league.homeYellowCards / (double) league.matchesPlayed, 2);
                double averageHomeYC1T = MyMath.round(league.homeYellowCards1T / (double) league.matchesPlayed, 2);
                double averageHomeYC2T = MyMath.round(league.homeYellowCards2T / (double) league.matchesPlayed, 2);
                double averageAwayYC = MyMath.round(league.awayYellowCards / (double) league.matchesPlayed, 2);
                double averageAwayYC1T = MyMath.round(league.awayYellowCards1T / (double) league.matchesPlayed, 2);
                double averageAwayYC2T = MyMath.round(league.awayYellowCards2T / (double) league.matchesPlayed, 2);
                String sAvarageYC = String.valueOf(averageYC) + "   -   " + String.valueOf(averageHomeYC) + "   -   " + String.valueOf(averageAwayYC);
                String sAvarageYC1T = String.valueOf(averageYC1T) + "   -   " + String.valueOf(averageHomeYC1T) + "   -   " + String.valueOf(averageAwayYC1T);
                String sAvarageYC2T = String.valueOf(averageYC2T) + "   -   " + String.valueOf(averageHomeYC2T) + "   -   " + String.valueOf(averageAwayYC2T);

                double averageFouls = MyMath.round((league.homeFouls + league.awayFouls) / (double) league.matchesPlayed, 2);
                double averageHomeFouls = MyMath.round(league.homeFouls / (double) league.matchesPlayed, 2);
                double averageAwayFouls = MyMath.round(league.awayFouls / (double) league.matchesPlayed, 2);
                String sAverageFouls = String.valueOf(averageFouls) + "   -   " + String.valueOf(averageHomeFouls) + "   -   " + String.valueOf(averageAwayFouls);


                String labelRef = "   Статистика арбитра " + refName + " в сезоне " + season.replace("Сезон ", "");
                if (lastOrFullSeason.contains("Последние")){
                    labelRef = "   Статистика последних " + selector.listOfMatches.size() + " матчей арбитра " + refName + " в сезоне " + season.replace("Сезон ", "");
                }
                final JLabel label = new JLabel(labelRef);
                label.setBorder(new EmptyBorder(10, 10, 0, 0));
                Font fontLabel = new Font("Arial", Font.BOLD, 15);
                label.setFont(fontLabel);
                container.add(label);

                final JLabel label2 = new JLabel("   Провел матчей:  " + String.valueOf(matches));
                label2.setBorder(new EmptyBorder(5, 10, 0, 0));
                label2.setFont(fontLabel);
                container.add(label2);

                String[] colHeads = {"Параметр" , "Всего", "Хозяева", "Гости", "В среднем по лиге (общ. - хоз. - гос.)"};
                final String[] params = {"Желтые карточки (все)", "Желтые карточки 1 тайм ", "Желтые карточки 2 тайм ",
                        "Желтые карточки (в среднем за матч)", "Желтые карточки 1 тайм (в среднем)",
                         "Желтые карточки 2 тайм (в среднем)", "СКО желтых карточек", "СКО фолов",
                        "ЖК --> КК", "Прямые КК", "Фолы (в среднем за матч)", "Фолы 1 тайм (сред.)", "Фолы 2 тайм (сред.)", "Назначенные пенальти"};
                Object[][] data;

                if (Settings.isWhoScoredLeague(leagueName)){
                    data = new Object[][]{
                            {" " + params[0], (int) Double.parseDouble(selector.refList.get(0).get(1)), (int) Double.parseDouble(selector.refList.get(0).get(2)), (int) Double.parseDouble(selector.refList.get(0).get(3)), "-"},
                            {" " + params[1], (int) Double.parseDouble(selector.refList.get(1).get(1)), (int) Double.parseDouble(selector.refList.get(1).get(2)), (int) Double.parseDouble(selector.refList.get(1).get(3)), "-"},
                            {" " + params[2], (int) Double.parseDouble(selector.refList.get(2).get(1)), (int) Double.parseDouble(selector.refList.get(2).get(2)), (int) Double.parseDouble(selector.refList.get(2).get(3)), "-"},
                            {" " + params[3], MyMath.round(Double.parseDouble(selector.refList.get(0).get(1)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(0).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(0).get(3)) / matches, 2), sAvarageYC},
                            {" " + params[4], MyMath.round(Double.parseDouble(selector.refList.get(1).get(1)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(1).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(1).get(3)) / matches, 2), sAvarageYC1T},
                            {" " + params[5], MyMath.round(Double.parseDouble(selector.refList.get(2).get(1)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(2).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(2).get(3)) / matches, 2), sAvarageYC2T},
                            {" " + params[6], MyMath.round(Double.parseDouble(selector.refList.get(9).get(1)), 2), MyMath.round(Double.parseDouble(selector.refList.get(9).get(2)), 2), MyMath.round(Double.parseDouble(selector.refList.get(9).get(3)), 2), "-"},
                            {" " + params[7], MyMath.round(Double.parseDouble(selector.refList.get(10).get(1)), 2), MyMath.round(Double.parseDouble(selector.refList.get(10).get(2)), 2), MyMath.round(Double.parseDouble(selector.refList.get(10).get(3)), 2), "-"},
                            {" " + params[8], (int) Double.parseDouble(selector.refList.get(3).get(1)), (int) Double.parseDouble(selector.refList.get(3).get(2)), (int) Double.parseDouble(selector.refList.get(3).get(3)), "-"},
                            {" " + params[9], (int) Double.parseDouble(selector.refList.get(4).get(1)), (int) Double.parseDouble(selector.refList.get(4).get(2)), (int) Double.parseDouble(selector.refList.get(4).get(3)), "-"},
                            {" " + params[10], MyMath.round(Double.parseDouble(selector.refList.get(5).get(1)) / selector.refNumberOfMatchesWithParam[5], 2), MyMath.round(Double.parseDouble(selector.refList.get(5).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(5).get(3)) / matches, 2), sAverageFouls},
                            {" " + params[11], MyMath.round(Double.parseDouble(selector.refList.get(6).get(1)) / selector.refNumberOfMatchesWithParam[6], 2), MyMath.round(Double.parseDouble(selector.refList.get(6).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(6).get(3)) / matches, 2), "-"},
                            {" " + params[12], MyMath.round(Double.parseDouble(selector.refList.get(7).get(1)) / selector.refNumberOfMatchesWithParam[7], 2), MyMath.round(Double.parseDouble(selector.refList.get(7).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(7).get(3)) / matches, 2), "-"},
                            {" " + params[13], (int) Double.parseDouble(selector.refList.get(8).get(1)), (int) Double.parseDouble(selector.refList.get(8).get(2)), (int) Double.parseDouble(selector.refList.get(8).get(3)), "-"},
                    };
                } else {
                    data = new Object[][]{
                            {" " + params[0], (int) Double.parseDouble(selector.refList.get(0).get(1)), (int) Double.parseDouble(selector.refList.get(0).get(2)), (int) Double.parseDouble(selector.refList.get(0).get(3)), "-"},
                            {" " + params[1], (int) Double.parseDouble(selector.refList.get(1).get(1)), (int) Double.parseDouble(selector.refList.get(1).get(2)), (int) Double.parseDouble(selector.refList.get(1).get(3)), "-"},
                            {" " + params[2], (int) Double.parseDouble(selector.refList.get(2).get(1)), (int) Double.parseDouble(selector.refList.get(2).get(2)), (int) Double.parseDouble(selector.refList.get(2).get(3)), "-"},
                            {" " + params[3], MyMath.round(Double.parseDouble(selector.refList.get(0).get(1)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(0).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(0).get(3)) / matches, 2), "-"},
                            {" " + params[4], MyMath.round(Double.parseDouble(selector.refList.get(1).get(1)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(1).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(1).get(3)) / matches, 2), "-"},
                            {" " + params[5], MyMath.round(Double.parseDouble(selector.refList.get(2).get(1)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(2).get(2)) / matches, 2), MyMath.round(Double.parseDouble(selector.refList.get(2).get(3)) / matches, 2), "-"},
                            {" " + params[6], MyMath.round(Double.parseDouble(selector.refList.get(9).get(1)), 2), MyMath.round(Double.parseDouble(selector.refList.get(9).get(2)), 2), MyMath.round(Double.parseDouble(selector.refList.get(9).get(3)), 2), "-"},
                            {" " + params[7], "-", "-", "-", "-"},
                            {" " + params[8], (int) Double.parseDouble(selector.refList.get(3).get(1)), (int) Double.parseDouble(selector.refList.get(3).get(2)), (int) Double.parseDouble(selector.refList.get(3).get(3)), "-"},
                            {" " + params[9], (int) Double.parseDouble(selector.refList.get(4).get(1)), (int) Double.parseDouble(selector.refList.get(4).get(2)), (int) Double.parseDouble(selector.refList.get(4).get(3)), "-"},
                            {" " + params[10], MyMath.round(Double.parseDouble(selector.refList.get(5).get(1)) / selector.refNumberOfMatchesWithParam[5], 2), MyMath.round(Double.parseDouble(selector.refList.get(5).get(2))  / selector.refNumberOfMatchesWithParam[5], 2), MyMath.round(Double.parseDouble(selector.refList.get(5).get(3))  / selector.refNumberOfMatchesWithParam[5], 2), "-"},
                            {" " + params[11], MyMath.round(Double.parseDouble(selector.refList.get(6).get(1)) / selector.refNumberOfMatchesWithParam[6], 2), MyMath.round(Double.parseDouble(selector.refList.get(6).get(2))  / selector.refNumberOfMatchesWithParam[6], 2), MyMath.round(Double.parseDouble(selector.refList.get(6).get(3))  / selector.refNumberOfMatchesWithParam[6], 2), "-"},
                            {" " + params[12], MyMath.round(Double.parseDouble(selector.refList.get(7).get(1)) / selector.refNumberOfMatchesWithParam[7], 2), MyMath.round(Double.parseDouble(selector.refList.get(7).get(2))  / selector.refNumberOfMatchesWithParam[7], 2), MyMath.round(Double.parseDouble(selector.refList.get(7).get(3))  / selector.refNumberOfMatchesWithParam[7], 2), "-"},
                            {" " + params[13], (int) Double.parseDouble(selector.refList.get(8).get(1)), (int) Double.parseDouble(selector.refList.get(8).get(2)), (int) Double.parseDouble(selector.refList.get(8).get(3)), "-"},
                    };
                }

                Font font = new Font("Arial", Font.BOLD, 15);
                JTable table = new JTable(data, colHeads);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setEnabled(false);
                table.getTableHeader().setFont(font);
                table.setFont(font);
                table.getColumnModel().getColumn(0).setPreferredWidth(300);
                table.setRowHeight(25);
                table.getColumnModel().getColumn(1).setPreferredWidth(100);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(2).setPreferredWidth(100);
                table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(3).setPreferredWidth(100);
                table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(4).setPreferredWidth(200);
                table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                JPanel tablePanel = new JPanel();
                tablePanel.setLayout(new BorderLayout());
                tablePanel.setBorder(new EmptyBorder(10, 10, 0, 0));
                tablePanel.add(table);
                tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
                tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));

                container.add(tablePanel);

                JPanel panelWithButton = new JPanel();
                JButton buttonLeagueTable = new JButton("Построить таблицу арбитров по лиге");
                //buttonLeagueTable.setPreferredSize(new Dimension((int) (150*procWIDTH), 30));
                panelWithButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                Font fontForButton = new Font("", 0, 15);
                buttonLeagueTable.setFont(fontForButton);
                panelWithButton.add(buttonLeagueTable);
                container.add(panelWithButton);

                if (Settings.isWhoScoredLeague(leagueName)){
                    final JLabel label3 = new JLabel("   Корреляции показателей у арбитра " + refName);
                    label3.setBorder(new EmptyBorder(5, 10, 0, 0));
                    label3.setFont(fontLabel);
                    container.add(label3);

                    colHeads = new String[]{"Тотал фолов и тотал ЖК", "Фолы хозяев и ЖК хозяев", "Фолы гостей и ЖК гостей", "Фора по фолам и фора по ЖК"};
                    data = new Object[][]{{selector.refList.get(11).get(1), selector.refList.get(11).get(2), selector.refList.get(11).get(3), selector.refList.get(12).get(1)}};

                    JTable tableCorr = new JTable(data, colHeads);
                    tableCorr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    tableCorr.setEnabled(false);
                    tableCorr.getTableHeader().setFont(font);
                    tableCorr.setFont(font);
                    tableCorr.setRowHeight(25);
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                    tableCorr.getColumnModel().getColumn(0).setPreferredWidth(100);
                    tableCorr.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                    tableCorr.getColumnModel().getColumn(1).setPreferredWidth(100);
                    tableCorr.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                    tableCorr.getColumnModel().getColumn(2).setPreferredWidth(100);
                    tableCorr.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                    tableCorr.getColumnModel().getColumn(3).setPreferredWidth(100);
                    tableCorr.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                    JPanel tablePanelCorr = new JPanel();
                    tablePanelCorr.setLayout(new BorderLayout());
                    tablePanelCorr.setBorder(new EmptyBorder(10, 10, 0, 0));
                    tablePanelCorr.add(tableCorr);
                    tablePanelCorr.add(tableCorr.getTableHeader(), BorderLayout.NORTH);
                    tablePanelCorr.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));

                    container.add(tablePanelCorr);

                    //Вставка просмотрщика параметров
                    final JPanel paramsPanel = new JPanel(new VerticalLayout());

                    JLabel labelTitleParamsPanel = new JLabel("Слайдер проходимости");
                    labelTitleParamsPanel.setFont(font15);
                    labelTitleParamsPanel.setHorizontalAlignment(SwingConstants.CENTER);
                    labelTitleParamsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
                    paramsPanel.add(labelTitleParamsPanel);

//                    paramsPanel.setBorder(BorderFactory.createTitledBorder(""));
                    paramsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    JPanel selectorsPanel = new JPanel(new GridLayout(1, 0, 5, 5));

                    String[] paramsList = Parameters.getRefParamsList();
                    final JComboBox<String> paramsChooser = new JComboBox<>(paramsList);
                    paramsChooser.setFont(font15);
                    paramsChooser.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    selectorsPanel.add(paramsChooser);

                    String[] indexList = {"Выберите тип ставки"};
                    final JComboBox<String> indexChooser = new JComboBox<>(indexList);
                    indexChooser.setFont(font15);
                    indexChooser.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
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

                    container.add(paramsPanel);

                    paramsChooser.setSelectedIndex(indexForParameterChooser);
                    String[] list = Parameters.getRefIndex((String) paramsChooser.getSelectedItem());
                    DefaultComboBoxModel modelH = new DefaultComboBoxModel(list);
                    indexChooser.setModel(modelH);
                    indexChooser.setSelectedIndex(indexForIndexChooser);
                    slider.setValue(indexForValueChooser);
                    final double[] sliderParams = Parameters.getRefValues((String) paramsChooser.getSelectedItem(), (String) indexChooser.getSelectedItem());
                    stepSlider = sliderParams[2];

                    if (sliderParams[2] > 0){
                        minSliderValue = sliderParams[0];
                        stepSlider = sliderParams[2];
                        leftValue.setText(String.valueOf(sliderParams[0]));
                        rightValue.setText(String.valueOf(sliderParams[1]));
                        bottomValue.setText("Выбрано значение: " + valueForSlider);
                        getParamsPanel(paramsPanel, refName, selector, paramsChooser, indexChooser, valueForSlider);
                        slider.setMajorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setEnabled(true);
                        int numberOfVariants = (int) ((sliderParams[1] - sliderParams[0]) / sliderParams[2] + 1);
                        slider.setMaximum(numberOfVariants-1);
                    }

                    paramsChooser.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indexForParameterChooser = paramsChooser.getSelectedIndex();
                            String[] list = Parameters.getRefIndex((String) paramsChooser.getSelectedItem());
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
                            double[] sliderParams = Parameters.getRefValues((String) paramsChooser.getSelectedItem(), (String) indexChooser.getSelectedItem());
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

                            getParamsPanel(paramsPanel, refName, selector, paramsChooser, indexChooser, valueForSlider);
                            indexChooser.setFocusable(false);
                        }
                    });

                    slider.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                            valueForSlider = minSliderValue + stepSlider*slider.getValue();
                            bottomValue.setText("Выбрано значение: " + String.valueOf(valueForSlider));
                            indexForValueChooser = slider.getValue();
                            getParamsPanel(paramsPanel, refName, selector, paramsChooser, indexChooser, valueForSlider);
                        }
                    });


                }


                allInfoPanel.add(container, BorderLayout.NORTH);

                ////////////////////////ГРАФИКИ И ТАБЛИЦЫ ВСТАВЛЯЮ ТУТ
                JPanel graphAndTables = new JPanel(new BorderLayout());

                Graphic graphic = new Graphic(0, refName);
                JPanel panelGraphics = graphic.getRefGraphics(refName, selector);
                graphAndTables.add(panelGraphics, BorderLayout.CENTER);


                ////////////////////////ГРАФИКИ И ТАБЛИЦЫ БОЛЬШЕ НЕ ВСТАВЛЯЮ
                allInfoPanel.add(graphAndTables, BorderLayout.CENTER);

                scrollPane = new JScrollPane(allInfoPanel);
                scrollPane.setVerticalScrollBar( new JScrollBar() {
                    public int getUnitIncrement( int direction ) {
                        return 50;
                    }
                } );

                buttonLeagueTable.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        refereesThread = new RefereesThread(leagueName, season.replace("Сезон ", ""), lastOrFullSeason);
                        refereesThread.start();
                    }
                });

            } else {
                JPanel container = new JPanel(new BorderLayout());

                String labelText = "   В сезоне " + season + " по арбитру " + refName + " информации нет.";
                final JLabel label = new JLabel(labelText);
                label.setLocation(10, 0);
                label.setPreferredSize(new Dimension(800, 25));
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

            final JLabel label = new JLabel("   Задайте все условия поиска. Лига и/или судья не заданы.");
            label.setLocation(10, 0);
            label.setPreferredSize(new Dimension(800, 25));
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
        this.setCursor(Cursor.getDefaultCursor());
        return scrollPane;
    }

    private void getParamsPanel(JPanel paramsPanel, String teamName, Selector selector, JComboBox paramsChooser, JComboBox indexChooser, double valueOfSlider){
        if (paramsPanel.getComponentCount()>3){
            paramsPanel.remove(3);
        }
        final JTable tableByParams = Parameters.getTableRefByParams(teamName, selector.listOfMatches, (String) (paramsChooser.getSelectedItem()), (String) (indexChooser.getSelectedItem()), valueOfSlider);
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
        seasonCB.setSelectedItem("Сезон " + season);
        leagueChooser.setSelectedItem(league);
    }

}

