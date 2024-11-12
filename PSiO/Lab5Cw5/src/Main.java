class Util {

    public static void bubbleSort(int[] a) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static int randomInteger(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = randomInteger(10);
        }
    }

    public static void fillArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = randomInteger(10);
            }
        }
    }

    public static void printArray(int[] array) {
        System.out.println("Tablica:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] array) {
        System.out.println("Tablica:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printArray(int[] array, boolean is_sorted) {
        if (is_sorted) {
            System.out.println("Tablica przed posortowaniem:");
        } else {
            System.out.println("Tablica po posortowaniu:");
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] array, boolean is_sorted) {
        if (!is_sorted) {
            System.out.println("Tablica przed posortowaniem:");
        } else {
            System.out.println("Tablica po posortowaniu:");
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bubbleSortRows(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            Util.bubbleSort(a[i]);
        }
    }
}

class Zad1 {

    public static void zadanie1() {

        int[] tab = new int[10];
        System.out.println("Zadanie 1");
        Util.fillArray(tab);
        Util.printArray(tab, false);
        Util.bubbleSort(tab);
        Util.printArray(tab, true);
        System.out.println();
    }
}

class Zad2 {

    public static void zadanie2() {
        int[][] tab = new int[10][20];
        System.out.println("Zadanie 2");
        Util.fillArray(tab);
        Util.printArray(tab, false);
        Util.bubbleSortRows(tab);
        Util.printArray(tab, true);
        System.out.println();
    }
}

class Zad3 {

    static int ileElementowPodzielnychPrzezCoNajmniej3Wartosci(int[] tab1, int[] tab2) {
        int licznikElementow = 0;

        for (int i = 0; i < tab1.length; i++) {
            int LicznikElementowPodzielnych = 0;
            for (int j = 0; j < tab2.length; j++) {
                if (tab1[i]%tab2[j] == 0) {
                    LicznikElementowPodzielnych++;
                }
            }
            if (LicznikElementowPodzielnych >= 3) {
                licznikElementow++;
            }
        }

        return licznikElementow;
    }

    public static void zadanie3() {
        int[] tab1 = new int[10];
        int[] tab2 = new int[10];

        Util.fillArray(tab1);
        Util.fillArray(tab2);
        System.out.println("Zadanie 3");
        System.out.print("1.");
        Util.printArray(tab1);
        System.out.print("2.");
        Util.printArray(tab2);

        System.out.println(ileElementowPodzielnychPrzezCoNajmniej3Wartosci(tab1, tab2) + " elementy tablicy 1 " +
                "sa podzielne przez co najmniej 3 elementy tablicy 2");
    }
}

class Zad4 {

    static void bubbleSortColumns(int[][] a) {
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                for (int k = 0; k < a.length - j - 1; k++) {
                    if (a[k][i] > a[k+1][i]) {
                        int temp = a[k][i];
                        a[k][i] = a[k+1][i];
                        a[k+1][i] = temp;
                    }
                }
            }
        }
    }

    public static void zadanie4() {
        int[][] tab1 = new int[10][20];
        System.out.println("Zadanie 4");
        Util.fillArray(tab1);
        Util.printArray(tab1, false);
        bubbleSortColumns(tab1);
        Util.printArray(tab1, true);
        System.out.println();
    }
}

class Zad5 {
    public static void zadanie5() {
        int[][] tab1 = new int[10][];
        System.out.println("Zadanie 5");

        for (int i = 0; i < tab1.length; i++) {
            tab1[i] = new int[Util.randomInteger(10)];
        }
        Util.fillArray(tab1);
        Util.printArray(tab1, false);
        Util.bubbleSortRows(tab1);
        Util.printArray(tab1, true);
        System.out.println();
    }
}

class Zad6 {

    static void fillRelation(int[][] rel) {
        for (int i = 0; i < rel.length; i++) {
            for (int j = 0; j < rel.length; j++) {
                rel[i][j] = (int) ((Math.random() * 10)%2);
            }
        }
    }

    static boolean czyZwrotna(int[][] rel){
        for (int i = 0; i < rel.length; i++) {
            if (rel[i][i] == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean czyPrzeciwzwrotna(int[][] rel){
        for (int i = 0; i < rel.length; i++) {
            if (rel[i][i] == 1) {
                return false;
            }
        }
        return true;
    }

    static boolean czySymetryczna(int[][] rel){
        for (int i = 0; i < rel.length; i++) {
            for (int j = 0; j < rel[i].length; j++) {
                if (rel[i][j] != rel[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean czyPrzeciwsymetryczna(int[][] rel){
        for (int i = 0; i < rel.length; i++) {
            for (int j = 0; j < rel[i].length; j++) {
                if (rel[i][j] == rel[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void wlasnosciRelacji(int[][] rel){
        System.out.println("Zwrotnosc: " + czyZwrotna(rel));
        System.out.println("Przeciwzwrotnosc: " + czyPrzeciwzwrotna(rel));
        System.out.println("Symetrycznosc: " + czySymetryczna(rel));
        System.out.println("Przeciwsymetrycznosc: " + czyPrzeciwsymetryczna(rel));
    }

    public static void zadanie6() {
        int[][] rel = new int[5][5];
        System.out.println("Zadanie 6");
        fillRelation(rel);
        Util.printArray(rel);
        wlasnosciRelacji(rel);
    }
}

public class Main {
    public static void main(String[] args) {
        Zad1.zadanie1();
        Zad2.zadanie2();
        Zad3.zadanie3();
        Zad4.zadanie4();
        Zad5.zadanie5();
        Zad6.zadanie6();
    }
}