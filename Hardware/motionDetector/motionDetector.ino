//https://www.wired.com/2012/09/using-motion-detectors-with-an-arduino/
int motion_1 = 2 ;
int light_1 = 13 ;
void setup() {
  // put your setup code here, to run once:
  pinMode (motion_1,INPUT);
  pinMode (light_1, OUTPUT);
   Serial.begin (9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite (light_1,LOW);
  delay(1000); //this delay is to let the sensor settle down before taking a reading
  int sensor_1 = digitalRead(motion_1);
  if (sensor_1 == HIGH){
    digitalWrite(light_1,HIGH);
    delay(500);
    digitalWrite(light_1,LOW);
    delay(500);
  }
  Serial.print("detection :");
  Serial.println(sensor_1) ;
  delay(1000);
}
