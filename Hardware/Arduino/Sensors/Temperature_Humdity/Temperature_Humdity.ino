#include <dht.h>

#define dhtPin A0

dht DHT;


void setup(){
  
  Serial.begin(9600);
  delay(500); //DelayforSystemBoot
  Serial.println("Temperature & Humiditsy Measuring\n\n");
  delay(1000);  //WaitBeforeAccessingtoSensor

}//end "setup()"

 
void loop(){

  DHT.read11(dhtPin);  //ReadTemperature&Humidity

  /* Humidity */
  Serial.print("Currnet Humidity: ");
  Serial.print(DHT.humidity);
  Serial.print("%  "); 
  /* Temperature */
  Serial.print("Current Temperature: ");
  Serial.print(DHT.temperature);
  Serial.print("C\n");

  delay(2000);
 
}//end "loop()"
