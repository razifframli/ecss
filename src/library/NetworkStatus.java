/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import Bean.ConnectCSS;
import Helper.S;
import config.Config;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import oms.rmi.server.Message;

/**
 *
 * @author user
 */
public class NetworkStatus {

    public static boolean DoPing(String ipstr, int time) {

            boolean retv = true;

            //S.oln("Ping Start");
            try {                
                final InetAddress host = InetAddress.getByName(ipstr);
                boolean netStat = host.isReachable(time);
                //S.oln("host: "+ipstr+"\nping: " + netStat+"\ntime: "+time);
                if(!netStat) {
                    retv=false;
                }
//                InetAddress addr
//                        = InetAddress.getByName(ipstr);
//                Socket theSocket = new Socket(addr, ConnectCSS.getPortRMI());
//                System.out.println("Connected to "
//                        + theSocket.getInetAddress()
//                        + " on port " + theSocket.getPort() + " from port "
//                        + theSocket.getLocalPort() + " of "
//                        + theSocket.getLocalAddress());
//                retv=true;
            } catch( Exception ex ) {
                //S.oln("Error: "+ex.getMessage());
                ex.printStackTrace();
                retv=false;
            }
            //S.oln("Ping "+ipstr+": "+retv);
            //S.oln("Ping End");
            return retv;
    }
    
    public static boolean cekWord(String str) {
        
        boolean bol = false;
        
        String words[] = str.split("[ |.]");
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("unreachable")) {
                bol = true;
                break;
            }
        }
        
        return bol;
    }
    
    public static void main(String[] args) {
        int nice = 0;
        int bad = 0;
        int total = 1;
        int rmi_port = 1099;
//        String ip = "10.73.32.200";
//        String ip = "210.48.157.104";
//        String ip = "10.73.32.201";
//        String ip = "210.48.157.126";
//        String ip = "10.1.3.209";
        String ip = "biocore-stag.utem.edu.my";
        int time = 2000;
//        int time = 7000;
        
        long lStartTime = System.currentTimeMillis();
        
        for (int i = 0; i < total; i++) {
            boolean stat = false;
//            boolean stat = NetworkStatus.DoPing(ip, time);
            
//            if (stat) {
                try {

                System.out.println("a1");
                    Registry myRegistry = LocateRegistry.getRegistry(ip, rmi_port);
                System.out.println("a2");
                    final String[] boundNames = myRegistry.list();
                    for (final String name : boundNames) {
                        System.out.println("\t" + name);
                    }
                System.out.println("a22");
                    Message impl = (Message) myRegistry.lookup("myMessage");
                System.out.println("a23");
                    System.setProperty("java.rmi.server.hostname", ip);
                    System.out.println("ip: "+ip);
                    System.out.println("host server: "+System.getProperty("java.rmi.server.hostname"));
                System.out.println("a3");
                    impl.sayHello("umar");
                System.out.println("msg delivered");

                    stat = true;

                } catch (Exception e) {
                    
                    e.printStackTrace();

                    stat = false;

                    //e.printStackTrace();
                }
//            } else {
//                stat = false;
//            }
            
            System.out.println("STATUS :  " + stat);
            if (stat) {
                nice += 1;
            } else {
                bad += 1;
            }
        }
        
        //some tasks
        long lEndTime = System.currentTimeMillis();

        double difference = (lEndTime - lStartTime) * 1.0 / 1000;
        
        System.out.println("Nice : "+nice);
        System.out.println("Bad  : "+bad);
        
        System.out.println("Time : "+difference+" seconds");
    }
}
