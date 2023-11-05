package ba.unsa.etf.rpr;

import java.util.Map;
import java.util.Objects;

/**
 * klasa koja predstavlja fiksni broj
 */
public class FiksniBroj extends TelefonskiBroj{
    private Grad grad;
    private String broj; //String fiksnog broja u formi xxx/xxx-xxx

    /**
     * konstruktor koji kreira objekat FiksniBroj
     * @param grad - glavni grad nekog kantona ili regije u BiH
     * @param broj - fiksni broj bez pozivnog u obliku xxx-xxx
     */
    public FiksniBroj(Grad grad, String broj){
        if(grad == null || broj == null || broj.length() != 7)
            throw new TelefonskiBrojException("Jedinstveni broj ili pozivni broj za fiksni telefon nije OK!");
        this.broj = broj;
        this.grad = grad;
    }

    /**
     * standardni geter za Grad, odnosno pozivni broj
     * @return vraća tip Grad
     */
    public Grad getGrad(){
        return this.grad;
    }

    /**
     * vraća String fiksnog broja spreman za ispis
     * @return String fiksnog broja u obliku xxx/xxx-xxx
     */
    public String ispisi(){
        return grad.getPozivniBroj() + "/" + broj;
    }

    /**
     * generisana equals metoda
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiksniBroj that = (FiksniBroj) o;
        return grad == that.grad && Objects.equals(broj, that.broj);
    }

    /**
     * generisana intellij-jem
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(grad, broj);
    }

    /**
     * standardni geter za broj
     * @return vraća broj bez pozivnog broja
     */
    public String getBroj() {
        return broj;
    }


}