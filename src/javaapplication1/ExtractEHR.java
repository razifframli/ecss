/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Helper.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Compudyne
 */
public class ExtractEHR {

    /**
     * MSH|^~|CIS^001|<cr>
    PDI|PMS10003^LEE WEI CHUAN^891031075331^Chinese^Male^31/10/1989^AB^Married^|<cr>
    CCN|Knows the complaints procedure (finding)^Mild^Select One^2Minutes^Select One^ serious^Active^2012-08-03 
    13:12:47.17^|<cr>
    DGS|Able to perform recreational use of conversation (finding)^Mild^08/08/2012^2012-08-03 13:12:47.17^|<cr>
    IMU|Consent status for immunizations (finding)^as^02/08/2012^2012-08-03 13:12:47.17^|<cr>
    ALG|Allergic reaction to drug^10/08/2012^aaaa^2012-08-03 13:12:47.17^|<cr>
    SH|Cigar smoker^01/08/2012^heavy^2012-08-03 13:12:47.17^|<cr>
     */
   
     //Get 7 latest EHR
    public List getEHRLatest7(String pmi_no) throws ClassNotFoundException, SQLException {
        
        List list = new ArrayList();
       
        
        //String sql = "SELECT * FROM EHR_CENTRAL WHERE PMI_NO=?";
        
        String sql = "select * from ehr_central where pmi_no=? order by c_txndate desc limit 7";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, pmi_no);
        ResultSet rs = ps.executeQuery();
        String[] ehr = null;
        while (rs.next()) {
            ehr = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.println("...record....:"+rs.getString(i + 1));
                
                ehr[i] = rs.getString(i + 1);
            
            }
            System.out.println("...Next Record....");
            list.add(ehr);
            
        }
            System.out.println("...Return lsit size...."+list.size());
            
//             for (int i = 0; i < list.size(); i++) {
//                 System.out.println(".extract..list....:" + list.get(i));
//                  System.out.println("***************");
//             }
            
        return list;
    }
    
    
    //save EHR - not being used at the mmt
    
   public void addEHR(String data, String PMI_No, String facility_code) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseConnection.MySQLconnect();
        System.out.println("......data...: " + data);

        //insert data into EHR_Central table
        //PreparedStatement ps = conn.prepareStatement("INSERT INTO EHR_CENTRAL (PMI_NO,C_TXNDATE,C_TXNDATA)VALUES (?,?,?)");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO EHR_CENTRAL_2 (PMI_NO,FACILITY_CODE, EPISODE_DATE, ENCOUNTER_DATE,DATA)VALUES (?,?,?,?,?)");
        //encounter_date already in the message (clob)

        //hfc_code - Kajang
        //episode - date
        //encounter - date
        java.util.Date today = new java.util.Date();
        java.sql.Date sqlToday = new java.sql.Date(today.getTime());

        ps.setString(1, PMI_No);
        ps.setString(2, facility_code);
        ps.setDate(3, sqlToday); //20102012 11:50:35
        ps.setDate(4, null); // no need encounter as already in the message (clob)
        ps.setString(5, data);
        ps.execute();

    }

    //get patient biodata from database when actor click table row from appointment list
    public String[] getEHR(String pmi_no) throws ClassNotFoundException, SQLException {

        Connection conn = DatabaseConnection.MySQLconnect();

        String[] ehr = new String[5];

        String sql = "SELECT * FROM EHR_CENTRAL WHERE PMI_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, pmi_no);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            for (int i = 0; i < 4; i++) {
                System.out.print(rs.getString(i + 1));
                ehr[i] = rs.getString(i + 1);
            }
        }

        conn.close();
        return ehr;
    }
}
