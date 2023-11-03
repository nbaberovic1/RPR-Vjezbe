package ba.unsa.etf.rpr;

public class FiksniBroj extends TelefonskiBroj{

    private Grad grad;

    private String broj;

    public FiksniBroj(Grad grad, String broj){
        this.grad = grad;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
