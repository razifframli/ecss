/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import Bean.Item;
import DBConnection.DBConnection;
import DatabaseConnection.connection;
import Helper.Session;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.Consultation;
import javax.swing.DefaultComboBoxModel;
import library.Func;
import oms.rmi.server.Message;

/**
 *
 * @author WC
 */
public class LookupController {

    DefaultComboBoxModel GeneralCBmodel;
    private Item item;
    Connection conn = null;
    
    public DefaultComboBoxModel getDataCmbx(String txt, boolean blank) throws ClassNotFoundException, SQLException {
        GeneralCBmodel = new DefaultComboBoxModel();

        if (blank == true) {
            GeneralCBmodel.addElement("Select " + txt);
            GeneralCBmodel.addElement("-");

        } else {
            GeneralCBmodel.addElement("Select " + txt);
        }

//        if (Session.getPrev_stat()) {
            try {
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");

                ArrayList<ArrayList<String>> rs = DBConnection.getImpl().getDoctors(Session.getUser_id());

                if (rs.size() > 0) {
                    Consultation.rs_ref = rs;
                }

                for (int i = 0; i < rs.size(); i++) {
                    item = new Item(rs.get(i).get(0), rs.get(i).get(3));
                    GeneralCBmodel.addElement(item);
                }

                Consultation.showOnline();
            } catch (Exception e) {
                Consultation.showOffline();
                //e.printStackTrace();
            }
//        } else {
//            Consultation.showOffline();
//        }

//        conn.close();
        return GeneralCBmodel;
    }

    public DefaultComboBoxModel getLookupReferences(String Code, String txt, boolean blank) throws ClassNotFoundException, SQLException {
        GeneralCBmodel = new DefaultComboBoxModel();

        if (blank == true) {
            GeneralCBmodel.addElement("Select " + txt);
            GeneralCBmodel.addElement("-");

        } else {
            GeneralCBmodel.addElement("Select " + txt);
        }
        //System.out.println("getLookupReferences.....COde : " + Code);
        //System.out.println("STart..."+System.currentTimeMillis());
        //FRiza 15/2/2013
        //conn = connection.getConnInstance();
        //System.out.println("STart after conn..."+new Date());
        //System.out.println("STart..."+System.currentTimeMillis());


        String sql = "SELECT DETAIL_REF_CODE, DESCRIPTION "
                + "From LOOKUP_DETAIL "
                + "where MASTER_REF_CODE=? "
                + "ORDER BY DESCRIPTION ASC ";
        //PreparedStatement ps = conn.prepareStatement(sql); //FRiza 11/2/2013
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        ps.setString(1, Code);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            item = new Item(rs.getString(1), rs.getString(2));
            GeneralCBmodel.addElement(item);

        }

//        conn.close();
        return GeneralCBmodel;
    }

    public DefaultComboBoxModel getAllergy(String alg_str) {
        GeneralCBmodel = new DefaultComboBoxModel();
        try {
            if(alg_str.length() > 0) {
                String data_alg[] = alg_str.split("\\|");
                
                for (int i = 0; i < data_alg.length; i++) {
                    GeneralCBmodel.addElement(data_alg[i]);
                }
            } else {
                GeneralCBmodel.addElement("-");
            }
        } catch (Exception ex) {
            GeneralCBmodel.addElement("-");
            ex.printStackTrace();
        }
        return GeneralCBmodel;
    }
}
