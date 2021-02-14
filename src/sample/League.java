package sample;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;

// определяем корневой элемент
@XmlRootElement(name = "League")
// определяем последовательность тегов в XML
@XmlType(propOrder = { "leagueName", "season", "fileName", "matchesPlayed", "matchesToAddingInStat", "goalsScored", "goalsScored1T", "goalsScored2T",
        "homeGoals", "awayGoals", "homeGoals1T", "awayGoals1T", "homeGoals2T", "awayGoals2T", "goalsScoredBy15Min",
        "homeGoalsBy15Min", "awayGoalsBy15Min", "homeXG", "awayXG", "homeBallPossession", "awayBallPossession",
        "homeCorners", "awayCorners", "homeCorners1T", "awayCorners1T", "homeCorners2T", "awayCorners2T",
        "homeYellowCards", "awayYellowCards", "homeYellowCards1T", "awayYellowCards1T", "homeYellowCards2T", "awayYellowCards2T",
        "homeRedCards", "awayRedCards", "homeShots", "awayShots", "homeShotsOnTarget", "awayShotsOnTarget", "homeShotsOffTarget", "awayShotsOffTarget",
        "homeBlockedShots", "awayBlockedShots", "homeOffsides", "awayOffsides", "homeFouls", "awayFouls", "homeShotsOnPost", "awayShotsOnPost",
        "homeSaves", "awaySaves", "homeDribbles", "awayDribbles", "homeAerialsWon", "awayAerialsWon", "homeClearances", "awayClearances",
        "homeInterceptions", "awayInterceptions", "homeDispossessed", "awayDispossessed", "homeTackles", "awayTackles", "homePasses", "awayPasses",
        "homeKeyPasses", "awayKeyPasses", "homePassesSuccessfully", "awayPassesSuccessfully", "homeSecondYellowCards", "awaySecondYellowCards",
        "homeDirectRedCards", "awayDirectRedCards", "homeOGScored", "awayOGScored", "homePen", "awayPen", "homePenScored", "awayPenScored",
        "g_homeWin_draw_awayWin", "g_homeWin_draw_awayWin_1T", "g_homeWin_draw_awayWin_2T", "g_OZ", "g_totals", "g_totals_1T", "g_totals_2T",
        "g_goalsInBothTimes", "g_firstTimeMoreGoals", "g_secondTimeMoreGoals",
        "c_homeWin_draw_awayWin","c_homeWin_draw_awayWin_1T","c_homeWin_draw_awayWin_2T","c_totals","c_totals_1T","c_totals_2T","c_firstTimeMoreCorners","c_secondTimeMoreCorners",
        "yc_homeWin_draw_awayWin","yc_homeWin_draw_awayWin_1T","yc_homeWin_draw_awayWin_2T","yc_totals","yc_totals_1T","yc_totals_2T","yc_firstTimeMoreYC","yc_secondTimeMoreYC",
        "tournamentTable", "overallStatsTable", "homeStatsTable", "awayStatsTable"
})

public class League {
    public String leagueName;
    public String season;
    public String fileName;
    public int matchesPlayed;
    public ArrayList<String> matchesToAddingInStat;

    // голы и xG
    public int goalsScored;
    public int goalsScored1T;
    public int goalsScored2T;
    public int homeGoals;
    public int awayGoals;
    public int homeGoals1T;
    public int awayGoals1T;
    public int homeGoals2T;
    public int awayGoals2T;
    public String goalsScoredBy15Min;
    public String homeGoalsBy15Min;
    public String awayGoalsBy15Min;
    public double homeXG;
    public double awayXG;

