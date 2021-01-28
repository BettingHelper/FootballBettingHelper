package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;

// определяем корневой элемент
@XmlRootElement(name = "Referee")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"name", "matches", "expandedMatches", "homeWins", "draws", "awayWins",
        "totalYC", "homeYC", "awayYC", "totalSecondYC", "homeSecondYC", "awaySecondYC",
        "totalDirectRC", "homeDirectRC", "awayDirectRC", "totalPen", "homePen", "awayPen",
        "totalFouls", "homeFouls", "awayFouls", "matchList"})

public class Referee {

    public String name;
    public int matches;
    public int expandedMatches;
    public int homeWins;
    public int draws;
    public int awayWins;
    public int totalYC;
    public int homeYC;
    public int awayYC;
    public int totalSecondYC;
    public int homeSecondYC;
    public int awaySecondYC;
    public int totalDirectRC;
    public int homeDirectRC;
    public int awayDirectRC;
    public int totalPen;
    public int homePen;
    public int awayPen;
    public int totalFouls;
    public int homeFouls;
    public int awayFouls;
    //======================= Список матчей
    public ArrayList<String> matchList;

    public Referee() {
        this.matchList = new ArrayList<>();
    }

    public Referee(String name) {
        this.name = name;
        this.matches = 0;
        this.expandedMatches = 0;
        this.homeWins = 0;
        this.draws = 0;
        this.awayWins = 0;
        this.totalYC = 0;
        this.homeYC = 0;
        this.awayYC = 0;
        this.totalSecondYC = 0;
        this.homeSecondYC = 0;
        this.awaySecondYC = 0;
        this.totalDirectRC = 0;
        this.homeDirectRC = 0;
        this.awayDirectRC = 0;
        this.totalPen = 0;
        this.homePen = 0;
        this.awayPen = 0;
        this.totalFouls = 0;
        this.homeFouls = 0;
        this.awayFouls = 0;

        this.matchList = new ArrayList<>();
    }

    public static Referee getRefFromFile(String refName, String season, String league) {
        String fileName = "database/" + season + "/" + league + "/Referees/" + refName+".xml";
        Referee ref = null;
        int n = Settings.getNumberOfAccount();
        try {
            FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/data/football/" + fileName, fileName);
            LogWriter.writeLog("Successful download of " + refName + ".xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Referee.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            ref = (Referee) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            MyException.sendException(e.getStackTrace(), fileName + " содержит ошибку!");
            e.printStackTrace();
        }

        return ref;
    }

    public static String getLeague(String name){
        String result = "";
        try {
            File fileDir = new File("database/Referees.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(name)){
                    result = str.split("=")[2];
                }
            }
            in.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}