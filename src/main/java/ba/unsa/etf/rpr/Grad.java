package ba.unsa.etf.rpr;

/**
 * klasa koja predstavlja grad i njegov pozivni broj
 */
public enum Grad { TRAVNIK("030"), ORAŠJE("031"), ZENICA("032"), SARAJEVO("033"), LIVNO("034"),
    TUZLA("035"), MOSTAR("036"), BIHAĆ("037"), GORAŽDE("038"), ŠIROKI_BRIJEG("039"),
    BRČKO("049"), MRKONJIĆ_GRAD("050"), BANJA_LUKA("051"), PRIJEDOR("052"), DOBOJ("053"),
    ŠAMAC("054"), BIJELJINA("055"), ZVORNIK("056"), PALE("057"), FOČA("058"), TREBINJE("059");

    private String pozivniBroj;  //string koji sadrzi pozivni broj u obliku xxx/

    //metoda koja vraća pozivni broj
    public String getPozivniBroj() {
        return pozivniBroj;
    }

    public static Grad izPozivnog(String pozivni){
        for(Grad grad : Grad.values()){
            if(grad.getPozivniBroj().equals(pozivni)) {
                return grad;
            }
        }
        return null;
    }

    //privatni konstuktor koji inicijalizira privatni atribut pozivniBroj
    Grad(String pozivniBroj){
        this.pozivniBroj = pozivniBroj;
    }
}