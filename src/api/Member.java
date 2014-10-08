/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import Database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author NoNamEr
 */
public class Member {
    private String memberID;

    public Member()
    {}

    public String memberlogin1(String user,String pass) throws SQLException, ClassNotFoundException
    {
        // member login function

        String username, password;
        String id="false";
        Connection conn = DbConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT PMI_No, Username, Password FROM PAS_PatientInformation WHERE Username ='"+user+"'");
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            username = rs.getString("Username");
            password = rs.getString("Password");
            id = rs.getString("PMI_No");
            if(username.equals(user) && password.equals(pass))
            {
                return id;
            }
             else
                return "false";
        }
        return id;
    }

    public void addMember(String[] member) throws ClassNotFoundException, SQLException
    {
        // register member function

        String empty = "-";

        Connection conn = DbConnection.doConnection();
        String sql =
        "INSERT INTO PAS_PATIENTINFORMATION(PMI_NO,PATIENT_NAME,NEW_IC_NO," +
                "BIRTH_DATE,GENDER,MOBILE_PHONE,ADDRESS,USERNAME,PASSWORD) VALUES ('"+member[0]+"','"+member[1]+"','"+member[2]+"','"+member[3]+"','"+member[4]+"','"+member[5]+"','"+member[6]+"','"+member[7]+"','"+member[8]+"')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();

        String sql1 =
                "INSERT INTO PMS_PATIENT_BIODATA (PMI_NO,PMI_NO_TEMP,PATIENT_NAME,TITLE_CODE,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE,BIRTH_DATE,SEX_CODE,MARITAL_STATUS_CODE,RACE_CODE,NATIONALITY,RELIGION_CODE,BLOOD_TYPE,BLOOD_RHESUS_CODE,ALLERGY_IND,CHRONIC_DISEASE_IND,ORGAN_DONOR_IND,HOME_ADDRESS,HOME_DISTRICT_CODE,HOME_TOWN_CODE,HOME_POSTCODE,HOME_STATE_CODE,HOME_COUNTRY_CODE,HOME_PHONE,POSTAL_ADDRESS,POSTAL_DISTRICT_CODE,POSTAL_TOWN_CODE,POSTAL_POSTCODE,POSTAL_STATE_CODE,POSTAL_COUNTRY_CODE,MOBILE_PHONE) "
                + "VALUES ('" + member[0] + "','" + empty + "','" + member[1] + "','" + empty + "','" + member[2] + "','" + empty + "','" + empty + "','" + empty  + "','" + empty + "','" + empty + "','" + member[3] + "','" + member[4] + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + member[6] + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + member[5] + "')";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ps1.execute();

        String sql2 =
                "INSERT INTO PMS_LOCAL_PATIENT_BIODATA (PMI_NO,PMI_NO_TEMP,PATIENT_NAME,TITLE_CODE,NEW_IC_NO,OLD_IC_NO,ID_TYPE,ID_NO,ELIGIBILITY_CATEGORY_CODE,ELIGIBILITY_TYPE_CODE,BIRTH_DATE,SEX_CODE,MARITAL_STATUS_CODE,RACE_CODE,NATIONALITY,RELIGION_CODE,BLOOD_TYPE,BLOOD_RHESUS_CODE,ALLERGY_IND,CHRONIC_DISEASE_IND,ORGAN_DONOR_IND,HOME_ADDRESS,HOME_DISTRICT_CODE,HOME_TOWN_CODE,HOME_POSTCODE,HOME_STATE_CODE,HOME_COUNTRY_CODE,HOME_PHONE,POSTAL_ADDRESS,POSTAL_DISTRICT_CODE,POSTAL_TOWN_CODE,POSTAL_POSTCODE,POSTAL_STATE_CODE,POSTAL_COUNTRY_CODE,MOBILE_PHONE) "
                + "VALUES ('" + member[0] + "','" + empty + "','" + member[1] + "','" + empty + "','" + member[2] + "','" + empty + "','" + empty + "','" + empty  + "','" + empty + "','" + empty + "','" + member[3] + "','" + member[4] + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + member[6] + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + empty + "','" + member[5] + "')";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.execute();

        String sql3 =
                "INSERT INTO AUTOGENERATE_ESNO (EMPLOYMENT_SEQ_NO) VALUES ('" + member[0] + "')";
        PreparedStatement ps3 = conn.prepareStatement(sql3);
        ps3.execute();

        conn.close();
    }

    public String[] getMemberDetail(String memberID) throws ClassNotFoundException, SQLException
    {
        // retrieve data from database

        String[] member = new String[10];
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PAS_PATIENTINFORMATION WHERE PMI_NO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,memberID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            for(int i=0;i<9;i++) {
                member[i]=rs.getString(i+1);
            }
        }
        
        conn.close();
        return member;
    }

   public void updateMember(String[] member) throws ClassNotFoundException, SQLException
    {
       // update member information

        Connection conn = DbConnection.doConnection();
        String sql = "UPDATE PAS_PATIENTINFORMATION SET PATIENT_NAME='"+member[1]+"',NEW_IC_NO='"+member[2]+"',BIRTH_DATE='"+member[3]+"',GENDER='"+member[4]+"',MOBILE_PHONE='"+member[5]+"',ADDRESS='"+member[6]+"',USERNAME='"+member[7]+"',PASSWORD='"+member[8]+"' WHERE PMI_NO='"+member[0]+"'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }

    public void newAppointment(String[] data1) throws ClassNotFoundException, SQLException
    {
        // make new appointment

        String data="Appointment";
        Connection conn = DbConnection.doConnection();
        String sql =

        "INSERT INTO PMS_APPOINTMENT_LIST(APPOINTMENT_ID,APPOINTMENT_DATE,APPOINTMENT_TIME,PMI_NO,PATIENT_NAME,DOCTOR_NAME,LOCATION_NAME,DISCIPLINE,SUBDISCIPLINE,APPOINTMENT_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, data1[0]);
        ps.setString(2, data1[1]);
        ps.setString(3, data1[2]);
        ps.setString(4, data1[3]);
        ps.setString(5, data1[4]);
        ps.setString(6, data1[5]);
        ps.setString(7, data1[6]);
        ps.setString(8, data1[7]);
        ps.setString(9, data1[8]);
        ps.setString(10, data);

        ps.executeUpdate();
        conn.close();
    }

//    public Vector getAppointment(String Appointment_id) throws ClassNotFoundException, SQLException
//    {
//        // retrieve appointment that made before
//
//        Vector<Vector<String>> dateVector = new Vector<Vector<String>>();
//        Connection conn = DbConnection.doConnection();
//        PreparedStatement ps = conn.prepareStatement("SELECT * FROM PMS_APPOINTMENT WHERE DOCTOR_USER_ID=? and Date=? ");
//        ResultSet rs = ps.executeQuery();
//         while(rs.next())
//         {
//            Vector<String> date = new Vector<String>();
//            date.add(rs.getString(1));
//            String ID = rs.getString(3);
//            ps = conn.prepareStatement("SELECT * FROM PMS_APPOINTMENT WHERE APPOINTMENT_ID="+ID);
//            ResultSet rs1 = ps.executeQuery();
//            rs1.next();
//            date.add(rs1.getString(1));
//            date.add(rs.getString(5));
//            date.add(rs.getString(4));
//            date.add(rs.getString(6));
//
//            dateVector.add(date);
//         }
//        conn.close();
//        return dateVector;
//    }

    public Vector<Vector<String>> viewAppointment(String text) throws ClassNotFoundException, SQLException {

        // view appointment

        Vector<Vector<String>> viewVector = new Vector<Vector<String>>();
        Connection conn1 = DbConnection.doConnection();

            String sql = "";
            sql="SELECT * FROM PMS_APPOINTMENT_LIST where PMI_NO = ?";

            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();

                    while(rs.next()) {
                        Vector<String> data = new Vector<String>();
                        data.add(rs.getString(1));  //APPOINTMENTt_ID
                        data.add(rs.getString(2));  //APPOINTMENT_DATE
                        data.add(rs.getString(3));  //APPOINTMENT_TIME
                        data.add(rs.getString(5));  //PATIENT_NAME
                        data.add(rs.getString(6));  //DOCTOR_NAME
                        data.add(rs.getString(7));  //LOCATION_NAME
                        viewVector.add(data);   //APPOINTMENT_TYPE

            }

        return viewVector;
    }

    public String[] viewAppoint(String selectedID) throws ClassNotFoundException, SQLException {

        // view appointment

            Connection conn1 = DbConnection.doConnection();
            String[] appointDetail = new String[9];

            String sql = "";
            sql="SELECT * FROM PMS_APPOINTMENT_LIST where APPOINTMENT_ID = ?";

            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setString(1, selectedID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                for(int i=0;i<9;i++) {
                    appointDetail[i]=rs.getString(i+1);
                }
            }
            conn1.close();
            return appointDetail;
    }

    public String[] getDoctorDetail(String doctorID) throws ClassNotFoundException, SQLException {

        // retrive doctor information

        String[] member = new String[8];
        Connection conn1 = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_DOCTORINFORMATION WHERE DOCTOR_ID=?";
        PreparedStatement ps = conn1.prepareStatement(sql);
        ps.setString(1,doctorID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            for(int i=0;i<8;i++) {
                member[i]=rs.getString(i+1);
            }
        }

        conn1.close();
        return member;
    }

    public void deleteAppointment(String ID) throws ClassNotFoundException, SQLException {
        
        // delete the appointment

        Connection conn1 = DbConnection.doConnection();
        String sql = "Delete FROM PMS_APPOINTMENT_LIST WHERE APPOINTMENT_ID=?";
        PreparedStatement ps = conn1.prepareStatement(sql);
        ps.setString(1,ID);

        ps.executeUpdate();
        conn1.close();
    }
     
    public String[] getAutoPMINo() throws ClassNotFoundException, SQLException {

        // generate PMI number automatively

        String[] AutogenerateID = new String[1];
        Connection conn1 = DbConnection.doConnection();
        String sql = "SELECT PMI_NO FROM AUTOGENERATE_PMI";
        PreparedStatement ps = conn1.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        String id = null;


        while(rs.next()){
            //for(int i=0;i<1;i++)
             {
                 id = rs.getString(1);
             }

             int num = Integer.parseInt(id.substring(3,id.length()));
             num += 1;

             String formatted = String.format("%05d",num);
             AutogenerateID[0] = formatted;
        }

        conn1.close();
        return AutogenerateID;

    }
}
