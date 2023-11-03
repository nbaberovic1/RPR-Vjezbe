package ba.unsa.etf.rpr;

import java.util.Objects;

public class MedunarodniBroj extends TelefonskiBroj{

    private String drzava;

    private String broj;

    public MedunarodniBroj(String drzava, String broj) {
        if(drzava == null || broj == null)
            throw new IllegalArgumentException("Nekorektni argumenti!");
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
