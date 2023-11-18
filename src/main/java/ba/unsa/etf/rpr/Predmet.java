package ba.unsa.etf.rpr;

public class Predmet {
    private String naziv;
    private String opis;

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
}
