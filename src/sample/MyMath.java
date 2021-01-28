package sample;

import java.util.ArrayList;

public class MyMath {
    public static double xG_of_Penalty = 0.75;
    public static double xG_of_ownGoal = 0.3;

    public static double round(double d, int precise) {
        precise = (int) Math.pow(10,precise);
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }

    public static double[] getXWinXDrawXLose(double xDiff){
        double[] result = new double[3];

        if (xDiff>=0){
            if (xDiff>1){
                result[0] = 1;
                result[1] = 0;
                result[2] = 0;
            } else {
                result[0] = MyMath.round((1 / (1 + Math.exp(5 - 10 * xDiff)) - 0.0066928509242848554) / 0.9866142981514304, 3);
                result[1] = MyMath.round(1 - result[0], 3);
                result[2] = 0;
            }
        } else {
            if (xDiff<-1){
                result[0] = 0;
                result[1] = 0;
                result[2] = 1;
            } else {
                result[2] = MyMath.round((1 / (1 + Math.exp(5 - 10 * Math.abs(xDiff))) - 0.0066928509242848554) / 0.9866142981514304, 3);
                result[1] = MyMath.round(1 - result[2], 3);
                result[0] = 0;
            }
        }
        return result;
    }

    public static ArrayList<Double> getWeights(int n, double w){
        ArrayList<Double> resultList = new ArrayList<>();

        for (int i=0; i<n; i++){
//           resultList.add((2*(i+1) - 1)/(double) n);
           resultList.add((i - (n-1) / 2.0)*2*w/n + 1);
        }
        return resultList;
    }

    public static double getValueBetweenAB(double a, double b, double weight){
        return MyMath.round( a+(b-a)*weight , 2);
    }

}
