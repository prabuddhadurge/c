//package subnet;

import java.util.*;
import java.io.*;

public class subnet 
{
	public static void main(String args[])
	{
		subnet sb=new subnet();
		Scanner s=new Scanner(System.in);
		int ch;
		do
		{
			System.out.println("\n1.Calculate subnet mask from given ip address \n2.Set Ip address and subnet mask to PC \n3.Ping to given IP address \n4.Show IP address of PC \n5.Exit \nEnter your choice:");
			ch=s.nextInt();
			
			switch(ch)
			{
			case 1:	
				sb.calculate_subnet();
				break;
				
			case 2:
				try
				{
				//String ip,mask;
				//System.out.println("Enter the IP address and subnet mask to set:");
				//ip=s.nextLine();
				//mask=s.nextLine();
				//mask=s.nextLine();
					String cmd="sudo ifconfig lo 192.168.10.1 netmask 255.255.255.0";
					Process p=Runtime.getRuntime().exec(cmd);
					BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line="";
					while((line=reader.readLine())!=null)
					{
						System.out.println(line);
					}
					p.waitFor();
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				break;
				
			case 3:
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
					else if(returnVal==256)
						System.out.println("Host is Shutdown!");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				break;
				
			case 4:
				try
				{
					String cmd="hostname -I";
					Process p2=Runtime.getRuntime().exec(cmd);
					BufferedReader reader=new BufferedReader(new InputStreamReader(p2.getInputStream()));
					System.out.println("IP address is:");
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
				break;
				
			}
		}while(ch!=5);
		s.close();
	}
	
	void calculate_subnet()
	{
		Scanner s=new Scanner(System.in);
		System.out.print("Enter the ip address=");
		String ip=s.nextLine();
		String split_ip[] = ip.split("\\.");
		String split_bip[]= new String[4];
		String bip = "";

		for(int i=0;i<4;i++)
		{
			split_bip[i]=appendZeroes(Integer.toBinaryString(Integer.parseInt(split_ip[i])));
			bip+=split_bip[i];
		}
		int ch;
		
		do
		{
			
		System.out.println("\n\n1.Determine Class of IP address \n2.Calculate Subnet Mask \n3.Exit \nEnter your choice:");
		ch=s.nextInt();
		switch(ch)
		{
		
		case 1:
			int ip1=Integer.parseInt(split_ip[0]);
			if(ip1>=0 && ip1<=127)
			{
				System.out.println("Ip address belongs to Class A");
				System.out.println("Default subnet mask is:255.0.0.0");
			}
			else if(ip1>=128 && ip1<=191)
			{
				System.out.println("Ip address belongs to Class B");
				System.out.println("Default subnet mask is:255.255.0.0");
			}
			else if(ip1>=192 && ip1<=223)
			{
				System.out.println("Ip address belongs to Class C");
				System.out.println("Default subnet mask is:255.255.255.0");
			}
			else if(ip1>=224 && ip1<=239)
			{
				System.out.println("Ip address belongs to Class D");
			}
			else if(ip1>=240 && ip1<=255)
			{
				System.out.println("Ip address belongs to Class E");
			}
			break;
			
		case 2:
			
			System.out.println("\nIp Address in binary format="+bip);
			
			System.out.println("\nEnter the number of adresses in each subnet:");
			int n=s.nextInt();
			
			int bits=(int)Math.ceil(Math.log(n)/Math.log(2));
			System.out.println("\nNumber of bits required:"+bits);
			
			int mask=32-bits;
			System.out.println("\nSubnet mask="+mask);
			
			int total_address=(int)Math.pow(2,bits);
			
			//---- Finding the first and last address----

			//---- First address Calculation--------
			int fbip[]=new int[32];

			for(int i=0;i<32;i++)
			{
			//Convert to the character 1,0 to integer 1,0

				fbip[i]=(int)bip.charAt(i)-48;

			}

			for(int i=31;i>31-bits;i--)
			{
			//Get first address by anding the last bits with 0

				fbip[i] &=0;
			}

			String fip[]={"","","",""};
			for(int i=0;i<32;i++)
			{
				fip[i/8]=new String(fip[i/8]+fbip[i]);
			} 
			int first_offset=0;
			int ipAddr[]=new int[4];  	;
			System.out.println("\nGROUP 1 \n FIRST ADDRESS:");
			for(int i=0;i<4;i++)
			{
				System.out.print(ipAddr[i]=first_offset=Integer.parseInt(fip[i],2));
				if(i!=3)
					System.out.print(".");
			}

			//--- Last address Calculation----
			int lbip[]=new int [32];

			for(int i=0;i<32;i++)
			{
			// Convert the character 1,0 to integer 1,0

				lbip[i]=(int)bip.charAt(i)-48;
			}

			for(int i=31;i>31-bits;i--)
			{

			// Get last address by oring with last bits with 1

				lbip[i]|= 1;
			}
			String lip[]={"","","",""};
			for(int i=0;i<32;i++)
			{
				lip[i/8]=new String(lip[i/8]+lbip[i]);
			}
			int ipLast[]=new int[4]; 
			System.out.println("\n LAST ADDRESS:");
			for(int i=0;i<4;i++)
			{
				System.out.print(ipLast[i]=Integer.parseInt(lip[i],2));
				if(i!=3)
					System.out.print(".");
			}
			System.out.println();
			//System.out.println("\nHow many subnets do you want to form?");
			int scount=256/n;
			for(int j=1;j<scount;j++)
			{
				System.out.println("\nGROUP "+ (j+1));
				System.out.println(" FIRST ADDRESS:");
				for(int i=0;i<4;i++)
				{
					if(i<3)
					{
						System.out.print(ipAddr[i]+".");
					}
					else
						System.out.print(ipAddr[i]=ipAddr[i]+total_address);
				}
				System.out.println("\n LAST ADDRESS:");
				for(int i=0;i<4;i++)
				{
					if(i<3)
					{
						System.out.print(ipLast[i]+".");
					}
					else
						System.out.print(ipLast[i]=ipLast[i]+total_address);
				}
				System.out.println();
			}
			break;
		
		}
		
		}while(ch!=3);
		
	s.close();
	}
	
	static String appendZeroes(String s)
	{
		String temp= new  String("00000000");
		return temp.substring(s.length())+ s;
	}
}
