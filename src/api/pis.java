/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import DBConnection.DBConnection;
import Helper.S;
import Helper.Session;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;

/**
 *
 * @author ftec
 */
public class pis {
    public pis(){}
    
    private static String dbUrl = "jdbc:hsqldb:file:lib/db/cis;shutdown=true";
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
    
    public String[] getAutogeneratePIS() throws ClassNotFoundException, SQLException
    {
        String[] AutogeneratePIS = new String[1];
        
        ResultSet rs = null;
        
//        if (Session.getPrev_stat()) {
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");

                rs = DBConnection.getImpl().getAUTOGENERATE_ONO();
                        
            } catch (Exception e) {
                //String sql = "SELECT ORDER_NO FROM PIS_ORDER_MASTER";
                String sql = "SELECT ORDER_NO FROM AUTOGENERATE_ONO";
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);

                rs = ps.executeQuery();
                
                e.printStackTrace();
            }
//        } else {
//            //String sql = "SELECT ORDER_NO FROM PIS_ORDER_MASTER";
//            String sql = "SELECT ORDER_NO FROM AUTOGENERATE_ONO";
//            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//
//            rs = ps.executeQuery();
//        }

        String oNo = null;
        
        while(rs.next()){
             {
                 oNo = rs.getString(1);
             }
             java.util.Date d = new java.util.Date();
                SimpleDateFormat simpleDateformat = new SimpleDateFormat("yy");

             int num = Integer.parseInt(oNo.substring(2,oNo.length()));
             num += 1;
             
             String formatted = String.format("%07d",num);
             //System.out.println("formatted :"+formatted);
             AutogeneratePIS[0] = simpleDateformat.format(d)+formatted;         
        }

        return AutogeneratePIS;
    }
    //get auto generate supplier number
    public String[] getAutogenerateSPL() throws ClassNotFoundException, SQLException
    {
        String[] AutogenerateSPL = new String[1];
        String sql = "SELECT SUPPLIER_ID FROM PIS_SUPPLIER";
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();

        String sup_id = null;
        while(rs.next()){
            //for(int i=0;i<1;i++)
             {
                 sup_id = rs.getString(1);
                 //System.out.println("sup_id :"+sup_id);
             }

             int num = Integer.parseInt(sup_id.substring(3,sup_id.length()));
             num += 1;

             String formatted = String.format("%04d",num);
             //System.out.println("Hello SPL"+formatted);
             AutogenerateSPL[0] = formatted;         
        }

        return AutogenerateSPL;
    }
    
     public void addSup(String[] supDetail) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO PIS_SUPPLIER (SUPPLIER_ID, SUPPLIER_NAME, CONTACT_NO) VALUES (?,?,?)";

        //String sql2 = "INSERT INTO PMS_DUTY_ROSTER (DOCTOR_ID, DISCIPLINE) VALUES (?,?)";
        
        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
       // PreparedStatement ps2 = conn1.prepareStatement(sql2);
        
        ps.setString(1, supDetail[0]);
        ps.setString(2, supDetail[1]);
        ps.setString(3, supDetail[2]);
     

        ps.executeUpdate();
        //ps2.executeUpdate();
    }
     
public boolean addPISOrder(String [] order){
   
    try{
        
       String sql = "INSERT INTO AUTOGENERATE_ONO"
                        + "(ORDER_NO)"
                        + "VALUES ('" + order[0] + "')";
       PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            
       String sql2 = "INSERT INTO PIS_ORDER_MASTER "
                        + "(order_no, pmi_no,health_facility_code,episode_code,encounter_date,"
                        + "order_date, order_by,order_from, order_to, "
                        + "hfc_from, hfc_to, spub_no, keyin_by, "
                        + "total_order, status) "
                        + "VALUES ("
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "',"+ "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + ""+ "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "')";
               
       String sql3 = "INSERT INTO PIS_ORDER_DETAIL "
               + "(order_no,drug_item_code,drug_item_desc,"
               + "drug_frequency,drug_route,drug_form,drug_strength,"
               + "drug_dosage,order_oum,duration,order_status ,qty_ordered,"
               + "qty_supplied,supplied_oum,qty_dispensed,dispense_oum,status) "
               + "VALUES ('" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "',"
               + "'" + order[0] + "','" + order[0] + "')";

//       LongRunProcess.check_network2();
//       if (Session.getPrev_stat()) {
           try {
               // fire to server port 1099
//               ArrayList<String> listOnline = Func.readXML("online");
//               Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//               // search for myMessage service
//               Message impl = (Message) myRegistry.lookup("myMessage");
               // call server's method	
               DBConnection.getImpl().insertOrder(order);//tukar & buat kat server

               System.out.println(".....Message Sent....");
           } catch (Exception e) {
               //offline
               System.out.println("Drug Offline!");
               //e.printStackTrace();
           }
           
//       }
//       Session.setPrev_stat(false);
//       Session.setCurr_stat(false);
       
       PreparedStatement ps1 = Session.getCon_x(100).prepareStatement(sql);
       PreparedStatement ps12 = Session.getCon_x(1000).prepareStatement(sql2);
       PreparedStatement ps13 = Session.getCon_x(1000).prepareStatement(sql3);
       
       
        ps1.executeUpdate();
        ps12.executeUpdate();
        ps13.executeUpdate();

       return true;
             
   }catch(SQLException ex){
       ex.printStackTrace();
             
      }
   return false;
        
         
     }

    public void insertD(String[] dispense) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO PIS_DISPENSE_MASTER"
                           + "(order_no, order_date, location_code, arrival_date, dispensed_date, dispensed_by ,"
                           + "filled_by, screened_by, assigned_by, status)"
                           + "VALUES ('" + dispense[0] + "','" + dispense[1] + "','" + dispense[2] + "'"
                           + ",'" + dispense[3] + "','" + dispense[4] + "','" + dispense[5] + "','" + dispense[6] + "'"
                           + ",'" + dispense[7] + "','" + dispense[8] + "','" + dispense[9] + "')";
        
        String sql2 = "INSERT INTO PIS_DISPENSE_DETAIL"
                           + "(order_no,drug_item_code,dispensed_qty,dispensed_uom ,status)"
                           + "VALUES ('" + dispense[10] + "','" + dispense[11] + "','" + dispense[12] + "'"
                           + ",'" + dispense[13] + "','" + dispense[14] + "')";
        
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

                DBConnection.getImpl().insertD(dispense);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                e.printStackTrace();
            }
//        }
//
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
//        Session.setCon_x();

        PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
        S.oln("sql....." + sql);
        ps.executeUpdate();
        
        PreparedStatement ps2 = Session.getCon_x(100).prepareStatement(sql2);
        S.oln("sql....." + sql2);
        ps2.executeUpdate();
    }
    
    
    
}
