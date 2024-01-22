package ba.unsa.etf.rpr.lv10_11;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeografijaDAOTest {

    private static final GeografijaDAO geografijaDAO = GeografijaDAO.getInstance();

    @Test
    @Order(1)
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
    @Order(2)
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
    @Order(3)
    public void glavniGradTest3() {
        assertEquals(new Grad(2, "London", 8538700, new Drzava(2, "Velika Britanija", 2)), geografijaDAO.glavniGrad("Velika Britanija"));
        assertEquals(new Grad(1, "Pariz", 2193031, new Drzava(1, "Francuska", 1)), geografijaDAO.glavniGrad("Francuska"));
        assertEquals(new Grad(4, "Beč", 1800000, new Drzava(3, "Austrija", 4)), geografijaDAO.glavniGrad("Austrija"));
        assertEquals(new Grad(6, "Sarajevo", 274879, new Drzava(4, "BiH", 6)), geografijaDAO.glavniGrad("BiH"));
    }

    @Test
    @Order(4)
    public void nadjiDrzavuTest4() {
        assertEquals(new Drzava(1, "Francuska", 1), geografijaDAO.nadjiDrzavu("Francuska"));
        assertEquals(new Drzava(2, "Velika Britanija", 2), geografijaDAO.nadjiDrzavu("Velika Britanija"));
        assertEquals(new Drzava(3, "Austrija", 4), geografijaDAO.nadjiDrzavu("Austrija"));
        assertEquals(new Drzava(4, "BiH", 6), geografijaDAO.nadjiDrzavu("BiH"));
    }

    @Test
    @Order(5)
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
    @Order(6)
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

    @Test
    @Order(7)
    public void dodajDrzavuTest7() {
        geografijaDAO.dodajDrzavu(new Drzava(5, "Njemačka", 0));

        ArrayList<Drzava> drzave = geografijaDAO.listDrzave();
        assertEquals(4, drzave.size());

        Drzava novaDrzava = null;
        for(Drzava d : drzave) {
            if(d.getId() == 5) {
                novaDrzava = d;
                break;
            }
        }
        assertEquals(new Drzava(5, "Njemačka", 0), novaDrzava);
    }

    @Test
    @Order(8)
    public void izmijeniGradTest8() {
        geografijaDAO.izmijeniGrad(new Grad(7, "Berlin", 3600000, new Drzava(5, "Njemačka", 0)));

        ArrayList<Grad> gradovi = geografijaDAO.listGradovi();
        assertEquals(5, gradovi.size());

        Grad grad = null;
        for(Grad g : gradovi) {
            if(g.getId() == 7) {
                grad = g;
                break;
            }
        }
        assertNotNull(grad);
        assertEquals(new Grad(7, "Berlin", 3600000, new Drzava(5, "Njemačka", 0)), grad);
    }

    @Test
    @Order(9)
    public void obrisiGradTest9() {
        geografijaDAO.obrisiGrad(new Grad(7, "Berlin", 3600000, new Drzava(5, "Njemačka", 0)));

        ArrayList<Grad> gradovi = geografijaDAO.listGradovi();
        assertEquals(4, gradovi.size());

        Grad grad = null;
        for(Grad g : gradovi) {
            if(g.getId() == 7) {
                grad = g;
                break;
            }
        }
        assertNull(grad);
    }
}