package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.data.general.KeyedValues2DDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.tabbedui.VerticalLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WindowMatchStats extends JFrame{
    Settings settings = Settings.getSettingsFromFile();

    public WindowMatchStats(final Match match){
        super(match.homeTeam + " - " + match.awayTeam + ". " + match.date);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 550;
        int height = 900;
        if (screenSize.width < width)
            width = screenSize.width;
        if (screenSize.height < height)
            height = screenSize.height;

        setSize(width,height);
        setLocation(0, 0);
        setMinimumSize(new Dimension(width, 300));
        this.setLayout(new BorderLayout());
        JPanel headPanel = new JPanel(new BorderLayout());

        KeyedValues2DDataset dataset;
        JFreeChart jfreechart;

        File fileHTI = new File("images/" + match.homeTeam + ".png");
        File fileATI = new File("images/" + match.awayTeam + ".png");
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(fileHTI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = bimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel homeTeamImage = new JLabel(new ImageIcon(scaled));

        try {
            bimg = ImageIO.read(fileATI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scaled = bimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel awayTeamImage = new JLabel(new ImageIcon(scaled));
        headPanel.add(homeTeamImage, BorderLayout.WEST);
        headPanel.add(awayTeamImage, BorderLayout.EAST);

        JPanel scorePanel = new JPanel(new BorderLayout());
        JLabel labelEnded = new JLabel("Завершен");
        labelEnded.setFont(new Font("", 1, 15));
        labelEnded.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(labelEnded, BorderLayout.NORTH);

        JLabel labelScore = new JLabel(match.homeScore + " : " + match.awayScore);
        labelScore.setFont(new Font("", 1, 40));
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(labelScore);

        JLabel labelScoreByTimes = new JLabel("(" + match.homeScore1T + " : " + match.awayScore1T + "   " + match.homeScore2T + " : " + match.awayScore2T + ")");
        labelScoreByTimes.setFont(new Font("", 1, 15));
        labelScoreByTimes.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(labelScoreByTimes, BorderLayout.SOUTH);

        headPanel.add(scorePanel);
        headPanel.setBorder(BorderFactory.createTitledBorder(""));
        this.add(headPanel, BorderLayout.NORTH);

        JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
                JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel panelStatsAll = new JPanel(new VerticalLayout());
        JPanel panelStats1T = new JPanel(new VerticalLayout());
        JPanel panelStats2T = new JPanel(new VerticalLayout());

        double[] argsHT = {MyMath.round(match.homeXG, 2), match.homeBallPossession,
                match.homeShots, match.homeShotsOnTarget, match.homeShotsOffTarget, match.homeBlockedShots, match.homeShotsOnPost,
                match.homeCorners, match.homeOffsides, match.homeDribbles, match.homeAerialsWon, match.homeFouls,
                match.homeSaves, match.homeClearances, match.homeInterceptions, match.homeTackles, match.homePassesSuccessfully,
                MyMath.round(100 * match.homePassesSuccessfully / (double) match.homePasses, 0),
                match.homeKeyPasses, match.homeDispossessed,
                match.homeGoalKicks, match.homeThrowIns,
                match.homeYellowCards, match.homeSecondYellowCards, match.homeDirectRedCards, match.homePen, match.homePenScored,
                match.homeOGScored};
        double[] argsAT = {MyMath.round(match.awayXG, 2), match.awayBallPossession,
                match.awayShots, match.awayShotsOnTarget, match.awayShotsOffTarget, match.awayBlockedShots, match.awayShotsOnPost,
                match.awayCorners, match.awayOffsides, match.awayDribbles, match.awayAerialsWon, match.awayFouls,
                match.awaySaves, match.awayClearances, match.awayInterceptions, match.awayTackles, match.awayPassesSuccessfully,
                MyMath.round(100 * match.awayPassesSuccessfully / (double) match.awayPasses, 0),
                match.awayKeyPasses, match.awayDispossessed,
                match.awayGoalKicks, match.awayThrowIns,
                match.awayYellowCards, match.awaySecondYellowCards, match.awayDirectRedCards, match.awayPen, match.awayPenScored,
                match.awayOGScored};
        String[] labelTitles = {"xG","Владение мячом, %", "Удары", "Удары в створ", "Удары мимо", "Заблокировано ударов",
                "Штанги/Перекладины", "Угловые", "Офсайды", "Успешный дриблинг", "Выиграно воздушных единоборств",
                "Фолы", "Сэйвы", "Выносы", "Перехваты", "Отборы", "Точные передачи", "Точность передач, %", "Ключевые передачи", "Потери",
                "Удары от ворот", "Вброс аутов",
                "Желтые карточки", "<html>ЖК &rArr; КК</html>", "Прямые красные карточки", "Пробитые пенальти", "Забитые пенальти", "Автоголы противника"};

        for (int i = 0; i<argsAT.length; i++){
            JPanel panelParameter = new JPanel(new BorderLayout());
            JLabel labelParameter = new JLabel(labelTitles[i]);
            labelParameter.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelParameter, BorderLayout.NORTH);
            JLabel labelHT;
            if (i==0)
                labelHT = new JLabel(String.valueOf(argsHT[i]));
            else
                labelHT = new JLabel(String.valueOf((int) argsHT[i]));
            labelHT.setPreferredSize(new Dimension(50, 15));
            labelHT.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelHT, BorderLayout.WEST);
            JLabel labelAT;
            if (i==0)
                labelAT = new JLabel(String.valueOf(argsAT[i]));
            else
                labelAT = new JLabel(String.valueOf((int) argsAT[i]));
            labelAT.setPreferredSize(new Dimension(50, 15));
            labelAT.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelAT, BorderLayout.EAST);

            dataset = createDataset(argsHT[i], argsAT[i]);
            jfreechart = ChartFactory.createStackedBarChart("", "", "", dataset, PlotOrientation.HORIZONTAL, false, false, false);
            jfreechart.setBackgroundPaint(new Color(238, 238, 238));
            CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
            plot.setBackgroundPaint(new Color(238, 238, 238));
            plot.setInsets(new RectangleInsets(0, 0, 0, 0));
            plot.setOutlineVisible(false);
            plot.getDomainAxis().setVisible(false);
            plot.getRangeAxis().setVisible(false);
            ValueAxis rangeAxis = plot.getRangeAxis();
            double range = argsHT[i] + argsAT[i];
            if (range == 0)
                range = 1;
            rangeAxis.setRange(-range, range);
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setSeriesPaint(0, new Color(100, 100, 100));
            renderer.setSeriesPaint(1, new Color(200, 200, 200));
            renderer.setSeriesPaint(2, new Color(100, 100, 100));
            renderer.setSeriesPaint(3, new Color(200, 200, 200));

            if (argsHT[i] > argsAT[i])
                renderer.setSeriesPaint(0, Color.RED);
            if (argsHT[i] < argsAT[i])
                renderer.setSeriesPaint(2, Color.RED);
            Marker marker = new ValueMarker(0);
            marker.setPaint(Color.black);
            marker.setStroke(new BasicStroke(2));
            plot.addRangeMarker(marker);
            marker = new ValueMarker(0.01);
            marker.setPaint(Color.WHITE);
            marker.setStroke(new BasicStroke(1.5f));
            plot.addRangeMarker(marker);
            marker = new ValueMarker(-0.01);
            marker.setPaint(Color.WHITE);
            marker.setStroke(new BasicStroke(1.5f));
            plot.addRangeMarker(marker);

            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(400, 15));
            chartpanel.setRangeZoomable(false);
            panelParameter.add(chartpanel, BorderLayout.CENTER);
            if (argsHT[i] + argsAT[i] > 0)
                panelStatsAll.add(panelParameter);
        }
        JLabel labelRef;
        if (!match.referee.equals("")){
            labelRef = new JLabel("Арбитр матча - " + match.referee);
            labelRef.setHorizontalAlignment(SwingConstants.CENTER);
            labelRef.setFont(new Font("", 0, 15));
            panelStatsAll.add(labelRef, BorderLayout.SOUTH);
        }
        JButton buttonWSMatchCenter = new JButton("Матч-центр на WhoScored.com");
        buttonWSMatchCenter.setFont(new Font("", 0, 15));
        buttonWSMatchCenter.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.gray));
        panelStatsAll.add(buttonWSMatchCenter, BorderLayout.SOUTH);
        if (match.URIonWhoscored.equals("-"))
            buttonWSMatchCenter.setEnabled(false);

        JScrollPane jsp = new JScrollPane(panelStatsAll);
        jtp.add("Матч", jsp);

        double[] argsHT1Time = {match.homeBallPossession1T, match.homeShots1T, match.homeShotsOnTarget1T, match.homeShotsOffTarget1T,
                match.homeBlockedShots1T, match.homeCorners1T, match.homeOffsides1T, match.homeDribbles1T, match.homeAerialsWon1T,
                match.homeFouls1T, match.homeSaves1T, match.homeClearances1T, match.homeInterceptions1T, match.homeTackles1T,
                match.homePassesSuccessfully1T, MyMath.round(100 * match.homePassesSuccessfully1T / (double) match.homePasses1T, 0),
                match.homeKeyPasses1T, match.homeDispossessed1T, match.homeGoalKicks1T, match.homeThrowIns1T, match.homeYellowCards1T};
        double[] argsAT1Time = {match.awayBallPossession1T, match.awayShots1T, match.awayShotsOnTarget1T, match.awayShotsOffTarget1T,
                match.awayBlockedShots1T, match.awayCorners1T, match.awayOffsides1T, match.awayDribbles1T, match.awayAerialsWon1T,
                match.awayFouls1T, match.awaySaves1T, match.awayClearances1T, match.awayInterceptions1T, match.awayTackles1T,
                match.awayPassesSuccessfully1T, MyMath.round(100 * match.awayPassesSuccessfully1T / (double) match.awayPasses1T, 0),
                match.awayKeyPasses1T, match.awayDispossessed1T, match.awayGoalKicks1T, match.awayThrowIns1T, match.awayYellowCards1T};
        String[] labelTitles1Time = {"Владение мячом, %", "Удары", "Удары в створ", "Удары мимо", "Заблокировано ударов",
                "Угловые", "Офсайды", "Успешный дриблинг", "Выиграно воздушных единоборств", "Фолы", "Сэйвы", "Выносы", "Перехваты",
                "Отборы", "Точные передачи", "Точность передач, %", "Ключевые передачи", "Потери", "Удары от ворот", "Вброс аутов", "Желтые карточки"};
        for (int i = 0; i<argsHT1Time.length; i++){
            JPanel panelParameter = new JPanel(new BorderLayout());
            JLabel labelParameter = new JLabel(labelTitles1Time[i]);
            labelParameter.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelParameter, BorderLayout.NORTH);
            JLabel labelHT = new JLabel(String.valueOf((int) argsHT1Time[i]));
            labelHT.setPreferredSize(new Dimension(50, 15));
            labelHT.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelHT, BorderLayout.WEST);
            JLabel labelAT = new JLabel(String.valueOf((int) argsAT1Time[i]));
            labelAT.setPreferredSize(new Dimension(50, 15));
            labelAT.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelAT, BorderLayout.EAST);

            dataset = createDataset(argsHT1Time[i], argsAT1Time[i]);
            jfreechart = ChartFactory.createStackedBarChart("", "", "", dataset, PlotOrientation.HORIZONTAL, false, false, false);
            jfreechart.setBackgroundPaint(new Color(238, 238, 238));
            CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
            plot.setBackgroundPaint(new Color(238, 238, 238));
            plot.setInsets(new RectangleInsets(0, 0, 0, 0));
            plot.setOutlineVisible(false);
            plot.getDomainAxis().setVisible(false);
            plot.getRangeAxis().setVisible(false);
            ValueAxis rangeAxis = plot.getRangeAxis();         //getDomainAxis();
            double range = argsHT1Time[i] + argsAT1Time[i];
            if (range == 0)
                range = 1;
            rangeAxis.setRange(-range, range);
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setSeriesPaint(0, new Color(100, 100, 100));
            renderer.setSeriesPaint(1, new Color(200, 200, 200));
            renderer.setSeriesPaint(2, new Color(100, 100, 100));
            renderer.setSeriesPaint(3, new Color(200, 200, 200));

            if (argsHT1Time[i] > argsAT1Time[i])
                renderer.setSeriesPaint(0, Color.RED);
            if (argsHT1Time[i] < argsAT1Time[i])
                renderer.setSeriesPaint(2, Color.RED);
            Marker marker = new ValueMarker(0);
            marker.setPaint(Color.black);
            marker.setStroke(new BasicStroke(2));
            plot.addRangeMarker(marker);
            marker = new ValueMarker(0.01);
            marker.setPaint(Color.WHITE);
            marker.setStroke(new BasicStroke(1.5f));
            plot.addRangeMarker(marker);
            marker = new ValueMarker(-0.01);
            marker.setPaint(Color.WHITE);
            marker.setStroke(new BasicStroke(1.5f));
            plot.addRangeMarker(marker);

            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(400, 15));
            chartpanel.setRangeZoomable(false);
            panelParameter.add(chartpanel, BorderLayout.CENTER);
            panelStats1T.add(panelParameter);
        }
        jsp = new JScrollPane(panelStats1T);
        jtp.add("1 тайм", jsp);

        double[] argsHT2Time = {match.homeBallPossession2T, match.homeShots2T, match.homeShotsOnTarget2T, match.homeShotsOffTarget2T,
                match.homeBlockedShots2T, match.homeCorners2T, match.homeOffsides2T, match.homeDribbles2T, match.homeAerialsWon2T,
                match.homeFouls2T, match.homeSaves2T, match.homeClearances2T, match.homeInterceptions2T, match.homeTackles2T,
                match.homePassesSuccessfully2T, MyMath.round(100 * match.homePassesSuccessfully2T / (double) match.homePasses2T, 0),
                match.homeKeyPasses2T, match.homeDispossessed2T, match.homeGoalKicks2T, match.homeThrowIns2T, match.homeYellowCards2T};
        double[] argsAT2Time = {match.awayBallPossession2T, match.awayShots2T, match.awayShotsOnTarget2T, match.awayShotsOffTarget2T,
                match.awayBlockedShots2T, match.awayCorners2T, match.awayOffsides2T, match.awayDribbles2T, match.awayAerialsWon2T,
                match.awayFouls2T, match.awaySaves2T, match.awayClearances2T, match.awayInterceptions2T, match.awayTackles2T,
                match.awayPassesSuccessfully2T, MyMath.round(100 * match.awayPassesSuccessfully2T / (double) match.awayPasses2T, 0),
                match.awayKeyPasses2T, match.awayDispossessed2T, match.awayGoalKicks2T, match.awayThrowIns2T, match.awayYellowCards2T};
        String[] labelTitles2Time = {"Владение мячом, %", "Удары", "Удары в створ", "Удары мимо", "Заблокировано ударов",
                "Угловые", "Офсайды", "Успешный дриблинг", "Выиграно воздушных единоборств", "Фолы", "Сэйвы", "Выносы", "Перехваты",
                "Отборы", "Точные передачи", "Точность передач, %", "Ключевые передачи", "Потери", "Удары от ворот", "Вброс аутов", "Желтые карточки"};
        for (int i = 0; i<argsHT2Time.length; i++){
            JPanel panelParameter = new JPanel(new BorderLayout());
            JLabel labelParameter = new JLabel(labelTitles2Time[i]);
            labelParameter.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelParameter, BorderLayout.NORTH);
            JLabel labelHT = new JLabel(String.valueOf((int) argsHT2Time[i]));
            labelHT.setPreferredSize(new Dimension(50, 15));
            labelHT.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelHT, BorderLayout.WEST);
            JLabel labelAT = new JLabel(String.valueOf((int) argsAT2Time[i]));
            labelAT.setPreferredSize(new Dimension(50, 15));
            labelAT.setHorizontalAlignment(SwingConstants.CENTER);
            panelParameter.add(labelAT, BorderLayout.EAST);

            dataset = createDataset(argsHT2Time[i], argsAT2Time[i]);
            jfreechart = ChartFactory.createStackedBarChart("", "", "", dataset, PlotOrientation.HORIZONTAL, false, false, false);
            jfreechart.setBackgroundPaint(new Color(238, 238, 238));
            CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
            plot.setBackgroundPaint(new Color(238, 238, 238));
            plot.setInsets(new RectangleInsets(0, 0, 0, 0));
            plot.setOutlineVisible(false);
            plot.getDomainAxis().setVisible(false);
            plot.getRangeAxis().setVisible(false);
            ValueAxis rangeAxis = plot.getRangeAxis();
            double range = argsHT2Time[i] + argsAT2Time[i];
            if (range == 0)
                range = 1;
            rangeAxis.setRange(-range, range);
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setSeriesPaint(0, new Color(100, 100, 100));
            renderer.setSeriesPaint(1, new Color(200, 200, 200));
            renderer.setSeriesPaint(2, new Color(100, 100, 100));
            renderer.setSeriesPaint(3, new Color(200, 200, 200));

            if (argsHT2Time[i] > argsAT2Time[i])
                renderer.setSeriesPaint(0, Color.RED);
            if (argsHT2Time[i] < argsAT2Time[i])
                renderer.setSeriesPaint(2, Color.RED);
            Marker marker = new ValueMarker(0);
            marker.setPaint(Color.black);
            marker.setStroke(new BasicStroke(2));
            plot.addRangeMarker(marker);
            marker = new ValueMarker(0.01);
            marker.setPaint(Color.WHITE);
            marker.setStroke(new BasicStroke(1.5f));
            plot.addRangeMarker(marker);
            marker = new ValueMarker(-0.01);
            marker.setPaint(Color.WHITE);
            marker.setStroke(new BasicStroke(1.5f));
            plot.addRangeMarker(marker);

            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(400, 15));
            chartpanel.setRangeZoomable(false);
            panelParameter.add(chartpanel, BorderLayout.CENTER);
            panelStats2T.add(panelParameter);
        }
        jsp = new JScrollPane(panelStats2T);
        jtp.add("2 тайм", jsp);

        this.add(jtp);
        //this.pack();
        if (settings.windowsOnTop)
            setAlwaysOnTop(true);

        buttonWSMatchCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = java.awt.Desktop.getDesktop();
                URI uri = null;
                try {
                    uri = new URI(match.URIonWhoscored);
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                try {
                    desktop.browse(uri);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private KeyedValues2DDataset createDataset(double leftValue, double rightValue) {
        DefaultKeyedValues2DDataset defaultkeyedvalues2ddataset = new DefaultKeyedValues2DDataset();

        if (leftValue + rightValue == 0){
            defaultkeyedvalues2ddataset.addValue(0, "Negative", "row 1");
            defaultkeyedvalues2ddataset.addValue(-1, "NegativeSumm", "row 1");
            defaultkeyedvalues2ddataset.addValue(0, "Positive", "row 1");
            defaultkeyedvalues2ddataset.addValue(1, "PositiveSumm", "row 1");
        } else {
            defaultkeyedvalues2ddataset.addValue(-leftValue, "Negative", "row 1");
            defaultkeyedvalues2ddataset.addValue(-rightValue, "NegativeSumm", "row 1");
            defaultkeyedvalues2ddataset.addValue(rightValue, "Positive", "row 1");
            defaultkeyedvalues2ddataset.addValue(leftValue, "PositiveSumm", "row 1");
        }

        return defaultkeyedvalues2ddataset;
    }
}
