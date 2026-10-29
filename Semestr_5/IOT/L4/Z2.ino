#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2); 

const byte POT_PIN = A0;

int lastAdcValue = -1; 

void setup() {
  Serial.begin(9600);
  lcd.init();
  lcd.backlight();
  
  lcd.setCursor(0, 0);
  lcd.print("Volt         ADC"); 
}

void loop() {
  int adcValue = analogRead(POT_PIN);
  float voltage = adcValue * (5.0 / 1024.0);

  Serial.print("Napiecie:");
  Serial.print(voltage);
  Serial.print(",");
  Serial.print("ADC:");
  Serial.println(adcValue);

    
  if(adcValue != lastAdcValue) {
    lcd.setCursor(0, 1);
    lcd.print(voltage, 2);
    lcd.print("V  ");     
    
    
    lcd.setCursor(12, 1);
    
    if (adcValue < 1000) lcd.print(" ");
    if (adcValue < 100)  lcd.print(" ");
    if (adcValue < 10)   lcd.print(" ");
    
    lcd.print(adcValue);
    
    lastAdcValue = adcValue; 
  }
  
  delay(20);
}
