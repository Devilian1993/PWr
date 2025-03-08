import java.util.Random;

public class Zadanie1 {

    public static int generuj_losowa_liczba() {
        Random random = new Random();
        return random.nextInt(257);
    }

    public static int[][] generuj_macierz(int M, int N) {
        int[][] wynik = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                wynik[i][j] = generuj_losowa_liczba();
            }
        }

        return wynik;
    }

    public static int[] tablicaZliczen(int[][] macierz) {
        int[] tablica_zliczen = new int[257];
        for (int i = 0; i < macierz.length; i++) {
            for (int j = 0; j < macierz[i].length; j++) {
                tablica_zliczen[macierz[i][j]]++;
            }
        }
        return tablica_zliczen;
    }

    public static void histogram(int[][] macierz) {
        int[] tablica_zliczen = tablicaZliczen(macierz);
        for (int i = 0; i < tablica_zliczen.length; i++) {
            System.out.printf("Liczba %d wystepuje %d razy\n", i, tablica_zliczen[i]);
        }
    }

    public static void wyswietlWartosciPowyzejProguWystapien(int T, int[][] macierz) {
        int[] tablica_zliczen = tablicaZliczen(macierz);
        System.out.printf("Wartosci o progu wystapien powyzej: %d\n", T);
        for (int i = 0; i < tablica_zliczen.length; i++) {
            if(tablica_zliczen[i] > T) {
                System.out.println(i);
            }
        }
    }

    public static void wyswietlWartosciOMaksymalnejLicznosci(int[][] macierz) {
        System.out.println("Najczesciej wystepujace wartosci: ");
        int[] tablica_zliczen = tablicaZliczen(macierz);
        int max_licznosc = 0;
        for (int i = 0; i < tablica_zliczen.length; i++) {
            if(tablica_zliczen[i] > max_licznosc) {
                max_licznosc = tablica_zliczen[i];
            }
        }
        for (int i = 0; i < tablica_zliczen.length; i++) {
            if(tablica_zliczen[i] == max_licznosc) {
                System.out.println(i);
            }
        }

    }

    public static void wyswietlMacierz(int[][] macierz) {
        for (int i = 0; i < macierz.length; i++) {
            for (int j = 0; j < macierz[i].length; j++) {
                System.out.printf("%3d ", macierz[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final int M = 10;
        final int N = 10;
        int[][] macierz = generuj_macierz(M, N);
        wyswietlMacierz(macierz);
        histogram(macierz);
        wyswietlWartosciPowyzejProguWystapien(2, macierz);
        wyswietlWartosciOMaksymalnejLicznosci(macierz);
    }
}