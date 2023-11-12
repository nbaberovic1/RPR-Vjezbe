package ba.unsa.etf.rpr;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoJSONFile(){
        file = new File("json_file.json");
        laptopi = new ArrayList<>();
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        this.laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {

    }

    @Override
    public Laptop getLaptop(String procesor) {
        return null;
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {

    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        return null;
    }
}
