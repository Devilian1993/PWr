const byte LED_RED = 6;
const byte LED_GREEN = 5;
const byte LED_BLUE = 3;

bool isNumeric(String str) {
  if (str.length() == 0) {
    return false;
  }

  for (byte i = 0; i < str.length(); i++) {
    if (!isdigit(str.charAt(i))) {
      return false; 
    }
  }
  return true; 
}

void setup()
{
  Serial.begin(9600);
  DDRD |= (1 << LED_RED) | (1 << LED_GREEN) | (1 << LED_BLUE);
  PORTD &= ~((1 << LED_RED) | (1 << LED_GREEN) | (1 << LED_BLUE));
  while(!Serial) {

  }
}

void loop() {
  if(Serial.available() > 0) {
    String command = Serial.readStringUntil('\n');

    command.trim();
    command.toLowerCase();
    int spaceIndex = command.indexOf(' ');
  
    if(spaceIndex != -1) {
      byte currentColor;
      bool error = false;

      String color = command.substring(0, spaceIndex);
      String numberStr = command.substring(spaceIndex + 1);
      int brightness = numberStr.toInt();

      if(numberStr.indexOf(' ') != -1 || !isNumeric(numberStr)) {
        error = true;
      }

      if(color == "red") {
        currentColor = LED_RED;
      } else if(color == "green") {
        currentColor = LED_GREEN;
      } else if(color == "blue") {
        currentColor = LED_BLUE;
      } else {
        error = true;
      }

      if(brightness < 0 || brightness > 255) {
        error = true;
      }

      if(error) {
        Serial.println("Wrong command");
      } else {
        analogWrite(currentColor, brightness);
      }
    }
  }
}
