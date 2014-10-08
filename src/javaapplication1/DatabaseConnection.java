/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import Helper.S;
import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import library.NetworkStatus;

/**
 *
 * @author user
 */
public class DatabaseConnection {
    
    private static String url = "jdbc:hsqldb:file:lib/userdata/friza;shutdown=true";

    public static Connection connect() throws ClassNotFoundException, SQLException {

        Connection conn = null;

        try {
            if (NetworkStatus.DoPing(Config.getIpServer(), 100)) { //server
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Config.getDbUrlServer(), Config.getUserServer(), Config.getPassServer());
                S.oln("Online DB");
            } else { //localhost
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                conn = DriverManager.getConnection(Config.getDbUrlLocal(), Config.getUserLocal(), Config.getPassLocal());
                S.oln("Offline DB");
            }
        } catch (Exception ex) {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conn = DriverManager.getConnection(Config.getDbUrlLocal(), Config.getUserLocal(), Config.getPassLocal());
            S.oln("Offline DB");
            ex.printStackTrace();
        }
        return conn;


    }
   
     public static Connection MySQLconnect() throws ClassNotFoundException, SQLException, SQLTransientConnectionException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection Sqlconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis", "root", ""); //skatizi
        System.out.println("SQL Database connected");
        return Sqlconn;

    }
     
      /** Class.forName("org.hsqldb.jdbc.JDBCDriver");
        String url = "jdbc:hsqldb:file:C:/Users/Windows/Desktop/hsqldb-2.1.0-rc4/hsqldb/data/database";
        Connection conn = DriverManager.getConnection(url,"SA","");
        return conn;**/


}
