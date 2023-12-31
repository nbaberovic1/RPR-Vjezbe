package ba.unsa.etf.rpr;

import java.awt.dnd.DragGestureEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static GeografijaDAO geo;

    public static Scanner ulaz = new Scanner(System.in);

    public static String ispisiGradove() {
        String gradoviZaIspis = "";
        try {
            ArrayList<Grad> gradovi = geo.gradovi();
            for (Grad g : gradovi) {
                gradoviZaIspis = new StringBuilder().append(gradoviZaIspis).append(g.getNaziv()).append(" (")
                        .append(g.getDrzava().getNaziv()).append(") - ").append(g.getBrojStanovnika()).append(System.lineSeparator()).toString();
            }
        } catch (SQLException e) {
            System.out.println("Greska!");
            return null;
        }
        return gradoviZaIspis;
    }

    public static void glavniGrad() {
        ulaz.nextLine();
        System.out.println("Unesi naziv drzave: ");
        String imeDrzave = ulaz.nextLine();
        Grad grad = geo.glavniGrad(imeDrzave);
        if(grad != null) {
            System.out.println("Glavni grad drzave " + imeDrzave + " je " + grad.getNaziv() );
        } else {
            System.out.println("Nepostojeća država");
        }
    }

    public static void brisanjeDrzave () {
        ulaz.nextLine();
        System.out.println("Unesi naziv drzave: ");
        String imeDrzave = ulaz.nextLine();
        geo.obrisiDrzavu(imeDrzave);
    }

    public static void dodavanjeGrada() {
        ulaz.nextLine();
        System.out.println("Unesi id grada: ");
        int id = ulaz.nextInt();
        ulaz.nextLine();
        System.out.println("Unesi naziv grada: ");
        String naziv = ulaz.nextLine();
        System.out.println("Unesi broj stanovnika grada: ");
        int brojStanovnika = ulaz.nextInt();
        ulaz.nextLine();
        System.out.println("Unesi naziv drzave u kojoj se grad nalazi: ");
        String nazivDrzave = ulaz.nextLine();

        Drzava drzava = geo.nadjiDrzavu(nazivDrzave);

        if(nazivDrzave.equals("") || drzava != null) {
            Grad grad = new Grad(id, naziv, brojStanovnika, drzava);
            geo.dodajGrad(grad);
        } else {
            System.out.println("Nema navedene drzave u bazi!");
        }
    }

    public static void dodavanjeDrzave() {
        ulaz.nextLine();
        System.out.println("Unesi id drzave: ");
        int id = ulaz.nextInt();
        ulaz.nextLine();
        System.out.println("Unesi naziv drzave: ");
        String naziv = ulaz.nextLine();
        System.out.println("Unesi id glavnog grada drzave: ");
        int idGlavnogGrada = ulaz.nextInt();

        Drzava drzava = new Drzava(id, naziv, idGlavnogGrada);
        geo.dodajDrzavu(drzava);
    }

    public static void izmjenaGrada() {
        ulaz.nextLine();
        System.out.println("Unesi id grada kojeg zelis promijeniti: ");
        int id = ulaz.nextInt();
        ulaz.nextLine();
        System.out.println("Unesi novi naziv grada: ");
        String naziv = ulaz.nextLine();
        System.out.println("Unesi novi broj stanovnika grada: ");
        int brojStanovnika = ulaz.nextInt();
        ulaz.nextLine();
        System.out.println("Unesi novi naziv drzave u kojoj se grad nalazi: ");
        String nazivDrzave = ulaz.nextLine();

        Drzava drzava = geo.nadjiDrzavu(nazivDrzave);

        if(nazivDrzave.equals("") || drzava != null) {
            Grad grad = new Grad(id, naziv, brojStanovnika, drzava);
            geo.izmijeniGrad(grad);
        } else {
            System.out.println("Nema navedene drzave u bazi!");
        }
    }

    public static void trazenjeDrzave() {
        ulaz.nextLine();
        System.out.println("Unesi naziv drzave za koju zelis detaljnije podatke: ");
        String nazivDrzave = ulaz.nextLine();

        Drzava drzava = geo.nadjiDrzavu(nazivDrzave);

        if(drzava != null) {
            Grad glavniGrad = geo.glavniGrad(nazivDrzave);
            if(glavniGrad != null) {
                System.out.println(drzava.getId() + " " + drzava.getNaziv() + " " + drzava.getGlavniGrad() + " " + glavniGrad.getNaziv());
            } else {
                System.out.println(drzava.getId() + " " + drzava.getNaziv());
            }
        } else {
            System.out.println("Nema trazene drzave u bazi!");
        }
    }

    public static void main(String[] args) throws SQLException {
        geo = GeografijaDAO.getInstance();
        while(true) {
            System.out.println("""
                    Odaberite jednu od ponudjenih opcija unoseci redni broj opcije: 
                    1. Ispisivanje gradova
                    2. Trazenje glavnog grada drzave
                    3. Brisanje drzave
                    4. Dodavanje grada
                    5. Dodavanje drzave
                    6. Izmjena grada
                    7. Trazenje drzave
                    """);

            int izbor = ulaz.nextInt();

            switch (izbor) {
                case 1:
                    System.out.println(ispisiGradove());
                    break;
                case 2:
                    glavniGrad();
                    break;
                case 3:
                    brisanjeDrzave();
                    break;
                case 4:
                    dodavanjeGrada();
                    break;
                case 5:
                    dodavanjeDrzave();
                    break;
                case 6:
                    izmjenaGrada();
                    break;
                case 7:
                    trazenjeDrzave();
                    break;
                default:
                    System.out.println("Izbor opcije nije validan!");
            }
        }
    }

}