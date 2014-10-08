/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import Database.DbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author NoNamEr
 */
public class Staff {

    private static String dbUrl = "jdbc:hsqldb:file:lib/userdata/friza;shutdown=true";
   // private static String dbUrl = "jdbc:hsqldb:file:lib/userdata/friza;shutdown=true";
    
    private static Connection conn = null;
    private static String driverName = "org.hsqldb.jdbcDriver";
    public static Connection getConnectionInstance;

    public static Connection getConnInstance()
    {
         if (conn == null)
         {
             //call createConnection static method
             conn = createConnection();
         }
         return conn;
     }


    private static Connection createConnection() {
        try
         {
             //Class.forName(driverName);
             //Connection conn = DriverManager.getConnection(dbURL+dbLocation);
             Class.forName(driverName).newInstance();
            // conn = DriverManager.getConnection(dbUrl+";ifexists=true","sa","");
              conn = DriverManager.getConnection(dbUrl,"sa","");
             System.out.println("Connection successful!....STaff");
         }
         catch (Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return conn;
    }

    public void addDoctor(String[] doctorDetail) throws ClassNotFoundException, SQLException {
       Connection conn1 = DbConnection.doConnection();
        String sql = "INSERT INTO PMS_DOCTORINFORMATION (DOCTOR_ID, DOCTOR_NAME, DOCTOR_IC, DOCTOR_MOBILE_PHONE, DOCTOR_EMAIL," +
            "DOCTOR_ADDRESS,DISCIPLINE, REMARKS) VALUES (?,?,?,?,?,?,?,?)";

        String sql2 = "INSERT INTO PMS_DUTY_ROSTER (DOCTOR_ID, DISCIPLINE) VALUES (?,?)";
        
        PreparedStatement ps = conn1.prepareStatement(sql);
        PreparedStatement ps2 = conn1.prepareStatement(sql2);
        
        ps.setString(1, doctorDetail[0]);
        ps.setString(2, doctorDetail[1]);
        ps.setString(3, doctorDetail[2]);
        ps.setString(4, doctorDetail[3]);
        ps.setString(5, doctorDetail[4]);
        ps.setString(6, doctorDetail[5]);
        ps.setString(7, doctorDetail[6]);
        ps.setString(8, doctorDetail[7]);

        ps2.setString(1, doctorDetail[0]);
        ps2.setString(2, doctorDetail[6]);

        ps.executeUpdate();
        ps2.executeUpdate();
        conn1.close();
    }

    public String[] getAutogenerateID() throws ClassNotFoundException, SQLException {

        String[] AutogenerateID = new String[1];
        Connection conn1 = DbConnection.doConnection();
        String sql = "SELECT DOCTOR_ID FROM PMS_DOCTORINFORMATION";
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

             String formatted = String.format("%03d",num);
             AutogenerateID[0] = formatted;
        }

        conn1.close();
        return AutogenerateID;

    }

    public String[] getAutoAppointID() throws ClassNotFoundException, SQLException {

        String[] AutogenerateID = new String[1];
        Connection conn1 = DbConnection.doConnection();
        String sql = "SELECT APPOINTMENT_ID FROM PMS_APPOINTMENT_LIST";
        PreparedStatement ps = conn1.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        String id = null;


        while(rs.next()){
            //for(int i=0;i<1;i++)
             {
                 id = rs.getString(1);
             }

             int num = Integer.parseInt(id.substring(1,id.length()));
             num += 1;

             String formatted = String.format("%05d",num);
             AutogenerateID[0] = formatted;
        }

        conn1.close();
        return AutogenerateID;

    }

    public Vector viewAppointment(String discipline, String date) throws Exception
    {
        Vector<Vector<String>> viewVector = new Vector<Vector<String>>();
        Connection conn1 = DbConnection.doConnection();

            String sql = "";
            sql="SELECT * FROM PMS_APPOINTMENT_LIST where Discipline = ? and Appointment_Date=?";

            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setString(1, discipline);
            ps.setString(2, date);
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



    public Vector getDocInfo(String discipline) throws Exception
    {
        Vector<Vector<String>> doctorVector = new Vector<Vector<String>>();
        Connection conn1 = DbConnection.doConnection();

            String sql = "";
            sql="SELECT * FROM PMS_DUTY_ROSTER where Discipline = ?";

            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setString(1, discipline);
            ResultSet rs = ps.executeQuery();

                    while(rs.next()) {
                        Vector<String> data = new Vector<String>();
                        data.add(rs.getString(1));
                        //data.add(rs.getString(2));
                        data.add(rs.getString(3));
                        data.add(rs.getString(4));
                        data.add(rs.getString(5));
                        data.add(rs.getString(6));
                        data.add(rs.getString(7));
                        data.add(rs.getString(8));
                        //data.add(rs.getString(9));
                        doctorVector.add(data);
            }

        return doctorVector;
    }

    public void addSchedule(String[] roster) throws ClassNotFoundException, SQLException {
        Connection conn1 = DbConnection.doConnection();
        String sql =

        //"UPDATE PMS_DUTY_ROSTER(DOCTOR_ID, DISCIPLINE, SUBDISCIPLINE, LOCATION_CODE, START_DATE,END_DATE,START_TIME,END_TIME) VALUES (?,?,?,?,?,?,?,?)";
        "UPDATE PMS_DUTY_ROSTER SET SUBDISCIPLINE=?, LOCATION_CODE=?, START_DATE=?, END_DATE=?, START_TIME=?, END_TIME=? WHERE DOCTOR_ID='"+roster[0]+"'";

        PreparedStatement ps = conn1.prepareStatement(sql);
        ps.setString(1, roster[1]);
        ps.setString(2, roster[2]);
        ps.setString(3, roster[3]);
        ps.setString(4, roster[4]);
        ps.setString(5, roster[5]);
        ps.setString(6, roster[6]);
        
        
        ps.executeUpdate();
        conn.close();
    }

    public Vector<Vector<String>> schedule(String text) throws ClassNotFoundException, SQLException {
        Vector<Vector<String>> viewSchedule = new Vector<Vector<String>>();
        Connection conn1 = DbConnection.doConnection();

            String sql = "";
            sql="SELECT * FROM PMS_DUTY_ROSTER where DISCIPLINE = ?";

            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();

                    while(rs.next()) {
                        Vector<String> data = new Vector<String>();
                        data.add(rs.getString(1));
                        data.add(rs.getString(4));
                        data.add(rs.getString(5));
                        data.add(rs.getString(6));
                        data.add(rs.getString(7));
                        data.add(rs.getString(8));
                        viewSchedule.add(data);
                    }

        return viewSchedule;
    }

    public String[] getdoctor(String selectedID) throws ClassNotFoundException, SQLException {
        String[] member = new String[8];
        Connection conn1 = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_DOCTORINFORMATION WHERE DOCTOR_ID=?";
        PreparedStatement ps = conn1.prepareStatement(sql);
        ps.setString(1,selectedID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            for(int i=0;i<8;i++) {
                member[i]=rs.getString(i+1);
            }
        }

        conn1.close();
        return member;
    }

    public String[] getpatientInfo(String text) throws ClassNotFoundException, SQLException {
        String[] info = new String[10];
        Connection conn1=DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?";
        PreparedStatement ps = conn1.prepareStatement(sql);
        ps.setString(1, text);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            for(int i =0; i<10; i++){
                info[i]=rs.getString(i+1);
            }
        }
        conn1.close();
        return info;

    }

}
