from socket import *

s = socket(AF_INET, SOCK_DGRAM)
mysock = socket(AF_INET, SOCK_DGRAM)
ip = "127.0.0.1"
port = 1337
mysock.bind((ip,port))
remoteip = "127.0.0.1"
remoteport = 31337
remoteaddr = (remoteip,remoteport)

while(True):
	message = mysock.recv(1024)
	print(message)
	msg = raw_input("Enter your message (q for quit) >>")
	if(msg is "q"):
		s.sendto("Bye!",remoteaddr)
		break
		s.close()
	else:
		s.sendto(msg,remoteaddr)