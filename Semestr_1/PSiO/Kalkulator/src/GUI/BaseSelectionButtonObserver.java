package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class BaseSelectionButtonObserver {
    private ArrayList<BaseSelectionButton> buttons;
    private JPanel buttonPanel;

    public BaseSelectionButtonObserver(JPanel buttonPanel) {
        buttons = new ArrayList<BaseSelectionButton>();
        this.buttonPanel = buttonPanel;
    }

    public void addButton(BaseSelectionButton button) {
        buttons.add(button);
    }


    public void notifyButtonsButtonPressed(BaseSelectionButton buttonPressed) {
        if (buttonPressed.getText().equals("DEC") && buttonPressed.IsInput()) {
            for (BaseSelectionButton button : buttons) {
                if (button.getText().equals("HEX") && buttonPressed.IsInput()) {
                    SetupButtonPanel.setCalculatorInputMode("HexadecimalDecimalInput");
                    SetupButtonPanel.setupButtonPanelHexadecimalDecimalInput(buttonPanel);
                } else if (button.getText().equals("BIN") && buttonPressed.IsInput()) {
                    SetupButtonPanel.setCalculatorInputMode("BinaryDecimalInput");
                    SetupButtonPanel.setupButtonPanelBinaryDecimalInput(buttonPanel);
                }
            }
        } else if (buttonPressed.getText().equals("BIN") && buttonPressed.IsInput()){
            SetupButtonPanel.setCalculatorInputMode("BinaryBinaryInput");
            SetupButtonPanel.setupButtonPanelBinaryBinaryInput(buttonPanel);
        } else if (buttonPressed.getText().equals("HEX") && buttonPressed.IsInput()){
            SetupButtonPanel.setCalculatorInputMode("HexadecimalHexadecimalInput");
            SetupButtonPanel.setupButtonPanelHexadecimalHexadecimalInput(buttonPanel);
        } else if (buttonPressed.getText().equals("DEC")) {
            SetupButtonPanel.setCalculatorOutputMode("DEC");
        } else if (buttonPressed.getText().equals("BIN")) {
            SetupButtonPanel.setCalculatorOutputMode("BIN");
        } else {
            SetupButtonPanel.setCalculatorOutputMode("HEX");
        }
    }
}
