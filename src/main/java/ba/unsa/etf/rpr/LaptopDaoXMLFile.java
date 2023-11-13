package ba.unsa.etf.rpr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao{

    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoXMLFile(){
        file = new File("C:\\Users\\DT User3\\IdeaProjects\\lv4-z1\\src\\main\\java\\ba\\unsa\\etf\\rpr\\xml_file.xml");
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

            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(file, listaLaptopa);

        }catch (Exception ex){
            System.out.println("Neuspjesno dodavanje laptopa u xml file sa porukom: " + ex.getMessage());
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
            XmlMapper mapper = new XmlMapper();
            izDatoteke = mapper.readValue(file, izDatoteke.getClass());

        }catch (Exception ex){
            System.out.println("Neuspjesno vracanje podataka iz xml file-a sa porukom: " + ex.getMessage());
        }
        return izDatoteke;
    }

    @Override
    public ArrayList<Laptop> getLaptopi() {
        return laptopi;
    }
}
