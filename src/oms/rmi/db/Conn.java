package oms.rmi.db;


import java.sql.*;

/**
 *
 * @author WC
 */
public class Conn {

// public String url = "jdbc:mysql://localhost:3306/";
// public String dbName = "cissms";
// public String SqluserName = "root";
// public String password = "1234";
//    public static Connection HSQLconnect() throws ClassNotFoundException, SQLException {
//
//        Class.forName("org.hsqldb.jdbcDriver");
//        Connection Hsqlconn = DriverManager.getConnection("jdbc:hsqldb:file:lib/userdata/friza;shutdown=true", "SA", "");
//        System.out.println("HSQL Database connected");
//        return Hsqlconn;
//
//    }

    public static Connection MySQLConnect() throws ClassNotFoundException, SQLException, SQLTransientConnectionException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection SqlConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis", "root", "abc123"); 
        //System.out.println("..MySQL Database connected");
        return SqlConn;

    }
}