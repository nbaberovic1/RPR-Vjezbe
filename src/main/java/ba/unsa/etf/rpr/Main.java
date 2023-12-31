package ba.unsa.etf.rpr;

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



    public static void main(String[] args) throws SQLException {
        geo = GeografijaDAO.getInstance();

        System.out.println(ispisiGradove());

        glavniGrad();
    }

}