package ba.unsa.etf.rpr;

import java.io.*;

import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao{

    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile(){

        file = new File("C:\\Users\\DT User3\\IdeaProjects\\lv4-z1\\src\\main\\java\\ba\\unsa\\etf\\rpr\\file.txt");
        try {
            FileWriter fwOb = new FileWriter(file, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

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

            FileOutputStream outFile = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(outFile);

            out.writeObject(listaLaptopa);

            out.flush();
            out.close();
            outFile.close();

        }catch (Exception ex){
            System.out.println("Neuspjesno dodavanje laptopa u file sa porukom: " + ex.getMessage());
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
        ArrayList<Laptop> izDatoteke = new ArrayList<Laptop>();
        try{
            if(file.length() != 0) {

                FileInputStream fileInput = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                izDatoteke = (ArrayList<Laptop>) in.readObject();

                in.close();
                fileInput.close();
            }
        }catch (Exception ex){
            System.out.println("Neuspjesno vracanje podataka iz file-a sa porukom: " + ex.getMessage());
        }
        return izDatoteke;
    }

    @Override
    public ArrayList<Laptop> getLaptopi() {
        return laptopi;
    }
}
