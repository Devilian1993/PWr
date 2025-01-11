package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToggleButton extends JButton implements ActionListener {
    private ToggleButtonObserver observer;
    public ToggleButton(String text, ToggleButtonObserver observer) {
        super(text);
        this.setForeground(Color.BLACK);
        this.observer = observer;
        this.setBackground(new Color(120,120,120));
        this.addActionListener(this);
        if (text.equals("Standard")) {
            this.setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        observer.notifyButtonsButtonPressed(this);
    }
}
