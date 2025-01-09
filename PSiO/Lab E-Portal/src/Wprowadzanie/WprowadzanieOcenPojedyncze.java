package Wprowadzanie;

import Modele.Kurs;
import Modele.Osoba;
import Modele.Student;

import Wyszukiwanie.Wyszukiwanie;

import java.util.ArrayList;
import java.util.Scanner;

public class WprowadzanieOcenPojedyncze implements WprowadzanieOcenStrategia {
    @Override
    public void MenuWprowadzanieOcen(ArrayList<Osoba> osoby) {
        System.out.println("Pojedyncze wprowadzanie ocen (dla jednego kursu)");

        Wyszukiwanie.wypiszWszystkichStudentow(osoby);
        ArrayList<Student> studenci = Wyszukiwanie.getWszyscyStudenci(osoby);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz nr indeksu studenta, ktoremu chcesz wpisac ocene");
        int nr_indeksu = scanner.nextInt();
        scanner.nextLine();

        boolean studentZnaleziony = false;

        for (Student student : studenci) {
            if (student.getNr_indeksu() == nr_indeksu) {
                studentZnaleziony = true;

                ArrayList<Kurs> kursy = student.getKursy();

                if (kursy.isEmpty()) {
                    System.out.println("Student nie jest zapisany na żadne kursy. Nie można wpisać oceny");
                    break;
                }

                for (Kurs kurs : kursy) {
                    kurs.wypiszDaneKursu(kursy.indexOf(kurs) + 1);
                }

                System.out.println("Podaj nr kursu z którego chcesz wpisać ocene");
                int nr_kursu = scanner.nextInt() - 1;

                scanner.nextLine();
                System.out.printf("Kurs: %s\nPodaj ocenę: ", kursy.get(nr_kursu).getNazwa());
                int ocena = scanner.nextInt();
                scanner.nextLine();
                kursy.get(nr_kursu).setOcena(ocena);
            }
        }
    }
}
