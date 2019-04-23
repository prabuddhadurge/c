
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String args[] ){
		try{
			ServerSocket ss= new ServerSocket(3330);
			Socket server =ss.accept();
			DataInputStream dis = new DataInputStream(server.getInputStream());
			DataOutputStream dos = new DataOutputStream(server.getOutputStream());
			
			BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			
			String str1="" , str2 = "" ,str="stop";
			while(!str1.equalsIgnoreCase(str)){
			    str1= (String)dis.readUTF();
				System.out.println("Client says : "+ str1);
				str2= br.readLine();
				dos.writeUTF(str2);
				dos.flush();
			}
			dis.close();
			server.close();
			ss.close();
			
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
