package Student;

import java.util.Random;
import Insurance.Insurance;

public class Student {
    private int pesel;
    private int index;
    private String name;
    private String last_name;
    private int[] grades;
    private int year;
    private Insurance insurance;

    public Student(int pesel, int index, String name, String last_name) {
        this.pesel = pesel;
        this.index = index;
        this.name = name;
        this.last_name = last_name;
        this.insurance = null;
        this.grades = generate_grades();
        this.year = generate_year();
    }

    public Student(int pesel, int index, String name, String last_name, Insurance insurance) {
        this.pesel = pesel;
        this.index = index;
        this.name = name;
        this.last_name = last_name;
        this.insurance = insurance;
        this.grades = generate_grades();
        this.year = generate_year();
    }

    public Student(int pesel, int index, String name, String last_name, int[] grades, int year, Insurance insurance) {
        this.pesel = pesel;
        this.index = index;
        this.name = name;
        this.last_name = last_name;
        this.grades = grades;
        this.year = year;
        this.insurance = insurance;
    }

    public void setPesel(int p) {
        this.pesel = p;
    }

    public int getPesel() {
        return pesel;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    public int[] getGrades() {
        return grades;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public double getAverage() {
        double sum = 0;
        int[] grades = this.getGrades();
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        return sum / grades.length;
    }

    public static int[] generate_grades(){
        int[] grades = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            grades[i] = random.nextInt(4) + 2;
        }
        return grades;
    }

    public static int generate_year(){
        Random random = new Random();
        return random.nextInt(4) + 1;
    }

    public void display_grades(){
        for (int i = 0; i < this.getGrades().length; i++) {
            System.out.print(this.getGrades()[i] + " ");
        }
        System.out.println();
    }

    public void display_insurance(){
        if (this.insurance == null) {
            System.out.println("Student has no insurance");
        } else {
            this.insurance.insurance_data();
        }
    }

    public void student_data() {
        System.out.println("Name: " + this.name);
        System.out.println("Last name: " + this.last_name);
        System.out.println("PESEL: " + this.pesel);
        System.out.println("Index nr: " + this.index);
        System.out.println("Year: " + this.year);
        System.out.println("Grades: ");
        this.display_grades();
        System.out.println("Insurance: ");
        this.display_insurance();
    }
}
