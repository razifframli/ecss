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
import java.util.Vector;
/**
 *
 * @author Chung Wei Ming
 */
public class Referral {



    public Referral()
    {
    }



        //get patient biodata from database using MyKad
        public Vector<Vector<String>>  getReferralBiodataUsingMyKad(String MyKad) throws ClassNotFoundException, SQLException
    {

        //Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_REFERRAL_EMAIL WHERE NEW_IC_NO =?";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, MyKad);
        ResultSet rs = ps.executeQuery();

        Vector< Vector<String>> list = new   Vector< Vector<String>>();


        while(rs.next())
            {
               Vector<String> referralEmailInfoMyKad = new Vector<String>();
                referralEmailInfoMyKad.add(rs.getString(1));
                referralEmailInfoMyKad.add(rs.getString(2));
                referralEmailInfoMyKad.add(rs.getString(3));
                referralEmailInfoMyKad.add(rs.getString(4));
                referralEmailInfoMyKad.add(rs.getString(5));
                referralEmailInfoMyKad.add(rs.getString(6));
                referralEmailInfoMyKad.add(rs.getString(7));
                referralEmailInfoMyKad.add(rs.getString(8));
                referralEmailInfoMyKad.add(rs.getString(9));


                list.add(referralEmailInfoMyKad);
            }
                //conn.close();
                return list;
    }




        //get Email referral detail from database
        public Vector getEmailReferral() throws Exception
    {

        Vector<Vector<String>> ReferralVector = new Vector<Vector<String>>();

        //Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_REFERRAL_EMAIL";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> referralEmailInfo = new Vector<String>();
                referralEmailInfo.add(rs.getString(1));
                referralEmailInfo.add(rs.getString(2));
                referralEmailInfo.add(rs.getString(3));
                referralEmailInfo.add(rs.getString(4));
                referralEmailInfo.add(rs.getString(5));
                referralEmailInfo.add(rs.getString(6));
                referralEmailInfo.add(rs.getString(7));
                referralEmailInfo.add(rs.getString(8));
                referralEmailInfo.add(rs.getString(9));


                ReferralVector.add(referralEmailInfo);
            }
                //conn.close();
                return ReferralVector;

         }


    public static void deleteEmailReferral(String EmailReferral) throws ClassNotFoundException, SQLException
    {
        //Connection conn = DbConnection.doConnection();
        String sql = "DELETE FROM PMS_REFERRAL_EMAIL WHERE NAME = ('" + EmailReferral+ "')";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.executeUpdate();
        //conn.close();
    }



     //get Email referral detail from database using ID
     public Vector getEmailReferralUsingID(String idno, String idtype) throws Exception
    {

        Vector<Vector<String>> ReferralVectorID = new Vector<Vector<String>>();

        //Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_REFERRAL_EMAIL WHERE ID_NO=? AND ID_TYPE=?";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, idno);
        ps.setString(2, idtype);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> referralEmailInfoID = new Vector<String>();
                referralEmailInfoID.add(rs.getString(1));
                referralEmailInfoID.add(rs.getString(2));
                referralEmailInfoID.add(rs.getString(3));
                referralEmailInfoID.add(rs.getString(4));
                referralEmailInfoID.add(rs.getString(5));
                referralEmailInfoID.add(rs.getString(6));
                referralEmailInfoID.add(rs.getString(7));
                referralEmailInfoID.add(rs.getString(8));
                referralEmailInfoID.add(rs.getString(9));


                ReferralVectorID.add(referralEmailInfoID);
            }
                //conn.close();
                return ReferralVectorID;

         }
     
     
     //get Email referral detail from database using new ic
     public Vector getEmailReferralUsingNewIC(String NewIC) throws Exception
    {

        Vector<Vector<String>> ReferralVectorNewIC = new Vector<Vector<String>>();

        //Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_REFERRAL_EMAIL WHERE NEW_IC_NO=?";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, NewIC);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> referralEmailInfoNewIC = new Vector<String>();
                referralEmailInfoNewIC.add(rs.getString(1));
                referralEmailInfoNewIC.add(rs.getString(2));
                referralEmailInfoNewIC.add(rs.getString(3));
                referralEmailInfoNewIC.add(rs.getString(4));
                referralEmailInfoNewIC.add(rs.getString(5));
                referralEmailInfoNewIC.add(rs.getString(6));
                referralEmailInfoNewIC.add(rs.getString(7));
                referralEmailInfoNewIC.add(rs.getString(8));
                referralEmailInfoNewIC.add(rs.getString(9));


                ReferralVectorNewIC.add(referralEmailInfoNewIC);
            }
                //conn.close();
                return ReferralVectorNewIC;

         }


     //get Email referral detail from database using old ic
     public Vector getEmailReferralUsingOldIC(String OldIC) throws Exception
    {

        Vector<Vector<String>> ReferralVectorOldIC = new Vector<Vector<String>>();

       // Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_REFERRAL_EMAIL WHERE OLD_IC_NO=?";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, OldIC);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> referralEmailInfoOldIC = new Vector<String>();
                referralEmailInfoOldIC.add(rs.getString(1));
                referralEmailInfoOldIC.add(rs.getString(2));
                referralEmailInfoOldIC.add(rs.getString(3));
                referralEmailInfoOldIC.add(rs.getString(4));
                referralEmailInfoOldIC.add(rs.getString(5));
                referralEmailInfoOldIC.add(rs.getString(6));
                referralEmailInfoOldIC.add(rs.getString(7));
                referralEmailInfoOldIC.add(rs.getString(8));
                referralEmailInfoOldIC.add(rs.getString(9));


                ReferralVectorOldIC.add(referralEmailInfoOldIC);
            }
                //conn.close();
                return ReferralVectorOldIC;

         }


     //get Email referral detail from database using pmi no
     public Vector getEmailReferralUsingPMI(String newic, String oldic,String ID) throws Exception
    {

        Vector<Vector<String>> ReferralVectorPMI = new Vector<Vector<String>>();

        //Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_REFERRAL_EMAIL WHERE (NEW_IC_NO = (SELECT NEW_IC_NO FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?)) OR (OLD_IC_NO = (SELECT OLD_IC_NO FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?)) OR (ID_NO = (SELECT ID_NO FROM PMS_PATIENT_BIODATA WHERE PMI_NO=?))";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, newic);
        ps.setString(2, oldic);
        ps.setString(3, ID);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            {

                Vector<String> referralEmailInfoPMI = new Vector<String>();
                referralEmailInfoPMI.add(rs.getString(1));
                referralEmailInfoPMI.add(rs.getString(2));
                referralEmailInfoPMI.add(rs.getString(3));
                referralEmailInfoPMI.add(rs.getString(4));
                referralEmailInfoPMI.add(rs.getString(5));
                referralEmailInfoPMI.add(rs.getString(6));
                referralEmailInfoPMI.add(rs.getString(7));
                referralEmailInfoPMI.add(rs.getString(8));
                referralEmailInfoPMI.add(rs.getString(9));


                ReferralVectorPMI.add(referralEmailInfoPMI);
            }
                //conn.close();
                return ReferralVectorPMI;
         }
     
  }

