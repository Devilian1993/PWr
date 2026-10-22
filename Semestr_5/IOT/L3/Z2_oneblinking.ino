#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

const byte LED_RED = 6;
const byte LED_GREEN = 5;
const byte LED_BLUE = 3;

const byte RED_BUTTON = 2;
const byte GREEN_BUTTON = 4;

unsigned long red_time = 900;
unsigned long green_time = 1000;
unsigned long blue_time = 1100;
const int step = 100;

byte selectedLed = LED_RED;

void displayLedInfo() {
  lcd.clear();
  
  lcd.setCursor(0, 0);
  lcd.print("Edycja: ");
  if (selectedLed == LED_RED) {
    lcd.print("R");
  } else if (selectedLed == LED_GREEN) {
    lcd.print("G");
  } else {
    lcd.print("B");
  }

  unsigned long currentTimeValue;
  if (selectedLed == LED_RED) currentTimeValue = red_time;
  else if (selectedLed == LED_GREEN) currentTimeValue = green_time;
  else currentTimeValue = blue_time;

  lcd.setCursor(0, 1);
  lcd.print("Czas: ");
  lcd.print(currentTimeValue / 1000.0, 1);
  lcd.print(" s");
}

void blinkSelectedLed() {
  unsigned long currentMillis = millis();
  static unsigned long lastChange = 0UL;

  unsigned long activeTime;
  if (selectedLed == LED_RED) activeTime = red_time;
  else if (selectedLed == LED_GREEN) activeTime = green_time;
  else activeTime = blue_time;

  if (currentMillis - lastChange >= activeTime) {
    lastChange = currentMillis;
    PORTD ^= (1 << selectedLed); 
  }
}

void setup() {
  Serial.begin(9600);
  lcd.init();
  lcd.backlight();
  
  DDRD |= (1 << LED_RED) | (1 << LED_GREEN) | (1 << LED_BLUE);
  DDRD &= ~((1 << RED_BUTTON) | (1 << GREEN_BUTTON)); 
  PORTD |= (1 << RED_BUTTON) | (1 << GREEN_BUTTON);
  
  displayLedInfo();
}

void loop() {
  static bool lastGreenPressed = false;
  static bool lastRedPressed = false;
  static unsigned long lastDebounceTime = 0UL;
  const unsigned long debounceDelay = 50UL; 

  bool greenPressed = !(PIND & (1 << GREEN_BUTTON)); 
  bool redPressed = !(PIND & (1 << RED_BUTTON)); 

  if (millis() - lastDebounceTime >= debounceDelay) {
    
    if (redPressed != lastRedPressed) {
      lastDebounceTime = millis(); 
      lastRedPressed = redPressed;

      if (redPressed) { 
        PORTD &= ~((1 << LED_RED) | (1 << LED_GREEN) | (1 << LED_BLUE));

        if (selectedLed == LED_RED) selectedLed = LED_GREEN;
        else if (selectedLed == LED_GREEN) selectedLed = LED_BLUE;
        else selectedLed = LED_RED;
        
        displayLedInfo();
      }
    }

    if (greenPressed != lastGreenPressed) {
      lastDebounceTime = millis();
      lastGreenPressed = greenPressed;

      if (greenPressed) {
        if (selectedLed == LED_RED) {
          red_time += step;
          if (red_time > 2000) red_time = 500;
        } else if (selectedLed == LED_GREEN) {
          green_time += step;
          if (green_time > 2000) green_time = 500;
        } else {
          blue_time += step;
          if (blue_time > 2000) blue_time = 500;
        }
        displayLedInfo();
      }
    }
  }

  blinkSelectedLed();
}
