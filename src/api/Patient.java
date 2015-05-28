/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

//import package or classes
import DBConnection.DBConnection;
import Database.DbConnection;
import GUI.Registration;
import Helper.J;
import Helper.S;
import Helper.Session;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.DatabaseConnection;
import javax.swing.JOptionPane;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;

/**
 *
 * @author Chung Wei Ming
 */
public class Patient {
    
    private Registration registration;

    public Patient(Registration reg){
        registration = reg;
    }
    
    public static ArrayList<String> getTableMedical(String pmino) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO = ?";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, pmino);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                data.add(rs.getString("PMI_NO")); //0
                data.add(rs.getString("INSURANCE_SEQ_NO")); //1
                data.add(rs.getString("INSURANCE_COMPANY_CODE")); //2
                data.add(rs.getString("POLICY_NO")); //3
                data.add(rs.getString("MATURITY_DATE")); //4
                data.add(rs.getString("HEALTH_FACILITY")); //5
                data.add(rs.getString("POLICY_STATUS")); //6
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<String> getTableFamily(String pmino) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO = ?";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, pmino);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                data.add(rs.getString("PMI_NO")); //0
                data.add(rs.getString("FAMILY_SEQ_NO")); //1
                data.add(rs.getString("FAMILY_RELATIONSHIP_CODE")); //2
                data.add(rs.getString("PMI_NO_FAMILY")); //3
                data.add(rs.getString("FAMILY_MEMBER_NAME")); //4
                data.add(rs.getString("OCCUPATION_CODE")); //5
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<String> getTableNOK(String pmino) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO = ?";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, pmino);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                data.add(rs.getString("PMI_NO")); //0
                data.add(rs.getString("NEXTOFKIN_SEQ_NO")); //1
                data.add(rs.getString("NEXTOFKIN_RELATIONSHIP_CODE")); //2
                data.add(rs.getString("NEXTOFKIN_NAME")); //3
                data.add(rs.getString("NEW_IC_NO")); //4
                data.add(rs.getString("OLD_IC_NO")); //5
                data.add(rs.getString("ID_TYPE")); //6
                data.add(rs.getString("ID_NO")); //7
                data.add(rs.getString("BIRTH_DATE")); //8
                data.add(rs.getString("OCCUPATION_CODE")); //9
                data.add(rs.getString("ADDRESS")); //10
                data.add(rs.getString("DISTRICT_CODE")); //11
                data.add(rs.getString("TOWN_CODE")); //12
                data.add(rs.getString("POSTCODE")); //13
                data.add(rs.getString("STATE_CODE")); //14
                data.add(rs.getString("COUNTRY_CODE")); //15
                data.add(rs.getString("MOBILE_PHONE")); //16
                data.add(rs.getString("HOME_PHONE")); //17
                data.add(rs.getString("E_MAIL")); //18
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<String> getTableEmploy(String pmino) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO = ?";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, pmino);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                data.add(rs.getString("PMI_NO")); //0
                data.add(rs.getString("EMPLOYMENT_SEQ_NO")); //1
                data.add(rs.getString("EMPLOYER_CODE")); //2
                data.add(rs.getString("EMPLOYER_NAME")); //3
                data.add(rs.getString("OCCUPATION_CODE")); //4
                data.add(rs.getString("JOINED_DATE")); //5
                data.add(rs.getString("INCOME_RANGE_CODE")); //6
                data.add(rs.getString("HEALTH_FACILITY")); //7
                data.add(rs.getString("CREATE_DATE")); //8
                data.add(rs.getString("EMPLOYMENT_STATUS")); //9
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

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
    //save EHR
    public void addEHR(String data, String PMI_No, String facility_code) throws ClassNotFoundException, SQLException {
        System.out.println("......data...: " + data);

        //insert data into EHR_Central table
        //PreparedStatement ps = conn.prepareStatement("INSERT INTO EHR_CENTRAL (PMI_NO,C_TXNDATE,C_TXNDATA)VALUES (?,?,?)");
        String sql = "INSERT INTO EHR_CENTRAL_2 (PMI_NO,FACILITY_CODE, EPISODE_DATE, ENCOUNTER_DATE,DATA)VALUES (?,?,?,?,?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
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
        
        
        String[] ehr = new String[5];
        
        String sql = "SELECT * FROM EHR_CENTRAL WHERE PMI_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, pmi_no);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            for (int i = 0; i < 4; i++) {
                System.out.print(rs.getString(i + 1));
                ehr[i] = rs.getString(i + 1);
            }
        }

        return ehr;
    }
    
    //Get 7 latest EHR
    public String[] getEHR7Latest(String pmi_no) throws ClassNotFoundException, SQLException {
        
        
        String[] ehr = new String[7];
        
        //String sql = "SELECT * FROM EHR_CENTRAL WHERE PMI_NO=?";
        
        String sql = "select * from ehr_central where pmi_no=? order by c_txndate desc limit 7";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, pmi_no);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            for (int i = 0; i < 8; i++) {
                System.out.print(rs.getString(i + 1));
                ehr[i] = rs.getString(i + 1);
            }
        }

        return ehr;
    }
    
    
        //save patient family information into database
        public void addPatientFamilyInfo(String[] familyinfo) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO PMS_FAMILY (PMI_NO,FAMILY_SEQ_NO,FAMILY_RELATIONSHIP_CODE,PMI_NO_FAMILY,FAMILY_MEMBER_NAME,OCCUPATION_CODE) VALUES ('" + familyinfo[0] + "','" + familyinfo[1] + "','" + familyinfo[2] + "','" + familyinfo[3] + "','" + familyinfo[4] + "','" + familyinfo[5] + "')";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//        String sql2 = "INSERT INTO AUTOGENERATE_FSNO (FAMILY_SEQ_NO) VALUES ('" + familyinfo[1] + "')";
//        PreparedStatement ps2 = Session.getCon_x(100).prepareStatement(sql2);
        ps.executeUpdate();
