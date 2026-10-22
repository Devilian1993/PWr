const byte LED_RED = 6;
const byte LED_GREEN = 5;
const byte LED_BLUE = 3;

const byte RED_BUTTON = 2;
const byte GREEN_BUTTON = 4;

int counter = 0;

void blink(int n) {
  for(int i = 0; i < n; i++) {
    PORTD |= (1 << LED_RED);
    delay(200);
    PORTD &= ~(1 << LED_RED);
    delay(200);
  }
}

void setup()
{
  Serial.begin(9600);
  DDRD |= (1 << LED_RED) | (1 << LED_GREEN) | (1 << LED_BLUE);
  PORTD |= (1 << RED_BUTTON) | (1 << GREEN_BUTTON);
}

void loop()
{
  bool greenPressed = !(PIND & (1 << GREEN_BUTTON)); 
  bool redPressed = !(PIND & (1 << RED_BUTTON)); 

  if (greenPressed || redPressed) {
    delay(80); 

    greenPressed = !(PIND & (1 << GREEN_BUTTON)); 
    redPressed = !(PIND & (1 << RED_BUTTON)); 

    if (greenPressed && redPressed) {
      Serial.println("Dwa na raz");
      blink(counter);
    } else if (greenPressed) {
      counter++;
      Serial.println(counter);
    } else if (redPressed) {
      counter--;
      Serial.println(counter);
    }

    while (!(PIND & (1 << GREEN_BUTTON)) || !(PIND & (1 << RED_BUTTON))) {
      delay(10); 
    }
    
    delay(50); 
  }
}
