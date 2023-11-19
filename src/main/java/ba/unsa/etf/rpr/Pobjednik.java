package ba.unsa.etf.rpr;

public class Pobjednik {
    private String ime;
    private String prezime;
    private int brojZnakova;
    private Kolekcija kolekcijaImena;

    public Pobjednik(Kolekcija kolekcija){
        this.kolekcijaImena = kolekcija;
        this.ime = kolekcija.getImeNajduzegImenaIPrezimena();
        this.prezime = kolekcija.getPrezimeNajduzegImenaIPrezimena();
        this.brojZnakova = this.ime.length();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getBrojZnakova() {
        return brojZnakova;
    }

    public Kolekcija getKolekcijaImena() {
        return kolekcijaImena;
    }
}