//        ps2.executeUpdate();
    }

        //save patient insurance information into database
        public void addPatientInsuranceInfo(String[] insuranceinfo) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO PMS_MEDICAL_INSURANCE (PMI_NO,INSURANCE_SEQ_NO,INSURANCE_COMPANY_CODE,POLICY_NO,MATURITY_DATE,HEALTH_FACILITY,POLICY_STATUS) VALUES ('" + insuranceinfo[0] + "','" + insuranceinfo[1] + "','" + insuranceinfo[2] + "','" + insuranceinfo[3] + "','" + insuranceinfo[4] + "','" + insuranceinfo[5] + "','" + insuranceinfo[6] + "')";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.executeUpdate();
    }

        //save patient next of kin information into database
        public void addPatientNextOfKinInfo(String[] nokinfo) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO PMS_NEXTOFKIN (PMI_NO,NEXTOFKIN_SEQ_NO,NEXTOFKIN_RELATIONSHIP_CODE,NEXTOFKIN_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,BIRTH_DATE,OCCUPATION_CODE,ADDRESS,DISTRICT_CODE,TOWN_CODE,POSTCODE,STATE_CODE,COUNTRY_CODE,MOBILE_PHONE,HOME_PHONE,E_MAIL) VALUES ('" + nokinfo[0] + "','" + nokinfo[1] + "','" + nokinfo[2] + "','" + nokinfo[3] + "','" + nokinfo[4] + "','" + nokinfo[5] + "','" + nokinfo[6] + "','" + nokinfo[7] + "','" + nokinfo[8] + "','" + nokinfo[9] + "','" + nokinfo[10] + "','" + nokinfo[11] + "','" + nokinfo[12] + "','" + nokinfo[13] + "','" + nokinfo[14] + "','" + nokinfo[15] + "','" + nokinfo[16] + "','" + nokinfo[17] + "','" + nokinfo[18] + "')";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.executeUpdate();
