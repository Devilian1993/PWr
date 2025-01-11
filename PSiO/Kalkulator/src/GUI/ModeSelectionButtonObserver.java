package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class ModeSelectionButtonObserver {
    private ArrayList<ModeSelectionButton> buttons;
    private JPanel buttonPanel;

    public ModeSelectionButtonObserver(JPanel buttonPanel) {
        buttons = new ArrayList<ModeSelectionButton>();
        this.buttonPanel = buttonPanel;
    }

    public void addButton(ModeSelectionButton button) {
        buttons.add(button);
    }


    public void notifyButtonsButtonPressed(ModeSelectionButton buttonPressed) {
        if (buttonPressed.getText().equals("DEC") && buttonPressed.IsInput()) {
            for (ModeSelectionButton button : buttons) {
                if (button.getText().equals("HEX") && buttonPressed.IsInput()) {
                    SetupButtonPanel.setupButtonPanelHexadecimalDecimalInput(buttonPanel);
                } else if (button.getText().equals("BIN") && buttonPressed.IsInput()) {
                    SetupButtonPanel.setupButtonPanelBinaryDecimalInput(buttonPanel);
                }
            }
        } else if (buttonPressed.getText().equals("BIN") && buttonPressed.IsInput()){
            //buttonPressed.setEnabled(true);
            //for (ModeSelectionButton button : buttons) {
            //    if (!buttonPressed.getText().equals("DEC") && button.IsInput()) {
            //        button.setEnabled(false);
            //    }
            //}
            SetupButtonPanel.setupButtonPanelBinaryBinaryInput(buttonPanel);
        } else if (buttonPressed.getText().equals("HEX") && buttonPressed.IsInput()){
            //buttonPressed.setEnabled(true);
            //for (ModeSelectionButton button : buttons) {
            //    if (!buttonPressed.getText().equals("DEC") && button.IsInput()) {
            //        button.setEnabled(false);
            //    }
            //}
            SetupButtonPanel.setupButtonPanelHexadecimalHexadecimalInput(buttonPanel);
        }
    }
}
