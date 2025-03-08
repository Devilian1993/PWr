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

        BaseSelectionButtonObserver observer = new BaseSelectionButtonObserver(buttonPanel);
        BaseSelectionButton inputDEC = new BaseSelectionButton("DEC", true, observer, true);
        BaseSelectionButton inputHEX = new BaseSelectionButton("HEX", observer, true);
        BaseSelectionButton inputBIN = new BaseSelectionButton("BIN", observer, true);
        inputBaseSelectionButtonGroup.add(inputDEC);
        inputBaseSelectionButtonGroup.add(inputHEX);
        inputBaseSelectionButtonGroup.add(inputBIN);
        observer.addButton(inputDEC);
        observer.addButton(inputBIN);

        baseSelectionPanelInputButtons.add(inputDEC);
        baseSelectionPanelInputButtons.add(inputBIN);

        BaseSelectionButton outputDEC = new BaseSelectionButton("DEC", observer, false);
        BaseSelectionButton outputHEX = new BaseSelectionButton("HEX", observer, false);
        BaseSelectionButton outputBIN = new BaseSelectionButton("BIN", true, observer, false);
        outputBaseSelectionButtonGroup.add(outputDEC);
        outputBaseSelectionButtonGroup.add(outputHEX);
        outputBaseSelectionButtonGroup.add(outputBIN);
        //observer.addButton(outputDEC);
        //observer.addButton(outputBIN);

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

        BaseSelectionButtonObserver observer = new BaseSelectionButtonObserver(buttonPanel);
        BaseSelectionButton inputDEC = new BaseSelectionButton("DEC", true, observer, true);
        BaseSelectionButton inputHEX = new BaseSelectionButton("HEX", observer, true);
        BaseSelectionButton inputBIN = new BaseSelectionButton("BIN", observer, true);
        inputBaseSelectionButtonGroup.add(inputDEC);
        inputBaseSelectionButtonGroup.add(inputHEX);
        inputBaseSelectionButtonGroup.add(inputBIN);
        observer.addButton(inputDEC);
        observer.addButton(inputHEX);

        baseSelectionPanelInputButtons.add(inputDEC);
        baseSelectionPanelInputButtons.add(inputHEX);

        BaseSelectionButton outputDEC = new BaseSelectionButton("DEC", observer, false);
        BaseSelectionButton outputHEX = new BaseSelectionButton("HEX", true, observer, false);
        BaseSelectionButton outputBIN = new BaseSelectionButton("BIN", observer, false);
        outputBaseSelectionButtonGroup.add(outputDEC);
        outputBaseSelectionButtonGroup.add(outputHEX);
        outputBaseSelectionButtonGroup.add(outputBIN);
        //observer.addButton(outputDEC);
        //observer.addButton(outputHEX);

        baseSelectionPanelOutputButtons.add(outputDEC);
        baseSelectionPanelOutputButtons.add(outputHEX);

        baseSelectionPanel.revalidate();
        baseSelectionPanel.repaint();
    }
}
