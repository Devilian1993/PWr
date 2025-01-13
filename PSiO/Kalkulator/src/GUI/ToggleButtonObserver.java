package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class ToggleButtonObserver {
    private ArrayList<ToggleButton> buttons;
    private JPanel buttonPanel;
    private JPanel baseSelectionPanel;

    public ToggleButtonObserver(JPanel buttonPanel, JPanel baseSelectionPanel) {
        buttons = new ArrayList<ToggleButton>();
        this.buttonPanel = buttonPanel;
        this.baseSelectionPanel = baseSelectionPanel;
    }

    public void addButton(ToggleButton button) {
        buttons.add(button);
    }

    public void notifyButtonsButtonPressed(ToggleButton selectedButton) {
        for (ToggleButton button : buttons) {
            if (button.equals(selectedButton)) {
                button.setEnabled(false);
            } else {
                button.setEnabled(true);
            }
        }

        if (selectedButton.getText().equals("Standard")) {
            SetupButtonPanel.setCalculatorMode("Standard");
            SetupButtonPanel.setupButtonPanelStandardMode(buttonPanel);
            SetupBaseSelectionPanel.setupBaseSelectionPanelEmpty(baseSelectionPanel);
        } else if (selectedButton.getText().equals("Scientific")) {
            SetupButtonPanel.setCalculatorMode("Scientific");
            SetupButtonPanel.setupButtonPanelScientific(buttonPanel);
            SetupBaseSelectionPanel.setupBaseSelectionPanelEmpty(baseSelectionPanel);
        } else if (selectedButton.getText().equals("Binary")) {
            SetupButtonPanel.setCalculatorMode("BinaryDecimalInput");
            SetupButtonPanel.setupButtonPanelBinaryDecimalInput(buttonPanel);
            SetupBaseSelectionPanel.setupBaseSelectionPanelBinary(baseSelectionPanel, buttonPanel);
        } else {
            SetupButtonPanel.setCalculatorMode("HexadecimalDecimalInput");
            SetupButtonPanel.setupButtonPanelHexadecimalDecimalInput(buttonPanel);
            SetupBaseSelectionPanel.setupBaseSelectionPanelHexadecimal(baseSelectionPanel, buttonPanel);
        }
    }
}
