package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Program {
    public static void main( String[] args ) {

        Laptop l1 = new Laptop("HP", "KingKong", 1000, 8, 1000, 500, "i7", 24, "GTX730Ti");
        Laptop l2 = new Laptop("MS", "KingKong", 800, 16, 1000, 250, "i5", 23, "GTX1080Ti");
        Laptop l3 = new Laptop("Apple", "Mac123", 2000, 16, 1000, 500, "M2", 27, "GTX730Ti");
        Laptop l4 = new Laptop("Ryzen", "Warrior", 1000, 8, 1000, 500, "i5", 22, "GT1050");
        Laptop l5 = new Laptop("MS", "Skyline", 5000, 32, 1000, 1000, "i9", 24, "GT1050Ti");

        ArrayList<Laptop> zaNapunitiListu = new ArrayList<>();
        zaNapunitiListu.add(l1);
        zaNapunitiListu.add(l2);
        zaNapunitiListu.add(l3);

        LaptopDaoSerializableFile serializableFile = new LaptopDaoSerializableFile();
        serializableFile.napuniListu(zaNapunitiListu);

        serializableFile.dodajLaptopUListu(l4);

        ArrayList<Laptop> povratIzListe = serializableFile.getLaptopi();

        for(Laptop l : povratIzListe){
            System.out.println(l);
        }

        System.out.println("");

        serializableFile.dodajLaptopUFile(l1);
        serializableFile.dodajLaptopUFile(l3);
        serializableFile.dodajLaptopUFile(l5);

        ArrayList<Laptop> povratIzDatoteke = serializableFile.vratiPodatkeIzDatoteke();

        for(Laptop l : povratIzDatoteke){
            System.out.println(l);
        }
    }
}
