package ba.unsa.etf.rpr.lv10_11;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.Comparator;

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

    private TableView<Grad> tableViewGradovi;

    private boolean ubacivanjeGrada;

    public GradController (TableView<Grad> tableViewGradovi, boolean ubacivanjeGrada) {
        this.tableViewGradovi = tableViewGradovi;
        this.ubacivanjeGrada = ubacivanjeGrada;
    }

    @FXML
    public void initialize() {

        choiceDrzava.setItems(GlavnaController.drzave);

        btnOk.setOnMouseClicked( e-> {
            String naziv = fieldNaziv.getText();

            if(naziv.equals("")) {
                fieldNaziv.setStyle("-fx-background-color: lightpink");
            } else if(fieldNaziv.getStyle().equals("-fx-background-color: lightpink")) {
                fieldNaziv.setStyle("-fx-background-color: white");
            }

            int brojStanovnika;
            try {
                brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
                if(brojStanovnika < 0) {
                    fieldBrojStanovnika.setStyle("-fx-background-color: lightpink");
                } else if(fieldBrojStanovnika.getStyle().equals("-fx-background-color: lightpink")) {
                    fieldBrojStanovnika.setStyle("-fx-background-color: white");
                }
            } catch (Exception ex) {
                fieldBrojStanovnika.setStyle("-fx-background-color: lightpink");
                return;
            }

            if(naziv.equals("") || brojStanovnika < 0) return;

            if(ubacivanjeGrada) {
                int id = Collections.max(GlavnaController.gradovi, new Comparator<Grad>() {
                    @Override
                    public int compare(Grad g1, Grad g2) {
                        return Integer.compare(g1.getId(), g2.getId());
                    }
                }).getId() + 1;

                GlavnaController.gradovi.add(new Grad(id, naziv, brojStanovnika, choiceDrzava.getValue()));

            } else {
                Grad grad = tableViewGradovi.getSelectionModel().getSelectedItem();
                if(grad != null) {
                    grad.setNaziv(naziv);
                    grad.setBrojStanovnika(brojStanovnika);
                    grad.setDrzava(choiceDrzava.getValue());
                    tableViewGradovi.refresh();
                }
            }

            Stage gradProzor = (Stage) choiceDrzava.getScene().getWindow();
            gradProzor.close();
        });

        btnCancel.setOnMouseClicked(e -> {
            Stage gradProzor = (Stage) btnCancel.getScene().getWindow();
            gradProzor.close();
        });
    }
}
