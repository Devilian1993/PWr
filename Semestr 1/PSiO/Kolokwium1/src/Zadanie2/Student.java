package Zadanie2;

public class Student extends Osoba {

    private int nr_indeksu;
    private Kurs[] kursy;

    public Student(String imie, String nazwisko, String PESEL, int nr_indeksu, Kurs[] kursy) {
        super(imie, nazwisko, PESEL);
        this.nr_indeksu = nr_indeksu;
        this.kursy = kursy;
    }

    public int getNr_indeksu() {
        return nr_indeksu;
    }

    public void setNr_indeksu(int nr_indeksu) {
        this.nr_indeksu = nr_indeksu;
    }

    public Kurs[] getKursy() {
        return kursy;
    }

    public void setKursy(Kurs[] kursy) {
        this.kursy = kursy;
    }

    public void wyswietlStan() {
        System.out.printf("Student: %s %s\nPESEL: %s\nNr indeksu: %d\nKursy studenta:\n", getImie(), getNazwisko(), getPESEL(), getNr_indeksu());
        for (int i = 0; i < kursy.length; i++) {
            kursy[i].wyswietlStanKursu();
        }
        System.out.println();
    }
}
