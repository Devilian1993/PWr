package Samochod;
import Wlasciciel.Wlasciciel;

public class Samochod {
    private String Marka;
    private String Model;
    private String rok_produkcji;
    private int przebieg;
    private String VIN;
    private String nr_rejestracyjny;
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia_biegow;
    private Wlasciciel wlasciciel;

    public Samochod(String marka, String model, String rok_produkcji, int przebieg, String VIN, String nr_rejestracyjny, Silnik silnik, SkrzyniaBiegow skrzynia_biegow, Wlasciciel wlasciciel) {
        Marka = marka;
        Model = model;
        this.rok_produkcji = rok_produkcji;
        this.przebieg = przebieg;
        this.VIN = VIN;
        this.nr_rejestracyjny = nr_rejestracyjny;
        this.silnik = silnik;
        this.skrzynia_biegow = skrzynia_biegow;
        this.wlasciciel = wlasciciel;
    }

    public String getMarka() {
        return Marka;
    }

    public void setMarka(String marka) {
        Marka = marka;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getRok_produkcji() {
        return rok_produkcji;
    }

    public void setRok_produkcji(String rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getNr_rejestracyjny() {
        return nr_rejestracyjny;
    }

    public void setNr_rejestracyjny(String nr_rejestracyjny) {
        this.nr_rejestracyjny = nr_rejestracyjny;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public void setSilnik(Silnik silnik) {
        this.silnik = silnik;
    }

    public SkrzyniaBiegow getSkrzynia_biegow() {
        return skrzynia_biegow;
    }

    public void setSkrzynia_biegow(SkrzyniaBiegow skrzynia_biegow) {
        this.skrzynia_biegow = skrzynia_biegow;
    }

    public void wyswietlSamochod() {
        System.out.println("DANE SAMOCHODU");
        System.out.println("Marka: " + Marka);
        System.out.println("Model: " + Model);
        System.out.println("Rok produkcji: " + rok_produkcji);
        System.out.println("Przebieg: " + przebieg + " km");
        System.out.println("VIN: " + VIN);
        System.out.println("Numer rejestracyjny: " + nr_rejestracyjny);
        System.out.println("Silnik: ");
        if (silnik != null) {
            silnik.wyswietlSilnik();
        } else {
            System.out.print("Brak silnika");
        }
        System.out.println("Skrzynia: ");
        if (skrzynia_biegow != null) {
            skrzynia_biegow.wyswietlSkrzynieBiegow();
        } else {
            System.out.print("Brak skrzyni biegow");
        }
        System.out.println("Wlasciciel: ");
        if (wlasciciel != null) {
            wlasciciel.wyswietlWlasciciela();
        } else {
            System.out.print("Wlasciciel nieznany");
        }
    }
}
