#include <dht.h>

#define dhtPin A0
#define MoisturePin A1
#define LightPin A2

unsigned long minute = 60 * 1000L;
int Count[20];
int MsensorValue;
int light;
dht DHT;

void setup(){
  
  Serial.begin(38400);
  delay(500); //DelayforSystemBoot
  pinMode(7, OUTPUT);
  digitalWrite(7, LOW);
  for(size_t i=0 ; i < 20 ; i++)
    Count[i] = i;
}//end "setup()"

void loop(){
delay(2 * minute); //rest between Sending Data

/*Soil Moisture*/ 
  digitalWrite(7, HIGH);
  delay(1000); // Rest till Voltage is Valid
    MsensorValue = analogRead(MoisturePin);
    //260 is Moisture of pure water
  if(MsensorValue <= 260)
    MsensorValue = 260;
    //1002 is Moisture of air 
  if(MsensorValue >= 1002)
      MsensorValue = 1002;
      //mapping 
  MsensorValue = (1002 - MsensorValue)/7.42;
    digitalWrite(7, LOW);
    Serial.print("SM");
    Serial.print(Count[0]);
    Serial.print(" = ");
    Serial.println(MsensorValue);
    DHT.read11(dhtPin);  //ReadTemperature&Humidity
    delay(1000);
  /* Humidity */
   Serial.print("H");
   Serial.print(Count[0]);
   Serial.print(" = ");
   Serial.println(DHT.humidity);
   delay(1000); 

  /* Temperature */
   Serial.print("T");
   Serial.print(Count[0]);
   Serial.print(" = ");
   Serial.println(DHT.temperature);
   delay(1000);
   
 /*photoResistor*/
  Serial.print("PhR");
  Serial.print(Count[0]);
  Serial.print(" = ");
  Serial.println(analogRead(LightPin));
  
}//end "loop()"
