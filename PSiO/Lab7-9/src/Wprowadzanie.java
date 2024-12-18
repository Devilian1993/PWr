import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Wprowadzanie {
    public static void wprowadzStudenta(ArrayList<Osoba> osoby) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadzanie studenta:");
        System.out.print("Imie: ");
        String imie = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Nazwisko: ");
        String nazwisko = scanner.nextLine();
        scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Nr indeksu: ");
        int nr_indeksu = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Rok: ");
        int rok = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Semestr: ");
        int semestr = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Kierunek: ");
        String kierunek = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Wydzial: ");
        String wydzial = scanner.nextLine();
        scanner.nextLine();

        osoby.add(new Student(imie, nazwisko, pesel, nr_indeksu, rok, semestr, kierunek, wydzial));
    }

    public static void wprowadzPracownikaNaukowoDydaktycznego(ArrayList<Osoba> osoby) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadzanie pracownika naukowo-dydaktycznego:");
        System.out.print("Imie: ");
        String imie = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Nazwisko: ");
        String nazwisko = scanner.nextLine();
        scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Pensja: ");
        int pensja = scanner.nextInt();
        scanner.nextLine();
        System.out.print("E-mail: ");
        String e_mail = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Jednostka: ");
        String jednostka = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Staz pracy: ");
        int staz = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Stopien naukowy: ");
        String stopien = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Dorobek: ");
        int dorobek = scanner.nextInt();
        scanner.nextLine();

        osoby.add(new PracownikNaukowoDydaktyczny(imie, nazwisko, pesel, pensja, e_mail, jednostka, staz, stopien, dorobek));
    }

    public static void wprowadzPracownikaAdministracyjnego(ArrayList<Osoba> osoby) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadzanie pracownika administracyjnego:");
        System.out.print("Imie: ");
        String imie = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Nazwisko: ");
        String nazwisko = scanner.nextLine();
        scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Pensja: ");
        int pensja = scanner.nextInt();
        scanner.nextLine();
        System.out.print("E-mail: ");
        String e_mail = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Jednostka: ");
        String jednostka = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Staz pracy: ");
        int staz = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Stanowisko: ");
        String stanowisko = scanner.nextLine();
        scanner.nextLine();

        osoby.add(new PracownikUczelni(imie, nazwisko, pesel, pensja, e_mail, jednostka, staz, stanowisko));
    }

    public static void menuWprowadzania(ArrayList<Osoba> osoby) {
        System.out.println("Jaki typ osoby chcesz wprowadzic?\n1. Studenta\n2. Pracownika naukowo-dydaktycznego\n3. Pracownika administracyjnego\n4. Anuluj");
        System.out.print("Wybierz: ");
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();

        switch (wybor) {
            case 1:
                wprowadzStudenta(osoby);
                break;
            case 2:
                wprowadzPracownikaNaukowoDydaktycznego(osoby);
                break;
            case 3:
                wprowadzPracownikaAdministracyjnego(osoby);
                break;
            case 4:
                System.out.println("Anulowano wprowadzanie");
                break;
            default:
                System.out.println("Wybierz poprawna liczbe!");
                menuWprowadzania(osoby);
                break;
        }
    }

    public static void zapiszNaKursy(ArrayList<Osoba> osoby) {
        Wyszukiwanie.wypiszWszystkichStudentow(osoby);
        ArrayList<Student> studenci = Wyszukiwanie.getWszyscyStudenci(osoby);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz nr indeksu studenta, ktorego chcesz zapisac na kursy");
        int nr_indeksu = scanner.nextInt();
        scanner.nextLine();

        boolean studentZnaleziony = false;

        for (Student student : studenci) {
            if (student.getNr_indeksu() == nr_indeksu) {
                studentZnaleziony = true;

                ArrayList<Kurs> kursy = new ArrayList<>();

                boolean zapisywanieNaKursy = true;

                while (zapisywanieNaKursy) {

                    System.out.println("Zapisywanie studenta na kursy");
                    System.out.println("1. Dodaj kurs\n2. Zapisz i zakończ");

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            System.out.print("Nazwa kursu: ");
                            String nazwa = scanner.nextLine();
                            scanner.nextLine();

                            System.out.print("Rodzaj kursu: ");
                            String rodzaj = scanner.nextLine();
                            scanner.nextLine();

                            System.out.print("Liczba punktow ECTS: ");
                            int liczba = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Liczba godzin kursu w semestrze: ");
                            int semestrze = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Czy grupa kursow (T/N): ");
                            String czy = scanner.nextLine();
                            scanner.nextLine();
                            boolean gk = czy.equals("T");

                            kursy.add(new Kurs(nazwa, rodzaj, liczba, semestrze, gk));

                            break;
                        case 2:
                            student.setKursy(kursy);
                            zapisywanieNaKursy = false;
                            System.out.println("Kursy zapisane");
                            break;
                    }
                }
            }
        }
    }
}
