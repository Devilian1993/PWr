package Wlasciciel;

import Adres.Adres;

public class Wlasciciel {

    private String imie;
    private String nazwisko;
    private String PESEL;
    private Adres adres;
    private PrawoJazdy prawoJazdy;

    public Wlasciciel(String imie, String nazwisko, String PESEL, Adres adres, PrawoJazdy prawoJazdy) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
        this.adres = adres;
        this.prawoJazdy = prawoJazdy;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public PrawoJazdy getPrawoJazdy() {
        return prawoJazdy;
    }

    public void setPrawoJazdy(PrawoJazdy prawoJazdy) {
        this.prawoJazdy = prawoJazdy;
    }

    public void wyswietlWlasciciela() {
        System.out.println("Imie: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("PESEL: " + PESEL);
        System.out.println("Adres: ");
        adres.wyswietlAdres();
        System.out.println("Prawo jazdy: ");
        prawoJazdy.wyswietlPrawoJazdy();
    }
}