//        String sql2 = "INSERT INTO AUTOGENERATE_NOKSNO (NEXTOFKIN_SEQ_NO) VALUES ('" + nokinfo[1] + "')";
//        PreparedStatement ps2 = Session.getCon_x(100).prepareStatement(sql2);
//        ps2.executeUpdate();
        
    }

        //save patient biodata into database
     public boolean addPatientBiodata(String[] biodata)
    {
        try {
            String sql = "INSERT INTO PMS_PATIENT_BIODATA (PMI_NO,PMI_NO_TEMP,"
                    + "PATIENT_NAME,TITLE_CODE,NEW_IC_NO,OLD_IC_NO,ID_TYPE,"
                    + "ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE,"
                    + "BIRTH_DATE,SEX_CODE,MARITAL_STATUS_CODE,RACE_CODE,"
                    + "NATIONALITY,RELIGION_CODE,BLOOD_TYPE,BLOOD_RHESUS_CODE,"
                    + "ALLERGY_IND,CHRONIC_DISEASE_IND,ORGAN_DONOR_IND,"
                    + "HOME_ADDRESS,HOME_DISTRICT_CODE,HOME_TOWN_CODE,"
                    + "HOME_POSTCODE,HOME_STATE_CODE,HOME_COUNTRY_CODE,"
                    + "HOME_PHONE,POSTAL_ADDRESS,POSTAL_DISTRICT_CODE,"
                    + "POSTAL_TOWN_CODE,POSTAL_POSTCODE,POSTAL_STATE_CODE,"
                    + "POSTAL_COUNTRY_CODE,MOBILE_PHONE) "
                    + "VALUES ('" + biodata[0] + "','" + biodata[1] + "','" 
                    + biodata[2] + "','" + biodata[3] + "','" + biodata[4] 
                    + "','" + biodata[5] + "','" + biodata[6] + "','" 
                    + biodata[7] + "','" + biodata[8] + "','" + biodata[9] 
                    + "','" + biodata[10] + "','" + biodata[11] + "','" 
                    + biodata[12] + "','" + biodata[13] + "','" + biodata[14] 
                    + "','" + biodata[15] + "','" + biodata[16] + "','" 
                    + biodata[17] + "','" + biodata[18] + "','" + biodata[19] 
                    + "','" + biodata[20] + "','" + biodata[21] + "','" 
                    + biodata[22] + "','" + biodata[23] + "','" + biodata[24] 
                    + "','" + biodata[25] + "','" + biodata[26] + "','" 
                    + biodata[27] + "','" + biodata[28] + "','" + biodata[29] 
                    + "','" + biodata[30] + "','" + biodata[31] + "','" 
                    + biodata[32] + "','" + biodata[33] + "','" + biodata[34] 
                    + "')";
            
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            //            String sql2 = "INSERT INTO PMS_LOCAL_PATIENT_BIODATA (PMI_NO,PMI_NO_TEMP,PATIENT_NAME,TITLE_CODE,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE,BIRTH_DATE,SEX_CODE,MARITAL_STATUS_CODE,RACE_CODE,NATIONALITY,RELIGION_CODE,BLOOD_TYPE,BLOOD_RHESUS_CODE,ALLERGY_IND,CHRONIC_DISEASE_IND,ORGAN_DONOR_IND,HOME_ADDRESS,HOME_DISTRICT_CODE,HOME_TOWN_CODE,HOME_POSTCODE,HOME_STATE_CODE,HOME_COUNTRY_CODE,HOME_PHONE,POSTAL_ADDRESS,POSTAL_DISTRICT_CODE,POSTAL_TOWN_CODE,POSTAL_POSTCODE,POSTAL_STATE_CODE,POSTAL_COUNTRY_CODE,MOBILE_PHONE) VALUES ('" + biodata[0] + "','" + biodata[1] + "','" + biodata[2] + "','" + biodata[3] + "','" + biodata[4] + "','" + biodata[5] + "','" + biodata[6] + "','" + biodata[7] + "','" + biodata[8] + "','" + biodata[9] + "','" + biodata[10] + "','" + biodata[11] + "','" + biodata[12] + "','" + biodata[13] + "','" + biodata[14] + "','" + biodata[15] + "','" + biodata[16] + "','" + biodata[17] + "','" + biodata[18] + "','" + biodata[19] + "','" + biodata[20] + "','" + biodata[21] + "','" + biodata[22] + "','" + biodata[23] + "','" + biodata[24] + "','" + biodata[25] + "','" + biodata[26] + "','" + biodata[27] + "','" + biodata[28] + "','" + biodata[29] + "','" + biodata[30] + "','" + biodata[31] + "','" + biodata[32] + "','" + biodata[33] + "','" + biodata[34] + "')";
            //            PreparedStatement ps2 = Session.getCon_x(100).prepareStatement(sql2);
            String sql3 = "INSERT INTO AUTOGENERATE_PMI (PMI_NO) VALUES ('" 
                    + biodata[0] + "')";
            PreparedStatement ps3 = Session.getCon_x(100).prepareStatement(sql3);
            
//            LongRunProcess.check_network2();
//            if (Session.getPrev_stat()) {
                //Read BLOB from EHR_Central
                System.err.println();
                System.err.println("Server Online");
                System.out.println("Start invoke remote server");
                try {
                    // fire to server port 1099
//                    ArrayList<String> listOnline = Func.readXML("online");
//                    Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                    // search for myMessage service
//                    Message impl = (Message) myRegistry.lookup("myMessage");
                    // call server's method	
                    
                    DBConnection.getImpl().insertPatientBiodata(biodata);

                    System.out.println(".....Message Sent....");
                } catch (Exception e) {
                    //offline
                    e.printStackTrace();
                }
//            }
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
            
            S.oln("Register after online...\nGo offline...");
            
            PreparedStatement ps1 = Session.getCon_x(100).prepareStatement(sql);
//            PreparedStatement ps12 = Session.getCon_x(100).prepareStatement(sql2);
            PreparedStatement ps13 = Session.getCon_x(100).prepareStatement(sql3);

            S.oln("Insert new patient local, sql.......:" + sql);
//            S.oln("Insert new patient local, sql2.......:" + sql2);
            S.oln("Insert new patient local, sql3.......:" + sql3);

            ps1.executeUpdate();
//            ps12.executeUpdate();
            ps13.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            S.oln("Error Insert Data: "+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

     //save patient employment information into database
     public void addPatientEmploymentInfo(String[] employmentinfo) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO PMS_EMPLOYMENT (PMI_NO,EMPLOYMENT_SEQ_NO,EMPLOYER_CODE,EMPLOYER_NAME,OCCUPATION_CODE,JOINED_DATE,INCOME_RANGE_CODE,HEALTH_FACILITY,CREATE_DATE,EMPLOYMENT_STATUS) VALUES ('" + employmentinfo[0] + "','" + employmentinfo[1] + "','" + employmentinfo[2] + "','" + employmentinfo[3] + "','" + employmentinfo[4] + "','" + employmentinfo[5] + "','" + employmentinfo[6] + "','" + employmentinfo[7] + "','" + employmentinfo[8] + "','" + employmentinfo[9] + "')";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//        String sql2 = "INSERT INTO AUTOGENERATE_ESNO (EMPLOYMENT_SEQ_NO) VALUES ('" + employmentinfo[1] + "')";
//        PreparedStatement ps2 = Session.getCon_x(100).prepareStatement(sql2);
        ps.executeUpdate();
//        ps2.executeUpdate();
    }
     
     public boolean isAlreadyRegistered(String pmino) {
         boolean status = false;
         try {
             status = DBConnection.getImpl().isAlreadyRegistered(pmino);
         } catch (Exception e) {
             J.o("Offline", "Network to server is offline!", 0);
             status = DBConnection.isAlreadyRegistered(pmino);
         }
         return status;
     }

     //save patient employment information into database
     public void registerAndCreateQueue(String[] queue) throws ClassNotFoundException, SQLException
    {
        
        String sql = "INSERT INTO PMS_EPISODE ("
                + "PMI_NO,"
                + "EPISODE_DATE,"
                + "NAME,"
                + "NEW_IC_NO,"
                + "OLD_IC_NO,"
                + "ID_TYPE,"
                + "ID_NO,"
                + "RN_NO,"
                + "PATIENT_CATEGORY_CODE,"
                + "VISIT_TYPE_CODE,"
                + "EMERGENCY_TYPE_CODE,"
                + "ELIGIBILITY_CATEGORY_CODE,"
                + "ELIGIBILITY_TYPE_CODE,"
                + "DISCIPLINE_CODE,"
                + "SUBDISCIPLINE_CODE,"
                + "CONSULTATION_ROOM,"
                + "COMMON_QUEUE,"
                + "DOCTOR,"
                + "PRIORITY_GROUP_CODE,"
                + "POLICE_CASE,"
                + "COMMUNICABLE_DISEASE_CODE,"
                + "NATURAL_DISASTER_CODE,"
                + "DOC_TYPE,"
                + "GUARDIAN_IND,"
                + "REFERENCE_NO,"
                + "GROUP_GUARDIAN,"
                + "GL_EXPIRY_DATE,"
                + "EPISODE_TIME,"
                + "STATUS,"
                + "HEALTH_FACILITY_CODE)"
                + " VALUES ('" + queue[0] + "','" + queue[1] + "','" + queue[2] + "'"
                + ",'" + queue[3] + "','" + queue[4] + "','" + queue[5] + "','" + queue[6] + "'"
                + ",'" + queue[7] + "','" + queue[8] + "','" + queue[9] + "','" + queue[10] + "'"
                + ",'" + queue[11] + "','" + queue[12] + "','" + queue[13] + "','" + queue[14] + "'"
                + ",'" + queue[15] + "','" + queue[16] + "','" + queue[17] + "','" + queue[18] + "'"
                + ",'" + queue[19] + "','" + queue[20] + "','" + queue[21] + "','" + queue[22] + "'"
                + ",'" + queue[23] + "','" + queue[24] + "','" + queue[25] + "','" + queue[26] + "'"
                + ",'" + queue[27] + "','" + queue[28] + "', '" + queue[29] + "')";
        
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	
                
                DBConnection.getImpl().insertRegCreateQ(queue);
                S.oln("hehe!");
                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                S.oln("haha:"+e.getMessage());
                e.printStackTrace();
            }
//        }
//
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        Session.setCon_x();
        
        PreparedStatement ps1 = Session.getCon_x(100).prepareStatement(sql);
        S.oln("sql....."+sql);
        ps1.executeUpdate();
    }

    //get patient family information from database using PMINO
     public String[] getFamilyDetail(String family) throws ClassNotFoundException, SQLException
    {
        String[] familyinfo = new String[6];
        String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, family);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                familyinfo[i]=rs.getString(i+1);
             }
        }
        
        return familyinfo;
    }

     //get patient family information from database using New IC
     public String[] getFamilyDetailUsingNewIC(String family1) throws ClassNotFoundException, SQLException
     {
        String[] familyinfo1 = new String[6];
        
         try {
             
             String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO = ? ";
             String data[] = {family1};
             ArrayList<ArrayList<String>> rs = DBConnection.getImpl().getQuery(sql, 6, data);
             for (int j = 0; j < rs.size(); j++) {
                 for (int i = 0; i < rs.get(j).size(); i++) {
                     System.out.print(rs.get(j).get(i));
                     familyinfo1[i] = rs.get(j).get(i);
                 }
             }
             
         } catch (Exception e) {
             String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO = ? ";

             PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
             ps.setString(1, family1);
             ResultSet rs = ps.executeQuery();

             while (rs.next()) {
                 for (int i = 0; i < 6; i++) {
                     System.out.print(rs.getString(i + 1));
                     familyinfo1[i] = rs.getString(i + 1);
                 }
             }
         }
        
        return familyinfo1;
     }


      //get patient family information from database using Old IC
     public String[] getFamilyDetailUsingOldIC(String family2) throws ClassNotFoundException, SQLException
     {
        String[] familyinfo2 = new String[6];
        
        String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO = ?)";

        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, family2);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                familyinfo2[i]=rs.getString(i+1);
             }
        }

        return familyinfo2;
     }


     //get patient family information from database using ID
     public String[] getFamilyDetailUsingID(String family3,String family4) throws ClassNotFoundException, SQLException
     {
        String[] familyinfo3 = new String[6];
        String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE ID_NO = ? AND ID_TYPE=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, family3);
        ps.setString(2, family4);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                familyinfo3[i]=rs.getString(i+1);
             }
        }

        return familyinfo3;
     }

     //get patient next of kin information from database using PMINO
     public String[] getNokDetail(String nok) throws ClassNotFoundException, SQLException
    {
        String[] nokinfo = new String[19];
        String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, nok);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<19;i++)
             {
                System.out.print(rs.getString(i+1));
                nokinfo[i]=rs.getString(i+1);
             }
        }
        
        return nokinfo;
    }

    //get patient next of kin information from database using New IC
     public String[] getNokDetailUsingNewIC(String nok1) throws ClassNotFoundException, SQLException
    {
        String[] nokinfo1 = new String[19];
        
        try {
            
            String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO = ? ";
            String data[] = {nok1};
            ArrayList<ArrayList<String>> rs = DBConnection.getImpl().getQuery(sql, 19, data);
            for (int j = 0; j < rs.size(); j++) {
                for (int i = 0; i < rs.get(j).size(); i++) {
                    System.out.print(rs.get(j).get(i));
                    nokinfo1[i] = rs.get(j).get(i);
                }
            }
            
        } catch (Exception e) {
            String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO = ? ";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, nok1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < 19; i++) {
                    System.out.print(rs.getString(i + 1));
                    nokinfo1[i] = rs.getString(i + 1);
                }
            }
        }

        return nokinfo1;
    }

     //get patient next of kin information from database using Old IC
     public String[] getNokDetailUsingOldIC(String nok2) throws ClassNotFoundException, SQLException
    {
        String[] nokinfo2 = new String[19];
        String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, nok2);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<19;i++)
             {
                System.out.print(rs.getString(i+1));
                nokinfo2[i]=rs.getString(i+1);
             }
        }

        return nokinfo2;
    }


     //get patient next of kin information from database using ID
        public String[] getNokDetailUsingID(String nok3,String nok4) throws ClassNotFoundException, SQLException
    {
        String[] nokinfo3 = new String[19];
        String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE ID_NO = ? AND ID_TYPE=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, nok3);
        ps.setString(2, nok4);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<19;i++)
             {
                System.out.print(rs.getString(i+1));
                nokinfo3[i]=rs.getString(i+1);
             }
        }

        return nokinfo3;
    }

    //get patient insurance information from database using PMINO
    public String[] getInsuranceDetail(String insurance) throws ClassNotFoundException, SQLException
    {
        String[] insuranceinfo = new String[6];
        String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, insurance);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                insuranceinfo[i]=rs.getString(i+1);
             }
        }

        return insuranceinfo;
    }

     //get patient insurance information from database using New IC
      public String[] getInsuranceDetailUsingNewIC(String insurance1) throws ClassNotFoundException, SQLException
    {
        String[] insuranceinfo1 = new String[6];
        
        try {
            
            String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO = ? ";
            String data[] = {insurance1};
            ArrayList<ArrayList<String>> rs = DBConnection.getImpl().getQuery(sql, 6, data);
            for (int j = 0; j < rs.size(); j++) {
                for (int i = 0; i < rs.get(j).size(); i++) {
                    System.out.print(rs.get(j).get(i));
                    insuranceinfo1[i] = rs.get(j).get(i);
                }
            }
            
        } catch (Exception e) {
            String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO = ? ";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, insurance1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < 6; i++) {
                    System.out.print(rs.getString(i + 1));
                    insuranceinfo1[i] = rs.getString(i + 1);
                }
            }
        }
        
        return insuranceinfo1;
    }


      //get patient insurance information from database using Old IC
      public String[] getInsuranceDetailUsingOldIC(String insurance2) throws ClassNotFoundException, SQLException
    {
        String[] insuranceinfo2 = new String[6];
        String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, insurance2);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                insuranceinfo2[i]=rs.getString(i+1);
             }
        }

        return insuranceinfo2;
    }


      //get patient insurance information from database using ID
      public String[] getInsuranceDetailUsingID(String insurance3,String insurance4) throws ClassNotFoundException, SQLException
    {
        String[] insuranceinfo3 = new String[6];
        String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, insurance3);
        ps.setString(2, insurance4);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                insuranceinfo3[i]=rs.getString(i+1);
             }
        }

        return insuranceinfo3;
    }

    //get patient employment information from database using PMINO
    public String[] getEmploymentDetail(String employment) throws ClassNotFoundException, SQLException
    {
        String[] employmentinfo = new String[10];
        String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, employment);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<10;i++)
             {
                System.out.print(rs.getString(i+1));
                employmentinfo[i]=rs.getString(i+1);
             }
        }

        return employmentinfo;
    }

    //get patient employment information from database using New IC
    public String[] getEmploymentDetailUsingNewIC(String employment1) throws ClassNotFoundException, SQLException
    {
        String[] employmentinfo1 = new String[10];
        
        try {
            
            String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO = ? ";
            String data[] = {employment1};
            ArrayList<ArrayList<String>> rs = DBConnection.getImpl().getQuery(sql, 10, data);
            for (int j = 0; j < rs.size(); j++) {
                for (int i = 0; i < rs.get(j).size(); i++) {
                    System.out.print(rs.get(j).get(i));
                    employmentinfo1[i] = rs.get(j).get(i);
                }
            }
            
        } catch (Exception e) {
            String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO = ? ";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, employment1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < 10; i++) {
                    System.out.print(rs.getString(i + 1));
                    employmentinfo1[i] = rs.getString(i + 1);
                }
            }
        }

        return employmentinfo1;
    }

        //get patient employment information from database using Old IC
    public String[] getEmploymentDetailUsingOldIC(String employment2) throws ClassNotFoundException, SQLException
    {
        String[] employmentinfo2 = new String[10];
        String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, employment2);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<10;i++)
             {
                System.out.print(rs.getString(i+1));
                employmentinfo2[i]=rs.getString(i+1);
             }
        }

        return employmentinfo2;
    }

    //get patient employment information from database using ID
    public String[] getEmploymentDetailUsingID(String employment3,String employment4) throws ClassNotFoundException, SQLException
    {
        String[] employmentinfo3 = new String[10];
        String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE=?)";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, employment3);
        ps.setString(2, employment4);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<10;i++)
             {
                System.out.print(rs.getString(i+1));
                employmentinfo3[i]=rs.getString(i+1);
             }
        }

        return employmentinfo3;
    }


    //get patient biodata from database using PMINO
    public String[] getBiodata(String biodatainfo) throws ClassNotFoundException, SQLException
    {
        String[] biodatainformation = new String[35+Registration.num_sii];
        
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	

                biodatainformation = DBConnection.getImpl().getBio(2, biodatainfo, "", 35); //2 for PMI No

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                
                String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?";
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                ps.setString(1, biodatainfo);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    for (int i = 0; i < 35; i++) {
                        System.out.print(rs.getString(i + 1));
                        biodatainformation[i] = rs.getString(i + 1);
                    }
                }
                
                e.printStackTrace();
            }
