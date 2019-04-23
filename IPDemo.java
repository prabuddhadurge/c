import java.net.*; 
import java.util.*; 

public class IPDemo 
{ 
	public static void main(String[] args){ 
		String host; 
		
		Scanner input = new Scanner(System.in); 
		System.out.print("\n Enter host name: "); 
		host = input.nextLine(); 
		try { 
			InetAddress address = InetAddress.getByName(host);
			
			System.out.println("IP address: " + address.getHostAddress());
			//System.out.println("Host name : " + address.getHostName());  

			//System.out.println("Host name and IP address: " + address.toString()); 

	        
			//InetAddress address = InetAddress.getByName(new URL(urlString).getHost());
			//InetAddress addr = InetAddress.getByName("172.217.27.196");
 			 //String h = addr.getHostName();
  			//System.out.println(h);

  			//InetAddress host1 = InetAddress.getByName("172.217.160.196");
            //System.out.println(host1.getHostName());
		} 
		catch (UnknownHostException ex) {
		     System.out.println("Could not find " + host); 
		}
		String ip;
		System.out.print("\n Enter IP: "); 
		ip = input.nextLine(); 
		try { 
			

	        InetAddress address1 = InetAddress.getByName(ip);
			System.out.println("IP address: " + address1.getHostAddress());
			System.out.println("Host name : " + address1.getHostName());  
			//System.out.println("Host name and IP address: " + address1.toString()); 
		} 
		catch (UnknownHostException ex) {
		     System.out.println("Could not find " + ip); 
		}


	} 
}
