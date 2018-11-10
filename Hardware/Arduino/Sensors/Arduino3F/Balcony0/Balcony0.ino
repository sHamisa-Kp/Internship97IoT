#include <dht.h>

const unsigned long oneSecond = 1000L;
const unsigned long delayEach = 5L * oneSecond;
const unsigned long delayAll = 420L * oneSecond;
const unsigned long firstDelay = 120 * oneSecond;

/* select input pins for sensors */
int SM10{A0};
int SM11{A1};
int SM12{A2};
int T0{A3};
int PhR0{A4};
int FH0{A5};

int Count[20];
int MsensorValue;
long fHumidity;
dht DHT;


void tH(int index);
int soilMoisture(int pinName, int index);
int phConfig();
void floorHumidity(int index);
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
  soilMoisture(2, SM10);
  String tempStr = "SM" + String(Count[10]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  soilMoisture(3, SM11);
  tempStr = "SM" + String(Count[11]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

   
  soilMoisture(4, SM12);
  tempStr = "SM" + String(Count[12]) + "=" + String(MsensorValue);
  zigbeePrint(tempStr);
  delay(delayEach); 

  /* PH */
  tempStr = "PH" + String(Count[10]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[11]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

  tempStr = "PH" + String(Count[12]) + "=" + String(phConfig());
  zigbeePrint(tempStr);
  delay(delayEach);

   
  /* Temperature */
  tH(T0);
  tempStr = "T" + String(Count[0]) + "=" + String(DHT.temperature);
  zigbeePrint(tempStr);
  delay(delayEach);

  /* humidity */
  tH(T0);
  tempStr = "H" + String(Count[0]) + "=" + String(DHT.humidity);
  zigbeePrint(tempStr);
  delay(delayEach);
  
  /* floor humidity */
  floorHumidity(FH0);
  tempStr = "FH" + String(Count[0]) + "=" + String(fHumidity);
  zigbeePrint(tempStr);
  delay(delayEach);

  
  /* PhotoResistor */
  tempStr = "PhR" + String(Count[0]) + "=" + String(photoResistor(PhR0));
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

  /*Temperature and humidity*/
void tH(int index) {
  DHT.read11(T0);
}

  /*floor humidity*/
void floorHumidity(int index) {
  fHumidity = analogRead(index);
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



