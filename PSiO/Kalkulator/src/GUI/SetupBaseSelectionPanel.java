package GUI;

import javax.swing.*;
import java.awt.*;

public class SetupBaseSelectionPanel {
    public static void setupBaseSelectionPanelEmpty(JPanel baseSelectionPanel) {
        baseSelectionPanel.removeAll();
        baseSelectionPanel.revalidate();
        baseSelectionPanel.repaint();
    }

    public static void setupBaseSelectionPanelBinary(JPanel baseSelectionPanel, JPanel buttonPanel) {
        baseSelectionPanel.removeAll();

        JPanel baseSelectionPanelInput = new JPanel();
        baseSelectionPanelInput.setPreferredSize(new Dimension(120, 40));
        baseSelectionPanelInput.setBackground(new Color(163,163,163));
        baseSelectionPanelInput.setLayout(new FlowLayout(FlowLayout.LEFT));
        baseSelectionPanel.add(baseSelectionPanelInput);
        JLabel inputText = new JLabel("Input");
        inputText.setForeground(Color.BLACK);
        baseSelectionPanelInput.add(inputText);

        JPanel baseSelectionPanelOutput = new JPanel();
        baseSelectionPanelOutput.setPreferredSize(new Dimension(120, 40));
        baseSelectionPanelOutput.setBackground(new Color(163,163,163));
        baseSelectionPanelOutput.setLayout(new FlowLayout(FlowLayout.LEFT));
        baseSelectionPanel.add(baseSelectionPanelOutput);
        JLabel outputText = new JLabel("Output");
        outputText.setForeground(Color.BLACK);
        baseSelectionPanelOutput.add(outputText);

        JPanel baseSelectionPanelInputButtons = new JPanel();
        baseSelectionPanelInputButtons.setPreferredSize(new Dimension(70, 35));
        baseSelectionPanelInputButtons.setBackground(new Color(163,163,163));
        baseSelectionPanelInputButtons.setLayout(new BoxLayout(baseSelectionPanelInputButtons, BoxLayout.Y_AXIS));
        baseSelectionPanelInput.add(baseSelectionPanelInputButtons);
        ButtonGroup inputBaseSelectionButtonGroup = new ButtonGroup();

        JPanel baseSelectionPanelOutputButtons = new JPanel();
        baseSelectionPanelOutputButtons.setPreferredSize(new Dimension(70, 35));
        baseSelectionPanelOutputButtons.setBackground(new Color(163,163,163));
        baseSelectionPanelOutputButtons.setLayout(new BoxLayout(baseSelectionPanelOutputButtons, BoxLayout.Y_AXIS));
        baseSelectionPanelOutput.add(baseSelectionPanelOutputButtons);
        ButtonGroup outputBaseSelectionButtonGroup = new ButtonGroup();

        ModeSelectionButtonObserver observer = new ModeSelectionButtonObserver(buttonPanel);
        ModeSelectionButton inputDEC = new ModeSelectionButton("DEC", true, observer, true);
        ModeSelectionButton inputHEX = new ModeSelectionButton("HEX", observer, true);
        ModeSelectionButton inputBIN = new ModeSelectionButton("BIN", observer, true);
        inputBaseSelectionButtonGroup.add(inputDEC);
        inputBaseSelectionButtonGroup.add(inputHEX);
        inputBaseSelectionButtonGroup.add(inputBIN);
        observer.addButton(inputDEC);
        observer.addButton(inputHEX);

        baseSelectionPanelInputButtons.add(inputDEC);
        baseSelectionPanelInputButtons.add(inputBIN);

        ModeSelectionButton outputDEC = new ModeSelectionButton("DEC", observer, false);
        ModeSelectionButton outputHEX = new ModeSelectionButton("HEX", observer, false);
        ModeSelectionButton outputBIN = new ModeSelectionButton("BIN", true, observer, false);
        outputBaseSelectionButtonGroup.add(outputDEC);
        outputBaseSelectionButtonGroup.add(outputHEX);
        outputBaseSelectionButtonGroup.add(outputBIN);

        baseSelectionPanelOutputButtons.add(outputDEC);
        baseSelectionPanelOutputButtons.add(outputBIN);

        baseSelectionPanel.revalidate();
        baseSelectionPanel.repaint();
    }

