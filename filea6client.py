from socket import *
import sys
import time

s = socket(AF_INET,SOCK_STREAM)
host = "127.0.0.1"
port = 4444
buffersize =1024
addr = (host,port)
s.connect(addr)
file_name= "flower.jpg"
s.send(file_name)
time.sleep(1)
f=open(file_name,"rb")
data = f.read(buffersize)
print("Sending ...")
while (data):
     if(s.send(data)):
         data = f.read(buffersize)
s.shutdown(SHUT_WR)
s.close()
f.close()