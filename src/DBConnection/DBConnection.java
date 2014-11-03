/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBConnection;

import Bean.CIS_Procedure;
import Bean.DrugOrderBean;
import Bean.GCS_Month;
import Bean.GCS_Response;
import Bean.GCS_Scale;
import Bean.PatientBean;
import Bean.PhysicalExamBean;
import Bean.StaffBean;
import Helper.J;
import Helper.S;
import Helper.Session;
import config.Config;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.Func;
import library.NetworkStatus;
import oms.rmi.server.Message;
//import org.hsqldb.DatabaseManager;
/**
 *
 * @author phoebe
 */
public class DBConnection {

    private static String dbURL = "jdbc:hsqldb:file:db/cis;shutdown=true";
    //private static String dbURL = Config.getDbUrlLocal(); 
    //  /dist/lib/userdata
    private static Connection conn = createConnection();
    private static String driverName = "org.hsqldb.jdbcDriver";
    private static String staffID;
    private static String staffPassword;
    private static String staffName;
    private static String pmiNo;
    private static String dispenseStatus;
    private static String patientName;
    private static String drugName;
    private static String mdcCode;
    private static String dosage;
    private static String frequency;
    private static int frequency1;
    private static String status;
    private static String stockQty;
    private static int stockQty1;
    private static String dosageForm;
    private static String duration;
    private static String qtyPerTime;
    private static String route;
    private static String medicFormCode;
    private static int totalQty;
    private static String totalQty1;
    private static String instruction;
    private static String oNo;
    private static String qtydispensed;
    //private static int qtydispensed1;
    //private static boolean oSD;
    private static String oSD;
    private static String productCode;
    private static String productName;
    private static String seq_no;
    private static String strength;
    private static String orderStatus;
    private static String oSM;
    //private static boolean oSM;

    private static String adate;
    private static String odate;
    private static String loccode;
    private static String name;
    private static String doc;
//    private static String oS;//full partial
    
    private static Message impl;
    
    public static void startRMI()
    {
        try {
            // fire to server port 1099
            ArrayList<String> listOnline = Func.readXML("online");
            Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
            
            // search for myMessage service
            impl = (Message) myRegistry.lookup("myMessage");
        } catch (RemoteException ex) {
            //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Message getImpl()
    {
        try {
            impl.sayHello("get rmi");
        } catch (Exception e) {
            try {
                // fire to server port 1099
                ArrayList<String> listOnline = Func.readXML("online");
                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
                
                // search for myMessage service
                impl = (Message) myRegistry.lookup("myMessage");
            } catch (RemoteException ex) {
                //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return impl;
    }

    public static Connection getConnInstance()
    {
        if (conn == null)
        {
            //call createConnection static method
            conn = createConnection();
        }
        return conn;
    }

    public static Connection createConnection()
    {
        try
        {
            ArrayList<String> listOnline = Func.readXML("online");
            System.out.println("\nConnection successful!.......");
            //if (NetworkStatus.DoPing(Config.getIpServer())) { //server
            if(Session.getPrev_stat()) {
            //if(false) {
                return listOnline.get(5).equals("true") ? 
                        DBConnection.online() :
                        DBConnection.offline();
            } else { //localhost
                return DBConnection.offline();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("\nBackup connection!.......");
            try {
                return DBConnection.offline();
            } catch (Exception ex1) {
                System.out.println(ex1);
                J.o("Connection Error", ".. Unable to connect to any connection! ..", 0);
                return null;
            }
        }
    }
    
    public static Connection online() throws ClassNotFoundException, SQLException {
        System.out.println(".......Type of database : Server");
        System.out.println(".......Path of userdata :" + Config.getIpServer());
        System.out.println(".......Username: " + Config.getUserServer() + ", Password: " + Config.getPassServer());
        Class.forName("com.mysql.jdbc.Driver");
        S.oln("Online DB");
        return DriverManager.getConnection(Config.getDbUrlServer(), Config.getUserServer(), Config.getPassServer());
    }
    
    public static Connection offline() throws ClassNotFoundException, SQLException {
        System.out.println(".......Type of database : Local");
        System.out.println(".......Path of userdata :" + dbURL);
        System.out.println(".......Username: SA, Password: - ");
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        S.oln("Offline DB");
        return DriverManager.getConnection(Config.getDbUrlLocal(), Config.getUserLocal(), Config.getPassLocal());
    }

    //friza
    
    public static void shutdown() throws SQLException {

        Statement st = Session.getCon_x(100).createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
        st.execute("SHUTDOWN");
        //conn.close();    // if there are no other open connection
    }
    
     public static void closeConnection() throws SQLException {
     
            try{
                //System.out.println("test 6..finally");
               
                //conn.close(); 
            }catch(Exception e1){
                System.err.print(e1);
            }
            
        } 
       
    
    
    public static StaffBean[] StaffLogin(String id, String password)
    {
        //create a list to store the results
        List list = Collections.synchronizedList(new ArrayList(10));
        PreparedStatement ps = null;
        try
        {
            String sql = "SELECT * from PIS_STAFF WHERE Staff_ID = ? AND Staff_Password = ?";
                      
            ps = Session.getCon_x(100).prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2, password);
            
            ResultSet results = ps.executeQuery();

            while(results.next())
            {

                //create String objects to store different column data of results
                staffID = results.getString("Staff_ID");
                staffPassword = results.getString("Staff_Password");
                staffName = results.getString("Staff_Name");

                //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                list.add(new StaffBean(staffID, staffPassword, staffName));
            }
            //clean the result and data
            results.close();
           
        }
         catch(Exception e)
        {
            
            System.out.println(e);
        }
   
        return(StaffBean[])list.toArray(new StaffBean[list.size()]);
    }

    public static String getStaffNameAndID ( String id)
    {
        try
        {

            String sql="SELECT Staff_Name FROM PIS_STAFF where Staff_ID = ? ";

            //prepare sql query and execute it
            //PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1,id);

            ResultSet results = ps.executeQuery();
//            System.out.println("trace 1.3");
            if(results.next())
            {
                    //create String objects to store data of results

                    staffName = results.getString("Staff_Name");
            }
                //clean the results and data
                results.close();
                ps.close(); 

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return(staffName);
    }
    
    public static String getPMINo(String search, String idtype, int type) {
        String pmino = "";
        try {
            
            String col_type = "PMI_NO=?";
            switch(type) {
                case 1:
                    col_type = "PMI_NO=?";
                    break;
                case 2:
                    col_type = "NEW_IC_NO=?";
                    break;
                case 3:
                    col_type = "OLD_IC_NO=?";
                    break;
                case 4:
                    col_type = "ID_NO=? AND ID_TYPE=?";
                    break;
            }

            String sql = "SELECT PMI_NO FROM PMS_PATIENT_BIODATA WHERE "+col_type;
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, search);
            if (type == 4) {
                ps.setString(2, idtype);
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pmino = rs.getString("PMI_NO");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return pmino;
    }
    
    
    
    public static ArrayList<String> getPatientBiodata(String pmino) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * "
                    + "FROM PMS_PATIENT_BIODATA "
                    + "WHERE PMI_NO = ? ";

            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, pmino);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                //read data get from database to all fields
                data.add(result.getString("Patient_Name"));
                data.add(result.getString("PMI_No"));
                data.add(result.getString("New_IC_No"));
                data.add(result.getString("Sex_Code"));
                data.add(result.getString("Birth_Date"));
                data.add(result.getString("Race_Code"));
                data.add(result.getString("Blood_Type"));
                data.add(result.getString("Allergy_Ind"));
            }
            //clean the results and data
            ps.close();
            result.close();

        } catch (Exception ex) {
            System.out.println("S PMS_P_BIODATA " + ex.getMessage());
        }
        return data;
    }
    
    public static String getProductNameDrug(String ud_mdc_code) {
        String productName = "";
        try {
            String sql = "SELECT * "
                    + "FROM PIS_MDC2 "
                    + "WHERE UD_MDC_CODE = ? ";
            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, ud_mdc_code);
            //ps.setString(2,selectedoNo);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                //read data get from database to all fields

//                productName = result.getString("DRUG_PRODUCT_NAME");
                productName = result.getString("D_TRADE_NAME");

            }
            //clean the results and data
            ps.close();
            result.close();

        } catch (Exception ex) {
            System.out.println("getProductNameDrug: " + ex.getMessage());
        }
        return productName;
    }
    
