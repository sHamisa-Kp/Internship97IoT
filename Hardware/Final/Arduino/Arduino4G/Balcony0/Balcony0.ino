/* select input pins for sensors */

const unsigned long oneSecond = 1000L;
const unsigned long delayEach = 5L * oneSecond;
const unsigned long delayAll = 420L * oneSecond;
const unsigned long firstDelay = 280L * oneSecond;

int SM00{A0};
int SM01{A1};
int SM02{A2};
int SM03{A3};

int Count[20];
int MsensorValue;

int soilMoisture(int pinName, int index);
int phConfig();
void zigbeePrint(String str);

void setup(){
  
  Serial.begin(38400);
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
  soilMoisture(2, SM00);
  String tempStr = "SM" + String(Count[0]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(3, SM01);
  tempStr = "SM" + String(Count[1]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(4, SM02);
  tempStr = "SM" + String(Count[2]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(5, SM03);
  tempStr = "SM" + String(Count[3]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  /* PH */
  tempStr = "PH" + String(Count[0]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[1]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[2]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);
  
  tempStr = "PH" + String(Count[3]) + "=" + String(phConfig());
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




