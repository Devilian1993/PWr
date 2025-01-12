package GUI;

import java.util.ArrayList;
import Calculator.Calculator;

public class CalcButtonObserver {
    ArrayList<CalcButton> buttons;
    Calculator calculator;
    InputPanel inputPanel;
    String equationString;

    CalcButtonObserver(InputPanel inputPanel) {
        this.buttons = new ArrayList<CalcButton>();
        this.equationString = "";
        this.calculator = new Calculator();
        this.inputPanel = inputPanel;
    }

    public void addButton(CalcButton button) {
        this.buttons.add(button);
    }

    public void notifyButtonsPressed(CalcButton buttonPressed) {
        if (buttonPressed.isNumber()) {
            //inputPanel.add()
        }
    }
}
