package GUI;

import ManagerUczelni.ManagerUczelni;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {
    public static void main(String[] args) {

        ManagerUczelni managerUczelni = new ManagerUczelni(true);
        Notificator notificator = new Notificator(managerUczelni);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("E-Portal");
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel viewSelectionPanel = new JPanel();
        viewSelectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        viewSelectionPanel.setBackground(Color.LIGHT_GRAY);
        viewSelectionPanel.setPreferredSize(new Dimension(1200, 50));

        frame.add(viewSelectionPanel, BorderLayout.NORTH);
        ViewSelectionButton studentButton = new ViewSelectionButton("Studenci");
        ViewSelectionButton pracNDButton = new ViewSelectionButton("Pracownicy ND");
        ViewSelectionButton pracUczButton = new ViewSelectionButton("Pracownicy U");

        ArrayList<ViewSelectionButton> viewSelectionButtons = new ArrayList<ViewSelectionButton>();
        viewSelectionButtons.add(studentButton);
        viewSelectionButtons.add(pracNDButton);
        viewSelectionButtons.add(pracUczButton);

        viewSelectionPanel.add(new ViewSelectionButtonPanel(studentButton));
        viewSelectionPanel.add(new ViewSelectionButtonPanel(pracNDButton));
        viewSelectionPanel.add(new ViewSelectionButtonPanel(pracUczButton));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPanel.setBackground(Color.WHITE);
        frame.add(mainPanel, BorderLayout.CENTER);

        SetupMainPanel setupMainPanel = new SetupMainPanel(mainPanel, viewSelectionButtons);

        frame.setVisible(true);
    }
}
