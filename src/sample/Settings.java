package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

// определяем корневой элемент
@XmlRootElement(name = "Settings")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"showGoals", "showxG", "showxGA", "showxGAndxGA", "showGoals1T", "showGoals2T", "showPossession",
        "showPossession1T", "showPossession2T", "showShots", "showShots1T", "showShots2T", "showShotsOnTarget",
        "showShotsOnTarget1T", "showShotsOnTarget2T", "showShotsOffTarget", "showShotsOffTarget1T", "showShotsOffTarget2T",
        "showBlockedShots", "showBlockedShots1T", "showBlockedShots2T", "showShotsOnPost", "showCorners", "showCorners1T",
        "showCorners2T", "showOffsides", "showOffsides1T", "showOffsides2T", "showFouls", "showFouls1T", "showFouls2T",
        "showYC", "showYC1T", "showYC2T", "showFirstYCMinute", "showLastYCMinute", "showRC", "showDribbles", "showAerialsWon",
        "showSaves", "showClearances", "showInterceptions", "showTackles", "showDispossessed", "showPassesAcc", "showKeyPasses",
        "showGoalKicks", "showGoalKicks1T", "showGoalKicks2T",
        "showThrowIns", "showThrowIns1T", "showThrowIns2T",
        "showTotal", "showGoalsBy15Min", "windowResolution", "trendPercent", "form", "windowsOnTop",
        "localTime", "pivotTable", "showGraphics", "ip", "useColors", "trendsHA", "bubbleChartsHA"
})

public class Settings {
    public final static String TMP_DIR = System.getProperty("java.io.tmpdir");
    public boolean showGoals;
    public boolean showxG;
    public boolean showxGA;
    public boolean showxGAndxGA;
    public boolean showGoals1T;
    public boolean showGoals2T;
    public boolean showPossession;
    public boolean showPossession1T;
    public boolean showPossession2T;
    public boolean showShots;
    public boolean showShots1T;
    public boolean showShots2T;
    public boolean showShotsOnTarget;
    public boolean showShotsOnTarget1T;
    public boolean showShotsOnTarget2T;
    public boolean showShotsOffTarget;
    public boolean showShotsOffTarget1T;
    public boolean showShotsOffTarget2T;
    public boolean showBlockedShots;
    public boolean showBlockedShots1T;
    public boolean showBlockedShots2T;
    public boolean showShotsOnPost;
    public boolean showCorners;
    public boolean showCorners1T;
    public boolean showCorners2T;
    public boolean showOffsides;
    public boolean showOffsides1T;
    public boolean showOffsides2T;
    public boolean showFouls;
    public boolean showFouls1T;
    public boolean showFouls2T;
    public boolean showYC;
    public boolean showYC1T;
    public boolean showYC2T;
    public boolean showFirstYCMinute;
    public boolean showLastYCMinute;
    public boolean showRC;
    public boolean showDribbles;
    public boolean showAerialsWon;
    public boolean showSaves;
    public boolean showClearances;
    public boolean showInterceptions;
    public boolean showTackles;
    public boolean showDispossessed;
    public boolean showPassesAcc;
    public boolean showKeyPasses;
    public boolean showThrowIns;
    public boolean showThrowIns1T;
    public boolean showThrowIns2T;
    public boolean showGoalKicks;
    public boolean showGoalKicks1T;
    public boolean showGoalKicks2T;
    public boolean showTotal;
    public boolean showGoalsBy15Min;
    public boolean windowsOnTop;
    public String windowResolution;
    public String trendPercent;
    public String form;
    public String ip;
    public int localTime;
    public boolean pivotTable;
    public boolean trendsHA;
    public boolean bubbleChartsHA;
    public boolean showGraphics;
    public boolean useColors;

    public Settings(){
    }

