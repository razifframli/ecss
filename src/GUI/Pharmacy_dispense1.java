/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Helper.J;
import java.util.Properties;

/**
 *
 * @author umarmukhtar
 */
public class Pharmacy_dispense1 extends javax.swing.JFrame {

    private static int index_row = -1;
    private static int stock = 0;
    private static int quantity = 0;
    private static int new_qty = 0;
    private static int new_stock = 0;
    private static double price_per_unit = 0;
    private static double price = 0;
    
    /**
     * Creates new form Pharmacy_dispense1
     */
    public Pharmacy_dispense1(Properties prop) {
        initComponents();
        
        try {
            index_row = Integer.parseInt(prop.getProperty("ind"));
            stock = Integer.parseInt(prop.getProperty("sto"));
            
            String qty_temp = prop.getProperty("qty");
            String qty_split[] = qty_temp.split("<HTML><U>");
            String qty_split2[] = qty_split[1].split("</U></HTML>");
            String qty2 = qty_split2[0];
            
            quantity = Integer.parseInt(qty2);
            new_qty = 0;
            new_stock = Integer.parseInt(prop.getProperty("sto"));
            price_per_unit = Double.parseDouble(prop.getProperty("ppu"));
            price = Double.parseDouble(prop.getProperty("pri"));
        } catch (Exception e) {
            e.printStackTrace();
            index_row = -1;
            stock = 0;
            quantity = 0;
            new_qty = 0;
            new_stock = 0;
            price_per_unit = 0.0;
            price = 0.0;
        }
        
        setReset();
    }
    
    private static void setReset() {

        lbl_stock.setText(stock+"");
        lbl_qty.setText(quantity + "");
        txt_newqty.setText("0");
        lbl_newbalance.setText(stock+"");
        lbl_ppu.setText(price_per_unit + "");
        lbl_price.setText(price + "");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_qty = new javax.swing.JLabel();
        lbl_stock = new javax.swing.JLabel();
        lbl_newbalance = new javax.swing.JLabel();
        lbl_ppu = new javax.swing.JLabel();
        lbl_price = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_newqty = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dispense Detail");

        jLabel1.setText("Stock :");

        jLabel2.setText("Dispensed Qty :");

        jLabel3.setText("Qty to be dispensed : ");

        jLabel4.setText("New Stock :");

        jLabel5.setText("Price/Unit :");

        jLabel6.setText("Price :");

        lbl_qty.setText("0");

        lbl_stock.setText("0");

        lbl_newbalance.setText("0");

        lbl_ppu.setText("0");

        lbl_price.setText("0");

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txt_newqty.setText("0");
        txt_newqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_newqtyKeyReleased(evt);
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
                        .addGap(65, 65, 65)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_newqty, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_price, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_ppu, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_newbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_stock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_qty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_newqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_newbalance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_ppu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbl_price))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_newqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_newqtyKeyReleased
        // TODO add your handling code here:
        try {
            int stock = Integer.parseInt(lbl_stock.getText());
            int qty = Integer.parseInt(lbl_qty.getText());
            int newqty = 0;
            if (txt_newqty.getText().equals("") || txt_newqty.getText() == "" || txt_newqty.getText() == null) {
                newqty = 0;
            } else {
                newqty = Integer.parseInt(txt_newqty.getText());
            }
            if (newqty < 0) {
                setReset();
                J.o("Invalid Quantity", "Invalid Quantity!\nPlease insert valid new quantity.", 0);
            }
            if (newqty > stock) {
                setReset();
                J.o("Invalid Quantity", "Invalid Quantity!\nPlease insert valid new quantity.", 0);
            }
            int newstock = stock - newqty;
            double priceperunit = Double.parseDouble(lbl_ppu.getText());
            double pricenew = newqty * priceperunit;
            lbl_newbalance.setText(newstock+"");
            lbl_price.setText(pricenew+"");
            
//            dispose();
        } catch (Exception e) {
            setReset();
            J.o("Invalid Quantity", "Invalid Quantity!\nPlease insert valid new quantity.", 0);
        }
    }//GEN-LAST:event_txt_newqtyKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String newqty = txt_newqty.getText();
        try {
            int new_qty = Integer.parseInt(newqty);
            if (new_qty <= 0) {
                J.o("Invalid Quantity!", "Invalid dispensed quantity!", 0);
                return;
            }
        } catch (Exception e) {
            J.o("Invalid Quantity!", "Invalid dispensed quantity!", 0);
            return;
        }
        
        Pharmacy.tbl_drugList.setValueAt(lbl_newbalance.getText(), index_row, 6); //sto
        Pharmacy.tbl_drugList.setValueAt(txt_newqty.getText(), index_row, 7); //qty
        Pharmacy.tbl_drugList.setValueAt(lbl_price.getText(), index_row, 9); //pri
        
        String curr_total = Pharmacy.jLabel_Total.getText();
        
        try {
            double back_total = Double.parseDouble(curr_total) - price;
            double new_total = back_total + Double.parseDouble(lbl_price.getText());
            Pharmacy.jLabel_Total.setText(Pharmacy.format_cash.format(new_total));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        dispose();
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
            java.util.logging.Logger.getLogger(Pharmacy_dispense1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pharmacy_dispense1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pharmacy_dispense1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pharmacy_dispense1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Pharmacy_dispense1().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JLabel lbl_newbalance;
    private static javax.swing.JLabel lbl_ppu;
    private static javax.swing.JLabel lbl_price;
    private static javax.swing.JLabel lbl_qty;
    private static javax.swing.JLabel lbl_stock;
    private static javax.swing.JTextField txt_newqty;
    // End of variables declaration//GEN-END:variables
}
