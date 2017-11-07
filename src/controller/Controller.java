package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import modell.bean.Add;
import modell.bean.Cust;

import  org.apache.poi.xwpf.usermodel.XWPFDocument;
import  org.apache.poi.xwpf.usermodel.XWPFParagraph;
import  org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class Controller {


    public void saveToDocxFile(Add add, Cust cust) {

        XWPFDocument document = new XWPFDocument();

        for (int i = 0; i < 2; i++) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            run.setText("SU-INFO MEDIA DOO TANČIČ MIHAJ 2.   SENTA  PIB:104 566 007   Tel.:024/815-411");
            run.addBreak();
            run.addBreak();
            run.addBreak();
            run.setText("                                                   DEKLARACIJA br." + add.getDeklarationNo());
            run.addBreak();
            run.setText("Podaci oglasivača: " + cust.getName());
            run.addBreak();
            run.setText("Adresa stanovanja: " + cust.getAddress());
            run.addBreak();
            run.setText("Br.lične karte: " + cust.getLicnaNo() + "     mesto izdavanja: " + add.getMestoIzdavanja());
            run.addBreak();
            run.setText("Superinfo broj: " + add.getSuperInfoBroja() + "     Kategorija" + add.getKategory());
            run.addBreak();
            run.setText("Tekst oglasa:");//run.addBreak();

            XWPFTable table = document.createTable();

            // Width of the table
            CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
            width.setType(STTblWidth.DXA);
            width.setW(BigInteger.valueOf(9072));
            // ***

            // Height of the table
            XWPFTableRow row0 = table.getRow(0);
            row0.setHeight(2000);
            XWPFTableCell cell0 = row0.getCell(0);
            // ***
            cell0.setText(add.getText());


            XWPFParagraph paragraph2 = document.createParagraph();
            run = paragraph2.createRun();

            run.setText("Oglasa predao/la                                                                                                     Oglas preuzeo/la");
            run.addBreak();
            run.setText("________________________                                                               ________________________");
            run.addBreak();
        }


        try {
            FileOutputStream output = new FileOutputStream("Awesome.docx");
            document.write(output);
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                saveToFile(add.getText(), add.getKategory());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToFile(String text, String kategory) throws IOException {
        File yourFile = new File("test.txt");
        yourFile.createNewFile(); // if file already exists will do nothing
        //FileOutputStream oFile = new FileOutputStream(yourFile, false);



        Path path = Paths.get("test.txt");
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
        if(content.contains(kategory)) {
            content = content.replaceAll(kategory + "\r\n", kategory + "\r\n" + text + "\r\n");
            Files.write(path, content.getBytes(charset));
        } else {
            content += kategory + "\r\n" + text + "\r\n";
            Files.write(path, content.getBytes(charset));
        }
    }


}
