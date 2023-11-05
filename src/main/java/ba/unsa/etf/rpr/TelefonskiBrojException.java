package ba.unsa.etf.rpr;

/**
 * izuzetak TelefonskiBrojException naslijeden iz RuntimeException
 */
public class TelefonskiBrojException extends RuntimeException{
    /**
     * komstruktor izuzetka klase TelefonskiBrojException
     * @param msg poruka za detaljniji opis razloga bacanja izuzetka
     */
    public TelefonskiBrojException(String msg){
        super(msg);
    }
}
