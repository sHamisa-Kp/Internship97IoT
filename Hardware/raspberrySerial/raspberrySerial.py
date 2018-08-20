import serial

ser = serial.Serial(
    port='/dev/ttyACM0',
    baudrate=38400,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=1
)

while True:
    if ser.inWaiting() > 0:
        inputBin = ser.readline()
        print(inputBin.decode('ascii'))
        ser.write(inputBin)
