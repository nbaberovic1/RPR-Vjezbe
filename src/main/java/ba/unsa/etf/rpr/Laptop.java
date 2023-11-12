package ba.unsa.etf.rpr;

import java.io.Serializable;

public class Laptop implements Serializable {
    private String brend;
    private String model;
    private double cijena;
    private int ram;
    private int hdd;
    private int ssd;
    private String procesor;

    private String grafickaKartica;

    private double velicinaEkrana;

    public Laptop(String brend, String model, double cijena, int ram, int hdd, int ssd, String procesor, double velicinaEkrana, String grafickaKartica) {
        this.brend = brend;
        this.model = model;
        this.cijena = cijena;
        this.ram = ram;
        this.hdd = hdd;
        this.ssd = ssd;
        this.procesor = procesor;
        this.grafickaKartica = grafickaKartica;
        this.velicinaEkrana = velicinaEkrana;
    }

    public String getModel() {
        return model;
    }

    public String getBrend() {
        return brend;
    }

    public double getCijena() {
        return cijena;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public double getVelicinaEkrana() {
        return velicinaEkrana;
    }

    public void setVelicinaEkrana(double velicinaEkrana) {
        this.velicinaEkrana = velicinaEkrana;
    }
}