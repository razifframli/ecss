/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Bean.GCS_Month;
import Bean.GCS_Response;
import Bean.GCS_Scale;
import DBConnection.DBConnection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javaapplication1.Consultation;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author End User
 */
public class PGCS_GUI extends javax.swing.JFrame {
    
    private static Consultation cons;

    /**
     * Creates new form PGCS_GUI
     */
    public PGCS_GUI() {
        initComponents();
        
        setData();
        
        pack();
    }
    
    public void setParent(Consultation con) {
        cons = con;
        cons.resizeTbl_pgcs(response.size());
    }
    
    protected static ArrayList<GCS_Response> response = DBConnection.getGcs_responseAll("pgcs");
    protected static int max_size = 100;
    protected static JCheckBox cbx[][] = new JCheckBox[max_size][max_size];
    protected static JCheckBox cbx2[][] = new JCheckBox[max_size][max_size];
    protected static int points = 0;
    
    public static void setData() {
        final int s11 = 2;
        final int s12 = 1;
        JPanel p1[] = new JPanel[s11];
        JPanel p2[] = new JPanel[s12];
        
        pnl_pgcs.setLayout(new GridLayout(s11+s12, 1));
        for (int i = 0; i < s11; i++) {
            p1[i] = new JPanel();
            p1[i].setBorder(BorderFactory.createTitledBorder(response.get(i).getResponse_name()));
            
            GCS_Month month = new GCS_Month();
            month.setMonth_code("1");
            ArrayList<GCS_Scale> scale = DBConnection.getGcs_scaleAll_parent(response.get(i), month);
            
            GCS_Month month2 = new GCS_Month();
            month2.setMonth_code("2");
            ArrayList<GCS_Scale> scale2 = DBConnection.getGcs_scaleAll_parent(response.get(i), month2);
            
            final int s21 = scale.size();
            final int s22 = scale.size();
            p1[i].setLayout(new GridLayout(1+s21, 3));
            p1[i].add(new JLabel(""));
            p1[i].add(new JLabel(scale.get(0).getgCS_Month().getMonth_desc()));
            p1[i].add(new JLabel(scale2.get(0).getgCS_Month().getMonth_desc()));
            for (int j = 0; j < s21 && j < s22; j++) {
                JLabel lbl = new JLabel(scale.get(j).getScale_desc());
                JLabel lbl2 = new JLabel(scale2.get(j).getScale_desc());
                cbx[i][j] = new JCheckBox();
                cbx[i][j].setName(scale.get(j).getScale_code()+"|"+scale2.get(j).getScale_code());
                cbx[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int iii = 0;
                        int jjj = 0;
                        for (int ii = 0; ii < s11; ii++) {
                            for (int jj = 0; jj < s21 && jj < s22; jj++) {
                                if (e.getSource().equals(cbx[ii][jj])) {
                                    iii = ii;
                                    jjj = jj;
                                }
                            }
                        }
                        for (int jj = 0; jj < s21 && jj < s22; jj++) {
                            if (jj != jjj) {
                                cbx[iii][jj].setSelected(false);
                            }
                        }
                        calcPoints();
                    }
                });
                p1[i].add(cbx[i][j]);
                p1[i].add(lbl);
                p1[i].add(lbl2);
            }
            pnl_pgcs.add(p1[i]);
        }
        for (int i = 0; i < s12; i++) {
            int k = s11+i;
            p2[i] = new JPanel();
            p2[i].setBorder(BorderFactory.createTitledBorder(response.get(k).getResponse_name()));
            
            GCS_Month month3 = new GCS_Month();
            month3.setMonth_code("3");
            ArrayList<GCS_Scale> scale3 = DBConnection.getGcs_scaleAll_parent(response.get(k), month3);
            
            GCS_Month month4 = new GCS_Month();
            month4.setMonth_code("4");
            ArrayList<GCS_Scale> scale4 = DBConnection.getGcs_scaleAll_parent(response.get(k), month4);
            
            GCS_Month month5 = new GCS_Month();
            month5.setMonth_code("5");
            ArrayList<GCS_Scale> scale5 = DBConnection.getGcs_scaleAll_parent(response.get(k), month5);
            
            final int s23 = scale3.size();
            final int s24 = scale4.size();
            final int s25 = scale5.size();
            p2[i].setLayout(new GridLayout(1+s23, 4));
            p2[i].add(new JLabel(""));
            p2[i].add(new JLabel(scale3.get(0).getgCS_Month().getMonth_desc()));
            p2[i].add(new JLabel(scale4.get(0).getgCS_Month().getMonth_desc()));
            p2[i].add(new JLabel(scale5.get(0).getgCS_Month().getMonth_desc()));
            for (int j = 0; j < s23 && j < s24 && j < s25; j++) {
                JLabel lbl3 = new JLabel(scale3.get(j).getScale_desc());
                JLabel lbl4 = new JLabel(scale4.get(j).getScale_desc());
                JLabel lbl5 = new JLabel(scale5.get(j).getScale_desc());
                cbx2[i][j] = new JCheckBox();
                cbx2[i][j].setName(scale3.get(j).getScale_code()+"|"+scale4.get(j).getScale_code()+"|"+scale5.get(j).getScale_code());
                cbx2[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int iii = 0;
                        int jjj = 0;
                        for (int ii = 0; ii < s11; ii++) {
                            for (int jj = 0; jj < s23 && jj < s24 && jj < s25; jj++) {
                                if (e.getSource().equals(cbx2[ii][jj])) {
                                    iii = ii;
                                    jjj = jj;
                                }
                            }
                        }
                        for (int jj = 0; jj < s23 && jj < s24 && jj < s25; jj++) {
                            if (jj != jjj) {
                                cbx2[iii][jj].setSelected(false);
                            }
                        }
                        calcPoints();
                    }
                });
                p2[i].add(cbx2[i][j]);
                p2[i].add(lbl3);
                p2[i].add(lbl4);
                p2[i].add(lbl5);
            }
            pnl_pgcs.add(p2[i]);
        }
    }
    
    public static void calcPoints() {
        cons.clearTablePGCS();
        points = 0;
        for (int i = 0; i < response.size(); i++) {
            
            GCS_Month month = new GCS_Month();
            month.setMonth_code("1");
            ArrayList<GCS_Scale> scale = DBConnection.getGcs_scaleAll_parent(response.get(i), month);
            
            for (int j = 0; j < scale.size(); j++) {
                if (cbx[i][j] != null && cbx[i][j].isSelected()) {
                    
                    String code = cbx[i][j].getName().split("\\|")[0];
                    GCS_Scale g_temp = new GCS_Scale();
                    g_temp.setScale_code(code);
                    
                    String code2 = cbx[i][j].getName().split("\\|")[1];
                    GCS_Scale g_temp2 = new GCS_Scale();
                    g_temp2.setScale_code(code2);
                    
                    GCS_Scale pgcs = DBConnection.getGcs_scale(g_temp);
                    GCS_Scale pgcs2 = DBConnection.getGcs_scale(g_temp2);
                    cons.tbl_pgcs.getModel().setValueAt(pgcs.getScale_desc()+"/"+pgcs2.getScale_desc(), i, 0);
                    int point = Integer.parseInt(pgcs.getScale_score().split(" ")[0]);
                    cons.tbl_pgcs.getModel().setValueAt(point, i, 1);
                    points += point;
                }
            }
            
            GCS_Month month2 = new GCS_Month();
            month2.setMonth_code("3");
            ArrayList<GCS_Scale> scale2 = DBConnection.getGcs_scaleAll_parent(response.get(i), month2);
            
            for (int j = 0; j < scale2.size(); j++) {
                if (cbx2[i][j] != null && cbx2[i][j].isSelected()) {
                    
                    String code3 = cbx2[i][j].getName().split("\\|")[0];
                    GCS_Scale g_temp3 = new GCS_Scale();
                    g_temp3.setScale_code(code3);
                    
                    String code4 = cbx2[i][j].getName().split("\\|")[1];
                    GCS_Scale g_temp4 = new GCS_Scale();
                    g_temp4.setScale_code(code4);
                    
                    String code5 = cbx2[i][j].getName().split("\\|")[2];
                    GCS_Scale g_temp5 = new GCS_Scale();
                    g_temp5.setScale_code(code5);
                    
                    GCS_Scale pgcs3 = DBConnection.getGcs_scale(g_temp3);
                    GCS_Scale pgcs4 = DBConnection.getGcs_scale(g_temp4);
                    GCS_Scale pgcs5 = DBConnection.getGcs_scale(g_temp5);
                    cons.tbl_pgcs.getModel().setValueAt(pgcs3.getScale_desc()+"/"+pgcs4.getScale_desc()+"/"+pgcs5.getScale_desc(), i, 0);
                    int point = Integer.parseInt(pgcs3.getScale_score().split(" ")[0]);
                    cons.tbl_pgcs.getModel().setValueAt(point, i, 1);
                    points += point;
                }
            }
        }
        String total = points + " point";
        total = points > 1 ? total + "s" : total;
        txt_total.setText(total);
        String result = "";
        if (points <= 8) {
            result = "Severe Brain Injury";
        } else if (points > 8 && points <= 12) {
            result = "Moderate Brain Injury";
        } else if (points > 12 && points <= 14 ) {
            result = "Minor Brain Injury";
        } else {
            result = "Normal";
        }
        txt_result.setText(result);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        txt_result = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnl_pgcs = new javax.swing.JPanel();

        setTitle("Glasgow Coma Scale (GCS)");
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(173, 182, 200));

        jLabel1.setText("Total Points :");

        jLabel2.setText("Result :");

        txt_total.setEditable(false);

        txt_result.setEditable(false);

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnl_pgcs.setBackground(new java.awt.Color(173, 182, 200));
        pnl_pgcs.setAutoscrolls(true);

        javax.swing.GroupLayout pnl_pgcsLayout = new javax.swing.GroupLayout(pnl_pgcs);
        pnl_pgcs.setLayout(pnl_pgcsLayout);
        pnl_pgcsLayout.setHorizontalGroup(
            pnl_pgcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        pnl_pgcsLayout.setVerticalGroup(
            pnl_pgcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnl_pgcs);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_total)
                    .addComponent(txt_result, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String total = txt_total.getText();
        String result = txt_result.getText();
        cons.txt_pgcs_points.setText(total);
        cons.txt_pgcs_result.setText(result);
        cons.pgcs_gui.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PGCS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PGCS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PGCS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PGCS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PGCS_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    protected static javax.swing.JPanel pnl_pgcs;
    protected static javax.swing.JTextField txt_result;
    protected static javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
