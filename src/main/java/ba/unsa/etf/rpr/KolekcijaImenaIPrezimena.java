package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class KolekcijaImenaIPrezimena {
    private ArrayList<String> imena;
    private ArrayList<String> prezimena;

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
}
