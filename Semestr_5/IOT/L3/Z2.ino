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

void blinkAllLeds() {
  unsigned long currentMillis = millis();

  static unsigned long lastRedChange = 0UL;
  static int redState = LOW;
  if (currentMillis - lastRedChange >= red_time) {
    lastRedChange += red_time;
    if (redState == HIGH) {
      redState = LOW;
      PORTD &= ~(1 << LED_RED);
    } else {
      redState = HIGH;
      PORTD |= (1 << LED_RED);
    }
  }

  static unsigned long lastGreenChange = 0UL;
  static int greenState = LOW;
  if (currentMillis - lastGreenChange >= green_time) {
    lastGreenChange += green_time;
    if (greenState == HIGH) {
      greenState = LOW;
      PORTD &= ~(1 << LED_GREEN);
    } else {
      greenState = HIGH;
      PORTD |= (1 << LED_GREEN);
    }
  }

  static unsigned long lastBlueChange = 0UL;
  static int blueState = LOW;
  if (currentMillis - lastBlueChange >= blue_time) {
    lastBlueChange += blue_time;
    if (blueState == HIGH) {
      blueState = LOW;
      PORTD &= ~(1 << LED_BLUE);
    } else {
      blueState = HIGH;
      PORTD |= (1 << LED_BLUE);
    }
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
    
    if (redPressed && !lastRedPressed) {
      lastDebounceTime = millis();
      
      if (selectedLed == LED_RED) {
        selectedLed = LED_GREEN;
      } else if (selectedLed == LED_GREEN) {
        selectedLed = LED_BLUE;
      } else {
        selectedLed = LED_RED;
      }
      displayLedInfo();
    }

    if (greenPressed && !lastGreenPressed) {
      lastDebounceTime = millis();
      
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

  lastRedPressed = redPressed;
  lastGreenPressed = greenPressed;

  blinkAllLeds();
}
