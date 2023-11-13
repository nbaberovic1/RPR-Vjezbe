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
        try{
            ArrayList<Laptop> listaLaptopa = this.vratiPodatkeIzDatoteke();
            listaLaptopa.add(laptop);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, listaLaptopa);

        }catch (Exception ex){
            System.out.println("Neuspjesno dodavanje laptopa u json file sa porukom: " + ex.getMessage());
        }
    }

    @Override
    public Laptop getLaptop(String procesor) {
        for(Laptop l : this.laptopi){
            if(l.getProcesor().equals(procesor)){
                return l;
            }
        }
        ArrayList<Laptop> laptopiIzDat = this.vratiPodatkeIzDatoteke();
        for(Laptop l : laptopiIzDat){
            if(l.getProcesor().equals(procesor)){
                return l;
            }
        }
        throw new NeodgovarajuciProcesorException("Nema laptopa sa tim procesorom!");
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        for(Laptop l : laptopi){
            this.laptopi.add(l);
        }
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> izDatoteke = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            izDatoteke = mapper.readValue(file, izDatoteke.getClass());

        }catch (Exception ex){
            System.out.println("Neuspjesno vracanje podataka iz json file-a sa porukom: " + ex.getMessage());
        }
        return izDatoteke;
    }

    public ArrayList<Laptop> getLaptopi() {
        return laptopi;
    }
}
