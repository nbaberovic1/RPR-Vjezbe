package ba.unsa.etf.rpr.lv10_11;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class DrzavaController {

    private GeografijaDAO geografijaDAO;

    @FXML
    private TextField fieldNaziv;

    @FXML
    private ChoiceBox<Grad> choiceGrad;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    public DrzavaController() {
        try {
            geografijaDAO = GeografijaDAO.getInstance();
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {

        choiceGrad.setItems(geografijaDAO.getGradovi());

        btnOk.setOnMouseClicked(e -> {
            String naziv = fieldNaziv.getText();

            if (naziv.equals("")) {
                fieldNaziv.setStyle("-fx-background-color: lightpink");
                return;
            }

            int id;
            try {
                id = Collections.max(geografijaDAO.getDrzave(), new Comparator<Drzava>() {
                    @Override
                    public int compare(Drzava d1, Drzava d2) {
                        return Integer.compare(d1.getId(), d2.getId());
                    }
                }).getId() + 1;
            } catch (NoSuchElementException ex) {
                id = 1;
            } catch (Exception exception) {
                exception.printStackTrace();
                return;
            }

            Grad glavniGrad = choiceGrad.getValue();
            int idGlavniGrad;
            if(glavniGrad == null) {
                idGlavniGrad = 0;
            }else {
                idGlavniGrad = glavniGrad.getId();
            }

            geografijaDAO.dodajDrzavu(new Drzava(id, naziv, idGlavniGrad));

            Stage gradProzor = (Stage) choiceGrad.getScene().getWindow();
            gradProzor.close();
        });

        btnCancel.setOnMouseClicked(e -> {
            Stage gradProzor = (Stage) btnCancel.getScene().getWindow();
            gradProzor.close();
        });
    }
}