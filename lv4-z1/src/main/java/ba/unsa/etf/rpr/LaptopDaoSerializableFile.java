package ba.unsa.etf.rpr;

import java.io.File;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao{

    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile(){
        file = new File("/file.txt");
        laptopi = new ArrayList<>();
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {

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
