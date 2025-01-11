package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeSelectionButton extends JRadioButton implements ActionListener {
    private ModeSelectionButtonObserver observer;
    private boolean isInput;
    public ModeSelectionButton(String mode, ModeSelectionButtonObserver observer, boolean isInput) {
        super(mode);
        this.observer = observer;
        this.isInput = isInput;
        this.setPreferredSize(new Dimension(20, 10));
        this.setBackground(new Color(163,163,163));
        this.setForeground(Color.BLACK);
        this.addActionListener(this);
    }

    public boolean IsInput() {
        return isInput;
    }

    public ModeSelectionButton(String mode, boolean isPressedByDefault, ModeSelectionButtonObserver observer, boolean isInput) {
        super(mode, isPressedByDefault);
        this.observer = observer;
        this.isInput = isInput;
        this.setPreferredSize(new Dimension(20, 10));
        this.setBackground(new Color(163,163,163));
        this.setForeground(Color.BLACK);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        observer.notifyButtonsButtonPressed(this);
    }
}
