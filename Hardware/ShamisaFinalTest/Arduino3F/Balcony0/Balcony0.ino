#include <dht.h>

/* select input pins for sensors */
int SM10{A0};
int SM11{A1};
int SM12{A2};
int T0{A3};
int MD0{A4};
int FH0{A5};

int Count[20];
int delays{100};
int MsensorValue;
long fHumidity;
dht DHT;


void tH(int index);
int soilMoisture(int pinName, int index);
int phConfig();
long floorHumidity(int index);

void setup(){
  
  Serial.begin(38400);
  delay(500); //DelayforSystemBoot
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
//  mq2.begin();
  
  digitalWrite(2, LOW);
  digitalWrite(3, LOW);
  digitalWrite(4, LOW);
  
  for(size_t i=0 ; i < 20 ; i++)
    Count[i] = i;
}//end "setup()"

void loop(){
  delay(delays); //rest between Sending Data

  
  /* Soil Moisture */
  soilMoisture(2, SM10);
  Serial.print("SM");
  Serial.print(Count[10]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(delays); 

  soilMoisture(3, SM11);
  Serial.print("SM");
  Serial.print(Count[11]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(100); 

   
  soilMoisture(3, SM12);
  Serial.print("SM");
  Serial.print(Count[12]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(100); 

  /* PH */
   Serial.print("PH");
   Serial.print(Count[10]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(100);

   Serial.print("PH");
   Serial.print(Count[11]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(delays);

   Serial.print("PH");
   Serial.print(Count[12]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(delays);
   
  /* Temperature */
  tH(T0);
  Serial.print("T");
  Serial.print(Count[0]);
  Serial.print(" = ");
  Serial.println(DHT.temperature);

  /* humidity */
  tH(T0);
  Serial.print("H");
  Serial.print(Count[0]);
  Serial.print(" = ");
  Serial.println(DHT.humidity);

  /* floor humidity */
  floorHumidity(FH0);
  Serial.print("FH");
  Serial.print(Count[0]);
  Serial.print(" = ");
  Serial.println(fHumidity);
  Serial.println("END OF LOOP\n\n");
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
  delay(delays);
  return MsensorValue;
}

  /*Temperature and humidity*/
void tH(int index) {
  DHT.read11(T0);
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



