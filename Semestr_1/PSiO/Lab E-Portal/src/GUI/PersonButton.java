package GUI;

import Modele.Osoba;

import javax.swing.*;
import java.awt.*;

public class PersonButton extends JButton {
    Osoba osoba;
    public String personType;

    public PersonButton(Osoba osoba) {
        super();

        this.osoba = osoba;
        this.setText(osoba.getImie() + " " + osoba.getNazwisko());
        this.setPreferredSize(new Dimension(250, 30));
    }

    public PersonButton(String personToAdd) {
        personType = personToAdd;

        if (personToAdd.equals("Student")) {
            this.setText("+ Dodaj nowego studenta");
        } else if (personToAdd.equals("PracND")) {
            this.setText("+ Dodaj nowego pracownika N-D");
        } else if (personToAdd.equals("PracU")){
            this.setText("+ Dodaj nowego pracownika U");
        }

        this.setPreferredSize(new Dimension(250, 30));
    }

    public Osoba getOsoba() {
        return osoba;
    }
}
