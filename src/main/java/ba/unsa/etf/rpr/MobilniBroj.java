package ba.unsa.etf.rpr;

import java.util.Objects;

public class MobilniBroj extends TelefonskiBroj{

    private int mobilnaMreza;

    private String broj;

    public MobilniBroj(int mobilnaMreza, String broj) throws TelefonskiBrojException {
        if( mobilnaMreza < 60 || mobilnaMreza > 67 || broj == null || broj.length() != 7 )
            throw new TelefonskiBrojException("Jedinstveni broj ili broj mobilne mreze nije OK!");
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return new StringBuilder().append("0").append(mobilnaMreza).append("/").append(broj).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobilnaMreza, broj);
    }
}
