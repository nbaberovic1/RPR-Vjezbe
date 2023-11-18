package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Main {

    public static void main (String[]args){
        while(true){

            Predmet predmet = new Predmet();
            predmet.setNaziv("RPR");
            predmet.setOpis("Predmet na drugoj godini ETF-a.");

            InformacijeONastavniku nastavnik = new InformacijeONastavniku();

            nastavnik.setIme("Profa");
            nastavnik.setPrezime("Profic");
            nastavnik.setTitula("prof");

            Scanner ulaz = new Scanner(System.in);
            System.out.println("""
                    Ako ste student pritisnite 1,
                    ako ste nastavnik pritisnike 2,
                    ako ste neko drugi pritisnite 3: """);

            int uloga = ulaz.nextInt();
            if(uloga<1 || uloga>3) {
                System.out.println("Neispravan unos!");
                continue;
            }

            LicneInformacije osoba = null;

            ulaz.nextLine();

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
            Ocjena ocjena = null;
            try{
                ocjena = new Ocjena(osoba, o);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                continue;
            }

            System.out.println("Zelite li ocijeniti predmet ili nastavnika, za odabir unesite 1 ili 2 respektivno: ");
            int odabir = ulaz.nextInt();

            switch (odabir){
                case 1:
                    predmet.dodajOcjenu(ocjena);
                    System.out.println("Ocjena uspjesno dodana.");
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }

    }




}