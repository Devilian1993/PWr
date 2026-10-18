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