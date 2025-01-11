package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SetupButtonPanel {
    public static void setupButtonPanelStandardMode(JPanel buttonPanel) {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(6, 4));
        buttonPanel.add(new CalcButton("C/CE"));
        buttonPanel.add(new CalcButton("OFF"));
        buttonPanel.add(new CalcButton("SQRT"));
        buttonPanel.add(new CalcButton("%"));
        buttonPanel.add(new CalcButton("MRC"));
        buttonPanel.add(new CalcButton("M-"));
        buttonPanel.add(new CalcButton("M+"));
        buttonPanel.add(new CalcButton("/"));
        buttonPanel.add(new CalcButton("7"));
        buttonPanel.add(new CalcButton("8"));
        buttonPanel.add(new CalcButton("9"));
        buttonPanel.add(new CalcButton("*"));
        buttonPanel.add(new CalcButton("4"));
        buttonPanel.add(new CalcButton("5"));
        buttonPanel.add(new CalcButton("6"));
        buttonPanel.add(new CalcButton("-"));
        buttonPanel.add(new CalcButton("1"));
        buttonPanel.add(new CalcButton("2"));
        buttonPanel.add(new CalcButton("3"));
        buttonPanel.add(new CalcButton("+"));
        buttonPanel.add(new CalcButton("."));
        buttonPanel.add(new CalcButton("0"));
        buttonPanel.add(new CalcButton("00"));
        buttonPanel.add(new CalcButton("="));

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelScientific(JPanel buttonPanel) {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(2, 7));
        functionPanel.setPreferredSize(new Dimension(600, 75));
        functionPanel.setBorder(new EmptyBorder(0, 0, 25, 0));

        functionPanel.add(new CalcButton("sin"));
        functionPanel.add(new CalcButton("cos"));
        functionPanel.add(new CalcButton("tan"));
        functionPanel.add(new CalcButton("asin"));
        functionPanel.add(new CalcButton("acos"));
        functionPanel.add(new CalcButton("atan"));
        functionPanel.add(new CalcButton("abs"));
        functionPanel.add(new CalcButton("exp"));
        functionPanel.add(new CalcButton("ln"));
        functionPanel.add(new CalcButton("log"));
        functionPanel.add(new CalcButton("a^x"));
        functionPanel.add(new CalcButton("cbrt"));
        functionPanel.add(new CalcButton("floor"));
        functionPanel.add(new CalcButton("ceil"));

        buttonPanel.add(functionPanel);

        JPanel operationsAndNumbersPanel = new JPanel();
        operationsAndNumbersPanel.setLayout(new GridLayout(6, 5));
        operationsAndNumbersPanel.setPreferredSize(new Dimension(600, 400));

        operationsAndNumbersPanel.add(new CalcButton("C/CE"));
        operationsAndNumbersPanel.add(new CalcButton("OFF"));
        operationsAndNumbersPanel.add(new CalcButton("SQRT"));
        operationsAndNumbersPanel.add(new CalcButton("%"));
        operationsAndNumbersPanel.add(new CalcButton("<---"));
        operationsAndNumbersPanel.add(new CalcButton("MRC"));
        operationsAndNumbersPanel.add(new CalcButton("M-"));
        operationsAndNumbersPanel.add(new CalcButton("M+"));
        operationsAndNumbersPanel.add(new CalcButton("/"));
        operationsAndNumbersPanel.add(new CalcButton("("));
        operationsAndNumbersPanel.add(new CalcButton("7"));
        operationsAndNumbersPanel.add(new CalcButton("8"));
        operationsAndNumbersPanel.add(new CalcButton("9"));
        operationsAndNumbersPanel.add(new CalcButton("*"));
        operationsAndNumbersPanel.add(new CalcButton(")"));
        operationsAndNumbersPanel.add(new CalcButton("4"));
        operationsAndNumbersPanel.add(new CalcButton("5"));
        operationsAndNumbersPanel.add(new CalcButton("6"));
        operationsAndNumbersPanel.add(new CalcButton("-"));
        operationsAndNumbersPanel.add(new CalcButton("pi"));
        operationsAndNumbersPanel.add(new CalcButton("1"));
        operationsAndNumbersPanel.add(new CalcButton("2"));
        operationsAndNumbersPanel.add(new CalcButton("3"));
        operationsAndNumbersPanel.add(new CalcButton("+"));
        operationsAndNumbersPanel.add(new CalcButton("e"));
        operationsAndNumbersPanel.add(new CalcButton("."));
        operationsAndNumbersPanel.add(new CalcButton("0"));
        operationsAndNumbersPanel.add(new CalcButton("00"));
        operationsAndNumbersPanel.add(new CalcButton("="));
        operationsAndNumbersPanel.add(new CalcButton(""));

        buttonPanel.add(operationsAndNumbersPanel);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelBinaryDecimalInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        setupButtonPanelStandardMode(buttonPanel);

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelBinaryBinaryInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        buttonPanel.setLayout(new GridLayout(3, 4));
        buttonPanel.add(new CalcButton("C/CE"));
        buttonPanel.add(new CalcButton("MRC"));
        buttonPanel.add(new CalcButton("M+"));
        buttonPanel.add(new CalcButton("M-"));
        buttonPanel.add(new CalcButton("/"));
        buttonPanel.add(new CalcButton("*"));
        buttonPanel.add(new CalcButton("+"));
        buttonPanel.add(new CalcButton("-"));
        buttonPanel.add(new CalcButton("1"));
        buttonPanel.add(new CalcButton("0"));
        buttonPanel.add(new CalcButton("."));
        buttonPanel.add(new CalcButton("="));

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelHexadecimalDecimalInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        setupButtonPanelStandardMode(buttonPanel);

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void setupButtonPanelHexadecimalHexadecimalInput(JPanel buttonPanel) {
        buttonPanel.removeAll();

        buttonPanel.setLayout(new GridLayout(5, 5));
        buttonPanel.add(new CalcButton("C/CE"));
        buttonPanel.add(new CalcButton("MRC"));
        buttonPanel.add(new CalcButton("M+"));
        buttonPanel.add(new CalcButton("M-"));
        buttonPanel.add(new CalcButton("/"));
        buttonPanel.add(new CalcButton("F"));
        buttonPanel.add(new CalcButton("E"));
        buttonPanel.add(new CalcButton("D"));
        buttonPanel.add(new CalcButton("C"));
        buttonPanel.add(new CalcButton("*"));
        buttonPanel.add(new CalcButton("B"));
        buttonPanel.add(new CalcButton("A"));
        buttonPanel.add(new CalcButton("9"));
        buttonPanel.add(new CalcButton("8"));
        buttonPanel.add(new CalcButton("+"));
        buttonPanel.add(new CalcButton("7"));
        buttonPanel.add(new CalcButton("6"));
        buttonPanel.add(new CalcButton("5"));
        buttonPanel.add(new CalcButton("4"));
        buttonPanel.add(new CalcButton("-"));
        buttonPanel.add(new CalcButton("3"));
        buttonPanel.add(new CalcButton("2"));
        buttonPanel.add(new CalcButton("1"));
        buttonPanel.add(new CalcButton("0"));
        buttonPanel.add(new CalcButton("="));

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
