package ba.unsa.etf.rpr;

import java.util.*;

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

    public String dajIme(TelefonskiBroj broj) {
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(kontakt.getValue().ispisi().equals(broj.ispisi())){
                return kontakt.getKey();
            }
        }
        return null;
    }

    public String naSlovo(char s){
        String lista = "";
        int redniBroj = 1;
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(kontakt.getKey().toLowerCase().startsWith(String.valueOf(s).toLowerCase())){
                lista = new StringBuilder().append(lista).append(redniBroj).append(". ").append(kontakt.getKey())
                        .append(" - ").append(kontakt.getValue().ispisi()).append(System.lineSeparator()).toString();
                redniBroj = redniBroj + 1;
            }
        }
        return lista;
    }

    public Set<String> izGrada(Grad g){
        Set<String> imena = new TreeSet<>();
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(jeLiIzGrada(kontakt.getValue(), g)){
                imena.add(kontakt.getKey());
            }
        }
        return imena;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(Grad g){
        Set<TelefonskiBroj> brojevi = new TreeSet<>(new Comparator<TelefonskiBroj>() {
            @Override
            public int compare(TelefonskiBroj o1, TelefonskiBroj o2) {
                return o1.ispisi().compareTo(o2.ispisi());
            }
        });

        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(jeLiIzGrada(kontakt.getValue(), g)){
                brojevi.add(kontakt.getValue());
            }
        }
        return brojevi;
    }

    public boolean jeLiIzGrada(TelefonskiBroj broj, Grad g){
        if(broj instanceof FiksniBroj){
            return g.equals(((FiksniBroj) broj).getGrad());
        }
        return false;
    }

}
