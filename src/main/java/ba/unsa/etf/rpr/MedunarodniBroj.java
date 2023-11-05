package ba.unsa.etf.rpr;

import java.util.Objects;

/**
 * klasa koja predstavlja medunarodni broj
 */
public class MedunarodniBroj extends TelefonskiBroj{
    private String drzava; // cuva string pozivnog broja drzave
    private String broj; //cuva string broja

    /**
     * konstrukor klase MedunarodniBroj
     * @param drzava kompletan pozivni broj drzave
     * @param broj
     */
    public MedunarodniBroj(String drzava, String broj){
        if(drzava == null || broj == null)
            throw new TelefonskiBrojException("Jedinstveni broj ili pozivni broj nije OK!");
        this.drzava = drzava;
        this.broj = broj;
    }

    /**
     * vraca string medunarodnog broja spreman za ispis
     * @return string medunarodnog broja
     */
    public String ispisi() {
        return new StringBuilder().append(drzava).append(broj).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedunarodniBroj that = (MedunarodniBroj) o;
        return Objects.equals(drzava, that.drzava) && Objects.equals(broj, that.broj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drzava, broj);
    }
}