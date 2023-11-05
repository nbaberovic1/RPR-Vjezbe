package ba.unsa.etf.rpr;

import java.util.*;

public class Imenik {
    //privatni atribut koji sadrzi imenik(ime i broj)
    private Map<String, TelefonskiBroj> imenik;

    public Imenik(){ //konstruktor
        this.imenik = new HashMap<>();
    }

    /**
     * dodaje novi kontakt u imenik
     * @param ime ime kontakta
     * @param broj broj kontakta
     */
    public void dodaj(String ime, TelefonskiBroj broj){
        this.imenik.put(ime, broj);
    }

    /**
     * vraca broj osobe cije je ime proslijedeno
     * @param ime ime osobe za koju se trazi broj
     * @return broj telefona
     */
    public String dajBroj(String ime){
        TelefonskiBroj broj = this.imenik.get(ime);
        if(broj != null){
            return broj.ispisi();
        }else {
            return null;
        }
    }

    /**
     * daje ime kontakta sa navedenim brojem
     * @param broj telefonski broj korisnika za kojeg zelimo naci ime
     * @return ime korisnika sa navedenim brojem
     */
    public String dajIme(TelefonskiBroj broj){
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(kontakt.getValue().ispisi().equals(broj.ispisi()))
                return kontakt.getKey();
        }
        return null;
    }

    /**
     * vraca listu kontakata cije ime pocinje proslijedenim slovom
     * @param s slovo po kojem zelimo izvrsiti pretragu kontakata
     * @return listu kontakata
     */
    public String naSlovo(char s){
        String lista = "";
        int redniBroj = 1;
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(kontakt.getKey().toUpperCase().charAt(0) == Character.toUpperCase(s)){ //kontakt.getKey().toUpperCase().startsWith(String.valueOf(s).toUpperCase())
                lista = new StringBuilder().append(lista).append(redniBroj).append(". ").append(kontakt.getKey()).
                        append(" - ").append(kontakt.getValue().ispisi()).append(System.lineSeparator()).toString();
                redniBroj = redniBroj + 1;
            }
        }
        return lista;
    }

    /**
     * vraca skup imena svih osoba koje zive u prooslijedenom gradu
     * @param g grad za koji zelimo pretraziti
     * @return skup imena
     */
    public Set<String> izGrada(Grad g){
        Set<String> imena = new TreeSet<String>();
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(jelIzGrada(kontakt.getValue(), g)){
                imena.add(kontakt.getKey());
            }
        }
        return imena;
    }

    /**
     * vraća brojeve koji se nalaze u imeniku, a pripadaju proslijeđenom gradu, i sortirani su na osnovu metode ispisi()
     * @param g grad za koji pretrazujemo
     * @return vraća skup brojeva
     */
    public Set<TelefonskiBroj> izGradaBrojevi(Grad g){
        Set<TelefonskiBroj> brojevi = new TreeSet<>(new Comparator<TelefonskiBroj>() {
            @Override
            public int compare(TelefonskiBroj o1, TelefonskiBroj o2) {
                return o1.ispisi().compareTo(o2.ispisi());
            }
        });

        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            if(jelIzGrada(kontakt.getValue(), g)){
                brojevi.add(kontakt.getValue());
            }
        }
        return brojevi;
    }

    /**
     * pomoćna metoda koja provjerava da li prolijeđeni broj pripada proslijeđenom gradu
     * @param broj telefonski broj
     * @param grad
     * @return vraća istinitosnu vrijednost koja pretsavalja odgovor da li navedeni broj pripada navedenom gradu
     */
    private boolean jelIzGrada(TelefonskiBroj broj, Grad grad){
        if(broj instanceof FiksniBroj){
            return grad.equals(((FiksniBroj) broj).getGrad());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int redniBroj = 1;
        for(Map.Entry<String, TelefonskiBroj> kontakt : this.imenik.entrySet()){
            builder.append(redniBroj).append(". ").append(kontakt.getKey()).
                    append(" - ").append(kontakt.getValue().ispisi()).append(System.lineSeparator());
            redniBroj = redniBroj + 1;
        }
        return builder.toString();
    }

    public void setImenik(Map<String, TelefonskiBroj> imenik) {
        this.imenik = imenik;
    }
}
