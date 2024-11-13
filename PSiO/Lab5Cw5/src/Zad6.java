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