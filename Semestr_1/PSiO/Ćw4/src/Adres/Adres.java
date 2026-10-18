package Adres;

public class Adres {
    private String ulica;
    private int nrDomu;
    private String miasto;
    private String kodPocztowy;

    public Adres(String ulica, int nrDomu, String miasto, String kodPocztowy) {
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(int nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public void wyswietlAdres() {
        System.out.print(this.getUlica() + " " + this.getNrDomu() + ", " + this.getKodPocztowy() + " " +this.getMiasto());
        System.out.println();
    }
}
