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
                if (rel[i][j] == rel[j][i] && rel[i][j] == 1) {
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
    static void testZwrotna(){
        int[][] relacja_zwrotna = {
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1}
        };

        if (czyZwrotna(relacja_zwrotna)) {
            System.out.println("Sprawdzanie zwrotnosci działa prawidłowo");
        } else {
            System.out.println("Sprawdzanie zwrotnosci NIE działa prawidłowo");
        }
    }

    static void testPrzeciwzwrotna(){
        int[][] relacja_przeciwzwrotna = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {0, 1, 1, 1, 0}
        };

        if (czyPrzeciwzwrotna(relacja_przeciwzwrotna)) {
            System.out.println("Sprawdzanie przeciwzwrotnosci działa prawidłowo");
        } else {
            System.out.println("Sprawdzanie przeciwzwrotnosci NIE działa prawidłowo");
        }
    }

    static void testSymetryczna(){
        int[][] relacja_symetryczna = {
                {1, 0, 1, 0, 1},
                {0, 1, 1, 0, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 1, 1}
        };

        if (czySymetryczna(relacja_symetryczna)) {
            System.out.println("Sprawdzanie symetrii działa prawidłowo");
        } else {
            System.out.println("Sprawdzanie symetrii NIE działa prawidłowo");
        }
    }

    static void testPrzeciwsymetryczna(){
        int[][] relacja_przeciwsymetryczna = {
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        if (czyPrzeciwsymetryczna(relacja_przeciwsymetryczna)) {
            System.out.println("Sprawdzanie przeciwsymetrii działa prawidłowo");
        } else {
            System.out.println("Sprawdzanie przeciwsymetrii NIE działa prawidłowo");
        }
    }

    static void TestRelacje() {
        System.out.println();
        testZwrotna();
        testPrzeciwzwrotna();
        testSymetryczna();
        testPrzeciwsymetryczna();

    }

    public static void zadanie6() {
        int[][] rel = new int[5][5];
        System.out.println("Zadanie 6");
        fillRelation(rel);
        Util.printArray(rel);
        wlasnosciRelacji(rel);
        TestRelacje();
    }
}