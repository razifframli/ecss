/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import DBConnection.DBConnection;
import Helper.J;
import Helper.S;
import Helper.Session;
import Process.MainRetrieval;
import config.Config;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.swing.JOptionPane;
import library.Func;
import library.NetworkStatus;
import library.Q;
import oms.rmi.server.Message;

/**
 *
 * @author user
 */
public class FormattedMessage implements Runnable {

    

    //Check network status and monitor the transmission of data
    public void run() {
        //Connection conn = null;
        //Connection conn1 = null;
        boolean active = true;

        while (active) {

//            try {
//                Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
//                //boolean status = NetworkStatus.DoPing("58.71.136.10");//58.71.136.10//10.1.3.83
//                //From Journal File to central database (Clob -> Individual tables)
////                if (NetworkStatus.DoPing(Config.getIpServer(), 2000)) { //server
//
//                    try {
//                        //conn = DatabaseConnection.connect();
//                        //conn1 = connection.MySQLconnect();
//                        //Find out Journal file data with "O"
//                        PreparedStatement ps = Session.getCon_x(100).prepareStatement("SELECT * FROM JOURNAL_FILE WHERE Status = ?");
//                        ps.setString(1, "P");
//                        // --- reading the columns
//
//                        ResultSet rs = ps.executeQuery();
//                        String clobData = "";
//                        while (rs.next()) {
//                            //Read JournalFile data
//                            int txnCode = rs.getInt("TxnCode");
//                            String pid = rs.getString("PMI_NO");
//                            System.out.println(pid);
//                            clobData = rs.getString("TxnData");//full clob data
//                            System.out.println("....[FormattedMessage-run]...Clob Data : " + clobData);
//
//                            //Store data into the central database
//                            ps = Session.getCon_x(100).prepareStatement("INSERT INTO EHR_CENTRAL (PMI_NO,C_TXNDATE,C_TXNDATA)VALUES (?,?,?)");
//                            ps.setString(1, pid);
//                            ps.setTimestamp(2, timestamp);
//                            ps.setString(3, clobData);
//                            ps.execute();
//
//
//                            //Update the Journal File data to "T" after being transmit to central database
//                            ps = Session.getCon_x(100).prepareStatement("UPDATE JOURNAL_FILE SET status=? WHERE TxnCode=?");
//                            ps.setString(1, "T");
//                            ps.setInt(2, txnCode);
//                            ps.execute();
//                            JOptionPane.showMessageDialog(null, "Data is transmitted.");
//
//                        }
//
//                        //Save the network status into log audit
//                        String sql = "INSERT INTO Log_Audit(DATE_TIME,LOG_STATUS) VALUES (?,?)";
//                        ps = Session.getCon_x(100).prepareStatement(sql);
//                        java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
//                        ps.setTimestamp(1, sqlDate);
//                        ps.setString(2, "Online");
//                        ps.executeUpdate();
//                        
//                        Consultation.showOnline();//Online
//
//                    } catch (Exception ex) {
//                        System.out.print(ex);
//                         //Save the network status into log audit
//                        //conn = DatabaseConnection.connect();
//                        String sql = "INSERT INTO Log_Audit(DATE_TIME,LOG_STATUS) VALUES (?,?)";
//                        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//                        java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
//                        ps.setTimestamp(1, sqlDate);
//                        ps.setString(2, "Offline");
//                        ps.executeUpdate();
//
//                        Consultation.showOffline();//Offline
//                    }
//                
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//
//            }
            active = false;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FormattedMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
}
