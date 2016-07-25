/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import DBConnection.DBConnection;
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

    public Appointment() {

    }

    //get appointment detail from database using pmi no
    public Vector getAppointmentUsingPMI(String PMI) throws Exception {

        Vector<Vector<String>> AppointmentVectorPMI = new Vector<Vector<String>>();

//        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO = ?";
        String sql = "SELECT "
                + "lookSub.appointment_date, "
                + "lookSub.start_time, "
                + "lookSub.pmi_no, "
                + "lookSub.patient_name, "
                + "lookSub.staff_name, "
                + "lookSub.discipline_name, "
                + "lds.Description AS subdipline_name, "
                + "lookSub.appointment_type, "
                + "lookSub.ID_NO, "
                + "lookSub.status "
                + "FROM lookup_detail lds, (SELECT lookDis.appointment_date, "
                + "lookDis.start_time, lookDis.pmi_no, "
                + "lookDis.PATIENT_NAME AS patient_name, "
                + "lookDis.USER_NAME AS staff_name, "
                + "ld.Description AS discipline_name, lookDis.subdiscipline, "
                + "lookDis.appointment_type, lookDis.ID_NO, lookDis.status "
                + "FROM lookup_detail ld, "
                + "(SELECT DATE(pa.appointment_date) AS appointment_date, "
                + "TIME(pa.start_time) AS start_time, pa.pmi_no, "
                + "LCASE(pb.PATIENT_NAME) AS PATIENT_NAME, "
                + "LCASE(au.USER_NAME) AS USER_NAME, pa.discipline, "
                + "pa.subdiscipline, pa.appointment_type, pb.ID_NO, "
                + "pa.status FROM pms_appointment pa, pms_patient_biodata pb, "
                + "adm_user au WHERE pa.pmi_no = pb.PMI_NO AND "
                + "pa.userid = au.USER_ID ORDER BY pa.appointment_date ASC) "
                + "lookDis WHERE lookDis.discipline=ld.Detail_Ref_code AND "
                + "ld.Master_Ref_code = '0072') lookSub WHERE "
                + "lds.Master_Ref_code = '0071' "
                + "AND lookSub.subdiscipline=lds.Detail_Ref_code";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, PMI);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vector<String> AppointmentInfoPMI = new Vector<String>();
