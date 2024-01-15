package ba.unsa.etf.rpr.lv10_11;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grad {
    private SimpleIntegerProperty id;
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty brojStanovnika;
    private ObjectProperty<Drzava> drzava;

    public Grad () {
        this.id = new SimpleIntegerProperty(0);
        this.naziv = new SimpleStringProperty("");
        this.brojStanovnika = new SimpleIntegerProperty(0);
        this.drzava = null;
    }

    public Grad (SimpleIntegerProperty id, SimpleStringProperty naziv, SimpleIntegerProperty brojStanovnika, ObjectProperty<Drzava> drzava) {
        this.id = new SimpleIntegerProperty(id.get());
        this.naziv = new SimpleStringProperty(naziv.get());
        this.drzava = new SimpleObjectProperty<>(drzava.get());
        this.brojStanovnika = new SimpleIntegerProperty(brojStanovnika.get());
    }

    public Grad ( int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.drzava = new SimpleObjectProperty<>(drzava);
        this.brojStanovnika = new SimpleIntegerProperty(brojStanovnika);
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

    public final int getBrojStanovnika() {
        return brojStanovnika.get();
    }

    public final SimpleIntegerProperty brojStanovnikaProperty() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(final int brojStanovnika) {
        this.brojStanovnika.set(brojStanovnika);
    }

    public final Drzava getDrzava() {
        return drzava.get();
    }

    public final ObjectProperty<Drzava> drzavaProperty() {
        return drzava;
    }

    public void setDrzava(final Drzava drzava) {
        this.drzava.set(drzava);
    }

    @Override
    public String toString() {
        return this.naziv.get();
    }

    @Override
    public boolean equals(Object o) {
        Grad g2 = (Grad) o;
        return id.get() == g2.getId() && naziv.get().equals(g2.getNaziv()) && brojStanovnika.get() == g2.getBrojStanovnika() && drzava.get().equals(g2.getDrzava());
    }
}
