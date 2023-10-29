package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Double> brojevi = new ArrayList<Double>();
        Scanner ulaz = new Scanner(System.in);

        while (true){
            System.out.print("Unesite broj ili rijec stop: ");
            String unos = ulaz.nextLine();
            if("stop".equalsIgnoreCase(unos.trim())) break;
            try{
                Double broj = Double.parseDouble(unos);
                brojevi.add(broj);
            }catch (Exception e){
                System.out.println("Nije unesen broj!");
            }
        }

        System.out.println("\nNajmanji element je: " + MatVis.min(brojevi));
        System.out.println("Najveći element je: " + MatVis.max(brojevi));
        System.out.println("Mean vrijednost je: " + MatVis.mean(brojevi));
        System.out.println("Standardna devijacija je: " + MatVis.standardnaDevijacija(brojevi));

    }
}