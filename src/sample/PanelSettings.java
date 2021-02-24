package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class PanelSettings extends JPanel{
    Settings settings;
    JPanel container;
    String formS;
    boolean windowsOnTop;
    boolean flagGraphics;
    boolean flagTrendsHA;
    boolean flagBubbleChartsHA;
    final String path = "database/";

    public PanelSettings(){
        this.setLayout(new BorderLayout());

        JScrollPane sp;
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder(""));

        JPanel secondPanel = new JPanel(new BorderLayout());
        mainPanel.add(secondPanel);

        settings = Settings.getSettingsFromFile();

        JPanel selectGraphics = new JPanel();
        selectGraphics.setBorder(
                BorderFactory.createTitledBorder("Отображение графиков"));
        selectGraphics.setLayout(new BorderLayout());

        JPanel allMatchPanel = new JPanel(new VerticalLayout());

        Font font = new Font("Arial", Font.BOLD, 15);
        final JCheckBox goalsAndConcededGoals  = new JCheckBox("Заб. и проп. голы", settings.showGoals);
        allMatchPanel.add(goalsAndConcededGoals);
        final JCheckBox goalsAndxG  = new JCheckBox("Голы и xG", settings.showxG);
        allMatchPanel.add(goalsAndxG);
        final JCheckBox goalsCAndxGA = new JCheckBox("Проп. голы и xGA", settings.showxGA);
        allMatchPanel.add(goalsCAndxGA);
        final JCheckBox xGAndxGA = new JCheckBox("xG и xGA", settings.showxGAndxGA);
        allMatchPanel.add(xGAndxGA);
        final JCheckBox ballPossetion = new JCheckBox("Владение мячом", settings.showPossession);
        allMatchPanel.add(ballPossetion);
        final JCheckBox shots = new JCheckBox("Удары", settings.showShots);
        allMatchPanel.add(shots);
        final JCheckBox shotsOnTarget  = new JCheckBox("Удары в створ", settings.showShotsOnTarget);
        allMatchPanel.add(shotsOnTarget);
        final JCheckBox shotsOffTarget = new JCheckBox("Удары мимо", settings.showShotsOffTarget);
        allMatchPanel.add(shotsOffTarget);
        final JCheckBox blockedShots = new JCheckBox("Блок. ударов", settings.showBlockedShots);
        allMatchPanel.add(blockedShots);
        final JCheckBox shotsOnPost = new JCheckBox("Штанги/перекл.", settings.showShotsOnPost);
        allMatchPanel.add(shotsOnPost);
        final JCheckBox corners  = new JCheckBox("Угловые", settings.showCorners);
        allMatchPanel.add(corners);
        final JCheckBox offsides = new JCheckBox("Офсайды", settings.showOffsides);
        allMatchPanel.add(offsides);
        final JCheckBox fouls  = new JCheckBox("Фолы", settings.showFouls);
        allMatchPanel.add(fouls);
        final JCheckBox yellowCards  = new JCheckBox("Желтые карточки", settings.showYC);
        allMatchPanel.add(yellowCards);
        final JCheckBox goalKicks  = new JCheckBox("Удары от ворот", settings.showGoalKicks);
        allMatchPanel.add(goalKicks);
        final JCheckBox throwIns  = new JCheckBox("Вброс аутов", settings.showThrowIns);
        allMatchPanel.add(throwIns);
        final JCheckBox firstYCMinute  = new JCheckBox("Минута первой ЖК", settings.showFirstYCMinute);
        allMatchPanel.add(firstYCMinute);
        final JCheckBox lastYCMinute  = new JCheckBox("Минута последней ЖК", settings.showLastYCMinute);
        allMatchPanel.add(lastYCMinute);
        final JCheckBox redCards  = new JCheckBox("Удаления", settings.showRC);
        allMatchPanel.add(redCards);
        final JCheckBox dribbles  = new JCheckBox("Усп. дриблинг", settings.showDribbles);
        allMatchPanel.add(dribbles);
        final JCheckBox aerialsWon  = new JCheckBox("Возд.един.", settings.showAerialsWon);
        allMatchPanel.add(aerialsWon);
        final JCheckBox saves  = new JCheckBox("Сэйвы", settings.showSaves);
        allMatchPanel.add(saves);
        final JCheckBox clearances  = new JCheckBox("Выносы", settings.showClearances);
        allMatchPanel.add(clearances);
        final JCheckBox interceptions  = new JCheckBox("Перехваты", settings.showInterceptions);
        allMatchPanel.add(interceptions);
        final JCheckBox tackles  = new JCheckBox("Отборы", settings.showTackles);
        allMatchPanel.add(tackles);
        final JCheckBox dispossessed  = new JCheckBox("Потери", settings.showDispossessed);
        allMatchPanel.add(dispossessed);
        final JCheckBox passesAcc  = new JCheckBox("Точность передач", settings.showPassesAcc);
        allMatchPanel.add(passesAcc);
        final JCheckBox keyPasses  = new JCheckBox("Ключевые пасы", settings.showKeyPasses);
        allMatchPanel.add(keyPasses);

        selectGraphics.add(allMatchPanel, BorderLayout.WEST);

        JPanel byTimesPanel = new JPanel(new VerticalLayout());
        byTimesPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        final JCheckBox goalsAndConcededGoals1T  = new JCheckBox("Заб. и проп. голы 1 тайм", settings.showGoals1T);
        byTimesPanel.add(goalsAndConcededGoals1T);
        final JCheckBox goalsAndConcededGoals2T  = new JCheckBox("Заб. и проп. голы 2 тайм", settings.showGoals2T);
        byTimesPanel.add(goalsAndConcededGoals2T);
        final JCheckBox ballPossetion1T = new JCheckBox("Владение мячом 1 тайм", settings.showPossession1T);
        byTimesPanel.add(ballPossetion1T);
        final JCheckBox ballPossetion2T = new JCheckBox("Владение мячом 2 тайм", settings.showPossession2T);
        byTimesPanel.add(ballPossetion2T);
        final JCheckBox shots1T = new JCheckBox("Удары 1 тайм", settings.showShots1T);
        byTimesPanel.add(shots1T);
        final JCheckBox shots2T = new JCheckBox("Удары 2 тайм", settings.showShots2T);
        byTimesPanel.add(shots2T);
        final JCheckBox shotsOnTarget1T = new JCheckBox("Удары в створ 1 тайм", settings.showShotsOnTarget1T);
        byTimesPanel.add(shotsOnTarget1T);
        final JCheckBox shotsOnTarget2T = new JCheckBox("Удары в створ 2 тайм", settings.showShotsOnTarget2T);
        byTimesPanel.add(shotsOnTarget2T);
        final JCheckBox shotsOffTarget1T = new JCheckBox("Удары мимо 1 тайм", settings.showShotsOffTarget1T);
        byTimesPanel.add(shotsOffTarget1T);
        final JCheckBox shotsOffTarget2T = new JCheckBox("Удары мимо 2 тайм", settings.showShotsOffTarget2T);
        byTimesPanel.add(shotsOffTarget2T);
        final JCheckBox blockedShots1T = new JCheckBox("Блок.ударов 1 тайм", settings.showBlockedShots1T);
        byTimesPanel.add(blockedShots1T);
        final JCheckBox blockedShots2T = new JCheckBox("Блок.ударов 2 тайм", settings.showBlockedShots2T);
        byTimesPanel.add(blockedShots2T);
        final JCheckBox corners1T = new JCheckBox("Угловые 1 тайм", settings.showCorners1T);
        byTimesPanel.add(corners1T);
        final JCheckBox corners2T = new JCheckBox("Угловые 2 тайм", settings.showCorners2T);
        byTimesPanel.add(corners2T);
        final JCheckBox offsides1T = new JCheckBox("Офсайды 1 тайм", settings.showOffsides1T);
        byTimesPanel.add(offsides1T);
        final JCheckBox offsides2T = new JCheckBox("Офсайды 2 тайм", settings.showOffsides2T);
        byTimesPanel.add(offsides2T);
        final JCheckBox fouls1T = new JCheckBox("Фолы 1 тайм", settings.showFouls1T);
        byTimesPanel.add(fouls1T);
        final JCheckBox fouls2T = new JCheckBox("Фолы 2 тайм", settings.showFouls2T);
        byTimesPanel.add(fouls2T);
        final JCheckBox YC1T = new JCheckBox("ЖК 1 тайм", settings.showYC1T);
        byTimesPanel.add(YC1T);
        final JCheckBox YC2T = new JCheckBox("ЖК 2 тайм", settings.showYC2T);
        byTimesPanel.add(YC2T);
        final JCheckBox goalKicks1T  = new JCheckBox("Удары от ворот 1 тайм", settings.showGoalKicks1T);
        byTimesPanel.add(goalKicks1T);
        final JCheckBox goalKicks2T  = new JCheckBox("Удары от ворот 2 тайм", settings.showGoalKicks2T);
        byTimesPanel.add(goalKicks2T);
        final JCheckBox throwIns1T  = new JCheckBox("Вброс аутов 1 тайм", settings.showThrowIns1T);
        byTimesPanel.add(throwIns1T);
        final JCheckBox throwIns2T  = new JCheckBox("Вброс аутов 2 тайм", settings.showThrowIns2T);
        byTimesPanel.add(throwIns2T);

        selectGraphics.add(byTimesPanel);

        final JCheckBox showTotal  = new JCheckBox("Тоталы на графиках", settings.showTotal);
        showTotal.setHorizontalAlignment(SwingConstants.CENTER);
        selectGraphics.add(showTotal, BorderLayout.SOUTH);

        secondPanel.add(selectGraphics);

        JPanel otherSettings = new JPanel(new VerticalLayout());

        JPanel panelWithSlider = new JPanel();
        panelWithSlider.setBorder(BorderFactory.createTitledBorder("Граница проходимости тренда"));

        final JSlider slider = new JSlider(60, 100, Integer.parseInt(settings.trendPercent));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPreferredSize(new Dimension(280, 100));
        panelWithSlider.add(slider);
        otherSettings.add(panelWithSlider);


        final JCheckBox goalsBy15Min = new JCheckBox("Голы по 15-тиминуткам", settings.showGoalsBy15Min);
        otherSettings.add(goalsBy15Min);
        final JCheckBox useColors = new JCheckBox("Цветовое оформление таблиц", settings.useColors);
        otherSettings.add(useColors);



        JPanel rbPanel = new JPanel(new FlowLayout());
        rbPanel.setBorder(BorderFactory.createTitledBorder("Вывод матчей в строке 'Форма'"));
        JRadioButton leftToRight = new JRadioButton("Слева направо");
        JRadioButton rightToLeft = new JRadioButton("Справа налево");

        if (settings.form.equals("leftToRight")){
            leftToRight.setSelected(true);
            formS = "leftToRight";
        }
        else{
            rightToLeft.setSelected(true);
            formS = "rightToLeft";
        }

        final ButtonGroup group = new ButtonGroup();
        group.add(leftToRight);
        group.add(rightToLeft);
        rbPanel.add(leftToRight);
        rbPanel.add(rightToLeft);
        otherSettings.add(rbPanel);

        final JCheckBox windowsOnTop = new JCheckBox("Открывать дополнит. окна поверх всех", settings.windowsOnTop);
        otherSettings.add(windowsOnTop);

        String[] hoursList = new String[]{
                "GMT + 12. Анадырь",
                "GMT + 11. Магадан",
                "GMT + 10. Владивосток",
                "GMT + 9. Якутск",
                "GMT + 8. Иркутск",
                "GMT + 7. Красноярск",
                "GMT + 6. Омск",
                "GMT + 5. Екатеринбург",
                "GMT + 4. Самара",
                "GMT + 3. Москва",
                "GMT + 2. Калининград",
                "GMT + 1. Среднеевропейское время",
                "GMT + 0. Гринвичское время",
                "GMT - 1. Гренландия",
                "GMT - 2. Среднеатлантическое время",
                "GMT - 3. Аргентина",
                "GMT - 4. Атлантическое время",
                "GMT - 5. Нью-Йорк",
                "GMT - 6. Чикаго",
                "GMT - 7. Денвер",
                "GMT - 8. Лос-Анджелес",
                "GMT - 9. Аляска",
                "GMT - 10. Гавайское время",
                "GMT - 11. Американское Самоа",
                "GMT - 12. Ну вы серьезно до туда добрались?:) Ѳ",
        };
        JPanel timeChooserPanel = new JPanel(new BorderLayout());
        timeChooserPanel.setBorder(BorderFactory.createTitledBorder("Выберите ваш часовой пояс"));
        final JComboBox<String> timeChooser = new JComboBox<>(hoursList);
        timeChooser.setSelectedIndex(settings.localTime);
        timeChooserPanel.add(timeChooser);
        otherSettings.add(timeChooserPanel);

        final JCheckBox pivotTable = new JCheckBox("Отображать сводную таблицу", settings.pivotTable);
        otherSettings.add(pivotTable);

        JPanel panelGraphicOrTables = new JPanel();
        panelGraphicOrTables.setBorder(BorderFactory.createTitledBorder("Отображение статистики"));

        JRadioButton graphicRB = new JRadioButton("Графики");
        JRadioButton tablesRB = new JRadioButton("Таблицы");

        if (settings.showGraphics){
            graphicRB.setSelected(true);
            flagGraphics = true;
        }
        else{
            tablesRB.setSelected(true);
            flagGraphics = false;
        }

        final ButtonGroup groupGraphicRB = new ButtonGroup();
        groupGraphicRB.add(graphicRB);
        groupGraphicRB.add(tablesRB);
        panelGraphicOrTables.add(graphicRB);
        panelGraphicOrTables.add(tablesRB);
        otherSettings.add(panelGraphicOrTables);

        JPanel panelTrendsSettings = new JPanel();
        panelTrendsSettings.setBorder(BorderFactory.createTitledBorder("Какие тренды отображать?"));

        JRadioButton trendsHA = new JRadioButton("Дом - выезд");
        JRadioButton trendsAll = new JRadioButton("Все игры - все игры");

        if (settings.trendsHA){
            trendsHA.setSelected(true);
            flagTrendsHA = true;
        }
        else{
            trendsAll.setSelected(true);
            flagTrendsHA = false;
        }

        final ButtonGroup groupTrends = new ButtonGroup();
        groupTrends.add(trendsHA);
        groupTrends.add(trendsAll);
        panelTrendsSettings.add(trendsHA);
        panelTrendsSettings.add(trendsAll);
        otherSettings.add(panelTrendsSettings);

        JPanel panelBubblesChartSettings = new JPanel();
        panelBubblesChartSettings.setBorder(BorderFactory.createTitledBorder("Перекрестные графики"));

        JRadioButton bubbleChartsHA = new JRadioButton("Дом - выезд");
        JRadioButton bubbleChartsAll = new JRadioButton("Все игры - все игры");

        if (settings.bubbleChartsHA){
            bubbleChartsHA.setSelected(true);
            flagBubbleChartsHA = true;
        }
        else{
            bubbleChartsAll.setSelected(true);
            flagBubbleChartsHA = false;
        }

        final ButtonGroup groupBubbleCharts = new ButtonGroup();
        groupBubbleCharts.add(bubbleChartsHA);
        groupBubbleCharts.add(bubbleChartsAll);
        panelBubblesChartSettings.add(bubbleChartsHA);
        panelBubblesChartSettings.add(bubbleChartsAll);
        otherSettings.add(panelBubblesChartSettings);


        secondPanel.add(otherSettings, BorderLayout.EAST);

        JButton buttonApply = new JButton("Применить");
        buttonApply.setFont(font);
        buttonApply.setLocation(20, 320);
        buttonApply.setSize(240, 40);
        mainPanel.add(buttonApply, BorderLayout.SOUTH);

        sp = new JScrollPane(mainPanel);
        sp.setVerticalScrollBar( new JScrollBar() {
            public int getUnitIncrement( int direction ) {
                return 50;
            }
        } );
        this.add(sp, BorderLayout.WEST);

        leftToRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formS = "leftToRight";
            }
        });
        rightToLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formS = "rightToLeft";
            }
        });
        graphicRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagGraphics = true;
            }
        });
        tablesRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagGraphics = false;
            }
        });
        trendsHA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagTrendsHA = true;
            }
        });
        trendsAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagTrendsHA = false;
            }
        });

        bubbleChartsHA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagBubbleChartsHA = true;
            }
        });
        bubbleChartsAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagBubbleChartsHA = false;
            }
        });

        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Boolean> arrayList = new ArrayList<>();
                arrayList.add(goalsAndConcededGoals.isSelected());
                arrayList.add(goalsAndxG.isSelected());
                arrayList.add(goalsCAndxGA.isSelected());
                arrayList.add(xGAndxGA.isSelected());
                arrayList.add(goalsAndConcededGoals1T.isSelected());
                arrayList.add(goalsAndConcededGoals2T.isSelected());
                arrayList.add(ballPossetion.isSelected());
                arrayList.add(ballPossetion1T.isSelected());
                arrayList.add(ballPossetion2T.isSelected());
                arrayList.add(shots.isSelected());
                arrayList.add(shots1T.isSelected());
                arrayList.add(shots2T.isSelected());
                arrayList.add(shotsOnTarget.isSelected());
                arrayList.add(shotsOnTarget1T.isSelected());
                arrayList.add(shotsOnTarget2T.isSelected());
                arrayList.add(shotsOffTarget.isSelected());
                arrayList.add(shotsOffTarget1T.isSelected());
                arrayList.add(shotsOffTarget2T.isSelected());
                arrayList.add(blockedShots.isSelected());
                arrayList.add(blockedShots1T.isSelected());
                arrayList.add(blockedShots2T.isSelected());
                arrayList.add(shotsOnPost.isSelected());
                arrayList.add(corners.isSelected());
                arrayList.add(corners1T.isSelected());
                arrayList.add(corners2T.isSelected());
                arrayList.add(offsides.isSelected());
                arrayList.add(offsides1T.isSelected());
                arrayList.add(offsides2T.isSelected());
                arrayList.add(fouls.isSelected());
                arrayList.add(fouls1T.isSelected());
                arrayList.add(fouls2T.isSelected());
                arrayList.add(yellowCards.isSelected());
                arrayList.add(YC1T.isSelected());
                arrayList.add(YC2T.isSelected());
                arrayList.add(firstYCMinute.isSelected());
                arrayList.add(lastYCMinute.isSelected());
                arrayList.add(redCards.isSelected());
                arrayList.add(dribbles.isSelected());
                arrayList.add(aerialsWon.isSelected());
                arrayList.add(saves.isSelected());
                arrayList.add(clearances.isSelected());
                arrayList.add(interceptions.isSelected());
                arrayList.add(tackles.isSelected());
                arrayList.add(dispossessed.isSelected());
                arrayList.add(passesAcc.isSelected());
                arrayList.add(keyPasses.isSelected());
                arrayList.add(showTotal.isSelected());
                arrayList.add(goalsBy15Min.isSelected());
                arrayList.add(goalKicks.isSelected());
                arrayList.add(goalKicks1T.isSelected());
                arrayList.add(goalKicks2T.isSelected());
                arrayList.add(throwIns.isSelected());
                arrayList.add(throwIns1T.isSelected());
                arrayList.add(throwIns2T.isSelected());
                arrayList.add(windowsOnTop.isSelected());
                arrayList.add(pivotTable.isSelected());
                arrayList.add(flagGraphics);
                arrayList.add(flagTrendsHA);
                arrayList.add(useColors.isSelected());
                arrayList.add(flagBubbleChartsHA);

                settings = new Settings(arrayList, Main.getFrames()[0].getWidth(), Main.getFrames()[0].getHeight(), slider.getValue(), formS, settings.getIp(), timeChooser.getSelectedIndex());
                settings.pushSettingsToFile();
            }
        });

        JPanel panelReloadDatabase = new JPanel(new VerticalLayout());
        panelReloadDatabase.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton buttonReloadDatabase = new JButton("Перезагрузка БД");
        buttonReloadDatabase.setFont(font);
        buttonReloadDatabase.setPreferredSize(new Dimension(200, 30));
        panelReloadDatabase.add(buttonReloadDatabase);

        this.add(panelReloadDatabase, BorderLayout.EAST);

        buttonReloadDatabase.addActionListener(e -> {
            Settings.setLastUpdate("213_01.03.2020.txt");
            buttonReloadDatabase.setText("Перезапустите программу!");
            buttonReloadDatabase.setEnabled(false);
        });



    }
}