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