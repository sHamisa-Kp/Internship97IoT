
const int relay1Pin= 3; // for vegtables
const int relay2Pin= 4; // for flowers
const int relay3Pin= 5; // for mehpash!
const int relay4Pin= 6; // for left lamp 
const int relay5Pin= 7; // for right lamp
const int relay6Pin= 8; // for ultrasonic sensor

#define trigPin 13
#define echoPin 12

///////////////////////////////////////////////////////////void setup /////////////////////////////////////////////////////////////
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
 pinMode(relay6Pin, OUTPUT);

 digitalWrite(relay1Pin, HIGH);
 digitalWrite(relay2Pin, HIGH);
 digitalWrite(relay3Pin, HIGH);
 digitalWrite(relay4Pin, HIGH);
 digitalWrite(relay5Pin, HIGH);
 digitalWrite(relay6Pin, HIGH); 
}

//////////////////////////////////////////////////////////////void loop///////////////////////////////////////////////////////

void loop() {
  // put your main code here, to run repeatedly:
  
//**************************Ultrasonic & Relay***********************************//
 long  duration, distance;
 
  digitalWrite(trigPin, LOW);  // Added this line
  delayMicroseconds(2); // Added this line
  digitalWrite(trigPin, HIGH); //  delayMicroseconds(1000); - Removed this line
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin, LOW);
  
  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;
  
  if (distance < 30){
    Serial.println("The amount of water is too little");
    digitalWrite(relay6Pin, LOW);
  }
  else {
    digitalWrite(relay6Pin, HIGH);
  }
  
  if (distance >= 100 || distance <= 0){
    Serial.println("Out of range");
  }
  else {
    Serial.write("Value of water's level ");
    Serial.print(100 - distance);
    Serial.println(" cm");
  }
  delay(200);
  
//*******************************Other Relays************************************//

  Serial.println("Connect? (Y?/N?)");

  while(!Serial.available()){}
  String  ans = String (Serial.read());

  if(ans == "setPS0ON"){
    Serial.println("setPS0ON Recieved");
    digitalWrite(relay1Pin, LOW);
    }
    else if(ans == "setPS0OFF"){
      Serial.println("setPS0OFF Received");
      digitalWrite(relay1Pin, HIGH);
      }

  if(ans == "setPS1ON"){
    Serial.println("setPS1ON Recieved");
    digitalWrite(relay2Pin, LOW);
    }
    else if(ans == "setPS1OFF"){
      Serial.println("setPS1OFF Received");
      digitalWrite(relay2Pin, HIGH);
    }

  
  if(ans == "setPS2ON"){
    Serial.println("setPS2ON Recieved");
    digitalWrite(relay3Pin, LOW);
    }
    else if(ans == "setPS2OFF"){
      Serial.println("setPS2OFF Received");
      digitalWrite(relay3Pin, HIGH);
    }

     if(ans == "setLBS0ON"){
    Serial.println("setLBS0ON Recieved");
    digitalWrite(relay4Pin, LOW);
    }
    else if(ans == "setLBS0OFF"){
      Serial.println("setLBS0OFF Received");
      digitalWrite(relay4Pin, HIGH);
    }

     if(ans == "setLBS1ON"){
    Serial.println("setLBS1ON Recieved");
    digitalWrite(relay5Pin, LOW);
    }
    else if(ans == "setLBS1OFF"){
      Serial.println("setLBS1OFF Received");
      digitalWrite(relay5Pin, HIGH);
    }

      if(ans == "setTS0ON"){
    Serial.println("setTS0ON Recieved");
    digitalWrite(relay6Pin, LOW);
    }
    else if(ans == "setTS0OFF"){
      Serial.println("setTS0OFF Received");
      digitalWrite(relay6Pin, HIGH);
    }

    delay(500);
}
//********************************************sending data for Raspberry******************************************************//

