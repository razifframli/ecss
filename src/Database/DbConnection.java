/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Helper.S;
import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.Consultation;
import library.NetworkStatus;


/**
 *
 * @author Chung Wei Ming
 */
public class DbConnection {
    
    public static Connection doConnection() {
        Connection conn = null;
        try {
            //if (NetworkStatus.DoPing(Config.getIpServer())) { //server
            //-- if(Session.getPrev_stat()) {
            if(false) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Config.getDbUrlServer(), Config.getUserServer(), Config.getPassServer());
                S.oln("Online DB");
            } else { //localhost
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                conn = DriverManager.getConnection(Config.getDbUrlLocal(), Config.getUserLocal(), Config.getPassLocal());
                S.oln("Offline DB");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                conn = DriverManager.getConnection(Config.getDbUrlLocal(), Config.getUserLocal(), Config.getPassLocal());
                S.oln("Offline DB");
                ex.printStackTrace();
            } catch (SQLException ex1) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return conn;
    }


     public static void main(String[] args) throws ClassNotFoundException, SQLException{

         DbConnection.doConnection();

     }
}
