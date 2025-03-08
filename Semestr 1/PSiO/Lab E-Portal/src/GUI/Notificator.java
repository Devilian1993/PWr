package GUI;

import ManagerUczelni.*;
import Modele.Student;

import javax.swing.*;
import java.awt.*;

public class Notificator implements Observer {
    public Notificator(ManagerUczelni manager) {
        manager.registerObserver(this);
    }

    @Override
    public void update(Student student) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(240, 180));
        frame.setLocationRelativeTo(null);
        frame.setTitle("Można awansować studenta");
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        frame.add(new JLabel(String.format("<html>Student %s %s ma wystawione wszystkie oceny. Można go awansować na kolejny semestr</html>",
                student.getImie(), student.getNazwisko())), BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
