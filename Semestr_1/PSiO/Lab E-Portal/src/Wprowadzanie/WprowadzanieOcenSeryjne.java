package Wprowadzanie;

import Modele.Kurs;
import Modele.Osoba;
import Modele.Student;

import Wyszukiwanie.Wyszukiwanie;

import java.util.ArrayList;
import java.util.Scanner;

public class WprowadzanieOcenSeryjne implements WprowadzanieOcenStrategia {
    @Override
    public void MenuWprowadzanieOcen(ArrayList<Osoba> osoby) {
        System.out.println("Seryjne wprowadzanie ocen (wszystkie kursy jednoczesnie)");

        Wyszukiwanie.wypiszWszystkichStudentow(osoby);
        ArrayList<Student> studenci = Wyszukiwanie.getWszyscyStudenci(osoby);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz nr indeksu studenta, ktoremu chcesz wpisac oceny");
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
                    System.out.printf("Kurs: %s\nPodaj ocenę: ", kurs.getNazwa());
                    int ocena = scanner.nextInt();
                    scanner.nextLine();

                    kurs.setOcena(ocena);
                }

                System.out.println("Wpisano wszystkie oceny");
            }
        }
    }
}
