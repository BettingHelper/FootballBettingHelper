package sample;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FTPLoader {

    public static String downloadFile(String log, String password, String pathOnServer, String pathToSave) throws FileNotFoundException {
        String result;
        Settings settings = Settings.getSettingsFromFile();
        FTPClient fClient = new FTPClient();

        FileOutputStream fOutput = new FileOutputStream(pathToSave);
        try {
            fClient.connect(settings.getIp());
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            fClient.retrieveFile(pathOnServer, fOutput);
            fClient.logout();
            fClient.disconnect();
            fOutput.close();
            result = "Success";
        } catch (IOException ex) {
            System.err.println(ex);
            result = ex.toString();
        }
        return result;
    }

    public static void uploadFile(String log, String password, String pathOnServer, String pathToFile) throws FileNotFoundException {
        FTPClient fClient = new FTPClient();
        Settings settings = Settings.getSettingsFromFile();
        FileInputStream fInput = new FileInputStream(pathToFile);
        try {
            fClient.connect(settings.getIp());
            fClient.login(log, password);
            fClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            fClient.enterLocalPassiveMode();
            fClient.storeFile(pathOnServer, fInput);
            fClient.logout();
            fClient.disconnect();
            fInput.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}