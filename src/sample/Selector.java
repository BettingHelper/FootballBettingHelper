package sample;

import java.util.ArrayList;

public class Selector {
    public String teamName;
    public ArrayList<Match> listOfMatches;
    public ArrayList<String> listForConfrontation;
    public ArrayList<ArrayList<String>> pList;
    public int[] numberOfMatchesWithParam;
    public int[] refNumberOfMatchesWithParam;
    public ArrayList<ArrayList<String>> refList;
    public String[] params = {"Победы", "Ничьи", "Поражения", "Очки", "Голы", "xG", "Владение", "Удары", "Удары в створ",
            "Удары мимо", "Угловые", "Офсайды", "Блокировано ударов", "Фолы", "Желтые", "Красные", "Форма", "xGMatches", "xWins", "xDraws", "xLoses", "xPoints",
            "Голы 1 тайм", "Голы 2 тайм", "Голы 0-15", "Голы 16-30", "Голы 31-45'+", "Голы 46-60", "Голы 61-75", "Голы 76-90'+",
            "Владение 1 тайм", "Владение 2 тайм", "Угловые 1 тайм", "Угловые 2 тайм", "ЖК 1 тайм", "ЖК 2 тайм",
            "Удары 1 тайм", "Удары 2 тайм", "Удары в створ 1 тайм", "Удары в створ 2 тайм", "Удары мимо 1 тайм", "Удары мимо 2 тайм",
            "Блок.ударов 1 тайм", "Блок.ударов 2 тайм", "Офсайды 1 тайм", "Офсайды 2 тайм", "Фолы 1 тайм", "Фолы 2 тайм",
            "Дриблинг 1 тайм", "Дриблинг 2 тайм", "Возд.един. 1 тайм", "Возд.един. 2 тайм", "Сэйвы 1 тайм", "Сэйвы 2 тайм",
            "Выносы 1 тайм", "Выносы 2 тайм", "Перехваты 1 тайм", "Перехваты 2 тайм", "Отборы 1 тайм", "Отборы 2 тайм",
            "Точные передачи 1 тайм", "Точные передачи 2 тайм",  "Процент точности передач 1 тайм", "Процент точности передач 2 тайм",
            "Ключевые передачи 1 тайм", "Ключевые передачи 2 тайм", "Потери 1 тайм", "Потери 2 тайм", "Дриблинг", "Возд.един.", "Сэйвы", "Выносы",
            "Перехваты", "Отборы", "Точные передачи", "Процент точности передач", "Ключевые передачи", "Потери",
            "Удары от ворот", "Удары от ворот в 1-ом тайме", "Удары от ворот во 2-ом тайме",
            "Вброс аутов", "Вброс аутов в 1-ом тайме", "Вброс аутов во 2-ом тайме",
    };
    public String[] refParams = {"Желтые карточки (все)", "Желтые карточки 1 тайм ", "Желтые карточки 2 тайм ","ЖК --> КК", "Прямые КК",
            "Фолы", "Фолы 1 тайм", "Фолы 2 тайм", "Назначенные пенальти", "СКО по ЖК", "СКО по фолам",
            "Корреляция фолов и ЖК", "Корреляция форы фолов и  форы ЖК",  "Фолы", "Отборы"};

    public Selector(){
        pList = new ArrayList<>();
        numberOfMatchesWithParam = new int[params.length];
        refNumberOfMatchesWithParam = new int[refParams.length];
        refList = new ArrayList<>();
        listOfMatches = new ArrayList<>();
        listForConfrontation = new ArrayList<>();
    }

