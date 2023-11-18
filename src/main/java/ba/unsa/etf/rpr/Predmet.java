package ba.unsa.etf.rpr;

import java.util.List;

public class Predmet implements SaPorukom{
    private String naziv;
    private String opis;

    private List<Ocjena> ocjene;

    public String getNaziv(){
        return this.naziv;
    }

    public void setNaziv(String naziv){
        this.naziv = naziv;
    }

    public String getOpis(){
        return this.opis;
    }

    public void setOpis(String opis){
        this.opis = opis;
    }

    @Override
    public String dajPoruku() {
        return new StringBuilder().append(naziv).append(" ").append(opis).toString();
    }
}
