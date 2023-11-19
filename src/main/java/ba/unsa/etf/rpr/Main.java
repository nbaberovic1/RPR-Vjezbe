package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        KolekcijaImena kolekcijaImena = new KolekcijaImena();
        kolekcijaImena.dodajIme("Per Perka");
        kolekcijaImena.dodajIme("Mujo Mujic");
        kolekcijaImena.dodajIme("Profa Profic");

        try{
            kolekcijaImena.dodajIme("Beg Bego Begic");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Imena u kolekciji su: ");
        ArrayList<String> imena = kolekcijaImena.getImena();
        for(String ime : imena){
            System.out.println(ime);
        }

        System.out.println("Najduze ime iz kolekcije je: " + kolekcijaImena.getNajduzeIme());
        System.out.println();


    }
}