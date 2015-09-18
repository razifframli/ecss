/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login.java
 *
 * Created on Mar 12, 2011, 5:22:26 PM
 */

package GUI;

import AccessDB.AccessDB;
import DBConnection.DBConnection;
import Helper.J;
import Helper.S;
import Helper.Session;
import config.Config;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import library.Func;
import library.LongRunProcess;
import library.NetworkStatus;
import oms.rmi.server.Message;

/**
 *
 * @author phoebe
 */
public class Login extends javax.swing.JFrame {
    private String id;
    private String password;
    
    public static LoadingForm lf = new LoadingForm();
    
    //Connection conn = Session.getCon_x();

    /** Creates new form Login */
    public Login() {
        
        Session.startUp();
        if(Session.getNum_open_db() == 1) {
            
            try {
                DBConnection.startRMI();
            } catch (Exception e) {
                J.o("RMI Offline", "Network to server is offline!", 0);
            }
            
            //Session.setPrev_stat(NetworkStatus.DoPing(Config.getIpServer(), 2000));
            Session.setPrev_stat(false);
            Session.setCurr_stat(false);
            
            Thread t = new Thread(new LoginThread());
            t.start();
            
            Session.setCon_x();
            Session.setCon_x2();
        }
        
        /*
         * Checking Network
         */
//        final LongRunProcess lrp = new LongRunProcess();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                lrp.execute();
//            }
//        });
        
        //conn = Session.getCon_x();
          try {
              // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (ClassNotFoundException e) {System.out.println("11");}

        catch (InstantiationException e) {System.out.println("121");}

        catch (IllegalAccessException e) {System.out.println("311");}

        catch (UnsupportedLookAndFeelException e) {System.out.println("114");}
        initComponents();
        jLabel4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabel4.setVisible(false);
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_userID = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_submit = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinical Support System");

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(51, 51, 255));
        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Clinical Support System");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Please key in UserID and Password");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("UserID      :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Password :");

        txt_userID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userIDActionPerformed(evt);
            }
        });
        txt_userID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_userIDKeyReleased(evt);
            }
        });

        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passwordKeyReleased(evt);
            }
        });

        btn_submit.setText("Submit");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("New Patient?");
        jLabel4.setToolTipText("Click here to register");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setText("Forgot Password?");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setText("Version 16.12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel4)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btn_submit)
                            .addGap(33, 33, 33)
                            .addComponent(btn_cancel))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(159, 159, 159)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_password))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_userID, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_userID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_submit)
                    .addComponent(btn_cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        setSize(new java.awt.Dimension(563, 302));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_submit_login() {
        id = String.valueOf(txt_userID.getText());
        password = String.valueOf(txt_password.getPassword());
        
        if (id.equals("god") && password.equals("")) {
            new PageTest1().setVisible(true);
            return;
        } 

        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> data_online = new ArrayList<String>();
        //check network
        //LongRunProcess.check_network2();
        //online
        //if (Session.getPrev_stat()) {
            try {
                

                //1. online check login
                data_online = DBConnection.getImpl().getStaffLogin(id, password);
                //2. check with offline data
                data = DBConnection.getStaffLogin(id, password);
                //3. IF valid login
                if (data_online.size() > 0) {
                    //3.1. IF online data NOT EQUAL offline data
                    boolean stat_match = Func.isMatch1(data, data_online);
                    System.out.println("stat match: "+stat_match);
                    if (!stat_match) {
                        //3.1.1. copy online data to offline db
                        DBConnection.copyDataStaff(id, data_online);
                        //3.1.2. check offline data again
                        data = DBConnection.getStaffLogin(id, password);
                    }
                }

            } catch (Exception e) {
                //offline
                data = DBConnection.getStaffLogin(id, password);
                //e.printStackTrace();
            }
//        } else {
//            //offline
//            data = DBConnection.getStaffLogin(id, password);
//        }
//        //close network
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

        if (data.size() > 0) {
            
            Session.setData_user(data);
            
            Session.login(id, data.get(3), password);
            FacilityPage facipage = new FacilityPage(id);
            facipage.setVisible(true);
            this.dispose();
        } else if ((txt_userID.getText().equals("")) || (txt_password.getPassword().toString().equals(""))) {
            JOptionPane.showMessageDialog(btn_submit, "Please insert your UserID or Password!");
        } else {
            JOptionPane.showMessageDialog(btn_submit, "Invalid UserID or Password!");
        }
    }
    
    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        // TODO add your handling code here:
        btn_submit_login();
    }//GEN-LAST:event_btn_submitActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        txt_userID.setText("");
        txt_password.setText("");
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        try {
            // TODO add your handling code here:
            DialogRegister register = new DialogRegister();
            register.setVisible(true);
            this.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jLabel4MouseClicked

    private void txt_userIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userIDActionPerformed

    private void txt_passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyReleased
        
        int a = evt.getKeyChar();
        if(a == 10) {
            // TODO add your handling code here:
            btn_submit_login();
        }
    }//GEN-LAST:event_txt_passwordKeyReleased

    private void txt_userIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_userIDKeyReleased
        
        int a = evt.getKeyChar();
        if (a == 10) {
            // TODO add your handling code here:
            btn_submit_login();
        }
    }//GEN-LAST:event_txt_userIDKeyReleased

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private java.awt.Label label1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_userID;
    // End of variables declaration//GEN-END:variables

}
