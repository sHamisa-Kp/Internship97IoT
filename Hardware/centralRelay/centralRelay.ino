const int relay1Pin= 3; // for flowers
const int relay2Pin= 4; // for vegtables
const int relay3Pin= 5; // for mehpash!
const int relay4Pin= 6; // for ultrasonic sensor
const int relay5Pin= 7; // for lamp

#define trigPin 13
#define echoPin 12

void setup() {
  
  // put your setup code here, to run once:
 Serial.begin(9600);

 pinMode(trigPin, OUTPUT); // for ultrasonic sensor
 pinMode(echoPin, INPUT); // for ultrasonic sensor
 
 pinMode(relay1Pin, OUTPUT);
 pinMode(relay2Pin, OUTPUT);
 pinMode(relay3Pin, OUTPUT);
 pinMode(relay4Pin, OUTPUT);
 pinMode(relay5Pin, OUTPUT);

 digitalWrite(relay1Pin, HIGH);
 digitalWrite(relay2Pin, HIGH);
 digitalWrite(relay3Pin, HIGH);
 digitalWrite(relay4Pin, HIGH);
 digitalWrite(relay5Pin, HIGH);
 
}

void loop() {
  // put your main code here, to run repeatedly:

 long  duration, distance;
 
  digitalWrite(trigPin, LOW);  // Added this line
  delayMicroseconds(2); // Added this line
  digitalWrite(trigPin, HIGH); //  delayMicroseconds(1000); - Removed this line
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin, LOW);
  
  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;
  
  if (distance < 10){
    Serial.println("The amount of water is too little");
    digitalWrite(relay4Pin, LOW);
    }
  while(!Serial.available()){
  Serial.print(distance);
  Serial.println(" cm");
  delay(500);
  Serial.write("Value of water's level");
  Serial.write(100- distance);
  }

  delay(500);

  Serial.println("Connect? (Y?/N?)");

  while(!Serial.available()){
  char ans = Serial.read();

  if(ans == 'Y'){
    Serial.println("Y1 Recieved");
    digitalWrite(relay1Pin, LOW);
    }
    else if(ans == 'N'){
      Serial.println("N1 Received");
      digitalWrite(relay1Pin, HIGH);
      }

  if(ans == 'y'){
    Serial.println("Y2 Recieved");
    digitalWrite(relay1Pin, LOW);
    }
    else if(ans == 'n'){
      Serial.println("N2 Received");
      digitalWrite(relay1Pin, HIGH);
    }

    
  if(ans == '1'){
    Serial.println("Y3 Recieved");
    digitalWrite(relay1Pin, LOW);
    }
    else if(ans == '0'){
      Serial.println("N3 Received");
      digitalWrite(relay1Pin, HIGH);
    }
  }
}
