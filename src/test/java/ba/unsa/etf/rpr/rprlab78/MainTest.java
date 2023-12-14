package ba.unsa.etf.rpr.rprlab78;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class MainTest {

    private TextField fldIme;
    private TextField fldPrezime;
    private TextField fldEmail;
    private TextField fldKorisnickoIme;
    private TextField fldLozinka;

    @Start
    public void start(Stage stage) throws IOException {
        KorisniciModel model = new KorisniciModel();
        model.napuni();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("korisnici.fxml"));
        loader.setController(new Controller(model));
        Parent root = loader.load();
        stage.setTitle("Korisnici");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void kreceSaPraznimPoljimaZaUnos (FxRobot robot) {
        fldIme = robot.lookup("#fldIme").queryAs(TextField.class);
        assertEquals("", fldIme.getText());
    }

}
