/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import DBConnection.DBConnection;
import DBConnection.ReportDB;
import GUI.PrintTest2;
import Helper.Session;
import Process.MainRetrieval;
import com.itextpdf.text.*;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import library.Func;
import org.joda.time.DateTime;
import org.joda.time.Period;

public class PDFiText {

    private static String FILE = "FirstPdf.pdf";
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD);
    private static Font subtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);
    private static Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 9,
            Font.NORMAL);
    private static Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN, 9,
            Font.BOLD);
    
    private static Font labelFont = new Font(Font.FontFamily.TIMES_ROMAN,9);
    private static Font labelTitle = new Font(Font.FontFamily.TIMES_ROMAN,5);
    
    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Pharmacy Prescription");
        document.addSubject("Pharmacy Prescription");
        document.addKeywords("Pharmacy, Drugs, Drug, Dispense");
        document.addAuthor("Clinical Support System");
        document.addCreator("Prof. Madya Dr. Mohd. Khanapi Bin Abd. Ghani");
    }
    
    private static void addMetaDataMC(Document document) {
        document.addTitle("Medical Certification");
        document.addSubject("Medical Certification (MC)");
        document.addKeywords("MC, Medical, Medical Certification");
        document.addAuthor("Clinical Support System");
        document.addCreator("Prof. Madya Dr. Mohd. Khanapi Bin Abd. Ghani");
    }
    
    private static void addMetaDataICD10(Document document) {
        document.addTitle("Report ICD10");
        document.addSubject("Report ICD10");
        document.addKeywords("Report, ICD10, Report ICD10");
        document.addAuthor("Clinical Support System");
        document.addCreator("Prof. Madya Dr. Mohd. Khanapi Bin Abd. Ghani");
    }
    
    private static void addMetaDataTimeSlip(Document document) {
        document.addTitle("Time Slip");
        document.addSubject("Time Slip (TS)");
        document.addKeywords("TS, Time, Slip, Time Slip");
        document.addAuthor("Clinical Support System");
        document.addCreator("Prof. Madya Dr. Mohd. Khanapi Bin Abd. Ghani");
    }

    private static void addTitlePage(Document document, String data)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        
        Image image1 = null;
        try {
            image1 = Image.getInstance("assets/logoUTeMPNG.png");
            image1.scaleAbsolute(image1.getWidth()*0.05f, image1.getHeight()*0.05f);
        } catch (BadElementException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        }
        image1.setAlignment(Element.ALIGN_CENTER);
        preface.add(image1);
        
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        
        String hospital = "** No Health Facility **";
        
        MainRetrieval mr = new MainRetrieval();
        mr.startProcess(data);
        String patient[][] = mr.getData("DTO");
        int row = mr.getRowNums();
        if (row > 0) {
            hospital = patient[0][29];
        }
        
        Paragraph title = new Paragraph(hospital, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);
        
        addEmptyLine(preface, 1);
        
        Paragraph subtitle = new Paragraph("Drug Prescription", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        preface.add(subtitle);
        
        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static void addDataMC(Document document, String data, ArrayList<String> masa) 
            throws DocumentException {
        Paragraph preface = new Paragraph();
        
        String name = "-";
        String nokp = "-";
        String time1 = "-";
        String time2 = "-";
        String diagnosis = "-";
        String tarikh = "-";
        
        MainRetrieval mr = new MainRetrieval();
        mr.startProcess(data);
        String patient[][] = mr.getData("PDI");
        int row = mr.getRowNums();
        if (row > 0) {
            name = patient[0][1];
            nokp = patient[0][2];
        }
        
        MainRetrieval mr2 = new MainRetrieval();
        mr2.startProcess(data);
        String patient2[][] = mr2.getData("CCN");
        int row2 = mr2.getRowNums();
        if (row2 > 0) {
            diagnosis = patient2[0][2];
            tarikh = Func.timestampToDate(patient2[0][0]);
        }
        
        int line_paragraph = 4;
        Paragraph subtitle[] = new Paragraph[line_paragraph];
        String ayat[] = new String[line_paragraph];
        
        int diffDate = Func.getDiffDate1(masa.get(2), masa.get(3)) + 1;
        
        
        ayat[0] = "NAMA: "+name.toUpperCase();
        ayat[1] = "NO. K/P: "+nokp.toUpperCase();
        ayat[2] = "Tidak sihat untuk menjalankan tugas selama "+diffDate+" hari";
//        ayat[2] = "MENDAPAT RAWATAN DI KLINIK INI DARI JAM "+masa.get(0) +" HINGGA "+masa.get(1);
//        ayat[3] = "DIAGNOSA: "+diagnosis.toUpperCase();
        ayat[3] = "dari "+masa.get(2)+" hingga "+masa.get(3);
        
        for (int i = 0; i < line_paragraph; i++) {
            subtitle[i] = new Paragraph(ayat[i], subtitleFont);
            subtitle[i].setAlignment(Element.ALIGN_CENTER);
            preface.add(subtitle[i]);
        }

        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static void addDataICD10(Document document, String date) 
            throws DocumentException {
        Paragraph preface = new Paragraph();
        
        try {
            
            //CCN, DGS
            String data = ReportDB.getDataICD10(date);
            
            String str[] = data.split("___");
            for (int i = 0; i < str.length; i++) {
                String str2[] = str[i].split("______");
                for (int j = 0; j < str2.length; j++) {
                    preface.add(getPara(str2[j], Element.ALIGN_LEFT));
                }
            }
            
        } catch (Exception e) {
            //e.printStackTrace();
        }
        
        //System.exit(1);
        
        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static Paragraph getPara(String ayat, int align) {
        Paragraph subtitle = new Paragraph(ayat, subtitleFont);
        subtitle.setAlignment(align);
        return subtitle;
    }
    
    private static void addDataTimeSlip(Document document, String data, ArrayList<String> masa) 
            throws DocumentException {
        Paragraph preface = new Paragraph();
        
        String name = "-";
        String nokp = "-";
        String time1 = "-";
        String time2 = "-";
        String diagnosis = "-";
        String tarikh = "-";
        
        MainRetrieval mr = new MainRetrieval();
        mr.startProcess(data);
        String patient[][] = mr.getData("PDI");
        int row = mr.getRowNums();
        if (row > 0) {
            name = patient[0][1];
            nokp = patient[0][2];
        }
        
        MainRetrieval mr2 = new MainRetrieval();
        mr2.startProcess(data);
        String patient2[][] = mr2.getData("CCN");
        int row2 = mr2.getRowNums();
        if (row2 > 0) {
            diagnosis = patient2[0][2];
            tarikh = Func.timestampToDate(patient2[0][0]);
        }
        
        int line_paragraph = 5;
        Paragraph subtitle[] = new Paragraph[line_paragraph];
        String ayat[] = new String[line_paragraph];
        
        //int diffDate = Func.getDiffDate1(masa.get(2), masa.get(3)) + 1;
        
        
        ayat[0] = "Kepada: "+name.toUpperCase();
        ayat[1] = "Saya sahkan bahawa pesakit bernama: "+name.toUpperCase();
        ayat[2] = "NO. K/P: "+nokp.toUpperCase();
        ayat[3] = "Mendapat rawatan di klinik dari jam "+masa.get(0) +" hingga "+masa.get(1);
        ayat[4] = "Diagnosa: "+diagnosis.toUpperCase();
        //ayat[5] = "Dari "+masa.get(2)+" hingga "+masa.get(3);
        
        for (int i = 0; i < line_paragraph; i++) {
            subtitle[i] = new Paragraph(ayat[i], subtitleFont);
            subtitle[i].setAlignment(Element.ALIGN_CENTER);
            preface.add(subtitle[i]);
        }

        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static void addTitleMC(Document document, String data)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        
        Image image1 = null;
        try {
            image1 = Image.getInstance("assets/logoUTeMPNG.png");
            image1.scaleAbsolute(image1.getWidth()*0.05f, image1.getHeight()*0.05f);
        } catch (BadElementException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        }
        image1.setAlignment(Element.ALIGN_CENTER);
        preface.add(image1);
        
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        
        String hospital = "****";
        String code_mc = "****";
        
        MainRetrieval mr[] = new MainRetrieval[2];
        int row[] = new int[2];
        String patient[][][] = new String[2][][];
        
        mr[0] = new MainRetrieval();
        mr[0].startProcess(data);
        patient[0] = mr[0].getData("CCN");
        row[0] = mr[0].getRowNums();
        
        mr[1] = new MainRetrieval();
        mr[1].startProcess(data);
        patient[1] = mr[1].getData("DGS");
        row[1] = mr[1].getRowNums();
        
        System.out.println("row "+row[0]+" row2 "+row[1]);
        if (row[0] > 0) {
            hospital = patient[0][0][16];
            code_mc = patient[0][0][0];
        } else if (row[1] > 0) {
            hospital = patient[1][0][23];
            code_mc = patient[0][0][0];
        }
        
        Paragraph title = new Paragraph(hospital, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);
        
        addEmptyLine(preface, 1);
        
        Paragraph subtitle = new Paragraph("SIJIL CUTI SAKIT (Kod: "+code_mc+")", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        preface.add(subtitle);
        
        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static void addTitleICD10(Document document, String date)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        
        Image image1 = null;
        try {
            image1 = Image.getInstance("assets/logoUTeMPNG.png");
            image1.scaleAbsolute(image1.getWidth()*0.05f, image1.getHeight()*0.05f);
        } catch (BadElementException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        }
        image1.setAlignment(Element.ALIGN_CENTER);
        preface.add(image1);
        
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header

        preface.add(getPara("LAPORAN ICD10", Element.ALIGN_CENTER));
        
        String date1[] = date.split(" ")[0].split("-");
        String year = date1[0];
        String month = date1[1];
        String day = date1[2];
        
        preface.add(getPara("Year: "+year, Element.ALIGN_CENTER));
        
        if (!month.equals("00")) {
            preface.add(getPara("Month: "+month, Element.ALIGN_CENTER));
            if (!day.equals("00")) {
                preface.add(getPara("Day: "+day, Element.ALIGN_CENTER));
            }
        }
        
        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static void addTitleTimeSlip(Document document, String data)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        Image image1 = null;
        try {
            image1 = Image.getInstance("assets/logoUTeMPNG.png");
            image1.scaleAbsolute(image1.getWidth() * 0.03f, image1.getHeight() * 0.03f);
        } catch (BadElementException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFiText.class.getName()).log(Level.SEVERE, null, ex);
        }
        image1.setAlignment(Element.ALIGN_CENTER);
        preface.add(image1);

        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header

        String hospital = "****";

        MainRetrieval mr[] = new MainRetrieval[2];
        int row[] = new int[2];
        String patient[][][] = new String[2][][];

        mr[0] = new MainRetrieval();
        mr[0].startProcess(data);
        patient[0] = mr[0].getData("CCN");
        row[0] = mr[0].getRowNums();

        mr[1] = new MainRetrieval();
        mr[1].startProcess(data);
        patient[1] = mr[1].getData("DGS");
        row[1] = mr[1].getRowNums();

        System.out.println("row " + row[0] + " row2 " + row[1]);
        if (row[0] > 0) {
            hospital = patient[0][0][16];
        } else if (row[1] > 0) {
            hospital = patient[1][0][23];
        }

        Paragraph title = new Paragraph(hospital, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);

        addEmptyLine(preface, 1);

        Paragraph subtitle = new Paragraph("SURAT MENDAPAT RAWATAN", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        preface.add(subtitle);

        addEmptyLine(preface, 1);

        document.add(preface);
    }
    
    private static PdfPCell getCell(PdfPTable ptable, String word, 
            int align_type, int content_type) {
        PdfPCell pcell;
        switch(content_type) {
            case 1:
                pcell = new PdfPCell(new Phrase(word, contentFont));
                break;
            case 2:
                pcell = new PdfPCell(new Phrase(word, footerFont));
                break;
            default:
                pcell = new PdfPCell(new Phrase(word, contentFont));
                break;
        }
        switch(align_type) {
            case 1:
                pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
            case 2:
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                break;
            case 3:
                pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                break;
            case 4:
                pcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                break;
            default:
                pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
        }
        pcell.setBorder(Rectangle.NO_BORDER);
        return pcell;
    }
    
    private static void addTablePatient(Document document, String data) throws DocumentException {
        int num_col = 7;
        int num_row = 6;

        PdfPTable table = new PdfPTable(num_col);
        
        String name = "";
        String pid = "";
        String age = "";
        String ic = "";
        String gender = "";
        String weight = "";
        String subdiscipline = "";
        String height = "";
        String presno = "";
        String orderdate = "";
        String allergy = "";
        
        MainRetrieval mr = new MainRetrieval();
        mr.startProcess(data);
        String patient[][] = mr.getData("PDI");
        int row = mr.getRowNums();
        if(row > 0) {
            name = patient[0][1];
            pid = patient[0][0];
            String tarikhlahir[] = patient[0][5].split("/");
            String tarikhhariini[] = new Date(new java.util.Date().getTime()).toString().split(" ");
            age = String.valueOf(Integer.parseInt(tarikhhariini[5]) - Integer.parseInt(tarikhlahir[2]));
            ic = patient[0][2];
            gender = patient[0][4];
        }

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        String cell[][] = {
            {"Patient Name", ":", name,
            "", "Patient Id", ":", pid},
            {"Age", ":", age+"Y",
            "", "NRIC", ":", ic},
            {"Gender", ":", gender,
            "", "Weight", ":", weight},
            {"Location", ":", subdiscipline,
            "", "Height", ":", height},
            {"Prescription #", ":", presno,
            "", "Order Date", ":", orderdate},
            {"Allergy", ":", allergy,
            "", "", "", ""}
        };
        
        for (int i = 0; i < num_row; i++) {
            for (int j = 0; j < num_col; j++) {
                table.addCell(getCell(table, cell[i][j], 1, 1));
            }
        }

        document.add(table);
        
        addHorizontalLine(document);
        document.add(new Paragraph(" "));
    }
    
    private static void addHorizontalLine(Document document) throws DocumentException {
        String line = "_";
        for (int i = 0; i < 100; i++) {
            line += "_";
        }
        document.add(new Paragraph(line, subtitleFont));
    }

    private static void addTableDrugs(Document document, String data) throws DocumentException {
        int num_col = 9;
        int num_row = 0;
        
        PdfPTable table = new PdfPTable(num_col);

        MainRetrieval mr = new MainRetrieval();
        mr.startProcess(data);
        String dto[][] = mr.getData("DTO");
        int row = mr.getRowNums();
        if (row > 0) {
            num_row = row;
        }
        
        String header[] = {"No.", "Drug", "Route", "Dosage", "Frequency", "Start Date", 
            "End Date", "Duration", "Prescribed Quantity"};
        
        PdfPCell c1;
        
        //add header column
        for(int i = 0; i < num_col; i++) {
            table.addCell(getCell(table, header[i], 1, 1));
        }
        
        //add data row by row
        for(int i = 0; i < num_row; i++) {
            table.addCell(getCell(table, (i+1)+".", 1, 1));
            table.addCell(getCell(table, dto[i][5], 1, 1));
            table.addCell(getCell(table, dto[i][12], 1, 1));
            table.addCell(getCell(table, dto[i][17], 1, 1));
            table.addCell(getCell(table, dto[i][14], 1, 1));
            table.addCell(getCell(table, getDate(dto[i][0], 0), 1, 1));
            table.addCell(getCell(table, getDate(dto[i][0], getDay(dto[i][22])), 1, 1));
            table.addCell(getCell(table, dto[i][22] + " day" + getS(dto[i][22]), 1, 1));
            table.addCell(getCell(table, dto[i][23], 1, 1));
        }

        document.add(table);

        addHorizontalLine(document);
        document.add(new Paragraph(" "));
    }
    
    private static void addFooter(Document document, String data) throws DocumentException {
        int num_col = 2;
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String now = dateFormat.format(today.getTime());
        PdfPTable table = new PdfPTable(num_col);

        String header[] = {"Prescribed by: "+Session.getUser_name()+" ("+Session.getUser_id()+")", 
            "Date: "+now};

        PdfPCell c1;

        table.addCell(getCell(table, header[0], 1, 2));
        table.addCell(getCell(table, header[1], 3, 2));

        document.add(table);

        document.add(new Paragraph(" "));
    }
    
    private static void addFooterMC(Document document, String data) throws DocumentException {
        int num_col = 2;
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String now = dateFormat.format(today.getTime());
        PdfPTable table = new PdfPTable(num_col);

        String header[] = {"Disediakan oleh: "+Session.getUser_name()+" ("+Session.getUser_id()+")", 
            "Tarikh: "+now};

        PdfPCell c1;

        table.addCell(getCell(table, header[0], 1, 2));
        table.addCell(getCell(table, header[1], 3, 2));
        
        Paragraph preface = new Paragraph();
        
        //addEmptyLine(preface, 1);
        //addEmptyLine(preface, 1);
        
        String clinic = "-";
        
        MainRetrieval mr = new MainRetrieval();
        mr.startProcess(data);
        String patient[][] = mr.getData("CCN");
        int row = mr.getRowNums();
        if (row > 0) {
            clinic = patient[0][16];
        }
        
        String signature = ""
                + "\n..........................................."
                + "\n"+Session.getUser_name().toUpperCase()
                + "\n"+clinic.toUpperCase();
        
        Paragraph subtitle = new Paragraph(signature, subtitleFont);
        subtitle.setAlignment(Element.ALIGN_RIGHT);
        preface.add(subtitle);

        //addEmptyLine(preface, 1);
        
        document.add(preface);

        //document.add(new Paragraph(" "));
        
        document.add(table);
        
        //document.add(new Paragraph(" "));
    }
    
    private static void addFooterICD10(Document document) throws DocumentException {
        int num_col = 2;
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String now = dateFormat.format(today.getTime());
        PdfPTable table = new PdfPTable(num_col);

        String header[] = {"Disediakan oleh: "+Session.getUser_name()+" ("+Session.getUser_id()+")", 
            "Tarikh: "+now};

        PdfPCell c1;

        table.addCell(getCell(table, header[0], 1, 2));
        table.addCell(getCell(table, header[1], 3, 2));
        
        Paragraph preface = new Paragraph();
        
        //addEmptyLine(preface, 1);
        //addEmptyLine(preface, 1);
        
        String clinic = Session.getHfc_code();
        
        String signature = ""
                + "\n............................................."
                + "\nPEGAWAI PERUBATAN / PEN. PEG. PERUBATAN"
                + "\n"+clinic.toUpperCase();
        
        Paragraph subtitle = new Paragraph(signature, subtitleFont);
        subtitle.setAlignment(Element.ALIGN_RIGHT);
        preface.add(subtitle);

        //addEmptyLine(preface, 1);
        
        document.add(preface);

        //document.add(new Paragraph(" "));
        
        document.add(table);
        
        //document.add(new Paragraph(" "));
    }
    
    private static String getDate(String date, int period) {
        String s1 = "00/00/0000";
        try {
            String s2[] = date.split(" ");
            String s3[] = s2[0].split("-");
            int day = Integer.parseInt(s3[2]);
            int month = Integer.parseInt(s3[1]);
            int year = Integer.parseInt(s3[0]);
            
            DateTime dt = new DateTime(year, month, day, 0, 0, 0, 0);
            Period nextDay = Period.days(period);
            DateTime dt1 = dt.plus(nextDay);
            
            s1 = String.valueOf(dt1.getDayOfMonth() + "/" + dt1.getMonthOfYear() + "/" + dt1.getYear());
            
            //s1 = s3[2] + "/" + s3[1] + "/" + s3[0];
        } catch (Exception e) {
            s1 = "00/00/0000";
            e.printStackTrace();
        }
        return s1;
    }
    
    private static String getS(String s1) {
        String s = "";
        try {
            double d1 = Double.parseDouble(s1);
            if(d1 > 1) {
                return "s";
            }
        } catch (Exception e) {
            s = "";
            e.printStackTrace();
        }
        return s;
    }
    
    private static int getDay(String s) {
        int s1 = 0;
        try {
            s1 = Integer.parseInt(s) - 1;
        } catch (Exception e) {
            s1 = 0;
            e.printStackTrace();
        }
        return s1;
    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static void createPrescription(String title, String data_temp) {
        try {
            Document document = new Document(PageSize.A5.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(title));
            document.open();
            addMetaData(document);
            addTitlePage(document, data_temp);
            addTablePatient(document, data_temp);
            addTableDrugs(document, data_temp);
            addFooter(document, data_temp);
            document.close();
            
            PrintTest2.print3(title);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void createMC(String title, String data_temp, ArrayList<String> masa) {
        try {
            Document document = new Document(PageSize.A5.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(title));
            document.open();
            addMetaDataMC(document);
            addTitleMC(document, data_temp);
            addDataMC(document, data_temp, masa);
            addFooterMC(document, data_temp);
            document.close();

            PrintTest2.print3(title);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        //Fn to create rpt of dispensed drug -- Hariz 20141122
    public static void createDispensedDrug()
    {
        try
        {
           String strSql = "select order_no, order_date, dispensed_by from pis_dispense_master, pis_dispense_detail"
                   + " where order_date < CURDATE()";
        }catch (Exception ex)
        {
             ex.printStackTrace();
        }
    }
    //Fn to create rpt of dispensed drug -- Hariz 20141122 END
    
    //    Fn to print out medicine label --Hariz 20141014
    public static void createPrescriptionLabel(String headerTitle, String pName, String orderDate,String orderNo) throws FileNotFoundException, DocumentException, SQLException
    {
        String sqlGetDrugPresc = "";
        
        Rectangle customRec = new Rectangle(238f, 138f);
        Document document = new Document(customRec,1,1,2,3);
        PdfWriter.getInstance(document,new FileOutputStream(headerTitle));
        document.open();
        
       
        
        try{
            sqlGetDrugPresc = "Select * from pis_order_detail where order_no = '"+ orderNo +"'";  
            
            String a[] = {};
            ArrayList<ArrayList<String>> fromRmi = DBConnection.getImpl().getQuery(sqlGetDrugPresc, 17, a);           
            for(int i = 0 ; i < fromRmi.size() ; i++)
            {
                Paragraph mainContent = new Paragraph();

                Paragraph clinicName = new Paragraph("Klinik Utem Induk",labelTitle);
                //clinicName.setFont(labelTitle);
                clinicName.setAlignment(Element.ALIGN_CENTER);

                mainContent.add(clinicName);
                //addEmptyLine(mainContent,1);
                Paragraph ptnName = new Paragraph("Name :"+pName,labelFont);
                ptnName.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(ptnName);

                Paragraph oDate = new Paragraph("Date :"+orderDate.substring(0,10),labelFont);
                oDate.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(oDate);
                
                Paragraph descrp1 = new Paragraph(fromRmi.get(i).get(2),labelFont);
                descrp1.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descrp1);

                Paragraph descrp2 = new Paragraph(fromRmi.get(i).get(4),labelFont);
                descrp2.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descrp2);

                Paragraph descOrderOUM = new Paragraph(fromRmi.get(i).get(8),labelFont);
                descOrderOUM.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descOrderOUM);

                Paragraph descDrugStrength = new Paragraph(fromRmi.get(i).get(6),labelFont);
                descDrugStrength.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descDrugStrength);

                document.add(mainContent);
                if(i != fromRmi.size()- 1)
                    document.newPage();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
                     
            try 
            {
            sqlGetDrugPresc = "Select * from pis_order_detail where order_no = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sqlGetDrugPresc);
            ps.setString(1, orderNo);
            ResultSet rs = ps.executeQuery();          

            for(int i=0;rs.next();i++)
            { 
                Paragraph mainContent = new Paragraph();

                Paragraph clinicName = new Paragraph("Klinik Utem Induk",labelTitle);
                //clinicName.setFont(labelTitle);
                clinicName.setAlignment(Element.ALIGN_CENTER);

                mainContent.add(clinicName);
                //addEmptyLine(mainContent,1);
                Paragraph ptnName = new Paragraph("Name :"+pName,labelFont);
                ptnName.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(ptnName);

                Paragraph oDate = new Paragraph("Date :"+orderDate.substring(0,10),labelFont);
                oDate.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(oDate);
                
                Paragraph descrp1 = new Paragraph(rs.getString("DRUG_ITEM_DESC"),labelFont);
                descrp1.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descrp1);
                
                Paragraph descrp2 = new Paragraph(rs.getString("DRUG_FREQUENCY"),labelFont);
                descrp2.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descrp2);
                
                Paragraph descOrderOUM = new Paragraph(rs.getString("ORDER_OUM"),labelFont);
                descOrderOUM.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descOrderOUM);
                
                Paragraph descDrugStrength = new Paragraph(rs.getString("DRUG_STRENGTH"),labelFont);
                descDrugStrength.setAlignment(Element.ALIGN_LEFT);
                mainContent.add(descDrugStrength);
                
                document.add(mainContent);
                if(!rs.isLast())
                    document.newPage();
            }                          
            } catch (DocumentException exx) {
                exx.printStackTrace();
            }
        }
        document.close();
        PrintTest2.print3(headerTitle);
    }
    
    //    Fn to print out medicine label --Hariz 20141014 END
    
    public static void createReportICD10(String title, String date) {
        try {
            Document document = new Document(PageSize.A5.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(title));
            document.open();
            addMetaDataICD10(document);
            addTitleICD10(document, date);
            addDataICD10(document, date);
            addFooterICD10(document);
            document.close();

            PrintTest2.print3(title);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void createTimeSlip(String title, String data_temp, ArrayList<String> masa) {
        try {
            Document document = new Document(PageSize.A5.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(title));
            document.open();
            addMetaDataTimeSlip(document);
            addTitleTimeSlip(document, data_temp);
            addDataTimeSlip(document, data_temp, masa);
            addFooterMC(document, data_temp);
            document.close();

            PrintTest2.print3(title);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String data_temp = "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-03 15:52:50.793|18R50R509^Fever, unspecified^01^Mild^2 Day^^^00^-^00^-^-^2014-04-03 15:52:50.793^Active^2014-04-03 15:52:50.793^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R50R509^Fever, unspecified^|<cr>\n"
                + "\n"
                + "VTS|2014-04-03 15:52:50.793|38.5^154^94^141^90^133^87^78^175^50^^^70^^^^^^^^^2014-04-03 15:52:50.793^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "DGS|2014-04-03 15:52:50.793|tp002^Provisional^^03/03/2014^01A00A01^Typhoid and paratyphoid fevers^01A00A01^Typhoid and paratyphoid fevers^^Mild^^-^^^^-^^^ ^2014-04-03 15:52:50.793^^2014-04-03 15:52:50.793^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "DTO|2014-04-03 15:52:50.793|18R50R509^Fever, unspecified^18R50R509|^amoxicillin + clavulanic acid^|^-^|^^|^Twice a day^|2|1|625mg|^^625mg|5|10|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|Do not stop taking this medicine except on your doctors's advice|<cr>\n"
                + "\n"
                + "MEC|2014-04-03 15:52:50.793|18R50R509^Fever, unspecified^^^18R50R509^Fever, unspecified^^^^15:50:16^15:52:23^03/04/2014^03/04/2014^2014-04-03 15:52:50.793^^2014-04-03 15:52:50.793^Klinik UTeM Induk^A001^DR. VIPER^<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10013|umar mukhtar^891031065213^Malay^Male^13/02/1999^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-03 16:14:48.331|18R00R070^Pain in throat^02^Moderate^2 Day^^^00^-^00^-^-^2014-04-03 16:14:48.331^Active^2014-04-03 16:14:48.331^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R00R070^Pain in throat^|<cr>\n"
                + "\n"
                + "DTO|2014-04-03 16:14:48.331|18R00R070^Pain in throat^18R00R070|^ascorbic acid^|^-^|^^|^At night^|1|1|500mg|^^500mg|5|5|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|sucked or chewed|<cr>\n"
                + "\n"
                + "MEC|2014-04-03 16:14:48.331|18R00R070^Pain in throat^^^18R00R070^Pain in throat^^^^16:13:27^16:14:36^03/04/2014^03/04/2014^2014-04-03 16:14:48.331^^2014-04-03 16:14:48.331^Klinik UTeM Induk^A001^DR. VIPER^<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|8805063555888|AISHAH^880506355588^Malay^Female^06/05/1988^-^SINGLE^|<cr>\n"
                + "\n"
                + "CCN|2014-04-08 15:18:49.794|18R00R070^Pain in throat^01^Mild^2 Day^^^00^-^00^-^SAKIT TEKAK^2014-04-08 15:18:49.794^Active^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R00R070^Pain in throat^|<cr>\n"
                + "\n"
                + "HPI|2014-04-08 15:18:49.794|PERNAH SAKIT BATUK2^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "PMH|2014-04-08 15:18:49.794|18R50R509^Fever, unspecified^Active^Active^2014-04-08 15:18:49.794^PERNAH DEMAM LAMA DA^^^2014-04-08 15:18:49.794^Active^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "FMH|2014-04-08 15:18:49.794|FATHER^^^21Z00Z013^Examination of blood pressure^2014-04-08 15:18:49.794^DARAH TINGGI^^^2014-04-08 15:18:49.794^^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "IMU|2014-04-08 15:18:49.794|20Y40Y580^BCG vaccine^01/01/2000^MASA DARJAH 6^^^2014-04-08 15:18:49.794^^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "VTS|2014-04-08 15:18:49.794|37^-^-^-^-^-^-^52^158^-^^^-^^^^^^^^^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "DGS|2014-04-08 15:18:49.794|tp001^Final^^08/04/2014^01A20A25^Rat-bite fevers^01A20A25^Rat-bite fevers^^Mild^^-^^^^-^^^DEMAM BATUK2 TIKUIH^2014-04-08 15:18:49.794^^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "PNT|2014-04-08 15:18:49.794|PERLU DIPANTAU DAN PERLU REHAT SECUKUPNYA.^^2014-04-08 15:18:49.794^^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "DTO|2014-04-08 15:18:49.794|18R00R070^Pain in throat^18R00R070|^ascorbic acid^|^-^|^^|^At night^|1|1|500mg|^^500mg|5|5|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|sucked or chewed|<cr>\n"
                + "\n"
                + "DTO|2014-04-08 15:18:49.794|18R00R070^Pain in throat^18R00R070|^acetamenophane^|^-^|^^|^4 times a day^|4|2|500mg|^^500mg|3|24|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|Do not take more than 2 at anytime. Do not take more than 8 in 24 hours|<cr>\n"
                + "\n"
                + "MEC|2014-04-08 15:18:49.794|18R00R070^Pain in throat^^^18R00R070^Pain in throat^^^^15:11:32^15:18:25^08/04/2014^08/04/2014^2014-04-08 15:18:49.794^^2014-04-08 15:18:49.794^Klinik UTeM Induk^A001^DR. VIPER^<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|8805063555888|AISHAH^880506355588^Malay^Female^06/05/1988^-^SINGLE^|<cr>\n"
                + "\n"
                + "VTS|2014-04-08 15:27:39.534|37^131^81^133^79^132^80^52^158^-^^^75^^^^^^^^^2014-04-08 15:27:39.534^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|8805063555888|AISHAH^880506355588^Malay^Female^06/05/1988^-^SINGLE^|<cr>\n"
                + "\n"
                + "VTS|2014-04-08 15:27:58.183|37^131^81^133^79^132^80^52^158^-^^^75^^^^^^^^^2014-04-08 15:27:58.183^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-09 11:16:51.155|18R50R509^Fever, unspecified^01^Mild^2 Day^^^00^-^00^-^-^2014-04-09 11:16:51.155^Active^2014-04-09 11:16:51.155^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R50R509^Fever, unspecified^|<cr>\n"
                + "\n"
                + "DTO|2014-04-09 11:16:51.155|18R50R509^Fever, unspecified^18R50R509|^acetamenophane^|^-^|^^|^4 times a day^|4|2|500mg|^^500mg|3|24|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|Do not take more than 2 at anytime. Do not take more than 8 in 24 hours|<cr>\n"
                + "\n"
                + "MEC|2014-04-09 11:16:51.155|18R50R509^Fever, unspecified^^^18R50R509^Fever, unspecified^^^^11:15:42^11:16:26^09/04/2014^09/04/2014^2014-04-09 11:16:51.155^^2014-04-09 11:16:51.155^Klinik UTeM Induk^A001^DR. VIPER^<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10013|umar mukhtar^891031065213^Malay^Male^13/02/1999^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-09 15:43:10.702|-^Pain in throat^01^Mild^1 Day^^^00^-^00^-^SAKIT TEKAK^2014-04-09 15:43:10.702^Active^2014-04-09 15:43:10.702^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "VTS|2014-04-09 15:43:10.702|37^131^83^133^81^132^82^76^175^-^^^70^^^^^^^^^2014-04-09 15:43:10.702^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "DTO|2014-04-09 15:43:10.702|18R00R070^Pain in throat^18R00R070|^acetamenophane^|^-^|^^|^4 times a day^|4|2|500mg|^^500mg|4|32|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|<cr>\n"
                + "\n"
                + "DTO|2014-04-09 15:43:10.702|18R00R070^Pain in throat^18R00R070|^ascorbic acid^|^-^|^^|^At night^|1|1|500mg|^^500mg|5|5|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10013|umar mukhtar^891031065213^Malay^Male^13/02/1999^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-09 15:48:16.977|18R00R070^Pain in throat^01^Mild^1 Day^^^00^-^00^-^SAKIT TEKAK^2014-04-09 15:48:16.977^Active^2014-04-09 15:48:16.977^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R00R070^Pain in throat^|<cr>\n"
                + "\n"
                + "VTS|2014-04-09 15:48:16.977|37^131^83^133^81^132^82^76^175^-^^^70^^^^^^^^^2014-04-09 15:48:16.977^Klinik UTeM Induk^A001^DR. VIPER^|<cr>\n"
                + "\n"
                + "DTO|2014-04-09 15:48:16.977|18R00R070^Pain in throat^18R00R070|^ascorbic acid^|^-^|^^|^At night^|1|1|500mg|^^500mg|5|5|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|sucked or chewed|<cr>\n"
                + "\n"
                + "DTO|2014-04-09 15:48:16.977|18R00R070^Pain in throat^18R00R070|^acetamenophane^|^-^|^^|^4 times a day^|4|2|500mg|^^500mg|3|24|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|Do not take more than 2 at anytime. Do not take more than 8 in 24 hours|<cr>\n"
                + "\n"
                + "MEC|2014-04-09 15:48:16.977|18R00R070^Pain in throat^^^18R00R070^Pain in throat^^^^15:43:26^15:48:06^09/04/2014^10/04/2014^2014-04-09 15:48:16.977^^2014-04-09 15:48:16.977^Klinik UTeM Induk^A001^DR. VIPER^<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-10 19:48:57.917|18R50R682^Dry mouth, unspecified^02^Moderate^1 Week^^^01^Right^00^-^Right^2014-04-10 19:48:57.917^Active^2014-04-10 19:48:57.917^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R50R682^Dry mouth, unspecified^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-14 14:56:55.514|18R50R509^Fever, unspecified^01^Mild^2 Day^^^00^-^00^-^-^2014-04-14 14:56:55.514^Active^2014-04-14 14:56:55.514^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R50R509^Fever, unspecified^|<cr>\n"
                + "\n"
                + "DTO|2014-04-14 14:56:55.514|18R50R509^Fever, unspecified^18R50R509|^acetamenophane^|^-^|^^|^4 times a day^|4|2|500mg|^^500mg|3|24|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|Do not take more than 2 at anytime. Do not take more than 8 in 24 hours|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10023|hahahahahaha^891031065777^Malay^Male^31/10/1989^O^MARRIED^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-18 12:12:52.699|18R50R509^Fever, unspecified^01^Mild^2 Day^^^00^-^00^-^-^2014-04-18 12:12:52.699^Active^2014-04-18 12:12:52.699^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R50R509^Fever, unspecified^|<cr>\n"
                + "\n"
                + "DTO|2014-04-18 12:12:52.699|18R50R509^Fever, unspecified^18R50R509|^amoxicillin + clavulanic acid^|^-^|^^|^Twice a day^|2|1|625mg|^^625mg|5|10|^^|-|^Klinik UTeM Induk^^Outpatient Discipline^^Outpatient Discipline|Do not stop taking this medicine except on your doctors's advice|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|9108220852222|PUTTERY BINTI ABD RAZAK^910822085222^Malay^Female^22/08/1991^-^SINGLE^|<cr>\n"
                + "\n"
                + "\n"
                + "MSH|^~|CIS^T12109|<cr>\n"
                + "\n"
                + "PDI|PMS10015|umar umar^891031065213^Malay^Male^31/10/1989^O^Single^|<cr>\n"
                + "\n"
                + "CCN|2014-04-18 16:19:17.092|18R50R682^Dry mouth, unspecified^02^Moderate^1 Week^^^00^-^01^Right^Right^2014-04-18 16:19:17.092^Active^2014-04-18 16:19:17.092^Klinik UTeM Induk^A001^DR. VIPER^ICD10^18R50R682^Dry mouth, unspecified^|<cr>\n"
                + "\n"
                + "MEC|2014-04-18 16:19:17.092|18R50R682^Dry mouth, unspecified^^^18R50R682^Dry mouth, unspecified^^^^16:17:51^16:19:05^18/04/2014^19/04/2014^2014-04-18 16:19:17.092^^2014-04-18 16:19:17.092^Klinik UTeM Induk^A001^DR. VIPER^<cr>\n"
                + "";
        ArrayList<String> masa = new ArrayList<String>();
        masa.add("08:01:00");
        masa.add("08:35:00");
        masa.add("03/03/2014");
        masa.add("05/03/2014");
        Session.setUser_name("Umar Mukhtar");
//        PDFiText.createReportICD10("TimeSlip_.pdf", "2014-04-24 16:33:15");
//        PDFiText.createTimeSlip("timeslip_.pdf", data_temp, masa);
        PDFiText.createMC("mcSlip_.pdf", data_temp, masa);
    }
}
