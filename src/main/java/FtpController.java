import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;

public class FtpController {
    private final String FTP_USER = "user";
    private final String FTP_PASS = "qwerty123";
    private final String FTP_ADDRESS = "192.168.6.134";
    private final int FTP_PORT = 21;

    private ArrayList<String> nameFiles;
    private FTPClient ftpClient = new FTPClient();
    public FtpController(ArrayList<String> nameFiles) {
        this.nameFiles = nameFiles;
    }

    public void getFiles() {

        try {
            ftpClient.connect(FTP_ADDRESS, FTP_PORT);
            ftpClient.login(FTP_USER, FTP_PASS);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            downloadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFiles() {
        OutputStream outputStream;
        File downloadFile;

        try {
            for (String name : nameFiles) {
                downloadFile = new File(name);
                System.out.println(name);
                outputStream = new FileOutputStream(downloadFile);
                boolean success = ftpClient.retrieveFile("/home/vsftpd/files/"+name, outputStream);
                if (success) {
                    System.out.println("File download");
                } else {
                    System.out.println("Download Failed");
                }
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