//        } else {
//            //offline
//            
//            String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?";
//            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//            ps.setString(1, biodatainfo);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                for (int i = 0; i < 35; i++) {
//                    System.out.print(rs.getString(i + 1));
//                    biodatainformation[i] = rs.getString(i + 1);
//                }
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

        return biodatainformation;
    }

    //get patient biodata from database using New IC
    public String[] getBiodataUsingNewIC(String biodatainfo1) throws ClassNotFoundException, SQLException
    {
        String[] biodatainformation1 = new String[35];
        try {
            
            String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO = ? ";
            String data[] = {biodatainfo1};
            ArrayList<ArrayList<String>> rs = DBConnection.getImpl().getQuery(sql, 35, data);
            for (int j = 0; j < rs.size(); j++) {
                for (int i = 0; i < 35; i++) {
                    System.out.print(rs.get(j).get(i));
                    biodatainformation1[i] = rs.get(j).get(i);
                }
            }
            
        } catch (Exception e) {
            String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO = ? ";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, biodatainfo1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < 35; i++) {
                    System.out.print(rs.getString(i + 1));
                    biodatainformation1[i] = rs.getString(i + 1);
                }
            }
        }

        return biodatainformation1;
    }


     //get patient biodata from database using Old IC
    public String[] getBiodataUsingOldIC(String biodatainfo2) throws ClassNotFoundException, SQLException
    {
        String[] biodatainformation2 = new String[35];
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, biodatainfo2);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                biodatainformation2[i]=rs.getString(i+1);
             }
        }

        return biodatainformation2;
    }


    //get patient biodata from database using ID
    public String[] getBiodataUsingID(String biodatainfo3,String biodatainfo4) throws ClassNotFoundException, SQLException
    {
        String[] biodatainformation3 = new String[35];
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, biodatainfo3);
        ps.setString(2, biodatainfo4);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                biodatainformation3[i]=rs.getString(i+1);
             }
        }

        return biodatainformation3;
    }


    //get existing patient biodata from database using PMINO
    public String[] getRegisterBiodata(String existbiodata) throws ClassNotFoundException, SQLException
    {
        String[] existBiodataInfo = new String[8];
        String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, existbiodata);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<8;i++)
             {
                System.out.print(rs.getString(i+1));
                existBiodataInfo[i]=rs.getString(i+1);
             }
        }

        return existBiodataInfo;
    }

    public boolean ayatSMP(int flag, String[] existBiodataInfo1, int num1, String carian, String dropdownIDtype) {
        boolean stat = true;
        
        /**
         * 00 - Xsmp Xpms
         * 01 - Xsmp /pms
         * 10 - /smp Xpms
         * 11 - /smp /pms
         */
        String kod = existBiodataInfo1[num1].substring(0, 1);
        
        for (int i = num1; i < existBiodataInfo1.length; i++) {
            System.out.println("kod:"+existBiodataInfo1[i]);
        }
        
        if (kod.equals("0")) {
            int ask = JOptionPane.showConfirmDialog(null, "This patient IS NOT RECOGNIZED as UTeM's student "
                    + "or staff.\nDo you want to continue?", "Registration", JOptionPane.YES_NO_OPTION);
            if (ask == JOptionPane.NO_OPTION) {
                stat = false;
            } else {
                stat = true;
            }
        } else {
            String local_foreign = existBiodataInfo1[num1 + 3]; // L local, F foreign
            Registration.numCheckDigit = existBiodataInfo1[num1 + 1];
            String stud_staf = existBiodataInfo1[num1 + 4]; // 1 student, 0 staff
            String active = existBiodataInfo1[num1 + 6];
            System.out.println("active:"+active);
            if (!active.equals("AKTIF")) {
                int ask = JOptionPane.showConfirmDialog(null, "This patient IS NOT ACTIVE as UTeM's student "
                        + "or staff.\nDo you want to continue?", "Registration", JOptionPane.YES_NO_OPTION);
                if (ask == JOptionPane.NO_OPTION) {
                    stat = false;
                } else {
                    stat = true;
                }
            }
        }
        
        if (stat == false) {
            Registration.numCheckDigit = carian;
        }
        
        System.out.println("stat:"+stat);
        System.out.println("Registration.numCheckDigit:"+Registration.numCheckDigit);
        System.out.println("carian:"+carian);
        
        return stat;
    }

    //get existing patient biodata from database using new ic no
    public String[] getRegisterBiodataUsingNewIC(String existbiodata1) throws ClassNotFoundException, SQLException
    {
        int num_ic = 8;
        String[] existBiodataInfo1 = new String[num_ic+Registration.num_sii];
        
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	

                existBiodataInfo1 = DBConnection.getImpl().getBio(1, existbiodata1, "", num_ic); //1 for New IC
                
                Registration.isRecognizedPatient = ayatSMP(1, existBiodataInfo1, num_ic, existbiodata1, "");

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                
                String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?";
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                ps.setString(1, existbiodata1);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    for (int i = 0; i < 8; i++) {
                        System.out.print(rs.getString(i + 1));
                        existBiodataInfo1[i] = rs.getString(i + 1);
                    }
                }
                
                e.printStackTrace();
            }
