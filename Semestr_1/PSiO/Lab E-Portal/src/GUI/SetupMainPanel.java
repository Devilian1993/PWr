package GUI;

import Dane.Serializacja;
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

public class SetupMainPanel {
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel optionPanel;
    private ArrayList<ViewSelectionButton> viewSelectionButtons = new ArrayList<>();
    private ArrayList<PersonButton> personButtons = new ArrayList<>();
    private SetupOptionPanel setupOptionPanel;

    SetupMainPanel(JPanel panel, ArrayList<ViewSelectionButton> viewSelectionButtons) {
        mainPanel = panel;
        this.viewSelectionButtons = viewSelectionButtons;

        listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPanel.setBackground(Color.GRAY);
        listPanel.setPreferredSize(new Dimension(300, 850));

        optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        optionPanel.setBackground(Color.WHITE);
        optionPanel.setPreferredSize(new Dimension(850, 850));

        mainPanel.add(listPanel);
        mainPanel.add(optionPanel);

        setupOptionPanel = new SetupOptionPanel(optionPanel);

        viewSelectionButtons.get(0).addActionListener(new ReactionViewButton());
        viewSelectionButtons.get(1).addActionListener(new ReactionViewButton());
        viewSelectionButtons.get(2).addActionListener(new ReactionViewButton());

        Serializacja.zapisz(ManagerUczelni.getOsoby());
    }

    public void setupMainPanelStudents() {
        listPanel.removeAll();
        optionPanel.removeAll();

        for (Osoba osoba : ManagerUczelni.getOsoby()) {
            if (osoba instanceof Student) {
                PersonButton osobaButton = new PersonButton(osoba);
                osobaButton.addActionListener(new ReactionPersonButton());
                listPanel.add(osobaButton);
            }
        }
        listPanel.add(new PersonButton("Student"){{
            addActionListener(new ReactionAddStudentButton());
        }});

        listPanel.revalidate();
        listPanel.repaint();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void setupMainPanelPracND() {
        for (Osoba osoba : ManagerUczelni.getOsoby()) {
            if (osoba instanceof PracownikNaukowoDydaktyczny) {
                PersonButton osobaButton = new PersonButton(osoba);
                osobaButton.addActionListener(new ReactionPersonButton());
                listPanel.add(osobaButton);
            }
        }
        listPanel.add(new PersonButton("PracND"){{
            addActionListener(new ReactionAddPracNDButton());
        }});
    }

    public void setupMainPanelPracU() {
        for (Osoba osoba : ManagerUczelni.getOsoby()) {
            if (osoba instanceof PracownikUczelni) {
                PersonButton osobaButton = new PersonButton(osoba);
                osobaButton.addActionListener(new ReactionPersonButton());
                listPanel.add(osobaButton);
            }
        }
        listPanel.add(new PersonButton("PracND"){{
            addActionListener(new ReactionAddPracUButton());
        }});
    }

    class ReactionViewButton implements ActionListener {
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

    class ReactionPersonButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (((PersonButton) e.getSource()).getOsoba() instanceof Student) {
                setupOptionPanel.setupPanelStudent((Student) ((PersonButton) e.getSource()).getOsoba());
            } else if (((PersonButton) e.getSource()).getOsoba() instanceof PracownikNaukowoDydaktyczny) {
                setupOptionPanel.setupPanelPracND((PracownikNaukowoDydaktyczny) ((PersonButton) e.getSource()).getOsoba());
            } else {
                setupOptionPanel.setupPanelPracU((PracownikUczelni) ((PersonButton) e.getSource()).getOsoba());
            }
        }
    }

    class ReactionAddStudentButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();

            frame.setTitle(String.format("Nowy %s", ((PersonButton)e.getSource()).personType));
            frame.setSize(300, 400);
            frame.setResizable(false);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            JButton button = new JButton("Zapisz");

            panel.setPreferredSize(new Dimension(250, 540));
            button.setPreferredSize(new Dimension(95, 35));

            class inputTextLabel extends JLabel {
                inputTextLabel(String text) {
                    super(text);
                    setPreferredSize(new Dimension(100, 30));
                }
            }

            class inputArea extends JTextArea {
                inputArea() {
                    super();
                    setBackground(Color.WHITE);
                    setPreferredSize(new Dimension(140, 30));
                }
            }

            JTextArea nameInput = new inputArea();
            JTextArea surnameInput = new inputArea();
            JTextArea peselInput = new inputArea();
            JTextArea indexInput = new inputArea();
            JTextArea studyInput = new inputArea();
            JTextArea depInput = new inputArea();
            JTextArea yearInput = new inputArea();
            JTextArea semInput = new inputArea();

