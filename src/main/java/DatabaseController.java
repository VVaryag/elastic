import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseController {
    private final String IP_MONGO = "192.168.6.134";
    private final int PORT_MONGO = 27018;
    private final String NAME_DATABASE = "file";
    private final String NAME_COLLECTION = "fileExample";

    public FindIterable<Document> getFileName() {
        Bson bson = Filters.eq("file_extention", "txt");
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        return getCollection().find(bson);
    }

    private MongoClient getMongoConnection() {
        return new MongoClient(IP_MONGO, PORT_MONGO);
    }

    private MongoDatabase getDataBase() {
        return getMongoConnection().getDatabase(NAME_DATABASE);
    }

    private MongoCollection<Document> getCollection() {
        return getDataBase().getCollection(NAME_COLLECTION);

    }

}


