package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class CalcButton extends JButton implements ActionListener {

    private final int width = 30;
    private final int height = 30;
    private final Color defaultColor = Color.LIGHT_GRAY;
    private final Color clickedColor = new Color(221,221,221);
    private final Color hoveredColor = new Color(163,163,163);
    private boolean isClicked = false;
    private CalcButtonObserver observer;
    private boolean isNumber;

    public CalcButton(String text, CalcButtonObserver observer, boolean isNumber) {
        super();
        this.observer = observer;
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setText(text);
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        //this.setBorderPainted(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBorder(new LineBorder(Color.BLACK));
        this.actionListener = this;
        this.isNumber = isNumber;

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setBackground(clickedColor);
                isClicked = true;
            }

            public void mouseReleased(MouseEvent e) {
                if (isClicked) {
                    setBackground(hoveredColor);
                    isClicked = false;
                } else {
                    setBackground(defaultColor);
                }
            }

            public void mouseEntered(MouseEvent e) {
                if (!isClicked) {
                    setBackground(hoveredColor);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (!isClicked) {
                    setBackground(defaultColor);
                };
            }
        });
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void actionPerformed(ActionEvent e) {
        observer.notifyButtonsPressed(this);
    }
}
