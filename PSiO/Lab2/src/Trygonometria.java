public class Trygonometria {


    static double cosX(double x, int k){
        double result = 1;
        double licznik = 1;
        double mianownik = 1;

        for (int i = 1; i <= k; i++) {
            licznik *= x*x;
            mianownik *= (2*i - 1)*(2*i);
            if(i%2 == 0) {
                result += licznik/mianownik;
            } else {
                result -= licznik/mianownik;
            }
        }
        return result;
    }

    static double sinX(double x, int k){
        double result = x;
        double licznik = x;
        double mianownik = 1;

        for (int i = 1; i <= k; i++) {
            licznik *= x*x;
            mianownik *= (2*i)*(2*i+1);
            if(i%2 == 0) {
                result += licznik/mianownik;
            } else {
                result -= licznik/mianownik;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        double x = 3.141592/6;
        int k = 100;
        System.out.println("Sin(x): " + sinX(x, k));
        System.out.println("Sin(x) z Math: " + Math.sin(x));
        System.out.println("Cos(x): " + cosX(x, k));
        System.out.println("Cos(x) z Math: " + Math.cos(x));
    }
}
