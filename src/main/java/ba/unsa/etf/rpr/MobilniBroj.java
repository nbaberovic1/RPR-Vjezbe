package ba.unsa.etf.rpr;

import java.util.Objects;

/**
 * klasa koja predstavlja mobilni broj
 *
 */
public class MobilniBroj extends TelefonskiBroj{
    private int mobilnaMreza;
    private String broj; //cuva mobilni broj u obliku xxx/xxx-xxx

    /**
     * konstruktor klase MobilniBroj, prima dva parametra
     * @param mobilnaMreza - predstavlja drugu i trecu cifru pozivnog broja
     * @param broj - mobilni broj u obliku xxx-xxx
     */
    public MobilniBroj(int mobilnaMreza, String broj){
        if( mobilnaMreza < 60 || mobilnaMreza > 67 || broj == null || broj.length() != 7 )
            throw new TelefonskiBrojException("Jedinstveni broj ili broj mobilne mreze nije OK!");
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

    /**
     * daje string mobilnog broja spreman za ispis
     * @return string mobilnog broja u obliku xxx/xxx-xxx
     */
    public String ispisi() {
        return new StringBuilder().append("0").append(mobilnaMreza).append("/").append(broj).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobilniBroj that = (MobilniBroj) o;
        return mobilnaMreza == that.mobilnaMreza && Objects.equals(broj, that.broj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobilnaMreza, broj);
    }
}