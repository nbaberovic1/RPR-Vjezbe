package ba.unsa.etf.rpr;

import java.util.Objects;

public class MedunarodniBroj extends TelefonskiBroj{

    private String drzava;

    private String broj;

    public MedunarodniBroj(String drzava, String broj) throws TelefonskiBrojException {
        if(drzava == null || broj == null)
            throw new TelefonskiBrojException("Jedinstveni broj ili pozivni broj nije OK!");
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return new StringBuilder().append(drzava).append(broj).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(drzava, broj);
    }
}
