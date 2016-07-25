/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Helper.J;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.net.MalformedURLException;
import javax.print.*;
import javax.swing.JTextPane;

/**
 *
 * @author End User
 */
public class PrintTest2 {
    
    static File fi = new File(Login.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    static String par = fi.getParent()+"/";
//    static String par = "";
    
    public static void main(String[] args) {
        //print3();
    }
    public static void print3(String title) {
        try {

            File pdfFile = new File(par+title);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
                J.o("Error", "Presription is not exists!", 0);
            }

            System.out.println("Done");

        } catch (Exception ex) {
            J.o("Error", "Error generating file!", 0);
            ex.printStackTrace();
        }
    }
    public static void print2() {
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob printerJob = defaultPrintService.createPrintJob();
        File pdfFile = new File("Presription_.pdf");
        SimpleDoc simpleDoc = null;

        try {
            simpleDoc = new SimpleDoc(pdfFile.toURL(), DocFlavor.URL.AUTOSENSE, null);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        try {
            printerJob.print(simpleDoc, null);
        } catch (PrintException ex) {
            ex.printStackTrace();
        }

    }
    public static void print1() {
        JTextPane jtp = new JTextPane();
        jtp.setBackground(Color.white);
        jtp.setText("text to print");
        boolean show = true;
        try {
            jtp.print(null, null, show, null, null, show);
        } catch (java.awt.print.PrinterException ex) {
            ex.printStackTrace();
        }
    }
}
