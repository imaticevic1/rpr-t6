package ba.unsa.etf.rpr.tutorijal6;
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
    public CheckBox redovan;
    public CheckBox Samofinancirajući;
    public ComboBox<String> mjestoRodjenja;
    public ComboBox smjerBoks;
    public ComboBox godinaStudija;
    public ComboBox ciklusStudija;
    private boolean validanCiklus = false;
    private boolean godinaValidna = false;
    private boolean smjerValidan = false;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean brIndeksaValidan;
    private boolean datumValidan = false;
    private boolean jmbgValidan = false;
    private boolean telefonValidan = true;
    private boolean ispravnoMjesto = false;
    public TextField poljePrezime;
    public DatePicker izborDatuma;
    public TextField poljeIme;
    private String datumRodjenja = null;
    private String control = null;
    private String jmbg = null;
    private String selekcija = null;
    private String status = null;
    private ArrayList<String> nastavci = new ArrayList<String>();
    {
        nastavci.add(".hotmail.com");
        nastavci.add(".gmail.com");
        nastavci.add(".yahoo.com");
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
        int suma = 0;
        int broj = 7;
        for(int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
            if(control != null && s.length() == 13) {
                if (!control.equals(s.substring(0, 7)))
                    return false;
            }
                if(s.length() == 13){
                    int j = 0;
                    while( j < 6 ){
                        suma = suma + broj*(Integer.valueOf(s.substring(j,j+1))+Integer.valueOf(s.substring(j+6,j+7)));
                        broj -= 1;
                        j++;
                    }
                    int cifra = 11 - (suma % 11);
                    if(cifra > 9) cifra = 0;
                    System.out.println(s.substring(12,13));
                    System.out.println(suma);
                    System.out.println( cifra );
                    if(cifra != Integer.valueOf(s.substring(12,13)))
                        return false;
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
            if(jmbg.length() == 13)
            if(!control.equals(jmbg.substring(0,7)) && jmbgValidan ){
                jmbgPolje.getStyleClass().removeAll("poljeIspravno");
                jmbgPolje.getStyleClass().add("poljeNeispravno");
                jmbgValidan = false;
            }
            if(jmbg.length() == 13)
            if(!jmbgValidan  && control.equals(jmbg.substring(0,7))){
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
        mjestoRodjenja.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!isImePrezimeValidno(newValue) &&  newValue != ""){
                    mjestoRodjenja.getStyleClass().removeAll("poljeIspravno");
                    mjestoRodjenja.getStyleClass().add("poljeNeispravno");
                    ispravnoMjesto = false;
                }
                else {
                    mjestoRodjenja.getStyleClass().removeAll("poljeNeispravno");
                    mjestoRodjenja.getStyleClass().add("poljeIspravno");
                    ispravnoMjesto = true;
                }
            }
        });
        smjerBoks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!isImePrezimeValidno(newValue) &&  newValue != ""){
                    smjerBoks.getStyleClass().removeAll("poljeIspravno");
                    smjerBoks.getStyleClass().add("poljeNeispravno");
                    smjerValidan = false;
                }
                else {
                    smjerBoks.getStyleClass().removeAll("poljeNeispravno");
                    smjerBoks.getStyleClass().add("poljeIspravno");
                    smjerValidan = true;
                }
            }
        });
        godinaStudija.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!isImePrezimeValidno(newValue) &&  newValue != ""){
                    godinaStudija.getStyleClass().removeAll("poljeIspravno");
                    godinaStudija.getStyleClass().add("poljeNeispravno");
                    godinaValidna = false;
                }
                else {
                    godinaStudija.getStyleClass().removeAll("poljeNeispravno");
                    godinaStudija.getStyleClass().add("poljeIspravno");
                    godinaValidna = true;
                }
            }
        });
        ciklusStudija.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!isImePrezimeValidno(newValue) &&  newValue != ""){
                    ciklusStudija.getStyleClass().removeAll("poljeIspravno");
                    ciklusStudija.getStyleClass().add("poljeNeispravno");
                    validanCiklus = false;
                }
                else {
                    ciklusStudija.getStyleClass().removeAll("poljeNeispravno");
                    ciklusStudija.getStyleClass().add("poljeIspravno");
                    validanCiklus = true;
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
            poruka = poruka + "Nekorektan broj indeksa!\n";
        if(!datumValidan || !jmbgValidan)
            poruka = poruka + "Datum i JMBG se ne podudaraju!\n";
        if(!telefonValidan)
            poruka = poruka + "Netočan broj telefona\n";
        if(!neBoks.isSelected() && !daBoks.isSelected())
            poruka = poruka + "Nijedan boks nije selektiran!\n";
        if(!Samofinancirajući.isSelected() && !redovan.isSelected())
            poruka = poruka + "Niste izbarali da li ste redovan ili samofinancirajući!\n";
        if(!ispravnoMjesto)
            poruka = poruka + "Neispravano mjeato rodjenja!\n";
        if(!smjerValidan || smjerBoks.getSelectionModel().getSelectedItem() == null)
            poruka = poruka + "Neispravan odsjek na fakultetu!\n";
        if(!godinaValidna || godinaStudija.getSelectionModel().getSelectedItem() == null)
            poruka = poruka + "Neispravna godina studija!\n";
        if(!validanCiklus || ciklusStudija.getSelectionModel().getSelectedItem() == null)
            poruka = poruka + "Neispravan ciklus studija!";
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
            System.out.println("Mjesto rodjenja: " + mjestoRodjenja.getSelectionModel().getSelectedItem());
            System.out.println("email: " + emailPolje.getText());
            System.out.println("Adresa: " + AdresaPolje.getText());
            System.out.println("Kontakt telefon: " + telefonpolje.getText());
            System.out.println("Odsjek na studiju: " + smjerBoks.getSelectionModel().getSelectedItem());
            System.out.println("Ciklus na studiju: " + ciklusStudija.getSelectionModel().getSelectedItem());
            System.out.println("Godina studija: " + godinaStudija.getSelectionModel().getSelectedItem());
            if(selekcija != null)
                System.out.println("Student" + selekcija + "boračkim kategorijama");
            if(status != null)
                System.out.println("Student je " + status + ".");
        }

    }

    public void choice1(ActionEvent actionEvent) {
        if(neBoks.isSelected())
            neBoks.setSelected(false);
        selekcija = " pripada ";
    }

    public void choice2(ActionEvent actionEvent) {
        if(daBoks.isSelected())
            daBoks.setSelected(false);
        selekcija = " ne pripada ";
    }

    public void redovan(ActionEvent actionEvent) {
        if(Samofinancirajući.isSelected())
            Samofinancirajući.setSelected(false);
        status = "redovan";
    }

    public void samofinancirajući(ActionEvent actionEvent) {
        if(redovan.isSelected())
            redovan.setSelected(false);
        status = "samofinancirajući";
    }

}
