package view;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPImpl;

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

    public void print() {
        if (butClick()) {

            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.PRINT))
                try {
                    desktop.print(new File("deklaracija.docx"));
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: Hiba történt a nyomtatás során", ButtonType.OK);
                    alert.showAndWait();
                }
        }
    }

    public boolean butClick() {
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
                return false;
            }

            try {
                Integer.parseInt(brLicne.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: számot kell megadni: broj licne karte", ButtonType.OK);
                alert.showAndWait();
                return false;
            }

            if(!(brLicne.getText().length() == 9)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: a broj licne karte 9 számjegyből áll", ButtonType.OK);
                alert.showAndWait();
                return false;
            } else if (!(new Character('0').equals(brLicne.getText().charAt(0)) && new Character('0').equals(brLicne.getText().charAt(1)))) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: a broj licne karte első két számjegye: 0", ButtonType.OK);
                alert.showAndWait();
                return false;
            }

            String precondition = superInfoBr.getText().replaceAll(" ", "");

            List<Integer> superInfoBrList = new ArrayList<>();
            for(String temp: Arrays.asList(precondition.split(","))) {
                try {
                    if (1 <= Integer.parseInt(temp) && Integer.parseInt(temp) <= 23 && !superInfoBrList.contains(Integer.parseInt(temp)))
                        superInfoBrList.add(Integer.parseInt(temp));
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: csak 1 és 23 közötti szám elfogadható vesszővel elválasztva", ButtonType.OK);
                        alert.showAndWait();
                        return false;
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: SuperInfo broj nem megfelelően lett kitöltve.\n" +
                            "Megfelelő formátum: 1 és 23 közötti szám vesszővel elválasztva pl.: 1,2,3,4,5,6", ButtonType.OK);
                    alert.showAndWait();
                    return false;
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
                return false;
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
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: a mentés/nyomtatás sikertelen! Lehet, hogy nem zárta be a Word-öt", ButtonType.OK);
                    alert.showAndWait();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (readPrevDekBroj() != null) {
                dekBr.setText(readPrevDekBroj());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba: minden mezőt ki kell tölteni!", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        return  true;
    }

    public void countCena() {
        int cena = 0;
        String[] words = text.getText().split(" ");

        for(String temp : words) {
            if(!(temp.matches("\\d+")) && temp.length() >= 3 ) {
                cena += 20;
            }
        }

        label.setText("" + cena);
    }

    @FXML
    public void initialize() {
        kategorija.setValue("Razno");
        kategorija.setItems(kategorijaList);

        text.textProperty().addListener((observable, oldValue, newValue) -> {
            countCena();
        });

        if (readPrevDekBroj() != null) {
            dekBr.setText(readPrevDekBroj());
        }
    }

    private String readPrevDekBroj() {

        StringBuffer prevDek = new StringBuffer("");

        try {
            FileInputStream input = new FileInputStream("deklaracija.docx");
            XWPFDocument document = new XWPFDocument(input);

            XWPFWordExtractor we = new XWPFWordExtractor(document);
            for (int i = 15; /*!("\n".equals(we.getText().charAt(we.getText().indexOf("DEKLARACIJA br.") + i)))*/; i++) {
                System.out.println(we.getText().charAt(we.getText().indexOf("DEKLARACIJA br.") + i));
                try {
                    Integer.parseInt(we.getText().substring(we.getText().indexOf("DEKLARACIJA br.") + i, we.getText().indexOf("DEKLARACIJA br.") + i + 1));
                    prevDek.append(we.getText().substring(we.getText().indexOf("DEKLARACIJA br.") + i, we.getText().indexOf("DEKLARACIJA br.") + i + 1));
                } catch (Exception e) {
                    break;
                }
            }
            return Integer.toString(Integer.parseInt(prevDek.toString())+1);

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nem találtam előző deklarációt (deklaracija.docx) a DEKLARACIJA br. automatikus kitöltése nem sikerült, kézzel kell kitölteni", ButtonType.OK);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nem találtam előző deklarációt (deklaracija.docx) a DEKLARACIJA br. automatikus kitöltése nem sikerült, kézzel kell kitölteni", ButtonType.OK);
            alert.showAndWait();
        }
        return null;
    }

}
