package ba.unsa.etf.rpr;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao{

    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile(){
        file = new File("file.txt");
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
        for(Laptop l : laptopi){
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
        throw new
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {

    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> izDatoteke = null;
        try{
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);

            izDatoteke = (ArrayList<Laptop>) in.readObject();

            in.close();
            fileInput.close();

        }catch (Exception ex){
            System.out.println("Neuspjesno vracanje podataka iz file-a sa porukom: " + ex.getMessage());
        }
        return izDatoteke;
    }
}
