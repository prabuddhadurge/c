//============================================================================

#include <iostream>
#include <string>
#include <string.h>
#include <math.h>
#include <sstream>
using namespace std;

class crc
{
	string dataword;
	string codeword;
	string generator;
	int ldword,lcword,r,g;
	public:
	crc()
	{
		ldword=lcword=r=0;

	}

	void get();
	string generateCode();
	void checker(int );
	string mod2div(string);
	void result(int );
	int check();

};

int crc::check()
{
	if(dataword[0]!='0')
	{
		return 1;
	}
	else
		return 0;
}


void crc::get()
{
cout<<"Enter the Dataword : "<<endl;
cin>>dataword;
cout<<"Enter the Generator :"<<endl;
cin>>generator;

}

string crc::generateCode()
{
	r=generator.length()-1;
	string z="0";
	string dataw;
	dataw=dataword;
	for(int i=r;i>0;i--)
	{
		dataw.append(z);
	}
return dataw;
}
string crc::mod2div(string dword)
{

	cout<<"dataword:"<<dword<<endl;
	g=generator.length();
	int d= dword.length();
	char rem[g];
	char rem1[g-1];
	int k=0;
	for(int i=0;i<g;i++)
	{
		rem[i]=dword[i];
		k++;
	}

	for( k;k<=d;k++)
	{
		if(rem[0]=='1')
		{
			for(int j=0;j<g;j++)
			{

			    if((rem[j]=='1' && generator[j]=='1') || (rem[j]=='0' && generator[j]=='0'))
			    {
				rem[j]='0';
			    }
			    else
			    {
				rem[j]='1';
			    }
			}
		}
		for(int j=0;j<g-1;j++)
		{
			rem[j]=rem[j+1];
		}
		if(k<d)
		{
			rem[g-1]=dword[k];
		}
		else if(k>=d)
		{
			rem[g-1]=0;
		}

	cout<<"remc:"<<rem<<endl;
	}


	cout<<rem<<endl;

	return rem;
}

void crc::checker(int choice)
{
	
	
	
	string dataWord;
	dataWord=generateCode();
	string cr;
	int x;
	if(choice==1)
	{
		cr=mod2div(dataWord);
		cout<<"cr: "<<cr<<endl;
		codeword= dataword+cr;

		cr= mod2div(codeword);
	}
	else if(choice==2)
	{
		cr=mod2div(dataword);
		//cout<<cr<<endl;
		if(cr[0]=='0')
			cr[0]='1';
		else
			cr[0]='0';
		//cout<<cr<<endl;
		codeword= dataword+cr;
		cr= mod2div(codeword);
	}

	stringstream sst(cr);
	sst>>x;
	result(x);
	
}
	
	



void crc::result(int x)
{
	if(x==0)
			cout<<"as the syndrome is zero CRC passed"<<endl;
		else
			cout<<"as the syndrome is not zero CRC failed"<<endl;
}

int main() {
	crc c;
	int ch;
	c.get();
	int p=c.check();
	if(p==1)
	{
		cout<<"enter Your choice :\n 1.pass condition\n 2.fail condition"<<endl;
		cin>>ch;
		switch(ch)
		{
			case 1:c.checker(1);
					break;
			case 2:c.checker(2);
					break;
			default:cout<<"Invalid Input "<<endl;
		}
	}
	else
	{
		cout<<"datawaord should not start with 0"<<endl;
	}
	//c.checker();
	return 0;

}
