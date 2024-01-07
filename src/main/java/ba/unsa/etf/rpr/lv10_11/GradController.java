package ba.unsa.etf.rpr.lv10_11;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GradController {

    @FXML
    private TextField fieldNaziv;

    @FXML
    private TextField fieldBrojStanovnika;

    @FXML
    private ChoiceBox<Drzava> choiceDrzava;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    private boolean ubacivanjeGrada;

    public GradController (boolean ubacivanjeGrada) {
        this.ubacivanjeGrada = ubacivanjeGrada;
    }

    @FXML
    public void initialize() {

        choiceDrzava.setItems(GlavnaController.drzave);

        btnOk.setOnMouseClicked( e-> {

        });

        btnCancel.setOnMouseClicked(e -> {
            Stage gradProzor = (Stage) btnCancel.getScene().getWindow();
            gradProzor.close();
        });
    }
}
