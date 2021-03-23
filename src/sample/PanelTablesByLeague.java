package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYImageAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.tabbedui.VerticalLayout;
import org.jfree.util.ShapeUtilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class PanelTablesByLeague extends JPanel{
    JScrollPane scrollPane;
    final String path = "database/";
    ArrayList<Selector> arrayList = new ArrayList<>();
    //Renderer renderer = new Renderer();
    TablesThread tablesThread;
    String leagueName;
    String lastOrFull;
    String lastCalculatedLeague = "";
    String lastCalculatedSeason = "";
    String lastCalculatedLastOrFull = "";
    String season;
    String parameter;
    final JButton buttonShowInfo;
    JPanel panelWithTablesByLeague;
    JComboBox<String> seasonCB;
    JComboBox<String> leagueChooser;
    League league;
    Settings settings;

    public PanelTablesByLeague(){
        this.setLayout(new BorderLayout());
        final String curSeason = Settings.getDefaultSeason();

        ////////////////////////////////////////////ПАНЕЛЬ
        final JPanel headersPanel = new JPanel(new GridLayout(1, 0, 5, 5));

        ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
        String[] seasonList = new String[listOfSeasons.size()];
        for (int i = 0; i < seasonList.length; i++)
            seasonList[i] = "Сезон " + listOfSeasons.get(i);
        seasonCB = new JComboBox<>(seasonList);
        headersPanel.add(seasonCB);

        JFileChooser fileChooser = new JFileChooser(path + curSeason + "/leagues");
        String[] directoryList = fileChooser.getCurrentDirectory().list();
        final ArrayList<String> leagueList = new ArrayList<>();
        leagueList.add("Выберите лигу");
        for (String aDirectoryList : directoryList) leagueList.add(aDirectoryList.replace(".txt", ""));
        String[] listOfLeagues = new String[leagueList.size()];
        for (int i = 0; i < listOfLeagues.length; i++)
            listOfLeagues[i] = leagueList.get(i);
        leagueChooser = new JComboBox<>(listOfLeagues);
        headersPanel.add(leagueChooser);

        buttonShowInfo = new JButton("Отобразить!");
        Font fontForButton = new Font("", 0, 20);
        buttonShowInfo.setFont(fontForButton);
        headersPanel.add(buttonShowInfo);

        this.add(headersPanel, BorderLayout.NORTH);

        final JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        scrollPane = new JScrollPane();
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(infoPanel);

        leagueChooser.addActionListener(e -> {
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
            String leagueName = leagueChooser.getSelectedItem().toString();

        });

        seasonCB.addActionListener(e -> {
            seasonCB.setFocusable(false);
            String season = seasonCB.getSelectedItem().toString().replace("Сезон ", "");

            String leagueName = String.valueOf(leagueChooser.getSelectedItem());

            String pathToLeaguesList = path + season + "/leagues/";
            JFileChooser fileChooser1 = new JFileChooser(pathToLeaguesList);
            String[] directoryList1 = new String[fileChooser1.getCurrentDirectory().list().length+1];
            directoryList1[0] = "Выберите лигу";
            for (int i = 1; i< directoryList1.length; i++)
                directoryList1[i] = fileChooser1.getCurrentDirectory().list()[i-1].replace(".txt", "");
            DefaultComboBoxModel modelH = new DefaultComboBoxModel(directoryList1);
            leagueChooser.setModel(modelH);

            for (int i = 0; i< directoryList1.length; i++){
                if (directoryList1[i].equals(leagueName))
                    leagueChooser.setSelectedItem(leagueName);
            }
        });

        buttonShowInfo.addActionListener(e -> {
            infoPanel.removeAll();

            if (!((String) leagueChooser.getSelectedItem()).contains("Выберите")){
                JScrollPane panel = refreshLeagueData((String) leagueChooser.getSelectedItem(),
                        (String) seasonCB.getSelectedItem()
                );
                infoPanel.add(panel);
                infoPanel.revalidate();
            } else {
                infoPanel.add(new JLabel("  Не выбрана лига"), BorderLayout.NORTH);
                infoPanel.revalidate();
            }
            buttonShowInfo.setFocusable(false);

        });

    }

    public JScrollPane refreshLeagueData(final String leagueName, String seasonString){
        JScrollPane scrollPane = null;
        settings = Settings.getSettingsFromFile();
        this.setCursor(Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));

        JPanel container = new JPanel(new VerticalLayout());
        this.season = seasonString.replace("Сезон ", "");
        this.leagueName = leagueName;
        this.league = League.getLeagueFromFile(leagueName, season);

        panelWithTablesByLeague = new JPanel(new BorderLayout());
        panelWithTablesByLeague.setBorder(BorderFactory.createTitledBorder("Таблица статистических показателей по командам"));
        String[] paramsForTables;
        if (Settings.isWhoScoredLeague(leagueName)){
            paramsForTables = new String[]{"Выберите показатель", "Голы", "Голы в 1-ом тайме", "Голы во 2-ом тайме", "xG", "Владение",
                    "Ударов всего", "Удары в 1-ом тайме", "Удары во 2-ом тайме", "Удары в створ", "Удары в створ в 1-ом тайме",
                    "Удары в створ во 2-ом тайме", "Удары мимо", "Угловые", "Угловые в 1-ом тайме", "Угловые во 2-ом тайме",
                    "Офсайды", "Офсайды в 1-ом тайме", "Офсайды во 2-ом тайме", "Блокировано ударов", "Фолы",
                    "Фолы в 1-ом тайме", "Фолы во 2-ом тайме", "Желтые карточки",
                    "Желтые карточки в 1-ом тайме", "Желтые карточки во 2-ом тайме",
                    "Вброс аутов", "Вброс аутов в 1-ом тайме", "Вброс аутов во 2-ом тайме",
                    "Удары от ворот", "Удары от ворот в 1-ом тайме", "Удары от ворот во 2-ом тайме",
            };
        } else {
            paramsForTables = new String[]{"Выберите показатель", "Голы", "Голы в 1-ом тайме", "Голы во 2-ом тайме",
                    "Угловые", "Угловые в 1-ом тайме", "Угловые во 2-ом тайме",
                    "Желтые карточки", "Желтые карточки в 1-ом тайме", "Желтые карточки во 2-ом тайме",
            };
        }


        JPanel panelParameter = new JPanel(new GridLayout(1, 3, 10, 10));
        panelParameter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelForTable = new JLabel("Выберите показатели для построения таблицы:  ");
        labelForTable.setFont(new Font("", Font.BOLD, 15));
        panelParameter.add(labelForTable);

        String[] lastOrFullArr = new String[]{"Весь сезон", "Последние 3", "Последние 4", "Последние 5", "Последние 6", "Последние 7", "Последние 8", "Последние 9", "Последние 10", "Последние 15", "Последние 20"};
        final JComboBox<String> lastOrFullChooser = new JComboBox<>(lastOrFullArr);
        lastOrFull = lastOrFullArr[0];
        panelParameter.add(lastOrFullChooser);

        final JComboBox<String> paramChooser = new JComboBox<>(paramsForTables);
        panelParameter.add(paramChooser);

        panelWithTablesByLeague.add(panelParameter, BorderLayout.NORTH);
        container.add(panelWithTablesByLeague);

        JLabel label = new JLabel("Матчей сыграно: " + league.matchesPlayed);
        Font fontLabel = new Font("Arial", Font.BOLD, 15);
        label.setFont(fontLabel);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBorder(new EmptyBorder(10, 10, 0, 0));
        container.add(label);

        JLabel jtf = new JLabel("Таблица голов в " + leagueName);
        jtf.setFont(new Font("", 0, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        String[] colHeads = {"Показатель", "Общие", "Хозяева", "Гости", "Разница"};
        String[] params;
        if (league.homeXG + league.awayXG > 0)
            params = new String[]{"  Голы", "  Голы в 1-ом тайме", "  Голы во 2-ом тайме", "  Голы (сред.)", "  Голы в 1-ом тайме (сред.)", "  Голы во 2-ом тайме (сред.)", "  xG (сред.)", "  Реализация"};
        else
            params = new String[]{"  Голы", "  Голы в 1-ом тайме", "  Голы во 2-ом тайме", "  Голы (сред.)", "  Голы в 1-ом тайме (сред.)", "  Голы во 2-ом тайме (сред.)"};


        Object[][] data = new Object[params.length][colHeads.length];
        data[0][0] = params[0];
        data[0][1] = String.valueOf(league.homeGoals + league.awayGoals);
        data[0][2] = String.valueOf(league.homeGoals);
        data[0][3] = String.valueOf(league.awayGoals);
        data[0][4] = String.valueOf(league.homeGoals - league.awayGoals);
        data[1][0] = params[1];
        data[1][1] = String.valueOf(league.homeGoals1T + league.awayGoals1T);
        data[1][2] = String.valueOf(league.homeGoals1T);
        data[1][3] = String.valueOf(league.awayGoals1T);
        data[1][4] = String.valueOf(league.homeGoals1T - league.awayGoals1T);
        data[2][0] = params[2];
        data[2][1] = String.valueOf(league.homeGoals2T + league.awayGoals2T);
        data[2][2] = String.valueOf(league.homeGoals2T);
        data[2][3] = String.valueOf(league.awayGoals2T);
        data[2][4] = String.valueOf(league.homeGoals2T - league.awayGoals2T);
        data[3][0] = params[3];
        data[3][1] = String.valueOf(MyMath.round((league.homeGoals + league.awayGoals) / (double) league.matchesPlayed, 2) );
        data[3][2] = String.valueOf(MyMath.round(league.homeGoals / (double) league.matchesPlayed, 2) );
        data[3][3] = String.valueOf(MyMath.round(league.awayGoals / (double) league.matchesPlayed, 2) );
        data[3][4] = String.valueOf(MyMath.round((league.homeGoals - league.awayGoals) / (double) league.matchesPlayed, 2) );
        data[4][0] = params[4];
        data[4][1] = String.valueOf(MyMath.round((league.homeGoals1T + league.awayGoals1T) / (double) league.matchesPlayed, 2) );
        data[4][2] = String.valueOf(MyMath.round(league.homeGoals1T / (double) league.matchesPlayed, 2) );
        data[4][3] = String.valueOf(MyMath.round(league.awayGoals1T / (double) league.matchesPlayed, 2) );
        data[4][4] = String.valueOf(MyMath.round((league.homeGoals1T - league.awayGoals1T) / (double) league.matchesPlayed, 2) );
        data[5][0] = params[5];
        data[5][1] = String.valueOf(MyMath.round((league.homeGoals2T + league.awayGoals2T) / (double) league.matchesPlayed, 2) );
        data[5][2] = String.valueOf(MyMath.round(league.homeGoals2T / (double) league.matchesPlayed, 2) );
        data[5][3] = String.valueOf(MyMath.round(league.awayGoals2T / (double) league.matchesPlayed, 2) );
        data[5][4] = String.valueOf(MyMath.round((league.homeGoals2T - league.awayGoals2T) / (double) league.matchesPlayed, 2) );
        if (league.homeXG + league.awayXG > 0){
            data[6][0] = params[6];
            data[6][1] = String.valueOf(MyMath.round((league.homeXG + league.awayXG) / (double) league.matchesPlayed, 2) );
            data[6][2] = String.valueOf(MyMath.round(league.homeXG / (double) league.matchesPlayed, 2) );
            data[6][3] = String.valueOf(MyMath.round(league.awayXG / (double) league.matchesPlayed, 2) );
            data[6][4] = String.valueOf(MyMath.round((league.homeXG - league.awayXG) / (double) league.matchesPlayed, 2) );
            data[7][0] = params[7];
            data[7][1] = String.valueOf(MyMath.round((league.homeXG + league.awayXG) / (double) (league.homeGoals + league.awayGoals), 2) );
            data[7][2] = String.valueOf(MyMath.round(league.homeXG / (double) league.homeGoals, 2) );
            data[7][3] = String.valueOf(MyMath.round(league.awayXG / (double) league.awayGoals, 2) );
            data[7][4] = "-";
        }

        JTable table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        Renderer tableRenderer = new Renderer(6);
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
//        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=1; r<colHeads.length; r++)
            table.getColumnModel().getColumn(r).setCellRenderer(tableRenderer);
//            table.getColumnModel().getColumn(r).setCellRenderer(renderer);


        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        container.add(tablePanel);

        JPanel panelButton = new JPanel(new BorderLayout());
        JButton buttonShowBubble = new JButton("Отобразить графики перекрестных показателей");
        buttonShowBubble.setFont(fontLabel);
        panelButton.setBorder(BorderFactory.createEmptyBorder(5, 300, 5, 300));

        if (!Settings.isTop6League(leagueName)){
            buttonShowBubble.setEnabled(false);
        }
        panelButton.add(buttonShowBubble);

        container.add(panelButton);

        JPanel bubbleChartsPanel = new JPanel(new BorderLayout());
        container.add(bubbleChartsPanel);


        jtf = new JLabel("Диаграммы голов в " + leagueName);
        jtf.setFont(new Font("", Font.BOLD, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        // row keys...
        String series1 = "Победа хозяев";
        String series2 = "Ничья";
        String series3 = "Победа гостей";
        // column keys...
        String category1 = "Матч" ;
        String category2 = "1-ый тайм" ;
        String category3 = "2-ой тайм" ;

        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin.split("\\*")[2]), series3, category1);

        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin_1T.split("\\*")[0]), series1, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin_1T.split("\\*")[1]), series2, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin_1T.split("\\*")[2]), series3, category2);

        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin_2T.split("\\*")[0]), series1, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin_2T.split("\\*")[1]), series2, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_homeWin_draw_awayWin_2T.split("\\*")[2]), series3, category3);

        JFreeChart chartWinDrawLose = ChartFactory.createBarChart(
                "Исходы матчей и таймов", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        CategoryPlot plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(255, 220, 60 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(40, 40, 255 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        NumberAxis axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        SubCategoryAxis subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        ChartPanel cp = new ChartPanel(chartWinDrawLose);
//        cp.setPreferredSize(new Dimension(500, graphicHeight));
        container.add(cp);

        jtf = new JLabel("Тоталы голов в " + leagueName);
        jtf.setFont(new Font("", Font.BOLD, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        categoryDataset = new DefaultCategoryDataset();
        // row keys...
        String series0 = "0";
        series1 = "1";
        series2 = "2";
        series3 = "3";
        String series4 = "4";
        String series5 = "5";
        String series6 = "6+";

        // column keys...
        category1 = "Матч" ;
        category2 = "1-ый тайм" ;
        category3 = "2-ой тайм" ;

        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[0]), series0, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[1]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[2]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[3]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[4]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[5]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.g_totals.split("\\*")[6]), series6, category1);

        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[0]), series0, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[1]), series1, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[2]), series2, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[3]), series3, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[4]), series4, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[5]), series5, category2);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_1T.split("\\*")[6]), series6, category2);

        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[0]), series0, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[1]), series1, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[2]), series2, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[3]), series3, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[4]), series4, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[5]), series5, category3);
        categoryDataset.addValue(Double.parseDouble(league.g_totals_2T.split("\\*")[6]), series6, category3);

        JFreeChart chartTotals = ChartFactory.createBarChart(
                "", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartTotals.setBackgroundPaint(new Color(238, 238, 238));
        chartTotals.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartTotals.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        CategoryPlot plotTotals = chartTotals.getCategoryPlot();
        plotTotals.setBackgroundPaint(new Color(238, 238, 238));
        plotTotals.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotTotals.getRenderer().setSeriesPaint(1, new Color(40, 40, 255 ));
        plotTotals.getRenderer().setSeriesPaint(2, new Color(0, 140, 20 ));
        plotTotals.getRenderer().setSeriesPaint(3, new Color(242, 120, 21 ));
        plotTotals.getRenderer().setSeriesPaint(4, new Color(187,  46, 230));
        plotTotals.getRenderer().setSeriesPaint(5, new Color(30 , 200, 230));
        plotTotals.getRenderer().setSeriesPaint(6, new Color(255, 220, 60));

        plotTotals.setDomainGridlinePaint(Color.black);
        plotTotals.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotTotals.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotTotals.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartTotals);
