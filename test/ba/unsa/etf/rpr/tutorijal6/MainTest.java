package ba.unsa.etf.rpr.tutorijal6;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class MainTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/fxml/formular.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }
    @Test
    public void start(FxRobot robot){
        TextField tekst = robot.lookup("#poljeIme").queryAs(TextField.class);
        assertEquals("", tekst.getText());

    }
    @Test
    public void imeTest(FxRobot robot) {
        TextField poljeIme = robot.lookup("#poljeIme").queryAs(TextField.class);
        robot.clickOn("#poljeIme").write("ivan");
        assertEquals("ivan", poljeIme.getText());
    }
    @Test
    public void prezimeTest(FxRobot robot) {
        TextField prezime = robot.lookup("#poljePrezime").queryAs(TextField.class);
        robot.clickOn("#poljePrezime").write("maticevic");
        assertEquals("maticevic", prezime.getText());
    }
    @Test
    public void indeksTest(FxRobot robot) {
        TextField prezime = robot.lookup("#poljeBrojIndeksa").queryAs(TextField.class);
        robot.clickOn("#poljeBrojIndeksa").write("109348");
        assertEquals("109348", prezime.getText());
    }
    @Test
    public void jmbgTest(FxRobot robot) {
        TextField jmbg = robot.lookup("#jmbgPolje").queryAs(TextField.class);
        robot.clickOn("#jmbgPolje").write("2409963174007");
        assertEquals("2409963174007", jmbg.getText());
    }
    @Test
    public void adresaTest(FxRobot robot) {
        TextField adresa = robot.lookup("#AdresaPolje").queryAs(TextField.class);
        robot.clickOn("#AdresaPolje").write("Pruga 40 Vares BiH");
        assertEquals("Pruga 40 Vares BiH", adresa.getText());
    }
    @Test
    public void emailTest(FxRobot robot) {
        TextField email = robot.lookup("#emailPolje").queryAs(TextField.class);
        robot.clickOn("#emailPolje").write("mujo.mujic@etf.unsa.ba");
        assertEquals("mujo.mujic@etf.unsa.ba", email.getText());
    }
}
