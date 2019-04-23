import socket

host=input("Enter host name: ")
addr1=socket.gethostbyname(host)
print(addr1)
ip=input("Enter ip address: ")
addr2=socket.gethostbyaddr(ip)
print(addr2)
