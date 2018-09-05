//https://www.instructables.com/id/Arduino-Modules-Rain-Sensor/
#define analogPin A0
void setup() {
  // put your setup code here, to run once:
  Serial.begin (9600);
  pinMode(analogPin ,INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  long drop;
  drop =analogRead (A0) ;
  Serial.print("amounts of drops :");
  Serial.println(drop) ;
  delay(3000);
}
