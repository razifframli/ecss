/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;


import DBConnection.DBConnection;
import Database.DbConnection;
import Helper.S;
import Helper.Session;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;

/**
 *
 * @author Chung Wei Ming
 */
public class Queue {

    public Queue()
    {
    }

    //get queue information from database
    public Vector getQueueList(String date) throws Exception {

        Vector<Vector<String>> QueueVector = new Vector<Vector<String>>();
        String sql = "SELECT PMI_NO,NAME,EPISODE_TIME,CONSULTATION_ROOM,DOCTOR,STATUS FROM PMS_EPISODE WHERE EPISODE_DATE=? AND STATUS NOT LIKE 'Discharge'";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, date);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vector<String> queue = new Vector<String>();
            queue.add(rs.getString(1));//pmino
            queue.add(rs.getString(2));//name
            queue.add(rs.getString(3));//time
            queue.add(rs.getString(4));//appointmenttype
            queue.add(rs.getString(5));
            queue.add(rs.getString(6));//status

            QueueVector.add(queue);
        }
        return QueueVector;

    }
    
    private static boolean isDateToday(String date) {
        boolean stat = false;
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String now = dateFormat.format(today.getTime());
        String str1[] = date.split("/");
        String day1 = str1[0];
        String month1 = str1[1];
        String year1 = str1[2];
        String str2[] = now.split("/");
        String day2 = str2[0];
        String month2 = str2[1];
        String year2 = str2[2];
        if(day1.equals(day2) && month1.equals(month2) && year1.equals(year2)) {
            stat = true;
        }
        return stat;
    }
       
    public Vector getQueueNameList(String name, int tanda) throws Exception {
        
        Vector<Vector<String>> QueueVector = new Vector<Vector<String>>();
        
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            //System.err.println();
            //System.err.println("Server Online");
            //System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	

                QueueVector = DBConnection.getImpl().getQueueNameList(name, Session.getHfc_code());

                //System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                
                String sql = "SELECT PMI_NO,NAME,EPISODE_TIME,CONSULTATION_ROOM,DOCTOR,"
                        + "STATUS,EPISODE_DATE FROM PMS_EPISODE "
                        + "WHERE NAME LIKE upper(?) "
                        + "AND HEALTH_FACILITY_CODE = ? "
                        //+ "AND STATUS NOT LIKE 'Consult' "
                        + "AND STATUS NOT LIKE 'Discharge' "
                        + "AND STATUS NOT LIKE 'Missing' "
                        + "ORDER BY EPISODE_TIME ASC";
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                name = "%" + name + "%";
                ps.setString(1, name);
                ps.setString(2, Session.getHfc_code());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (isDateToday(rs.getString(7))) {
                        Vector<String> queue = new Vector<String>();
                        queue.add(rs.getString(1));//pmino
                        queue.add(rs.getString(2));//name
                        queue.add(rs.getString(3));//time
                        queue.add(rs.getString(4));//room
                        queue.add(rs.getString(5));//doctor
                        queue.add(rs.getString(6));
                        
                        if (tanda == 2) {
                            queue.add("DELETE");
                        } 

                        QueueVector.add(queue);
                    }
                }
                
                //e.printStackTrace();
            }
