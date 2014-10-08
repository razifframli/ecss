/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AccessDB;

import Bean.ItemBean;
import Bean.PatientBean1;
import Bean.StaffBean;
import Helper.S;
import Helper.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jessica
 */
public class AccessDB {
    //private static String dbUrl = "jdbc:hsqldb:file:C:/Users/Windows/Desktop/hsqldb-2.1.0-rc4/hsqldb/data/database";
    private static String dbUrl = "jdbc:hsqldb:file:lib/userdata/friza;shutdown=true";
    private static Connection conn = null;
    private static String driverName = "org.hsqldb.jdbcDriver";
    public static Connection getConnectionInstance;
    private static String staffID;
    private static String staffPassword;
    private static String staffName;

     public static Connection getConnInstance()
    {
         if (conn == null)
         {
             //call createConnection static method
             conn = createConnection();
         }
         return conn;
     }

     private static Connection createConnection()
     {
         try
         {
             //Class.forName(driverName);
             //Connection conn = DriverManager.getConnection(dbURL+dbLocation);
             Class.forName(driverName).newInstance();
             conn = DriverManager.getConnection(dbUrl+";ifexists=true","sa","");
             System.out.println("Connection successful!....fr");
         }
         catch (Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return conn;
     }

     public static StaffBean[] StaffLogin(Connection conn, String id, String password)
    {
        //create a list to store the results
        List list = Collections.synchronizedList(new ArrayList(10));

        try
        {
            String sql = "";
            sql = "SELECT * from STAFF WHERE STAFF_ID = ? AND STAFF_Password = ?";

            //prepare sql query and execute
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);

            ResultSet results = ps.executeQuery();
            while(results.next())
            {
                //create String objects to store different column data of results
                staffID = results.getString("STAFF_ID");
                staffPassword = results.getString("STAFF_Password");
                staffName = results.getString("STAFF_Name");

                //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                list.add(new StaffBean(staffID, staffPassword, staffName));
            }
            //clean the result and data
            results.close();
            ps.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return(StaffBean[])list.toArray(new StaffBean[list.size()]);
    }
     
     
     public static ArrayList<String> getStaff(String id, String password)
     {
         //create a list to store the results
         ArrayList<String> staf = new ArrayList<String>();
         try {
             String sql = "SELECT * from PIS_STAFF WHERE Staff_ID = ? AND Staff_Password = ?";
             //S.oln(sql);

             //prepare sql query and execute
             PreparedStatement ps = Session.getCon_x(100).prepareStatement(sql);
             ps.setString(1, id);
             ps.setString(2, password);

             ResultSet results = ps.executeQuery();
             while (results.next()) {
                 //create String objects to store different column data of results
                 //S.oln("Staff ID: "+results.getString("Staff_ID"));
                 staf.add(results.getString("Staff_ID"));
                 staf.add(results.getString("Staff_Name"));
                 staf.add(results.getString("Staff_Password"));
                 
                 
             }
             //clean the result and data
             results.close();
             ps.close();
         } catch (Exception e) {
             Session.lineMessage();
             Session.setPrev_stat(Session.getCurr_stat());
             Session.setCon_x();
         }
         return staf;
    }

    public static PatientBean1[] Patient(Connection conn)
    {
        //create a list to store the results
        List list = Collections.synchronizedList(new ArrayList(10));
        try
        {
            String sql = "";
            sql="SELECT PMI_NO,NAME,NEW_IC_NO FROM PMS_EPISODE WHERE STATUS = ?";

            //prepare the sql query and execute it
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"Inpatient");
            ResultSet results = ps.executeQuery();

            while(results.next())
            {
                //create String objects to store different column data of results
                String PmiNo = results.getString("PMI_NO");
                String name = results.getString("NAME");
                String newIcNo = results.getString("NEW_IC_NO");

                //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                list.add(new PatientBean1(PmiNo,name,newIcNo));
            }
            //clean the results and data
            results.close();
            ps.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        //transfer the BuildingInfoBean objects in the list to a BuildingInfoBean array
        return (PatientBean1[])list.toArray(new PatientBean1[list.size()]);
    }

    public static ItemBean[] Item(Connection conn)
    {
        //create a list to store the results
        List list = Collections.synchronizedList(new ArrayList(10));
        try
        {
            String sql = "";
            sql="SELECT ITEM_NAME, PRICE FROM PMS_CHARGEABLE_ITEM";

            //prepare the sql query and execute it
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet results = ps.executeQuery();

            while(results.next())
            {
                //create String objects to store different column data of results
                String itemName = results.getString("ITEM_NAME");
                String itemPrice = results.getString("ITEM_PRICE");

                //create a BuildingInfoBean object using the String objects above and add the BuildingInfoBean object into the list
                list.add(new ItemBean(itemName,itemPrice));
            }
            //clean the results and data
            results.close();
            ps.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        //transfer the BuildingInfoBean objects in the list to a BuildingInfoBean array
        return (ItemBean[])list.toArray(new ItemBean[list.size()]);
    }
}
