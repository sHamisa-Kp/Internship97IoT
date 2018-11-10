from threading import Thread

from flask import Flask
import serial
import requests
# import RPi.GPIO as GPIO
import datetime

app = Flask(__name__)

ser = serial.Serial(
    # port='/dev/ttyAMA0',
    port='/dev/ttyUSB0',
    baudrate=38400,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=0.1
)

currentValuesOfVariables = {
    'PS': [None, None, None],
    'LBS': [0, 1],
    'TS': [0],
    'SM': [None for i in range(18)],
    'T': [None, None],
    'H': [None, None],
    'FH': [None, None],
    'PH': [None for i in range(18)],
    'WL': [None],
    'PhR': [None, None],
    'G': [None, None],
    'MD': [None, None],
    'WM': [None]
}

thresholdsOfSensors = {
    'H': {'min': 40, 'max': 50},
    'PhR': {'min': 300, 'max': 350},  # < 350: LIGHT; > 350: DARK;
    'SM': {'minV': 50, 'maxV': 70, 'minF': 50, 'maxF': 70},
    'WL': {'min': 0.3, 'max': 0.8}
}

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


@app.route("/actuators/<command>")
def pumpControl(command):
    if not command.startswith('set'):
        return

    dataTypes = ['PS', 'LBS', 'TS']

    for dT in dataTypes:
        if command[3:].startswith(dT):
            if command[len(dT) + 1 + 3:] == 'ON' or command[len(dT) + 1 + 3:] == 'OFF':
                if int(command[len(dT) + 3]) < len(apiKeys[dT]):
                    zigbeeSerialWrite(command)
                    print("This Text has been sent to Arduino: ", command)
                    return command

    return "NOT FOUND", 404


def zigbeeSerialWrite(s):
    binaryC = b"\xFD" + bytes([len(s)]) + b"\x3C\xB8" + s.encode('utf-8')
    ser.write(binaryC)


def zigbeeDataToString(inputBin):
    inputStr = inputBin[4:-2].decode('ascii') + "\n\n"
    return inputStr


def stringToData(inputStr, dataType):
    nodeNumber = inputStr[len(dataType): inputStr.find('=')]
    return {'dataType': dataType, 'data': inputStr[len(dataType + nodeNumber + '='):-2], 'nodeNumber': nodeNumber}


def stringToDataHandler(inputStr):
    dataTypes = ['SM', 'TS', 'T', 'H', 'FH', 'PH', 'WL', 'PhR', 'PS', 'LBS', 'G', 'MD', 'WM']

    for dT in dataTypes:
        if inputStr.startswith(dT):
            return stringToData(inputStr, dT)

    print("ERROR: Wrong data format.")


def updateCurrentValuesOfVariables(inputData):
    dataType = inputData['dataType']
    nodeNumber = int(inputData['nodeNumber'])
    data = float(inputData['data'])
    if dataType in ['PS', 'LBS', 'TS']:  # Actuators -> return
        data = int(inputData['data'])
    currentValuesOfVariables[dataType][nodeNumber] = data