    public static void setupBaseSelectionPanelHexadecimal(JPanel baseSelectionPanel, JPanel buttonPanel) {
        baseSelectionPanel.removeAll();

        JPanel baseSelectionPanelInput = new JPanel();
        baseSelectionPanelInput.setPreferredSize(new Dimension(120, 40));
        baseSelectionPanelInput.setBackground(new Color(163,163,163));
        baseSelectionPanelInput.setLayout(new FlowLayout(FlowLayout.LEFT));
        baseSelectionPanel.add(baseSelectionPanelInput);
        JLabel inputText = new JLabel("Input");
        inputText.setForeground(Color.BLACK);
        baseSelectionPanelInput.add(inputText);

        JPanel baseSelectionPanelOutput = new JPanel();
        baseSelectionPanelOutput.setPreferredSize(new Dimension(120, 40));
        baseSelectionPanelOutput.setBackground(new Color(163,163,163));
        baseSelectionPanelOutput.setLayout(new FlowLayout(FlowLayout.LEFT));
        baseSelectionPanel.add(baseSelectionPanelOutput);
        JLabel outputText = new JLabel("Output");
        outputText.setForeground(Color.BLACK);
        baseSelectionPanelOutput.add(outputText);

        JPanel baseSelectionPanelInputButtons = new JPanel();
        baseSelectionPanelInputButtons.setPreferredSize(new Dimension(70, 35));
        baseSelectionPanelInputButtons.setBackground(new Color(163,163,163));
        baseSelectionPanelInputButtons.setLayout(new BoxLayout(baseSelectionPanelInputButtons, BoxLayout.Y_AXIS));
        baseSelectionPanelInput.add(baseSelectionPanelInputButtons);
        ButtonGroup inputBaseSelectionButtonGroup = new ButtonGroup();

        JPanel baseSelectionPanelOutputButtons = new JPanel();
        baseSelectionPanelOutputButtons.setPreferredSize(new Dimension(70, 35));
        baseSelectionPanelOutputButtons.setBackground(new Color(163,163,163));
        baseSelectionPanelOutputButtons.setLayout(new BoxLayout(baseSelectionPanelOutputButtons, BoxLayout.Y_AXIS));
        baseSelectionPanelOutput.add(baseSelectionPanelOutputButtons);
        ButtonGroup outputBaseSelectionButtonGroup = new ButtonGroup();

        ModeSelectionButtonObserver observer = new ModeSelectionButtonObserver(buttonPanel);
        ModeSelectionButton inputDEC = new ModeSelectionButton("DEC", true, observer, true);
        ModeSelectionButton inputHEX = new ModeSelectionButton("HEX", observer, true);
        ModeSelectionButton inputBIN = new ModeSelectionButton("BIN", observer, true);
        inputBaseSelectionButtonGroup.add(inputDEC);
        inputBaseSelectionButtonGroup.add(inputHEX);
        inputBaseSelectionButtonGroup.add(inputBIN);
        observer.addButton(inputDEC);
        observer.addButton(inputHEX);

        baseSelectionPanelInputButtons.add(inputDEC);
        baseSelectionPanelInputButtons.add(inputHEX);

        ModeSelectionButton outputDEC = new ModeSelectionButton("DEC", observer, false);
        ModeSelectionButton outputHEX = new ModeSelectionButton("HEX", true, observer, false);
        ModeSelectionButton outputBIN = new ModeSelectionButton("BIN", observer, false);
        outputBaseSelectionButtonGroup.add(outputDEC);
        outputBaseSelectionButtonGroup.add(outputHEX);
        outputBaseSelectionButtonGroup.add(outputBIN);

        baseSelectionPanelOutputButtons.add(outputDEC);
        baseSelectionPanelOutputButtons.add(outputHEX);

        baseSelectionPanel.revalidate();
        baseSelectionPanel.repaint();
    }
}
