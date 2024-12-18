import java.util.ArrayList;
import java.util.Scanner;

public class Wyszukiwanie {

    public static void wypiszWszystkieOsoby(ArrayList<Osoba> osoby) {
        for (Osoba osoba : osoby) {
            osoba.wypiszDane();
        }
    }

    public static void wypiszWszystkichStudentow(ArrayList<Osoba> osoby) {
        ArrayList<Student> studenci = getWszyscyStudenci(osoby);
        for (Student student: studenci) {
            student.wypiszDane();
        }
    }

    public static ArrayList<Student> getWszyscyStudenci(ArrayList<Osoba> osoby) {
        ArrayList<Student> students = new ArrayList<>();

        for (Osoba osoba : osoby) {
            if (osoba instanceof Student) {
                students.add((Student) osoba);
            }
        }

        return students;
    }

    public static void wypiszPracownikowPowyzejDorobku(ArrayList<Osoba> osoby, int dorobek) {
        for (Osoba osoba : osoby) {
            if (osoba instanceof PracownikNaukowoDydaktyczny && ((PracownikNaukowoDydaktyczny) osoba).getDorobek() > dorobek) {
                osoba.wypiszDane();
            }
        }
    }

    public static void znajdzPoNazwisku(ArrayList<Osoba> osoby, String nazwisko) {
        for (Osoba osoba : osoby) {
            if (osoba.getNazwisko().equals(nazwisko)) {
                System.out.println("Znaleziono osobe");
                osoba.wypiszDane();
            }
        }
    }

    public static void znajdzPoNrIndeksu(ArrayList<Osoba> osoby, int nr_indeksu) {
        for (Osoba osoba : osoby) {
            if (osoba instanceof Student && ((Student) osoba).getNr_indeksu() == nr_indeksu) {
                System.out.println("Znaleziono studenta");
                osoba.wypiszDane();
            }
        }
    }

    public static void znajdzPoStanowisku(ArrayList<Osoba> osoby, String stanowisku) {
        for (Osoba osoba : osoby) {
            if (osoba instanceof PracownikUczelni && ((PracownikUczelni) osoba).getStanowisko().equals(stanowisku)) {
                System.out.println("Znaleziono pracownika");
                osoba.wypiszDane();
            }
        }
    }

    public static void menuWyszukiwania(ArrayList<Osoba> osoby) {
        System.out.println("Wyszukiwania\n1. Znajdz osobe po nazwisku\n2. Znajdz studenta po nr indeksu\n3. Znajdz pracownika po stanowisku \n4. Znajdz pracownika naukowo-dydaktycznego o dorobku powyzej danej wartosci\n5. Anuluj");
        System.out.print("Wybierz opcje: ");
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
        scanner.nextLine();

        switch (wybor) {
            case 1:
                System.out.print("Podaj nazwisko: ");
                znajdzPoNazwisku(osoby, scanner.nextLine());
                scanner.nextLine();
                break;
            case 2:
                System.out.print("Podaj nr indeksu: ");
                znajdzPoNrIndeksu(osoby, scanner.nextInt());
                scanner.nextLine();
                break;
            case 3:
                System.out.print("Podaj stanowisko: ");
                znajdzPoStanowisku(osoby, scanner.nextLine());
                scanner.nextLine();
                break;
            case 4:
                System.out.print("Podaj dorobek: ");
                wypiszPracownikowPowyzejDorobku(osoby, scanner.nextInt());
                scanner.nextLine();
                break;
            case 5:
                break;
            default:
                System.out.println("Wybierz poprawna opcje!");
        }

    }
}
