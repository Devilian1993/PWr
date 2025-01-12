package GUI;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    CalcButtonObserver observer;
    String text;

    public InputPanel() {
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(new Color(163,163,163));
        this.text = "Babu frik";
        JLabel textLabel = new JLabel(text);
        textLabel.setPreferredSize(new Dimension(600, 100));
        textLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 64));
        this.add(textLabel);
    }

    public void registerObserver(CalcButtonObserver observer) {
        this.observer = observer;
    }


}
