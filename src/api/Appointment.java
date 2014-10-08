/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import Database.DbConnection;
import Helper.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Chung Wei Ming
 */
public class Appointment {

   

    public Appointment()
    {
       
    }


     //get appointment detail from database using pmi no
    public Vector getAppointmentUsingPMI(String PMI) throws Exception {

        Vector<Vector<String>> AppointmentVectorPMI = new Vector<Vector<String>>();

        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO = ?";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, PMI);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vector<String> AppointmentInfoPMI = new Vector<String>();
            AppointmentInfoPMI.add(rs.getString(4));
            AppointmentInfoPMI.add(rs.getString(5));
            AppointmentInfoPMI.add(rs.getString(2));
            AppointmentInfoPMI.add(rs.getString(3));
            AppointmentInfoPMI.add(rs.getString(10));

            AppointmentVectorPMI.add(AppointmentInfoPMI);
        }
        return AppointmentVectorPMI;
    }


     //get appointment detail from database using new ic
     public Vector getAppointmentUsingNewIC(String NewIC) throws Exception
    {

        Vector<Vector<String>> AppointmentVectorNewIC = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO= (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, NewIC);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> AppointmentInfoNewIC = new Vector<String>();
                AppointmentInfoNewIC.add(rs.getString(4));
                AppointmentInfoNewIC.add(rs.getString(5));
                AppointmentInfoNewIC.add(rs.getString(2));
                AppointmentInfoNewIC.add(rs.getString(3));
                AppointmentInfoNewIC.add(rs.getString(10));
                
                AppointmentVectorNewIC.add(AppointmentInfoNewIC);
            }
                conn.close();
                return AppointmentVectorNewIC;
         }



     //get appointment detail from database using new ic
     public Vector getAppointmentUsingOldIC(String OldIC) throws Exception
    {

        Vector<Vector<String>> AppointmentVectorOldIC = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO= (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, OldIC);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> AppointmentInfoOldIC = new Vector<String>();
                AppointmentInfoOldIC.add(rs.getString(4));
                AppointmentInfoOldIC.add(rs.getString(5));
                AppointmentInfoOldIC.add(rs.getString(2));
                AppointmentInfoOldIC.add(rs.getString(3));
                AppointmentInfoOldIC.add(rs.getString(10));

                AppointmentVectorOldIC.add(AppointmentInfoOldIC);
            }
                conn.close();
                return AppointmentVectorOldIC;
         }


     //get Appointment detail from database using ID
     public Vector getAppointmentUsingID(String idno, String idtype) throws Exception
    {

        Vector<Vector<String>> AppointmentVectorID = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO= (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE=?)";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idno);
        ps.setString(2, idtype);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> AppointmentInfoID = new Vector<String>();
                AppointmentInfoID.add(rs.getString(4));
                AppointmentInfoID.add(rs.getString(5));
                AppointmentInfoID.add(rs.getString(2));
                AppointmentInfoID.add(rs.getString(3));
                AppointmentInfoID.add(rs.getString(10));
               
                AppointmentVectorID.add(AppointmentInfoID);
            }
                conn.close();
                return AppointmentVectorID;
         }

    //get patient biodata from database using MyKad
    public Vector<Vector<String>>  getAppointmentBiodataUsingMyKad(String MyKad) throws ClassNotFoundException, SQLException
    {
        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE (PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?))";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, MyKad);
        ResultSet rs = ps.executeQuery();

        Vector<Vector<String>> list = new   Vector<Vector<String>>();

        while(rs.next())
            {
               Vector<String> AppointmentInfoMyKad = new Vector<String>();
               AppointmentInfoMyKad.add(rs.getString(4));
               AppointmentInfoMyKad.add(rs.getString(5));
               AppointmentInfoMyKad.add(rs.getString(2));
               AppointmentInfoMyKad.add(rs.getString(3));
               AppointmentInfoMyKad.add(rs.getString(10));

                list.add(AppointmentInfoMyKad);
            }
                conn.close();
                return list;
    }


     public static void deleteAppointment(String appointmentInfo) throws ClassNotFoundException, SQLException
    {
        Connection conn = DbConnection.doConnection();
        String sql = "DELETE FROM PMS_APPOINTMENT_LIST WHERE PMI_NO = ('" + appointmentInfo+ "')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        conn.close();
        System.out.println("done delete");
    }


     //get appointment detail from database
     public Vector getAppointment() throws Exception
    {

        Vector<Vector<String>> AppointmentVector = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> AppointmentInfo = new Vector<String>();
                AppointmentInfo.add(rs.getString(4));
                AppointmentInfo.add(rs.getString(5));
                AppointmentInfo.add(rs.getString(2));
                AppointmentInfo.add(rs.getString(3));
                AppointmentInfo.add(rs.getString(10));
                
                AppointmentVector.add(AppointmentInfo);
            }
                conn.close();
                return AppointmentVector;

         }


}


