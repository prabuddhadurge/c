#include <iostream>
#include <stdio.h>
#include <sys/socket.h>
#include<arpa/inet.h>
#include<stdlib.h>
#include<string.h>
#include <unistd.h>
#include <errno.h>
#include<fstream>
#include<cmath>

#define PORT_NUMBER     5001
#define SERVER_ADDRESS "127.0.0.1"
#define PI 3.14159265

using namespace std;

class myServer
{
	int server_socket,client_socket;
	sockaddr_in server_addr,client_addr;
	socklen_t sock_len;

public:
	myServer()
	{
		sock_len = sizeof(client_addr);
	}
	
	void screate();
	void sconstruct();
	void sbind();
	void slisten();
	void saccept();
	void sclose()
	{
		close(client_socket);
        	close(server_socket);
	}
	void schat();
	void sfile();
	void arith();
		
};

void myServer::screate()
{
	server_socket = socket(AF_INET, SOCK_STREAM, 0);
        if (server_socket == -1)
        {
                fprintf(stderr, "Error creating socket --> %s", strerror(errno));

                exit(1);
        }
	else
	{
		cout<<"Socket created Successfully\n";
	}
	int en=1;
	if (setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &en, sizeof(int)) < 0)
   	{ fprintf(stderr, "Error reusing socket --> %s\n", strerror(errno)); }

}

void myServer::sconstruct()
{
	/* Zeroing server_addr struct */

	memset(&server_addr, 0, sizeof(server_addr));

	/* Construct server_addr struct */

	server_addr.sin_family = AF_INET;
	inet_pton(AF_INET, SERVER_ADDRESS, &(server_addr.sin_addr));
	server_addr.sin_port = htons(PORT_NUMBER);

}

void myServer::sbind()
{
	 if ((bind(server_socket, (struct sockaddr *)&server_addr, sizeof(struct sockaddr))) == -1)
        {
                fprintf(stderr, "Error on bind --> %s", strerror(errno));

                exit(1);
        }
	else
	{
		cout<<"Binding Successful \n";
	}
	
}

void myServer::slisten()
{
	 if ((listen(server_socket, 5)) == -1)
        {
                fprintf(stderr, "Error on listen --> %s", strerror(errno));

                exit(1);
        }
	else
	{
		cout<<"Listening to client\n";
	}

}

void myServer::saccept()
{
	/* Accepting incoming peers */
        client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &sock_len);
        if (client_socket == -1)
        {
                fprintf(stderr, "Error on accept --> %s", strerror(errno));

                exit(1);
        }
	else
	{
		 fprintf(stdout, "Accept peer --> %s\n", inet_ntoa(client_addr.sin_addr));
	}
       
}

void myServer::schat()
{
	char buffer[256];
	cin.ignore();
	while(1)
	{ 
		cout <<endl<< "..." << endl;
		bzero(buffer,256);
		recv(client_socket,buffer,255,0);
		cout<<"Client: "<<buffer<<endl;
		if(buffer[0]=='e' && buffer[1]=='x' && buffer[2]=='i' && buffer[3]=='t'){
			sclose();
			cout<<"Termination of Connection";
 			return;
		}
		cout<<"\nmsg >> ";
		
		string data;
		//cin.ignore();
		getline(cin,data);
		bzero(buffer,256);
		strcpy(buffer,data.c_str());
		
		//cin.clear();
		// fflush(stdin);
		send(client_socket,(char*)&buffer,strlen(buffer),0);

		if(buffer[0]=='e' && buffer[1]=='x' && buffer[2]=='i' && buffer[3]=='t'){
			sclose();
			cout<<"Termination of Connection";
 			return;
		}
	}

}

void myServer::sfile()
{
	long long int msg_len,file_size;

		char filename[BUFSIZ];
		
		fstream fout;

		bzero((char *)filename,sizeof(filename));

		msg_len = recv(client_socket,filename,BUFSIZ,0); //receive filename

		if(msg_len==-1)
		{
			fprintf(stderr, "Error receiving filename --> %s", strerror(errno));
		}
		else
		{
			filename[msg_len]='\0';
			cout<<"File requested by Client: "<<filename<<endl;
		}

		fout.open(filename,ios::in|ios::out|ios::binary);
		fout.seekg(0,ios::end);

		file_size = fout.tellg(); //get file size

		char *filebuff=new char[file_size];

		fout.seekg(0,ios::beg);

		fout.read(filebuff,file_size); //reading file content


		msg_len = send(client_socket,filebuff,file_size,0); //send file contents
		if(msg_len==-1)
			fprintf(stderr, "Error sending file --> %s", strerror(errno));
		else
			cout<<"\nTransmission Successful\n";


		fout.close();
		
		sclose();
}

void myServer::arith()
{
	float fno,sno,un3;
	float result;
	char firstno[5],secondno[5],s_ans[20],moperand[5];
	

	while(1)
	{
		
		int n1=recv(client_socket,firstno,5,0);
		fno=atof(firstno);	//atof - string to floating point number
		bzero((char *)firstno,sizeof(firstno));		
		cout<<"First number is "<<fno <<endl;
		
		

		
		int n3=recv(client_socket,secondno,5,0);
		sno=atof(secondno);	//atof - string to floating point number
		bzero((char *)secondno,sizeof(secondno));
		cout<<"Second number is "<<sno <<endl;
		
		
		
		int n4=recv(client_socket,moperand,5,0);
		un3=atof(moperand);	//atof - string to floating point number
		bzero((char *)moperand,sizeof(moperand));
		cout<<"operand is "<<un3<<endl;
	
     if(un3 == 1)
     {
        result = fno + sno;
     }
     else if(un3 == 2)
     {

        result = fno - sno;
     }
     else if(un3 == 3)
     {
        result = fno * sno;
     }
     else if(un3 == 4)
     {
     	result = fno / sno;
     }
		
		
		cout<<"Answer is : "<<result<<endl;
		bzero((char *)s_ans,sizeof(s_ans));
		snprintf(s_ans,20,"%f",result);
		send(client_socket,s_ans,20,0);

		
		sclose();
		break;
	}
	
	
}

int main()
{
	myServer ms;
	int choice;

	do{

	cout<<"\n1.Chat \n";
	cout<<"2.File Transfer\n";
	cout<<"3.Arithmatic Calculator\n";
	cout<<"4.Exit\n";
	cin>>choice;

	switch(choice)	
	{
	case 1:
		
		ms.screate();
		ms.sconstruct();
		ms.sbind();
		ms.slisten();
		ms.saccept();
		ms.schat();

		
	break;
	case 2:
		ms.screate();
		ms.sconstruct();
		ms.sbind();
		ms.slisten();
		ms.saccept();
		ms.sfile();
	
	break;
	case 3:

		ms.screate();
		ms.sconstruct();
		ms.sbind();
		ms.slisten();
		ms.saccept();
		ms.arith();
		ms.sclose();

	break;

	case 4:
		ms.sclose();
		exit(1);
	}
	}while(choice<=4);

return 0;

}
