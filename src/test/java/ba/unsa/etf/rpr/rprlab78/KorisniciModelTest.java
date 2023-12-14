package ba.unsa.etf.rpr.rprlab78;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KorisniciModelTest {

    private static KorisniciModel model = new KorisniciModel();

    @BeforeEach
    private void modelNapuni(){
        model = new KorisniciModel();
        model.napuni();
    }

    @Test
    public void getKorisniciTest() {
        ObservableList<Korisnik> listaKorisnika = model.getKorisnici();
        assertEquals(2, listaKorisnika.size());
        assertEquals("Korisnik", listaKorisnika.get(0).getIme());
        assertEquals("Korisnik", listaKorisnika.get(1).getIme());
        assertEquals("1", listaKorisnika.get(0).getPrezime());
        assertEquals("2", listaKorisnika.get(1).getPrezime());
        assertEquals("korisnik1@live.com", listaKorisnika.get(0).getEmail());
        assertEquals("korisnik2@live.com", listaKorisnika.get(1).getEmail());
    }

    @Test
    public void TrenutniKorisnikTest() {
        model.setTrenutniKorisnik(model.getKorisnici().get(0));
        assertEquals(model.getKorisnici().get(0), model.getTrenutniKorisnik());
        assertEquals(model.getKorisnici().get(0).toString(), model.getTrenutniKorisnik().toString());
    }

    @Test
    public void addKorisnikTest() {
        Korisnik korisnik = new Korisnik("Novi", "Korisnik", "novi.korisnik@live.com", "NoviK", "Novi123");
        model.addKorisnik(korisnik);
        assertEquals(korisnik, model.getKorisnici().get(2));
        assertEquals(korisnik.toString(), model.getKorisnici().get(2).toString());
    }

}
