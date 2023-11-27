import socket
import random
import time

bufferSize  = 1024
serverAddressPort   = ("127.0.0.1", 7500)
clientAddressPort   = ("127.0.0.1", 7501)


print('this program will generate some test traffic for 2 players on the red ')
print('team as well as 2 players on the green team')
print('')

# red1 = input('Enter equipment id of red player 1 ==> ')
# red2 = input('Enter equipment id of red player 2 ==> ')
# green1 = input('Enter equipment id of green player 1 ==> ')
# green2 = input('Enter equipment id of green player 2 ==> ')

red1 = 1
red2 = 2
green1 = 3
green2 = 4

# Create datagram sockets
UDPServerSocketReceive = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
UDPClientSocketTransmit = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

# bind server socket
UDPServerSocketReceive.bind(serverAddressPort)

# wait for start from game software
print ('')
print ('waiting for start from game_software')

received_data = ' '
while received_data != '202':
    received_data, address = UDPServerSocketReceive.recvfrom(bufferSize)
    received_data = received_data.decode('utf-8')
    print ('Received from game software: '), received_data
print ('')

# create events, random player and order

while True:
    rand_number = random.random()

    if rand_number < 0.6:
        redplayer = red1 if random.randint(1,2) == 1 else red2
        greenplayer = green1 if random.randint(1,2) == 1 else green2

        if random.randint(1,2) == 1:
            shooter = redplayer
            hit = greenplayer
        else:
            shooter = greenplayer
            hit = redplayer

    elif rand_number < 0.85:
        if random.randint(1,2) == 1:
            if random.randint(1,2) == 1:
                shooter = red1
                hit = red2
            else:
                shooter = red2
                hit = red1
        else:
            if random.randint(1,2) == 1:
                shooter = green1
                hit = green2
            else:
                shooter = green2
                hit = green1
    else:
        # if random.randint(1,2) == 1:
        if random.randint(1,2) == 1:
            shooter = red1 if random.randint(1,2) == 1 else red2
            hit = 53
        else:
            shooter = green1 if random.randint(1,2) == 1 else green2
            hit = 43

    time.sleep(1)

    message = str(shooter) + ":" + str(hit)

    print(message)

    UDPClientSocketTransmit.sendto(str.encode(str(message)), clientAddressPort)
    # receive answer from game softare
    received_data, address = UDPServerSocketReceive.recvfrom(bufferSize)
    received_data = received_data.decode('utf-8')
    print ('Received from game software: '), received_data
    print ('')
    if received_data == '221':
        break
    time.sleep(random.randint(1,3))

print("program complete")
