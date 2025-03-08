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