from socket import *
import sys


s = socket(AF_INET,SOCK_STREAM)
host="127.0.0.1"
port = 4444
addr = (host,port)
s.bind(addr)
s.listen(4)
buffersize=1024	
da ,addr = s.accept()
filename = da.recv(1024)
print("Received ",filename)
f = open(filename,'wb')
data = da.recv(buffersize)
while(data):
    f.write(data)
    data = da.recv(buffersize)
f.close()
s.close()
print("File Downloaded")