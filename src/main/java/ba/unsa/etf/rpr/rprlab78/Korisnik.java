package ba.unsa.etf.rpr.rprlab78;

import javafx.beans.property.SimpleStringProperty;

public class Korisnik {
    private SimpleStringProperty ime;
    private SimpleStringProperty prezime;
    private SimpleStringProperty email;
    private SimpleStringProperty korisnickoIme;
    private SimpleStringProperty lozinka;

    public Korisnik () {}

    public Korisnik (SimpleStringProperty ime, SimpleStringProperty prezime, SimpleStringProperty email,
                     SimpleStringProperty korisnickoIme, SimpleStringProperty lozinka){
        this.ime = new SimpleStringProperty(ime.get());;
        this.prezime = new SimpleStringProperty(prezime.get());
        this.email =  new SimpleStringProperty(email.get());
        this.korisnickoIme = new SimpleStringProperty(korisnickoIme.get());
        this.lozinka = new SimpleStringProperty(lozinka.get());
    }

    public Korisnik (String ime, String prezime, String email, String korisnickoIme, String lozinka) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.email = new SimpleStringProperty(email);
        this.korisnickoIme = new SimpleStringProperty(korisnickoIme);
        this.lozinka = new SimpleStringProperty(lozinka);
    }

    public final String getIme() {
        return ime.get();
    }

    public final SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(final String ime) {
        this.ime.set(ime);
    }

    public final String getPrezime() {
        return this.prezime.get();
    }

    public final SimpleStringProperty prezimeProperty() {
        return this.prezime;
    }

    public void setPrezime(final String prezime) {
        this.prezime.set(prezime);
    }

    public final String getEmail() {
        return this.email.get();
    }

    public final SimpleStringProperty emailProperty() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email.set(email);
    }

    public final String getKorisnikoIme() {
        return this.korisnickoIme.get();
    }

    public final SimpleStringProperty korisnickoImeProperty() {
        return this.korisnickoIme;
    }

    public void setKorisnickoIme(final String korisnickoIme){
        this.korisnickoIme.set(korisnickoIme);
    }

    public final String getLozinka() {
        return this.lozinka.get();
    }

    public final SimpleStringProperty lozinkaProperty() {
        return this.lozinka;
    }

    public void setLozinka(final String lozinka) {
        this.lozinka.set(lozinka);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.ime.get()).append(" ").append(this.prezime.get()).toString();
    }
}
