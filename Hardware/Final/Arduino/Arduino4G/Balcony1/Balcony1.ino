  /* select input pins for sensors */

const unsigned long oneSecond = 1000L;
const unsigned long delayEach = 5L * oneSecond;
const unsigned long delayAll = 420L * oneSecond;
const unsigned long firstDelay = 350L * oneSecond;

int SM04{A0};
int SM05{A1};
int SM06{A2};

int SM07{A3};

int Count[20];
int MsensorValue;

int soilMoisture(int pinName, int index);
int phConfig();
void zigbeePrint(String str);

void setup(){
  Serial.begin(38400);
  delay(500); //DelayforSystemBoot
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
//  mq2.begin();
  
  digitalWrite(2, LOW);
  digitalWrite(3, LOW);
  digitalWrite(4, LOW);
  digitalWrite(5, LOW);
  
  for(size_t i=0 ; i < 20 ; i++)
    Count[i] = i;
  delay(firstDelay);
}//end "setup()"

void loop(){
  
  /* Soil Moisture */
  soilMoisture(2, SM04);
  String tempStr = "SM" + String(Count[4]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(3, SM05);
  tempStr = "SM" + String(Count[5]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(4, SM06);
  tempStr = "SM" + String(Count[6]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(5, SM07);
  tempStr = "SM" + String(Count[7]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  /* PH */
  tempStr = "PH" + String(Count[4]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[5]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[6]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);
  
  tempStr = "PH" + String(Count[7]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);
   
  delay(delayAll); //rest between Sending Data

}//end "loop()"


  /*Soil Moiture*/
int soilMoisture(int pinName, int index) {
   digitalWrite(pinName, HIGH);
   MsensorValue = analogRead(index);
   if(MsensorValue <= 260)
    MsensorValue = 260;
    //1002 is Moisture of air 
  if(MsensorValue >= 1002)
      MsensorValue = 1002;
      //mapping 
  MsensorValue = (1002 - MsensorValue)/7.42;
  digitalWrite(pinName, LOW);
  return MsensorValue;
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