//        } else {
//            //offline
//            
//            String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?";
//            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//            ps.setString(1, existbiodata1);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                for (int i = 0; i < 8; i++) {
//                    System.out.print(rs.getString(i + 1));
//                    existBiodataInfo1[i] = rs.getString(i + 1);
//                }
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

        return existBiodataInfo1;
    }


    //get existing patient biodata from database using old ic no
    public String[] getRegisterBiodataUsingOldIC(String existbiodata2) throws ClassNotFoundException, SQLException
    {
        String[] existBiodataInfo2 = new String[8+Registration.num_sii];
        
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	

                existBiodataInfo2 = DBConnection.getImpl().getBio(4, existbiodata2, "", 8); //1 for New IC
                
                Registration.isRecognizedPatient = ayatSMP(4, existBiodataInfo2, 8, existbiodata2, "");

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline

                String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?";
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                ps.setString(1, existbiodata2);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    for (int i = 0; i < 8; i++) {
                        System.out.print(rs.getString(i + 1));
                        existBiodataInfo2[i] = rs.getString(i + 1);
                    }
                }

                e.printStackTrace();
            }
//        } else {
//            //offline
//
//            String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?";
//            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//            ps.setString(1, existbiodata2);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                for (int i = 0; i < 8; i++) {
//                    System.out.print(rs.getString(i + 1));
//                    existBiodataInfo2[i] = rs.getString(i + 1);
//                }
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        
        return existBiodataInfo2;
    }


     //get existing patient biodata from database using id no and id type
    public String[] getRegisterBiodataUsingID(String existbiodata3,String existbiodata4) throws ClassNotFoundException, SQLException
    {
        int num_ic = 8;
        String[] existBiodataInfo3 = new String[num_ic+Registration.num_sii];
        
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	

                existBiodataInfo3 = DBConnection.getImpl().getBio(3, existbiodata3, existbiodata4, 8); //3 for id type
                
                Registration.isRecognizedPatient = ayatSMP(3, existBiodataInfo3, num_ic, existbiodata3, existbiodata4);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline

                String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE =?";
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                ps.setString(1, existbiodata3);
                ps.setString(2, existbiodata4);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    for (int i = 0; i < 8; i++) {
                        System.out.print(rs.getString(i + 1));
                        existBiodataInfo3[i] = rs.getString(i + 1);
                    }
                }

                e.printStackTrace();
            }