//            AppointmentInfoPMI.add(rs.getString(4));
//            AppointmentInfoPMI.add(rs.getString(5));
//            AppointmentInfoPMI.add(rs.getString(2));
//            AppointmentInfoPMI.add(rs.getString(3));
//            AppointmentInfoPMI.add(rs.getString(10));
            AppointmentInfoPMI.add(rs.getString(3));
            AppointmentInfoPMI.add(rs.getString(4));
            AppointmentInfoPMI.add(rs.getString(1));
            AppointmentInfoPMI.add(rs.getString(2));
            AppointmentInfoPMI.add(rs.getString(8));

            AppointmentVectorPMI.add(AppointmentInfoPMI);
        }
        return AppointmentVectorPMI;
    }

    //get appointment detail from database using new ic
    public Vector getAppointmentUsingNewIC(String NewIC) throws Exception {

        Vector<Vector<String>> AppointmentVectorNewIC = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
//        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO= (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?)";
        String sql = "SELECT "
                + "lookSub.appointment_date, "
                + "lookSub.start_time, "
                + "lookSub.pmi_no, "
                + "lookSub.patient_name, "
                + "lookSub.staff_name, "
                + "lookSub.discipline_name, "
                + "lds.Description AS subdipline_name, "
                + "lookSub.appointment_type, "
                + "lookSub.ID_NO, "
                + "lookSub.status "
                + "FROM lookup_detail lds, (SELECT lookDis.appointment_date, "
                + "lookDis.start_time, lookDis.pmi_no, "
                + "lookDis.PATIENT_NAME AS patient_name, "
                + "lookDis.USER_NAME AS staff_name, "
                + "ld.Description AS discipline_name, lookDis.subdiscipline, "
                + "lookDis.appointment_type, lookDis.ID_NO, lookDis.status "
                + "FROM lookup_detail ld, "
                + "(SELECT DATE(pa.appointment_date) AS appointment_date, "
                + "TIME(pa.start_time) AS start_time, pa.pmi_no, "
                + "LCASE(pb.PATIENT_NAME) AS PATIENT_NAME, "
                + "LCASE(au.USER_NAME) AS USER_NAME, pa.discipline, "
                + "pa.subdiscipline, pa.appointment_type, pb.ID_NO, "
                + "pa.status FROM pms_appointment pa, pms_patient_biodata pb, "
                + "adm_user au WHERE pa.pmi_no = pb.PMI_NO AND "
                + "pa.userid = au.USER_ID ORDER BY pa.appointment_date ASC) "
                + "lookDis WHERE lookDis.discipline=ld.Detail_Ref_code AND "
                + "ld.Master_Ref_code = '0072') lookSub WHERE "
                + "lds.Master_Ref_code = '0071' "
                + "AND lookSub.subdiscipline=lds.Detail_Ref_code";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, NewIC);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vector<String> AppointmentInfoNewIC = new Vector<String>();
            AppointmentInfoNewIC.add(rs.getString(3));
            AppointmentInfoNewIC.add(rs.getString(4));
            AppointmentInfoNewIC.add(rs.getString(1));
            AppointmentInfoNewIC.add(rs.getString(2));
            AppointmentInfoNewIC.add(rs.getString(8));

            AppointmentVectorNewIC.add(AppointmentInfoNewIC);
        }
        conn.close();
        return AppointmentVectorNewIC;
    }

    //get appointment detail from database using new ic
    public Vector getAppointmentUsingOldIC(String OldIC) throws Exception {

        Vector<Vector<String>> AppointmentVectorOldIC = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
//        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO= (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE OLD_IC_NO=?)";
        String sql = "SELECT "
                + "lookSub.appointment_date, "
                + "lookSub.start_time, "
                + "lookSub.pmi_no, "
                + "lookSub.patient_name, "
                + "lookSub.staff_name, "
                + "lookSub.discipline_name, "
                + "lds.Description AS subdipline_name, "
                + "lookSub.appointment_type, "
                + "lookSub.ID_NO, "
                + "lookSub.status "
                + "FROM lookup_detail lds, (SELECT lookDis.appointment_date, "
                + "lookDis.start_time, lookDis.pmi_no, "
                + "lookDis.PATIENT_NAME AS patient_name, "
                + "lookDis.USER_NAME AS staff_name, "
                + "ld.Description AS discipline_name, lookDis.subdiscipline, "
                + "lookDis.appointment_type, lookDis.ID_NO, lookDis.status "
                + "FROM lookup_detail ld, "
                + "(SELECT DATE(pa.appointment_date) AS appointment_date, "
                + "TIME(pa.start_time) AS start_time, pa.pmi_no, "
                + "LCASE(pb.PATIENT_NAME) AS PATIENT_NAME, "
                + "LCASE(au.USER_NAME) AS USER_NAME, pa.discipline, "
                + "pa.subdiscipline, pa.appointment_type, pb.ID_NO, "
                + "pa.status FROM pms_appointment pa, pms_patient_biodata pb, "
                + "adm_user au WHERE pa.pmi_no = pb.PMI_NO AND "
                + "pa.userid = au.USER_ID ORDER BY pa.appointment_date ASC) "
                + "lookDis WHERE lookDis.discipline=ld.Detail_Ref_code AND "
                + "ld.Master_Ref_code = '0072') lookSub WHERE "
                + "lds.Master_Ref_code = '0071' "
                + "AND lookSub.subdiscipline=lds.Detail_Ref_code";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, OldIC);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vector<String> AppointmentInfoOldIC = new Vector<String>();
            AppointmentInfoOldIC.add(rs.getString(3));
            AppointmentInfoOldIC.add(rs.getString(4));
            AppointmentInfoOldIC.add(rs.getString(1));
            AppointmentInfoOldIC.add(rs.getString(2));
            AppointmentInfoOldIC.add(rs.getString(8));

            AppointmentVectorOldIC.add(AppointmentInfoOldIC);
        }
        conn.close();
        return AppointmentVectorOldIC;
    }

    //get Appointment detail from database using ID
    public Vector getAppointmentUsingID(String idno, String idtype) throws Exception {

        Vector<Vector<String>> AppointmentVectorID = new Vector<Vector<String>>();

        Connection conn = DbConnection.doConnection();
//        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE PMI_NO= (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE ID_NO=? AND ID_TYPE=?)";
        String sql = "SELECT "
                + "lookSub.appointment_date, "
                + "lookSub.start_time, "
                + "lookSub.pmi_no, "
                + "lookSub.patient_name, "
                + "lookSub.staff_name, "
                + "lookSub.discipline_name, "
                + "lds.Description AS subdipline_name, "
                + "lookSub.appointment_type, "
                + "lookSub.ID_NO, "
                + "lookSub.status "
                + "FROM lookup_detail lds, (SELECT lookDis.appointment_date, "
                + "lookDis.start_time, lookDis.pmi_no, "
                + "lookDis.PATIENT_NAME AS patient_name, "
                + "lookDis.USER_NAME AS staff_name, "
                + "ld.Description AS discipline_name, lookDis.subdiscipline, "
                + "lookDis.appointment_type, lookDis.ID_NO, lookDis.status "
                + "FROM lookup_detail ld, "
                + "(SELECT DATE(pa.appointment_date) AS appointment_date, "
                + "TIME(pa.start_time) AS start_time, pa.pmi_no, "
                + "LCASE(pb.PATIENT_NAME) AS PATIENT_NAME, "
                + "LCASE(au.USER_NAME) AS USER_NAME, pa.discipline, "
                + "pa.subdiscipline, pa.appointment_type, pb.ID_NO, "
                + "pa.status FROM pms_appointment pa, pms_patient_biodata pb, "
                + "adm_user au WHERE pa.pmi_no = pb.PMI_NO AND "
                + "pa.userid = au.USER_ID ORDER BY pa.appointment_date ASC) "
                + "lookDis WHERE lookDis.discipline=ld.Detail_Ref_code AND "
                + "ld.Master_Ref_code = '0072') lookSub WHERE "
                + "lds.Master_Ref_code = '0071' "
                + "AND lookSub.subdiscipline=lds.Detail_Ref_code";
//PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idno);
        ps.setString(2, idtype);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vector<String> AppointmentInfoID = new Vector<String>();
            AppointmentInfoID.add(rs.getString(3));
            AppointmentInfoID.add(rs.getString(4));
            AppointmentInfoID.add(rs.getString(1));
            AppointmentInfoID.add(rs.getString(2));
            AppointmentInfoID.add(rs.getString(8));

            AppointmentVectorID.add(AppointmentInfoID);
        }
        conn.close();
        return AppointmentVectorID;
    }

    //get patient biodata from database using MyKad
    public Vector<Vector<String>> getAppointmentBiodataUsingMyKad(String MyKad) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.doConnection();
