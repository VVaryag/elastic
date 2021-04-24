import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> nameFile = new ArrayList<>();
        DatabaseController db = new DatabaseController();
        FindIterable<Document> cursor = db.getFileName();
        for (Document doc : cursor
        ) {
            nameFile.add(doc.getString("name"));
        }
        FtpController ftpController = new FtpController(nameFile);
        ftpController.getFiles();
    }
}
