package Samochod;

public class Silnik {

    private int moc;
    private int pojemnosc;
    private boolean stan;

    public Silnik(int moc, int pojemnosc, boolean stan) {
        this.moc = moc;
        this.pojemnosc = pojemnosc;
        this.stan = stan;
    }

    public int getMoc() {
        return moc;
    }

    public void setMoc(int moc) {
        this.moc = moc;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public boolean isStan() {
        return stan;
    }

    public void setStan(boolean stan) {
        this.stan = stan;
    }

    public void wyswietlSilnik() {
        System.out.println("Moc: " + moc + " KM");
        System.out.println("Pojemnosc: " + pojemnosc + " cm^3");
        if (stan) {
            System.out.println("Silnik pracuje");
        } else {
            System.out.println("Silnik jest wyłączony");
        }
    }
}
