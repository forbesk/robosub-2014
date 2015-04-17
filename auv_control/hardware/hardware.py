from abc import ABCMeta, abstractmethod

import zmq
import serial
import struct

class BaseConnection(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def connect(self, address, port):
        pass

    @abstractmethod
    def disconnect(self):
        pass

    @abstractmethod
    def request_waiting(self):
        pass

    @abstractmethod
    def send(self, response):
        pass

    @abstractmethod
    def receive(self):
        pass

class ZMQConnection(BaseConnection):
    def __init__(self):
        super(BaseConnection)
        self.context = zmq.Context()
        self.poller = zmq.Poller()

    def connect(self, address):
        self.address = "tcp://%s" % address
        self.socket = socket = self.context.socket(zmq.REP)
        socket.bind(self.address)
        self.poller.register(socket, zmq.POLLIN)

    def disconnect(self):
        self.poller.unregister(self.socket)
        self.socket.unbind()
        self.context.term()

    def request_waiting(self):
        available = dict(self.poller.poll(timeout=10))
        return self.socket in available

    def send(self, response):
        try:
            self.socket.send_json(response, flags=zmq.NOBLOCK)
        except:
            raise ResponseError("The response %s could not be sent" % response)

    def receive(self):
        try:
            return self.socket.recv_json(flags=zmq.NOBLOCK)
        except Exception as exception:
            raise RequestError(exception)

class RequestError(ValueError):
    '''Exception raised when a request was not successful.'''

class ResponseError(ValueError):
    '''Exception raised when a response was not successful.'''

def conv2hex(number):
    return abs(int(number))

def getcompassdata(s):
    s.flushInput()
    while 1:
        data_bytes = []
        byte1 = s.read(1).encode("hex")
        if byte1 == '00':
            byte2 = s.read(1).encode("hex")
            if byte2 == '26':
                data_bytes.append(byte1)
                data_bytes.append(byte2)
                for i in range(0, 34):
                    data_bytes.append(s.read(1).encode("hex"))

                msg = str(int(data_bytes[5], 16)) + ' '
                msg = msg + str(int(data_bytes[6], 16)) + ' '
                msg = msg + str(int(data_bytes[7], 16)) + ' '
                msg = msg + str(int(data_bytes[8], 16))

                heading = struct.unpack('!f', ''.join(data_bytes[5:9]).decode("hex"))[0]
                pitch = struct.unpack('!f', ''.join(data_bytes[10:14]).decode("hex"))[0]
                roll = struct.unpack('!f', ''.join(data_bytes[15:19]).decode("hex"))[0]
                gyroX = struct.unpack('!f', ''.join(data_bytes[22:26]).decode("hex"))[0]
                gyroY = struct.unpack('!f', ''.join(data_bytes[27:31]).decode("hex"))[0]
                gyroZ = struct.unpack('!f', ''.join(data_bytes[32:36]).decode("hex"))[0]
                return {'heading': heading, 'pitch': pitch, 'roll': roll, 'gyroX': gyroX, 'gyroY': gyroY, 'gyroZ': gyroZ}

            #else:
                #break


socket = ZMQConnection()
socket.connect("127.0.0.1:5560")

arduino_serial = serial.Serial("/dev/ttyACM0", 19200, timeout=0.02)
compass_serial = serial.Serial("/dev/ttyS0", 38400, timeout=1)

comp_conf = (0x00, 0x0d, 0x03, 0x07, 0x05, 0x18, 0x19, 0x4f, 0x4a, 0x4b, 0x4c, 0x98, 0x40)
comp_start = (0x00, 0x05, 0x15, 0xbd, 0x61)
comp_reset = (0x00, 0x05, 0x6e, 0x72, 0x9d)
compass_serial.write(bytearray(comp_conf))
compass_serial.write(bytearray(comp_start))

missionswitch = 1
missionSwitchToggle = 0;
maxSpeed = 1.0

while(1):
    if(socket.request_waiting()):
        response = socket.receive()

        if response['heaveRight']['speed'] > maxSpeed : response['heaveRight']['speed'] = maxSpeed 
        if response['heaveRight']['speed'] < -maxSpeed : response['heaveRight']['speed'] = -maxSpeed 
        if response['heaveLeft']['speed'] > maxSpeed : response['heaveLeft']['speed'] = maxSpeed
        if response['heaveLeft']['speed'] < -maxSpeed : response['heaveLeft']['speed'] = -maxSpeed 
        if response['surgeRight']['speed'] > maxSpeed : response['surgeRight']['speed'] = maxSpeed 
        if response['surgeRight']['speed'] < -maxSpeed : response['surgeRight']['speed'] = -maxSpeed 
        if response['surgeLeft']['speed'] > maxSpeed : response['surgeLeft']['speed'] = maxSpeed 
        if response['surgeLeft']['speed'] < -maxSpeed : response['surgeLeft']['speed'] = -maxSpeed 
        if response['sway']['speed'] > 0.75 : response['sway']['speed'] = 0.75  #limit sway to 75%
        if response['sway']['speed'] < -0.75 : response['sway']['speed'] = -0.75  #limit sway to -75%

        asout =   (0x7f, #Start byte for indexing
        0x01, 
        0x00 if response['heaveRight']['speed'] < 0 else 0x01, 
        conv2hex(response['heaveRight']['speed'] * 100),
        0x02, 
        0x00 if response['surgeRight']['speed'] < 0 else 0x01, 
        conv2hex(response['surgeRight']['speed'] * 100),
        0x03, 
        0x00 if response['surgeLeft']['speed'] > 0 else 0x01, 
        conv2hex(response['surgeLeft']['speed'] * 100),
        0x04, 
        0x00 if response['heaveLeft']['speed'] < 0 else 0x01, 
        conv2hex(response['heaveLeft']['speed'] * 100),
        0x05, 
        0x00 if response['sway']['speed'] > 0 else 0x01, 
        conv2hex(response['sway']['speed'] * 100),
        0x0d,
        conv2hex(response['indicatorLights']['white'] * 100),
        0x0b,
        conv2hex(response['indicatorLights']['red'] * 100),
        0x0a,
        conv2hex(response['indicatorLights']['green'] * 100),
        0x09,
        conv2hex(response['indicatorLights']['blue'] * 100),
        0x00, 0x00)	#empty bytes!

        arduino_serial.write(bytearray(asout))
        msg = arduino_serial.read(3)

        #sensor * 5.0f / 1024.0f) - 1.828f) * 49.0f * 3.909f;
        if len(msg) >= 3:
            response['depthGauge']['depth'] = (((ord(msg[0])*256 + ord(msg[1])) * 5.0/1024.0) - 1.828) * 49.0 * 3.909 + 3
            if ord(msg[2]) == 0 and missionswitch == 1: 
                response['missionSwitch']['on'] = 1 - response['missionSwitch']['on']
            missionswitch = ord(msg[2])
            print missionswitch

        if response['missionComplete'] == 1:
            response['missionSwitch']['on'] = 0

        compdata = getcompassdata(compass_serial)
        response['compass']['heading'] = compdata['heading']
        response['compass']['pitch'] = compdata['pitch']
        response['compass']['roll'] = compdata['roll']	
        response['compass']['gyroX'] = compdata['gyroX']
        response['compass']['gyroY'] = compdata['gyroY']
        response['compass']['gyroZ'] = compdata['gyroZ']

	if response['compass']['reset'] == 1 :
           compass_serial.write(bytearray(comp_reset)) 
           response['compass']['reset'] = 0

        socket.send(response)
	
	
