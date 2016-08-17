/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Helper.J;
import Helper.S;
import api.Queue;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author End User
 */
public class RegQueueList extends javax.swing.JFrame {

    /**
     * Creates new form RegQueueList
     */
    public RegQueueList() {
        initComponents();
    }
    
    public void list_Queue(int stat, String ch) {
        //Vector queueinfo = new Vector();
        Queue queue1 = new Queue();
        //try
        {
            try {
                Vector data = null;
                //queueinfo = queue1.getQueueNameList(ch);

                switch(stat) {
                    case 1:
                        data = queue1.getQueueNameList(ch, 2);
                        break;
                    case 2:
                        data = queue1.getQueueIcList(ch);
                        break;
                }
                
                S.oln("DATA: "+data);
                Vector<String> header = new Vector<String>();
                header.add("PMI_NO");
                header.add("NAME");
                header.add("EPISODE_TIME");
                header.add("QUEUE NAME");
                header.add("QUEUE NO.");
                header.add("DOCTOR");
                header.add("STATUS");
                header.add("ACTION");


                tblQueueREG.setModel(new javax.swing.table.DefaultTableModel(data, header) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });



            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQueueREG = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient Queue List");

        label1.setBackground(new java.awt.Color(204, 204, 204));
        label1.setText("PATIENT QUEUE LIST");

        tblQueueREG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PMI_NO", "NAME", "EPISODE_TIME", "CONSULTATION_ROOM", "DOCTOR", "STATUS", "ACTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQueueREG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQueueREGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQueueREG);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 88, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblQueueREGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQueueREGMouseClicked
        // TODO add your handling code here:
        
        int index = tblQueueREG.getSelectedColumn();
        if (index == 7) {
            
            int rowIndex = tblQueueREG.getSelectedRow();
            String pmiNo = tblQueueREG.getValueAt(rowIndex, 0).toString();
            String time = tblQueueREG.getValueAt(rowIndex, 2).toString();
            
            System.out.println("pmiNo:"+pmiNo);
            System.out.println("time:"+time);
            
            String query = "DELETE FROM PMS_EPISODE WHERE PMI_NO = ? AND EPISODE_TIME = ? ";
            String data[] = {pmiNo, time};
            try {
                boolean statExec = DBConnection.DBConnection.getImpl().setQuery(query, data);
                if (statExec) {
                    J.o("Succeed", "Succeed deleting patient in queue.", 1);
                    Registration.que.dispose();
                } else {
                    J.o("Error While Deleting", "Opss! There's an error while deleting that data. ..", 0);
                }
            } catch (RemoteException ex) {
                J.o("Error While Deleting", "Opss!\nThere's an error while deleting that data.\nError: "+ex.getMessage(), 0);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_tblQueueREGMouseClicked

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
            java.util.logging.Logger.getLogger(RegQueueList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegQueueList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegQueueList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegQueueList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegQueueList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    public static javax.swing.JTable tblQueueREG;
    // End of variables declaration//GEN-END:variables
}