    public Settings(ArrayList<Boolean> arrayList, int width, int height, int percent, String formS, String ip, int localTime) {
        this.showGoals = arrayList.get(0);
        this.showxG = arrayList.get(1);
        this.showxGA = arrayList.get(2);
        this.showxGAndxGA = arrayList.get(3);
        this.showGoals1T = arrayList.get(4);
        this.showGoals2T = arrayList.get(5);
        this.showPossession = arrayList.get(6);
        this.showPossession1T = arrayList.get(7);
        this.showPossession2T = arrayList.get(8);
        this.showShots = arrayList.get(9);
        this.showShots1T = arrayList.get(10);
        this.showShots2T = arrayList.get(11);
        this.showShotsOnTarget = arrayList.get(12);
        this.showShotsOnTarget1T = arrayList.get(13);
        this.showShotsOnTarget2T = arrayList.get(14);
        this.showShotsOffTarget = arrayList.get(15);
        this.showShotsOffTarget1T = arrayList.get(16);
        this.showShotsOffTarget2T = arrayList.get(17);
        this.showBlockedShots = arrayList.get(18);
        this.showBlockedShots1T = arrayList.get(19);
        this.showBlockedShots2T = arrayList.get(20);
        this.showShotsOnPost = arrayList.get(21);
        this.showCorners = arrayList.get(22);
        this.showCorners1T = arrayList.get(23);
        this.showCorners2T = arrayList.get(24);
        this.showOffsides = arrayList.get(25);
        this.showOffsides1T = arrayList.get(26);
        this.showOffsides2T = arrayList.get(27);
        this.showFouls = arrayList.get(28);
        this.showFouls1T = arrayList.get(29);
        this.showFouls2T = arrayList.get(30);
        this.showYC = arrayList.get(31);
        this.showYC1T = arrayList.get(32);
        this.showYC2T = arrayList.get(33);
        this.showFirstYCMinute = arrayList.get(34);
        this.showLastYCMinute = arrayList.get(35);
        this.showRC = arrayList.get(36);
        this.showDribbles = arrayList.get(37);
        this.showAerialsWon = arrayList.get(38);
        this.showSaves = arrayList.get(39);
        this.showClearances = arrayList.get(40);
        this.showInterceptions = arrayList.get(41);
        this.showTackles = arrayList.get(42);
        this.showDispossessed = arrayList.get(43);
        this.showPassesAcc = arrayList.get(44);
        this.showKeyPasses = arrayList.get(45);
        this.showGoalKicks = arrayList.get(46);
        this.showGoalKicks1T = arrayList.get(47);
        this.showGoalKicks2T = arrayList.get(48);
        this.showThrowIns = arrayList.get(49);
        this.showThrowIns1T = arrayList.get(50);
        this.showThrowIns2T = arrayList.get(51);
        this.windowResolution = String.valueOf(width) + "x" + String.valueOf(height);
        this.trendPercent = String.valueOf(percent);
        this.form = formS;
        this.windowsOnTop = arrayList.get(52);
        this.pivotTable = arrayList.get(53);
        this.showGraphics = arrayList.get(54);
        this.trendsHA = arrayList.get(55);
        this.useColors = arrayList.get(56);
        this.bubbleChartsHA = arrayList.get(57);
        this.showGoalsBy15Min = arrayList.get(58);
        this.showTotal = arrayList.get(59);
        this.localTime = localTime;
        this.ip = ip;
    }

