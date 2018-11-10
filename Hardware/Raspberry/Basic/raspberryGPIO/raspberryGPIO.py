import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BOARD)
pumpPin = 40
GPIO.setup(pumpPin, GPIO.OUT)
GPIO.output(pumpPin, GPIO.HIGH)
