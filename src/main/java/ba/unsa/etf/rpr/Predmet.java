package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Predmet implements SaPorukom{
    private String naziv;
    private String opis;

    private ArrayList<Ocjena> ocjene;

    public Predmet(){
        this.ocjene = new ArrayList<Ocjena>();
    }

    public void dodajOcjenu(Ocjena ocjena){
        this.ocjene.add(ocjena);
    }

    public ArrayList<Ocjena> getOcjene(){
        return this.ocjene;
    }

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
