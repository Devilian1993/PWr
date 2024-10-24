package Program_glowny;


import Biblioteka.biblioteka;

public class main {
    public static void main(String[] args) {
        double x = 3.141/6;
        System.out.println(biblioteka.sin(x));
        System.out.println(Math.sin(x));
        System.out.println(biblioteka.cos(x));
        System.out.println(Math.cos(x));

        int y = 1;
        System.out.println(biblioteka.exp(y));
        System.out.println(Math.exp(y));
    }
}