//        } else {
//            //offline
//
//            String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE =?";
//            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//            ps.setString(1, existbiodata3);
//            ps.setString(2, existbiodata4);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                for (int i = 0; i < 8; i++) {
//                    System.out.print(rs.getString(i + 1));
//                    existBiodataInfo3[i] = rs.getString(i + 1);
//                }
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        
        return existBiodataInfo3;
    }


     //get patient biodata from database using MyKad and show on registration screen
    public String[] getRegisterBiodataUsingMyKad(String existbiodata4) throws ClassNotFoundException, SQLException
    {
        String[] existBiodataInfo4 = new String[8];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, existbiodata4);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<8;i++)
             {
                System.out.print(rs.getString(i+1));
                existBiodataInfo4[i]=rs.getString(i+1);
             }
        }
        
        conn.close();
        return existBiodataInfo4;
    }


     //get patient biodata from database using MyKad and show on patient master index screen
    public String[] getBiodataInfoUsingMyKad(String biodataInfo) throws ClassNotFoundException, SQLException
    {
        String[] biodata = new String[35];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, biodataInfo);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                biodata[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return biodata;
    }

    //get patient employment information from database using MyKad
    public String[] getEmploymentInfoUsingMyKad(String employment5) throws ClassNotFoundException, SQLException
    {
        String[] employmentInfo5 = new String[10];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_EMPLOYMENT WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, employment5);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<10;i++)
             {
                System.out.print(rs.getString(i+1));
                employmentInfo5[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return employmentInfo5;
    }

    //get patient employment information from database using MyKad
    public String[] getNOKInfoUsingMyKad(String nok5) throws ClassNotFoundException, SQLException
    {
        String[] nokInfo5 = new String[19];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_NEXTOFKIN WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nok5);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<19;i++)
             {
                System.out.print(rs.getString(i+1));
                nokInfo5[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return nokInfo5;
    }


    //get patient family information from database using MyKad
    public String[] getFamilyInfoUsingMyKad(String family5) throws ClassNotFoundException, SQLException
    {
        String[] familyInfo5 = new String[6];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_FAMILY WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, family5);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                familyInfo5[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return familyInfo5;
    }


     //get patient insurance information from database using MyKad
    public String[] getInsuranceInfoUsingMyKad(String insurance5) throws ClassNotFoundException, SQLException
    {
        String[] insuranceInfo5 = new String[6];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_MEDICAL_INSURANCE WHERE PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, insurance5);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<6;i++)
             {
                System.out.print(rs.getString(i+1));
                insuranceInfo5[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return insuranceInfo5;
    }
    
    public String[] getAutoGenLocal(int stat) {
        String[] g = new String[1];
        try {
            //Connection conn = DbConnection.doConnection();
            String sql = "SELECT PMI_NO FROM AUTOGENERATE_PMI";
            switch(stat) {
                case 1: {
                    sql = "SELECT PMI_NO FROM AUTOGENERATE_PMI";
                } break;
                case 2: {
                    sql = "SELECT FAMILY_SEQ_NO FROM AUTOGENERATE_FSNO";
                } break;
                case 3: {
                    sql = "SELECT NEXTOFKIN_SEQ_NO FROM AUTOGENERATE_NOKSNO";
                } break;
                case 4: {
                    sql = "SELECT EMPLOYMENT_SEQ_NO FROM AUTOGENERATE_ESNO";
                } break;
                case 5: {
                    sql = "SELECT RECEIPT_NO FROM AUTOGENERATE_RECNO";
                } break;
                default: {
                    sql = "SELECT PMI_NO FROM AUTOGENERATE_PMI";
                }
            }
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            String pmi = null;
            while (rs.next()) {
                pmi = rs.getString(1);
                int num = 0;
                switch(stat) {
                    case 1: {
                        num = Integer.parseInt(pmi.substring(3, pmi.length()));
                    } break;
                    case 2: {
                        num = Integer.parseInt(pmi.substring(2, pmi.length()));
                    } break;
                    case 3: {
                        num = Integer.parseInt(pmi.substring(4, pmi.length()));
                    } break;
                    case 4: {
                        num = Integer.parseInt(pmi.substring(2, pmi.length()));
                    } break;
                    case 5: {
                        num = Integer.parseInt(pmi.substring(3, pmi.length()));
                    } break;
                    default: {
                        num = Integer.parseInt(pmi.substring(3, pmi.length()));
                    }
                    break;
                }
                num += 1;
                String formatted = String.format("%05d", num);
                g[0] = formatted;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

    //get autogenerate pminumber from database
    public String[] getAutogeneratePMI() throws ClassNotFoundException, SQLException
    {
        String[] AutogeneratePMI = new String[1];
        
        
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                AutogeneratePMI = DBConnection.getImpl().getAutoGen(1); //1 for pmi

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                AutogeneratePMI = getAutoGenLocal(1);
            }
//        } else {
//            //offline
//
//            AutogeneratePMI = getAutoGenLocal(1);
//        }
        

        //conn.close();
        return AutogeneratePMI;
    }


    //get autogenerate family sequence number from database
    public String[] getAutogenerateFSNo() throws ClassNotFoundException, SQLException
    {
        String[] AutogenerateFS = new String[1];
        
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                AutogenerateFS = DBConnection.getImpl().getAutoGen(2); //2 for FSqNo

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                AutogenerateFS = getAutoGenLocal(2);
            }
//        } else {
//            //offline
//
//            AutogenerateFS = getAutoGenLocal(2);
//        }
        
        return AutogenerateFS;
    }


    //get autogenerate family sequence number from database
    public String[] getAutogenerateNOKNo() throws ClassNotFoundException, SQLException
    {
        String[] AutogenerateNOK = new String[1];
        
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                AutogenerateNOK = DBConnection.getImpl().getAutoGen(3);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                AutogenerateNOK = getAutoGenLocal(3);
            }
//        } else {
//            //offline
//
//            AutogenerateNOK = getAutoGenLocal(3);
//        }
        
        return AutogenerateNOK;
    }


    //get autogenerate employment sequence number from database
    public String[] getAutogenerateESNo() throws ClassNotFoundException, SQLException
    {
        String[] AutogenerateES = new String[1];
        
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                AutogenerateES = DBConnection.getImpl().getAutoGen(4);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                AutogenerateES = getAutoGenLocal(4);
            }
//        } else {
//            //offline
//
//            AutogenerateES = getAutoGenLocal(4);
//        }
        
        return AutogenerateES;
    }


     //get autogenerate receipt no from database
    public String[] getAutogenerateRecNo() throws ClassNotFoundException, SQLException
    {
        String[] AutogenerateRec = new String[1];
        
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                AutogenerateRec = DBConnection.getImpl().getAutoGen(5);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                AutogenerateRec = getAutoGenLocal(5);
            }
