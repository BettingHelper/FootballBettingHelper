package sample;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Correlator {

    public Correlator(){
    }

    public static double getCorrelationOfParams(String teamName, String param1, String param2, ArrayList<Match> listOfMatches, ArrayList<ArrayList<String>> paramsList){
        double result = 0;
        double verhSumm = 0;
        double nizSumm1 = 0;
        double nizSumm2 = 0;
        double MO1 = 0;
        double MO2 = 0;

        if ( (param1.equals("Владение")) && (param2.equals("Угловые")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(10).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("УгловыеПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(10).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("УгловыеФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(10).get(1)) - Double.parseDouble(paramsList.get(10).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("Фолы")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(13).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeFouls - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeFouls - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayFouls  - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayFouls - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("ФолыПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(13).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayFouls - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayFouls - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeFouls - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeFouls - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("ФолыФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(13).get(1)) - Double.parseDouble(paramsList.get(13).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayFouls - listOfMatches.get(i).homeFouls - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayFouls - listOfMatches.get(i).homeFouls - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("УдарыВСтвор")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(8).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("УдарыВСтворПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(8).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession  - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("УдарыВСтворФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(8).get(1)) - Double.parseDouble(paramsList.get(8).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayShotsOnTarget - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeShotsOnTarget - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("Офсайды")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(11).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeOffsides - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeOffsides - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayOffsides - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayOffsides - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("ОфсайдыПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(11).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayOffsides - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession  - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayOffsides - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeOffsides - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeOffsides - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("ОфсайдыФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(11).get(1)) - Double.parseDouble(paramsList.get(11).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeOffsides - listOfMatches.get(i).awayOffsides - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeOffsides - listOfMatches.get(i).awayOffsides - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayOffsides- listOfMatches.get(i).homeOffsides - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayOffsides - listOfMatches.get(i).homeOffsides - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("ЖК")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(14).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("ЖКПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(14).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("ЖКФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(14).get(1)) - Double.parseDouble(paramsList.get(14).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayYellowCards - listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - listOfMatches.get(i).homeYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("Ауты")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(81).get(1))/(double)listOfMatches.size();

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeThrowIns - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeThrowIns - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayThrowIns - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayThrowIns - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("АутыПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(81).get(2))/(double)listOfMatches.size();

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayThrowIns - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayThrowIns - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeThrowIns - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeThrowIns - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("АутыФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = (Double.parseDouble(paramsList.get(81).get(1)) - Double.parseDouble(paramsList.get(81).get(2)))/(double)listOfMatches.size();;

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeThrowIns - listOfMatches.get(i).awayThrowIns - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeThrowIns - listOfMatches.get(i).awayThrowIns - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayThrowIns - listOfMatches.get(i).homeThrowIns - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayThrowIns - listOfMatches.get(i).homeThrowIns - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("УдарыОтВорот")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(78).get(1))/(double)listOfMatches.size();

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeGoalKicks - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeGoalKicks - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayGoalKicks - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayGoalKicks - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("УдарыОтВоротПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(78).get(2))/(double)listOfMatches.size();

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayGoalKicks - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayGoalKicks - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeGoalKicks - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeGoalKicks - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("УдарыОтВоротФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = (Double.parseDouble(paramsList.get(78).get(1)) - Double.parseDouble(paramsList.get(78).get(2)))/(double)listOfMatches.size();;

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeGoalKicks - listOfMatches.get(i).awayGoalKicks - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeGoalKicks - listOfMatches.get(i).awayGoalKicks - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayGoalKicks - listOfMatches.get(i).homeGoalKicks - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayGoalKicks - listOfMatches.get(i).homeGoalKicks - MO2) , 2);
                }
            }
        }


        if ( (param1.equals("Удары")) && (param2.equals("УдарыВСтвор")) ){
            MO1 = Double.parseDouble(paramsList.get(7).get(1));
            MO2 = Double.parseDouble(paramsList.get(8).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeShots - MO1)*(listOfMatches.get(i).homeShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayShots - MO1)*(listOfMatches.get(i).awayShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыПротивника")) && (param2.equals("УдарыВСтворПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(7).get(2));
            MO2 = Double.parseDouble(paramsList.get(8).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayShots - MO1)*(listOfMatches.get(i).awayShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShots  - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeShots - MO1)*(listOfMatches.get(i).homeShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыФора")) && (param2.equals("УдарыВСтворФора")) ){
            MO1 = Double.parseDouble(paramsList.get(7).get(1)) - Double.parseDouble(paramsList.get(7).get(2));
            MO2 = Double.parseDouble(paramsList.get(8).get(1)) - Double.parseDouble(paramsList.get(8).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeShots - listOfMatches.get(i).awayShots - MO1)*(listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShots - listOfMatches.get(i).awayShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayShotsOnTarget - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayShots - listOfMatches.get(i).homeShots - MO1)*(listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeShotsOnTarget - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShots - listOfMatches.get(i).homeShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeShotsOnTarget - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Удары")) && (param2.equals("Угловые")) ){
            MO1 = Double.parseDouble(paramsList.get(7).get(1));
            MO2 = Double.parseDouble(paramsList.get(10).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeShots - MO1)*(listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayShots - MO1)*(listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыПротивника")) && (param2.equals("УгловыеПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(7).get(2));
            MO2 = Double.parseDouble(paramsList.get(10).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayShots - MO1)*(listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShots  - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeShots - MO1)*(listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыФора")) && (param2.equals("УгловыеФора")) ){
            MO1 = Double.parseDouble(paramsList.get(7).get(1)) - Double.parseDouble(paramsList.get(7).get(2));
            MO2 = Double.parseDouble(paramsList.get(10).get(1)) - Double.parseDouble(paramsList.get(10).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeShots - listOfMatches.get(i).awayShots - MO1)*(listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShots - listOfMatches.get(i).awayShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayShots - listOfMatches.get(i).homeShots - MO1)*(listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShots - listOfMatches.get(i).homeShots - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Фолы")) && (param2.equals("Желтые карточки")) ){
            MO1 = Double.parseDouble(paramsList.get(13).get(1));
            MO2 = Double.parseDouble(paramsList.get(14).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeFouls - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayFouls - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Фолы")) && (param2.equals("ЖК")) ){
            MO1 = Double.parseDouble(paramsList.get(13).get(1));
            MO2 = Double.parseDouble(paramsList.get(14).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeFouls - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayFouls - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ФолыПротивника")) && (param2.equals("ЖКПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(13).get(2));
            MO2 = Double.parseDouble(paramsList.get(14).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayFouls - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeFouls - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ФолыФора")) && (param2.equals("ЖКФора")) ){
            MO1 = Double.parseDouble(paramsList.get(13).get(1)) - Double.parseDouble(paramsList.get(13).get(2));
            MO2 = Double.parseDouble(paramsList.get(14).get(1)) - Double.parseDouble(paramsList.get(14).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO1)*(listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeFouls - listOfMatches.get(i).awayFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayFouls - listOfMatches.get(i).homeFouls - MO1)*(listOfMatches.get(i).awayYellowCards - listOfMatches.get(i).homeYellowCards- MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayFouls - listOfMatches.get(i).homeFouls - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - listOfMatches.get(i).homeYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("Удары")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(7).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeShots - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShots - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayShots - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShots - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("ВладениеПротивника")) && (param2.equals("УдарыПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(2));
            MO2 = Double.parseDouble(paramsList.get(7).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayShots - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession  - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShots - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeShots - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShots - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Владение")) && (param2.equals("УдарыФора")) ){
            MO1 = Double.parseDouble(paramsList.get(6).get(1));
            MO2 = Double.parseDouble(paramsList.get(7).get(1)) - Double.parseDouble(paramsList.get(7).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeBallPossession - MO1)*(listOfMatches.get(i).homeShots - listOfMatches.get(i).awayShots - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeShots - listOfMatches.get(i).awayShots - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayBallPossession - MO1)*(listOfMatches.get(i).awayShots - listOfMatches.get(i).homeShots - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayBallPossession - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayShots - listOfMatches.get(i).homeShots - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыВСтвор")) && (param2.equals("Угловые")) ){
            MO1 = Double.parseDouble(paramsList.get(8).get(1));
            MO2 = Double.parseDouble(paramsList.get(10).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeShotsOnTarget - MO1)*(listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayShotsOnTarget - MO1)*(listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыВСтворПротивника")) && (param2.equals("УгловыеПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(8).get(2));
            MO2 = Double.parseDouble(paramsList.get(10).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayShotsOnTarget - MO1)*(listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget  - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeShotsOnTarget - MO1)*(listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УдарыВСтворФора")) && (param2.equals("УгловыеФора")) ){
            MO1 = Double.parseDouble(paramsList.get(8).get(1)) - Double.parseDouble(paramsList.get(8).get(2));
            MO2 = Double.parseDouble(paramsList.get(10).get(1)) - Double.parseDouble(paramsList.get(10).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayShotsOnTarget - MO1)*(listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayShotsOnTarget - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeShotsOnTarget - MO1)*(listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeShotsOnTarget - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("Угловые")) && (param2.equals("ЖК")) ){
            MO1 = Double.parseDouble(paramsList.get(10).get(1));
            MO2 = Double.parseDouble(paramsList.get(14).get(1));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeCorners - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeCorners - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayCorners - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayCorners - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УгловыеПротивника")) && (param2.equals("ЖКПротивника")) ){
            MO1 = Double.parseDouble(paramsList.get(10).get(2));
            MO2 = Double.parseDouble(paramsList.get(14).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).awayCorners - MO1)*(listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayCorners - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).homeCorners - MO1)*(listOfMatches.get(i).homeYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeCorners - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - MO2) , 2);
                }
            }
        }

        if ( (param1.equals("УгловыеФора")) && (param2.equals("ЖКФора")) ){
            MO1 = Double.parseDouble(paramsList.get(10).get(1)) - Double.parseDouble(paramsList.get(10).get(2));
            MO2 = Double.parseDouble(paramsList.get(14).get(1)) - Double.parseDouble(paramsList.get(14).get(2));

            for (int i=0; i<listOfMatches.size(); i++){
                if (teamName.equals(listOfMatches.get(i).homeTeam)){
                    verhSumm += (listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO1)*(listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).homeCorners - listOfMatches.get(i).awayCorners - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).homeYellowCards - listOfMatches.get(i).awayYellowCards - MO2) , 2);
                } else{
                    verhSumm += (listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO1)*(listOfMatches.get(i).awayYellowCards - listOfMatches.get(i).homeYellowCards- MO2);
                    nizSumm1 += Math.pow( (listOfMatches.get(i).awayCorners - listOfMatches.get(i).homeCorners - MO1) , 2);
                    nizSumm2 += Math.pow( (listOfMatches.get(i).awayYellowCards - listOfMatches.get(i).homeYellowCards - MO2) , 2);
                }
            }
        }

        result = verhSumm / Math.sqrt(nizSumm1*nizSumm2);

        return result;
    }
}
