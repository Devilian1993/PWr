package ObliczanieSredniej;

import java.util.ArrayList;

import Modele.Student;
import Modele.Osoba;

public interface ObliczanieSredniejStrategia {
    public double obliczanieSredniej(Student student);
    public void menuObliczanieSredniej(ArrayList<Osoba> osoby);
}
