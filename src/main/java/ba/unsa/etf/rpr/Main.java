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
        System.out.println(System.lineSeparator() + g.getNaziv());
    }
}