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