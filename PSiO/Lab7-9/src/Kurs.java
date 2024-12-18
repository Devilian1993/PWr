import java.io.Serializable;

public class Kurs implements Serializable {

    private String nazwa;
    private String rodzaj;
    private int ECTS;
    private int liczba_godzin_w_semestrze;
    private boolean czy_grupa_kursow;

    public Kurs(String nazwa, String rodzaj, int ECTS, int liczba_godzin_w_semestrze, boolean czy_grupa_kursow) {
        this.nazwa = nazwa;
        this.rodzaj = rodzaj;
        this.ECTS = ECTS;
        this.liczba_godzin_w_semestrze = liczba_godzin_w_semestrze;
        this.czy_grupa_kursow = czy_grupa_kursow;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public int getLiczba_godzin_w_semestrze() {
        return liczba_godzin_w_semestrze;
    }

    public void setLiczba_godzin_w_semestrze(int liczba_godzin_w_semestrze) {
        this.liczba_godzin_w_semestrze = liczba_godzin_w_semestrze;
    }

    public boolean isCzy_grupa_kursow() {
        return czy_grupa_kursow;
    }

    public void setCzy_grupa_kursow(boolean czy_grupa_kursow) {
        this.czy_grupa_kursow = czy_grupa_kursow;
    }

    public void wypiszDaneKursu(int nr_kursu) {
        System.out.printf(" - Kurs nr %d", nr_kursu);
        System.out.printf("  Nazwa: %s\n", nazwa);
        System.out.printf("  Rodzaj: %s\n", rodzaj);
        System.out.printf("  ECTS: %d\n", ECTS);
        System.out.printf("  Liczba godzin w semestrze: %d\n", liczba_godzin_w_semestrze);
        System.out.printf("  Czy grupa kursow?: %b\n", czy_grupa_kursow);
        System.out.println();
    }

    public void wypiszDaneKursu() {
        System.out.println("Dane Kursu:");
        System.out.printf("Nazwa: %s\n", nazwa);
        System.out.printf("Rodzaj: %s\n", rodzaj);
        System.out.printf("ECTS: %d\n", ECTS);
        System.out.printf("Liczba godzin w semestrze: %d\n", liczba_godzin_w_semestrze);
        System.out.printf("Czy grupa kursow?: %b\n", czy_grupa_kursow);
        System.out.println();
    }
}
