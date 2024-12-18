public class PracownikUczelni extends Pracownik {

    private String stanowisko;

    public PracownikUczelni(String imie, String nazwisko, String PESEL, int pensja, String e_mail, String jednostka, int staz_pracy, String stanowisko) {
        super(imie, nazwisko, PESEL, pensja, e_mail, jednostka, staz_pracy);
        this.stanowisko = stanowisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void wypiszDane() {
        System.out.println("Dane pracownika uczelni: ");
        System.out.printf("Imie: %s\n", this.getImie());
        System.out.printf("Nazwisko: %s\n", this.getNazwisko());
        System.out.printf("PESEL: %s\n", this.getPESEL());
        System.out.printf("Pensja: %s\n", this.getPensja());
        System.out.printf("E-mail: %s\n", this.getE_mail());
        System.out.printf("Jednostka: %s\n", this.getJednostka());
        System.out.printf("Staz pracy: %s\n", this.getStaz_pracy());
        System.out.printf("Stanowisko: %s\n", stanowisko);
        System.out.println();
    }
}
