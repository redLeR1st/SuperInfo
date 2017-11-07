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
import javafx.scene.control.ComboBox;
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

    Controller cnt = new Controller();

    ObservableList<String> kategorijaList = FXCollections.observableArrayList("Razno", "Mali");

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

            List<Integer> superInfoBrList = new ArrayList<>();
            for(String temp: Arrays.asList(superInfoBr.getText().split(","))) {
                superInfoBrList.add(Integer.parseInt(temp));
            }



            Add add = new Add(Integer.parseInt(dekBr.getText()), text.getText(), kategorija.getValue().toString(), mestoIzdavanje.getText(), superInfoBrList);
            Cust cust = new Cust(Integer.parseInt(brLicne.getText()), imePreziem.getText(), address.getText());

            System.out.println(cust.toString());
            System.out.println(add.toString());

            try {
                cnt.saveToDocxFile(add, cust);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong stuff", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
        }
    }


    @FXML
    public void initialize() {
        kategorija.setValue("Razno");
        kategorija.setItems(kategorijaList);
    }
}
