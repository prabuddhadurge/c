from socket import *

s = socket(AF_INET, SOCK_STREAM)
ip = "127.0.0.1"
port = 12344
s.connect((ip,port))
msg = raw_input("Enter Trignometric expression eg. sin 60, tan 30 > ")
s.send(str(msg))
print(str(s.recv(1024)))
s.close()