//        } else {
//            //offline
//            
//            String sql = "SELECT PMI_NO,NAME,EPISODE_TIME,CONSULTATION_ROOM,DOCTOR,"
//                    + "STATUS,EPISODE_DATE FROM PMS_EPISODE "
//                    + "WHERE NAME LIKE upper(?) "
//                    + "AND HEALTH_FACILITY_CODE = ? "
//                    + "AND STATUS NOT LIKE 'Consult' "
//                    + "AND STATUS NOT LIKE 'Discharge' "
//                    + "AND STATUS NOT LIKE 'Missing' "
//                    + "ORDER BY EPISODE_TIME ASC";
//            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//            name = "%" + name + "%";
//            ps.setString(1, name);
//            ps.setString(2, Session.getHfc_code());
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                if(isDateToday(rs.getString(7))) {
//                    Vector<String> queue = new Vector<String>();
//                    queue.add(rs.getString(1));//pmino
//                    queue.add(rs.getString(2));//name
//                    queue.add(rs.getString(3));//time
//                    queue.add(rs.getString(4));//room
//                    queue.add(rs.getString(5));//doctor
//                    queue.add(rs.getString(6));
//
//                    QueueVector.add(queue);
//                }
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        
        return QueueVector;
    }



    public Vector getQueueIcList(String IC) throws Exception {
        Vector<Vector<String>> QueueVector = new Vector<Vector<String>>();
        
        String sql = "SELECT PMI_NO,NAME,EPISODE_TIME,CONSULTATION_ROOM,DOCTOR,"
                + "STATUS FROM PMS_EPISODE "
                + "WHERE NEW_IC_NO like ? "
                + "AND STATUS NOT LIKE 'Discharge' "
                + "AND STATUS NOT LIKE 'Missing' "
                + "ORDER BY EPISODE_TIME ASC";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        IC = "%"+IC+"%";
        ps.setString(1, IC);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Vector<String> queue = new Vector<String>();
            queue.add(rs.getString(1));//pmino
            queue.add(rs.getString(2));//name
            queue.add(rs.getString(3));//date
            queue.add(rs.getString(4));//time
            queue.add(rs.getString(5));//appointmenttype
            queue.add(rs.getString(6));

            QueueVector.add(queue);
        }
        return QueueVector;

    }


    public Vector getActivePatientHistory(String pmiNo) throws Exception {

        Vector<Vector<String>> QueueVector = new Vector<Vector<String>>();

        String sql = "SELECT PMI_NO,PROBLEM,ENCOUNTER_DATE,PROBLEM_STATUS "
                + "FROM CHIEF_COMPLAINTS "
                + "WHERE PMI_NO=? "
                + "AND PROBLEM_STATUS='Active'";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, pmiNo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vector<String> queue = new Vector<String>();
            queue.add(rs.getString(1));//pmino
            queue.add(rs.getString(2));//PROBLEM
            queue.add(rs.getString(3));//ENCOUNTER_DATE
            queue.add(rs.getString(4));

            QueueVector.add(queue);
        }
        rs.close();
        return QueueVector;

    }


    public Vector getInactivePatientHistory(String pmiNo) throws Exception {

        Vector<Vector<String>> QueueVector = new Vector<Vector<String>>();

        String sql = "SELECT PMI_NO,PROBLEM,ENCOUNTER_DATE,PROBLEM_STATUS "
                + "FROM CHIEF_COMPLAINTS "
                + "WHERE PMI_NO=? "
                + "AND PROBLEM_STATUS='In Active'";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, pmiNo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vector<String> queue = new Vector<String>();
            queue.add(rs.getString(1));//pmino
            queue.add(rs.getString(2));//PROBLEM
            queue.add(rs.getString(3));//ENCOUNTER_DATE
            queue.add(rs.getString(4));

            QueueVector.add(queue);
        }
        rs.close();
        return QueueVector;

    }


    public Vector getAllPatientHistory(String pmiNo) throws Exception {

        Vector<Vector<String>> QueueVector = new Vector<Vector<String>>();

        String sql = "SELECT PMI_NO,PROBLEM,ENCOUNTER_DATE,PROBLEM_STATUS "
                + "FROM CHIEF_COMPLAINTS "
                + "WHERE PMI_NO=?";
        //PreparedStatement ps = conn.prepareStatement("SELECT PMI_NO,NAME,DATE,TIME,APPOINTMENT_TYPE FROM PMS_APPOINTMENT_LIST WHERE DISCIPLINE=? AND SUBDISCIPLINE=? AND DATE=?");
        //ps.setString(1, appointmentInformation);
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, pmiNo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vector<String> queue = new Vector<String>();
            queue.add(rs.getString(1));//pmino
            queue.add(rs.getString(2));//PROBLEM
            queue.add(rs.getString(3));//ENCOUNTER_DATE
            queue.add(rs.getString(4));

            QueueVector.add(queue);
        }
        rs.close();
        return QueueVector;

    }
    
    public void updateStatusEpisode(String PMINumber, String TimeEpisode, String status, String referer) {
        
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
                
                DBConnection.getImpl().updateStatEpisode(PMINumber, TimeEpisode, status, Session.getUser_name(), referer);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                try {
                    //offline
                    S.oln("-- Offline --");
                    String plussql = "";
                    if (status.equals("Consult") || status.equals("Hold") || status.equals("Second Opinion")) {
                        plussql = ", DOCTOR=?";
                    }
                    PreparedStatement statement2 = Session.getCon_x(100).prepareStatement("UPDATE PMS_EPISODE "
                            + "SET STATUS=?"
                            + plussql + " "
                            + "WHERE PMI_NO=? "
                            + "AND EPISODE_TIME=?");
                    statement2.setString(1, status);
                    if (status.equals("Consult") || status.equals("Hold") || status.equals("Second Opinion")) {
                        String doctor = Session.getUser_name();
                        if (status.equals("Second Opinion")) {
                            doctor = referer;
                        }
                        statement2.setString(2, doctor);
                        statement2.setString(3, PMINumber);
                        statement2.setString(4, TimeEpisode);
                    } else {
                        statement2.setString(2, PMINumber);
                        statement2.setString(3, TimeEpisode);
                    }
                    statement2.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
//        } else {
//            try {
//                //offline
//                S.oln("-- Offline --");
//                String plussql = "";
//                if (status.equals("Consult")) {
//                    plussql = ", DOCTOR=?";
//                }
//                PreparedStatement statement2 = Session.getCon_x(100).prepareStatement("UPDATE PMS_EPISODE "
//                        + "SET STATUS=?"
//                        + plussql + " "
//                        + "WHERE PMI_NO=? "
//                        + "AND EPISODE_TIME=?");
//                statement2.setString(1, status);
//                if (status.equals("Consult")) {
//                    statement2.setString(2, Session.getUser_name());
//                    statement2.setString(3, PMINumber);
//                    statement2.setString(4, TimeEpisode);
//                } else {
//                    statement2.setString(2, PMINumber);
//                    statement2.setString(3, TimeEpisode);
//                }
//                statement2.executeUpdate();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
    }
    
    public void updateStatusEpisodeHold(String PMINumber, String TimeEpisode) {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String now = dateFormat.format(today.getTime());
        
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
                
                DBConnection.getImpl().updateStatEpisode2(PMINumber, TimeEpisode, now);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
                try {
                    PreparedStatement statement2 = Session.getCon_x(100).prepareStatement("UPDATE PMS_EPISODE "
                            + "SET STATUS='Hold' "
                            + "WHERE PMI_NO=? "
                            + "AND EPISODE_TIME=? "
                            + "AND EPISODE_DATE=?");
                    statement2.setString(1, PMINumber);
                    statement2.setString(2, TimeEpisode);
                    statement2.setString(3, now);
                    statement2.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
//        } else {
//            try {
//                PreparedStatement statement2 = Session.getCon_x(100).prepareStatement("UPDATE PMS_EPISODE "
//                        + "SET STATUS='Hold' "
//                        + "WHERE PMI_NO=? "
//                        + "AND EPISODE_TIME=? "
//                        + "AND EPISODE_DATE=?");
//                statement2.setString(1, PMINumber);
//                statement2.setString(2, TimeEpisode);
//                statement2.setString(3, now);
//                statement2.executeUpdate();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        
    }
    
}
