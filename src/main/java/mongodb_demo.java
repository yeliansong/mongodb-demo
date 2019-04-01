
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;

import java.util.List;
import javax.swing.text.Document;
import java.util.ArrayList;

public class mongodb_demo{
    public static void main(String args[]){
        try{
            //connect db, localhost can be replaced with a remote server ip
            ServerAddress sAddr = new ServerAddress("localhost", 27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(sAddr);

            //auth username.password,database name
            MongoCredential credential = MongoCredential.createScramSha1Credential("root", "testLiansong", "123456".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
            credentials.add(credential);

            //connect db
            MongoClient mongoClient = new MongoClient(addrs,credentials);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("testLiansong");
            System.out.println("Connect to database successfully");

            //operate collection
            mongoDatabase.createCollection("test");
            System.out.println("create clump success");

            //operate document
            MongoCollection<org.bson.Document> collection = mongoDatabase.getCollection("test");
            System.out.println("get clump success");

            org.bson.Document doc = new org.bson.Document("title", "MongoDB").
            append("description", "database").  
            append("likes", 100).  
            append("by", "Fly");

            List<Document> documents = new ArrayList<Document>();
            collection.insertOne(doc);

            FindIterable<org.bson.Document> findIterable = collection.find();
            MongoCursor<org.bson.Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }

            collection.updateMany(Filters.eq("likes", 100), new org.bson.Document("$set", new org.bson.Document("likes", 200)));
            while(mongoCursor.hasNext()){  
                System.out.println(mongoCursor.next());  
             } 

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}