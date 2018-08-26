from threading import Thread
# from time import sleep
from flask import Flask
# from flask_restful import Api, Resource
import serial
import requests
import RPi.GPIO as GPIO
import datetime

app = Flask(__name__)


@app.route("/pump/<status>")
def pumpControl(status):
    if status == 'on':
        print("Pump Turned on By API")
        # ser.write(b'1')
        GPIO.output(pumpPin, GPIO.LOW)
        sendData(stringToData('PS0 = 1\r\n'))
        return "Pump ON", 200
    elif status == 'off':
        print("Pump Turned off By API")
        # ser.write(b'0')
        GPIO.output(pumpPin, GPIO.HIGH)
        sendData(stringToData('PS0 = 0\r\n'))
        return "Pump OFF", 200
    return "NOT FOUND", 404


def zigbeeDataToString(inputBin):
    inputStr = inputBin.decode('ascii')
    return inputStr


def stringToData(inputStr):
    # 'T10 = 23' (Temperature)
    if inputStr[0] == 'T':
        nodeNumber = inputStr[1: inputStr.find('=') - 1]
        return {'dataType': 'T', 'data': inputStr[len('T' + nodeNumber + ' = '):-2], 'nodeNumber': nodeNumber}

    # 'H10 = 100' (Humidity)
    elif inputStr[0] == 'H':
        nodeNumber = inputStr[1: inputStr.find('=') - 1]
        return {'dataType': 'H', 'data': inputStr[len('H' + nodeNumber + ' = '):-2], 'nodeNumber': nodeNumber}

    # 'SM10 = 100' (Soil Moisture)
    elif inputStr[0:2] == 'SM':
        nodeNumber = inputStr[2: inputStr.find('=') - 1]
        return {'dataType': 'SM', 'data': inputStr[len('SM' + nodeNumber + ' = '):-2], 'nodeNumber': nodeNumber}

    # 'PS0 = 1' Pump Status
    elif inputStr[0:2] == 'PS':
        nodeNumber = inputStr[2: inputStr.find('=') - 1]
        return {'dataType': 'PS', 'data': inputStr[len('PS' + nodeNumber + ' = '):-2], 'nodeNumber': nodeNumber}

    # 'PhR10 = 100' PhotoResistor
    elif inputStr[0:3] == 'PhR':
        nodeNumber = inputStr[3: inputStr.find('=') - 1]
        return {'dataType': 'PhR', 'data': inputStr[len('PhR' + nodeNumber + ' = '):-2], 'nodeNumber': nodeNumber}

    else:
        print("ERROR: Wrong data format.")


def sendData(inputData):
    payload = {'api_key': apiKeys[inputData["dataType"]][int(inputData["nodeNumber"])],
               'field1': inputData["data"]}
    r = requests.post("http://thingtalk.ir/update", data=payload)
    print("Data sent! ")
    print(r.status_code, r.reason)
    print("r.text: " + r.text)
    print("--------------------------------------------------")
    if r.status_code == 200 and r.text != '0':
        return True
    return False


def serialReadThread():
    print("Now Raspberry pi is ready :)")
    while True:
        if ser.inWaiting() > 0:
            print(datetime.datetime.now())
            inputBin = ser.readline()

            inputStr = zigbeeDataToString(inputBin)
            print("inputStr: " + inputStr)
            inputData = stringToData(inputStr)
            print("inputData: ")
            print(inputData)
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
    baudrate=38400,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=1
)

GPIO.setmode(GPIO.BOARD)
pumpPin = 40
GPIO.setup(pumpPin, GPIO.OUT)
GPIO.output(pumpPin, GPIO.HIGH)

thread = Thread(target=serialReadThread)
thread.start()

app.run(host='0.0.0.0', port=5050, debug=False)

thread.join()
