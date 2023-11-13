package ba.unsa.etf.rpr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main( String[] args ){

        Laptop l1 = new Laptop("HP", "KingKong", 1000, 8, 1000, 500, "i7", 24, "GTX730Ti");
        Laptop l2 = new Laptop("MS", "KingKong", 800, 16, 1000, 250, "i5", 23, "GTX1080Ti");
        Laptop l3 = new Laptop("Apple", "Mac123", 2000, 16, 1000, 500, "M2", 27, "GTX730Ti");
        Laptop l4 = new Laptop("Ryzen", "Warrior", 1000, 8, 1000, 500, "i5", 22, "GT1050");
        Laptop l5 = new Laptop("MS", "Skyline", 5000, 32, 1000, 1000, "i9", 24, "GT1050Ti");

        ArrayList<Laptop> zaNapunitiListu = new ArrayList<>();
        zaNapunitiListu.add(l1);
        zaNapunitiListu.add(l2);
        zaNapunitiListu.add(l3);

        System.out.println("Koju klasu zelite testirati: ");
        Scanner ulaz = new Scanner(System.in);
        String odabranaKlasa = ulaz.nextLine();

        LaptopDao klasaKojuTestiramo = null;

        switch (odabranaKlasa){
            case "LaptopDaoSerializableFile":
                klasaKojuTestiramo = new LaptopDaoSerializableFile();
                break;
            case "LaptopDaoXMLFile":
                klasaKojuTestiramo = new LaptopDaoXMLFile();
                break;
            case "LaptopDaoJSONFile":
                klasaKojuTestiramo = new LaptopDaoJSONFile();
                break;
            default:
                System.out.println("Nema takve klase!");
        }

        klasaKojuTestiramo.napuniListu(zaNapunitiListu);

        klasaKojuTestiramo.dodajLaptopUListu(l4);

        ArrayList<Laptop> povratIzListe = klasaKojuTestiramo.getLaptopi();

        for(Laptop l : povratIzListe){
            System.out.println(l);
        }

        System.out.println("");

        klasaKojuTestiramo.dodajLaptopUFile(l1);
        klasaKojuTestiramo.dodajLaptopUFile(l3);
        klasaKojuTestiramo.dodajLaptopUFile(l5);

        ArrayList<Laptop> povratIzDatoteke = klasaKojuTestiramo.vratiPodatkeIzDatoteke();

        for(Laptop l : povratIzDatoteke){
            System.out.println(l);
        }
        System.out.println("");

        Laptop trazeni1 = klasaKojuTestiramo.getLaptop("i5");
        System.out.println(trazeni1);

        Laptop trazeni2 = klasaKojuTestiramo.getLaptop("i9");
        System.out.println(trazeni2);
    }
}
