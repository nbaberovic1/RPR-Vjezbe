package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class KolekcijaPoruka {
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
}
