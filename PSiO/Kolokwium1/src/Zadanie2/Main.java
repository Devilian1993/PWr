package Zadanie2;

public class Main {
    public static void main(String[] args) {
        Osoba[] osoba = new Osoba[5];

        osoba[0] = new Student("Tomasz", "Kowalski", "05311114987", 200321,
                new Kurs[]{
                        new Kurs(8, "Analiza matematyczna", 60),
                        new Kurs(5, "Algebra", 60),
                });
        osoba[1] = new Student("Jan", "Nowak", "043115098613", 198714,
                new Kurs[]{
                        new Kurs(8, "Analiza matematyczna", 60),
                        new Kurs(5, "Algebra", 60),
                        new Kurs(5, "Programowanie strukturalne i obiektowe", 90)
                });
        osoba[2] = new Student("Ania", "Duda", "06320406342", 284003,
                new Kurs[]{
                        new Kurs(8, "Analiza matematyczna", 60),
                        new Kurs(5, "Algebra", 60),
                        new Kurs(5, "Programowanie strukturalne i obiektowe", 90),
                        new Kurs(3, "Fizyka 1", 45)
                });
        osoba[3] = new Prowadzacy("Joanna", "Karolak", "931209763", "magister",
                new String[]{"Matematyka", "Informatyka"});
        osoba[4] = new Prowadzacy("Karol", "Powazny", "76090987654", "profesor",
                new String[]{"Sieci neuronowe", "Fizyka kwantowa", "Psychologia"});

        for (int i = 0; i < osoba.length; i++) {
            osoba[i].wyswietlStan();
        }
    }
}
