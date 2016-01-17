/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Helper.S;
import Helper.Session;
import config.Config;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.Func;
import library.NetworkStatus;
import library.Q;

/**
 *
 * @author End User
 */
public class Test1 {
    
    public static void main(String[] args) {
        String v = Func.SPECIAL_CHARACTER;
        for (int i = 0; i < v.length(); i++) {
            System.out.println("|"+v.charAt(i)+"|");
        }
    }
    
    public static void main2(String[] args) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int a = 1;
            int b = 2;
            int c = (a + b) * i;
            System.out.println(c);
        }
        long time2 = System.currentTimeMillis();
        long diff = time2 - time1;
        double second = diff * 1.0 / 1000;
        System.out.println("Time taken: "+second+" seconds");
    }
    
    public static void main1(String [] args) {
        Session.startUp();
        if (Session.getNum_open_db() == 1) {
            Session.setPrev_stat(NetworkStatus.DoPing(Config.getIpServer(), 2000));
            Session.setCon_x();
        }
        for (int i = 0; i < 20; i++) {
            try {
                Scanner in = new Scanner(System.in);
                String sql = "";
                String pmiNo = i<10?"PMS1000"+i:i<100?"PMS100"+i:"";
                String coldata[] = new String[100]; //100, maximum number of column
                if (Session.getPrev_stat()) {
                    //Read BLOB from EHR_Central
                    sql = "SELECT C_TxnData "
                            + "FROM ehr_central "
                            + "WHERE PMI_NO LIKE ? "
                            + "ORDER BY C_TXNDATE DESC "
                            + "LIMIT 1";
                    coldata[0] = "C_TxnData";
                } else {
                    //Read BLOB from Journal_File
                    sql = "SELECT TXNDATA "
                            + "FROM PUBLIC.JOURNAL_FILE "
                            + "WHERE PMI_NO LIKE ? "
                            + "ORDER BY TXNDATE DESC "
                            + "LIMIT 1";
                    coldata[0] = "TXNDATA";
                }
                //SELECT TXNDATA FROM PUBLIC.JOURNAL_FILE WHERE PMI_NO LIKE 'PMI10001' ORDER BY TXNDATE DESC
                Q.ps = Session.getCon_x(100).prepareStatement(sql);
                Q.ps.setString(1, pmiNo);
                Q.rs = Q.ps.executeQuery();
                String cpyFile = "";
                if (Q.rs.next()) {
                    cpyFile = Q.rs.getString(coldata[0]);
                }
                S.oln(cpyFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                continue;
            }
        }
    }
}
