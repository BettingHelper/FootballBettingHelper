package sample;

import java.awt.*;
import java.util.ArrayList;

public class TrendMaker {
    Settings settings = Settings.getSettingsFromFile();
    int percentBorder = Integer.parseInt(settings.trendPercent);
    ArrayList<String> listOfTrends = new ArrayList<>();
    ArrayList<String> resultList = new ArrayList<>();
    double tb1T = 1.5;
    double tm1T = 1;
    double tb2T = 1.5;
    double tm2T = 2;
    double tbCorners = 9.5;
    double tmCorners = 9.5;
    double itmCorners = 4.5;
    double itbCorners = 5.5;
    double tbCornersTime = 5.5;
    double tmCornersTime = 4.5;
    double itmCornersTime = 2.5;
    double itbCornersTime = 3.5;
    double tbYC = 3.5;
    double tmYC = 3.5;
    double itmYC = 2.5;
    double itbYC = 2.5;
    double tmOfs = 3.5;
    double tbOfs = 4.5;
    double itmOfs = 2.5;
    double itbOfs = 3.5;
    double tmSOT = 8.5;
    double tbSOT = 9.5;
    double itmSOT = 3.5;
    double itbSOT = 4.5;
    double tmFouls = 25.5;
    double tbFouls = 30.5;
    double itmFouls = 12.5;
    double itbFouls = 15.5;

