package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class YC_Calculator extends JPanel{
    final Font font15 = new Font("Arial", Font.BOLD, 15);
    final Font font16 = new Font("Arial", Font.BOLD, 16);
    final Font font18 = new Font("Arial", Font.BOLD, 18);
    final Font font20 = new Font("Arial", Font.BOLD, 20);
    final Font font25 = new Font("Arial", Font.BOLD, 25);

    int graphicHeight = 350;
    int indexForParameterChooser = 0;
    int indexForIndexChooser = 0;
    int indexForValueChooser = 0;
    double valueForSlider = 0;
    double minSliderValue = 0.5;
    double maxSliderValue = 6.5;
    double stepSlider = 0.5;
    JLabel leftValue;
    JLabel rightValue;
    JLabel bottomValue;
    JSlider slider = new JSlider(0,12,0);
    JComboBox<String> seasonChooser;
    JComboBox<String> leagueChooser;
    JPanel teamPanel;
    JPanel infoPanel;
    JButton buttonShowInfo;
    JRadioButton totalB;
    JRadioButton totalM;


    public YC_Calculator(){
        this.setLayout(new BorderLayout());
        String curSeason = Settings.getDefaultSeason();
        final String path = "database/";

        ////////////////////////////////////////////ПАНЕЛЬ
        JPanel panelFilters = new JPanel(new VerticalLayout());

        JPanel panelChoosers1 = new JPanel(new GridLayout(1, 0, 5, 5));

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonChooser = new JComboBox<>(seasonList);
        panelChoosers1.add(seasonChooser);

        JFileChooser fileChooser = new JFileChooser(path + curSeason + "/leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        ArrayList<String> leagueList = new ArrayList<>();
        leagueList.add("Выберите лигу");
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        String[] listOfLeagues = new String[leagueList.size()];
        for (int i = 0; i < listOfLeagues.length; i++)
            listOfLeagues[i] = leagueList.get(i);
        leagueChooser = new JComboBox<>(listOfLeagues);
        panelChoosers1.add(leagueChooser);

        String fileName = path + curSeason + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
        String[] list = {"Выберите команду"};
        if (!fileName.contains("ыберите")) {
            list = Main.readTxtFile(fileName);
        }
        final JComboBox<String> homeTeamChooser = new JComboBox<>(list);
        panelChoosers1.add(homeTeamChooser);

        final JComboBox<String> awayTeamChooser = new JComboBox<>(list);
        panelChoosers1.add(awayTeamChooser);

        String fileRefName = path + leagueChooser.getSelectedItem() +"/Referees/Referees.txt";
        String[] listForCB = {"Выберите арбитра"};
        if (!fileRefName.contains("ыберите")) {
            listForCB = Main.getListOfRefs(fileRefName);
        }
        final JComboBox<String> refChooser = new JComboBox<>(listForCB);
        panelChoosers1.add(refChooser);

        panelFilters.add(panelChoosers1);

        JPanel panelChoosers2 = new JPanel( new GridLayout(1, 0, 5, 5));

        JPanel rbPanel = new JPanel(new FlowLayout());
        rbPanel.setBorder(BorderFactory.createTitledBorder("Больше / меньше"));
        totalB = new JRadioButton("Тотал больше");
        totalB.setFont(font20);
        totalM = new JRadioButton("Тотал меньше");
        totalM.setFont(font20);

        totalB.setSelected(true);

        final ButtonGroup group = new ButtonGroup();
        group.add(totalB);
        group.add(totalM);
        rbPanel.add(totalB);
        rbPanel.add(totalM);
        panelChoosers2.add(rbPanel);

        JPanel panelSlider = new JPanel(new BorderLayout());
        panelSlider.setBorder(BorderFactory.createTitledBorder("Задать значение тотала"));
        leftValue = new JLabel("0.5");
        leftValue.setFont(font15);
        leftValue.setPreferredSize(new Dimension(40, 40));
        leftValue.setHorizontalAlignment(SwingConstants.CENTER);
        panelSlider.add(leftValue, BorderLayout.WEST);
        rightValue = new JLabel("6.5");
        rightValue.setFont(font15);
        rightValue.setPreferredSize(new Dimension(40, 40));
        rightValue.setHorizontalAlignment(SwingConstants.CENTER);
        panelSlider.add(rightValue, BorderLayout.EAST);
        valueForSlider = minSliderValue + stepSlider*slider.getValue();
        bottomValue = new JLabel("Выбрано значение: " + valueForSlider);
        bottomValue.setFont(font15);
        bottomValue.setPreferredSize(new Dimension(40, 40));
        bottomValue.setHorizontalAlignment(SwingConstants.CENTER);
        panelSlider.add(bottomValue, BorderLayout.SOUTH);


        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        panelSlider.add(slider);
        panelChoosers2.add(panelSlider);

        panelFilters.add(panelChoosers2);


        buttonShowInfo = new JButton("Расчет!");
        buttonShowInfo.setFont(font18);
        panelFilters.add(buttonShowInfo);

        this.add(panelFilters, BorderLayout.NORTH);

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        teamPanel = new JPanel();
        infoPanel.add(teamPanel, BorderLayout.CENTER);

        this.add(infoPanel);

        seasonChooser.addActionListener(e -> {
            seasonChooser.setFocusable(false);

            String homeTeam = String.valueOf(homeTeamChooser.getSelectedItem());
            String awayTeam = String.valueOf(awayTeamChooser.getSelectedItem());
            String league = String.valueOf(leagueChooser.getSelectedItem());

            String pathToLeaguesList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/";
            JFileChooser fileChooser1 = new JFileChooser(pathToLeaguesList);
            String[] directoryList1 = new String[fileChooser1.getCurrentDirectory().list().length + 1];
            directoryList1[0] = "Выберите лигу";
            for (int i = 1; i < directoryList1.length; i++)
                directoryList1[i] = fileChooser1.getCurrentDirectory().list()[i - 1].replace(".txt", "");
            DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList1);
            leagueChooser.setModel(modelH);

            for (int i = 0; i < directoryList1.length; i++) {
                if (directoryList1[i].equals(league))
                    leagueChooser.setSelectedItem(league);
            }

            String pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
            String[] list1 = {"Выберите команду"};
            if (!pathToTeamsList.contains("ыберите")) {
                list1 = Main.readTxtFile(pathToTeamsList);
            }
            modelH = new DefaultComboBoxModel(list1);
            homeTeamChooser.setModel(modelH);
            homeTeamChooser.setEnabled(false);
            awayTeamChooser.setModel(modelH);
            awayTeamChooser.setEnabled(false);

            pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/" + league + "/Teams/";
            fileChooser1 = new JFileChooser(pathToTeamsList);
            directoryList1 = new String[fileChooser1.getCurrentDirectory().list().length + 1];
            directoryList1[0] = "Выберите команду";
            for (int i = 1; i < directoryList1.length; i++)
                directoryList1[i] = fileChooser1.getCurrentDirectory().list()[i - 1].replace(".xml", "");
            for (int i = 0; i < directoryList1.length; i++) {
                if (directoryList1[i].equals(homeTeam)) {
                    homeTeamChooser.setSelectedItem(homeTeam);
                    homeTeamChooser.setEnabled(true);
                    awayTeamChooser.setSelectedItem(awayTeam);
                    awayTeamChooser.setEnabled(true);
                }
            }
            leagueChooser.setFocusable(true);
        });

        leagueChooser.addActionListener(e -> {
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

            homeTeamChooser.setEnabled(true);
            awayTeamChooser.setEnabled(true);
            String pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/leagues/" + leagueChooser.getSelectedItem() + ".txt";
            String[] listRight = {"Выберите команду"};
            if (!pathToTeamsList.contains("ыберите")) {
                listRight = Main.readTxtFile(pathToTeamsList);
            }

            DefaultComboBoxModel modelH2;
            modelH = new DefaultComboBoxModel(listRight);
            modelH2 = new DefaultComboBoxModel(listRight);

            homeTeamChooser.setModel(modelH);
            awayTeamChooser.setModel(modelH2);

            pathToTeamsList = path + seasonChooser.getSelectedItem().toString().replace("Сезон ", "") + "/" + leagueChooser.getSelectedItem() + "/Referees/Referees.txt";
            String[] listOfRefs = {"Выберите судью"};
            if (!pathToTeamsList.contains("ыберите")) {
                listOfRefs = Main.getListOfRefs(pathToTeamsList);
            }
            modelH = new DefaultComboBoxModel(listOfRefs);
            refChooser.setModel(modelH);

        });

        homeTeamChooser.addActionListener(e -> {
            homeTeamChooser.setFocusable(false);
        });

        awayTeamChooser.addActionListener(e -> {
            awayTeamChooser.setFocusable(false);
        });

        slider.addChangeListener(e -> {
            valueForSlider = minSliderValue + stepSlider*slider.getValue();
            bottomValue.setText("Выбрано значение: " + valueForSlider);
            indexForValueChooser = slider.getValue();
        });

        buttonShowInfo.addActionListener(e -> {
            if (teamPanel != null) {
                infoPanel.remove(teamPanel);
            }
            teamPanel = refreshData(
                    (String) seasonChooser.getSelectedItem(),
                    (String) leagueChooser.getSelectedItem(),
                    (String) homeTeamChooser.getSelectedItem(),
                    (String) awayTeamChooser.getSelectedItem(),
                    (String) refChooser.getSelectedItem()
            );
            infoPanel.add(teamPanel);
            infoPanel.revalidate();
            buttonShowInfo.setFocusable(false);
        });

    }

    public JPanel refreshData(String season, String leagueName, final String homeTeam, String awayTeam, String ref){
        this.setCursor(Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        JPanel result = new JPanel(new VerticalLayout());
        final Settings settings = Settings.getSettingsFromFile();
        season = season.replace("Сезон ", "");
        final JScrollPane scrollPane;
        String parameter = "";
        if (totalB.isSelected())
            parameter = "Тотал больше";
        else
            parameter = "Тотал меньше";

        valueForSlider = minSliderValue + stepSlider*slider.getValue();



        if (!leagueName.contains("ыберит") && !homeTeam.contains("ыберит") && !awayTeam.contains("ыберит") && !ref.contains("ыберит")){
            JPanel panelData = new JPanel(new VerticalLayout());

            String textResult = "<html>";
            boolean flag = false;
            JLabel labelBet = new JLabel("");

            boolean refNumberOfMatchesFlag = true;

            League league = League.getLeagueFromFile(leagueName, season);
            double averageYC = MyMath.round((league.homeYellowCards+league.awayYellowCards)/ (double) league.matchesPlayed , 2);
            double borderYC = 15;
            //Referee referee = Referee.getRefFromFile(ref, season, leagueName);

            Selector selectorRef20 = new Selector();
            selectorRef20.getRefNMatches(leagueName, ref, season, refNumberOfMatchesFlag, 20);
            int refMatches20 = selectorRef20.listOfMatches.size();

            double refYC20 = MyMath.round(Double.parseDouble(selectorRef20.refList.get(0).get(1)) / refMatches20, 2);
            double diff20 = MyMath.round(Math.abs((refYC20 / averageYC) * 100 - 100), 1);

            Selector selectorRef5 = new Selector();
            selectorRef5.getRefNMatches(leagueName, ref, season, refNumberOfMatchesFlag, 5);
            int refMatches5 = selectorRef5.listOfMatches.size();

            double refYC5 = MyMath.round(Double.parseDouble(selectorRef5.refList.get(0).get(1)) / refMatches5, 2);
            double diff5 = MyMath.round(Math.abs((refYC5 / refYC20) * 100 - 100), 1);


            if (refNumberOfMatchesFlag && diff20 > borderYC && diff5 < borderYC){
                flag = true;

                textResult += "Судья подходит. <br>" +
                        "Средние ЖК за матч по лиге: " + averageYC + "<br>" +
                        "Средние ЖК арбитра " + ref + ": " + refYC20 + "<br>" +
                        "Отклонение судьи: " + diff20 + "<br>" +
                        "Отклонение судьи за 5 игр: " + diff5 + "<br>" +
                        "<br>";

                Selector selectorHT = new Selector();
                selectorHT.getListOfMatches(leagueName, homeTeam, "Дома", season, "Весь сезон");
                selectorHT.getPList(selectorHT.listOfMatches, homeTeam);
                Selector selectorAT = new Selector();
                selectorAT.getListOfMatches(leagueName, awayTeam, "На выезде", season, "Весь сезон");
                selectorAT.getPList(selectorAT.listOfMatches, awayTeam);

                double teamsMatches = selectorHT.listOfMatches.size() + selectorAT.listOfMatches.size();
                double htPlus = getPlusYC(parameter, selectorHT.listOfMatches, valueForSlider);
                double atPlus = getPlusYC(parameter, selectorAT.listOfMatches, valueForSlider);

                double teamsPercent = MyMath.round( (htPlus+atPlus)/teamsMatches * 100, 2);

                double refPlus = getPlusYC(parameter, selectorRef20.listOfMatches, valueForSlider);
                double refPercent = MyMath.round( refPlus/refMatches20 * 100, 2);

                textResult += "Показатели по командам: " + (htPlus+atPlus) + " из " + (int) teamsMatches +  ": " + teamsPercent + "% <br>" +
                        "Процент команд: " + teamsPercent + "<br><br>" +

                        "Средние ЖК арбитра " + ref + ": " + refYC20 + "<br>" +
                        "Процент арбитра: " + refPercent + "<br><br>";

                double totalPercent = MyMath.round( (refPercent+teamsPercent)/ 2.0, 2);

                textResult += "Итоговый процент: " + totalPercent + "% <br><br>";


                if (totalPercent > 60){
                    double homeTeamPercent = htPlus / (double) selectorHT.listOfMatches.size() * 100;
                    double awayTeamPercent = atPlus / (double) selectorAT.listOfMatches.size() * 100;


                    /*if (homeTeamPercent < 50){
                        textResult += "Дополнительный расчет хозяев: <br>";

                        double homeTeamYC = Double.parseDouble(selectorHT.pList.get(14).get(1))
                                + Double.parseDouble(selectorHT.pList.get(14).get(2));

                        double deltaHT = MyMath.round(Math.abs(homeTeamYC / averageYC *100 - 100) , 1);
                        double deltaRef = MyMath.round(Math.abs(refYC20 / averageYC *100 - 100), 1);

                        if (deltaRef > deltaHT){
                            textResult += "Отклонение судьи ( " + deltaRef + ") больше отклонения хозяев (" + deltaHT + ").<br>" +
                                    "Это допустимо. <br>";
                        } else {
                            textResult += "Отклонение судьи ( " + deltaRef + ") не больше отклонения хозяев (" + deltaHT + ").<br>" +
                                    "Условия не выполнены. <br>";
                            flag = false;
                        }
                    }

                    if (awayTeamPercent < 50){
                        textResult += "Дополнительный расчет гостей: <br>";

                        double awayTeamYC = Double.parseDouble(selectorAT.pList.get(14).get(1))
                                + Double.parseDouble(selectorAT.pList.get(14).get(2));

                        double deltaAT = MyMath.round(Math.abs(awayTeamYC / averageYC *100 - 100) , 1);
                        double deltaRef = MyMath.round(Math.abs(refYC20 / averageYC *100 - 100), 1);

                        if (deltaRef > deltaAT){
                            textResult += "Отклонение судьи ( " + deltaRef + ") больше отклонения гостей (" + deltaAT + ").<br>" +
                                    "Это допустимо. <br>";
                        } else {
                            textResult += "Отклонение судьи ( " + deltaRef + ") не больше отклонения гостей (" + deltaAT + ").<br>" +
                                    "Условия не выполнены. <br>";
                            flag = false;
                        }
                    }*/





                } else {
                    textResult += "Итоговый процент не подходит. <br>";
                    flag = false;
                }

                if (flag){
                    labelBet.setText("<html> <br>Ставка: " + parameter + " " + valueForSlider + "</html>");
                    labelBet.setFont(font25);
                }

                int ttt = 0;



            } else {
                textResult += "Судья не подходит <br>";
            }


            textResult += "</html>";

            JLabel labelResult = new JLabel(textResult);
            labelResult.setFont(font20);

            panelData.add(labelResult);
            panelData.add(labelBet);

            panelData.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            int ttt = 0;

            scrollPane = new JScrollPane(panelData);
        } else {
            JPanel container = new JPanel(new BorderLayout());

            final JLabel label = new JLabel("   Задайте все условия поиска.");
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


    public static double getPlusYC(String par, ArrayList<Match> matchList, Double value){
        double result = 0;

        int numberOfMatches = matchList.size();
        int numberPlus = 0;
        int numberMinus = 0;
        int numberEqual = 0;

        for (int i=0; i<numberOfMatches; i++){
            if (matchList.get(i).homeYellowCards + matchList.get(i).awayYellowCards > value)
                numberPlus++;
            if (matchList.get(i).homeYellowCards + matchList.get(i).awayYellowCards < value)
                numberMinus++;
            if (matchList.get(i).homeYellowCards + matchList.get(i).awayYellowCards == value)
                numberEqual++;
        }

        if (par.contains("больше")){
            result = numberPlus + numberEqual*0.5;
        }
        else {
            result = numberMinus + numberEqual*0.5;
        }
        return result;
    }




}
