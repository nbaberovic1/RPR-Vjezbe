package ba.unsa.etf.rpr.rprlab78;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    private KorisniciModel model;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldKorisnickoIme;
    public TextField fldLozinka;
    public ListView<Korisnik> listKorisnici;

    public Controller (KorisniciModel m) {
        model = m;
    }
    @FXML
    public void initialize() {
        //fldIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().imeProperty());
        //fldPrezime.textProperty().bindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
        //fldEmail.textProperty().bindBidirectional(model.getTrenutniKorisnik().emailProperty());
        //fldKorisnickoIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().korisnickoImeProperty());
        //fldLozinka.textProperty().bindBidirectional(model.getTrenutniKorisnik().lozinkaProperty());

        listKorisnici.setItems(model.getKorisnici());

        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(oldKorisnik != null){
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                fldKorisnickoIme.textProperty().unbindBidirectional(oldKorisnik.korisnickoImeProperty());
                fldLozinka.textProperty().unbindBidirectional(oldKorisnik.lozinkaProperty());
            }

            if( newKorisnik == null){
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                fldKorisnickoIme.setText("");
                fldLozinka.setText("");
            } else {
                fldIme.textProperty().bindBidirectional(newKorisnik.imeProperty());
                fldPrezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                fldEmail.textProperty().bindBidirectional(newKorisnik.emailProperty());
                fldKorisnickoIme.textProperty().bindBidirectional(newKorisnik.korisnickoImeProperty());
                fldLozinka.textProperty().bindBidirectional(newKorisnik.lozinkaProperty());
            }
        });
    }

}