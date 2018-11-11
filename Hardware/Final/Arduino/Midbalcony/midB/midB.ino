const int relay1Pin = 3; // for vegtables
const int relay2Pin = 4; // for flowers
const int relay3Pin = 5; // for mehpash!
const int relay4Pin = 6; // for left lamp
const int relay5Pin = 7; // for right lamp
const int relay6Pin = 8; // for ultrasonic sensor

const unsigned long oneSecond = 1000L;
const unsigned long delayEach = 5L * oneSecond;
const unsigned long delayAll = 420L * oneSecond;
const unsigned long firstDelay = 40L * oneSecond;
const unsigned long counterLimit = 58000000L;

long long int counter = 0;
#define trigPin 13
#define echoPin 12

void zigbeePrint(String str) {
  Serial.write(0xfd);
  Serial.write(str.length());
  Serial.write(0x00);
  Serial.write(0x00);
  Serial.print(str);
}

///////////////////////////////////////////////////////////void setup /////////////////////////////////////////////////////////////
void setup() {
  counter = 0;
  // put your setup code here, to run once:
  Serial.begin(38400);
  delay(1000);
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
  delay(firstDelay);
}

//////////////////////////////////////////////////////////////void loop///////////////////////////////////////////////////////

void loop() {
  // put your main code here, to run repeatedly:

  //**************************Ultrasonic & Relay***********************************//
  if(counter == 0) {
    long  duration;
    double distance;
    digitalWrite(trigPin, LOW);  // Added this line
    delayMicroseconds(2); // Added this line
    digitalWrite(trigPin, HIGH); //  delayMicroseconds(1000); - Removed this line
    delayMicroseconds(10); // Added this line
    digitalWrite(trigPin, LOW);
  
    duration = pulseIn(echoPin, HIGH);
    distance = (duration / 2) / 29.1;
  
    String tempStr = "WL0=";
    if (distance >= 0 && distance <= 100) {
      tempStr += String(1 - distance / 100);
    }
    else if (distance < 0) {
      tempStr += "1.00";
    }
    else if (distance > 100) {
      tempStr += "0.00";
    }
  
      zigbeePrint(tempStr);
      delay(5000);
  }

  ////}
  //*******************************Other Relays************************************//
  while(!Serial.available()) {
    counter++;
    if(counterLimit == counter) {
      counter = 0;
      break;
    }
  }
  delay(3000);
  String ans = Serial.readString();
  ans = ans.substring(4);
  if(ans == "setPS0ON") {
    zigbeePrint("PS0=1");
    digitalWrite(relay1Pin, LOW);
  }
  else if (ans == "setPS0OFF") {
    zigbeePrint("PS0=0");
    digitalWrite(relay1Pin, HIGH);
  }

  else if (ans == "setPS1ON") {
    zigbeePrint("PS1=1");
    digitalWrite(relay2Pin, LOW);
  }
  else if (ans == "setPS1OFF") {
    zigbeePrint("PS1=0");
    digitalWrite(relay2Pin, HIGH);
  }


  else if (ans == "setPS2ON") {
    zigbeePrint("PS2=1");
    digitalWrite(relay3Pin, LOW);
  }
  else if (ans == "setPS2OFF") {
    zigbeePrint("PS2=0");
    digitalWrite(relay3Pin, HIGH);
  }

  else if (ans == "setLBS0ON") {
    zigbeePrint("LBS0=1");
    digitalWrite(relay4Pin, LOW);
  }
  else if (ans == "setLBS0OFF") {
    zigbeePrint("LBS0=0");
    digitalWrite(relay4Pin, HIGH);
  }

  else if (ans == "setLBS1ON") {
    zigbeePrint("LBS1=1");
    digitalWrite(relay5Pin, LOW);
  }
  else if (ans == "setLBS1OFF") {
    zigbeePrint("LBS1=0");
    digitalWrite(relay5Pin, HIGH);
  }

  else if (ans == "setTS0ON") {
    zigbeePrint("TS0=1");
    digitalWrite(relay6Pin, LOW);
  }
  else if (ans == "setTS0OFF") {
    zigbeePrint("TS0=0");
    digitalWrite(relay6Pin, HIGH);
  }
  delay(3000);
}
//********************************************sending data for Raspberry******************************************************//

