package ba.unsa.etf.rpr.lv10_11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GlavnaController {

    @FXML
    private TableView<Grad> tableViewGradovi;

    @FXML
    private Button btnDodajGrad;

    @FXML
    private Button btnDodajDrzavu;

    @FXML
    private Button btnIzmijeniGrad;

    @FXML
    private Button btnObrisiGrad;

    private GeografijaDAO geografijaDAO;

    public GlavnaController() {
        try {
            geografijaDAO = GeografijaDAO.getInstance();
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void pozoviGradFormu (boolean dodavanjeGrada) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/grad.fxml"));
        loader.setController(new GradController(tableViewGradovi, dodavanjeGrada));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        final Stage gradProzor = new Stage();
        gradProzor.setTitle("Grad");
        gradProzor.setResizable(false);
        gradProzor.setScene(new Scene(root));
        gradProzor.show();
    }

    @FXML
    public void initialize() throws SQLException {

        tableViewGradovi.setItems(geografijaDAO.getGradovi());

        ObservableList<TableColumn<Grad, ?>> kolone = tableViewGradovi.getColumns();

        kolone.get(0).setCellValueFactory(new PropertyValueFactory<>("id"));

        kolone.get(1).setCellValueFactory(new PropertyValueFactory<>("naziv"));

        kolone.get(2).setCellValueFactory(new PropertyValueFactory<>("brojStanovnika"));

        kolone.get(3).setCellValueFactory(new PropertyValueFactory<>("drzava"));

        btnDodajDrzavu.setOnMouseClicked( e-> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/drzava.fxml"));
            loader.setController(new DrzavaController());
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            final Stage drzavaProzor = new Stage();
            drzavaProzor.setTitle("Drzava");
            drzavaProzor.setResizable(false);
            drzavaProzor.setScene(new Scene(root));
            drzavaProzor.show();
        });

        btnDodajGrad.setOnMouseClicked( e-> pozoviGradFormu(true));

        btnIzmijeniGrad.setOnMouseClicked( e-> {
            if(tableViewGradovi.getSelectionModel().getSelectedItem() != null){
                pozoviGradFormu(false);
            }
        });

        btnObrisiGrad.setOnMouseClicked( e-> {
            Grad grad = tableViewGradovi.getSelectionModel().getSelectedItem();
            if(grad != null) {
                geografijaDAO.obrisiGrad(grad);
                tableViewGradovi.refresh();
            }
        });
    }
}
