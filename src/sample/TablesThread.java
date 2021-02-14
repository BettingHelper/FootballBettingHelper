package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class TablesThread extends Thread{
    String path;
    String leagueName;
    String parameter;
    String seasonString;
    JFrame tw;
    PanelTablesByLeague pt;
    String lastOrFull;
    JProgressBar jpb;
    int numberOfTeams;

    public TablesThread(String leagueName, final String parameter, String seasonString, String lastOrFull, PanelTablesByLeague pt){
        path = "database/";
        this.leagueName = leagueName;
        this.parameter = parameter;
        this.seasonString = seasonString;
        this.lastOrFull = lastOrFull;
        this.pt = pt;
        numberOfTeams = Settings.getNumberOfTeamsInLeague(leagueName, seasonString);

        tw = new JFrame("Внимание!");
        tw.setResizable(false);
        tw.setLayout(new BorderLayout());
        tw.setSize(500, 200);
        tw.setLocation(200, 200);

        JLabel label = new JLabel("Подождите, идет расчет таблицы");
        label.setFont(new Font("", Font.BOLD, 20));
        tw.add(label, BorderLayout.NORTH);

        jpb = new JProgressBar(0, 100);
        jpb = new JProgressBar(0, 100);
        jpb.setPreferredSize(new Dimension(600, 50));
        jpb.setStringPainted(true);
        tw.add(jpb, BorderLayout.SOUTH);

        tw.setVisible(true);
    }

    @Override
    public void run(){
        JPanel arr = createTable(pt.leagueName, pt.parameter, pt.season, pt.lastOrFull);
        if (pt.panelWithTablesByLeague.getComponentCount() > 1) {
            pt.panelWithTablesByLeague.remove(1);
        }

        pt.panelWithTablesByLeague.add(arr);
        pt.panelWithTablesByLeague.revalidate();
        pt.revalidate();
        tw.setVisible(false);

    }

    public JPanel createTable(String leagueName, final String parameter, String season, String lastOrFull){
        JPanel result = new JPanel(new BorderLayout());

        if (parameter.contains("Выберите")){
            JPanel r = new JPanel();
            JLabel l = new JLabel("Не выбран показатель");
            l.setFont(new Font("", Font.BOLD,15));
            r.setAlignmentX(Component.CENTER_ALIGNMENT);
            r.add(l);
            return r;
        }

        String[] directoryList = new JFileChooser(path + season + "/leagues").getCurrentDirectory().list();

        if (!leagueName.contains("Выберите") && directoryList.length > 0){
            if (!lastOrFull.contains("Весь")){
                if (!(pt.lastCalculatedLeague.equals(leagueName) && pt.lastCalculatedSeason.equals(season) && pt.lastCalculatedLastOrFull.equals(lastOrFull))){
                    pt.league.resetTables(season, leagueName, lastOrFull, jpb);
                }
            }

            int indexOfParameter = 0;
            switch (parameter){
                case "Голы":{
                    indexOfParameter = 2;
                    break;
                }
                case "Голы в 1-ом тайме":{
                    indexOfParameter = 3;
                    break;
                }
                case "Голы во 2-ом тайме":{
                    indexOfParameter = 4;
                    break;
                }
                case "xG":{
                    indexOfParameter = 5;
                    break;
                }
                case "Владение":{
                    indexOfParameter = 6;
                    break;
                }
                case "Ударов всего":{
                    indexOfParameter = 7;
                    break;
                }
                case "Удары в 1-ом тайме":{
                    indexOfParameter = 25;
                    break;
                }
                case "Удары во 2-ом тайме":{
                    indexOfParameter = 26;
                    break;
                }
                case "Удары в створ":{
                    indexOfParameter = 8;
                    break;
                }
                case "Удары в створ в 1-ом тайме":{
                    indexOfParameter = 27;
                    break;
                }
                case "Удары в створ во 2-ом тайме":{
                    indexOfParameter = 28;
                    break;
                }
                case "Удары мимо":{
                    indexOfParameter = 9;
                    break;
                }
                case "Угловые":{
                    indexOfParameter = 10;
                    break;
                }
                case "Угловые в 1-ом тайме":{
                    indexOfParameter = 11;
                    break;
                }
                case "Угловые во 2-ом тайме":{
                    indexOfParameter = 12;
                    break;
                }
                case "Офсайды":{
                    indexOfParameter = 13;
                    break;
                }
                case "Офсайды в 1-ом тайме":{
                    indexOfParameter = 29;
                    break;
                }
                case "Офсайды во 2-ом тайме":{
                    indexOfParameter = 30;
                    break;
                }
                case "Блокировано ударов":{
                    indexOfParameter = 14;
                    break;
                }
                case "Фолы":{
                    indexOfParameter = 15;
                    break;
                }
                case "Фолы в 1-ом тайме":{
                    indexOfParameter = 31;
                    break;
                }
                case "Фолы во 2-ом тайме":{
                    indexOfParameter = 32;
                    break;
                }
                case "Желтые карточки":{
                    indexOfParameter = 16;
                    break;
                }
                case "Желтые карточки в 1-ом тайме":{
                    indexOfParameter = 17;
                    break;
                }
                case "Желтые карточки во 2-ом тайме":{
                    indexOfParameter = 18;
                    break;
                }
                case "Вброс аутов":{
                    indexOfParameter = 19;
                    break;
                }
                case "Вброс аутов в 1-ом тайме":{
                    indexOfParameter = 20;
                    break;
                }
                case "Вброс аутов во 2-ом тайме":{
                    indexOfParameter = 21;
                    break;
                }
                case "Удары от ворот":{
                    indexOfParameter = 22;
                    break;
                }
                case "Удары от ворот в 1-ом тайме":{
                    indexOfParameter = 23;
                    break;
                }
                case "Удары от ворот во 2-ом тайме":{
                    indexOfParameter = 24;
                    break;
                }
            }

            ArrayList<ArrayList<Double>> data = new ArrayList<>();
            ArrayList<String> listOfTeams = new ArrayList<>();
//            League league = League.getLeagueFromFile(leagueName, season);

            for (int i=0; i<pt.league.overallStatsTable.size(); i++){
                listOfTeams.add(pt.league.overallStatsTable.get(i).split("\\*")[0]);
                int matchesAll = Integer.parseInt(pt.league.overallStatsTable.get(i).split("\\*")[1]);
                int matchesHome = Integer.parseInt(pt.league.homeStatsTable.get(i).split("\\*")[1]);
                int matchesAway = Integer.parseInt(pt.league.awayStatsTable.get(i).split("\\*")[1]);
                ArrayList<Double> record = new ArrayList<>();

                if (matchesAll > 0){
                    record.add(MyMath.round(Double.parseDouble(pt.league.overallStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[0]) / (double) matchesAll,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.overallStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[1]) / (double) matchesAll,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.overallStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[2]) / (double) matchesAll,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.overallStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[3]) / (double) matchesAll,2));
                } else {
                    record.add(0.0);
                    record.add(0.0);
                    record.add(0.0);
                    record.add(0.0);
                }
                if (matchesHome > 0){
                    record.add(MyMath.round(Double.parseDouble(pt.league.homeStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[0]) / (double) matchesHome,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.homeStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[1]) / (double) matchesHome,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.homeStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[2]) / (double) matchesHome,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.homeStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[3]) / (double) matchesHome,2));
                } else {
                    record.add(0.0);
                    record.add(0.0);
                    record.add(0.0);
                    record.add(0.0);
                }
                if (matchesAway > 0){
                    record.add(MyMath.round(Double.parseDouble(pt.league.awayStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[0]) / (double) matchesAway,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.awayStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[1]) / (double) matchesAway,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.awayStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[2]) / (double) matchesAway,2));
                    record.add(MyMath.round(Double.parseDouble(pt.league.awayStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[3]) / (double) matchesAway,2));
                } else {
                    record.add(0.0);
                    record.add(0.0);
                    record.add(0.0);
                    record.add(0.0);
                }
                data.add(record);

            }

            Font font = new Font("Arial", Font.BOLD, 15);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

            String[] colHeaders = {"", "Общая" , "Дома", "На выезде"};
            Object[][] emptyData = new Object[0][0];
            JTable tableHeaders = new JTable(emptyData, colHeaders);
            tableHeaders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableHeaders.setEnabled(false);
            tableHeaders.getTableHeader().setFont(font);
            tableHeaders.setFont(font);
            tableHeaders.setRowHeight(25);
            for (int r=0; r<colHeaders.length; r++){
                tableHeaders.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);
                tableHeaders.getColumnModel().getColumn(r).setPreferredWidth(360);
            }
            tableHeaders.getColumnModel().getColumn(0).setPreferredWidth(185);

            Dimension tableSize = tableHeaders.getPreferredSize();

            JPanel tablePanelHeaders = new JPanel();
            tablePanelHeaders.setLayout(new BorderLayout());
            tablePanelHeaders.add(tableHeaders, BorderLayout.CENTER);
            tablePanelHeaders.add(tableHeaders.getTableHeader(), BorderLayout.NORTH);
            result.add(tablePanelHeaders, BorderLayout.NORTH);

            String[] rankHeader = {"  "};
            Object[][] ranks = new Object[numberOfTeams][1];
            for (int i=0; i<numberOfTeams;i++)
                ranks[i][0] = i+1;

            JTable tableRanks = new JTable(ranks, rankHeader);
            tableRanks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableRanks.setEnabled(false);
            tableRanks.getTableHeader().setFont(font);
            tableRanks.setFont(font);
            tableRanks.getColumnModel().getColumn(0).setPreferredWidth(35);
            tableRanks.setRowHeight(25);
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableRanks.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            JPanel tablePanelRanks = new JPanel();
            tablePanelRanks.setLayout(new BorderLayout());
            tablePanelRanks.setLayout(new BorderLayout());
            tablePanelRanks.add(tableRanks, BorderLayout.CENTER);
            tablePanelRanks.add(tableRanks.getTableHeader(), BorderLayout.NORTH);

            result.add(tablePanelRanks, BorderLayout.WEST);

            String[] colHeads = {"Команда" , "Свои", "Чужие", "Разница", "Тотал", "Свои", "Чужие", "Разница", "Тотал", "Свои", "Чужие", "Разница", "Тотал"};
            Object[][] dataForTable = new Object[numberOfTeams][colHeads.length];
            double[][] minMaxArray = new double[2][colHeads.length];
            for (int i=0; i<numberOfTeams; i++){
                dataForTable[i][0] = listOfTeams.get(i);
                for (int j=1; j<colHeads.length; j++)
                    dataForTable[i][j] = data.get(i).get(j-1);
            }

            for (int j=1; j<colHeads.length; j++){
                for (int i=0; i<numberOfTeams; i++){
                    minMaxArray[0][j] = 1000000;
                    minMaxArray[1][j] = -1000000;
                }
            }

            for (int j=1; j<colHeads.length; j++){
                for (int i=0; i<numberOfTeams; i++){
                    if ((double) dataForTable[i][j] <= minMaxArray[0][j])
                        minMaxArray[0][j] = (double) dataForTable[i][j];
                    if ((double) dataForTable[i][j] >= minMaxArray[1][j])
                        minMaxArray[1][j] = (double) dataForTable[i][j];
            }
            }

            TableModel model = new DefaultTableModel(dataForTable, colHeads) {
                public Class getColumnClass(int column) {
                    Class returnValue;
                    if ((column >= 0) && (column < getColumnCount())) {
                        returnValue = getValueAt(0, column).getClass();
                    }  else {
                        returnValue = Object.class;
                    }
                    return returnValue;
                }
            };
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setEnabled(false);
            table.getTableHeader().setFont(font);
            table.setFont(font);
            table.setRowHeight(25);
            Renderer renderer = new Renderer(minMaxArray, 3);
            for (int r=0; r<colHeads.length; r++){
                table.getColumnModel().getColumn(r).setCellRenderer(renderer);
                table.getColumnModel().getColumn(r).setPreferredWidth(90);
            }
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            RowSorter<TableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BorderLayout());
            tablePanel.add(table, BorderLayout.CENTER);
            tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);


            result.add(tablePanel);

            pt.lastCalculatedLeague = leagueName;
            pt.lastCalculatedSeason = season;
            pt.lastCalculatedLastOrFull = lastOrFull;
        } else {
            result.setLayout(new VerticalLayout());

            final JLabel label = new JLabel("Не выбран чемпионат.");
            if (directoryList.length == 0){
                label.setText("В сезоне " + season + " команды из " + leagueName + " не провели ни одной игры.");
            }

            label.setLocation(10, 0);
            Font fontLabel = new Font("Arial", Font.BOLD, 15);
            label.setFont(fontLabel);
            result.add(label);

        }
        return result;
    }
}

