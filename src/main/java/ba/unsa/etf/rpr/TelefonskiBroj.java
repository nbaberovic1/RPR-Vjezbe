package ba.unsa.etf.rpr;

/**
 * apstraktna klasa telefonski broj
 */
public abstract class TelefonskiBroj {
    public abstract String ispisi(); //vraća broj u tekstualnoj formi pogodnoj za ispis
    @Override
    public abstract int hashCode(); //vraća hash code
}