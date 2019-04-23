//package subnetf;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

public class subnetf {
	
	static String appendZeros(String s){
		String temp = new String("00000000");
		return temp.substring(s.length())+ s;
	}	
		
	
public static void ping(String ip_address) throws UnknownHostException, IOException {
	
	InetAddress ia =  InetAddress.getByName(ip_address);
	
    System.out.println("Pinging Request sent to " + ip_address);
    
    if(ia.isReachable(4000))
        System.out.println("ping successful" );
    else
        System.out.println("pinging failed ");


}
	
public static void main(String args[])throws UnknownHostException, IOException{
	
	double n;

	String ip;
	Scanner s =new Scanner(System.in);
	
	System.out.println("enter the network address");
	ip=s.nextLine();
	String [] split_ip = ip.split("\\.");
	
	
    System.out.println("enter the number of subnets");
  	n= s.nextInt();
  	int p;
  	p= (int)Math.ceil( Math.log(n)/Math.log(2));
  	
  	int ind=7,subMask=0;
 
  	for(int i=0;i<p;i++){
  		subMask = (int) Math.ceil(subMask + Math.pow(2,ind));
  		ind--;
  	}
  	
	StringJoiner dot = new StringJoiner(".");
  	String [] smask= new String [4]  ;
  	int c=Integer.parseInt(split_ip[0]);

    if(c>0 && c<=127){
    	System.out.println("class type: A");
    	System.out.println("Default mask : 255.0.0.0");
    	for(int i=0;i<4;i++) {
    		if(i<1)
    			smask[i]="255";
    		else if(i==1)
    			smask[i]=Integer.toString(subMask);
    		else 
    			smask[i]= "0";
    	}
    	for(String st:smask) dot.add(st);
    	System.out.println("Subnet mask : "+dot); 
    }
    else if(c>=128 && c<=191){
    	System.out.println("class type: B");
    	System.out.println("Default mask : 255.255.0.0");
    	for(int i=0;i<4;i++) {
    		if(i<2)
    			smask[i]="255";
    		else if(i==2)
    			smask[i]=Integer.toString(subMask);
    		else 
    			smask[i]= "0";
    	}
    	for(String st:smask) dot.add(st);
    	System.out.println("Subnet mask : "+dot); 
    }
    else if(c>=192 && c<=223){
    	System.out.println("class type: C");
    	System.out.println("Default mask : 255.255.255.0");
    	for(int i=0;i<4;i++) {
    		if(i<3)
    			smask[i]="255";
    		else if(i==3)
    			smask[i]=Integer.toString(subMask);
    		else 
    			smask[i]= "0";
    	}
 
    
    	for(String st:smask) dot.add(st);
    	System.out.println("Subnet mask : "+dot); 
    	
    
    }
    else if(c>=224 && c<=239){
    	System.out.println("class type: D");
    }
    else if(c>=240 && c<=255){
    	System.out.println("class type: E");
    }
    
  	
  	double noOfSub= Math.pow(2, ind+1); 
  	System.out.println("Number of possible networks in each subnet :"+noOfSub);
  	
    // ping(ip);
    
	String [] split_bip = new String[4];
	for(int i=0;i<4;i++){
		split_bip[i]= appendZeros(Integer.toBinaryString(Integer.parseInt(split_ip[i])));
	}
	
	String adr;
	try
	{
		System.out.println("Enter the IP address to ping:");
		adr=s.nextLine();
		adr=s.nextLine();
		Process p1=java.lang.Runtime.getRuntime().exec("ping -c 5 "+adr);
		BufferedReader reader=new BufferedReader(new InputStreamReader(p1.getInputStream()));
		String line="";
		while((line=reader.readLine())!=null)
		{
			System.out.println(line);
		}
		int returnVal=p1.waitFor();
		if(returnVal==0)
			System.out.println("\n\nHost Reachable!");
		else if(returnVal==2)
			System.out.println("Host Unreachable!");
		//if(line.contains("connect"))
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	try
	{
		String cmd="hostname -I";
		Process p2=Runtime.getRuntime().exec(cmd);
		BufferedReader reader=new BufferedReader(new InputStreamReader(p2.getInputStream()));
		System.out.println("IP address of this machine is:");
		String line="";
		while((line=reader.readLine())!=null)
		{
			System.out.println(line);
		}
		p2.waitFor();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	s.close();
	
	
}
}
