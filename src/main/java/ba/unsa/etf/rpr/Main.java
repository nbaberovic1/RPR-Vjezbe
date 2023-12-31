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
        System.out.println("Unesi naziv drzave: ");
        String imeDrzave = ulaz.nextLine();
        geo.obrisiDrzavu(imeDrzave);
    }

    public static void dodavanjeGrada() {
        System.out.println("Unesi id grada: ");
        int id = ulaz.nextInt();
        System.out.println("Unesi naziv grada: ");
        String naziv = ulaz.nextLine();
        System.out.println("Unesi broj stanovnika grada: ");
        int brojStanovnika = ulaz.nextInt();
        System.out.println("Unesi naziv drzave u kojoj se grad nalazi: ");
        String nazivDrzave = ulaz.nextLine();

        Drzava drzava = geo.nadjiDrzavu(nazivDrzave);

        if(nazivDrzave.equals("") || drzava != null) {
            Grad grad = new Grad(id, naziv, brojStanovnika, geo.nadjiDrzavu(nazivDrzave));
        } else {
            System.out.println("Nema navedene drzave u bazi!");
        }
    }

    public static void main(String[] args) throws SQLException {
        geo = GeografijaDAO.getInstance();

        System.out.println(ispisiGradove());

        glavniGrad();
    }

}