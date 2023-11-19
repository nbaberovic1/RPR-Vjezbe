package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String[]args){

        Predmet predmet = new Predmet();
        predmet.setNaziv("RPR");
        predmet.setOpis("Predmet na drugoj godini ETF-a.");
        predmet.dodajOcjenu(new Ocjena(new LicneInformacije(), 7));

        InformacijeONastavniku nastavnik = new InformacijeONastavniku();

        nastavnik.setIme("Profa");
        nastavnik.setPrezime("Profic");
        nastavnik.setTitula("prof");

        Scanner ulaz = new Scanner(System.in);

        while(true){

            System.out.println("""
            Ako zelite ocijeniti: predmet unesite 1,
                               nastavnika unesite 2.
            Ako zelite da se ispisu ocjene za: predmet unesite 3,
                                            nastavnika unesite 4.
            A za izlaz iz programa unesite 0: """);

            int odabir = ulaz.nextInt();
            ulaz.nextLine();

            if(odabir < 0 || odabir > 4){
                System.out.println("Neispravan izbor! ");
                continue;
            }

            switch (odabir){
                case 3:
                    System.out.println(predmet.getNaziv());
                    ArrayList<Ocjena> ocjene = predmet.getOcjene();
                    for(Ocjena ocjena : ocjene){
                        System.out.print(ocjena.getOcjena() + " ");
                    }
                    System.out.println();
                    continue;
                case 4:
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("""
                    Ako ste student pritisnite 1,
                    ako ste nastavnik pritisnike 2,
                    ako ste neko drugi pritisnite 3:""");

            int uloga = ulaz.nextInt();
            ulaz.nextLine();
            if(uloga<1 || uloga>3) {
                System.out.println("Neispravan unos!");
                continue;
            }

            if(odabir == 2 && uloga != 1){
                System.out.println("Vi ne mozete ocijeniti nastavnika!");
                continue;
            }

            LicneInformacije osoba = null;


            System.out.println("Unesite ime: ");
            String ime = ulaz.nextLine();

            System.out.println("Unesite prezime: ");
            String prezime = ulaz.nextLine();

            switch (uloga) {
                case 1:
                    osoba = new InformacijeOStudentu();
                    System.out.println("Unesite godinu studija: ");
                    String godinaStudija = ulaz.nextLine();
                    System.out.println("Unesite broj indexa: ");
                    String brojIndexa = ulaz.nextLine();
                    osoba.setIme(ime);
                    osoba.setPrezime(prezime);
                    InformacijeOStudentu osobaStudent = (InformacijeOStudentu) osoba;
                    osobaStudent.setGodinaStudija(godinaStudija);
                    osobaStudent.setBrojIndexa(brojIndexa);
                    break;
                case 2:
                    osoba = new InformacijeONastavniku();
                    System.out.println("Unesite titulu: ");
                    String titula = ulaz.nextLine();
                    InformacijeONastavniku osobaNastavnik = (InformacijeONastavniku) osoba;
                    osobaNastavnik.setTitula(titula);
                    break;
                case 3:
                    osoba = new LicneInformacije();
                    osoba.setIme(ime);
                    osoba.setPrezime(prezime);
                    break;
            }

            System.out.println("Unesite ocjenu: ");
            int o = ulaz.nextInt();
            ulaz.nextLine();
            Ocjena ocjena = null;
            try{
                ocjena = new Ocjena(osoba, o);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                continue;
            }

            if(odabir == 1) {
                predmet.dodajOcjenu(ocjena);
                System.out.println("Uspjesno ocijenjen predmet.");
            }else{
                nastavnik.dodajOcjenu(ocjena);
                System.out.println("Uspjesno ocijenjen nastavnik.");
            }
        }
    }
}