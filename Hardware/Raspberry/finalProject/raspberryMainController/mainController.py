from threading import Thread

from flask import Flask
import serial
import requests
# import RPi.GPIO as GPIO
import datetime

app = Flask(__name__)


@app.route("/actuators/<command>")
def pumpControl(command):
    if not command.startswith('set'):
        return

    dataTypes = ['PS', 'LBS', 'TS']

    for dT in dataTypes:
        if command[3:].startswith(dT):
            if command[len(dT) + 1 + 3:] == 'ON' or command[len(dT) + 1 + 3:] == 'OFF':
                if int(command[len(dT) + 3]) < len(apiKeys[dT]):
                    ser.write(command.encode('ascii'))
                    print("This Text has been sent to Arduino: ", command)
                    return command

    return "NOT FOUND", 404


def zigbeeDataToString(inputBin):
    inputStr = inputBin.decode('ascii')
    return inputStr


def stringToData(inputStr, dataType):
    nodeNumber = inputStr[len(dataType): inputStr.find('=') - 1]
    return {'dataType': dataType, 'data': inputStr[len(dataType + nodeNumber + ' = '):-2], 'nodeNumber': nodeNumber}


def stringToDataHandler(inputStr):
    dataTypes = ['SM', 'T', 'H', 'FH', 'PH', 'WL', 'PhR', 'PS', 'LBS', 'G', 'TS', 'MD', 'WM']

    for dT in dataTypes:
        if inputStr.startswith(dT):
            return stringToData(inputStr, dT)

    print("ERROR: Wrong data format.")


def sendData(inputData):
    try:
        payload = {'api_key': apiKeys[inputData["dataType"]][int(inputData["nodeNumber"])]['apiKey'],
                   'field1': inputData["data"]}
        r = requests.post("http://thingtalk.ir/update", data=payload)
        print("Data sent! ")
        print(r.status_code, r.reason)
        print("r.text: " + r.text)
        print("--------------------------------------------------")
        if r.status_code == 200 and r.text != '0':
            return True
        return False
    except:
        print("Connection Error... Thingtalk is kidding us :(")
        return False


def serialReadThread():
    print("Now Raspberry pi is ready :)")
    while True:
        if ser.inWaiting() > 0:
            print(datetime.datetime.now())
            inputBin = ser.readline()

            inputStr = zigbeeDataToString(inputBin)
            print("inputStr: " + inputStr)
            inputData = stringToDataHandler(inputStr)
            print("inputData: ")
            print(inputData)
            sendData(inputData)

        ### TEST THREAD WORKING ###
        # print("Enter 1 or 0:")
        # num = input()
        # ser.write(num.encode('ascii'))


apiKeys = {
    # SoilMoisture (Vegetables: 0-7, Flowers: 8-17)
    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},
           {'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
           {'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},
           {'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},
           {'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
           {'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},
           {'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},
           {'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'},
           {'id': '710', 'apiKey': 'FYMFZRW8E2YLIKUK'},
           {'id': '711', 'apiKey': 'OXXNS5C338I0TCIR'},
           {'id': '712', 'apiKey': 'MZEZGGZL09ZB48L1'},
           {'id': '713', 'apiKey': '7R9CHM056LSY23ZO'},
           {'id': '714', 'apiKey': 'ZPX8TF59L251UFVE'},
           {'id': '715', 'apiKey': '3DMD11SS9G5B94I0'},
           {'id': '716', 'apiKey': 'QLH9NDZ20RQNK96S'},
           {'id': '717', 'apiKey': 'BVAC00J644INRNG7'},
           {'id': '718', 'apiKey': 'T42SU8NKLQ4KYF5E'},
           {'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}],

    # Temperature
    'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'},
          {'id': '720', 'apiKey': '6P4WUZHZZDR6U0TX'}],

    # Humidity
    'H': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
          {'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}],

    # Floor Humidity
    'FH': [{'id': '722', 'apiKey': '3ZIOUDCBO1X4W0B7'},
           {'id': '723', 'apiKey': 'IG7Z0OW1NR2LVGSW'}],

    # pH (Vegetables: 0-7, Flowers: 8-17)
    'PH': [{'id': '724', 'apiKey': 'A7SC0GJKQAFJZWAO'},
           {'id': '725', 'apiKey': 'THRJGRI2XUH1YZIO'},
           {'id': '726', 'apiKey': 'E8QFCI9OYGTOZIMT'},
           {'id': '727', 'apiKey': 'ATGVGAYRFPS4QCVO'},
           {'id': '728', 'apiKey': 'Z4AXFJXQK1ZN33BQ'},
           {'id': '729', 'apiKey': '61SF7VT52KJZJCPW'},
           {'id': '730', 'apiKey': 'NF3K4HLNFZ0BE2XO'},
           {'id': '731', 'apiKey': 'VAK0BLGBNXUDZMQB'},
           {'id': '732', 'apiKey': 'LG2G6VYVGRROA0O2'},
           {'id': '733', 'apiKey': 'BQYNJJL4G8A5RCHJ'},
           {'id': '734', 'apiKey': 'RZAEIWTMDVBOCNZS'},
           {'id': '735', 'apiKey': '4NWVLGMJHDE8WDAJ'},
           {'id': '736', 'apiKey': 'OW4B80VUEXJ38AG7'},
           {'id': '737', 'apiKey': 'I7MAFZ8E11BHLD4T'},
           {'id': '738', 'apiKey': '5WPKZ6PW9ZYCXC1T'},
           {'id': '739', 'apiKey': 'TD2B6CE14BODBAU4'},
           {'id': '740', 'apiKey': 'AGOE4BOV5662UOQF'},
           {'id': '741', 'apiKey': '29LLHTWCLAZONUOS'}],

    # Water Level
    'WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}],

    # Photo Resistor
    'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
            {'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}],

    # Pump Status (Vegetables, Flowers, TOP)
    'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
           {'id': '744', 'apiKey': 'PD74MGJ9RFR4YMHK'},
           {'id': '750', 'apiKey': 'FGGX36CLKXBW2BN4'}],

    # Light Bulb Status
    'LBS': [{'id': '745', 'apiKey': '2ZRJZIFPTQF79NOH'},
            {'id': '746', 'apiKey': '17J1AKN992YL3HUX'}],

    # Gas Sensor
    'G': [{'id': '747', 'apiKey': '65ZJD9TRET64FJ03'},
          {'id': '751', 'apiKey': '05VOPP6KT2NA1AZ5'}],

    # Tap Status (Water Tap)
    'TS': [{'id': '748', 'apiKey': 'A1079N4WNZESIRFC'}],

    # Motion Detector
    'MD': [{'id': '749', 'apiKey': 'V197BB4SL21A2IKG'},
           {'id': '752', 'apiKey': 'ZCLT56CFVCG7DU50'}],

    # WattMeter
    'WM': [{'id': '753', 'apiKey': 'OUAV3VIB076Y5UO0'}]}

ser = serial.Serial(
    port='/dev/ttyAMA0',
    baudrate=38400,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=1
)

# GPIO.setmode(GPIO.BOARD)
# pumpPin = 40
# GPIO.setup(pumpPin, GPIO.OUT)
# GPIO.output(pumpPin, GPIO.HIGH)

thread = Thread(target=serialReadThread)
thread.start()

app.run(host='0.0.0.0', port=5050, debug=False)

thread.join()
