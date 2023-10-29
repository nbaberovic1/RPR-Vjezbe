package ba.unsa.etf.rpr;

public class Racun {

    private Long brojRacuna;
    private Osoba korisnikRacuna;
    private boolean odobrenjePrekoracenja;
    private Double stanjeRacuna;
    private Double prekoracenje;

    public Racun(Long brojRacuna, Osoba korisnikRacuna){
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
    }

    private boolean provjeriOdobrenjePrekoracenja(Double iznos){
        return this.odobrenjePrekoracenja && this.prekoracenje > iznos;
    }

    public boolean izvrsiUplatu(Double iznos){
        this.stanjeRacuna = this.stanjeRacuna + iznos;
        return true;
    }

    public  boolean izvrsiIsplatu(Double iznos){
        if(this.stanjeRacuna + this.prekoracenje < iznos)
            return false;
        this.stanjeRacuna = this.stanjeRacuna - iznos;
        return true;
    }

    public void odobriPrekoracenje(Double iznosPrekoracenja){
        this.odobrenjePrekoracenja = true;
        this.prekoracenje = iznosPrekoracenja;
    }
}
