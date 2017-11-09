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


    public boolean saveToDocxFile(Add add, Cust cust) {

        boolean rvS = false;

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
            run.setText("Superinfo broj: " + add.getSuperInfoBroja() + "     Kategorija: " + add.getKategory());
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
            FileOutputStream output = new FileOutputStream("deklaracija.docx");
            document.write(output);
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                for(int temp : add.getSuperInfoBroja()) {
                    if(saveToFile(add.getText(), add.getKategory(), temp)) {
                        rvS = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rvS;
    }

    private boolean saveToFile(String text, String kategory, int superInfoBroja) throws IOException {

        boolean rvS = true;

        File yourFile = new File("SuperInfo" + superInfoBroja + ".txt");
        yourFile.createNewFile(); // if file already exists will do nothing
        //FileOutputStream oFile = new FileOutputStream(yourFile, false);

        Path path = null;
        Charset charset = null;
        try {
            path = Paths.get("SuperInfo" + superInfoBroja + ".txt");
            charset = StandardCharsets.UTF_8;
        } catch (Exception e) {
            e.printStackTrace();
            rvS = false;
        }
        String content = new String(Files.readAllBytes(path), charset);
        if(content.contains(kategory)) {
            content = content.replaceAll(kategory + "\r\n", kategory + "\r\n" + text + "\r\n");
            Files.write(path, content.getBytes(charset));
        } else {
            content += kategory + "\r\n" + text + "\r\n";
            Files.write(path, content.getBytes(charset));
        }



        return rvS;
    }


}
