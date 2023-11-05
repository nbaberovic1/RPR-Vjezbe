package ba.unsa.etf.rpr;

import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Program {

    public static Scanner ulaz = new Scanner(System.in);
    public static Imenik imenik = new Imenik();

    public static void main( String[] args ) throws TelefonskiBrojException {
        popuniPodatke();
        while (true) {
            System.out.println("Unesite komandu [dodaj, dajBroj, dajIme, naSlovo, izGrada, izGradaBrojevi, imenik, izlaz: ");
            String komanda = ulaz.nextLine();
            switch (komanda) {
                case "dodaj":
                    dodajBroj();
                    break;
                case "dajBroj":
                    dajBroj();
                    break;
                case "dajIme":
                    dajIme();
                    break;
                case "naSlovo":
                    naSlovo();
                    break;
                case "izGrada":
                    izGrada();
                    break;
                case "izGradaBrojevi":
                    izGradaBrojevi();
                    break;
                case "imenik":
                    ispisiImenik();
                    break;
                case "izlaz":
                    System.exit(0);
                default:
                    System.out.println("Nema takve komande!");
            }
        }
    }

    private static void ispisiImenik() {
        System.out.println(imenik.toString());
    }

    private static void dajBroj() {
        System.out.println("Unesite ime: ");
        String ime = ulaz.nextLine();
        String broj = imenik.dajBroj(ime);
        System.out.println( broj == null ? "Nema u imeniku!" : broj);
    }

    private static void dajIme() {
        try {
            TelefonskiBroj broj = unesiBrojTelefona();
            String ime = imenik.dajIme(broj);
            if (ime != null) {
                System.out.println("Vlasnik broja " + broj.ispisi() + " je " + ime);
            } else {
                System.out.println("Ne postoji u imeniku!");
            }
        }catch(TelefonskiBrojException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Uneseni netacni podaci!");
        }
    }

    private static TelefonskiBroj unesiBrojTelefona() throws TelefonskiBrojException {
        System.out.println("Unesi tip broja [mobilni, fiksni, medunarodni]: ");
        String tip = ulaz.nextLine();
        switch (tip){
            case "mobilni":
                System.out.println("Unesite mrežu: ");
                int mreza = ulaz.nextInt();
                ulaz.nextLine();
                System.out.println("Unesite broj: ");
                String mobBroj = ulaz.nextLine();
                return new MobilniBroj(mreza, mobBroj);
            case "fiksni":
                System.out.println("Unesite pozivni: ");
                String pozivni = ulaz.nextLine();
                System.out.println("Unesite broj: ");
                String fikBroj = ulaz.nextLine();
                return new FiksniBroj(Grad.izPozivnog(pozivni), fikBroj);
            case "medunarodni":
                System.out.println("Unesite kod drzave [+387]: ");
                String drzava = ulaz.nextLine();
                System.out.println("Unesite broj: ");
                String medBroj = ulaz.nextLine();
                return new MedunarodniBroj(drzava, medBroj);
            default:
        }
        return null;
    }

    private static void naSlovo() {
        System.out.println("Unesite slovo za koje zelite da pretražite imenik: ");
        String s = ulaz.nextLine();
        String lista = imenik.naSlovo(s.toCharArray()[0]);
        System.out.println(lista);
    }

    private static void izGrada() {
        System.out.println("Unesi ime grada: ");
        String grad = ulaz.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<String> imena = imenik.izGrada(g);
            System.out.println(imena);
        }catch (Exception e){
            System.out.println("Pogresan grad!");
        }
    }

    private static void izGradaBrojevi() {
        System.out.println("Unesite ime grada: ");
        String grad = ulaz.nextLine();
        try{
            Grad g = Grad.valueOf(grad);
            Set<TelefonskiBroj> brojevi = imenik.izGradaBrojevi(g);
            for(TelefonskiBroj broj : brojevi){
                System.out.println(broj.ispisi());
            }
        }catch (Exception e){
            System.out.println("Pogresan grad!");
        }
    }

    private static void dodajBroj() {
        System.out.println("Unesite ime: ");
        String ime = ulaz.nextLine();
        try {
            TelefonskiBroj broj = unesiBrojTelefona();
            imenik.dodaj(ime, broj);
        }catch(TelefonskiBrojException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Uneseni netacni podaci!");
        }
    }

    private static void popuniPodatke() throws TelefonskiBrojException {
        imenik.dodaj("Eldar", new FiksniBroj(Grad.SARAJEVO, "225-883"));
        imenik.dodaj("Dino", new FiksniBroj(Grad.ZENICA, "225-884"));
        imenik.dodaj("Amir", new MobilniBroj(61, "225-885"));
        imenik.dodaj("Chris", new MedunarodniBroj("+44", "7768878794"));
    }
}
