package ba.unsa.etf.rpr.rprlab78;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {

    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private ObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    public Korisnik getTrenutniKorisnik() {
        return this.trenutniKorisnik.get();
    }

    public ObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return this.trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik korisnik) {
        this.trenutniKorisnik.set(korisnik);
    }

    public ObservableList<Korisnik> getKorisnici() {
        return this.korisnici;
    }

    public void addKorisnik(Korisnik korisnik) {
        korisnici.add(korisnik);
    }

    public void napuni() {
        korisnici.add(new Korisnik("Korisnik", "1", "korisnik1@live.com", "korisnik1", "kor1"));
        korisnici.add(new Korisnik("Korisnik", "2", "korisnik2@live.com", "korisnik2", "kor2"));
    }
}
