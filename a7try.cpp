#include<iostream>
#include<fstream>
#include<iomanip>
#include<string>
using namespace std;

int main()
{
	cout<<"--------------------Packet Analyser--------------------------"<<endl;
	string sno,time,source,destination,protocol,len,info;
	int choice;
	
	do
	{
		ifstream file("a7.csv");
		int i=0, count=-1;
		cout<<"Enter your Choice :\n 1.IP\n 2.UDP\n 3.TCP\n 4.Ethernet\n 5.exit"<<endl;
		cin>>choice;
		string protocolChoice;
		
		switch(choice)
		{
			case 1 : protocolChoice = "ICMP";
				break;
			case 2 : protocolChoice = "UDP";
				break;
			case 3 : protocolChoice = "TCP";
				break;
			case 4 : protocolChoice = "ARP";
				break;
			default : cout<<"Enter valid option"<<endl;
				break;		
			
		}
		while(file.good() and choice!=5)
		{	
			getline(file,sno,',');
			getline(file,time ,',');
			getline(file,source ,',');
			getline(file,destination ,',');
			getline(file,protocol,',');
			getline(file,len,',');
			getline(file,info,'\n');
			
			
			protocol = string(protocol,1,protocol.length()-2);
			
			if(protocol=="Protocol"||protocol==protocolChoice)
			{
				cout<<setw(4)<<left<<i++;
				cout<<setw(20)<<left<<string(time,1,time.length()-2);
				cout<<setw(30)<<left<<string(source,1,source.length()-2);
				cout<<setw(30)<<left<<string(destination,1,destination.length()-2);
				cout<<setw(10)<<left<<protocol;
				cout<<setw(8)<<left<<string(len,1,len.length()-2);
				cout<<string(info,1,info.length()-2)<<"\n\n";
				count++;
			}
			
		}
		file.close();
		if(choice!=5)
		cout<<"Total number of packets : "<<count<<endl;
		
	}while(choice!=5);
	return 0;

}

