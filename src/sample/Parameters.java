package sample;

import javax.swing.*;
import java.util.ArrayList;

public class Parameters {

    public Parameters() {
    }

    public static String[] getParamsList(){
        return new String[]{"Выберите показатель", "Голы", "Голы 1 тайм", "Голы 2 тайм", "Владение", "Удары", "Удары 1 тайм", "Удары 2 тайм",
                "Удары в створ", "Удары в створ 1 тайм", "Удары в створ 2 тайм", "Угловые", "Угловые 1 тайм", "Угловые 2 тайм",
                "Офсайды", "Офсайды 1 тайм", "Офсайды 2 тайм", "Фолы", "Фолы 1 тайм", "Фолы 2 тайм", "Желтые карточки",
                "Желтые карточки 1 тайм", "Желтые карточки 2 тайм", "Красные карточки",
                "Удары от ворот", "Удары от ворот 1 тайм", "Удары от ворот 2 тайм",
                "Вброс аутов", "Вброс аутов 1 тайм", "Вброс аутов 2 тайм", "Дриблинг", "Воздушные единоборства"};
    }

    public static String[] getIndex( String parameter){
        String[] result = new String[0];

        switch (parameter){
            case "Выберите показатель":{
                result = new String[]{"Выберите тип ставки"}; break;
            }
            case "Голы":
            case "Голы 1 тайм":
            case "Голы 2 тайм":{
                result = new String[]{"Выберите тип ставки", "Общий тотал", "Инд.тотал команды", "Инд.тотал соперника", "Фора команды"}; break;
            }
            case "Владение":{
                result = new String[]{"Выберите тип ставки", "Фора команды"}; break;
            }
            case "Удары":
            case "Удары 1 тайм":
            case "Удары 2 тайм":
            case "Удары в створ":
            case "Удары в створ 1 тайм":
            case "Удары в створ 2 тайм":
            case "Угловые":
            case "Угловые 1 тайм":
            case "Угловые 2 тайм":
            case "Офсайды":
            case "Офсайды 1 тайм":
            case "Офсайды 2 тайм":
            case "Фолы":
            case "Фолы 1 тайм":
            case "Фолы 2 тайм":
            case "Желтые карточки":
            case "Желтые карточки 1 тайм":
            case "Желтые карточки 2 тайм":
            case "Красные карточки":
            case "Дриблинг":
            case "Воздушные единоборства":
            case "Удары от ворот":
            case "Удары от ворот 1 тайм":
            case "Удары от ворот 2 тайм":
            case "Вброс аутов":
            case "Вброс аутов 1 тайм":
            case "Вброс аутов 2 тайм":
            {
                result = new String[]{"Выберите тип ставки", "Общий тотал", "Инд.тотал команды", "Инд.тотал соперника", "Фора команды"}; break;
            }
        }

        return result;
    }