    public void getListOfMatches(String leagueName, String teamName, String allOrHomeOrAway, String season, String lastOrFullSeason){
        this.teamName = teamName;
        if ((!leagueName.contains("Выберите"))&&(!teamName.contains("Выберите"))){

            String path = "database/" + season + "/" + leagueName + "/Matches/";
            Team team = Team.getTeamFromFileWithSeason(teamName, season, leagueName);

            if (lastOrFullSeason.contains("Последние")){
                int count = Integer.parseInt(lastOrFullSeason.split(" ")[1]);
                int index = team.matchList.size()-1;
                while (count>0&&index>=0){
                    if (allOrHomeOrAway.contains("Все")){
                        listOfMatches.add(0, Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("Дома") && team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                        listOfMatches.add(0, Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("На выезде") && team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                        listOfMatches.add(0, Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    index --;
                }
            } else if (lastOrFullSeason.contains("Весь сезон")) {
                if (allOrHomeOrAway.contains("Все")){
                    for (int index=0; index<team.matchList.size(); index++){
                        listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                    }
                }
                if (allOrHomeOrAway.contains("Дома")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
                if (allOrHomeOrAway.contains("На выезде")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
            } else {
                // ветка для "ДО-ПОСЛЕ"
                int beginIndex = Integer.parseInt(lastOrFullSeason.split("-")[0]);
                int endIndex = Integer.parseInt(lastOrFullSeason.split("-")[1]);

                if (allOrHomeOrAway.contains("Все")){
                    for (int index=0; index<team.matchList.size(); index++){
                        listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                    }
                }
                if (allOrHomeOrAway.contains("Дома")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
                if (allOrHomeOrAway.contains("На выезде")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        }
                    }
                }

                if (beginIndex == 0){
                    int size = listOfMatches.size();
                    for (int i=endIndex+1; i<size; i++){
                        listOfMatches.remove(listOfMatches.size()-1);
                    }
                } else{
                    if (beginIndex > 0) {
                        listOfMatches.subList(0, beginIndex).clear();
                    }
                }
            }
        }
    }

    public void getPList(ArrayList<Match> listOfMatches, String teamName){
        if (listOfMatches.size()>0){
            double[][] paramsD = new double[params.length][2];
            StringBuilder forma = new StringBuilder();
            for (int i=0; i<listOfMatches.size(); i++){
                Match m = listOfMatches.get(i);
                incNumbersArray(m);
                if (teamName.equals(m.homeTeam)){
                    if (m.homeScore == m.awayScore){
                        paramsD[1][0] += 1;
                        paramsD[1][1] += 1;
                        paramsD[3][0] += 1;
                        paramsD[3][1] += 1;
                        forma.append("D");
                    }
                    else if (m.homeScore > m.awayScore){
                        paramsD[0][0] += 1;
                        paramsD[2][1] += 1;
                        paramsD[3][0] += 3;
                        forma.append("W");
                    }
                    else {
                        paramsD[2][0] += 1;
                        paramsD[0][1] += 1;
                        paramsD[3][1] += 3;
                        forma.append("L");
                    }

                    paramsD[4][0] += m.homeScore;
                    paramsD[4][1] += m.awayScore;
                    paramsD[5][0] += m.homeXG;
                    paramsD[5][1] += m.awayXG;
                    paramsD[6][0] += m.homeBallPossession;
                    paramsD[6][1] += m.awayBallPossession;
                    paramsD[7][0] += m.homeShots;
                    paramsD[7][1] += m.awayShots;
                    paramsD[8][0] += m.homeShotsOnTarget;
                    paramsD[8][1] += m.awayShotsOnTarget;
                    paramsD[9][0] += m.homeShotsOffTarget;
                    paramsD[9][1] += m.awayShotsOffTarget;
                    paramsD[10][0] += m.homeCorners;
                    paramsD[10][1] += m.awayCorners;
                    paramsD[11][0] += m.homeOffsides;
                    paramsD[11][1] += m.awayOffsides;
                    paramsD[12][0] += m.homeBlockedShots;
                    paramsD[12][1] += m.awayBlockedShots;
                    paramsD[13][0] += m.homeFouls;
                    paramsD[13][1] += m.awayFouls;
                    paramsD[14][0] += m.homeYellowCards;
                    paramsD[14][1] += m.awayYellowCards;
                    paramsD[15][0] += m.homeRedCards;
                    paramsD[15][1] += m.awayRedCards;

                    paramsD[22][0] += m.homeScore1T;
                    paramsD[22][1] += m.awayScore1T;
                    paramsD[23][0] += m.homeScore2T;
                    paramsD[23][1] += m.awayScore2T;
                    if (m.homeGoalsBy15minutes == null){
                        m.homeGoalsBy15minutes = "0*0*0*0*0*0";
                    }
                    if (m.awayGoalsBy15minutes == null){
                        m.awayGoalsBy15minutes = "0*0*0*0*0*0";
                    }
                    paramsD[24][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[0]);
                    paramsD[24][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[0]);
                    paramsD[25][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[1]);
                    paramsD[25][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[1]);
                    paramsD[26][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[2]);
                    paramsD[26][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[2]);
                    paramsD[27][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[3]);
                    paramsD[27][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[3]);
                    paramsD[28][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[4]);
                    paramsD[28][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[4]);
                    paramsD[29][0] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[5]);
                    paramsD[29][1] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[5]);

                    paramsD[30][0] += m.homeBallPossession1T;
                    paramsD[30][1] += m.awayBallPossession1T;
                    paramsD[31][0] += m.homeBallPossession2T;
                    paramsD[31][1] += m.awayBallPossession2T;
                    paramsD[32][0] += m.homeCorners1T;
                    paramsD[32][1] += m.awayCorners1T;
                    paramsD[33][0] += m.homeCorners2T;
                    paramsD[33][1] += m.awayCorners2T;
                    paramsD[34][0] += m.homeYellowCards1T;
                    paramsD[34][1] += m.awayYellowCards1T;
                    paramsD[35][0] += m.homeYellowCards2T;
                    paramsD[35][1] += m.awayYellowCards2T;
                    paramsD[36][0] += m.homeShots1T;
                    paramsD[36][1] += m.awayShots1T;
                    paramsD[37][0] += m.homeShots2T;
                    paramsD[37][1] += m.awayShots2T;
                    paramsD[38][0] += m.homeShotsOnTarget1T;
                    paramsD[38][1] += m.awayShotsOnTarget1T;
                    paramsD[39][0] += m.homeShotsOnTarget2T;
                    paramsD[39][1] += m.awayShotsOnTarget2T;
                    paramsD[40][0] += m.homeShotsOffTarget1T;
                    paramsD[40][1] += m.awayShotsOffTarget1T;
                    paramsD[41][0] += m.homeShotsOffTarget2T;
                    paramsD[41][1] += m.awayShotsOffTarget2T;
                    paramsD[42][0] += m.homeBlockedShots1T;
                    paramsD[42][1] += m.awayBlockedShots1T;
                    paramsD[43][0] += m.homeBlockedShots2T;
                    paramsD[43][1] += m.awayBlockedShots2T;
                    paramsD[44][0] += m.homeOffsides1T;
                    paramsD[44][1] += m.awayOffsides1T;
                    paramsD[45][0] += m.homeOffsides2T;
                    paramsD[45][1] += m.awayOffsides2T;
                    paramsD[46][0] += m.homeFouls1T;
                    paramsD[46][1] += m.awayFouls1T;
                    paramsD[47][0] += m.homeFouls2T;
                    paramsD[47][1] += m.awayFouls2T;
                    paramsD[48][0] += m.homeDribbles1T;
                    paramsD[48][1] += m.awayDribbles1T;
                    paramsD[49][0] += m.homeDribbles2T;
                    paramsD[49][1] += m.awayDribbles2T;
                    paramsD[50][0] += m.homeAerialsWon1T;
                    paramsD[50][1] += m.awayAerialsWon1T;
                    paramsD[51][0] += m.homeAerialsWon2T;
                    paramsD[51][1] += m.awayAerialsWon2T;
                    paramsD[52][0] += m.homeSaves1T;
                    paramsD[52][1] += m.awaySaves1T;
                    paramsD[53][0] += m.homeSaves2T;
                    paramsD[53][1] += m.awaySaves2T;
                    paramsD[54][0] += m.homeClearances1T;
                    paramsD[54][1] += m.awayClearances1T;
                    paramsD[55][0] += m.homeClearances2T;
                    paramsD[55][1] += m.awayClearances2T;
                    paramsD[56][0] += m.homeInterceptions1T;
                    paramsD[56][1] += m.awayInterceptions1T;
                    paramsD[57][0] += m.homeInterceptions2T;
                    paramsD[57][1] += m.awayInterceptions2T;
                    paramsD[58][0] += m.homeTackles1T;
                    paramsD[58][1] += m.awayTackles1T;
                    paramsD[59][0] += m.homeTackles2T;
                    paramsD[59][1] += m.awayTackles2T;
                    paramsD[60][0] += m.homePassesSuccessfully1T;
                    paramsD[60][1] += m.awayPassesSuccessfully1T;
                    paramsD[61][0] += m.homePassesSuccessfully2T;
                    paramsD[61][1] += m.awayPassesSuccessfully2T;
                    paramsD[62][0] += MyMath.round(100 * m.homePassesSuccessfully1T / (double) m.homePasses1T, 0);
                    paramsD[62][1] += MyMath.round(100 * m.awayPassesSuccessfully1T / (double) m.awayPasses1T, 0);
                    paramsD[63][0] += MyMath.round(100 * m.homePassesSuccessfully2T / (double) m.homePasses2T, 0);
                    paramsD[63][1] += MyMath.round(100 * m.awayPassesSuccessfully2T / (double) m.awayPasses2T, 0);
                    paramsD[64][0] += m.homeKeyPasses1T;
                    paramsD[64][1] += m.awayKeyPasses1T;
                    paramsD[65][0] += m.homeKeyPasses2T;
                    paramsD[65][1] += m.awayKeyPasses2T;
                    paramsD[66][0] += m.homeDispossessed1T;
                    paramsD[66][1] += m.awayDispossessed1T;
                    paramsD[67][0] += m.homeDispossessed2T;
                    paramsD[67][1] += m.awayDispossessed2T;
                    paramsD[68][0] += m.homeDribbles;
                    paramsD[68][1] += m.awayDribbles;
                    paramsD[69][0] += m.homeAerialsWon;
                    paramsD[69][1] += m.awayAerialsWon;
                    paramsD[70][0] += m.homeSaves;
                    paramsD[70][1] += m.awaySaves;
                    paramsD[71][0] += m.homeClearances;
                    paramsD[71][1] += m.awayClearances;
                    paramsD[72][0] += m.homeInterceptions;
                    paramsD[72][1] += m.awayInterceptions;
                    paramsD[73][0] += m.homeTackles;
                    paramsD[73][1] += m.awayTackles;
                    paramsD[74][0] += m.homePassesSuccessfully;
                    paramsD[74][1] += m.awayPassesSuccessfully;
                    paramsD[75][0] += MyMath.round(100 * m.homePassesSuccessfully / (double) m.homePasses, 0);
                    paramsD[75][1] += MyMath.round(100 * m.awayPassesSuccessfully / (double) m.awayPasses, 0);
                    paramsD[76][0] += m.homeKeyPasses;
                    paramsD[76][1] += m.awayKeyPasses;
                    paramsD[77][0] += m.homeDispossessed;
                    paramsD[77][1] += m.awayDispossessed;

                    paramsD[78][0] += m.homeGoalKicks;
                    paramsD[78][1] += m.awayGoalKicks;
                    paramsD[79][0] += m.homeGoalKicks1T;
                    paramsD[79][1] += m.awayGoalKicks1T;
                    paramsD[80][0] += m.homeGoalKicks2T;
                    paramsD[80][1] += m.awayGoalKicks2T;
                    paramsD[81][0] += m.homeThrowIns;
                    paramsD[81][1] += m.awayThrowIns;
                    paramsD[82][0] += m.homeThrowIns1T;
                    paramsD[82][1] += m.awayThrowIns1T;
                    paramsD[83][0] += m.homeThrowIns2T;
                    paramsD[83][1] += m.awayThrowIns2T;

                    if (!((m.homeXG==0)&&(m.awayXG==0))){
                        paramsD[17][0] += 1;
                        double[] xWxDxL = MyMath.getXWinXDrawXLose(m.homeXG - m.awayXG);
                        paramsD[18][0] += xWxDxL[0];
                        paramsD[19][0] += xWxDxL[1];
                        paramsD[20][0] += xWxDxL[2];
                        paramsD[21][0] += MyMath.round(3 * xWxDxL[0] + xWxDxL[1], 2);
                    }

                } else if (teamName.equals(m.awayTeam)){
                    if (m.awayScore == m.homeScore){
                        paramsD[1][0] += 1;
                        paramsD[1][1] += 1;
                        paramsD[3][0] += 1;
                        paramsD[3][1] += 1;
                        forma.append("D");
                    }
                    else if (m.awayScore > m.homeScore){
                        paramsD[0][0] += 1;
                        paramsD[2][1] += 1;
                        paramsD[3][0] += 3;
                        forma.append("W");
                    }
                    else {
                        paramsD[2][0] += 1;
                        paramsD[0][1] += 1;
                        paramsD[3][1] += 3;
                        forma.append("L");
                    }

                    paramsD[4][1] += m.homeScore;
                    paramsD[4][0] += m.awayScore;
                    paramsD[5][1] += m.homeXG;
                    paramsD[5][0] += m.awayXG;
                    paramsD[6][1] += m.homeBallPossession;
                    paramsD[6][0] += m.awayBallPossession;
                    paramsD[7][1] += m.homeShots;
                    paramsD[7][0] += m.awayShots;
                    paramsD[8][1] += m.homeShotsOnTarget;
                    paramsD[8][0] += m.awayShotsOnTarget;
                    paramsD[9][1] += m.homeShotsOffTarget;
                    paramsD[9][0] += m.awayShotsOffTarget;
                    paramsD[10][1] += m.homeCorners;
                    paramsD[10][0] += m.awayCorners;
                    paramsD[11][1] += m.homeOffsides;
                    paramsD[11][0] += m.awayOffsides;
                    paramsD[12][1] += m.homeBlockedShots;
                    paramsD[12][0] += m.awayBlockedShots;
                    paramsD[13][1] += m.homeFouls;
                    paramsD[13][0] += m.awayFouls;
                    paramsD[14][1] += m.homeYellowCards;
                    paramsD[14][0] += m.awayYellowCards;
                    paramsD[15][1] += m.homeRedCards;
                    paramsD[15][0] += m.awayRedCards;

                    paramsD[22][0] += m.awayScore1T;
                    paramsD[22][1] += m.homeScore1T;
                    paramsD[23][0] += m.awayScore2T;
                    paramsD[23][1] += m.homeScore2T;
                    if (m.homeGoalsBy15minutes == null){
                        m.homeGoalsBy15minutes = "0*0*0*0*0*0";

                    }
                    if (m.awayGoalsBy15minutes == null){
                        m.awayGoalsBy15minutes = "0*0*0*0*0*0";
                    }
                    paramsD[24][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[0]);
                    paramsD[24][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[0]);
                    paramsD[25][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[1]);
                    paramsD[25][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[1]);
                    paramsD[26][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[2]);
                    paramsD[26][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[2]);
                    paramsD[27][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[3]);
                    paramsD[27][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[3]);
                    paramsD[28][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[4]);
                    paramsD[28][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[4]);
                    paramsD[29][0] += Integer.parseInt(m.awayGoalsBy15minutes.split("\\*")[5]);
                    paramsD[29][1] += Integer.parseInt(m.homeGoalsBy15minutes.split("\\*")[5]);

                    paramsD[30][1] += m.homeBallPossession1T;
                    paramsD[30][0] += m.awayBallPossession1T;
                    paramsD[31][1] += m.homeBallPossession2T;
                    paramsD[31][0] += m.awayBallPossession2T;
                    paramsD[32][1] += m.homeCorners1T;
                    paramsD[32][0] += m.awayCorners1T;
                    paramsD[33][1] += m.homeCorners2T;
                    paramsD[33][0] += m.awayCorners2T;
                    paramsD[34][1] += m.homeYellowCards1T;
                    paramsD[34][0] += m.awayYellowCards1T;
                    paramsD[35][1] += m.homeYellowCards2T;
                    paramsD[35][0] += m.awayYellowCards2T;
                    paramsD[36][1] += m.homeShots1T;
                    paramsD[36][0] += m.awayShots1T;
                    paramsD[37][1] += m.homeShots2T;
                    paramsD[37][0] += m.awayShots2T;
                    paramsD[38][1] += m.homeShotsOnTarget1T;
                    paramsD[38][0] += m.awayShotsOnTarget1T;
                    paramsD[39][1] += m.homeShotsOnTarget2T;
                    paramsD[39][0] += m.awayShotsOnTarget2T;
                    paramsD[40][1] += m.homeShotsOffTarget1T;
                    paramsD[40][0] += m.awayShotsOffTarget1T;
                    paramsD[41][1] += m.homeShotsOffTarget2T;
                    paramsD[41][0] += m.awayShotsOffTarget2T;
                    paramsD[42][1] += m.homeBlockedShots1T;
                    paramsD[42][0] += m.awayBlockedShots1T;
                    paramsD[43][1] += m.homeBlockedShots2T;
                    paramsD[43][0] += m.awayBlockedShots2T;
                    paramsD[44][1] += m.homeOffsides1T;
                    paramsD[44][0] += m.awayOffsides1T;
                    paramsD[45][1] += m.homeOffsides2T;
                    paramsD[45][0] += m.awayOffsides2T;
                    paramsD[46][1] += m.homeFouls1T;
                    paramsD[46][0] += m.awayFouls1T;
                    paramsD[47][1] += m.homeFouls2T;
                    paramsD[47][0] += m.awayFouls2T;
                    paramsD[48][1] += m.homeDribbles1T;
                    paramsD[48][0] += m.awayDribbles1T;
                    paramsD[49][1] += m.homeDribbles2T;
                    paramsD[49][0] += m.awayDribbles2T;
                    paramsD[50][1] += m.homeAerialsWon1T;
                    paramsD[50][0] += m.awayAerialsWon1T;
                    paramsD[51][1] += m.homeAerialsWon2T;
                    paramsD[51][0] += m.awayAerialsWon2T;
                    paramsD[52][1] += m.homeSaves1T;
                    paramsD[52][0] += m.awaySaves1T;
                    paramsD[53][1] += m.homeSaves2T;
                    paramsD[53][0] += m.awaySaves2T;
                    paramsD[54][1] += m.homeClearances1T;
                    paramsD[54][0] += m.awayClearances1T;
                    paramsD[55][1] += m.homeClearances2T;
                    paramsD[55][0] += m.awayClearances2T;
                    paramsD[56][1] += m.homeInterceptions1T;
                    paramsD[56][0] += m.awayInterceptions1T;
                    paramsD[57][1] += m.homeInterceptions2T;
                    paramsD[57][0] += m.awayInterceptions2T;
                    paramsD[58][1] += m.homeTackles1T;
                    paramsD[58][0] += m.awayTackles1T;
                    paramsD[59][1] += m.homeTackles2T;
                    paramsD[59][0] += m.awayTackles2T;
                    paramsD[60][1] += m.homePassesSuccessfully1T;
                    paramsD[60][0] += m.awayPassesSuccessfully1T;
                    paramsD[61][1] += m.homePassesSuccessfully2T;
                    paramsD[61][0] += m.awayPassesSuccessfully2T;
                    paramsD[62][1] += MyMath.round(100 * m.homePassesSuccessfully1T / (double) m.homePasses1T, 0);
                    paramsD[62][0] += MyMath.round(100 * m.awayPassesSuccessfully1T / (double) m.awayPasses1T, 0);
                    paramsD[63][1] += MyMath.round(100 * m.homePassesSuccessfully2T / (double) m.homePasses2T, 0);
                    paramsD[63][0] += MyMath.round(100 * m.awayPassesSuccessfully2T / (double) m.awayPasses2T, 0);
                    paramsD[64][1] += m.homeKeyPasses1T;
                    paramsD[64][0] += m.awayKeyPasses1T;
                    paramsD[65][1] += m.homeKeyPasses2T;
                    paramsD[65][0] += m.awayKeyPasses2T;
                    paramsD[66][1] += m.homeDispossessed1T;
                    paramsD[66][0] += m.awayDispossessed1T;
                    paramsD[67][1] += m.homeDispossessed2T;
                    paramsD[67][0] += m.awayDispossessed2T;
                    paramsD[68][1] += m.homeDribbles;
                    paramsD[68][0] += m.awayDribbles;
                    paramsD[69][1] += m.homeAerialsWon;
                    paramsD[69][0] += m.awayAerialsWon;
                    paramsD[70][1] += m.homeSaves;
                    paramsD[70][0] += m.awaySaves;
                    paramsD[71][1] += m.homeClearances;
                    paramsD[71][0] += m.awayClearances;
                    paramsD[72][1] += m.homeInterceptions;
                    paramsD[72][0] += m.awayInterceptions;
                    paramsD[73][1] += m.homeTackles;
                    paramsD[73][0] += m.awayTackles;
                    paramsD[74][1] += m.homePassesSuccessfully;
                    paramsD[74][0] += m.awayPassesSuccessfully;
                    paramsD[75][1] += MyMath.round(100 * m.homePassesSuccessfully / (double) m.homePasses, 0);
                    paramsD[75][0] += MyMath.round(100 * m.awayPassesSuccessfully / (double) m.awayPasses, 0);
                    paramsD[76][1] += m.homeKeyPasses;
                    paramsD[76][0] += m.awayKeyPasses;
                    paramsD[77][1] += m.homeDispossessed;
                    paramsD[77][0] += m.awayDispossessed;

                    paramsD[78][1] += m.homeGoalKicks;
                    paramsD[78][0] += m.awayGoalKicks;
                    paramsD[79][1] += m.homeGoalKicks1T;
                    paramsD[79][0] += m.awayGoalKicks1T;
                    paramsD[80][1] += m.homeGoalKicks2T;
                    paramsD[80][0] += m.awayGoalKicks2T;
                    paramsD[81][1] += m.homeThrowIns;
                    paramsD[81][0] += m.awayThrowIns;
                    paramsD[82][1] += m.homeThrowIns1T;
                    paramsD[82][0] += m.awayThrowIns1T;
                    paramsD[83][1] += m.homeThrowIns2T;
                    paramsD[83][0] += m.awayThrowIns2T;

                    if (!((m.homeXG==0)&&(m.awayXG==0))){
                        paramsD[17][0] += 1;
                        double[] xWxDxL = MyMath.getXWinXDrawXLose(m.awayXG - m.homeXG);
                        paramsD[18][0] += xWxDxL[0];
                        paramsD[19][0] += xWxDxL[1];
                        paramsD[20][0] += xWxDxL[2];
                        paramsD[21][0] += MyMath.round(3 * xWxDxL[0] + xWxDxL[1], 2);
                    }
                }
            }

            for (int i=0; i<paramsD.length; i++){
                ArrayList<String> parametr = new ArrayList<>();
                if ((i>3&&i<16) || i==73) {
                    paramsD[i][0] = MyMath.round(paramsD[i][0] / numberOfMatchesWithParam[i], 2);
                    paramsD[i][1] = MyMath.round(paramsD[i][1] / numberOfMatchesWithParam[i], 2);
                }
                parametr.add(params[i]);
                parametr.add(String.valueOf(MyMath.round(paramsD[i][0], 2)));
                parametr.add(String.valueOf(MyMath.round(paramsD[i][1], 2)));
                pList.add(parametr);
            }
            ArrayList<String> f = new ArrayList<>();
            if (forma.length()>20){
                forma = new StringBuilder(forma.substring(forma.length() - 20, forma.length()));
            }
            f.add("Форма");
            f.add(forma.toString());
            f.add(forma.toString());
            pList.set(16,f);
        }
    }

    public void getRefListOfMatches(String leagueName, String refName, String season, String lastOrFullSeason){
        if ((!leagueName.contains("Выберите"))&&(!refName.contains("Выберите"))){

            String path = "database/" + season + "/" + leagueName + "/Matches/";
            Referee ref = Referee.getRefFromFile(refName, season, leagueName);

            if (lastOrFullSeason.contains("Последние")){
                int count = Integer.parseInt(lastOrFullSeason.split(" ")[1]);
                int index = ref.matchList.size()-1;
                while (count>0&&index>=0){
                    listOfMatches.add(0, Match.getMatchFromFileByName(path + ref.matchList.get(index)+ ".xml"));
                    count--;
                    index --;
                }
            } else {
                for (int index=0; index<ref.matchList.size(); index++)
                    listOfMatches.add(Match.getMatchFromFileByName(path + ref.matchList.get(index)+ ".xml"));
            }

            double[][] paramsD = new double[refParams.length][3];
            for (int i=0; i<listOfMatches.size(); i++){
                paramsD[0][0] += listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards;
                paramsD[0][1] += listOfMatches.get(i).homeYellowCards ;
                paramsD[0][2] += listOfMatches.get(i).awayYellowCards;
                refNumberOfMatchesWithParam[0] ++;

                paramsD[1][0] += listOfMatches.get(i).homeYellowCards1T + listOfMatches.get(i).awayYellowCards1T;
                paramsD[1][1] += listOfMatches.get(i).homeYellowCards1T ;
                paramsD[1][2] += listOfMatches.get(i).awayYellowCards1T;
                refNumberOfMatchesWithParam[1] ++;

                paramsD[2][0] += listOfMatches.get(i).homeYellowCards2T + listOfMatches.get(i).awayYellowCards2T;
                paramsD[2][1] += listOfMatches.get(i).homeYellowCards2T ;
                paramsD[2][2] += listOfMatches.get(i).awayYellowCards2T;
                refNumberOfMatchesWithParam[2] ++;

                paramsD[3][0] += listOfMatches.get(i).homeSecondYellowCards + listOfMatches.get(i).awaySecondYellowCards;
                paramsD[3][1] += listOfMatches.get(i).homeSecondYellowCards;
                paramsD[3][2] += listOfMatches.get(i).awaySecondYellowCards;
                refNumberOfMatchesWithParam[3] ++;

                paramsD[4][0] += listOfMatches.get(i).homeDirectRedCards + listOfMatches.get(i).awayDirectRedCards;
                paramsD[4][1] += listOfMatches.get(i).homeDirectRedCards;
                paramsD[4][2] += listOfMatches.get(i).awayDirectRedCards;
                refNumberOfMatchesWithParam[4] ++;

                paramsD[5][0] += listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls;
                paramsD[5][1] += listOfMatches.get(i).homeFouls;
                paramsD[5][2] += listOfMatches.get(i).awayFouls;
                if (listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls > 0){
                    refNumberOfMatchesWithParam[5] ++;
                    refNumberOfMatchesWithParam[6] ++;
                    refNumberOfMatchesWithParam[7] ++;
                }

                paramsD[6][0] += listOfMatches.get(i).homeFouls1T + listOfMatches.get(i).awayFouls1T;
                paramsD[6][1] += listOfMatches.get(i).homeFouls1T;
                paramsD[6][2] += listOfMatches.get(i).awayFouls1T;

                paramsD[7][0] += listOfMatches.get(i).homeFouls2T + listOfMatches.get(i).awayFouls2T;
                paramsD[7][1] += listOfMatches.get(i).homeFouls2T;
                paramsD[7][2] += listOfMatches.get(i).awayFouls2T;

                paramsD[8][0] += listOfMatches.get(i).homePen + listOfMatches.get(i).awayPen;
                paramsD[8][1] += listOfMatches.get(i).homePen;
                paramsD[8][2] += listOfMatches.get(i).awayPen;
                refNumberOfMatchesWithParam[8] ++;

                paramsD[9][0] += listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls;
                paramsD[9][1] += listOfMatches.get(i).homeFouls;
                paramsD[9][2] += listOfMatches.get(i).awayFouls;
                refNumberOfMatchesWithParam[9] ++;

                paramsD[10][0] += listOfMatches.get(i).homeTackles + listOfMatches.get(i).awayTackles;
                paramsD[10][1] += listOfMatches.get(i).homeTackles;
                paramsD[10][2] += listOfMatches.get(i).awayTackles;
                refNumberOfMatchesWithParam[10] ++;

            }

            double dispF1 = 0;
            double dispF2 = 0;
            double dispF3 = 0;
            double dispYC1 = 0;
            double dispYC2 = 0;
            double dispYC3 = 0;
            for (int i=0; i<listOfMatches.size(); i++){
                dispF1 += Math.pow( (listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls - paramsD[5][0]/ (double) listOfMatches.size()) , 2);
                dispF2 += Math.pow( (listOfMatches.get(i).homeFouls - paramsD[5][1]/ (double) listOfMatches.size()) , 2);
                dispF3 += Math.pow( (listOfMatches.get(i).awayFouls - paramsD[5][2]/ (double) listOfMatches.size()) , 2);
                dispYC1 += Math.pow( (listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards - paramsD[0][0]/ (double) listOfMatches.size()) , 2);
                dispYC2 += Math.pow( (listOfMatches.get(i).homeYellowCards - paramsD[0][1]/ (double) listOfMatches.size()) , 2);
                dispYC3 += Math.pow( (listOfMatches.get(i).awayYellowCards - paramsD[0][2]/ (double) listOfMatches.size()) , 2);
            }

            paramsD[11][0] = Math.sqrt(dispYC1 / (double) listOfMatches.size());
            paramsD[11][1] = Math.sqrt(dispYC2 / (double) listOfMatches.size());
            paramsD[11][2] = Math.sqrt(dispYC3 / (double) listOfMatches.size());
            paramsD[12][0] = Math.sqrt(dispF1 / (double) listOfMatches.size());
            paramsD[12][1] = Math.sqrt(dispF2 / (double) listOfMatches.size());
            paramsD[12][2] = Math.sqrt(dispF3 / (double) listOfMatches.size());


            double MO_Fouls = paramsD[5][0]/ (double) listOfMatches.size();
            double MO_FoulsHT = paramsD[5][1]/ (double) listOfMatches.size();
            double MO_FoulsAT = paramsD[5][2]/ (double) listOfMatches.size();
            double MO_FoulsDiff = (paramsD[5][1] - paramsD[5][2]) / (double) listOfMatches.size();
            double MO_YC = paramsD[0][0]/ (double) listOfMatches.size();
            double MO_YCHT = paramsD[0][1]/(double) listOfMatches.size();
            double MO_YCAT = paramsD[0][2]/(double) listOfMatches.size();
            double MO_YCDiff = (paramsD[0][1] - paramsD[0][2]) / (double) listOfMatches.size();

            double verhSumm = 0;
            double nizSumm1 = 0;
            double nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls - MO_Fouls)*(listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards - MO_YC);
                nizSumm1 += Math.pow( listOfMatches.get(i).homeFouls + listOfMatches.get(i).awayFouls - MO_Fouls , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).homeYellowCards + listOfMatches.get(i).awayYellowCards - MO_YC , 2);
            }
            paramsD[13][0] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

            verhSumm = 0;
            nizSumm1 = 0;
            nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).homeFouls - MO_FoulsHT)*(listOfMatches.get(i).homeYellowCards - MO_YCHT);
                nizSumm1 += Math.pow( listOfMatches.get(i).homeFouls - MO_FoulsHT , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).homeYellowCards - MO_YCHT , 2);
            }
            paramsD[13][1] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

            verhSumm = 0;
            nizSumm1 = 0;
            nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).awayFouls - MO_FoulsAT)*(listOfMatches.get(i).awayYellowCards - MO_YCAT);
                nizSumm1 += Math.pow( listOfMatches.get(i).awayFouls - MO_FoulsAT , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).awayYellowCards - MO_YCAT , 2);
            }
            paramsD[13][2] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

            verhSumm = 0;
            nizSumm1 = 0;
            nizSumm2 = 0;

            for (int i=0; i<listOfMatches.size(); i++){
                verhSumm += (listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO_FoulsDiff)*(listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO_YCDiff);
                nizSumm1 += Math.pow( listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO_FoulsDiff , 2);
                nizSumm2 += Math.pow( listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO_YCDiff , 2);
            }
            paramsD[14][0] = verhSumm / Math.sqrt(nizSumm1*nizSumm2);



            for (int i=0; i<paramsD.length; i++){
                ArrayList<String> parametr = new ArrayList<>();
                parametr.add(refParams[i]);
                parametr.add(String.valueOf(MyMath.round(paramsD[i][0], 2)));
                parametr.add(String.valueOf(MyMath.round(paramsD[i][1], 2)));
                parametr.add(String.valueOf(MyMath.round(paramsD[i][2], 2)));
                refList.add(parametr);
            }
        }
    }

    public void getConfrontationList(String leagueName, String homeTeamName, String awayTeamName, String allOrHomeAway){
        if ((!leagueName.contains("Выберите"))&&(!homeTeamName.contains("Выберите"))&&(!awayTeamName.contains("Выберите"))){
            ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
            for (int i=listOfSeasons.size()-1; i>=0; i--){
                boolean flagBreak1 = false;
                boolean flagBreak2 = false;
                boolean flagTeamFound = Team.isTeamFoundInSeason(homeTeamName, listOfSeasons.get(i));
                Team ht = null;
                if (flagTeamFound)
                        ht = Team.getTeamFromFileWithSeason(homeTeamName, listOfSeasons.get(i), leagueName);
                if (ht != null){
                    String matchTitle1 = Team.getShortName(homeTeamName) + Team.getShortName(awayTeamName);
                    String matchTitle2 = Team.getShortName(awayTeamName) + Team.getShortName(homeTeamName);
                    String path = "database/" + listOfSeasons.get(i) + "/" + leagueName + "/Matches/";
                    for (int k=0; k<ht.matchList.size(); k++){
                        if (ht.matchList.get(k).contains(matchTitle1)){
                            listOfMatches.add(Match.getMatchFromFileByName(path + ht.matchList.get(k) + ".xml"));
                            listForConfrontation.add(listOfSeasons.get(i));
                            flagBreak1 = true;
                        }
                        if (ht.matchList.get(k).contains(matchTitle2) && allOrHomeAway.contains("Все матчи")){
                            listOfMatches.add(Match.getMatchFromFileByName(path + ht.matchList.get(k) + ".xml"));
                            listForConfrontation.add(listOfSeasons.get(i));
                            flagBreak2 = true;
                        }
                        if (flagBreak1 && flagBreak2)
                            break;
                    }
                }
            }
        }
    }

    public void getArraysWithStats(String teamName, double[][] dataArrayThis, double[][] dataArrayOpponent, double[][] dataArrayTotal,
                                   String[] arrayOpponents, ArrayList<String> tournamentTable){
        this.teamName = teamName;
        for (int i=0; i<listOfMatches.size(); i++){
            Match m = listOfMatches.get(i);
            if (teamName.equals(m.homeTeam)){

                dataArrayThis[0][i] = m.homeScore;
                dataArrayThis[1][i] = m.homeScore;
                dataArrayThis[2][i] = m.awayScore;
                dataArrayThis[3][i] = m.homeXG;
                dataArrayThis[4][i] = 0;
                dataArrayThis[5][i] = 0;
                dataArrayThis[6][i] = m.homeScore1T;
                dataArrayThis[7][i] = m.homeScore2T;
                dataArrayThis[8][i] = m.homeBallPossession;
                dataArrayThis[9][i] = m.homeBallPossession1T;
                dataArrayThis[10][i] = m.homeBallPossession2T;
                dataArrayThis[11][i] = m.homeShots;
                dataArrayThis[12][i] = m.homeShots1T;
                dataArrayThis[13][i] = m.homeShots2T;
                dataArrayThis[14][i] = m.homeShotsOnTarget;
                dataArrayThis[15][i] = m.homeShotsOnTarget1T;
                dataArrayThis[16][i] = m.homeShotsOnTarget2T;
                dataArrayThis[17][i] = m.homeShotsOffTarget;
                dataArrayThis[18][i] = m.homeShotsOffTarget1T;
                dataArrayThis[19][i] = m.homeShotsOffTarget2T;
                dataArrayThis[20][i] = m.homeBlockedShots;
                dataArrayThis[21][i] = m.homeBlockedShots1T;
                dataArrayThis[22][i] = m.homeBlockedShots2T;
                dataArrayThis[23][i] = m.homeShotsOnPost;
                dataArrayThis[24][i] = m.homeCorners;
                dataArrayThis[25][i] = m.homeCorners1T;
                dataArrayThis[26][i] = m.homeCorners2T;
                dataArrayThis[27][i] = m.homeOffsides;
                dataArrayThis[28][i] = m.homeOffsides1T;
                dataArrayThis[29][i] = m.homeOffsides2T;
                dataArrayThis[30][i] = m.homeFouls;
                dataArrayThis[31][i] = m.homeFouls1T;
                dataArrayThis[32][i] = m.homeFouls2T;
                dataArrayThis[33][i] = m.homeYellowCards;
                dataArrayThis[34][i] = m.homeYellowCards1T;
                dataArrayThis[35][i] = m.homeYellowCards2T;

                dataArrayThis[36][i] = Math.min(m.firstYCMinute, 90);
                dataArrayThis[37][i] = Math.max(m.lastYCMinute, 0);

                dataArrayThis[38][i] = m.homeRedCards;
                dataArrayThis[39][i] = m.homeDribbles;
                dataArrayThis[40][i] = m.homeAerialsWon;
                dataArrayThis[41][i] = m.homeSaves;
                dataArrayThis[42][i] = m.homeClearances;
                dataArrayThis[43][i] = m.homeInterceptions;
                dataArrayThis[44][i] = m.homeTackles;
                dataArrayThis[45][i] = m.homeDispossessed;
                dataArrayThis[46][i] = MyMath.round(100 * m.homePassesSuccessfully / (double) m.homePasses, 0);
                dataArrayThis[47][i] = m.homeKeyPasses;
                dataArrayThis[48][i] = m.homeGoalKicks;
                dataArrayThis[49][i] = m.homeGoalKicks1T;
                dataArrayThis[50][i] = m.homeGoalKicks2T;
                dataArrayThis[51][i] = m.homeThrowIns;
                dataArrayThis[52][i] = m.homeThrowIns1T;
                dataArrayThis[53][i] = m.homeThrowIns2T;

                dataArrayOpponent[0][i] = m.awayScore;
                dataArrayOpponent[1][i] = m.homeXG;
                dataArrayOpponent[2][i] = m.awayXG;
                dataArrayOpponent[3][i] = m.awayXG;
                dataArrayOpponent[4][i] = 0;
                dataArrayOpponent[5][i] = 0;
                dataArrayOpponent[6][i] = m.awayScore1T;
                dataArrayOpponent[7][i] = m.awayScore2T;
                dataArrayOpponent[8][i] = m.awayBallPossession;
                dataArrayOpponent[9][i] = m.awayBallPossession1T;
                dataArrayOpponent[10][i] = m.awayBallPossession2T;
                dataArrayOpponent[11][i] = m.awayShots;
                dataArrayOpponent[12][i] = m.awayShots1T;
                dataArrayOpponent[13][i] = m.awayShots2T;
                dataArrayOpponent[14][i] = m.awayShotsOnTarget;
                dataArrayOpponent[15][i] = m.awayShotsOnTarget1T;
                dataArrayOpponent[16][i] = m.awayShotsOnTarget2T;
                dataArrayOpponent[17][i] = m.awayShotsOffTarget;
                dataArrayOpponent[18][i] = m.awayShotsOffTarget1T;
                dataArrayOpponent[19][i] = m.awayShotsOffTarget2T;
                dataArrayOpponent[20][i] = m.awayBlockedShots;
                dataArrayOpponent[21][i] = m.awayBlockedShots1T;
                dataArrayOpponent[22][i] = m.awayBlockedShots2T;
                dataArrayOpponent[23][i] = m.awayShotsOnPost;
                dataArrayOpponent[24][i] = m.awayCorners;
                dataArrayOpponent[25][i] = m.awayCorners1T;
                dataArrayOpponent[26][i] = m.awayCorners2T;
                dataArrayOpponent[27][i] = m.awayOffsides;
                dataArrayOpponent[28][i] = m.awayOffsides1T;
                dataArrayOpponent[29][i] = m.awayOffsides2T;
                dataArrayOpponent[30][i] = m.awayFouls;
                dataArrayOpponent[31][i] = m.awayFouls1T;
                dataArrayOpponent[32][i] = m.awayFouls2T;
                dataArrayOpponent[33][i] = m.awayYellowCards;
                dataArrayOpponent[34][i] = m.awayYellowCards1T;
                dataArrayOpponent[35][i] = m.awayYellowCards2T;

                dataArrayOpponent[38][i] = m.awayRedCards;
                dataArrayOpponent[39][i] = m.awayDribbles;
                dataArrayOpponent[40][i] = m.awayAerialsWon;
                dataArrayOpponent[41][i] = m.awaySaves;
                dataArrayOpponent[42][i] = m.awayClearances;
                dataArrayOpponent[43][i] = m.awayInterceptions;
                dataArrayOpponent[44][i] = m.awayTackles;
                dataArrayOpponent[45][i] = m.awayDispossessed;
                dataArrayOpponent[46][i] = MyMath.round(100 * m.awayPassesSuccessfully / (double) m.awayPasses, 0);
                dataArrayOpponent[47][i] = m.awayKeyPasses;
                dataArrayOpponent[48][i] = m.awayGoalKicks;
                dataArrayOpponent[49][i] = m.awayGoalKicks1T;
                dataArrayOpponent[50][i] = m.awayGoalKicks2T;
                dataArrayOpponent[51][i] = m.awayThrowIns;
                dataArrayOpponent[52][i] = m.awayThrowIns1T;
                dataArrayOpponent[53][i] = m.awayThrowIns2T;

                arrayOpponents[i] = Team.getShortName(m.awayTeam)+"(H)(" + League.getPositionInLeague(m.awayTeam, tournamentTable) + ")";

            } else if(teamName.equals(m.awayTeam)) {
                dataArrayOpponent[0][i] = m.homeScore;
                dataArrayOpponent[1][i] = m.awayXG;
                dataArrayOpponent[2][i] = m.homeXG;
                dataArrayOpponent[3][i] = m.homeXG;
                dataArrayOpponent[4][i] = 0;
                dataArrayOpponent[5][i] = 0;
                dataArrayOpponent[6][i] = m.homeScore1T;
                dataArrayOpponent[7][i] = m.homeScore2T;
                dataArrayOpponent[8][i] = m.homeBallPossession;
                dataArrayOpponent[9][i] = m.homeBallPossession1T;
                dataArrayOpponent[10][i] = m.homeBallPossession2T;
                dataArrayOpponent[11][i] = m.homeShots;
                dataArrayOpponent[12][i] = m.homeShots1T;
                dataArrayOpponent[13][i] = m.homeShots2T;
                dataArrayOpponent[14][i] = m.homeShotsOnTarget;
                dataArrayOpponent[15][i] = m.homeShotsOnTarget1T;
                dataArrayOpponent[16][i] = m.homeShotsOnTarget2T;
                dataArrayOpponent[17][i] = m.homeShotsOffTarget;
                dataArrayOpponent[18][i] = m.homeShotsOffTarget1T;
                dataArrayOpponent[19][i] = m.homeShotsOffTarget2T;
                dataArrayOpponent[20][i] = m.homeBlockedShots;
                dataArrayOpponent[21][i] = m.homeBlockedShots1T;
                dataArrayOpponent[22][i] = m.homeBlockedShots2T;
                dataArrayOpponent[23][i] = m.homeShotsOnPost;
                dataArrayOpponent[24][i] = m.homeCorners;
                dataArrayOpponent[25][i] = m.homeCorners1T;
                dataArrayOpponent[26][i] = m.homeCorners2T;
                dataArrayOpponent[27][i] = m.homeOffsides;
                dataArrayOpponent[28][i] = m.homeOffsides1T;
                dataArrayOpponent[29][i] = m.homeOffsides2T;
                dataArrayOpponent[30][i] = m.homeFouls;
                dataArrayOpponent[31][i] = m.homeFouls1T;
                dataArrayOpponent[32][i] = m.homeFouls2T;
                dataArrayOpponent[33][i] = m.homeYellowCards;
                dataArrayOpponent[34][i] = m.homeYellowCards1T;
                dataArrayOpponent[35][i] = m.homeYellowCards2T;

                dataArrayOpponent[38][i] = m.homeRedCards;
                dataArrayOpponent[39][i] = m.homeDribbles;
                dataArrayOpponent[40][i] = m.homeAerialsWon;
                dataArrayOpponent[41][i] = m.homeSaves;
                dataArrayOpponent[42][i] = m.homeClearances;
                dataArrayOpponent[43][i] = m.homeInterceptions;
                dataArrayOpponent[44][i] = m.homeTackles;
                dataArrayOpponent[45][i] = m.homeDispossessed;
                dataArrayOpponent[46][i] = MyMath.round(100 * m.homePassesSuccessfully / (double) m.homePasses, 0);
                dataArrayOpponent[47][i] = m.homeKeyPasses;
                dataArrayOpponent[48][i] = m.homeGoalKicks;
                dataArrayOpponent[49][i] = m.homeGoalKicks1T;
                dataArrayOpponent[50][i] = m.homeGoalKicks2T;
                dataArrayOpponent[51][i] = m.homeThrowIns;
                dataArrayOpponent[52][i] = m.homeThrowIns1T;
                dataArrayOpponent[53][i] = m.homeThrowIns2T;

                dataArrayThis[0][i] = m.awayScore;
                dataArrayThis[1][i] = m.awayScore;
                dataArrayThis[2][i] = m.homeScore;
                dataArrayThis[3][i] = m.awayXG;
                dataArrayThis[4][i] = 0;
                dataArrayThis[5][i] = 0;
                dataArrayThis[6][i] = m.awayScore1T;
                dataArrayThis[7][i] = m.awayScore2T;
                dataArrayThis[8][i] = m.awayBallPossession;
                dataArrayThis[9][i] = m.awayBallPossession1T;
                dataArrayThis[10][i] = m.awayBallPossession2T;
                dataArrayThis[11][i] = m.awayShots;
                dataArrayThis[12][i] = m.awayShots1T;
                dataArrayThis[13][i] = m.awayShots2T;
                dataArrayThis[14][i] = m.awayShotsOnTarget;
                dataArrayThis[15][i] = m.awayShotsOnTarget1T;
                dataArrayThis[16][i] = m.awayShotsOnTarget2T;
                dataArrayThis[17][i] = m.awayShotsOffTarget;
                dataArrayThis[18][i] = m.awayShotsOffTarget1T;
                dataArrayThis[19][i] = m.awayShotsOffTarget2T;
                dataArrayThis[20][i] = m.awayBlockedShots;
                dataArrayThis[21][i] = m.awayBlockedShots1T;
                dataArrayThis[22][i] = m.awayBlockedShots2T;
                dataArrayThis[23][i] = m.awayShotsOnPost;
                dataArrayThis[24][i] = m.awayCorners;
                dataArrayThis[25][i] = m.awayCorners1T;
                dataArrayThis[26][i] = m.awayCorners2T;
                dataArrayThis[27][i] = m.awayOffsides;
                dataArrayThis[28][i] = m.awayOffsides1T;
                dataArrayThis[29][i] = m.awayOffsides2T;
                dataArrayThis[30][i] = m.awayFouls;
                dataArrayThis[31][i] = m.awayFouls1T;
                dataArrayThis[32][i] = m.awayFouls2T;
                dataArrayThis[33][i] = m.awayYellowCards;
                dataArrayThis[34][i] = m.awayYellowCards1T;
                dataArrayThis[35][i] = m.awayYellowCards2T;

                dataArrayThis[36][i] = Math.min(m.firstYCMinute, 90);
                dataArrayThis[37][i] = Math.max(m.lastYCMinute, 0);

                dataArrayThis[38][i] = m.awayRedCards;
                dataArrayThis[39][i] = m.awayDribbles;
                dataArrayThis[40][i] = m.awayAerialsWon;
                dataArrayThis[41][i] = m.awaySaves;
                dataArrayThis[42][i] = m.awayClearances;
                dataArrayThis[43][i] = m.awayInterceptions;
                dataArrayThis[44][i] = m.awayTackles;
                dataArrayThis[45][i] = m.awayDispossessed;
                dataArrayThis[46][i] = MyMath.round(100 * m.awayPassesSuccessfully / (double) m.awayPasses, 0);
                dataArrayThis[47][i] = m.awayKeyPasses;
                dataArrayThis[48][i] = m.awayGoalKicks;
                dataArrayThis[49][i] = m.awayGoalKicks1T;
                dataArrayThis[50][i] = m.awayGoalKicks2T;
                dataArrayThis[51][i] = m.awayThrowIns;
                dataArrayThis[52][i] = m.awayThrowIns1T;
                dataArrayThis[53][i] = m.awayThrowIns2T;

                arrayOpponents[i] = Team.getShortName(m.homeTeam)+"(A)(" + League.getPositionInLeague(m.homeTeam, tournamentTable) + ")";
            }

            dataArrayTotal[0][i] = m.homeScore + m.awayScore;
            dataArrayTotal[1][i] = 0;
            dataArrayTotal[2][i] = 0;
            dataArrayTotal[3][i] = m.homeXG + m.awayXG;
            dataArrayTotal[4][i] = 0;
            dataArrayTotal[5][i] = 0;
            dataArrayTotal[6][i] = m.homeScore1T + m.awayScore1T;
            dataArrayTotal[7][i] = m.homeScore2T + m.awayScore2T;
            dataArrayTotal[8][i] = m.homeBallPossession + m.awayBallPossession;
            dataArrayTotal[9][i] = m.homeBallPossession1T + m.awayBallPossession1T;
            dataArrayTotal[10][i] = m.homeBallPossession2T + m.awayBallPossession2T;
            dataArrayTotal[11][i] = m.homeShots + m.awayShots;
            dataArrayTotal[12][i] = m.homeShots1T + m.awayShots1T;
            dataArrayTotal[13][i] = m.homeShots2T + m.awayShots2T;
            dataArrayTotal[14][i] = m.homeShotsOnTarget + m.awayShotsOnTarget;
            dataArrayTotal[15][i] = m.homeShotsOnTarget1T + m.awayShotsOnTarget1T;
            dataArrayTotal[16][i] = m.homeShotsOnTarget2T + m.awayShotsOnTarget2T;
            dataArrayTotal[17][i] = m.homeShotsOffTarget + m.awayShotsOffTarget;
            dataArrayTotal[18][i] = m.homeShotsOffTarget1T + m.awayShotsOffTarget1T;
            dataArrayTotal[19][i] = m.homeShotsOffTarget2T + m.awayShotsOffTarget2T;
            dataArrayTotal[20][i] = m.homeBlockedShots + m.awayBlockedShots;
            dataArrayTotal[21][i] = m.homeBlockedShots1T + m.awayBlockedShots1T;
            dataArrayTotal[22][i] = m.homeBlockedShots2T + m.awayBlockedShots2T;
            dataArrayTotal[23][i] = m.homeShotsOnPost + m.awayShotsOnPost;
            dataArrayTotal[24][i] = m.homeCorners + m.awayCorners;
            dataArrayTotal[25][i] = m.homeCorners1T + m.awayCorners1T;
            dataArrayTotal[26][i] = m.homeCorners2T + m.awayCorners2T;
            dataArrayTotal[27][i] = m.homeOffsides + m.awayOffsides;
            dataArrayTotal[28][i] = m.homeOffsides1T + m.awayOffsides1T;
            dataArrayTotal[29][i] = m.homeOffsides2T + m.awayOffsides2T;
            dataArrayTotal[30][i] = m.homeFouls + m.awayFouls;
            dataArrayTotal[31][i] = m.homeFouls1T + m.awayFouls1T;
            dataArrayTotal[32][i] = m.homeFouls2T + m.awayFouls2T;
            dataArrayTotal[33][i] = m.homeYellowCards + m.awayYellowCards;
            dataArrayTotal[34][i] = m.homeYellowCards1T + m.awayYellowCards1T;
            dataArrayTotal[35][i] = m.homeYellowCards2T + m.awayYellowCards2T;
            dataArrayTotal[38][i] = m.homeRedCards + m.awayRedCards;
            dataArrayTotal[39][i] = m.homeDribbles + m.awayDribbles;
            dataArrayTotal[40][i] = m.homeAerialsWon + m.awayAerialsWon;
            dataArrayTotal[41][i] = m.homeSaves + m.awaySaves;
            dataArrayTotal[42][i] = m.homeClearances + m.awayClearances;
            dataArrayTotal[43][i] = m.homeInterceptions + m.awayInterceptions;
            dataArrayTotal[44][i] = m.homeTackles + m.awayTackles;
            dataArrayTotal[45][i] = m.homeDispossessed + m.awayDispossessed;
            dataArrayTotal[46][i] = 0;
            dataArrayTotal[47][i] = m.homeKeyPasses + m.awayKeyPasses;
            dataArrayTotal[48][i] = m.homeGoalKicks + m.awayGoalKicks;
            dataArrayTotal[49][i] = m.homeGoalKicks1T + m.awayGoalKicks1T;
            dataArrayTotal[50][i] = m.homeGoalKicks2T + m.awayGoalKicks2T;
            dataArrayTotal[51][i] = m.homeThrowIns + m.awayThrowIns;
            dataArrayTotal[52][i] = m.homeThrowIns1T + m.awayThrowIns1T;
            dataArrayTotal[53][i] = m.homeThrowIns2T + m.awayThrowIns2T;


        }
    }

    public void incNumbersArray(Match match){
        if (Settings.isWhoScoredLeague(match.league)){
            for(int i=0; i<numberOfMatchesWithParam.length; i++){
                numberOfMatchesWithParam[i]++;
            }
        } else {
            for(int i=0; i<5; i++){
                numberOfMatchesWithParam[i]++;
            }
            if (match.homeXG + match.awayXG > 0){
                numberOfMatchesWithParam[5]++;
            }
            if (match.homeBallPossession + match.awayBallPossession > 0){
                numberOfMatchesWithParam[6]++;
                if (match.homeBallPossession1T + match.awayBallPossession1T > 0){
                    numberOfMatchesWithParam[30]++;
                }
                if (match.homeBallPossession2T + match.awayBallPossession2T > 0){
                    numberOfMatchesWithParam[31]++;
                }
            }
            if (match.homeShots + match.awayShots > 0){
                numberOfMatchesWithParam[7]++;
                if (match.homeShots1T + match.awayShots1T > 0){
                    numberOfMatchesWithParam[36]++;
                }
                if (match.homeShots2T + match.awayShots2T > 0){
                    numberOfMatchesWithParam[37]++;
                }
            }

            if (match.homeShotsOnTarget + match.awayShotsOnTarget > 0){
                numberOfMatchesWithParam[8]++;
                if (match.homeShotsOnTarget1T + match.awayShotsOnTarget1T > 0){
                    numberOfMatchesWithParam[38]++;
                }
                if (match.homeShotsOnTarget2T + match.awayShotsOnTarget2T > 0){
                    numberOfMatchesWithParam[39]++;
                }
            }

            if (match.homeShotsOffTarget + match.awayShotsOffTarget > 0){
                numberOfMatchesWithParam[9]++;
                if (match.homeShotsOffTarget1T + match.awayShotsOffTarget1T > 0){
                    numberOfMatchesWithParam[40]++;
                }
                if (match.homeShotsOffTarget2T + match.awayShotsOffTarget2T > 0){
                    numberOfMatchesWithParam[41]++;
                }
            }

            if (match.homeCorners + match.awayCorners > 0){
                numberOfMatchesWithParam[10]++;
                if (match.homeCorners1T + match.awayCorners1T > 0){
                    numberOfMatchesWithParam[32]++;
                }
                if (match.homeCorners2T + match.awayCorners2T > 0){
                    numberOfMatchesWithParam[33]++;
                }
            }

            if (match.homeOffsides + match.awayOffsides > 0){
                numberOfMatchesWithParam[11]++;
                if (match.homeOffsides1T + match.awayOffsides1T > 0){
                    numberOfMatchesWithParam[44]++;
                }
                if (match.homeOffsides2T + match.awayOffsides2T > 0){
                    numberOfMatchesWithParam[45]++;
                }
            }

            if (match.homeBlockedShots + match.awayBlockedShots > 0){
                numberOfMatchesWithParam[12]++;
                if (match.homeBlockedShots1T + match.awayBlockedShots1T > 0){
                    numberOfMatchesWithParam[42]++;
                }
                if (match.homeBlockedShots2T + match.awayBlockedShots2T > 0){
                    numberOfMatchesWithParam[43]++;
                }
            }

            if (match.homeFouls + match.awayFouls > 0){
                numberOfMatchesWithParam[13]++;
                if (match.homeFouls1T + match.awayFouls1T > 0){
                    numberOfMatchesWithParam[46]++;
                }
                if (match.homeFouls2T + match.awayFouls2T > 0){
                    numberOfMatchesWithParam[47]++;
                }
            }

            numberOfMatchesWithParam[14]++;
            numberOfMatchesWithParam[15]++;
            numberOfMatchesWithParam[34]++;
            numberOfMatchesWithParam[35]++;

            for(int i=16; i<=21; i++){
                numberOfMatchesWithParam[i]++;
            }

            if (match.homeScore1T + match.awayScore1T > 0){
                numberOfMatchesWithParam[22]++;
            }
            if (match.homeScore2T + match.awayScore2T > 0){
                numberOfMatchesWithParam[23]++;
            }

            if (match.homeGoalsBy15minutes != null){
                String[] goalsBy15MinutesHT = match.homeGoalsBy15minutes.split("\\*");
                String[] goalsBy15MinutesAT = match.homeGoalsBy15minutes.split("\\*");
                if (match.homeScore + match.awayScore > 0){
                    int summ = 0;
                    for (int j=0; j<goalsBy15MinutesHT.length; j++){
                        summ += Integer.parseInt(goalsBy15MinutesHT[j]) + Integer.parseInt(goalsBy15MinutesAT[j]);
                    }
                    if (summ > 0 ){
                        numberOfMatchesWithParam[24]++;
                        numberOfMatchesWithParam[25]++;
                        numberOfMatchesWithParam[26]++;
                        numberOfMatchesWithParam[27]++;
                        numberOfMatchesWithParam[28]++;
                        numberOfMatchesWithParam[29]++;
                    }
                } else {
                    numberOfMatchesWithParam[24]++;
                    numberOfMatchesWithParam[25]++;
                    numberOfMatchesWithParam[26]++;
                    numberOfMatchesWithParam[27]++;
                    numberOfMatchesWithParam[28]++;
                    numberOfMatchesWithParam[29]++;
                }
            }

            if (match.homeDribbles + match.awayDribbles > 0){
                numberOfMatchesWithParam[68]++;
                if (match.homeDribbles1T + match.awayDribbles1T > 0){
                    numberOfMatchesWithParam[48]++;
                }
                if (match.homeDribbles2T + match.awayDribbles2T > 0){
                    numberOfMatchesWithParam[49]++;
                }
            }

            if (match.homeAerialsWon + match.awayAerialsWon > 0){
                numberOfMatchesWithParam[69]++;
                if (match.homeAerialsWon1T + match.awayAerialsWon1T > 0){
                    numberOfMatchesWithParam[50]++;
                }
                if (match.homeAerialsWon2T + match.awayAerialsWon2T > 0){
                    numberOfMatchesWithParam[51]++;
                }
            }

            if (match.homeSaves + match.awaySaves > 0){
                numberOfMatchesWithParam[70]++;
                if (match.homeSaves1T + match.awaySaves1T > 0){
                    numberOfMatchesWithParam[52]++;
                }
                if (match.homeSaves2T + match.awaySaves2T > 0){
                    numberOfMatchesWithParam[53]++;
                }
            }
            if (match.homeClearances + match.awayClearances > 0){
                numberOfMatchesWithParam[71]++;
                if (match.homeClearances1T + match.awayClearances1T > 0){
                    numberOfMatchesWithParam[54]++;
                }
                if (match.homeClearances2T + match.awayClearances2T > 0){
                    numberOfMatchesWithParam[55]++;
                }
            }

            if (match.homeInterceptions + match.awayInterceptions > 0){
                numberOfMatchesWithParam[72]++;
                if (match.homeInterceptions1T + match.awayInterceptions1T > 0){
                    numberOfMatchesWithParam[56]++;
                }
                if (match.homeInterceptions2T + match.awayInterceptions2T > 0){
                    numberOfMatchesWithParam[57]++;
                }
            }

            if (match.homeTackles + match.awayTackles > 0){
                numberOfMatchesWithParam[73]++;
                if (match.homeTackles1T + match.awayTackles1T > 0){
                    numberOfMatchesWithParam[58]++;
                }
                if (match.homeTackles2T + match.awayTackles2T > 0){
                    numberOfMatchesWithParam[59]++;
                }
            }

            if (match.homePassesSuccessfully + match.awayPassesSuccessfully > 0){
                numberOfMatchesWithParam[74]++;
                numberOfMatchesWithParam[75]++;
                if (match.homePassesSuccessfully1T + match.awayPassesSuccessfully1T > 0){
                    numberOfMatchesWithParam[60]++;
                    numberOfMatchesWithParam[62]++;
                }
                if (match.homePassesSuccessfully2T + match.awayPassesSuccessfully2T > 0){
                    numberOfMatchesWithParam[61]++;
                    numberOfMatchesWithParam[63]++;
                }
            }

            if (match.homeKeyPasses + match.awayKeyPasses > 0){
                numberOfMatchesWithParam[76]++;
                if (match.homeKeyPasses1T + match.awayKeyPasses1T > 0){
                    numberOfMatchesWithParam[64]++;
                }
                if (match.homeKeyPasses2T + match.awayKeyPasses2T > 0){
                    numberOfMatchesWithParam[65]++;
                }
            }

            if (match.homeDispossessed + match.awayDispossessed > 0){
                numberOfMatchesWithParam[77]++;
                if (match.homeDispossessed1T + match.awayDispossessed1T > 0){
                    numberOfMatchesWithParam[66]++;
                }
                if (match.homeDispossessed2T + match.awayDispossessed2T > 0){
                    numberOfMatchesWithParam[67]++;
                }
            }

            if (match.homeGoalKicks + match.awayGoalKicks > 0){
                numberOfMatchesWithParam[78]++;
                if (match.homeGoalKicks1T + match.awayGoalKicks1T > 0){
                    numberOfMatchesWithParam[79]++;
                }
                if (match.homeGoalKicks2T + match.awayGoalKicks2T > 0){
                    numberOfMatchesWithParam[80]++;
                }
            }

            if (match.homeThrowIns + match.awayThrowIns > 0){
                numberOfMatchesWithParam[81]++;
                if (match.homeThrowIns1T + match.awayThrowIns1T > 0){
                    numberOfMatchesWithParam[82]++;
                }
                if (match.homeThrowIns2T + match.awayThrowIns2T > 0){
                    numberOfMatchesWithParam[83]++;
                }
            }



        }
    }

}