//        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST WHERE (PMI_NO = (SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=?))";
        String sql = "SELECT "
                + "lookSub.appointment_date, "
                + "lookSub.start_time, "
                + "lookSub.pmi_no, "
                + "lookSub.patient_name, "
                + "lookSub.staff_name, "
                + "lookSub.discipline_name, "
                + "lds.Description AS subdipline_name, "
                + "lookSub.appointment_type, "
                + "lookSub.ID_NO, "
                + "lookSub.status "
                + "FROM lookup_detail lds, (SELECT lookDis.appointment_date, "
                + "lookDis.start_time, lookDis.pmi_no, "
                + "lookDis.PATIENT_NAME AS patient_name, "
                + "lookDis.USER_NAME AS staff_name, "
                + "ld.Description AS discipline_name, lookDis.subdiscipline, "
                + "lookDis.appointment_type, lookDis.ID_NO, lookDis.status "
                + "FROM lookup_detail ld, "
                + "(SELECT DATE(pa.appointment_date) AS appointment_date, "
                + "TIME(pa.start_time) AS start_time, pa.pmi_no, "
                + "LCASE(pb.PATIENT_NAME) AS PATIENT_NAME, "
                + "LCASE(au.USER_NAME) AS USER_NAME, pa.discipline, "
                + "pa.subdiscipline, pa.appointment_type, pb.ID_NO, "
                + "pa.status FROM pms_appointment pa, pms_patient_biodata pb, "
                + "adm_user au WHERE pa.pmi_no = pb.PMI_NO AND "
                + "pa.userid = au.USER_ID ORDER BY pa.appointment_date ASC) "
                + "lookDis WHERE lookDis.discipline=ld.Detail_Ref_code AND "
                + "ld.Master_Ref_code = '0072') lookSub WHERE "
                + "lds.Master_Ref_code = '0071' "
                + "AND lookSub.subdiscipline=lds.Detail_Ref_code";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, MyKad);
        ResultSet rs = ps.executeQuery();

        Vector<Vector<String>> list = new Vector<Vector<String>>();

        while (rs.next()) {
            Vector<String> AppointmentInfoMyKad = new Vector<String>();
            AppointmentInfoMyKad.add(rs.getString(3));
            AppointmentInfoMyKad.add(rs.getString(4));
            AppointmentInfoMyKad.add(rs.getString(1));
            AppointmentInfoMyKad.add(rs.getString(2));
            AppointmentInfoMyKad.add(rs.getString(8));

            list.add(AppointmentInfoMyKad);
        }
        conn.close();
        return list;
    }

    public static void deleteAppointment(String appointmentInfo) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.doConnection();
        String sql = "DELETE FROM PMS_APPOINTMENT_LIST WHERE PMI_NO = ('" + appointmentInfo + "')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        conn.close();
        System.out.println("done delete");
    }

    //get appointment detail from database
    public Vector getAppointment() throws Exception {

        Vector<Vector<String>> AppointmentVector = new Vector<Vector<String>>();

//        Connection conn = DbConnection.doConnection();
//        String sql = "SELECT * FROM PMS_APPOINTMENT_LIST";
        String sql = "SELECT "
                + "lookSub.appointment_date, "
                + "lookSub.start_time, "
                + "lookSub.pmi_no, "
                + "lookSub.patient_name, "
                + "lookSub.staff_name, "
                + "lookSub.discipline_name, "
                + "lds.Description AS subdipline_name, "
                + "lookSub.appointment_type, "
                + "lookSub.ID_NO, "
                + "lookSub.status "
                + "FROM lookup_detail lds, (SELECT lookDis.appointment_date, "
                + "lookDis.start_time, lookDis.pmi_no, "
                + "lookDis.PATIENT_NAME AS patient_name, "
                + "lookDis.USER_NAME AS staff_name, "
                + "ld.Description AS discipline_name, lookDis.subdiscipline, "
                + "lookDis.appointment_type, lookDis.ID_NO, lookDis.status "
                + "FROM lookup_detail ld, "
                + "(SELECT DATE(pa.appointment_date) AS appointment_date, "
                + "TIME(pa.start_time) AS start_time, pa.pmi_no, "
                + "LCASE(pb.PATIENT_NAME) AS PATIENT_NAME, "
                + "LCASE(au.USER_NAME) AS USER_NAME, pa.discipline, "
                + "pa.subdiscipline, pa.appointment_type, pb.ID_NO, "
                + "pa.status FROM pms_appointment pa, pms_patient_biodata pb, "
                + "adm_user au WHERE pa.pmi_no = pb.PMI_NO AND "
                + "pa.userid = au.USER_ID ORDER BY pa.appointment_date ASC) "
                + "lookDis WHERE lookDis.discipline=ld.Detail_Ref_code AND "
                + "ld.Master_Ref_code = '0072') lookSub WHERE "
                + "lds.Master_Ref_code = '0071' "
                + "AND lookSub.subdiscipline=lds.Detail_Ref_code";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
        
        ArrayList<ArrayList<String>> data_app = DBConnection.getImpl().getQuerySQL(sql);

//        while (rs.next()) {
        for (int i = 0; i < data_app.size(); i++) {

            Vector<String> AppointmentInfo = new Vector<String>();
            AppointmentInfo.add(data_app.get(i).get(2));
            AppointmentInfo.add(data_app.get(i).get(3));
            AppointmentInfo.add(data_app.get(i).get(0));
            AppointmentInfo.add(data_app.get(i).get(1));
            AppointmentInfo.add(data_app.get(i).get(7));

            AppointmentVector.add(AppointmentInfo);
        }
//        conn.close();
        return AppointmentVector;

    }

}
