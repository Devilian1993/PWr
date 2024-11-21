public class MnozenieMacierzy {
    public static int[][] mnozenieMacierzy(int[][] macierz1, int[][] macierz2, int m, int n, int k) {
        int[][] macierz_wynikowa = new int [macierz1.length][macierz2[0].length];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                for (int k1 = 0; k1 < m; k1++) {
                    macierz_wynikowa[i][j] += macierz1[i][k1]*macierz2[k1][j];
                }
            }
        }
        return macierz_wynikowa;
    }

    public static void main(String[] args) {
        final int m = 4;
        final int n = 3;
        final int k = 4;
        int[][] macierz1 = new int[m][n];
        int[][] macierz2 = new int[n][k];

        Util.fillArray(macierz1);
        Util.fillArray(macierz2);
        Util.printArray(macierz1);
        Util.printArray(macierz2);

        Util.printArray(mnozenieMacierzy(macierz1, macierz2, m, n, k));
    }
}
