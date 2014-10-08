/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBConnection.ReportDB;
import Helper.Session;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import library.Func;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author End User
 */
public class Report extends javax.swing.JFrame {

    /**
     * Creates new form Report
     */
    public Report() {
        initComponents();
        
        setDate();
        
        setLocation(300, 50);
    }
    
    public void setDate() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        System.out.println("year:"+year);
        System.out.println("month:"+month);
        System.out.println("day:"+day);
        for (int i = 1970; i <= year; i++) {
            cbx_year1.addItem(""+i);
            cbx_year2.addItem(""+i);
        }
        for (int i = 1; i <= 12; i++) {
            cbx_month1.addItem(""+i);
            cbx_month2.addItem(""+i);
        }
        for (int i = 1; i <= 31; i++) {
            cbx_day1.addItem(""+i);
            cbx_day2.addItem(""+i);
        }
        Func.cmbSelectInput(cbx_year2, ""+year);
        Func.cmbSelectInput(cbx_month2, ""+month);
        Func.cmbSelectInput(cbx_day2, ""+day);
        Func.cmbSelectInput(cbx_year1, ""+year);
        Func.cmbSelectInput(cbx_month1, "1");
        Func.cmbSelectInput(cbx_day1, "1");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbx_symptom = new javax.swing.JComboBox();
        btn_piechart = new javax.swing.JButton();
        btn_barchart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbx_year1 = new javax.swing.JComboBox();
        cbx_month1 = new javax.swing.JComboBox();
        cbx_day1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbx_year2 = new javax.swing.JComboBox();
        cbx_month2 = new javax.swing.JComboBox();
        cbx_day2 = new javax.swing.JComboBox();
        btn_list = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinical Support System");

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(51, 51, 255));
        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Full Report");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Type of Symptom : ");

        cbx_symptom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C. Complaints", "Past Medical History", "Family History", "Social History", "Allergy", "Immunization", "Disability", "Diagnosis", "Drug Treatment Order" }));

        btn_piechart.setText("Pie Chart");
        btn_piechart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_piechartActionPerformed(evt);
            }
        });

        btn_barchart.setText("Bar Chart");
        btn_barchart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_barchartActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("FROM"));

        jLabel2.setText("Year : ");

        jLabel3.setText("Month : ");

        jLabel4.setText("Day : ");

        cbx_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_year1ActionPerformed(evt);
            }
        });

        cbx_month1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_month1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_year1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_month1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_day1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbx_year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbx_month1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TO"));

        jLabel5.setText("Year : ");

        jLabel10.setText("Month : ");

        jLabel11.setText("Day : ");

        cbx_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_year2ActionPerformed(evt);
            }
        });

        cbx_month2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_month2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_year2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_month2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_day2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbx_year2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbx_month2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_day2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btn_list.setText("Summary");
        btn_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_piechart, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_barchart, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_list, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_symptom, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbx_symptom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_piechart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_barchart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_list, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ReportMenu reportMenu = new ReportMenu();
        reportMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_barchartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_barchartActionPerformed
        try {
            int ind = cbx_symptom.getSelectedIndex();
            String str = String.valueOf(cbx_symptom.getSelectedItem());
            int year1 = Integer.parseInt(String.valueOf(cbx_year1.getSelectedItem()));
            int month1 = Integer.parseInt(String.valueOf(cbx_month1.getSelectedItem()));
            int day1 = Integer.parseInt(String.valueOf(cbx_day1.getSelectedItem()));
            int year2 = Integer.parseInt(String.valueOf(cbx_year2.getSelectedItem()));
            int month2 = Integer.parseInt(String.valueOf(cbx_month2.getSelectedItem()));
            int day2 = Integer.parseInt(String.valueOf(cbx_day2.getSelectedItem()));
            String date1 = year1 + "-" + Func.getFormatZero(1, month1) + "-" + Func.getFormatZero(1, day1) + " 00:00:00";
            String date2 = year2 + "-" + Func.getFormatZero(1, month2) + "-" + Func.getFormatZero(1, day2) + " 00:00:00";
            String from = day1 + "/" + month1 + "/" + year1;
            String to = day2 + "/" + month2 + "/" + year2;
            String axis_y = "Number of " + str;
            String header = "Statistic of " + str + " from " + from + " to " + to;
            ArrayList<ArrayList<String>> dat = ReportDB.getData(ind, date1, date2);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < dat.get(0).size(); i++) {
                int val = 0;
                try {
                    val = Integer.parseInt(dat.get(1).get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    dataset.setValue(val, axis_y, dat.get(0).get(i).split("\\|")[1]);
                } catch (Exception e) {
                    dataset.setValue(0, axis_y, "-");
                }
            }
            JFreeChart chart = ChartFactory.createBarChart(header, str, axis_y, dataset, PlotOrientation.VERTICAL, false, true, false);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame(header, chart);
            frame.setVisible(true);
            frame.setSize(800, 550);
            frame.setLocation(200, 50);
        } catch (Exception ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_barchartActionPerformed
    
    private void btn_piechartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piechartActionPerformed
        int ind = cbx_symptom.getSelectedIndex();
        String str = String.valueOf(cbx_symptom.getSelectedItem());
        int year1 = Integer.parseInt(String.valueOf(cbx_year1.getSelectedItem()));
        int month1 = Integer.parseInt(String.valueOf(cbx_month1.getSelectedItem()));
        int day1 = Integer.parseInt(String.valueOf(cbx_day1.getSelectedItem()));
        int year2 = Integer.parseInt(String.valueOf(cbx_year2.getSelectedItem()));
        int month2 = Integer.parseInt(String.valueOf(cbx_month2.getSelectedItem()));
        int day2 = Integer.parseInt(String.valueOf(cbx_day2.getSelectedItem()));
        String date1 = year1 + "-" + Func.getFormatZero(1, month1) + "-" + Func.getFormatZero(1, day1) + " 00:00:00";
        String date2 = year2 + "-" + Func.getFormatZero(1, month2) + "-" + Func.getFormatZero(1, day2) + " 00:00:00";
        String from = day1+"/"+month1+"/"+year1;
        String to = day2+"/"+month2+"/"+year2;
        String header = "Statistic of " + str + " from " + from + " to " + to;
        ArrayList<ArrayList<String>> dat = ReportDB.getData(ind, date1, date2);
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i < dat.get(0).size(); i++) {
            int val = 0;
            try {
                val = Integer.parseInt(dat.get(1).get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                pieDataset.setValue(dat.get(0).get(i).split("\\|")[1], val);
            } catch (Exception e) {
                pieDataset.setValue("-", 0);
            }
        }
        JFreeChart chart = ChartFactory.createPieChart(header, pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame(header, chart);
        frame.setVisible(true);
        frame.setSize(800, 580);
        frame.setLocation(200, 50);
    }//GEN-LAST:event_btn_piechartActionPerformed

    private void cbx_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_year1ActionPerformed
        Func.cmbSelectInput(cbx_month1, "1");
        Func.cmbSelectInput(cbx_day1, "1");
    }//GEN-LAST:event_cbx_year1ActionPerformed

    private void cbx_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_year2ActionPerformed
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        Func.cmbSelectInput(cbx_month2, ""+month);
        Func.cmbSelectInput(cbx_day2, ""+day);
    }//GEN-LAST:event_cbx_year2ActionPerformed

    private void cbx_month1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_month1ActionPerformed
        Func.cmbSelectInput(cbx_day1, "1");
    }//GEN-LAST:event_cbx_month1ActionPerformed

    private void cbx_month2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_month2ActionPerformed
        int day = Calendar.getInstance().get(Calendar.DATE);
        Func.cmbSelectInput(cbx_day2, ""+day);
    }//GEN-LAST:event_cbx_month2ActionPerformed

    private void btn_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listActionPerformed
        int ind = cbx_symptom.getSelectedIndex();
        String str = String.valueOf(cbx_symptom.getSelectedItem());
        int year1 = Integer.parseInt(String.valueOf(cbx_year1.getSelectedItem()));
        int month1 = Integer.parseInt(String.valueOf(cbx_month1.getSelectedItem()));
        int day1 = Integer.parseInt(String.valueOf(cbx_day1.getSelectedItem()));
        int year2 = Integer.parseInt(String.valueOf(cbx_year2.getSelectedItem()));
        int month2 = Integer.parseInt(String.valueOf(cbx_month2.getSelectedItem()));
        int day2 = Integer.parseInt(String.valueOf(cbx_day2.getSelectedItem()));
        String date1 = year1 + "-" + Func.getFormatZero(1, month1) + "-" + Func.getFormatZero(1, day1) + " 00:00:00";
        String date2 = year2 + "-" + Func.getFormatZero(1, month2) + "-" + Func.getFormatZero(1, day2) + " 00:00:00";
        String from = day1 + "/" + month1 + "/" + year1;
        String to = day2 + "/" + month2 + "/" + year2;
        String axis_y = "Number of " + str;
        String header = "Statistic of " + str + " from " + from + " to " + to;
        ArrayList<ArrayList<String>> dat = ReportDB.getData(ind, date1, date2);
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable jTable1 = new javax.swing.JTable();
        int num_col = 2;
        int num_row = 100;
        Object object[][] = new Object[num_row][num_col];
        for (int i = 0; i < num_row; i++) {
            for (int j = 0; j < num_col; j++) {
                object[i][j] = "";
            }
        }
        for (int i = 0; i < dat.get(0).size() && i < num_row; i++) {
            int val = 0;
            try {
                val = Integer.parseInt(dat.get(1).get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                object[i][0] = dat.get(0).get(i).split("\\|")[1];
                object[i][1] = val;
            } catch (Exception e) {
                object[i][0] = "-";
                object[i][1] = 0;
            }
        }
        String string[] = {str, axis_y};
        jTable1.setModel(new javax.swing.table.DefaultTableModel(object, string));
        jScrollPane1.setViewportView(jTable1);
        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel(header);
        l1.setFont(new Font("Serif", Font.BOLD, 28));
        p1.setLayout(new BorderLayout());
        p1.add(l1, BorderLayout.NORTH);
        p1.add(jScrollPane1, BorderLayout.SOUTH);
        JFrame frame = new JFrame(header);
        frame.add(p1);
        frame.setVisible(true);
        frame.setSize(800, 580);
        frame.setLocation(200, 50);
        frame.pack();
    }//GEN-LAST:event_btn_listActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Report().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_barchart;
    private javax.swing.JButton btn_list;
    private javax.swing.JButton btn_piechart;
    private javax.swing.JComboBox cbx_day1;
    private javax.swing.JComboBox cbx_day2;
    private javax.swing.JComboBox cbx_month1;
    private javax.swing.JComboBox cbx_month2;
    private javax.swing.JComboBox cbx_symptom;
    private javax.swing.JComboBox cbx_year1;
    private javax.swing.JComboBox cbx_year2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
