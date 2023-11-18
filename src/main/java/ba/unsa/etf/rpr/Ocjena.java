package ba.unsa.etf.rpr;

public class Ocjena {
    private LicneInformacije osoba;
    private int ocjena;

    public Ocjena(LicneInformacije osoba, int ocjena){
        this.osoba = osoba;
        this.ocjena = ocjena;
    }

    public void setOcjena(int x){
        if(x < 0 || x > 10) throw new IllegalArgumentException("Ocjena izvan dozvoljenog opsega!");
        this.ocjena = x;
    }
}
