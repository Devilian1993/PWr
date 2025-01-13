package GUI;

import java.util.ArrayList;
import Calculator.Calculator;

public class CalcButtonObserver {
    private ArrayList<CalcButton> buttons;
    private final Calculator calculator;
    private InputPanel inputPanel;
    private String equationString;
    private String calculatorInputMode;
    private String calculatorOutputMode;

    CalcButtonObserver() {
        this.buttons = new ArrayList<CalcButton>();
        this.equationString = "";
        this.calculator = new Calculator();
        //this.inputPanel = inputPanel;
    }

    public void setCalculatorInputMode(String mode) {
        this.calculatorInputMode = mode;
    }

    public void setCalculatorOutputMode(String mode) { this.calculatorOutputMode = mode; }

    public void setInputPanel(InputPanel inputPanel) { this.inputPanel = inputPanel; }

    public void addButton(CalcButton button) {
        this.buttons.add(button);
    }

    public void notifyButtonsPressed(CalcButton buttonPressed) {
        calculatorOutputMode = SetupButtonPanel.getCalculatorOutputMode();
        if (calculatorInputMode.equals("Standard")) {
            if ((buttonPressed.isNumber() || buttonPressed.getText().equals(".")) && !inputPanel.getInputText().equals("ERROR")) {
                inputPanel.addToInputText(buttonPressed.getText());
                equationString += buttonPressed.getText();
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
        } else {
            if ((buttonPressed.isNumber() || buttonPressed.isOperator() || buttonPressed.getText().equals(".")) && !inputPanel.getInputText().equals("ERROR")) {
                if (buttonPressed.isNumber() && buttonPressed.getText().startsWith("BIN")) {
                    inputPanel.addToInputText(buttonPressed.getText().substring(3));
                } else if (buttonPressed.isNumber() && buttonPressed.getText().startsWith("0x")) {
                    inputPanel.addToInputText(buttonPressed.getText().substring(2));
                } else {
                    inputPanel.addToInputText(buttonPressed.getText());
                }
                equationString = inputPanel.getInputText();
            } else {
                if (buttonPressed.getText().equals("=") && inputPanel.getInputText().length() > 2 && !inputPanel.getInputText().equals("ERROR")) {
                    if (calculatorInputMode.equals("Scientific")) {
                        inputPanel.setInputText(calculator.calculate(equationString));
                        equationString = "";
                    } else if (calculatorInputMode.equals("HexadecimalDecimalInput")) {
                        inputPanel.setInputText(calculator.calculateHexadecimal(equationString, 10, calculatorOutputMode.equals("DEC") ? 10 : 16));
                        equationString = "";
                    } else if (calculatorInputMode.equals("HexadecimalHexadecimalInput")) {
                        inputPanel.setInputText(calculator.calculateHexadecimal(equationString, 16, calculatorOutputMode.equals("DEC") ? 10 : 16));
                        equationString = "";
                    } else if (calculatorInputMode.equals("BinaryDecimalInput")) {
                        inputPanel.setInputText(calculator.calculateBinary(equationString, 10, calculatorOutputMode.equals("DEC") ? 10 : 2));
                        equationString = "";
                    } else {
                        inputPanel.setInputText(calculator.calculateBinary(equationString, 2, calculatorOutputMode.equals("DEC") ? 10 : 2));
                        equationString = "";
                    }
                } else if (buttonPressed.getText().equals("C/CE")) {
                    inputPanel.clearText();
                } else if (buttonPressed.getText().equals("<---")) {
                    inputPanel.removePreviousEntry();
                }
            }
        }
    }
}
