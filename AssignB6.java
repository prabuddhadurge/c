import java.util.Iterator;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase; 
import com.mongodb.util.JSON;
import com.mongodb.DBObject;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  

import org.json.simple.JSONObject;    
import org.json.simple.JSONObject;

public class AssignB6 { 

	public static void main( String args[] ) {  

		MongoClient mongo = new MongoClient( "127.0.0.1" , 27017 ); 

		MongoCredential credential; 
		credential = MongoCredential.createCredential("", "3332db", 
				"".toCharArray()); 
		System.out.println("Connected to the DB successfully");  

		MongoDatabase database = mongo.getDatabase("3332db"); 
		System.out.println("Credentials ::"+ credential);     

		MongoCollection<Document> collection = database.getCollection("col");
		System.out.println("Collection orders selected successfully"); 

		JSONObject obj[]=new JSONObject[2];
		   
		obj[0]=new JSONObject();
		obj[0].put("id",new Integer(10));    
		obj[0].put("title","android4");    
		obj[0].put("by","abc10"); 
		obj[0].put("likes",new Integer(75)); 
		
		obj[1]=new JSONObject();
		obj[1].put("id",new Integer(11));    
		obj[1].put("title","android5");    
		obj[1].put("by","abc11"); 
		obj[1].put("likes",new Integer(80)); 

		for(int i=0;i<2;i++)
		{
			Document doc = Document.parse(obj[i].toString()); 

			collection.insertOne(doc);
		}

	     

		FindIterable<Document> iterDoc = collection.find(); 
		int i = 1; 

		Iterator it = iterDoc.iterator(); 

		while (it.hasNext()) {  
			System.out.println(it.next());  
			i++; 
		}
		
	} 
}