package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI {
    public static final int width = 750;
    public static final int height = 900;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Kalkulator");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(238,238,238));
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600, 800));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel toggleField = new JPanel();
        toggleField.setPreferredSize(new Dimension(750, 60));
        toggleField.setBackground(new Color(163,163,163));
        toggleField.setLayout(new FlowLayout(FlowLayout.LEFT));

        toggleField.add(new JPanel() {{
            setBackground(new Color(163,163,163));
            setPreferredSize(new Dimension(2, 60));
        }
        });

        InputPanel inputPanel = new InputPanel();
        SetupButtonPanel.setInputPanel(inputPanel);

        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(500, 1));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(600, 600));
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        frame.add(toggleField, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel);
        mainPanel.add(gapPanel);
        mainPanel.add(buttonsPanel);

        JPanel modeSelectPanel = new JPanel();
        modeSelectPanel.setPreferredSize(new Dimension(400, 50));
        modeSelectPanel.setBackground(new Color(163,163,163));
        modeSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        toggleField.add(modeSelectPanel);

        JPanel baseSelectionPanel = new JPanel();
        baseSelectionPanel.setPreferredSize(new Dimension(255, 50));
        baseSelectionPanel.setBackground(new Color(163,163,163));
        baseSelectionPanel.setLayout(new BoxLayout(baseSelectionPanel, BoxLayout.X_AXIS));
        toggleField.add(baseSelectionPanel);

        ToggleButtonObserver toggleButtonObserver = new ToggleButtonObserver(buttonsPanel, baseSelectionPanel);
        ToggleButton standard = new ToggleButton("Standard", toggleButtonObserver);
        ToggleButton scientific = new ToggleButton("Scientific", toggleButtonObserver);
        ToggleButton binary = new ToggleButton("Binary", toggleButtonObserver);
        ToggleButton hexadecimal = new ToggleButton("Hexadecimal", toggleButtonObserver);

        modeSelectPanel.add(standard);
        modeSelectPanel.add(scientific);
        modeSelectPanel.add(binary);
        modeSelectPanel.add(hexadecimal);
        SetupButtonPanel.setupButtonPanelStandardMode(buttonsPanel);

        toggleButtonObserver.addButton(standard);
        toggleButtonObserver.addButton(scientific);
        toggleButtonObserver.addButton(binary);
        toggleButtonObserver.addButton(hexadecimal);

        JPanel historyButtonPanel = new JPanel();
        historyButtonPanel.setPreferredSize(new Dimension(50, 50));
        historyButtonPanel.setOpaque(false);
        historyButtonPanel.setLayout(new BoxLayout(historyButtonPanel, BoxLayout.X_AXIS));
        historyButtonPanel.setBorder(new EmptyBorder(0, 0, 10, 40));
        historyButtonPanel.add(new HistoryButton());

        toggleField.add(historyButtonPanel);

        frame.setVisible(true);
    }
}