    public static Settings getSettingsFromFile(){
        String fileName = "settings/graphicSettings.xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Settings) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 24<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);

        }
        return null;
    }

    public ArrayList<Boolean> getShowList(){
        ArrayList<Boolean> result = new ArrayList<>();
        result.add(this.showGoals);
        result.add(this.showxG);
        result.add(this.showxGA);
        result.add(this.showxGAndxGA);
        result.add(this.showGoals1T);
        result.add(this.showGoals2T);
        result.add(this.showPossession);
        result.add(this.showPossession1T);
        result.add(this.showPossession2T);
        result.add(this.showShots);
        result.add(this.showShots1T);
        result.add(this.showShots2T);
        result.add(this.showShotsOnTarget);
        result.add(this.showShotsOnTarget1T);
        result.add(this.showShotsOnTarget2T);
        result.add(this.showShotsOffTarget);
        result.add(this.showShotsOffTarget1T);
        result.add(this.showShotsOffTarget2T);
        result.add(this.showBlockedShots);
        result.add(this.showBlockedShots1T);
        result.add(this.showBlockedShots2T);
        result.add(this.showShotsOnPost);
        result.add(this.showCorners);
        result.add(this.showCorners1T);
        result.add(this.showCorners2T);
        result.add(this.showOffsides);
        result.add(this.showOffsides1T);
        result.add(this.showOffsides2T);
        result.add(this.showFouls);
        result.add(this.showFouls1T);
        result.add(this.showFouls2T);
        result.add(this.showYC);
        result.add(this.showYC1T);
        result.add(this.showYC2T);
        result.add(this.showFirstYCMinute);
        result.add(this.showLastYCMinute);
        result.add(this.showRC);
        result.add(this.showDribbles);
        result.add(this.showAerialsWon);
        result.add(this.showSaves);
        result.add(this.showClearances);
        result.add(this.showInterceptions);
        result.add(this.showTackles);
        result.add(this.showDispossessed);
        result.add(this.showPassesAcc);
        result.add(this.showKeyPasses);
        result.add(this.showGoalKicks);
        result.add(this.showGoalKicks1T);
        result.add(this.showGoalKicks2T);
        result.add(this.showThrowIns);
        result.add(this.showThrowIns1T);
        result.add(this.showThrowIns2T);
        result.add(this.windowsOnTop);
        result.add(this.pivotTable);
        result.add(this.showGraphics);
        result.add(this.trendsHA);
        result.add(this.useColors);
        result.add(this.bubbleChartsHA);
        result.add(this.showGoalsBy15Min);
        result.add(this.showTotal);

        return result;
    }

    public void pushSettingsToFile(){
        String fileName = "settings/graphicSettings.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Settings.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 25<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }
    }

    public static String getCurrentSeasonInLeague(String leagueName){
        String result = "";
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(leagueName)){
                    return str.split("=")[2];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getDefaultSeason(){
        String result = "";
        try {
            File fileDir = new File("database/seasons.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            result = in.readLine();
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static ArrayList<String> getListOfSeasons(){
        ArrayList<String> result = new ArrayList<>();
        try {
            File fileDir = new File("database/seasons.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            while (((str = in.readLine()) != null)) {
                result.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getSerialNumber() throws Exception{
        String line;
        StringBuilder serial = new StringBuilder("F");
        Process process = Runtime.getRuntime().exec("cmd /c wmic path Win32_PhysicalMedia where \"tag like '%Drive0%'\" Get SerialNumber");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(process.getInputStream()) );
        while ((line = in.readLine()) != null) {
            if ((!line.contains("erial"))&&(!line.equals("")))
                serial.append(line);
        }
        in.close();
        serial = new StringBuilder(serial.toString().replaceAll("\\s", ""));
        if (serial.toString().toUpperCase().contains("ERROR")){
            PopupWindow window = new PopupWindow("<html>   Ошибка при проверке ID. Код ошибки 1<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }
        return serial.toString();
    }

    public static void setLastUpdate(String path){
        File file = new File("settings/lastUpdate.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(path);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getLastUpdateName(){
        String result = "";
        try {
            File fileDir = new File("settings/lastUpdate.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static boolean checkKey(String serialNumber) throws Exception{
        boolean flag = false;
        int numberOfAccount = Settings.getNumberOfAccount();
        try {
            String res = FTPLoader.downloadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/users.txt", Settings.TMP_DIR + "/users.txt");
            if (res.contains("Exception"))
                throw new Exception(res);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при проверке ID. Код ошибки 2<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        String ID = getIDBySerialNumber(serialNumber);
        String user = getUserBySerialNumber(serialNumber);
        try {
            File fileDir = new File(Settings.TMP_DIR + "/users.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (serialNumber.contains(str.split("=")[2]))
                    ID = str.split("=")[2];
                if (serialNumber.contains(str.split("=")[2]) && str.split("=")[1].toUpperCase().equals("YES")){
                    flag = true;
                    break;
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 3<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }

        if (flag){
            try {
                String res = FTPLoader.downloadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/visit_log_FBH.txt", Settings.TMP_DIR + "/visit_log_FBH.txt");
                File file = new File(Settings.TMP_DIR + "/visit_log_FBH.txt");
                FileWriter fr = null;
                BufferedWriter br = null;
                try {
                    //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
                    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));

                    String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                    String month = String.valueOf(c.get(Calendar.MONTH)+1);
                    String year = String.valueOf(c.get(Calendar.YEAR));
                    String hour = String.valueOf(c.get(11));
                    String minute = String.valueOf(c.get(12));
                    String second = String.valueOf(c.get(13));

                    if (day.length()==1){
                        day = "0" + day;
                    }
                    if (month.length()==1){
                        month = "0" + month;
                    }
                    if (hour.length()==1){
                        hour = "0" + hour;
                    }
                    if (minute.length()==1){
                        minute = "0" + minute;
                    }
                    if (second.length()==1){
                        second = "0" + second;
                    }

                    fr = new FileWriter(file,true);
                    br = new BufferedWriter(fr);
                    br.newLine();
                    //теперь мы можем использовать метод write или метод append
                    try {
                        br.write("[" + day + "." + month + "." + year + "   " + hour + ":" + minute + ":" + second + "]   "  + "User " + user + " checked serial number. ID: " + ID + ".");
                    } catch (Exception e) {
                        e.printStackTrace();
                        PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 4<br>" +
                                "Обратитесь к администратору</html>");
                        window.setVisible(true);
                        window.setAlwaysOnTop(true);
                    }

                } catch (IOException e) {
                    PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 32<br>" +
                            "Обратитесь к администратору</html>");
                    window.setVisible(true);
                    window.setAlwaysOnTop(true);
                } finally{
                    try {
                        br.close();
                        fr.close();
                        FTPLoader.uploadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/visit_log_FBH.txt", Settings.TMP_DIR + "/visit_log_FBH.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                        PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 5<br>" +
                                "Обратитесь к администратору</html>");
                        window.setVisible(true);
                        window.setAlwaysOnTop(true);
                    }
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 6<br>" +
                        "Обратитесь к администратору</html>");
                window.setVisible(true);
                window.setAlwaysOnTop(true);
            }
        }

        return flag;
    }

    public static boolean checkDate(String serialNumber){
        String user = "checkDateUser";
        String ID = "NO_ID";
        boolean flag = false;
        int numberOfAccount = Settings.getNumberOfAccount();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));

        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(c.get(Calendar.MONTH)+1);
        String year = String.valueOf(c.get(Calendar.YEAR));

        if ((year.equals("2020") && month.equals("1")) || (year.equals("2020") && month.equals("2") && Integer.parseInt(day) < 23))
            flag = true;

        if (flag){
            try {
                FTPLoader.downloadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/visit_log_FBH.txt", Settings.TMP_DIR + "/visit_log_FBH.txt");
                File file = new File(Settings.TMP_DIR + "/visit_log_FBH.txt");
                FileWriter fr = null;
                BufferedWriter br = null;
                try {
                    //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
                    String hour = String.valueOf(c.get(11));
                    String minute = String.valueOf(c.get(12));
                    String second = String.valueOf(c.get(13));

                    if (day.length()==1){
                        day = "0" + day;
                    }
                    if (month.length()==1){
                        month = "0" + month;
                    }
                    if (hour.length()==1){
                        hour = "0" + hour;
                    }
                    if (minute.length()==1){
                        minute = "0" + minute;
                    }
                    if (second.length()==1){
                        second = "0" + second;
                    }

                    fr = new FileWriter(file,true);
                    br = new BufferedWriter(fr);
                    br.newLine();
                    //теперь мы можем использовать метод write или метод append
                    try {
                        br.write("[" + day + "." + month + "." + year + "   " + hour + ":" + minute + ":" + second + "]   "  + "User " + user + " checked serial number. ID: " + ID + ".");                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 4<br>" +
                                "Обратитесь к администратору." + ".</html>");
                        window.setVisible(true);
                        window.setAlwaysOnTop(true);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    try {
                        br.close();
                        fr.close();
                        FTPLoader.uploadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/visit_log_FBH.txt", Settings.TMP_DIR + "/visit_log_FBH.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                        PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 5<br>" +
                                "Обратитесь к администратору." + ".</html>");
                        window.setVisible(true);
                        window.setAlwaysOnTop(true);
                    }
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 6<br>" +
                        "Обратитесь к администратору." + ".</html>");
                window.setVisible(true);
                window.setAlwaysOnTop(true);
            }
        }

        return flag;
    }

    public String getIp(){
        return ip;
    }

    public static String getLogin(int n){
        return "client" + String.valueOf(n);
    }

    public static String getPassword(int n){
        switch (n){
            case 0:{
                return "wfa92^@m<WOFphfWOQG*@5202nIG@(@4t3gg2";
            }
            case 1:{
                return "vXSdk07852!%d{FWjf23451";
            }
            case 2:{
                return "d&21?fegFF(2nFpgr%@741";
            }
            case 3:{
                return "*12dskDE@(JdvsE6EOx";
            }
            case 4:{
                return "dP@RTU#ffdjgf203R$KjHSfG;w[";
            }
            case 5:{
                return "kgnW439352R2@-)#@$JG<eaE[WFKsgkp2";
            }
            case 6:{
                return "GPdwn1086%$@$,mf@$)@";
            }
            case 7:{
                return "&249HF@ebww324%@((LdwmwfI#24";
            }
            case 8:{
                return "*2Hd$2mH&3D,p)842nfU3*egf73";
            }
            case 9:{
                return "bf(@#br3525BWEF^#@(*$f6e3tjg2g";
            }

        }
        return null;
    }

    public static int getNumberOfAccount(){
        double t = MyMath.round(Math.random(), 2);
        String r = String.valueOf(t).substring(2,3);
        return Integer.parseInt(r);
    }

    public static String toRefreshDatabase(WindowWithProgressBar windowWithProgressBar, int numberOfAccount){
        String resultOfRefreshing = "Fail";
        String lastUpdateName = getLastUpdateName();

        String fileName = "_listOfUpdates.txt";
        try {
            String resultOfDownload = FTPLoader.downloadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/football/database/updates/" + fileName, Settings.TMP_DIR + "/" + fileName);
            if (resultOfDownload.equals("Success")){
                if (lastUpdateName.equals("")){
                    String listFile = Settings.TMP_DIR + "/_listOfUpdates.txt";
                    try {
                        File fileDir = new File(listFile);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(fileDir), "UTF-8"));
                        String str;
                        while (((str = in.readLine()) != null)) {
                            getUpdatesByName(str, windowWithProgressBar, numberOfAccount);
                            Settings.setLastUpdate(str);
                        }
                        in.close();
                        resultOfRefreshing = "Success";
                    } catch (IOException e)
                    {
                        System.out.println(e.getMessage());
                        PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 26<br>" +
                                "Обратитесь к администратору</html>");
                        window.setVisible(true);
                        window.setAlwaysOnTop(true);
                    }
                } else {
                    String listFile = Settings.TMP_DIR + "/_listOfUpdates.txt";
                    try {
                        File fileDir = new File(listFile);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(fileDir), "UTF-8"));
                        String str;
                        boolean flag = false;
                        while (((str = in.readLine()) != null)) {
                            if (str.contains(lastUpdateName) || flag){
                                flag = true;
                                if (flag){
                                    getUpdatesByName(str, windowWithProgressBar, numberOfAccount);
                                    Settings.setLastUpdate(str);
                                }
                            }
                        }
                        in.close();
                        resultOfRefreshing = "Success";
                    } catch (IOException e)
                    {
                        System.out.println(e.getMessage());
                        PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 7<br>" +
                                "Обратитесь к администратору</html>");
                        window.setVisible(true);
                        window.setAlwaysOnTop(true);
                    }
                }
                windowWithProgressBar.setProgressBarValue(100);
            } else resultOfRefreshing += ": " + resultOfDownload;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 8<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
            resultOfRefreshing = "Crash: " + e.toString();
        }

        return  resultOfRefreshing;
    }

    public static void getUpdatesByName(String updateName, WindowWithProgressBar windowWithProgressBar, int numberOfAccount){
        try {
            String resultOfDownload = FTPLoader.downloadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/football/database/updates/" + updateName, Settings.TMP_DIR + "/" + updateName);
            String listFile = Settings.TMP_DIR + "/" + updateName;
            try {
                File fileDir = new File(listFile);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(fileDir), "UTF-8"));
                String str;
                while (((str = in.readLine()) != null)) {
                    getDataFromDatabase(str, windowWithProgressBar, numberOfAccount);
                    LogWriter.writeLog("Successful download of " + str.substring(str.lastIndexOf('/') + 1));
                }
                in.close();
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
                PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 9<br>" +
                        "Обратитесь к администратору</html>");
                window.setVisible(true);
                window.setAlwaysOnTop(true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 10<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }
    }

    public static boolean isTop6League(String leagueName){
        switch (leagueName){
            case "ENG.PrLeague":
            case "FRA.Ligue1":
            case "GER.1-Bundesliga":
            case "ITA.SerieA":
            case "RUS.RrLeague":
            case "SPA.LaLiga":{
                return true;
            }
            default: {
                return false;
            }
        }

    }

    public static void getDataFromDatabase(String dataName, WindowWithProgressBar windowWithProgressBar, int numberOfAccount){
        String command = "";
        String argument = "";
        if (dataName.split("=").length > 1){
            command = dataName.split("=")[0];
            argument = dataName.split("=")[1];
        }
        switch (command){
            case "download":{
                try {
                    String resultOfDownload = FTPLoader.downloadFile(getLogin(numberOfAccount), getPassword(numberOfAccount), "/data/football/" + argument, argument);
                    windowWithProgressBar.downloadedFiles ++;
                    windowWithProgressBar.setProgressBarValue((int) (100*windowWithProgressBar.downloadedFiles / (double) windowWithProgressBar.numberOfFiles));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 27<br>" +
                            "Обратитесь к администратору</html>");
                    window.setVisible(true);
                    window.setAlwaysOnTop(true);
                }
                break;
            }
            case "createDirectory":{
                try{
                    String absPath = new File(".pathToProject").getAbsolutePath().replace(".pathToProject", "");
                    File file2 = new File(absPath + argument);
                    if (!file2.exists())
                        file2.mkdir();
                } catch (Exception e){
                    e.printStackTrace();
                }


                break;
            }
            case "clearDirectory":{
                try{
                    deleteAllFilesFolder(argument);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case "deleteDirectory":{
                getDataFromDatabase("clearDirectory=" + argument, windowWithProgressBar, numberOfAccount);
                File directory = new File(argument);
                File[] children = directory.listFiles();
                for (File child : children) {
                    System.out.println(child.getAbsolutePath());
                }

                boolean result = directory.delete();
                if (result) {
                    System.out.printf("Directory " + argument + " is successfully deleted",
                            directory.getAbsolutePath());
                } else {
                    System.out.printf("Failed to delete directory " + argument,
                            directory.getAbsolutePath());
                }
                break;
            }
            case "deleteFile":{
                File file = new File(argument);
                if (argument.contains("graphicSettings.xml")){
                    String sn = null;
                    try {
                        sn = Settings.getSerialNumber();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String ID = getIDBySerialNumber(sn);
                    String user = getUserBySerialNumber(sn);
                    if (user.contains("UNKNOWN_USER")){
                        file.delete();
                    }
                } else {
                    file.delete();
                }
                break;
            }
        }
    }

    public static void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }

    public static String getUserBySerialNumber(String serialNumber){
        String result = "UNKNOWN_USER";
        try {
            File fileDir = new File(Settings.TMP_DIR + "/users.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (serialNumber.contains(str.split("=")[2])){
                    result = str.split("=")[0];
                    break;
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 28<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }

        return result;
    }

    public static String getIDBySerialNumber(String serialNumber){
        String result = serialNumber;
        try {
            File fileDir = new File(Settings.TMP_DIR + "/users.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (serialNumber.contains(str.split("=")[2])){
                    result = str.split("=")[2];
                    break;
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 2435<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }

        return result;
    }

    public static String getRefCodeBySerialNumber(String serialNumber){
        String result = "";
        try {
            File fileDir = new File(Settings.TMP_DIR + "/users.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (serialNumber.contains(str.split("=")[2])){
                    result = str.split("=")[5];
                    break;
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 29<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }

        return result;
    }

    public static String getDateBySerialNumber(String serialNumber){
        String result = "";
        try {
            File fileDir = new File(Settings.TMP_DIR + "/users.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (serialNumber.contains(str.split("=")[2])){
                    result = str.split("=")[3];
                    break;
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 30<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }

        return result;
    }

    public static String getDateString(String s){
        String result = s.substring(0,3);
        switch (s.substring(4,7)){
            case "Jan":{
                result += "01."; break;
            }
            case "Feb":{
                result += "02."; break;
            }
            case "Mar":{
                result += "03."; break;
            }
            case "Apr":{
                result += "04."; break;
            }
            case "May":{
                result += "05."; break;
            }
            case "Jun":{
                result += "06."; break;
            }
            case "Jul":{
                result += "07."; break;
            }
            case "Aug":{
                result += "08."; break;
            }
            case "Sep":{
                result += "09."; break;
            }
            case "Oct":{
                result += "10."; break;
            }
            case "Nov":{
                result += "11."; break;
            }
            case "Dec":{
                result += "12."; break;
            }
        }
        result += s.substring(10,12);
        return result;
    }

    public static int getDayCode(String s){
        //Jun 8 2019

        int result = 0;
        String resultS = s.split(" ")[2];
        switch (s.split(" ")[0]){
            case "Jan":{
                resultS += "01"; break;
            }
            case "Feb":{
                resultS += "02"; break;
            }
            case "Mar":{
                resultS += "03"; break;
            }
            case "Apr":{
                resultS += "04"; break;
            }
            case "May":{
                resultS += "05"; break;
            }
            case "Jun":{
                resultS += "06"; break;
            }
            case "Jul":{
                resultS += "07"; break;
            }
            case "Aug":{
                resultS += "08"; break;
            }
            case "Sep":{
                resultS += "09"; break;
            }
            case "Oct":{
                resultS += "10"; break;
            }
            case "Nov":{
                resultS += "11"; break;
            }
            case "Dec":{
                resultS += "12"; break;
            }
        }
        String day = s.split(" ")[1];
        if (day.length() < 2)
            day = "0" + day;

        resultS += day;
        return Integer.parseInt(resultS);
    }

    public static int getNextDayCode(int todayCode){
        int res = todayCode;
        int currentDay = Integer.parseInt(String.valueOf(todayCode).substring(6,8));
        int currentMonth = Integer.parseInt(String.valueOf(todayCode).substring(4,6));
        int currentYear = Integer.parseInt(String.valueOf(todayCode).substring(0,4));

        if (currentDay+1 <= getLastDayInMonth(currentMonth, currentYear))
            res++;
        else {
            currentDay = 1;
            if (currentMonth < 12)
                currentMonth++;
            else{
                currentMonth = 1;
                currentYear++;
            }
            res = currentYear*10000 + currentMonth*100 + currentDay;
        }
        return  res;
    }

    public static int getPreviousDayCode(int todayCode){
        int res = todayCode;
        int currentDay = Integer.parseInt(String.valueOf(todayCode).substring(6,8));
        int currentMonth = Integer.parseInt(String.valueOf(todayCode).substring(4,6));
        int currentYear = Integer.parseInt(String.valueOf(todayCode).substring(0,4));

        if (currentDay-1 > 0)
            res--;
        else {
            if (currentMonth-1 > 0)
                currentMonth--;
            else {
                currentMonth = 12;
                currentYear--;
            }
            res = currentYear*10000 + currentMonth*100 + currentDay;
        }
        return  res;
    }

    public static File downloadDayFile(int n, String nameOfFile){
        try {
            FTPLoader.downloadFile(Settings.getLogin(n), Settings.getPassword(n), "/data/football/matchCenter/" + nameOfFile, Settings.TMP_DIR + nameOfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new File(Settings.TMP_DIR + "/" + nameOfFile);
    }

    public static void downloadSettings(String sn){
        try {
            if (checkKey(sn))
            try {
                FTPLoader.downloadFile(Settings.getLogin(0), Settings.getPassword(0), "/data/football/settings/allLeagues.txt", "settings/allLeagues.txt");
                FTPLoader.downloadFile(Settings.getLogin(0), Settings.getPassword(0), "/data/football/settings/graphicSettings.xml", "settings/graphicSettings.xml");
                FTPLoader.downloadFile(Settings.getLogin(0), Settings.getPassword(0), "/data/football/settings/leaguesWithXG.txt", "settings/leaguesWithXG.txt");
                FTPLoader.downloadFile(Settings.getLogin(0), Settings.getPassword(0), "/data/football/settings/version.txt", "settings/version.txt");
                FTPLoader.downloadFile(Settings.getLogin(0), Settings.getPassword(0), "/data/football/settings/lastUpdate.txt", "settings/lastUpdate.txt");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isWhoScoredLeague(String leagueName){
        boolean result = false;

        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), StandardCharsets.UTF_8));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(leagueName)){
                    if (str.split("=")[4].equals("whoscored"))
                        return true;
                }
            }
            in.close();
        } catch (IOException e){
            MyException.sendException(e.getStackTrace(), "Не удалось проверить лигу " + leagueName);
            System.out.println(e.getMessage());
        }


        return result;
    }

    public static int getLastDayInMonth(int month, int year){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                {
                return 31;
            }
            case 2: {
                if (year % 4 == 0)
                    return 29;
                else
                    return 28;
            }
            case 4:
            case 6:
            case 9:
            case 11:
                {
                return 30;
            }

        }
        return 0;
    }

    /*public static ArrayList<String> getListForMatchCenter(String fullFileName){
        ArrayList result = new ArrayList();
        try {
            File fileDir = new File(fullFileName);
            if (!fileDir.exists()){
                fileDir = Settings.downloadDayFile(1, fullFileName.replace(Settings.TMP_DIR + "/", ""));
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }*/

    public static ArrayList<String> getListForMatchCenter(String dayCode, int hourDifference){
        ArrayList<String> result = new ArrayList<>();
        String fullFileName;
        if (hourDifference < 0){
            fullFileName = Settings.TMP_DIR + String.valueOf(getNextDayCode(Integer.parseInt(dayCode))) + ".txt";
            try {
//                File fileDir = new File(fullFileName);
                File fileDir = downloadDayFile(Settings.getNumberOfAccount(), getNextDayCode(Integer.parseInt(dayCode)) + ".txt");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(fileDir), "UTF-8"));
                String str;
                String league = "";
                while (((str = in.readLine()) != null)) {
                    if (str.split("\\*").length == 1){
                        league = str;
                    } else {
                        String hour = str.split("\\*")[0].split(":")[0];
                        if (hour.startsWith("0"))
                            hour = hour.replaceFirst("0", "");
                        int newHour = Integer.parseInt(hour) + hourDifference;
                        if (newHour < 0){
                            String newHourString = String.valueOf(newHour + 24);
                            if (newHourString.length() < 2)
                                newHourString = "0" + newHourString;
                            str = str.replaceFirst(str.split("\\*")[0].split(":")[0], newHourString);
                            if (!result.contains(league))
                                result.add(league);
                            result.add(str);

                        }
                    }
                }
                in.close();
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
                MyException.sendException(e.getStackTrace(), "getListForMatchCenter1");
            }
        }
        try {
            fullFileName = Settings.TMP_DIR + String.valueOf(dayCode) + ".txt";
            File fileDir = new File(fullFileName);
            if (!fileDir.exists()){
                fileDir = downloadDayFile(Settings.getNumberOfAccount(), String.valueOf(dayCode) + ".txt");
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            String league = "";
            while (((str = in.readLine()) != null)) {
                if (str.split("\\*").length == 1){
                    league = str;
                } else {
                    String hour = str.split("\\*")[0].split(":")[0];
                    if (hour.startsWith("0"))
                        hour = hour.replaceFirst("0", "");
                    int newHour = Integer.parseInt(hour) + hourDifference;
                    if (newHour < 24 && newHour >= 0){
                        String newHourString = String.valueOf(newHour);
                        if (newHourString.length() < 2)
                            newHourString = "0" + newHourString;
                        str = str.replaceFirst(str.split("\\*")[0].split(":")[0], newHourString);
                        if (!result.contains(league)){
                            result.add(league);
                            result.add(str);
                        } else {
                            int countOfAddedMatchesInLeague = 0;
                            int index = result.indexOf(league)+1;
                            while (index<result.size()){
                                if (result.get(index).split("\\*").length > 1 ){
                                    countOfAddedMatchesInLeague++;
                                    index++;
                                } else{
                                    break;
                                }
                            }
                            result.add(result.indexOf(league)+countOfAddedMatchesInLeague+1, str);
                        }
                    }
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            MyException.sendException(e.getStackTrace(), "getListForMatchCenter2");
        }
        if (hourDifference > 0){
            fullFileName = Settings.TMP_DIR + String.valueOf(getPreviousDayCode(Integer.parseInt(dayCode))) + ".txt";
            try {
                File fileDir = new File(fullFileName);
                if (!fileDir.exists()){
                    fileDir = downloadDayFile(Settings.getNumberOfAccount(), String.valueOf(getPreviousDayCode(Integer.parseInt(dayCode))) + ".txt");
                }
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(fileDir), "UTF-8"));
                String str;
                String league = "";
                while (((str = in.readLine()) != null)) {
                    if (str.split("\\*").length == 1){
                        league = str;
                    } else {
                        String hour = str.split("\\*")[0].split(":")[0];
                        if (hour.startsWith("0"))
                            hour = hour.replaceFirst("0", "");
                        int newHour = Integer.parseInt(hour) + hourDifference;
                        if (newHour >= 24){
                            String newHourString = String.valueOf(newHour - 24);
                            if (newHourString.length() < 2)
                                newHourString = "0" + newHourString;
                            str = str.replaceFirst(str.split("\\*")[0].split(":")[0], newHourString);
                            if (!result.contains(league)){
                                result.add(league);
                                result.add(str);
                            } else {
                                int countOfAddedMatchesInLeague = 0;
                                int index = result.indexOf(league)+1;
                                while (index<result.size()){
                                    if (result.get(index).split("\\*").length > 1 ){
                                        countOfAddedMatchesInLeague++;
                                        index++;
                                    } else{
                                        break;
                                    }
                                }
                                result.add(result.indexOf(league)+countOfAddedMatchesInLeague+1, str);
                            }
                        }
                    }
                }
                in.close();
            } catch (IOException e)
            {
                MyException.sendException(e.getStackTrace(), "getListForMatchCenter3");
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    public static int countLines(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        count ++;
                    }
                }
            }
            count ++;
            return (count == 0 && !empty) ? 1 : count;
        }
    }

    public static String getPrevSeason(String season){
        if (season.contains("-")){
            String s1 = String.valueOf(Integer.parseInt(season.split("-")[0])-1);
            String s2 = String.valueOf(Integer.parseInt(season.split("-")[1])-1);
            return s1+"-"+s2;
        } else{
            return String.valueOf(Integer.parseInt(season)-1);
        }

    }

    public static void setWindowResolution(int width, int height){
        Settings settings = getSettingsFromFile();
        settings.windowResolution = String.valueOf(width) + "x" + String.valueOf(height);
        settings.pushSettingsToFile();
    }

    public static int getNumberOfTeamsInLeague(String leagueName, String season){
//        File fileName = new File("database/" + season + "/leagues/" + leagueName + ".txt");

        try {
            return countLines("database/" + season + "/leagues/" + leagueName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getWindowResolution(){
        return this.windowResolution;
    }
}
