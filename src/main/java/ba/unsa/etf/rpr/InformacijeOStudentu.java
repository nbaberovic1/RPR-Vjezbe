package ba.unsa.etf.rpr;

public class InformacijeOStudentu extends LicneInformacije implements SaPorukom{
    private String godinaStudija;
    private String brojIndexa;

    public String getGodinaStudija(){
        return this.godinaStudija;
    }

    public void setGodinaStudija(String godinaStudija){
        this.godinaStudija = godinaStudija;
    }

    public String getBrojIndexa(){
        return this.brojIndexa;
    }

    public void setBrojIndexa(String brojIndexa){
        this.brojIndexa = brojIndexa;
    }

    @Override
    public String dajPoruku(){
        return new StringBuilder().append(super.dajPoruku()).append(" ").append(godinaStudija).append(" ").append(brojIndexa).toString();
    }
}