    // остальная статистика
    public int homeBallPossession;
    public int awayBallPossession;
    public int homeCorners;
    public int awayCorners;
    public int homeCorners1T;
    public int awayCorners1T;
    public int homeCorners2T;
    public int awayCorners2T;
    public int homeYellowCards;
    public int awayYellowCards;
    public int homeYellowCards1T;
    public int awayYellowCards1T;
    public int homeYellowCards2T;
    public int awayYellowCards2T;
    public int homeRedCards;
    public int awayRedCards;
    public int homeShots;
    public int awayShots;
    public int homeShotsOnTarget;
    public int awayShotsOnTarget;
    public int homeShotsOffTarget;
    public int awayShotsOffTarget;
    public int homeBlockedShots;
    public int awayBlockedShots;
    public int homeOffsides;
    public int awayOffsides;
    public int homeFouls;
    public int awayFouls;
    public int homeShotsOnPost;
    public int awayShotsOnPost;
    public int homeSaves;
    public int awaySaves;
    public int homeDribbles;
    public int awayDribbles;
    public int homeAerialsWon;
    public int awayAerialsWon;
    public int homeClearances;
    public int awayClearances;
    public int homeInterceptions;
    public int awayInterceptions;
    public int homeDispossessed;
    public int awayDispossessed;
    public int homeTackles;
    public int awayTackles;
    public int homePasses;
    public int awayPasses;
    public int homeKeyPasses;
    public int awayKeyPasses;
    public int homePassesSuccessfully;
    public int awayPassesSuccessfully;
    public int homeSecondYellowCards;
    public int awaySecondYellowCards;
    public int homeDirectRedCards;
    public int awayDirectRedCards;
    public int homeOGScored;
    public int awayOGScored;
    public int homePen;
    public int awayPen;
    public int homePenScored;
    public int awayPenScored;

    // показатели по лиге (голы)
    public String g_homeWin_draw_awayWin;
    public String g_homeWin_draw_awayWin_1T;
    public String g_homeWin_draw_awayWin_2T;
    public int g_OZ;
    public String g_totals;
    public String g_totals_1T;
    public String g_totals_2T;
    public int g_goalsInBothTimes;
    public int g_firstTimeMoreGoals;
    public int g_secondTimeMoreGoals;

    // показатели по лиге (угловые)
    public String c_homeWin_draw_awayWin;
    public String c_homeWin_draw_awayWin_1T;
    public String c_homeWin_draw_awayWin_2T;
    public String c_totals;
    public String c_totals_1T;
    public String c_totals_2T;
    public int c_firstTimeMoreCorners;
    public int c_secondTimeMoreCorners;

    // показатели по лиге (ЖК)
    public String yc_homeWin_draw_awayWin;
    public String yc_homeWin_draw_awayWin_1T;
    public String yc_homeWin_draw_awayWin_2T;
    public String yc_totals;
    public String yc_totals_1T;
    public String yc_totals_2T;
    public int yc_firstTimeMoreYC;
    public int yc_secondTimeMoreYC;

    // турнирная таблица и сводные таблицы параметров
    public ArrayList<String> tournamentTable;
    public ArrayList<String> overallStatsTable;
    public ArrayList<String> homeStatsTable;
    public ArrayList<String> awayStatsTable;

    public League(){

    }

