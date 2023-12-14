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
        assertEquals("Korisnik 1", listaKorisnika.get(0).toString());
        assertEquals("Korisnik 2", listaKorisnika.get(1).toString());
        assertEquals("korisnik1@live.com", listaKorisnika.get(0).getEmail());
        assertEquals("korisnik2@live.com", listaKorisnika.get(1).getEmail());
        assertEquals("korisnik1", listaKorisnika.get(0).getKorisnickoIme());
        assertEquals("korisnik2", listaKorisnika.get(1).getKorisnickoIme());
        assertEquals("kor1", listaKorisnika.get(0).getLozinka());
        assertEquals("kor2", listaKorisnika.get(1).getLozinka());
    }

    @Test
    public void TrenutniKorisnikTest() {
        model.setTrenutniKorisnik(model.getKorisnici().get(0));
        assertEquals(model.getKorisnici().get(0), model.getTrenutniKorisnik());
    }

    @Test
    public void addKorisnikTest() {
        Korisnik korisnik = new Korisnik("Novi", "Korisnik", "novi.korisnik@live.com", "NoviK", "Novi123");
        model.addKorisnik(korisnik);
        assertEquals(korisnik, model.getKorisnici().get(2));
    }

    @Test
    public void trenutniKorisnikPropertyTest() {
        Korisnik korisnik = new Korisnik("Novi", "Korisnik", "novi.korisnik@live.com", "NoviK", "Novi123");
        model.addKorisnik(korisnik);
        model.setTrenutniKorisnik(korisnik);
        assertEquals(korisnik, model.trenutniKorisnikProperty().get());
    }

    @Test
    public void TrenutniKorisnikTest2() {
        Korisnik korisnik = new Korisnik("Novi", "Korisnik", "novi.korisnik@live.com", "NoviK", "Novi123");
        model.addKorisnik(korisnik);
        model.setTrenutniKorisnik(korisnik);
        model.getKorisnici().remove(0);
        assertEquals(korisnik, model.getTrenutniKorisnik());
    }
}
