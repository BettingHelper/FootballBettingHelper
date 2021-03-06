package sample;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Renderer extends DefaultTableCellRenderer {
    int typeOfTable;
    double[][] minMaxArray;
    String shortTeamName;
    int index;
    Settings settings = Settings.getSettingsFromFile();
    boolean useColors = settings.useColors;

    public Renderer(int typeOfTable){
        this.typeOfTable = typeOfTable;
    }

    public Renderer(double[][] minMaxArray, int index){
        this.typeOfTable = index;
        this.minMaxArray = minMaxArray;
    }

    public Renderer(String shortTeamName, int index){
        this.typeOfTable = 4;
        this.shortTeamName = shortTeamName;
        this.index = index;
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!useColors){
            setHorizontalAlignment(JLabel.CENTER);
            cell.setBackground(new Color(255, 255, 255));
            return cell;
        }

        String cellValue = value.toString();

        switch (typeOfTable){
            case 0:{ // baseTableOfTeam (Сравнение команд и Статистика команды
                if (column == 1) {
                    double selfValue = 0;
                    double valueOpp = 0;
                    if (!cellValue.contains("%")){
                        selfValue = Double.parseDouble(cellValue);
                        valueOpp = Double.parseDouble((table.getValueAt(row, 2)).toString());
                    } else{
                        selfValue = Double.parseDouble(cellValue.split("\\(")[0].trim());
                        valueOpp = Double.parseDouble((table.getValueAt(row, 2)).toString().split("\\(")[0].trim());
                    }
                    if (selfValue == valueOpp){
                        cell.setBackground(new Color(250, 210, 10, 150));
                    } else {
                        if (selfValue > valueOpp) {
                            cell.setBackground(new Color(30, 240, 40, 150));
                        } else {
                            cell.setBackground(new Color(250, 70, 70, 150));
                        }
                    }
                    break;
                }
                if (column == 2) {
                    double selfValue = 0;
                    double valueOpp = 0;
                    if (!cellValue.contains("%")){
                        selfValue = Double.parseDouble(cellValue);
                        valueOpp = Double.parseDouble((table.getValueAt(row, 1)).toString());
                    } else{
                        selfValue = Double.parseDouble(cellValue.split("\\(")[0].trim());
                        valueOpp = Double.parseDouble((table.getValueAt(row, 1)).toString().split("\\(")[0].trim());
                    }
                    if (selfValue == valueOpp){
                        cell.setBackground(new Color(250, 210, 10, 150));
                    } else {
                        if (selfValue > valueOpp) {
                            cell.setBackground(new Color(30, 240, 40, 150));
                        } else {
                            cell.setBackground(new Color(250, 70, 70, 150));
                        }
                    }
                    break;
                }

                if (column == 4) {
                    if (cellValue.equals("-")){
                        cell.setBackground(new Color(255, 255, 255));
                    } else{
                        if (Double.parseDouble(cellValue) == 0){
                            cell.setBackground(new Color(250, 210, 10, 150));
                        } else {
                            if (Double.parseDouble(cellValue) > 0) {
                                cell.setBackground(new Color(30, 240, 40, 150));
                            } else {
                                cell.setBackground(new Color(250, 70, 70, 150));
                            }
                        }
                    }
                    break;
                }

                cell.setBackground(new Color(255, 255, 255));
                break;
            }
            case 1:{ // pivotTable (Сравнение команд и Статистика команды
                if (column > 1 && row < table.getRowCount()-1){
                    String homeOrAway = (String) table.getValueAt(row, 1);
                    int homeValue = Integer.parseInt(cellValue.split(":")[0].trim());
                    int awayValue = Integer.parseInt(cellValue.split(":")[1].trim());

                    if (homeValue == awayValue){
                        cell.setBackground(new Color(250, 210, 10, 150));
                    } else {
                        if (homeOrAway.contains("Дом")){
                            if (homeValue > awayValue) {
                                cell.setBackground(new Color(30, 240, 40, 150));
                            } else {
                                cell.setBackground(new Color(250, 70, 70, 150));
                            }
                        } else {
                            if (homeValue > awayValue) {
                                cell.setBackground(new Color(250, 70, 70, 150));
                            } else {
                                cell.setBackground(new Color(30, 240, 40, 150));
                            }
                        }
                    }
                } else {
                    cell.setBackground(new Color(255, 255, 255));
                }
                break;
            }
            case 2:{ // correlationTable
                if (column > 0) {
                    double selfValue = Double.parseDouble(cellValue);
                    cell.setBackground(getColorForCorrelation(selfValue));
                    break;
                } else {
                    cell.setBackground(new Color(255, 255, 255));
                }
                break;
            }
            case 3:{ // League tables
                if (column > 0){
                    double selfValue = Double.parseDouble(cellValue);
                    cell.setBackground(getColorBetweenRedAndGreen(selfValue, column, minMaxArray));
                } else {
                    cell.setBackground(new Color(255, 255, 255));
                }

                break;
            }
            case 4:{ // tables instead graphics
                if (index == 1 || index == 2 || index == 36 || index == 37){
                    break;
                }
                if (column == 2){
                    String homeTeamName = (String) table.getValueAt(row, 1);
                    double homeValue = Double.parseDouble(cellValue.split(":")[0].trim());
                    double awayValue = Double.parseDouble(cellValue.split(":")[1].trim());

                    if (homeValue == awayValue){
                        cell.setBackground(new Color(250, 210, 10, 150));
                    } else {
                        if (homeTeamName.contains(shortTeamName)){
                            if (homeValue > awayValue) {
                                cell.setBackground(new Color(30, 240, 40, 150));
                            } else {
                                cell.setBackground(new Color(250, 70, 70, 150));
                            }
                        } else {
                            if (homeValue > awayValue) {
                                cell.setBackground(new Color(250, 70, 70, 150));
                            } else {
                                cell.setBackground(new Color(30, 240, 40, 150));
                            }
                        }
                    }
                    break;
                }
                if (column == 5){
                    double selfValue = Double.parseDouble(cellValue);
                    if (selfValue == 0) {
                        cell.setBackground(new Color(250, 210, 10, 150));
                    }
                    if (selfValue > 0) {
                        cell.setBackground(new Color(30, 240, 40, 150));
                    }
                    if (selfValue < 0) {
                        cell.setBackground(new Color(250, 70, 70, 150));
                    }
                    break;
                }
                cell.setBackground(new Color(238, 238, 238));
                break;
            }
            case 5:{ //all referees Table
                if (column > 0){
                    double selfValue = Double.parseDouble(cellValue);
                    cell.setBackground(getColorBetweenRedAndGreen(selfValue, column, minMaxArray));
                } else {
                    cell.setBackground(new Color(238, 238, 238));
                }

                break;
            }
            case 6:{ //
                if (cellValue.equals("-"))
                    break;
                cell.setBackground(new Color(255, 255, 255));// refTable
                if (column == 2 && row < table.getRowCount()){
                    double homeValue = Double.parseDouble((table.getValueAt(row, 2)).toString().trim());
                    double awayValue = Double.parseDouble((table.getValueAt(row, 3)).toString().trim());

                    if (homeValue == awayValue){
                        cell.setBackground(new Color(250, 210, 10, 150));
                    } else {
                        if (homeValue > awayValue) {
                            cell.setBackground(new Color(30, 240, 40, 150));
                        } else {
                            cell.setBackground(new Color(250, 70, 70, 150));
                        }
                    }
                }
                if (column == 3 && row < table.getRowCount()){
                    double homeValue = Double.parseDouble((table.getValueAt(row, 2)).toString().trim());
                    double awayValue = Double.parseDouble((table.getValueAt(row, 3)).toString().trim());
                    if (homeValue == awayValue){
                        cell.setBackground(new Color(250, 210, 10, 150));
                    } else {
                        if (homeValue > awayValue) {
                            cell.setBackground(new Color(250, 70, 70, 150));
                        } else {
                            cell.setBackground(new Color(30, 240, 40, 150));
                        }
                    }
                }
                break;
            }
            case 7:{ // refereeTables (instead Graphics)
                cell.setBackground(new Color(255, 255, 255));
                if (column == 2 && cellValue.contains(":")){
                    double homeValue = Double.parseDouble(cellValue.split(":")[0].trim());
                    double awayValue = Double.parseDouble(cellValue.split(":")[1].trim());

                    if (homeValue == awayValue) {
                        cell.setBackground(new Color(250, 210, 10, 150));
                    }
                    if (homeValue > awayValue) {
                        cell.setBackground(new Color(30, 240, 40, 150));
                    }
                    if (homeValue < awayValue) {
                        cell.setBackground(new Color(250, 70, 70, 150));
                    }
                }
                break;
            }
        }

        setHorizontalAlignment(JLabel.CENTER);
        return cell;
    }

    public static Color getColorBetweenRedAndGreen(double value, int column, double[][] minMaxArray){
        double min = minMaxArray[0][column];
        double max = minMaxArray[1][column];
        double center = (max + min) / 2;

        if (value >= min && value <= max){
            if (value > center){
                int a = (int) (255*(value-center)/(max-center));
                return new Color(0, 255, 0, a);
            } else {
                int a = (int) (255*(center - value)/(center-min));
                return new Color(255, 0, 0, a);
            }
        } else {
            return new Color(255, 255, 255);
        }

    }

    public static Color getColorForCorrelation(double value){
        if (value > 1 || value < -1)
            return new Color(255, 255, 255);
        if (value >= -1 && value < 0){
            int a = (int) (255*Math.abs(value));
            return new Color(255, 0, 0, a);
        }
        if (value > 0 && value <= 1){
            int a = (int) (255*Math.abs(value));
            return new Color(0, 255, 0, a);
        }




        return new Color(255, 255, 255);
    }
}