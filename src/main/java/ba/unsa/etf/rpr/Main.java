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

        Pobjednik pobjednik = new Pobjednik(kolekcijaImena);

        System.out.println("Ime pobjednika je: " + pobjednik.getIme());
        System.out.println("Prezime je: " + pobjednik.getPrezime());
        System.out.println("Duzina imena je: " + pobjednik.getBrojZnakova());
        System.out.println();

        KolekcijaImenaIPrezimena kolekcijaIIP  =  new KolekcijaImenaIPrezimena();

        kolekcijaIIP.setImeIPrezime("Meho", "Mehic");
        kolekcijaIIP.setImeIPrezime("Mis", "Misic");
        kolekcijaIIP.setImeIPrezime("Najduze", "Ime");

        int indexNajduzegPara = kolekcijaIIP.getIndexNajduzegPara();
        System.out.println("Najduzi par ime i prezime je: " + kolekcijaIIP.getImeIPrezime(indexNajduzegPara));
        System.out.println();

        Pobjednik pobjednikNoveKolek = new Pobjednik(kolekcijaIIP);

        System.out.println("Ime novog pobjednika je: " + pobjednikNoveKolek.getIme());
        System.out.println("Prezime je: " + pobjednikNoveKolek.getPrezime());
        System.out.println("Duzina imena je: " + pobjednikNoveKolek.getBrojZnakova());
    }
}