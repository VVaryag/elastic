import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseController {
    private final String IP_MONGO = "192.168.6.134";
    private final int PORT_MONGO = 27018;
    private final String NAME_DATABASE = "file";
    private final String NAME_COLLECTION = "fileExample";
    private final String FILE_EXTENSION = "file_extention";

   private final ArrayList<String> name;

    public DatabaseController(ArrayList<String> name) {
        this.name = name;
    }

    public FindIterable<Document> getFileName() {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        return getCollection().find(Filters.and(filterExtension(), Date()));
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

    private Bson filterExtension() {
        return Filters.in(FILE_EXTENSION, name);
    }

    private BasicDBObject Date(){
        Date d = new Date();
        saveData(d);
        BasicDBObject query = new BasicDBObject();
        query.put("date", BasicDBObjectBuilder.start("$gt",getLastDate()).add("$lt", d).get());
        return query;
    }

    private void saveData(Date date){
        try {
            Writer fileWriter = new FileWriter("./lastDate.txt", false);
            fileWriter.write(date.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Date getLastDate(){

        try {
            Reader getDate = new FileReader("./lastDate.txt");
           StringBuilder builder = new StringBuilder();
           builder.append(getDate);
           String date = builder.toString();
           Date d = new Date(date);
           return d;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new Date();
        }
    }


}


