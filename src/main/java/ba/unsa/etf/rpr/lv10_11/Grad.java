package ba.unsa.etf.rpr.lv10_11;

public class Grad {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;

    public Grad () {
        this.id = 0;
        this.naziv = "";
        this.brojStanovnika = 0;
        this.drzava = null;
    }

    public Grad (int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
        this.brojStanovnika = brojStanovnika;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
}
