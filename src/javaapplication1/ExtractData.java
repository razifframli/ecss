/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Helper.S;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compudyne
 */
public class ExtractData {

    public static Consultation consultation;

    public String checkNull(String str) {
        if (str == null) {
            str = "-";
        }
        return str;
    }

    public void readClob(String data) {
        // String data = CISData[3]; //data clob
        //data;
        //break words by identify ">" symbol
        StringTokenizer tokenLine = new StringTokenizer(data, ">");//break words by identify ">" symbol

        System.out.println("CIS....txt_data..... : " + data);//number of line including header
        int CCID = 0;
        String pid = null;
        boolean redundant = false;
        boolean justInsert = false;
        for (int i = tokenLine.countTokens(); i > 0; i--) {

            String line = tokenLine.nextToken(); //insert each line into string
            S.oln("line #"+i+": "+line);
            // System.out.println(line);
            StringTokenizer column = new StringTokenizer(line, "|");// get on off status from header word
            String TableType = column.nextToken().trim();//1st column is table name
            System.out.println("...TableType....:" + TableType);

            consultation = new Consultation();
            if (TableType.equals("PDI")) {
                String dataField1 = column.nextToken();
                pid = dataField1;
                String dataField2 = column.nextToken();
                System.out.println(".......dataField1........." + dataField1);

                System.out.println(".......dataField2........." + dataField2);
                StringTokenizer tokenData = new StringTokenizer(dataField2, "^");
                S.oln("\n\n\n\ntokenData: "+tokenData+"\n\n\n\n");
                //pid = tokenData.nextToken();
                String name = tokenData.nextToken();
                String ic = tokenData.nextToken();
                String race = tokenData.nextToken();
                String sex = tokenData.nextToken();
                String dob = tokenData.nextToken();
                String pblood = tokenData.nextToken();
                String pstatus = tokenData.nextToken();
                System.out.println(pid + " " + name + " " + ic + " " + race + " " + sex + " " + dob + pblood + pstatus);

                consultation.txt_pPmiNo.setText(pid);
                consultation.txt_pName.setText(name);
                consultation.txt_pIcNo.setText(ic);
                consultation.txt_pRace.setText(race);
                consultation.txt_pSex.setText(sex);
                consultation.lblDOB.setText(dob);
                consultation.txt_pBloodSex.setText(pblood);
                consultation.txt_pStatus.setText(pstatus);
            } else if (TableType.equals("CCN")) {
                System.out.println("........CCN yes...........");
                String dataField = column.nextToken();
                System.out.println("....CCN...dataField........." + dataField);

                String dataField1 = column.nextToken();
                System.out.println("....CCN...dataField1........." + dataField1);

                StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                String problem = tokenData.nextToken();
                System.out.println("....CCN...problem........." + problem);
//                String codeSevere = tokenData.nextToken();
//                System.out.println("....CCN...codeSevere........." + codeSevere);
                
                S.oln("\n\n\n\n---- 90 ----\n\n\n\n");
                String severe = tokenData.nextToken();
                S.oln("\n\n\n\n---- 92 ----\n\n\n\n");
                
                String site = tokenData.nextToken();
                System.out.println("....CCN...site........." + site);
                String duration = tokenData.nextToken();
                System.out.println("....CCN...duration........." + duration);
                String laterality = tokenData.nextToken();
                System.out.println("....CCN...laterality........." + laterality);
                String comment = tokenData.nextToken();
                System.out.println("....CCN...comment........." + comment);
                String probStatus = tokenData.nextToken();
                System.out.println("....CCN...probStatus........." + probStatus);
                String date = tokenData.nextToken();
                System.out.println("....CCN...date........." + date);

//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                java.util.Date parsedDate = null;
//                try {
//                    parsedDate = dateFormat.parse(date);
//                } catch (ParseException ex) {
//                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//                
//                Connection conn = DatabaseConnection.MySQLconnect();
//               
//                String sql = "SELECT * FROM CHIEF_COMPLAINTS "
//                        + "WHERE ENCOUNTER_DATE=? "
//                        + "AND PMI_NO = ?";
//                PreparedStatement psRedundant = conn.prepareStatement(sql);
//                psRedundant.setTimestamp(1, timestamp);
//                psRedundant.setString(2, pid);
//                ResultSet rsRedundant = psRedundant.executeQuery();
//
//                if (rsRedundant.next()) {
//                    redundant = true;
//                    System.out.println("REDUNDANT DATA");
//                } else {
//                    redundant = false;
//                }
//
//                if (redundant == false || justInsert == true) {
//
//                    System.out.println(pid + " " + problem + " " + severe + " " + site + " " + duration + " " + laterality + " " + comment + " " + probStatus);
//                    sql = "INSERT INTO CHIEF_COMPLAINTS (PMI_NO,PROBLEM,SEVERITY,SITE,DURATION,LATERALITY,C_COMMENT,PROBLEM_STATUS,ENCOUNTER_DATE)VALUES (?,?,?,?,?,?,?,?,?)";
//                    PreparedStatement ps = null;
//                    ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//                    ps.setString(1, pid);
//                    ps.setString(2, problem);
//                    ps.setString(3, severe);
//                    ps.setString(4, site);
//                    ps.setString(5, duration);
//                    ps.setString(6, laterality);
//                    ps.setString(7, comment);
//                    ps.setString(8, probStatus);
//                    ps.setTimestamp(9, timestamp);
//                    int status = ps.executeUpdate();
//
//                    if (status != 0) {
//                        ResultSet rs = ps.getGeneratedKeys();
//                        while (rs.next()) {
//                            CCID = rs.getInt(1);
//                        }
//                    }
//                    System.out.println("CCID" + CCID);
//                    justInsert = true;
//                }

            } else if (TableType.equals("DGS")) {
                System.out.println("DGS yes");
                String dataField = column.nextToken();
                StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                String dsgType = tokenData.nextToken();
                String dsgsev = tokenData.nextToken();
                String diagDate = tokenData.nextToken();
//                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
//                java.util.Date parsedDate1=null;
//                try {
//                    parsedDate1 = dateFormat1.parse(diagDate);
//                } catch (ParseException ex) {
//                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                java.sql.Date date1 = new java.sql.Date(parsedDate1.getTime());
//
//                String date = tokenData.nextToken();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                java.util.Date parsedDate = dateFormat.parse(date);
//                java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//
//                if (redundant == false || justInsert == true) {
//                    System.out.println(pid + " " + dsgType + " " + dsgsev);
//                    PreparedStatement ps = conn.prepareStatement("INSERT INTO DIAGNOSIS (PMI_NO,DIAGNOSIS_TYPE,SEVERITY,DIAG_DATE,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?)");
//                    ps.setString(1, pid);
//                    ps.setString(2, dsgType);
//                    ps.setString(3, dsgsev);
//                    ps.setDate(4, date1);//05/09/2005
//                    ps.setTimestamp(5, timestamp);
//                    ps.setInt(6, CCID);
//                    ps.execute();
//                }

            } else if (TableType.equals("IMU")) {
                try {
                    System.out.println("IMU yes");
                    String dataField = column.nextToken();
                    StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                    String IMUtype = tokenData.nextToken();
                    String IMUcomment = tokenData.nextToken();
                    String imuDate = tokenData.nextToken();
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date parsedDate1 = dateFormat1.parse(imuDate);
                    java.sql.Date imuDate1 = new java.sql.Date(parsedDate1.getTime());

                    String date = tokenData.nextToken();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                    //                if (redundant == false || justInsert == true) {
                    //                    System.out.println(pid + " " + IMUtype + " " + IMUcomment);
                    //                    PreparedStatement ps = conn.prepareStatement("INSERT INTO IMMUNISATION (PMI_NO,IMMU_TYPE,IMMU_COMMENT,IMMU_DATE,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?)");
                    //                    ps.setString(1, pid);
                    //                    ps.setString(2, IMUtype);
                    //                    ps.setString(3, IMUcomment);
                    //                    ps.setDate(4, imuDate1);
                    //                    ps.setTimestamp(5, timestamp);
                    //                    ps.setInt(6, CCID);
                    //                    ps.execute();
                    //                }
                } catch (ParseException ex) {
                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (TableType.equals("VTS")) {
                try {
                    System.out.println("VTS yes");
                    String dataField = column.nextToken();
                    StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                    double height = Double.parseDouble(tokenData.nextToken());
                    double weight = Double.parseDouble(tokenData.nextToken());
                    double BMI = Double.parseDouble(tokenData.nextToken());
                    String weightStatus = tokenData.nextToken();
                    String headCir = tokenData.nextToken();
                    double temp = Double.parseDouble(tokenData.nextToken());
                    double pulse = Double.parseDouble(tokenData.nextToken());
                    String date = tokenData.nextToken();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                    //                if (redundant == false || justInsert == true) {
                    //                    System.out.println(height + " " + weight + " " + BMI + " " + weightStatus + " " + headCir + " " + temp + " " + pulse + " " + date);
                    //                    PreparedStatement ps = conn.prepareStatement("INSERT INTO Vital_Sign (PMI_NO,HEIGHT,WEIGHT,BMI,WEIGHT_STATUS,HEAD_CIRCUM,TEMP,PULSE,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?,?,?,?,?)");
                    //                    ps.setString(1, pid);
                    //                    ps.setDouble(2, height);
                    //                    ps.setDouble(3, weight);
                    //                    ps.setDouble(4, BMI);
                    //                    ps.setString(5, weightStatus);
                    //                    ps.setString(6, headCir);
                    //                    ps.setDouble(7, temp);
                    //                    ps.setDouble(8, pulse);
                    //                    ps.setTimestamp(9, timestamp);
                    //                    ps.setInt(10, CCID);
                    //                    ps.execute();
                    //                }
                } catch (ParseException ex) {
                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (TableType.equals("DRU")) {
                try {
                    System.out.println("DRUG yes");
                    String dataField = column.nextToken();
                    System.out.println("DRUG yes333");
                    StringTokenizer tokenData = new StringTokenizer(dataField, "^");
                    System.out.println("DRUG yes4444");
                    String drug = tokenData.nextToken();
                    String product = tokenData.nextToken();
                    String dose = tokenData.nextToken();
                    String quantity = tokenData.nextToken();
                    String form = tokenData.nextToken();
                    String duration = tokenData.nextToken();
                    String freq = (tokenData.nextToken()).toString();
                    String instuct = (tokenData.nextToken()).toString();
                    System.out.println(form + "  DRUG yes7777");
                    String date = tokenData.nextToken();
                    System.out.println("DRUG yes2");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                    //                if (redundant == false || justInsert == true) {
                    //                    System.out.println("DRUG yes3333");
                    //                    System.out.println(pid + " " + drug + product + " " + dose + " " + quantity + " " + form + " " + duration + " " + freq + " " + instuct);
                    //
                    //                    PreparedStatement ps = conn.prepareStatement("INSERT INTO DRUG_ADDICT (PMI_NO,DRUG_NAME,PRODUCT_NAME,DOSE,QUANTITY,FORM,DURATION,FREQUENCY,INSTRUCTION,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    //                    ps.setString(1, pid);
                    //                    ps.setString(2, drug);
                    //                    ps.setString(3, product);
                    //                    ps.setString(4, dose);
                    //                    ps.setString(5, quantity);
                    //                    ps.setString(6, form);
                    //                    ps.setString(7, duration);
                    //                    ps.setString(8, freq);
                    //                    ps.setString(9, instuct);
                    //                    ps.setTimestamp(10, timestamp);
                    //                    ps.setInt(11, CCID);
                    //                    ps.execute();
                    //                    System.out.println("DRUG yes55");
                    //                }
                } catch (ParseException ex) {
                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (TableType.equals("DIS")) {
                try {
                    System.out.println("DIS yes");
                    String dataField = column.nextToken();
                    StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                    String DISname = tokenData.nextToken();
                    String DISdate = tokenData.nextToken();
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date parsedDate1 = dateFormat1.parse(DISdate);
                    java.sql.Date disDate = new java.sql.Date(parsedDate1.getTime());

                    String DIScom = tokenData.nextToken();
                    String date = tokenData.nextToken();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                    //                if (redundant == false || justInsert == true) {
                    //                    System.out.println(pid + " " + DISname + " " + DISdate + " " + DIScom);
                    //                    PreparedStatement ps = conn.prepareStatement("INSERT INTO DISABILITY (PMI_NO,DIS_NAME,DIS_FROMDATE,DIS_COM,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?)");
                    //                    ps.setString(1, pid);
                    //                    ps.setString(2, DISname);
                    //                    ps.setDate(3, disDate);
                    //                    ps.setString(4, DIScom);
                    //                    ps.setTimestamp(5, timestamp);
                    //                    ps.setInt(6, CCID);
                    //                    ps.execute();
                    //                }
                } catch (ParseException ex) {
                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (TableType.equals("ALG")) {
                try {
                    System.out.println("ALG yes");
                    String dataField = column.nextToken();
                    StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                    String ALGname = tokenData.nextToken();
                    String ALGdate = tokenData.nextToken();
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    S.oln("\n\n\n\nALGdate 348: "+ALGdate+"\n\n\n\n");
                    java.util.Date parsedDate1 = dateFormat1.parse(ALGdate);
                    java.sql.Date algDate = new java.sql.Date(parsedDate1.getTime());

                    String ALGcom = tokenData.nextToken();
                    String date = tokenData.nextToken();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                    //                if (redundant == false || justInsert == true) {
                    //                    System.out.println(pid + " " + ALGname + " " + ALGdate + " " + ALGcom);
                    //                    PreparedStatement ps = conn.prepareStatement("INSERT INTO ALLERGY (PMI_NO,ALL_NAME,ALL_FROMDATE,ALL_COM,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?)");
                    //                    ps.setString(1, pid);
                    //                    ps.setString(2, ALGname);
                    //                    ps.setDate(3, algDate);
                    //                    ps.setString(4, ALGcom);
                    //                    ps.setTimestamp(5, timestamp);
                    //                    ps.setInt(6, CCID);
                    //                    ps.execute();
                    //                }
                } catch (ParseException ex) {
                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (TableType.equals("SH")) {
                try {
                    System.out.println("SH yes");
                    String dataField = column.nextToken();
                    StringTokenizer tokenData = new StringTokenizer(dataField, "^");

                    String SHname = tokenData.nextToken();
                    String SHdate = tokenData.nextToken();
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date parsedDate1 = dateFormat1.parse(SHdate);
                    java.sql.Date shDate = new java.sql.Date(parsedDate1.getTime());

                    String SHcom = tokenData.nextToken();
                    String date = tokenData.nextToken();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                    //                if (redundant == false || justInsert == true) {
                    //                    System.out.println(pid + " " + SHname + " " + SHdate + " " + SHcom);
                    //                    PreparedStatement ps = conn.prepareStatement("INSERT INTO SOCIAL_HISTORY (PMI_NO,SH_NAME,SH_FROMDATE,SH_COM,ENCOUNTER_DATE,CC_ID)VALUES (?,?,?,?,?,?)");
                    //                    ps.setString(1, pid);
                    //                    ps.setString(2, SHname);
                    //                    ps.setDate(3, shDate);
                    //                    ps.setString(4, SHcom);
                    //                    ps.setTimestamp(5, timestamp);
                    //                    ps.setInt(6, CCID);
                    //                    ps.execute();
                    //                }
                } catch (ParseException ex) {
                    Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }


    }
}