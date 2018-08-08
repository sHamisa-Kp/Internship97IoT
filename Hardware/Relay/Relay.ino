
const int relay1Pin = 3;

void setup() {
  Serial.begin(9600);
  pinMode(relay1Pin, OUTPUT);
  digitalWrite(relay1Pin, HIGH);
}

void loop() {
  Serial.print("Connect? (Y/N) ");
  
  while(!Serial.available()) {}
  char ans = Serial.read();
  if(ans == 'Y') {
    Serial.println("Y Received");
    digitalWrite(relay1Pin, LOW);
  }
  else if(ans == 'N') {
    Serial.println("N Received");
    digitalWrite(relay1Pin, HIGH);
  }
}
