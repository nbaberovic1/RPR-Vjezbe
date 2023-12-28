package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private String naziv;
    private int glavniGrad;

    public Drzava() {
        this.id = 0;
        this.naziv = "";
        this.glavniGrad = 0;
    }

    public Drzava(int id, String naziv, int glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(int glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
