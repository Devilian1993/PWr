package GUI;

import Calculator.Calculator;

import javax.swing.*;
import java.awt.*;
import Calculator.Utils;

public class GraphFrame extends JFrame {

    private String function;
    private Calculator calculator;

    public GraphFrame(String function, Calculator calculator) {
        super(function);
        this.function = function;
        this.setSize(800, 600);
        this.getContentPane().setBackground(new Color(238,238,238));
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());

        class GraphPanel extends JPanel {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int margin = 30;

                g2d.drawLine(margin, height / 2, width - margin, height / 2);
                g2d.drawLine(width / 2, margin, width / 2, height - margin);

                g2d.setColor(Color.RED);

                for (double x = -20; x < 20; x += 0.01) {
                    int x1 = (int) (width / 2 + x * 30);
                    int y1 = (int) (height / 2 - calculateFunction(x) * 30);
                    if (y1 != 999999999) {
                        g2d.fillOval(x1, y1, 2, 2);
                    }
                }
            }

            public double calculateFunction(double x) {
                return !calculator.calculate(function.replace("x", Double.toString(x))).equals("ERROR") ? Utils.stringToDouble(calculator.calculate(function.replace("x", Double.toString(x)))) : 999999999;
            }
        }
        GraphPanel graphPanel = new GraphPanel();
        this.add(graphPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
