/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Helper.S;
import Helper.Session;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.Func;
import oms.rmi.server.Message;

/**
 *
 * @author End User
 */
public class PIS_getData {
    public static void main(String [] args) {
        try {
            Session.startUp();
            if(Session.getNum_open_db() == 1) {
                //Session.setPrev_stat(NetworkStatus.DoPing(Config.getIpServer(), 2000));
                Session.setPrev_stat(true);
                Session.setCurr_stat(true);
                Session.setCon_x();
            }
            
            try {
                // fire to server port 1099
                ArrayList<String> listOnline = Func.readXML("online");
                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);

                // search for myMessage service
                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method			
                //impl.sayHello("..Friza ");

                //String IC = "891031075331"; //for testing purpose
                String s2 = impl.insertDTO("PMS10013", "DTO|2013-05-28 "
                        + "18:47:36.86|-^Pestilential fever ^-|S01AA110101G5102^GENTAMICIN SULPHATE^S01AA110101G5102|^OINTMENT, EYE^|^^|^At night^||3MG/GM|3MG/GM|^^Day|2 Day|4|^^||^^^^^|As directed|<cr>"
                        + "DTO|2013-05-28 18:47:36.86|-^Pestilential fever ^-|R06AB540000A4101^NAPHAZOLINE HYDROCHLORIDE 100MG,"
                        + "CHLORPHENIRAMINE MALEATE 250MG/100M^R06AB540000A4101|^SPRAY, "
                        + "NASAL^|^^|^Daily^||100MG|100MG|^^Week|3 Week|5|^^||^^^^^|Every second day|<cr>");
                System.out.println(s2);


                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(""
                    + "SELECT * FROM PIS_ORDER_MASTER");
            ResultSet rs = ps.executeQuery();
            S.oln("PIS ORDER MASTER");
            while(rs.next()) {
                S.oln(rs.getString(5));
            }
            S.oln();
            PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(""
                    + "SELECT * FROM PIS_ORDER_DETAIL");
            ResultSet rs2 = ps2.executeQuery();
            S.oln("PIS ORDER DETAIL");
            while(rs2.next()) {
                S.oln(rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PIS_getData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