    public static ArrayList<String> getPisOrderMaster(String pmino, String orderdate) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PIS_ORDER_MASTER WHERE PMI_NO = ? "
                    + "AND ORDER_DATE = ?";
            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, pmino);
            ps.setString(2, orderdate);
            //ps.setString(2,selectedoNo);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                //read data get from database to all fields

                data.add(result.getString("ORDER_BY"));
                data.add(result.getString("ORDER_NO"));
                data.add(result.getString("ORDER_DATE"));
                data.add(result.getString("HFC_FROM"));

            }
            //clean the results and data
            ps.close();
            result.close();

        } catch (Exception ex) {
            System.out.println("S po_master" + ex.getMessage());
        }
        return data;
    }
    
    public static boolean updateDispensedMaster(String orderNo, boolean status) {
        boolean stat = false;
        try {
            String sql = "UPDATE PIS_DISPENSE_MASTER "
                    + "SET STATUS = ? "
                    + "WHERE ORDER_NO = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, orderNo);
            stat = ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static boolean updateOrderMaster(String orderNo, boolean status) {
        boolean stat = false;
        try {
            String sql = "UPDATE PIS_ORDER_MASTER "
                    + "SET STATUS = ? "
                    + "WHERE ORDER_NO = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setBoolean(1, status);
//            ps.setInt(1, status);
            ps.setString(2, orderNo);
            stat = ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static boolean isOrderDetail(String orderNo) {
        boolean stat = true;
        try {
            String sql = "SELECT STATUS "
                    + "FROM PIS_ORDER_DETAIL "
                    + "WHERE ORDER_NO = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, orderNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getInt("STATUS") == 0) {
                    stat = false;
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static boolean updateOrderDetail(int qtyDispensed, String orderNo, String drugCode) {
        boolean stat = false;
        try {
            String sql = "SELECT QTY_ORDERED, QTY_DISPENSED "
                    + "FROM PIS_ORDER_DETAIL "
                    + "WHERE ORDER_NO = ? "
                    + "AND DRUG_ITEM_CODE = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, orderNo);
            ps.setString(2, drugCode);
            ResultSet rs = ps.executeQuery();
            int old_qty_dispensed = 0;
            int old_qty_ordered = 0;
            int drugQty = 0;//add

            if(rs.next()) {
                old_qty_ordered = rs.getInt("QTY_ORDERED");
                old_qty_dispensed = rs.getInt("QTY_DISPENSED");
            }
            old_qty_dispensed += qtyDispensed;
            

          if(old_qty_dispensed == old_qty_ordered) {

            //* * * * * * * * * * * * * * * * * * * * * * * * * * * 
                try {

                    String sql2 = "SELECT * "
                            + "FROM PIS_MDC2 "
                            + "where UD_MDC_Code = ?";
                    PreparedStatement ps3 = Session.getCon_x(1000).prepareStatement(sql2);
                    ps3.setString(1, drugCode);
                    ResultSet rs2 = ps3.executeQuery();
                    while (rs2.next()) {
                        drugQty = rs2.getInt("D_STOCK_QTY");
                        drugQty -= (old_qty_dispensed);
                        S.oln("show data"+drugQty);   
                    }
                        

                        try {
                            String sql3 = "UPDATE PIS_MDC2 "
                                    + "SET D_STOCK_QTY = ? "
                                    + "where UD_MDC_CODE = ?";
                            PreparedStatement ps4 = Session.getCon_x(1000).prepareStatement(sql3);
                            ps4.setInt(1, drugQty);
                            ps4.setString(2, drugCode);
        //                    ps4.setString(2, mdcCode);
                            stat = ps4.execute();
                            S.oln("Success update pis_mdc" + drugQty);
                        } catch (Exception vv) {
                            vv.printStackTrace();
                        }
           

                } catch (Exception qq) {
                    qq.printStackTrace();
                } 
         //* * * * * * * * * * * * * * * * * * * * * * * * * * *
                try{
                    //full
                    String oS = "Full";
                    sql = "UPDATE PIS_ORDER_DETAIL "
                            + "SET QTY_DISPENSED = ? "
                            + ", STATUS = ?, ORDER_STATUS = ? "
                            + "WHERE ORDER_NO = ? AND DRUG_ITEM_CODE = ? ";
                    PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
                    ps2.setInt(1, old_qty_dispensed);
                    ps2.setBoolean(2, true);//true
                    ps2.setString(3, oS);
    //                ps2.setInt(2, 1);//true
                    ps2.setString(4, orderNo);
                    ps2.setString(5, drugCode);
                    stat = ps2.execute();
                    S.oln("success update:old_qty_dispensed >= old_qty_ordered");

                }catch(Exception mm){
                    mm.printStackTrace();
                }


         } else {
        //* * * * * * * * * * * * * * * * * * * * * * * * * * * 
              try {

                  String sql2 = "SELECT * "
                          + "FROM PIS_MDC2 "
                          + "where UD_MDC_Code = ?";
                  PreparedStatement ps3 = Session.getCon_x(1000).prepareStatement(sql2);
                  ps3.setString(1, drugCode);
                  ResultSet rs2 = ps3.executeQuery();
                  while (rs2.next()) {
                      drugQty = rs2.getInt("D_STOCK_QTY");
                      drugQty -= (old_qty_dispensed);
                      S.oln("show data" + drugQty);
                  }


                  try {
                      String sql3 = "UPDATE PIS_MDC2 "
                              + "SET D_STOCK_QTY = ? "
                              + "where UD_MDC_CODE = ?";
                      PreparedStatement ps4 = Session.getCon_x(1000).prepareStatement(sql3);
                      ps4.setInt(1, drugQty);
                      ps4.setString(2, drugCode);
                      //                    ps4.setString(2, mdcCode);
                      stat = ps4.execute();
                      S.oln("Success update pis_mdc" + drugQty);
                  } catch (Exception vv) {
                      vv.printStackTrace();
                  }


              } catch (Exception qq) {
                  qq.printStackTrace();
              }
      //* * * * * * * * * * * * * * * * * * * * * * * * * * *
                    
              //stat false
              try{
                  String oS = "Partial";
                  sql = "UPDATE PIS_ORDER_DETAIL "
                          + "SET QTY_DISPENSED = ? , ORDER_STATUS = ?"
                          + "WHERE ORDER_NO = ? AND DRUG_ITEM_CODE = ? ";
                  PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
                  ps2.setInt(1, old_qty_dispensed);
                  ps2.setString(2, oS);
                  ps2.setString(3, orderNo);
                  ps2.setString(4, drugCode);
                  stat = ps2.execute();
                  
              }catch(Exception nn){
                  nn.printStackTrace();
              }
                    

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return stat;
    }
    
  
    public static boolean insertDispenseDetail(String[] data1, int data2, boolean data3) {
        boolean stat = false;
        try {
            String in2 = "INSERT INTO PIS_DISPENSE_DETAIL "
                    + "(order_no,drug_item_code,dispensed_qty,dispensed_uom ,status)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement ins2 = Session.getCon_x(1000).prepareStatement(in2);
            ins2.setString(1, data1[0]);//oNo
            ins2.setString(2, data1[1]);//product code
            ins2.setInt(3, data2);//drg_qty
            ins2.setString(4, data1[2]);//disp_uom
            ins2.setBoolean(5, data3);//true
            stat = ins2.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stat;
    }
   
    
    public static boolean insertDispenseMaster(String [] data1, java.sql.Timestamp data2, boolean data3) {
        boolean stat = false;
        try {
            String in1 = "INSERT INTO PIS_DISPENSE_MASTER "
                    + "(order_no, order_date, location_code, "
                    + "arrival_date, dispensed_date, dispensed_by, "
                    + "filled_by, screened_by, assigned_by, status)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ins1 = Session.getCon_x(100).prepareStatement(in1);
            ins1.setString(1, data1[0]);
            ins1.setString(2, data1[1]);
            ins1.setString(3, data1[2]);//loc code
            ins1.setString(4, data1[3]);
            ins1.setTimestamp(5, data2);
            ins1.setString(6, data1[4]);
            ins1.setString(7, data1[5]);
            ins1.setString(8, data1[6]);
            ins1.setString(9, data1[7]);
            ins1.setBoolean(10, data3);
            stat = ins1.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static ArrayList<String> getOrderDetail(String orderNo, String drugCode) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PIS_ORDER_DETAIL "
                    + "WHERE ORDER_NO = ? "
                    + "AND DRUG_ITEM_CODE = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, orderNo);
            ps.setString(2, drugCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for (int i = 1; i <= 17; i++) {
                    data.add(rs.getString(i));
                    S.oln("getOrderDetail");
//                    S.oln(i);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<String> getDispenseMaster(String orderNo) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * "
                    + "FROM PIS_DISPENSE_MASTER "
                    + "WHERE ORDER_NO = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, orderNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                for(int i = 1; i <= 10; i++) {
                    data.add(rs.getString(i));
                    S.oln("getDispenseMaster");
//                    S.oln(i);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<ArrayList<String>> getPatientInQueue(int stat, String pmi_no, String order_no) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        try {

            Date date = new Date(new java.util.Date().getTime());
            //insert data into EHR_Central table
            String tamb = "";
            if (stat != 1) {
                switch (stat) {
                    case 2: {
                        tamb = "AND pom.pmi_no = ? ";
                    }
                    break;
                    case 3: {
                        tamb = "AND pom.pmi_no = ? AND pom.order_no = ? ";
                    }
                    break;
                }
            }
            String ayat = "SELECT * "
                    + "FROM pis_order_master pom, "
                    //+ "pis_order_detail pod, "
                    + "PMS_PATIENT_BIODATA ppb "
                    //+ "WHERE pom.ORDER_NO = pod.order_no "
                    + "WHERE DAYOFYEAR(ORDER_DATE) = DAYOFYEAR(CURDATE()) "
                    + "AND ppb.PMI_NO = pom.pmi_no "
                    + "AND pom.STATUS <> 1 "
                    + tamb
                    + "LIMIT 30";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(ayat);
            if (stat != 1) {
                switch (stat) {
                    case 2: {
                        ps.setString(1, pmi_no);
                    }
                    break;
                    case 3: {
                        ps.setString(1, pmi_no);
                        ps.setString(2, order_no);
                    }
                    break;
                }
            }
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i < 30 && rs.next(); i++) {
                ArrayList<String> d = new ArrayList<String>();
                for (int j = 0; j < 50; j++) {
                    d.add(rs.getString(j + 1));
                }
                data.add(d);
            }
            System.out.println("Get Order Master");
        } catch (Exception ex) {
            System.out.println("FAILED Get Order Master");
            ex.printStackTrace();
        }
        return data;
    }
    public static ArrayList<ArrayList<String>> getPatientInQueueOff(int stat, String pmi_no, String order_no) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        try {

            Date date = new Date(new java.util.Date().getTime());
            //insert data into EHR_Central table
            String tamb = "";
            if (stat != 1) {
                switch (stat) {
                    case 2: {
                        tamb = "AND pom.pmi_no = ? ";
                    }
                    break;
                    case 3: {
                        tamb = "AND pom.pmi_no = ? AND pom.order_no = ? ";
                    }
                    break;
                }
            }
            String ayat = "SELECT * "
                    + "FROM pis_order_master pom, "
                    //+ "pis_order_detail pod, "
                    + "PMS_PATIENT_BIODATA ppb "
                    //+ "WHERE pom.ORDER_NO = pod.order_no "
                    + "WHERE DAYOFYEAR(ORDER_DATE) = DAYOFYEAR(CURDATE()) "
                    + "AND ppb.PMI_NO = pom.pmi_no "
                    + "AND pom.STATUS <> 1 "
                    + tamb
                    + "ORDER BY ORDER_DATE DESC "
                    + "LIMIT 30";
            System.out.println("ayat: "+ayat);
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(ayat);
            if (stat != 1) {
                switch (stat) {
                    case 2: {
                        ps.setString(1, pmi_no);
                    }
                    break;
                    case 3: {
                        ps.setString(1, pmi_no);
                        ps.setString(2, order_no);
                    }
                    break;
                }
            }
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i < 30 && rs.next(); i++) {
                ArrayList<String> d = new ArrayList<String>();
                for (int j = 0; j < 50; j++) {
                    d.add(rs.getString(j + 1));
                }
                data.add(d);
            }
            System.out.println("Get Order Master off");
        } catch (Exception ex) {
            System.out.println("FAILED Get Order Master off");
            ex.printStackTrace();
        }
        return data;
    }
    
    public static PatientBean[] patientInQueue()
    {
        //panggil data utk display pd list of order : current day
        List list = Collections.synchronizedList(new ArrayList(25));
     
        try
            {
                Boolean status1 = false;
                
//              String sql = "SELECT * FROM PIS_ORDER_MASTER WHERE STATUS = ? LIMIT 25";//PMI_NO, ORDER_DATE, ENCOUNTER_DATE,HFC, STATUS
                String sql = "SELECT * "
                        + "FROM PIS_ORDER_MASTER "
                        + "WHERE DAYOFYEAR(ORDER_DATE) =  DAYOFYEAR(CURDATE()) "
                        + "AND STATUS = ? LIMIT 25";
                
                //prepare the sql query and execute it
                PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
                ps.setBoolean(1, status1);
                ResultSet results = ps.executeQuery();//SQL
                
                String sql2 = "SELECT * FROM PIS_ORDER_DETAIL WHERE STATUS = ? LIMIT 25";
                PreparedStatement ps2 = Session.getCon_x(100).prepareStatement(sql2);
                ps2.setBoolean(1, status1);
                ResultSet results2 = ps2.executeQuery();//SQL2

                    //show results for SQL : PIS_ORDER_MASTER
                    while(results.next())
                    {
                        //create String objects to store different column data of results
                        oNo = results.getString("Order_No");
                        pmiNo = results.getString("Pmi_No");
                        odate = results.getString("Order_Date");
                        loccode = results.getString ("HEALTH_FACILITY_CODE");
                        adate = results.getString("Encounter_Date");
                        doc = results.getString("ORDER_BY");
                        oSM = results.getString("Status");

                        if(oSM.equals("FALSE"))
                        {
                            oSM = "Waiting Dispense";
                        }
                        else
                        {
                            oSM = "Dispense";
                        }

                        //get patient name from table PMS_PATIENT_BIODATA
                        try
                        {
                             String sql1="SELECT PATIENT_NAME FROM PMS_PATIENT_BIODATA WHERE PMI_NO = ? LIMIT 1";

                            //prepare the sql query and execute it
                            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                            ps1.setString(1, pmiNo);

                            ResultSet results1 = ps1.executeQuery();

                            if(results1.next())
                            {
                                //create String objects to store different column data of results
                                patientName = results1.getString("PATIENT_NAME");
                            }
                        }
                        catch (Exception ex)
                        {
                                System.out.println("dbc 0 display 25 a "+ex);
                        }
                        //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                        list.add(new PatientBean(pmiNo, patientName, odate, loccode, adate, doc));
                    }
            
                    //show result for sql2 : PIS_ORDER_DETAIL
                    while(results2.next())
                    {
                        //create String objects to store different column data of results
                        oNo = results.getString("Order_No");
                        pmiNo = results.getString("Pmi_No");
                        odate = results.getString("Order_Date");
                        loccode = results.getString ("HEALTH_FACILITY_CODE");
                        adate = results.getString("Encounter_Date");
                        oSD = results.getString("Status");
                        orderStatus = results.getString("ORDER_STATUS");//New/Partial

                        if(oSD.equals("FALSE"))
                        {
                            //oSD = "Waiting Dispense/Hold";
                            orderStatus = "New/Partial";
                        }
                        else
                        {
                            orderStatus = "Full";
                        }

                        //get patient name from table PMS_PATIENT_BIODATA
                        try
                        {
                             String sql1="SELECT PATIENT_NAME FROM PMS_PATIENT_BIODATA WHERE PMI_NO = ? LIMIT 1";

                            //prepare the sql query and execute it
                            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                            ps1.setString(1, pmiNo);

                            ResultSet results1 = ps1.executeQuery();

                            if(results1.next())
                            {
                                //create String objects to store different column data of results
                                patientName = results1.getString("PATIENT_NAME");
                            }

                        }
                        catch (Exception ex)
                        {
                                System.out.println("dbc 0 display 25 b "+ex);
                        }

                        //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                        list.add(new PatientBean(pmiNo, patientName, odate, loccode, adate, doc));
                    }
        }
        catch (Exception ex)
        {
                System.out.println(ex);
        }      

        //transfer the BuildingInfoBean objects in the list to a BuildingInfoBean array
        return (PatientBean[])list.toArray(new PatientBean[list.size()]);
    }
//PLS CHANGE CODING BELOW - 13 MAY 2013
    public static DrugOrderBean[] drugOrder(String formCode)
    {
        //create a list to store the results
        List list = Collections.synchronizedList(new ArrayList(50));
        //get data from table PMS_DRUG_DISPENSE
        //update : 9,may2013 change into PIS_ORDER_MASTER && PIS_ORDER_DETAIL
        
        boolean s = false;
        try
        {
            J.o("", pmiNo+"|"+formCode, 1);
            //boolean s = false; 
            String sql =  "SELECT * "
                        + "FROM pis_order_detail,pis_order_master "
                        + "WHERE pis_order_master.order_no = pis_order_detail.order_no "
                        + "AND pis_order_master.pmi_no = ? "
                        + "AND pis_order_master.status = ? "
                        + "AND pis_order_detail.status = ? ";
//            String sql =  "SELECT * "
//                        + "FROM pis_order_detail,pis_order_master "
//                        + "WHERE pis_order_master.order_no = pis_order_detail.order_no "
//                        + "AND pis_order_master.pmi_no = ? "
//                        + "AND pis_order_detail.order_no = ?"
//                        + "AND pis_order_master.status = ? "
//                        + "AND pis_order_detail.status = ? ";
            
            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1,pmiNo);
            //ps.setString(2,pmiNo);
            ps.setString (2,formCode);
            ps.setBoolean(3,s);
            ps.setBoolean(4,s);
            ResultSet result = ps.executeQuery();

            J.o("", "before while", 1);
            while(result.next())
            {
                J.o("", "in", 1);
               //read data get from database to all fields
               oNo = result.getString("ORDER_NO");
               productCode = result.getString("DRUG_ITEM_CODE");
               productName = result.getString("DRUG_ITEM_DESC");
               frequency = result.getString("DRUG_FREQUENCY");
               route = result.getString ("DRUG_ROUTE");
               dosageForm = result.getString("DRUG_FORM");
               strength = result.getString("DRUG_STRENGTH");
               dosage = result.getString("DRUG_DOSAGE");
               instruction = result.getString("ORDER_OUM");
               
               duration = result.getString("Duration");
               //Double duration1 = Double.parseDouble(duration);
               int duration1 = Integer.parseInt(duration);
               
               int qtyPerTime1 = result.getInt("QTY_ORDERED");
               qtyPerTime = Integer.toString(qtyPerTime1);
               
               totalQty = result.getInt("QTY_SUPPLIED");
               //totalQty1 = Integer.toString(totalQty);
               
               qtydispensed = result.getString("QTY_DISPENSED");
               //int qtydispensed1= Integer.parseInt(qtydispensed);
               
               oSD = result.getString("STATUS");//true false
               orderStatus = result.getString("ORDER_STATUS");//new partial full
               
                //identify frequency
                if((frequency.equals("In the morning"))||(frequency.equals("At night"))||(frequency.equals("Daily")))
                {
                    frequency1 = 1;
                }
                else if(frequency.equals("Twice a day"))
                {
                    frequency1 = 2;
                }
                else if((frequency.equals("3 times a day"))||(frequency.equals("8 hourly")))
                {
                    frequency1 = 3;
                }
                else if((frequency.equals("4 times a day"))||(frequency.equals("6 hourly")))
                {
                    frequency1 = 4;
                }
                else
                {
                    frequency1 = 6;
                }

               //calculate total quantity
               totalQty = frequency1*qtyPerTime1*duration1;
               totalQty1 = Double.toString(totalQty);
               
               //get data from table PIS_MDC
               try
               {
                    String sql1="SELECT * "
                            + "FROM PIS_MDC2 "
                            + "WHERE UD_MDC_Code = ?";

                    //prepare the sql query and execute it
                    PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                    ps1.setString(1,mdcCode);
                    ResultSet result1 = ps1.executeQuery();

                    while(result1.next())
                    {
                       //read data get from database to all fields
//                       productName = result1.getString("Drug_Product_Name");
//                       route = result1.getString("Def_Route_Code");
                       productName = result1.getString("D_TRADE_NAME");
                       route = result1.getString("D_ROUTE_CODE");
                    }
                }catch(Exception ex){
                    System.out.println("dbc 1 "+ex.getMessage());
                }

               //get data from table PMS_MDC_PHARMACY
               try
               {
                    //String sql1="SELECT MDC_Stock_Qty FROM PIS_MDC_PHARMACY WHERE UD_MDC_Code = ?";
                    String sql1="SELECT * FROM PIS_MDC2 WHERE UD_MDC_Code = ?";//11012013

                    //prepare the sql query and execute it
                    PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                    ps1.setString(1,mdcCode);
                    ResultSet result1 = ps1.executeQuery();

                    while(result1.next())
                    {
                       //read data get from database to all fields
                       stockQty1 = result1.getInt("D_STOCK_QTY");

                       if(stockQty1 != 0)
                       {
                            stockQty = "Available";
                       }
                       else
                       {
                           stockQty = "Pending";
                           //stockQty = "Not Available";
                       }
                    }
                }catch(Exception ex){
                    System.out.println("dbc 2 "+ex.getMessage());
                }
               //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
               list.add(new DrugOrderBean(productCode, productName, frequency, route, dosageForm, strength, dosage, instruction, duration, qtyPerTime, totalQty1, qtydispensed,orderStatus));
            
            }

                //clean the results and data
                ps.close();
                result.close();
            }catch(Exception ex){
                System.out.println("close 1"+ex.getMessage());
            }finally{
            
        try{
            //conn.close();
        }catch(Exception e1){
            System.err.println("close 2"+e1);

        }

    }

        //transfer the BuildingInfoBean objects in the list to a BuildingInfoBean array
        return (DrugOrderBean[])list.toArray(new DrugOrderBean[list.size()]);
    }
    
    public static ArrayList<ArrayList<String>> getDrugOrderDetail(String orderNo) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            try {
                String sql = "SELECT * FROM PIS_ORDER_DETAIL WHERE ORDER_NO = ? "
                        + "AND STATUS = 0 ";

                //prepare the sql query and execute it
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, orderNo);
                ResultSet result = ps.executeQuery();

                while (result.next()) {
                    //read data get from database to all fields
                    
                    ArrayList<String> d = new ArrayList<String>();
                    
                    oNo = result.getString("ORDER_NO");
                    productCode = result.getString("DRUG_ITEM_CODE");
                    productName = result.getString("DRUG_ITEM_DESC");
                    frequency = result.getString("DRUG_FREQUENCY");
                    route = result.getString("DRUG_ROUTE");
                    dosageForm = result.getString("DRUG_FORM");
                    strength = result.getString("DRUG_STRENGTH");
                    dosage = result.getString("DRUG_DOSAGE");
                    instruction = result.getString("ORDER_OUM");

                    duration = result.getString("Duration");
                    //Double duration1 = Double.parseDouble(duration);
                    int duration1 = Integer.parseInt(duration);

                    int qtyPerTime1 = result.getInt("QTY_ORDERED");
                    qtyPerTime = Integer.toString(qtyPerTime1);

                    totalQty = result.getInt("QTY_SUPPLIED");
                    //totalQty1 = Integer.toString(totalQty);

                    qtydispensed = result.getString("QTY_DISPENSED");
                    //int qtydispensed1= Integer.parseInt(qtydispensed);

                    oSD = result.getString("STATUS");//true false
                    orderStatus = result.getString("ORDER_STATUS");//new partial full

//                    //identify frequency
//                    if ((frequency.equals("In the morning")) || (frequency.equals("At night")) || (frequency.equals("Daily"))) {
//                        frequency1 = 1;
//                    } else if (frequency.equals("Twice a day")) {
//                        frequency1 = 2;
//                    } else if ((frequency.equals("3 times a day")) || (frequency.equals("8 hourly"))) {
//                        frequency1 = 3;
//                    } else if ((frequency.equals("4 times a day")) || (frequency.equals("6 hourly"))) {
//                        frequency1 = 4;
//                    } else {
//                        frequency1 = 6;
//                    }
//
//                    //calculate total quantity
//                    totalQty = frequency1 * qtyPerTime1 * duration1;
                    totalQty1 = Double.toString(totalQty);
                    
                    String generic_name = "";
                    
                    try {
//                        String sql1 = "SELECT Drug_Product_Name, Def_Route_Code "
//                                + "FROM PIS_MDC "
//                                + "WHERE UD_MDC_Code = ?";
                        String sql1 = "SELECT * "
                                + "FROM PIS_MDC2 "
                                + "WHERE UD_MDC_CODE = ? ";

                        //prepare the sql query and execute it
                        PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                        ps1.setString(1, mdcCode);
                        ResultSet result1 = ps1.executeQuery();

                        if (result1.next()) {
                            //read data get from database to all fields
//                            productName = result1.getString("Drug_Product_Name");
//                            route = result1.getString("Def_Route_Code");
//                            stockQty1 = result1.getInt("STOCK_QTY");
                            productName = result1.getString("D_TRADE_NAME");
                            route = result1.getString("D_ROUTE_CODE");
                            stockQty1 = result1.getInt("D_STOCK_QTY");
                            generic_name = result1.getString("D_GNR_NAME");

                            if (stockQty1 != 0) {
                                stockQty = "Available";
                            } else {
                                stockQty = "Pending";
                                //stockQty = "Not Available";
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("dbc 1 " + ex.getMessage());
                    }
                    
                    d.add(productCode); //0
                    d.add(productName); //1
                    d.add(frequency); //2
                    d.add(route); //3
                    d.add(dosageForm); //4
                    d.add(strength); //5
                    d.add(dosage); //6
                    d.add(instruction); //7
                    d.add(duration); //8
                    d.add(qtyPerTime); //9
                    d.add(totalQty1); //10
                    d.add(qtydispensed); //11
                    d.add(orderStatus); //12
                    d.add(stockQty1+""); //13
                    d.add(stockQty+""); //14
                    
                    d.add(generic_name); //15
                    
                    data.add(d);
                }
                //clean the results and data
                ps.close();
                result.close();

            } catch (Exception ex) {
                System.out.println("S po_master" + ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static DrugOrderBean[] callOrder(String formCode) {
        //get data from list & display to dispense tab
        List list = Collections.synchronizedList(new ArrayList(50));
        //get data from table PMS_DRUG_DISPENSE
        //update : 9,may2013 change into PIS_ORDER_MASTER && PIS_ORDER_DETAIL

        boolean s = false;
        try {
            J.o("", pmiNo + "|" + formCode, 1);
            //boolean s = false; 
            String sql =  "SELECT * "
                        + "FROM pis_order_detail,pis_order_master "
                        + "WHERE pis_order_master.order_no = pis_order_detail.order_no "
                        + "AND pis_order_master.pmi_no = ? "
                        + "AND pis_order_master.status = ? "
                        + "AND pis_order_detail.status = ? ";

            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
//            ps.setString(1, pmiNo);
            //ps.setString(2,pmiNo);
            ps.setString(1, formCode);
            ps.setBoolean(2,s);
            ps.setBoolean(3,s);
            ResultSet result = ps.executeQuery();

            J.o("", "before while", 1);
            while (result.next()) {
                J.o("", "in", 1);
                //read data get from database to all fields
                oNo = result.getString("ORDER_NO");
                productCode = result.getString("DRUG_ITEM_CODE");
                productName = result.getString("DRUG_ITEM_DESC");
                frequency = result.getString("DRUG_FREQUENCY");
                route = result.getString("DRUG_ROUTE");
                dosageForm = result.getString("DRUG_FORM");
                strength = result.getString("DRUG_STRENGTH");
                dosage = result.getString("DRUG_DOSAGE");
                instruction = result.getString("ORDER_OUM");

                duration = result.getString("Duration");
                //Double duration1 = Double.parseDouble(duration);
                int duration1 = Integer.parseInt(duration);

                int qtyPerTime1 = result.getInt("QTY_ORDERED");
                qtyPerTime = Integer.toString(qtyPerTime1);

                totalQty = result.getInt("QTY_SUPPLIED");
                //totalQty1 = Integer.toString(totalQty);

                qtydispensed = result.getString("QTY_DISPENSED");
                //int qtydispensed1= Integer.parseInt(qtydispensed);

                oSD = result.getString("STATUS");//true false
                orderStatus = result.getString("ORDER_STATUS");//new partial full

                //identify frequency
                if ((frequency.equals("In the morning")) || (frequency.equals("At night")) || (frequency.equals("Daily"))) {
                    frequency1 = 1;
                } else if (frequency.equals("Twice a day")) {
                    frequency1 = 2;
                } else if ((frequency.equals("3 times a day")) || (frequency.equals("8 hourly"))) {
                    frequency1 = 3;
                } else if ((frequency.equals("4 times a day")) || (frequency.equals("6 hourly"))) {
                    frequency1 = 4;
                } else {
                    frequency1 = 6;
                }

                //calculate total quantity
                totalQty = frequency1 * qtyPerTime1 * duration1;
                totalQty1 = Double.toString(totalQty);

                //get data from table PIS_MDC
                try {
                    String sql1 = "SELECT * "
                            + "FROM PIS_MDC2 "
                            + "WHERE UD_MDC_Code = ?";

                    //prepare the sql query and execute it
                    PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                    ps1.setString(1, mdcCode);
                    ResultSet result1 = ps1.executeQuery();

                    while (result1.next()) {
                        //read data get from database to all fields
//                        productName = result1.getString("Drug_Product_Name");
//                        route = result1.getString("Def_Route_Code");
                        productName = result1.getString("D_TRADE_NAME");
                        route = result1.getString("D_ROUTE_CODE");
                    }
                } catch (Exception ex) {
                    System.out.println("dbc 1 " + ex.getMessage());
                }

                //get data from table PMS_MDC_PHARMACY
                try {
                    //String sql1="SELECT MDC_Stock_Qty FROM PIS_MDC_PHARMACY WHERE UD_MDC_Code = ?";
                    String sql1 = "SELECT * FROM PIS_MDC2 WHERE UD_MDC_Code = ?";//11012013

                    //prepare the sql query and execute it
                    PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                    ps1.setString(1, mdcCode);
                    ResultSet result1 = ps1.executeQuery();

                    while (result1.next()) {
                        //read data get from database to all fields
                        stockQty1 = result1.getInt("D_STOCK_QTY");

                        if (stockQty1 != 0) {
                            stockQty = "Available";
                        } else {
                            stockQty = "Pending";
                            //stockQty = "Not Available";
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("dbc 2 " + ex.getMessage());
                }
                //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                list.add(new DrugOrderBean(productCode, productName, frequency, route, dosageForm, strength, dosage, instruction, duration, qtyPerTime, totalQty1, qtydispensed, orderStatus));

            }

            //clean the results and data
            ps.close();
            result.close();
        } catch (Exception ex) {
            System.out.println("close 1" + ex.getMessage());
        } finally {

            try {
                //conn.close();
            } catch (Exception e1) {
                System.err.println("close 2" + e1);

            }

        }

        //transfer the BuildingInfoBean objects in the list to a BuildingInfoBean array
        return (DrugOrderBean[]) list.toArray(new DrugOrderBean[list.size()]);
    }
    
    public static int getNewPmi() {
        int nilai = 1;
        try {
            String sql = "SELECT pmi_i "
                    + "FROM autogenerated_pmi "
                    + "ORDER BY pmi_i DESC";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nilai = rs.getInt("pmi_i");
                sql = "INSERT INTO autogenerated_pmi(pmi_i) "
                        + "VALUES(?)";
                PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
                ps2.setInt(1, nilai + 1);
                ps2.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nilai;
    }
    
    public static ArrayList<String> getLabRequest(String parent) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT lrc_name "
                    + "FROM lab_request_child "
                    + "WHERE lrp_name = ? "
                    + "LIMIT 7 ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, parent);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                data.add(rs.getString("lrc_name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<ArrayList<String>> getListOfStaffs(String user_id) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            String sql = "SELECT * "
                    + "FROM ADM_USER AU, ADM_USER_ACCESS AUA "
                    + "WHERE AU.USER_ID = AUA.USER_ID "
                    + "AND AU.USER_ID <> ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> d = new ArrayList<String>();
                d.add(rs.getString("USER_ID"));
                d.add(rs.getString("USER_NAME"));
                d.add(rs.getString("ROLE_CODE"));
                d.add(rs.getString("HEALTH_FACILITY_CODE"));
                d.add(rs.getString("DISCIPLINE_CODE"));
                d.add(rs.getString("SUBDISCIPLINE_CODE"));
                data.add(d);
            }
        } catch (Exception ex) {
            data = new ArrayList<ArrayList<String>>();
            ex.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<ArrayList<String>> getStaffs(String user_id) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            String sql = "SELECT * "
                    + "FROM ADM_USER AU, ADM_USER_ACCESS AUA "
                    + "WHERE AU.USER_ID = AUA.USER_ID ";
            if(!user_id.equals("")) {
                sql += "AND AU.USER_ID = ? ";
            }
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            if(!user_id.equals("")) {
                ps.setString(1, user_id);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> d = new ArrayList<String>();
                for (int i = 0; i < 22; i++) {
                    d.add(rs.getString(i+1));
                }
                data.add(d);
            }
        } catch (Exception ex) {
            data = new ArrayList<ArrayList<String>>();
            ex.printStackTrace();
        }
        return data;
    }
    
    public static boolean addStaff(String data1[], String data2[]) {
        boolean stat = false;
        int num_cols1 = data1.length;
        int num_cols2 = data2.length;
        try {
            String sql1 = "INSERT INTO ADM_USER VALUES(?";
            for (int i = 1; i < num_cols1; i++) {
                sql1 += ",?";
            }
            sql1 += ")";
            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
            for (int i = 0; i < num_cols1; i++) {
                ps1.setString(i+1, data1[i]);
            }
            ps1.execute();
            
            String sql2 = "INSERT INTO ADM_USER_ACCESS VALUES(?";
            for (int i = 1; i < num_cols2; i++) {
                sql2 += ",?";
            }
            sql2 += ")";
            PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
            for (int i = 0; i < num_cols2; i++) {
                ps2.setString(i+1, data2[i]);
            }
            ps2.execute();
            stat = true;
        } catch (Exception ex) {
            stat = false;
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static boolean deleteStaff(String user_id) {
        boolean stat = false;
        try {
            String sql1 = "DELETE FROM ADM_USER "
                    + "WHERE USER_ID = ? ";
            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
            ps1.setString(1, user_id);
            ps1.execute();
            String sql2 = "DELETE FROM ADM_USER_ACCESS "
                    + "WHERE USER_ID = ? ";
            PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
            ps2.setString(1, user_id);
            ps2.execute();
            stat = true;
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static boolean isStaffs(String user_id) {
        boolean stat = false;
        try {
            String sql = "SELECT * "
                    + "FROM ADM_USER AU, ADM_USER_ACCESS AUA "
                    + "WHERE AU.USER_ID = AUA.USER_ID "
                    + "AND AU.USER_ID = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                stat = true;
            } else {
                stat = false;
            }
        } catch (Exception ex) {
            stat = false;
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static boolean updateStaff(String user_id, String cols1[], String data1[], String cols2[], String data2[]) {
        boolean stat = false;
        int num_cols1 = data1.length;
        int num_cols2 = data2.length;
        try {
            String sql1 = "UPDATE ADM_USER SET ";
            for (int i = 0; i < cols1.length - 1; i++) {
                sql1 += cols1[i] + " = ?, ";
            }
            sql1 += cols1[cols1.length - 1] + " = ? " + "WHERE USER_ID = ? ";
            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
            for (int i = 0; i < data1.length; i++) {
                ps1.setString(i+1, data1[i]);
            }
            ps1.setString(data1.length+1, user_id);
            ps1.execute();
            
            String sql2 = "UPDATE ADM_USER_ACCESS SET ";
            for (int i = 0; i < cols2.length - 1; i++) {
                sql2 += cols2[i] + " = ?, ";
            }
            sql2 += cols2[cols2.length - 1] + " = ? " + "WHERE USER_ID = ? ";
            PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
            for (int i = 0; i < data2.length; i++) {
                ps2.setString(i+1, data2[i]);
            }
            ps2.setString(data2.length+1, user_id);
            ps2.execute();
            
            stat = true;
        } catch (Exception ex) {
            stat = false;
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static ArrayList<String> getStaffLogin(String user_id, String password) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            String sql = "SELECT * "
                    + "FROM ADM_USER AU, ADM_USER_ACCESS AUA "
                    + "WHERE AU.USER_ID = AUA.USER_ID "
                    + "AND AU.USER_ID = ? "
                    + "AND AU.PASSWORD = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, user_id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                for (int i = 0; i < 22; i++) {
                    data.add(rs.getString(i + 1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public static boolean copyDataStaff(String user_id, ArrayList<String> data) {
        boolean stat = false;
        int num_cols1 = 17;
        int num_cols2 = 5;
        try {
            String sql1 = "DELETE FROM ADM_USER "
                    + "WHERE USER_ID = ? ";
            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
            ps1.setString(1, user_id);
            ps1.execute();
            String sql2 = "DELETE FROM ADM_USER_ACCESS "
                    + "WHERE USER_ID = ? ";
            PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
            ps2.setString(1, user_id);
            ps2.execute();
            
            String sql3 = "INSERT INTO ADM_USER VALUES(?";
            for (int i = 1; i < num_cols1; i++) {
                sql3 += ",?";
            }
            sql3 += ")";
            PreparedStatement ps3 = Session.getCon_x(1000).prepareStatement(sql3);
            for (int i = 0; i < num_cols1; i++) {
                ps3.setString(i+1, data.get(i));
            }
            ps3.execute();
            String sql4 = "INSERT INTO ADM_USER_ACCESS VALUES(?";
            for (int i = 1; i < num_cols2; i++) {
                sql4 += ",?";
            }
            sql4 += ")";
            PreparedStatement ps4 = Session.getCon_x(1000).prepareStatement(sql4);
            for (int i = 0 + num_cols1, j = 0; i < num_cols2 + num_cols1; i++, j++) {
                ps4.setString(j+1, data.get(i));
            }
            ps4.execute();
            
            stat = true;
        } catch (Exception ex) {
            stat = false;
            ex.printStackTrace();
        }
        return stat;
    }
    
    public static boolean updatePmsPatientBiodata(String pmino, ArrayList<String> column, ArrayList<String> data) {
        boolean stat = false;
        try {
            String sql = "UPDATE pms_patient_biodata SET ";
            for (int i = 0; i < column.size(); i++) {
                sql += column.get(i) + " = ?";
                if (i == column.size()-1) {
                    sql += " ";
                } else {
                    sql += ", ";
                }
            }
            sql += "WHERE pmi_no = ? ";
            System.out.println("sql: "+sql);
            System.out.println("col: "+column);
            System.out.println("dat: "+data);
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            for (int i = 0; i < column.size(); i++) {
                ps.setString(i+1, data.get(i));
            }
            ps.setString(column.size()+1, pmino);
            ps.execute();
            stat = true;
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static boolean changePassword(String userid, String pwd) {
        boolean stat = false;
        try {
            String sql = "UPDATE adm_user SET password = ? WHERE user_id = ? ";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, pwd);
            ps.setString(2, userid);
            ps.execute();
            stat = true;
        } catch (Exception e) {
            e.printStackTrace();
            stat = false;
        }
        return stat;
    }
    
    public static String getPassword(String icno, String userid) {
        String pwd = "-";
        try {
            String sql = "SELECT * "
                    + "FROM adm_user "
                    + "WHERE new_icno = ? "
                    + "AND user_id = ? ";
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.setString(1, icno);
            ps.setString(2, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pwd = rs.getString("password");
            }
        } catch (Exception e) {
            pwd = "-";
            e.printStackTrace();
        }
        return pwd;
    }
    
    public static boolean isUpdatePatientBiodata(String[] Biodata) {
        boolean stat = false;
        try {
            //Connection conn = DbConnection.doConnection();
            String sql = "UPDATE PMS_PATIENT_BIODATA SET PMI_NO = '" + Biodata[0] + "',PMI_NO_TEMP = '" + Biodata[1] + "',PATIENT_NAME = '" + Biodata[2] + "',TITLE_CODE = '" + Biodata[3] + "',NEW_IC_NO = '" + Biodata[4] + "',OLD_IC_NO = '" + Biodata[5] + "',ID_TYPE = '" + Biodata[6] + "',ID_NO = '" + Biodata[7] + "',ELIGIBILITY_CATEGORY_CODE = '" + Biodata[8] + "',ELIGIBILITY_TYPE_CODE = '" + Biodata[9] + "',BIRTH_DATE = '" + Biodata[10] + "',SEX_CODE = '" + Biodata[11] + "',MARITAL_STATUS_CODE = '" + Biodata[12] + "',RACE_CODE = '" + Biodata[13] + "',NATIONALITY = '" + Biodata[14] + "',RELIGION_CODE = '" + Biodata[15] + "',BLOOD_TYPE = '" + Biodata[16] + "',BLOOD_RHESUS_CODE = '" + Biodata[17] + "',ALLERGY_IND = '" + Biodata[18] + "',CHRONIC_DISEASE_IND = '" + Biodata[19] + "',ORGAN_DONOR_IND = '" + Biodata[20] + "',HOME_ADDRESS = '" + Biodata[21] + "',HOME_DISTRICT_CODE = '" + Biodata[22] + "',HOME_TOWN_CODE = '" + Biodata[23] + "',HOME_POSTCODE = '" + Biodata[24] + "',HOME_STATE_CODE = '" + Biodata[25] + "',HOME_COUNTRY_CODE = '" + Biodata[26] + "',HOME_PHONE = '" + Biodata[27] + "',POSTAL_ADDRESS = '" + Biodata[28] + "',POSTAL_DISTRICT_CODE = '" + Biodata[29] + "',POSTAL_TOWN_CODE = '" + Biodata[30] + "',POSTAL_POSTCODE = '" + Biodata[31] + "',POSTAL_STATE_CODE = '" + Biodata[32] + "',POSTAL_COUNTRY_CODE = '" + Biodata[33] + "',MOBILE_PHONE = '" + Biodata[34] + "' WHERE PMI_NO = '" + Biodata[0] + "'";
            S.oln("isUpdate:"+sql);
            PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
            ps.executeUpdate();
            stat = true;
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static ArrayList<String> getProcedureDetail(int level, String procedure_cd) {
        ArrayList<String> data = new ArrayList<String>();
        String table = "CIS_PROCEDURE";
        String pcd = "PROCEDURE_CD";
        try {
            switch (level) {
                case 1:
                    table = "CIS_PROCEDURE";
                    pcd = "PROCEDURE_CD";
                    break;
                case 2:
                    table = "CIS_PROCEDURE_1";
                    pcd = "PROCEDURE_1_CD";
                    break;
                case 3:
                    table = "CIS_PROCEDURE_2";
                    pcd = "PROCEDURE_2_CD";
                    break;
            }
            String sql = "SELECT * FROM "+table+" WHERE "+pcd+" = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, procedure_cd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int sizeCol = 3;
                if (level != 1) {
                    sizeCol = 4;
                }
                for (int i = 0; i < sizeCol; i++) {
                    data.add(rs.getString(i+1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<String> getProcedureDetail2(int level, String procedure_name) {
        ArrayList<String> data = new ArrayList<String>();
        String table = "CIS_PROCEDURE";
        String pcd = "PROCEDURE_NAME";
        try {
            switch (level) {
                case 1:
                    table = "CIS_PROCEDURE";
                    pcd = "PROCEDURE_NAME";
                    break;
                case 2:
                    table = "CIS_PROCEDURE_1";
                    pcd = "PROCEDURE_1_NAME";
                    break;
                case 3:
                    table = "CIS_PROCEDURE_2";
                    pcd = "PROCEDURE_2_NAME";
                    break;
            }
            String sql = "SELECT * FROM "+table+" WHERE UCASE("+pcd+") LIKE UCASE(?) ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, "%"+procedure_name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int sizeCol = 3;
                if (level != 1) {
                    sizeCol = 4;
                }
                for (int i = 0; i < sizeCol; i++) {
                    data.add(rs.getString(i+1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<ArrayList<String>> getProcedure(int level) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        String table = "CIS_PROCEDURE";
        try {
            switch (level) {
                case 1:
                    table = "CIS_PROCEDURE";
                    break;
                case 2:
                    table = "CIS_PROCEDURE_1";
                    break;
                case 3:
                    table = "CIS_PROCEDURE_2";
                    break;
            }
            String sql = "SELECT * FROM "+table+"";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> d = new ArrayList<String>();
                int sizeCol = 3;
                if (level != 1) {
                    sizeCol = 4;
                }
                for (int i = 0; i < sizeCol; i++) {
                    d.add(rs.getString(i+1));
                }
                data.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static boolean addProcedure(int level, CIS_Procedure cis_procedure) {
        boolean status = false;
        String table = "CIS_PROCEDURE";
        String sql = "";
        try {
            switch (level) {
                case 1:
                    table = "CIS_PROCEDURE";
                    sql = "INSERT INTO "+table+" VALUES(?, ?, ?)";
                    break;
                case 2:
                    table = "CIS_PROCEDURE_1";
                    sql = "INSERT INTO "+table+" VALUES(?, ?, ?, ?)";
                    break;
                case 3:
                    table = "CIS_PROCEDURE_2";
                    sql = "INSERT INTO "+table+" VALUES(?, ?, ?, ?)";
                    break;
            }
            System.out.println("sql:"+sql);
            System.out.println("cis_procedure.getProcedure_cd():"+cis_procedure.getProcedure_cd());
            System.out.println("cis_procedure.getProcedure_name():"+cis_procedure.getProcedure_name());
            System.out.println("cis_procedure.getStatus():"+cis_procedure.getStatus());
            System.out.println("cis_procedure.getProcedure_parent():"+cis_procedure.getProcedure_parent());
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            switch (level) {
                case 1:
                    ps.setString(1, cis_procedure.getProcedure_cd());
                    ps.setString(2, cis_procedure.getProcedure_name());
                    ps.setString(3, cis_procedure.getStatus());
                    break;
                case 2:
                    ps.setString(1, cis_procedure.getProcedure_cd());
                    ps.setString(2, cis_procedure.getProcedure_name());
                    ps.setString(3, cis_procedure.getProcedure_parent());
                    ps.setString(4, cis_procedure.getStatus());
                    break;
                case 3:
                    ps.setString(1, cis_procedure.getProcedure_cd());
                    ps.setString(2, cis_procedure.getProcedure_name());
                    ps.setString(3, cis_procedure.getProcedure_parent());
                    ps.setString(4, cis_procedure.getStatus());
                    break;
            }
            ps.execute();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }
    
    public static boolean updateProcedure(int level, CIS_Procedure cis_procedure) {
        boolean status = false;
        String table = "CIS_PROCEDURE";
        String sql = "";
        try {
            switch (level) {
                case 1:
                    table = "CIS_PROCEDURE";
                    sql = "UPDATE "+table+" SET PROCEDURE_NAME = ?, STATUS = ? WHERE PROCEDURE_CD = ?";
                    break;
                case 2:
                    table = "CIS_PROCEDURE_1";
                    sql = "UPDATE "+table+" SET PROCEDURE_1_NAME = ?, PROCEDURE_CD = ?, STATUS = ? WHERE PROCEDURE_1_CD = ?";
                    break;
                case 3:
                    table = "CIS_PROCEDURE_2";
                    sql = "UPDATE "+table+" SET PROCEDURE_2_NAME = ?, PROCEDURE_1_CD = ?, STATUS = ? WHERE PROCEDURE_2_CD = ?";
                    break;
            }
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            switch (level) {
                case 1:
                    ps.setString(1, cis_procedure.getProcedure_name());
                    ps.setString(2, cis_procedure.getStatus());
                    ps.setString(3, cis_procedure.getProcedure_cd());
                    break;
                case 2:
                    ps.setString(1, cis_procedure.getProcedure_name());
                    ps.setString(2, cis_procedure.getProcedure_parent());
                    ps.setString(3, cis_procedure.getStatus());
                    ps.setString(4, cis_procedure.getProcedure_cd());
                    break;
                case 3:
                    ps.setString(1, cis_procedure.getProcedure_name());
                    ps.setString(2, cis_procedure.getProcedure_parent());
                    ps.setString(3, cis_procedure.getStatus());
                    ps.setString(4, cis_procedure.getProcedure_cd());
                    break;
            }
            ps.execute();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }
    
    public static boolean deleteProcedure(int level, CIS_Procedure cis_procedure) {
        boolean status = false;
        String table = "CIS_PROCEDURE";
        String sql = "";
        try {
            switch (level) {
                case 1:
                    table = "CIS_PROCEDURE";
                    sql = "DELETE FROM "+table+" WHERE PROCEDURE_CD = ?";
                    break;
                case 2:
                    table = "CIS_PROCEDURE_1";
                    sql = "DELETE FROM "+table+" WHERE PROCEDURE_1_CD = ?";
                    break;
                case 3:
                    table = "CIS_PROCEDURE_2";
                    sql = "DELETE FROM "+table+" WHERE PROCEDURE_2_CD = ?";
                    break;
            }
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, cis_procedure.getProcedure_cd());
            ps.execute();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }
    
    public static ArrayList<GCS_Response> getGcs_responseAll(String type) {
        ArrayList<GCS_Response> data = new ArrayList<GCS_Response>();
        try {
            String sql = "SELECT * FROM gcs_response WHERE response_type = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GCS_Response gcs = new GCS_Response();
                gcs.setResponse_code(rs.getString(1));
                gcs.setResponse_name(rs.getString(2));
                gcs.setResponse_type(rs.getString(3));
                data.add(gcs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static GCS_Response getGcs_response(GCS_Response gcs) {
        try {
            String sql = "SELECT * FROM gcs_response WHERE response_code = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, gcs.getResponse_code());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gcs.setResponse_code(rs.getString(1));
                gcs.setResponse_name(rs.getString(2));
                gcs.setResponse_type(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gcs;
    }
    
    public static ArrayList<GCS_Scale> getGcs_scaleAll(String type) {
        ArrayList<GCS_Scale> data = new ArrayList<GCS_Scale>();
        try {
            String sql = "SELECT * FROM gcs_scale g1, gcs_response g2, gcs_month g3 "
                    + "WHERE g1.response_code = g2.response_code "
                    + "AND g1.month_code = g3.month_code "
                    + "AND g2.response_type = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GCS_Scale gcs = new GCS_Scale();
                GCS_Response gcs_re = new GCS_Response();
                gcs_re.setResponse_code(rs.getString("response_code"));
                gcs_re.setResponse_name(rs.getString("response_name"));
                gcs_re.setResponse_type(rs.getString("response_type"));
                gcs.setgCS_Response(gcs_re);
                gcs.setScale_code(rs.getString("scale_code"));
                gcs.setScale_desc(rs.getString("scale_desc"));
                gcs.setScale_score(rs.getString("scale_score"));
                GCS_Month gcs_mo = new GCS_Month();
                gcs_mo.setMonth_code(rs.getString("month_code"));
                gcs_mo.setMonth_min(rs.getString("month_min"));
                gcs_mo.setMonth_max(rs.getString("month_max"));
                gcs_mo.setMonth_desc(rs.getString("month_desc"));
                gcs.setgCS_Month(gcs_mo);
                data.add(gcs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<GCS_Scale> getGcs_scaleAll_parent(GCS_Response parent, GCS_Month month) {
        ArrayList<GCS_Scale> data = new ArrayList<GCS_Scale>();
        try {
            String sql = "SELECT * FROM gcs_scale g1, gcs_response g2, gcs_month g3 "
                    + "WHERE g1.response_code = g2.response_code "
                    + "AND g1.month_code = g3.month_code "
                    + "AND g1.response_code = ? "
                    + "AND g1.month_code = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, parent.getResponse_code());
            ps.setString(2, month.getMonth_code());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GCS_Scale gcs = new GCS_Scale();
                GCS_Response gcs_re = new GCS_Response();
                gcs_re.setResponse_code(rs.getString("response_code"));
                gcs_re.setResponse_name(rs.getString("response_name"));
                gcs_re.setResponse_type(rs.getString("response_type"));
                gcs.setgCS_Response(gcs_re);
                gcs.setScale_code(rs.getString("scale_code"));
                gcs.setScale_desc(rs.getString("scale_desc"));
                gcs.setScale_score(rs.getString("scale_score"));
                GCS_Month gcs_mo = new GCS_Month();
                gcs_mo.setMonth_code(rs.getString("month_code"));
                gcs_mo.setMonth_min(rs.getString("month_min"));
                gcs_mo.setMonth_max(rs.getString("month_max"));
                gcs_mo.setMonth_desc(rs.getString("month_desc"));
                gcs.setgCS_Month(gcs_mo);
                data.add(gcs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static GCS_Scale getGcs_scale(GCS_Scale gcs) {
        try {
            String sql = "SELECT * FROM gcs_scale g1, gcs_response g2, gcs_month g3 "
                    + "WHERE g1.response_code = g2.response_code "
                    + "AND g1.month_code = g3.month_code "
                    + "AND g1.scale_code = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, gcs.getScale_code());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GCS_Response gcs_re = new GCS_Response();
                gcs_re.setResponse_code(rs.getString("response_code"));
                gcs_re.setResponse_name(rs.getString("response_name"));
                gcs_re.setResponse_type(rs.getString("response_type"));
                gcs.setgCS_Response(gcs_re);
                gcs.setScale_code(rs.getString("scale_code"));
                gcs.setScale_desc(rs.getString("scale_desc"));
                gcs.setScale_score(rs.getString("scale_score"));
                GCS_Month gcs_mo = new GCS_Month();
                gcs_mo.setMonth_code(rs.getString("month_code"));
                gcs_mo.setMonth_min(rs.getString("month_min"));
                gcs_mo.setMonth_max(rs.getString("month_max"));
                gcs_mo.setMonth_desc(rs.getString("month_desc"));
                gcs.setgCS_Month(gcs_mo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gcs;
    }
    
    public static boolean addPhysicalExam(int level, PhysicalExamBean pe) {
        boolean stat = false;
        try {
            String table = "cis_physical_exam_system(physical_exam_cd, physical_exam_name, status)";
            String values = "(?,?,?)";
            switch (level) {
                case 1:
                    table = "cis_pe_1(pe_1_cd, pe_1_name, physical_exam_cd, status)";
                    break;
                case 2:
                    table = "cis_pe_2(pe_2_cd, pe_2_name, pe_1_cd, status)";
                    break;
                case 3:
                    table = "cis_pe_3(pe_3_cd, pe_3_name, pe_2_cd, status)";
                    break;
                case 4:
                    table = "cis_pe_4(pe_4_cd, pe_4_name, pe_3_cd, status)";
                    break;
                case 5:
                    table = "cis_pe_5(pe_5_cd, pe_5_name, pe_4_cd, status)";
                    break;
                case 6:
                    table = "cis_pe_6(pe_6_cd, pe_6_name, pe_5_cd, status)";
                    break;
                case 7:
                    table = "cis_pe_7(pe_7_cd, pe_7_name, pe_6_cd, status)";
                    break;
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    values = "(?,?,?,?)";
                    break;
            }
            String sql = "INSERT INTO "+table+" VALUES"+values+" ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            switch (level) {
                default:
                case 0:
                    ps.setString(1, pe.getPe_cd());
                    ps.setString(2, pe.getPe_name());
                    ps.setString(3, pe.getPe_status());
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    ps.setString(1, pe.getPe_cd());
                    ps.setString(2, pe.getPe_name());
                    ps.setString(3, pe.getPe_parent());
                    ps.setString(4, pe.getPe_status());
                    break;
            }
            System.out.println("sql:"+sql);
            System.out.println("pe.getPe_cd():"+pe.getPe_cd());
            System.out.println("pe.getPe_name():"+pe.getPe_name());
            System.out.println("pe.getPe_parent():"+pe.getPe_parent());
            System.out.println("pe.getPe_status():"+pe.getPe_status());
            ps.execute();
            stat = true;
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static boolean updatePhysicalExam(int level, PhysicalExamBean pe) {
        boolean stat = false;
        try {
            String table = "cis_physical_exam_system";
            String values = "physical_exam_name=?,status=?";
            String where = "physical_exam_cd=?";
            switch (level) {
                case 1:
                    table = "cis_pe_1";
                    values = "pe_1_name=?,physical_exam_cd=?,status=?";
                    where = "pe_1_cd=?";
                    break;
                case 2:
                    table = "cis_pe_2";
                    values = "pe_2_name=?,pe_1_cd=?,status=?";
                    where = "pe_2_cd=?";
                    break;
                case 3:
                    table = "cis_pe_3";
                    values = "pe_3_name=?,pe_2_cd=?,status=?";
                    where = "pe_3_cd=?";
                    break;
                case 4:
                    table = "cis_pe_4";
                    values = "pe_4_name=?,pe_3_cd=?,status=?";
                    where = "pe_4_cd=?";
                    break;
                case 5:
                    table = "cis_pe_5";
                    values = "pe_5_name=?,pe_4_cd=?,status=?";
                    where = "pe_5_cd=?";
                    break;
                case 6:
                    table = "cis_pe_6";
                    values = "pe_6_name=?,pe_5_cd=?,status=?";
                    where = "pe_6_cd=?";
                    break;
                case 7:
                    table = "cis_pe_7";
                    values = "pe_7_name=?,pe_6_cd=?,status=?";
                    where = "pe_7_cd=?";
                    break;
            }
            String sql = "UPDATE "+table+" SET "+values+" WHERE "+where+" ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            switch (level) {
                default:
                case 0:
                    ps.setString(1, pe.getPe_name());
                    ps.setString(2, pe.getPe_status());
                    ps.setString(3, pe.getPe_cd());
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    ps.setString(1, pe.getPe_name());
                    ps.setString(2, pe.getPe_parent());
                    ps.setString(3, pe.getPe_status());
                    ps.setString(4, pe.getPe_cd());
                    break;
            }
            ps.execute();
            stat = true;
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static boolean deletePhysicalExam(int level, PhysicalExamBean pe) {
        boolean stat = false;
        try {
            String table = "cis_physical_exam_system";
            String value = "physical_exam_cd=?";
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    table = "cis_pe_"+level;
                    value = "pe_"+level+"_cd=?";
                    break;
            }
            String sql = "DELETE FROM "+table+" WHERE "+value+" ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, pe.getPe_cd());
            ps.execute();
            stat = true;
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static PhysicalExamBean getPhysicalExam(int level, PhysicalExamBean pe) {
        try {
            String column = "physical_exam_cd, physical_exam_name, status";
            String table = "cis_physical_exam_system";
            String where = "physical_exam_cd=?";
            switch (level) {
                case 1:
                    column = "pe_1_cd, pe_1_name, physical_exam_cd, status";
                    table = "cis_pe_1";
                    where = "pe_1_cd=?";
                    break;
                case 2:
                    column = "pe_2_cd, pe_2_name, pe_1_cd, status";
                    table = "cis_pe_2";
                    where = "pe_2_cd=?";
                    break;
                case 3:
                    column = "pe_3_cd, pe_3_name, pe_2_cd, status";
                    table = "cis_pe_3";
                    where = "pe_3_cd=?";
                    break;
                case 4:
                    column = "pe_4_cd, pe_4_name, pe_3_cd, status";
                    table = "cis_pe_4";
                    where = "pe_4_cd=?";
                    break;
                case 5:
                    column = "pe_5_cd, pe_5_name, pe_4_cd, status";
                    table = "cis_pe_5";
                    where = "pe_5_cd=?";
                    break;
                case 6:
                    column = "pe_6_cd, pe_6_name, pe_5_cd, status";
                    table = "cis_pe_6";
                    where = "pe_6_cd=?";
                    break;
                case 7:
                    column = "pe_7_cd, pe_7_name, pe_6_cd, status";
                    table = "cis_pe_7";
                    where = "pe_7_cd=?";
                    break;
            }
            String sql = "SELECT "+column+" FROM "+table+" WHERE "+where+" ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, pe.getPe_cd());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                switch (level) {
                    case 0:
                    default:
                        pe.setPe_cd(rs.getString(1));
                        pe.setPe_name(rs.getString(2));
                        pe.setPe_status(rs.getString(3));
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        pe.setPe_cd(rs.getString(1));
                        pe.setPe_name(rs.getString(2));
                        pe.setPe_parent(rs.getString(3));
                        pe.setPe_status(rs.getString(4));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pe;
    }
    
    public static PhysicalExamBean getPhysicalExam2(int level, PhysicalExamBean pe) {
        try {
            String column = "physical_exam_cd, physical_exam_name, status";
            String table = "cis_physical_exam_system";
            String where = "UCASE(physical_exam_name) LIKE UCASE(?)";
            switch (level) {
                case 1:
                    column = "pe_1_cd, pe_1_name, physical_exam_cd, status";
                    table = "cis_pe_1";
                    where = "UCASE(pe_1_name) LIKE UCASE(?)";
                    break;
                case 2:
                    column = "pe_2_cd, pe_2_name, pe_1_cd, status";
                    table = "cis_pe_2";
                    where = "UCASE(pe_2_name) LIKE UCASE(?)";
                    break;
                case 3:
                    column = "pe_3_cd, pe_3_name, pe_2_cd, status";
                    table = "cis_pe_3";
                    where = "UCASE(pe_3_name) LIKE UCASE(?)";
                    break;
                case 4:
                    column = "pe_4_cd, pe_4_name, pe_3_cd, status";
                    table = "cis_pe_4";
                    where = "UCASE(pe_4_name) LIKE UCASE(?)";
                    break;
                case 5:
                    column = "pe_5_cd, pe_5_name, pe_4_cd, status";
                    table = "cis_pe_5";
                    where = "UCASE(pe_5_name) LIKE UCASE(?)";
                    break;
                case 6:
                    column = "pe_6_cd, pe_6_name, pe_5_cd, status";
                    table = "cis_pe_6";
                    where = "UCASE(pe_6_name) LIKE UCASE(?)";
                    break;
                case 7:
                    column = "pe_7_cd, pe_7_name, pe_6_cd, status";
                    table = "cis_pe_7";
                    where = "UCASE(pe_7_name) LIKE UCASE(?)";
                    break;
            }
            String sql = "SELECT "+column+" FROM "+table+" WHERE "+where+" ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, "%"+pe.getPe_name()+"%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                switch (level) {
                    case 0:
                    default:
                        pe.setPe_cd(rs.getString(1));
                        pe.setPe_name(rs.getString(2));
                        pe.setPe_status(rs.getString(3));
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        pe.setPe_cd(rs.getString(1));
                        pe.setPe_name(rs.getString(2));
                        pe.setPe_parent(rs.getString(3));
                        pe.setPe_status(rs.getString(4));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pe;
    }
    
    public static ArrayList<PhysicalExamBean> getPhysicalExamAll(int level) {
        ArrayList<PhysicalExamBean> pe = new ArrayList<PhysicalExamBean>();
        try {
            String column = "physical_exam_cd, physical_exam_name, status";
            String table = "cis_physical_exam_system";
            switch (level) {
                case 1:
                    column = "pe_1_cd, pe_1_name, physical_exam_cd, status";
                    table = "cis_pe_1";
                    break;
                case 2:
                    column = "pe_2_cd, pe_2_name, pe_1_cd, status";
                    table = "cis_pe_2";
                    break;
                case 3:
                    column = "pe_3_cd, pe_3_name, pe_2_cd, status";
                    table = "cis_pe_3";
                    break;
                case 4:
                    column = "pe_4_cd, pe_4_name, pe_3_cd, status";
                    table = "cis_pe_4";
                    break;
                case 5:
                    column = "pe_5_cd, pe_5_name, pe_4_cd, status";
                    table = "cis_pe_5";
                    break;
                case 6:
                    column = "pe_6_cd, pe_6_name, pe_5_cd, status";
                    table = "cis_pe_6";
                    break;
                case 7:
                    column = "pe_7_cd, pe_7_name, pe_6_cd, status";
                    table = "cis_pe_7";
                    break;
            }
            String sql = "SELECT "+column+" FROM "+table+" ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhysicalExamBean pe_temp = new PhysicalExamBean();
                switch (level) {
                    case 0:
                    default:
                        pe_temp.setPe_cd(rs.getString(1));
                        pe_temp.setPe_name(rs.getString(2));
                        pe_temp.setPe_status(rs.getString(3));
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        pe_temp.setPe_cd(rs.getString(1));
                        pe_temp.setPe_name(rs.getString(2));
                        pe_temp.setPe_parent(rs.getString(3));
                        pe_temp.setPe_status(rs.getString(4));
                        break;
                }
                pe.add(pe_temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pe;
    }
    
    public static String getPE_treelink(String pe_code) {
        String code_split[] = pe_code.split("\\^");
        int size = code_split.length;
        String pe_name = "";
        for (int i = 0; i < size-1; i++) {
            PhysicalExamBean peb_temp = new PhysicalExamBean();
            String code_temp = "";
            for (int j = 0; j <= i-1; j++) {
                code_temp += code_split[j]+"^";
            }
            code_temp += code_split[i];
            peb_temp.setPe_cd(code_temp);
            PhysicalExamBean peb = DBConnection.getPhysicalExam(i, peb_temp);
            if (peb.getPe_name() != null) {
                pe_name += peb.getPe_name() + ", ";
            }
        }
        PhysicalExamBean peb_temp = new PhysicalExamBean();
        peb_temp.setPe_cd(pe_code);
        PhysicalExamBean peb = DBConnection.getPhysicalExam(size-1, peb_temp);
        pe_name += peb.getPe_name();
        return pe_name;
    }
}
