int sensorValue;

void setup() {
  pinMode(7, OUTPUT);
  digitalWrite(7, LOW);
  Serial.begin(9600);
}

void loop() {

  delay(5000);       
  Serial.print("current Moisture is :");
  Serial.println(readMoisture()); // read the input on analog pin 0
}
int readMoisture()
{
  digitalWrite(7, HIGH);
  delay(1000);   // delay in between reads for stability
  sensorValue = analogRead(A0);
  if(sensorValue <= 260)
    sensorValue = 260;
  if(sensorValue >= 1002)
      sensorValue = 1002;
  sensorValue = (1002 - sensorValue)/7.42;
  digitalWrite(7, LOW);
    return sensorValue;
}
