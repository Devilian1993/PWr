package GUI;

import java.util.ArrayList;
import Calculator.Calculator;

public class CalcButtonObserver {
    private ArrayList<CalcButton> buttons;
    private Calculator calculator;
    private InputPanel inputPanel;
    private String equationString;
    private String calculatorMode;

    CalcButtonObserver(InputPanel inputPanel) {
        this.buttons = new ArrayList<CalcButton>();
        this.equationString = "";
        this.calculator = new Calculator();
        this.inputPanel = inputPanel;
    }

    public void setCalculatorMode(String mode) {
        this.calculatorMode = mode;
    }

    public void addButton(CalcButton button) {
        this.buttons.add(button);
    }

    public void notifyButtonsPressed(CalcButton buttonPressed) {
        if (calculatorMode.equals("Scientific")) {
            if ((buttonPressed.isNumber() || buttonPressed.isOperator() || buttonPressed.getText().equals(".")) && !inputPanel.getInputText().equals("ERROR")) {
                inputPanel.addToInputText(buttonPressed.getText());
                equationString = inputPanel.getInputText();
            } else {
                if (buttonPressed.getText().equals("=") && inputPanel.getInputText().length() > 2 && !inputPanel.getInputText().equals("ERROR")) {
                    inputPanel.setInputText(calculator.calculate(equationString));
                    equationString = "";
                } else if (buttonPressed.getText().equals("C/CE")) {
                    inputPanel.clearText();
                } else if (buttonPressed.getText().equals("<---")) {
                    inputPanel.removePreviousEntry();
                }
            }
        } else {
            if ((buttonPressed.isNumber() || buttonPressed.getText().equals(".")) && !inputPanel.getInputText().equals("ERROR")) {
                inputPanel.addToInputText(buttonPressed.getText());
                equationString += inputPanel.getInputText();
            } else if (buttonPressed.isOperator() && !inputPanel.getInputText().equals("ERROR") && !buttonPressed.getText().equals("=") && !equationString.isEmpty()) {
                inputPanel.clearText();
                equationString += buttonPressed.getText();
            } else {
                if (buttonPressed.getText().equals("=") && equationString.length() > 2 && !inputPanel.getInputText().equals("ERROR")) {
                    inputPanel.setInputText(calculator.calculate(equationString));
                    equationString = inputPanel.getInputText();
                } else if (buttonPressed.getText().equals("C/CE")) {
                    inputPanel.clearText();
                } else if (buttonPressed.getText().equals("<---")) {
                    inputPanel.removePreviousEntry();
                }
            }
        }
    }
}
