package Zadanie2;

public class Prowadzacy extends Osoba {

    private String tytul_naukowy;
    private String[] zainteresowania_naukowe;

    public Prowadzacy(String imie, String nazwisko, String PESEL, String tytul_naukowy, String[] zainteresowania_naukowe) {
        super(imie, nazwisko, PESEL);
        this.tytul_naukowy = tytul_naukowy;
        this.zainteresowania_naukowe = zainteresowania_naukowe;
    }

    public String getTytul_naukowy() {
        return tytul_naukowy;
    }

    public void setTytul_naukowy(String tytul_naukowy) {
        this.tytul_naukowy = tytul_naukowy;
    }

    public String[] getZainteresowania_naukowe() {
        return zainteresowania_naukowe;
    }

    public void setZainteresowania_naukowe(String[] zainteresowania_naukowe) {
        this.zainteresowania_naukowe = zainteresowania_naukowe;
    }

    public void wyswietlZainteresowaniaNaukowe() {
        for (int i = 0; i < zainteresowania_naukowe.length; i++) {
            System.out.printf(" - %s\n", zainteresowania_naukowe[i]);
        }
    }

    public void wyswietlStan() {
        System.out.printf("Prowadzacy: %s %s %s\nPESEL: %s\nZainteresowania naukowe: \n", tytul_naukowy, getImie(), getNazwisko(), getPESEL());
        wyswietlZainteresowaniaNaukowe();
        System.out.println();
    }
}
