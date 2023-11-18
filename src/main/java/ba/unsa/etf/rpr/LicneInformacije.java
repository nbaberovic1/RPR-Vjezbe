package ba.unsa.etf.rpr;

public class LicneInformacije implements SaPorukom {
    private String ime;
    private String prezime;

    public String getIme(){
        return this.ime;
    }

    public void setIme(String ime){
        this.ime = ime;
    }

    public String getPrezime(){
        return this.prezime;
    }

    public void setPrezime(String prezime){
        this.prezime = prezime;
    }

    @Override
    public String dajPoruku() {
        return new StringBuilder().append(ime).append(" ").append(prezime).toString();
    }
}
