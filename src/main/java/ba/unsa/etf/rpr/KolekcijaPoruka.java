package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class KolekcijaPoruka {
    private ArrayList<String> poruke;

    public<Tip> KolekcijaPoruka(List<Tip> lista){
        poruke = new ArrayList<String>();

    }

    public ArrayList<String> getPoruke(){
        return this.poruke;
    }
}
