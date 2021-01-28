package sample;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame{
    JProgressBar jpb;

    public TestWindow(String text/*, String leagueName, String parameter, String season*/){
        super("Внимание!");
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(500, 200);
        this.setLocation(200, 200);

        JLabel label = new JLabel(text);
        label.setFont(new Font("", Font.BOLD, 20));
        this.add(label, BorderLayout.NORTH);

        jpb = new JProgressBar(0, 100);
        jpb.setPreferredSize(new Dimension(600, 50));
        jpb.setStringPainted(true);
        this.add(jpb, BorderLayout.SOUTH);

        this.setVisible(true);

        /*Calculator calc = new Calculator("", "", "", this);
        calc.start();


        for (int i=1; i<=100; i++){
            try {
                Thread.sleep(50);
                jpb.setValue(i);
                System.out.println("ПрогрессБар = " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}
