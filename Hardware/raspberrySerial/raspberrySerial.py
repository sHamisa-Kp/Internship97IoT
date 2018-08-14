import time
import serial
import requests


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

    # 'PhR10 = 100' Pump Status
    elif inputStr[0:3] == 'PhR':
        nodeNumber = inputStr[3: inputStr.find('=') - 1]
        return {'dataType': 'PhR', 'data': inputStr[len('PhR' + nodeNumber + ' = '):], 'nodeNumber': nodeNumber}

    else:
        print("ERROR: Wrong data format.")


def sendData(inputData):
    payload = {'api_key': apiKeys[inputData["dataType"]][int(inputData["nodeNumber"])], 'field1': inputData["data"]}
    r = requests.post("http://thingtalk.ir/update", data=payload)
    # print(r.status_code, r.reason)
    # print(r.text)
    if r.status_code == 200 and r.text != '-1':
        return True
    return False


apiKeys = {'T': ['G7KHR97UPN9OC5AC'],
           'H': ['7TPW8OQOGN1EMURD'],
           'SM': ['LUJ9D21E177HESAW'],
           'PS': ['XAKAVEUUJQ9GZGMT'],
           'PhR': ['B1JQYWFKX2PCRBYF']}

ser = serial.Serial(
    port='/dev/ttyACM0',
    baudrate=9600,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=1
)

while True:
    while ser.inWaiting() > 0:
        inputBin = ser.readline()
        # print("inputBin: " + inputBin)
        inputStr = zigbeeDataToString(inputBin)
        inputData = stringToData(inputStr)
        sendData(inputData)
