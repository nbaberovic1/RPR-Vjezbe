package ba.unsa.etf.rpr;

public class Main {
    public static void main(String[] args) {
        LicneInformacije lInfo = new LicneInformacije();
        lInfo.setIme("Jah");
        lInfo.setPrezime("Staces");
        System.out.println(lInfo.getIme() + " " + lInfo.getPrezime());

        InformacijeOStudentu sInfo = new InformacijeOStudentu();
        sInfo.setIme("Takoje");
        sInfo.setPrezime("Nemasta");
        sInfo.setBrojIndexa("12345");
        sInfo.setGodinaStudija("2");
        System.out.println(sInfo.getIme() + " " + sInfo.getPrezime() + " " + sInfo.getBrojIndexa() + " " + sInfo.getGodinaStudija());

        InformacijeONastavniku nInfo = new InformacijeONastavniku();
        nInfo.setIme("Profa");
        nInfo.setPrezime("Profic");
        nInfo.setTitula("prof");
        System.out.println( nInfo.getTitula() + " " + nInfo.getIme() + " " + nInfo.getPrezime());
    }
}