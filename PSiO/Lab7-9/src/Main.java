import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Osoba> osoby = new ArrayList<Osoba>();
        Serializacja.wczytaj(osoby);

        /*

        Student student1 = new Student("Tomek", "Informatyk", "052611239853", 284312,
                "Informatyka stosowana", "Wydział Informatyki i Telekomunikacji");

        ArrayList<Kurs> kursy = new ArrayList<>();
        kursy.add(new Kurs("Analiza matematyczna 1", "Wykład + ćwiczenia", 8, 30, true));
        kursy.add(new Kurs("Algebra liniowa z geometrią analityczną", "Wykład + ćwiczenia", 5, 30, true));
        kursy.add(new Kurs("Logika dla informatyków", "Wykład + ćwiczenia", 5, 30, true));
        kursy.add(new Kurs("Organizacja systemów komputerowych", "Wykład + ćwiczenia", 4, 30, true));
        kursy.add(new Kurs("Programowanie strukturalne i obiektowe", "Laboratoria", 2, 30, false));

        student1.setKursy(kursy);

        Student student2 = new Student("Anna", "Matematyk", "052611123456", 123456,
                "Matematyka", "Wydział Matematyki");

        ArrayList<Kurs> kursy2 = new ArrayList<>();
        kursy2.add(new Kurs("Analiza matematyczna 2", "Wykład + ćwiczenia", 7, 30, true));
        kursy2.add(new Kurs("Rachunek prawdopodobieństwa", "Wykład + ćwiczenia", 6, 30, true));
        kursy2.add(new Kurs("Statystyka matematyczna", "Wykład + ćwiczenia", 5, 30, true));
        kursy2.add(new Kurs("Metody numeryczne", "Wykład + ćwiczenia", 4, 30, true));
        kursy2.add(new Kurs("Podstawy teorii liczb", "Wykład + ćwiczenia", 3, 30, false));

        student2.setKursy(kursy2);

        // Student 3
        Student student3 = new Student("Piotr", "Architekt", "052611987654", 654321,
                "Architektura", "Wydział Architektury");

        ArrayList<Kurs> kursy3 = new ArrayList<>();
        kursy3.add(new Kurs("Historia architektury", "Wykład", 6, 30, true));
        kursy3.add(new Kurs("Podstawy projektowania", "Laboratoria", 8, 30, true));
        kursy3.add(new Kurs("Budownictwo ogólne", "Wykład + ćwiczenia", 5, 30, true));
        kursy3.add(new Kurs("Rysunek techniczny", "Laboratoria", 4, 30, true));
        kursy3.add(new Kurs("Zasady ergonomii", "Wykład", 3, 30, false));

        student3.setKursy(kursy3);

        PracownikNaukowoDydaktyczny pracowniknd1 = new PracownikNaukowoDydaktyczny("Janusz", "Doktorski", "751212098732",
                6500, "janusz.doktorski@pwr.edu.pl", "Wydzial informatyki i telekomunikacji", 5, "dr", 2000);
        PracownikNaukowoDydaktyczny pracowniknd2 = new PracownikNaukowoDydaktyczny("Anna", "Matematyczna", "850101123456",
                7200, "anna.matematyczna@pwr.edu.pl", "Wydzial matematyki", 10, "prof. dr hab.", 5000);


        PracownikUczelni pracowniku1 = new PracownikUczelni("Janina", "Dziekanacka", "5901017613",
                4500, "janina.dziekanacka@pwr.edu.pl", "Wydzial informatyki i telekomunikacji", 20, "Dziekanat");
        PracownikUczelni pracowniku2 = new PracownikUczelni("Piotr", "Rekrutacyjny", "870312456789",
                4000, "piotr.rekrutacyjny@pwr.edu.pl", "Dział rekrutacji", 8, "Specjalista ds. rekrutacji");


        osoby.add(student1);
        osoby.add(student2);
        osoby.add(student3);

        osoby.add(pracowniku1);
        osoby.add(pracowniku2);

        osoby.add(pracowniknd1);
        osoby.add(pracowniknd2);

        */
        //Wyszukiwanie.znajdzPoNazwisku(osoby, "Matematyk");
        //Wyszukiwanie.znajdzPoNrIndeksu(osoby, 654321);
        //Wyszukiwanie.znajdzPoStanowisku(osoby, "Dziekanat");
        //Wyszukiwanie.wypiszPracownikowPowyzejDorobku(osoby, 4000);

        boolean dziala = true;

        while (dziala) {
            System.out.println("Wybierz opcje\n1. Wypisywanie wszystkich osob\n2. Wyszukiwanie osoby\n3. Dodanie nowej osoby\n4. Zapisz studenta na kursy\n5. Wylacz program");
            Scanner scanner = new Scanner(System.in);
            int opcja = scanner.nextInt();
            scanner.nextLine();

            switch (opcja) {
                case 1:
                    Wyszukiwanie.wypiszWszystkieOsoby(osoby);
                    Serializacja.zapisz(osoby);
                    break;
                case 2:
                    Wyszukiwanie.menuWyszukiwania(osoby);
                    Serializacja.zapisz(osoby);
                    break;
                case 3:
                    Wprowadzanie.menuWprowadzania(osoby);
                    Serializacja.zapisz(osoby);
                    break;
                case 4:
                    Wprowadzanie.zapiszNaKursy(osoby);
                    Serializacja.zapisz(osoby);
                    break;
                case 5:
                    dziala = false;
                    Serializacja.zapisz(osoby);
                    break;
                default:
                    System.out.println("Wybierz poprawna opcje!");
            }
        }
    }
}
