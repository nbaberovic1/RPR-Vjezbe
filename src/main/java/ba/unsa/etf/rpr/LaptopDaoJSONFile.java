package ba.unsa.etf.rpr;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

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
        for(Laptop l : laptopi){
            this.laptopi.add(l);
        }
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException {
        ArrayList<Laptop> izDatoteke = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            izDatoteke = mapper.readValue(file, izDatoteke.getClass());

        }catch (Exception ex){
            System.out.println("Neuspjesno vracanje podataka iz json file-a sa porukom: " + ex.getMessage());
        }
        return izDatoteke;
    }
}
