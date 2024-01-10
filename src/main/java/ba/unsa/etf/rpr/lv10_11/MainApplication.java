package ba.unsa.etf.rpr.lv10_11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        GeografijaDAO geo = GeografijaDAO.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/glavna.fxml"));
        loader.setController(new GlavnaController());
        Parent root = loader.load();
        stage.setTitle("Gradovi svijeta");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}