//        } else {
//            //offline
//
//            AutogenerateRec = getAutoGenLocal(5);
//        }
        
        return AutogenerateRec;
    }


    //save receipt number into database
        public void addReceiptNo(String[] receipt) throws ClassNotFoundException, SQLException
    {
        //Connection conn = DbConnection.doConnection();
        String sql = "INSERT INTO AUTOGENERATE_RECNO (RECEIPT_NO) VALUES ('" + receipt[0] + "')";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.executeUpdate();
        //conn.close();
    }




        //get patient biodata from database when actor click table row from appointment list
    public String[] getAppointmentBiodata(String appointmentbiodatainfo, String episodeTime) throws ClassNotFoundException, SQLException
    {
        String[] appointmentbiodatainformation = new String[35];
        
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                
//                boolean isConsult = impl.isConsult(appointmentbiodatainfo);
//                
//                if (isConsult) {
//                    appointmentbiodatainformation = impl.getBiodata(appointmentbiodatainfo);
//                }
                appointmentbiodatainformation = DBConnection.getImpl().simplifyCheckBiodata(appointmentbiodatainfo, episodeTime, Session.getUser_name());
                
            } catch(Exception ex) {
                String sql1 = "SELECT * "
                        + "FROM PMS_EPISODE "
                        + "WHERE PMI_NO = ? "
                        + "AND STATUS NOT LIKE 'Consult' "
                        + "AND EPISODE_DATE = ? "
                        + "AND (DOCTOR = ? OR DOCTOR = '-') ";
                try {
                    if (!episodeTime.equals("")) {
                        sql1 += "AND EPISODE_TIME = ? ";
                    }
                } catch (Exception e) {
                }
                sql1 += "ORDER BY EPISODE_TIME ASC";
                PreparedStatement ps1 = Session.getCon_x(100).prepareStatement(sql1);
                ps1.setString(1, appointmentbiodatainfo);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                ps1.setString(2, sdf.format(date));
                ps1.setString(3, Session.getUser_name());
                
                try {
                    if (!episodeTime.equals("")) {
                        ps1.setString(4, episodeTime);
                    }
                } catch (Exception e) {
                }
                
                ResultSet rs1 = ps1.executeQuery();

                if (rs1.next()) {
                    String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?";
                    PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                    ps.setString(1, appointmentbiodatainfo);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        for (int i = 0; i < 35; i++) {
                            System.out.print(rs.getString(i + 1));
                            appointmentbiodatainformation[i] = rs.getString(i + 1);
                        }
                    }
                }
                //ex.printStackTrace();
            }
