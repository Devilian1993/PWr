import Samochod.Samochod;
import Samochod.Silnik;
import Samochod.SkrzyniaBiegow;
import Wlasciciel.Wlasciciel;
import Wlasciciel.PrawoJazdy;
import Adres.Adres;

public class Main {
    public static void main(String[] args) {
        Samochod[] samochody = new Samochod[5];

        String[] kategorie_1 = new String[1];
        kategorie_1[0] = "B";
        String[] kategorie_2 = new String[2];
        kategorie_2[0] = "A";
        kategorie_2[1] = "B";
        String[] kategorie_3 = new String[3];
        kategorie_3[0] = "B";
        kategorie_3[1] = "C";
        kategorie_3[2] = "C+E";
        String[] kategorie_4 = new String[1];
        kategorie_4[0] = "B";
        String[] kategorie_5 = new String[1];
        kategorie_5[0] = "B";

        samochody[0] = new Samochod(
                "Volkswagen", "Passat", "01-01-2005", 300000, "VWV10269123", "DW12345",
                new Silnik(150, 1800, false),
                new SkrzyniaBiegow(6, 1, "neutralny"),
                new Wlasciciel("Jan", "Kowalski", "950101981623",
                        new Adres("Kolorowa", 8, "51-123", "Wroclaw"),
                        new PrawoJazdy("01-01-2018", kategorie_1, "Prezydent Wroclawia")
                )
        );

        samochody[1] = new Samochod(
                "Toyota", "Corolla", "15-03-2010", 180000, "TYT93847218", "KR56789",
                new Silnik(120, 1600, true),
                new SkrzyniaBiegow(5, 1, "neutralny"),
                new Wlasciciel("Piotr", "Nowak", "870503876512",
                        new Adres("Zielona", 12, "31-456", "Krakow"),
                        new PrawoJazdy("12-06-2015", kategorie_2, "Prezydent Krakowa")
                )
        );

        samochody[2] = new Samochod(
                "Ford", "Focus", "10-07-2012", 210000, "FRD12387126", "PO98765",
                new Silnik(140, 2000, false),
                new SkrzyniaBiegow(6, 1, "neutralny"),
                new Wlasciciel("Michał", "Wiśniewski", "890104543829",
                        new Adres("Słoneczna", 5, "60-789", "Poznan"),
                        new PrawoJazdy("05-05-2014", kategorie_3, "Prezydent Poznania")
                )
        );

        samochody[3] = new Samochod(
                "Honda", "Civic", "25-12-2015", 150000, "HND73829823", "GD23456",
                new Silnik(130, 1800, true),
                new SkrzyniaBiegow(6, 1, "neutralny"),
                new Wlasciciel("Adam", "Szymański", "900210765321",
                        new Adres("Wiosenna", 22, "80-456", "Gdansk"),
                        new PrawoJazdy("18-09-2016", kategorie_4, "Prezydent Gdanska")
                )
        );

        samochody[4] = new Samochod(
                "BMW", "3 Series", "08-09-2018", 90000, "BMW23876292", "WA12389",
                new Silnik(180, 2500, false),
                new SkrzyniaBiegow(8, 1, "neutralny"),
                new Wlasciciel("Kamil", "Dąbrowski", "920410394758",
                        new Adres("Lazurowa", 16, "02-345", "Warszawa"),
                        new PrawoJazdy("02-02-2019", kategorie_5, "Prezydent Warszawy")
                )
        );
        for (int i = 0; i < samochody.length; i++) {
            samochody[i].wyswietlSamochod();
            System.out.println();
        }

    }
}
