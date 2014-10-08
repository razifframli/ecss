/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InpatientFrm.java
 *
 * Created on Feb 27, 2011, 2:05:33 PM
 */

package AdtFrm;

import AccessDB.AccessDB;
import Bean.PatientBean1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jessica
 */
public class InpatientFrm extends javax.swing.JFrame {
        private String selectedPatient;

    /** Creates new form InpatientFrm */
    public InpatientFrm() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInpatient = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        label1.setBackground(new java.awt.Color(204, 204, 204));
        label1.setFont(new java.awt.Font("Dialog", 1, 12));
        label1.setText("INPATIENT");

        tblInpatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "PMI No", "Patient Name", "New IC No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInpatient.setColumnSelectionAllowed(true);
        tblInpatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInpatientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInpatient);
        tblInpatient.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblInpatient.getColumnModel().getColumn(0).setResizable(false);
        tblInpatient.getColumnModel().getColumn(1).setResizable(false);
        tblInpatient.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 1263, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Connection conn = AccessDB.getConnInstance();
        PatientBean1[] patient=AccessDB.Patient(conn);

        for (int j=0;j<patient.length;j++){
            tblInpatient.setValueAt(patient[j].getPmiNo(),j, 0);
            tblInpatient.setValueAt(patient[j].getName(),j,1);
            tblInpatient.setValueAt(patient[j].getNewIcNo(),j,2);
        }
    }//GEN-LAST:event_formWindowActivated

    private void tblInpatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInpatientMouseClicked
        int rowIndex = tblInpatient.getSelectedRow();
        selectedPatient =(String) tblInpatient.getValueAt(rowIndex,0);

        System.out.println(rowIndex);
        System.out.println(selectedPatient);

        System.out.println(tabName);
        MainFrm main = new MainFrm();
        main.setVisible(true);
        main.setSelectedPatient(selectedPatient,tabName);
        System.out.println("here");
        this.dispose();
    }//GEN-LAST:event_tblInpatientMouseClicked

    public void getLocation(String tabName)
    {
        this.tabName = tabName;
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InpatientFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable tblInpatient;
    // End of variables declaration//GEN-END:variables
    private String tabName;
}
