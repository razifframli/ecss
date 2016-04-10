/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Bean.ConnectCSS;
import DBConnection.DBConnection;
import GUI.Console;
import GUI.LoadingForm;
import GUI.Login;
import config.Config;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ConsoleLog;
import library.Func;
import library.LongRunProcess;
import library.NetworkStatus;
import oracle.toplink.essentials.sessions.SessionProfiler;

/**
 *
 * @author syed
 */
public class Session {
    
    private static String user_id;
    private static String user_name;
    private static String password;
    private static boolean logged_in;
    private static Connection con_x;
    private static Connection con_x2;
    private static boolean prev_stat;
    private static boolean curr_stat;
    private static int num_open_db = 1;
    public static Console console;
    
    private static String hfc_code;
    private static String discipline;
    private static String subdiscipline;
    
    private static String rmi_hostname;
    private static int rmi_port;
    
    private static ArrayList<String> data_user = new ArrayList<String>();
    
    public static void startUp() {
        
//        Session.console = new Console();
//        Session.console.setVisible(true);
        
        ArrayList<String> listOnline = Func.readXML("online");
        ArrayList<String> listOffline = Func.readXML("offline");
        Config.setIpServer(listOnline.get(0));
        Config.setDbServer(listOnline.get(1));
        Config.setDbUrlServer(listOnline.get(4));
        Config.setUserServer(listOnline.get(2));
        Config.setPassServer(listOnline.get(3));
        Config.setDbUrlLocal(listOffline.get(4));
        Config.setUserLocal(listOffline.get(2));
        Config.setPassLocal(listOffline.get(3));
        Config.setDbUrlLocal2(listOffline.get(5));
        
//        ConnectCSS.online();
//        Config.setIpServer(ConnectCSS.getIp());
//        Config.setDbServer(ConnectCSS.getDb());
//        Config.setDbUrlServer(ConnectCSS.getUrl());
//        Config.setUserServer(ConnectCSS.getUser());
//        Config.setPassServer(ConnectCSS.getPass());
//        
//        ConnectCSS.offline();
//        Config.setDbUrlLocal(ConnectCSS.getUrl());
//        Config.setUserLocal(ConnectCSS.getUser());
//        Config.setPassLocal(ConnectCSS.getPass());
        
        Session.setCurr_stat(true);
        Session.setRmi_hostname(listOnline.get(0));
        Session.setRmi_port(1099);
    }
    
    public static String getHfc_code() {
        return hfc_code;
    }
    public static void setHfc_code(String h) {
        hfc_code = h;
    }
    public static String getDiscipline() {
        return discipline;
    }
    public static void setDiscipline(String d) {
        discipline = d;
    }
    public static String getSubdiscipline() {
        return subdiscipline;
    }
    public static void setSubdiscipline(String s) {
        subdiscipline = s;
    }
    public static String getUser_id() {
        return user_id;
    }
    public static void setUser_id(String u) {
        user_id = u;
    }
    public static String getUser_name() {
        return user_name;
    }
    public static void setUser_name(String n) {
        user_name = n;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String p) {
        password = p;
    }
    public static boolean getLogged_in() {
        return logged_in;
    }
    public static void setLogged_in(boolean l) {
        logged_in = l;
    }
    public static Connection getCon_x(int time) {
        //S.oln("Prev Stat: "+Session.getPrev_stat());
        //Session.setCurr_stat(NetworkStatus.DoPing(Config.getIpServer(), time));
        Session.setCurr_stat(Session.getPrev_stat());
        //S.oln("Curr Stat: "+Session.getCurr_stat());
        if(Session.getCurr_stat() != Session.getPrev_stat()) {
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        return con_x;
    } 
    public static void setCon_x() {
        
        
        con_x = DBConnection.createConnection();
        Login.lf.dispose();
    }
    
    public static Connection getCon_x2(int time) {
        //S.oln("Prev Stat: "+Session.getPrev_stat());
        //Session.setCurr_stat(NetworkStatus.DoPing(Config.getIpServer(), time));
        Session.setCurr_stat(Session.getPrev_stat());
        //S.oln("Curr Stat: "+Session.getCurr_stat());
        if(Session.getCurr_stat() != Session.getPrev_stat()) {
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x2();
        }
        return con_x2;
    } 
    public static void setCon_x2() {
        
        
        con_x2 = DBConnection.createConnection();
        Login.lf.dispose();
    }
    
    public static boolean getPrev_stat() {
        return prev_stat;
    }
    public static void setPrev_stat(boolean p_s) {
        prev_stat = p_s;
    }
    public static boolean getCurr_stat() {
        return curr_stat;
    }
    public static void setCurr_stat(boolean c_s) {
        curr_stat = c_s;
    }
    public static int getNum_open_db() {
        S.oln("num_open_db: "+num_open_db);
        return num_open_db++;
    }
    
    public static void logout() {
        Session.setPassword("");
        Session.setUser_id("");
        Session.setUser_name("");
        Session.setLogged_in(false);
        Session.setHfc_code("");
        Session.setDiscipline("");
        Session.setSubdiscipline("");
    }
    public static void login(String id, String name, String pass) {
        Session.setUser_id(id);
        Session.setUser_name(name);
        Session.setPassword(pass);
        Session.setLogged_in(true);
    }
    public static void lineMessage() {
        J.o((Session.getCurr_stat()?"Online":"Offline"), 
                ".. The connection is "
                +(Session.getCurr_stat()?"Online":"Offline")
                +" ..\n"
                + ".. Please wait for the connection to be opened ..", 
                1);
    }

    /**
     * @return the data_user
     */
    public static ArrayList<String> getData_user() {
        return data_user;
    }

    /**
     * @param aData_user the data_user to set
     */
    public static void setData_user(ArrayList<String> aData_user) {
        data_user = aData_user;
    }

    public static String getRmi_hostname() {
        return rmi_hostname;
    }

    public static void setRmi_hostname(String aRmi_hostname) {
        rmi_hostname = aRmi_hostname;
    }

    public static int getRmi_port() {
        return rmi_port;
    }

    public static void setRmi_port(int aRmi_port) {
        rmi_port = aRmi_port;
    }
    
}