            class ReactionSaveStudentButton implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ManagerUczelni.zapiszStudenta(new String[]{nameInput.getText(), surnameInput.getText(), peselInput.getText(),
                    indexInput.getText(), yearInput.getText(), semInput.getText(), studyInput.getText(), depInput.getText()});
                    frame.setVisible(false);
                    setupMainPanelStudents();
                }
            }

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Imie"));
                add(nameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Nazwisko"));
                add(surnameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("PESEL"));
                add(peselInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Nr indeksu"));
                add(indexInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Kierunek"));
                add(studyInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Wydzial"));
                add(depInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Rok"));
                add(yearInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Semestr"));
                add(semInput);
            }});

            frame.add(panel);
            frame.add(new JPanel(){{
                setPreferredSize(new Dimension(100, 40));
                setLayout(new FlowLayout(FlowLayout.CENTER));
                add(button);
            }});

            button.addActionListener(new ReactionSaveStudentButton());

            frame.setVisible(true);
        }
    }

    class ReactionAddPracNDButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();

            frame.setTitle(String.format("Nowy %s", ((PersonButton)e.getSource()).personType));
            frame.setSize(300, 400);
            frame.setResizable(false);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            JButton button = new JButton("Zapisz");

            panel.setPreferredSize(new Dimension(250, 540));
            button.setPreferredSize(new Dimension(95, 35));

            class inputTextLabel extends JLabel {
                inputTextLabel(String text) {
                    super(text);
                    setPreferredSize(new Dimension(100, 30));
                }
            }

            class inputArea extends JTextArea {
                inputArea() {
                    super();
                    setBackground(Color.WHITE);
                    setPreferredSize(new Dimension(140, 30));
                }
            }

            JTextArea nameInput = new inputArea();
            JTextArea surnameInput = new inputArea();
            JTextArea peselInput = new inputArea();
            JTextArea salaryInput = new inputArea();
            JTextArea mailInput = new inputArea();
            JTextArea jedInput = new inputArea();
            JTextArea expInput = new inputArea();
            JTextArea degInput = new inputArea();
            JTextArea accInput = new inputArea();

            class ReactionSavePracNDButton implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ManagerUczelni.zapiszPracND(new String[]{nameInput.getText(), surnameInput.getText(), peselInput.getText(),
                    salaryInput.getText(), mailInput.getText(), jedInput.getText() ,expInput.getText(), degInput.getText(), accInput.getText()});
                    frame.setVisible(false);
                    setupMainPanelPracND();
                }
            }

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Imie"));
                add(nameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Nazwisko"));
                add(surnameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("PESEL"));
                add(peselInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Pensja"));
                add(salaryInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("E-mail"));
                add(mailInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Jednostka"));
                add(jedInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Staz pracy"));
                add(expInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Stopien naukowy"));
                add(degInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Dorobek naukowy"));
                add(accInput);
            }});

            frame.add(panel);
            frame.add(new JPanel(){{
                setPreferredSize(new Dimension(100, 40));
                setLayout(new FlowLayout(FlowLayout.CENTER));
                add(button);
            }});

            button.addActionListener(new ReactionSavePracNDButton());

            frame.setVisible(true);
        }
    }

    class ReactionAddPracUButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();

            frame.setTitle(String.format("Nowy %s", ((PersonButton)e.getSource()).personType));
            frame.setSize(300, 400);
            frame.setResizable(false);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            JButton button = new JButton("Zapisz");

            panel.setPreferredSize(new Dimension(250, 540));
            button.setPreferredSize(new Dimension(95, 35));

            class inputTextLabel extends JLabel {
                inputTextLabel(String text) {
                    super(text);
                    setPreferredSize(new Dimension(100, 30));
                }
            }

            class inputArea extends JTextArea {
                inputArea() {
                    super();
                    setBackground(Color.WHITE);
                    setPreferredSize(new Dimension(140, 30));
                }
            }

            JTextArea nameInput = new inputArea();
            JTextArea surnameInput = new inputArea();
            JTextArea peselInput = new inputArea();
            JTextArea salaryInput = new inputArea();
            JTextArea mailInput = new inputArea();
            JTextArea jedInput = new inputArea();
            JTextArea expInput = new inputArea();
            JTextArea jobInput = new inputArea();

            class ReactionSavePracUButton implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ManagerUczelni.zapiszPracU(new String[]{nameInput.getText(), surnameInput.getText(), peselInput.getText(),
                            salaryInput.getText(), mailInput.getText(), jedInput.getText() ,expInput.getText(), jobInput.getText()});
                    frame.setVisible(false);
                    setupMainPanelPracU();
                }
            }

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Imie"));
                add(nameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Nazwisko"));
                add(surnameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("PESEL"));
                add(peselInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Pensja"));
                add(salaryInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("E-mail"));
                add(mailInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Jednostka"));
                add(jedInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Staz pracy"));
                add(expInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Stanowisko"));
                add(jobInput);
            }});


            frame.add(panel);
            frame.add(new JPanel(){{
                setPreferredSize(new Dimension(100, 40));
                setLayout(new FlowLayout(FlowLayout.CENTER));
                add(button);
            }});

            button.addActionListener(new ReactionSavePracUButton());

            frame.setVisible(true);
        }
    }
}
