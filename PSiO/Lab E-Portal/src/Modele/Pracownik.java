package Modele;

public abstract class Pracownik extends Osoba {

    private int pensja;
    private String e_mail;
    private String jednostka;
    private int staz_pracy;

    public Pracownik(String imie, String nazwisko, String PESEL, int pensja, String e_mail, String jednostka, int staz_pracy) {
        super(imie, nazwisko, PESEL);
        this.pensja = pensja;
        this.e_mail = e_mail;
        this.jednostka = jednostka;
        this.staz_pracy = staz_pracy;
    }

    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public int getStaz_pracy() {
        return staz_pracy;
    }

    public void setStaz_pracy(int staz_pracy) {
        this.staz_pracy = staz_pracy;
    }
}
