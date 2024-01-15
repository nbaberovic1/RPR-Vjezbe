package ba.unsa.etf.rpr.lv10_11;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeografijaDAOTest {

    private static final GeografijaDAO geografijaDAO = GeografijaDAO.getInstance();

    @Test
    public void listGradoviTest1() {
        ArrayList<Grad> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Grad(2, "London", 8538700, new Drzava(2, "Velika Britanija", 2)));
        ocekivanaLista.add(new Grad(1, "Pariz", 2193031, new Drzava(1, "Francuska", 1)));
        ocekivanaLista.add(new Grad(4, "Beč", 1800000, new Drzava(3, "Austrija", 4)));
        ocekivanaLista.add(new Grad(3, "Manchester", 520000, new Drzava(2, "Velika Britanija", 2)));
        ocekivanaLista.add(new Grad(5, "Graz", 280800, new Drzava(3, "Austrija", 4)));
        ocekivanaLista.add(new Grad(6, "Sarajevo", 274879, new Drzava(4, "BiH", 6)));

        ArrayList<Grad> dobivenaLista = geografijaDAO.listGradovi();

        assertEquals(ocekivanaLista, dobivenaLista);
    }

    @Test
    public void listDrzaveTest2() {
        ArrayList<Drzava> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Drzava(1, "Francuska", 1));
        ocekivanaLista.add(new Drzava(2, "Velika Britanija", 2));
        ocekivanaLista.add(new Drzava(3, "Austrija", 4));
        ocekivanaLista.add(new Drzava(4, "BiH", 6));

        ArrayList<Drzava> dobivenaLista = geografijaDAO.listDrzave();

        assertEquals(ocekivanaLista, dobivenaLista);
    }

    @Test
    public void glavniGradTest3() {
        assertEquals(new Grad(2, "London", 8538700, new Drzava(2, "Velika Britanija", 2)), geografijaDAO.glavniGrad("Velika Britanija"));
        assertEquals(new Grad(1, "Pariz", 2193031, new Drzava(1, "Francuska", 1)), geografijaDAO.glavniGrad("Francuska"));
        assertEquals(new Grad(4, "Beč", 1800000, new Drzava(3, "Austrija", 4)), geografijaDAO.glavniGrad("Austrija"));
        assertEquals(new Grad(6, "Sarajevo", 274879, new Drzava(4, "BiH", 6)), geografijaDAO.glavniGrad("BiH"));
    }

    @Test
    public void nadjiDrzavuTest4() {
        assertEquals(new Drzava(1, "Francuska", 1), geografijaDAO.nadjiDrzavu("Francuska"));
        assertEquals(new Drzava(2, "Velika Britanija", 2), geografijaDAO.nadjiDrzavu("Velika Britanija"));
        assertEquals(new Drzava(3, "Austrija", 4), geografijaDAO.nadjiDrzavu("Austrija"));
        assertEquals(new Drzava(4, "BiH", 6), geografijaDAO.nadjiDrzavu("BiH"));
    }

    @Test
    public void obrisiDrzavuTest5() throws SQLException {
        geografijaDAO.obrisiDrzavu("Austrija");

        ArrayList<Drzava> drzave = geografijaDAO.listDrzave();
        assertEquals(3, drzave.size());
        assertNull(geografijaDAO.nadjiDrzavu("Austrija"));

        ArrayList<Grad> gradoviNakonBrisanja = geografijaDAO.listGradovi();
        boolean obrisaniIGradovi = true;
        for(Grad g : gradoviNakonBrisanja) {
            if(g.getDrzava() != null && g.getDrzava().getNaziv().equals("Austrija")){
                obrisaniIGradovi = false;
                break;
            }
        }
        assertTrue(obrisaniIGradovi);
    }

    @Test
    public void dodajGradTest6() {
        geografijaDAO.dodajGrad(new Grad(7, "Visoko", 39838, new Drzava(4, "BiH", 6)));

        ArrayList<Grad> gradovi = geografijaDAO.listGradovi();
        assertEquals(5, gradovi.size());

        Grad noviGrad = null;
        for(Grad g : gradovi) {
            if(g.getId() == 7) {
                noviGrad = g;
                break;
            }
        }
        assertEquals(new Grad(7, "Visoko", 39838, new Drzava(4, "BiH", 6)), noviGrad);
    }
}