package ba.unsa.etf.rpr.lv10_11;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class GradController {

    private GeografijaDAO geografijaDAO;

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
        try {
            geografijaDAO = GeografijaDAO.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {

        choiceDrzava.setItems(geografijaDAO.getDrzave());

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
                int id;
                try {
                    id = Collections.max(geografijaDAO.getGradovi(), new Comparator<Grad>() {
                        @Override
                        public int compare(Grad g1, Grad g2) {
                            return Integer.compare(g1.getId(), g2.getId());
                        }
                    }).getId() + 1;
                } catch (NoSuchElementException ex) {
                  id = 1;
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return;
                }

                geografijaDAO.dodajGrad(new Grad(id, naziv, brojStanovnika, choiceDrzava.getValue()));

            } else {
                Grad grad = tableViewGradovi.getSelectionModel().getSelectedItem();
                if(grad != null) {
                    geografijaDAO.izmijeniGrad(new Grad(grad.getId(), naziv, brojStanovnika, choiceDrzava.getValue()));
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
