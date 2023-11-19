package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class KolekcijaImena {

    private ArrayList<String> imena;

    public KolekcijaImena(){
        imena = new ArrayList<>();
    }

    public String getNajduzeIme(){
        String najduzeIme = "";
        for(String ime : imena){
            if(ime.length() > najduzeIme.length()) najduzeIme = ime;
        }
        return najduzeIme;
    }

    public void dodajIme(String ime){
        String[] imeIPrezime = ime.split("\\s+");
        if(imeIPrezime.length != 2) throw new IllegalArgumentException("Neodgovarajuci format imena!");
        this.imena.add(ime);
    }

    public ArrayList<String> getImena(){
        return this.imena;
    }
}
