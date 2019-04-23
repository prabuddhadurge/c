from socket import *
import re
import math

s = socket(AF_INET, SOCK_STREAM)
ip = "127.0.0.1"
port = 12344
s.bind((ip,port))
s.listen(4)
c, addr = s.accept()
msg = str(c.recv(1024))
functiontype = re.findall(r'sin|cos|tan',msg)
value = re.findall(r'\d{1,3}',msg)
if 'sin' in functiontype:
	c.send(str(math.sin(int(value[0]))))
elif 'cos' in functiontype:
	c.send(str(math.cos(int(value[0]))))
elif 'tan' in functiontype:
	c.send(str(math.tan(int(value[0]))))
s.close()
