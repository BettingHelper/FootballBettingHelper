package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

// определяем корневой элемент
@XmlRootElement(name = "SliderSettings")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"slider_HT_1_Value", "slider_HT_2_Value", "slider_HT_3_Value", "slider_HT_4_Value",
        "slider_AT_1_Value", "slider_AT_2_Value", "slider_AT_3_Value", "slider_AT_4_Value" })

public class SliderSettings {
    public final static String TMP_DIR = System.getProperty("java.io.tmpdir");
    public int slider_HT_1_Value;
    public int slider_HT_2_Value;
    public int slider_HT_3_Value;
    public int slider_HT_4_Value;
    public int slider_AT_1_Value;
    public int slider_AT_2_Value;
    public int slider_AT_3_Value;
    public int slider_AT_4_Value;

    public SliderSettings(){
    }

    public SliderSettings(int slider_HT_1_Value, int slider_HT_2_Value, int slider_HT_3_Value, int slider_HT_4_Value,
                          int slider_AT_1_Value, int slider_AT_2_Value, int slider_AT_3_Value, int slider_AT_4_Value) {
        this.slider_HT_1_Value = slider_HT_1_Value;
        this.slider_HT_2_Value = slider_HT_2_Value;
        this.slider_HT_3_Value = slider_HT_3_Value;
        this.slider_HT_4_Value = slider_HT_4_Value;
        this.slider_AT_1_Value = slider_AT_1_Value;
        this.slider_AT_2_Value = slider_AT_2_Value;
        this.slider_AT_3_Value = slider_AT_3_Value;
        this.slider_AT_4_Value = slider_AT_4_Value;

    }

    public static SliderSettings getSliderSettingsFromFile(){
        String fileName = "settings/sliderSettings.xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(SliderSettings.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (SliderSettings) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 24<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);

        }
        return null;
    }

    public void pushSliderSettingsToFile(){
        String fileName = "settings/sliderSettings.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(SliderSettings.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
            PopupWindow window = new PopupWindow("<html>   Ошибка при синхронизации базы. Код ошибки 25<br>" +
                    "Обратитесь к администратору</html>");
            window.setVisible(true);
            window.setAlwaysOnTop(true);
        }
    }


}
