package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class KolekcijaImenaIPrezimena implements Kolekcija{
    private ArrayList<String> imena;
    private ArrayList<String> prezimena;

    public KolekcijaImenaIPrezimena(){
        imena = new ArrayList<>();
        prezimena = new ArrayList<>();
    }

    public int getIndexNajduzegPara(){
        int index = -1;
        String najduziPar = "";
        for(int i=0; i<imena.size(); i = i + 1){
            String trenutni = new StringBuilder().append(imena.get(i)).append(prezimena.get(i)).toString();
            if(trenutni.length() > najduziPar.length()) {
                najduziPar = trenutni;
                index = i;
            }
        }
        return index;
    }

    public String getImeIPrezime(int i){
        return new StringBuilder().append(imena.get(i)).append(" ").append(prezimena.get(i)).toString();
    }

    public void setImeIPrezime(String ime, String prezime){
        this.imena.add(ime);
        this.prezimena.add(prezime);
    }

    @Override
    public String getImeNajduzegImenaIPrezimena() {
        return imena.get(this.getIndexNajduzegPara());
    }

    @Override
    public String getPrezimeNajduzegImenaIPrezimena() {
        return prezimena.get(this.getIndexNajduzegPara());
    }
}
