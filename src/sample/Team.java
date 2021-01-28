package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;

// определяем корневой элемент
@XmlRootElement(name = "Team")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"teamName", "matches", "refStatMatches", "wins", "draws", "loses", "goalsScored", "goalsConceded", "goalsDifference", "points",
        "ballPossetion", "shots", "shotsOnTarget", "shotsOffTarget", "corners", "offsides", "fouls", "saves", "yellowCards", "secondYellowCards",
        "redCards", "directRedCards", "shotsOpponent","shotsOnTargetOpponent","shotsOffTargetOpponent","cornersOpponent",
        "offsidesOpponent", "foulsOpponent", "savesOpponent", "yellowCardsOpponent", "secondYellowCardsOpponent",
        "redCardsOpponent", "directRedCardsOpponent", "xGMatches" ,"xG", "xGA", "penFor", "penAg", "ownGoalsFor", "ownGoalsAg",
        "xDiff", "xWins", "xDraws", "xLoses", "xPoints", "matchList"})

public class Team {

    public String teamName;
    //======================= Основные параметры команды
    public int matches;
    public int refStatMatches;
    public int wins;
    public int draws;
    public int loses;
    public int goalsScored;
    public int goalsConceded;
    public int goalsDifference;
    public int points;
    //======================= Статистические параметры команды за весь турнир и за матч (Заполняемые)
    public int ballPossetion;
    public int shots;
    public int shotsOnTarget;
    public int shotsOffTarget;
    public int corners;
    public int offsides;
    public int fouls;
    public int saves;
    public int yellowCards;
    public int secondYellowCards;
    public int redCards;
    public int directRedCards;
    public int shotsOpponent;
    public int shotsOnTargetOpponent;
    public int shotsOffTargetOpponent;
    public int cornersOpponent;
    public int offsidesOpponent;
    public int foulsOpponent;
    public int savesOpponent;
    public int yellowCardsOpponent;
    public int secondYellowCardsOpponent;
    public int redCardsOpponent;
    public int directRedCardsOpponent;
    public int xGMatches;
    public double xG;
    public double xGA;
    public int penFor;
    public int penAg;
    public int ownGoalsFor;
    public int ownGoalsAg;
    public double xDiff;
    public double xWins;
    public double xDraws;
    public double xLoses;
    public double xPoints;
    //======================= Список матчей
    public ArrayList<String> matchList;

    public Team() {
        this.matchList = new ArrayList<>();
    }

    public Team(String name) {

        this.teamName = name;
        this.matches = 0;
        this.refStatMatches = 0;
        this.wins = 0;
        this.draws = 0;
        this.loses = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.goalsDifference = 0;
        this.points = 0;
        this.ballPossetion = 0;
        this.shots = 0;
        this.shotsOnTarget = 0;
        this.shotsOffTarget = 0;
        this.corners = 0;
        this.offsides = 0;
        this.fouls = 0;
        this.saves = 0;
        this.yellowCards = 0;
        this.secondYellowCards = 0;
        this.redCards = 0;
        this.directRedCards = 0;
        this.shotsOpponent = 0;
        this.shotsOnTargetOpponent = 0;
        this.shotsOffTargetOpponent = 0;
        this.cornersOpponent = 0;
        this.offsidesOpponent = 0;
        this.foulsOpponent = 0;
        this.savesOpponent = 0;
        this.yellowCardsOpponent = 0;
        this.secondYellowCardsOpponent = 0;
        this.redCardsOpponent = 0;
        this.directRedCardsOpponent = 0;
        this.xGMatches = 0;
        this.xG = 0.0;
        this.xGA = 0.0;
        this.penFor = 0;
        this.penAg = 0;
        this.ownGoalsFor = 0;
        this.ownGoalsAg = 0;
        this.xDiff = 0.0;
        this.xWins = 0.0;
        this.xDraws = 0.0;
        this.xLoses = 0.0;
        this.xPoints = 0.0;
        this.matchList = new ArrayList<>();
    }

    public static Team getTeamFromFileWithSeason(String teamName, String season, String league){
        String path = "database/" + season + "/" + league + "/Teams/" + teamName+".xml";
        Team team = null;
        int n = Settings.getNumberOfAccount();
        try {
            FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/data/football/database/" + season + "/" + league + "/Teams/" + teamName+".xml", path);
            LogWriter.writeLog("Successful download of " + teamName + ".xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyException.sendException(e.getStackTrace(), path + " не найден!");
        }

        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Team.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            team = (Team) un.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
            MyException.sendException(e.getStackTrace(), path + " содержит ошибку!");
        }

        return team;
    }

    public static String getNameOfTeam(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[1].equals(teamName)){
                    result = str.split("=")[0];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getShortName(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[2];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getLeague(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[3];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getWebsite(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[4];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getTwitter(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[5];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getWeather(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[6];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getTransferMarkt(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[7];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getMedicine(String teamName){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[7];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getNameForMatchCenter(String teamName, String league){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[8].equals(teamName) && str.split("=")[3].equals(league)){
                    return str.split("=")[0];
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), teamName);
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static boolean isTeamFoundInSeason(String teamName, String season){
        boolean flagResult = false;
        try {
            File fileDir = new File("database/" + season + "/leagues/" + Team.getLeague(teamName) + ".txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null) && !flagResult) {
                if (str.equals(teamName)){
                    flagResult = true;
                }
            }
            in.close();
        } catch (IOException e){
            //System.out.println(e.getMessage());
        }
        return flagResult;
    }
}