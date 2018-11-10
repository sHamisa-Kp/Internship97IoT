#include <dht.h>

const unsigned long oneSecond = 1000L;
const unsigned long delayEach = 5L * oneSecond;
const unsigned long delayAll = 420L * oneSecond;
const unsigned long firstDelay = 200 * oneSecond;
// const unsigned long firstDelay = 0 * oneSecond;

/* select input pins for sensors */
int SM15{A0};
int SM16{A1};
int SM17{A2};
int T1{A3};
int PhR1{A4};
int FH1{A5};

int Count[20];
int MsensorValue;
long fHumidity;
dht DHT;


void tH(int index);
int soilMoisture(int pinName, int index);
int phConfig();
long floorHumidity(int index);
int photoResistor(int index);
void zigbeePrint(String str);

void setup(){
  
  Serial.begin(38400);
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
//  mq2.begin();
  
  digitalWrite(2, LOW);
  digitalWrite(3, LOW);
  digitalWrite(4, LOW);
  
  for(size_t i=0 ; i < 20 ; i++)
    Count[i] = i;
  delay(firstDelay);
}//end "setup()"

void loop(){  
  /* Soil Moisture */
  soilMoisture(2, SM15);
  String tempStr = "SM" + String(Count[15]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(3, SM16);
  tempStr = "SM" + String(Count[16]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

   
  soilMoisture(4, SM17);
  tempStr = "SM" + String(Count[17]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  /* PH */
  tempStr = "PH" + String(Count[15]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[16]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[17]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);


  /* Temperature */
  tH(T1);
  tempStr = "T" + String(Count[1]) + "=" + String(DHT.temperature);
  zigbeePrint(tempStr);
  delay(delayEach);

  /* humidity */
  tH(T1);
  tempStr = "H" + String(Count[1]) + "=" + String(DHT.humidity);
  zigbeePrint(tempStr);
  delay(delayEach);
  
  /* floor humidity */
  floorHumidity(FH1);
  tempStr = "FH" + String(Count[1]) + "=" + String(fHumidity);
  zigbeePrint(tempStr);
  delay(delayEach);

  
  /* PhotoResistor */
  tempStr = "PhR" + String(Count[1]) + "=" + String(photoResistor(PhR1));
  zigbeePrint(tempStr);
  delay(delayEach);

  delay(delayAll); //rest between Sending Data
}

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

  /*Temperature and humidity*/
void tH(int index) {
  DHT.read11(T1);
}

  /*floor humidity*/
long floorHumidity(int index) {
  fHumidity = analogRead(index);
  return fHumidity;
}

  /*phConfig*/
int phConfig() {
  int pH{1};
  return pH;
}

 /* PhotoResistor */
int photoResistor(int index) {
  return analogRead(index);
}

void zigbeePrint(String str) {
  Serial.write(0xfd);
  Serial.write(str.length());
  Serial.write(0x00);
  Serial.write(0x00);
  Serial.print(str);
}



