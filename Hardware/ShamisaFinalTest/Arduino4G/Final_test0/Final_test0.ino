/* select input pins for sensors */
int SM00{A0};
int SM01{A1};
int SM02{A2};
int SM03{A3};

int Count[20];
int delays{100};
int MsensorValue;

int soilMoisture(int pinName, int index);
int phConfig();

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
}//end "setup()"

void loop(){
  delay(delays); //rest between Sending Data

  
  /* Soil Moisture */
  soilMoisture(2, SM00);
  Serial.print("SM");
  Serial.print(Count[0]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(delays); 

  soilMoisture(3, SM01);
  Serial.print("SM");
  Serial.print(Count[1]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(100); 

   
  soilMoisture(4, SM02);
  Serial.print("SM");
  Serial.print(Count[2]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(100); 

  
  soilMoisture(5, SM03);
  Serial.print("SM");
  Serial.print(Count[3]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(100); 

  /* PH */
   Serial.print("PH");
   Serial.print(Count[0]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(100);

   Serial.print("PH");
   Serial.print(Count[1]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(delays);

   Serial.print("PH");
   Serial.print(Count[2]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(delays);

   
   Serial.print("PH");
   Serial.print(Count[3]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(delays);
   
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

  /*phConfig*/
int phConfig() {
  int pH{1};
  return pH;
}




