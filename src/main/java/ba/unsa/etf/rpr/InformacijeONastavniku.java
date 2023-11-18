package ba.unsa.etf.rpr;

import java.util.List;

public class InformacijeONastavniku extends LicneInformacije implements SaPorukom {
    private String titula;
    private List<Ocjena> ocjene;

    public String getTitula(){
        return this.titula;
    }

    public void setTitula(String titula){
        this.titula = titula;
    }

    @Override
    public String dajPoruku(){
        return new StringBuilder().append(titula).append(" ").append(super.dajPoruku()).toString();
    }
}
