package Modele;

import java.util.ArrayList;

public class Student extends Osoba {

    private int nr_indeksu;
    private int rok;
    private int semestr;
    private String kierunek;
    private String wydzial;
    private ArrayList<Kurs> kursy;

    public Student(String imie, String nazwisko, String PESEL, int nr_indeksu, String kierunek, String wydzial) {
        super(imie, nazwisko, PESEL);
        this.nr_indeksu = nr_indeksu;
        this.rok = 1;
        this.semestr = 1;
        this.kierunek = kierunek;
        this.wydzial = wydzial;
        this.kursy = new ArrayList<Kurs>();
    }

    public Student(String imie, String nazwisko, String PESEL, int nr_indeksu, int rok, int semestr, String kierunek, String wydzial) {
        super(imie, nazwisko, PESEL);
        this.nr_indeksu = nr_indeksu;
        this.rok = rok;
        this.semestr = semestr;
        this.kierunek = kierunek;
        this.wydzial = wydzial;
        this.kursy = new ArrayList<Kurs>();
    }

    public Student(String imie, String nazwisko, String PESEL, int nr_indeksu, int rok, int semestr, String kierunek, String wydzial, ArrayList<Kurs> kursy) {
        super(imie, nazwisko, PESEL);
        this.nr_indeksu = nr_indeksu;
        this.rok = rok;
        this.semestr = semestr;
        this.kierunek = kierunek;
        this.wydzial = wydzial;
        this.kursy = kursy;
    }

    public int getNr_indeksu() {
        return nr_indeksu;
    }

    public void setNr_indeksu(int nr_indeksu) {
        this.nr_indeksu = nr_indeksu;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public ArrayList<Kurs> getKursy() {
        return kursy;
    }

    public void setKursy(ArrayList<Kurs> kursy) {
        this.kursy = kursy;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    public String getWydzial() {
        return wydzial;
    }

    public void setWydzial(String wydzial) {
        this.wydzial = wydzial;
    }

    public void wypiszKursy() {
        System.out.println("Kursy studenta: ");
        for (Kurs kurs : kursy) {
            kurs.wypiszDaneKursu();
        }
    }

    public String getKursyString() {
        String kursyString = "";
        for (Kurs kurs : kursy) {
            kursyString += kurs.toString();
        }
        return kursyString;
    }

    public void wypiszDane() {
        System.out.println("Dane studenta:");
        System.out.printf("Imie: %s\n", getImie());
        System.out.printf("Nazwisko: %s\n", getNazwisko());
        System.out.printf("PESEL: %s\n", getPESEL());
        System.out.printf("Nr indeksu: %d\n", getNr_indeksu());
        System.out.printf("Kierunek: %s\n", getKierunek());
        System.out.printf("Wydzial: %s\n", getWydzial());
        System.out.printf("Rok: %d\n", getRok());
        System.out.printf("Semestr: %d\n", getSemestr());
        if (!getKursy().isEmpty()) {
            wypiszKursy();
        } else {
            System.out.println("Student nie jest zapisany na zadne kursy");
        }
        System.out.println();
    }

    public String toString() {
        return String.format("<html>STUDENT %s %s\n<br>" +
                "PESEL: %s\n<br>" +
                "Nr indeksu: %d\n<br>" +
                "Kierunek: %s\n<br>" +
                "Wydzial: %s\n<br>" +
                "Rok: %d\n<br>" +
                "Semestr: %d\n<br>", getImie(), getNazwisko(), getPESEL(), nr_indeksu, kierunek, wydzial, rok, semestr) + "</html>";
    }
}
