/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Helper.S;
import Helper.Session;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import library.Q;

/**
 *
 * @author user
 */
public class TextFileReader {

    public static Consultation consultation;

    public TextFileReader(Consultation consultation) {
        this.consultation = consultation;
    }

    public void ImportData() throws FileNotFoundException, IOException {
        File drives[] = File.listRoots();
        String pid = null;

        //Loop through the drive list and store the data to the drives.
        for (int index = 3; index < drives.length; index++) {
            try {
                String fileName = drives[index] + "HealthRecord.txt";
                File file = new File(fileName);
                FileInputStream fstream = new FileInputStream(file);
                // Get the object of DataInputStream
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));

                //Read lines from the text file
                String filetxt = buf.readLine();
                String cpyFile_history = "";
                
                /** keluar pada 7 history **/
                while (filetxt != null) {
                    cpyFile_history += filetxt;
                    filetxt = buf.readLine();
                }

                S.oln("*[cpyFile_history: "+cpyFile_history+"]*");

                String tokenLine_history[] = cpyFile_history.split(">");
                System.out.println("Token number : " + tokenLine_history.length);

                this.consultation.resetTable();
                this.consultation.jTextArea7.setText("");

                int ccn = 0;
                int vts = 0;

                //S.oln("cpyFile_history: |---|*"+cpyFile_history+"*|---|");

                /** latest 7 history **/
                for (int i = 0; i < tokenLine_history.length; i++) {
                    String line = tokenLine_history[i].equals("") ? "-" : tokenLine_history[i]; //insert each line into string
                    String column[] = line.split("\\|");
                    int k = 0;
                    String TableType = column[k++].equals("") ? "-" : column[k - 1];//1st column is table name
                    if (TableType.contains("CCN")) {
                        String encounterDate = ""; try { encounterDate=column[k++].equals("")?"-":column[k-1]; } catch(Exception e) { encounterDate=""; } 
                        String dataField = ""; try { dataField=column[k++].equals("")?"-":column[k-1]; } catch(Exception e) { dataField=""; } 
                        String tokenData[] = dataField.split("\\^");
                        int k1 = 0;
                        String idProblem = ""; try { idProblem=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { idProblem=""; } 
                        String problem = ""; try { problem=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { problem=""; }
                        String sevCd = ""; try { sevCd=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { sevCd=""; } 
                        String severe = ""; try { severe=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { severe=""; } 
                        String site = ""; try { site=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { site=""; } 
                        String duration = ""; try { duration=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { duration=""; }
                        String laterality = ""; try { laterality=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { laterality=""; } 
                        String comment = ""; try { comment=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { comment=""; }
                        String probStatus = ""; try { probStatus=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { probStatus=""; } 
                        String drId = ""; try { drId=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { drId=""; } 
                        String drName = ""; try { drName=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { drName=""; } 
                        String date = ""; try { date=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { date=""; } 
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                        //java.util.Date parsedDate = dateFormat.parse(date);
                        //java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                        /**
                        * umar - 04012013 - start masuk history CCN *
                        */
                        this.consultation.tbl_cc.getModel().setValueAt(problem, ccn, 0);
                        this.consultation.tbl_cc.getModel().setValueAt(severe, ccn, 1);
                        this.consultation.tbl_cc.getModel().setValueAt(comment, ccn, 2);
                        this.consultation.tbl_cc.getModel().setValueAt(duration, ccn, 3);
                        this.consultation.tbl_cc.getModel().setValueAt(site, ccn, 4);
                        this.consultation.tbl_cc.getModel().setValueAt(laterality, ccn, 5);
                        ccn++;
                        /**
                        * umar - 04012013 - end masuk history CCN *
                        */
                    } else if (TableType.contains("VTS")) {
                        String dataField = ""; try { dataField=column[k++].equals("")?"-":column[k-1]; } catch(Exception e) { dataField=""; } 
                        String tokenData[] = dataField.split("\\^");
                        int k1 = 0;
                        double height = 0.0; try { height=Double.parseDouble(tokenData[k1++].equals("")?"-":tokenData[k1-1]); } catch(Exception e) { height=0.0; } 
                        double weight = 0.0; try { weight=Double.parseDouble(tokenData[k1++].equals("")?"-":tokenData[k1-1]); } catch(Exception e) { weight=0.0; } 
                        double BMI = 0.0; try { BMI=Double.parseDouble(tokenData[k1++].equals("")?"-":tokenData[k1-1]); } catch(Exception e) { BMI=0.0; } 
                        String weightStatus = ""; try { weightStatus=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { weightStatus=""; } 
                        String headCir = ""; try { headCir=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { headCir=""; } 
                        double temp = 0.0; try { temp=Double.parseDouble(tokenData[k1++].equals("")?"-":tokenData[k1-1]); } catch(Exception e) { temp=0.0; } 
                        double pulse = 0.0; try { pulse=Double.parseDouble(tokenData[k1++].equals("")?"-":tokenData[k1-1]); } catch(Exception e) { pulse=0.0; } 
                        String date = ""; try { date=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { date=""; } 
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                        //java.util.Date parsedDate = dateFormat.parse(date);
                        //java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//                        this.consultation.tbl_vts.getModel().setValueAt(height, vts, 0);
//                        this.consultation.tbl_vts.getModel().setValueAt(weight, vts, 1);
//                        this.consultation.tbl_vts.getModel().setValueAt(pulse, vts, 2);

                        vts++;
                    }

                }
                
                buf.close();
            } catch (Exception ex_last) {
                S.oln(ex_last.getMessage());
                ex_last.printStackTrace();
            }
        }
    }
}
