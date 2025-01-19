package GUI;

import ManagerUczelni.ManagerUczelni;
import Modele.Osoba;
import Modele.PracownikNaukowoDydaktyczny;
import Modele.PracownikUczelni;
import Modele.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetupMainPanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel optionPanel;
    private ArrayList<ViewSelectionButton> viewSelectionButtons = new ArrayList<>();

    SetupMainPanel(JPanel panel, ArrayList<ViewSelectionButton> viewSelectionButtons) {
        mainPanel = panel;
        this.viewSelectionButtons = viewSelectionButtons;

        listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPanel.setBackground(Color.GRAY);
        listPanel.setPreferredSize(new Dimension(200, 850));

        optionPanel = new JPanel();
        optionPanel.setBackground(Color.WHITE);
        optionPanel.setPreferredSize(new Dimension(950, 850));

        mainPanel.add(listPanel);
        mainPanel.add(optionPanel);

        viewSelectionButtons.get(0).addActionListener(this);
        viewSelectionButtons.get(1).addActionListener(this);
        viewSelectionButtons.get(2).addActionListener(this);
    }

    public void setupMainPanelStudents() {
        for (Osoba osoba : ManagerUczelni.getOsoby()) {
            if (osoba instanceof Student) {
                listPanel.add(new PersonButton(osoba));
            }
        }
    }

    public void setupMainPanelPracND() {
        for (Osoba osoba : ManagerUczelni.getOsoby()) {
            if (osoba instanceof PracownikNaukowoDydaktyczny) {
                listPanel.add(new PersonButton(osoba));
            }
        }
    }

    public void setupMainPanelPracU() {
        for (Osoba osoba : ManagerUczelni.getOsoby()) {
            if (osoba instanceof PracownikUczelni) {
                listPanel.add(new PersonButton(osoba));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listPanel.removeAll();
        optionPanel.removeAll();

        if (e.getSource() == viewSelectionButtons.get(0)) {
            setupMainPanelStudents();
        } else if (e.getSource() == viewSelectionButtons.get(1)) {
            setupMainPanelPracND();
        } else {
            setupMainPanelPracU();
        }

        listPanel.revalidate();
        listPanel.repaint();

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
