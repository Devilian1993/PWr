package GUI;

import javax.swing.*;
import java.awt.*;

public class ViewSelectionButtonPanel extends JPanel{

    ViewSelectionButtonPanel(ViewSelectionButton button) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(150, 35));
        this.add(button);
    }
}
