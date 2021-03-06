/* select input pins for sensors */
#include "MQ2.h"

const unsigned long oneSecond = 1000L;
const unsigned long delayEach = 5L * oneSecond;
const unsigned long delayAll = 420L * oneSecond;
const unsigned long firstDelay = 0L;

int SM08{A0};
int SM09{A1};
int G0{A2};
int MD0{8};
int relayInput{7};

MQ2 mq2(G0);

int Count[20];
int MsensorValue;
int smokeVal;
int m;

int smoke();
int soilMoisture(int pinName, int index);
int motion(int index);
int phConfig();
void zigbeePrint(String str);

void setup(){
  Serial.begin(38400);
  pinMode(relayInput, OUTPUT);
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  mq2.begin();
  
  digitalWrite(2, LOW);
  digitalWrite(3, LOW);
  
  for(size_t i=0 ; i < 20 ; i++)
    Count[i] = i;
  delay(firstDelay);
}//end "setup()"

void loop(){
  /* Soil Moisture */
  soilMoisture(2, SM08);
  String tempStr = "SM" + String(Count[8]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(3, SM09);
  tempStr = "SM" + String(Count[9]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  /* PH */
  tempStr = "PH" + String(Count[8]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[9]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);
   
  /* Motion */
  tempStr = "MD" + String(Count[0]) + "=" + String(motion(MD0));
  zigbeePrint(tempStr);
  delay(delayEach);

  /* Smoke */
  int tempSmoke = smoke();
  tempStr = "G" + String(Count[0]) + "=" + String(tempSmoke);
  zigbeePrint(tempStr);
  delay(delayEach);

  delay(delayAll); //rest between Sending Data
  
}//end "loop()"


  /*Soil Moiture*/
int soilMoisture(int pinName, int index) {
   digitalWrite(pinName, HIGH);
   delay(1000);
   MsensorValue = analogRead(index);
   if(MsensorValue <= 260)
    MsensorValue = 260;
  if(MsensorValue >= 1002)
      MsensorValue = 1002;
  MsensorValue = (1002 - MsensorValue)/7.42;
  digitalWrite(pinName, LOW);
   return MsensorValue;
}

  /*Smoke*/
int smoke() {
  float* values= mq2.read(false);
  smokeVal = values[2]; 
//  smokeVal = mq2.readSmoke();
  return smokeVal;
}

  /*Motion Detector*/
int motion(int index) {
  m = digitalRead(index);
  if (m == 1) 
    digitalWrite(relayInput, LOW);
  return m;
}

  /*phConfig*/
int phConfig() {
  int pH{1};
  return pH;
}

void zigbeePrint(String str) {
  Serial.write(0xfd);
  Serial.write(str.length());
  Serial.write(0x00);
  Serial.write(0x00);
  Serial.print(str);
}


