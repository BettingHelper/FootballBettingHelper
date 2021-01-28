package sample;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TablePivotRenderer extends JLabel implements TableCellRenderer{

    public TablePivotRenderer(){
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column){
        String text = (String) value;

        if (text.contains(":")){
            super.setBackground(new Color(10, 230, 10, 150));
        }


        /*if(text == ) {
            super.setBackground(Color.GREEN);
        }
        else if(grade == Grade.B) {
            super.setBackground(Color.BLUE);
        }
        else if(grade == Grade.C) {
            super.setBackground(Color.RED);
        }*/

        return this;
    }

}