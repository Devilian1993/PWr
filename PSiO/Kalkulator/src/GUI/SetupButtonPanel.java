package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SetupButtonPanel {
    private static InputPanel inputPanel;
    private static String calculatorInputMode = "Standard";
    private static String calculatorOutputMode = "DEC";
    private static final CalcButtonObserver observer = new CalcButtonObserver();
    private static boolean firstBaseToggle = true;

    public static void setInputPanel(InputPanel inputPanel) {
        SetupButtonPanel.inputPanel = inputPanel;
    }

    public static void setCalculatorInputMode(String mode) {
        calculatorInputMode = mode;
    }

    public static void setCalculatorOutputMode(String mode) {
        calculatorOutputMode = mode;
    }

    public static String getCalculatorOutputMode() {
        return calculatorOutputMode;
    }

    public static int getPreviousBase() {
        if (calculatorInputMode.equals("HexadecimalDecimalInput")) {
            return 16;
        } else if (calculatorInputMode.equals("BinaryDecimalInput")) {
            return 2;
        } else {
            return 10;
        }
    }

    public static void clearInputPanel() {
        inputPanel.clearText();
    }

    public static void setupButtonPanelStandardMode(JPanel buttonPanel) {
        buttonPanel.removeAll();

        //CalcButtonObserver observer = new CalcButtonObserver(inputPanel);
        inputPanel.registerObserver(observer);
        observer.setInputPanel(inputPanel);
        observer.setCalculatorInputMode(calculatorInputMode);
        observer.setCalculatorOutputMode(calculatorOutputMode);

        if (calculatorInputMode.equals("Standard")) {
            firstBaseToggle = true;
        }

        buttonPanel.setLayout(new GridLayout(6, 4));
        buttonPanel.add(new CalcButton("C/CE", observer, false));
        buttonPanel.add(new CalcButton("OFF", observer, false));
        buttonPanel.add(new CalcButton("sqrt", observer, false));
        buttonPanel.add(new CalcButton("%", observer, false));
        buttonPanel.add(new CalcButton("MRC", observer, false));
        buttonPanel.add(new CalcButton("M-", observer, false));
        buttonPanel.add(new CalcButton("M+", observer, false));
        buttonPanel.add(new CalcButton("/", observer, false));
        buttonPanel.add(new CalcButton("7", observer, true));
        buttonPanel.add(new CalcButton("8", observer, true));
        buttonPanel.add(new CalcButton("9", observer, true));
        buttonPanel.add(new CalcButton("*", observer, false));
        buttonPanel.add(new CalcButton("4", observer, true));
        buttonPanel.add(new CalcButton("5", observer, true));
        buttonPanel.add(new CalcButton("6", observer, true));
        buttonPanel.add(new CalcButton("-", observer, false));
        buttonPanel.add(new CalcButton("1", observer, true));
        buttonPanel.add(new CalcButton("2", observer, true));
        buttonPanel.add(new CalcButton("3", observer, true));
        buttonPanel.add(new CalcButton("+", observer, false));
        buttonPanel.add(new CalcButton(".", observer, false));
        buttonPanel.add(new CalcButton("0", observer, true));
        buttonPanel.add(new CalcButton("00", observer, true));
        buttonPanel.add(new CalcButton("=", observer, false));

        for (Component button : buttonPanel.getComponents()) {
            observer.addButton((CalcButton) button);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelScientific(JPanel buttonPanel) {
        firstBaseToggle = true;

        buttonPanel.removeAll();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(2, 7));
        functionPanel.setPreferredSize(new Dimension(600, 75));
        functionPanel.setBorder(new EmptyBorder(0, 0, 25, 0));

        //CalcButtonObserver observer = new CalcButtonObserver(inputPanel);
        observer.setCalculatorInputMode(calculatorInputMode);
        observer.setCalculatorOutputMode(calculatorOutputMode);
        inputPanel.registerObserver(observer);
        observer.setInputPanel(inputPanel);
        functionPanel.add(new CalcButton("sin", observer, false));
        functionPanel.add(new CalcButton("cos", observer, false));
        functionPanel.add(new CalcButton("tan", observer, false));
        functionPanel.add(new CalcButton("asin", observer, false));
        functionPanel.add(new CalcButton("acos", observer, false));
        functionPanel.add(new CalcButton("atan", observer, false));
        functionPanel.add(new CalcButton("abs", observer, false));
        functionPanel.add(new CalcButton("exp", observer, false));
        functionPanel.add(new CalcButton("ln", observer, false));
        functionPanel.add(new CalcButton("log", observer, false));
        functionPanel.add(new CalcButton("a^x", observer, false));
        functionPanel.add(new CalcButton("cbrt", observer, false));
        functionPanel.add(new CalcButton("floor", observer, false));
        functionPanel.add(new CalcButton("ceil", observer, false));

        buttonPanel.add(functionPanel);

        JPanel operationsAndNumbersPanel = new JPanel();
        operationsAndNumbersPanel.setLayout(new GridLayout(6, 5));
        operationsAndNumbersPanel.setPreferredSize(new Dimension(600, 400));

        operationsAndNumbersPanel.add(new CalcButton("C/CE", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("OFF", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("sqrt", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("%", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("<---", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("MRC", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("M-", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("M+", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("/", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("(", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("7", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("8", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("9", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("*", observer, false));
        operationsAndNumbersPanel.add(new CalcButton(")", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("4", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("5", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("6", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("-", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("pi", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("1", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("2", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("3", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("+", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("e", observer, false));
        operationsAndNumbersPanel.add(new CalcButton(".", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("0", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("00", observer, true));
        operationsAndNumbersPanel.add(new CalcButton("=", observer, false));
        operationsAndNumbersPanel.add(new CalcButton("", observer, false));

        for (Component button : functionPanel.getComponents()) {
            observer.addButton((CalcButton) button);
        }
        for (Component button : operationsAndNumbersPanel.getComponents()) {
            observer.addButton((CalcButton) button);
        }

        buttonPanel.add(operationsAndNumbersPanel);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelBinaryDecimalInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        setCalculatorOutputMode("BIN");
        setupButtonPanelStandardMode(buttonPanel);

        if (!firstBaseToggle) {
            inputPanel.changeInputTextBase(10, getPreviousBase());
        }

        firstBaseToggle = false;

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelBinaryBinaryInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        setCalculatorOutputMode("BIN");
        //CalcButtonObserver observer = new CalcButtonObserver(inputPanel);
        inputPanel.registerObserver(observer);
        observer.setCalculatorInputMode(calculatorInputMode);
        observer.setCalculatorOutputMode(calculatorOutputMode);
        observer.setInputPanel(inputPanel);
        buttonPanel.setLayout(new GridLayout(3, 4));
        buttonPanel.add(new CalcButton("C/CE", observer, false));
        buttonPanel.add(new CalcButton("MRC", observer, false));
        buttonPanel.add(new CalcButton("M+", observer, false));
        buttonPanel.add(new CalcButton("M-", observer, false));
        buttonPanel.add(new CalcButton("/", observer, false));
        buttonPanel.add(new CalcButton("*", observer, false));
        buttonPanel.add(new CalcButton("+", observer, false));
        buttonPanel.add(new CalcButton("-", observer, false));
        buttonPanel.add(new CalcButton("BIN1", observer, true));
        buttonPanel.add(new CalcButton("BIN0", observer, true));
        buttonPanel.add(new CalcButton(".", observer, false));
        buttonPanel.add(new CalcButton("=", observer, false));

        for (Component button : buttonPanel.getComponents()) {
            observer.addButton((CalcButton) button);
        }

        if (!firstBaseToggle) {
            inputPanel.changeInputTextBase(2, getPreviousBase());
        }

        firstBaseToggle = false;

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelHexadecimalDecimalInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        setCalculatorOutputMode("HEX");
        setupButtonPanelStandardMode(buttonPanel);

        if (!firstBaseToggle) {
            inputPanel.changeInputTextBase(10, getPreviousBase());
        }

        firstBaseToggle = false;

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelHexadecimalHexadecimalInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        setCalculatorOutputMode("HEX");
        //CalcButtonObserver observer = new CalcButtonObserver(inputPanel);
        inputPanel.registerObserver(observer);
        observer.setCalculatorInputMode(calculatorInputMode);
        observer.setCalculatorOutputMode(calculatorOutputMode);
        observer.setInputPanel(inputPanel);

        buttonPanel.setLayout(new GridLayout(5, 5));
        buttonPanel.add(new CalcButton("C/CE", observer, false));
        buttonPanel.add(new CalcButton("MRC", observer, false));
        buttonPanel.add(new CalcButton("M+", observer, false));
        buttonPanel.add(new CalcButton("M-", observer, false));
        buttonPanel.add(new CalcButton("/", observer, false));
        buttonPanel.add(new CalcButton("0xF", observer, true));
        buttonPanel.add(new CalcButton("0xE", observer, true));
        buttonPanel.add(new CalcButton("0xD", observer, true));
        buttonPanel.add(new CalcButton("0xC", observer, true));
        buttonPanel.add(new CalcButton("*", observer, false));
        buttonPanel.add(new CalcButton("0xB", observer, true));
        buttonPanel.add(new CalcButton("0xA", observer, true));
        buttonPanel.add(new CalcButton("0x9", observer, true));
        buttonPanel.add(new CalcButton("0x8", observer, true));
        buttonPanel.add(new CalcButton("+", observer, false));
        buttonPanel.add(new CalcButton("0x7", observer, true));
        buttonPanel.add(new CalcButton("0x6", observer, true));
        buttonPanel.add(new CalcButton("0x5", observer, true));
        buttonPanel.add(new CalcButton("0x4", observer, true));
        buttonPanel.add(new CalcButton("-", observer, false));
        buttonPanel.add(new CalcButton("0x3", observer, true));
        buttonPanel.add(new CalcButton("0x2", observer, true));
        buttonPanel.add(new CalcButton("0x1", observer, true));
        buttonPanel.add(new CalcButton("0x0", observer, true));
        buttonPanel.add(new CalcButton("=", observer, false));

        for (Component button : buttonPanel.getComponents()) {
            observer.addButton((CalcButton) button);
        }

        if (!firstBaseToggle) {
            inputPanel.changeInputTextBase(16, getPreviousBase());
        }

        firstBaseToggle = false;

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
