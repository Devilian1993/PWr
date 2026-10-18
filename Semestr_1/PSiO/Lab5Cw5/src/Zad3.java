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
        System.out.println();
    }
}
