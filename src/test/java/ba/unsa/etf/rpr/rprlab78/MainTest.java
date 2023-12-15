package ba.unsa.etf.rpr.rprlab78;

import static org.junit.jupiter.api.Assertions.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
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
    private PasswordField fldLozinka;
    private Button btnDodaj;
    private ListView<Korisnik> listaKorisnika;

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

    @BeforeEach
    private void povezi (FxRobot robot) {
        listaKorisnika = robot.lookup("#listKorisnici").queryListView();
        fldIme = robot.lookup("#fldIme").queryAs(TextField.class);
        fldPrezime = robot.lookup("#fldPrezime").queryAs(TextField.class);
        fldEmail = robot.lookup("#fldEmail").queryAs(TextField.class);
        fldKorisnickoIme = robot.lookup("#fldKorisnickoIme").queryAs(TextField.class);
        fldLozinka = robot.lookup("#fldLozinka").queryAs(PasswordField.class);
        btnDodaj = robot.lookup("#btnDodaj").queryButton();
    }

    @Test
    public void pocinjeSaPraznimPoljimaZaUnos (FxRobot robot) {
        assertEquals("", fldIme.getText());
        assertEquals("", fldPrezime.getText());
        assertEquals("", fldEmail.getText());
        assertEquals("", fldKorisnickoIme.getText());
        assertEquals("", fldLozinka.getText());
    }

    @Test
    public void pocinjeSaNapunjenomListom (FxRobot robot) {
        ObservableList<Korisnik> korisnici = listaKorisnika.getItems();
        assertEquals(2, korisnici.size());
        assertEquals("Korisnik 1", korisnici.get(0).toString());
        assertEquals("Korisnik 2", korisnici.get(1).toString());
    }

    @Test
    public void odabirKorisnika (FxRobot robot) {
        robot.clickOn(listaKorisnika.getItems().get(0).toString());
        assertEquals("Korisnik", fldIme.getText());
        assertEquals("1", fldPrezime.getText());
        assertEquals("korisnik1@live.com", fldEmail.getText());
        assertEquals("korisnik1", fldKorisnickoIme.getText());
        assertEquals("kor1", fldLozinka.getText());
    }

    @Test
    public void promjenaOdabiraKorisnika (FxRobot robot) {
        robot.clickOn(listaKorisnika.getItems().get(0).toString());
        robot.clickOn(listaKorisnika.getItems().get(1).toString());
        assertEquals("Korisnik", fldIme.getText());
        assertEquals("2", fldPrezime.getText());
        assertEquals("korisnik2@live.com", fldEmail.getText());
        assertEquals("korisnik2", fldKorisnickoIme.getText());
        assertEquals("kor2", fldLozinka.getText());
    }

    @Test
    public void promjenaAtributaKorisnika (FxRobot robot) {
        robot.clickOn(listaKorisnika.getItems().get(0).toString());
        robot.clickOn("#fldIme").write("Promjena");
        assertEquals("KorisnikPromjena 1", listaKorisnika.getSelectionModel().getSelectedItem().toString());
        robot.clickOn("#fldPrezime").eraseText(1).write("Prezime");
        assertEquals("KorisnikPromjena Prezime", listaKorisnika.getSelectionModel().getSelectedItem().toString());

        robot.moveTo("#fldEmail").moveBy(35, 0).clickOn().eraseText(18).write("korisnikPromjena.prezime@live.com", 18);
        assertEquals("korisnikPromjena.prezime@live.com", listaKorisnika.getSelectionModel().getSelectedItem().getEmail());
        robot.clickOn("#fldKorisnickoIme").eraseText(9).write("KorisnikPromjenaP");
        assertEquals("KorisnikPromjenaP", listaKorisnika.getSelectionModel().getSelectedItem().getKorisnickoIme());
        robot.clickOn("#fldLozinka").eraseText(1).write("P");
        assertEquals("korP", listaKorisnika.getSelectionModel().getSelectedItem().getLozinka());
    }

    @Test
    public void dodavanjeNovogKorisnika (FxRobot robot) {
        robot.clickOn("#btnDodaj");
        assertEquals(3, listaKorisnika.getItems().size());
        assertEquals(2, listaKorisnika.getSelectionModel().getSelectedIndex());
        assertEquals(" ", listaKorisnika.getSelectionModel().getSelectedItem().toString());
        assertEquals("", fldIme.getText());
        assertEquals("", fldPrezime.getText());
        assertEquals("", fldEmail.getText());
        assertEquals("", fldKorisnickoIme.getText());
        assertEquals("", fldLozinka.getText());
    }

    @Test
    public void unosAtributaTekDodanomKorisniku (FxRobot robot) {
        robot.clickOn("#btnDodaj");
        robot.clickOn("#fldIme").write("Novi");
        assertEquals("Novi ", listaKorisnika.getSelectionModel().getSelectedItem().toString());
        robot.clickOn("#fldPrezime").write("Korisnik");
        assertEquals("Novi Korisnik", listaKorisnika.getSelectionModel().getSelectedItem().toString());

        robot.clickOn("#fldEmail").write("novi.korisnik@live.com");
        assertEquals("novi.korisnik@live.com", listaKorisnika.getSelectionModel().getSelectedItem().getEmail());
        robot.clickOn("#fldKorisnickoIme").write("NoviK");
        assertEquals("NoviK", listaKorisnika.getSelectionModel().getSelectedItem().getKorisnickoIme());
        robot.clickOn("#fldLozinka").write("NoviK");
        assertEquals("NoviK", listaKorisnika.getSelectionModel().getSelectedItem().getLozinka());
    }


}
