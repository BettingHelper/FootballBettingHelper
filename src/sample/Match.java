package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;

// определяем корневой элемент
@XmlRootElement(name = "Match")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"homeTeam", "awayTeam", "homeScore", "awayScore", "homeScore1T", "awayScore1T", "homeScore2T", "awayScore2T",
        "homeBallPossession", "awayBallPossession", "homeBallPossession1T", "awayBallPossession1T", "homeBallPossession2T", "awayBallPossession2T",
        "homeCorners", "awayCorners", "homeCorners1T", "awayCorners1T", "homeCorners2T", "awayCorners2T",
        "homeYellowCards", "awayYellowCards", "homeYellowCards1T", "awayYellowCards1T", "homeYellowCards2T", "awayYellowCards2T",
        "homeRedCards", "awayRedCards", "homeShots", "awayShots", "homeShots1T", "awayShots1T", "homeShots2T", "awayShots2T",
        "homeShotsOnTarget", "awayShotsOnTarget", "homeShotsOnTarget1T", "awayShotsOnTarget1T", "homeShotsOnTarget2T", "awayShotsOnTarget2T",
        "homeShotsOffTarget", "awayShotsOffTarget", "homeShotsOffTarget1T", "awayShotsOffTarget1T", "homeShotsOffTarget2T", "awayShotsOffTarget2T",
        "homeBlockedShots", "awayBlockedShots", "homeBlockedShots1T", "awayBlockedShots1T", "homeBlockedShots2T", "awayBlockedShots2T",
        "homeOffsides", "awayOffsides", "homeOffsides1T", "awayOffsides1T", "homeOffsides2T", "awayOffsides2T",
        "homeFouls", "awayFouls", "homeFouls1T", "awayFouls1T", "homeFouls2T", "awayFouls2T",
        "homeShotsOnPost", "awayShotsOnPost", "homeSaves", "awaySaves", "homeSaves1T", "awaySaves1T", "homeSaves2T", "awaySaves2T",
        "homeDribbles", "awayDribbles", "homeDribbles1T", "awayDribbles1T", "homeDribbles2T", "awayDribbles2T",
        "homeAerialsWon", "awayAerialsWon", "homeAerialsWon1T", "awayAerialsWon1T", "homeAerialsWon2T", "awayAerialsWon2T",
        "homeClearances", "awayClearances", "homeClearances1T", "awayClearances1T", "homeClearances2T", "awayClearances2T",
        "homeInterceptions", "awayInterceptions", "homeInterceptions1T", "awayInterceptions1T", "homeInterceptions2T", "awayInterceptions2T",
        "homeTackles", "awayTackles", "homeTackles1T", "awayTackles1T", "homeTackles2T", "awayTackles2T",
        "homeDispossessed", "awayDispossessed", "homeDispossessed1T", "awayDispossessed1T", "homeDispossessed2T", "awayDispossessed2T",
        "homeKeyPasses", "awayKeyPasses", "homeKeyPasses1T", "awayKeyPasses1T", "homeKeyPasses2T", "awayKeyPasses2T",
        "homePasses", "awayPasses", "homePasses1T", "awayPasses1T", "homePasses2T", "awayPasses2T",
        "homePassesSuccessfully", "awayPassesSuccessfully", "homePassesSuccessfully1T", "awayPassesSuccessfully1T", "homePassesSuccessfully2T", "awayPassesSuccessfully2T",
        "referee", "homeXG", "awayXG", "homeSecondYellowCards", "awaySecondYellowCards", "homeDirectRedCards", "awayDirectRedCards",
        "homeOGScored", "awayOGScored", "homePen", "awayPen", "homePenScored", "awayPenScored", "URIonWhoscored",
        "homeGoalsBy15minutes", "awayGoalsBy15minutes", "firstYCMinute", "lastYCMinute", "firstYCTeam", "lastYCTeam",
        "homeThrowIns", "awayThrowIns", "homeThrowIns1T", "awayThrowIns1T", "homeThrowIns2T", "awayThrowIns2T",
        "homeGoalKicks", "awayGoalKicks", "homeGoalKicks1T", "awayGoalKicks1T", "homeGoalKicks2T", "awayGoalKicks2T",
        "date", "league", "isWhoScoredStats", "title", "season"})

