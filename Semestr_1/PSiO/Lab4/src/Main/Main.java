package Main;

import Student.Student;
import Insurance.Insurance;


public class Main {

    final static int MAX_ST = 10;
    final static int YEAR_TO_CHECK = 2;

    public static Student best_student_from_year(Student[] students, int year){
        Student best_student = null;
        double best_average = 0;

        for (int i = 0; i < students.length; i++) {
            if (students[i].getYear() == year && students[i].getAverage() > best_average) {
                best_student = students[i];
                best_average = students[i].getAverage();
            }
        }

        return best_student;
    }

    public static void display_all_students(Student[] students){
        for (int i = 0; i < students.length; i++) {
            students[i].student_data();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Student[] students = new Student[MAX_ST];

        students[0] = new Student(87123951, 123456, "Jan", "Kowalski", new Insurance(0, true, "07-11-2025", 100000));
        students[1] = new Student(92134951, 123457, "Piotr", "Nowak");
        students[2] = new Student(83124511, 123458, "Michał", "Wiśniewski", new Insurance(1, false, "15-05-2024", 50000));
        students[3] = new Student(91123571, 123459, "Kamil", "Wójcik");
        students[4] = new Student(78125611, 123460, "Tomasz", "Kamiński", new Insurance(2, true, "20-03-2026", 75000));
        students[5] = new Student(85123711, 123461, "Paweł", "Zieliński");
        students[6] = new Student(74123841, 123462, "Adam", "Szymański", new Insurance(3, false, "10-10-2023", 120000));
        students[7] = new Student(80123951, 123463, "Jakub", "Woźniak");
        students[8] = new Student(76124521, 123464, "Mateusz", "Dąbrowski", new Insurance(4, true, "12-12-2024", 95000));
        students[9] = new Student(79123671, 123465, "Dawid", "Kozłowski");

        /*
        Student best_student = best_student_from_year(students, YEAR_TO_CHECK);
        if (best_student == null) {
            System.out.println("There is no student on that year");
        } else {
            best_student.student_data();
        }
        */

        display_all_students(students);
    }
}
