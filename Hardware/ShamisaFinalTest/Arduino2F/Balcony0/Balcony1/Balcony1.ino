/* select input pins for sensors */
#include <MQ2.h>

int SM13{A0};
int SM14{A1};
int G1{A2};
int MD1{8};
int relayInput{7};

MQ2 mq2(G1);

int Count[20];
int MsensorValue;
int smokeVal;
int m;

int smoke();
int soilMoisture(int pinName, int index);
int motion(int index);
int phConfig();

void setup(){
  
  Serial.begin(38400);
  delay(500); //DelayforSystemBoot
  pinMode(relayInput, OUTPUT);
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  mq2.begin();
  
  digitalWrite(2, LOW);
  digitalWrite(3, LOW);
  
  for(size_t i=0 ; i < 20 ; i++)
    Count[i] = i;
}//end "setup()"

void loop(){
  delay(100); //rest between Sending Data

  
  /* Soil Moisture */
  soilMoisture(2, SM13);
  Serial.print("SM");
  Serial.print(Count[13]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
  delay(100); 

  soilMoisture(3, SM14);
  Serial.print("SM");
  Serial.print(Count[14]);
  Serial.print(" = ");
  Serial.println(MsensorValue);
   delay(100); 

  /* PH */
   Serial.print("PH");
   Serial.print(Count[13]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(100);

   Serial.print("PH");
   Serial.print(Count[14]);
   Serial.print(" = ");
   Serial.println(phConfig());
   delay(100);
   
  /* Motion */
  Serial.print("MD");
  Serial.print(Count[1]);
  Serial.print(" = ");
  Serial.println(motion(MD1));

  /* Smoke */
  Serial.print("G");
  Serial.print(Count[1]);
  Serial.print(" = ");
  Serial.println(smoke());
  Serial.println("END OF LOOP\n\n");
}//end "loop()"


  /*Soil Moiture*/
int soilMoisture(int pinName, int index) {
   digitalWrite(pinName, HIGH);
   MsensorValue = analogRead(index);
   delay(1000);
   return MsensorValue;
}

  /*Smoke*/
int smoke() {
  float* values= mq2.read(true);
  smokeVal = values[2];
  smokeVal = mq2.readSmoke();
  delay(1000);
  return smokeVal;
}

  /*Motion Detector*/
int motion(int index) {
  m = digitalRead(index);
  if (m == 1) 
    digitalWrite(relayInput, LOW);
  delay(1000);
  return m;
}

  /*phConfig*/
int phConfig() {
  int pH{1};
  return pH;
}


