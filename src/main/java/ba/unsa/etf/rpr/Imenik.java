package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Map;

public class Imenik {
    private Map<String, TelefonskiBroj> imenik;

    public Imenik(){
        imenik = new HashMap<>();
    }

    public void dodaj(String ime, TelefonskiBroj broj){
        this.imenik.put(ime, broj);
    }

    public String dajBroj(String ime){
        TelefonskiBroj broj = this.imenik.get(ime);
        if(broj != null) {
            return broj.ispisi();
        } else {
            return null;
        }
    }


}
