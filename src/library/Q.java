package library;

import Helper.S;
import Helper.Session;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Q {
    
    public static ResultSet rs;
    public static Statement st;
    public static PreparedStatement ps;
    
    /**
     * get ResultSet
     * @return current ResultSet
     */
    public static ResultSet gRs() {
        return Q.rs;
    }
    
    /**
     * set ResultSet
     * @param sql 
     */
    public static void sRs(String sql) {
        try {
            Q.sSt(Session.getCon_x(100).createStatement());
            Q.rs = Q.gSt().executeQuery(sql);
        } catch (SQLException ex) {
            S.oln("Error Query 32: "+ex.getMessage());
        }
    }
    
    /**
     * get Statement
     * @return current Statement
     */
    public static Statement gSt() {
        return Q.st;
    }
    
    /**
     * set Statement
     * @param st 
     */
    public static void sSt(Statement st) {
        Q.st = st;
    }
    
    /**
     * get PreparedStatement
     * @return current PreparedStatement
     */
    public static PreparedStatement gPs() {
        return Q.ps;
    }
    
    /**
     * set PreparedStatement
     * @param sql
     */
    public static void sPs(String sql) {
        try {
            Q.ps = Session.getCon_x(100).prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            S.oln("Error Query 69: "+ex.getMessage());
        }
    }
    
    /**
     * set setString for current PreparedStatement
     * @param parameterIndex
     * @param x 
     */
    public static void s(int parameterIndex, String x) {
        try {
            Q.gPs().setString(parameterIndex, x);
        } catch (Exception ex) {
            ex.printStackTrace();
            S.oln("Error Query 83: "+ex.getMessage());
        }
    }

    /**
     * set setDate for current PreparedStatement
     * @param parameterIndex
     * @param x 
     */
    public static void s(int parameterIndex, Date x) {
        try {
            Q.gPs().setDate(parameterIndex, x);
        } catch (SQLException ex) {
            S.oln("Error Query 95: "+ex.getMessage());
        }
    }
    
    /**
     * set setInt for current PreparedStatement
     * @param parameterIndex
     * @param x 
     */
    public static void s(int parameterIndex, int x) {
        try {
            Q.gPs().setInt(parameterIndex, x);
        } catch (SQLException ex) {
            S.oln("Error Query 89: "+ex.getMessage());
        }
    }
}
