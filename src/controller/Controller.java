package controller;

import java.io.FileOutputStream;
import java.math.BigInteger;

/*
import java.util.List;
import java.util.Map;

import org.docx4j.Docx4J;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.ProtectDocument;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.CTOdsoFieldMapData;
import org.docx4j.wml.CTTblPrBase;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STDocProtect;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
*/

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
            run.setText("                                                   DEKLARACIJA br." + "10");
            run.addBreak();
            run.setText("Podaci oglasivača: " + "Barta Bence");
            run.addBreak();
            run.setText("Adresa stanovanja: " + "Csuka Zoltán 11. Zenta");
            run.addBreak();
            run.setText("Br.lične karte: " + "004516441" + "     mesto izdavanja: " + "Senta");
            run.addBreak();
            run.setText("Superinfo broj: " + "21" + "     Kategorija" + "Razno");
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
            cell0.setText("SZÖóóóVEG");


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
        }







/*
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();

        mdp.addParagraphOfText("SU-INFO MEDIA DOO TANČIČ MIHAJ 2.   SENTA  PIB:104 566 007   Tel.:024/815-411\n\n");
        mdp.addParagraphOfText(".                DEKLARACIJA br." + "10");
        mdp.addParagraphOfText("Podaci oglasivača: " + "Barta Bence");
        mdp.addParagraphOfText("Adresa stanovanja: " + "Csuka Zoltán 11. Zenta");
        mdp.addParagraphOfText("Br.lične karte: " + "004516441" + "     mesto izdavanja: " + "Senta");
        mdp.addParagraphOfText("Superinfo broj:" + "21" + "     Kategorija" + "Razno");
        mdp.addParagraphOfText("Tekst oglasa");
        mdp.addParagraphOfText("");

        /*Tbl table = n
        mdp.addObject(table);*/




        // To make a document Read-Only
        /*ProtectDocument protection = new ProtectDocument(wordMLPackage);
        protection.restrictEditing(STDocProtect.READ_ONLY, "foobaa");*/




/*
        String filename = System.getProperty("user.dir") + "\\protect.docx";
        Docx4J.save(wordMLPackage, new java.io.File(filename), Docx4J.FLAG_SAVE_ZIP_FILE);
*/
        // To save encrypted, you'd use FLAG_SAVE_ENCRYPTED_AGILE, for example:
        // Docx4J.save(wordMLPackage, new java.io.File(filename), Docx4J.FLAG_SAVE_ENCRYPTED_AGILE, "foo");


  //      System.out.println("Saved " + filename);

    }
    /*private Tbl getSampleTable(WordprocessingMLPackage wPMLpackage) {

        ObjectFactory factory = Context.getWmlObjectFactory();
        int writableWidthTwips = wPMLpackage.getDocumentModel().getSections()
                .get(0).getPageDimensions()
                .getWritableWidthTwips();
        List<Map<String, String>> data = getSampleTableData();
        TableDefinition tableDef = getSampleTableDef();
        int cols = tableDef.getColumns().size();
        int cellWidthTwips = new Double(
                Math.floor((writableWidthTwips / cols))
        ).intValue();

        Tbl table = TblFactory.createTable((data.size() + 1), cols, cellWidthTwips);

        Tr headerRow = (Tr) table.getContent().get(0);

        int f = 0;
        for (CTOdsoFieldMapData.Column column : tableDef.getColumns()) {
            Tc column = (Tc) headerRow.getContent().get(f);
            P columnPara = (P) column.getContent().get(0);
            f++;
            Text text = factory.createText();
            text.setValue(column.getName());
            R run = factory.createR();
            run.getContent().add(text);
            columnPara.getContent().add(run);
        }
        int i = 1;

        for (Map<String, String> entry : data) {
            Tr row = (Tr) table.getContent().get(i);
            i++;
            int d = 0;
            for (String key : entry.keySet()) {
                Tc column = (Tc) row.getContent().get(d);
                P columnPara = (P) column.getContent().get(0);
                d++;
                Text tx = factory.createText();
                R run = factory.createR();
                tx.setValue(entry.get(key));
                run.getContent().add(tx);
                columnPara.getContent().add(run);
            }
        }
        return table;
    }*/

}
