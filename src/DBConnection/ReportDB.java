/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Helper.J;
import Helper.Session;
import Process.MainRetrieval;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;

/**
 *
 * @author End User
 */
public class ReportDB {
    public static String getDataICD10(String date) {
        String dat = "";
        try {
            ArrayList<String> data = new ArrayList<String>();
            String temp = "";
            String sql = "";
            PreparedStatement ps = null;
//            LongRunProcess.check_network2();
//            //Insert data into database
//            if (Session.getPrev_stat()) { //Online
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//                Message impl = (Message) myRegistry.lookup("myMessage");
                //dat = impl.getReportICD10(date);
                dat = DBConnection.getImpl().getICD10SortReport(date);
//            } else {
//                
//            }
//            
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
        } catch (Exception ex) {
            J.o("Network Down", "System reporting is down!", 0);
        }
        return dat;
    }
    
    public static ArrayList<ArrayList<String>> getData(int type, String date1, String date2) {
        type += 1;
        ArrayList<ArrayList<String>> dat = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        String temp = "";
        String sql = "";
        PreparedStatement ps = null;
        try {
//            LongRunProcess.check_network2();
//            //Insert data into database
//            if (Session.getPrev_stat()) { //Online
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//                Message impl = (Message) myRegistry.lookup("myMessage");
                dat = DBConnection.getImpl().getReport(type, date1, date2);
//            } else {
//                
//            }
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
        } catch (Exception ex) {
            try {
                sql = "SELECT TXNDATA "
                        + "FROM PUBLIC.JOURNAL_FILE "
                        + "WHERE TXNDATE >= ? "
                        + "AND TXNDATE <= ? ";
                ps = Session.getCon_x(100).prepareStatement(sql);
                ps.setString(1, date1);
                ps.setString(2, date2);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    temp = rs.getString("TXNDATA");
                    MainRetrieval mr = new MainRetrieval();
                    mr.startProcess(temp);
                    String msg[][] = mr.getData(getSymptom(type));
                    int row = mr.getRowNums();
                    for (int i = 0; i < row; i++) {
                        data.add(msg[i][getReadCode(type)] + "|" + msg[i][getReadCode(type) + 1]);
                        System.out.println("msg: " + msg[i][getReadCode(type)] + "|" + msg[i][getReadCode(type) + 1]);
                    }
                }
                String te = "";
                ArrayList<String> lst = new ArrayList<String>();
                ArrayList<String> lst2 = new ArrayList<String>();
                for (int i = 0; i < data.size(); i++) {
                    te += data.get(i) + ", ";
                    lst.add(data.get(i));
                    lst2.add(data.get(i));
                }
                System.out.println("Duplicates List " + lst);
                Object[] st = lst.toArray();
                for (Object s : st) {
                    if (lst.indexOf(s) != lst.lastIndexOf(s)) {
                        lst.remove(lst.lastIndexOf(s));
                    }
                }
                System.out.println("Distinct List " + lst);
                ArrayList<String> llt = new ArrayList<String>();
                for (int i = 0; i < lst.size(); i++) {
                    int count = 0;
                    for (int j = 0; j < lst2.size(); j++) {
                        if (lst.get(i).equals(lst2.get(j))) {
                            count += 1;
                        }
                    }
                    llt.add("" + count);
                }
                System.out.println("Size          " + llt);
                dat.add(lst); //key
                dat.add(llt); //value
            } catch (SQLException ex1) {
//                Logger.getLogger(ReportDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return dat;
    }
    
    public static String getSymptom(int type) {
        /**
         * msg[0] = mr[0].getData("CCN");
            row[0] = mr[0].getRowNums();
            msg[1] = mr[1].getData("HPI");
            row[1] = mr[1].getRowNums();
            msg[2] = mr[2].getData("PMH");
            row[2] = mr[2].getRowNums();
            msg[3] = mr[3].getData("FMH");
            row[3] = mr[3].getRowNums();
            msg[4] = mr[4].getData("SOH");
            row[4] = mr[4].getRowNums();
            msg[5] = mr[5].getData("ALG");
            row[5] = mr[5].getRowNums();
            msg[6] = mr[6].getData("IMU");
            row[6] = mr[6].getRowNums();
            msg[7] = mr[7].getData("DAB");
            row[7] = mr[7].getRowNums();
            msg[8] = mr[8].getData("VTS");
            row[8] = mr[8].getRowNums();
            msg[9] = mr[9].getData("DGS");
            row[9] = mr[9].getRowNums();
            msg[10] = mr[10].getData("DTO");
         */
        String str = "CCN";
        switch(type) {
            case 1:
                str = "CCN";
                break;
            case 2:
                str = "PMH";
                break;
            case 3:
                str = "FMH";
                break;
            case 4:
                str = "SOH";
                break;
            case 5:
                str = "ALG";
                break;
            case 6:
                str = "IMU";
                break;
            case 7:
                str = "DAB";
                break;
            case 8:
                str = "DGS";
                break;
            case 9:
                str = "DTO";
                break;
        }
        return str;
    }
    
    public static int getReadCode(int type) {
        /**
         * msg[0] = mr[0].getData("CCN");
            msg[2] = mr[2].getData("PMH");
            msg[3] = mr[3].getData("FMH");
            msg[4] = mr[4].getData("SOH");
            msg[5] = mr[5].getData("ALG");
            msg[6] = mr[6].getData("IMU");
            msg[7] = mr[7].getData("DAB");
            msg[9] = mr[9].getData("DGS");
            msg[10] = mr[10].getData("DTO");
         */
        int str = 1;
        switch(type) {
            case 1:
                str = 1;
                break;
            case 2:
                str = 1;
                break;
            case 3:
                str = 4;
                break;
            case 4:
                str = 1;
                break;
            case 5:
                str = 1;
                break;
            case 6:
                str = 1;
                break;
            case 7:
                str = 1;
                break;
            case 8:
                str = 5;
                break;
            case 9:
                str = 4;
                break;
        }
        return str;
    }
}
