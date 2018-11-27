package sample;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    public TextField poljeBrojIndeksa;
    public TextField jmbgPolje;
    public TextField AdresaPolje;
    public TextField emailPolje;
    public Button dugmePotvrdi;
    public TextField telefonpolje;
    public CheckBox daBoks;
    public CheckBox neBoks;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean brIndeksaValidan;
    private boolean datumValidan = false;
    private boolean jmbgValidan = false;
    private boolean telefonValidan = true;
    public TextField poljePrezime;
    public DatePicker izborDatuma;
    public TextField poljeIme;
    private String datumRodjenja = null;
    private String control = null;
    private String jmbg = null;
    private ArrayList<String> nastavci = new ArrayList<String>();
    {
        nastavci.add("hotmail.com");
        nastavci.add("gmail.com");
        nastavci.add("yahoo.com");
        nastavci.add("etf.unsa.ba");
    }
    private boolean isImePrezimeValidno(String n){
        for(int i = 0; i < n.length(); i++){
            if(!Character.isLetter(n.charAt(i)) || i > 19){
                return false;
            }
        }
        return !n.trim().isEmpty();
    }
    private boolean isNumber(String s, int granica) {
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i)) || i > granica){
                return false;
            }
        }
        return !s.trim().isEmpty();
    }
    private boolean isValidanJMBG(String s) {
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
            if(control != null && s.length() == 13){
                if(!control.equals(s.substring(0,7)))
                    return false;
            }
        }
        jmbg = s;
        return true;
    }

    public void dateHandler(ActionEvent actionEvent) {
        LocalDate d = izborDatuma.getValue();
        datumRodjenja = d.toString().substring(8,10) + "." + d.toString().substring(5,7) + "." + d.toString().substring(0,4);
        if(Integer.parseInt(d.toString().substring(0, 4)) > 2017) {
            izborDatuma.getStyleClass().removeAll("poljeIspravno");
            izborDatuma.getStyleClass().add("poljeNeispravno");
            datumValidan = false;
        }
        else {
            izborDatuma.getStyleClass().removeAll("poljeNeispravno");
            izborDatuma.getStyleClass().add("poljeIspravno");
            datumValidan = true;
        }
        String s2 = d.toString().substring(8,10) + d.toString().substring(5,7) + d.toString().substring(1,4);
        if(control == null)
            control = s2;
        if(control != s2) {
            control = s2;
            if(!control.equals(jmbg.substring(0,7)) && jmbgValidan && jmbg.length() == 13){
                jmbgPolje.getStyleClass().removeAll("poljeIspravno");
                jmbgPolje.getStyleClass().add("poljeNeispravno");
                jmbgValidan = false;
            }
            if(!jmbgValidan && jmbg.length() == 13 && control.equals(jmbg.substring(0,7))){
                jmbgPolje.getStyleClass().removeAll("poljeNeispravno");
                jmbgPolje.getStyleClass().add("poljeIspravno");
                jmbgValidan = true;
            }
        }
    }

    @FXML
    public void initialize(){
        imeValidno = false;
        poljeIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(isImePrezimeValidno(newValue)){
                    poljeIme.getStyleClass().removeAll("poljeNeispravno");
                    poljeIme.getStyleClass().add("poljeIspravno");
                    imeValidno = true;
                }
                if(!isImePrezimeValidno(newValue)){
                    poljeIme.getStyleClass().removeAll("poljeNeispravno","poljeIspravno");
                    poljeIme.getStyleClass().add("poljeNeispravno");
                    imeValidno = false;
                }
            }
        });
        prezimeValidno = false;
        poljePrezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(isImePrezimeValidno(newValue)){
                    poljePrezime.getStyleClass().removeAll("poljeNeispravno");
                    poljePrezime.getStyleClass().add("poljeIspravno");
                    prezimeValidno = true;
                }
                if(!isImePrezimeValidno(newValue)){
                    poljePrezime.getStyleClass().removeAll("poljeNeispravno","poljeIspravno");
                    poljePrezime.getStyleClass().add("poljeNeispravno");
                    prezimeValidno = false;
                }
            }
        });
        brIndeksaValidan = false;
        poljeBrojIndeksa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(isNumber(newValue ,7)){
                    poljeBrojIndeksa.getStyleClass().removeAll("poljeNeispravno");
                    poljeBrojIndeksa.getStyleClass().add("poljeIspravno");
                    brIndeksaValidan = true;
                }
                if(!isNumber(newValue, 7)){
                    poljeBrojIndeksa.getStyleClass().removeAll("poljeNeispravno","poljeIspravno");
                    poljeBrojIndeksa.getStyleClass().add("poljeNeispravno");
                    brIndeksaValidan = false;
                }
            }

        });
        jmbgValidan = false;
        jmbg = jmbgPolje.getText();
        jmbgPolje.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!isValidanJMBG(newValue) || newValue.length() >= 13){
                    jmbgPolje.getStyleClass().removeAll("poljeIspravno");
                    jmbgPolje.getStyleClass().add("poljeNeispravno");
                    jmbgValidan = false;
                }
                if(isValidanJMBG(newValue) && newValue.length() == 13){
                    jmbgPolje.getStyleClass().removeAll("poljeNeispravno");
                    jmbgPolje.getStyleClass().add("poljeIspravno");
                    jmbgValidan = true;
                }
                if(isValidanJMBG(newValue) && newValue.length() < 13)
                    jmbgPolje.getStyleClass().removeAll("poljeNeispravno", "poljeIspravno");
            }
        });
        emailPolje.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                for(String s : nastavci){
                    if(newValue.endsWith(s)){
                        emailPolje.getStyleClass().add("poljeIspravno");
                        break;
                    }
                    else emailPolje.getStyleClass().removeAll("poljeIspravno");
                }
            }
        });
        telefonpolje.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!isNumber(newValue, 10) && (newValue != null || newValue != "")){
                    telefonpolje.getStyleClass().removeAll("poljeIspravno");
                    telefonpolje.getStyleClass().add("poljeNeispravno");
                    telefonValidan = false;
                }
                else {
                    telefonpolje.getStyleClass().removeAll("poljeNeispravno");
                    telefonpolje.getStyleClass().add("poljeIspravno");
                    telefonValidan = true;
                }
            }
        });
    }
    private String korektanUnos(){
        String poruka = "";


        if(!imeValidno)
            poruka = poruka + "Netočno ime!\n";
        if(!prezimeValidno)
            poruka = poruka + "Netočno prezime!\n";
        if(!brIndeksaValidan)
            poruka = poruka + "Nekorektan broj indeksa\n";
        if(!datumValidan || !jmbgValidan)
            poruka = poruka + "Datum i JMBG se ne podudaraju\n";
        if(!telefonValidan)
            poruka = poruka + "Netočan broj telefona";
        return poruka;
    }

    public void dugmeKliknuto(ActionEvent actionEvent) {
        if(!korektanUnos().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije validno");
            alert.setHeaderText("Popunjeni formular nije validan");
            alert.setContentText(korektanUnos());
            alert.show();
        }
        else {
            System.out.println("Ime: " + poljeIme.getText());
            System.out.println("Prezime: " + poljePrezime.getText());
            System.out.println("Broj indeksa: " + poljeBrojIndeksa.getText());
            System.out.println("Datum rođenja: " + datumRodjenja);
            System.out.println("email: " + emailPolje.getText());
            System.out.println("Adresa: " + AdresaPolje.getText());
            System.out.println("Kontakt telefon: " + telefonpolje.getText());
        }


    }
}
