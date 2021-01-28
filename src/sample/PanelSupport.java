package sample;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.util.TimeZone;

public class PanelSupport extends JPanel{
    Settings settings;
    JPanel container;
    String formS;
    boolean windowsOnTop;
    final String path = "database/";
    String defText = "  Здесь вы можете написать в Betting Helper. \n" +
            "\n" +
            "  Через данную форму вы можете: \n" +
            "     - сообщить о проблеме в работе программы;\n" +
            "     - предложить реализовать какое-либо полезное улучшение;\n" +
            "     - задать разработчикам интересующий вас вопрос;\n" +
            "     - передать респект или дизреспект за наши старания. \n" +
            "\n" +
            "  К обращению можно приложить файлы .png или .jpg (.jpeg) \n" +
            "  Чтобы добавить несколько файлов, зажмите Ctrl и выберите \n" +
            "  те файлы, которые хотите направить нам на рассмотрение. \n" +
            "\n" +
            "  Не отправляйте, пожалуйста, нам откровенный спам или всякий оффтоп. Вы же адекватные люди ;)\n" +
            "\n" +
            "  Не забудьте указать почту, чтобы мы отправили на нее свой ответ." +
            "  На все сообщения по теме мы обязательно ответим!";


    public PanelSupport(){
        this.setLayout(new BorderLayout());

        final Font defFont = new Font("default", Font.BOLD, 15);

        JLabel header = new JLabel("Сообщение в службу поддержки \"Betting Helper\"");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setFont(new Font("", Font.BOLD, 20));
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(1,2,5,0));

        final JTextArea textArea = new JTextArea(defText);
        textArea.setFont(defFont);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mainPanel.add(textArea);

        JPanel secondPanel = new JPanel(new VerticalLayout());
        secondPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel fileChoserHeader = new JLabel("Выбор файла");
        fileChoserHeader.setHorizontalAlignment(SwingConstants.CENTER);
        fileChoserHeader.setFont(defFont);
        secondPanel.add(fileChoserHeader);

        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toUpperCase().contains(".JPEG") || f.getName().toUpperCase().contains(".PNG") || f.getName().toUpperCase().contains(".JPG");
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        fileChooser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        secondPanel.add(fileChooser);

        JLabel labelEmail = new JLabel("    Почта, на которую мы направим ответ:");
        labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
        labelEmail.setFont(defFont);
        labelEmail.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        secondPanel.add(labelEmail);
        final JTextField email = new JTextField("");
        email.setFont(defFont);
        email.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        secondPanel.add(email);

        mainPanel.add(secondPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        this.add(mainPanel);

        JButton buttonSend = new JButton("Отправить!");
        buttonSend.setFont(new Font("", Font.BOLD, 20));
        this.add(buttonSend, BorderLayout.SOUTH);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().contains(defText))
                    textArea.selectAll();
            }
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = null;
                boolean flagCorrectData = true;
                String textToUser = "";
                if (textArea.getText().contains(defText) || textArea.getText().equals("")){
                    textToUser = "Вы не ввели текст своего обращения";
                    flagCorrectData = false;
                }
                if (!(email.getText().contains("@") && (email.getText().contains(".")))){
                    textToUser = "Почта введена некорректно";
                    flagCorrectData = false;
                }

                if (flagCorrectData){
                    try {
                        userName = Settings.getUserBySerialNumber(Settings.getSerialNumber());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));

                    String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                    String month = String.valueOf(c.get(Calendar.MONTH)+1);
                    String year = String.valueOf(c.get(Calendar.YEAR));
                    String hour = String.valueOf(c.get(11));
                    String minute = String.valueOf(c.get(12));
                    if (day.length()==1){
                        day = "0" + day;
                    }
                    if (month.length()==1){
                        month = "0" + month;
                    }
                    if (hour.length()==1){
                        hour = "0" + hour;
                    }
                    if (minute.length()==1){
                        minute = "0" + minute;
                    }

                    userName = userName.replaceAll(" ", "_");
                    String fileName = "Mail: " + userName + "_" + hour + "." + minute + "_" + day + "." + month + "." + year + ".txt";
                    File text = new File(Settings.TMP_DIR + fileName);
                    FileWriter fr = null;
                    BufferedWriter br = null;
                    int numberOfAccount = 0;
                    try {
                        fr = new FileWriter(text,true);
                        br = new BufferedWriter(fr);
                        br.write(textArea.getText());
                        br.newLine();
                        br.write("Почта для ответа: " + email.getText());
                        for (int i=0; i<fileChooser.getSelectedFiles().length; i++){
                            try {
                                String pathToFile = fileChooser.getSelectedFiles()[i].getAbsolutePath();
                                String fileNameForPicture = fileName.replace(".txt", "") + "_" + (i+1) + "_" + fileChooser.getSelectedFiles()[i].getName();
                                br.newLine();
                                br.write(fileNameForPicture);
                                FTPLoader.uploadFile(Settings.getLogin(numberOfAccount), Settings.getPassword(numberOfAccount),
                                        "/data/football/support/" + fileNameForPicture, pathToFile);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                PopupWindow window = new PopupWindow("<html>Ошибка отправки файлов</html>");
                                window.setVisible(true);
                                window.setAlwaysOnTop(true);
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally{
                        try {
                            br.close();
                            fr.close();
                            numberOfAccount = Settings.getNumberOfAccount();
                            FTPLoader.uploadFile(Settings.getLogin(numberOfAccount), Settings.getPassword(numberOfAccount),
                                    "/data/football/support/" + fileName, Settings.TMP_DIR + fileName);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            PopupWindow window = new PopupWindow("<html>Ошибка отправки сообщения</html>");
                            window.setVisible(true);
                            window.setAlwaysOnTop(true);
                        }
                        PopupWindow popupWindow = new PopupWindow("<html>Ваше обращение отправлено!<br>" +
                                "Спасибо!</html>");
                        popupWindow.setFont(defFont);
                        popupWindow.setVisible(true);
                    }
                } else {
                    PopupWindow popupWindow = new PopupWindow(textToUser);
                    popupWindow.setFont(defFont);
                    popupWindow.setVisible(true);
                }
            }
        });
    }
}