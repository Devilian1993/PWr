package Wlasciciel;

public class PrawoJazdy {

    private String dataWydania;
    private String[] kategorie;
    private String organWydajacy;

    public PrawoJazdy(String dataWydania, String[] kategorie, String organWydajacy) {
        this.dataWydania = dataWydania;
        this.kategorie = kategorie;
        this.organWydajacy = organWydajacy;
    }

    public String getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(String dataWydania) {
        this.dataWydania = dataWydania;
    }

    public String[] getKategorie() {
        return kategorie;
    }

    public void setKategorie(String[] kategorie) {
        this.kategorie = kategorie;
    }

    public String getOrganWydajacy() {
        return organWydajacy;
    }

    public void setOrganWydajacy(String organWydajacy) {
        this.organWydajacy = organWydajacy;
    }

    public void wyswietlKategorie() {
        for (int i = 0; i < kategorie.length; i++) {
            System.out.print(kategorie[i] + " ");
        }
    }

    public void wyswietlPrawoJazdy() {
        System.out.println("Prawo jazdy wydane przez " + this.getOrganWydajacy() + " wydane dnia " + this.getDataWydania());
        System.out.print("Kategorie prawa jazdy: ");
        wyswietlKategorie();
        System.out.println();
    }
}