    public static double[] getValues(String parameter, String index){
        double[] result = new double[3];

        if ((index.equals("Выберите тип ставки"))||(parameter.equals("Выберите показатель"))){
            return new double[]{0,0,0};
        }
        if ((parameter.equals("Голы"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Голы"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Голы"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Голы"))&&(index.equals("Фора команды"))){
            return new double[]{-3.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-2.5,2.5,0.5};
        }
        if ((parameter.equals("Голы 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,3.5,0.5};
        }
        if ((parameter.equals("Голы 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-2.5,2.5,0.5};
        }
        if ((parameter.equals("Владение"))&&(index.equals("Фора команды"))){
            return new double[]{-45.5,45.5,1};
        }
        if ((parameter.equals("Удары в створ"))&&(index.equals("Общий тотал"))){
            return new double[]{4.5,20.5,0.5};
        }
        if ((parameter.equals("Удары в створ"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,12.5,0.5};
        }
        if ((parameter.equals("Удары в створ"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,12.5,0.5};
        }
        if ((parameter.equals("Удары в створ"))&&(index.equals("Фора команды"))){
            return new double[]{-6.5,6.5,0.5};
        }
        if ((parameter.equals("Удары в створ 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,15.5,0.5};
        }
        if ((parameter.equals("Удары в створ 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары в створ 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары в створ 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-6.5,6.5,0.5};
        }
        if ((parameter.equals("Удары в створ 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,15.5,0.5};
        }
        if ((parameter.equals("Удары в створ 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары в створ 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары в створ 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-6.5,6.5,0.5};
        }
        if ((parameter.equals("Удары"))&&(index.equals("Общий тотал"))){
            return new double[]{9.5,50.5,0.5};
        }
        if ((parameter.equals("Удары"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Удары"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Удары"))&&(index.equals("Фора команды"))){
            return new double[]{-9.5,9.5,0.5};
        }
        if ((parameter.equals("Удары 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{3.5,25.5,0.5};
        }
        if ((parameter.equals("Удары 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,20.5,0.5};
        }
        if ((parameter.equals("Удары 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,20.5,0.5};
        }
        if ((parameter.equals("Удары 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-9.5,9.5,0.5};
        }
        if ((parameter.equals("Удары 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{3.5,25.5,0.5};
        }
        if ((parameter.equals("Удары 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,20.5,0.5};
        }
        if ((parameter.equals("Удары 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,20.5,0.5};
        }
        if ((parameter.equals("Удары 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-9.5,9.5,0.5};
        }
        if ((parameter.equals("Угловые"))&&(index.equals("Общий тотал"))){
            return new double[]{4.5,16.5,0.5};
        }
        if ((parameter.equals("Угловые"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,12.5,0.5};
        }
        if ((parameter.equals("Угловые"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,12.5,0.5};
        }
        if ((parameter.equals("Угловые"))&&(index.equals("Фора команды"))){
            return new double[]{-5.5,5.5,0.5};
        }
        if ((parameter.equals("Угловые 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{1.5,7.5,0.5};
        }
        if ((parameter.equals("Угловые 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,6.5,0.5};
        }
        if ((parameter.equals("Угловые 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,6.5,0.5};
        }
        if ((parameter.equals("Угловые 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-4.5,4.5,0.5};
        }
        if ((parameter.equals("Угловые 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,12.5,0.5};
        }
        if ((parameter.equals("Угловые 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,6.5,0.5};
        }
        if ((parameter.equals("Угловые 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,6.5,0.5};
        }
        if ((parameter.equals("Угловые 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-4.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,12.5,0.5};
        }
        if ((parameter.equals("Офсайды"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Офсайды"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Офсайды"))&&(index.equals("Фора команды"))){
            return new double[]{-5.5,5.5,0.5};
        }
        if ((parameter.equals("Офсайды 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-2.5,2.5,0.5};
        }
        if ((parameter.equals("Офсайды 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Офсайды 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-2.5,2.5,0.5};
        }
        if ((parameter.equals("Фолы"))&&(index.equals("Общий тотал"))){
            return new double[]{9.5,45.5,0.5};
        }
        if ((parameter.equals("Фолы"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Фолы"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Фолы"))&&(index.equals("Фора команды"))){
            return new double[]{-10.5,10.5,0.5};
        }
        if ((parameter.equals("Фолы 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{9.5,45.5,0.5};
        }
        if ((parameter.equals("Фолы 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Фолы 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Фолы 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-10.5,10.5,0.5};
        }
        if ((parameter.equals("Фолы 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{9.5,45.5,0.5};
        }
        if ((parameter.equals("Фолы 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Фолы 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Фолы 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-10.5,10.5,0.5};
        }
        if ((parameter.equals("Желтые карточки"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,9.5,0.5};
        }
        if ((parameter.equals("Желтые карточки"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,6.5,0.5};
        }
        if ((parameter.equals("Желтые карточки"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,6.5,0.5};
        }
        if ((parameter.equals("Желтые карточки"))&&(index.equals("Фора команды"))){
            return new double[]{-3.5,3.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 1 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-2.5,2.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,5.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,4.5,0.5};
        }
        if ((parameter.equals("Желтые карточки 2 тайм"))&&(index.equals("Фора команды"))){
            return new double[]{-2.5,2.5,0.5};
        }
        if ((parameter.equals("Красные карточки"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,2.5,0.5};
        }
        if ((parameter.equals("Красные карточки"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,2.5,0.5};
        }
        if ((parameter.equals("Красные карточки"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,2.5,0.5};
        }
        if ((parameter.equals("Дриблинг"))&&(index.equals("Общий тотал"))){
            return new double[]{5.5,30.5,0.5};
        }
        if ((parameter.equals("Дриблинг"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,20.5,0.5};
        }
        if ((parameter.equals("Дриблинг"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,20.5,0.5};
        }
        if ((parameter.equals("Дриблинг"))&&(index.equals("Фора команды"))){
            return new double[]{-10.5,10.5,0.5};
        }
        if ((parameter.equals("Воздушные единоборства"))&&(index.equals("Общий тотал"))){
            return new double[]{15.5,45.5,0.5};
        }
        if ((parameter.equals("Воздушные единоборства"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{5.5,25.5,0.5};
        }
        if ((parameter.equals("Воздушные единоборства"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{5.5,25.5,0.5};
        }
        if ((parameter.equals("Воздушные единоборства"))&&(index.equals("Фора команды"))) {
            return new double[]{-10.5, 10.5, 0.5};
        }
        if ((parameter.equals("Удары от ворот"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,30.5,0.5};
        }
        if ((parameter.equals("Удары от ворот"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,15.5,0.5};
        }
        if ((parameter.equals("Удары от ворот"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,15.5,0.5};
        }
        if ((parameter.equals("Удары от ворот"))&&(index.equals("Фора команды"))) {
            return new double[]{-10.5, 10.5, 0.5};
        }
        if ((parameter.equals("Удары от ворот 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,15.5,0.5};
        }
        if ((parameter.equals("Удары от ворот 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары от ворот 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары от ворот 1 тайм"))&&(index.equals("Фора команды"))) {
            return new double[]{-10.5, 10.5, 0.5};
        }
        if ((parameter.equals("Удары от ворот 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,15.5,0.5};
        }
        if ((parameter.equals("Удары от ворот 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары от ворот 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,10.5,0.5};
        }
        if ((parameter.equals("Удары от ворот 2 тайм"))&&(index.equals("Фора команды"))) {
            return new double[]{-10.5, 10.5, 0.5};
        }
        if ((parameter.equals("Вброс аутов"))&&(index.equals("Общий тотал"))){
            return new double[]{10.5,65.5,0.5};
        }
        if ((parameter.equals("Вброс аутов"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{5.5,35.5,0.5};
        }
        if ((parameter.equals("Вброс аутов"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{5.5,35.5,0.5};
        }
        if ((parameter.equals("Вброс аутов"))&&(index.equals("Фора команды"))) {
            return new double[]{-20.5, 20.5, 0.5};
        }
        if ((parameter.equals("Вброс аутов 1 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,35.5,0.5};
        }
        if ((parameter.equals("Вброс аутов 1 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Вброс аутов 1 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Вброс аутов 1 тайм"))&&(index.equals("Фора команды"))) {
            return new double[]{-20.5, 20.5, 0.5};
        }
        if ((parameter.equals("Вброс аутов 2 тайм"))&&(index.equals("Общий тотал"))){
            return new double[]{0.5,35.5,0.5};
        }
        if ((parameter.equals("Вброс аутов 2 тайм"))&&(index.equals("Инд.тотал команды"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Вброс аутов 2 тайм"))&&(index.equals("Инд.тотал соперника"))){
            return new double[]{0.5,25.5,0.5};
        }
        if ((parameter.equals("Вброс аутов 2 тайм"))&&(index.equals("Фора команды"))) {
            return new double[]{-20.5, 20.5, 0.5};
        }
        return result;
    }

    public static JTable getTableByParams(String teamName, ArrayList<Match> matchList, String parameter, String index, Double value){

        int typeOfTable = 1;

        if ((index == null)||(value == null)){
            index = "Выберите тип ставки";
        }
        int numberOfMatches = matchList.size();
        int numberPlus = 0;
        int numberEqual = 0;
        int numberMinus = 0;

        double morePercent;
        double lessPercent;
        double equalPercent;

        switch (parameter + "_" + index){
            case "Голы_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore + matchList.get(i).awayScore > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore + matchList.get(i).awayScore < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore + matchList.get(i).awayScore == value)
                        numberEqual++;
                }
                break;
            }
            case "Голы_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore == value)
                            numberEqual++;
                    }
                }
                break;
            }
            case "Голы_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore == value)
                            numberEqual++;
                    }
                }
                break;
            }
            case "Голы_Фора команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore - matchList.get(i).awayScore + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore - matchList.get(i).awayScore + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore - matchList.get(i).awayScore + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore - matchList.get(i).homeScore + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore - matchList.get(i).homeScore + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore - matchList.get(i).homeScore + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
                break;
            }
            case "Голы 1 тайм_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore1T + matchList.get(i).awayScore1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore1T + matchList.get(i).awayScore1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore1T + matchList.get(i).awayScore1T == value)
                        numberEqual++;
                }
                break;
            }
            case "Голы 1 тайм_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore1T == value)
                            numberEqual++;
                    }
                }
                break;
            }
            case "Голы 1 тайм_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore1T == value)
                            numberEqual++;
                    }
                }
                break;
            }
            case "Голы 1 тайм_Фора команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore1T - matchList.get(i).awayScore1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore1T - matchList.get(i).awayScore1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore1T - matchList.get(i).awayScore1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore1T - matchList.get(i).homeScore1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore1T - matchList.get(i).homeScore1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore1T - matchList.get(i).homeScore1T + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
                break;
            }
            case "Голы 2 тайм_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore2T + matchList.get(i).awayScore2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore2T + matchList.get(i).awayScore2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore2T + matchList.get(i).awayScore2T == value)
                        numberEqual++;
                }
                break;
            }
            case "Голы 2 тайм_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore2T == value)
                            numberEqual++;
                    }
                }
                break;
            }
            case "Голы 2 тайм_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore2T == value)
                            numberEqual++;
                    }
                }
                break;
            }
            case "Голы 2 тайм_Фора команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore2T - matchList.get(i).awayScore2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore2T - matchList.get(i).awayScore2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore2T - matchList.get(i).awayScore2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore2T - matchList.get(i).homeScore2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore2T - matchList.get(i).homeScore2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore2T - matchList.get(i).homeScore2T + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
                break;
            }
            case "Владение_Фора команды":{
                matchList = deleteEmptyMatches("Владение", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeBallPossession - matchList.get(i).awayBallPossession + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeBallPossession - matchList.get(i).awayBallPossession + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeBallPossession - matchList.get(i).awayBallPossession + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayBallPossession - matchList.get(i).homeBallPossession + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayBallPossession - matchList.get(i).homeBallPossession + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayBallPossession - matchList.get(i).homeBallPossession + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }

                break;
            }
            case "Удары в створ_Общий тотал":{
                matchList = deleteEmptyMatches("Удары в створ", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShotsOnTarget + matchList.get(i).awayShotsOnTarget > value)
                        numberPlus++;
                    if (matchList.get(i).homeShotsOnTarget + matchList.get(i).awayShotsOnTarget < value)
                        numberMinus++;
                    if (matchList.get(i).homeShotsOnTarget + matchList.get(i).awayShotsOnTarget == value)
                        numberEqual++;

                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары в створ", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары в створ", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ_Фора команды":{
                matchList = deleteEmptyMatches("Удары в створ", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget - matchList.get(i).awayShotsOnTarget + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget - matchList.get(i).awayShotsOnTarget + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget - matchList.get(i).awayShotsOnTarget + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget - matchList.get(i).homeShotsOnTarget + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget - matchList.get(i).homeShotsOnTarget + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget - matchList.get(i).homeShotsOnTarget + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Удары в створ 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShotsOnTarget1T + matchList.get(i).awayShotsOnTarget1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeShotsOnTarget1T + matchList.get(i).awayShotsOnTarget1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeShotsOnTarget1T + matchList.get(i).awayShotsOnTarget1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары в створ 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары в створ 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Удары в створ 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget1T - matchList.get(i).awayShotsOnTarget1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget1T - matchList.get(i).awayShotsOnTarget1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget1T - matchList.get(i).awayShotsOnTarget1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget1T - matchList.get(i).homeShotsOnTarget1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget1T - matchList.get(i).homeShotsOnTarget1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget1T - matchList.get(i).homeShotsOnTarget1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Удары в створ 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShotsOnTarget2T + matchList.get(i).awayShotsOnTarget2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeShotsOnTarget2T + matchList.get(i).awayShotsOnTarget2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeShotsOnTarget2T + matchList.get(i).awayShotsOnTarget2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары в створ 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары в створ 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары в створ 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Удары в створ 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget2T - matchList.get(i).awayShotsOnTarget2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget2T - matchList.get(i).awayShotsOnTarget2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget2T - matchList.get(i).awayShotsOnTarget2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget2T - matchList.get(i).homeShotsOnTarget2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget2T - matchList.get(i).homeShotsOnTarget2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget2T - matchList.get(i).homeShotsOnTarget2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары_Общий тотал":{
                matchList = deleteEmptyMatches("Удары", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShots + matchList.get(i).awayShots > value)
                        numberPlus++;
                    if (matchList.get(i).homeShots + matchList.get(i).awayShots < value)
                        numberMinus++;
                    if (matchList.get(i).homeShots + matchList.get(i).awayShots == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShots > value)
                            numberPlus++;
                        if (matchList.get(i).homeShots < value)
                            numberMinus++;
                        if (matchList.get(i).homeShots == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots > value)
                            numberPlus++;
                        if (matchList.get(i).awayShots < value)
                            numberMinus++;
                        if (matchList.get(i).awayShots == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShots > value)
                            numberPlus++;
                        if (matchList.get(i).homeShots < value)
                            numberMinus++;
                        if (matchList.get(i).homeShots == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots > value)
                            numberPlus++;
                        if (matchList.get(i).awayShots < value)
                            numberMinus++;
                        if (matchList.get(i).awayShots == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары_Фора команды":{
                matchList = deleteEmptyMatches("Удары", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShots - matchList.get(i).awayShots + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShots - matchList.get(i).awayShots + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShots - matchList.get(i).awayShots + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots - matchList.get(i).homeShots + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShots - matchList.get(i).homeShots + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShots - matchList.get(i).homeShots + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Удары 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShots1T + matchList.get(i).awayShots1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeShots1T + matchList.get(i).awayShots1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeShots1T + matchList.get(i).awayShots1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShots1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShots1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShots1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShots1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShots1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShots1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShots1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShots1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShots1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShots1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Удары 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShots1T - matchList.get(i).awayShots1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShots1T - matchList.get(i).awayShots1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShots1T - matchList.get(i).awayShots1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots1T - matchList.get(i).homeShots1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShots1T - matchList.get(i).homeShots1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShots1T - matchList.get(i).homeShots1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Удары 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShots2T + matchList.get(i).awayShots2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeShots2T + matchList.get(i).awayShots2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeShots2T + matchList.get(i).awayShots2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShots2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShots2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShots2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShots2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShots2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShots2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeShots2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeShots2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayShots2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayShots2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Удары 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShots2T - matchList.get(i).awayShots2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShots2T - matchList.get(i).awayShots2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShots2T - matchList.get(i).awayShots2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShots2T - matchList.get(i).homeShots2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShots2T - matchList.get(i).homeShots2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShots2T - matchList.get(i).homeShots2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }

            case "Угловые_Общий тотал":{
                matchList = deleteEmptyMatches("Угловые", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeCorners + matchList.get(i).awayCorners > value)
                        numberPlus++;
                    if (matchList.get(i).homeCorners + matchList.get(i).awayCorners < value)
                        numberMinus++;
                    if (matchList.get(i).homeCorners + matchList.get(i).awayCorners == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Угловые", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners > value)
                            numberPlus++;
                        if (matchList.get(i).homeCorners < value)
                            numberMinus++;
                        if (matchList.get(i).homeCorners == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners > value)
                            numberPlus++;
                        if (matchList.get(i).awayCorners < value)
                            numberMinus++;
                        if (matchList.get(i).awayCorners == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Угловые", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners > value)
                            numberPlus++;
                        if (matchList.get(i).homeCorners < value)
                            numberMinus++;
                        if (matchList.get(i).homeCorners == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners > value)
                            numberPlus++;
                        if (matchList.get(i).awayCorners < value)
                            numberMinus++;
                        if (matchList.get(i).awayCorners == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые_Фора команды":{
                matchList = deleteEmptyMatches("Угловые", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners - matchList.get(i).awayCorners + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeCorners - matchList.get(i).awayCorners + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeCorners - matchList.get(i).awayCorners + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners - matchList.get(i).homeCorners + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayCorners - matchList.get(i).homeCorners + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayCorners - matchList.get(i).homeCorners + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Угловые 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeCorners1T + matchList.get(i).awayCorners1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeCorners1T + matchList.get(i).awayCorners1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeCorners1T + matchList.get(i).awayCorners1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Угловые 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeCorners1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeCorners1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayCorners1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayCorners1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Угловые 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeCorners1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeCorners1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayCorners1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayCorners1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Угловые 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners1T - matchList.get(i).awayCorners1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeCorners1T - matchList.get(i).awayCorners1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeCorners1T - matchList.get(i).awayCorners1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners1T - matchList.get(i).homeCorners1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayCorners1T - matchList.get(i).homeCorners1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayCorners1T - matchList.get(i).homeCorners1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Угловые 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeCorners2T + matchList.get(i).awayCorners2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeCorners2T + matchList.get(i).awayCorners2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeCorners2T + matchList.get(i).awayCorners2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Угловые 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeCorners2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeCorners2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayCorners2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayCorners2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Угловые 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeCorners2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeCorners2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayCorners2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayCorners2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Угловые 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Угловые 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeCorners2T - matchList.get(i).awayCorners2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeCorners2T - matchList.get(i).awayCorners2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeCorners2T - matchList.get(i).awayCorners2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayCorners2T - matchList.get(i).homeCorners2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayCorners2T - matchList.get(i).homeCorners2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayCorners2T - matchList.get(i).homeCorners2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды_Общий тотал":{
                matchList = deleteEmptyMatches("Офсайды", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeOffsides + matchList.get(i).awayOffsides > value)
                        numberPlus++;
                    if (matchList.get(i).homeOffsides + matchList.get(i).awayOffsides < value)
                        numberMinus++;
                    if (matchList.get(i).homeOffsides + matchList.get(i).awayOffsides == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Офсайды", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides > value)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides < value)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides > value)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides < value)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Офсайды", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides > value)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides < value)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides > value)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides < value)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды_Фора команды":{
                matchList = deleteEmptyMatches("Офсайды", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides - matchList.get(i).awayOffsides + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides - matchList.get(i).awayOffsides + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides - matchList.get(i).awayOffsides + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides - matchList.get(i).homeOffsides + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides - matchList.get(i).homeOffsides + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides - matchList.get(i).homeOffsides + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Офсайды 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeOffsides1T + matchList.get(i).awayOffsides1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeOffsides1T + matchList.get(i).awayOffsides1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeOffsides1T + matchList.get(i).awayOffsides1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Офсайды 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Офсайды 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Офсайды 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides1T - matchList.get(i).awayOffsides1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides1T - matchList.get(i).awayOffsides1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides1T - matchList.get(i).awayOffsides1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides1T - matchList.get(i).homeOffsides1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides1T - matchList.get(i).homeOffsides1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides1T - matchList.get(i).homeOffsides1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Офсайды 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeOffsides2T + matchList.get(i).awayOffsides2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeOffsides2T + matchList.get(i).awayOffsides2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeOffsides2T + matchList.get(i).awayOffsides2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Офсайды 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Офсайды 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Офсайды 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Офсайды 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeOffsides2T - matchList.get(i).awayOffsides2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeOffsides2T - matchList.get(i).awayOffsides2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeOffsides2T - matchList.get(i).awayOffsides2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayOffsides2T - matchList.get(i).homeOffsides2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayOffsides2T - matchList.get(i).homeOffsides2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayOffsides2T - matchList.get(i).homeOffsides2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы_Общий тотал":{
                matchList = deleteEmptyMatches("Фолы", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeFouls + matchList.get(i).awayFouls > value)
                        numberPlus++;
                    if (matchList.get(i).homeFouls + matchList.get(i).awayFouls < value)
                        numberMinus++;
                    if (matchList.get(i).homeFouls + matchList.get(i).awayFouls == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Фолы", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls > value)
                            numberPlus++;
                        if (matchList.get(i).homeFouls < value)
                            numberMinus++;
                        if (matchList.get(i).homeFouls == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls > value)
                            numberPlus++;
                        if (matchList.get(i).awayFouls < value)
                            numberMinus++;
                        if (matchList.get(i).awayFouls == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Фолы", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls > value)
                            numberPlus++;
                        if (matchList.get(i).homeFouls < value)
                            numberMinus++;
                        if (matchList.get(i).homeFouls == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls > value)
                            numberPlus++;
                        if (matchList.get(i).awayFouls < value)
                            numberMinus++;
                        if (matchList.get(i).awayFouls == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы_Фора команды":{
                matchList = deleteEmptyMatches("Фолы", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls - matchList.get(i).awayFouls + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeFouls - matchList.get(i).awayFouls + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeFouls - matchList.get(i).awayFouls + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls - matchList.get(i).homeFouls + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayFouls - matchList.get(i).homeFouls + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayFouls - matchList.get(i).homeFouls + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Фолы 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeFouls1T + matchList.get(i).awayFouls1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeFouls1T + matchList.get(i).awayFouls1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeFouls1T + matchList.get(i).awayFouls1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Фолы 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeFouls1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeFouls1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayFouls1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayFouls1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Фолы 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeFouls1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeFouls1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayFouls1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayFouls1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Фолы 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls1T - matchList.get(i).awayFouls1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeFouls1T - matchList.get(i).awayFouls1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeFouls1T - matchList.get(i).awayFouls1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls1T - matchList.get(i).homeFouls1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayFouls1T - matchList.get(i).homeFouls1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayFouls1T - matchList.get(i).homeFouls1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Фолы 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeFouls2T + matchList.get(i).awayFouls2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeFouls2T + matchList.get(i).awayFouls2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeFouls2T + matchList.get(i).awayFouls2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Фолы 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeFouls2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeFouls2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayFouls2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayFouls2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Фолы 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeFouls2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeFouls2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayFouls2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayFouls2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Фолы 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Фолы 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeFouls2T - matchList.get(i).awayFouls2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeFouls2T - matchList.get(i).awayFouls2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeFouls2T - matchList.get(i).awayFouls2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayFouls2T - matchList.get(i).homeFouls2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayFouls2T - matchList.get(i).homeFouls2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayFouls2T - matchList.get(i).homeFouls2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Желтые карточки_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeYellowCards + matchList.get(i).awayYellowCards > value)
                        numberPlus++;
                    if (matchList.get(i).homeYellowCards + matchList.get(i).awayYellowCards < value)
                        numberMinus++;
                    if (matchList.get(i).homeYellowCards + matchList.get(i).awayYellowCards == value)
                        numberEqual++;
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards > value)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards < value)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards > value)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards < value)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards > value)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards < value)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards > value)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards < value)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки_Фора команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards - matchList.get(i).awayYellowCards + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards - matchList.get(i).awayYellowCards + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards - matchList.get(i).awayYellowCards + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards - matchList.get(i).homeYellowCards + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards - matchList.get(i).homeYellowCards + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards - matchList.get(i).homeYellowCards + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
                break;
            }
            case "Желтые карточки 1 тайм_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeYellowCards1T + matchList.get(i).awayYellowCards1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeYellowCards1T + matchList.get(i).awayYellowCards1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeYellowCards1T + matchList.get(i).awayYellowCards1T == value)
                        numberEqual++;
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки 1 тайм_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards1T == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки 1 тайм_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards1T == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки 1 тайм_Фора команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards1T - matchList.get(i).awayYellowCards1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards1T - matchList.get(i).awayYellowCards1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards1T - matchList.get(i).awayYellowCards1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards1T - matchList.get(i).homeYellowCards1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards1T - matchList.get(i).homeYellowCards1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards1T - matchList.get(i).homeYellowCards1T + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
                break;
            }
            case "Желтые карточки 2 тайм_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeYellowCards2T + matchList.get(i).awayYellowCards2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeYellowCards2T + matchList.get(i).awayYellowCards2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeYellowCards2T + matchList.get(i).awayYellowCards2T == value)
                        numberEqual++;
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки 2 тайм_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards2T == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки 2 тайм_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards2T == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Желтые карточки 2 тайм_Фора команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeYellowCards2T - matchList.get(i).awayYellowCards2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeYellowCards2T - matchList.get(i).awayYellowCards2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeYellowCards2T - matchList.get(i).awayYellowCards2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayYellowCards2T - matchList.get(i).homeYellowCards2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayYellowCards2T - matchList.get(i).homeYellowCards2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayYellowCards2T - matchList.get(i).homeYellowCards2T + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
                break;
            }
            case "Красные карточки_Общий тотал":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeRedCards + matchList.get(i).awayRedCards > value)
                        numberPlus++;
                    if (matchList.get(i).homeRedCards + matchList.get(i).awayRedCards < value)
                        numberMinus++;
                    if (matchList.get(i).homeRedCards + matchList.get(i).awayRedCards == value)
                        numberEqual++;
                }
                typeOfTable = 1;
                break;
            }
            case "Красные карточки_Инд.тотал команды":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeRedCards > value)
                            numberPlus++;
                        if (matchList.get(i).homeRedCards < value)
                            numberMinus++;
                        if (matchList.get(i).homeRedCards == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayRedCards > value)
                            numberPlus++;
                        if (matchList.get(i).awayRedCards < value)
                            numberMinus++;
                        if (matchList.get(i).awayRedCards == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Красные карточки_Инд.тотал соперника":{
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeRedCards > value)
                            numberPlus++;
                        if (matchList.get(i).homeRedCards < value)
                            numberMinus++;
                        if (matchList.get(i).homeRedCards == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayRedCards > value)
                            numberPlus++;
                        if (matchList.get(i).awayRedCards < value)
                            numberMinus++;
                        if (matchList.get(i).awayRedCards == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
                break;
            }
            case "Дриблинг_Общий тотал":{
                matchList = deleteEmptyMatches("Дриблинг", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeDribbles + matchList.get(i).awayDribbles > value)
                        numberPlus++;
                    if (matchList.get(i).homeDribbles + matchList.get(i).awayDribbles < value)
                        numberMinus++;
                    if (matchList.get(i).homeDribbles + matchList.get(i).awayDribbles == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Дриблинг_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Дриблинг", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeDribbles > value)
                            numberPlus++;
                        if (matchList.get(i).homeDribbles < value)
                            numberMinus++;
                        if (matchList.get(i).homeDribbles == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayDribbles > value)
                            numberPlus++;
                        if (matchList.get(i).awayDribbles < value)
                            numberMinus++;
                        if (matchList.get(i).awayDribbles == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Дриблинг_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Дриблинг", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeDribbles > value)
                            numberPlus++;
                        if (matchList.get(i).homeDribbles < value)
                            numberMinus++;
                        if (matchList.get(i).homeDribbles == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayDribbles > value)
                            numberPlus++;
                        if (matchList.get(i).awayDribbles < value)
                            numberMinus++;
                        if (matchList.get(i).awayDribbles == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Дриблинг_Фора команды":{
                matchList = deleteEmptyMatches("Дриблинг", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeDribbles - matchList.get(i).awayDribbles + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeDribbles - matchList.get(i).awayDribbles + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeDribbles - matchList.get(i).awayDribbles + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayDribbles - matchList.get(i).homeDribbles + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayDribbles - matchList.get(i).homeDribbles + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayDribbles - matchList.get(i).homeDribbles + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Воздушные единоборства_Общий тотал":{
                matchList = deleteEmptyMatches("Воздушные единоборства", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeAerialsWon + matchList.get(i).awayAerialsWon > value)
                        numberPlus++;
                    if (matchList.get(i).homeAerialsWon + matchList.get(i).awayAerialsWon < value)
                        numberMinus++;
                    if (matchList.get(i).homeAerialsWon + matchList.get(i).awayAerialsWon == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Воздушные единоборства_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Воздушные единоборства", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeAerialsWon > value)
                            numberPlus++;
                        if (matchList.get(i).homeAerialsWon < value)
                            numberMinus++;
                        if (matchList.get(i).homeAerialsWon == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayAerialsWon > value)
                            numberPlus++;
                        if (matchList.get(i).awayAerialsWon < value)
                            numberMinus++;
                        if (matchList.get(i).awayAerialsWon == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Воздушные единоборства_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Воздушные единоборства", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeAerialsWon > value)
                            numberPlus++;
                        if (matchList.get(i).homeAerialsWon < value)
                            numberMinus++;
                        if (matchList.get(i).homeAerialsWon == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayAerialsWon > value)
                            numberPlus++;
                        if (matchList.get(i).awayAerialsWon < value)
                            numberMinus++;
                        if (matchList.get(i).awayAerialsWon == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Воздушные единоборства_Фора команды":{
                matchList = deleteEmptyMatches("Воздушные единоборства", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeAerialsWon - matchList.get(i).awayAerialsWon + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeAerialsWon - matchList.get(i).awayAerialsWon + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeAerialsWon - matchList.get(i).awayAerialsWon + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayAerialsWon - matchList.get(i).homeAerialsWon + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayAerialsWon - matchList.get(i).homeAerialsWon + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayAerialsWon - matchList.get(i).homeAerialsWon + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот_Общий тотал":{
                matchList = deleteEmptyMatches("Удары от ворот", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeGoalKicks + matchList.get(i).awayGoalKicks > value)
                        numberPlus++;
                    if (matchList.get(i).homeGoalKicks + matchList.get(i).awayGoalKicks < value)
                        numberMinus++;
                    if (matchList.get(i).homeGoalKicks + matchList.get(i).awayGoalKicks == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары от ворот", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks > value)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks < value)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks > value)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks < value)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары от ворот", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks > value)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks < value)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks > value)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks < value)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот_Фора команды":{
                matchList = deleteEmptyMatches("Удары от ворот", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks - matchList.get(i).awayGoalKicks + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks - matchList.get(i).awayGoalKicks + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks - matchList.get(i).awayGoalKicks + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks - matchList.get(i).homeGoalKicks + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks - matchList.get(i).homeGoalKicks + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks - matchList.get(i).homeGoalKicks + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }

            case "Удары от ворот 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Удары от ворот 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeGoalKicks1T + matchList.get(i).awayGoalKicks1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeGoalKicks1T + matchList.get(i).awayGoalKicks1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeGoalKicks1T + matchList.get(i).awayGoalKicks1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары от ворот 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары от ворот 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Удары от ворот 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks1T - matchList.get(i).awayGoalKicks1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks1T - matchList.get(i).awayGoalKicks1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks1T - matchList.get(i).awayGoalKicks1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks1T - matchList.get(i).homeGoalKicks1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks1T - matchList.get(i).homeGoalKicks1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks1T - matchList.get(i).homeGoalKicks1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Удары от ворот 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeGoalKicks2T + matchList.get(i).awayGoalKicks2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeGoalKicks2T + matchList.get(i).awayGoalKicks2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeGoalKicks2T + matchList.get(i).awayGoalKicks2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Удары от ворот 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Удары от ворот 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Удары от ворот 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Удары от ворот 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeGoalKicks2T - matchList.get(i).awayGoalKicks2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeGoalKicks2T - matchList.get(i).awayGoalKicks2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeGoalKicks2T - matchList.get(i).awayGoalKicks2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayGoalKicks2T - matchList.get(i).homeGoalKicks2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayGoalKicks2T - matchList.get(i).homeGoalKicks2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayGoalKicks2T - matchList.get(i).homeGoalKicks2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов_Общий тотал":{
                matchList = deleteEmptyMatches("Вброс аутов", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeThrowIns + matchList.get(i).awayThrowIns > value)
                        numberPlus++;
                    if (matchList.get(i).homeThrowIns + matchList.get(i).awayThrowIns < value)
                        numberMinus++;
                    if (matchList.get(i).homeThrowIns + matchList.get(i).awayThrowIns == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Вброс аутов", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns > value)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns < value)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns > value)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns < value)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Вброс аутов", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns > value)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns < value)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns > value)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns < value)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов_Фора команды":{
                matchList = deleteEmptyMatches("Вброс аутов", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns - matchList.get(i).awayThrowIns + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns - matchList.get(i).awayThrowIns + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns - matchList.get(i).awayThrowIns + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns - matchList.get(i).homeThrowIns + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns - matchList.get(i).homeThrowIns + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns - matchList.get(i).homeThrowIns + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 1 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Вброс аутов 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeThrowIns1T + matchList.get(i).awayThrowIns1T > value)
                        numberPlus++;
                    if (matchList.get(i).homeThrowIns1T + matchList.get(i).awayThrowIns1T < value)
                        numberMinus++;
                    if (matchList.get(i).homeThrowIns1T + matchList.get(i).awayThrowIns1T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 1 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Вброс аутов 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 1 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Вброс аутов 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns1T > value)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns1T < value)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns1T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns1T > value)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns1T < value)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns1T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 1 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Вброс аутов 1 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns1T - matchList.get(i).awayThrowIns1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns1T - matchList.get(i).awayThrowIns1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns1T - matchList.get(i).awayThrowIns1T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns1T - matchList.get(i).homeThrowIns1T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns1T - matchList.get(i).homeThrowIns1T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns1T - matchList.get(i).homeThrowIns1T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }

            case "Вброс аутов 2 тайм_Общий тотал":{
                matchList = deleteEmptyMatches("Вброс аутов 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeThrowIns2T + matchList.get(i).awayThrowIns2T > value)
                        numberPlus++;
                    if (matchList.get(i).homeThrowIns2T + matchList.get(i).awayThrowIns2T < value)
                        numberMinus++;
                    if (matchList.get(i).homeThrowIns2T + matchList.get(i).awayThrowIns2T == value)
                        numberEqual++;
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 2 тайм_Инд.тотал команды":{
                matchList = deleteEmptyMatches("Вброс аутов 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 2 тайм_Инд.тотал соперника":{
                matchList = deleteEmptyMatches("Вброс аутов 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns2T > value)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns2T < value)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns2T == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns2T > value)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns2T < value)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns2T == value)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 1;
                } else {
                    typeOfTable = 3;
                }
                break;
            }
            case "Вброс аутов 2 тайм_Фора команды":{
                matchList = deleteEmptyMatches("Вброс аутов 2 тайм", matchList);
                numberOfMatches = matchList.size();
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeThrowIns2T - matchList.get(i).awayThrowIns2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeThrowIns2T - matchList.get(i).awayThrowIns2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeThrowIns2T - matchList.get(i).awayThrowIns2T + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayThrowIns2T - matchList.get(i).homeThrowIns2T + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayThrowIns2T - matchList.get(i).homeThrowIns2T + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayThrowIns2T - matchList.get(i).homeThrowIns2T + value == 0)
                            numberEqual++;
                    }
                }
                if (numberOfMatches > 0){
                    typeOfTable = 2;
                } else {
                    typeOfTable = 3;
                }
                break;
            }


            case "":{

                break;
            }
        }

        morePercent =  MyMath.round(100 * (double) numberPlus / numberOfMatches, 2);
        lessPercent =  MyMath.round(100 * (double) numberMinus / numberOfMatches, 2);
        equalPercent = MyMath.round(100 * (double) numberEqual / numberOfMatches, 2);

        String[] heads = new String[0];
        Object[][] data = new Object[0][];
        if (typeOfTable == 1){
            heads = new String[]{"Больше", "Меньше", "Ровно", "% больше", "% меньше", "% ровно"};
            data = new Object[][]{
                    {String.valueOf(numberPlus), String.valueOf(numberMinus), String.valueOf(numberEqual), String.valueOf(morePercent), String.valueOf(lessPercent), String.valueOf(equalPercent)}
            };
        }
        if (typeOfTable == 2){
            heads = new String[]{"Прошла", "Не прошла", "Возврат", "% прохода", "% непрохода", "% возврата"};
            data = new Object[][]{
                    {String.valueOf(numberPlus), String.valueOf(numberMinus), String.valueOf(numberEqual), String.valueOf(morePercent), String.valueOf(lessPercent), String.valueOf(equalPercent)}
            };
        }
        if (typeOfTable == 3){
            heads = new String[]{"Данных по параметру " + parameter + " нет."};
            data = new Object[][]{{""}
            };
        }
        return new JTable(data, heads);
    }

    public static ArrayList<Match> deleteEmptyMatches(String parameter, ArrayList<Match> matchList){
        ArrayList<Match> result = (ArrayList<Match>) matchList.clone();

        for (int i=0; i<result.size(); i++){
            switch (parameter){
                case "Владение":{
                    if (result.get(i).homeBallPossession + result.get(i).awayBallPossession == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Удары":
                case "Удары 1 тайм":
                case "Удары 2 тайм":
                {
                    if (result.get(i).homeShots + result.get(i).awayShots == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Удары в створ":
                case "Удары в створ 1 тайм":
                case "Удары в створ 2 тайм":
                {
                    if (result.get(i).homeShotsOnTarget + result.get(i).awayShotsOnTarget == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Угловые в створ":
                case "Угловые в створ 1 тайм":
                case "Угловые в створ 2 тайм":
                {
                    if (result.get(i).homeCorners + result.get(i).awayCorners == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Офсайды в створ":
                case "Офсайды в створ 1 тайм":
                case "Офсайды в створ 2 тайм":
                {
                    if (result.get(i).homeOffsides + result.get(i).awayOffsides == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Фолы в створ":
                case "Фолы в створ 1 тайм":
                case "Фолы в створ 2 тайм":
                {
                    if (result.get(i).homeFouls + result.get(i).awayFouls == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Удары от ворот":
                case "Удары от ворот 1 тайм":
                case "Удары от ворот 2 тайм":
                {
                    if (result.get(i).homeGoalKicks + result.get(i).awayGoalKicks == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Вброс аутов":
                case "Вброс аутов 1 тайм":
                case "Вброс аутов 2 тайм":
                {
                    if (result.get(i).homeThrowIns + result.get(i).awayThrowIns == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Дриблинг":
                {
                    if (result.get(i).homeDribbles + result.get(i).awayDribbles == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }
                case "Воздушные единоборства":
                {
                    if (result.get(i).homeAerialsWon + result.get(i).awayAerialsWon == 0){
                        result.remove(i);
                        i--;
                    }
                    break;
                }

                case "":{

                    break;
                }
            }
        }

        return  result;
    }
}