def automateTasks(inputData):
    dataType = inputData['dataType']

    if dataType in ['PS', 'LBS', 'TS']:  # Actuators -> return
        return
    nodeNumber = int(inputData['nodeNumber'])
    data = float(inputData['data'])

    if dataType != 'SM':
        average = 0
        counter = 0
        for V in currentValuesOfVariables[dataType]:
            if V is not None:
                average += V
                counter += 1
        if counter == 0:
            return
        average /= counter
        data = average

    if dataType == 'H':
        if data < thresholdsOfSensors[dataType]['min'] and (currentValuesOfVariables['PS'][2] == 0 or
                                                            currentValuesOfVariables['PS'][2] is None):
            print('automateTasks: setPS2ON')
            zigbeeSerialWrite('setPS2ON')
        elif data > thresholdsOfSensors[dataType]['max'] and (currentValuesOfVariables['PS'][2] == 1 or
                                                              currentValuesOfVariables['PS'][2] is None):
            print('automateTasks: setPS2OFF')
            zigbeeSerialWrite('setPS2OFF')

    elif dataType == 'PhR':
        if data > thresholdsOfSensors[dataType]['max'] and (currentValuesOfVariables['LBS'][0] == 0 or
                                                            currentValuesOfVariables['LBS'][1] == 0):
            print('automateTasks: setLBS0ON')
            zigbeeSerialWrite('setLBS0ON')
            print('automateTasks: setLBS1ON')
            zigbeeSerialWrite('setLBS1ON')
        elif data < thresholdsOfSensors[dataType]['min'] and (currentValuesOfVariables['LBS'][0] == 1 or
                                                              currentValuesOfVariables['LBS'][1] == 1):
            print('automateTasks: setLBS0OFF')
            zigbeeSerialWrite('setLBS0OFF')
            print('automateTasks: setLBS1OFF')
            zigbeeSerialWrite('setLBS1OFF')

    elif dataType == 'WL':
        if data < thresholdsOfSensors[dataType]['min'] and (currentValuesOfVariables['TS'][0] == 0 or
                                                            currentValuesOfVariables['TS'][0] is None):
            print('automateTasks: setTS0ON')
            zigbeeSerialWrite('setTS0ON')
        elif data > thresholdsOfSensors[dataType]['max'] and (currentValuesOfVariables['TS'][0] == 1 or
                                                              currentValuesOfVariables['TS'][0] is None):
            print('automateTasks: setTS0OFF')
            zigbeeSerialWrite('setTS0OFF')

    elif dataType == 'SM':
        if nodeNumber <= 7:
            average = 0
            counter = 0
            for i in range(0, 8):
                V = currentValuesOfVariables[dataType][i]
                if V is not None:
                    average += V
                    counter += 1

            if counter == 0:
                return
            average /= counter
            data = average

            if data < thresholdsOfSensors[dataType]['minV'] and (currentValuesOfVariables['PS'][0] == 0 or
                                                                 currentValuesOfVariables['PS'][0] is None):
                print('automateTasks: setPS0ON')
                zigbeeSerialWrite('setPS0ON')
            elif data > thresholdsOfSensors[dataType]['maxV'] and (currentValuesOfVariables['PS'][0] == 1 or
                                                                   currentValuesOfVariables['PS'][0] is None):
                print('automateTasks: setPS0OFF')
                zigbeeSerialWrite('setPS0OFF')
        else:
            average = 0
            counter = 0
            for i in range(8, 18):
                V = currentValuesOfVariables[dataType][i]
                if V is not None:
                    average += V
                    counter += 1

            if counter == 0:
                return
            average /= counter
            data = average

            if data < thresholdsOfSensors[dataType]['minF'] and (currentValuesOfVariables['PS'][1] == 0 or
                                                                 currentValuesOfVariables['PS'][1] is None):
                print('automateTasks: setPS1ON')
                zigbeeSerialWrite('setPS1ON')
            elif data > thresholdsOfSensors[dataType]['maxF'] and (currentValuesOfVariables['PS'][1] == 1 or
                                                                   currentValuesOfVariables['PS'][1] is None):
                print('automateTasks: setPS1OFF')
                zigbeeSerialWrite("setPS1OFF")


def sendData(inputData):
    updateCurrentValuesOfVariables(inputData)
    automateTasks(inputData)

    payload = {'api_key': apiKeys[inputData["dataType"]][int(inputData["nodeNumber"])]['apiKey'],
               'field1': inputData["data"]}
    try:
        r = requests.post("http://thingtalk.ir/update", data=payload)
        print("PAYLOAD: ", end='')
        print(payload)
        print("Data sent! ")
        print(r.status_code, r.reason)
        print("r.text: " + r.text)
        if r.status_code == 200 and r.text != '0':
            return True
        return False
    except Exception as e:
        print("Thingtalk Problem!")
        print(e)


def serialReadThread():
    print("Now Raspberry pi is ready :)")
    # zigbeeSerialWrite("setPS0ON")

    while True:
        if ser.inWaiting() > 0:
            try:
                inputBin = ser.readline()
                print("-------------------------------------")
                print(datetime.datetime.now())
                print("inputBin: ", end='')
                print(inputBin)
                inputStr = zigbeeDataToString(inputBin)

                print("inputStr: " + inputStr, end='')
                inputData = stringToDataHandler(inputStr)

                print("inputData: ", end='')
                print(inputData)
                sendData(inputData)
            except Exception as e:
                print(e)


thread = Thread(target=serialReadThread)
thread.start()

app.run(host='0.0.0.0', port=5050, debug=False)

thread.join()
