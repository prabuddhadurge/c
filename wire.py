import re

def tcp():
	with open("data.csv","r") as f:
		for line in f.readlines():
			if((re.findall(r',"TCP"',line)) or re.findall(r',"HTTP"',line)):
				print(line)

def udp():
	with open("data.csv","r") as f:
		for line in f.readlines():
			if(re.findall(r',"DNS"',line)):
				print(line)

def ICMP():
	with open("data.csv","r") as f:
		for line in f.readlines():
			if(re.findall(r',"ICMP"',line)):
				print(line)

while(True):
	ch = int(raw_input("1: TCP\n2: UDP\n3: ICMP\n4: Exit\n"))
	if ch is 1:
		tcp()
	elif ch is 2:
		udp()
	elif ch is 3:
		ICMP()
	elif ch is 4:
		break
	else:
		break
