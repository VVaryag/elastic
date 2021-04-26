
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class Main {

    public static void main(String[] args) {

//        System.out.println(ZonedDateTime.now( ZoneOffset.UTC ).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        ArrayList<String> fileExtension = new ArrayList<>();
        ArrayList<String> nameFile = new ArrayList<>();
        fileExtension.add("txt");
        DatabaseController db = new DatabaseController(fileExtension);
        FindIterable<Document> cursor = db.getFileName();
        for (Document doc : cursor
        ) {
            System.out.println(doc.getString("name"));
            nameFile.add(doc.getString("name"));
        }
        FtpController ftpController = new FtpController(nameFile);
        ftpController.getFiles();
    }
}
