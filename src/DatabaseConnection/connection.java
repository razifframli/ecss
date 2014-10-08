/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatabaseConnection;
  import java.sql.*;

/**
 *
 * @author WC
 */
public class connection {


   private static String dbURL="jdbc:hsqldb:file:lib/userdata/friza;shutdown=true";
   private static Connection conn = null;
   private static String driverName = "org.hsqldb.jdbcDriver";

       public static Connection getConnInstance() throws ClassNotFoundException, SQLException
    {
//        if (conn == null)
//        {
//            //call createConnection static method
//            conn = createConnection();
//        }
       Class.forName(driverName);
         
       Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:lib/userdata/friza;shutdown=true","SA","");
         
        return conn;
    }

    private static Connection createConnection() throws ClassNotFoundException, SQLException
    {
          
            Class.forName(driverName);
         
            conn = DriverManager.getConnection(dbURL, "SA", "");
         
 
        return conn;
    }
//   public static Connection HSQLconnect() throws ClassNotFoundException, SQLException{
//
//        Class.forName("org.hsqldb.jdbcDriver");
//        Connection Hsqlconn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb","SA","");
//        System.out.println("HSQL Database connected");
//        return Hsqlconn;
//
//    }


       public static Connection SQLconnect() throws ClassNotFoundException, SQLException, SQLTransientConnectionException{

        Class.forName("com.mysql.jdbc.Driver");
        Connection Sqlconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cissms", "root" , "1234");
        System.out.println("SQL Database connected");
        return Sqlconn;

    }

}

