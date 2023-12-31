package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        GeografijaDAO geo = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = geo.gradovi();
        for(Grad g : gradovi) {
            System.out.println(g.getNaziv() + " " + g.getDrzava().getNaziv());
        }
        System.out.println(System.lineSeparator());
/*
        Grad g = geo.glavniGrad("Austrija");
        Drzava d = g.getDrzava();
        System.out.println(System.lineSeparator() + g.getNaziv());
        System.out.println(d.getNaziv() + System.lineSeparator());

        geo.obrisiDrzavu("Velika Britanija");*/

        //geo.dodajGrad(new Grad(7, "Hamburg", 1786448, null));

        //geo.dodajGrad(new Grad(6, "Nice", 348721, new Drzava(1, "Francuska", 1)));

        //geo.dodajDrzavu(new Drzava(4, "BiH", 0));

        //geo.izmijeniGrad(new Grad(4, "Beč", 225883, new Drzava(3, "Austrija", 4)));

        /*
        gradovi = geo.gradovi();
        for(Grad g1 : gradovi) {
            System.out.println(g1.getNaziv() + " " + g1.getBrojStanovnika());
        }
         */

        Drzava drzava = geo.nadjiDrzavu("BiH");

        System.out.println(drzava.getId() + " " + drzava.getNaziv() + " " + drzava.getGlavniGrad());

    }

}