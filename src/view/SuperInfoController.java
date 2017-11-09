package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modell.bean.Add;
import modell.bean.Cust;

public class SuperInfoController {

    @FXML private TextField dekBr;
    @FXML private TextField imePreziem;
    @FXML private TextField address;
    @FXML private TextField brLicne;
    @FXML private TextField mestoIzdavanje;
    @FXML private TextField superInfoBr;
    @FXML private ComboBox kategorija;
    @FXML private TextArea text;
    @FXML private Label label;
    @FXML private CheckBox checkBox;
    @FXML private TextField drugaKategorija;

    Controller cnt = new Controller();

    ObservableList<String> kategorijaList = FXCollections.observableArrayList("Nekretnina", "Voćnjak, Oranica",
            "Izadje se", "Letovanje", "Usluga", "Vozilo", "Posao", "Társkereső", "Razno", "Otkup");

    public void butClick() {
        if (!("".equals(dekBr.getText()) ||
              "".equals(imePreziem.getText()) ||
              "".equals(address.getText()) ||
              "".equals(brLicne.getText()) ||
              "".equals(mestoIzdavanje.getText()) ||
              "".equals(superInfoBr.getText()) ||
              "".equals(kategorija.getValue().toString()) ||
              "".equals(text.getText())
        )){

            try {
                Integer.parseInt(dekBr.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: számot kell megadni: deklaracija broj", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            try {
                Integer.parseInt(brLicne.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: számot kell megadni: broj licne karte", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(!(brLicne.getText().length() == 10)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: a broj licne karte 10 számjegyből áll", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            String precondition = superInfoBr.getText().replaceAll(" ", "");

            List<Integer> superInfoBrList = new ArrayList<>();
            for(String temp: Arrays.asList(precondition.split(","))) {
                if(1 <= Integer.parseInt(temp) && Integer.parseInt(temp) <= 23 && !superInfoBrList.contains(Integer.parseInt(temp)))
                    superInfoBrList.add(Integer.parseInt(temp));
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: csak 1 és 23 közötti szám elfogadható vesszővel elválasztva", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            }

            String kat = null;

            if (!checkBox.isSelected()) {
                kat = kategorija.getValue().toString();
            } else if (!"".equals(drugaKategorija.getText())){
                kat = drugaKategorija.getText();
                kategorijaList.add(kat);
                initialize();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: minden mezőt ki kell tölteni!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            Add add = new Add(Integer.parseInt(dekBr.getText()), text.getText(), kat, mestoIzdavanje.getText(), superInfoBrList);
            Cust cust = new Cust(brLicne.getText(), imePreziem.getText(), address.getText());

            System.out.println(cust.toString());
            System.out.println(add.toString());

            try {
                if(cnt.saveToDocxFile(add, cust)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "A mentés sikeres!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: a mentés sikertelen!", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: minden mezőt ki kell tölteni!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void countCena() {
        int cena = 0;
        String[] words = text.getText().split(" ");

        for(String temp : words) {
            if(!(temp.matches("\\d+")) && temp.length() >= 3 ) {
                cena += 20;
            }
        }

        label.setText(cena + " din");
    }

    @FXML
    public void initialize() {
        kategorija.setValue("Razno");
        kategorija.setItems(kategorijaList);

        text.textProperty().addListener((observable, oldValue, newValue) -> {
            countCena();
        });
    }


}
