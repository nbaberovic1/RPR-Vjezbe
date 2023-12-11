package ba.unsa.etf.rpr.rprlab78;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private KorisniciModel model;
    @FXML
    private TextField fldIme;
    @FXML
    private TextField fldPrezime;
    @FXML
    private TextField fldEmail;
    @FXML
    private TextField fldKorisnickoIme;
    @FXML
    private TextField fldLozinka;
    @FXML
    private ListView<Korisnik> listKorisnici;
    @FXML
    private Button btnDodaj;
    @FXML
    private Button btnKraj;

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

        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);
            listKorisnici.refresh();
        });

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

        btnDodaj.setOnMouseClicked( e-> {
            Korisnik korisnik = new Korisnik("", "", "", "", "");
           model.addKorisnik(korisnik);
           model.setTrenutniKorisnik(korisnik);

        });

        btnKraj.setOnMouseClicked(e -> System.exit(0));
    }

}