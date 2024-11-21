class Zad4 {

    public static void zadanie4() {
        int[][] tab1 = new int[10][20];
        System.out.println("Zadanie 4");
        Util.fillArray(tab1);
        Util.printArray(tab1, false);
        tab1 = Util.arraySortedByColumns(tab1);
        Util.printArray(tab1, true);
        System.out.println();
    }
}