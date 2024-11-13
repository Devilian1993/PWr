class Util {

    public static void bubbleSort(int[] a) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static int randomInteger(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = randomInteger(10);
        }
    }

    public static void fillArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = randomInteger(10);
            }
        }
    }

    public static void printArray(int[] array) {
        System.out.println("Tablica:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] array) {
        System.out.println("Tablica:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(int[] array, boolean is_sorted) {
        if (!is_sorted) {
            System.out.println("Tablica przed posortowaniem:");
        } else {
            System.out.println("Tablica po posortowaniu:");
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] array, boolean is_sorted) {
        if (!is_sorted) {
            System.out.println("Tablica przed posortowaniem:");
        } else {
            System.out.println("Tablica po posortowaniu:");
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bubbleSortRows(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            Util.bubbleSort(a[i]);
        }
    }
}