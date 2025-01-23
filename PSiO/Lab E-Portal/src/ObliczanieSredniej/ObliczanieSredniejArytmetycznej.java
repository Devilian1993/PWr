package ObliczanieSredniej;

import java.util.ArrayList;
import java.util.Scanner;
import Modele.Student;
import Modele.Kurs;
import Modele.Osoba;
import Wyszukiwanie.Wyszukiwanie;

public class ObliczanieSredniejArytmetycznej implements ObliczanieSredniejStrategia {
    @Override
    public double obliczanieSredniej(Student student) {
        int suma = 0;
        for (Kurs kurs: student.getKursy()) {
            suma += kurs.getOcena();
        }
        if (student.getKursy().size() == 0 || suma == 0) {
            return -1;
        }
        return suma / (double) student.getKursy().size();
    }

    public static double obliczanieSredniejStatic(Student student) {
        int suma = 0;
        int rozmiar = 0;
        for (Kurs kurs: student.getKursy()) {
            if (kurs.getOcena() != 0) {
                suma += kurs.getOcena();
                rozmiar++;
            }
        }
        return suma / (double) rozmiar;
    }

    @Override
    public void menuObliczanieSredniej(ArrayList<Osoba> osoby) {
        System.out.println("Obliczanie sredniej arytmetycznej\n1. Wybierz studenta\n2. Wypisz srednia wszystkich");
        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Student> studenci = Wyszukiwanie.getWszyscyStudenci(osoby);

        switch (option) {
            case 1:

                Wyszukiwanie.wypiszWszystkichStudentow(osoby);

                System.out.println("Wpisz nr indeksu studenta, ktorego srednia chcesz policzyc");
                int nr_indeksu = scanner.nextInt();
                scanner.nextLine();

                boolean studentZnaleziony = false;

                for (Student student : studenci) {
                    if (student.getNr_indeksu() == nr_indeksu) {
                        studentZnaleziony = true;
                        double srednia = obliczanieSredniej(student);

                        if (srednia >= 0) {
                            System.out.printf("Student: %s\nSrednia: %3f\n", student.getImie() + " " + student.getNazwisko(), obliczanieSredniej(student));
                        } else {
                            System.out.printf("Student: %s\nBrak ocen, nie mozna policzyc sredniej\n", student.getImie() + " " + student.getNazwisko());
                        }
                    }
                }
                break;

            case 2:

                for (Student student : studenci) {
                    double srednia = obliczanieSredniej(student);

                    if (srednia >= 0) {
                        System.out.printf("Student: %s\nSrednia: %3f\n", student.getImie() + " " + student.getNazwisko(), obliczanieSredniej(student));
                    } else {
                        System.out.printf("Student: %s\nBrak ocen, nie mozna policzyc sredniej\n", student.getImie() + " " + student.getNazwisko());
                    }
                }
                break;

            default:
                System.out.println("Wybierz poprawna opcje!");
                break;
        }
    }
}
