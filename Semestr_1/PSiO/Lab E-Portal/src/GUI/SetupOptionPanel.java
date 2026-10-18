package GUI;

import ManagerUczelni.ManagerUczelni;
import Modele.Kurs;
import Modele.PracownikNaukowoDydaktyczny;
import Modele.PracownikUczelni;
import Modele.Student;
import ObliczanieSredniej.ObliczanieSredniejArytmetycznej;
import ObliczanieSredniej.ObliczanieSredniejWazonej;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetupOptionPanel {
    private final JPanel panel;
    private final int optionButtonWidth = 180;
    private final int optionButtonHeight = 90;

    SetupOptionPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setupPanelPracND(PracownikNaukowoDydaktyczny prac) {
        panel.removeAll();

        JPanel studentDataPanel = new JPanel();

        studentDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        studentDataPanel.setPreferredSize(new Dimension(panel.getWidth()/2 - 100, panel.getHeight()/2));
        studentDataPanel.add(new JLabel(prac.toString()));

        panel.add(studentDataPanel);

        panel.revalidate();
        panel.repaint();
    }

    public void setupPanelPracU(PracownikUczelni prac) {
        panel.removeAll();

        JPanel studentDataPanel = new JPanel();

        studentDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        studentDataPanel.setPreferredSize(new Dimension(panel.getWidth()/2 - 100, panel.getHeight()/2));
        studentDataPanel.add(new JLabel(prac.toString()));

        panel.add(studentDataPanel);

        panel.revalidate();
        panel.repaint();
    }

    public void setupPanelStudent(Student student) {
        panel.removeAll();

        JPanel studentDataPanel = new JPanel();
        JPanel studentKursyPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        studentDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        studentDataPanel.setPreferredSize(new Dimension(panel.getWidth()/2 - 200, panel.getHeight()/2));
        studentDataPanel.add(new JLabel(student.toString()));

        studentKursyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        studentKursyPanel.setPreferredSize(new Dimension(panel.getWidth()/2 + 150, panel.getHeight()/2));

        for (Kurs kurs : student.getKursy()) {
            studentKursyPanel.add(new JLabel(kurs.toString()){{
                setPreferredSize(new Dimension(120, 200));
            }});
        }

        buttonsPanel.add(new JButton("Dodaj kurs"){{
            setPreferredSize(new Dimension(optionButtonWidth, optionButtonHeight));
            addActionListener(new ReactionAddKurs(student));
        }});

        buttonsPanel.add(new JButton("Dodaj ocene"){{
            setPreferredSize(new Dimension(optionButtonWidth, optionButtonHeight));
            addActionListener(new ReactionAddGrade(student));

            if (student.getKursy().isEmpty()) {
                setEnabled(false);
            }
        }});

        buttonsPanel.add(new JButton("Policz średnią"){{
            setPreferredSize(new Dimension(optionButtonWidth, optionButtonHeight));
            addActionListener(new ReactionMean(student));
            setEnabled(false);

            for (Kurs kurs : student.getKursy()) {
                if (kurs.getOcena() != 0) {
                    setEnabled(true);
                }
            }

        }});

        buttonsPanel.add(new JButton("Awansuj"){{
            setPreferredSize(new Dimension(optionButtonWidth, optionButtonHeight));
            addActionListener(new ReactionPromote(student));
            if (student.getKursy().isEmpty()) {
                setEnabled(false);
            }
            for (Kurs kurs : student.getKursy()) {
                if (kurs.getOcena() == 0) {
                    setEnabled(false);
                }
            }
        }});

        panel.add(studentDataPanel);
        panel.add(studentKursyPanel);
        panel.add(buttonsPanel);

        panel.revalidate();
        panel.repaint();
    }

    public class ReactionAddKurs implements ActionListener {
        Student student;

        ReactionAddKurs(Student student) {
            this.student = student;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();

            frame.setTitle("Dodaj kurs");
            frame.setSize(350, 300);
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
            JTextArea typeInput = new inputArea();
            JTextArea ectsInput = new inputArea();
            JTextArea hourInput = new inputArea();

            ButtonGroup buttonGroup = new ButtonGroup();
            JRadioButton isGroupYes = new JRadioButton("Tak");
            JRadioButton isGroupNo = new JRadioButton("Nie");

            buttonGroup.add(isGroupYes);
            buttonGroup.add(isGroupNo);

            class ReactionSaveKursButton implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ManagerUczelni.dodajKursStudenta(new String[]{nameInput.getText(), typeInput.getText(), ectsInput.getText(), hourInput.getText()},
                            isGroupYes.isSelected(), student);
                    frame.setVisible(false);
                    setupPanelStudent(student);
                }
            }

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Nazwa"));
                add(nameInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Rodzaj"));
                add(typeInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("ECTS"));
                add(ectsInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Liczba godzin"));
                add(hourInput);
            }});

            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(250, 30));
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(new inputTextLabel("Czy grupa kursów"));
                add(isGroupYes);
                add(isGroupNo);
            }});

            frame.add(panel);

            frame.add(new JPanel(){{
                setPreferredSize(new Dimension(100, 40));
                setLayout(new FlowLayout(FlowLayout.CENTER));
                add(button);
            }});

            button.addActionListener(new ReactionSaveKursButton());

            frame.setVisible(true);
        }
    }

    public class ReactionAddGrade implements ActionListener {
        Student student;

        ReactionAddGrade(Student student) {
            this.student = student;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();

            frame.setTitle("Dodaj oceny");
            frame.setSize(400, 300);
            frame.setResizable(false);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            JButton button = new JButton("Zapisz");

            panel.setPreferredSize(new Dimension(390, 250));
            button.setPreferredSize(new Dimension(95, 35));

            class inputTextLabel extends JLabel {
                inputTextLabel(String text) {
                    super(text);
                    setPreferredSize(new Dimension(200, 30));
                }
            }

            class inputArea extends JTextArea {
                inputArea() {
                    super();
                    setBackground(Color.WHITE);
                    setPreferredSize(new Dimension(100, 30));
                }
            }

            ArrayList<inputArea> grades = new ArrayList<>();

            class ReactionSaveGradesButton implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String> gradesString = new ArrayList<>();
                    for (int i = 0; i < grades.size(); i++) {
                        if (grades.get(i).getText().isEmpty()) {
                            gradesString.add("0");
                        } else {
                            gradesString.add(grades.get(i).getText());
                        }
                    }
                    ManagerUczelni.wstawOceny(student, gradesString);
                    frame.setVisible(false);
                    setupPanelStudent(student);
                }
            }

            for (Kurs kurs : student.getKursy()) {
                panel.add(new JPanel() {{
                    setPreferredSize(new Dimension(380, 30));
                    setLayout(new FlowLayout(FlowLayout.LEFT));
                    add(new inputTextLabel(kurs.getNazwa()));
                    inputArea gradeInput = new inputArea();
                    add(gradeInput);
                    grades.add(gradeInput);
                }});
            }

            frame.add(panel);

            frame.add(new JPanel() {{
                setPreferredSize(new Dimension(100, 40));
                setLayout(new FlowLayout(FlowLayout.CENTER));
                add(button);
            }});

            button.addActionListener(new ReactionSaveGradesButton());

            frame.setVisible(true);
        }
    }

    public class ReactionPromote implements ActionListener {
        Student student;

        ReactionPromote(Student student) {
            this.student = student;
        }

        public void actionPerformed(ActionEvent e) {
            ManagerUczelni.awansujStudenta(student);
            setupPanelStudent(student);
        }
    }

    public class ReactionMean implements ActionListener {
        Student student;

        ReactionMean(Student student) {
            this.student = student;
        }

        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();
            frame.setTitle("Średnia ocen studenta");
            frame.setSize(200, 150);
            frame.setResizable(false);
            frame.setLayout(new BorderLayout());

            JLabel text = new JLabel();
            text.setText(String.format("<html>Student %s %s<br>" +
                    "Średnia arytmetyczna ocen: %4f<br>" +
                    "Średnia ważona ocen: %4f<br></html>", student.getImie(), student.getNazwisko(), ObliczanieSredniejArytmetycznej.obliczanieSredniejStatic(student), ObliczanieSredniejWazonej.obliczanieSredniejStatic(student)));

            frame.add(text, BorderLayout.CENTER);
            frame.setVisible(true);
        }
    }

}


