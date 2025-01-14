package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

import Calculator.NumberBaseUtils.*;

public class InputPanel extends JPanel {
    private CalcButtonObserver observer;
    private String inputText;
    private JLabel textLabel;

    public InputPanel() {
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(new Color(163,163,163));
        this.inputText = "";
        this.textLabel = new JLabel(inputText);
        textLabel.setPreferredSize(new Dimension(600, 100));
        textLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 64));
        this.add(textLabel);
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String text) {
        this.inputText = text;
        textLabel.setText(inputText);

    }

    public void changeInputTextBase(int base, int previousBase) {
        if (!inputText.isEmpty() && Calculator.Utils.isNumeric(inputText)) {
            if (base == 2 && previousBase == 10) {
                try {
                    this.inputText = DecimalToBinaryConverter.convertToBinary(inputText);
                    textLabel.setText(inputText);
                    observer.clearEquation();
                } catch (IllegalArgumentException e) {
                    clearText();
                }
            } else if (base == 16 && previousBase == 10) {
                this.inputText = DecimalToHexConverter.convertToHex(inputText);
                textLabel.setText(inputText);
                observer.clearEquation();
            } else if (base == 10 && previousBase == 2) {
                this.inputText = BinaryToDecimalConverter.convertToDecimal(inputText);
                textLabel.setText(inputText);
                observer.clearEquation();
            } else {
                this.inputText = HexToDecimalConverter.convertToDecimal(inputText);
                textLabel.setText(inputText);
                observer.clearEquation();
            }
        } else {
            clearText();
        }
    }

    public void addToInputText(String text) {
        this.inputText += text.equals("a^x") ? "^" : text;
        textLabel.setText(inputText);
    }

    public void removePreviousEntry() {
        this.inputText = inputText.length() > 1 ? inputText.substring(0, inputText.length() - 1) : "";
        textLabel.setText(inputText);
    }

    public void clearText() {
        this.inputText = "";
        textLabel.setText(inputText);
    }

    public void registerObserver(CalcButtonObserver observer) {
        this.observer = observer;
    }
}
