package modell.bean;

import java.util.ArrayList;
import java.util.List;

public class Add {

    private int deklarationNo;
    private String text;
    private String kategory;
    private String mestoIzdavanja;
    private List<Integer> superInfoBroja;

    private String folder;

    public Add() {
        superInfoBroja = new ArrayList<Integer>(){
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    public Add(int deklarationNo, String text, String kategory, String mestoIzdavanja, List<Integer> superInfoBroja, String folder) {
        this.deklarationNo = deklarationNo;
        this.text = text;
        this.kategory = kategory;
        this.mestoIzdavanja = mestoIzdavanja;
        this.superInfoBroja = superInfoBroja;
        this.folder = folder;
    }

    public int getDeklarationNo() {
        return deklarationNo;
    }

    public void setDeklarationNo(int deklarationNo) {
        this.deklarationNo = deklarationNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKategory() {
        return kategory;
    }

    public void setKategory(String kategory) {
        this.kategory = kategory;
    }

    public String getMestoIzdavanja() {
        return mestoIzdavanja;
    }

    public void setMestoIzdavanja(String mestoIzdavanja) {
        this.mestoIzdavanja = mestoIzdavanja;
    }

    public List<Integer> getSuperInfoBroja() {
        return superInfoBroja;
    }

    public void setSuperInfoBroja(List<Integer> superInfoBroja) {
        this.superInfoBroja = superInfoBroja;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "Add{" +
                "deklarationNo=" + deklarationNo +
                ", text='" + text + '\'' +
                ", kategory='" + kategory + '\'' +
                ", mestoIzdavanja='" + mestoIzdavanja + '\'' +
                ", superInfoBroja=" + superInfoBroja +
                ", folder= " + folder +
                '}';
    }
}