    public TrendMaker(String parameter, String league){

        if (Settings.isWhoScoredLeague(league)){
            switch (parameter){
                case "all":{
                    this.listOfTrends.add("ТМ(2.5)");
                    this.listOfTrends.add("ТБ(2.5)");
                    this.listOfTrends.add("Обе забьют - ДА");
                    this.listOfTrends.add("Обе забьют - НЕТ");
                    this.listOfTrends.add("1тайм: ТБ");
                    this.listOfTrends.add("1тайм: ТМ");
                    this.listOfTrends.add("1тайм: Фора команды");
                    this.listOfTrends.add("1тайм: Фора соперника");
                    this.listOfTrends.add("2тайм: ТБ");
                    this.listOfTrends.add("2тайм: ТМ");
                    this.listOfTrends.add("2тайм: Фора команды");
                    this.listOfTrends.add("2тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ: ТМ");
                    this.listOfTrends.add("УГЛ: ТБ");
                    this.listOfTrends.add("УГЛ: Фора команды");
                    this.listOfTrends.add("УГЛ: Фора соперника");
                    this.listOfTrends.add("УГЛ: ИТБ команды");
                    this.listOfTrends.add("УГЛ: ИТМ команды");
                    this.listOfTrends.add("УГЛ: ИТБ соперника");
                    this.listOfTrends.add("УГЛ: ИТМ соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ТМ");
                    this.listOfTrends.add("УГЛ 1тайм: ТБ");
                    this.listOfTrends.add("УГЛ 1тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 1тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ТМ");
                    this.listOfTrends.add("УГЛ 2тайм: ТБ");
                    this.listOfTrends.add("УГЛ 2тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 2тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ соперника");
                    this.listOfTrends.add("ЖК: ТМ");
                    this.listOfTrends.add("ЖК: ТБ");
                    this.listOfTrends.add("ЖК: Фора команды");
                    this.listOfTrends.add("ЖК: Фора соперника");
                    this.listOfTrends.add("ЖК: ИТБ команды");
                    this.listOfTrends.add("ЖК: ИТМ команды");
                    this.listOfTrends.add("ЖК: ИТБ соперника");
                    this.listOfTrends.add("ЖК: ИТМ соперника");
                    this.listOfTrends.add("ОФС: ТМ");
                    this.listOfTrends.add("ОФС: ТБ");
                    this.listOfTrends.add("ОФС: Фора команды");
                    this.listOfTrends.add("ОФС: Фора соперника");
                    this.listOfTrends.add("ОФС: ИТБ команды");
                    this.listOfTrends.add("ОФС: ИТМ команды");
                    this.listOfTrends.add("ОФС: ИТБ соперника");
                    this.listOfTrends.add("ОФС: ИТМ соперника");
                    this.listOfTrends.add("УВС: ТМ");
                    this.listOfTrends.add("УВС: ТБ");
                    this.listOfTrends.add("УВС: Фора команды");
                    this.listOfTrends.add("УВС: Фора соперника");
                    this.listOfTrends.add("УВС: ИТБ команды");
                    this.listOfTrends.add("УВС: ИТМ команды");
                    this.listOfTrends.add("УВС: ИТБ соперника");
                    this.listOfTrends.add("УВС: ИТМ соперника");
                    this.listOfTrends.add("ФОЛЫ: ТМ");
                    this.listOfTrends.add("ФОЛЫ: ТБ");
                    this.listOfTrends.add("ФОЛЫ: Фора команды");
                    this.listOfTrends.add("ФОЛЫ: Фора соперника");
                    this.listOfTrends.add("ФОЛЫ: ИТБ команды");
                    this.listOfTrends.add("ФОЛЫ: ИТМ команды");
                    this.listOfTrends.add("ФОЛЫ: ИТБ соперника");
                    this.listOfTrends.add("ФОЛЫ: ИТМ соперника");
                    break;
                }
                case "Голы":{
                    this.listOfTrends.add("ТМ(2.5)");
                    this.listOfTrends.add("ТБ(2.5)");
                    this.listOfTrends.add("Обе забьют - ДА");
                    this.listOfTrends.add("Обе забьют - НЕТ");
                    break;
                }
                case "Голы в 1-ом тайме":{
                    this.listOfTrends.add("1тайм: ТБ");
                    this.listOfTrends.add("1тайм: ТМ");
                    this.listOfTrends.add("1тайм: Фора команды");
                    this.listOfTrends.add("1тайм: Фора соперника");
                    break;
                }
                case "Голы во 2-ом тайме":{
                    this.listOfTrends.add("2тайм: ТБ");
                    this.listOfTrends.add("2тайм: ТМ");
                    this.listOfTrends.add("2тайм: Фора команды");
                    this.listOfTrends.add("2тайм: Фора соперника");
                    break;
                }
                case "Желтые карточки":{
                    this.listOfTrends.add("ЖК: ТМ");
                    this.listOfTrends.add("ЖК: ТБ");
                    this.listOfTrends.add("ЖК: Фора команды");
                    this.listOfTrends.add("ЖК: Фора соперника");
                    this.listOfTrends.add("ЖК: ИТБ команды");
                    this.listOfTrends.add("ЖК: ИТМ команды");
                    this.listOfTrends.add("ЖК: ИТБ соперника");
                    this.listOfTrends.add("ЖК: ИТМ соперника");
                    break;
                }
                case "Угловые":{
                    this.listOfTrends.add("УГЛ: ТМ");
                    this.listOfTrends.add("УГЛ: ТБ");
                    this.listOfTrends.add("УГЛ: Фора команды");
                    this.listOfTrends.add("УГЛ: Фора соперника");
                    this.listOfTrends.add("УГЛ: ИТБ команды");
                    this.listOfTrends.add("УГЛ: ИТМ команды");
                    this.listOfTrends.add("УГЛ: ИТБ соперника");
                    this.listOfTrends.add("УГЛ: ИТМ соперника");
                    break;
                }
                case "Угловые в 1-ом тайме":{
                    this.listOfTrends.add("УГЛ 1тайм: ТМ");
                    this.listOfTrends.add("УГЛ 1тайм: ТБ");
                    this.listOfTrends.add("УГЛ 1тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 1тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ соперника");
                    break;
                }
                case "Угловые во 2-ом тайме":{
                    this.listOfTrends.add("УГЛ 2тайм: ТМ");
                    this.listOfTrends.add("УГЛ 2тайм: ТБ");
                    this.listOfTrends.add("УГЛ 2тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 2тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ соперника");
                    break;
                }
                case "Удары в створ":{
                    this.listOfTrends.add("УВС: ТМ");
                    this.listOfTrends.add("УВС: ТБ");
                    this.listOfTrends.add("УВС: Фора команды");
                    this.listOfTrends.add("УВС: Фора соперника");
                    this.listOfTrends.add("УВС: ИТБ команды");
                    this.listOfTrends.add("УВС: ИТМ команды");
                    this.listOfTrends.add("УВС: ИТБ соперника");
                    this.listOfTrends.add("УВС: ИТМ соперника");
                    break;
                }
                case "Офсайды":{
                    this.listOfTrends.add("ОФС: ТМ");
                    this.listOfTrends.add("ОФС: ТБ");
                    this.listOfTrends.add("ОФС: Фора команды");
                    this.listOfTrends.add("ОФС: Фора соперника");
                    this.listOfTrends.add("ОФС: ИТБ команды");
                    this.listOfTrends.add("ОФС: ИТМ команды");
                    this.listOfTrends.add("ОФС: ИТБ соперника");
                    this.listOfTrends.add("ОФС: ИТМ соперника");
                    break;
                }
                case "Фолы":{
                    this.listOfTrends.add("ФОЛЫ: ТМ");
                    this.listOfTrends.add("ФОЛЫ: ТБ");
                    this.listOfTrends.add("ФОЛЫ: Фора команды");
                    this.listOfTrends.add("ФОЛЫ: Фора соперника");
                    this.listOfTrends.add("ФОЛЫ: ИТБ команды");
                    this.listOfTrends.add("ФОЛЫ: ИТМ команды");
                    this.listOfTrends.add("ФОЛЫ: ИТБ соперника");
                    this.listOfTrends.add("ФОЛЫ: ИТМ соперника");
                    break;
                }

            }
        } else {
            switch (parameter){
                case "all":{
                    this.listOfTrends.add("ТМ(2.5)");
                    this.listOfTrends.add("ТБ(2.5)");
                    this.listOfTrends.add("Обе забьют - ДА");
                    this.listOfTrends.add("Обе забьют - НЕТ");
                    this.listOfTrends.add("1тайм: ТБ");
                    this.listOfTrends.add("1тайм: ТМ");
                    this.listOfTrends.add("1тайм: Фора команды");
                    this.listOfTrends.add("1тайм: Фора соперника");
                    this.listOfTrends.add("2тайм: ТБ");
                    this.listOfTrends.add("2тайм: ТМ");
                    this.listOfTrends.add("2тайм: Фора команды");
                    this.listOfTrends.add("2тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ: ТМ");
                    this.listOfTrends.add("УГЛ: ТБ");
                    this.listOfTrends.add("УГЛ: Фора команды");
                    this.listOfTrends.add("УГЛ: Фора соперника");
                    this.listOfTrends.add("УГЛ: ИТБ команды");
                    this.listOfTrends.add("УГЛ: ИТМ команды");
                    this.listOfTrends.add("УГЛ: ИТБ соперника");
                    this.listOfTrends.add("УГЛ: ИТМ соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ТМ");
                    this.listOfTrends.add("УГЛ 1тайм: ТБ");
                    this.listOfTrends.add("УГЛ 1тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 1тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ТМ");
                    this.listOfTrends.add("УГЛ 2тайм: ТБ");
                    this.listOfTrends.add("УГЛ 2тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 2тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ соперника");
                    this.listOfTrends.add("ЖК: ТМ");
                    this.listOfTrends.add("ЖК: ТБ");
                    this.listOfTrends.add("ЖК: Фора команды");
                    this.listOfTrends.add("ЖК: Фора соперника");
                    this.listOfTrends.add("ЖК: ИТБ команды");
                    this.listOfTrends.add("ЖК: ИТМ команды");
                    this.listOfTrends.add("ЖК: ИТБ соперника");
                    this.listOfTrends.add("ЖК: ИТМ соперника");
                    break;
                }
                case "Голы":{
                    this.listOfTrends.add("ТМ(2.5)");
                    this.listOfTrends.add("ТБ(2.5)");
                    this.listOfTrends.add("Обе забьют - ДА");
                    this.listOfTrends.add("Обе забьют - НЕТ");
                    break;
                }
                case "Голы в 1-ом тайме":{
                    this.listOfTrends.add("1тайм: ТБ");
                    this.listOfTrends.add("1тайм: ТМ");
                    this.listOfTrends.add("1тайм: Фора команды");
                    this.listOfTrends.add("1тайм: Фора соперника");
                    break;
                }
                case "Голы во 2-ом тайме":{
                    this.listOfTrends.add("2тайм: ТБ");
                    this.listOfTrends.add("2тайм: ТМ");
                    this.listOfTrends.add("2тайм: Фора команды");
                    this.listOfTrends.add("2тайм: Фора соперника");
                    break;
                }
                case "Желтые карточки":{
                    this.listOfTrends.add("ЖК: ТМ");
                    this.listOfTrends.add("ЖК: ТБ");
                    this.listOfTrends.add("ЖК: Фора команды");
                    this.listOfTrends.add("ЖК: Фора соперника");
                    this.listOfTrends.add("ЖК: ИТБ команды");
                    this.listOfTrends.add("ЖК: ИТМ команды");
                    this.listOfTrends.add("ЖК: ИТБ соперника");
                    this.listOfTrends.add("ЖК: ИТМ соперника");
                    break;
                }
                case "Угловые":{
                    this.listOfTrends.add("УГЛ: ТМ");
                    this.listOfTrends.add("УГЛ: ТБ");
                    this.listOfTrends.add("УГЛ: Фора команды");
                    this.listOfTrends.add("УГЛ: Фора соперника");
                    this.listOfTrends.add("УГЛ: ИТБ команды");
                    this.listOfTrends.add("УГЛ: ИТМ команды");
                    this.listOfTrends.add("УГЛ: ИТБ соперника");
                    this.listOfTrends.add("УГЛ: ИТМ соперника");
                    break;
                }
                case "Угловые в 1-ом тайме":{
                    this.listOfTrends.add("УГЛ 1тайм: ТМ");
                    this.listOfTrends.add("УГЛ 1тайм: ТБ");
                    this.listOfTrends.add("УГЛ 1тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 1тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 1тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 1тайм: ИТМ соперника");
                    break;
                }
                case "Угловые во 2-ом тайме":{
                    this.listOfTrends.add("УГЛ 2тайм: ТМ");
                    this.listOfTrends.add("УГЛ 2тайм: ТБ");
                    this.listOfTrends.add("УГЛ 2тайм: Фора команды");
                    this.listOfTrends.add("УГЛ 2тайм: Фора соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ команды");
                    this.listOfTrends.add("УГЛ 2тайм: ИТБ соперника");
                    this.listOfTrends.add("УГЛ 2тайм: ИТМ соперника");
                    break;
                }

            }
        }


        switch (league){
            case "England":{
                tmYC = 2.5;
                tbYC = 3.5;
                itbYC = 2.5;
                itmYC = 1.5;
                tmCorners = 9.5;
                tbCorners = 10.5;
                tmFouls = 18.5;
                tbFouls = 23.5;
                itmFouls = 11.5;
                itbFouls = 12.5;
                break;
            }
            case "Germany":{
                tmYC = 2.5;
                tbYC = 3.5;
                itbYC = 2.5;
                itmYC = 1.5;
                tmFouls = 18.5;
                tbFouls = 23.5;
                itmFouls = 11.5;
                itbFouls = 12.5;
                break;
            }
            case "France":{
                tmSOT = 8.5;
                tbSOT = 8.5;
                itmSOT = 3.5;
                itbSOT = 4.5;
                break;
            }
        }
    }

