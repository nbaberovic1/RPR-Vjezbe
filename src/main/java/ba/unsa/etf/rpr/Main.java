package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Main {
    public static int sumaCifara(int n) {
            int suma = 0;
            if(n<0) n = -n;
            while(n != 0) {
                suma += n%10;
                n /= 10;
            }
            return suma;
    }

    public static void main(String[] args) {
        int n;
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite broj n: ");
        n = ulaz.nextInt();
        if(n < 1) System.out.println("Uneseni broj je manji od 1!");
        for(int i=1; i <= n; i++) {
            if ((i % sumaCifara(i)) == 0) System.out.println(i);
        }
    }
}