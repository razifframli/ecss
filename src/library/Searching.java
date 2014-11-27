package library;

import Helper.J;
import Helper.S;
import Helper.Session;
import java.awt.datatransfer.StringSelection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.Consultation;
import static javaapplication1.Consultation.lbx_complaintSearch;
import static javaapplication1.Consultation.ps;
import static javaapplication1.Consultation.txt_complaintSearch;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Searching {
    
    static String drugName = "";
    static String productName = "";
    static String product = "";
    static String dosageForm = "";
    static String dosage = "";
    static String stockQty = "";
    
    public static void searchDrug(final Consultation cons) {
        //get pmiNo selected
        String pmiNo = cons.txt_pPmiNo.getText();

        if (pmiNo.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a patient!");
        } else {

//            drugName = cons.txt_drugNameOListSearch.getText();
            if (drugName.equals("")) {
                for (int i = 0; i < 50; i++) {
                    cons.tbl_productname.getModel().setValueAt("", i, 0);
                }
            } else {
                try {
                    for (int i = 0; i < 50; i++) {
                        cons.tbl_productname.getModel().setValueAt("", i, 0);
                    }
                    String sql = "SELECT * FROM PIS_MDC WHERE ACTIVE_INGREDIENT_CODE LIKE ?";
                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                    ps.setString(1, drugName.toUpperCase() + "%");
                    ResultSet results = ps.executeQuery();
                    for (int i = 0; results.next() && i < 50; i++) {
                        cons.tbl_productname.getModel().setValueAt(results.getString("Drug_Product_Name"), i, 0);
                    }
                } catch (Exception ex) {
                    S.oln(ex.getMessage());
                }
            }

            //get connection
//            final Connection conn= DBConnection.createConnection();

            //get text from textFiels

            /**
             * comment this code on 21/03/2013
             */
//            drugName = txt_drugNameOListSearch.getText();
//            
//            //if text field do not has any input
//            if (drugName.equals("")) {
//                //reset drug name list box
//                lst_drugNameOListSearch = new javax.swing.JList();
//                lst_drugNameOListSearch.setModel(new javax.swing.AbstractListModel() {
//
//                    String[] strings = {""};
//
//                    public int getSize() {
//                        return strings.length;
//                    }
//
//                    public Object getElementAt(int i) {
//                        return strings[i];
//                    }
//                });
//
//                jScrollPane4.setViewportView(lst_drugNameOListSearch);
//            } else //if textField has input
//            {
//
//                try {
////                    String sql = "SELECT * FROM PIS_MDC WHERE DRUG_PRODUCT_NAME LIKE '"+drugName+"%'";
//                    String sql = "SELECT * FROM PIS_MDC WHERE ACTIVE_INGREDIENT_CODE LIKE '" + drugName.toUpperCase() + "%'";
////                    String sql = "SELECT DRUG_PRODUCT_NAME FROM PIS_MDC WHERE ACTIVE_INGREDIENT_CODE LIKE '"+drugName.toUpperCase()+"%'";
////                    String sql = "SELECT ACTIVE_INGREDIENT_CODE FROM PIS_MDC WHERE ACTIVE_INGREDIENT_CODE LIKE '"+drugName+"%'";
//                    //prepare sql query and execute it
//                    //PreparedStatement ps = conn.prepareStatement(sql);
//                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                    ResultSet results = ps.executeQuery();
//
//                    //set drugName to null so that not affecting drugName shown in list
//                    drugName = "";
//
//                    //if get result
//                    while (results.next()) {
//                        //create String objects to store data of results
//                        drugName += results.getString("Drug_Product_Name") + "\n";
////                        drugName += results.getString("Active_Ingredient_Code") + "\n";
//
//                        //if no data match
//                        if (drugName.equals("")) {
//                            //set data into JList
//                            lst_drugNameOListSearch = new javax.swing.JList();
//
//                            lst_drugNameOListSearch.setModel(new javax.swing.AbstractListModel() {
//
//                                String[] strings = {""};
//
//                                public int getSize() {
//                                    return strings.length;
//                                }
//
//                                public Object getElementAt(int i) {
//                                    return strings[i];
//                                }
//                            });
//
//                            jScrollPane4.setViewportView(lst_drugNameOListSearch);
//                        } else //if has data match
//                        {
//                            //set data into JList
//                            lst_drugNameOListSearch = new javax.swing.JList();
//
//                            lst_drugNameOListSearch.setModel(new javax.swing.AbstractListModel() {
//
//                                String[] strings = drugName.split("\n");
//
//                                public int getSize() {
//                                    return strings.length;
//                                }
//
//                                public Object getElementAt(int i) {
//                                    return strings[i];
//                                }
//                            });
//
//                            jScrollPane4.setViewportView(lst_drugNameOListSearch);
//
//                            //get data from JList into textField
//                            final DefaultListModel model = new DefaultListModel();
//                            lst_drugNameOListSearch.addListSelectionListener(new ListSelectionListener() {
//
//                                public void valueChanged(ListSelectionEvent e) {
//                                    if (!e.getValueIsAdjusting()) {
//                                        model.clear();
//                                        String st = (String) lst_drugNameOListSearch.getSelectedValue();
//                                        txt_drugNameOListSearch.setText(st);
//
//                                        //select data and display in product list
//                                        try {
//                                            String sql = "SELECT * FROM PIS_MDC WHERE DRUG_PRODUCT_NAME = ?";
//                                            //String sql = "SELECT DRUG_PRODUCT_NAME FROM PIS_MDC WHERE DRUG_PRODUCT_NAME = ?";
////                                          String sql = "SELECT DRUG_PRODUCT_NAME FROM PIS_MDC WHERE ACTIVE_INGREDIENT_CODE = ?";
//                                            //prepare sql query and execute it
//                                            //PreparedStatement ps = conn.prepareStatement(sql);
//                                            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                                            ps.setString(1, st);
//
//                                            ResultSet results = ps.executeQuery();
//
//                                            //set productName to null so that not affecting productName shown in list
//                                            productName = "";
//
//                                            //if get result
//                                            while (results.next()) {
//                                                //create String objects to store data of results
//                                                productName += results.getString("Drug_Product_Name") + "\n";
//
//                                                //set data into JList
//                                                lst_productNameOList = new javax.swing.JList();
//
//                                                lst_productNameOList.setModel(new javax.swing.AbstractListModel() {
//
//                                                    String[] strings = productName.split("\n");
//
//                                                    public int getSize() {
//                                                        return strings.length;
//                                                    }
//
//                                                    public Object getElementAt(int i) {
//                                                        return strings[i];
//                                                    }
//                                                });
//
//                                                jScrollPane12.setViewportView(lst_productNameOList);
//
//                                                //get data from JList into textField
//                                                final DefaultListModel model = new DefaultListModel();
//                                                lst_productNameOList.addListSelectionListener(new ListSelectionListener() {
//
//                                                    public void valueChanged(ListSelectionEvent e) {
//                                                        if (!e.getValueIsAdjusting()) {
//                                                            model.clear();
//                                                            //get product selected
//                                                            product = (String) lst_productNameOList.getSelectedValue();
//
//                                                            //clear all text field
//                                                            txt_productNameOList.setText("");
//                                                            txt_dosageFormOList.setText("");
//                                                            txt_dosageOList.setText("");
//                                                            cb_frequencyOList.setSelectedItem("-");
//                                                            txt_quantityOList.setText("");
//                                                            cb_durationOList.setSelectedItem("-");
//                                                            cb_durationTypeOList.setSelectedItem("-");
//                                                            cb_instructionOList.setSelectedItem("-");
//
//                                                            stock_qty.setText("");
//
//                                                            /*
//                                                             * search data base
//                                                             * on the drug
//                                                             * product choosed
//                                                             */
//                                                            //call data from PIS_MDC
//                                                            try {
//
//
//                                                                String sql = "SELECT * FROM PIS_MDC where DRUG_PRODUCT_NAME = ?";
//
//                                                                //prepare sql query and execute it
//                                                                //PreparedStatement ps = conn.prepareStatement(sql);
//                                                                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                                                                ps.setString(1, product);
//
//                                                                ResultSet results = ps.executeQuery();
//
//                                                                while (results.next()) {
//                                                                    //create String objects to store data of results
//                                                                    productName = results.getString("Drug_Product_Name");
//                                                                    dosageForm = results.getString("Dosage_Form_Code");
//                                                                    //Double dosage1 = results.getDouble("Def_Dosage");//DEF_DOSAGE
//                                                                    //dosage = Double.toString(dosage1);
//                                                                    dosage = results.getString("Def_Dosage");
//                                                                    //stockQty = results.getString ("Stock_Qty");
//
//                                                                    Double stock_qty1 = results.getDouble("Stock_Qty");//Stock_Qty
//                                                                    stockQty = Double.toString(stock_qty1);
//
//                                                                    //set value into text field
//                                                                    txt_productNameOList.setText(productName);
//                                                                    txt_dosageFormOList.setText(dosageForm);
//                                                                    txt_dosageOList.setText(dosage);
//                                                                    stock_qty.setText(stockQty);
//
//                                                                    if (stock_qty1 <= 0) {
//                                                                        JOptionPane.showMessageDialog(null, "Drug stock quantity is low " + stock_qty1
//                                                                                + "\nPlease choose another product name");
//
//                                                                        txt_productNameOList.setText("");
//                                                                        txt_dosageOList.setText("");
//                                                                        cb_frequencyOList.setSelectedItem("-");
//                                                                        cb_durationOList.setSelectedItem("-");
//                                                                        cb_durationTypeOList.setSelectedItem("-");
//                                                                        txt_quantityOList.setText("");
//                                                                        txt_dosageFormOList.setText("");
//                                                                        cb_instructionOList.setSelectedItem("-");
//                                                                        stock_qty.setText("");
//                                                                    }
//
//
//                                                                }
//                                                                //clean the results and data
//                                                                results.close();
//                                                                ps.close();
//                                                            } catch (Exception e1) {
//                                                                System.out.println(e1);
//                                                            }
//                                                        }
//                                                    }
//                                                });
//                                            }
//                                            //clean the results and data
//                                            results.close();
//                                            ps.close();
//                                        } catch (Exception e1) {
//                                            //System.out.println(e1);
//                                        }
//                                    }
//                                }
//                            });
//                        }
//                    }
//
//                    //clean the results and data
//                    results.close();
//                    ps.close();
//                } catch (Exception e) {
//                    //System.out.println(e);
//                }
//            }
        }
    }
    
    public static boolean isSearchImmune(String code) {
        boolean s = false;
        try {
            String sql = "SELECT RI_DESC FROM READCODE_IMMUNIZATION "
                        + "where UCASE(RI_DESC) = UCASE(?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = true;
            } else {
                s = false;
            }
        } catch (Exception e) {
            s = false;
        }
        return s;
    }
    
    public static void searchImmune(Consultation cons) {
        try {
            cons.st = Session.getCon_x(100).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        cons.ch = cons.txt_immSearch.getText();
        cons.listModel = new DefaultListModel();
        if (cons.ch.equals("")) {
            cons.listModel.addElement("");
            cons.lbx_immSearch.setModel(cons.listModel);
        } else {
            S.oln("SEARCH IMMUNIZATION");
            try {
                cons.tempQuery = "SELECT RI_DESC FROM READCODE_IMMUNIZATION "
                        + "where UCASE(RI_DESC) like UCASE('%" + cons.ch + "%')";
//                cons.tempQuery = "SELECT * FROM icd10_codes "
//                                    + "where UCASE(icd10_desc) like UCASE('%" + cons.ch + "%') ";
                cons.rs = cons.st.executeQuery(cons.tempQuery);
                while (cons.rs.next()) {
//                    cons.name = cons.rs.getString("icd10_desc");
                    cons.name = cons.rs.getString("RI_DESC");
                    cons.listModel.addElement(cons.name);
                }
                cons.lbx_immSearch.setModel(cons.listModel);
            } catch (Exception ex) {
                J.o("Error Immunization Search", "Error: " + ex.getMessage(), 0);
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean isSearchAllergy1(String code) {
        boolean s = false;
        try {
            String sql = "SELECT RA_DESC FROM READCODE_ALLERGY "
                        + "where UCASE(RA_DESC) = UCASE(?) ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = true;
            } else {
                s = false;
            }
        } catch (Exception e) {
            s = false;
        }
        return s;
    }
    
    public static void searchAllergy1(Consultation cons) {
        try {
            cons.st = Session.getCon_x(100).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        cons.ch = cons.txt_allergySearch.getText();
        cons.listModel = new DefaultListModel();
        if (cons.ch.equals("")) {
            cons.listModel.addElement("");
            cons.lbx_allergySearch.setModel(cons.listModel);
        } else {
            try {
                S.oln("SEARCH ALLERGY");
                cons.tempQuery = "SELECT RA_DESC FROM READCODE_ALLERGY "
                        + "where UCASE(RA_DESC) like UCASE('%" + cons.ch + "%') ";
//                cons.tempQuery = "SELECT * FROM icd10_codes "
//                        + "where UCASE(icd10_desc) like UCASE('%" + cons.ch + "%') ";
                S.oln(cons.tempQuery);
                cons.rs = cons.st.executeQuery(cons.tempQuery);
                while (cons.rs.next()) {
//                    cons.name = cons.rs.getString("icd10_desc");
                    cons.name = cons.rs.getString("RA_DESC");
                    cons.listModel.addElement(cons.name);
                }
                cons.lbx_allergySearch.setModel(cons.listModel);
            } catch (Exception ex) {
                J.o("Error Allery Search", "Error: " + ex.getMessage(), 0);
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean isSearchSH1(String code) {
        boolean status = false;
        try {
            String sql = "SELECT RSH_DESC FROM READCODE_SOCIAL_HISTORY "
                        + "WHERE UCASE(RSH_DESC) = UCASE(?) ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
    
    public static void searchSH1(Consultation cons) {
        try {
            cons.st = Session.getCon_x(100).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        cons.ch = cons.txt_socialProblem.getText();
        cons.listModel = new DefaultListModel();
        if (cons.ch.equals("")) {
            cons.listModel.addElement("");
            cons.lbx_socialProblem.setModel(cons.listModel);
        } else {
            try {
                cons.tempQuery = "SELECT RSH_DESC FROM READCODE_SOCIAL_HISTORY "
                        + "WHERE UCASE(RSH_DESC) LIKE UCASE(?) ";
                cons.ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                cons.ps.setString(1, "%" + cons.ch + "%");
                cons.rs = cons.ps.executeQuery();
                while (cons.rs.next()) {
                    cons.name = cons.rs.getString("RSH_DESC");
                    cons.listModel.addElement(cons.name);
                }
                cons.lbx_socialProblem.setModel(cons.listModel);
            } catch (Exception ex) {
                J.o("Error SH Search", "Error: " + ex.getMessage(), 0);
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean isSearchDiagnosis(String code) {
        boolean s = false;
        try {
            String sql = "SELECT * FROM icd10_codes "
                        + "where UCASE(icd10_desc) = UCASE(?) ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = true;
            } else {
                s = false;
            }
        } catch (Exception e) {
            s = false;
        }
        return s;
    }
    
    public static void searchDiagnosis(Consultation cons) {
        String ch = cons.txt_diagnosisSearch.getText().toLowerCase();

        cons.listModel = new DefaultListModel();

        if (ch.equals("")) {
        } else {
            try {
//                tempQuery = "SELECT RCC_DESC FROM READCODE_CHIEF_COMPLAINT "
//                        + "where UCASE(RCC_DESC) like UCASE(?) ";
                cons.tempQuery = "SELECT * FROM icd10_codes "
                        + "where UCASE(icd10_desc) like UCASE(?) ";
                ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                ps.setString(1, ch);
                cons.rs = ps.executeQuery();
                while (cons.rs.next()) {
                    cons.name = cons.rs.getString("icd10_desc");
                    cons.listModel.addElement(cons.name);
                }
                cons.lbx_diagnosisSearch.setModel(cons.listModel);
            } catch (Exception ex) {
            }
        }
    }
    
    public static boolean isSearchDAB1(String code) {
        boolean s = false;
        try {
            String sql = "SELECT RD_DESC FROM READCODE_DISABILITY "
                                        + "WHERE UCASE(RD_DESC) = UCASE(?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = true;
            } else {
                s = false;
            }
        } catch (Exception e) {
            s = false;
        }
        return s;
    }
    
    public static void searchDAB1(Consultation cons) {
        cons.ch = cons.txt_disabilityType.getText();
        cons.listModel = new DefaultListModel();
        if (cons.ch.equals("")) {
            cons.listModel.addElement("");
            cons.lbx_disabilityType.setModel(cons.listModel);
        } else {
            try {
                cons.tempQuery = "SELECT RD_DESC FROM READCODE_DISABILITY "
                                        + "WHERE UCASE(RD_DESC) LIKE UCASE(?) ";
//                cons.tempQuery = "SELECT * FROM icd10_codes "
//                + "where UCASE(icd10_desc) like UCASE(?) ";
                ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                ps.setString(1, "%" + cons.ch + "%");
                cons.rs = ps.executeQuery();
                while (cons.rs.next()) {
//                    cons.name = cons.rs.getString("icd10_desc");
                    cons.name = cons.rs.getString("RD_DESC");
                    cons.listModel.addElement(cons.name);
                }
                cons.lbx_disabilityType.setModel(cons.listModel);
            } catch (Exception ex) {
            }
        }
    }
    
    public static boolean isSearchCCN1(String desc) {
        boolean status = false;
        try {
            String sql = "SELECT RCC_DESC FROM READCODE_CHIEF_COMPLAINT "
                        + "where UCASE(RCC_DESC) = UCASE(?) order by RCC_DESC ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, desc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
    
    public static void searchCCN1(Consultation cons) {
        try {
            cons.st = Session.getCon_x(100).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        cons.ch = txt_complaintSearch.getText();
        cons.listModel = new DefaultListModel();
        if (cons.ch.equals("")) {
            cons.listModel.addElement("");
            lbx_complaintSearch.setModel(cons.listModel);
        } else {
            System.out.println(cons.ch);
            try {
                cons.tempQuery = "SELECT RCC_DESC FROM READCODE_CHIEF_COMPLAINT "
                        + "where UCASE(RCC_DESC) like UCASE(?) order by RCC_DESC ";
//                tempQuery = "SELECT * FROM icd10_codes "
//                        + "where UCASE(icd10_desc) like UCASE(?) ";
//                        //+ "and SNOMEDDESC like '%(finding)%'";
                ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
                ps.setString(1, cons.ch);
                cons.rs = ps.executeQuery();
                while (cons.rs.next()) {
//                    String name = rs.getString("icd10_desc");
                    String name = cons.rs.getString("RCC_DESC");
                    cons.listModel.addElement(name);
                }
                lbx_complaintSearch.setModel(cons.listModel);
            } catch (Exception ex) {
                S.oln(ex.getMessage());
            }
        }
    }
    
    public static boolean isSearchPMH1(String code) {
        boolean status = false;
        try {
            String sql = "SELECT * FROM icd10_codes "
                    + "where UCASE(icd10_desc) = UCASE(?) ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
    
    public static void searchPMH1(Consultation cons) {
        try {
            cons.st = Session.getCon_x(100).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        cons.ch = cons.txt_PMHSearch.getText();
        cons.listModel = new DefaultListModel();
        S.oln("PMH Search");
        try {
//                cons.tempQuery = "SELECT RCC_DESC FROM READCODE_CHIEF_COMPLAINT "
//                        + "where UCASE(RCC_DESC) like UCASE(?) ";
            cons.tempQuery = "SELECT * FROM icd10_codes "
                    + "where UCASE(icd10_desc) like UCASE(?) ";
            cons.ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
            cons.ps.setString(1, "%" + cons.ch + "%");
            cons.rs = cons.ps.executeQuery();
            while (cons.rs.next()) {
                cons.name = cons.rs.getString("icd10_desc");
                cons.listModel.addElement(cons.name);
            }
            cons.lbx_PMHSearch.setModel(cons.listModel);
        } catch (Exception ex) {
            J.o("Error PMH Search", "Error: " + ex.getMessage(), 0);
            ex.printStackTrace();
        }
    }
    
    public static boolean isSearchFH1(String code) {
        boolean status = false;
        try {
            String sql = "SELECT * FROM icd10_codes "
                        + "where UCASE(icd10_desc) = UCASE(?) ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
    
    public static void searchFH1(Consultation cons) {
        try {
            cons.st = Session.getCon_x(100).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        cons.ch = cons.txt_FHSearch.getText();
        cons.listModel = new DefaultListModel();
        S.oln("FH Search");
        try {
//            cons.tempQuery = "SELECT RPMH_DESC "
//                    + "FROM READCODE_PAST_MEDICAL_HISTORY "
//                    + "WHERE UCASE(RPMH_DESC) "
//                    + "LIKE UCASE(?) ";
            cons.tempQuery = "SELECT * FROM icd10_codes "
                        + "where UCASE(icd10_desc) like UCASE(?) ";
            cons.ps = Session.getCon_x(1000).prepareStatement(cons.tempQuery);
            cons.ps.setString(1, "%" + cons.ch + "%");
            cons.rs = cons.ps.executeQuery();
            while (cons.rs.next()) {
                cons.name = cons.rs.getString("icd10_desc");
                cons.listModel.addElement(cons.name);
            }
            cons.lbx_FHSearch.setModel(cons.listModel);
        } catch (Exception ex) {
            J.o("Error FH Search", "Error: " + ex.getMessage(), 0);
            ex.printStackTrace();
        }
    }
}
