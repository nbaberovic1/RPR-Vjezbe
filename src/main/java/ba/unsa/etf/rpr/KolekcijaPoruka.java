package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class KolekcijaPoruka{
    /* PRVA IMPLEMENTACIJA
    private ArrayList<String> poruke;

    public<Tip extends Predstavljiv> KolekcijaPoruka(List<Tip> lista){
        poruke = new ArrayList<String>();
        for(Tip element : lista){
            poruke.add(element.predstavi());
        }
    }

    public ArrayList<String> getPoruke(){
        return this.poruke;
    }
       DRUGA IMPLEMENTACIJA */

    private ArrayList<SaPorukom> poruke;

    public KolekcijaPoruka(List<SaPorukom> lista){
        poruke = new ArrayList<>(lista);
    }

    public ArrayList<String> getPoruke(){
        ArrayList<String> porukeIzKolekcije = new ArrayList<>();
        for(SaPorukom x : poruke){
            porukeIzKolekcije.add(x.dajPoruku());
        }
        return porukeIzKolekcije;
    }
}
