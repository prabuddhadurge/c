from socket import *

s = socket(AF_INET, SOCK_STREAM)
ip = "127.0.0.1"
port = 4446
s.connect((ip,port))
s.send("Hello from client")
servermsg = s.recv(1024)
print(servermsg)
s.close()