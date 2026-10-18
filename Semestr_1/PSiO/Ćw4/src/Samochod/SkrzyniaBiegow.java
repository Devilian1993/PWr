package Samochod;

public class SkrzyniaBiegow {

    private int iloscBiegow;
    private int aktualnyBieg;
    private String tryb;

    public SkrzyniaBiegow(int iloscBiegow, int aktualnyBieg, String tryb) {
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = aktualnyBieg;
        this.tryb = tryb;
    }

    public int getIloscBiegow() {
        return iloscBiegow;
    }

    public void setIloscBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
    }

    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public void setAktualnyBieg(int aktualnyBieg) {
        this.aktualnyBieg = aktualnyBieg;
    }

    public String getTryb() {
        return tryb;
    }

    public void setTryb(String tryb) {
        this.tryb = tryb;
    }

    public void wyswietlSkrzynieBiegow() {
        System.out.println(iloscBiegow + " biegowa skrzynia biegow");
        System.out.println("Aktualny bieg: " + aktualnyBieg);
    }
}
