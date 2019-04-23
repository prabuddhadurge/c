import java.net.*; 
import java.util.*; 

public class Dns
{ 
	public static void main(String[] args){ 
		String host; 
		
		Scanner input = new Scanner(System.in); 
		System.out.print("\n PLEASE ENTER HOSTNAME : "); 
		host = input.nextLine(); 
		try { 
			InetAddress address = InetAddress.getByName(host);
			
			System.out.println("IP ADDRESS IS : " + address.getHostAddress());
			System.out.println("HOSTNAME : " + address.getHostName());  

			System.out.println("HOSTNAME AND IP : " + address.toString()); 

	        
			//InetAddress address = InetAddress.getByName(new URL(urlString).getHost());
			//InetAddress addr = InetAddress.getByName("172.217.27.196");
 			 //String h = addr.getHostName();
  			//System.out.println(h);

  			//InetAddress host1 = InetAddress.getByName("172.217.160.196");
            //System.out.println(host1.getHostName());
		} 
		catch (UnknownHostException ex) {
		     System.out.println("NOT FOUND..CHECK CONNECTIVITY " + host); 
		}
		String ip;
		System.out.print("\n ENTER IP : "); 
		ip = input.nextLine(); 
		try { 
			

	        InetAddress address1 = InetAddress.getByName(ip);
			System.out.println("IP ADDRESS: " + address1.getHostAddress());
			System.out.println("HOSTNAME : " + address1.getHostName());  
			System.out.println("HOSTNAME AND IP : " + address1.toString()); 
		} 
		catch (UnknownHostException ex) {
		     System.out.println(" NOT FOUND..CHECK CONNECTIVITY 9" + ip); 
		}


	} 
}
