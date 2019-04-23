from socket import *
import sys

s = socket(AF_INET,SOCK_DGRAM)
host = "127.0.0.1"
port = 5252
buffersize =1024
addr = (host,port)
s.connect(addr)
file_name= "test.txt"
s.send(file_name)
f=open(file_name,"rb")
data = f.read(buffersize)
print("Sending ...")
while (data):
    if(s.send(data)):
        data = f.read(buffersize)
s.close()
f.close()
