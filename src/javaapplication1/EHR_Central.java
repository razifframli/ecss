/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import DBConnection.DBConnection;
import Helper.J;
import Helper.S;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.jms.JMSException;
import library.Q;

/**
 *
 * @author End User
 */
public class EHR_Central {
    //boolean status = NetworkStatus.DoPing("58.71.136.10");
    //Method for formating header message
    String msgheader = "";
    String data = "";

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

    //Method for storing formatted message into Journal File
    public void insertJournal(int status, String header, String patientInfo, 
            String msgs[], String PMI, String date1)
            throws ClassNotFoundException, SQLException, ParseException {
        System.out.println("......Start insertJournal.......");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new java.sql.Date(System.currentTimeMillis());
        try {
            date = Date.valueOf(date1);
        } catch (Exception eee) {
            date = new java.sql.Date(System.currentTimeMillis());
        }
        String data = header + patientInfo;
        for(int i = 0; i < msgs.length; i++) {
            if(msgs[i].length() > 0) {
                data += msgs[i];
            }
        }
        
        String statusSync = "T";
//        if(Session.getPrev_stat()) {
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");

                DBConnection.getImpl().sayHello("T/P");
                statusSync = "T";
            } catch (Exception e) {
                statusSync = "P";
            }
//        } else {
//            statusSync = "P";
//        }

        S.oln("\n\n\n\nNah1\n" + data + "\n\n\n\n");

        //insert data into Journal_File table
        String sql = "INSERT INTO JOURNAL_FILE (PMI_NO,TxnDate,TxnData,Status,STATUS2)VALUES(?,?,?,?,?)";
        Q.sPs(sql);
        Q.s(1, PMI);
        Q.s(2, date);
        Q.s(3, data);
        Q.s(4, statusSync);
        Q.s(5, status);
        Q.gPs().execute();
        System.out.println("......End insertJournal.......");
    }
    
    public void updateJournalSync(int txncode, String date)
            throws ClassNotFoundException, SQLException {
        
        String statusSync = "T";

        S.oln("\n\n\n\nNah1\n" + data + "\n\n\n\n");

        //insert data into Journal_File table
        String sql = "UPDATE JOURNAL_FILE SET Status = ? WHERE TXNCODE = ? AND TxnDate = ?";
        Q.sPs(sql);
        Q.s(1, statusSync);
        Q.s(2, txncode);
        Q.s(3, date);
        Q.gPs().execute();
        System.out.println("......End updateJournalSync.......");
    }

    //Method for storing formatted message into Central database
    public void insertCentral(int status, String header, String patientInfo, 
            String msgs[], 
            String PMI,
            String episodeDate) throws ClassNotFoundException, SQLException {

        String data = header + patientInfo;
        String dataDTO = data;
        String dataPOS = data;
        boolean isDTO = false;
        boolean isPOS = false;
        for(int i = 0; i < msgs.length; i++) {
            if(msgs[i].length() > 0) {
                data += msgs[i];
                if(msgs[i].split("\\|")[0].contains("DTO")) {
                    isDTO = true;
                    dataDTO += msgs[i];
                }
                if(msgs[i].split("\\|")[0].contains("POS")) {
                    isPOS = true;
                    dataPOS += msgs[i];
                }
            }
        }

        S.oln("\n\n\n\nNah2\n" + data + "\n\n\n\n");
        //insert data into EHR_Central table
        //Connection conn = DatabaseConnection.connect(); //hsql
//        Connection conn = connection.MySQLconnect();
//        PreparedStatement ps = conn.prepareStatement("INSERT INTO EHR_CENTRAL (PMI_NO,C_TXNDATE,C_TXNDATA)VALUES (?,?,?)");
//        ps.setString(1, PMI);
//        ps.setDate(2, date);
//        ps.setString(3, data);
//        ps.execute();

        //Friza - insert CIS
        //Insert thru RMI - invoke remote method and save to MySql
        
        System.out.println("Start invoke remote server - Saving CIS to server during Discharge");
        
        S.oln("\nDischarge DTO :-\n"+dataDTO+"\n");

        try {
            // fire to server port 1099
//            ArrayList<String> listOnline = Func.readXML("online");
//            Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//            // search for myMessage service
//            Message impl = (Message) myRegistry.lookup("myMessage");
            // call server's method			
            //impl.sayHello("..Friza ");

            //String IC = "891031075331"; //for testing purpose
            String ehr_central = DBConnection.getImpl().insertEHRCentral(status, PMI, data, episodeDate); //Insert CIS
            if (status == 1) {
                if (isDTO) {
                    String dto = DBConnection.getImpl().insertDTO(PMI, dataDTO);
                }
            }
            if (isPOS) {
                String pos = DBConnection.getImpl().insertPOS(PMI, dataPOS);
            }
            System.out.println(ehr_central);

            System.out.println(".....Message Sent....");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //Method for storing formatted message into Central database
    public boolean insertCentralSync(int status, String data, 
            String PMI,
            String episodeDate) throws ClassNotFoundException, SQLException {
        
        boolean stat = false;

        S.oln("\n\n\n\nNah2\n" + data + "\n\n\n\n");
        //insert data into EHR_Central table
        //Connection conn = DatabaseConnection.connect(); //hsql
//        Connection conn = connection.MySQLconnect();
//        PreparedStatement ps = conn.prepareStatement("INSERT INTO EHR_CENTRAL (PMI_NO,C_TXNDATE,C_TXNDATA)VALUES (?,?,?)");
//        ps.setString(1, PMI);
//        ps.setDate(2, date);
//        ps.setString(3, data);
//        ps.execute();

        //Friza - insert CIS
        //Insert thru RMI - invoke remote method and save to MySql
        
        System.out.println("Start invoke remote server - Saving CIS to server during Discharge");
        
        //S.oln("\nDischarge DTO :-\n"+dataDTO+"\n");

        try {
            // fire to server port 1099
//            ArrayList<String> listOnline = Func.readXML("online");
//            Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//            // search for myMessage service
//            Message impl = (Message) myRegistry.lookup("myMessage");
            // call server's method			
            //impl.sayHello("..Friza ");

            //String IC = "891031075331"; //for testing purpose
            String s = DBConnection.getImpl().insertEHRCentral(status, PMI, data, episodeDate); //Insert CIS
            //String s2 = impl.insertDTO(PMI, dataDTO);
            System.out.println(s);

            System.out.println(".....Message Sent....");
            stat = true;
        } catch (Exception e) {
            stat = false;
            J.o("Network Down!", "Sorry! Network still not connected ..", 0);
            //e.printStackTrace();
        }
        
        return stat;

    }
}
