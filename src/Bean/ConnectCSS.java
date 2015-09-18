/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author End User
 */
public class ConnectCSS {
    private static String ip;
    private static String db;
    private static String user;
    private static String pass;
    private static String url;
    private static String url2;
    private static String on;
    
    private static String hostCallingSystem;
    private static int portCallingSystem;
    
    private static int portRMI;
    
    private static String statusCallingSystem;
    
    public static void online() {
        try {
            ip = getIpCall()[2];
        } catch (Exception e) {
            ip = "10.73.32.200";
            e.printStackTrace();
        }
        db = "servercis";
        user = "root";
        pass = "qwerty";
        url = "jdbc:mysql://"+ip+"/servercis";
        on = "true";
    }
    
    public static void offline() {
        ip = "127.0.0.1";
        db = "";
        user = "SA";
        pass = "";
        url = "jdbc:hsqldb:file:db/cis;shutdown=true";
        on = "false";
    }
    
    public static void offline2() {
        ip = "127.0.0.1";
        db = "";
        user = "SA";
        pass = "";
        url2 = "jdbc:hsqldb:file:db_per/cis_per;shutdown=true";
        on = "false";
    }

    /**
     * @return the ip
     */
    public static String getIp() {
        return ip;
    }

    /**
     * @param aIp the ip to set
     */
    public static void setIp(String aIp) {
        ip = aIp;
    }

    /**
     * @return the db
     */
    public static String getDb() {
        return db;
    }

    /**
     * @param aDb the db to set
     */
    public static void setDb(String aDb) {
        db = aDb;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the pass
     */
    public static String getPass() {
        return pass;
    }

    /**
     * @param aPass the pass to set
     */
    public static void setPass(String aPass) {
        pass = aPass;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param aUrl the url to set
     */
    public static void setUrl(String aUrl) {
        url = aUrl;
    }

    /**
     * @return the on
     */
    public static String getOn() {
        return on;
    }

    /**
     * @param aOn the on to set
     */
    public static void setOn(String aOn) {
        on = aOn;
    }

    public static String getHostCallingSystem() {
        try {
            hostCallingSystem = getIpCall()[0];
        } catch (Exception e) {
            hostCallingSystem = "10.73.38.205";
            e.printStackTrace();
        }
        return hostCallingSystem;
    }

    public static int getPortCallingSystem() {
        try {
            portCallingSystem = Integer.parseInt(getIpCall()[1]);
        } catch (Exception e) {
            portCallingSystem = 1098;
            e.printStackTrace();
        }
        return portCallingSystem;
    }
    
    public static String[] getIpCall() {
        String data[] = new String[4];
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream("ipcall");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            for (int i = 0; (strLine = br.readLine()) != null; i++) {
                // Print the content on the console
                //System.out.println(strLine);
                String pecah[] = strLine.split(":");
                // Get Calling System IP Address
                if (pecah[0].equals("ip")) {
                    data[0] = pecah[1];
                }
                // Get Calling System Port Number
                if (pecah[0].equals("port")) {
                    data[1] = pecah[1];
                }
                // Get CSS IP Address
                if (pecah[0].equals("ipserver")) {
                    data[2] = pecah[1];
                }
                // Get Calling System Status
                if (pecah[0].equals("callsys")) {
                    data[3] = pecah[1];
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return data;
    }

    public static int getPortRMI() {
        portRMI = 1099;
        return portRMI;
    }

    public static String getStatusCallingSystem() {
        try {
            statusCallingSystem = getIpCall()[3];
        } catch (Exception e) {
            statusCallingSystem = "off";
            e.printStackTrace();
        }
        return statusCallingSystem;
    }

    public static void setStatusCallingSystem(String aStatusCallingSystem) {
        statusCallingSystem = aStatusCallingSystem;
    }

    public static String getUrl2() {
        return url2;
    }

    public static void setUrl2(String aUrl2) {
        url2 = aUrl2;
    }
}
