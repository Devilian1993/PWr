package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Calculator.Calculator;
import Calculator.Calculator.*;

public class HistoryButton extends JButton implements ActionListener {
    private boolean isHistoryFrameOpen = false;

    public HistoryButton() {
        super("H");
        this.setPreferredSize(new Dimension(60, 50));
        this.setBackground(new Color(120,120,120));
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Arial", Font.BOLD, 36));
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (isHistoryFrameOpen) {
            return;
        }

        class HistoryFrame extends JFrame {

            final JPanel basePanel = new JPanel();
            final JPanel equationPanel = new JPanel();
            final ArrayList<String[]> equations = Calculator.getCalculationsHistory();

            public HistoryFrame() {
                this.setTitle("Historia działań");
                this.setSize(400, 600);
                //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.getContentPane().setBackground(new Color(238,238,238));
                this.setResizable(false);
                ImageIcon icon = new ImageIcon("icon.png");
                this.setIconImage(icon.getImage());
                this.setLayout(new FlowLayout(FlowLayout.LEFT));

                basePanel.setBackground(new Color(238,238,238));
                equationPanel.setBackground(new Color(238,238,238));

                basePanel.setPreferredSize(new Dimension(100, 600));
                equationPanel.setPreferredSize(new Dimension(250, 600));
                basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
                equationPanel.setLayout(new BoxLayout(equationPanel, BoxLayout.Y_AXIS));

                for (String[] equation : equations) {
                    if (equation[0].equals("DEC") && equation[1].equals("DEC")) {
                        JLabel baseLabel = new JLabel("DEC");
                        baseLabel.setFont(new Font("Arial", Font.BOLD, 12));
                        baseLabel.setForeground(Color.BLACK);
                        baseLabel.setOpaque(false);
                        basePanel.add(baseLabel);
                    } else {
                        JLabel baseLabel = new JLabel(String.format("%s -> %s", equation[0], equation[1]));
                        baseLabel.setFont(new Font("Arial", Font.BOLD, 12));
                        baseLabel.setForeground(Color.BLACK);
                        baseLabel.setOpaque(false);
                        basePanel.add(baseLabel);
                    }
                    JLabel equationLabel = new JLabel(equation[2]);
                    equationLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    equationLabel.setForeground(Color.BLACK);
                    equationLabel.setOpaque(false);
                    equationPanel.add(equationLabel);
                }

                this.add(basePanel);
                this.add(equationPanel);

                this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        isHistoryFrameOpen = false;
                    }
                });
            }
        }

        isHistoryFrameOpen = true;
        HistoryFrame frame = new HistoryFrame();
        frame.setVisible(true);
    }
}
