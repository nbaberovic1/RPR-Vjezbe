package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class InformacijeONastavniku extends LicneInformacije implements SaPorukom {
    private String titula;
    private ArrayList<Ocjena> ocjene;

    public InformacijeONastavniku(){
        this.ocjene = new ArrayList<Ocjena>();
    }

    public void dodajOcjenu(Ocjena ocjena){
        this.ocjene.add(ocjena);
    }

    public ArrayList<Ocjena> getOcjene(){
        return this.ocjene;
    }

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