public class Match {
    public String homeTeam;
    public String awayTeam;
    public int homeScore;
    public int awayScore;
    public int homeScore1T;
    public int awayScore1T;
    public int homeScore2T;
    public int awayScore2T;
    public int homeBallPossession;
    public int awayBallPossession;
    public int homeBallPossession1T;
    public int awayBallPossession1T;
    public int homeBallPossession2T;
    public int awayBallPossession2T;
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
    public int homeShots1T;
    public int awayShots1T;
    public int homeShots2T;
    public int awayShots2T;
    public int homeShotsOnTarget;
    public int awayShotsOnTarget;
    public int homeShotsOnTarget1T;
    public int awayShotsOnTarget1T;
    public int homeShotsOnTarget2T;
    public int awayShotsOnTarget2T;
    public int homeShotsOffTarget;
    public int awayShotsOffTarget;
    public int homeShotsOffTarget1T;
    public int awayShotsOffTarget1T;
    public int homeShotsOffTarget2T;
    public int awayShotsOffTarget2T;
    public int homeBlockedShots;
    public int awayBlockedShots;
    public int homeBlockedShots1T;
    public int awayBlockedShots1T;
    public int homeBlockedShots2T;
    public int awayBlockedShots2T;
    public int homeOffsides;
    public int awayOffsides;
    public int homeOffsides1T;
    public int awayOffsides1T;
    public int homeOffsides2T;
    public int awayOffsides2T;
    public int homeFouls;
    public int awayFouls;
    public int homeFouls1T;
    public int awayFouls1T;
    public int homeFouls2T;
    public int awayFouls2T;
    public int homeShotsOnPost;
    public int awayShotsOnPost;
    public int homeSaves;
    public int awaySaves;
    public int homeSaves1T;
    public int awaySaves1T;
    public int homeSaves2T;
    public int awaySaves2T;
    public int homeDribbles;
    public int awayDribbles;
    public int homeDribbles1T;
    public int awayDribbles1T;
    public int homeDribbles2T;
    public int awayDribbles2T;
    public int homeAerialsWon;
    public int awayAerialsWon;
    public int homeAerialsWon1T;
    public int awayAerialsWon1T;
    public int homeAerialsWon2T;
    public int awayAerialsWon2T;
    public int homeClearances;
    public int awayClearances;
    public int homeClearances1T;
    public int awayClearances1T;
    public int homeClearances2T;
    public int awayClearances2T;
    public int homeInterceptions;
    public int awayInterceptions;
    public int homeInterceptions1T;
    public int awayInterceptions1T;
    public int homeInterceptions2T;
    public int awayInterceptions2T;
    public int homeDispossessed;
    public int awayDispossessed;
    public int homeDispossessed1T;
    public int awayDispossessed1T;
    public int homeDispossessed2T;
    public int awayDispossessed2T;
    public int homeTackles;
    public int awayTackles;
    public int homeTackles1T;
    public int awayTackles1T;
    public int homeTackles2T;
    public int awayTackles2T;
    public int homePasses;
    public int awayPasses;
    public int homePasses1T;
    public int awayPasses1T;
    public int homePasses2T;
    public int awayPasses2T;
    public int homeKeyPasses;
    public int awayKeyPasses;
    public int homeKeyPasses1T;
    public int awayKeyPasses1T;
    public int homeKeyPasses2T;
    public int awayKeyPasses2T;
    public int homeThrowIns;
    public int awayThrowIns;
    public int homeThrowIns1T;
    public int awayThrowIns1T;
    public int homeThrowIns2T;
    public int awayThrowIns2T;
    public int homeGoalKicks;
    public int awayGoalKicks;
    public int homeGoalKicks1T;
    public int awayGoalKicks1T;
    public int homeGoalKicks2T;
    public int awayGoalKicks2T;
    public int homePassesSuccessfully;
    public int awayPassesSuccessfully;
    public int homePassesSuccessfully1T;
    public int awayPassesSuccessfully1T;
    public int homePassesSuccessfully2T;
    public int awayPassesSuccessfully2T;
    public String referee;
    public double homeXG;
    public double awayXG;
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
    public String URIonWhoscored;
    public String homeGoalsBy15minutes;
    public String awayGoalsBy15minutes;
    public int firstYCMinute;
    public int lastYCMinute;
    public String firstYCTeam = "";
    public String lastYCTeam = "";
    public String date;
    public String league;
    public boolean isWhoScoredStats;
    public String season;
    public String title;

    public Match(){
    }

    public static Match getMatchFromFileByName(String matchName){
        Match match = null;
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            match = (Match) un.unmarshal(new File(matchName));
            if (match.homeXG + match.awayXG == 0 && match.isNeedToDownloadXG()){
                throw new JAXBException("Need to download match");
            } else
                return (Match) un.unmarshal(new File(matchName));
        } catch (JAXBException e) {
            int n = Settings.getNumberOfAccount();
            try {
                FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/data/football/" + matchName, matchName);
                LogWriter.writeLog("Successful download of " + matchName);
                match = getMatchFromFileByNameWithoutDownload(matchName);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                MyException.sendException(e.getStackTrace(), matchName + " не найден!");
            }
        }
        return match;
    }

    public static Match getMatchFromFileByNameWithoutDownload(String matchName){
        Match match = null;
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            match = (Match) un.unmarshal(new File(matchName));
        } catch (JAXBException e) {
            MyException.sendException(e.getStackTrace(), matchName+ " содержит ошибку!");
        }
        return match;
    }

    public boolean isNeedToDownloadXG(){
        boolean result = false;
        String leaguesWithXG = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("settings/leaguesWithXG.txt"), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                leaguesWithXG += str + "\n";
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            MyException.sendException(e.getStackTrace(), "settings/leaguesWithXG.txt");
        }
        if (leaguesWithXG.contains(league) && (leaguesWithXG.contains(season)))
            result = true;

        return result;
    }
}
