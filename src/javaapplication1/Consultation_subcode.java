/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import Bean.PhysicalExamBean;
import DBConnection.DBConnection;
import Helper.J;
import Helper.S;
import Helper.Session;
import api.Queue;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static javaapplication1.Consultation.max_row;
import static javaapplication1.Consultation.note_array;
import static javaapplication1.Consultation.ps;
import static javaapplication1.Consultation.row_count;
import javax.swing.JOptionPane;
import library.Func;

/**
 *
 * @author End User
 */
public class Consultation_subcode {
    
    public static void btn_discharge(Consultation cons) {
        String message = "Are you sure want to Discharge this data?";
        String title = "Consultation";
        int reply = JOptionPane.showConfirmDialog(null, message, title,
                JOptionPane.YES_NO_OPTION);
        if (reply != JOptionPane.YES_OPTION) {
            return;
        }
        cons.vph.setVisible(false);
        cons.que.setVisible(false);
        try {
//            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
            DriversLocation driverLocation = new DriversLocation();
//            Timestamp date = timestamp;
            
            Date date1 = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(date1) + " " + Consultation.EpisodeTime;

            String PMI = cons.txt_pPmiNo.getText();
            String name = cons.txt_pName.getText();
            String IC = cons.txt_pIcNo.getText();
            String race = cons.txt_pRace.getText();
            String sex = cons.txt_pSex.getText();
            String DOB = cons.lblDOB.getText();
            String blood = cons.txt_pBloodSex.getText();
            String pstatus = cons.txt_pStatus.getText();

            String header = "MSH|^~|CIS|"
                    +Session.getHfc_code()+"^"
                    +Session.getDiscipline()+"^"
                    +Session.getSubdiscipline()+"|"
                    +"CIS|"
                    +Session.getHfc_code()+"^"
                    +Session.getDiscipline()+"^"
                    +Session.getSubdiscipline()+"|"
                    +date+"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    +"|"
                    + "<cr>" + "\n";
            String patientInfo = "PDI|" + PMI + "|" + name + "^" + IC + "^" + race + "^" + sex + "^" + DOB + "^" + blood + "^" + pstatus + "^" + "|" + "<cr>" + "\n";
            String msgs[] = new String[200];
            for (int zz = 0; zz < 200; zz++) {
                msgs[zz] = "";
            }

            try {
                cons.destroyPatientQueue(PMI);
            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean stat_dto = false;

            for (int zz = 0, ii = 0; zz < max_row && ii < 200; zz++, ii++) {
                try {
                    S.oln(note_array[zz]);
                    if (note_array[zz].equals("C.Complaint")) {
                        zz++;
                        String searchcbx = note_array[zz++].split(": ")[1];
                        String severity = note_array[zz++].split(": ")[1];
                        String site = note_array[zz++].split(": ")[1];
                        String durationtxt = note_array[zz].split(": ")[1].split(" ")[0];
                        String durationcbx = note_array[zz++].split(": ")[1].split(" ")[1];
                        String lateralitycbx = note_array[zz++].split(": ")[1];
                        String cmmt = note_array[zz++].split(": ")[1];
                        String SnomedID = "-";
                        String selectedRbtn = "Active";
                        //                        if (rbtn_active.isSelected()) {
                        //                            selectedRbtn = "Active";
                        //                        }
                        //                        if (rbtn_inactive.isSelected()) {
                        //                            selectedRbtn = "In Active";
                        //                        }
                        try {
                            //tempQuery = "SELECT SNOMEDID FROM SNOMEDCCOMPLAINTS where SNOMEDDESC like '%" + searchcbx + "%' ";
                            //                            tempQuery = "SELECT RCC_CODE FROM READCODE_CHIEF_COMPLAINT "
                            //                                    + "where UCASE(RCC_DESC) like UCASE(?) ";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + searchcbx + "%");
                            cons.rs = ps.executeQuery();
                            if (cons.rs.next()) {
                                SnomedID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            SnomedID,
                            searchcbx,
                            cons.getSeverity(severity),
                            severity,
                            durationtxt + " " + durationcbx,
                            "",
                            "",
                            cons.getSide(site),
                            site,
                            cons.getLaterality(lateralitycbx),
                            lateralitycbx,
                            cmmt,
                            date.toString(),
                            selectedRbtn,
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name(),
                            "ICD10",
                            SnomedID,
                            searchcbx
                        };
                        msgs[ii] = "CCN|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                        S.oln(msgs[ii]);
                    } else if (note_array[zz].equals("History Of Present Illness")) {
                        zz++;
                        String data[] = {
                            note_array[zz++].split(": ")[1],
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "HPI|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Past Medical History")) {
                        zz++;
                        String ProblemID = "-";
                        String Problem = note_array[zz++].split(": ")[1]; //Problem
                        String Status = note_array[zz++].split(": ")[1]; //Status
                        String Details = note_array[zz++].split(": ")[1]; //Details
                        try {
                            //                            tempQuery = "SELECT RPMH_CODE "
                            //                                    + "FROM READCODE_PAST_MEDICAL_HISTORY "
                            //                                    + "WHERE RPMH_DESC like ?";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Problem + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ProblemID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            ProblemID,
                            Problem,
                            Status,
                            Status,
                            date.toString(),
                            Details,
                            "",
                            "",
                            date.toString(),
                            Status,
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "PMH|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Family History")) {
                        zz++;
                        String ProblemID = "-";
                        String Problem = note_array[zz++].split(": ")[1]; //Problems
                        String Relationship = note_array[zz++].split(": ")[1]; //Relationship
                        String cmmt = note_array[zz++].split(": ")[1]; //Comments
                        try {
                            //                            tempQuery = "SELECT RPMH_CODE "
                            //                                    + "FROM READCODE_PAST_MEDICAL_HISTORY "
                            //                                    + "WHERE RPMH_DESC like ?";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Problem + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ProblemID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            Relationship,
                            "",
                            "",
                            ProblemID,
                            Problem,
                            date.toString(),
                            cmmt,
                            "",
                            "",
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "FMH|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Social History")) {
                        zz++;
                        String ProblemID = "-";
                        String Problem = note_array[zz++].split(": ")[1]; //Problem
                        String Comment = note_array[zz++].split(": ")[1]; //Comment
                        String Date = note_array[zz++].split(": ")[1]; //Date
                        String scl_snomedID = "";
                        try {
                            cons.tempQuery = "SELECT RSH_CODE FROM READCODE_SOCIAL_HISTORY "
                                    + "WHERE RSH_DESC LIKE ?";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Problem + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ProblemID = cons.rs.getString("RSH_CODE");
                            }
                        } catch (Exception ex) {
                            S.oln(ex.getMessage());
                        }
                        String data[] = {
                            ProblemID,
                            Problem,
                            "",
                            "",
                            "",
                            "",
                            "",
                            Date,
                            "",
                            "",
                            "",
                            Comment,
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "SOH|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Vital Sign")) {
                        zz++;
                        
                        String gcs_points = note_array[zz++].split(": ")[1].split(" ")[0];
                        String gcs_result = note_array[zz++].split(": ")[1].split(" ")[0];
                        String pgcs_points = note_array[zz++].split(": ")[1].split(" ")[0];
                        String pgcs_result = note_array[zz++].split(": ")[1].split(" ")[0];
                        String respiratory_rate = note_array[zz++].split(": ")[1].split(" ")[0];
                        String oxygen_saturation = note_array[zz++].split(": ")[1].split(" ")[0];
                        String pain_scale = note_array[zz++].split(": ")[1].split(" ")[0];
                        
                        String hei = note_array[zz++].split(": ")[1].split(" ")[0];
                        String wei = note_array[zz++].split(": ")[1].split(" ")[0];
                        String bmi = note_array[zz++].split(": ")[1].split(" ")[0];
                        String sta = note_array[zz++].split(": ")[1].split(" ")[0];
                        String hea = note_array[zz++].split(": ")[1].split(" ")[0];
                        String tem = note_array[zz++].split(": ")[1].split(" ")[0];
                        String sys = note_array[zz++].split(": ")[1].split(" ")[0];
                        String dis = note_array[zz++].split(": ")[1].split(" ")[0];
                        String pul = note_array[zz++].split(": ")[1].split(" ")[0];
                        String sys1 = note_array[zz++].split(": ")[1].split(" ")[0];
                        String dis1 = note_array[zz++].split(": ")[1].split(" ")[0];
                        String pul1 = note_array[zz++].split(": ")[1].split(" ")[0];
                        String sys2 = note_array[zz++].split(": ")[1].split(" ")[0];
                        String dis2 = note_array[zz++].split(": ")[1].split(" ")[0];
                        String pul2 = note_array[zz++].split(": ")[1].split(" ")[0];
                        
                        String blood_glucose = note_array[zz++].split(": ")[1].split(" ")[0];
                        
                        String data[] = {
                            tem, //1
                            sys, //2
                            dis, //3
                            sys2, //4
                            dis2, //5
                            sys1, //6
                            dis1, //7
                            wei, //8
                            hei, //9
                            hea, //10
                            respiratory_rate, //11
                            "", //12
                            pul+","+pul1+","+pul2, //13
                            "", //14
                            "", //15
                            "", //16
                            "", //17
                            "", //18
                            "", //19
                            "", //20
                            "", //21
                            date.toString(), //22
                            Session.getHfc_code(), //23
                            Session.getUser_id(), //24
                            Session.getUser_name(), //25
                            
                            gcs_points, //26
                            gcs_result, //27
                            pgcs_points, //28
                            pgcs_result, //29
                            oxygen_saturation, //30
                            pain_scale, //31
                            
                            blood_glucose //32
                        };
                        msgs[ii] = "VTS|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Blood Group / G6PD")) {
                        zz++;
                        String Blood = note_array[zz++].split(": ")[1];
                        String Rhesus = note_array[zz++].split(": ")[1];
                        String G6PD = note_array[zz++].split(": ")[1];
                        String Comment = note_array[zz++].split(": ")[1];

                        String data[] = {
                            Blood,
                            Rhesus,
                            G6PD,
                            Comment
                        };
                        msgs[ii] = "BLD|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "|";
                        }
                        msgs[ii] += "<cr>" + "\n";
                    } else if (note_array[zz].equals("Allergy")) {
                        zz++;
                        String Type = note_array[zz++].split(": ")[1];
                        String Comment = note_array[zz++].split(": ")[1];
                        String Date = note_array[zz++].split(": ")[1];
                        String ID = "-";
                        try {
                            //                            tempQuery = "SELECT RA_CODE "
                            //                                    + "FROM READCODE_ALLERGY "
                            //                                    + "WHERE RA_DESC like ?";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Type + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            ID,
                            Type,
                            Date,
                            Comment,
                            "",
                            "",
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "ALG|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Immunisation")) {
                        zz++;
                        String ID = "-";
                        String Type = note_array[zz++].split(": ")[1]; //Type
                        String Comment = note_array[zz++].split(": ")[1]; //Comment
                        String Date = note_array[zz++].split(": ")[1]; //Date
                        try {
                            //                            tempQuery = "SELECT RI_CODE "
                            //                                    + "FROM READCODE_IMMUNIZATION "
                            //                                    + "WHERE RI_DESC like ?";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Type + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            ID,
                            Type,
                            Date,
                            Comment,
                            "",
                            "",
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "IMU|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Disability")) {
                        zz++;
                        String ID = "-";
                        String Type = note_array[zz++].split(": ")[1]; //Type
                        String Comment = note_array[zz++].split(": ")[1]; //Comment
                        String Date = note_array[zz++].split(": ")[1]; //Date
                        try {
                            //                            tempQuery = "SELECT RD_CODE FROM READCODE_DISABILITY "
                            //                                    + "WHERE RD_DESC LIKE ?";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Type + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            ID,
                            Type,
                            Date
                        };
                        msgs[ii] = "DAB|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Diagnosis")) {
                        zz++;
                        String ID = "-";
                        String Type = note_array[zz++].split(": ")[1];
                        String Code = note_array[zz++].split(": ")[1];
                        String Diagnosis = note_array[zz++].split(": ")[1];
                        String Severity = note_array[zz++].split(": ")[1];
                        String Site = note_array[zz++].split(": ")[1];
                        String Laterality = note_array[zz++].split(": ")[1];
                        String Comment = note_array[zz++].split(": ")[1];
                        String Date = note_array[zz++].split(": ")[1];
                        try {
                            //                            tempQuery = "SELECT RCC_CODE FROM READCODE_CHIEF_COMPLAINT "
                            //                                    + "where RCC_DESC like ? ";
                            cons.tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + Diagnosis + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                ID = cons.rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            Code,
                            Type,
                            "",
                            Date,
                            ID,
                            Diagnosis,
                            ID,
                            Diagnosis,
                            "",
                            Severity,
                            "",
                            Site,
                            "",
                            "",
                            "",
                            Laterality,
                            "",
                            "",
                            Comment,
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "DGS|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Progress Notes")) {
                        zz++;
                        String Notes = note_array[zz++].split(": ")[1];
                        String data[] = {
                            Notes,
                            "",
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "PNT|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Drugs")) {
                        zz++;
                        String ProblemCode = note_array[zz++].split(": ")[1];
                        String ProblemDesc = note_array[zz++].split(": ")[1];
                        String ActiveIngredient = note_array[zz++].split(": ")[1];
                        String ProductName = note_array[zz++].split(": ")[1];
                        String Dose = note_array[zz++].split(": ")[1];
                        String Quantity = note_array[zz++].split(": ")[1];
                        String DrugForm = note_array[zz++].split(": ")[1];
                        String Duration = note_array[zz++].split(": ")[1];
                        String Frequency = note_array[zz++].split(": ")[1];
                        String Instruction = note_array[zz++].split(": ")[1];
                        String UD_MDC_Code = "";
                        String Cautionary = note_array[zz++].split(": ")[1];
                        String packType = note_array[zz++].split(": ")[1];
                        try {
                            //                            tempQuery = "SELECT UD_MDC_CODE "
                            //                                    + "FROM PIS_MDC "
                            //                                    + "WHERE ACTIVE_INGREDIENT_CODE LIKE ? "
                            //                                    + "AND DRUG_PRODUCT_NAME LIKE ? ";
                            cons.tempQuery = "SELECT UD_MDC_CODE "
                                    + "FROM PIS_MDC2 "
                                    + "WHERE UCASE(D_GNR_NAME) LIKE UCASE(?) "
                                    + "OR UCASE(D_TRADE_NAME) LIKE UCASE(?) ";
                            ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                            ps.setString(1, "%" + ActiveIngredient + "%");
                            ps.setString(2, "%" + ProductName + "%");
                            cons.rs = ps.executeQuery();
                            while (cons.rs.next()) {
                                UD_MDC_Code = cons.rs.getString("UD_MDC_CODE");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        
                        String qty_drug = Quantity;
                        if (packType.equals("CAP") || packType.equals("TAB")) {
                            qty_drug = cons.getDrugQuantity(cons.getFrequencyCode(Frequency), Quantity, cons.getDayDrugCode(Duration));
                        }
                        
                        String data[] = {
                            ProblemCode + "^" + ProblemDesc + "^" + ProblemCode,
                            UD_MDC_Code + "^" + ProductName + "-"+ActiveIngredient + "^" + UD_MDC_Code,
                            "" + "^" + DrugForm + "^" + "",
                            "" + "^" + "" + "^" + "",
                            "" + "^" + Frequency + "^" + "",
                            cons.getFrequencyCode(Frequency),
                            Quantity,
                            Dose,
                            "" + "^" + "" + "^" + Dose,
                            cons.getDayDrugCode(Duration),
                            qty_drug,
                            "" + "^" + "" + "^" + "",
                            Instruction,
                            "" + "^" + Session.getHfc_code()  + "^"
                            + "" + "^" + Session.getUser_id() + "^"   //20141218 Hariz --> if data inserted at wrong column, might need to put at last 
                            + "" + "^" + Session.getDiscipline() + "^"
                            + "" + "^" + Session.getDiscipline(),
                            Cautionary
                        };
                        msgs[ii] = "DTO|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "|";
                        }
                        msgs[ii] += "<cr>" + "\n";

                        stat_dto = true;

                    } else if (note_array[zz].equals("Physical Examination")) {
                        zz++;
                        String pe_exam_line = note_array[zz++].split(": ")[1];
                        String pe_comments = note_array[zz++].split(": ")[1];
                        String pe_exam = pe_exam_line.split(Func.SEPARATOR_LINK)[pe_exam_line.split(Func.SEPARATOR_LINK).length-1];
                        String pe_cd = "";
                        for (int level = 1; level <= Func.NUM_LEVEL_PHYSICAL_EXAMINATION; level++) {
                            PhysicalExamBean pe = new PhysicalExamBean();
                            pe.setPe_name(pe_exam);
                            PhysicalExamBean getPhysicalExam2 = DBConnection.getPhysicalExam2(level, pe);
//                            ArrayList<String> procedure_detail = DBConnection.getProcedureDetail2(level, procedure_desc);
                            if (getPhysicalExam2.getPe_cd() != null && getPhysicalExam2.getPe_cd().length() > 0) {
                                pe_cd = getPhysicalExam2.getPe_cd();
                            }
                        }
//                        String tekak = note_array[zz++].split(": ")[1];
//                        String jantung = note_array[zz++].split(": ")[1];
//                        String peparuKanan = note_array[zz++].split(": ")[1];
//                        String bahuKanan = note_array[zz++].split(": ")[1];
//                        String bahuKiri = note_array[zz++].split(": ")[1];
//                        String perut = note_array[zz++].split(": ")[1];
//                        String kepala = note_array[zz++].split(": ")[1];
//                        String hidung = note_array[zz++].split(": ")[1];
//                        String mulut = note_array[zz++].split(": ")[1];
//                        String telingaKanan = note_array[zz++].split(": ")[1];
                        String data[] = {
                            "",
                            "",
                            Func.getCodePemToDB(pe_cd),
                            pe_exam,
//                            tekak + "^" + jantung + "^" + peparuKanan + "^" + bahuKanan + "^"
//                            + bahuKiri + "^" + perut + "^" + kepala + "^"
//                            + hidung + "^" + mulut + "^" + telingaKanan,
                            "",
                            pe_comments,
                            "",
                            "",
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "PEM|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                    } else if (note_array[zz].equals("Laboratory Investigation Order")) {
                        zz++;
                        String notes = note_array[zz++].split(": ")[1];
                        String subdiscipline = note_array[zz++].split(": ")[1];
                        String discipline = note_array[zz++].split(": ")[1];
                        String hfc = note_array[zz++].split(": ")[1];
                        String condition = note_array[zz++].split(": ")[1];
                        String priority = note_array[zz++].split(": ")[1];
                        String test = note_array[zz++].split(": ")[1];
                        String problem = note_array[zz++].split(": ")[1];
                        String problemCode = note_array[zz++].split(": ")[1];
                        String type = note_array[zz++].split(": ")[1];
                        String data[] = {
                            problemCode + "^" + problem + "^" + "",
                            "" + "^" + test + "^" + "",
                            date.toString(),
                            "" + "^" + "" + "^" + priority,
                            "" + "^" + "" + "^" + condition,
                            "" + "^" + hfc + "&" + discipline + "&" + subdiscipline + "^" + "",
                            notes,
                            "" + "^" + Session.getHfc_code() + "^"
                            + "" + "^" + Session.getDiscipline() + "^"
                            + "" + "^" + Session.getDiscipline()
                        };
                        msgs[ii] = "LIO|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "|";
                        }
                        msgs[ii] += "<cr>" + "\n";
                    } else if (note_array[zz].equals("Medical Certification (MC)")) {
                        zz++;
                        String diagnosisCode = note_array[zz++].split(": ")[1];
                        String diagnosisDesc = note_array[zz++].split(": ")[1];
                        String timeFrom = note_array[zz++].split(": ")[1];
                        String timeTo = note_array[zz++].split(": ")[1];
                        String dateFrom = note_array[zz++].split(": ")[1];
                        String dateTo = note_array[zz++].split(": ")[1];
                        String data[] = {
                            diagnosisCode,
                            diagnosisDesc,
                            "",
                            "",
                            diagnosisCode,
                            diagnosisDesc,
                            "",
                            "",
                            "",
                            timeFrom,
                            timeTo,
                            dateFrom,
                            dateTo,
                            date.toString(),
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "MEC|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "<cr>" + "\n";
                    } else if (note_array[zz].equals("Procedure Order")) {
                        zz++;
                        String diagnosisCode = note_array[zz++].split(": ")[1];
                        String diagnosisDesc = note_array[zz++].split(": ")[1];
                        String procedure_desc_line = note_array[zz++].split(": ")[1];
                        String procedure_desc = procedure_desc_line.split(Func.SEPARATOR_LINK)[procedure_desc_line.split(Func.SEPARATOR_LINK).length-1];
                        String procedure_cd = "";
                        for (int level = 1; level <= Func.NUM_LEVEL_PROCEDURE; level++) {
                            ArrayList<String> procedure_detail = DBConnection.getProcedureDetail2(level, procedure_desc);
                            if (procedure_detail.size() > 0) {
                                procedure_cd = procedure_detail.get(0);
                            }
                        }
                        String data[] = {
                            diagnosisCode+"^"+diagnosisDesc+"^"+diagnosisCode,
                            procedure_cd+"^"+procedure_desc+"^"+procedure_cd,
                            date.toString(),
                            "A",
                            "1",
                            "surgeon id",
                            "surgeon name",
                            "",
                            "place of surgeon code",
                            "place of surgeon name",
                            "notes",
                            Session.getHfc_code()
                        };
                        msgs[ii] = "POS|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "|";
                        }
                        msgs[ii] += "<cr>" + "\n";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //create prescription
            Date date_temp = new Date(new java.util.Date().getTime());
            String data_temp = header + patientInfo;
            for (int i = 0; i < msgs.length; i++) {
                if (msgs[i].length() > 0) {
                    data_temp += msgs[i];
                }
            }

            //            if (stat_dto) {
            //                //PrescriptionNote pn = new PrescriptionNote();
            //                //pn.setVisible(true);
            //                PDFiText.createPrescription("Presription_.pdf", data_temp);
            //            }
            String msg_discharge = "";

            /**
             * start discharge
             */
            //            LongRunProcess.change_status_network2();
            // }
            //Insert data into database
            //            if (Session.getPrev_stat())//Online
            //            {
            //Insert into Server
            System.out.println("Online - #########  Insert into Local  #########");

            System.out.println("..Offline...friza 2.... ");

            //Formatted Message store into Journal File and thumb drives
            if ((cons.MItem_local.isSelected() == true)
                    && (cons.MItem_drives.isSelected() == true)) {
                System.out.println("...Insert both...thumb and local db");

                //  ehr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
                //     driverLocation.insertToDrive(header, patientInfo, (String) enumTabA.nextElement(), (String) enumTabB.nextElement(), (String) enumTabC.nextElement(), (String) enumTabD.nextElement(), (String) enumTabE.nextElement(), (String) enumTabF.nextElement(), (String) enumTabG.nextElement(), (String) enumTabH.nextElement());
                /*umar - while to if*/
                if (msgs.length > 0) {
                    System.out.println("Insert both..");
                    try {

                        //Friza - insert CIS to server-MySql
                        //ehr.insertCentral(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
                        System.out.println("After insert local..");

                        //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
                        cons.ehr.insertJournal(1, header, patientInfo,
                                msgs,
                                PMI, date);
                        driverLocation.insertToDrive(1, header, patientInfo,
                                msgs);

                        //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                msg_discharge += "Patient record is saved into local database and thumb drive.\n";
            } //Formatted message store into thumb drives by invoked insertToDrive method
            else if (cons.MItem_drives.isSelected() == true) {
                System.out.println("external");
                if (msgs.length > 0) {
                    System.out.println("external inside");
                    driverLocation.insertToDrive(1, header, patientInfo,
                            msgs);
                    //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
                }

                msg_discharge += "Patient record is saved into thumb drive.\n";
            } //Formatted Message store into Journal File
            else if (cons.MItem_local.isSelected() == true) {
                System.out.println("local");

                if (msgs.length > 0) {
                    cons.ehr.insertJournal(1, header, patientInfo,
                            msgs, PMI, date);
                    //Friza
                    //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);

                    //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
                }
                msg_discharge += "Patient record is saved into local database.\n";
            }
            cons.lblStatus.setText("Online");

            S.oln("Online - #########  Insert into Server #########");

            if (msgs.length > 0) {
                //Insert to local after Discharge
                //ehr.insertCentral(header, patientInfo, (String)enumTab1.nextElement(), (String)enumTab2.nextElement(),(String)enumTab3.nextElement(),(String)enumTab4.nextElement(),(String)enumTab5.nextElement(),(String)enumTab6.nextElement(),(String)enumTab7.nextElement(),(String)enumTab8.nextElement(), PMI);
                // ehr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);

                //ehr.insertJournal(header, patientInfo, checkNull(enumTab1.nextElement()), checkNull(enumTab2.nextElement()), checkNull(enumTab3.nextElement()), checkNull(enumTab4.nextElement()), checkNull(enumTab5.nextElement()), checkNull(enumTab6.nextElement()), checkNull(enumTab7.nextElement()), checkNull(enumTab8.nextElement()), PMI);
                //Friza - insert CIS to server-MySql
                //umar - cek setiap enumTab.nextElement tu tngok ada data atau x..
                cons.ehr.insertCentral(1, header, patientInfo,
                        msgs, PMI, date);

                //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
            }

            System.out.println("Online - .....After insert Journal...");

            msg_discharge += "Patient record is saved into local database.\n";

            //Go offline
            //                Session.setPrev_stat(false);
            //                Session.setCurr_stat(false);
            //Session.setCon_x();
            //            } else//Offline
            //            {
            //                System.out.println("..Offline...friza 2.... ");
            //
            //                //Formatted Message store into Journal File and thumb drives
            //                if ((MItem_local.isSelected() == true)
            //                        && (MItem_drives.isSelected() == true)) {
            //                    System.out.println("...Insert both...thumb and local db");
            //
            //
            //                    //  ehr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
            //                    //     driverLocation.insertToDrive(header, patientInfo, (String) enumTabA.nextElement(), (String) enumTabB.nextElement(), (String) enumTabC.nextElement(), (String) enumTabD.nextElement(), (String) enumTabE.nextElement(), (String) enumTabF.nextElement(), (String) enumTabG.nextElement(), (String) enumTabH.nextElement());
            //                    if (msgs.length > 0) {
            //                        System.out.println("Insert both..");
            //                        try {
            //
            //                            //Friza - insert CIS to server-MySql
            //                            //ehr.insertCentral(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
            //
            //                            System.out.println("After insert local..");
            //
            //                            //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
            //                            ehr.insertJournal(1, header, patientInfo,
            //                                    msgs,
            //                                    PMI);
            //                            driverLocation.insertToDrive(1, header, patientInfo,
            //                                    msgs);
            //
            //                            //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
            //                        } catch (Exception e) {
            //                            e.printStackTrace();
            //                        }
            //                    }
            //                    msg_discharge += "Patient record is saved into local database and thumb drive.\n";
            //                } //Formatted message store into thumb drives by invoked insertToDrive method
            //                else if (MItem_drives.isSelected() == true) {
            //                    System.out.println("external");
            //                    if (msgs.length > 0) {
            //                        System.out.println("external inside");
            //                        driverLocation.insertToDrive(1, header, patientInfo,
            //                                msgs);
            //                        //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
            //                    }
            //
            //                    msg_discharge += "Patient record is saved into thumb drive.\n";
            //                } //Formatted Message store into Journal File
            //                else if (MItem_local.isSelected() == true) {
            //                    System.out.println("local");
            //
            //                    if (msgs.length > 0) {
            //                        ehr.insertJournal(1, header, patientInfo,
            //                                msgs, PMI);
            //                        //Friza
            //                        //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
            //
            //
            //                        //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
            //                    }
            //                    msg_discharge += "Patient record is saved into local database.\n";
            //                }
            //            }
            J.o("Discharge", msg_discharge, 1);

            row_count = 0;
            cons.setBtnOn();

            Queue updatequeue = new Queue();
            updatequeue.updateStatusEpisode(cons.txt_pPmiNo.getText(), cons.EpisodeTime, "Discharge");

        //            Session.setPrev_stat(false);
            //            Session.setCurr_stat(false);
            //Session.setCon_x();
            CheckNewPatient.active = false;
            new Consultation().setVisible(true);
            cons.dispose();

        } catch (Exception ex) {
            System.out.println("\n\nerror: " + ex + "\n\n");
            ex.printStackTrace();
        }
    }
}
