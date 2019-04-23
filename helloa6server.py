from socket import *

s = socket(AF_INET, SOCK_STREAM)
ip = "127.0.0.1"
port = 4446
s.bind((ip,port))
s.listen(4)
da, addr = s.accept()
msg = da.recv(1024)
print(msg)
da.send("Hello from server")
s.close()