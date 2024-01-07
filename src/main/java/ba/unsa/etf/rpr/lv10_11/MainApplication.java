package ba.unsa.etf.rpr.lv10_11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("glavna.fxml"));
        loader.setController(new GlavnaController());
        GlavnaController.gradovi.add(new Grad(1, "Sarajevo", 500000, new Drzava(1, "BiH", 1)));
        GlavnaController.gradovi.add(new Grad(2, "Visoko", 40000, new Drzava(1, "BiH", 1)));
        GlavnaController.drzave.add(new Drzava(1, "BiH", 1));
        GlavnaController.drzave.add(new Drzava(2, "Austrija", 0));
        Parent root = loader.load();
        stage.setTitle("Gradovi svijeta");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}