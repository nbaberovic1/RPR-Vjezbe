package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        GeografijaDAO geo = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = geo.gradovi();
        for(Grad g : gradovi) {
            System.out.println(g.getNaziv());
        }
        Grad g = geo.glavniGrad("Austrija");
        Drzava d = g.getDrzava();
        System.out.println(System.lineSeparator() + g.getNaziv());
        System.out.println(d.getNaziv() + System.lineSeparator());

        geo.obrisiDrzavu("Velika Britanija");

        gradovi = geo.gradovi();
        for(Grad grad : gradovi) {
            System.out.println(grad.getNaziv());
        }
    }
}