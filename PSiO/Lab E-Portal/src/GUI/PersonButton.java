package GUI;

import Modele.Osoba;

import javax.swing.*;
import java.awt.*;

public class PersonButton extends JButton {
    Osoba osoba;
    public PersonButton(Osoba osoba) {
        super();

        this.osoba = osoba;
        this.setText(osoba.getImie() + " " + osoba.getNazwisko());
        this.setPreferredSize(new Dimension(150, 30));
    }
}
