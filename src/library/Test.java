/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import Helper.S;
import Helper.Session;
import java.io.*;
import java.sql.PreparedStatement;

/**
 *
 * @author End User
 */
public class Test {
    
    public static void main(String [] args) {
        Session.startUp();
        if (Session.getNum_open_db() == 1) {
            Session.setPrev_stat(false);
            Session.setCurr_stat(false);
            Session.setCon_x();
        }
        File folder = new File("readcode_data/");
        File[] listOfFiles = folder.listFiles();
        int num_rows = 0;
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().contains("Fever")) {
                String filename = file.getName();
                try {
                    FileInputStream fstream = new FileInputStream("readcode_data/"+filename);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        try {
                            if (strLine.length() > 0) {
                                String data[] = strLine.split(" ");
                                String code = data[0];
                                String desc = "";
                                for (int i = 1; i < data.length; i++) {
                                    desc += data[i]+" ";
                                }
                                String sql = "INSERT "
                                        + "INTO READCODE_CHIEF_COMPLAINT "
                                        + "VALUES (?, ?)";
                                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                                ps.setString(1, code);
                                ps.setString(2, desc);
                                boolean bol = ps.execute();
                                S.oln("status readcode: "+bol+"");
                                num_rows++;
                            }
                        } catch(Exception ex) {
                            S.oln("Error: " + ex.getMessage());
                        }
                    }
                    in.close();
                } catch (Exception e) {
                    S.oln("Error: " + e.getMessage());
                }
            }
        }
        S.oln("Total rows: "+num_rows);
    }
    
}
