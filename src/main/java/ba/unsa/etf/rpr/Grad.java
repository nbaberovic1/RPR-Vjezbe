package ba.unsa.etf.rpr;

public enum Grad {

    TRAVNIK("030"), ORAŠJE("031"), ZENICA("032"), SARAJEVO("033"), LIVNO("034"),
    TUZLA("035"), MOSTAR("036"), BIHAĆ("037"), GORAŽDE("038"), ŠIROKI_BRIJEG("039"),
    BRČKO("049"), MRKONJIĆ_GRAD("050"), BANJA_LUKA("051"), PRIJEDOR("052"), DOBOJ("053"),
    ŠAMAC("054"), BIJELJINA("055"), ZVORNIK("056"), PALE("057"), FOČA("058"), TREBINJE("059");

    private String pozivniBroj;

    private Grad(String pozivniBroj){
        this.pozivniBroj = pozivniBroj;
    }

    public String getPozivniBroj(){
        return pozivniBroj;
    }
}
