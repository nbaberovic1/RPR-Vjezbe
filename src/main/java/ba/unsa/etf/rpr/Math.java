package ba.unsa.etf.rpr;

public class Math {

    public final static double PI = 3.14159265358979323846;

    public static double sin(double x) {
        double suma = 0;
        for (int i = 0; i <= 5; i = i + 1) {
            suma = suma + power(-1, i) * power(x, 2 * i + 1) / faktorijel(2 * i + 1);
        }
        return suma;
    }

    public static int faktorijel(int x){
        int fact = 1;
        for (int i=2; i <= x; i = i + 1){
            fact = fact * i;
        }
        return fact;
    }

    public static double power(double x, int pow){
        double produkt = 1;
        for (int i=0; i < pow; i = i + 1){
            produkt = produkt * x;
        }
        return produkt;
    }
}
