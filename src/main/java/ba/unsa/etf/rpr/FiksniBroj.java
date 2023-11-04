package ba.unsa.etf.rpr;

import java.util.Objects;

public class FiksniBroj extends TelefonskiBroj{

    private Grad grad;

    private String broj;

    public FiksniBroj(Grad grad, String broj){
        if(grad == null || broj == null || broj.length() != 7)
            throw new IllegalArgumentException("Nekorektni argumenti!");
        this.grad = grad;
        this.broj = broj;
    }

    public Grad getGrad(){
        return grad;
    }

    public String getBroj(){
        return broj;
    }
    @Override
    public String ispisi() {
        return grad.getPozivniBroj() + '/' + broj;
    }

    @Override
    public boolean equals(TelefonskiBroj broj) {
        return this.ispisi().equals(broj.ispisi());
    }

    @Override
    public int hashCode() {
        return Objects.hash(grad, broj);
    }
}
