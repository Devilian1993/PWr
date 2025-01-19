package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSelectionButton extends JButton {

    ViewSelectionButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(150, 30));
        this.setBackground(Color.GREEN);
    }
}
