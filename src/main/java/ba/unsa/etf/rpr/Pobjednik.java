package ba.unsa.etf.rpr;

public class Pobjednik {
    private String ime;
    private String prezime;
    private int brojZnakova;
    private KolekcijaImena kolekcijaImena;

    public Pobjednik(KolekcijaImena kolekcijaImena){
        this.kolekcijaImena = kolekcijaImena;
        String[] imeIPrezime = this.kolekcijaImena.getNajduzeIme().split("\\s+");
        this.ime = imeIPrezime[0];
        this.prezime = imeIPrezime[1];
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

    public KolekcijaImena getKolekcijaImena() {
        return kolekcijaImena;
    }
}
