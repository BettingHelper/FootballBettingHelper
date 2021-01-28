package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CalculatorThread extends Thread{
    final String path;
    String leagueName;
    String homeTeam;
    String awayTeam;
    String season;
    double slider_HT_1_Value;
    double slider_HT_2_Value;
    double slider_HT_3_Value;
    double slider_HT_4_Value;
    double slider_AT_1_Value;
    double slider_AT_2_Value;
    double slider_AT_3_Value;
    double slider_AT_4_Value;
    JFrame tw;
    PanelCalculator pCalc;
    JProgressBar jpb;

    public CalculatorThread(String season,  String leagueName, String homeTeam, String awayTeam, PanelCalculator pCalc,
                            double slider_HT_1_Value, double slider_HT_2_Value, double slider_HT_3_Value, double slider_HT_4_Value,
                            double slider_AT_1_Value, double slider_AT_2_Value, double slider_AT_3_Value, double slider_AT_4_Value
                            ){
        path = "database/";
        this.leagueName = leagueName;
        this.season = season;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.slider_HT_1_Value = slider_HT_1_Value;
        this.slider_HT_2_Value = slider_HT_2_Value;
        this.slider_HT_3_Value = slider_HT_3_Value;
        this.slider_HT_4_Value = slider_HT_4_Value;
        this.slider_AT_1_Value = slider_AT_1_Value;
        this.slider_AT_2_Value = slider_AT_2_Value;
        this.slider_AT_3_Value = slider_AT_3_Value;
        this.slider_AT_4_Value = slider_AT_4_Value;
        this.pCalc = pCalc;

        tw = new JFrame("Внимание!");
        tw.setResizable(false);
        tw.setLayout(new BorderLayout());
        tw.setSize(500, 200);
        tw.setLocation(200, 200);

        JLabel label = new JLabel("Подождите, идет расчет");
        label.setFont(new Font("", Font.BOLD, 20));
        tw.add(label, BorderLayout.NORTH);

        jpb = new JProgressBar(0, 100);
        jpb.setPreferredSize(new Dimension(600, 50));
        jpb.setStringPainted(true);
        tw.add(jpb, BorderLayout.SOUTH);

        tw.setVisible(true);
    }

    @Override
    public void run(){
        //tw.setVisible(true);
        //jpb.setValue(0);

        double[][] resultCornersHT = new double[2][2];
        double[][] resultCornersAT = new double[2][2];

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

        int numberOfStages = 11;
        int currentStage = 0;

        if (!pCalc.lastCalculatedLeague.equals(leagueName) || !pCalc.lastCalculatedSeason.equals(season)
                || !pCalc.lastCalculatedHomeTeam.equals(homeTeam) || !pCalc.lastCalculatedAwayTeam.equals(awayTeam)){
            pCalc.currentLeague = League.getLeagueFromFile(leagueName, season);
            ArrayList<String> tournamentTable = League.getLeagueFromFile(leagueName, season).tournamentTable;
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorHT_All = new Selector();
            pCalc.selectorHT_All.getListOfMatches(leagueName, homeTeam, "Все матчи", season, "Весь сезон");
            pCalc.selectorHT_All.getPList(pCalc.selectorHT_All.listOfMatches, homeTeam);
            pCalc.dataArrayThis_HT_All = new double[titles.length][pCalc.selectorHT_All.listOfMatches.size()];
            pCalc.dataArrayOpponent_HT_All = new double[titles.length][pCalc.selectorHT_All.listOfMatches.size()];
            pCalc.dataArrayTotal_HT_All = new double[titles.length][pCalc.selectorHT_All.listOfMatches.size()];
            pCalc.arrayOpponents_HT_All = new String[pCalc.selectorHT_All.listOfMatches.size()];
            pCalc.selectorHT_All.getArraysWithStats(homeTeam, pCalc.dataArrayThis_HT_All, pCalc.dataArrayOpponent_HT_All,
                    pCalc.dataArrayTotal_HT_All, pCalc.arrayOpponents_HT_All, tournamentTable);
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorAT_All = new Selector();
            pCalc.selectorAT_All.getListOfMatches(leagueName, awayTeam, "Все матчи", season, "Весь сезон");
            pCalc.selectorAT_All.getPList(pCalc.selectorAT_All.listOfMatches, awayTeam);
            pCalc.dataArrayThis_AT_All = new double[titles.length][pCalc.selectorAT_All.listOfMatches.size()];
            pCalc.dataArrayOpponent_AT_All = new double[titles.length][pCalc.selectorAT_All.listOfMatches.size()];
            pCalc.dataArrayTotal_AT_All = new double[titles.length][pCalc.selectorAT_All.listOfMatches.size()];
            pCalc.arrayOpponents_AT_All = new String[pCalc.selectorAT_All.listOfMatches.size()];
            pCalc.selectorAT_All.getArraysWithStats(awayTeam, pCalc.dataArrayThis_AT_All, pCalc.dataArrayOpponent_AT_All,
                    pCalc.dataArrayTotal_AT_All, pCalc.arrayOpponents_AT_All, tournamentTable);
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorHT_Home = new Selector();
            pCalc.selectorHT_Home.getListOfMatches(leagueName, homeTeam, "Дома", season, "Весь сезон");
            pCalc.selectorHT_Home.getPList(pCalc.selectorHT_Home.listOfMatches, homeTeam);
            pCalc.dataArrayThis_HT_Home = new double[titles.length][pCalc.selectorHT_Home.listOfMatches.size()];
            pCalc.dataArrayOpponent_HT_Home = new double[titles.length][pCalc.selectorHT_Home.listOfMatches.size()];
            pCalc.dataArrayTotal_HT_Home = new double[titles.length][pCalc.selectorHT_Home.listOfMatches.size()];
            pCalc.arrayOpponents_HT_Home = new String[pCalc.selectorHT_Home.listOfMatches.size()];
            pCalc.selectorHT_Home.getArraysWithStats(homeTeam, pCalc.dataArrayThis_HT_Home, pCalc.dataArrayOpponent_HT_Home,
                    pCalc.dataArrayTotal_HT_Home, pCalc.arrayOpponents_HT_Home, tournamentTable);
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorAT_Away = new Selector();
            pCalc.selectorAT_Away.getListOfMatches(leagueName, awayTeam, "На выезде", season, "Весь сезон");
            pCalc.selectorAT_Away.getPList(pCalc.selectorAT_Away.listOfMatches, awayTeam);
            pCalc.dataArrayThis_AT_Away = new double[titles.length][pCalc.selectorAT_Away.listOfMatches.size()];
            pCalc.dataArrayOpponent_AT_Away = new double[titles.length][pCalc.selectorAT_Away.listOfMatches.size()];
            pCalc.dataArrayTotal_AT_Away = new double[titles.length][pCalc.selectorAT_Away.listOfMatches.size()];
            pCalc.arrayOpponents_AT_Away = new String[pCalc.selectorAT_Away.listOfMatches.size()];
            pCalc.selectorAT_Away.getArraysWithStats(awayTeam, pCalc.dataArrayThis_AT_Away, pCalc.dataArrayOpponent_AT_Away,
                    pCalc.dataArrayTotal_AT_Away, pCalc.arrayOpponents_AT_Away, tournamentTable);
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorConfAll_HT = new Selector();
            pCalc.selectorConfAll_HT.getConfrontationList(leagueName, homeTeam, awayTeam, "Все матчи");
            pCalc.dataArrayThis_ConfAll_HT = new double[titles.length][pCalc.selectorConfAll_HT.listOfMatches.size()];
            pCalc.dataArrayOpponent_ConfAll_HT = new double[titles.length][pCalc.selectorConfAll_HT.listOfMatches.size()];
            pCalc.dataArrayTotal_ConfAll_HT = new double[titles.length][pCalc.selectorConfAll_HT.listOfMatches.size()];
            pCalc.arrayOpponents_ConfAll_HT = new String[pCalc.selectorConfAll_HT.listOfMatches.size()];
            pCalc.selectorConfAll_HT.getArraysWithStats(homeTeam, pCalc.dataArrayThis_ConfAll_HT, pCalc.dataArrayOpponent_ConfAll_HT,
                    pCalc.dataArrayTotal_ConfAll_HT, pCalc.arrayOpponents_ConfAll_HT, tournamentTable);
            pCalc.arrayLeaguesAll = new ArrayList<>();
            for (int i=0; i<pCalc.selectorConfAll_HT.listOfMatches.size(); i++){
                League localLeague = League.getLeagueFromFile(pCalc.selectorConfAll_HT.listOfMatches.get(i).league, pCalc.selectorConfAll_HT.listOfMatches.get(i).season);
                pCalc.arrayLeaguesAll.add(localLeague);
            }
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorConfHomeField_HT = new Selector();
            pCalc.selectorConfHomeField_HT.getConfrontationList(leagueName, homeTeam, awayTeam, "На поле хозяев");
            pCalc.dataArrayThis_ConfHomeField_HT = new double[titles.length][pCalc.selectorConfHomeField_HT.listOfMatches.size()];
            pCalc.dataArrayOpponent_ConfHomeField_HT = new double[titles.length][pCalc.selectorConfHomeField_HT.listOfMatches.size()];
            pCalc.dataArrayTotal_ConfHomeField_HT = new double[titles.length][pCalc.selectorConfHomeField_HT.listOfMatches.size()];
            pCalc.arrayOpponents_ConfHomeField_HT = new String[pCalc.selectorConfHomeField_HT.listOfMatches.size()];
            pCalc.selectorConfHomeField_HT.getArraysWithStats(homeTeam, pCalc.dataArrayThis_ConfHomeField_HT, pCalc.dataArrayOpponent_ConfHomeField_HT,
                    pCalc.dataArrayTotal_ConfHomeField_HT, pCalc.arrayOpponents_ConfHomeField_HT, tournamentTable);
            ArrayList<League> arrayLeaguesHomeField_HT = new ArrayList<>();
            for (int i=0; i<pCalc.selectorConfHomeField_HT.listOfMatches.size(); i++){
                League localLeague = League.getLeagueFromFile(pCalc.selectorConfHomeField_HT.listOfMatches.get(i).league, pCalc.selectorConfHomeField_HT.listOfMatches.get(i).season);
                arrayLeaguesHomeField_HT.add(localLeague);
            }
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorConfAll_AT = new Selector();
            pCalc.selectorConfAll_AT.getConfrontationList(leagueName, homeTeam, awayTeam, "Все матчи");
            pCalc.dataArrayThis_ConfAll_AT = new double[titles.length][pCalc.selectorConfAll_AT.listOfMatches.size()];
            pCalc.dataArrayOpponent_ConfAll_AT = new double[titles.length][pCalc.selectorConfAll_AT.listOfMatches.size()];
            pCalc.dataArrayTotal_ConfAll_AT = new double[titles.length][pCalc.selectorConfAll_AT.listOfMatches.size()];
            pCalc.arrayOpponents_ConfAll_AT = new String[pCalc.selectorConfAll_AT.listOfMatches.size()];
            pCalc.selectorConfAll_AT.getArraysWithStats(awayTeam, pCalc.dataArrayThis_ConfAll_AT, pCalc.dataArrayOpponent_ConfAll_AT,
                    pCalc.dataArrayTotal_ConfAll_AT, pCalc.arrayOpponents_ConfAll_AT, tournamentTable);
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.selectorConfHomeField_AT = new Selector();
            pCalc.selectorConfHomeField_AT.getConfrontationList(leagueName, homeTeam, awayTeam, "На поле хозяев");
            pCalc.dataArrayThis_ConfHomeField_AT = new double[titles.length][pCalc.selectorConfHomeField_AT.listOfMatches.size()];
            pCalc.dataArrayOpponent_ConfHomeField_AT = new double[titles.length][pCalc.selectorConfHomeField_AT.listOfMatches.size()];
            pCalc.dataArrayTotal_ConfHomeField_AT = new double[titles.length][pCalc.selectorConfHomeField_AT.listOfMatches.size()];
            pCalc.arrayOpponents_ConfHomeField_AT = new String[pCalc.selectorConfHomeField_AT.listOfMatches.size()];
            pCalc.selectorConfHomeField_AT.getArraysWithStats(awayTeam, pCalc.dataArrayThis_ConfHomeField_AT, pCalc.dataArrayOpponent_ConfHomeField_AT,
                    pCalc.dataArrayTotal_ConfHomeField_AT, pCalc.arrayOpponents_ConfHomeField_AT, tournamentTable);
            currentStage++;
            jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

            pCalc.lastCalculatedSeason = season;
            pCalc.lastCalculatedLeague = leagueName;
            pCalc.lastCalculatedHomeTeam = homeTeam;
            pCalc.lastCalculatedAwayTeam = awayTeam;
        } else {
            currentStage += 9;
        }

        /////////////////// *************** Слайдер 1 - В зависимости от его значения по-разному заполняются ВЕСА
        ArrayList<Double> weightsHT = MyMath.getWeights(pCalc.selectorHT_All.listOfMatches.size(), slider_HT_1_Value / pCalc.maxSliderValue);
        ArrayList<Double> weightsAT = MyMath.getWeights(pCalc.selectorAT_All.listOfMatches.size(), slider_AT_1_Value / pCalc.maxSliderValue);
        ArrayList<Double> weightsConfAll = MyMath.getWeights(pCalc.selectorConfAll_HT.listOfMatches.size(), (slider_HT_1_Value + slider_AT_1_Value) / 2.0 / pCalc.maxSliderValue);
        ArrayList<Double> weightsConfHomeField = MyMath.getWeights(pCalc.selectorConfHomeField_HT.listOfMatches.size(), (slider_HT_1_Value + slider_AT_1_Value) / 2.0 / pCalc.maxSliderValue);
        /////////////////// &&&&&&&&&&&&&&&&&&&& Слайдер 1

        /////////////////// *************** Считаем статистику сезона (ВСЕ МАТЧИ)
        double sumHT = 0;
        double sumHT_Opp = 0;
        double sumAT = 0;
        double sumAT_Opp = 0;
        for (int i=0; i<pCalc.selectorHT_All.listOfMatches.size(); i++){
            double average_HT_OppCornersConceded = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_HT_All[i], "Общее", 10, 1);
            sumHT += (pCalc.dataArrayThis_HT_All[24][i] - average_HT_OppCornersConceded)*weightsHT.get(i);

            double average_HT_OppCornersScored = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_HT_All[i], "Общее", 10, 0);
            sumHT_Opp += (pCalc.dataArrayOpponent_HT_All[24][i] - average_HT_OppCornersScored)*weightsHT.get(i);
        }

        for (int i=0; i<pCalc.selectorAT_All.listOfMatches.size(); i++){
            double average_AT_OppCornersConceded = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_AT_All[i], "Общее", 10, 1);
            sumAT += (pCalc.dataArrayThis_AT_All[24][i] - average_AT_OppCornersConceded)*weightsAT.get(i);

            double average_AT_OppCornersScored = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_AT_All[i], "Общее", 10, 0);
            sumAT_Opp += (pCalc.dataArrayOpponent_AT_All[24][i] - average_AT_OppCornersScored)*weightsAT.get(i);
        }


        sumHT = sumHT / (double) pCalc.selectorHT_All.listOfMatches.size();
        sumHT_Opp = sumHT_Opp / (double) pCalc.selectorHT_All.listOfMatches.size();
        sumAT = sumAT / (double) pCalc.selectorAT_All.listOfMatches.size();
        sumAT_Opp = sumAT_Opp / (double) pCalc.selectorAT_All.listOfMatches.size();

        double homeCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Общее", 10, 1) + sumHT;
        double awayCornersOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Общее", 10, 0) + sumAT_Opp;
        double awayCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Общее", 10, 1) + sumAT;
        double homeCornersSOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Общее", 10, 0) + sumHT_Opp;

        System.out.println("Текущий сезон:");
        System.out.println("Все игры");
        System.out.println("Промежуточные результаты хозяев: " + MyMath.round(homeCornersSelf , 2) + " и " + MyMath.round(awayCornersOpp , 2));
        System.out.println("Промежуточные результаты гостей : " + MyMath.round(awayCornersSelf , 2) + " и " + MyMath.round(homeCornersSOpp , 2));

        resultCornersHT[0][0] = MyMath.getValueBetweenAB(homeCornersSelf, awayCornersOpp,  slider_HT_4_Value/ pCalc.maxSliderValue);
        resultCornersAT[0][0] = MyMath.getValueBetweenAB(awayCornersSelf, homeCornersSOpp, slider_AT_4_Value/ pCalc.maxSliderValue);

        System.out.println("Хозяева : " + MyMath.round(resultCornersHT[0][0] , 2));
        System.out.println("Гости : " + MyMath.round(resultCornersAT[0][0] , 2));
        /////////////////// &&&&&&&&&&&&&&&&&&&& Считаем статистику сезона (ВСЕ МАТЧИ)

        /////////////////// *************** Считаем статистику сезона (Дом/выезд)
        sumHT = 0;
        sumHT_Opp = 0;
        sumAT = 0;
        sumAT_Opp = 0;
        for (int i=0; i<pCalc.selectorHT_Home.listOfMatches.size(); i++){
            double average_HT_OppCornersConceded = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_HT_Home[i], "Выезд", 10, 1);
            sumHT += (pCalc.dataArrayThis_HT_Home[24][i] - average_HT_OppCornersConceded)*weightsHT.get(i);

            double average_HT_OppCornersScored = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_HT_Home[i], "Выезд", 10, 0);
            sumHT_Opp += (pCalc.dataArrayOpponent_HT_Home[24][i] - average_HT_OppCornersScored)*weightsHT.get(i);
        }

        for (int i=0; i<pCalc.selectorAT_Away.listOfMatches.size(); i++){
            double average_AT_OppCornersConceded = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_AT_Away[i], "Дом", 10, 1);
            sumAT += (pCalc.dataArrayThis_AT_Away[24][i] - average_AT_OppCornersConceded)*weightsAT.get(i);

            double average_AT_OppCornersScored = pCalc.currentLeague.getParameterValue(pCalc.arrayOpponents_AT_Away[i], "Дом", 10, 0);
            sumAT_Opp += (pCalc.dataArrayOpponent_AT_Away[24][i] - average_AT_OppCornersScored)*weightsAT.get(i);
        }


        sumHT = sumHT / (double) pCalc.selectorHT_Home.listOfMatches.size();
        sumHT_Opp = sumHT_Opp / (double) pCalc.selectorHT_Home.listOfMatches.size();
        sumAT = sumAT / (double) pCalc.selectorAT_Away.listOfMatches.size();
        sumAT_Opp = sumAT_Opp / (double) pCalc.selectorAT_Away.listOfMatches.size();

        homeCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Выезд", 10, 1) + sumHT;
        awayCornersOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Дом", 10, 0) + sumAT_Opp;
        awayCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Дом", 10, 1) + sumAT;
        homeCornersSOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Выезд", 10, 0) + sumHT_Opp;


        System.out.println("Текущий сезон:");
        System.out.println("Дом/выезд");
        System.out.println("Промежуточные результаты хозяев: " + MyMath.round(homeCornersSelf , 2) + " и " + MyMath.round(awayCornersOpp , 2));
        System.out.println("Промежуточные результаты гостей : " + MyMath.round(awayCornersSelf , 2) + " и " + MyMath.round(homeCornersSOpp , 2));

        resultCornersHT[0][1] = MyMath.getValueBetweenAB(homeCornersSelf, awayCornersOpp,  slider_HT_4_Value/ pCalc.maxSliderValue);
        resultCornersAT[0][1] = MyMath.getValueBetweenAB(awayCornersSelf, homeCornersSOpp, slider_AT_4_Value/ pCalc.maxSliderValue);

        System.out.println("Хозяева : " + MyMath.round(resultCornersHT[0][1] , 2));
        System.out.println("Гости : " + MyMath.round(resultCornersAT[0][1] , 2));
        /////////////////// &&&&&&&&&&&&&&&&&&&& Считаем статистику сезона (Дом/выезд)

        /////////////////// ******************** Слайдер 2 - В зависимости от его значения посчитанные ниже значения будут разными
        double currentSeason_HT = MyMath.getValueBetweenAB(resultCornersHT[0][0], resultCornersHT[0][1], slider_HT_2_Value/ pCalc.maxSliderValue);
        double currentSeason_AT = MyMath.getValueBetweenAB(resultCornersAT[0][0], resultCornersAT[0][1], slider_AT_2_Value/ pCalc.maxSliderValue);
        /////////////////// &&&&&&&&&&&&&&&&&&&& Слайдер 2 - В зависимости от его значения посчитанные ниже значения будут разными

        currentStage++;
        jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

        /////////////////// ******************** Считаем статистику личных встреч (Все матчи)
        sumHT = 0;
        sumHT_Opp = 0;
        sumAT = 0;
        sumAT_Opp = 0;
        for (int i=0; i<pCalc.selectorConfAll_HT.listOfMatches.size(); i++){
            double average_HT_OppCornersConceded = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfAll_HT[i], "Общее", 10, 1);
            sumHT += (pCalc.dataArrayThis_ConfAll_HT[24][i] - average_HT_OppCornersConceded)*weightsConfAll.get(i);

            double average_HT_OppCornersScored = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfAll_HT[i], "Общее", 10, 0);
            sumHT_Opp += (pCalc.dataArrayOpponent_ConfAll_HT[24][i] - average_HT_OppCornersScored)*weightsConfAll.get(i);
        }

        for (int i=0; i<pCalc.selectorConfAll_AT.listOfMatches.size(); i++){
            double average_AT_OppCornersConceded = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfAll_AT[i], "Общее", 10, 1);
            sumAT += (pCalc.dataArrayThis_ConfAll_AT[24][i] - average_AT_OppCornersConceded)*weightsConfAll.get(i);

            double average_AT_OppCornersScored = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfAll_AT[i], "Общее", 10, 0);
            sumAT_Opp += (pCalc.dataArrayOpponent_ConfAll_AT[24][i] - average_AT_OppCornersScored)*weightsConfAll.get(i);
        }


        sumHT = sumHT / (double) pCalc.selectorConfAll_HT.listOfMatches.size();
        sumHT_Opp = sumHT_Opp / (double) pCalc.selectorConfAll_HT.listOfMatches.size();
        sumAT = sumAT / (double) pCalc.selectorConfAll_AT.listOfMatches.size();
        sumAT_Opp = sumAT_Opp / (double) pCalc.selectorConfAll_AT.listOfMatches.size();

        homeCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Общее", 10, 1) + sumHT;
        awayCornersOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Общее", 10, 0) + sumAT_Opp;
        awayCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Общее", 10, 1) + sumAT;
        homeCornersSOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Общее", 10, 0) + sumHT_Opp;

        System.out.println("Личные встречи:");
        System.out.println("Все игры");
        System.out.println("Промежуточные результаты хозяев: " + MyMath.round(homeCornersSelf , 2) + " и " + MyMath.round(awayCornersOpp , 2));
        System.out.println("Промежуточные результаты гостей : " + MyMath.round(awayCornersSelf , 2) + " и " + MyMath.round(homeCornersSOpp , 2));

        resultCornersHT[1][0] = MyMath.getValueBetweenAB(homeCornersSelf, awayCornersOpp,   slider_HT_4_Value/ pCalc.maxSliderValue);
        resultCornersAT[1][0] = MyMath.getValueBetweenAB(awayCornersSelf, homeCornersSOpp,  slider_AT_4_Value/ pCalc.maxSliderValue);

        System.out.println("Хозяева : " + MyMath.round(resultCornersHT[1][0] , 2));
        System.out.println("Гости : " + MyMath.round(resultCornersAT[1][0] , 2));
        /////////////////// ******************** Считаем статистику личных встреч (Все матчи)

        /////////////////// ******************** Считаем статистику личных встреч (Только хозяева)
        sumHT = 0;
        sumHT_Opp = 0;
        sumAT = 0;
        sumAT_Opp = 0;
        for (int i=0; i<pCalc.selectorConfHomeField_HT.listOfMatches.size(); i++){
            double average_HT_OppCornersConceded = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfHomeField_HT[i], "Выезд", 10, 1);
            sumHT += (pCalc.dataArrayThis_ConfHomeField_HT[24][i] - average_HT_OppCornersConceded)*weightsConfHomeField.get(i);

            double average_HT_OppCornersScored = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfHomeField_HT[i], "Выезд", 10, 0);
            sumHT_Opp += (pCalc.dataArrayOpponent_ConfHomeField_HT[24][i] - average_HT_OppCornersScored)*weightsConfHomeField.get(i);
        }

        for (int i=0; i<pCalc.selectorConfHomeField_AT.listOfMatches.size(); i++){
            double average_AT_OppCornersConceded = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfHomeField_AT[i], "Дом", 10, 1);
            sumAT += (pCalc.dataArrayThis_ConfHomeField_AT[24][i] - average_AT_OppCornersConceded)*weightsConfHomeField.get(i);

            double average_AT_OppCornersScored = pCalc.arrayLeaguesAll.get(i).getParameterValue(pCalc.arrayOpponents_ConfHomeField_AT[i], "Дом", 10, 0);
            sumAT_Opp += (pCalc.dataArrayOpponent_ConfHomeField_AT[24][i] - average_AT_OppCornersScored)*weightsConfHomeField.get(i);
        }


        sumHT = sumHT / (double) pCalc.selectorConfHomeField_HT.listOfMatches.size();
        sumHT_Opp = sumHT_Opp / (double) pCalc.selectorConfHomeField_HT.listOfMatches.size();
        sumAT = sumAT / (double) pCalc.selectorConfHomeField_AT.listOfMatches.size();
        sumAT_Opp = sumAT_Opp / (double) pCalc.selectorConfHomeField_AT.listOfMatches.size();

        homeCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Выезд", 10, 1) + sumHT;
        awayCornersOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Дом", 10, 0) + sumAT_Opp;
        awayCornersSelf = pCalc.currentLeague.getParameterValue(Team.getShortName(homeTeam), "Дом", 10, 1) + sumAT;
        homeCornersSOpp = pCalc.currentLeague.getParameterValue(Team.getShortName(awayTeam), "Выезд", 10, 0) + sumHT_Opp;

        System.out.println("Личные встречи:");
        System.out.println("На поле хозяев");
        System.out.println("Промежуточные результаты хозяев: " + MyMath.round(homeCornersSelf , 2) + " и " + MyMath.round(awayCornersOpp , 2));
        System.out.println("Промежуточные результаты гостей : " + MyMath.round(awayCornersSelf , 2) + " и " + MyMath.round(homeCornersSOpp , 2));

        resultCornersHT[1][1] = MyMath.getValueBetweenAB(homeCornersSelf, awayCornersOpp,   slider_HT_4_Value/ pCalc.maxSliderValue);
        resultCornersAT[1][1] = MyMath.getValueBetweenAB(awayCornersSelf, homeCornersSOpp,  slider_AT_4_Value/ pCalc.maxSliderValue);

        System.out.println("Хозяева : " + MyMath.round(resultCornersHT[1][1] , 2));
        System.out.println("Гости : " + MyMath.round(resultCornersAT[1][1] , 2));
        /////////////////// ******************** Считаем статистику личных встреч (Только хозяева)

        /////////////////// ******************** Слайдер 2 - В зависимости от его значения посчитанные ниже значения будут разными
        double headToHeadMatches_HT = MyMath.getValueBetweenAB(resultCornersHT[1][0], resultCornersHT[1][1], slider_HT_2_Value/ pCalc.maxSliderValue);
        double headToHeadMatches_AT = MyMath.getValueBetweenAB(resultCornersAT[1][0], resultCornersAT[1][1], slider_AT_2_Value/ pCalc.maxSliderValue);
        /////////////////// &&&&&&&&&&&&&&&&&&&& Слайдер 2 - В зависимости от его значения посчитанные ниже значения будут разными

        /////////////////// ******************** Слайдер 3 - В зависимости от его значения посчитанные ниже значения будут разными
        double resultHT = MyMath.getValueBetweenAB(currentSeason_HT, headToHeadMatches_HT, slider_HT_3_Value/ pCalc.maxSliderValue);
        double resultAT = MyMath.getValueBetweenAB(currentSeason_AT, headToHeadMatches_AT, slider_AT_3_Value/ pCalc.maxSliderValue);
        /////////////////// &&&&&&&&&&&&&&&&&&&& Слайдер 3 - В зависимости от его значения посчитанные ниже значения будут разными

        System.out.println("Итоговый результат:");
        System.out.println(homeTeam + "  " + resultHT + " : " + resultAT + "  " + awayTeam);
        System.out.println("***********************************");

        currentStage++;
        jpb.setValue((int) (currentStage / (double) numberOfStages * 100));

        pCalc.dataPanel.add(getResultPanel(resultHT, resultAT), BorderLayout.CENTER);
        pCalc.dataPanel.revalidate();
        pCalc.revalidate();

        tw.setVisible(false);

    }

    public JPanel getResultPanel(double resultHT, double resultAT){
        JPanel result = new JPanel(new BorderLayout());

        Font font = new Font("", Font.BOLD, 30);
        JPanel textPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        JLabel labelTop = new JLabel(homeTeam + " : " + awayTeam);
        labelTop.setFont(font);
        labelTop.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(labelTop);

        JLabel labelPredictCornersHeader = new JLabel("Расчетные значения угловых:");
        labelPredictCornersHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelPredictCornersHeader.setFont(font);
        textPanel.add(labelPredictCornersHeader);

        JLabel labelPredictCorners = new JLabel(resultHT + " : " + resultAT);
        labelPredictCorners.setHorizontalAlignment(SwingConstants.CENTER);
        labelPredictCorners.setFont(font);
        textPanel.add(labelPredictCorners);

        JLabel labelTotalHeader = new JLabel("Расчетное значение тотала:");
        labelTotalHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalHeader.setFont(font);
        textPanel.add(labelTotalHeader);

        JLabel labelPredictTotal = new JLabel(String.valueOf(MyMath.round(resultHT + resultAT, 2)));
        labelPredictTotal.setHorizontalAlignment(SwingConstants.CENTER);
        labelPredictTotal.setFont(font);
        textPanel.add(labelPredictTotal);

        JLabel labelTotalHandicapHeader = new JLabel("Расчетное значение форы:");
        labelTotalHandicapHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalHandicapHeader.setFont(font);
        textPanel.add(labelTotalHandicapHeader);

        JLabel labelPredictHandicap = null;
        if (resultHT > resultAT){
            labelPredictHandicap = new JLabel("Фора +" + MyMath.round(resultHT-resultAT , 2) + " в пользу " + homeTeam);
        }
        if (resultHT < resultAT){
            labelPredictHandicap = new JLabel("Фора +" + MyMath.round(resultAT-resultHT , 2) + " в пользу " + awayTeam);
        }
        if (resultHT == resultAT){
            labelPredictHandicap = new JLabel("Фора = 0");
        }
        labelPredictHandicap.setHorizontalAlignment(SwingConstants.CENTER);
        labelPredictHandicap.setFont(font);
        textPanel.add(labelPredictHandicap);

        result.add(textPanel, BorderLayout.CENTER);

        File fileHT = new File("images/" + homeTeam + ".png");
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(fileHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = bimg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel teamImage = new JLabel(new ImageIcon(scaled));
        teamImage.setBorder(new EmptyBorder(5,0,0,0));
        result.add(teamImage, BorderLayout.WEST);

        File fileAT = new File("images/" + awayTeam + ".png");
        bimg = null;
        try {
            bimg = ImageIO.read(fileAT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scaled = bimg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        teamImage = new JLabel(new ImageIcon(scaled));
        teamImage.setBorder(new EmptyBorder(5,0,0,0));
        result.add(teamImage, BorderLayout.EAST);

        return result;
    }

}