    public void analyzeTrends(String team, Selector selector){
        resultList = new ArrayList<>();
        for (int i=0; i<listOfTrends.size(); i++){
            int percent;
            int success = 0;
            switch (listOfTrends.get(i)){
                case "ТМ(2.5)":{
                    for (int j=0; j<selector.listOfMatches.size(); j++){
                        if (selector.listOfMatches.get(j).homeScore + selector.listOfMatches.get(j).awayScore < 2.5)
                            success++;
                    }
                    percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                    if (percent >= percentBorder){
                        resultList.add("ТМ(2.5): " + success + " матчей из " + selector.listOfMatches.size() + " (" + percent + "%)");
                    }
                    break;
                }
                case "ТБ(2.5)":{
                    for (int j=0; j<selector.listOfMatches.size(); j++){
                        if (selector.listOfMatches.get(j).homeScore + selector.listOfMatches.get(j).awayScore > 2.5)
                            success++;
                    }
                    percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                    if (percent >= percentBorder){
                        resultList.add("ТБ(2.5): " + success + " матчей из " + selector.listOfMatches.size() + " (" + percent + "%)");
                    }
                    break;
                }
                case "Обе забьют - ДА":{
                    for (int j=0; j<selector.listOfMatches.size(); j++){
                        if (selector.listOfMatches.get(j).homeScore * selector.listOfMatches.get(j).awayScore > 0)
                            success++;
                    }
                    percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                    if (percent >= percentBorder){
                        resultList.add("Обе забьют - ДА: " + success + " матчей из " + selector.listOfMatches.size() + " (" + percent + "%)");
                    }
                    break;
                }
                case "Обе забьют - НЕТ":{
                    for (int j=0; j<selector.listOfMatches.size(); j++){
                        if (selector.listOfMatches.get(j).homeScore * selector.listOfMatches.get(j).awayScore == 0)
                            success++;
                    }
                    percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                    if (percent >= percentBorder){
                        resultList.add("Обе забьют - НЕТ: " + success + " матчей из " + selector.listOfMatches.size() + " (" + percent + "%)");
                    }
                    break;
                }
                case "1тайм: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tb1T;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeScore1T + selector.listOfMatches.get(j).awayScore1T == matchValue)
                                vozvrat++;
                            if (selector.listOfMatches.get(j).homeScore1T + selector.listOfMatches.get(j).awayScore1T > matchValue)
                                plus++;

                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        matchValue += 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultTotal);
                        if (Math.abs(resultTotal - (int) resultTotal) < 0.01){
                            resultTotalS = String.valueOf((int) resultTotal);
                            resultList.add("1 тайм: ТБ (" + resultTotalS + ") " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("1 тайм: ТБ (" + resultTotalS + ") " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "1тайм: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tm1T;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeScore1T + selector.listOfMatches.get(j).awayScore1T == matchValue)
                                vozvrat++;
                            if (selector.listOfMatches.get(j).homeScore1T + selector.listOfMatches.get(j).awayScore1T < matchValue)
                                plus++;

                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        matchValue -= 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultTotal);
                        if (Math.abs(resultTotal - (int) resultTotal) < 0.01){
                            resultTotalS = String.valueOf((int) resultTotal);
                            resultList.add("1 тайм: ТМ (" + resultTotalS + ") " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("1 тайм: ТМ (" + resultTotalS + ") " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "1тайм: Фора команды":{
                    double fora = 0;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeScore1T - selector.listOfMatches.get(j).awayScore1T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).homeScore1T - selector.listOfMatches.get(j).awayScore1T + fora > 0)
                                    plus++;
                            } else {
                                if (selector.listOfMatches.get(j).awayScore1T - selector.listOfMatches.get(j).homeScore1T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).awayScore1T - selector.listOfMatches.get(j).homeScore1T + fora > 0)
                                    plus++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        fora -= 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultFora);
                        if (Math.abs(resultFora - (int) resultFora) < 0.01){
                            resultTotalS = String.valueOf((int) resultFora);
                            resultList.add("1 тайм: Ф(" + resultTotalS + ") " + team + ": " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("1 тайм: Ф(" + resultTotalS + ") " + team + ": " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "1тайм: Фора соперника":{
                    double fora = 0;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).awayTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeScore1T - selector.listOfMatches.get(j).awayScore1T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).homeScore1T - selector.listOfMatches.get(j).awayScore1T + fora > 0)
                                    plus++;
                            } else {
                                if (selector.listOfMatches.get(j).awayScore1T - selector.listOfMatches.get(j).homeScore1T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).awayScore1T - selector.listOfMatches.get(j).homeScore1T + fora > 0)
                                    plus++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        fora -= 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultFora);
                        if (Math.abs(resultFora - (int) resultFora) < 0.01){
                            resultTotalS = String.valueOf((int) resultFora);
                            resultList.add("1 тайм: Ф(" + resultTotalS + ") соперника " + team + ": " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("1 тайм: Ф(" + resultTotalS + ") соперника " + team + ": " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "2тайм: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tb2T;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeScore2T + selector.listOfMatches.get(j).awayScore2T == matchValue)
                                vozvrat++;
                            if (selector.listOfMatches.get(j).homeScore2T + selector.listOfMatches.get(j).awayScore2T > matchValue)
                                plus++;

                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        matchValue += 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultTotal);
                        if (Math.abs(resultTotal - (int) resultTotal) < 0.01){
                            resultTotalS = String.valueOf((int) resultTotal);
                            resultList.add("2 тайм: ТБ (" + resultTotalS + ") " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("2 тайм: ТБ (" + resultTotalS + ") " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "2тайм: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tm2T;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeScore2T + selector.listOfMatches.get(j).awayScore2T == matchValue)
                                vozvrat++;
                            if (selector.listOfMatches.get(j).homeScore2T + selector.listOfMatches.get(j).awayScore2T < matchValue)
                                plus++;

                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        matchValue -= 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultTotal);
                        if (Math.abs(resultTotal - (int) resultTotal) < 0.01){
                            resultTotalS = String.valueOf((int) resultTotal);
                            resultList.add("2 тайм: ТМ (" + resultTotalS + ") " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("2 тайм: ТМ (" + resultTotalS + ") " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "2тайм: Фора команды":{
                    double fora = 0;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeScore2T - selector.listOfMatches.get(j).awayScore2T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).homeScore2T - selector.listOfMatches.get(j).awayScore2T + fora > 0)
                                    plus++;
                            } else {
                                if (selector.listOfMatches.get(j).awayScore2T - selector.listOfMatches.get(j).homeScore2T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).awayScore2T - selector.listOfMatches.get(j).homeScore2T + fora > 0)
                                    plus++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        fora -= 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultFora);
                        if (Math.abs(resultFora - (int) resultFora) < 0.01){
                            resultTotalS = String.valueOf((int) resultFora);
                            resultList.add("2 тайм: Ф(" + resultTotalS + ") " + team + ": " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("2 тайм: Ф(" + resultTotalS + ") " + team + ": " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "2тайм: Фора соперника":{
                    double fora = 0;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultVozvrat = 99;
                    int resultMinus = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        int plus = 0;
                        int vozvrat = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).awayTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeScore2T - selector.listOfMatches.get(j).awayScore2T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).homeScore2T - selector.listOfMatches.get(j).awayScore2T + fora > 0)
                                    plus++;
                            } else {
                                if (selector.listOfMatches.get(j).awayScore2T - selector.listOfMatches.get(j).homeScore2T + fora == 0)
                                    vozvrat++;
                                if (selector.listOfMatches.get(j).awayScore2T - selector.listOfMatches.get(j).homeScore2T + fora > 0)
                                    plus++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) plus / (selector.listOfMatches.size() - vozvrat), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = plus;
                            resultVozvrat = vozvrat;
                            resultMinus = selector.listOfMatches.size() - plus - vozvrat;
                            resultPercent = percent;
                        }
                        fora -= 0.5;
                    }
                    if (resultFlag){
                        String resultTotalS = String.valueOf(resultFora);
                        if (Math.abs(resultFora - (int) resultFora) < 0.01){
                            resultTotalS = String.valueOf((int) resultFora);
                            resultList.add("2 тайм: Ф(" + resultTotalS + ") соперника " + team + ": " + resultSuccess + "+ " + resultVozvrat + "= " + resultMinus + "-" + " (" + resultPercent + "%)");
                        } else {
                            resultList.add("2 тайм: Ф(" + resultTotalS + ") соперника " + team + ": " + resultSuccess + "+ " + resultMinus + "-" + " (" + resultPercent + "%)");
                        }

                    }
                    break;
                }
                case "УГЛ: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmCorners;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeCorners + selector.listOfMatches.get(j).awayCorners < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("УГЛ: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbCorners;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeCorners + selector.listOfMatches.get(j).awayCorners > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("УГЛ: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ: Фора команды":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners - selector.listOfMatches.get(j).awayCorners + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners - selector.listOfMatches.get(j).homeCorners + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по УГЛ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ: Фора соперника":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners - selector.listOfMatches.get(j).homeCorners + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners - selector.listOfMatches.get(j).awayCorners + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по УГЛ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "УГЛ: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbCorners;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по УГЛ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmCorners;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по УГЛ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbCorners;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по УГЛ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmCorners;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по УГЛ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeCorners1T + selector.listOfMatches.get(j).awayCorners1T < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("УГЛ 1тайм: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeCorners1T + selector.listOfMatches.get(j).awayCorners1T > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("УГЛ 1 тайм: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: Фора команды":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners1T - selector.listOfMatches.get(j).awayCorners1T + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners1T - selector.listOfMatches.get(j).homeCorners1T + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по УГЛ в 1 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: Фора соперника":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners1T - selector.listOfMatches.get(j).homeCorners1T + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners1T - selector.listOfMatches.get(j).awayCorners1T + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по УГЛ в 1 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "УГЛ 1тайм: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners1T > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners1T > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по УГЛ в 1 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners1T < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners1T < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по УГЛ в 1 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners1T > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners1T > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по УГЛ в 1 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 1тайм: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners1T < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners1T < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по УГЛ в 1 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeCorners2T + selector.listOfMatches.get(j).awayCorners2T < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("УГЛ 2тайм: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeCorners2T + selector.listOfMatches.get(j).awayCorners2T > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("УГЛ 2 тайм: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: Фора команды":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners2T - selector.listOfMatches.get(j).awayCorners2T + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners2T - selector.listOfMatches.get(j).homeCorners2T + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по УГЛ во 2 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: Фора соперника":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners2T - selector.listOfMatches.get(j).homeCorners2T + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners2T - selector.listOfMatches.get(j).awayCorners2T + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по УГЛ во 2 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "УГЛ 2тайм: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners2T > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners2T > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по УГЛ во 2 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeCorners2T < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayCorners2T < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по УГЛ во 2 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners2T > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners2T > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по УГЛ во 2 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УГЛ 2тайм: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmCornersTime;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayCorners2T < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeCorners2T < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по УГЛ во 2 тайме: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ЖК: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmYC;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeYellowCards + selector.listOfMatches.get(j).awayYellowCards < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("ЖК: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "ЖК: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbYC;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeYellowCards + selector.listOfMatches.get(j).awayYellowCards > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("ЖК: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "ЖК: Фора команды":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeYellowCards - selector.listOfMatches.get(j).awayYellowCards + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayYellowCards - selector.listOfMatches.get(j).homeYellowCards + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по ЖК: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ЖК: Фора соперника":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayYellowCards - selector.listOfMatches.get(j).homeYellowCards + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeYellowCards - selector.listOfMatches.get(j).awayYellowCards + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по ЖК: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "ЖК: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbYC;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeYellowCards > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayYellowCards > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по ЖК: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ЖК: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmYC;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeYellowCards < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayYellowCards < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по ЖК: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ЖК: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbYC;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayYellowCards > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeYellowCards > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по ЖК: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ЖК: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmYC;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayYellowCards < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeYellowCards < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по ЖК: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ОФС: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmOfs;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeOffsides + selector.listOfMatches.get(j).awayOffsides < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("ОФС: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "ОФС: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbOfs;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeOffsides + selector.listOfMatches.get(j).awayOffsides > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("ОФС: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "ОФС: Фора команды":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeOffsides - selector.listOfMatches.get(j).awayOffsides + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayOffsides - selector.listOfMatches.get(j).homeOffsides + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по ОФС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ОФС: Фора соперника":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayOffsides - selector.listOfMatches.get(j).homeOffsides + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeOffsides - selector.listOfMatches.get(j).awayOffsides + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по ОФС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "ОФС: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbOfs;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeOffsides > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayOffsides > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по ОФС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ОФС: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmOfs;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeOffsides < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayOffsides < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по ОФС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ОФС: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbOfs;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayOffsides > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeOffsides > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по ОФС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ОФС: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmOfs;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayOffsides < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeOffsides < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по ОФС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УВС: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmSOT;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeShotsOnTarget + selector.listOfMatches.get(j).awayShotsOnTarget < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("УВС: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "УВС: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbSOT;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeShotsOnTarget + selector.listOfMatches.get(j).awayShotsOnTarget > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("УВС: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "УВС: Фора команды":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeShotsOnTarget - selector.listOfMatches.get(j).awayShotsOnTarget + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayShotsOnTarget - selector.listOfMatches.get(j).homeShotsOnTarget + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по УВС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УВС: Фора соперника":{
                    double fora = 0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayShotsOnTarget - selector.listOfMatches.get(j).homeShotsOnTarget + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeShotsOnTarget - selector.listOfMatches.get(j).awayShotsOnTarget + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по УВС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "УВС: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbSOT;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeShotsOnTarget > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayShotsOnTarget > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по УВС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УВС: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmSOT;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeShotsOnTarget < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayShotsOnTarget < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по УВС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УВС: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbSOT;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayShotsOnTarget > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeShotsOnTarget > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по УВС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "УВС: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmSOT;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayShotsOnTarget < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeShotsOnTarget < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по УВС: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ФОЛЫ: ТМ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tmFouls;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeFouls + selector.listOfMatches.get(j).awayFouls < matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("ФОЛЫ: ТМ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "ФОЛЫ: ТБ":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = tbFouls;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeFouls + selector.listOfMatches.get(j).awayFouls > matchValue)
                                success++;
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("ФОЛЫ: ТБ (" + resultTotal + ") " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }

                    break;
                }
                case "ФОЛЫ: Фора команды":{
                    double fora = -0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeFouls - selector.listOfMatches.get(j).awayFouls + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayFouls - selector.listOfMatches.get(j).homeFouls + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") " + team + " по ФОЛАМ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ФОЛЫ: Фора соперника":{
                    double fora = -0.5;
                    boolean flag = false;
                    double resultFora = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayFouls - selector.listOfMatches.get(j).homeFouls + fora > 0)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeFouls - selector.listOfMatches.get(j).awayFouls + fora > 0)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultFora = fora;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        fora -= 1;
                    }
                    if (resultFlag){
                        String str = String.valueOf(resultFora);
                        if (resultFora > 0)
                            str = "+" + str;
                        resultList.add("Фора (" + str + ") соперника " + team + " по ФОЛАМ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }

                case "ФОЛЫ: ИТБ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbFouls;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeFouls > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayFouls > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТБ (" + resultTotal + ") по ФОЛАМ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ФОЛЫ: ИТМ команды":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmFouls;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).homeFouls < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).awayFouls < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add(team + ": ИТМ (" + resultTotal + ") по ФОЛАМ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ФОЛЫ: ИТБ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itbFouls;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayFouls > matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeFouls > matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue += 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТБ (" + resultTotal + ") по ФОЛАМ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
                case "ФОЛЫ: ИТМ соперника":{
                    boolean flag = false;
                    double resultTotal = 99;
                    int resultSuccess = 99;
                    int resultPercent = 200;
                    boolean resultFlag = false;
                    double matchValue = itmFouls;

                    while (!flag){
                        success = 0;
                        for (int j=0; j<selector.listOfMatches.size(); j++){
                            if (selector.listOfMatches.get(j).homeTeam.equals(team)){
                                if (selector.listOfMatches.get(j).awayFouls < matchValue)
                                    success++;
                            } else{
                                if (selector.listOfMatches.get(j).homeFouls < matchValue)
                                    success++;
                            }
                        }
                        percent = (int) MyMath.round(100 * (double) success / selector.listOfMatches.size(), 0);
                        if (percent < percentBorder){
                            flag = true;
                        } else {
                            resultFlag = true;
                            resultTotal = matchValue;
                            resultSuccess = success;
                            resultPercent = percent;
                        }
                        matchValue -= 1;
                    }
                    if (resultFlag){
                        resultList.add("Соперник " + team + ": ИТМ (" + resultTotal + ") по ФОЛАМ: " + resultSuccess + " матчей из " + selector.listOfMatches.size() + " (" + resultPercent + "%)");
                    }
                    break;
                }
            }
        }
    }

    public static Color chooseColorForLabel(String text){

        if (text.contains("1 тайм") && !text.contains("УГЛ")){
            return new Color(180, 255, 245);
        }
        if (text.contains("2 тайм") && !text.contains("УГЛ")){
            return new Color(255, 193, 122);
        }
        if (text.contains("УГЛ") && !text.contains("тайм")){
            return new Color(149, 255, 125);
        }
        if (text.contains("УГЛ") && text.contains("1 тайм")){
            return new Color(142, 201, 255);
        }
        if (text.contains("УГЛ") && text.contains("2 тайм")) {
            return new Color(237, 143, 255);
        }
        if (text.contains("ЖК")){
            return new Color(255, 250, 140);
        }
        if (text.contains("ОФС")){
            return new Color(176, 167, 255);
        }
        if (text.contains("УВС")){
            return new Color(255, 124, 118);
        }
        if (text.contains("ФОЛ")){
            return new Color(244, 182, 255);
        }

        return new Color(200, 200, 200);
    }
}
