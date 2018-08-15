from threading import Thread
from time import sleep
from flask import Flask
from flask_restful import Api, Resource, reqparse
import serial
import requests
import RPi.GPIO as GPIO 


class Pump(Resource):
    def get(self, status):
        if status == 'on':
            print("Pump Turned on By API")
            # ser.write(b'1')
            GPIO.output(pumpPin, 0)
            return "Pump ON", 200
        elif status == 'off':
            print("Pump Turned off By API")
            # ser.write(b'0')
            GPIO.output(pumpPin, 1)
            return "Pump OFF", 200
        return "NOT FOUND", 404


def zigbeeDataToString(inputBin):
    inputStr = inputBin.decode('ascii')
    return inputStr


def stringToData(inputStr):
    # 'T10 = 23' (Temperature)
    if inputStr[0] == 'T':
        nodeNumber = inputStr[1: inputStr.find('=') - 1]
        return {'dataType': 'T', 'data': inputStr[len('T' + nodeNumber + ' = '):], 'nodeNumber': nodeNumber}

    # 'H10 = 100' (Humidity)
    elif inputStr[0] == 'H':
        nodeNumber = inputStr[1: inputStr.find('=') - 1]
        return {'dataType': 'H', 'data': inputStr[len('H' + nodeNumber + ' = '):], 'nodeNumber': nodeNumber}

    # 'SM10 = 100' (Soil Moisture)
    elif inputStr[0:2] == 'SM':
        nodeNumber = inputStr[2: inputStr.find('=') - 1]
        return {'dataType': 'SM', 'data': inputStr[len('SM' + nodeNumber + ' = '):], 'nodeNumber': nodeNumber}

    # 'PS0 = 1' Pump Status
    elif inputStr[0:2] == 'PS':
        nodeNumber = inputStr[2: inputStr.find('=') - 1]
        return {'dataType': 'PS', 'data': inputStr[len('PS' + nodeNumber + ' = '):], 'nodeNumber': nodeNumber}

    # 'PhR10 = 100' PhotoResistor
    elif inputStr[0:3] == 'PhR':
        nodeNumber = inputStr[3: inputStr.find('=') - 1]
        return {'dataType': 'PhR', 'data': inputStr[len('PhR' + nodeNumber + ' = '):], 'nodeNumber': nodeNumber}

    else:
        print("ERROR: Wrong data format.")


def sendData(inputData):
    payload = {'api_key': apiKeys[inputData["dataType"]][int(inputData["nodeNumber"])],
               'field1': inputData["data"]}
    r = requests.post("http://thingtalk.ir/update", data=payload)
    # print(r.status_code, r.reason)
    # print(r.text)
    if r.status_code == 200 and r.text != '-1':
        return True
    return False


def serialReadThread():
    while True:
        if ser.inWaiting() > 0:
            inputBin = ser.readline()
            # print("inputBin: " + inputBin)
            inputStr = zigbeeDataToString(inputBin)
            inputData = stringToData(inputStr)
            sendData(inputData)

        ### TEST THREAD WORKING ###
        # print("Enter 1 or 0:")
        # num = input()
        # ser.write(num.encode('ascii'))


apiKeys = {'T': ['G7KHR97UPN9OC5AC'],
           'H': ['7TPW8OQOGN1EMURD'],
           'SM': ['LUJ9D21E177HESAW'],
           'PS': ['XAKAVEUUJQ9GZGMT'],
           'PhR': ['B1JQYWFKX2PCRBYF']}

ser = serial.Serial(
    port='/dev/ttyAMA0',
    baudrate=9600,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=1
)

GPIO.setmode(GPIO.BOARD)
pumpPin = 40
GPIO.setup(pumpPin, GPIO.OUT, initial = 1)

app = Flask(__name__)
api = Api(app)
thread = Thread(target=serialReadThread)
thread.start()

api.add_resource(Pump, "/pump/<string:status>")

app.run(host="0.0.0.0")
thread.join()
