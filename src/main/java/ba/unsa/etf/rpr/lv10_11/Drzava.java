package ba.unsa.etf.rpr.lv10_11;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {
    private SimpleIntegerProperty id;
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty glavniGrad;

    public Drzava() {
        this.id = new SimpleIntegerProperty(0);
        this.naziv = new SimpleStringProperty("");
        this.glavniGrad = new SimpleIntegerProperty(0);
    }

    public Drzava(SimpleIntegerProperty id, SimpleStringProperty naziv, SimpleIntegerProperty glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public final int getId() {
        return id.get();
    }

    public final SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(final int id) {
        this.id.set(id);
    }

    public final String getNaziv() {
        return naziv.get();
    }

    public final SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(final String naziv) {
        this.naziv.set(naziv);
    }

    public final int getGlavniGrad() {
        return glavniGrad.get();
    }

    public final SimpleIntegerProperty glavniGradProperty() {
        return glavniGrad;
    }
    public void setGlavniGrad(final int glavniGrad) {
        this.glavniGrad.set(glavniGrad);
    }
}