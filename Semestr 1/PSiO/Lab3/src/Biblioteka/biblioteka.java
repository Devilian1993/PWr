package Biblioteka;

public class biblioteka {


    public static double sin(double x) {
        int k = 30;
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

    public static double cos(double x) {
        int k = 30;
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

    public static double exp(double x) {
        int k = 30;
        double result = 1;
        double licznik = 1;
        double mianownik = 1;

        for (int i = 1; i <= k; i++) {
            licznik *= x;
            mianownik *= i;
            result += licznik/mianownik;
        }

        return result;
    }
}