    public static League getLeagueFromFile(String leagueName, String season){
        String fileName = "leaguesInfo/" + leagueName + "_" + season + ".xml";
        int n = Settings.getNumberOfAccount();
        try {
            FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/data/football/" + fileName, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyException.sendException(e.getStackTrace(), "Проблема при загрузке " + fileName);
        }
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (League) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
            MyException.sendException(e.getStackTrace(), fileName + " содержит ошибку!");
        }
        return null;
    }

    public static String getPositionInLeague(String teamName, ArrayList<String> tournamentTable){
        String result;
        int localIndex = 0;
        int upperPlace;
        int downPlace;
        int localPoints = 0;

        for (int i=0; i<tournamentTable.size(); i++){
            if (tournamentTable.get(i).startsWith(teamName)){
                localIndex = i;
                localPoints = Integer.parseInt(tournamentTable.get(localIndex).split("\\*")[5]);
            }
        }

        upperPlace = localIndex;
        downPlace = localIndex;
        for (int i=0; i<localIndex; i++){
            if (tournamentTable.get(i).split("\\*")[5].equals(String.valueOf(localPoints))){
                upperPlace = i;
                break;
            }
        }
        for (int i=localIndex; i<tournamentTable.size(); i++){
            if (tournamentTable.get(i).split("\\*")[5].equals(String.valueOf(localPoints))){
                downPlace = i;
            }
        }

        if (upperPlace == localIndex && localIndex == downPlace){
            result = String.valueOf(localIndex+1);
        } else {
            result = String.valueOf(1 + Math.min(upperPlace, localIndex)) + "-" + String.valueOf(1 + Math.max(localIndex, downPlace));
        }

        return result;
    }

    public double getParameterValue(String teamName, String allHomeAway, int indexOfParameter, int selfOrOpp){
        double result = 0;


        if (allHomeAway.contains("Общее")){
            for (int i=0; i<overallStatsTable.size(); i++){
                String teamNameLocal = Team.getShortName(overallStatsTable.get(i).split("\\*")[0]);
                if (teamName.contains(teamNameLocal)){
                    return (Double.parseDouble(overallStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[selfOrOpp])
                            / Double.parseDouble(overallStatsTable.get(i).split("\\*")[1]) );
                }
            }
        }
        if (allHomeAway.contains("Дом")){
            for (int i=0; i<homeStatsTable.size(); i++){
                String teamNameLocal = Team.getShortName(homeStatsTable.get(i).split("\\*")[0]);
                if (teamName.contains(teamNameLocal)){
                    return (Double.parseDouble(homeStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[selfOrOpp])
                            / Double.parseDouble(homeStatsTable.get(i).split("\\*")[1]) );
                }
            }
        }
        if (allHomeAway.contains("Выезд")){
            for (int i=0; i<awayStatsTable.size(); i++){
                String teamNameLocal = Team.getShortName(awayStatsTable.get(i).split("\\*")[0]);
                if (teamName.contains(teamNameLocal)){
                    return (Double.parseDouble(awayStatsTable.get(i).split("\\*")[indexOfParameter].split("_")[selfOrOpp])
                            / Double.parseDouble(awayStatsTable.get(i).split("\\*")[1]) );
                }
            }
        }

        return result;
    }

    public void resetTables(String season, String leagueName, String lastOrFull, JProgressBar jpb){
        this.overallStatsTable = new ArrayList<>();
        this.homeStatsTable = new ArrayList<>();
        this.awayStatsTable = new ArrayList<>();
        jpb.setValue(0);

        String[] listOfTeams = new File("database/" + season + "/" + leagueName + "/Teams").list();

        for (int i=0; i<listOfTeams.length; i++){
            String teamName = listOfTeams[i].replaceAll(".xml","");

            Selector totalSelector = new Selector();
            totalSelector.getListOfMatches(leagueName, teamName, "Все матчи", season, lastOrFull);
            totalSelector.getPList(totalSelector.listOfMatches, teamName);
            double matchesTotal = totalSelector.listOfMatches.size();

            Selector homeSelector = new Selector();
            homeSelector.getListOfMatches(leagueName, teamName, "Дома", season, lastOrFull);
            homeSelector.getPList(homeSelector.listOfMatches, teamName);
            double homeMatches = homeSelector.listOfMatches.size();

            Selector awaySelector = new Selector();
            awaySelector.getListOfMatches(leagueName, teamName, "На выезде", season, lastOrFull);
            awaySelector.getPList(awaySelector.listOfMatches, teamName);
            double awayMatches = awaySelector.listOfMatches.size();

//            double real_Overall     = MyMath.round(100 * (Double.parseDouble(totalSelector.pList.get(9).get(1)) / Double.parseDouble(totalSelector.pList.get(8).get(1))) , 2);
//            double real_Overall_Opp = MyMath.round(100 * (Double.parseDouble(totalSelector.pList.get(9).get(2)) / Double.parseDouble(totalSelector.pList.get(8).get(2))) , 2);
//
//            double realHT     = MyMath.round(100 * (Double.parseDouble(homeSelector.pList.get(9).get(1)) / Double.parseDouble(homeSelector.pList.get(8).get(1))) , 2);
//            double realHT_Opp = MyMath.round(100 * (Double.parseDouble(homeSelector.pList.get(9).get(2)) / Double.parseDouble(homeSelector.pList.get(8).get(2))) , 2);
//            double realAT     = MyMath.round(100 * (Double.parseDouble(awaySelector.pList.get(9).get(1)) / Double.parseDouble(awaySelector.pList.get(8).get(1))) , 2);
//            double realAT_Opp = MyMath.round(100 * (Double.parseDouble(awaySelector.pList.get(9).get(2)) / Double.parseDouble(awaySelector.pList.get(8).get(2))) , 2);

            String recordTotal = teamName + "*" + (int) matchesTotal + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(4).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(4).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(4).get(1)) - Double.parseDouble(totalSelector.pList.get(4).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(4).get(1)) + Double.parseDouble(totalSelector.pList.get(4).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(22).get(1))  * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(22).get(2))  * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(22).get(1)) - Double.parseDouble(totalSelector.pList.get(22).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(22).get(1)) + Double.parseDouble(totalSelector.pList.get(22).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(23).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(23).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(23).get(1)) - Double.parseDouble(totalSelector.pList.get(23).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(23).get(1)) + Double.parseDouble(totalSelector.pList.get(23).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(5).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(5).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(5).get(1)) - Double.parseDouble(totalSelector.pList.get(5).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(5).get(1)) + Double.parseDouble(totalSelector.pList.get(5).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(6).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(6).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(6).get(1)) - Double.parseDouble(totalSelector.pList.get(6).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(6).get(1)) + Double.parseDouble(totalSelector.pList.get(6).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(7).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(7).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(7).get(1)) - Double.parseDouble(totalSelector.pList.get(7).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(7).get(1)) + Double.parseDouble(totalSelector.pList.get(7).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(8).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(8).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(8).get(1)) - Double.parseDouble(totalSelector.pList.get(8).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(8).get(1)) + Double.parseDouble(totalSelector.pList.get(8).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(9).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(9).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(9).get(1)) - Double.parseDouble(totalSelector.pList.get(9).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(9).get(1)) + Double.parseDouble(totalSelector.pList.get(9).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(10).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(10).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(10).get(1)) - Double.parseDouble(totalSelector.pList.get(10).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(10).get(1)) + Double.parseDouble(totalSelector.pList.get(10).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(32).get(1))  * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(32).get(2))  * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(32).get(1)) - Double.parseDouble(totalSelector.pList.get(32).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(32).get(1)) + Double.parseDouble(totalSelector.pList.get(32).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(33).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(33).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(33).get(1)) - Double.parseDouble(totalSelector.pList.get(33).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(33).get(1)) + Double.parseDouble(totalSelector.pList.get(33).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(11).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(11).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(11).get(1)) - Double.parseDouble(totalSelector.pList.get(11).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(11).get(1)) + Double.parseDouble(totalSelector.pList.get(11).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(12).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(12).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(12).get(1)) - Double.parseDouble(totalSelector.pList.get(12).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(12).get(1)) + Double.parseDouble(totalSelector.pList.get(12).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(13).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(13).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(13).get(1)) - Double.parseDouble(totalSelector.pList.get(13).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(13).get(1)) + Double.parseDouble(totalSelector.pList.get(13).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(14).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(14).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(14).get(1)) - Double.parseDouble(totalSelector.pList.get(14).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(14).get(1)) + Double.parseDouble(totalSelector.pList.get(14).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(34).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(34).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(34).get(1)) - Double.parseDouble(totalSelector.pList.get(34).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(34).get(1)) + Double.parseDouble(totalSelector.pList.get(34).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(35).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(35).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(35).get(1)) - Double.parseDouble(totalSelector.pList.get(35).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(35).get(1)) + Double.parseDouble(totalSelector.pList.get(35).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(81).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(81).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(81).get(1)) - Double.parseDouble(totalSelector.pList.get(81).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(81).get(1)) + Double.parseDouble(totalSelector.pList.get(81).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(82).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(82).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(82).get(1)) - Double.parseDouble(totalSelector.pList.get(82).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(82).get(1)) + Double.parseDouble(totalSelector.pList.get(82).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(83).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(83).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(83).get(1)) - Double.parseDouble(totalSelector.pList.get(83).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(83).get(1)) + Double.parseDouble(totalSelector.pList.get(83).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(78).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(78).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(78).get(1)) - Double.parseDouble(totalSelector.pList.get(78).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(78).get(1)) + Double.parseDouble(totalSelector.pList.get(78).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(79).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(79).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(79).get(1)) - Double.parseDouble(totalSelector.pList.get(79).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(79).get(1)) + Double.parseDouble(totalSelector.pList.get(79).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(80).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(80).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(80).get(1)) - Double.parseDouble(totalSelector.pList.get(80).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(80).get(1)) + Double.parseDouble(totalSelector.pList.get(80).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(36).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(36).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(36).get(1)) - Double.parseDouble(totalSelector.pList.get(36).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(36).get(1)) + Double.parseDouble(totalSelector.pList.get(36).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(37).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(37).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(37).get(1)) - Double.parseDouble(totalSelector.pList.get(37).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(37).get(1)) + Double.parseDouble(totalSelector.pList.get(37).get(2))) * matchesTotal, 2) + "*"

                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(38).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(38).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(38).get(1)) - Double.parseDouble(totalSelector.pList.get(38).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(38).get(1)) + Double.parseDouble(totalSelector.pList.get(38).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(39).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(39).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(39).get(1)) - Double.parseDouble(totalSelector.pList.get(39).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(39).get(1)) + Double.parseDouble(totalSelector.pList.get(39).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(44).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(44).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(44).get(1)) - Double.parseDouble(totalSelector.pList.get(44).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(44).get(1)) + Double.parseDouble(totalSelector.pList.get(44).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(45).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(45).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(45).get(1)) - Double.parseDouble(totalSelector.pList.get(45).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(45).get(1)) + Double.parseDouble(totalSelector.pList.get(45).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(46).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(46).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(46).get(1)) - Double.parseDouble(totalSelector.pList.get(46).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(46).get(1)) + Double.parseDouble(totalSelector.pList.get(46).get(2))) * matchesTotal, 2) + "*"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(47).get(1)) * matchesTotal, 2) + "_"
                    + MyMath.round(Double.parseDouble(totalSelector.pList.get(47).get(2)) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(47).get(1)) - Double.parseDouble(totalSelector.pList.get(47).get(2))) * matchesTotal, 2) + "_"
                    + MyMath.round((Double.parseDouble(totalSelector.pList.get(47).get(1)) + Double.parseDouble(totalSelector.pList.get(47).get(2))) * matchesTotal, 2)
                    ;


            String recordHome = teamName + "*" + (int) homeMatches + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(4).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(4).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(4).get(1)) - Double.parseDouble(homeSelector.pList.get(4).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(4).get(1)) + Double.parseDouble(homeSelector.pList.get(4).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(22).get(1)) , 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(22).get(2)) , 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(22).get(1)) - Double.parseDouble(homeSelector.pList.get(22).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(22).get(1)) + Double.parseDouble(homeSelector.pList.get(22).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(23).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(23).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(23).get(1)) - Double.parseDouble(homeSelector.pList.get(23).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(23).get(1)) + Double.parseDouble(homeSelector.pList.get(23).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(5).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(5).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(5).get(1)) - Double.parseDouble(homeSelector.pList.get(5).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(5).get(1)) + Double.parseDouble(homeSelector.pList.get(5).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(6).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(6).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(6).get(1)) - Double.parseDouble(homeSelector.pList.get(6).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(6).get(1)) + Double.parseDouble(homeSelector.pList.get(6).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(7).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(7).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(7).get(1)) - Double.parseDouble(homeSelector.pList.get(7).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(7).get(1)) + Double.parseDouble(homeSelector.pList.get(7).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(8).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(8).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(8).get(1)) - Double.parseDouble(homeSelector.pList.get(8).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(8).get(1)) + Double.parseDouble(homeSelector.pList.get(8).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(9).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(9).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(9).get(1)) - Double.parseDouble(homeSelector.pList.get(9).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(9).get(1)) + Double.parseDouble(homeSelector.pList.get(9).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(10).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(10).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(10).get(1)) - Double.parseDouble(homeSelector.pList.get(10).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(10).get(1)) + Double.parseDouble(homeSelector.pList.get(10).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(32).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(32).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(32).get(1)) - Double.parseDouble(homeSelector.pList.get(32).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(32).get(1)) + Double.parseDouble(homeSelector.pList.get(32).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(33).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(33).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(33).get(1)) - Double.parseDouble(homeSelector.pList.get(33).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(33).get(1)) + Double.parseDouble(homeSelector.pList.get(33).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(11).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(11).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(11).get(1)) - Double.parseDouble(homeSelector.pList.get(11).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(11).get(1)) + Double.parseDouble(homeSelector.pList.get(11).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(12).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(12).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(12).get(1)) - Double.parseDouble(homeSelector.pList.get(12).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(12).get(1)) + Double.parseDouble(homeSelector.pList.get(12).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(13).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(13).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(13).get(1)) - Double.parseDouble(homeSelector.pList.get(13).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(13).get(1)) + Double.parseDouble(homeSelector.pList.get(13).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(14).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(14).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(14).get(1)) - Double.parseDouble(homeSelector.pList.get(14).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(14).get(1)) + Double.parseDouble(homeSelector.pList.get(14).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(34).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(34).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(34).get(1)) - Double.parseDouble(homeSelector.pList.get(34).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(34).get(1)) + Double.parseDouble(homeSelector.pList.get(34).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(35).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(35).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(35).get(1)) - Double.parseDouble(homeSelector.pList.get(35).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(35).get(1)) + Double.parseDouble(homeSelector.pList.get(35).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(81).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(81).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(81).get(1)) - Double.parseDouble(homeSelector.pList.get(81).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(81).get(1)) + Double.parseDouble(homeSelector.pList.get(81).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(82).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(82).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(82).get(1)) - Double.parseDouble(homeSelector.pList.get(82).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(82).get(1)) + Double.parseDouble(homeSelector.pList.get(82).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(83).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(83).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(83).get(1)) - Double.parseDouble(homeSelector.pList.get(83).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(83).get(1)) + Double.parseDouble(homeSelector.pList.get(83).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(78).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(78).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(78).get(1)) - Double.parseDouble(homeSelector.pList.get(78).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(78).get(1)) + Double.parseDouble(homeSelector.pList.get(78).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(79).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(79).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(79).get(1)) - Double.parseDouble(homeSelector.pList.get(79).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(79).get(1)) + Double.parseDouble(homeSelector.pList.get(79).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(80).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(80).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(80).get(1)) - Double.parseDouble(homeSelector.pList.get(80).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(80).get(1)) + Double.parseDouble(homeSelector.pList.get(80).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(36).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(36).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(36).get(1)) - Double.parseDouble(homeSelector.pList.get(36).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(36).get(1)) + Double.parseDouble(homeSelector.pList.get(36).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(37).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(37).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(37).get(1)) - Double.parseDouble(homeSelector.pList.get(37).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(37).get(1)) + Double.parseDouble(homeSelector.pList.get(37).get(2))) * homeMatches, 2) + "*"

                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(38).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(38).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(38).get(1)) - Double.parseDouble(homeSelector.pList.get(38).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(38).get(1)) + Double.parseDouble(homeSelector.pList.get(38).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(39).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(39).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(39).get(1)) - Double.parseDouble(homeSelector.pList.get(39).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(39).get(1)) + Double.parseDouble(homeSelector.pList.get(39).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(44).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(44).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(44).get(1)) - Double.parseDouble(homeSelector.pList.get(44).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(44).get(1)) + Double.parseDouble(homeSelector.pList.get(44).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(45).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(45).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(45).get(1)) - Double.parseDouble(homeSelector.pList.get(45).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(45).get(1)) + Double.parseDouble(homeSelector.pList.get(45).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(46).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(46).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(46).get(1)) - Double.parseDouble(homeSelector.pList.get(46).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(46).get(1)) + Double.parseDouble(homeSelector.pList.get(46).get(2))) * homeMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(47).get(1)) * homeMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(homeSelector.pList.get(47).get(2)) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(47).get(1)) - Double.parseDouble(homeSelector.pList.get(47).get(2))) * homeMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(homeSelector.pList.get(47).get(1)) + Double.parseDouble(homeSelector.pList.get(47).get(2))) * homeMatches, 2)
                    ;

            String recordAway = teamName + "*" + (int) awayMatches + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(4).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(4).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(4).get(1)) - Double.parseDouble(awaySelector.pList.get(4).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(4).get(1)) + Double.parseDouble(awaySelector.pList.get(4).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(22).get(1)) , 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(22).get(2)) , 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(22).get(1)) - Double.parseDouble(awaySelector.pList.get(22).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(22).get(1)) + Double.parseDouble(awaySelector.pList.get(22).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(23).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(23).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(23).get(1)) - Double.parseDouble(awaySelector.pList.get(23).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(23).get(1)) + Double.parseDouble(awaySelector.pList.get(23).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(5).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(5).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(5).get(1)) - Double.parseDouble(awaySelector.pList.get(5).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(5).get(1)) + Double.parseDouble(awaySelector.pList.get(5).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(6).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(6).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(6).get(1)) - Double.parseDouble(awaySelector.pList.get(6).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(6).get(1)) + Double.parseDouble(awaySelector.pList.get(6).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(7).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(7).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(7).get(1)) - Double.parseDouble(awaySelector.pList.get(7).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(7).get(1)) + Double.parseDouble(awaySelector.pList.get(7).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(8).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(8).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(8).get(1)) - Double.parseDouble(awaySelector.pList.get(8).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(8).get(1)) + Double.parseDouble(awaySelector.pList.get(8).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(9).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(9).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(9).get(1)) - Double.parseDouble(awaySelector.pList.get(9).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(9).get(1)) + Double.parseDouble(awaySelector.pList.get(9).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(10).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(10).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(10).get(1)) - Double.parseDouble(awaySelector.pList.get(10).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(10).get(1)) + Double.parseDouble(awaySelector.pList.get(10).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(32).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(32).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(32).get(1)) - Double.parseDouble(awaySelector.pList.get(32).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(32).get(1)) + Double.parseDouble(awaySelector.pList.get(32).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(33).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(33).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(33).get(1)) - Double.parseDouble(awaySelector.pList.get(33).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(33).get(1)) + Double.parseDouble(awaySelector.pList.get(33).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(11).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(11).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(11).get(1)) - Double.parseDouble(awaySelector.pList.get(11).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(11).get(1)) + Double.parseDouble(awaySelector.pList.get(11).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(12).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(12).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(12).get(1)) - Double.parseDouble(awaySelector.pList.get(12).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(12).get(1)) + Double.parseDouble(awaySelector.pList.get(12).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(13).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(13).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(13).get(1)) - Double.parseDouble(awaySelector.pList.get(13).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(13).get(1)) + Double.parseDouble(awaySelector.pList.get(13).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(14).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(14).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(14).get(1)) - Double.parseDouble(awaySelector.pList.get(14).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(14).get(1)) + Double.parseDouble(awaySelector.pList.get(14).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(34).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(34).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(34).get(1)) - Double.parseDouble(awaySelector.pList.get(34).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(34).get(1)) + Double.parseDouble(awaySelector.pList.get(34).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(35).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(35).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(35).get(1)) - Double.parseDouble(awaySelector.pList.get(35).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(35).get(1)) + Double.parseDouble(awaySelector.pList.get(35).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(81).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(81).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(81).get(1)) - Double.parseDouble(awaySelector.pList.get(81).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(81).get(1)) + Double.parseDouble(awaySelector.pList.get(81).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(82).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(82).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(82).get(1)) - Double.parseDouble(awaySelector.pList.get(82).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(82).get(1)) + Double.parseDouble(awaySelector.pList.get(82).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(83).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(83).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(83).get(1)) - Double.parseDouble(awaySelector.pList.get(83).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(83).get(1)) + Double.parseDouble(awaySelector.pList.get(83).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(78).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(78).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(78).get(1)) - Double.parseDouble(awaySelector.pList.get(78).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(78).get(1)) + Double.parseDouble(awaySelector.pList.get(78).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(79).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(79).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(79).get(1)) - Double.parseDouble(awaySelector.pList.get(79).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(79).get(1)) + Double.parseDouble(awaySelector.pList.get(79).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(80).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(80).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(80).get(1)) - Double.parseDouble(awaySelector.pList.get(80).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(80).get(1)) + Double.parseDouble(awaySelector.pList.get(80).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(36).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(36).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(36).get(1)) - Double.parseDouble(awaySelector.pList.get(36).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(36).get(1)) + Double.parseDouble(awaySelector.pList.get(36).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(37).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(37).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(37).get(1)) - Double.parseDouble(awaySelector.pList.get(37).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(37).get(1)) + Double.parseDouble(awaySelector.pList.get(37).get(2))) * awayMatches, 2) + "*"

                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(38).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(38).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(38).get(1)) - Double.parseDouble(awaySelector.pList.get(38).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(38).get(1)) + Double.parseDouble(awaySelector.pList.get(38).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(39).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(39).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(39).get(1)) - Double.parseDouble(awaySelector.pList.get(39).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(39).get(1)) + Double.parseDouble(awaySelector.pList.get(39).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(44).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(44).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(44).get(1)) - Double.parseDouble(awaySelector.pList.get(44).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(44).get(1)) + Double.parseDouble(awaySelector.pList.get(44).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(45).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(45).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(45).get(1)) - Double.parseDouble(awaySelector.pList.get(45).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(45).get(1)) + Double.parseDouble(awaySelector.pList.get(45).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(46).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(46).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(46).get(1)) - Double.parseDouble(awaySelector.pList.get(46).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(46).get(1)) + Double.parseDouble(awaySelector.pList.get(46).get(2))) * awayMatches, 2) + "*"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(47).get(1)) * awayMatches, 2) + "_"
                    + MyMath.round(Double.parseDouble(awaySelector.pList.get(47).get(2)) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(47).get(1)) - Double.parseDouble(awaySelector.pList.get(47).get(2))) * awayMatches, 2) + "_"
                    + MyMath.round((Double.parseDouble(awaySelector.pList.get(47).get(1)) + Double.parseDouble(awaySelector.pList.get(47).get(2))) * awayMatches, 2)
                    ;

            this.overallStatsTable.add(recordTotal);
            this.homeStatsTable.add(recordHome);
            this.awayStatsTable.add(recordAway);

            jpb.setValue((int) ((i+1) / (double) listOfTeams.length * 100));
        }
    }

}
