/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Helper.Session;
//import conndb.connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.jms.JMSException;
//import javax.swing.JOptionPane;

/**
 *
 * @author Compudyne
 */
public class SaveEHR {
    
    String msgheader = "";
    String data = "";
    
     //Method for storing formatted message into Journal File
    public void insertJournal(String header, String patientInfo, String msgCC, String msgDIAG, String msgIMM,
            String msgVS, String msgDRUG, String msgDIS, String msgALG, String msgSH, String PMI) throws ClassNotFoundException, SQLException {
        System.out.println("..SaveEHR....Start insertJournal.......");
        Date date = new Date(new java.util.Date().getTime());
        String data = header + patientInfo + msgCC + msgDIAG + msgIMM + msgVS + msgDRUG + msgDIS + msgALG + msgSH;
        
        System.out.println("[SaveEHR-insertJournal]..data..:"+data);
        
        //Connection conn = DatabaseConnection.connect();
        //insert data into Journal_File table
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement("INSERT INTO JOURNAL_FILE (PMI_NO,TxnDate,TxnData,Status)VALUES(?,?,?,?)");
        ps.setString(1, PMI);
        ps.setDate(2, date);
        ps.setString(3, data);
        ps.setString(4, "O");
        ps.execute();
        System.out.println("..SaveEHR....End insertJournal.......");
    }

    //Method for storing formatted message into Central database
    public void insertCentral(String header, String patientInfo, String msgCC, String msgDIAG, String msgIMM,
            String msgVS, String msgDRUG, String msgDIS, String msgALG, String msgSH, String PMI) throws ClassNotFoundException, SQLException {

        Date date = new Date(new java.util.Date().getTime());
        String data = header + patientInfo + msgCC + msgDIAG + msgIMM + msgVS + msgDRUG + msgDIS + msgALG + msgSH;
        //Connection conn = DatabaseConnection.connect(); //hsql
        //Connection conn = connection.MySQLconnect();
        //insert data into EHR_Central table
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement("INSERT INTO EHR_CENTRAL (PMI_NO,C_TXNDATE,C_TXNDATA)VALUES (?,?,?)");
        ps.setString(1, PMI);
        ps.setDate(2, date);
        ps.setString(3, data);
        ps.execute();

    }
    
    public String formatHeader(String header, String patientInfo) {

        msgheader = header + patientInfo;

        return msgheader;
    }

    //Method for formating message
    public String formatMsg(String msgCC, String msgDIAG, String msgIMM,
            String msgVS, String msgDRUG, String msgDIS, String msgALG, String msgSH, String msgHPI, String msgBLD) throws JMSException {

        data = msgCC + msgDIAG + msgIMM + msgVS + msgDRUG + msgDIS + msgALG + msgSH + msgHPI + msgBLD + "\n";
        //String msg = msgheader + data;


        //sentQueue sq = new sentQueue();
        //sq.sentQueue(msg);
        return data;

    }
}
