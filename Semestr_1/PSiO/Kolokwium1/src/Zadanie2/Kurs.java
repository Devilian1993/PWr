package Zadanie2;

public class Kurs {

    int ECTS;
    String nazwa;
    int liczba_godzin_w_semestrze;

    public Kurs(int ECTS, String nazwa, int liczba_godzin_w_semestrze) {
        this.ECTS = ECTS;
        this.nazwa = nazwa;
        this.liczba_godzin_w_semestrze = liczba_godzin_w_semestrze;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getLiczba_godzin_w_semestrze() {
        return liczba_godzin_w_semestrze;
    }

    public void setLiczba_godzin_w_semestrze(int liczba_godzin_w_semestrze) {
        this.liczba_godzin_w_semestrze = liczba_godzin_w_semestrze;
    }

    public void wyswietlStanKursu() {
        System.out.printf(" Nazwa: %s\n Liczba punktow ECTS: %d\n Ilosc godzin w semestrze: %d\n", nazwa, ECTS, liczba_godzin_w_semestrze);
        System.out.println();
    }
}
