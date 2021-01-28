package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class RefereesThread extends Thread{
    String path;
    String leagueName;
    String season;
    String lastOrFullSeason;
    JFrame tw;
    JFrame frameTable;
    JProgressBar jpb;
    int numberOfRefs;

    public RefereesThread(String leagueName, String season, String lastOrFullSeason){
        path = "database/";
        this.leagueName = leagueName;
        this.season = season;
        this.lastOrFullSeason = lastOrFullSeason;

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
        JPanel table = createTable(leagueName, season, lastOrFullSeason);

        frameTable = new JFrame("Параметры судей в " + leagueName + " в сезоне " + season);
        frameTable.setLayout(new BorderLayout());
        Settings settings = Settings.getSettingsFromFile();
        frameTable.setSize(Integer.parseInt(settings.getWindowResolution().split("x")[0]), Integer.parseInt(settings.getWindowResolution().split("x")[1]));
        frameTable.setLocation(0, 0);

        JScrollPane sp = new JScrollPane(table);
        sp.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );

        frameTable.add(sp);
        if (settings.windowsOnTop)
            frameTable.setAlwaysOnTop(true);
        frameTable.setVisible(true);
        frameTable.pack();

        tw.setVisible(false);

    }

    public JPanel createTable(String leagueName, String season, String lastOrFullSeason){
        JPanel result = new JPanel(new VerticalLayout());
        JPanel content = new JPanel(new BorderLayout());

        int ttt = 0;
        String refString = "";

        try {
            File fileDir = new File(path + season + "/" + leagueName + "/Referees/Referees.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                refString += str + "*";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        String[] directoryList = refString.split("\\*");

        if (!leagueName.contains("Выберите") && directoryList.length > 0){

            ArrayList<ArrayList<Double>> data = new ArrayList<>();
            ArrayList<String> listOfRefs = new ArrayList<>();

            for (int i=0; i<directoryList.length; i++){
                if (!directoryList[i].contains(".txt")){
                    ArrayList<Double> record = new ArrayList<>();
                    Selector selector = new Selector();
                    selector.getRefListOfMatches(leagueName, directoryList[i].replace(".xml", ""), season, lastOrFullSeason);

                    if (selector.listOfMatches.size() > 0){
                        listOfRefs.add(directoryList[i].replace(".xml", ""));
                        double matches = (double) selector.listOfMatches.size();

                        if (Settings.isWhoScoredLeague(leagueName)){
                            record.add(matches);
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(0).get(1)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(0).get(2)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(0).get(3)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(5).get(1)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(5).get(2)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(5).get(3)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(9).get(1)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(9).get(2)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(9).get(3)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(10).get(1)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(10).get(2)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(10).get(3)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(11).get(1)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(11).get(2)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(11).get(3)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(12).get(1)), 2));
                        } else {
                            record.add(matches);
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(0).get(1)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(0).get(2)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(0).get(3)) / matches, 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(5).get(1)) / selector.refNumberOfMatchesWithParam[5], 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(5).get(2)) / selector.refNumberOfMatchesWithParam[5], 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(5).get(3)) / selector.refNumberOfMatchesWithParam[5], 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(9).get(1)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(9).get(2)), 2));
                            record.add(MyMath.round(Double.parseDouble(selector.refList.get(9).get(3)), 2));
                        }



                        data.add(record);
                        jpb.setValue((int) (100*(i+1) / (double) (directoryList.length-1)));
                    }
                }
            }
            numberOfRefs = listOfRefs.size();

            Font font = new Font("Arial", Font.BOLD, 15);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

            String[] rankHeader = {"  "};
            Object[][] ranks = new Object[numberOfRefs][1];
            for (int i=0; i< numberOfRefs;i++)
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
            tableRanks.setBackground(new Color(238, 238, 238));
            JPanel tablePanelRanks = new JPanel();
            tablePanelRanks.setLayout(new BorderLayout());
            tablePanelRanks.setLayout(new BorderLayout());
            tablePanelRanks.add(tableRanks, BorderLayout.CENTER);
            tablePanelRanks.add(tableRanks.getTableHeader(), BorderLayout.NORTH);

            content.add(tablePanelRanks, BorderLayout.WEST);

            String[] colHeads;
            if (Settings.isWhoScoredLeague(leagueName)){
                colHeads = new String[]{"Арбитр", "М", "ЖК", "ЖК_Х", "ЖК_Г", "Ф", "Ф_Х", "Ф_Х",
                        "<html>&sigma; ЖК</html>", "<html>&sigma; ЖК_Х</html>", "<html>&sigma; ЖК_Г</html>",
                        "<html>&sigma; Ф</html>", "<html>&sigma; Ф_Х</html>", "<html>&sigma; Ф_Г</html>",
                        "R1", "R2", "R3", "R4"
                };
            } else {
                colHeads = new String[]{"Арбитр", "М", "ЖК", "ЖК_Х", "ЖК_Г", "Ф", "Ф_Х", "Ф_Х",
                        "<html>&sigma; ЖК</html>", "<html>&sigma; ЖК_Х</html>", "<html>&sigma; ЖК_Г</html>",
                };
            }

            Object[][] dataForTable = new Object[numberOfRefs][colHeads.length];
            double[][] minMaxArray = new double[2][colHeads.length];

            for (int j=1; j<colHeads.length; j++){
                for (int i=0; i<numberOfRefs; i++){
                    minMaxArray[0][j] = 1000000;
                    minMaxArray[1][j] = -1000000;
                }
            }

            for (int i=0; i< numberOfRefs; i++){
                dataForTable[i][0] = listOfRefs.get(i);
                dataForTable[i][1] = Math.round(data.get(i).get(0));
                for (int j=2; j<colHeads.length; j++)
                    dataForTable[i][j] = data.get(i).get(j-1);
            }

            for (int j=1; j<colHeads.length; j++){
                for (int i=0; i<numberOfRefs; i++){
                    if (Double.parseDouble(String.valueOf(dataForTable[i][j])) <= minMaxArray[0][j])
                        minMaxArray[0][j] = Double.parseDouble(String.valueOf(dataForTable[i][j]));
                    if (Double.parseDouble(String.valueOf(dataForTable[i][j])) >= minMaxArray[1][j])
                        minMaxArray[1][j] = Double.parseDouble(String.valueOf(dataForTable[i][j]));
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
            Renderer renderer = new Renderer(minMaxArray, 5);

            for (int r=0; r<colHeads.length; r++){
                table.getColumnModel().getColumn(r).setCellRenderer(renderer);
                table.getColumnModel().getColumn(r).setPreferredWidth(60);
            }
            table.getColumnModel().getColumn(0).setPreferredWidth(120);
            RowSorter<TableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
            table.setBackground(new Color(238,238, 238));

            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BorderLayout());
            tablePanel.add(table, BorderLayout.CENTER);
            tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

            content.add(tablePanel);
            result.add(content);


            JPanel panelLegend = new JPanel(new BorderLayout());
            panelLegend.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel header = new JLabel("Условные обозначения:");
            header.setFont(new Font("", Font.BOLD, 18));
            panelLegend.add(header, BorderLayout.NORTH);

            JPanel panelText = new JPanel(new GridLayout(1, 0, 5, 5));

            JLabel textArea1 = new JLabel("");
            textArea1.setFont(new Font("", Font.BOLD, 15));
            textArea1.setBackground(new Color(238, 238, 238));
            //textArea.setEnabled(false);

            String text1 = "<html>М - матчей; <br>" +
                    "ЖК    - среднее число ЖК за матч <br>" +
                    "ЖК_Х  - среднее число ЖК хозяев за матч <br>" +
                    "ЖК_Г  - среднее число ЖК гостей за матч <br>" +
                    "Ф     - среднее число фолов за матч <br>" +
                    "Ф_Х   - среднее число фолов хозяев за матч <br>" +
                    "Ф_Г   - среднее число фолов гостей за матч <br>" +
                    "&sigma; ЖК - СКО желтых карточек <br>" +
                    "&sigma; ЖК_Х - СКО желтых карточек хозяев <br></html>";

            textArea1.setText(text1);
            panelText.add(textArea1);

            JLabel textArea2 = new JLabel("");
            textArea2.setFont(new Font("", Font.BOLD, 15));
            textArea2.setBackground(new Color(238, 238, 238));
            //textArea.setEnabled(false);

            String text2 = "<html>&sigma; ЖК_Г - СКО желтых карточек гостей <br>" +
                    "&sigma; Ф - СКО фолов <br>" +
                    "&sigma; Ф_Х - СКО фолов хозяев <br>" +
                    "&sigma; Ф_Г - СКО фолов гостей <br>" +
                    "R1 - коэффициент корреляции фолов и ЖК<br>" +
                    "R2 - коэффициент корреляции фолов и ЖК у хозяев<br>" +
                    "R3 - коэффициент корреляции фолов и ЖК у гостей<br>" +
                    "R4 - коэффициент корреляции форы фолов и форы ЖК<br></html>";

            textArea2.setText(text2);
            panelText.add(textArea2);

            panelLegend.add(panelText);

            result.add(panelLegend);

        } else {
            result.setLayout(null);
            result.setPreferredSize(new Dimension(700, 730));

            final JLabel label = new JLabel("Не выбран чемпионат.");
            if (directoryList.length == 0){
                label.setText("В сезоне " + season + " команды из " + leagueName + " не провели ни одной игры.");
            }

            label.setLocation(10, 0);
            label.setSize(new Dimension(700, 25));
            Font fontLabel = new Font("Arial", Font.BOLD, 15);
            label.setFont(fontLabel);
            result.add(label);

            /*JScrollPane scrollPane = new JScrollPane(result);
            scrollPane.setPreferredSize(new Dimension((int) (0.995 * pt.WIDTH), (int) (0.87 * pt.HEIGHT)));
            scrollPane.setLocation(5, 50);
            scrollPane.setVerticalScrollBar( new JScrollBar() {
                public int getUnitIncrement( int direction ) {
                    return 50;
                }
            } );*/
        }



        return result;
    }

}

