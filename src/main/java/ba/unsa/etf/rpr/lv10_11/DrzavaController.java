package ba.unsa.etf.rpr.lv10_11;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Comparator;

public class DrzavaController {

    @FXML
    private TextField fieldNaziv;

    @FXML
    private ChoiceBox<Grad> choiceGrad;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    public void initialize() {

        choiceGrad.setItems(GlavnaController.gradovi);

        btnOk.setOnMouseClicked(e -> {
            String naziv = fieldNaziv.getText();

            if (naziv.equals("")) {
                fieldNaziv.setStyle("-fx-background-color: lightpink");
                return;
            }

            int id = Collections.max(GlavnaController.drzave, new Comparator<Drzava>() {
                @Override
                public int compare(Drzava d1, Drzava d2) {
                    return Integer.compare(d1.getId(), d2.getId());
                }
            }).getId() + 1;

            Grad glavniGrad = choiceGrad.getValue();
            int idGlavniGrad;
            if(glavniGrad == null) {
                idGlavniGrad = 0;
            }else {
                idGlavniGrad = glavniGrad.getId();
            }

            GlavnaController.drzave.add(new Drzava(id, naziv, idGlavniGrad));

            Stage gradProzor = (Stage) choiceGrad.getScene().getWindow();
            gradProzor.close();
        });

        btnCancel.setOnMouseClicked(e -> {
            Stage gradProzor = (Stage) btnCancel.getScene().getWindow();
            gradProzor.close();
        });
    }
}