//        } else {
//            
//            String sql1 = "SELECT * "
//                    + "FROM PMS_EPISODE "
//                    + "WHERE PMI_NO = ? "
//                    + "AND STATUS NOT LIKE 'Consult' "
//                    + "ORDER BY EPISODE_TIME ASC";
//            PreparedStatement ps1 = Session.getCon_x(100).prepareStatement(sql1);
//            ps1.setString(1, appointmentbiodatainfo);
//            ResultSet rs1 = ps1.executeQuery();
//            
//            if (rs1.next()) {
//                String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?";
//                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//                ps.setString(1, appointmentbiodatainfo);
//                ResultSet rs = ps.executeQuery();
//
//                while (rs.next()) {
//                    for (int i = 0; i < 35; i++) {
//                        System.out.print(rs.getString(i + 1));
//                        appointmentbiodatainformation[i] = rs.getString(i + 1);
//                    }
//                }
//            }
//        }

        return appointmentbiodatainformation;
    }

        //get patient biodata from database when actor click table row from referral list
    public String[] getReferralBiodata(String referralInfo) throws ClassNotFoundException, SQLException
    {
        String[] referralInformation = new String[35];
        //Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PATIENT_NAME=?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, referralInfo);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                referralInformation[i]=rs.getString(i+1);
             }
        }

        //conn.close();
        return referralInformation;
    }


    /*
    //get patient biodata from database when actor click table row from rEFERRAL list
    public String[] getReferralBiodataUsingNewIC(String referralinfo) throws ClassNotFoundException, SQLException
    {
        String[] referralinformation = new String[35];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, referralinfo);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                referralinformation[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return referralinformation;
    }


     //get patient biodata from database when actor click table row from rEFERRAL list
    public String[] getReferralBiodataUsingOldIC(String referralinfo1) throws ClassNotFoundException, SQLException
    {
        String[] referralinformation1 = new String[35];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, referralinfo1);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                referralinformation1[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return referralinformation1;
    }


    //get patient biodata from database when actor click table row from rEFERRAL list
    public String[] getReferralBiodataUsingID(String referralinfo2) throws ClassNotFoundException, SQLException
    {
        String[] referralinformation2 = new String[35];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE ID_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, referralinfo2);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<35;i++)
             {
                System.out.print(rs.getString(i+1));
                referralinformation2[i]=rs.getString(i+1);
             }
        }

        conn.close();
        return referralinformation2;
    }
*/




        //update patient insurance information and save into database
        public void updatePatientInsuranceInfo(String[] insuranceInfo) throws ClassNotFoundException, SQLException
        {
            //Connection conn = DbConnection.doConnection();
            String sql = "UPDATE PMS_MEDICAL_INSURANCE SET PMI_NO = '" + insuranceInfo[0] + "',INSURANCE_SEQ_NO = '" + insuranceInfo[1] + "',INSURANCE_COMPANY_CODE = '" + insuranceInfo[2] + "',POLICY_NO = '" + insuranceInfo[3] + "',MATURITY_DATE = '" + insuranceInfo[4] + "',HEALTH_FACILITY = '" + insuranceInfo[5] + "',POLICY_STATUS = '" + insuranceInfo[6] + "' WHERE PMI_NO = '" + insuranceInfo[0] + "' AND INSURANCE_SEQ_NO = '" + insuranceInfo[1] + "'";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.executeUpdate();
            //conn.close();
        }


        //update patient family information and save into database
        public void updatePatientFamilyInfo(String[] familyInfo) throws ClassNotFoundException, SQLException
        {
            //Connection conn = DbConnection.doConnection();
            String sql = "UPDATE PMS_FAMILY SET PMI_NO = '" + familyInfo[0] + "',FAMILY_SEQ_NO = '" + familyInfo[1] + "',FAMILY_RELATIONSHIP_CODE = '" + familyInfo[2] + "',PMI_NO_FAMILY = '" + familyInfo[3] + "',FAMILY_MEMBER_NAME = '" + familyInfo[4] + "',OCCUPATION_CODE = '" + familyInfo[5] + "' WHERE PMI_NO = '" + familyInfo[0] + "' AND FAMILY_SEQ_NO = '" + familyInfo[1] + "'";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.executeUpdate();
            //conn.close();
        }

        //update patient next of kin information and save into database
        public void updatePatientNextOfKinInfo(String[] nokInfo) throws ClassNotFoundException, SQLException
        {
            //Connection conn = DbConnection.doConnection();
            String sql = "UPDATE PMS_NEXTOFKIN SET PMI_NO = '" + nokInfo[0] + "',NEXTOFKIN_SEQ_NO = '" + nokInfo[1] + "',NEXTOFKIN_RELATIONSHIP_CODE = '" + nokInfo[2] + "',NEXTOFKIN_NAME = '" + nokInfo[3] + "',NEW_IC_NO = '" + nokInfo[4] + "',OLD_IC_NO = '" + nokInfo[5] + "',ID_TYPE = '" + nokInfo[6] + "',ID_NO = '" + nokInfo[7] + "',BIRTH_DATE = '" + nokInfo[8] + "',OCCUPATION_CODE = '" + nokInfo[9] + "',ADDRESS = '" + nokInfo[10] + "',DISTRICT_CODE = '" + nokInfo[11] + "',TOWN_CODE = '" + nokInfo[12] + "',POSTCODE = '" + nokInfo[13] + "',STATE_CODE = '" + nokInfo[14] + "',COUNTRY_CODE = '" + nokInfo[15] + "',MOBILE_PHONE = '" + nokInfo[16] + "',HOME_PHONE = '" + nokInfo[17] + "',E_MAIL = '" + nokInfo[18] + "' WHERE PMI_NO = '" + nokInfo[0] + "' AND NEXTOFKIN_SEQ_NO = '" + nokInfo[1] + "'";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.executeUpdate();
            //conn.close();
        }

        //update patient employment information and save into database
        public void updatePatientEmploymentInfo(String[] employmentInfo) throws ClassNotFoundException, SQLException
        {
            Connection conn = DbConnection.doConnection();
            String sql = "UPDATE PMS_EMPLOYMENT SET PMI_NO = '" + employmentInfo[0] + "',EMPLOYMENT_SEQ_NO = '" + employmentInfo[1] + "',EMPLOYER_CODE = '" + employmentInfo[2] + "',EMPLOYER_NAME = '" + employmentInfo[3] + "',OCCUPATION_CODE = '" + employmentInfo[4] + "',JOINED_DATE = '" + employmentInfo[5] + "',INCOME_RANGE_CODE = '" + employmentInfo[6] + "',HEALTH_FACILITY = '" + employmentInfo[7] + "',CREATE_DATE = '" + employmentInfo[8] + "',EMPLOYMENT_STATUS = '" + employmentInfo[9] + "' WHERE PMI_NO = '" + employmentInfo[0] + "' AND EMPLOYMENT_SEQ_NO = '" + employmentInfo[1] + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.close();
        }

         //update patient biodata and save into database
        public boolean updatePatientBiodata(String[] Biodata) throws ClassNotFoundException, SQLException
        {
            boolean stat = false;
                    
//            LongRunProcess.check_network2();
//            if (Session.getPrev_stat()) {
                //Read BLOB from EHR_Central
                System.err.println();
                System.err.println("Server Online");
                System.out.println("Start invoke remote server");
                try {
                    // fire to server port 1099
//                    ArrayList<String> listOnline = Func.readXML("online");
//                    Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                    // search for myMessage service
//                    Message impl = (Message) myRegistry.lookup("myMessage");
                    // call server's method	
                    
                    stat = DBConnection.getImpl().isUpdatePatientBiodata(Biodata);

                    System.out.println(".....Message Sent....");
                } catch (Exception e) {
                    //offline
                    e.printStackTrace();
                }
//            }
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
            
            stat = DBConnection.isUpdatePatientBiodata(Biodata);
            
            return stat;
        }


    //get town information from database
    /*public String[] getTownInfo() throws SQLException, ClassNotFoundException{

        String[] towninfo = new String[16];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM COMBOBOX_PMIFORM";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

       for(int i=0;rs.next();i++){

                System.out.print(rs.getString(1));
                towninfo[i]=rs.getString(1);
        }

            //while(rs.next())
            //{
               // towninfo[i].addItem(rs.getString(1));
            //}

        conn.close();
        return towninfo;
    }

    //get state information from database
    public String[] getStateInfo() throws SQLException, ClassNotFoundException{

        String[] stateinfo = new String[16];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM COMBOBOX_PMIFORM";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

       for(int i=0;rs.next();i++){

                System.out.print(rs.getString(2));
                stateinfo[i]=rs.getString(2);
        }

            //while(rs.next())
            //{
               // towninfo[i].addItem(rs.getString(1));
            //}

        conn.close();
        return stateinfo;
    }
*/
}
