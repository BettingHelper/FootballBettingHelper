package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class TableMaker {
    public TableMaker(){
    }

    public static JPanel getTableGoals(ArrayList<Match> list){
        String[] colHeads = {"Ставка", "Заход и %"};
        int oz = 0;
        int tb25 = 0;
        int bothTimesWithGoals = 0;
        int firstMoreSecond = 0;
        int secondMoreFirst = 0;
        int total23 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeScore + list.get(i).awayScore > 2.5)
                tb25++;
            if ((list.get(i).homeScore>0) && (list.get(i).awayScore > 0))
                oz++;
            if ((list.get(i).homeScore1T + list.get(i).awayScore1T >0) && (list.get(i).homeScore2T + list.get(i).awayScore2T >0))
                bothTimesWithGoals++;
            if (list.get(i).homeScore1T + list.get(i).awayScore1T > list.get(i).homeScore2T + list.get(i).awayScore2T )
                firstMoreSecond++;
            if (list.get(i).homeScore1T + list.get(i).awayScore1T < list.get(i).homeScore2T + list.get(i).awayScore2T )
                secondMoreFirst++;
            if ((list.get(i).homeScore + list.get(i).awayScore == 2) || (list.get(i).homeScore + list.get(i).awayScore == 3))
                total23++;
        }
        Object[][] data = {
                {"Обе забьют" , oz + "/" + list.size() + " (" + MyMath.round(oz / (double) list.size() * 100, 1) + ")"},
                {"Обе забьют - нет" , (list.size() - oz) + "/" + list.size() + " (" + MyMath.round((list.size() - oz) / (double) list.size() * 100, 1) + ")"},
                {"ТБ(2.5)" , tb25 + "/" + list.size() + " (" + MyMath.round(tb25 / (double) list.size() * 100, 1) + ")"},
                {"ТМ(2.5)" , (list.size() - tb25) + "/" + list.size() + " (" + MyMath.round((list.size() - tb25) / (double) list.size() * 100, 1) + ")"},
                {"Гол в обоих таймах" , bothTimesWithGoals + "/" + list.size() + " (" + MyMath.round(bothTimesWithGoals / (double) list.size() * 100, 1) + ")"},
                {"1Т > 2Т" , firstMoreSecond + "/" + list.size() + " (" + MyMath.round(firstMoreSecond / (double) list.size() * 100, 1) + ")"},
                {"2Т > 1Т" , secondMoreFirst + "/" + list.size() + " (" + MyMath.round(secondMoreFirst / (double) list.size() * 100, 1) + ")"},
                {"Тотал 2-3" , total23 + "/" + list.size() + " (" + MyMath.round(total23 / (double) list.size() * 100, 1) + ")"},
        };

        JTable tableGoals = new JTable(data, colHeads);
        Font font = new Font("Arial", Font.BOLD, 15);
        tableGoals.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableGoals.setEnabled(false);
        tableGoals.getTableHeader().setFont(font);
        tableGoals.setFont(font);
        tableGoals.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableGoals.setRowHeight(25);
        tableGoals.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
        tableGoals.getColumnModel().getColumn(0).setCellRenderer(centerRenderer2);
        tableGoals.getColumnModel().getColumn(1).setCellRenderer(centerRenderer2);
        tableGoals.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableGoals, BorderLayout.CENTER);
        tablePanel.add(tableGoals.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableGoals1T(ArrayList<Match> list){
        String[] colHeads = {"Ставка", "Заход и %"};
        int oz = 0;
        int tb15 = 0;
        int tm15 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeScore1T + list.get(i).awayScore1T > 1.5)
                tb15++;
            if (list.get(i).homeScore1T + list.get(i).awayScore1T < 1.5)
                tm15++;
            if ((list.get(i).homeScore1T>0) && (list.get(i).awayScore1T > 0))
                oz++;
        }
        Object[][] data = {
                {"Обе забьют" , oz + "/" + list.size() + " (" + MyMath.round(oz / (double) list.size() * 100, 1) + ")"},
                {"ТБ(1.5)"    , tb15 + "/" + list.size() + " (" + MyMath.round(tb15 / (double) list.size() * 100, 1) + ")"},
                {"ТМ(1.5)"    , tm15 + "/" + list.size() + " (" + MyMath.round(tm15 / (double) list.size() * 100, 1) + ")"},
        };

        JTable tableGoals = new JTable(data, colHeads);
        Font font = new Font("Arial", Font.BOLD, 15);
        tableGoals.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableGoals.setEnabled(false);
        tableGoals.getTableHeader().setFont(font);
        tableGoals.setFont(font);
        tableGoals.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableGoals.setRowHeight(25);
        tableGoals.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
        tableGoals.getColumnModel().getColumn(0).setCellRenderer(centerRenderer2);
        tableGoals.getColumnModel().getColumn(1).setCellRenderer(centerRenderer2);
        tableGoals.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableGoals, BorderLayout.CENTER);
        tablePanel.add(tableGoals.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableGoals2T(ArrayList<Match> list){
        String[] colHeads = {"Ставка", "Заход и %"};
        int oz = 0;
        int tb15 = 0;
        int tm15 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeScore2T + list.get(i).awayScore2T > 1.5)
                tb15++;
            if (list.get(i).homeScore2T + list.get(i).awayScore2T < 1.5)
                tm15++;
            if ((list.get(i).homeScore2T>0) && (list.get(i).awayScore2T > 0))
                oz++;
        }
        Object[][] data = {
                {"Обе забьют" , oz + "/" + list.size() + " (" + MyMath.round(oz / (double) list.size() * 100, 1) + ")"},
                {"ТБ(1.5)"    , tb15 + "/" + list.size() + " (" + MyMath.round(tb15 / (double) list.size() * 100, 1) + ")"},
                {"ТМ(1.5)"    , tm15 + "/" + list.size() + " (" + MyMath.round(tm15 / (double) list.size() * 100, 1) + ")"},
        };


        JTable tableGoals = new JTable(data, colHeads);
        Font font = new Font("Arial", Font.BOLD, 15);
        tableGoals.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableGoals.setEnabled(false);
        tableGoals.getTableHeader().setFont(font);
        tableGoals.setFont(font);
        tableGoals.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableGoals.setRowHeight(25);
        tableGoals.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
        tableGoals.getColumnModel().getColumn(0).setCellRenderer(centerRenderer2);
        tableGoals.getColumnModel().getColumn(1).setCellRenderer(centerRenderer2);
        tableGoals.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableGoals, BorderLayout.CENTER);
        tablePanel.add(tableGoals.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableShots(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageShots = Double.parseDouble(selector.pList.get(7).get(1));
        double opAverageShots = Double.parseDouble(selector.pList.get(7).get(2));


        /*if (selector.numberOfMatchesWithParam[7] > 0 ){
            selfAverageShots = selfAverageShots / selector.listOfMatches.size() * selector.numberOfMatchesWithParam[7];
            opAverageShots   = opAverageShots   / selector.listOfMatches.size() * selector.numberOfMatchesWithParam[7];
        }*/
        double totalAverageShots = selfAverageShots + opAverageShots;

        if (MyMath.round(totalAverageShots, 0) > totalAverageShots)
            totalAverageShots = MyMath.round(totalAverageShots, 0) - 0.5;
        else
            totalAverageShots = MyMath.round(totalAverageShots, 0) + 0.5;
        if (MyMath.round(selfAverageShots, 0) > selfAverageShots)
            selfAverageShots = MyMath.round(selfAverageShots, 0) - 0.5;
        else
            selfAverageShots = MyMath.round(selfAverageShots, 0) + 0.5;
        if (MyMath.round(opAverageShots, 0) > opAverageShots)
            opAverageShots = MyMath.round(opAverageShots, 0) - 0.5;
        else
            opAverageShots = MyMath.round(opAverageShots, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeShots + list.get(i).awayShots > 0){
                if (list.get(i).homeShots + list.get(i).awayShots > (totalAverageShots-1))
                    totalMinus1++;
                if (list.get(i).homeShots + list.get(i).awayShots > totalAverageShots)
                    totalSred++;
                if (list.get(i).homeShots + list.get(i).awayShots > (totalAverageShots+1))
                    totalPlus1++;

                if ((list.get(i).homeShots > (selfAverageShots+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShots > (selfAverageShots+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeShots > (selfAverageShots)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShots > (selfAverageShots)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeShots > (selfAverageShots-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShots > (selfAverageShots-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeShots > (opAverageShots+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShots > (opAverageShots+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeShots > (opAverageShots)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShots > (opAverageShots)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeShots > (opAverageShots-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShots > (opAverageShots-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeShots > list.get(i).awayShots&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShots > list.get(i).homeShots))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeShots >= list.get(i).awayShots&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShots >= list.get(i).homeShots))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;

                number++;
            }

        }

        Object[] colHeads;
        Object[][] data;

        if (number > 0){
            String t85 = "ТБ(" + (totalAverageShots - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageShots - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageShots + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageShots + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageShots + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageShots + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageShots + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" +number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageShots;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageShots - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number- selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageShots + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageShots;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageShots - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };


        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableShotsOnTargets(String teamName, Selector selector){
        double tb75 = 0.0;
        double tb85 = 0.0;
        double tb95 = 0.0;
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageUSV = Double.parseDouble(selector.pList.get(8).get(1));
        double opAverageUSV = Double.parseDouble(selector.pList.get(8).get(2));

        /*if (selector.numberOfMatchesWithParam[8] > 0 ){
            selfAverageUSV = selfAverageUSV / selector.listOfMatches.size() * selector.numberOfMatchesWithParam[8];
            opAverageUSV   = opAverageUSV   / selector.listOfMatches.size() * selector.numberOfMatchesWithParam[8];
        }*/

        if (MyMath.round(selfAverageUSV, 0) > selfAverageUSV)
            selfAverageUSV = MyMath.round(selfAverageUSV, 0) - 0.5;
        else
            selfAverageUSV = MyMath.round(selfAverageUSV, 0) + 0.5;
        if (MyMath.round(opAverageUSV, 0) > opAverageUSV)
            opAverageUSV = MyMath.round(opAverageUSV, 0) - 0.5;
        else
            opAverageUSV = MyMath.round(opAverageUSV, 0) + 0.5;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeShotsOnTarget + list.get(i).awayShotsOnTarget > 0){
                if (list.get(i).homeShotsOnTarget + list.get(i).awayShotsOnTarget > 7.5)
                    tb75++;
                if (list.get(i).homeShotsOnTarget + list.get(i).awayShotsOnTarget > 8.5)
                    tb85++;
                if (list.get(i).homeShotsOnTarget + list.get(i).awayShotsOnTarget > 9.5)
                    tb95++;

                if ((list.get(i).homeShotsOnTarget > (selfAverageUSV+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget > (selfAverageUSV+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeShotsOnTarget > (selfAverageUSV)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget > (selfAverageUSV)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeShotsOnTarget > (selfAverageUSV-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget > (selfAverageUSV-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeShotsOnTarget > (opAverageUSV+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget > (opAverageUSV+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeShotsOnTarget > (opAverageUSV)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget > (opAverageUSV)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeShotsOnTarget > (opAverageUSV-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget > (opAverageUSV-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeShotsOnTarget > list.get(i).awayShotsOnTarget&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget > list.get(i).homeShotsOnTarget))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeShotsOnTarget >= list.get(i).awayShotsOnTarget&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget >= list.get(i).homeShotsOnTarget))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;

                number++;
            }
        }

        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t75 = "ТБ(7.5)";
            String t75s = (int) (tb75) + "/" + number + " (" + MyMath.round(tb75 / number * 100, 1) + ")";
            if (tb75/number < 0.5){
                t75 = "ТМ(7.5)";
                t75s = (int) (number - tb75) + "/" + number + " (" + MyMath.round((number - tb75) / number * 100, 1) + ")";
            }
            String t85 = "ТБ(8.5)";
            String t85s = (int) (tb85) + "/" + number + " (" + MyMath.round(tb85 / number * 100, 1) + ")";
            if (tb85/number < 0.5){
                t85 = "ТМ(8.5)";
                t85s = (int) (number - tb85) + "/" + number + " (" + MyMath.round((number - tb85) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(9.5)";
            String t95s = (int) (tb95) + "/" + number + " (" + MyMath.round(tb95 / number * 100, 1) + ")";
            if (tb95/number < 0.5){
                t95 = "ТМ(9.5)";
                t95s = (int) (number - tb95) + "/" + number + " (" + MyMath.round((number - tb95) / number * 100, 1) + ")";
            }

            double total = selfAverageUSV + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageUSV;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageUSV - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageUSV + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageUSV;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageUSV - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t75, t75s},
                    {t85, t85s},
                    {t95, t95s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        }  else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableShotsOnTargets1T(String teamName, Selector selector){
        double tb35 = 0.0;
        double tb45 = 0.0;
        double tb55 = 0.0;
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageUSV = Double.parseDouble(selector.pList.get(38).get(1));
        double opAverageUSV = Double.parseDouble(selector.pList.get(38).get(2))  ;
        if (selector.numberOfMatchesWithParam[38] > 0 ){
            selfAverageUSV = selfAverageUSV / (double) selector.numberOfMatchesWithParam[38];
            opAverageUSV   = opAverageUSV   / (double) selector.numberOfMatchesWithParam[38];
        }

        if (MyMath.round(selfAverageUSV, 0) > selfAverageUSV)
            selfAverageUSV = MyMath.round(selfAverageUSV, 0) - 0.5;
        else
            selfAverageUSV = MyMath.round(selfAverageUSV, 0) + 0.5;
        if (MyMath.round(opAverageUSV, 0) > opAverageUSV)
            opAverageUSV = MyMath.round(opAverageUSV, 0) - 0.5;
        else
            opAverageUSV = MyMath.round(opAverageUSV, 0) + 0.5;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeShotsOnTarget1T + list.get(i).awayShotsOnTarget1T > 0){
                if (list.get(i).homeShotsOnTarget1T + list.get(i).awayShotsOnTarget1T > 3.5)
                    tb35++;
                if (list.get(i).homeShotsOnTarget1T + list.get(i).awayShotsOnTarget1T > 4.5)
                    tb45++;
                if (list.get(i).homeShotsOnTarget1T + list.get(i).awayShotsOnTarget1T > 5.5)
                    tb55++;

                if ((list.get(i).homeShotsOnTarget1T > (selfAverageUSV+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget1T > (selfAverageUSV+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeShotsOnTarget1T > (selfAverageUSV)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget1T > (selfAverageUSV)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeShotsOnTarget1T > (selfAverageUSV-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget1T > (selfAverageUSV-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeShotsOnTarget1T > (opAverageUSV+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget1T > (opAverageUSV+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeShotsOnTarget1T > (opAverageUSV)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget1T > (opAverageUSV)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeShotsOnTarget1T > (opAverageUSV-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget1T > (opAverageUSV-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeShotsOnTarget1T > list.get(i).awayShotsOnTarget1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget1T > list.get(i).homeShotsOnTarget1T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeShotsOnTarget1T >= list.get(i).awayShotsOnTarget1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget1T >= list.get(i).homeShotsOnTarget1T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;

                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t35 = "ТБ(3.5)";
            String t35s = (int) (tb35) + "/" + number + " (" + MyMath.round(tb35 / number * 100, 1) + ")";
            if (tb35/number < 0.5){
                t35 = "ТМ(3.5)";
                t35s = (int) (number - tb35) + "/" + number + " (" + MyMath.round((number - tb35) / number * 100, 1) + ")";
            }
            String t45 = "ТБ(4.5)";
            String t45s = (int) (tb45) + "/" + number + " (" + MyMath.round(tb45 / number * 100, 1) + ")";
            if (tb45/number < 0.5){
                t45 = "ТМ(4.5)";
                t45s = (int) (number - tb45) + "/" + number + " (" + MyMath.round((number - tb45) / number * 100, 1) + ")";
            }
            String t55 = "ТБ(5.5)";
            String t55s = (int) (tb55) + "/" + number + " (" + MyMath.round(tb55 / number * 100, 1) + ")";
            if (tb55/number < 0.5){
                t55 = "ТМ(5.5)";
                t55s = (int) (number - tb55) + "/" + number + " (" + MyMath.round((number - tb55) / number * 100, 1) + ")";
            }

            double total = selfAverageUSV + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageUSV;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageUSV - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageUSV + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageUSV;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageUSV - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t35, t35s},
                    {t45, t45s},
                    {t55, t55s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableShotsOnTargets2T(String teamName, Selector selector){
        double tb35 = 0.0;
        double tb45 = 0.0;
        double tb55 = 0.0;
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageUSV = Double.parseDouble(selector.pList.get(39).get(1));
        double opAverageUSV = Double.parseDouble(selector.pList.get(39).get(2));

        if (selector.numberOfMatchesWithParam[39] > 0 ){
            selfAverageUSV = selfAverageUSV / (double) selector.numberOfMatchesWithParam[39];
            opAverageUSV   = opAverageUSV   / (double) selector.numberOfMatchesWithParam[39];
        }

        if (MyMath.round(selfAverageUSV, 0) > selfAverageUSV)
            selfAverageUSV = MyMath.round(selfAverageUSV, 0) - 0.5;
        else
            selfAverageUSV = MyMath.round(selfAverageUSV, 0) + 0.5;
        if (MyMath.round(opAverageUSV, 0) > opAverageUSV)
            opAverageUSV = MyMath.round(opAverageUSV, 0) - 0.5;
        else
            opAverageUSV = MyMath.round(opAverageUSV, 0) + 0.5;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeShotsOnTarget2T + list.get(i).awayShotsOnTarget2T > 0){
                if (list.get(i).homeShotsOnTarget2T + list.get(i).awayShotsOnTarget2T > 3.5)
                    tb35++;
                if (list.get(i).homeShotsOnTarget2T + list.get(i).awayShotsOnTarget2T > 4.5)
                    tb45++;
                if (list.get(i).homeShotsOnTarget2T + list.get(i).awayShotsOnTarget2T > 5.5)
                    tb55++;

                if ((list.get(i).homeShotsOnTarget2T > (selfAverageUSV+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget2T > (selfAverageUSV+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeShotsOnTarget2T > (selfAverageUSV)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget2T > (selfAverageUSV)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeShotsOnTarget2T > (selfAverageUSV-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget2T > (selfAverageUSV-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeShotsOnTarget2T > (opAverageUSV+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget2T > (opAverageUSV+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeShotsOnTarget2T > (opAverageUSV)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget2T > (opAverageUSV)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeShotsOnTarget2T > (opAverageUSV-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayShotsOnTarget2T > (opAverageUSV-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeShotsOnTarget2T > list.get(i).awayShotsOnTarget2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget2T > list.get(i).homeShotsOnTarget2T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeShotsOnTarget2T >= list.get(i).awayShotsOnTarget2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayShotsOnTarget2T >= list.get(i).homeShotsOnTarget2T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;

        if (number > 0){
            String t35 = "ТБ(3.5)";
            String t35s = (int) (tb35) + "/" + number + " (" + MyMath.round(tb35 / number * 100, 1) + ")";
            if (tb35/number < 0.5){
                t35 = "ТМ(3.5)";
                t35s = (int) (number - tb35) + "/" + number + " (" + MyMath.round((number - tb35) / number * 100, 1) + ")";
            }
            String t45 = "ТБ(4.5)";
            String t45s = (int) (tb45) + "/" + number + " (" + MyMath.round(tb45 / number * 100, 1) + ")";
            if (tb45/number < 0.5){
                t45 = "ТМ(4.5)";
                t45s = (int) (number - tb45) + "/" + number + " (" + MyMath.round((number - tb45) / number * 100, 1) + ")";
            }
            String t55 = "ТБ(5.5)";
            String t55s = (int) (tb55) + "/" + number + " (" + MyMath.round(tb55 / number * 100, 1) + ")";
            if (tb55/number < 0.5){
                t55 = "ТМ(5.5)";
                t55s = (int) (number - tb55) + "/" + number + " (" + MyMath.round((number - tb55) / number * 100, 1) + ")";
            }

            double total = selfAverageUSV + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageUSV;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageUSV - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageUSV + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageUSV;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageUSV - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new String[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t35, t35s},
                    {t45, t45s},
                    {t55, t55s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableCorners(String teamName, Selector selector){
        double tb85 = 0.0;
        double tb95 = 0.0;
        double tb105 = 0.0;
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageCorners = Double.parseDouble(selector.pList.get(10).get(1));
        double opAverageCorners = Double.parseDouble(selector.pList.get(10).get(2));

        if (MyMath.round(selfAverageCorners, 0) > selfAverageCorners)
            selfAverageCorners = MyMath.round(selfAverageCorners, 0) - 0.5;
        else
            selfAverageCorners = MyMath.round(selfAverageCorners, 0) + 0.5;
        if (MyMath.round(opAverageCorners, 0) > opAverageCorners)
            opAverageCorners = MyMath.round(opAverageCorners, 0) - 0.5;
        else
            opAverageCorners = MyMath.round(opAverageCorners, 0) + 0.5;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeCorners + list.get(i).awayCorners > 0){
                if (list.get(i).homeCorners + list.get(i).awayCorners > 8.5)
                    tb85++;
                if (list.get(i).homeCorners + list.get(i).awayCorners > 9.5)
                    tb95++;
                if (list.get(i).homeCorners + list.get(i).awayCorners > 10.5)
                    tb105++;

                if ((list.get(i).homeCorners > (selfAverageCorners+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners > (selfAverageCorners+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeCorners > (selfAverageCorners)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners > (selfAverageCorners)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeCorners > (selfAverageCorners-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners > (selfAverageCorners-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeCorners > (opAverageCorners+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners > (opAverageCorners+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeCorners > (opAverageCorners)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners > (opAverageCorners)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeCorners > (opAverageCorners-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners > (opAverageCorners-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeCorners > list.get(i).awayCorners&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners > list.get(i).homeCorners))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeCorners >= list.get(i).awayCorners&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners >= list.get(i).homeCorners))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }

        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(8.5)";
            String t85s = (int) (tb85) + "/" + number + " (" + MyMath.round(tb85 / number * 100, 1) + ")";
            if (tb85/number < 0.5){
                t85 = "ТМ(8.5)";
                t85s = (int) (number - tb85) + "/" + number + " (" + MyMath.round((number - tb85) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(9.5)";
            String t95s = (int) (tb95) + "/" + number + " (" + MyMath.round(tb95 / number * 100, 1) + ")";
            if (tb95/number < 0.5){
                t95 = "ТМ(9.5)";
                t95s = (int) (number - tb95) + "/" + number + " (" + MyMath.round((number - tb95) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(10.5)";
            String t105s = (int) (tb105) + "/" + number + " (" + MyMath.round(tb105 / number * 100, 1) + ")";
            if (tb105/number < 0.5){
                t105 = "ТМ(10.5)";
                t105s = (int) (number - tb105) + "/" + number + " (" + MyMath.round((number - tb105) / number * 100, 1) + ")";
            }

            double total = selfAverageCorners + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageCorners;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageCorners - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageCorners + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageCorners;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageCorners - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableCorners1T(String teamName, Selector selector){
        double tb45 = 0.0;
        double tb55 = 0.0;
        double tb65 = 0.0;
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageCorners = Double.parseDouble(selector.pList.get(32).get(1));
        double opAverageCorners = Double.parseDouble(selector.pList.get(32).get(2));
        if (selector.numberOfMatchesWithParam[32] > 0 ){
            selfAverageCorners = selfAverageCorners / selector.numberOfMatchesWithParam[32];
            opAverageCorners   = opAverageCorners   / selector.numberOfMatchesWithParam[32];
        }

        if (MyMath.round(selfAverageCorners, 0) > selfAverageCorners)
            selfAverageCorners = MyMath.round(selfAverageCorners, 0) - 0.5;
        else
            selfAverageCorners = MyMath.round(selfAverageCorners, 0) + 0.5;
        if (MyMath.round(opAverageCorners, 0) > opAverageCorners)
            opAverageCorners = MyMath.round(opAverageCorners, 0) - 0.5;
        else
            opAverageCorners = MyMath.round(opAverageCorners, 0) + 0.5;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeCorners1T + list.get(i).awayCorners1T > 0){
                if (list.get(i).homeCorners1T + list.get(i).awayCorners1T > 4.5)
                    tb45++;
                if (list.get(i).homeCorners1T + list.get(i).awayCorners1T > 5.5)
                    tb55++;
                if (list.get(i).homeCorners1T + list.get(i).awayCorners1T > 6.5)
                    tb65++;

                if ((list.get(i).homeCorners1T > (selfAverageCorners+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners1T > (selfAverageCorners+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeCorners1T > (selfAverageCorners)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners1T > (selfAverageCorners)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeCorners1T > (selfAverageCorners-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners1T > (selfAverageCorners-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeCorners1T > (opAverageCorners+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners1T > (opAverageCorners+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeCorners1T > (opAverageCorners)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners1T > (opAverageCorners)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeCorners1T > (opAverageCorners-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners1T > (opAverageCorners-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeCorners1T > list.get(i).awayCorners1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners1T > list.get(i).homeCorners1T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeCorners1T >= list.get(i).awayCorners1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners1T >= list.get(i).homeCorners1T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
            }
            number++;
        }

        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t45 = "ТБ(4.5)";
            String t45s = (int) (tb45) + "/" + number + " (" + MyMath.round(tb45 / number * 100, 1) + ")";
            if (tb45/number < 0.5){
                t45 = "ТМ(4.5)";
                t45s = (int) (number - tb45) + "/" + number + " (" + MyMath.round((number - tb45) / number * 100, 1) + ")";
            }
            String t55 = "ТБ(5.5)";
            String t55s = (int) (tb55) + "/" + number + " (" + MyMath.round(tb55 / number * 100, 1) + ")";
            if (tb55/number < 0.5){
                t55 = "ТМ(5.5)";
                t55s = (int) (number - tb55) + "/" + number + " (" + MyMath.round((number - tb55) / number * 100, 1) + ")";
            }

            String t65 = "ТБ(6.5)";
            String t65s = (int) (tb65) + "/" + number + " (" + MyMath.round(tb65 / number * 100, 1) + ")";
            if (tb65/number < 0.5){
                t65 = "ТМ(6.5)";
                t65s = (int) (number - tb65) + "/" + number + " (" + MyMath.round((number - tb65) / number * 100, 1) + ")";
            }

            double total = selfAverageCorners + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageCorners;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageCorners - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageCorners + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageCorners;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageCorners - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t45, t45s},
                    {t55, t55s},
                    {t65, t65s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableCorners2T(String teamName, Selector selector){
        double tb45 = 0.0;
        double tb55 = 0.0;
        double tb65 = 0.0;
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageCorners = Double.parseDouble(selector.pList.get(33).get(1));
        double opAverageCorners = Double.parseDouble(selector.pList.get(33).get(2));
        if (selector.numberOfMatchesWithParam[33] > 0 ){
            selfAverageCorners = selfAverageCorners / selector.numberOfMatchesWithParam[33];
            opAverageCorners   = opAverageCorners   / selector.numberOfMatchesWithParam[33];
        }

        if (MyMath.round(selfAverageCorners, 0) > selfAverageCorners)
            selfAverageCorners = MyMath.round(selfAverageCorners, 0) - 0.5;
        else
            selfAverageCorners = MyMath.round(selfAverageCorners, 0) + 0.5;
        if (MyMath.round(opAverageCorners, 0) > opAverageCorners)
            opAverageCorners = MyMath.round(opAverageCorners, 0) - 0.5;
        else
            opAverageCorners = MyMath.round(opAverageCorners, 0) + 0.5;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeCorners2T + list.get(i).awayCorners2T > 0){
                if (list.get(i).homeCorners2T + list.get(i).awayCorners2T > 4.5)
                    tb45++;
                if (list.get(i).homeCorners2T + list.get(i).awayCorners2T > 5.5)
                    tb55++;
                if (list.get(i).homeCorners2T + list.get(i).awayCorners2T > 6.5)
                    tb65++;

                if ((list.get(i).homeCorners2T > (selfAverageCorners+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners2T > (selfAverageCorners+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeCorners2T > (selfAverageCorners)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners2T > (selfAverageCorners)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeCorners2T > (selfAverageCorners-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners2T > (selfAverageCorners-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeCorners2T > (opAverageCorners+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners2T > (opAverageCorners+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeCorners2T > (opAverageCorners)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners2T > (opAverageCorners)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeCorners2T > (opAverageCorners-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayCorners2T > (opAverageCorners-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeCorners2T > list.get(i).awayCorners2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners2T > list.get(i).homeCorners2T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeCorners2T >= list.get(i).awayCorners2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayCorners2T >= list.get(i).homeCorners2T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t45 = "ТБ(4.5)";
            String t45s = (int) (tb45) + "/" + number + " (" + MyMath.round(tb45 / number * 100, 1) + ")";
            if (tb45/number < 0.5){
                t45 = "ТМ(4.5)";
                t45s = (int) (number - tb45) + "/" + number + " (" + MyMath.round((number - tb45) / number * 100, 1) + ")";
            }
            String t55 = "ТБ(5.5)";
            String t55s = (int) (tb55) + "/" + number + " (" + MyMath.round(tb55 / number * 100, 1) + ")";
            if (tb55/number < 0.5){
                t55 = "ТМ(5.5)";
                t55s = (int) (number - tb55) + "/" + number + " (" + MyMath.round((number - tb55) / number * 100, 1) + ")";
            }

            String t65 = "ТБ(6.5)";
            String t65s = (int) (tb65) + "/" + number + " (" + MyMath.round(tb65 / number * 100, 1) + ")";
            if (tb65/number < 0.5){
                t65 = "ТМ(6.5)";
                t65s = (int) (number - tb65) + "/" + number + " (" + MyMath.round((number - tb65) / number * 100, 1) + ")";
            }

            double total = selfAverageCorners + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageCorners;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageCorners - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageCorners + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageCorners;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageCorners - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t45, t45s},
                    {t55, t55s},
                    {t65, t65s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableOffsides(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageOffsides = Double.parseDouble(selector.pList.get(11).get(1));
        double opAverageOffsides = Double.parseDouble(selector.pList.get(11).get(2));

        double totalAverageOffsides = selfAverageOffsides + opAverageOffsides;

        if (MyMath.round(totalAverageOffsides, 0) > totalAverageOffsides)
            totalAverageOffsides = MyMath.round(totalAverageOffsides, 0) - 0.5;
        else
            totalAverageOffsides = MyMath.round(totalAverageOffsides, 0) + 0.5;
        if (MyMath.round(selfAverageOffsides, 0) > selfAverageOffsides)
            selfAverageOffsides = MyMath.round(selfAverageOffsides, 0) - 0.5;
        else
            selfAverageOffsides = MyMath.round(selfAverageOffsides, 0) + 0.5;
        if (MyMath.round(opAverageOffsides, 0) > opAverageOffsides)
            opAverageOffsides = MyMath.round(opAverageOffsides, 0) - 0.5;
        else
            opAverageOffsides = MyMath.round(opAverageOffsides, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeOffsides + list.get(i).awayOffsides > 0){
                if (list.get(i).homeOffsides + list.get(i).awayOffsides > (totalAverageOffsides-1))
                    totalMinus1++;
                if (list.get(i).homeOffsides + list.get(i).awayOffsides > totalAverageOffsides)
                    totalSred++;
                if (list.get(i).homeOffsides + list.get(i).awayOffsides > (totalAverageOffsides+1))
                    totalPlus1++;

                if ((list.get(i).homeOffsides > (selfAverageOffsides+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides > (selfAverageOffsides+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeOffsides > (selfAverageOffsides)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides > (selfAverageOffsides)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeOffsides > (selfAverageOffsides-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides > (selfAverageOffsides-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeOffsides > (opAverageOffsides+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides > (opAverageOffsides+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeOffsides > (opAverageOffsides)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides > (opAverageOffsides)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeOffsides > (opAverageOffsides-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides > (opAverageOffsides-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeOffsides > list.get(i).awayOffsides&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides > list.get(i).homeOffsides))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeOffsides >= list.get(i).awayOffsides&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides >= list.get(i).homeOffsides))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if ( number > 0 ){
            String t85 = "ТБ(" + (totalAverageOffsides - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageOffsides - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageOffsides + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageOffsides + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageOffsides + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageOffsides + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageOffsides + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageOffsides;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageOffsides - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageOffsides + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageOffsides;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageOffsides - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableOffsides1T(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageOffsides = Double.parseDouble(selector.pList.get(44).get(1));
        double opAverageOffsides = Double.parseDouble(selector.pList.get(44).get(2));

        if (selector.numberOfMatchesWithParam[44] > 0 ){
            selfAverageOffsides = selfAverageOffsides / selector.numberOfMatchesWithParam[44];
            opAverageOffsides   = opAverageOffsides   / selector.numberOfMatchesWithParam[44];
        }
        double totalAverageOffsides = selfAverageOffsides + opAverageOffsides;

        if (MyMath.round(totalAverageOffsides, 0) > totalAverageOffsides)
            totalAverageOffsides = MyMath.round(totalAverageOffsides, 0) - 0.5;
        else
            totalAverageOffsides = MyMath.round(totalAverageOffsides, 0) + 0.5;
        if (MyMath.round(selfAverageOffsides, 0) > selfAverageOffsides)
            selfAverageOffsides = MyMath.round(selfAverageOffsides, 0) - 0.5;
        else
            selfAverageOffsides = MyMath.round(selfAverageOffsides, 0) + 0.5;
        if (MyMath.round(opAverageOffsides, 0) > opAverageOffsides)
            opAverageOffsides = MyMath.round(opAverageOffsides, 0) - 0.5;
        else
            opAverageOffsides = MyMath.round(opAverageOffsides, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeOffsides1T + list.get(i).awayOffsides1T > 0){
                if (list.get(i).homeOffsides1T + list.get(i).awayOffsides1T > (totalAverageOffsides-1))
                    totalMinus1++;
                if (list.get(i).homeOffsides1T + list.get(i).awayOffsides1T > totalAverageOffsides)
                    totalSred++;
                if (list.get(i).homeOffsides1T + list.get(i).awayOffsides1T > (totalAverageOffsides+1))
                    totalPlus1++;

                if ((list.get(i).homeOffsides1T > (selfAverageOffsides+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides1T > (selfAverageOffsides+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeOffsides1T > (selfAverageOffsides)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides1T > (selfAverageOffsides)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeOffsides1T > (selfAverageOffsides-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides1T > (selfAverageOffsides-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeOffsides1T > (opAverageOffsides+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides1T > (opAverageOffsides+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeOffsides1T > (opAverageOffsides)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides1T > (opAverageOffsides)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeOffsides1T > (opAverageOffsides-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides1T > (opAverageOffsides-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeOffsides1T > list.get(i).awayOffsides1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides1T > list.get(i).homeOffsides1T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeOffsides1T >= list.get(i).awayOffsides1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides1T >= list.get(i).homeOffsides1T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageOffsides - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageOffsides - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageOffsides + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageOffsides + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageOffsides + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageOffsides + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageOffsides + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageOffsides;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageOffsides - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageOffsides + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageOffsides;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageOffsides - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableOffsides2T(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageOffsides = Double.parseDouble(selector.pList.get(45).get(1));
        double opAverageOffsides = Double.parseDouble(selector.pList.get(45).get(2));
        if (selector.numberOfMatchesWithParam[45] > 0 ){
            selfAverageOffsides = selfAverageOffsides / selector.numberOfMatchesWithParam[45];
            opAverageOffsides   = opAverageOffsides   / selector.numberOfMatchesWithParam[45];
        }
        double totalAverageOffsides = selfAverageOffsides + opAverageOffsides;

        if (MyMath.round(totalAverageOffsides, 0) > totalAverageOffsides)
            totalAverageOffsides = MyMath.round(totalAverageOffsides, 0) - 0.5;
        else
            totalAverageOffsides = MyMath.round(totalAverageOffsides, 0) + 0.5;
        if (MyMath.round(selfAverageOffsides, 0) > selfAverageOffsides)
            selfAverageOffsides = MyMath.round(selfAverageOffsides, 0) - 0.5;
        else
            selfAverageOffsides = MyMath.round(selfAverageOffsides, 0) + 0.5;
        if (MyMath.round(opAverageOffsides, 0) > opAverageOffsides)
            opAverageOffsides = MyMath.round(opAverageOffsides, 0) - 0.5;
        else
            opAverageOffsides = MyMath.round(opAverageOffsides, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeOffsides2T + list.get(i).awayOffsides2T > 0){
                if (list.get(i).homeOffsides2T + list.get(i).awayOffsides2T > (totalAverageOffsides-1))
                    totalMinus1++;
                if (list.get(i).homeOffsides2T + list.get(i).awayOffsides2T > totalAverageOffsides)
                    totalSred++;
                if (list.get(i).homeOffsides2T + list.get(i).awayOffsides2T > (totalAverageOffsides+1))
                    totalPlus1++;

                if ((list.get(i).homeOffsides2T > (selfAverageOffsides+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides2T > (selfAverageOffsides+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeOffsides2T > (selfAverageOffsides)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides2T > (selfAverageOffsides)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeOffsides2T > (selfAverageOffsides-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides2T > (selfAverageOffsides-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeOffsides2T > (opAverageOffsides+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides2T > (opAverageOffsides+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeOffsides2T > (opAverageOffsides)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides2T > (opAverageOffsides)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeOffsides2T > (opAverageOffsides-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayOffsides2T > (opAverageOffsides-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeOffsides2T > list.get(i).awayOffsides2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides2T > list.get(i).homeOffsides2T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeOffsides2T >= list.get(i).awayOffsides2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayOffsides2T >= list.get(i).homeOffsides2T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number >  0){
            String t85 = "ТБ(" + (totalAverageOffsides - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageOffsides - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageOffsides + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageOffsides + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageOffsides + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageOffsides + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageOffsides + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageOffsides;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageOffsides - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageOffsides + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageOffsides;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageOffsides - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableFouls(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageFouls = Double.parseDouble(selector.pList.get(13).get(1));
        double opAverageFouls = Double.parseDouble(selector.pList.get(13).get(2));
        double totalAverageFouls = opAverageFouls + selfAverageFouls;

        if (MyMath.round(totalAverageFouls, 0) > totalAverageFouls)
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) - 0.5;
        else
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) + 0.5;
        if (MyMath.round(selfAverageFouls, 0) > selfAverageFouls)
            selfAverageFouls = MyMath.round(selfAverageFouls, 0) - 0.5;
        else
            selfAverageFouls = MyMath.round(selfAverageFouls, 0) + 0.5;
        if (MyMath.round(opAverageFouls, 0) > opAverageFouls)
            opAverageFouls = MyMath.round(opAverageFouls, 0) - 0.5;
        else
            opAverageFouls = MyMath.round(opAverageFouls, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeFouls + list.get(i).awayFouls > 0){
                if (list.get(i).homeFouls + list.get(i).awayFouls > (totalAverageFouls-1))
                    totalMinus1++;
                if (list.get(i).homeFouls + list.get(i).awayFouls > totalAverageFouls)
                    totalSred++;
                if (list.get(i).homeFouls + list.get(i).awayFouls > (totalAverageFouls+1))
                    totalPlus1++;

                if ((list.get(i).homeFouls > (selfAverageFouls+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls > (selfAverageFouls+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeFouls > (selfAverageFouls)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls > (selfAverageFouls)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeFouls > (selfAverageFouls-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls > (selfAverageFouls-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeFouls > (opAverageFouls+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls > (opAverageFouls+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeFouls > (opAverageFouls)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls > (opAverageFouls)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeFouls > (opAverageFouls-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls > (opAverageFouls-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeFouls > list.get(i).awayFouls&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls > list.get(i).homeFouls))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeFouls >= list.get(i).awayFouls&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls >= list.get(i).homeFouls))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageFouls - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageFouls - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageFouls + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageFouls + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageFouls + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageFouls + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageFouls + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageFouls;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageFouls - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageFouls + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageFouls;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageFouls - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableFouls1T(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageFouls = Double.parseDouble(selector.pList.get(46).get(1));
        double opAverageFouls = Double.parseDouble(selector.pList.get(46).get(2));
        if (selector.numberOfMatchesWithParam[46] > 0 ){
            selfAverageFouls = selfAverageFouls / selector.numberOfMatchesWithParam[46];
            opAverageFouls   = opAverageFouls   / selector.numberOfMatchesWithParam[46];
        }
        double totalAverageFouls = selfAverageFouls + opAverageFouls;

        if (MyMath.round(totalAverageFouls, 0) > totalAverageFouls)
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) - 0.5;
        else
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) + 0.5;
        if (MyMath.round(selfAverageFouls, 0) > selfAverageFouls)
            selfAverageFouls = MyMath.round(selfAverageFouls, 0) - 0.5;
        else
            selfAverageFouls = MyMath.round(selfAverageFouls, 0) + 0.5;
        if (MyMath.round(opAverageFouls, 0) > opAverageFouls)
            opAverageFouls = MyMath.round(opAverageFouls, 0) - 0.5;
        else
            opAverageFouls = MyMath.round(opAverageFouls, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeFouls1T + list.get(i).awayFouls1T > 0){
                if (list.get(i).homeFouls1T + list.get(i).awayFouls1T > (totalAverageFouls-1))
                    totalMinus1++;
                if (list.get(i).homeFouls1T + list.get(i).awayFouls1T > totalAverageFouls)
                    totalSred++;
                if (list.get(i).homeFouls1T + list.get(i).awayFouls1T > (totalAverageFouls+1))
                    totalPlus1++;

                if ((list.get(i).homeFouls1T > (selfAverageFouls+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls1T > (selfAverageFouls+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeFouls1T > (selfAverageFouls)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls1T > (selfAverageFouls)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeFouls1T > (selfAverageFouls-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls1T > (selfAverageFouls-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeFouls1T > (opAverageFouls+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls1T > (opAverageFouls+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeFouls1T > (opAverageFouls)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls1T > (opAverageFouls)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeFouls1T > (opAverageFouls-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls1T > (opAverageFouls-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeFouls1T > list.get(i).awayFouls1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls1T > list.get(i).homeFouls1T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeFouls1T >= list.get(i).awayFouls1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls1T >= list.get(i).homeFouls1T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageFouls - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageFouls - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageFouls + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageFouls + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageFouls + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageFouls + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageFouls + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageFouls;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageFouls - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageFouls + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageFouls;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageFouls - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableFouls2T(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageFouls = Double.parseDouble(selector.pList.get(47).get(1));
        double opAverageFouls = Double.parseDouble(selector.pList.get(47).get(2));
        if (selector.numberOfMatchesWithParam[47] > 0 ){
            selfAverageFouls = selfAverageFouls / selector.numberOfMatchesWithParam[47];
            opAverageFouls   = opAverageFouls   / selector.numberOfMatchesWithParam[47];
        }
        double totalAverageFouls = selfAverageFouls + opAverageFouls;

        if (MyMath.round(totalAverageFouls, 0) > totalAverageFouls)
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) - 0.5;
        else
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) + 0.5;
        if (MyMath.round(selfAverageFouls, 0) > selfAverageFouls)
            selfAverageFouls = MyMath.round(selfAverageFouls, 0) - 0.5;
        else
            selfAverageFouls = MyMath.round(selfAverageFouls, 0) + 0.5;
        if (MyMath.round(opAverageFouls, 0) > opAverageFouls)
            opAverageFouls = MyMath.round(opAverageFouls, 0) - 0.5;
        else
            opAverageFouls = MyMath.round(opAverageFouls, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeFouls2T + list.get(i).awayFouls2T > 0){
                if (list.get(i).homeFouls2T + list.get(i).awayFouls2T > (totalAverageFouls-1))
                    totalMinus1++;
                if (list.get(i).homeFouls2T + list.get(i).awayFouls2T > totalAverageFouls)
                    totalSred++;
                if (list.get(i).homeFouls2T + list.get(i).awayFouls2T > (totalAverageFouls+1))
                    totalPlus1++;

                if ((list.get(i).homeFouls2T > (selfAverageFouls+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls2T > (selfAverageFouls+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeFouls2T > (selfAverageFouls)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls2T > (selfAverageFouls)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeFouls2T > (selfAverageFouls-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls2T > (selfAverageFouls-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeFouls2T > (opAverageFouls+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls2T > (opAverageFouls+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeFouls2T > (opAverageFouls)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls2T > (opAverageFouls)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeFouls2T > (opAverageFouls-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayFouls2T > (opAverageFouls-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeFouls2T > list.get(i).awayFouls2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls2T > list.get(i).homeFouls2T))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeFouls2T >= list.get(i).awayFouls2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayFouls2T >= list.get(i).homeFouls2T))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }
        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageFouls - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageFouls - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageFouls + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageFouls + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageFouls + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageFouls + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageFouls + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageFouls;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageFouls - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageFouls + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageFouls;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageFouls - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableYCards(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        double selfAverageYellowCards = Double.parseDouble(selector.pList.get(14).get(1));
        double opAverageYellowCards = Double.parseDouble(selector.pList.get(14).get(2));
        double totalAverageYellowCards = selfAverageYellowCards + opAverageYellowCards;

        if (MyMath.round(totalAverageYellowCards, 0) > totalAverageYellowCards)
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) - 0.5;
        else
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) + 0.5;
        if (MyMath.round(selfAverageYellowCards, 0) > selfAverageYellowCards)
            selfAverageYellowCards = MyMath.round(selfAverageYellowCards, 0) - 0.5;
        else
            selfAverageYellowCards = MyMath.round(selfAverageYellowCards, 0) + 0.5;
        if (MyMath.round(opAverageYellowCards, 0) > opAverageYellowCards)
            opAverageYellowCards = MyMath.round(opAverageYellowCards, 0) - 0.5;
        else
            opAverageYellowCards = MyMath.round(opAverageYellowCards, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeYellowCards + list.get(i).awayYellowCards > (totalAverageYellowCards-1))
                totalMinus1++;
            if (list.get(i).homeYellowCards + list.get(i).awayYellowCards > totalAverageYellowCards)
                totalSred++;
            if (list.get(i).homeYellowCards + list.get(i).awayYellowCards > (totalAverageYellowCards+1))
                totalPlus1++;

            if ((list.get(i).homeYellowCards > (selfAverageYellowCards+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards > (selfAverageYellowCards+1)))&&(teamName.equals(list.get(i).awayTeam))))
                selfPlus1++;
            if ((list.get(i).homeYellowCards > (selfAverageYellowCards)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards > (selfAverageYellowCards)))&&(teamName.equals(list.get(i).awayTeam))))
                selfSred++;
            if ((list.get(i).homeYellowCards > (selfAverageYellowCards-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards > (selfAverageYellowCards-1)))&&(teamName.equals(list.get(i).awayTeam))))
                selfMinus1++;

            if ((list.get(i).homeYellowCards > (opAverageYellowCards+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards > (opAverageYellowCards+1)))&&(teamName.equals(list.get(i).homeTeam))))
                opPlus1++;
            if ((list.get(i).homeYellowCards > (opAverageYellowCards)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards > (opAverageYellowCards)))&&(teamName.equals(list.get(i).homeTeam))))
                opSred++;
            if ((list.get(i).homeYellowCards > (opAverageYellowCards-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards > (opAverageYellowCards-1)))&&(teamName.equals(list.get(i).homeTeam))))
                opMinus1++;
            if ((list.get(i).homeYellowCards > list.get(i).awayYellowCards&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards > list.get(i).homeYellowCards))&&(teamName.equals(list.get(i).awayTeam))))
                win++;
            if ((list.get(i).homeYellowCards >= list.get(i).awayYellowCards&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards >= list.get(i).homeYellowCards))&&(teamName.equals(list.get(i).awayTeam))))
                winX++;
        }

        String t85 = "ТБ(" + (totalAverageYellowCards - 1) + ")";
        String t85s = (int) (totalMinus1) + "/" + list.size() + " (" + MyMath.round(totalMinus1 / list.size() * 100, 1) + ")";
        if (totalMinus1/list.size() < 0.5){
            t85 = "ТM(" + (totalAverageYellowCards - 1) + ")";
            t85s = (int) (list.size() - totalMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - totalMinus1) / list.size() * 100, 1) + ")";
        }
        String t95 = "ТБ(" + totalAverageYellowCards + ")";
        String t95s = (int) (totalSred) + "/" + list.size() + " (" + MyMath.round(totalSred / list.size() * 100, 1) + ")";
        if (totalSred/list.size() < 0.5){
            t95 = "ТM(" + totalAverageYellowCards + ")";
            t95s = (int) (list.size() - totalSred) + "/" + list.size() + " (" + MyMath.round((list.size() - totalSred) / list.size() * 100, 1) + ")";
        }

        String t105 = "ТБ(" + (totalAverageYellowCards + 1) + ")";
        String t105s = (int) (totalPlus1) + "/" + list.size() + " (" + MyMath.round(totalPlus1 / list.size() * 100, 1) + ")";
        if (totalPlus1/list.size() < 0.5){
            t105 = "ТM(" + (totalAverageYellowCards + 1) + ")";
            t105s = (int) (list.size() - totalPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - totalPlus1) / list.size() * 100, 1) + ")";
        }

        double total = selfAverageYellowCards + 1;
        String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBPlus1 = (int) (selfPlus1) + "/" + list.size() + " (" + MyMath.round(selfPlus1 / list.size() * 100, 1) + ")";
        if (selfPlus1/list.size() < 0.5){
            itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBPlus1 = (int) (list.size() - selfPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - selfPlus1) / list.size() * 100, 1) + ")";
        }

        total = selfAverageYellowCards;
        String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBSred = (int) (selfSred) + "/" + list.size() + " (" + MyMath.round(selfSred / list.size() * 100, 1) + ")";
        if (selfSred/list.size() < 0.5){
            itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBSred = (int) (list.size() - selfSred) + "/" + list.size() + " (" + MyMath.round((list.size() - selfSred) / list.size() * 100, 1) + ")";
        }

        total = selfAverageYellowCards - 1;
        String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBMinus1 = (int) (selfMinus1) + "/" + list.size() + " (" + MyMath.round(selfMinus1 / list.size() * 100, 1) + ")";
        if (selfMinus1/list.size() < 0.5){
            itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBMinus1 = (int) (list.size() - selfMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - selfMinus1) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards + 1;
        String optbPlus1 = "Opp: ТБ(" + total + ")";
        String opTBPlus1 = (int) (opPlus1) + "/" + list.size() + " (" + MyMath.round(opPlus1 / list.size() * 100, 1) + ")";
        if (opPlus1/list.size() < 0.5){
            optbPlus1 = "Opp: ТM(" + total + ")";
            opTBPlus1 = (int) (list.size() - opPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - opPlus1) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards;
        String optbSred = "Opp: ТБ(" + total + ")";
        String opTBSred = (int) (opSred) + "/" + list.size() + " (" + MyMath.round(opSred / list.size() * 100, 1) + ")";
        if (opSred/list.size() < 0.5){
            optbSred = "Opp: ТM(" + total + ")";
            opTBSred = (int) (list.size() - opSred) + "/" + list.size() + " (" + MyMath.round((list.size() - opSred) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards - 1;
        String optbMinus1 = "Opp: ТБ(" + total + ")";
        String opTBMinus1 = (int) (opMinus1) + "/" + list.size() + " (" + MyMath.round(opMinus1 / list.size() * 100, 1) + ")";
        if (opMinus1/list.size() < 0.5){
            optbMinus1 = "Opp: ТM(" + total + ")";
            opTBMinus1 = (int) (list.size() - opMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - opMinus1) / list.size() * 100, 1) + ")";
        }

        String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
        String winSS = (int) win + "/" + list.size() + " (" + MyMath.round(win / list.size() * 100, 1) + ")";

        String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
        String winXSS = (int) winX + "/" + list.size() + " (" + MyMath.round(winX / list.size() * 100, 1) + ")";

        String[] colHeads = {"Ставка", "Заход и %"};
        Object[][] data = {
                {t85 , t85s},
                {t95 , t95s},
                {t105 , t105s},
                {itbMinus1 , selfTBMinus1},
                {itbSred , selfTBSred},
                {itbPlus1 , selfTBPlus1},
                {optbMinus1 , opTBMinus1},
                {optbSred , opTBSred},
                {optbPlus1 , opTBPlus1},
                {winS , winSS},
                {winXS , winXSS},
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableYCards1T(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        double selfAverageYellowCards = Double.parseDouble(selector.pList.get(34).get(1)) / (double) selector.listOfMatches.size();
        double opAverageYellowCards = Double.parseDouble(selector.pList.get(34).get(2)) / (double) selector.listOfMatches.size();
        double totalAverageYellowCards = selfAverageYellowCards + opAverageYellowCards;

        if (MyMath.round(totalAverageYellowCards, 0) > totalAverageYellowCards)
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) - 0.5;
        else
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) + 0.5;
        if (MyMath.round(selfAverageYellowCards, 0) > selfAverageYellowCards)
            selfAverageYellowCards = MyMath.round(selfAverageYellowCards, 0) - 0.5;
        else
            selfAverageYellowCards = MyMath.round(selfAverageYellowCards, 0) + 0.5;
        if (MyMath.round(opAverageYellowCards, 0) > opAverageYellowCards)
            opAverageYellowCards = MyMath.round(opAverageYellowCards, 0) - 0.5;
        else
            opAverageYellowCards = MyMath.round(opAverageYellowCards, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeYellowCards1T    + list.get(i).awayYellowCards1T > (totalAverageYellowCards-1))
                totalMinus1++;
            if (list.get(i).homeYellowCards1T    + list.get(i).awayYellowCards1T > totalAverageYellowCards)
                totalSred++;
            if (list.get(i).homeYellowCards1T    + list.get(i).awayYellowCards1T > (totalAverageYellowCards+1))
                totalPlus1++;

            if ((list.get(i).homeYellowCards1T > (selfAverageYellowCards+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards1T > (selfAverageYellowCards+1)))&&(teamName.equals(list.get(i).awayTeam))))
                selfPlus1++;
            if ((list.get(i).homeYellowCards1T > (selfAverageYellowCards)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards1T > (selfAverageYellowCards)))&&(teamName.equals(list.get(i).awayTeam))))
                selfSred++;
            if ((list.get(i).homeYellowCards1T > (selfAverageYellowCards-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards1T > (selfAverageYellowCards-1)))&&(teamName.equals(list.get(i).awayTeam))))
                selfMinus1++;

            if ((list.get(i).homeYellowCards1T > (opAverageYellowCards+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards1T > (opAverageYellowCards+1)))&&(teamName.equals(list.get(i).homeTeam))))
                opPlus1++;
            if ((list.get(i).homeYellowCards1T > (opAverageYellowCards)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards1T > (opAverageYellowCards)))&&(teamName.equals(list.get(i).homeTeam))))
                opSred++;
            if ((list.get(i).homeYellowCards1T > (opAverageYellowCards-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards1T > (opAverageYellowCards-1)))&&(teamName.equals(list.get(i).homeTeam))))
                opMinus1++;
            if ((list.get(i).homeYellowCards1T > list.get(i).awayYellowCards1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards1T > list.get(i).homeYellowCards1T))&&(teamName.equals(list.get(i).awayTeam))))
                win++;
            if ((list.get(i).homeYellowCards1T >= list.get(i).awayYellowCards1T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards1T >= list.get(i).homeYellowCards1T))&&(teamName.equals(list.get(i).awayTeam))))
                winX++;
        }

        String t85 = "ТБ(" + (totalAverageYellowCards - 1) + ")";
        String t85s = (int) (totalMinus1) + "/" + list.size() + " (" + MyMath.round(totalMinus1 / list.size() * 100, 1) + ")";
        if (totalMinus1/list.size() < 0.5){
            t85 = "ТM(" + (totalAverageYellowCards - 1) + ")";
            t85s = (int) (list.size() - totalMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - totalMinus1) / list.size() * 100, 1) + ")";
        }
        String t95 = "ТБ(" + totalAverageYellowCards + ")";
        String t95s = (int) (totalSred) + "/" + list.size() + " (" + MyMath.round(totalSred / list.size() * 100, 1) + ")";
        if (totalSred/list.size() < 0.5){
            t95 = "ТM(" + totalAverageYellowCards + ")";
            t95s = (int) (list.size() - totalSred) + "/" + list.size() + " (" + MyMath.round((list.size() - totalSred) / list.size() * 100, 1) + ")";
        }

        String t105 = "ТБ(" + (totalAverageYellowCards + 1) + ")";
        String t105s = (int) (totalPlus1) + "/" + list.size() + " (" + MyMath.round(totalPlus1 / list.size() * 100, 1) + ")";
        if (totalPlus1/list.size() < 0.5){
            t105 = "ТM(" + (totalAverageYellowCards + 1) + ")";
            t105s = (int) (list.size() - totalPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - totalPlus1) / list.size() * 100, 1) + ")";
        }

        double total = selfAverageYellowCards + 1;
        String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBPlus1 = (int) (selfPlus1) + "/" + list.size() + " (" + MyMath.round(selfPlus1 / list.size() * 100, 1) + ")";
        if (selfPlus1/list.size() < 0.5){
            itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBPlus1 = (int) (list.size() - selfPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - selfPlus1) / list.size() * 100, 1) + ")";
        }

        total = selfAverageYellowCards;
        String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBSred = (int) (selfSred) + "/" + list.size() + " (" + MyMath.round(selfSred / list.size() * 100, 1) + ")";
        if (selfSred/list.size() < 0.5){
            itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBSred = (int) (list.size() - selfSred) + "/" + list.size() + " (" + MyMath.round((list.size() - selfSred) / list.size() * 100, 1) + ")";
        }

        total = selfAverageYellowCards - 1;
        String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBMinus1 = (int) (selfMinus1) + "/" + list.size() + " (" + MyMath.round(selfMinus1 / list.size() * 100, 1) + ")";
        if (selfMinus1/list.size() < 0.5){
            itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBMinus1 = (int) (list.size() - selfMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - selfMinus1) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards + 1;
        String optbPlus1 = "Opp: ТБ(" + total + ")";
        String opTBPlus1 = (int) (opPlus1) + "/" + list.size() + " (" + MyMath.round(opPlus1 / list.size() * 100, 1) + ")";
        if (opPlus1/list.size() < 0.5){
            optbPlus1 = "Opp: ТM(" + total + ")";
            opTBPlus1 = (int) (list.size() - opPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - opPlus1) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards;
        String optbSred = "Opp: ТБ(" + total + ")";
        String opTBSred = (int) (opSred) + "/" + list.size() + " (" + MyMath.round(opSred / list.size() * 100, 1) + ")";
        if (opSred/list.size() < 0.5){
            optbSred = "Opp: ТM(" + total + ")";
            opTBSred = (int) (list.size() - opSred) + "/" + list.size() + " (" + MyMath.round((list.size() - opSred) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards - 1;
        String optbMinus1 = "Opp: ТБ(" + total + ")";
        String opTBMinus1 = (int) (opMinus1) + "/" + list.size() + " (" + MyMath.round(opMinus1 / list.size() * 100, 1) + ")";
        if (opMinus1/list.size() < 0.5){
            optbMinus1 = "Opp: ТM(" + total + ")";
            opTBMinus1 = (int) (list.size() - opMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - opMinus1) / list.size() * 100, 1) + ")";
        }

        String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
        String winSS = (int) win + "/" + list.size() + " (" + MyMath.round(win / list.size() * 100, 1) + ")";

        String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
        String winXSS = (int) winX + "/" + list.size() + " (" + MyMath.round(winX / list.size() * 100, 1) + ")";

        String[] colHeads = {"Ставка", "Заход и %"};
        Object[][] data = {
                {t85 , t85s},
                {t95 , t95s},
                {t105 , t105s},
                {itbMinus1 , selfTBMinus1},
                {itbSred , selfTBSred},
                {itbPlus1 , selfTBPlus1},
                {optbMinus1 , opTBMinus1},
                {optbSred , opTBSred},
                {optbPlus1 , opTBPlus1},
                {winS , winSS},
                {winXS , winXSS},
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableYCards2T(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        double selfAverageYellowCards = Double.parseDouble(selector.pList.get(35).get(1)) / (double) selector.listOfMatches.size();
        double opAverageYellowCards = Double.parseDouble(selector.pList.get(35).get(2)) / (double) selector.listOfMatches.size();
        double totalAverageYellowCards = selfAverageYellowCards + opAverageYellowCards;

        if (MyMath.round(totalAverageYellowCards, 0) > totalAverageYellowCards)
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) - 0.5;
        else
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) + 0.5;
        if (MyMath.round(selfAverageYellowCards, 0) > selfAverageYellowCards)
            selfAverageYellowCards = MyMath.round(selfAverageYellowCards, 0) - 0.5;
        else
            selfAverageYellowCards = MyMath.round(selfAverageYellowCards, 0) + 0.5;
        if (MyMath.round(opAverageYellowCards, 0) > opAverageYellowCards)
            opAverageYellowCards = MyMath.round(opAverageYellowCards, 0) - 0.5;
        else
            opAverageYellowCards = MyMath.round(opAverageYellowCards, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeYellowCards2T    + list.get(i).awayYellowCards2T > (totalAverageYellowCards-1))
                totalMinus1++;
            if (list.get(i).homeYellowCards2T    + list.get(i).awayYellowCards2T > totalAverageYellowCards)
                totalSred++;
            if (list.get(i).homeYellowCards2T    + list.get(i).awayYellowCards2T > (totalAverageYellowCards+1))
                totalPlus1++;

            if ((list.get(i).homeYellowCards2T > (selfAverageYellowCards+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards2T > (selfAverageYellowCards+1)))&&(teamName.equals(list.get(i).awayTeam))))
                selfPlus1++;
            if ((list.get(i).homeYellowCards2T > (selfAverageYellowCards)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards2T > (selfAverageYellowCards)))&&(teamName.equals(list.get(i).awayTeam))))
                selfSred++;
            if ((list.get(i).homeYellowCards2T > (selfAverageYellowCards-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards2T > (selfAverageYellowCards-1)))&&(teamName.equals(list.get(i).awayTeam))))
                selfMinus1++;

            if ((list.get(i).homeYellowCards2T > (opAverageYellowCards+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards2T > (opAverageYellowCards+1)))&&(teamName.equals(list.get(i).homeTeam))))
                opPlus1++;
            if ((list.get(i).homeYellowCards2T > (opAverageYellowCards)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards2T > (opAverageYellowCards)))&&(teamName.equals(list.get(i).homeTeam))))
                opSred++;
            if ((list.get(i).homeYellowCards2T > (opAverageYellowCards-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayYellowCards2T > (opAverageYellowCards-1)))&&(teamName.equals(list.get(i).homeTeam))))
                opMinus1++;
            if ((list.get(i).homeYellowCards2T > list.get(i).awayYellowCards2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards2T > list.get(i).homeYellowCards2T))&&(teamName.equals(list.get(i).awayTeam))))
                win++;
            if ((list.get(i).homeYellowCards2T >= list.get(i).awayYellowCards2T&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayYellowCards2T >= list.get(i).homeYellowCards2T))&&(teamName.equals(list.get(i).awayTeam))))
                winX++;
        }

        String t85 = "ТБ(" + (totalAverageYellowCards - 1) + ")";
        String t85s = (int) (totalMinus1) + "/" + list.size() + " (" + MyMath.round(totalMinus1 / list.size() * 100, 1) + ")";
        if (totalMinus1/list.size() < 0.5){
            t85 = "ТM(" + (totalAverageYellowCards - 1) + ")";
            t85s = (int) (list.size() - totalMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - totalMinus1) / list.size() * 100, 1) + ")";
        }
        String t95 = "ТБ(" + totalAverageYellowCards + ")";
        String t95s = (int) (totalSred) + "/" + list.size() + " (" + MyMath.round(totalSred / list.size() * 100, 1) + ")";
        if (totalSred/list.size() < 0.5){
            t95 = "ТM(" + totalAverageYellowCards + ")";
            t95s = (int) (list.size() - totalSred) + "/" + list.size() + " (" + MyMath.round((list.size() - totalSred) / list.size() * 100, 1) + ")";
        }

        String t105 = "ТБ(" + (totalAverageYellowCards + 1) + ")";
        String t105s = (int) (totalPlus1) + "/" + list.size() + " (" + MyMath.round(totalPlus1 / list.size() * 100, 1) + ")";
        if (totalPlus1/list.size() < 0.5){
            t105 = "ТM(" + (totalAverageYellowCards + 1) + ")";
            t105s = (int) (list.size() - totalPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - totalPlus1) / list.size() * 100, 1) + ")";
        }

        double total = selfAverageYellowCards + 1;
        String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBPlus1 = (int) (selfPlus1) + "/" + list.size() + " (" + MyMath.round(selfPlus1 / list.size() * 100, 1) + ")";
        if (selfPlus1/list.size() < 0.5){
            itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBPlus1 = (int) (list.size() - selfPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - selfPlus1) / list.size() * 100, 1) + ")";
        }

        total = selfAverageYellowCards;
        String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBSred = (int) (selfSred) + "/" + list.size() + " (" + MyMath.round(selfSred / list.size() * 100, 1) + ")";
        if (selfSred/list.size() < 0.5){
            itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBSred = (int) (list.size() - selfSred) + "/" + list.size() + " (" + MyMath.round((list.size() - selfSred) / list.size() * 100, 1) + ")";
        }

        total = selfAverageYellowCards - 1;
        String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
        String selfTBMinus1 = (int) (selfMinus1) + "/" + list.size() + " (" + MyMath.round(selfMinus1 / list.size() * 100, 1) + ")";
        if (selfMinus1/list.size() < 0.5){
            itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
            selfTBMinus1 = (int) (list.size() - selfMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - selfMinus1) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards + 1;
        String optbPlus1 = "Opp: ТБ(" + total + ")";
        String opTBPlus1 = (int) (opPlus1) + "/" + list.size() + " (" + MyMath.round(opPlus1 / list.size() * 100, 1) + ")";
        if (opPlus1/list.size() < 0.5){
            optbPlus1 = "Opp: ТM(" + total + ")";
            opTBPlus1 = (int) (list.size() - opPlus1) + "/" + list.size() + " (" + MyMath.round((list.size() - opPlus1) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards;
        String optbSred = "Opp: ТБ(" + total + ")";
        String opTBSred = (int) (opSred) + "/" + list.size() + " (" + MyMath.round(opSred / list.size() * 100, 1) + ")";
        if (opSred/list.size() < 0.5){
            optbSred = "Opp: ТM(" + total + ")";
            opTBSred = (int) (list.size() - opSred) + "/" + list.size() + " (" + MyMath.round((list.size() - opSred) / list.size() * 100, 1) + ")";
        }

        total = opAverageYellowCards - 1;
        String optbMinus1 = "Opp: ТБ(" + total + ")";
        String opTBMinus1 = (int) (opMinus1) + "/" + list.size() + " (" + MyMath.round(opMinus1 / list.size() * 100, 1) + ")";
        if (opMinus1/list.size() < 0.5){
            optbMinus1 = "Opp: ТM(" + total + ")";
            opTBMinus1 = (int) (list.size() - opMinus1) + "/" + list.size() + " (" + MyMath.round((list.size() - opMinus1) / list.size() * 100, 1) + ")";
        }

        String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
        String winSS = (int) win + "/" + list.size() + " (" + MyMath.round(win / list.size() * 100, 1) + ")";

        String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
        String winXSS = (int) winX + "/" + list.size() + " (" + MyMath.round(winX / list.size() * 100, 1) + ")";

        String[] colHeads = {"Ставка", "Заход и %"};
        Object[][] data = {
                {t85 , t85s},
                {t95 , t95s},
                {t105 , t105s},
                {itbMinus1 , selfTBMinus1},
                {itbSred , selfTBSred},
                {itbPlus1 , selfTBPlus1},
                {optbMinus1 , opTBMinus1},
                {optbSred , opTBSred},
                {optbPlus1 , opTBPlus1},
                {winS , winSS},
                {winXS , winXSS},
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableDribbles(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageDribbles = Double.parseDouble(selector.pList.get(68).get(1));
        double opAverageDribbles = Double.parseDouble(selector.pList.get(68).get(2)) ;
        if (selector.numberOfMatchesWithParam[68] > 0 ){
            selfAverageDribbles = selfAverageDribbles / selector.numberOfMatchesWithParam[68];
            opAverageDribbles   = opAverageDribbles   / selector.numberOfMatchesWithParam[68];
        }
        double totalAverageDribbles = selfAverageDribbles + opAverageDribbles;

        if (MyMath.round(totalAverageDribbles, 0) > totalAverageDribbles)
            totalAverageDribbles = MyMath.round(totalAverageDribbles, 0) - 0.5;
        else
            totalAverageDribbles = MyMath.round(totalAverageDribbles, 0) + 0.5;
        if (MyMath.round(selfAverageDribbles, 0) > selfAverageDribbles)
            selfAverageDribbles = MyMath.round(selfAverageDribbles, 0) - 0.5;
        else
            selfAverageDribbles = MyMath.round(selfAverageDribbles, 0) + 0.5;
        if (MyMath.round(opAverageDribbles, 0) > opAverageDribbles)
            opAverageDribbles = MyMath.round(opAverageDribbles, 0) - 0.5;
        else
            opAverageDribbles = MyMath.round(opAverageDribbles, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeDribbles + list.get(i).awayDribbles > 0){
                if (list.get(i).homeDribbles + list.get(i).awayDribbles > (totalAverageDribbles-1))
                    totalMinus1++;
                if (list.get(i).homeDribbles + list.get(i).awayDribbles > totalAverageDribbles)
                    totalSred++;
                if (list.get(i).homeDribbles + list.get(i).awayDribbles > (totalAverageDribbles+1))
                    totalPlus1++;

                if ((list.get(i).homeDribbles > (selfAverageDribbles+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayDribbles > (selfAverageDribbles+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeDribbles > (selfAverageDribbles)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayDribbles > (selfAverageDribbles)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeDribbles > (selfAverageDribbles-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayDribbles > (selfAverageDribbles-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeDribbles > (opAverageDribbles+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayDribbles > (opAverageDribbles+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeDribbles > (opAverageDribbles)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayDribbles > (opAverageDribbles)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeDribbles > (opAverageDribbles-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayDribbles > (opAverageDribbles-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeDribbles > list.get(i).awayDribbles&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayDribbles > list.get(i).homeDribbles))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeDribbles >= list.get(i).awayDribbles&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayDribbles >= list.get(i).homeDribbles))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageDribbles - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageDribbles - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageDribbles + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageDribbles + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageDribbles + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageDribbles + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageDribbles + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageDribbles;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageDribbles - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageDribbles + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageDribbles;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageDribbles - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableAerialsWon(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageAerialsWon = Double.parseDouble(selector.pList.get(69).get(1));
        double opAverageAerialsWon = Double.parseDouble(selector.pList.get(69).get(2));
        if (selector.numberOfMatchesWithParam[69] > 0 ){
            selfAverageAerialsWon = selfAverageAerialsWon / selector.numberOfMatchesWithParam[69];
            opAverageAerialsWon   = opAverageAerialsWon   / selector.numberOfMatchesWithParam[69];
        }
        double totalAverageAerialsWon = selfAverageAerialsWon + opAverageAerialsWon;

        if (MyMath.round(totalAverageAerialsWon, 0) > totalAverageAerialsWon)
            totalAverageAerialsWon = MyMath.round(totalAverageAerialsWon, 0) - 0.5;
        else
            totalAverageAerialsWon = MyMath.round(totalAverageAerialsWon, 0) + 0.5;
        if (MyMath.round(selfAverageAerialsWon, 0) > selfAverageAerialsWon)
            selfAverageAerialsWon = MyMath.round(selfAverageAerialsWon, 0) - 0.5;
        else
            selfAverageAerialsWon = MyMath.round(selfAverageAerialsWon, 0) + 0.5;
        if (MyMath.round(opAverageAerialsWon, 0) > opAverageAerialsWon)
            opAverageAerialsWon = MyMath.round(opAverageAerialsWon, 0) - 0.5;
        else
            opAverageAerialsWon = MyMath.round(opAverageAerialsWon, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeAerialsWon + list.get(i).awayAerialsWon > 0) {
                if (list.get(i).homeAerialsWon + list.get(i).awayAerialsWon > (totalAverageAerialsWon-1))
                    totalMinus1++;
                if (list.get(i).homeAerialsWon + list.get(i).awayAerialsWon > totalAverageAerialsWon)
                    totalSred++;
                if (list.get(i).homeAerialsWon + list.get(i).awayAerialsWon > (totalAverageAerialsWon+1))
                    totalPlus1++;

                if ((list.get(i).homeAerialsWon > (selfAverageAerialsWon+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayAerialsWon > (selfAverageAerialsWon+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeAerialsWon > (selfAverageAerialsWon)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayAerialsWon > (selfAverageAerialsWon)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeAerialsWon > (selfAverageAerialsWon-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayAerialsWon > (selfAverageAerialsWon-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeAerialsWon > (opAverageAerialsWon+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayAerialsWon > (opAverageAerialsWon+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeAerialsWon > (opAverageAerialsWon)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayAerialsWon > (opAverageAerialsWon)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeAerialsWon > (opAverageAerialsWon-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayAerialsWon > (opAverageAerialsWon-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeAerialsWon > list.get(i).awayAerialsWon&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayAerialsWon > list.get(i).homeAerialsWon))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeAerialsWon >= list.get(i).awayAerialsWon&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayAerialsWon >= list.get(i).homeAerialsWon))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageAerialsWon - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageAerialsWon - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageAerialsWon + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageAerialsWon + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageAerialsWon + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageAerialsWon + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageAerialsWon + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageAerialsWon;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageAerialsWon - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageAerialsWon + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageAerialsWon;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageAerialsWon - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableGoalKicks(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageGoalKicks = Double.parseDouble(selector.pList.get(78).get(1));
        double opAverageGoalsKicks = Double.parseDouble(selector.pList.get(78).get(2));
        if (selector.numberOfMatchesWithParam[78] > 0 ){
            selfAverageGoalKicks = selfAverageGoalKicks / selector.numberOfMatchesWithParam[78];
            opAverageGoalsKicks   = opAverageGoalsKicks   / selector.numberOfMatchesWithParam[78];
        }
        double totalAverageGoalKicks = selfAverageGoalKicks + opAverageGoalsKicks;

        if (MyMath.round(totalAverageGoalKicks, 0) > totalAverageGoalKicks)
            totalAverageGoalKicks = MyMath.round(totalAverageGoalKicks, 0) - 0.5;
        else
            totalAverageGoalKicks = MyMath.round(totalAverageGoalKicks, 0) + 0.5;
        if (MyMath.round(selfAverageGoalKicks, 0) > selfAverageGoalKicks)
            selfAverageGoalKicks = MyMath.round(selfAverageGoalKicks, 0) - 0.5;
        else
            selfAverageGoalKicks = MyMath.round(selfAverageGoalKicks, 0) + 0.5;
        if (MyMath.round(opAverageGoalsKicks, 0) > opAverageGoalsKicks)
            opAverageGoalsKicks = MyMath.round(opAverageGoalsKicks, 0) - 0.5;
        else
            opAverageGoalsKicks = MyMath.round(opAverageGoalsKicks, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeGoalKicks + list.get(i).awayGoalKicks > 0) {
                if (list.get(i).homeGoalKicks + list.get(i).awayGoalKicks > (totalAverageGoalKicks-1))
                    totalMinus1++;
                if (list.get(i).homeGoalKicks + list.get(i).awayGoalKicks > totalAverageGoalKicks)
                    totalSred++;
                if (list.get(i).homeGoalKicks + list.get(i).awayGoalKicks > (totalAverageGoalKicks+1))
                    totalPlus1++;

                if ((list.get(i).homeGoalKicks > (selfAverageGoalKicks+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayGoalKicks > (selfAverageGoalKicks+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeGoalKicks > (selfAverageGoalKicks)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayGoalKicks > (selfAverageGoalKicks)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeGoalKicks > (selfAverageGoalKicks-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayGoalKicks > (selfAverageGoalKicks-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeGoalKicks > (opAverageGoalsKicks+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayGoalKicks > (opAverageGoalsKicks+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeGoalKicks > (opAverageGoalsKicks)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayGoalKicks > (opAverageGoalsKicks)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeGoalKicks > (opAverageGoalsKicks-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayGoalKicks > (opAverageGoalsKicks-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeGoalKicks > list.get(i).awayGoalKicks&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayGoalKicks > list.get(i).homeGoalKicks))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeGoalKicks >= list.get(i).awayGoalKicks&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayGoalKicks >= list.get(i).homeGoalKicks))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageGoalKicks - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageGoalKicks - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageGoalKicks + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageGoalKicks + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageGoalKicks + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageGoalKicks + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageGoalKicks + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageGoalKicks;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageGoalKicks - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageGoalsKicks + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageGoalsKicks;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageGoalsKicks - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableThrowIns(String teamName, Selector selector){
        double win = 0;
        double winX = 0;
        ArrayList<Match> list = selector.listOfMatches;
        int number = 0;
        double selfAverageThrowIns = Double.parseDouble(selector.pList.get(81).get(1));
        double opAverageThrowIns = Double.parseDouble(selector.pList.get(81).get(2));
        if (selector.numberOfMatchesWithParam[81] > 0 ){
            selfAverageThrowIns = selfAverageThrowIns / selector.numberOfMatchesWithParam[81];
            opAverageThrowIns   = opAverageThrowIns   / selector.numberOfMatchesWithParam[81];
        }
        double totalAverageThrowIns = selfAverageThrowIns + opAverageThrowIns;

        if (MyMath.round(totalAverageThrowIns, 0) > totalAverageThrowIns)
            totalAverageThrowIns = MyMath.round(totalAverageThrowIns, 0) - 0.5;
        else
            totalAverageThrowIns = MyMath.round(totalAverageThrowIns, 0) + 0.5;
        if (MyMath.round(selfAverageThrowIns, 0) > selfAverageThrowIns)
            selfAverageThrowIns = MyMath.round(selfAverageThrowIns, 0) - 0.5;
        else
            selfAverageThrowIns = MyMath.round(selfAverageThrowIns, 0) + 0.5;
        if (MyMath.round(opAverageThrowIns, 0) > opAverageThrowIns)
            opAverageThrowIns = MyMath.round(opAverageThrowIns, 0) - 0.5;
        else
            opAverageThrowIns = MyMath.round(opAverageThrowIns, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double selfSred = 0;
        double selfPlus1 = 0;
        double selfMinus1 = 0;
        double opSred = 0;
        double opPlus1 = 0;
        double opMinus1 = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).homeThrowIns + list.get(i).awayThrowIns > 0) {
                if (list.get(i).homeThrowIns + list.get(i).awayThrowIns > (totalAverageThrowIns-1))
                    totalMinus1++;
                if (list.get(i).homeThrowIns + list.get(i).awayThrowIns > totalAverageThrowIns)
                    totalSred++;
                if (list.get(i).homeThrowIns + list.get(i).awayThrowIns > (totalAverageThrowIns+1))
                    totalPlus1++;

                if ((list.get(i).homeThrowIns > (selfAverageThrowIns+1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayThrowIns > (selfAverageThrowIns+1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfPlus1++;
                if ((list.get(i).homeThrowIns > (selfAverageThrowIns)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayThrowIns > (selfAverageThrowIns)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfSred++;
                if ((list.get(i).homeThrowIns > (selfAverageThrowIns-1)&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayThrowIns > (selfAverageThrowIns-1)))&&(teamName.equals(list.get(i).awayTeam))))
                    selfMinus1++;

                if ((list.get(i).homeThrowIns > (opAverageThrowIns+1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayThrowIns > (opAverageThrowIns+1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opPlus1++;
                if ((list.get(i).homeThrowIns > (opAverageThrowIns)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayThrowIns > (opAverageThrowIns)))&&(teamName.equals(list.get(i).homeTeam))))
                    opSred++;
                if ((list.get(i).homeThrowIns > (opAverageThrowIns-1)&&(teamName.equals(list.get(i).awayTeam))||((list.get(i).awayThrowIns > (opAverageThrowIns-1)))&&(teamName.equals(list.get(i).homeTeam))))
                    opMinus1++;
                if ((list.get(i).homeThrowIns > list.get(i).awayThrowIns&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayThrowIns > list.get(i).homeThrowIns))&&(teamName.equals(list.get(i).awayTeam))))
                    win++;
                if ((list.get(i).homeThrowIns >= list.get(i).awayThrowIns&&(teamName.equals(list.get(i).homeTeam))||((list.get(i).awayThrowIns >= list.get(i).homeThrowIns))&&(teamName.equals(list.get(i).awayTeam))))
                    winX++;
                number++;
            }

        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageThrowIns - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageThrowIns - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageThrowIns + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageThrowIns + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageThrowIns + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageThrowIns + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = selfAverageThrowIns + 1;
            String itbPlus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBPlus1 = (int) (selfPlus1) + "/" + number + " (" + MyMath.round(selfPlus1 / number * 100, 1) + ")";
            if (selfPlus1/number < 0.5){
                itbPlus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBPlus1 = (int) (number - selfPlus1) + "/" + number + " (" + MyMath.round((number - selfPlus1) / number * 100, 1) + ")";
            }

            total = selfAverageThrowIns;
            String itbSred = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBSred = (int) (selfSred) + "/" + number + " (" + MyMath.round(selfSred / number * 100, 1) + ")";
            if (selfSred/number < 0.5){
                itbSred = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBSred = (int) (number - selfSred) + "/" + number + " (" + MyMath.round((number - selfSred) / number * 100, 1) + ")";
            }

            total = selfAverageThrowIns - 1;
            String itbMinus1 = Team.getShortName(teamName) + ": ТБ(" + total + ")";
            String selfTBMinus1 = (int) (selfMinus1) + "/" + number + " (" + MyMath.round(selfMinus1 / number * 100, 1) + ")";
            if (selfMinus1/number < 0.5){
                itbMinus1 = Team.getShortName(teamName) + ": ТM(" + total + ")";
                selfTBMinus1 = (int) (number - selfMinus1) + "/" + number + " (" + MyMath.round((number - selfMinus1) / number * 100, 1) + ")";
            }

            total = opAverageThrowIns + 1;
            String optbPlus1 = "Opp: ТБ(" + total + ")";
            String opTBPlus1 = (int) (opPlus1) + "/" + number + " (" + MyMath.round(opPlus1 / number * 100, 1) + ")";
            if (opPlus1/number < 0.5){
                optbPlus1 = "Opp: ТM(" + total + ")";
                opTBPlus1 = (int) (number - opPlus1) + "/" + number + " (" + MyMath.round((number - opPlus1) / number * 100, 1) + ")";
            }

            total = opAverageThrowIns;
            String optbSred = "Opp: ТБ(" + total + ")";
            String opTBSred = (int) (opSred) + "/" + number + " (" + MyMath.round(opSred / number * 100, 1) + ")";
            if (opSred/number < 0.5){
                optbSred = "Opp: ТM(" + total + ")";
                opTBSred = (int) (number - opSred) + "/" + number + " (" + MyMath.round((number - opSred) / number * 100, 1) + ")";
            }

            total = opAverageThrowIns - 1;
            String optbMinus1 = "Opp: ТБ(" + total + ")";
            String opTBMinus1 = (int) (opMinus1) + "/" + number + " (" + MyMath.round(opMinus1 / number * 100, 1) + ")";
            if (opMinus1/number < 0.5){
                optbMinus1 = "Opp: ТM(" + total + ")";
                opTBMinus1 = (int) (number - opMinus1) + "/" + number + " (" + MyMath.round((number - opMinus1) / number * 100, 1) + ")";
            }

            String winS = Team.getShortName(teamName) + ": Ф(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = Team.getShortName(teamName) + ": Ф(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }
        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238,238,238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JTable getTableCorrelation(String teamName, ArrayList<Match> list, ArrayList<ArrayList<String>> pList){
        String[] headsCorr = {"Корреляции " + teamName , "Собственные", "Противник", "Фора" };

        double corPossCorners = Correlator.getCorrelationOfParams(teamName, "Владение", "Угловые", list, pList);
        double corPossCornersOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "УгловыеПротивника", list, pList);
        double corPossCornersHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "УгловыеФора", list, pList);
        double corPossFouls = Correlator.getCorrelationOfParams(teamName, "Владение", "Фолы", list, pList);
        double corPossFoulsOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "ФолыПротивника", list, pList);
        double corPossFoulsHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "ФолыФора", list, pList);
        double corPossUSV = Correlator.getCorrelationOfParams(teamName, "Владение", "УдарыВСтвор", list, pList);
        double corPossUSVOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "УдарыВСтворПротивника", list, pList);
        double corPossUSVHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "УдарыВСтворФора", list, pList);
        double corPossOffsides = Correlator.getCorrelationOfParams(teamName, "Владение", "Офсайды", list, pList);
        double corPossOffsidesOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "ОфсайдыПротивника", list, pList);
        double corPossOffsidesHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "ОфсайдыФора", list, pList);
        double corPossYC = Correlator.getCorrelationOfParams(teamName, "Владение", "ЖК", list, pList);
        double corPossYCOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "ЖКПротивника", list, pList);
        double corPossYCHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "ЖКФора", list, pList);
        double corPossThrowIns = Correlator.getCorrelationOfParams(teamName, "Владение", "Ауты", list, pList);
        double corPossThrowInsOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "АутыПротивника", list, pList);
        double corPossThrowInsHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "АутыФора", list, pList);
        double corPossGoalKicks = Correlator.getCorrelationOfParams(teamName, "Владение", "УдарыОтВорот", list, pList);
        double corPossGoalKicksOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "УдарыОтВоротПротивника", list, pList);
        double corPossGoalKicksHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "УдарыОтВоротФора", list, pList);
        double corShotsUSV = Correlator.getCorrelationOfParams(teamName, "Удары", "УдарыВСтвор", list, pList);
        double corShotsUSVOpponent = Correlator.getCorrelationOfParams(teamName, "УдарыПротивника", "УдарыВСтворПротивника", list, pList);
        double corShotsUSVHandicap = Correlator.getCorrelationOfParams(teamName, "УдарыФора", "УдарыВСтворФора", list, pList);
        double corShotsCorners = Correlator.getCorrelationOfParams(teamName, "Удары", "Угловые", list, pList);
        double corShotsCornersOpponent = Correlator.getCorrelationOfParams(teamName, "УдарыПротивника", "УгловыеПротивника", list, pList);
        double corShotsCornersHandicap = Correlator.getCorrelationOfParams(teamName, "УдарыФора", "УгловыеФора", list, pList);
        double corFoulsYC = Correlator.getCorrelationOfParams(teamName, "Фолы", "ЖК", list, pList);
        double corFoulsYCOpponent = Correlator.getCorrelationOfParams(teamName, "ФолыПротивника", "ЖКПротивника", list, pList);
        double corFoulsYCHandicap = Correlator.getCorrelationOfParams(teamName, "ФолыФора", "ЖКФора", list, pList);
        double corPossShots = Correlator.getCorrelationOfParams(teamName, "Владение", "Удары", list, pList);
        double corPossShotsOpponent = Correlator.getCorrelationOfParams(teamName, "ВладениеПротивника", "УдарыПротивника", list, pList);
        double corPossShotsHandicap = Correlator.getCorrelationOfParams(teamName, "Владение", "УдарыФора", list, pList);
        double corUVSCorners = Correlator.getCorrelationOfParams(teamName, "УдарыВСтвор", "Угловые", list, pList);
        double corUVSCornersOpponent = Correlator.getCorrelationOfParams(teamName, "УдарыВСтворПротивника", "УгловыеПротивника", list, pList);
        double corUVSCornersHandicap = Correlator.getCorrelationOfParams(teamName, "УдарыВСтворФора", "УгловыеФора", list, pList);
        double corCornersYC = Correlator.getCorrelationOfParams(teamName, "Угловые", "ЖК", list, pList);
        double corCornersYCOpponent = Correlator.getCorrelationOfParams(teamName, "УгловыеПротивника", "ЖКПротивника", list, pList);
        double corCornersYCHandicap = Correlator.getCorrelationOfParams(teamName, "УгловыеФора", "ЖКФора", list, pList);

        Object[][] dataCorr = {
                {"Владение и угловые", String.valueOf(MyMath.round(corPossCorners, 3)),
                        String.valueOf(MyMath.round(corPossCornersOpponent, 3)),
                        String.valueOf(MyMath.round(corPossCornersHandicap, 3))},
                {"Владение и фолы", String.valueOf(MyMath.round(corPossFouls, 3)),
                        String.valueOf(MyMath.round(corPossFoulsOpponent, 3)),
                        String.valueOf(MyMath.round(corPossFoulsHandicap, 3))},
                {"Владение и удары", String.valueOf(MyMath.round(corPossShots, 3)),
                        String.valueOf(MyMath.round(corPossShotsOpponent, 3)),
                        String.valueOf(MyMath.round(corPossShotsHandicap, 3))},
                {"Владение и удары в створ", String.valueOf(MyMath.round(corPossUSV, 3)),
                        String.valueOf(MyMath.round(corPossUSVOpponent, 3)),
                        String.valueOf(MyMath.round(corPossUSVHandicap, 3))},
                {"Владение и офсайды", String.valueOf(MyMath.round(corPossOffsides, 3)),
                        String.valueOf(MyMath.round(corPossOffsidesOpponent, 3)),
                        String.valueOf(MyMath.round(corPossOffsidesHandicap, 3))},
                {"Владение и ЖК", String.valueOf(MyMath.round(corPossYC, 3)),
                        String.valueOf(MyMath.round(corPossYCOpponent, 3)),
                        String.valueOf(MyMath.round(corPossYCHandicap, 3))},
                {"Владение и ауты", String.valueOf(MyMath.round(corPossThrowIns, 3)),
                        String.valueOf(MyMath.round(corPossThrowInsOpponent, 3)),
                        String.valueOf(MyMath.round(corPossThrowInsHandicap, 3))},
                {"Владение и удары от ворот", String.valueOf(MyMath.round(corPossGoalKicks, 3)),
                        String.valueOf(MyMath.round(corPossGoalKicksOpponent, 3)),
                        String.valueOf(MyMath.round(corPossGoalKicksHandicap, 3))},
                {"Удары и УВС", String.valueOf(MyMath.round(corShotsUSV, 3)),
                        String.valueOf(MyMath.round(corShotsUSVOpponent, 3)),
                        String.valueOf(MyMath.round(corShotsUSVHandicap, 3))},
                {"Удары и угловые", String.valueOf(MyMath.round(corShotsCorners, 3)),
                        String.valueOf(MyMath.round(corShotsCornersOpponent, 3)),
                        String.valueOf(MyMath.round(corShotsCornersHandicap, 3))},
                {"Удары в створ и угловые", String.valueOf(MyMath.round(corUVSCorners, 3)),
                        String.valueOf(MyMath.round(corUVSCornersOpponent, 3)),
                        String.valueOf(MyMath.round(corUVSCornersHandicap, 3))},
                {"Угловые и ЖК", String.valueOf(MyMath.round(corCornersYC, 3)),
                        String.valueOf(MyMath.round(corCornersYCOpponent, 3)),
                        String.valueOf(MyMath.round(corCornersYCHandicap, 3))},
                {"Фолы и ЖК", String.valueOf(MyMath.round(corFoulsYC, 3)),
                        String.valueOf(MyMath.round(corFoulsYCOpponent, 3)),
                        String.valueOf(MyMath.round(corFoulsYCHandicap, 3))}
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableCorr = new JTable(dataCorr, headsCorr);
        tableCorr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableCorr.setEnabled(false);
        tableCorr.getTableHeader().setFont(font);
        tableCorr.setFont(font);
        tableCorr.getColumnModel().getColumn(0).setPreferredWidth(273);
        tableCorr.setRowHeight(25);
        tableCorr.getColumnModel().getColumn(1).setPreferredWidth(140);
        tableCorr.getColumnModel().getColumn(2).setPreferredWidth(140);
        tableCorr.getColumnModel().getColumn(3).setPreferredWidth(140);
        Renderer renderer = new Renderer(2);
        renderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i=0; i<tableCorr.getColumnCount();i++){
            tableCorr.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        return tableCorr;
    }

    public static JPanel getTableFoulsOfRef(Selector selector){
        double win = 0;
        double winX = 0;
        int number = selector.refNumberOfMatchesWithParam[5];
        double totalAverageFouls = Double.parseDouble(selector.refList.get(5).get(1));
        double homeAverageFouls = Double.parseDouble(selector.refList.get(5).get(2));
        double awayAverageFouls = Double.parseDouble(selector.refList.get(5).get(3));
        if (number > 0){
            totalAverageFouls= totalAverageFouls / number;
            homeAverageFouls = homeAverageFouls  / number;
            awayAverageFouls = awayAverageFouls  / number;
        }

        if (MyMath.round(totalAverageFouls, 0) > totalAverageFouls)
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) - 0.5;
        else
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) + 0.5;
        if (MyMath.round(homeAverageFouls, 0) > homeAverageFouls)
            homeAverageFouls = MyMath.round(homeAverageFouls, 0) - 0.5;
        else
            homeAverageFouls = MyMath.round(homeAverageFouls, 0) + 0.5;
        if (MyMath.round(awayAverageFouls, 0) > awayAverageFouls)
            awayAverageFouls = MyMath.round(awayAverageFouls, 0) - 0.5;
        else
            awayAverageFouls = MyMath.round(awayAverageFouls, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double homeSred = 0;
        double homePlus1 = 0;
        double homeMinus1 = 0;
        double awaySred = 0;
        double awayPlus1 = 0;
        double awayMinus1 = 0;

        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeFouls + selector.listOfMatches.get(i).awayFouls > 0){
                if (selector.listOfMatches.get(i).homeFouls + selector.listOfMatches.get(i).awayFouls > (totalAverageFouls-1))
                    totalMinus1++;
                if (selector.listOfMatches.get(i).homeFouls + selector.listOfMatches.get(i).awayFouls > totalAverageFouls)
                    totalSred++;
                if (selector.listOfMatches.get(i).homeFouls + selector.listOfMatches.get(i).awayFouls > (totalAverageFouls+1))
                    totalPlus1++;

                if (selector.listOfMatches.get(i).homeFouls > (homeAverageFouls+1))
                    homePlus1++;
                if (selector.listOfMatches.get(i).homeFouls > (homeAverageFouls))
                    homeSred++;
                if (selector.listOfMatches.get(i).homeFouls > (homeAverageFouls-1))
                    homeMinus1++;

                if (selector.listOfMatches.get(i).awayFouls > (awayAverageFouls+1))
                    awayPlus1++;
                if (selector.listOfMatches.get(i).awayFouls > (awayAverageFouls))
                    awaySred++;
                if (selector.listOfMatches.get(i).awayFouls > (awayAverageFouls-1))
                    awayMinus1++;
                if (selector.listOfMatches.get(i).homeFouls > selector.listOfMatches.get(i).awayFouls)
                    win++;
                if (selector.listOfMatches.get(i).homeFouls >= selector.listOfMatches.get(i).awayFouls)
                    winX++;
            }
        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageFouls - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageFouls - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageFouls + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageFouls + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageFouls + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageFouls + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = homeAverageFouls + 1;
            String itbPlus1 = "Хозяева: ТБ(" + total + ")";
            String selfTBPlus1 = (int) (homePlus1) + "/" + number + " (" + MyMath.round(homePlus1 / number * 100, 1) + ")";
            if (homePlus1/number < 0.5){
                itbPlus1 = "Хозяева: ТM(" + total + ")";
                selfTBPlus1 = (int) (number - homePlus1) + "/" + number + " (" + MyMath.round((number - homePlus1) / number * 100, 1) + ")";
            }

            total = homeAverageFouls;
            String itbSred = "Хозяева: ТБ(" + total + ")";
            String selfTBSred = (int) (homeSred) + "/" + number + " (" + MyMath.round(homeSred / number * 100, 1) + ")";
            if (homeSred/number < 0.5){
                itbSred = "Хозяева: ТM(" + total + ")";
                selfTBSred = (int) (number - homeSred) + "/" + number + " (" + MyMath.round((number - homeSred) / number * 100, 1) + ")";
            }

            total = homeAverageFouls - 1;
            String itbMinus1 = "Хозяева: ТБ(" + total + ")";
            String selfTBMinus1 = (int) (homeMinus1) + "/" + number + " (" + MyMath.round(homeMinus1 / number * 100, 1) + ")";
            if (homeMinus1/number < 0.5){
                itbMinus1 = "Хозяева: ТM(" + total + ")";
                selfTBMinus1 = (int) (number - homeMinus1) + "/" + number + " (" + MyMath.round((number - homeMinus1) / number * 100, 1) + ")";
            }

            total = awayAverageFouls + 1;
            String optbPlus1 = "Гости: ТБ(" + total + ")";
            String opTBPlus1 = (int) (awayPlus1) + "/" + number + " (" + MyMath.round(awayPlus1 / number * 100, 1) + ")";
            if (awayPlus1/number < 0.5){
                optbPlus1 = "Гости: ТM(" + total + ")";
                opTBPlus1 = (int) (number - awayPlus1) + "/" + number + " (" + MyMath.round((number - awayPlus1) / number * 100, 1) + ")";
            }

            total = awayAverageFouls;
            String optbSred = "Гости: ТБ(" + total + ")";
            String opTBSred = (int) (awaySred) + "/" + number + " (" + MyMath.round(awaySred / number * 100, 1) + ")";
            if (awaySred/number < 0.5){
                optbSred = "Гости: ТM(" + total + ")";
                opTBSred = (int) (number - awaySred) + "/" + number + " (" + MyMath.round((number - awaySred) / number * 100, 1) + ")";
            }

            total = awayAverageFouls - 1;
            String optbMinus1 = "Гости: ТБ(" + total + ")";
            String opTBMinus1 = (int) (awayMinus1) + "/" + number + " (" + MyMath.round(awayMinus1 / number * 100, 1) + ")";
            if (awayMinus1/number < 0.5){
                optbMinus1 = "Гости: ТM(" + total + ")";
                opTBMinus1 = (int) (number - awayMinus1) + "/" + number + " (" + MyMath.round((number - awayMinus1) / number * 100, 1) + ")";
            }

            String winS = "Ф1(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = "Ф1(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableFouls1TOfRef(Selector selector){
        double win = 0;
        double winX = 0;
        int number = selector.refNumberOfMatchesWithParam[5];
        double totalAverageFouls = Double.parseDouble(selector.refList.get(5).get(1));
        double homeAverageFouls = Double.parseDouble(selector.refList.get(5).get(2));
        double awayAverageFouls = Double.parseDouble(selector.refList.get(5).get(3));
        if (number > 0){
            totalAverageFouls= totalAverageFouls / number;
            homeAverageFouls = homeAverageFouls  / number;
            awayAverageFouls = awayAverageFouls  / number;
        }

        if (MyMath.round(totalAverageFouls, 0) > totalAverageFouls)
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) - 0.5;
        else
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) + 0.5;
        if (MyMath.round(homeAverageFouls, 0) > homeAverageFouls)
            homeAverageFouls = MyMath.round(homeAverageFouls, 0) - 0.5;
        else
            homeAverageFouls = MyMath.round(homeAverageFouls, 0) + 0.5;
        if (MyMath.round(awayAverageFouls, 0) > awayAverageFouls)
            awayAverageFouls = MyMath.round(awayAverageFouls, 0) - 0.5;
        else
            awayAverageFouls = MyMath.round(awayAverageFouls, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double homeSred = 0;
        double homePlus1 = 0;
        double homeMinus1 = 0;
        double awaySred = 0;
        double awayPlus1 = 0;
        double awayMinus1 = 0;

        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeFouls1T + selector.listOfMatches.get(i).awayFouls1T > 0){
                if (selector.listOfMatches.get(i).homeFouls1T + selector.listOfMatches.get(i).awayFouls1T > (totalAverageFouls-1))
                    totalMinus1++;
                if (selector.listOfMatches.get(i).homeFouls1T + selector.listOfMatches.get(i).awayFouls1T > totalAverageFouls)
                    totalSred++;
                if (selector.listOfMatches.get(i).homeFouls1T + selector.listOfMatches.get(i).awayFouls1T > (totalAverageFouls+1))
                    totalPlus1++;

                if (selector.listOfMatches.get(i).homeFouls1T > (homeAverageFouls+1))
                    homePlus1++;
                if (selector.listOfMatches.get(i).homeFouls1T > (homeAverageFouls))
                    homeSred++;
                if (selector.listOfMatches.get(i).homeFouls1T > (homeAverageFouls-1))
                    homeMinus1++;

                if (selector.listOfMatches.get(i).awayFouls1T > (awayAverageFouls+1))
                    awayPlus1++;
                if (selector.listOfMatches.get(i).awayFouls1T > (awayAverageFouls))
                    awaySred++;
                if (selector.listOfMatches.get(i).awayFouls1T > (awayAverageFouls-1))
                    awayMinus1++;
                if (selector.listOfMatches.get(i).homeFouls1T > selector.listOfMatches.get(i).awayFouls1T)
                    win++;
                if (selector.listOfMatches.get(i).homeFouls1T >= selector.listOfMatches.get(i).awayFouls1T)
                    winX++;
            }
        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageFouls - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageFouls - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageFouls + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageFouls + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageFouls + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageFouls + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = homeAverageFouls + 1;
            String itbPlus1 = "Хозяева: ТБ(" + total + ")";
            String selfTBPlus1 = (int) (homePlus1) + "/" + number + " (" + MyMath.round(homePlus1 / number * 100, 1) + ")";
            if (homePlus1/number < 0.5){
                itbPlus1 = "Хозяева: ТM(" + total + ")";
                selfTBPlus1 = (int) (number - homePlus1) + "/" + number + " (" + MyMath.round((number - homePlus1) / number * 100, 1) + ")";
            }

            total = homeAverageFouls;
            String itbSred = "Хозяева: ТБ(" + total + ")";
            String selfTBSred = (int) (homeSred) + "/" + number + " (" + MyMath.round(homeSred / number * 100, 1) + ")";
            if (homeSred/number < 0.5){
                itbSred = "Хозяева: ТM(" + total + ")";
                selfTBSred = (int) (number - homeSred) + "/" + number + " (" + MyMath.round((number - homeSred) / number * 100, 1) + ")";
            }

            total = homeAverageFouls - 1;
            String itbMinus1 = "Хозяева: ТБ(" + total + ")";
            String selfTBMinus1 = (int) (homeMinus1) + "/" + number + " (" + MyMath.round(homeMinus1 / number * 100, 1) + ")";
            if (homeMinus1/number < 0.5){
                itbMinus1 = "Хозяева: ТM(" + total + ")";
                selfTBMinus1 = (int) (number - homeMinus1) + "/" + number + " (" + MyMath.round((number - homeMinus1) / number * 100, 1) + ")";
            }

            total = awayAverageFouls + 1;
            String optbPlus1 = "Гости: ТБ(" + total + ")";
            String opTBPlus1 = (int) (awayPlus1) + "/" + number + " (" + MyMath.round(awayPlus1 / number * 100, 1) + ")";
            if (awayPlus1/number < 0.5){
                optbPlus1 = "Гости: ТM(" + total + ")";
                opTBPlus1 = (int) (number - awayPlus1) + "/" + number + " (" + MyMath.round((number - awayPlus1) / number * 100, 1) + ")";
            }

            total = awayAverageFouls;
            String optbSred = "Гости: ТБ(" + total + ")";
            String opTBSred = (int) (awaySred) + "/" + number + " (" + MyMath.round(awaySred / number * 100, 1) + ")";
            if (awaySred/number < 0.5){
                optbSred = "Гости: ТM(" + total + ")";
                opTBSred = (int) (number - awaySred) + "/" + number + " (" + MyMath.round((number - awaySred) / number * 100, 1) + ")";
            }

            total = awayAverageFouls - 1;
            String optbMinus1 = "Гости: ТБ(" + total + ")";
            String opTBMinus1 = (int) (awayMinus1) + "/" + number + " (" + MyMath.round(awayMinus1 / number * 100, 1) + ")";
            if (awayMinus1/number < 0.5){
                optbMinus1 = "Гости: ТM(" + total + ")";
                opTBMinus1 = (int) (number - awayMinus1) + "/" + number + " (" + MyMath.round((number - awayMinus1) / number * 100, 1) + ")";
            }

            String winS = "Ф1(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = "Ф1(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableFouls2TOfRef(Selector selector){
        double win = 0;
        double winX = 0;
        int number = selector.refNumberOfMatchesWithParam[7];
        double totalAverageFouls = Double.parseDouble(selector.refList.get(7).get(1));
        double homeAverageFouls = Double.parseDouble(selector.refList.get(7).get(2));
        double awayAverageFouls = Double.parseDouble(selector.refList.get(7).get(3));
        if (number > 0){
            totalAverageFouls= totalAverageFouls / number;
            homeAverageFouls = homeAverageFouls  / number;
            awayAverageFouls = awayAverageFouls  / number;
        }

        if (MyMath.round(totalAverageFouls, 0) > totalAverageFouls)
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) - 0.5;
        else
            totalAverageFouls = MyMath.round(totalAverageFouls, 0) + 0.5;
        if (MyMath.round(homeAverageFouls, 0) > homeAverageFouls)
            homeAverageFouls = MyMath.round(homeAverageFouls, 0) - 0.5;
        else
            homeAverageFouls = MyMath.round(homeAverageFouls, 0) + 0.5;
        if (MyMath.round(awayAverageFouls, 0) > awayAverageFouls)
            awayAverageFouls = MyMath.round(awayAverageFouls, 0) - 0.5;
        else
            awayAverageFouls = MyMath.round(awayAverageFouls, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double homeSred = 0;
        double homePlus1 = 0;
        double homeMinus1 = 0;
        double awaySred = 0;
        double awayPlus1 = 0;
        double awayMinus1 = 0;

        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeFouls2T + selector.listOfMatches.get(i).awayFouls2T > 0){
                if (selector.listOfMatches.get(i).homeFouls2T + selector.listOfMatches.get(i).awayFouls2T > (totalAverageFouls-1))
                    totalMinus1++;
                if (selector.listOfMatches.get(i).homeFouls2T + selector.listOfMatches.get(i).awayFouls2T > totalAverageFouls)
                    totalSred++;
                if (selector.listOfMatches.get(i).homeFouls2T + selector.listOfMatches.get(i).awayFouls2T > (totalAverageFouls+1))
                    totalPlus1++;

                if (selector.listOfMatches.get(i).homeFouls2T > (homeAverageFouls+1))
                    homePlus1++;
                if (selector.listOfMatches.get(i).homeFouls2T > (homeAverageFouls))
                    homeSred++;
                if (selector.listOfMatches.get(i).homeFouls2T > (homeAverageFouls-1))
                    homeMinus1++;

                if (selector.listOfMatches.get(i).awayFouls2T > (awayAverageFouls+1))
                    awayPlus1++;
                if (selector.listOfMatches.get(i).awayFouls2T > (awayAverageFouls))
                    awaySred++;
                if (selector.listOfMatches.get(i).awayFouls2T > (awayAverageFouls-1))
                    awayMinus1++;
                if (selector.listOfMatches.get(i).homeFouls2T > selector.listOfMatches.get(i).awayFouls2T)
                    win++;
                if (selector.listOfMatches.get(i).homeFouls2T >= selector.listOfMatches.get(i).awayFouls2T)
                    winX++;
            }
        }
        Object[] colHeads;
        Object[][] data;
        if (number > 0){
            String t85 = "ТБ(" + (totalAverageFouls - 1) + ")";
            String t85s = (int) (totalMinus1) + "/" + number + " (" + MyMath.round(totalMinus1 / number * 100, 1) + ")";
            if (totalMinus1/number < 0.5){
                t85 = "ТM(" + (totalAverageFouls - 1) + ")";
                t85s = (int) (number - totalMinus1) + "/" + number + " (" + MyMath.round((number - totalMinus1) / number * 100, 1) + ")";
            }
            String t95 = "ТБ(" + totalAverageFouls + ")";
            String t95s = (int) (totalSred) + "/" + number + " (" + MyMath.round(totalSred / number * 100, 1) + ")";
            if (totalSred/number < 0.5){
                t95 = "ТM(" + totalAverageFouls + ")";
                t95s = (int) (number - totalSred) + "/" + number + " (" + MyMath.round((number - totalSred) / number * 100, 1) + ")";
            }

            String t105 = "ТБ(" + (totalAverageFouls + 1) + ")";
            String t105s = (int) (totalPlus1) + "/" + number + " (" + MyMath.round(totalPlus1 / number * 100, 1) + ")";
            if (totalPlus1/number < 0.5){
                t105 = "ТM(" + (totalAverageFouls + 1) + ")";
                t105s = (int) (number - totalPlus1) + "/" + number + " (" + MyMath.round((number - totalPlus1) / number * 100, 1) + ")";
            }

            double total = homeAverageFouls + 1;
            String itbPlus1 = "Хозяева: ТБ(" + total + ")";
            String selfTBPlus1 = (int) (homePlus1) + "/" + number + " (" + MyMath.round(homePlus1 / number * 100, 1) + ")";
            if (homePlus1/number < 0.5){
                itbPlus1 = "Хозяева: ТM(" + total + ")";
                selfTBPlus1 = (int) (number - homePlus1) + "/" + number + " (" + MyMath.round((number - homePlus1) / number * 100, 1) + ")";
            }

            total = homeAverageFouls;
            String itbSred = "Хозяева: ТБ(" + total + ")";
            String selfTBSred = (int) (homeSred) + "/" + number + " (" + MyMath.round(homeSred / number * 100, 1) + ")";
            if (homeSred/number < 0.5){
                itbSred = "Хозяева: ТM(" + total + ")";
                selfTBSred = (int) (number - homeSred) + "/" + number + " (" + MyMath.round((number - homeSred) / number * 100, 1) + ")";
            }

            total = homeAverageFouls - 1;
            String itbMinus1 = "Хозяева: ТБ(" + total + ")";
            String selfTBMinus1 = (int) (homeMinus1) + "/" + number + " (" + MyMath.round(homeMinus1 / number * 100, 1) + ")";
            if (homeMinus1/number < 0.5){
                itbMinus1 = "Хозяева: ТM(" + total + ")";
                selfTBMinus1 = (int) (number - homeMinus1) + "/" + number + " (" + MyMath.round((number - homeMinus1) / number * 100, 1) + ")";
            }

            total = awayAverageFouls + 1;
            String optbPlus1 = "Гости: ТБ(" + total + ")";
            String opTBPlus1 = (int) (awayPlus1) + "/" + number + " (" + MyMath.round(awayPlus1 / number * 100, 1) + ")";
            if (awayPlus1/number < 0.5){
                optbPlus1 = "Гости: ТM(" + total + ")";
                opTBPlus1 = (int) (number - awayPlus1) + "/" + number + " (" + MyMath.round((number - awayPlus1) / number * 100, 1) + ")";
            }

            total = awayAverageFouls;
            String optbSred = "Гости: ТБ(" + total + ")";
            String opTBSred = (int) (awaySred) + "/" + number + " (" + MyMath.round(awaySred / number * 100, 1) + ")";
            if (awaySred/number < 0.5){
                optbSred = "Гости: ТM(" + total + ")";
                opTBSred = (int) (number - awaySred) + "/" + number + " (" + MyMath.round((number - awaySred) / number * 100, 1) + ")";
            }

            total = awayAverageFouls - 1;
            String optbMinus1 = "Гости: ТБ(" + total + ")";
            String opTBMinus1 = (int) (awayMinus1) + "/" + number + " (" + MyMath.round(awayMinus1 / number * 100, 1) + ")";
            if (awayMinus1/number < 0.5){
                optbMinus1 = "Гости: ТM(" + total + ")";
                opTBMinus1 = (int) (number - awayMinus1) + "/" + number + " (" + MyMath.round((number - awayMinus1) / number * 100, 1) + ")";
            }

            String winS = "Ф1(-0.5)";
            String winSS = (int) win + "/" + number + " (" + MyMath.round(win / number * 100, 1) + ")";

            String winXS = "Ф1(+0.5)";
            String winXSS = (int) winX + "/" + number + " (" + MyMath.round(winX / number * 100, 1) + ")";

            colHeads = new Object[]{"Ставка", "Заход и %"};
            data = new Object[][]{
                    {t85, t85s},
                    {t95, t95s},
                    {t105, t105s},
                    {itbMinus1, selfTBMinus1},
                    {itbSred, selfTBSred},
                    {itbPlus1, selfTBPlus1},
                    {optbMinus1, opTBMinus1},
                    {optbSred, opTBSred},
                    {optbPlus1, opTBPlus1},
                    {winS, winSS},
                    {winXS, winXSS},
            };
        } else {
            colHeads = new Object[]{"К сожалению,", ""};
            data = new Object[][]{
                    {"данных ", " нет"},
            };
        }

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableYCardsOfRef(Selector selector){
        double win = 0;
        double winX = 0;
        double totalAverageYellowCards = Double.parseDouble(selector.refList.get(0).get(1))/selector.listOfMatches.size();
        double homeAverageYellowCards = Double.parseDouble(selector.refList.get(0).get(2))/selector.listOfMatches.size();
        double awayAverageYellowCards = Double.parseDouble(selector.refList.get(0).get(3))/selector.listOfMatches.size();

        if (MyMath.round(totalAverageYellowCards, 0) > totalAverageYellowCards)
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) - 0.5;
        else
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) + 0.5;
        if (MyMath.round(homeAverageYellowCards, 0) > homeAverageYellowCards)
            homeAverageYellowCards = MyMath.round(homeAverageYellowCards, 0) - 0.5;
        else
            homeAverageYellowCards = MyMath.round(homeAverageYellowCards, 0) + 0.5;
        if (MyMath.round(awayAverageYellowCards, 0) > awayAverageYellowCards)
            awayAverageYellowCards = MyMath.round(awayAverageYellowCards, 0) - 0.5;
        else
            awayAverageYellowCards = MyMath.round(awayAverageYellowCards, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double homeSred = 0;
        double homePlus1 = 0;
        double homeMinus1 = 0;
        double awaySred = 0;
        double awayPlus1 = 0;
        double awayMinus1 = 0;

        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeYellowCards + selector.listOfMatches.get(i).awayYellowCards > (totalAverageYellowCards-1))
                totalMinus1++;
            if (selector.listOfMatches.get(i).homeYellowCards + selector.listOfMatches.get(i).awayYellowCards > totalAverageYellowCards)
                totalSred++;
            if (selector.listOfMatches.get(i).homeYellowCards + selector.listOfMatches.get(i).awayYellowCards > (totalAverageYellowCards+1))
                totalPlus1++;

            if (selector.listOfMatches.get(i).homeYellowCards > (homeAverageYellowCards+1))
                homePlus1++;
            if (selector.listOfMatches.get(i).homeYellowCards > (homeAverageYellowCards))
                homeSred++;
            if (selector.listOfMatches.get(i).homeYellowCards > (homeAverageYellowCards-1))
                homeMinus1++;

            if (selector.listOfMatches.get(i).awayYellowCards > (awayAverageYellowCards+1))
                awayPlus1++;
            if (selector.listOfMatches.get(i).awayYellowCards > (awayAverageYellowCards))
                awaySred++;
            if (selector.listOfMatches.get(i).awayYellowCards > (awayAverageYellowCards-1))
                awayMinus1++;
            if (selector.listOfMatches.get(i).homeYellowCards > selector.listOfMatches.get(i).awayYellowCards)
                win++;
            if (selector.listOfMatches.get(i).homeYellowCards >= selector.listOfMatches.get(i).awayYellowCards)
                winX++;
        }

        String t85 = "ТБ(" + (totalAverageYellowCards - 1) + ")";
        String t85s = (int) (totalMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalMinus1/selector.listOfMatches.size() < 0.5){
            t85 = "ТM(" + (totalAverageYellowCards - 1) + ")";
            t85s = (int) (selector.listOfMatches.size() - totalMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }
        String t95 = "ТБ(" + totalAverageYellowCards + ")";
        String t95s = (int) (totalSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalSred / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalSred/selector.listOfMatches.size() < 0.5){
            t95 = "ТM(" + totalAverageYellowCards + ")";
            t95s = (int) (selector.listOfMatches.size() - totalSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalSred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        String t105 = "ТБ(" + (totalAverageYellowCards + 1) + ")";
        String t105s = (int) (totalPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalPlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalPlus1/selector.listOfMatches.size() < 0.5){
            t105 = "ТM(" + (totalAverageYellowCards + 1) + ")";
            t105s = (int) (selector.listOfMatches.size() - totalPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalPlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        double total = homeAverageYellowCards + 1;
        String itbPlus1 = "Хозяева: ТБ(" + total + ")";
        String selfTBPlus1 = (int) (homePlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homePlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (homePlus1/selector.listOfMatches.size() < 0.5){
            itbPlus1 = "Хозяева: ТM(" + total + ")";
            selfTBPlus1 = (int) (selector.listOfMatches.size() - homePlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homePlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = homeAverageYellowCards;
        String itbSred = "Хозяева: ТБ(" + total + ")";
        String selfTBSred = (int) (homeSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homeSred / selector.listOfMatches.size() * 100, 1) + ")";
        if (homeSred/selector.listOfMatches.size() < 0.5){
            itbSred = "Хозяева: ТM(" + total + ")";
            selfTBSred = (int) (selector.listOfMatches.size() - homeSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homeSred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = homeAverageYellowCards - 1;
        String itbMinus1 = "Хозяева: ТБ(" + total + ")";
        String selfTBMinus1 = (int) (homeMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homeMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (homeMinus1/selector.listOfMatches.size() < 0.5){
            itbMinus1 = "Хозяева: ТM(" + total + ")";
            selfTBMinus1 = (int) (selector.listOfMatches.size() - homeMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homeMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards + 1;
        String optbPlus1 = "Гости: ТБ(" + total + ")";
        String opTBPlus1 = (int) (awayPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awayPlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (awayPlus1/selector.listOfMatches.size() < 0.5){
            optbPlus1 = "Гости: ТM(" + total + ")";
            opTBPlus1 = (int) (selector.listOfMatches.size() - awayPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awayPlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards;
        String optbSred = "Гости: ТБ(" + total + ")";
        String opTBSred = (int) (awaySred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awaySred / selector.listOfMatches.size() * 100, 1) + ")";
        if (awaySred/selector.listOfMatches.size() < 0.5){
            optbSred = "Гости: ТM(" + total + ")";
            opTBSred = (int) (selector.listOfMatches.size() - awaySred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awaySred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards - 1;
        String optbMinus1 = "Гости: ТБ(" + total + ")";
        String opTBMinus1 = (int) (awayMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awayMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (awayMinus1/selector.listOfMatches.size() < 0.5){
            optbMinus1 = "Гости: ТM(" + total + ")";
            opTBMinus1 = (int) (selector.listOfMatches.size() - awayMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awayMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        String winS = "Ф1(-0.5)";
        String winSS = (int) win + "/" + selector.listOfMatches.size() + " (" + MyMath.round(win / selector.listOfMatches.size() * 100, 1) + ")";

        String winXS = "Ф1(+0.5)";
        String winXSS = (int) winX + "/" + selector.listOfMatches.size() + " (" + MyMath.round(winX / selector.listOfMatches.size() * 100, 1) + ")";

        String[] colHeads = {"Ставка", "Заход и %"};
        Object[][] data = {
                {t85 , t85s},
                {t95 , t95s},
                {t105 , t105s},
                {itbMinus1 , selfTBMinus1},
                {itbSred , selfTBSred},
                {itbPlus1 , selfTBPlus1},
                {optbMinus1 , opTBMinus1},
                {optbSred , opTBSred},
                {optbPlus1 , opTBPlus1},
                {winS , winSS},
                {winXS , winXSS},
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableYCards1TOfRef(Selector selector){
        double win = 0;
        double winX = 0;
        double totalAverageYellowCards = Double.parseDouble(selector.refList.get(1).get(1))/selector.listOfMatches.size();
        double homeAverageYellowCards = Double.parseDouble(selector.refList.get(1).get(2))/selector.listOfMatches.size();
        double awayAverageYellowCards = Double.parseDouble(selector.refList.get(1).get(3))/selector.listOfMatches.size();

        if (MyMath.round(totalAverageYellowCards, 0) > totalAverageYellowCards)
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) - 0.5;
        else
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) + 0.5;
        if (MyMath.round(homeAverageYellowCards, 0) > homeAverageYellowCards)
            homeAverageYellowCards = MyMath.round(homeAverageYellowCards, 0) - 0.5;
        else
            homeAverageYellowCards = MyMath.round(homeAverageYellowCards, 0) + 0.5;
        if (MyMath.round(awayAverageYellowCards, 0) > awayAverageYellowCards)
            awayAverageYellowCards = MyMath.round(awayAverageYellowCards, 0) - 0.5;
        else
            awayAverageYellowCards = MyMath.round(awayAverageYellowCards, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double homeSred = 0;
        double homePlus1 = 0;
        double homeMinus1 = 0;
        double awaySred = 0;
        double awayPlus1 = 0;
        double awayMinus1 = 0;

        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeYellowCards1T + selector.listOfMatches.get(i).awayYellowCards1T > (totalAverageYellowCards-1))
                totalMinus1++;
            if (selector.listOfMatches.get(i).homeYellowCards1T + selector.listOfMatches.get(i).awayYellowCards1T > totalAverageYellowCards)
                totalSred++;
            if (selector.listOfMatches.get(i).homeYellowCards1T + selector.listOfMatches.get(i).awayYellowCards1T > (totalAverageYellowCards+1))
                totalPlus1++;

            if (selector.listOfMatches.get(i).homeYellowCards1T > (homeAverageYellowCards+1))
                homePlus1++;
            if (selector.listOfMatches.get(i).homeYellowCards1T > (homeAverageYellowCards))
                homeSred++;
            if (selector.listOfMatches.get(i).homeYellowCards1T > (homeAverageYellowCards-1))
                homeMinus1++;

            if (selector.listOfMatches.get(i).awayYellowCards1T > (awayAverageYellowCards+1))
                awayPlus1++;
            if (selector.listOfMatches.get(i).awayYellowCards1T > (awayAverageYellowCards))
                awaySred++;
            if (selector.listOfMatches.get(i).awayYellowCards1T > (awayAverageYellowCards-1))
                awayMinus1++;
            if (selector.listOfMatches.get(i).homeYellowCards1T > selector.listOfMatches.get(i).awayYellowCards1T)
                win++;
            if (selector.listOfMatches.get(i).homeYellowCards1T >= selector.listOfMatches.get(i).awayYellowCards1T)
                winX++;
        }

        String t85 = "ТБ(" + (totalAverageYellowCards - 1) + ")";
        String t85s = (int) (totalMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalMinus1/selector.listOfMatches.size() < 0.5){
            t85 = "ТM(" + (totalAverageYellowCards - 1) + ")";
            t85s = (int) (selector.listOfMatches.size() - totalMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }
        String t95 = "ТБ(" + totalAverageYellowCards + ")";
        String t95s = (int) (totalSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalSred / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalSred/selector.listOfMatches.size() < 0.5){
            t95 = "ТM(" + totalAverageYellowCards + ")";
            t95s = (int) (selector.listOfMatches.size() - totalSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalSred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        String t105 = "ТБ(" + (totalAverageYellowCards + 1) + ")";
        String t105s = (int) (totalPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalPlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalPlus1/selector.listOfMatches.size() < 0.5){
            t105 = "ТM(" + (totalAverageYellowCards + 1) + ")";
            t105s = (int) (selector.listOfMatches.size() - totalPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalPlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        double total = homeAverageYellowCards + 1;
        String itbPlus1 = "Хозяева: ТБ(" + total + ")";
        String selfTBPlus1 = (int) (homePlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homePlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (homePlus1/selector.listOfMatches.size() < 0.5){
            itbPlus1 = "Хозяева: ТM(" + total + ")";
            selfTBPlus1 = (int) (selector.listOfMatches.size() - homePlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homePlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = homeAverageYellowCards;
        String itbSred = "Хозяева: ТБ(" + total + ")";
        String selfTBSred = (int) (homeSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homeSred / selector.listOfMatches.size() * 100, 1) + ")";
        if (homeSred/selector.listOfMatches.size() < 0.5){
            itbSred = "Хозяева: ТM(" + total + ")";
            selfTBSred = (int) (selector.listOfMatches.size() - homeSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homeSred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = homeAverageYellowCards - 1;
        String itbMinus1 = "Хозяева: ТБ(" + total + ")";
        String selfTBMinus1 = (int) (homeMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homeMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (homeMinus1/selector.listOfMatches.size() < 0.5){
            itbMinus1 = "Хозяева: ТM(" + total + ")";
            selfTBMinus1 = (int) (selector.listOfMatches.size() - homeMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homeMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards + 1;
        String optbPlus1 = "Гости: ТБ(" + total + ")";
        String opTBPlus1 = (int) (awayPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awayPlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (awayPlus1/selector.listOfMatches.size() < 0.5){
            optbPlus1 = "Гости: ТM(" + total + ")";
            opTBPlus1 = (int) (selector.listOfMatches.size() - awayPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awayPlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards;
        String optbSred = "Гости: ТБ(" + total + ")";
        String opTBSred = (int) (awaySred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awaySred / selector.listOfMatches.size() * 100, 1) + ")";
        if (awaySred/selector.listOfMatches.size() < 0.5){
            optbSred = "Гости: ТM(" + total + ")";
            opTBSred = (int) (selector.listOfMatches.size() - awaySred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awaySred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards - 1;
        String optbMinus1 = "Гости: ТБ(" + total + ")";
        String opTBMinus1 = (int) (awayMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awayMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (awayMinus1/selector.listOfMatches.size() < 0.5){
            optbMinus1 = "Гости: ТM(" + total + ")";
            opTBMinus1 = (int) (selector.listOfMatches.size() - awayMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awayMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        String winS = "Ф1(-0.5)";
        String winSS = (int) win + "/" + selector.listOfMatches.size() + " (" + MyMath.round(win / selector.listOfMatches.size() * 100, 1) + ")";

        String winXS = "Ф1(+0.5)";
        String winXSS = (int) winX + "/" + selector.listOfMatches.size() + " (" + MyMath.round(winX / selector.listOfMatches.size() * 100, 1) + ")";

        String[] colHeads = {"Ставка", "Заход и %"};
        Object[][] data = {
                {t85 , t85s},
                {t95 , t95s},
                {t105 , t105s},
                {itbMinus1 , selfTBMinus1},
                {itbSred , selfTBSred},
                {itbPlus1 , selfTBPlus1},
                {optbMinus1 , opTBMinus1},
                {optbSred , opTBSred},
                {optbPlus1 , opTBPlus1},
                {winS , winSS},
                {winXS , winXSS},
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getTableYCards2TOfRef(Selector selector){
        double win = 0;
        double winX = 0;
        double totalAverageYellowCards = Double.parseDouble(selector.refList.get(2).get(1))/selector.listOfMatches.size();
        double homeAverageYellowCards = Double.parseDouble(selector.refList.get(2).get(2))/selector.listOfMatches.size();
        double awayAverageYellowCards = Double.parseDouble(selector.refList.get(2).get(3))/selector.listOfMatches.size();

        if (MyMath.round(totalAverageYellowCards, 0) > totalAverageYellowCards)
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) - 0.5;
        else
            totalAverageYellowCards = MyMath.round(totalAverageYellowCards, 0) + 0.5;
        if (MyMath.round(homeAverageYellowCards, 0) > homeAverageYellowCards)
            homeAverageYellowCards = MyMath.round(homeAverageYellowCards, 0) - 0.5;
        else
            homeAverageYellowCards = MyMath.round(homeAverageYellowCards, 0) + 0.5;
        if (MyMath.round(awayAverageYellowCards, 0) > awayAverageYellowCards)
            awayAverageYellowCards = MyMath.round(awayAverageYellowCards, 0) - 0.5;
        else
            awayAverageYellowCards = MyMath.round(awayAverageYellowCards, 0) + 0.5;
        double totalSred = 0;
        double totalPlus1 = 0;
        double totalMinus1 = 0;
        double homeSred = 0;
        double homePlus1 = 0;
        double homeMinus1 = 0;
        double awaySred = 0;
        double awayPlus1 = 0;
        double awayMinus1 = 0;

        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeYellowCards2T + selector.listOfMatches.get(i).awayYellowCards2T > (totalAverageYellowCards-1))
                totalMinus1++;
            if (selector.listOfMatches.get(i).homeYellowCards2T + selector.listOfMatches.get(i).awayYellowCards2T > totalAverageYellowCards)
                totalSred++;
            if (selector.listOfMatches.get(i).homeYellowCards2T + selector.listOfMatches.get(i).awayYellowCards2T > (totalAverageYellowCards+1))
                totalPlus1++;

            if (selector.listOfMatches.get(i).homeYellowCards2T > (homeAverageYellowCards+1))
                homePlus1++;
            if (selector.listOfMatches.get(i).homeYellowCards2T > (homeAverageYellowCards))
                homeSred++;
            if (selector.listOfMatches.get(i).homeYellowCards2T > (homeAverageYellowCards-1))
                homeMinus1++;

            if (selector.listOfMatches.get(i).awayYellowCards2T > (awayAverageYellowCards+1))
                awayPlus1++;
            if (selector.listOfMatches.get(i).awayYellowCards2T > (awayAverageYellowCards))
                awaySred++;
            if (selector.listOfMatches.get(i).awayYellowCards2T > (awayAverageYellowCards-1))
                awayMinus1++;
            if (selector.listOfMatches.get(i).homeYellowCards2T > selector.listOfMatches.get(i).awayYellowCards2T)
                win++;
            if (selector.listOfMatches.get(i).homeYellowCards2T >= selector.listOfMatches.get(i).awayYellowCards2T)
                winX++;
        }

        String t85 = "ТБ(" + (totalAverageYellowCards - 1) + ")";
        String t85s = (int) (totalMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalMinus1/selector.listOfMatches.size() < 0.5){
            t85 = "ТM(" + (totalAverageYellowCards - 1) + ")";
            t85s = (int) (selector.listOfMatches.size() - totalMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }
        String t95 = "ТБ(" + totalAverageYellowCards + ")";
        String t95s = (int) (totalSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalSred / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalSred/selector.listOfMatches.size() < 0.5){
            t95 = "ТM(" + totalAverageYellowCards + ")";
            t95s = (int) (selector.listOfMatches.size() - totalSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalSred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        String t105 = "ТБ(" + (totalAverageYellowCards + 1) + ")";
        String t105s = (int) (totalPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(totalPlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (totalPlus1/selector.listOfMatches.size() < 0.5){
            t105 = "ТM(" + (totalAverageYellowCards + 1) + ")";
            t105s = (int) (selector.listOfMatches.size() - totalPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - totalPlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        double total = homeAverageYellowCards + 1;
        String itbPlus1 = "Хозяева: ТБ(" + total + ")";
        String selfTBPlus1 = (int) (homePlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homePlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (homePlus1/selector.listOfMatches.size() < 0.5){
            itbPlus1 = "Хозяева: ТM(" + total + ")";
            selfTBPlus1 = (int) (selector.listOfMatches.size() - homePlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homePlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = homeAverageYellowCards;
        String itbSred = "Хозяева: ТБ(" + total + ")";
        String selfTBSred = (int) (homeSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homeSred / selector.listOfMatches.size() * 100, 1) + ")";
        if (homeSred/selector.listOfMatches.size() < 0.5){
            itbSred = "Хозяева: ТM(" + total + ")";
            selfTBSred = (int) (selector.listOfMatches.size() - homeSred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homeSred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = homeAverageYellowCards - 1;
        String itbMinus1 = "Хозяева: ТБ(" + total + ")";
        String selfTBMinus1 = (int) (homeMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(homeMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (homeMinus1/selector.listOfMatches.size() < 0.5){
            itbMinus1 = "Хозяева: ТM(" + total + ")";
            selfTBMinus1 = (int) (selector.listOfMatches.size() - homeMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - homeMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards + 1;
        String optbPlus1 = "Гости: ТБ(" + total + ")";
        String opTBPlus1 = (int) (awayPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awayPlus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (awayPlus1/selector.listOfMatches.size() < 0.5){
            optbPlus1 = "Гости: ТM(" + total + ")";
            opTBPlus1 = (int) (selector.listOfMatches.size() - awayPlus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awayPlus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards;
        String optbSred = "Гости: ТБ(" + total + ")";
        String opTBSred = (int) (awaySred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awaySred / selector.listOfMatches.size() * 100, 1) + ")";
        if (awaySred/selector.listOfMatches.size() < 0.5){
            optbSred = "Гости: ТM(" + total + ")";
            opTBSred = (int) (selector.listOfMatches.size() - awaySred) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awaySred) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        total = awayAverageYellowCards - 1;
        String optbMinus1 = "Гости: ТБ(" + total + ")";
        String opTBMinus1 = (int) (awayMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round(awayMinus1 / selector.listOfMatches.size() * 100, 1) + ")";
        if (awayMinus1/selector.listOfMatches.size() < 0.5){
            optbMinus1 = "Гости: ТM(" + total + ")";
            opTBMinus1 = (int) (selector.listOfMatches.size() - awayMinus1) + "/" + selector.listOfMatches.size() + " (" + MyMath.round((selector.listOfMatches.size() - awayMinus1) / selector.listOfMatches.size() * 100, 1) + ")";
        }

        String winS = "Ф1(-0.5)";
        String winSS = (int) win + "/" + selector.listOfMatches.size() + " (" + MyMath.round(win / selector.listOfMatches.size() * 100, 1) + ")";

        String winXS = "Ф1(+0.5)";
        String winXSS = (int) winX + "/" + selector.listOfMatches.size() + " (" + MyMath.round(winX / selector.listOfMatches.size() * 100, 1) + ")";

        String[] colHeads = {"Ставка", "Заход и %"};
        Object[][] data = {
                {t85 , t85s},
                {t95 , t95s},
                {t105 , t105s},
                {itbMinus1 , selfTBMinus1},
                {itbSred , selfTBSred},
                {itbPlus1 , selfTBPlus1},
                {optbMinus1 , opTBMinus1},
                {optbSred , opTBSred},
                {optbPlus1 , opTBPlus1},
                {winS , winSS},
                {winXS , winXSS},
        };

        Font font = new Font("Arial", Font.BOLD, 15);
        JTable tableUSV = new JTable(data, colHeads);
        tableUSV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUSV.setEnabled(false);
        tableUSV.getTableHeader().setFont(font);
        tableUSV.setFont(font);
        tableUSV.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUSV.setRowHeight(25);
        tableUSV.getColumnModel().getColumn(1).setPreferredWidth(92);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableUSV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableUSV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableUSV.setBackground(new Color(238, 238, 238));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 1));
        tablePanel.add(tableUSV, BorderLayout.CENTER);
        tablePanel.add(tableUSV.getTableHeader(), BorderLayout.NORTH);

        return tablePanel;
    }

    public static JPanel getPivotTable(String teamName, Selector selector, ArrayList<String> tournamentTable){
        JPanel result = new JPanel(new VerticalLayout());

        JLabel labelSummaryByOpponents = new JLabel("  Сводная таблица показателей в играх " + teamName + ".");
        //JLabel labelSummaryByOpponents2 = new JLabel("  Статистика " + teamName + " выводится СЛЕВА, а соперника - СПРАВА.");
        Font fontLabel = new Font("Arial", Font.BOLD, 15);
        labelSummaryByOpponents.setFont(fontLabel);
        //labelSummaryByOpponents2.setFont(fontLabel);
        result.add(labelSummaryByOpponents);
        //result.add(labelSummaryByOpponents2);

        JPanel panelOverallTable = new JPanel();
        String[] colHeadsSummaryByOpponents = {"Соперник", "Дом/Выезд", "Счет матча", "Владение", "Удары", "УВС", "Угловые", "ЖК"}; //"Офсайды", "Фолы", "КК"
        double[] averageSelfStats = new double[colHeadsSummaryByOpponents.length];
        double[] averageOppStats = new double[colHeadsSummaryByOpponents.length];
        Object[][] dataSumByOpps = new Object[selector.listOfMatches.size()+1][colHeadsSummaryByOpponents.length];
        for (int i=0; i<selector.listOfMatches.size(); i++){
            if (selector.listOfMatches.get(i).homeTeam.equals(teamName)){
                dataSumByOpps[i][0] = Team.getShortName(selector.listOfMatches.get(i).awayTeam) + "(" + League.getPositionInLeague(selector.listOfMatches.get(i).awayTeam, tournamentTable) + ")";
                dataSumByOpps[i][1] = "Дом";
                dataSumByOpps[i][2] = selector.listOfMatches.get(i).homeScore + " : " + selector.listOfMatches.get(i).awayScore;
                dataSumByOpps[i][3] = selector.listOfMatches.get(i).homeBallPossession + " : " + selector.listOfMatches.get(i).awayBallPossession;
                dataSumByOpps[i][4] = selector.listOfMatches.get(i).homeShots + " : " + selector.listOfMatches.get(i).awayShots;
                dataSumByOpps[i][5] = selector.listOfMatches.get(i).homeShotsOnTarget + " : " + selector.listOfMatches.get(i).awayShotsOnTarget;
                dataSumByOpps[i][6] = selector.listOfMatches.get(i).homeCorners + " : " + selector.listOfMatches.get(i).awayCorners;
                dataSumByOpps[i][7] = selector.listOfMatches.get(i).homeYellowCards + " : " + selector.listOfMatches.get(i).awayYellowCards;

                averageSelfStats[0] = 0;
                averageSelfStats[1] = 0 ;
                averageSelfStats[2] += selector.listOfMatches.get(i).homeScore ;
                averageSelfStats[3] += selector.listOfMatches.get(i).homeBallPossession ;
                averageSelfStats[4] += selector.listOfMatches.get(i).homeShots;
                averageSelfStats[5] += selector.listOfMatches.get(i).homeShotsOnTarget;
                averageSelfStats[6] += selector.listOfMatches.get(i).homeCorners;
                averageSelfStats[7] += selector.listOfMatches.get(i).homeYellowCards;

                averageOppStats[0] = 0;
                averageOppStats[1] = 0;
                averageOppStats[2] += selector.listOfMatches.get(i).awayScore ;
                averageOppStats[3] += selector.listOfMatches.get(i).awayBallPossession ;
                averageOppStats[4] += selector.listOfMatches.get(i).awayShots;
                averageOppStats[5] += selector.listOfMatches.get(i).awayShotsOnTarget;
                averageOppStats[6] += selector.listOfMatches.get(i).awayCorners;
                averageOppStats[7] += selector.listOfMatches.get(i).awayYellowCards;
            } else if (selector.listOfMatches.get(i).awayTeam.equals(teamName)){
                dataSumByOpps[i][0] = Team.getShortName(selector.listOfMatches.get(i).homeTeam) + "(" + League.getPositionInLeague(selector.listOfMatches.get(i).homeTeam, tournamentTable) + ")";
                dataSumByOpps[i][1] = "Выезд";
                dataSumByOpps[i][2] = selector.listOfMatches.get(i).homeScore + " : " + selector.listOfMatches.get(i).awayScore;
                dataSumByOpps[i][3] = selector.listOfMatches.get(i).homeBallPossession + " : " + selector.listOfMatches.get(i).awayBallPossession;
                dataSumByOpps[i][4] = selector.listOfMatches.get(i).homeShots + " : " + selector.listOfMatches.get(i).awayShots;
                dataSumByOpps[i][5] = selector.listOfMatches.get(i).homeShotsOnTarget + " : " + selector.listOfMatches.get(i).awayShotsOnTarget;
                dataSumByOpps[i][6] = selector.listOfMatches.get(i).homeCorners + " : " + selector.listOfMatches.get(i).awayCorners;
                dataSumByOpps[i][7] = selector.listOfMatches.get(i).homeYellowCards + " : " + selector.listOfMatches.get(i).awayYellowCards;

                averageSelfStats[0] = 0;
                averageSelfStats[1] = 0;
                averageSelfStats[2] += selector.listOfMatches.get(i).awayScore ;
                averageSelfStats[3] += selector.listOfMatches.get(i).awayBallPossession ;
                averageSelfStats[4] += selector.listOfMatches.get(i).awayShots;
                averageSelfStats[5] += selector.listOfMatches.get(i).awayShotsOnTarget;
                averageSelfStats[6] += selector.listOfMatches.get(i).awayCorners;
                averageSelfStats[7] += selector.listOfMatches.get(i).awayYellowCards;

                averageOppStats[0] = 0;
                averageOppStats[1] = 0;
                averageOppStats[2] += selector.listOfMatches.get(i).homeScore ;
                averageOppStats[3] += selector.listOfMatches.get(i).homeBallPossession ;
                averageOppStats[4] += selector.listOfMatches.get(i).homeShots;
                averageOppStats[5] += selector.listOfMatches.get(i).homeShotsOnTarget;
                averageOppStats[6] += selector.listOfMatches.get(i).homeCorners;
                averageOppStats[7] += selector.listOfMatches.get(i).homeYellowCards;
            }
        }
        for (int i=0; i<=2; i++){
            averageSelfStats[i] = MyMath.round(averageSelfStats[i] / selector.listOfMatches.size(), 2);
            averageOppStats[i]  = MyMath.round(averageOppStats[i]  / selector.listOfMatches.size(), 2);
        }

        if (selector.numberOfMatchesWithParam[6] > 0){
            averageSelfStats[3] = MyMath.round(averageSelfStats[3] / selector.numberOfMatchesWithParam[6], 2);
            averageOppStats [3] = MyMath.round(averageOppStats [3] / selector.numberOfMatchesWithParam[6], 2);
        } else {
            averageSelfStats[3] = 0;
            averageOppStats [3] = 0;
        }
        if (selector.numberOfMatchesWithParam[7] > 0){
            averageSelfStats[4] = MyMath.round(averageSelfStats[4] / selector.numberOfMatchesWithParam[7], 2);
            averageOppStats [4] = MyMath.round(averageOppStats [4] / selector.numberOfMatchesWithParam[7], 2);
        } else {
            averageSelfStats[4] = 0;
            averageOppStats [4] = 0;
        }
        if (selector.numberOfMatchesWithParam[8] > 0){
            averageSelfStats[5] = MyMath.round(averageSelfStats[5] / selector.numberOfMatchesWithParam[8], 2);
            averageOppStats [5] = MyMath.round(averageOppStats [5] / selector.numberOfMatchesWithParam[8], 2);
        } else {
            averageSelfStats[5] = 0;
            averageOppStats [5] = 0;
        }
        if (selector.numberOfMatchesWithParam[10] > 0){
            averageSelfStats[6] = MyMath.round(averageSelfStats[6] / selector.numberOfMatchesWithParam[10], 2);
            averageOppStats [6] = MyMath.round(averageOppStats [6] / selector.numberOfMatchesWithParam[10], 2);
        } else {
            averageSelfStats[6] = 0;
            averageOppStats [6] = 0;
        }
        if (selector.numberOfMatchesWithParam[14] > 0){
            averageSelfStats[7] = MyMath.round(averageSelfStats[7] / selector.numberOfMatchesWithParam[14], 2);
            averageOppStats [7] = MyMath.round(averageOppStats [7] / selector.numberOfMatchesWithParam[14], 2);
        } else {
            averageSelfStats[7] = 0;
            averageOppStats [7] = 0;
        }

        dataSumByOpps[selector.listOfMatches.size()][0] = "Среднее";
        dataSumByOpps[selector.listOfMatches.size()][1] = "-";
        for (int i=2; i<averageSelfStats.length; i++){
            dataSumByOpps[selector.listOfMatches.size()][i] = averageSelfStats[i] + " : " + averageOppStats[i];
        }

        JTable table = new JTable(dataSumByOpps, colHeadsSummaryByOpponents);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        Font fontTable = new Font("", Font.PLAIN, 12);
        table.getTableHeader().setFont(fontTable);
        table.setFont(fontTable);
        table.setRowHeight(20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=0; r<colHeadsSummaryByOpponents.length; r++)
            table.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);

        Renderer renderer = new Renderer(1);
//        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=1; i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }



        JPanel tablePanel3 = new JPanel();
        tablePanel3.setLayout(new BorderLayout());
        tablePanel3.add(table, BorderLayout.CENTER);
        tablePanel3.add(table.getTableHeader(), BorderLayout.NORTH);

        result.add(tablePanel3);
        return result;
    }

}