//        cp.setPreferredSize(new Dimension(500, graphicHeight));
        container.add(cp);

        categoryDataset = new DefaultCategoryDataset();
        // row keys...
        series1 = "0' - 15'";
        series2 = "16' - 30'";
        series3 = "31' - 45+'";
        series4 = "46' - 60'";
        series5 = "61' - 75'";
        series6 = "76' - 90+'";
        // column keys...
        category1 = "Общие" ;
        category2 = "Хозяева" ;
        category3 = "Гости" ;

        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[0]) + Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[1]) + Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[2]) + Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[2]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[3]) + Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[3]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[4]) + Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[4]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[5]) + Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[5]), series6, category1);

        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[0]), series1, category2);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[1]), series2, category2);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[2]), series3, category2);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[3]), series4, category2);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[4]), series5, category2);
        categoryDataset.addValue(Double.parseDouble(league.homeGoalsBy15Min.split("\\*")[5]), series6, category2);

        categoryDataset.addValue(Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[0]), series1, category3);
        categoryDataset.addValue(Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[1]), series2, category3);
        categoryDataset.addValue(Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[2]), series3, category3);
        categoryDataset.addValue(Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[3]), series4, category3);
        categoryDataset.addValue(Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[4]), series5, category3);
        categoryDataset.addValue(Double.parseDouble(league.awayGoalsBy15Min.split("\\*")[5]), series6, category3);


        JFreeChart chartBy15Min = ChartFactory.createBarChart(
                "Голы по 15-минуткам", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartBy15Min.setBackgroundPaint(new Color(238, 238, 238));
        chartBy15Min.getTitle().setFont(new Font("", Font.BOLD, 18));
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

        cp = new ChartPanel(chartBy15Min);
        container.add(cp);

        JPanel panelPieDiagram = new JPanel(new GridLayout(1, 0, 0, 0));
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue("ДА",  league.g_OZ );
        dataset.setValue("НЕТ", league.matchesPlayed - league.g_OZ );

        JFreeChart chart = ChartFactory.createPieChart( "Обе забьют в " + leagueName, dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("ДА", new Color(0, 240, 20 ));
        plot.setSectionPaint("НЕТ", new Color(255, 40, 40 ));

        cp = new ChartPanel(chart);
        panelPieDiagram.add(cp);

        dataset = new DefaultPieDataset( );
        dataset.setValue("1 > 2", league.g_firstTimeMoreGoals );
        dataset.setValue("1 = 2", league.matchesPlayed - league.g_firstTimeMoreGoals - league.g_secondTimeMoreGoals );
        dataset.setValue("2 > 1", league.g_secondTimeMoreGoals );

        chart = ChartFactory.createPieChart( "Сравнение таймов по голам", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("1 > 2", new Color(0, 240, 20 ));
        plot.setSectionPaint("1 = 2", new Color(255, 40, 40 ));
        plot.setSectionPaint("2 > 1", new Color(40, 40, 255 ));

        cp = new ChartPanel(chart);
        panelPieDiagram.add(cp);

        dataset = new DefaultPieDataset( );
        dataset.setValue("ТБ(2.5)", league.matchesPlayed -(Double.parseDouble(league.g_totals.split("\\*")[0]) + Double.parseDouble(league.g_totals.split("\\*")[1]) + Double.parseDouble(league.g_totals.split("\\*")[2])));
        dataset.setValue("ТМ(2.5)", Double.parseDouble(league.g_totals.split("\\*")[0]) + Double.parseDouble(league.g_totals.split("\\*")[1]) + Double.parseDouble(league.g_totals.split("\\*")[2]));

        chart = ChartFactory.createPieChart( "Тотал 2.5 в матчах " + leagueName, dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("ТБ(2.5)", new Color(0, 240, 20 ));
        plot.setSectionPaint("ТМ(2.5)", new Color(255, 40, 40 ));

        cp = new ChartPanel(chart);
        panelPieDiagram.add(cp);
        panelPieDiagram.setPreferredSize(new Dimension(this.getWidth()-20, 300));

        container.add(panelPieDiagram);

        jtf = new JLabel("Таблица угловых в " + leagueName);
        jtf.setFont(new Font("", Font.BOLD, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        colHeads = new String[]{"Показатель", "Общие", "Хозяева", "Гости", "Разница"};
        params = new String[]{"  Угловые", "  Угловые в 1-ом тайме", "  Угловые во 2-ом тайме", "  Угловые (сред.)", "  Угловые в 1-ом тайме (сред.)", "  Угловые во 2-ом тайме (сред.)"};

        data = new Object[params.length][colHeads.length];
        data[0][0] = params[0];
        data[0][1] = String.valueOf(league.homeCorners + league.awayCorners);
        data[0][2] = String.valueOf(league.homeCorners);
        data[0][3] = String.valueOf(league.awayCorners);
        data[0][4] = String.valueOf(league.homeCorners - league.awayCorners);
        data[1][0] = params[1];
        data[1][1] = String.valueOf(league.homeCorners1T + league.awayCorners1T);
        data[1][2] = String.valueOf(league.homeCorners1T);
        data[1][3] = String.valueOf(league.awayCorners1T);
        data[1][4] = String.valueOf(league.homeCorners1T - league.awayCorners1T);
        data[2][0] = params[2];
        data[2][1] = String.valueOf(league.homeCorners2T + league.awayCorners2T);
        data[2][2] = String.valueOf(league.homeCorners2T);
        data[2][3] = String.valueOf(league.awayCorners2T);
        data[2][4] = String.valueOf(league.homeCorners2T - league.awayCorners2T);
        data[3][0] = params[3];
        data[3][1] = String.valueOf(MyMath.round((league.homeCorners + league.awayCorners) / (double) league.matchesPlayed, 2) );
        data[3][2] = String.valueOf(MyMath.round(league.homeCorners / (double) league.matchesPlayed, 2) );
        data[3][3] = String.valueOf(MyMath.round(league.awayCorners / (double) league.matchesPlayed, 2) );
        data[3][4] = String.valueOf(MyMath.round((league.homeCorners - league.awayCorners) / (double) league.matchesPlayed, 2) );
        data[4][0] = params[4];
        data[4][1] = String.valueOf(MyMath.round((league.homeCorners1T + league.awayCorners1T) / (double) league.matchesPlayed, 2) );
        data[4][2] = String.valueOf(MyMath.round(league.homeCorners1T / (double) league.matchesPlayed, 2) );
        data[4][3] = String.valueOf(MyMath.round(league.awayCorners1T / (double) league.matchesPlayed, 2) );
        data[4][4] = String.valueOf(MyMath.round((league.homeCorners1T - league.awayCorners1T) / (double) league.matchesPlayed, 2) );
        data[5][0] = params[5];
        data[5][1] = String.valueOf(MyMath.round((league.homeCorners2T + league.awayCorners2T) / (double) league.matchesPlayed, 2) );
        data[5][2] = String.valueOf(MyMath.round(league.homeCorners2T / (double) league.matchesPlayed, 2) );
        data[5][3] = String.valueOf(MyMath.round(league.awayCorners2T / (double) league.matchesPlayed, 2) );
        data[5][4] = String.valueOf(MyMath.round((league.homeCorners2T - league.awayCorners2T) / (double) league.matchesPlayed, 2) );

        table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);


        tableRenderer = new Renderer(6);
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=1; r<colHeads.length; r++)
            table.getColumnModel().getColumn(r).setCellRenderer(tableRenderer);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        container.add(tablePanel);

        series1 = "Победа хозяев";
        series2 = "Ничья";
        series3 = "Победа гостей";
        // column keys...
        category1 = "Матч" ;
        category2 = "1-ый тайм" ;
        category3 = "2-ой тайм" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin.split("\\*")[2]), series3, category1);

        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin_1T.split("\\*")[0]), series1, category2);
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin_1T.split("\\*")[1]), series2, category2);
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin_1T.split("\\*")[2]), series3, category2);

        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin_2T.split("\\*")[0]), series1, category3);
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin_2T.split("\\*")[1]), series2, category3);
        categoryDataset.addValue(Double.parseDouble(league.c_homeWin_draw_awayWin_2T.split("\\*")[2]), series3, category3);

        chartWinDrawLose = ChartFactory.createBarChart(
                "УГЛОВЫЕ. Исходы матчей и таймов", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(255, 220, 60 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(40, 40, 255 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
//        cp.setPreferredSize(new Dimension(500, graphicHeight));
        container.add(cp);

        JPanel panelCornersTotals = new JPanel(new GridLayout(1, 3, 0, 0));
        JPanel panel1 = new JPanel();
        series1 = "5-";
        series2 = "6";
        series3 = "7";
        series4 = "8";
        series5 = "9";
        series6 = "10";
        String series7 = "11";
        String series8 = "12";
        String series9 = "13";
        String series10 = "14+";
        // column keys...
        category1 = "Матч" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[0]) + Double.parseDouble(league.c_totals.split("\\*")[1]) + Double.parseDouble(league.c_totals.split("\\*")[2])
                                + Double.parseDouble(league.c_totals.split("\\*")[3]) + Double.parseDouble(league.c_totals.split("\\*")[4]) + Double.parseDouble(league.c_totals.split("\\*")[5]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[6]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[7]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[8]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[9]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[10]), series6, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[11]), series7, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[12]), series8, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[13]), series9, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals.split("\\*")[14]) + Double.parseDouble(league.c_totals.split("\\*")[15]) + Double.parseDouble(league.c_totals.split("\\*")[16]), series10, category1);

        chartWinDrawLose = ChartFactory.createBarChart(
                "Тоталы угловых", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(30 , 190, 240));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(255, 220, 60));
        plotWinDrawLose.getRenderer().setSeriesPaint(3, new Color(160, 40, 220));
        plotWinDrawLose.getRenderer().setSeriesPaint(4, new Color(130, 70, 0));
        plotWinDrawLose.getRenderer().setSeriesPaint(5, new Color(40, 40, 255 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(6, new Color(242, 120, 21 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(7, new Color(50, 240, 200));
        plotWinDrawLose.getRenderer().setSeriesPaint(8, new Color(240, 40, 230));
        plotWinDrawLose.getRenderer().setSeriesPaint(9, new Color(0, 140, 20 ));


        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 20, 300));
        panel1.add(cp);
        panelCornersTotals.add(panel1);

        JPanel panel2 = new JPanel();
        series1 = "0";
        series2 = "1";
        series3 = "2";
        series4 = "3";
        series5 = "4";
        series6 = "5";
        series7 = "6";
        series8 = "7";
        series9 = "8";
        series10 = "9+";
        // column keys...
        category1 = "1-ый тайм" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[2]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[3]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[4]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[5]), series6, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[6]), series7, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[7]), series8, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[8]), series9, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_1T.split("\\*")[9]) + Double.parseDouble(league.c_totals_1T.split("\\*")[10]), series10, category1);

        chartWinDrawLose = ChartFactory.createBarChart(
                "Тоталы угловых в 1-ом тайме", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(30 , 190, 240));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(255, 220, 60));
        plotWinDrawLose.getRenderer().setSeriesPaint(3, new Color(160, 40, 220));
        plotWinDrawLose.getRenderer().setSeriesPaint(4, new Color(130, 70, 0));
        plotWinDrawLose.getRenderer().setSeriesPaint(5, new Color(40, 40, 255 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(6, new Color(242, 120, 21 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(7, new Color(50, 240, 200));
        plotWinDrawLose.getRenderer().setSeriesPaint(8, new Color(240, 40, 230));
        plotWinDrawLose.getRenderer().setSeriesPaint(9, new Color(0, 140, 20 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 20, 300));
        panel2.add(cp);
        panelCornersTotals.add(panel2);

        JPanel panel3 = new JPanel();
        series1 = "0";
        series2 = "1";
        series3 = "2";
        series4 = "3";
        series5 = "4";
        series6 = "5";
        series7 = "6";
        series8 = "7";
        series9 = "8";
        series10 = "9+";
        // column keys...
        category1 = "2-ый тайм" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[2]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[3]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[4]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[5]), series6, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[6]), series7, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[7]), series8, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[8]), series9, category1);
        categoryDataset.addValue(Double.parseDouble(league.c_totals_2T.split("\\*")[9]) + Double.parseDouble(league.c_totals_2T.split("\\*")[10]), series10, category1);

        chartWinDrawLose = ChartFactory.createBarChart(
                "Тоталы угловых во 2-ом тайме", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(30 , 190, 240));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(255, 220, 60));
        plotWinDrawLose.getRenderer().setSeriesPaint(3, new Color(160, 40, 220));
        plotWinDrawLose.getRenderer().setSeriesPaint(4, new Color(130, 70, 0));
        plotWinDrawLose.getRenderer().setSeriesPaint(5, new Color(40, 40, 255 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(6, new Color(242, 120, 21 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(7, new Color(50, 240, 200));
        plotWinDrawLose.getRenderer().setSeriesPaint(8, new Color(240, 40, 230));
        plotWinDrawLose.getRenderer().setSeriesPaint(9, new Color(0, 140, 20 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 20, 300));
        panel3.add(cp);
        panelCornersTotals.add(panel3);
        container.add(panelCornersTotals);

        panelPieDiagram = new JPanel(new GridLayout(1, 0, 0, 0));
        dataset = new DefaultPieDataset( );
        dataset.setValue("1 > 2", league.c_firstTimeMoreCorners );
        dataset.setValue("1 = 2", league.matchesPlayed - league.c_firstTimeMoreCorners - league.c_secondTimeMoreCorners );
        dataset.setValue("2 > 1", league.c_secondTimeMoreCorners );

        chart = ChartFactory.createPieChart( "Сравнение таймов по угловым", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("1 > 2", new Color(0, 240, 20 ));
        plot.setSectionPaint("1 = 2", new Color(255, 40, 40 ));
        plot.setSectionPaint("2 > 1", new Color(40, 40, 255 ));

        cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, 300));
        panelPieDiagram.add(cp);

        int tm95 = 0;
        int tm105 = 0;
        for (int i=0; i<=9; i++){
            tm95 += (int) Double.parseDouble(league.c_totals.split("\\*")[i]);
            tm105 += (int) Double.parseDouble(league.c_totals.split("\\*")[i]);
        }
        tm105 += (int) Double.parseDouble(league.c_totals.split("\\*")[10]);

        dataset = new DefaultPieDataset( );
        dataset.setValue("ТБ(9.5)", league.matchesPlayed - tm95 );
        dataset.setValue("ТМ(9.5)", tm95 );

        chart = ChartFactory.createPieChart( "Тотал угловых 9.5", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("ТБ(9.5)", new Color(0, 240, 20 ));
        plot.setSectionPaint("ТМ(9.5)", new Color(255, 40, 40 ));

        cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, 300));
        panelPieDiagram.add(cp);

        dataset = new DefaultPieDataset( );
        dataset.setValue("ТБ(10.5)", league.matchesPlayed - tm105 );
        dataset.setValue("ТМ(10.5)", tm105 );

        chart = ChartFactory.createPieChart( "Тотал угловых 10.5", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("ТБ(10.5)", new Color(0, 240, 20 ));
        plot.setSectionPaint("ТМ(10.5)", new Color(255, 40, 40 ));

        cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, 300));
        panelPieDiagram.add(cp);
        container.add(panelPieDiagram);

        jtf = new JLabel("Таблица ЖК в " + leagueName);
        jtf.setFont(new Font("", Font.BOLD, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        colHeads = new String[]{"Показатель", "Общие", "Хозяева", "Гости", "Разница"};
        params = new String[]{"  Желтые карточки", "  Желтые карточки в 1-ом тайме", "  Желтые карточки во 2-ом тайме",
                "  Желтые карточки (сред.)", "  Желтые карточки в 1-ом тайме (сред.)", "  Желтые карточки во 2-ом тайме (сред.)"};

        data = new Object[params.length][colHeads.length];
        data[0][0] = params[0];
        data[0][1] = String.valueOf(league.homeYellowCards + league.awayYellowCards);
        data[0][2] = String.valueOf(league.homeYellowCards);
        data[0][3] = String.valueOf(league.awayYellowCards);
        data[0][4] = String.valueOf(league.homeYellowCards - league.awayYellowCards);
        data[1][0] = params[1];
        data[1][1] = String.valueOf(league.homeYellowCards1T + league.awayYellowCards1T);
        data[1][2] = String.valueOf(league.homeYellowCards1T);
        data[1][3] = String.valueOf(league.awayYellowCards1T);
        data[1][4] = String.valueOf(league.homeYellowCards1T - league.awayYellowCards1T);
        data[2][0] = params[2];
        data[2][1] = String.valueOf(league.homeYellowCards2T + league.awayYellowCards2T);
        data[2][2] = String.valueOf(league.homeYellowCards2T);
        data[2][3] = String.valueOf(league.awayYellowCards2T);
        data[2][4] = String.valueOf(league.homeYellowCards2T - league.awayYellowCards2T);
        data[3][0] = params[3];
        data[3][1] = String.valueOf(MyMath.round((league.homeYellowCards + league.awayYellowCards) / (double) league.matchesPlayed, 2) );
        data[3][2] = String.valueOf(MyMath.round(league.homeYellowCards / (double) league.matchesPlayed, 2) );
        data[3][3] = String.valueOf(MyMath.round(league.awayYellowCards / (double) league.matchesPlayed, 2) );
        data[3][4] = String.valueOf(MyMath.round((league.homeYellowCards - league.awayYellowCards) / (double) league.matchesPlayed, 2) );
        data[4][0] = params[4];
        data[4][1] = String.valueOf(MyMath.round((league.homeYellowCards1T + league.awayYellowCards1T) / (double) league.matchesPlayed, 2) );
        data[4][2] = String.valueOf(MyMath.round(league.homeYellowCards1T / (double) league.matchesPlayed, 2) );
        data[4][3] = String.valueOf(MyMath.round(league.awayYellowCards1T / (double) league.matchesPlayed, 2) );
        data[4][4] = String.valueOf(MyMath.round((league.homeYellowCards1T - league.awayYellowCards1T) / (double) league.matchesPlayed, 2) );
        data[5][0] = params[5];
        data[5][1] = String.valueOf(MyMath.round((league.homeYellowCards2T + league.awayYellowCards2T) / (double) league.matchesPlayed, 2) );
        data[5][2] = String.valueOf(MyMath.round(league.homeYellowCards2T / (double) league.matchesPlayed, 2) );
        data[5][3] = String.valueOf(MyMath.round(league.awayYellowCards2T / (double) league.matchesPlayed, 2) );
        data[5][4] = String.valueOf(MyMath.round((league.homeYellowCards2T - league.awayYellowCards2T) / (double) league.matchesPlayed, 2) );

        table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        tableRenderer = new Renderer(6);
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=1; r<colHeads.length; r++)
            table.getColumnModel().getColumn(r).setCellRenderer(tableRenderer);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        container.add(tablePanel);

        series1 = "Победа хозяев";
        series2 = "Ничья";
        series3 = "Победа гостей";
        // column keys...
        category1 = "Матч" ;
        category2 = "1-ый тайм" ;
        category3 = "2-ой тайм" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin.split("\\*")[2]), series3, category1);

        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin_1T.split("\\*")[0]), series1, category2);
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin_1T.split("\\*")[1]), series2, category2);
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin_1T.split("\\*")[2]), series3, category2);

        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin_2T.split("\\*")[0]), series1, category3);
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin_2T.split("\\*")[1]), series2, category3);
        categoryDataset.addValue(Double.parseDouble(league.yc_homeWin_draw_awayWin_2T.split("\\*")[2]), series3, category3);

        chartWinDrawLose = ChartFactory.createBarChart(
                "ЖК. Исходы матчей и таймов", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(255, 220, 60 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(40, 40, 255 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, 300));
        container.add(cp);

        JPanel panelYCTotals = new JPanel(new GridLayout(1, 3, 0, 0));
        panel1 = new JPanel();
        series1 = "0";
        series2 = "1";
        series3 = "2";
        series4 = "3";
        series5 = "4";
        series6 = "5";
        series7 = "6";
        series8 = "7";
        series9 = "8";
        // column keys...
        category1 = "Матч" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[2]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[3]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[4]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[5]), series6, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[6]), series7, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[7]), series8, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals.split("\\*")[8]), series9, category1);

        chartWinDrawLose = ChartFactory.createBarChart(
                "Тоталы ЖК", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(30 , 190, 240));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(255, 220, 60));
        plotWinDrawLose.getRenderer().setSeriesPaint(3, new Color(160, 40, 220));
        plotWinDrawLose.getRenderer().setSeriesPaint(4, new Color(130, 70, 0));
        plotWinDrawLose.getRenderer().setSeriesPaint(5, new Color(40, 40, 255 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(6, new Color(242, 120, 21 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(7, new Color(50, 240, 200));
        plotWinDrawLose.getRenderer().setSeriesPaint(8, new Color(0, 140, 20 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 20, 300));
        panel1.add(cp);
        panelYCTotals.add(panel1);

        panel2 = new JPanel();
        series1 = "0";
        series2 = "1";
        series3 = "2";
        series4 = "3";
        series5 = "4";
        series6 = "5";
        series7 = "6";
        // column keys...
        category1 = "1-ый тайм" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[2]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[3]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[4]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[5]), series6, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_1T.split("\\*")[6]), series7, category1);

        chartWinDrawLose = ChartFactory.createBarChart(
                "Тоталы ЖК в 1-ом тайме", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(30 , 190, 240));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(255, 220, 60));
        plotWinDrawLose.getRenderer().setSeriesPaint(3, new Color(160, 40, 220));
        plotWinDrawLose.getRenderer().setSeriesPaint(4, new Color(40, 40, 255 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(5, new Color(242, 120, 21 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(6, new Color(0, 140, 20 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 20, 300));
        panel2.add(cp);
        panelYCTotals.add(panel2);

        panel3 = new JPanel();
        series1 = "0";
        series2 = "1";
        series3 = "2";
        series4 = "3";
        series5 = "4";
        series6 = "5";
        series7 = "6";
        // column keys...
        category1 = "2-ый тайм" ;

        categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[0]), series1, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[1]), series2, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[2]), series3, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[3]), series4, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[4]), series5, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[5]), series6, category1);
        categoryDataset.addValue(Double.parseDouble(league.yc_totals_2T.split("\\*")[6]), series7, category1);

        chartWinDrawLose = ChartFactory.createBarChart(
                "Тоталы ЖК во 2-ом тайме", "", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        // Определение фона диаграммы
        chartWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        chartWinDrawLose.getTitle().setFont(new Font("", Font.BOLD, 18));
        chartWinDrawLose.getTitle().setMargin(10,0,0,0);
        // Настройка plot'а
        plotWinDrawLose = chartWinDrawLose.getCategoryPlot();
        plotWinDrawLose.setBackgroundPaint(new Color(238, 238, 238));
        plotWinDrawLose.getRenderer().setSeriesPaint(0, new Color(255, 40, 40 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(1, new Color(30 , 190, 240));
        plotWinDrawLose.getRenderer().setSeriesPaint(2, new Color(255, 220, 60));
        plotWinDrawLose.getRenderer().setSeriesPaint(3, new Color(160, 40, 220));
        plotWinDrawLose.getRenderer().setSeriesPaint(4, new Color(40, 40, 255 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(5, new Color(242, 120, 21 ));
        plotWinDrawLose.getRenderer().setSeriesPaint(6, new Color(0, 140, 20 ));

        plotWinDrawLose.setDomainGridlinePaint(Color.black);
        plotWinDrawLose.setRangeGridlinePaint(Color.black);
        axis = (NumberAxis) plotWinDrawLose.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        renderer = (BarRenderer)plotWinDrawLose.getRenderer();
        renderer.setItemMargin(0.02);
        subCategoryAxis = new SubCategoryAxis("");
        subCategoryAxis.setCategoryMargin(0.15);

        cp = new ChartPanel(chartWinDrawLose);
        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 20, 300));
        panel3.add(cp);
        panelYCTotals.add(panel3);

        container.add(panelYCTotals);

        panelPieDiagram = new JPanel(new GridLayout(1, 0, 0, 0));
        dataset = new DefaultPieDataset( );
        dataset.setValue("1 > 2", league.yc_firstTimeMoreYC );
        dataset.setValue("1 = 2", league.matchesPlayed - league.yc_firstTimeMoreYC - league.yc_secondTimeMoreYC );
        dataset.setValue("2 > 1", league.yc_secondTimeMoreYC );

        chart = ChartFactory.createPieChart( "Сравнение таймов по ЖК", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("1 > 2", new Color(0, 240, 20 ));
        plot.setSectionPaint("1 = 2", new Color(255, 40, 40 ));
        plot.setSectionPaint("2 > 1", new Color(40, 40, 255 ));

        cp = new ChartPanel(chart);
//        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, 300));
        panelPieDiagram.add(cp);

        int tm35 = 0;
        int tm45 = 0;
        for (int i=0; i<=3; i++){
            tm35 += (int) Double.parseDouble(league.yc_totals.split("\\*")[i]);
            tm45 += (int) Double.parseDouble(league.yc_totals.split("\\*")[i]);
        }
        tm45 += (int) Double.parseDouble(league.yc_totals.split("\\*")[4]);

        dataset = new DefaultPieDataset( );
        dataset.setValue("ТБ(3.5)", league.matchesPlayed - tm35 );
        dataset.setValue("ТМ(3.5)", tm35 );

        chart = ChartFactory.createPieChart( "Тотал ЖК 3.5", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("ТБ(3.5)", new Color(0, 240, 20 ));
        plot.setSectionPaint("ТМ(3.5)", new Color(255, 40, 40 ));

        cp = new ChartPanel(chart);
//        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, graphicHeight));
        panelPieDiagram.add(cp);

        dataset = new DefaultPieDataset( );
        dataset.setValue("ТБ(4.5)", league.matchesPlayed - tm45 );
        dataset.setValue("ТМ(4.5)", tm45 );

        chart = ChartFactory.createPieChart( "Тотал ЖК 4.5", dataset, true, true, false);
        chart.setBackgroundPaint(new Color(238, 238, 238));
        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} \n {2}"));
        plot.setBackgroundPaint(new Color(238, 238, 238));
        plot.setSectionPaint("ТБ(4.5)", new Color(0, 240, 20 ));
        plot.setSectionPaint("ТМ(4.5)", new Color(255, 40, 40 ));

        cp = new ChartPanel(chart);
//        cp.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, graphicHeight));
        panelPieDiagram.add(cp);
        panelPieDiagram.setPreferredSize(new Dimension(this.getWidth() / 3 - 10, 300));

        container.add(panelPieDiagram);

        jtf = new JLabel("Таблица средних значений стат. показателей в " + leagueName);
        jtf.setFont(new Font("", Font.BOLD, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        colHeads = new String[]{"Показатель", "Общие", "Хозяева", "Гости", "Разница"};
        params = new String[]{"  Владение мячом, %", "  Удары", "  Удары в створ",
                "  Удары мимо", "  Заблокировано ударов", "  Сэйвы", "  Фолы", "  Успешный дриблинг",
                "  Выиграно возд.единоборств", "  Офсайды", "  Выносы", "  Перехваты",
                "  Потери", "  Отборы", "  Ключевые передачи"
        };

        data = new Object[params.length][colHeads.length];
        data[0][0] = params[0];
        data[0][1] = String.valueOf(MyMath.round((league.homeBallPossession + league.awayBallPossession) / (double) league.matchesPlayed, 2) );
        data[0][2] = String.valueOf(MyMath.round(league.homeBallPossession / (double) league.matchesPlayed, 2) );
        data[0][3] = String.valueOf(MyMath.round(league.awayBallPossession / (double) league.matchesPlayed, 2) );
        data[0][4] = String.valueOf(MyMath.round((league.homeBallPossession - league.awayBallPossession) / (double) league.matchesPlayed, 2) );
        data[1][0] = params[1];
        data[1][1] = String.valueOf(MyMath.round((league.homeShots + league.awayShots) / (double) league.matchesPlayed, 2) );
        data[1][2] = String.valueOf(MyMath.round(league.homeShots / (double) league.matchesPlayed, 2) );
        data[1][3] = String.valueOf(MyMath.round(league.awayShots / (double) league.matchesPlayed, 2) );
        data[1][4] = String.valueOf(MyMath.round((league.homeShots - league.awayShots) / (double) league.matchesPlayed, 2) );
        data[2][0] = params[2];
        data[2][1] = String.valueOf(MyMath.round((league.homeShotsOnTarget + league.awayShotsOnTarget) / (double) league.matchesPlayed, 2) );
        data[2][2] = String.valueOf(MyMath.round(league.homeShotsOnTarget / (double) league.matchesPlayed, 2) );
        data[2][3] = String.valueOf(MyMath.round(league.awayShotsOnTarget / (double) league.matchesPlayed, 2) );
        data[2][4] = String.valueOf(MyMath.round((league.homeShotsOnTarget - league.awayShotsOnTarget) / (double) league.matchesPlayed, 2) );
        data[3][0] = params[3];
        data[3][1] = String.valueOf(MyMath.round((league.homeShotsOffTarget + league.awayShotsOffTarget) / (double) league.matchesPlayed, 2) );
        data[3][2] = String.valueOf(MyMath.round(league.homeShotsOffTarget / (double) league.matchesPlayed, 2) );
        data[3][3] = String.valueOf(MyMath.round(league.awayShotsOffTarget / (double) league.matchesPlayed, 2) );
        data[3][4] = String.valueOf(MyMath.round((league.homeShotsOffTarget - league.awayShotsOffTarget) / (double) league.matchesPlayed, 2) );
        data[4][0] = params[4];
        data[4][1] = String.valueOf(MyMath.round((league.homeBlockedShots + league.awayBlockedShots) / (double) league.matchesPlayed, 2) );
        data[4][2] = String.valueOf(MyMath.round(league.homeBlockedShots / (double) league.matchesPlayed, 2) );
        data[4][3] = String.valueOf(MyMath.round(league.awayBlockedShots / (double) league.matchesPlayed, 2) );
        data[4][4] = String.valueOf(MyMath.round((league.homeBlockedShots - league.awayBlockedShots) / (double) league.matchesPlayed, 2) );
        data[5][0] = params[5];
        data[5][1] = String.valueOf(MyMath.round((league.homeSaves + league.awaySaves) / (double) league.matchesPlayed, 2) );
        data[5][2] = String.valueOf(MyMath.round(league.homeSaves / (double) league.matchesPlayed, 2) );
        data[5][3] = String.valueOf(MyMath.round(league.awaySaves / (double) league.matchesPlayed, 2) );
        data[5][4] = String.valueOf(MyMath.round((league.homeSaves - league.awaySaves) / (double) league.matchesPlayed, 2) );
        data[6][0] = params[6];
        data[6][1] = String.valueOf(MyMath.round((league.homeFouls + league.awayFouls) / (double) league.matchesPlayed, 2) );
        data[6][2] = String.valueOf(MyMath.round(league.homeFouls / (double) league.matchesPlayed, 2) );
        data[6][3] = String.valueOf(MyMath.round(league.awayFouls / (double) league.matchesPlayed, 2) );
        data[6][4] = String.valueOf(MyMath.round((league.homeFouls - league.awayFouls) / (double) league.matchesPlayed, 2) );
        data[7][0] = params[7];
        data[7][1] = String.valueOf(MyMath.round((league.homeDribbles + league.awayDribbles) / (double) league.matchesPlayed, 2) );
        data[7][2] = String.valueOf(MyMath.round(league.homeDribbles / (double) league.matchesPlayed, 2) );
        data[7][3] = String.valueOf(MyMath.round(league.awayDribbles / (double) league.matchesPlayed, 2) );
        data[7][4] = String.valueOf(MyMath.round((league.homeDribbles - league.awayDribbles) / (double) league.matchesPlayed, 2) );
        data[8][0] = params[8];
        data[8][1] = String.valueOf(MyMath.round((league.homeAerialsWon + league.awayAerialsWon) / (double) league.matchesPlayed, 2) );
        data[8][2] = String.valueOf(MyMath.round(league.homeAerialsWon / (double) league.matchesPlayed, 2) );
        data[8][3] = String.valueOf(MyMath.round(league.awayAerialsWon / (double) league.matchesPlayed, 2) );
        data[8][4] = String.valueOf(MyMath.round((league.homeAerialsWon - league.awayAerialsWon) / (double) league.matchesPlayed, 2) );
        data[9][0] = params[9];
        data[9][1] = String.valueOf(MyMath.round((league.homeOffsides + league.awayOffsides) / (double) league.matchesPlayed, 2) );
        data[9][2] = String.valueOf(MyMath.round(league.homeOffsides / (double) league.matchesPlayed, 2) );
        data[9][3] = String.valueOf(MyMath.round(league.awayOffsides / (double) league.matchesPlayed, 2) );
        data[9][4] = String.valueOf(MyMath.round((league.homeOffsides - league.awayOffsides) / (double) league.matchesPlayed, 2) );
        data[10][0] = params[10];
        data[10][1] = String.valueOf(MyMath.round((league.homeClearances + league.awayClearances) / (double) league.matchesPlayed, 2) );
        data[10][2] = String.valueOf(MyMath.round(league.homeClearances / (double) league.matchesPlayed, 2) );
        data[10][3] = String.valueOf(MyMath.round(league.awayClearances / (double) league.matchesPlayed, 2) );
        data[10][4] = String.valueOf(MyMath.round((league.homeClearances - league.awayClearances) / (double) league.matchesPlayed, 2) );
        data[11][0] = params[11];
        data[11][1] = String.valueOf(MyMath.round((league.homeInterceptions + league.awayInterceptions) / (double) league.matchesPlayed, 2) );
        data[11][2] = String.valueOf(MyMath.round(league.homeInterceptions / (double) league.matchesPlayed, 2) );
        data[11][3] = String.valueOf(MyMath.round(league.awayInterceptions / (double) league.matchesPlayed, 2) );
        data[11][4] = String.valueOf(MyMath.round((league.homeInterceptions - league.awayInterceptions) / (double) league.matchesPlayed, 2) );
        data[12][0] = params[12];
        data[12][1] = String.valueOf(MyMath.round((league.homeDispossessed + league.awayDispossessed) / (double) league.matchesPlayed, 2) );
        data[12][2] = String.valueOf(MyMath.round(league.homeDispossessed / (double) league.matchesPlayed, 2) );
        data[12][3] = String.valueOf(MyMath.round(league.awayDispossessed / (double) league.matchesPlayed, 2) );
        data[12][4] = String.valueOf(MyMath.round((league.homeDispossessed - league.awayDispossessed) / (double) league.matchesPlayed, 2) );
        data[13][0] = params[13];
        data[13][1] = String.valueOf(MyMath.round((league.homeTackles + league.awayTackles) / (double) league.matchesPlayed, 2) );
        data[13][2] = String.valueOf(MyMath.round(league.homeTackles / (double) league.matchesPlayed, 2) );
        data[13][3] = String.valueOf(MyMath.round(league.awayTackles / (double) league.matchesPlayed, 2) );
        data[13][4] = String.valueOf(MyMath.round((league.homeTackles - league.awayTackles) / (double) league.matchesPlayed, 2) );
        data[14][0] = params[14];
        data[14][1] = String.valueOf(MyMath.round((league.homeKeyPasses + league.awayKeyPasses) / (double) league.matchesPlayed, 2) );
        data[14][2] = String.valueOf(MyMath.round(league.homeKeyPasses / (double) league.matchesPlayed, 2) );
        data[14][3] = String.valueOf(MyMath.round(league.awayKeyPasses / (double) league.matchesPlayed, 2) );
        data[14][4] = String.valueOf(MyMath.round((league.homeKeyPasses - league.awayKeyPasses) / (double) league.matchesPlayed, 2) );


        table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        tableRenderer = new Renderer(6);
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=1; r<colHeads.length; r++)
            table.getColumnModel().getColumn(r).setCellRenderer(tableRenderer);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        container.add(tablePanel);

        jtf = new JLabel("Таблица удалений, автоголов и пенальти в " + leagueName);
        jtf.setFont(new Font("", Font.BOLD, 18));
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jtf.setBorder(new EmptyBorder(10,10,0,10));
        container.add(jtf);

        colHeads = new String[]{"Показатель", "Общие", "Хозяева", "Гости", "Разница"};
        params = new String[]{"  Удаления всего", "  ЖК-->КК", "  Прямые КК",
                "  Автоголы противника", "  Пробитые пенальти", "  Забитые пенальти", "  Процент реализации пенальти"
        };

        data = new Object[params.length][colHeads.length];
        data[0][0] = params[0];
        data[0][1] = String.valueOf(league.homeRedCards + league.awayRedCards);
        data[0][2] = String.valueOf(league.homeRedCards);
        data[0][3] = String.valueOf(league.awayRedCards);
        data[0][4] = String.valueOf(league.homeRedCards - league.awayRedCards);
        data[1][0] = params[1];
        data[1][1] = String.valueOf(league.homeSecondYellowCards + league.awaySecondYellowCards);
        data[1][2] = String.valueOf(league.homeSecondYellowCards);
        data[1][3] = String.valueOf(league.awaySecondYellowCards);
        data[1][4] = String.valueOf(league.homeSecondYellowCards - league.awaySecondYellowCards);
        data[2][0] = params[2];
        data[2][1] = String.valueOf(league.homeDirectRedCards + league.awayDirectRedCards);
        data[2][2] = String.valueOf(league.homeDirectRedCards);
        data[2][3] = String.valueOf(league.awayDirectRedCards);
        data[2][4] = String.valueOf(league.homeDirectRedCards - league.awayDirectRedCards);
        data[3][0] = params[3];
        data[3][1] = String.valueOf(league.homeOGScored + league.awayOGScored );
        data[3][2] = String.valueOf(league.homeOGScored);
        data[3][3] = String.valueOf(league.awayOGScored);
        data[3][4] = String.valueOf(league.homeOGScored - league.awayOGScored );
        data[4][0] = params[4];
        data[4][1] = String.valueOf(league.homePen + league.awayPen);
        data[4][2] = String.valueOf(league.homePen);
        data[4][3] = String.valueOf(league.awayPen);
        data[4][4] = String.valueOf(league.homePen - league.awayPen);
        data[5][0] = params[5];
        data[5][1] = String.valueOf(league.homePenScored + league.awayPenScored);
        data[5][2] = String.valueOf(league.homePenScored);
        data[5][3] = String.valueOf(league.awayPenScored);
        data[5][4] = String.valueOf(league.homePenScored - league.awayPenScored);
        data[6][0] = params[6];
        data[6][1] = String.valueOf(MyMath.round(100 * (league.homePenScored + league.awayPenScored) / (double) (league.homePen + league.awayPen), 1));
        data[6][2] = String.valueOf(MyMath.round(100 * league.homePenScored / (double) league.homePen, 1));
        data[6][3] = String.valueOf(MyMath.round(100 * league.awayPenScored / (double) league.awayPen, 1));
        data[6][4] = "-";


        table = new JTable(data, colHeads);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.getTableHeader().setFont(fontLabel);
        table.setFont(fontLabel);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        tableRenderer = new Renderer(6);
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=1; r<colHeads.length; r++)
            table.getColumnModel().getColumn(r).setCellRenderer(tableRenderer);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        container.add(tablePanel);

        lastOrFullChooser.addActionListener(e ->
                lastOrFull = String.valueOf(lastOrFullChooser.getSelectedItem())
        );

        paramChooser.addActionListener(e -> {
            parameter = paramChooser.getSelectedItem().toString();
            tablesThread = new TablesThread(leagueName, parameter, season, lastOrFull, (PanelTablesByLeague) buttonShowInfo.getParent().getParent());
            tablesThread.start();
        });

        buttonShowBubble.addActionListener(e -> {
            if ( bubbleChartsPanel.getComponentCount() == 0){
                bubbleChartsPanel.add(getBubbleCharts());
                buttonShowBubble.setText("Скрыть графики перекрестных показателей");
            } else {
                bubbleChartsPanel.removeAll();
                buttonShowBubble.setText("Отобразить графики перекрестных показателей");
            }

        });



        scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );
        this.setCursor(Cursor.getDefaultCursor());
        return scrollPane;
    }

    public void setFilters(String league){
        buttonShowInfo.setEnabled(false);
        season = Settings.getCurrentSeasonInLeague(league);
        seasonCB.setSelectedItem("Сезон " + season);
        leagueChooser.setSelectedItem(league);
        buttonShowInfo.setEnabled(true);

    }

    public JPanel getBubbleCharts(){
        JPanel result = new JPanel(new GridLayout(0, 2, 0, 0));

        ArrayList<String> table1; // основная / домашняя таблица
        ArrayList<String> table2; // основная / выездная таблица
        int numberOfCharts = 4;
        int totalCharts = 4;

        ArrayList<String> graphicTitles = new ArrayList<>();

        if (settings.bubbleChartsHA){
            table1 = league.homeStatsTable;
            table2 = league.awayStatsTable;
            numberOfCharts *= 2;

            graphicTitles.add("Дома: Ударов/гол + Средний xG удара");
            graphicTitles.add("На выезде: Ударов/гол + Средний xG удара");
            graphicTitles.add("Дома: Ударов в среднем + Средний xG удара");
            graphicTitles.add("На выезде: Ударов в среднем + Средний xG удара");
            graphicTitles.add("Дома: Фолы + ЖК");
            graphicTitles.add("На выезде: Фолы + ЖК");
            graphicTitles.add("Дома: Ударов всего + Ударов от ворот");
            graphicTitles.add("На выезде: Ударов всего + Ударов от ворот");

        } else {
            table1 = league.overallStatsTable;
            table2 = league.overallStatsTable;

            graphicTitles.add("Весь сезон: Ударов/гол + Средний xG удара");
            graphicTitles.add("Весь сезон: Ударов в среднем + Средний xG удара");
            graphicTitles.add("Весь сезон: Фолы + ЖК");
            graphicTitles.add("Весь сезон: Ударов всего + Ударов от ворот");
        }




        int numberOfTeams = league.overallStatsTable.size();
        ArrayList<String> teamsList = new ArrayList<>();
        double [][][] data = new double[numberOfCharts][numberOfTeams][2];

        for (int i=0; i<numberOfTeams; i++){
            int index = 0;
            int matches1 = Integer.parseInt(table1.get(i).split("\\*")[1]);
            int matches2 = Integer.parseInt(table2.get(i).split("\\*")[1]);
            teamsList.add(table1.get(i).split("\\*")[0]);

            for(int j=0; j<totalCharts; j++){
                double par1_Team1 = 0;
                double par2_Team1 = 0;

                double par1_Team2 = 0;
                double par2_Team2 = 0;

                switch (j){
                    case 0:{ // Ударов на гол + Средний xG удара
                        par1_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[7].split("_")[0]) / Double.parseDouble(table1.get(i).split("\\*")[2].split("_")[0]),4);
                        par2_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[5].split("_")[0]) / Double.parseDouble(table1.get(i).split("\\*")[7].split("_")[0]),4);

                        par1_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[7].split("_")[0]) / Double.parseDouble(table2.get(i).split("\\*")[2].split("_")[0]),4);
                        par2_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[5].split("_")[0]) / Double.parseDouble(table2.get(i).split("\\*")[7].split("_")[0]),4);
                        break;
                    }
                    case 1:{// Ударов в среднем + Средний xG удара
                        par1_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[7].split("_")[0]) / matches1,4);
                        par2_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[5].split("_")[0]) / Double.parseDouble(table1.get(i).split("\\*")[7].split("_")[0]),4);

                        par1_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[7].split("_")[0]) / matches2,4);
                        par2_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[5].split("_")[0]) / Double.parseDouble(table2.get(i).split("\\*")[7].split("_")[0]),4);

                        break;
                    }
                    case 2:{// Фолы + ЖК
                        par1_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[15].split("_")[0]) / matches1,4);
                        par2_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[16].split("_")[0]) / matches1,4);

                        par1_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[15].split("_")[0]) / matches2,4);
                        par2_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[16].split("_")[0]) / matches2,4);

                        break;
                    }
                    case 3:{ // Ударов всего / Ударов от ворот
                        par1_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[7].split("_")[0]) / matches1,4);
                        par2_Team1 = MyMath.round(Double.parseDouble(table1.get(i).split("\\*")[22].split("_")[0]) / matches1,4);

                        par1_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[7].split("_")[0]) / matches2,4);
                        par2_Team2 = MyMath.round(Double.parseDouble(table2.get(i).split("\\*")[22].split("_")[0]) / matches2,4);

                        break;
                    }
                    /*case 4:{

                        break;
                    }
                    case 5:{

                        break;
                    }
                    case 6:{

                        break;
                    }
                    case 7:{

                        break;
                    }
                    case 8:{

                        break;
                    }
                    case 9:{

                        break;
                    }*/
                }


                if (settings.bubbleChartsHA){
                    data[index][i][0] = par1_Team1;
                    data[index][i][1] = par2_Team1;

                    data[index+1][i][0] = par1_Team2;
                    data[index+1][i][1] = par2_Team2;

                    index += 2;
                } else {
                    data[index][i][0] = par1_Team1;
                    data[index][i][1] = par2_Team1;

                    index ++;
                }


            }




        }

        for (int k=0; k<data.length; k++){
            XYDataset dataset = createDataset(k, data, teamsList);
            JFreeChart chart = ChartFactory.createScatterPlot(
                    graphicTitles.get(k),
                    graphicTitles.get(k).split(":")[1].split("\\+")[0].trim(),
                    graphicTitles.get(k).split(":")[1].split("\\+")[1].trim(),
                    dataset);

            XYPlot plot = (XYPlot)chart.getPlot();
            plot.setBackgroundPaint(new Color(230, 230, 230));
            XYItemRenderer renderer;
            renderer = plot.getRenderer();

            for (int i=0; i<numberOfTeams; i++){
                renderer.setSeriesShape(i, new Ellipse2D.Double(-5, -5, 10, 10));
            }

            XYItemLabelGenerator generator =
                    new StandardXYItemLabelGenerator("{0}");
            renderer.setBaseItemLabelGenerator(generator);
            renderer.setBaseItemLabelsVisible(true);

            chart.setBackgroundPaint(Color.white);
            chart.getLegend().setVisible(false);
            ChartPanel panel = new ChartPanel(chart);
            panel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
            result.add(panel);
        }



        return result;
    }

    private XYDataset createDataset(int index, double[][][] data, ArrayList<String> teamsList) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i=0; i<teamsList.size(); i++){
            XYSeries series = new XYSeries(teamsList.get(i));
            series.add(data[index][i][0], data[index][i][1]);
            dataset.addSeries(series);
        }

        return dataset;
    }

}

