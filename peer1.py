from socket import *

s = socket(AF_INET, SOCK_DGRAM)
mysock = socket(AF_INET, SOCK_DGRAM)
ip = "127.0.0.1"
port = 31337
mysock.bind((ip,port))
remoteip = "127.0.0.1"
remoteport = 1337
remoteaddr = (remoteip,remoteport)

while(True):
	msg = raw_input("Enter your message (q for quit) >>")
	if(msg is "q"):
		s.sendto("Bye!", remoteaddr)
		break
		s.close()
	else:
		s.sendto(msg, remoteaddr)
		message = mysock.recv(1024)
		print(message)