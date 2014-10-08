/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conndb;
  import java.sql.*;

/**
 *
 * @author WC
 */
public class connection {


// public String url = "jdbc:mysql://localhost:3306/";
// public String dbName = "cissms";
// public String SqluserName = "root";
// public String password = "1234";


    public static Connection HSQLconnect() throws ClassNotFoundException, SQLException{

        Class.forName("org.hsqldb.jdbcDriver");
        Connection Hsqlconn = DriverManager.getConnection("jdbc:hsqldb:file:lib/userdata/friza;shutdown=true","SA","");
        System.out.println("HSQL Database connected");
        return Hsqlconn;

    }


       public static Connection MySQLconnect() throws ClassNotFoundException, SQLException, SQLTransientConnectionException{

        Class.forName("com.mysql.jdbc.Driver");
        Connection Sqlconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis", "root" , ""); //1234
        //System.out.println("..MySQL Database connected");
        return Sqlconn;

    }

}

