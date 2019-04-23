
import java.net.*;
import java.io.*;

public class Client {
	public static void main(String args[]){
		try{
	
		String ip="localhost";
		int port=3330;
	    Socket client = new Socket(ip,port);
	   // String str="Hello Server";
	    DataOutputStream out= new DataOutputStream(client.getOutputStream());
	    DataInputStream din =new DataInputStream(client.getInputStream());
	    

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		String str1="" , str2 = "" ,str="stop";
		while(!str1.equalsIgnoreCase(str)){
			str2= br.readLine();
			out.writeUTF(str2);
			out.flush();
		    str1= (String)din.readUTF();
			System.out.println("Server says : "+ str1);
			
		}
		out.close();
		client.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	    
	}
}
