package ba.unsa.etf.rpr.lv10_11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class GlavnaController {

    public static ObservableList<Grad> gradovi = FXCollections.observableArrayList();
    public static ObservableList<Drzava> drzave = FXCollections.observableArrayList();

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

    @FXML
    public void initialize() {

        tableViewGradovi.setItems(gradovi);

        btnDodajDrzavu.setOnMouseClicked( e-> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("drzava.fxml"));
            loader.setController(new DrzavaController());
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            final Stage drzavaProzor = new Stage();
            drzavaProzor.setTitle("Drzava");
            drzavaProzor.setResizable(false);
            drzavaProzor.setScene(new Scene(root));
            drzavaProzor.show();
        });
    }
}
