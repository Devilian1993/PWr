public class PracownikNaukowoDydaktyczny extends Pracownik {

    private String stopien_naukowy;
    private Kurs[] prowadzone_kursy;
    private int dorobek;

    public PracownikNaukowoDydaktyczny(String imie, String nazwisko, String PESEL, int pensja, String e_mail, String jednostka, int staz_pracy, String stopien_naukowy, Kurs[] prowadzone_kursy, int dorobek) {
        super(imie, nazwisko, PESEL, pensja, e_mail, jednostka, staz_pracy);
        this.stopien_naukowy = stopien_naukowy;
        this.prowadzone_kursy = prowadzone_kursy;
        this.dorobek = dorobek;
    }

    public PracownikNaukowoDydaktyczny(String imie, String nazwisko, String PESEL, int pensja, String e_mail, String jednostka, int staz_pracy, String stopien_naukowy, int dorobek) {
        super(imie, nazwisko, PESEL, pensja, e_mail, jednostka, staz_pracy);
        this.stopien_naukowy = stopien_naukowy;
        this.prowadzone_kursy = new Kurs[5];
        this.dorobek = dorobek;
    }

    public String getStopien_naukowy() {
        return stopien_naukowy;
    }

    public void setStopien_naukowy(String stopien_naukowy) {
        this.stopien_naukowy = stopien_naukowy;
    }

    public Kurs[] getProwadzone_kursy() {
        return prowadzone_kursy;
    }

    public void setProwadzone_kursy(Kurs[] prowadzone_kursy) {
        this.prowadzone_kursy = prowadzone_kursy;
    }

    public int getDorobek() {
        return dorobek;
    }

    public void setDorobek(int dorobek) {
        this.dorobek = dorobek;
    }

    public void wypiszDane() {
        System.out.println("Dane pracownika naukowo-dydaktycznego:");
        System.out.printf("Stopien: %s\n", stopien_naukowy);
        System.out.printf("Imie: %s\n", getImie());
        System.out.printf("Nazwisko: %s\n", getNazwisko());
        System.out.printf("PESEL: %s\n", getPESEL());
        System.out.printf("Pensja: %d\n", getPensja());
        System.out.printf("E-mail: %s\n", getE_mail());
        System.out.printf("Jednostka: %s\n", getJednostka());
        System.out.printf("Staz pracy: %d\n", getStaz_pracy());
        System.out.println();
    }
}
