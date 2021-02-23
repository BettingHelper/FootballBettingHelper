package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.tabbedui.VerticalLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Graphic {
    String[] titles = {"Забитые голы", "Пропущенные голы", "Забитые голы", "xG", "Пропущенные голы", "xGA", "Голы в 1-ом тайме", "Голы во 2-ом тайме",
            "Владение", "Владение в 1-ом тайме", "Владение во 2-ом тайме", "Ударов всего", "Ударов в 1-ом тайме", "Ударов во 2-ом тайме",
            "Удары в створ", "Удары в створ в 1-ом тайме", "Удары в створ во 2-ом тайме", "Удары мимо", "Удары мимо в 1-ом тайме", "Удары мимо во 2-ом тайме",
            "Блокировано ударов", "Блокировано ударов в 1-ом тайме", "Блокировано ударов во 2-ом тайме", "Удары в штангу/перекладину",
            "Угловые", "Угловые в 1-ом тайме", "Угловые во 2-ом тайме", "Офсайды", "Офсайды в 1-ом тайме", "Офсайды во 2-ом тайме",
            "Фолы", "Фолы в 1-ом тайме", "Фолы во 2-ом тайме", "Желтые карточки", "Желтые карточки в 1-ом тайме", "Желтые карточки во 2-ом тайме",
            "Минута первой желтой карточки", "Минута последней желтой карточки", "Удаления", "Успешный дриблинг",
            "Выиграно воздушных единоборств", "Сэйвы", "Выносы", "Перехваты", "Отборы", "Потери", "Точность передач", "Ключевые пасы",
            "Удары от ворот", "Удары от ворот в 1-ом тайме", "Удары от ворот во 2-ом тайме",
            "Вброс аутов", "Вброс аутов  в 1-ом тайме", "Вброс аутов во 2-ом тайме"};

    double MAX = 0;
    double MIN = 100;
    int status;  // 0 - сравнение команд; 1 - статистика команды
    int graphicHeight = 300;
    ArrayList<Integer> heights = new ArrayList<>();
    String teamName;
    String shortTeamName;
    Settings settings;
    String series1name;
    String series2name;
    String series3name;
    ArrayList<String> graphicTitles;

    public Graphic(int status, String teamName){
        this.status = status;
        this.teamName = teamName;
        this.shortTeamName = Team.getShortName(this.teamName);
        graphicTitles = new ArrayList<>();
    }

    public JPanel getGraphics(String teamName, String allOrHomeOrAway, String lastOrFullSeason, Selector selector, ArrayList<String> tournamentTable){
        File fileI = new File("images/FBH.png");
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(fileI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Image scaled = bimg.getScaledInstance((int) (750*procWIDTH), graphicHeight, Image.SCALE_FAST);

        int numberOfMatchesLimit = 8;
        /*if (procWIDTH == 1)
            numberOfMatchesLimit = 20;*/

        ArrayList<Match> listOfMatches = selector.listOfMatches;
        this.settings = Settings.getSettingsFromFile();
        double[][] dataArrayThis = new double[titles.length][listOfMatches.size()];
        double[][] dataArrayOpponent = new double[titles.length][listOfMatches.size()];
        double[][] dataArrayTotal = new double[titles.length][listOfMatches.size()];
        String[] arrayOpponents = new String[listOfMatches.size()];
        selector.getArraysWithStats(teamName, dataArrayThis, dataArrayOpponent, dataArrayTotal, arrayOpponents, tournamentTable);
        int index = 0;
        int numberOfGraphics = 0;
        JPanel result = new JPanel(new GridLayout(0, 1, 0, 0));

        // Графики забиитых и пропущенных голов; Голов и xG; Пропущенных голов и xGA; xG и xGA
        while (index < 4){
            MAX = 0;
            MIN = 100;
            IntervalXYDataset dataSet = null;
            String localTitle = "";
            switch (index){
                case 0:{
                    series1name = "Забитые голы";
                    series2name = "Пропущенные голы";
                    series3name = "Тотал";
                    if (!settings.showTotal)
                        dataSet = createDoubleDataSet(dataArrayThis, dataArrayOpponent, index, series1name, series2name);
                    else
                        dataSet = createTripleDataSet(dataArrayThis, dataArrayOpponent, dataArrayTotal, index, series1name, series2name, series3name);
                    localTitle = "Забитые и пропущенные голы";
                    break;
                }
                case 1:{
                    series1name = "Забитые голы";
                    series2name = "xG";
                    dataSet = createDoubleDataSet(dataArrayThis, dataArrayOpponent, index, series1name, series2name);
                    localTitle = "Забитые голы и xG";
                    break;
                }
                case 2:{
                    series1name = "Пропущенные голы";
                    series2name = "xGA";
                    dataSet = createDoubleDataSet(dataArrayThis, dataArrayOpponent, index, series1name, series2name);
                    localTitle = "Пропущенные голы и xGA";
                    break;
                }
                case 3:{
                    series1name = "xG";
                    series2name = "xGA";
                    series3name = "Тотал";
                    if (!settings.showTotal)
                        dataSet = createDoubleDataSet(dataArrayThis, dataArrayOpponent, index, series1name, series2name);
                    else
                        dataSet = createTripleDataSet(dataArrayThis, dataArrayOpponent, dataArrayTotal, index, series1name, series2name, series3name);
                    localTitle = "xG и xGA";
                    break;
                }
            }

            final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesStroke(0, new BasicStroke(3f));
            renderer.setSeriesStroke(1, new BasicStroke(3f));
            renderer.setSeriesStroke(2, new BasicStroke(3f));

            XYPlot plot = new XYPlot(dataSet, new SymbolAxis("", arrayOpponents), new NumberAxis(), renderer);
            plot.setBackgroundImage(bimg);
            plot.setDomainGridlinePaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.lightGray);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer plotRenderer = plot.getRenderer();
            plotRenderer.setSeriesPaint(2, new Color(0, 228, 46));

            if (listOfMatches.size() > numberOfMatchesLimit){
                NumberAxis domain = (NumberAxis) plot.getDomainAxis();
                domain.setVerticalTickLabels(true);
            }

            ValueAxis rangeAxis = plot.getRangeAxis();         //getDomainAxis();
            rangeAxis.setRange(MIN - 0.1, MAX + 0.1);

            if (index == 0)
                rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            String lastOrFull = "Последние " + listOfMatches.size() + " игр";
            if (lastOrFullSeason != null){
                if (lastOrFullSeason.contains("Весь сезон"))
                    lastOrFull = "Все игры";
            }  else {
                lastOrFull = "Все игры";
            }

            if (allOrHomeOrAway.equals("Все матчи")) lastOrFull = " (" + lastOrFull + ")";
            if (allOrHomeOrAway.equals("Дома")) lastOrFull = " (" + lastOrFull + " дома)";
            if (allOrHomeOrAway.equals("На выезде")) lastOrFull = " (" + lastOrFull + " на выезде)";
            String title = teamName+ ": " + localTitle + lastOrFull;


            //String whereIsMatch = " (Все игры)";
            //if (allOrHomeOrAway.equals("Дома")) whereIsMatch = " (Игры дома)" ;
            //if (allOrHomeOrAway.equals("На выезде")) whereIsMatch = " (Игры на выезде)" ;
            //String title = teamName+ ": " + series1name + " и " + series2name + whereIsMatch;
            JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            chart.setBackgroundPaint(Color.white);
            JPanel panelWithGraphicAndTable = new JPanel(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            panelWithGraphicAndTable.add(chartPanel);
            if (settings.getShowList().get(index)){
                result.add(panelWithGraphicAndTable);
                graphicTitles.add(title);
                numberOfGraphics++;
            }
            index++;
        }

        // Графики остальные все
        for (index=6; index<dataArrayThis.length; index++){
            MAX = 0;
            MIN = 100;
            IntervalXYDataset dataSet;
            series1name = teamName;
            series2name = "Соперник";
            series3name = "Тотал";
            if (index == 36 || index == 37)
                dataSet = createDataSetForOneGraphic(dataArrayThis, index, titles[index]);
            else {
                if ((index==8)||(index==9)||(index==10)||(index==46)||(!settings.showTotal))
                    dataSet = createDoubleDataSet(dataArrayThis, dataArrayOpponent, index, series1name, series2name);
                else
                    dataSet = createTripleDataSet(dataArrayThis, dataArrayOpponent, dataArrayTotal, index, series1name, series2name, series3name);
            }

            final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesStroke(0, new BasicStroke(3f));
            renderer.setSeriesStroke(1, new BasicStroke(3f));
            if (settings.showTotal)
                renderer.setSeriesStroke(2, new BasicStroke(3f));

            XYPlot plot = new XYPlot(dataSet, new SymbolAxis("", arrayOpponents), new NumberAxis(), renderer);
            plot.setBackgroundImage(bimg);
            plot.setDomainGridlinePaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.lightGray);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer plotRenderer = plot.getRenderer();
            plotRenderer.setSeriesPaint(2, new Color(0, 228, 46));

            if (listOfMatches.size() > numberOfMatchesLimit){
                NumberAxis domain = (NumberAxis) plot.getDomainAxis();
                domain.setVerticalTickLabels(true);
            }

            ValueAxis rangeAxis = plot.getRangeAxis();         //getDomainAxis();
            rangeAxis.setRange(MIN/1.05-0.2, MAX*1.05+0.2);
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            String whereIsMatch = " (Все игры)";
            if (allOrHomeOrAway.equals("Дома")) whereIsMatch = " (Игры дома)" ;
            if (allOrHomeOrAway.equals("На выезде")) whereIsMatch = " (Игры на выезде)" ;
            String title = teamName+ ": " + titles[index] + whereIsMatch;
            JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            chart.setBackgroundPaint(Color.white);
            JPanel panelWithGraphicAndTable = new JPanel(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            panelWithGraphicAndTable.add(chartPanel);

            chartPanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            if (settings.getShowList().get(index-2)){
                result.add(panelWithGraphicAndTable);
                graphicTitles.add(title);
                numberOfGraphics++;
            }
        }

        if (status == 1){
            addTablesToGraphics(result, teamName, allOrHomeOrAway, selector);
        }

        result.setPreferredSize(new Dimension(300, numberOfGraphics*graphicHeight)); //300 - словное значение. Он все равно растягивает ее шире, но это и хорошо

        return result;
    }

    public JPanel getTablesWithStats(String teamName, String allOrHomeOrAway, Selector selector, ArrayList<String> tournamentTable){
        JPanel result = new JPanel(new VerticalLayout());
        ArrayList<Match> listOfMatches = selector.listOfMatches;
        this.settings = Settings.getSettingsFromFile();
        this.teamName = teamName;
        double[][] dataArrayThis = new double[titles.length][listOfMatches.size()];
        double[][] dataArrayOpponent = new double[titles.length][listOfMatches.size()];
        double[][] dataArrayTotal = new double[titles.length][listOfMatches.size()];
        String[] arrayOpponents = new String[listOfMatches.size()];
        selector.getArraysWithStats(teamName, dataArrayThis, dataArrayOpponent, dataArrayTotal, arrayOpponents, tournamentTable);

        for (int i=0; i<4; i++){
            switch (i){
                case 0:{
                    series1name = "Забитые голы";
                    series2name = "Пропущенные голы";
                    break;
                }
                case 1:{
                    series1name = "Забитые голы";
                    series2name = "xG";
                    break;
                }
                case 2:{
                    series1name = "Пропущенные голы";
                    series2name = "xGA";
                    break;
                }
                case 3:{
                    series1name = "xG";
                    series2name = "xGA";
                    break;
                }
            }
            if (settings.getShowList().get(i)){
                String whereIsMatch = " (Все игры)";
                if (allOrHomeOrAway.equals("Дома")) whereIsMatch = " (Игры дома)" ;
                if (allOrHomeOrAway.equals("На выезде")) whereIsMatch = " (Игры на выезде)" ;
                String title = "  " + teamName+ ": " + series1name + " и " + series2name + whereIsMatch;
                JPanel panelWithTable = getPanelWithTable(dataArrayThis, dataArrayOpponent, dataArrayTotal, arrayOpponents, i, title, tournamentTable);
                graphicTitles.add(title);
                heights.add(panelWithTable.getHeight());
                result.add(panelWithTable);
            }
        }

        for (int i=6; i<titles.length; i++){
            if (settings.getShowList().get(i-2)){
                String whereIsMatch = " (Все игры)";
                if (allOrHomeOrAway.equals("Дома")) whereIsMatch = " (Игры дома)" ;
                if (allOrHomeOrAway.equals("На выезде")) whereIsMatch = " (Игры на выезде)" ;
                String title = "  " + teamName+ ": " + titles[i] + whereIsMatch;
                JPanel panelWithTable = getPanelWithTable(dataArrayThis, dataArrayOpponent, dataArrayTotal, arrayOpponents, i, title, tournamentTable);
                graphicTitles.add(title);
                heights.add(panelWithTable.getHeight());
                result.add(panelWithTable);
            }
        }

        if (status == 1){
            addTablesToGraphics(result, teamName, allOrHomeOrAway, selector);
        }

        for (int i=0; i<heights.size(); i++){
            heights.set(i, heights.get(i) + 24);
        }

        return result;
    }

    public JPanel getPanelWithTable(double[][] dataArrayThis, double[][] dataArrayOpponent, double[][] dataArrayTotal,
                                    String[] arrayOpponents, int index, String title, ArrayList<String> tournamentTable){
        JPanel result = new JPanel(new BorderLayout());

        JLabel headerText = new JLabel(title);
        headerText.setFont(new Font("", 0, 18));
        result.add(headerText, BorderLayout.NORTH);

        Object[] colHeads = new Object[0];
        Object[][] data = new Object[0][];

        switch (index){
            case 1:{
                colHeads = new Object[]{"Номер", "Хозяева", "Голы / xG", "Гости"};
                data = new Object[arrayOpponents.length][6];
                for (int i=0; i<arrayOpponents.length; i++){
                    if (arrayOpponents[i].contains("(H)")){
                        data[i] = new Object[]{
                                i+1,
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf((int) dataArrayThis[index][i]) + " / " + String.valueOf(MyMath.round(dataArrayOpponent[index][i],2)),
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length())
                        };
                    } else {
                        data[i] = new Object[]{
                                i+1,
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf((int) dataArrayThis[index][i]) + " / " + String.valueOf(MyMath.round(dataArrayOpponent[index][i],2)),
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")"
                        };
                    }
                }
                break;
            }
            case 2:{
                colHeads = new Object[]{"Номер", "Хозяева", "Пропущ. голы / xGA", "Гости"};
                data = new Object[arrayOpponents.length][6];
                for (int i=0; i<arrayOpponents.length; i++){
                    if (arrayOpponents[i].contains("(H)")){
                        data[i] = new Object[]{
                                i+1,
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf((int) dataArrayThis[index][i]) + " / " + String.valueOf(MyMath.round(dataArrayOpponent[index][i],2)),
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length())
                        };
                    } else {
                        data[i] = new Object[]{
                                i+1,
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf((int) dataArrayThis[index][i]) + " / " + String.valueOf(MyMath.round(dataArrayOpponent[index][i],2)),
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                        };
                    }
                }
                break;
            }
            case 3:{
                colHeads = new Object[]{"Номер", "Хозяева", "Счет", "Гости", "Тотал", "Разница"};
                data = new Object[arrayOpponents.length][6];
                for (int i=0; i<arrayOpponents.length; i++){
                    if (arrayOpponents[i].contains("(H)")){
                        data[i] = new Object[]{
                                i+1,
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf(MyMath.round(dataArrayThis[index][i],2)) + " : " + String.valueOf(MyMath.round(dataArrayOpponent[index][i],2)),
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf(MyMath.round(dataArrayThis[index][i] + dataArrayOpponent[index][i],2)),
                                String.valueOf(MyMath.round(dataArrayThis[index][i] - dataArrayOpponent[index][i],2))
                        };
                    } else {
                        data[i] = new Object[]{
                                i+1,
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf(MyMath.round(dataArrayOpponent[index][i],2)) + " : " + String.valueOf(MyMath.round(dataArrayThis[index][i],2)),
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf(MyMath.round(dataArrayThis[index][i] + dataArrayOpponent[index][i],2)),
                                String.valueOf(MyMath.round(dataArrayThis[index][i] - dataArrayOpponent[index][i],2))
                        };
                    }
                }
                break;
            }
            case 36:
            case 37:{
                colHeads = new Object[]{"Номер", "Хозяева", "Гости", "Минута ЖК"};
                data = new Object[arrayOpponents.length][6];
                for (int i=0; i<arrayOpponents.length; i++){
                    if (arrayOpponents[i].contains("(H)")){
                        data[i] = new Object[]{
                                i+1,
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf((int) dataArrayThis[index][i]) + "'"
                        };
                    } else {
                        data[i] = new Object[]{
                                i+1,
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf((int) dataArrayThis[index][i]) + "'"
                        };
                    }
                }
                break;
            }
            default:{
                colHeads = new Object[]{"Номер", "Хозяева", "Счет", "Гости", "Тотал", "Разница"};
                data = new Object[arrayOpponents.length][6];
                for (int i=0; i<arrayOpponents.length; i++){
                    if (arrayOpponents[i].contains("(H)")){
                        data[i] = new Object[]{
                                i+1,
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf((int) dataArrayThis[index][i]) + " : " + String.valueOf((int) dataArrayOpponent[index][i]),
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf((int) dataArrayThis[index][i] + (int) dataArrayOpponent[index][i]),
                                String.valueOf((int) dataArrayThis[index][i] - (int) dataArrayOpponent[index][i])
                        };
                    } else {
                        data[i] = new Object[]{
                                i+1,
                                arrayOpponents[i].substring(0,3) + arrayOpponents[i].substring(6,arrayOpponents[i].length()),
                                String.valueOf((int) dataArrayOpponent[index][i]) + " : " + String.valueOf((int) dataArrayThis[index][i]),
                                shortTeamName + "(" + League.getPositionInLeague(teamName, tournamentTable) + ")",
                                String.valueOf((int) dataArrayThis[index][i] + (int) dataArrayOpponent[index][i]),
                                String.valueOf((int) dataArrayThis[index][i] - (int) dataArrayOpponent[index][i])
                        };
                    }
                }
            }
        }



        final Font fontLabel = new Font("Arial", Font.BOLD, 15);
        JTable table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.setBackground(new Color(238, 238, 238));
        table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        Renderer renderer = new Renderer(Team.getShortName(teamName), index);
        for (int t=0; t<table.getColumnCount(); t++){
            table.getColumnModel().getColumn(t).setCellRenderer(renderer);
        }
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        result.add(tablePanel);

        return result;
    }

    public JPanel getRefGraphics(String refName, String lastOrFull, Selector selector){
        File fileI = new File("images/FBH.png");
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(fileI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Match> listOfMatches = selector.listOfMatches;
        int numberOfMatchesLimit = 12;
        int numberOfGraphics = 0;

        this.settings = Settings.getSettingsFromFile();
        String[] titlesRef = {"Желтые карточки", "Фолы", "Желтые карточки 1 тайм", "Желтые карточки 2 тайм",
                "Фолы 1 тайм", "Фолы 2 тайм", "Минута первой желтой карточки", "Минута последней желтой карточки",
                "ЖК --> КК", "Прямые красные карточки", "Назначенные пенальти"};

        double[][] dataArrayHome = new double[titlesRef.length][listOfMatches.size()];
        double[][] dataArrayAway = new double[titlesRef.length][listOfMatches.size()];
        double[][] dataArrayTotal = new double[titlesRef.length][listOfMatches.size()];
        String[] arrayTitles = new String[listOfMatches.size()];
        for (int i=0; i<listOfMatches.size(); i++){
            dataArrayHome[0][i] = listOfMatches.get(i).homeYellowCards;
            dataArrayHome[1][i] = listOfMatches.get(i).homeFouls;
            dataArrayHome[2][i] = listOfMatches.get(i).homeYellowCards1T;
            dataArrayHome[3][i] = listOfMatches.get(i).homeYellowCards2T;
            dataArrayHome[4][i] = listOfMatches.get(i).homeFouls1T;
            dataArrayHome[5][i] = listOfMatches.get(i).homeFouls2T;
            dataArrayHome[6][i] = listOfMatches.get(i).firstYCMinute;
            dataArrayHome[7][i] = listOfMatches.get(i).lastYCMinute;
            dataArrayHome[8][i] = listOfMatches.get(i).homeSecondYellowCards;
            dataArrayHome[9][i] = listOfMatches.get(i).homeDirectRedCards;
            dataArrayHome[10][i] = listOfMatches.get(i).homePen;

            dataArrayAway[0][i] = listOfMatches.get(i).awayYellowCards;
            dataArrayAway[1][i] = listOfMatches.get(i).awayFouls;
            dataArrayAway[2][i] = listOfMatches.get(i).awayYellowCards1T;
            dataArrayAway[3][i] = listOfMatches.get(i).awayYellowCards2T;
            dataArrayAway[4][i] = listOfMatches.get(i).awayFouls1T;
            dataArrayAway[5][i] = listOfMatches.get(i).awayFouls2T;
            dataArrayAway[6][i] = 0;
            dataArrayAway[7][i] = 0;
            dataArrayAway[8][i] = listOfMatches.get(i).awaySecondYellowCards;
            dataArrayAway[9][i] = listOfMatches.get(i).awayDirectRedCards;
            dataArrayAway[10][i] = listOfMatches.get(i).awayPen;

            dataArrayTotal[0][i] = listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards;
            dataArrayTotal[1][i] = listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls;
            dataArrayTotal[2][i] = listOfMatches.get(i).homeYellowCards1T + listOfMatches.get(i).awayYellowCards1T;
            dataArrayTotal[3][i] = listOfMatches.get(i).homeYellowCards2T + listOfMatches.get(i).awayYellowCards2T;
            dataArrayTotal[4][i] = listOfMatches.get(i).homeFouls1T + listOfMatches.get(i).awayFouls1T;
            dataArrayTotal[5][i] = listOfMatches.get(i).homeFouls2T + listOfMatches.get(i).awayFouls2T;
            dataArrayTotal[6][i] = 0;
            dataArrayTotal[7][i] = 0;
            dataArrayTotal[8][i] = listOfMatches.get(i).homeSecondYellowCards + listOfMatches.get(i).awaySecondYellowCards;
            dataArrayTotal[9][i] = listOfMatches.get(i).homeDirectRedCards + listOfMatches.get(i).awayDirectRedCards;
            dataArrayTotal[10][i] = listOfMatches.get(i).homePen + listOfMatches.get(i).awayPen;

            arrayTitles[i] = Team.getShortName(listOfMatches.get(i).homeTeam) + "-" + Team.getShortName(listOfMatches.get(i).awayTeam);
        }
        int index;
        JPanel result = new JPanel(new GridLayout(0, 1, 0, 0));

        for (index=0; index<dataArrayHome.length; index++){
            MAX = 0;
            MIN = 100;
            IntervalXYDataset dataSet;
            series1name = "Хозяева";
            series2name = "Гости";
            series3name = "Тотал";

            if (titlesRef[index].contains("Минута первой желтой карточки") || titlesRef[index].contains("Минута последней желтой карточки"))
                dataSet = createDataSetForOneGraphic(dataArrayHome, index, titlesRef[index]);
            else {
                if (!settings.showTotal)
                    dataSet = createDoubleDataSet(dataArrayHome, dataArrayAway, index, series1name, series2name);
                else
                    dataSet = createTripleDataSet(dataArrayHome, dataArrayAway, dataArrayTotal, index, series1name, series2name, series3name);
            }

            final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesStroke(0, new BasicStroke(3f));
            renderer.setSeriesStroke(1, new BasicStroke(3f));
            renderer.setSeriesStroke(2, new BasicStroke(3f));

            XYPlot plot = new XYPlot(dataSet, new SymbolAxis("", arrayTitles), new NumberAxis(), renderer);
            plot.setBackgroundImage(bimg);
            plot.setDomainGridlinePaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.lightGray);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer plotRenderer = plot.getRenderer();
            plotRenderer.setSeriesPaint(2, new Color(0, 228, 46));

            if (listOfMatches.size() > numberOfMatchesLimit){
                NumberAxis domain = (NumberAxis) plot.getDomainAxis();
                domain.setVerticalTickLabels(true);
            }

            ValueAxis rangeAxis = plot.getRangeAxis();
            rangeAxis.setRange(MIN/1.05-0.2, MAX*1.05+0.2);

            JFreeChart chart = new JFreeChart(refName + ": " + titlesRef[index] + " (" + lastOrFull + ")", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            chart.setBackgroundPaint(Color.white);
            JPanel panelWithGraphicAndTable = new JPanel(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            panelWithGraphicAndTable.add(chartPanel);
            result.add(panelWithGraphicAndTable);
            numberOfGraphics++;
        }

        addTablesToRefGraphics(result, selector);
        result.setPreferredSize(new Dimension(300, numberOfGraphics*graphicHeight)); //300 - словное значение. Он все равно растягивает ее шире, но это и хорошо


        return result;
    }

    public JPanel getRefTablesWithStats(String refName, String lastOrFull, Selector selector, ArrayList<String> tournamentTable){
        JPanel result = new JPanel(new VerticalLayout());

        ArrayList<Match> listOfMatches = selector.listOfMatches;
        int numberOfGraphics = 0;

        this.settings = Settings.getSettingsFromFile();
        String[] titlesRef = {"Желтые карточки", "Фолы", "Желтые карточки 1 тайм", "Желтые карточки 2 тайм",
                "Фолы 1 тайм", "Фолы 2 тайм", "Минута первой желтой карточки", "Минута последней желтой карточки",
                "ЖК --> КК", "Прямые красные карточки", "Назначенные пенальти"};

        double[][] dataArrayHome = new double[titlesRef.length][listOfMatches.size()];
        double[][] dataArrayAway = new double[titlesRef.length][listOfMatches.size()];
        double[][] dataArrayTotal = new double[titlesRef.length][listOfMatches.size()];
        String[] arrayHomeTeams = new String[listOfMatches.size()];
        String[] arrayAwayTeams = new String[listOfMatches.size()];
        for (int i=0; i<listOfMatches.size(); i++){
            dataArrayHome[0][i] = listOfMatches.get(i).homeYellowCards;
            dataArrayHome[1][i] = listOfMatches.get(i).homeFouls;
            dataArrayHome[2][i] = listOfMatches.get(i).homeYellowCards1T;
            dataArrayHome[3][i] = listOfMatches.get(i).homeYellowCards2T;
            dataArrayHome[4][i] = listOfMatches.get(i).homeFouls1T;
            dataArrayHome[5][i] = listOfMatches.get(i).homeFouls2T;
            dataArrayHome[6][i] = listOfMatches.get(i).firstYCMinute;
            dataArrayHome[7][i] = listOfMatches.get(i).lastYCMinute;
            dataArrayHome[8][i] = listOfMatches.get(i).homeSecondYellowCards;
            dataArrayHome[9][i] = listOfMatches.get(i).homeDirectRedCards;
            dataArrayHome[10][i] = listOfMatches.get(i).homePen;

            dataArrayAway[0][i] = listOfMatches.get(i).awayYellowCards;
            dataArrayAway[1][i] = listOfMatches.get(i).awayFouls;
            dataArrayAway[2][i] = listOfMatches.get(i).awayYellowCards1T;
            dataArrayAway[3][i] = listOfMatches.get(i).awayYellowCards2T;
            dataArrayAway[4][i] = listOfMatches.get(i).awayFouls1T;
            dataArrayAway[5][i] = listOfMatches.get(i).awayFouls2T;
            dataArrayAway[6][i] = 0;
            dataArrayAway[7][i] = 0;
            dataArrayAway[8][i] = listOfMatches.get(i).awaySecondYellowCards;
            dataArrayAway[9][i] = listOfMatches.get(i).awayDirectRedCards;
            dataArrayAway[10][i] = listOfMatches.get(i).awayPen;

            dataArrayTotal[0][i] = listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards;
            dataArrayTotal[1][i] = listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls;
            dataArrayTotal[2][i] = listOfMatches.get(i).homeYellowCards1T + listOfMatches.get(i).awayYellowCards1T;
            dataArrayTotal[3][i] = listOfMatches.get(i).homeYellowCards2T + listOfMatches.get(i).awayYellowCards2T;
            dataArrayTotal[4][i] = listOfMatches.get(i).homeFouls1T + listOfMatches.get(i).awayFouls1T;
            dataArrayTotal[5][i] = listOfMatches.get(i).homeFouls2T + listOfMatches.get(i).awayFouls2T;
            dataArrayTotal[6][i] = 0;
            dataArrayTotal[7][i] = 0;
            dataArrayTotal[8][i] = listOfMatches.get(i).homeSecondYellowCards + listOfMatches.get(i).awaySecondYellowCards;
            dataArrayTotal[9][i] = listOfMatches.get(i).homeDirectRedCards + listOfMatches.get(i).awayDirectRedCards;
            dataArrayTotal[10][i] = listOfMatches.get(i).homePen + listOfMatches.get(i).awayPen;

            arrayHomeTeams[i] = listOfMatches.get(i).homeTeam;
            arrayAwayTeams[i] = listOfMatches.get(i).awayTeam;
        }
        int index;

        for (int i=0; i<titlesRef.length; i++){
            String title = refName + ": " + titlesRef[i] + " (" + lastOrFull + ")";
            JPanel panelWithTable = getRefPanelWithTable(dataArrayHome, dataArrayAway, dataArrayTotal, i, title, tournamentTable, arrayHomeTeams, arrayAwayTeams);
            result.add(panelWithTable);
        }

        addTablesToRefGraphics(result, selector);
        //result.setPreferredSize(new Dimension(300, numberOfGraphics*graphicHeight)); //300 - словное значение. Он все равно растягивает ее шире, но это и хорошо


        return result;
    }

    public JPanel getRefPanelWithTable(double[][] dataArrayHome, double[][] dataArrayAway, double[][] dataArrayTotal,
                                    int index, String title, ArrayList<String> tournamentTable, String[] arrayHomeTeams, String[] arrayAwayTeams){
        JPanel result = new JPanel(new BorderLayout());

        JLabel headerText = new JLabel(title);
        headerText.setFont(new Font("", 0, 18));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        result.add(headerText, BorderLayout.NORTH);

        Object[] colHeads = new Object[]{"Номер", "Хозяева", "Счет", "Гости"};
        Object[][] data = new Object[arrayHomeTeams.length][4];

        switch (index){
            case 6:
            case 7: {
                for (int i=0; i<arrayHomeTeams.length; i++){
                    data[i] = new Object[]{
                            i+1,
                            arrayHomeTeams[i],
                            (int) dataArrayHome[index][i],
                            arrayAwayTeams[i],
                    };
                }
                break;
            }
            default:{
                for (int i=0; i<arrayHomeTeams.length; i++){
                    data[i] = new Object[]{
                            i+1,
                            arrayHomeTeams[i],
                            (int) dataArrayHome[index][i] + " : " + ((int) dataArrayAway[index][i]),
                            arrayAwayTeams[i],
                    };
                }
                break;
            }
        }




        final Font fontLabel = new Font("Arial", Font.BOLD, 15);
        JTable table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.setBackground(new Color(238, 238, 238));
        table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        Renderer renderer = new Renderer(7);
        for (int t=0; t<table.getColumnCount(); t++){
            table.getColumnModel().getColumn(t).setCellRenderer(renderer);
        }
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        result.add(tablePanel);

        return result;
    }

    public JPanel getDiagrams(String teamName, String allOrHomeOrAway, String season, String lastOrFullSeason, Selector selector){
        JPanel result = new JPanel(new VerticalLayout());
        int diagramWidth = 290;
        String headerText = "";
        if (lastOrFullSeason.contains("Весь сезон")){
            if (allOrHomeOrAway.contains("Все")){
                headerText = "Все игры " + teamName + " в сезоне " + season;
            }
            if (allOrHomeOrAway.contains("Дома")){
                headerText = "Все домашние игры " + teamName + " в сезоне " + season;
            }
            if (allOrHomeOrAway.contains("На выезде")){
                headerText = "Все гостевые игры " + teamName + " в сезоне " + season;
            }
        } else {
            if (allOrHomeOrAway.contains("Все")){
                headerText = "Последние " + selector.listOfMatches.size() + " игр " + teamName + " в сезоне " + season;
            }
            if (allOrHomeOrAway.contains("Дома")){
                headerText = "Последние " + selector.listOfMatches.size() + " домашних игр " + teamName + " в сезоне " + season;
            }
            if (allOrHomeOrAway.contains("На выезде")){
                headerText = "Последние " + selector.listOfMatches.size() + " гостевых игр " + teamName + " в сезоне " + season;
            }
        }
        JLabel jtf = new JLabel(headerText);
        jtf.setFont(new Font("", 0, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        result.add(jtf);

        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        // row keys...
        String series1 = "1-ый тайм";
        String series2 = "2-ой тайм";
        // column keys...
        String category1 = "Забито" ;
        String category2 = "Пропущено" ;
        String category3 = "Разница" ;
        String category4 = "Тотал" ;

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(22).get(1)), series1, category1);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(23).get(1)), series2, category1);

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(22).get(2)), series1, category2);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(23).get(2)), series2, category2);

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(22).get(1)) - Double.parseDouble(selector.pList.get(22).get(2)), series1, category3);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(23).get(1)) - Double.parseDouble(selector.pList.get(23).get(2)), series2, category3);

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(22).get(1)) + Double.parseDouble(selector.pList.get(22).get(2)), series1, category4);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(23).get(1)) + Double.parseDouble(selector.pList.get(23).get(2)), series2, category4);

        JFreeChart chartByTimes = ChartFactory.createBarChart(
                "Голы по таймам", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartByTimes.setBackgroundPaint(new Color(238, 238, 238));
        chartByTimes.getTitle().setFont(new Font("", 0, 18));
        chartByTimes.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        CategoryPlot plotByTimes = chartByTimes.getCategoryPlot();
        plotByTimes.setBackgroundPaint(new Color(238, 238, 238));
        plotByTimes.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotByTimes.getRenderer().setSeriesPaint(1, new Color(40, 40, 255 ));

        plotByTimes.setDomainGridlinePaint(Color.black);
        plotByTimes.setRangeGridlinePaint(Color.black);
        NumberAxis axis = (NumberAxis) plotByTimes.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer)plotByTimes.getRenderer();
        renderer.setItemMargin(0.02);
        SubCategoryAxis subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        categoryDataset = new DefaultCategoryDataset();
        // row keys...
        series1 = "0' - 15'";
        series2 = "16' - 30'";
        String series3 = "31' - 45+'";
        String series4 = "46' - 60'";
        String series5 = "61' - 75'";
        String series6 = "76' - 90+'";
        // column keys...
        category1 = "Забито" ;
        category2 = "Пропущено" ;
        category3 = "Разница" ;
        category4 = "Тотал" ;

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(24).get(1)), series1, category1);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(25).get(1)), series2, category1);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(26).get(1)), series3, category1);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(27).get(1)), series4, category1);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(28).get(1)), series5, category1);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(29).get(1)), series6, category1);

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(24).get(2)), series1, category2);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(25).get(2)), series2, category2);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(26).get(2)), series3, category2);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(27).get(2)), series4, category2);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(28).get(2)), series5, category2);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(29).get(2)), series6, category2);

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(24).get(1)) - Double.parseDouble(selector.pList.get(24).get(2)), series1, category3);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(25).get(1)) - Double.parseDouble(selector.pList.get(25).get(2)), series2, category3);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(26).get(1)) - Double.parseDouble(selector.pList.get(26).get(2)), series3, category3);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(27).get(1)) - Double.parseDouble(selector.pList.get(27).get(2)), series4, category3);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(28).get(1)) - Double.parseDouble(selector.pList.get(28).get(2)), series5, category3);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(29).get(1)) - Double.parseDouble(selector.pList.get(29).get(2)), series6, category3);

        categoryDataset.addValue(Double.parseDouble(selector.pList.get(24).get(1)) + Double.parseDouble(selector.pList.get(24).get(2)), series1, category4);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(25).get(1)) + Double.parseDouble(selector.pList.get(25).get(2)), series2, category4);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(26).get(1)) + Double.parseDouble(selector.pList.get(26).get(2)), series3, category4);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(27).get(1)) + Double.parseDouble(selector.pList.get(27).get(2)), series4, category4);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(28).get(1)) + Double.parseDouble(selector.pList.get(28).get(2)), series5, category4);
        categoryDataset.addValue(Double.parseDouble(selector.pList.get(29).get(1)) + Double.parseDouble(selector.pList.get(29).get(2)), series6, category4);

        JFreeChart chartBy15Min = ChartFactory.createBarChart(
                "Голы по 15-минуткам", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartBy15Min.setBackgroundPaint(new Color(238, 238, 238));
        chartBy15Min.getTitle().setFont(new Font("", 0, 18));
        chartBy15Min.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        CategoryPlot plotBy15Min = chartBy15Min.getCategoryPlot();
        plotBy15Min.setBackgroundPaint(new Color(238, 238, 238));
        plotBy15Min.setDomainGridlinePaint(Color.black);
        plotBy15Min.setRangeGridlinePaint(Color.black);
        plotBy15Min.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotBy15Min.getRenderer().setSeriesPaint(1, new Color(40, 40, 255 ));
        plotBy15Min.getRenderer().setSeriesPaint(2, new Color(0, 140, 20 ));
        plotBy15Min.getRenderer().setSeriesPaint(3, new Color(242, 120, 21 ));
        plotBy15Min.getRenderer().setSeriesPaint(4, new Color(187,  46, 230));
        plotBy15Min.getRenderer().setSeriesPaint(5, new Color(30 , 200, 230));
        axis = (NumberAxis) plotBy15Min.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        renderer = (BarRenderer)plotBy15Min.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.1);

        ChartPanel cp = new ChartPanel(chartByTimes);
        cp.setPreferredSize(new Dimension(diagramWidth, graphicHeight));
        result.add(cp);

        cp = new ChartPanel(chartBy15Min);
        cp.setPreferredSize(new Dimension(diagramWidth, graphicHeight));
        result.add(cp);


        String[] graphics = {"Владение", "Угловые", "Желтые карточки", "Удары", "Удары в створ", "Удары мимо",
                "Заблокировано ударов", "Офсайды", "Фолы", "Дриблинг", "Возд. единоборства", "Сэйвы", "Выносы",
                "Перехваты", "Отборы", "Точные передачи", "Процент точности передач", "Ключевые передачи", "Потери"};
        for (int i=0; i<graphics.length; i++){
            categoryDataset = new DefaultCategoryDataset();
            // row keys...
            series1 = "1-ый тайм";
            series2 = "2-ой тайм";
            // column keys...
            category1 = "Собственные" ;
            category2 = "Соперник" ;
            category3 = "Разница" ;
            category4 = "Тотал" ;

            int index = 30 + i*2;
            int matches = selector.listOfMatches.size();

            categoryDataset.addValue(Double.parseDouble(selector.pList.get(index).get(1))/matches, series1, category1);
            categoryDataset.addValue(Double.parseDouble(selector.pList.get(index+1).get(1))/matches, series2, category1);

            categoryDataset.addValue(Double.parseDouble(selector.pList.get(index).get(2))/matches, series1, category2);
            categoryDataset.addValue(Double.parseDouble(selector.pList.get(index+1).get(2))/matches, series2, category2);

            categoryDataset.addValue((Double.parseDouble(selector.pList.get(index).get(1)) - Double.parseDouble(selector.pList.get(index).get(2)))/matches, series1, category3);
            categoryDataset.addValue((Double.parseDouble(selector.pList.get(index+1).get(1)) - Double.parseDouble(selector.pList.get(index+1).get(2)))/matches, series2, category3);

            if (!graphics[i].equals("Владение")){
                categoryDataset.addValue((Double.parseDouble(selector.pList.get(index).get(1)) + Double.parseDouble(selector.pList.get(index).get(2)))/matches, series1, category4);
                categoryDataset.addValue((Double.parseDouble(selector.pList.get(index+1).get(1)) + Double.parseDouble(selector.pList.get(index+1).get(2)))/matches, series2, category4);
            }

            chartByTimes = ChartFactory.createBarChart(
                    graphics[i] + " по таймам (сред.)", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

            // Определение фона диаграммы
            chartByTimes.setBackgroundPaint(new Color(238, 238, 238));
            chartByTimes.getTitle().setFont(new Font("", 0, 18));
            chartByTimes.getTitle().setMargin(20,0,0,0);
            // Настройка plot'а
            plotByTimes = chartByTimes.getCategoryPlot();
            plotByTimes.setBackgroundPaint(new Color(238, 238, 238));
            plotByTimes.getRenderer().setSeriesPaint(0, new Color(255, 40, 40));
            plotByTimes.getRenderer().setSeriesPaint(1, new Color(40, 40, 255));
            plotByTimes.setDomainGridlinePaint(Color.black);
            plotByTimes.setRangeGridlinePaint(Color.black);
            renderer = (BarRenderer)plotByTimes.getRenderer();
            renderer.setItemMargin(0.02);
            subCategoryAxis = new SubCategoryAxis("");
            subCategoryAxis.setCategoryMargin(0.15);

            cp = new ChartPanel(chartByTimes);
            cp.setPreferredSize(new Dimension(diagramWidth, graphicHeight));
            result.add(cp);


            if (i==2){
                JPanel panelShotsPieDiagram = new JPanel(new GridLayout(1, 0, 0, 0));
                DefaultPieDataset dataset = new DefaultPieDataset( );
                dataset.setValue("В створ", Double.parseDouble(selector.pList.get(8).get(1)) );
                dataset.setValue("Мимо", Double.parseDouble(selector.pList.get(9).get(1)) );
                dataset.setValue("Блок. пр-ком", Double.parseDouble(selector.pList.get(12).get(1)) );

                JFreeChart chart = ChartFactory.createPieChart( "Удары " + teamName, dataset, true, true, false);
                chart.setBackgroundPaint(new Color(238, 238, 238));
                PiePlot plot = (PiePlot) chart.getPlot();
                plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
                plot.setBackgroundPaint(new Color(238, 238, 238));
                plot.setSectionPaint("В створ", new Color(255, 40, 40 ));
                plot.setSectionPaint("Мимо", new Color(40, 40, 255 ));
                plot.setSectionPaint("Блок. пр-ком", new Color(30, 255, 30 ));

                cp = new ChartPanel(chart);
                cp.setPreferredSize(new Dimension(diagramWidth / 2 - 10, graphicHeight));
                panelShotsPieDiagram.add(cp);

                dataset = new DefaultPieDataset( );
                dataset.setValue("В створ", Double.parseDouble(selector.pList.get(8).get(2)) );
                dataset.setValue("Мимо", Double.parseDouble(selector.pList.get(9).get(2)) );
                dataset.setValue("Блок. пр-ком", Double.parseDouble(selector.pList.get(12).get(2)) );

                chart = ChartFactory.createPieChart( "Удары противника", dataset, true, true, false);
                chart.setBackgroundPaint(new Color(238, 238, 238));
                plot = (PiePlot) chart.getPlot();
                plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
                plot.setBackgroundPaint(new Color(238, 238, 238));
                plot.setSectionPaint("В створ", new Color(255, 40, 40 ));
                plot.setSectionPaint("Мимо", new Color(40, 40, 255 ));
                plot.setSectionPaint("Блок. пр-ком", new Color(30, 255, 30 ));
                cp = new ChartPanel(chart);
                cp.setPreferredSize(new Dimension(diagramWidth / 2 - 10, graphicHeight));
                panelShotsPieDiagram.add(cp);
                result.add(panelShotsPieDiagram);
            }
        }
        return result;
    }

    public IntervalXYDataset createDoubleDataSet(double[][] dataArrayThis, double[][] dataArrayOp, int index, String series1name, String series2name) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(series1name);
        for (int i = 0 ; i < dataArrayThis[0].length ; i++){
            series1.add(i, dataArrayThis[index][i]);
            if (dataArrayThis[index][i] > MAX){
                MAX = dataArrayThis[index][i];
            }
            if (dataArrayThis[index][i] < MIN){
                MIN = dataArrayThis[index][i];
            }
        }
        dataset.addSeries(series1);
        XYSeries series2 = new XYSeries(series2name);
        for (int i = 0 ; i < dataArrayOp[0].length ; i++){
            series2.add(i, dataArrayOp[index][i]);
            if (dataArrayOp[index][i] > MAX){
                MAX = dataArrayOp[index][i];
            }
            if (dataArrayOp[index][i] < MIN){
                MIN = dataArrayOp[index][i];
            }
        }
        dataset.addSeries(series2);
        return dataset;
    }

    public IntervalXYDataset createDataSetForOneGraphic(double[][] dataArrayThis, int index, String series1name) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(series1name);
        for (int i = 0 ; i < dataArrayThis[0].length ; i++){
            series1.add(i, dataArrayThis[index][i]);
            if (dataArrayThis[index][i] > MAX){
                MAX = dataArrayThis[index][i];
            }
            if (dataArrayThis[index][i] < MIN){
                MIN = dataArrayThis[index][i];
            }
        }
        dataset.addSeries(series1);
        return dataset;
    }

    public IntervalXYDataset createTripleDataSet(double[][] dataArrayThis, double[][] dataArrayOp, double[][] dataArrayTotal, int index, String series1name, String series2name, String series3name) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(series1name);
        for (int i = 0 ; i < dataArrayThis[0].length ; i++){
            series1.add(i, dataArrayThis[index][i]);
            if (dataArrayThis[index][i] > MAX){
                MAX = dataArrayThis[index][i];
            }
            if (dataArrayThis[index][i] < MIN){
                MIN = dataArrayThis[index][i];
            }
        }
        dataset.addSeries(series1);

        XYSeries series2 = new XYSeries(series2name);
        for (int i = 0 ; i < dataArrayOp[0].length ; i++){
            series2.add(i, dataArrayOp[index][i]);
            if (dataArrayOp[index][i] > MAX){
                MAX = dataArrayOp[index][i];
            }
            if (dataArrayOp[index][i] < MIN){
                MIN = dataArrayOp[index][i];
            }
        }
        dataset.addSeries(series2);

        XYSeries series3 = new XYSeries(series3name);
        for (int i = 0 ; i < dataArrayTotal[0].length ; i++){
            series3.add(i, dataArrayTotal[index][i]);
            if (dataArrayTotal[index][i] > MAX){
                MAX = dataArrayTotal[index][i];
            }
            if (dataArrayOp[index][i] < MIN){
                MIN = dataArrayTotal[index][i];
            }
        }
        dataset.addSeries(series3);

        return dataset;
    }

    public void addTablesToGraphics(JPanel content, String teamName, String allOrHomeOrAway, Selector selector){
        int index = 0;
        int numberOf4Graphics = 0;
        if (settings.showGoals)
            numberOf4Graphics ++;
        if (settings.showxG)
            numberOf4Graphics ++;
        if (settings.showxGA)
            numberOf4Graphics ++;
        if (settings.showxGAndxGA)
            numberOf4Graphics ++;

        int tableHeightLeft = 25 * (selector.listOfMatches.size()+1);
        int tableHeightRight = 0;

        if (settings.showGoals || settings.showxG || settings.showxGA || settings.showxGAndxGA){
            JPanel table = TableMaker.getTableGoals(selector.listOfMatches);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        for (int i=0; i<numberOf4Graphics-1;i++){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }

        if (settings.showGoals1T){
            JPanel table = TableMaker.getTableGoals1T(selector.listOfMatches);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }

        if (settings.showGoals2T){
            JPanel table = TableMaker.getTableGoals2T(selector.listOfMatches);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }

        if (settings.showPossession){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showPossession1T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showPossession2T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }

        if (settings.showShots){
            JPanel table = TableMaker.getTableShots(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShots1T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShots2T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOnTarget){
            JPanel table = TableMaker.getTableShotsOnTargets(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOnTarget1T){
            JPanel table = TableMaker.getTableShotsOnTargets1T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOnTarget2T){
            JPanel table = TableMaker.getTableShotsOnTargets2T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOffTarget){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOffTarget1T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOffTarget2T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showBlockedShots){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showBlockedShots1T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showBlockedShots2T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showShotsOnPost){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showCorners){
            JPanel table = TableMaker.getTableCorners(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showCorners1T){
            JPanel table = TableMaker.getTableCorners1T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showCorners2T){
            JPanel table = TableMaker.getTableCorners2T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showOffsides){
            JPanel table = TableMaker.getTableOffsides(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showOffsides1T){
            JPanel table = TableMaker.getTableOffsides1T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showOffsides2T){
            JPanel table = TableMaker.getTableOffsides2T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }

        if (settings.showFouls){
            JPanel table = TableMaker.getTableFouls(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showFouls1T){
            JPanel table = TableMaker.getTableFouls1T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showFouls2T){
            JPanel table = TableMaker.getTableFouls2T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showYC){
            JPanel table = TableMaker.getTableYCards(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showYC1T){
            JPanel table = TableMaker.getTableYCards1T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showYC2T){
            JPanel table = TableMaker.getTableYCards2T(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showFirstYCMinute){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showLastYCMinute){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showRC){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showDribbles){
            JPanel table = TableMaker.getTableDribbles(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showAerialsWon){
            JPanel table = TableMaker.getTableAerialsWon(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showSaves){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showClearances){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showInterceptions){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showTackles){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showDispossessed){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showPassesAcc){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showKeyPasses){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showGoalKicks){
            JPanel table = TableMaker.getTableGoalKicks(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showGoalKicks1T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showGoalKicks2T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showThrowIns){
            JPanel table = TableMaker.getTableThrowIns(teamName, selector);
            tableHeightRight = ((JTable) table.getComponent(0)).getRowHeight() * (((JTable) table.getComponent(0)).getRowCount()+1);
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showThrowIns1T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }
        if (settings.showThrowIns2T){
            JPanel table = new JPanel();
            tableHeightRight = 0;
            table.setPreferredSize(new Dimension(245, Math.max(tableHeightLeft, tableHeightRight)));
            if (!settings.showGraphics){
                if (Math.max(tableHeightLeft, tableHeightRight) > heights.get(index)){
                    heights.set(index, Math.max(tableHeightLeft, tableHeightRight));
                }
            }
            table.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            table.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
            index++;
        }

    }

    public void addTablesToRefGraphics(JPanel content, Selector selector){
        int index = 0;
        JPanel table = TableMaker.getTableYCardsOfRef(selector);
        table.setPreferredSize(new Dimension(245, graphicHeight));
        table.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        ((JPanel) content.getComponents()[index]).add(table, BorderLayout.EAST);
        index++;

        JPanel tableFouls = TableMaker.getTableFoulsOfRef(selector);
        tableFouls.setPreferredSize(new Dimension(245, graphicHeight));
        tableFouls.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        ((JPanel) content.getComponents()[index]).add(tableFouls, BorderLayout.EAST);
        index++;

        JPanel tableYC1T = TableMaker.getTableYCards1TOfRef(selector);
        tableYC1T.setPreferredSize(new Dimension(245, graphicHeight));
        tableYC1T.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        ((JPanel) content.getComponents()[index]).add(tableYC1T, BorderLayout.EAST);
        index++;

        JPanel tableYC2T = TableMaker.getTableYCards2TOfRef(selector);
        tableYC2T.setPreferredSize(new Dimension(245, graphicHeight));
        tableYC2T.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        ((JPanel) content.getComponents()[index]).add(tableYC2T, BorderLayout.EAST);
        index++;

        JPanel tableFouls1T = TableMaker.getTableFouls1TOfRef(selector);
        tableFouls1T.setPreferredSize(new Dimension(245, graphicHeight));
        tableFouls1T.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        ((JPanel) content.getComponents()[index]).add(tableFouls1T, BorderLayout.EAST);
        index++;

        JPanel tableFouls2T = TableMaker.getTableFouls2TOfRef(selector);
        tableFouls2T.setPreferredSize(new Dimension(245, graphicHeight));
        tableFouls2T.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        ((JPanel) content.getComponents()[index]).add(tableFouls2T, BorderLayout.EAST);
        index++;

        for (int i=0; i<5; i++){
            JPanel emptyPanel = new JPanel();
            emptyPanel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            emptyPanel.setPreferredSize(new Dimension(245, graphicHeight));
            emptyPanel.setBackground(new Color(238, 238, 238));
            ((JPanel) content.getComponents()[index]).add(emptyPanel, BorderLayout.EAST);
            index++;
        }

    }
}