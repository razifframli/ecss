package GUI.report_ICD10.symptom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DBConnection.DBConnection;
import GUI.Login;
import com.itextpdf.text.BaseColor;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Desktop;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.RMIConnector;



public class new_GUI extends javax.swing.JFrame { 
    
    
   /**
 * Java class new_GUI
 * @param message Java program to generate symptom report by faculty
 * 
 * @author arif
 * @version 1.0 29 DEC 2015
 */   
    static File fi = new File(Login.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    static String par = fi.getParent()+"/";
//    static String par = "";
    String fileNameSymptom = par+"Report_Symptom.pdf";
    String dateFormatAll = "yyyy/MM/dd";
    
String Faculty = null;
String[] tarikh;
String date1 = null;
String date2 = null;
String query = null;
//RMIConnector rc = new RMIConnector();
//String host = "biocore-stag.utem.edu.my";
//int port = 1099; // for now, stick to this port  
String sql=null;
String Patient_type ;
String Staff = null;
String Student = null;
String Patient = null;
    
    public new_GUI() {
        initComponents();
    }
    class TableHeader extends PdfPageEventHelper 
    {
        String header;
        String footer;
        PdfTemplate total;
 
        /**
         * Allows us to change the content of the header.
         * @param header The new header String
         */
        public void setHeader(String header) {
            this.header = header;
        }
        public void setFooter(String footer) {
            this.footer = footer;
        }
        public void onOpenDocument(PdfWriter writer, Document reportpdf) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
        public void onEndPage(PdfWriter writer, Document reportpdf) {
            PdfPTable table = new PdfPTable(3);
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
           //   table.addCell(footer);
            //  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            //  table.addCell(String.format("Page %d of  ",writer.getPageNumber()));
            // PdfPCell cell = new PdfPCell(Image.getInstance(total));
            //  cell.setBorder(Rectangle.NO_BORDER);
           //  table.addCell(cell);    
             //table.writeSelectedRows(0, -1, 36, 55, writer.getDirectContent())
                //-----------------------
                 PdfContentByte cb = writer.getDirectContent();
                //header content
                String headerContent = " ";
                //header content
                String footerContent = headerContent;
                /*
                 * Foooter
                 */
               ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.format("%d ", writer.getPageNumber())), 
                       reportpdf.right()- 12 , reportpdf.bottom() - 20, 0);
            //ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.format("Page %d of", writer.getPageNumber())), 
                  // reportpdf.right() - 12 , reportpdf.bottom() - 20, 0);
            // ColumnText.showTextAligned(total, Element.ALIGN_RIGHT, new Phrase(String.valueOf(writer.getPageSize())), 
                    // reportpdf.right() - 2 , reportpdf.bottom() - 20, 0);
                //-----------------------
            }
            catch(DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
        
 
        /**
         * Fills out the total number of pages before the document is closed.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onCloseDocument(PdfWriter writer, Document reportpdf) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Please select-", "ALL", "FTMK", "FKP", "FKM", "FKEKK", "FKE", "FPTT", "FTK" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("From:");

        jLabel3.setText("To:");

        jLabel4.setBackground(java.awt.Color.lightGray);
        jLabel4.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Symptom Report Based on Faculty");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Please select-", "Staff", "Student" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Type of Patient:");

        jLabel1.setText("Select a faculty :");

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy/MM/dd");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jButton2.setText("GENERATE REPORT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(76, 76, 76))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(488, 488, 488))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) 
    {
        JDateChooser tarikh1 = (JDateChooser) evt.getSource();
        if ("date".equals(evt.getPropertyName())) {
            tarikh1.getDate();
            DateFormat fmt = new SimpleDateFormat(dateFormatAll);
            String date = fmt.format(tarikh1.getDate()); //jdatechooser
            JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh1.getDateEditor();
            editor.setEditable(false);
        }
        JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh1.getDateEditor();
        editor.setEditable(false);   
    }
   
    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange

        JDateChooser tarikh2 = (JDateChooser) evt.getSource();
        if ("date".equals(evt.getPropertyName())) {
            tarikh2.getDate();
            DateFormat fmt = new SimpleDateFormat(dateFormatAll);
            String date = fmt.format(tarikh2.getDate()); //jdatechooser
            JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh2.getDateEditor();
            editor.setEditable(false);
        }
        JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh2.getDateEditor();
        editor.setEditable(false);
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
         Object selectedItem = jComboBox2.getSelectedItem();
        Patient = selectedItem.toString();
        {
            if ("Staff".equals(Patient) )
            {
               //int Staft = 5;
                Patient_type = Integer.toString(5);
            }
            else if ("Student".equals(Patient))
            {
               //int Pelajar = 10;
                 Patient_type = Integer.toString(10);  
            }
            else if (selectedItem == "-Please select-")
            {
                 JOptionPane.showMessageDialog(null, "Patient type is required!");
            }       
        }  
    }      
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
 
        
        
           
    }//GEN-LAST:event_jComboBox1ActionPerformed
/**
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
 
      
        
    }//GEN-LAST:event_jComboBox2ActionPerformed
*/
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
//         java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ReportModule().setVisible(true);
//                setVisible(false);
//            }
//        });
        this.setVisible(false);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        java.util.Date d1 = jDateChooser1.getDate();
        java.util.Date d2 = jDateChooser2.getDate();
        if (d1 == null || d2 == null) {
            JOptionPane.showMessageDialog(null, "Date is required!");
        } else {
            DateFormat fmt = new SimpleDateFormat(dateFormatAll);
            date1 = fmt.format(d1); //jdatechooser
            date2 = fmt.format(d2); //jdatechooser
        }
        Object selectedItem = jComboBox1.getSelectedItem();
        Faculty = selectedItem.toString();
        if ((date1 != null && date2 != null) && !"-Please select-".equals(Faculty) && !"-Please select-".equals(Patient)) {
            try {
                Document reportpdf = new Document(PageSize.A4);
                TableHeader event = new TableHeader();
                String timeStamp1 = new SimpleDateFormat(dateFormatAll).format(Calendar.getInstance().getTime());
//                PdfWriter writer = PdfWriter.getInstance(reportpdf, new FileOutputStream("Report '" + Faculty + "' - " + timeStamp1 + "  .pdf"));
                PdfWriter writer = PdfWriter.getInstance(reportpdf, new FileOutputStream(fileNameSymptom));
                writer.setPageEvent(event);
                reportpdf.open();
                PdfPTable header = new PdfPTable(2);
                float[] columnWidths = {2f, 1.19f};
                header.setWidths(columnWidths);
                Image logo = Image.getInstance("logo.png");
                logo.scalePercent(8);
                PdfPCell cell1 = new PdfPCell(logo);
                // logo.setAlignment(Element.ALIGN_CENTER);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setColspan(2);
                cell1.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell1);
                //PdfPCell cell2 = new PdfPCell(new Paragraph("Universiti Teknikal Malaysia Melaka\nHang Tuah Jaya, \n76100 Durian Tunggal, \nMelaka, Malaysia."));
                //cell2.setBorder(Rectangle.NO_BORDER);
                // cell2.setLeading(15f, 0.3f);
                //header.addCell(cell2);
                Font teks = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
                Font teks1 = new Font(Font.FontFamily.HELVETICA, 10);
                PdfPCell cell3 = new PdfPCell(new Paragraph("\nSymptom Report by Faculty", teks));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setColspan(2);
                cell3.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell3);
                PdfPCell cell7 = new PdfPCell(new Paragraph("\nFrom :  " + date1 + " To : " + date2 + "", teks));
                cell7.setColspan(20);
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell7.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell7);
                PdfPCell cell10 = new PdfPCell(new Paragraph("\n\nType of patient : " + Patient + "", teks1));
                cell10.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell10);
                PdfPCell cell8 = new PdfPCell(new Paragraph("\n\nReport ID : ECSS RPT 003", teks1));
                cell8.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell8);
                //PdfPCell cell4 = new PdfPCell(new Paragraph("\n\n\n"));
                //cell4.setBorder(Rectangle.NO_BORDER);
                //header.addCell(cell4);
                PdfPCell cell5 = new PdfPCell(new Paragraph("Faculty :  " + Faculty + " \n\n", teks1));
                cell5.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell5);
                String timeStamp = new SimpleDateFormat(dateFormatAll).format(Calendar.getInstance().getTime());
                PdfPCell cell6 = new PdfPCell(new Paragraph("Date : " + timeStamp + " \n\n", teks1));
                cell6.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell6);
                PdfPCell cell11 = new PdfPCell(new Paragraph("  "));
                cell11.setBorder(Rectangle.NO_BORDER);
                header.addCell(cell11);
                reportpdf.add(header);
                try {
                    PdfPTable reportpdftable = new PdfPTable(5);
                    reportpdftable.setWidths(new int[]{1, 5, 2, 2, 2});
                    PdfPCell reportpdf_cell;
                    Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
                    Font font1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
                    String No = ("No.");
                    reportpdf_cell = new PdfPCell(new Phrase(No, font1));
                    reportpdf_cell.setRowspan(2);
                    reportpdf_cell.setBackgroundColor(BaseColor.WHITE);
                    reportpdftable.addCell(reportpdf_cell);
                    String description = ("SYMPTOM NAME");
                    reportpdf_cell = new PdfPCell(new Phrase(description, font1));
                    reportpdf_cell.setRowspan(2);
                    reportpdf_cell.setBackgroundColor(BaseColor.WHITE);
                    reportpdftable.addCell(reportpdf_cell);
                    String PERSON_STATUS = ("GENDER");
                    reportpdf_cell = new PdfPCell(new Phrase(PERSON_STATUS, font1));
                    reportpdf_cell.setBackgroundColor(BaseColor.WHITE);
                    reportpdf_cell.setColspan(2);
                    reportpdftable.addCell(reportpdf_cell);
                    String total_patient = ("TOTAL ");
                    reportpdf_cell = new PdfPCell(new Phrase(total_patient, font1));
                    reportpdf_cell.setBackgroundColor(BaseColor.WHITE);
                    reportpdf_cell.setRowspan(2);
                    reportpdftable.addCell(reportpdf_cell);
                    reportpdf_cell = new PdfPCell(new Phrase("Male", font1));
                    reportpdf_cell.setBackgroundColor(BaseColor.WHITE);
                    reportpdftable.addCell(reportpdf_cell);
                    reportpdf_cell = new PdfPCell(new Phrase("Female", font1));
                    reportpdf_cell.setBackgroundColor(BaseColor.WHITE);
                    reportpdftable.addCell(reportpdf_cell);

                    if ("ALL".equals(Faculty)) {
                        sql = "select coalesce(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS Male,"
                                + "coalesce(SUM(CASE WHEN PERSON_STATUS = 'F' THEN 1 ELSE 0 END),0) AS Female, "
                                + "symptom_name ,  "
                                + "count(*) as Bilangan_pelajar "
                                + "from lhr_signs  where (Episode_date between '" + date1 + "' AND '" + date2 + "') and  LENGTH(PERSON_ID_NO) = '" + Patient_type + "'   group by symptom_name  ORDER BY `symptom_name` ASC ";
                    } else {
                        sql = "select coalesce( SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS Male,"
                                + "coalesce(SUM(CASE WHEN PERSON_STATUS = 'F' THEN 1 ELSE 0 END),0) AS Female , "
                                + "symptom_name,"
                                + "count(*) as Bilangan_pelajar  "
                                + "from lhr_signs where LENGTH(PERSON_ID_NO) = '" + Patient_type + "' and  centre_code = '" + Faculty + "'  and (Episode_date between '" + date1 + "' AND '" + date2 + "'   )    group by symptom_name ORDER BY `symptom_name` ASC ";
                    }

                    //  ArrayList<ArrayList<String>> by_fac= rc.getQuerySQL(host, port, sql) ;  
//                 ArrayList<ArrayList<String>> by_fac = rc.getQuerySQL(host, port, sql) ;  
                    String param[] = {};
                    ArrayList<ArrayList<String>> by_fac = DBConnection.getImpl().getQuery(sql, 4, param);

                    // for (ArrayList<String> by_fac1 : by_fac)
                    String k;
                    for (int i = 0; i < by_fac.size(); i++) {

                        k = Integer.toString(i + 1);
                        reportpdf_cell = new PdfPCell(new Phrase(k));
                        reportpdftable.addCell(reportpdf_cell);

                        reportpdf_cell = new PdfPCell(new Phrase(by_fac.get(i).get(2)));
                        reportpdftable.addCell(reportpdf_cell);
                        reportpdf_cell = new PdfPCell(new Phrase(by_fac.get(i).get(0)));
                        reportpdftable.addCell(reportpdf_cell);
                        reportpdf_cell = new PdfPCell(new Phrase(by_fac.get(i).get(1)));
                        reportpdftable.addCell(reportpdf_cell);
                        reportpdf_cell = new PdfPCell(new Phrase(by_fac.get(i).get(3)));
                        reportpdftable.addCell(reportpdf_cell);
                    }
                    reportpdf.add(reportpdftable);

                    try {

                        if ("ALL".equals(Faculty)) {
                            sql = "select count(*),  symptom_name from lhr_signs where  LENGTH(PERSON_ID_NO) = '" + Patient_type + "' and (Episode_date between '" + date1 + "' AND '" + date2 + "')  ";
                        } else {
                            sql = "select count(*),  symptom_name from lhr_signs where  LENGTH(PERSON_ID_NO) = '" + Patient_type + "' and  centre_code = '" + Faculty + "' and (Episode_date between '" + date1 + "' AND '" + date2 + "')   group by centre_code     ";
                        }

//                  ArrayList<ArrayList<String>> total = rc.getQuerySQL(host, port, sql) ;  
                        String param2[] = {};
                        ArrayList<ArrayList<String>> total = DBConnection.getImpl().getQuery(sql, 2, param2);
                        for (ArrayList<String> total1 : total) {
                            String Total = total1.get(0);
                            PdfPTable footer = new PdfPTable(5);
                            footer.setWidths(new int[]{1, 5, 2, 2, 2});

                            PdfPCell cell14 = new PdfPCell(new Paragraph("Grand total"));
                            cell14.setColspan(4);
                            footer.addCell(cell14);
                            PdfPCell cell9 = new PdfPCell(new Paragraph("" + Total + ""));
                            cell9.setColspan(1);//remove with space and dash
                            //cell9.setBorder(Rectangle.NO_BORDER);
                            footer.addCell(cell9);
                            reportpdf.add(footer);
                        }

                        Paragraph p = new Paragraph("\n\n--End of report-- \n\n", new Font(FontFamily.HELVETICA, 14));
                        p.setAlignment(Element.ALIGN_CENTER);
                        reportpdf.add(p);
                        reportpdf.newPage();
                        reportpdf.close();
                        if (Desktop.isDesktopSupported()) {
                            try {
                                File myFile = new File(fileNameSymptom);
                                Desktop.getDesktop().open(myFile);
                            } catch (IOException ex) {
                            }
                        }
                    } catch (DocumentException e) {
                        System.err.println("Got an exception!  ");
                        System.err.println(e.getMessage());
                    }
                } catch (DocumentException e) {
                    System.err.println("Got an exception!  ");
                    System.err.println(e.getMessage());
                }
            } catch (DocumentException | IOException e) {
                System.err.println("Got an exception!  ");
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws com.itextpdf.text.DocumentException
     * @throws java.io.FileNotFoundException
     */
public static void main(String args[])  throws SQLException, DocumentException, FileNotFoundException 
{
      /* Set the Nimbus look and feel */
  /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         
        public void run() {
            new new_GUI().setVisible(true);
        }
    });
  }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
