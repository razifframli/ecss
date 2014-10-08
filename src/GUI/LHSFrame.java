/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LHSFrame.java
 *
 * Created on Jul 31, 2012, 2:40:59 PM
 */
package GUI;
import BluethoothCompo.bluetoothGUI;
import GUI.AppointmentList;
import GUI.MainPage;
import GUI.QueueList;
import api.Patient;
import api.Queue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;

//java db
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author BHEBEG
 */
public class LHSFrame extends javax.swing.JFrame {

       String EpisodeTime;
    int tab1 = 0;
    int tab2 = 0;
    int tab3 = 0;
    int tab4 = 0;
    int tab5 = 0;
    int tab6 = 0;
    int tab7 = 0;
    int tab8 = 0;
    int tab9 = 0;
    int tab10 = 0;
    int tab11 = 0;
    int tab12 = 0;
    int tab13 = 0;
    int tab14 = 0;
    int tab15 = 0;
    int tab16 = 0;
    int tab17 = 0;
    int tab18 = 0;
    //hold current string value
    String tab1s = "";
    String tab2s = "";
    String tab3s = "";
    String tab4s = "";
    String tab5s = "";
    String tab6s = "";
    String tab7s = "";
    String tab8s = "";
    String tab9s = "";
    String tab10s = "";
    String tab11s = "";
    String tab12s = "";
    String tab13s = "";
    String tab14s = "";
    String tab15s = "";
    String tab16s = "";
    String tab17s = "";
    String tab18s = "";
    int v = 0;//for update purpose
    //reset all value to zero
    String productName = null;
    String route = null;
    String strength = null;
    String dosageForm = null;
    String dosage = null;
    String frequency = null;
    String duration = null;
    String qtyPerTime = null;
    String instruction = null;
    String dispenseStatus = "False";
    String medicFormCode = null;
    String staffID = "STF001";
    String num = null;
    String word = null;
    String mdcCode;
    String atcCode;
    String drugStatus = "true";
    
    String tempQuery;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
    javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
    String ch;
    String name;
    
    FileWriter outFile;
    
    boolean ctSpeak = false,ctText = false;
    double start,end;
    QueueList que = new QueueList(this);
    private String id;
    
   
    /** Creates new form LHSFrame */
    public LHSFrame() {
        initComponents();
        
        
//        
//         //********************************************************************************
//        //get popup menu at combo box according to Character type at textbox
//        //*
//        //**************************************************************************************************************
//    
//    txt_complaintSearch.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//               ch = txt_complaintSearch.getText();
//                listModel = new DefaultListModel();
//                if (ch.equals("")) {
//                    listModel.addElement("");
//                    lbx_complaintSearch.setModel(listModel);
//                } else {                    
//                    try {                        
//                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
//                            tempQuery = "SELECT SNOMEDDESC FROM SNOMEDCCOMPLAINTS "
//                                    + "where SNOMEDDESC like '%" + ch + "%' "
//                                    + "and SNOMEDDESC like '%(finding)%' limit 10";
//                        } else {
//                            tempQuery = "SELECT SNOMEDDESC FROM SNOMEDCCOMPLAINTS "
//                                    + "where SNOMEDDESC like '%" + ch + "%' "
//                                    + "and SNOMEDDESC like '%(finding)%'";
//                        }
//                        rs = st.executeQuery(tempQuery);                        
//                        while (rs.next()) {
//                            name = rs.getString("SNOMEDDESC");                            
//                            listModel.addElement(name);
//                        }
//                        lbx_complaintSearch.setModel(listModel);
//                    } catch (Exception ex) {
//                    }
//                } 
//            }
//        });
//        lbx_complaintSearch.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {                    
//                    String st = (String) lbx_complaintSearch.getSelectedValue();
//                    txt_complaintSearch.setText(st);
//                }
//            }
//        });
//
//txt_diagnosisSearch.addKeyListener(new KeyAdapter() {                       
//            @Override
//            public void keyReleased(KeyEvent e) {               
//                ch = txt_diagnosisSearch.getText().toLowerCase();                
//                
//                listModel = new DefaultListModel();        
//                if (ch.equals("")) {
//                } else {
//                    try {
//                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
//                            tempQuery = "SELECT SNOMEDDESC FROM SNOMEDDIAGNOSIS "
//                                    + "where lcase(SNOMEDDESC) like '%" + ch + "%' "
//                                    + "and SNOMEDDESC like '%(finding)%' limit 10";
//                        } else {
//                            tempQuery = "SELECT SNOMEDDESC FROM SNOMEDDIAGNOSIS "
//                                    + "where lcase(SNOMEDDESC) like '%" + ch + "%' "
//                                    + "and SNOMEDDESC like '%(finding)%'";
//                        }
//                            
//                        ResultSet rs = st.executeQuery(tempQuery);                        
//                        while (rs.next()) {
//                            name = rs.getString("SNOMEDDESC");                            
//                            listModel.addElement(name);
//                        }                        
//                        lbx_diagnosisSearch.setModel(listModel);                        
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        });
//        
//        lbx_diagnosisSearch.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {                    
//                    String st = (String) lbx_diagnosisSearch.getSelectedValue();
//                    txt_diagnosisSearch.setText(st);
////                    if(ctText == true){
////                        end = System.currentTimeMillis();
////                        //System.out.println("Typing Time: "+((end-start)/1000));
////                        JOptionPane.showMessageDialog(null,"Typing Time: "+((end-start)/1000));                        
////                        try {
////                            outFile = new FileWriter("D:/testing-ika.txt",true);
////                            outFile.write(st+" : "+((end-start)/1000)+"\n");
////                            outFile.close();
////                        } catch (IOException ex) {
////                            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
////                        }
////                        start = 0;
////                        end = 0;
////                        ctText = false;
////                    }
//                }
//                //System.gc();
//            }
//        });
//
//txt_immSearch.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                ch = txt_immSearch.getText();
//                listModel = new DefaultListModel();
//                if (ch.equals("")) {
//                    listModel.addElement("");
//                    lbx_immSearch.setModel(listModel);
//                } else {                                        
//                    try {
//                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
//                            tempQuery = "SELECT SNOMEDDESC FROM SNOMEDIMMUNISATION "
//                                + "where SNOMEDDESC like '%" + ch + "%' "
//                                + "and SNOMEDDESC like '%(finding)%' limit 10";
//                        } else {
//                             tempQuery = "SELECT SNOMEDDESC FROM SNOMEDIMMUNISATION "
//                                + "where SNOMEDDESC like '%" + ch + "%' "
//                                + "and SNOMEDDESC like '%(finding)%'"; 
//                        }                
//                        rs = st.executeQuery(tempQuery);                        
//                        while (rs.next()) {
//                            name = rs.getString("SNOMEDDESC");                            
//                            listModel.addElement(name);
//                        }
//                        lbx_immSearch.setModel(listModel);
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        });
//
//        lbx_immSearch.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    //Model.clear();
//                    String st = (String) lbx_immSearch.getSelectedValue();
//                    txt_immSearch.setText(st);
//                }
//            }
//        });
//txt_drugSearch.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                ch = txt_drugSearch.getText();
//                listModel = new DefaultListModel();
//                if (ch.equals("")) {
//                    listModel.addElement("");
//                    lbx_drugSearch.setModel(listModel);
//                } else {                                        
//                    try {                        
//                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
//                            tempQuery = "SELECT FullySpecifiedName FROM SNOMEDCONCEPT "
//                                + "where FullySpecifiedName like '%" + ch + "%' "
//                                + "and FullySpecifiedName like '%(product)%' limit 10";
//                        } else {
//                            tempQuery = "SELECT FullySpecifiedName FROM SNOMEDCONCEPT "
//                                + "where FullySpecifiedName like '%" + ch + "%' "
//                                + "and FullySpecifiedName like '%(product)%'";
//                        }
//                        
//                        rs = st.executeQuery(tempQuery);                        
//                        while (rs.next()) {
//                            name = rs.getString("FullySpecifiedName");                            
//                            listModel.addElement(name);
//                        }
//                        lbx_drugSearch.setModel(listModel);
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        });
//
//        lbx_drugSearch.addListSelectionListener(new ListSelectionListener() {
//
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    //Model.clear();
//                    String st = (String) lbx_drugSearch.getSelectedValue();
//                    txt_drugSearch.setText(st);
//
//                    javax.swing.DefaultListModel listModel1 = new javax.swing.DefaultListModel();
//
//                    try {
//                        Connection conn = DatabaseConnection.connect();
//                        Statement st1 = conn.createStatement();
//                        ResultSet rs = st1.executeQuery("SELECT * FROM PIS_MDC where DRUG_GNR_NAME ='" + st + "'");
//
//                        while (rs.next()) {
//                            String dosage_form = rs.getString("DOSAGE_FORM_CODE");
//                            String product_name = rs.getString("Drug_Product_Name");
//                            txt_drugForm.setText(dosage_form);
//                            listModel1.addElement(product_name);
//                            lbx_productNameUStockSearch.setModel(listModel1);
//                        }
//                    } catch (Exception ex) {
//                        System.out.println(ex);
//                    }
//
//                }
//            }
//        });
// txt_disabilityType.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                ch = txt_disabilityType.getText();
//                listModel = new DefaultListModel();
//                if (ch.equals("")) {
//                    listModel.addElement("");
//                    lbx_disabilityType.setModel(listModel);
//                } else {                                        
//                    try {                        
//                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
//                            tempQuery = "SELECT FullySpecifiedName FROM SNOMEDCONCEPT "
//                                + "where FullySpecifiedName like '%" + ch + "%' "
//                                + "and FullySpecifiedName like '%(finding)%' limit 10";
//                        } else {
//                            tempQuery = "SELECT FullySpecifiedName FROM SNOMEDCONCEPT "
//                                + "where FullySpecifiedName like '%" + ch + "%' "
//                                + "and FullySpecifiedName like '%(finding)%'";
//                        }
//                        
//                        rs = st.executeQuery(tempQuery);                        
//                        while (rs.next()) {
//                            name = rs.getString("FullySpecifiedName");                            
//                            listModel.addElement(name);
//                        }
//                        lbx_disabilityType.setModel(listModel);
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        });
//
//        lbx_disabilityType.addListSelectionListener(new ListSelectionListener() {
//
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    //Model.clear();
//                    String st = (String) lbx_disabilityType.getSelectedValue();
//                    txt_disabilityType.setText(st);
//                }
//            }
//        });
//txt_allergySearch.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                ch = txt_allergySearch.getText();
//                listModel = new DefaultListModel();
//                if (ch.equals("")) {
//                    listModel.addElement("");
//                    lbx_allergySearch.setModel(listModel);
//                } else {                                        
//                    try {                        
//                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
//                            tempQuery = "SELECT ALLERGYNAME FROM SNOMEDALLERGY "
//                                + "where ALLERGYNAME like '%" + ch + "%' limit 10";
//                        } else {
//                            tempQuery = "SELECT ALLERGYNAME FROM SNOMEDALLERGY "
//                                + "where ALLERGYNAME like '%" + ch + "%' "
//                                + "and ALLERGYNAME like '%(finding)%'";
//                        }
//                        
//                        rs = st.executeQuery(tempQuery);                        
//                        while (rs.next()) {
//                            name = rs.getString("ALLERGYNAME");                            
//                            listModel.addElement(name);
//                        }
//                        lbx_allergySearch.setModel(listModel);
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        });
//        lbx_allergySearch.addListSelectionListener(new ListSelectionListener() {
//
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    //Model.clear();
//                    String st = (String) lbx_allergySearch.getSelectedValue();
//                    txt_allergySearch.setText(st);
//                }
//            }
//        });
    
    }
    String tsttab1s[] = new String[100];
    int q1s = 0;
    String compsub[][] = new String[100][8];
    String tsttab2[] = new String[100];
    int q = 0;
    String diagsub[][] = new String[100][3];
    String tsttab3[] = new String[100];
    int q1 = 0;
    String immsub[][] = new String[100][3];
    String tsttab4[] = new String[100];
    int q2 = 0;
    String vtssub[][] = new String[100][7];
    String tsttab5[] = new String[100];
    int q3 = 0;
    String drgsub[][] = new String[100][9];
    int drgsub1[][] = new int[100][4];

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LHS_QueueBtn = new javax.swing.JButton();
        LHS_MissingBtn = new javax.swing.JButton();
        LHS_SearchBtn = new javax.swing.JButton();
        LHS_OnHoldBtn = new javax.swing.JButton();
        LHS_DischargeBtn = new javax.swing.JButton();
        LHS_nextBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnStatus = new javax.swing.JButton();
        txt_pName = new javax.swing.JTextField();
        txt_pIcNo = new javax.swing.JTextField();
        txt_pRace = new javax.swing.JTextField();
        txt_pBloodSex = new javax.swing.JTextField();
        txt_pSex = new javax.swing.JTextField();
        txt_pAge = new javax.swing.JTextField();
        txt_pID = new javax.swing.JTextField();
        jScrollPane64 = new javax.swing.JScrollPane();
        AllergyHeader = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rbtn_cActive = new javax.swing.JRadioButton();
        rbtn_cInactive = new javax.swing.JRadioButton();
        txt_complaintSearch = new javax.swing.JTextField();
        lbl_complaintSearch = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lbx_complaintSearch = new javax.swing.JList();
        cbx_site = new javax.swing.JComboBox();
        cbx_laterality = new javax.swing.JComboBox();
        lbl_laterality = new javax.swing.JLabel();
        lbl_site = new javax.swing.JLabel();
        cbx_cSeverity = new javax.swing.JComboBox();
        lbl_cSeverity = new javax.swing.JLabel();
        lbl_duration = new javax.swing.JLabel();
        txt_duration = new javax.swing.JTextField();
        cbx_duration = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_complaintComment = new javax.swing.JTextArea();
        lbl_complaintComment = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea12 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tbl_cc = new javax.swing.JTable();
        btn_complaintClear = new javax.swing.JButton();
        btnSrcComplaint = new javax.swing.JButton();
        btn_complaintAccept = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HPI_DetailstxtArea = new javax.swing.JTextArea();
        HPI_AcceptBtn = new javax.swing.JButton();
        HPI_ClrBtn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tbl_HPI1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txt_PMHSearch = new javax.swing.JTextField();
        lbl_complaintSearch1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lbx_PMHSearch = new javax.swing.JList();
        jLabel24 = new javax.swing.JLabel();
        txt_PMHDiagnosis = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        rbtn_cActive1 = new javax.swing.JRadioButton();
        rbtn_cInactive1 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_PMHComment = new javax.swing.JTextArea();
        btnSrcComplaint1 = new javax.swing.JButton();
        PMH_accBtn = new javax.swing.JButton();
        PMH_clearBtn = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txt_FHSearch = new javax.swing.JTextField();
        btnSrcSocialHistory1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        lbx_FHSearch = new javax.swing.JList();
        jLabel31 = new javax.swing.JLabel();
        txt_FHDiagnosis = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        FH_Relationship = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        FH_Comments = new javax.swing.JTextArea();
        FH_clrBtn = new javax.swing.JButton();
        FH_AccBtn = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane42 = new javax.swing.JScrollPane();
        tbl_sh = new javax.swing.JTable();
        jScrollPane26 = new javax.swing.JScrollPane();
        lbx_socialProblem = new javax.swing.JList();
        lbl_socialHistory = new javax.swing.JLabel();
        txt_socialDate1 = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        lbl_shdate = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        txt_socialComment = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_socialProblem = new javax.swing.JTextField();
        jScrollPane30 = new javax.swing.JScrollPane();
        jTextArea15 = new javax.swing.JTextArea();
        btnSrcSocialHistory = new javax.swing.JButton();
        btn_sclAccept = new javax.swing.JButton();
        btn_sclClear = new javax.swing.JButton();
        jScrollPane65 = new javax.swing.JScrollPane();
        jTextArea17 = new javax.swing.JTextArea();
        jPanel36 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        comboBloodType = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        rbtn_Bpositive = new javax.swing.JRadioButton();
        rbtn_Bnegative = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        rbtn_Bnormal = new javax.swing.JRadioButton();
        rbtn_Bdeficient = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txt_BldCmmnt = new javax.swing.JTextArea();
        BG_accBtn = new javax.swing.JButton();
        BG_clrBtn = new javax.swing.JButton();
        jScrollPane66 = new javax.swing.JScrollPane();
        jTextArea18 = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel37 = new javax.swing.JPanel();
        lbl_alldate = new javax.swing.JLabel();
        jScrollPane29 = new javax.swing.JScrollPane();
        jTextArea14 = new javax.swing.JTextArea();
        txt_allergyDate2 = new com.toedter.calendar.JDateChooser();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        lbx_allergySearch = new javax.swing.JList();
        lbl_allergy = new javax.swing.JLabel();
        lb_allergySearch = new javax.swing.JLabel();
        jScrollPane40 = new javax.swing.JScrollPane();
        tbl_all = new javax.swing.JTable();
        lbl_allergyComments = new javax.swing.JLabel();
        lbl_allergyDate = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txt_allergyComments = new javax.swing.JTextArea();
        txt_allergySearch = new javax.swing.JTextField();
        btnSrcAllergy = new javax.swing.JButton();
        btn_allergyAccept = new javax.swing.JButton();
        btn_allergyClear = new javax.swing.JButton();
        jScrollPane67 = new javax.swing.JScrollPane();
        jTextArea19 = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel38 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txt_immSearch = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane34 = new javax.swing.JScrollPane();
        tbl_imm = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        lbx_immSearch = new javax.swing.JList();
        lbl_immDate = new javax.swing.JLabel();
        lbl_immSearch = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea10 = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        txt_immComment = new javax.swing.JTextArea();
        lbl_immComment = new javax.swing.JLabel();
        lbl_immdate = new javax.swing.JLabel();
        txt_immDate1 = new com.toedter.calendar.JDateChooser();
        btnSrcImmunisation = new javax.swing.JButton();
        btn_immAccept = new javax.swing.JButton();
        btn_immClear = new javax.swing.JButton();
        jScrollPane68 = new javax.swing.JScrollPane();
        jTextArea20 = new javax.swing.JTextArea();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel39 = new javax.swing.JPanel();
        lbl_dComments = new javax.swing.JLabel();
        lbl_disDate = new javax.swing.JLabel();
        jScrollPane41 = new javax.swing.JScrollPane();
        tbl_dis = new javax.swing.JTable();
        lbl_disdate = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        lbx_disabilityType = new javax.swing.JList();
        lbl_disabilityType = new javax.swing.JLabel();
        txt_disabilityType = new javax.swing.JTextField();
        lbl_disability = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        txt_dComments = new javax.swing.JTextArea();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTextArea13 = new javax.swing.JTextArea();
        txt_dDate1 = new com.toedter.calendar.JDateChooser();
        btnSrcDisability = new javax.swing.JButton();
        btn_dAccept = new javax.swing.JButton();
        btn_dClear = new javax.swing.JButton();
        jScrollPane69 = new javax.swing.JScrollPane();
        jTextArea21 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jPanel40 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txt_headCircumference = new javax.swing.JTextField();
        txt_weight = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        txt_height = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txt_temperature = new javax.swing.JTextField();
        txt_pulse = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txt_weightStatus = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane35 = new javax.swing.JScrollPane();
        tbl_vs = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        lbl_vsdate = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txt_bmi = new javax.swing.JTextField();
        btn_calculateBmi = new javax.swing.JButton();
        btn_vitalSignAccept = new javax.swing.JButton();
        btn_vitalSignClear = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jSlider4 = new javax.swing.JSlider();
        jSlider5 = new javax.swing.JSlider();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane31 = new javax.swing.JScrollPane();
        jPanel41 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        lbl_dSeverity = new javax.swing.JLabel();
        cbx_dSeverity = new javax.swing.JComboBox();
        lbl_ddate = new javax.swing.JLabel();
        txt_date1 = new com.toedter.calendar.JDateChooser();
        jScrollPane36 = new javax.swing.JScrollPane();
        jTextArea9 = new javax.swing.JTextArea();
        jLabel59 = new javax.swing.JLabel();
        lbl_diagnosisSearch = new javax.swing.JLabel();
        txt_diagnosisSearch = new javax.swing.JTextField();
        jScrollPane37 = new javax.swing.JScrollPane();
        tbl_diag = new javax.swing.JTable();
        jScrollPane38 = new javax.swing.JScrollPane();
        lbx_diagnosisSearch = new javax.swing.JList();
        lbl_diagdate = new javax.swing.JLabel();
        rbtn_dFinal = new javax.swing.JRadioButton();
        rbtn_dProvisional = new javax.swing.JRadioButton();
        btnSrcDiagnosis = new javax.swing.JButton();
        btn_diagnosisAccept = new javax.swing.JButton();
        btn_diagnosisClear = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane39 = new javax.swing.JScrollPane();
        txt_pNotes = new javax.swing.JTextArea();
        PN_accBtn = new javax.swing.JButton();
        PN_clrBtn = new javax.swing.JButton();
        jScrollPane70 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel25 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane46 = new javax.swing.JScrollPane();
        jPanel26 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane43 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane44 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane45 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        btn_RadReqOP = new javax.swing.JButton();
        btn_RadReqPrint = new javax.swing.JButton();
        btn_RadReqAcc = new javax.swing.JButton();
        btn_RadReqClr = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane49 = new javax.swing.JScrollPane();
        lbx_instruction = new javax.swing.JList();
        jScrollPane53 = new javax.swing.JScrollPane();
        jPanel42 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane54 = new javax.swing.JScrollPane();
        lbx_productNameUStockSearch = new javax.swing.JList();
        cbx_drugDduration = new javax.swing.JComboBox();
        jLabel78 = new javax.swing.JLabel();
        txt_dStrength = new javax.swing.JTextField();
        btnSrcDrugs1 = new javax.swing.JButton();
        jScrollPane55 = new javax.swing.JScrollPane();
        lbx_frequency = new javax.swing.JList();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txt_dose = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jScrollPane56 = new javax.swing.JScrollPane();
        lbx_instruction2 = new javax.swing.JList();
        txt_drugSearch = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jScrollPane57 = new javax.swing.JScrollPane();
        lbx_drugSearch = new javax.swing.JList();
        txt_drugForm = new javax.swing.JTextField();
        cbx_drugDuration = new javax.swing.JComboBox();
        jLabel85 = new javax.swing.JLabel();
        jScrollPane58 = new javax.swing.JScrollPane();
        tbl_drug1 = new javax.swing.JTable();
        lbl_drgdate1 = new javax.swing.JLabel();
        btn_drugAccept1 = new javax.swing.JButton();
        btn_drugClear1 = new javax.swing.JButton();
        jScrollPane59 = new javax.swing.JScrollPane();
        lbx_instruction3 = new javax.swing.JList();
        jScrollPane71 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel29 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jScrollPane47 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane48 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane50 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel72 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jComboBox8 = new javax.swing.JComboBox();
        jTextField6 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel76 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel87 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        btn_MonOP = new javax.swing.JButton();
        btn_MonPrint = new javax.swing.JButton();
        btn_MonAcc = new javax.swing.JButton();
        btn_MonClr = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jScrollPane51 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        btn_FollAcc = new javax.swing.JButton();
        btn_FollClr = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jLabel94 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel96 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox();
        jLabel98 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox();
        jLabel101 = new javax.swing.JLabel();
        jScrollPane52 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        btn_ReffAcc = new javax.swing.JButton();
        btn_ReffClr = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jScrollPane60 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList();
        jLabel104 = new javax.swing.JLabel();
        jScrollPane61 = new javax.swing.JScrollPane();
        jList7 = new javax.swing.JList();
        jLabel105 = new javax.swing.JLabel();
        jScrollPane62 = new javax.swing.JScrollPane();
        jList8 = new javax.swing.JList();
        jLabel106 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jComboBox16 = new javax.swing.JComboBox();
        jComboBox17 = new javax.swing.JComboBox();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox();
        jLabel109 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        btn_ProcOP = new javax.swing.JButton();
        btn_ProcPrint = new javax.swing.JButton();
        btn_ProcAcc = new javax.swing.JButton();
        btn_ProcClr = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox();
        jLabel117 = new javax.swing.JLabel();
        jScrollPane63 = new javax.swing.JScrollPane();
        jTextArea16 = new javax.swing.JTextArea();
        jLabel118 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        btn_ATWReff = new javax.swing.JButton();
        btn_ATWPrev = new javax.swing.JButton();
        btn_ATWCls = new javax.swing.JButton();
        btn_LHSviewPrevVisit = new javax.swing.JButton();
        btn_LHSPrintCrntVisitSum = new javax.swing.JButton();
        btn_LHSletters = new javax.swing.JButton();
        btn_LHSReports = new javax.swing.JButton();
        btn_LHSAppend = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ActiveRadioButton = new javax.swing.JRadioButton();
        inActiveRadioButton = new javax.swing.JRadioButton();
        AllRadioButton = new javax.swing.JRadioButton();
        ProblemRadioButton = new javax.swing.JRadioButton();
        ProblemComboBox = new javax.swing.JComboBox();
        WellnessRadioButton = new javax.swing.JRadioButton();
        WellnessComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setName("jPanel1"); // NOI18N

        LHS_QueueBtn.setText("Queue");
        LHS_QueueBtn.setName("LHS_QueueBtn"); // NOI18N
        LHS_QueueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LHS_QueueBtnActionPerformed(evt);
            }
        });

        LHS_MissingBtn.setText("Missing");
        LHS_MissingBtn.setName("LHS_MissingBtn"); // NOI18N

        LHS_SearchBtn.setText("Search");
        LHS_SearchBtn.setActionCommand("LHS_Search_Btn");
        LHS_SearchBtn.setName("LHS_SearchBtn"); // NOI18N

        LHS_OnHoldBtn.setText("On Hold");
        LHS_OnHoldBtn.setName("LHS_OnHoldBtn"); // NOI18N

        LHS_DischargeBtn.setText("Discharge");
        LHS_DischargeBtn.setActionCommand("LHS_Discharge_Btn");
        LHS_DischargeBtn.setName("LHS_DischargeBtn"); // NOI18N

        LHS_nextBtn.setText("Next");
        LHS_nextBtn.setName("LHS_nextBtn"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LHS_nextBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LHS_QueueBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LHS_MissingBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LHS_SearchBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 579, Short.MAX_VALUE)
                .addComponent(LHS_OnHoldBtn)
                .addGap(18, 18, 18)
                .addComponent(LHS_DischargeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LHS_QueueBtn)
                .addComponent(LHS_MissingBtn)
                .addComponent(LHS_SearchBtn)
                .addComponent(LHS_nextBtn)
                .addComponent(LHS_OnHoldBtn)
                .addComponent(LHS_DischargeBtn))
        );

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText("Name   :");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Sex      :");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Age      :");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("IC/ID No :");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("ID Type  :");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Race       :");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Blood Group/ G6PD  :");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("Allergy  :");
        jLabel8.setName("jLabel8"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        btnStatus.setName("btnStatus"); // NOI18N

        txt_pName.setEditable(false);
        txt_pName.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pName.setBorder(null);
        txt_pName.setName("txt_pName"); // NOI18N
        txt_pName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pNameActionPerformed(evt);
            }
        });

        txt_pIcNo.setEditable(false);
        txt_pIcNo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pIcNo.setBorder(null);
        txt_pIcNo.setName("txt_pIcNo"); // NOI18N

        txt_pRace.setEditable(false);
        txt_pRace.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pRace.setBorder(null);
        txt_pRace.setName("txt_pRace"); // NOI18N

        txt_pBloodSex.setEditable(false);
        txt_pBloodSex.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pBloodSex.setBorder(null);
        txt_pBloodSex.setName("txt_pBloodSex"); // NOI18N

        txt_pSex.setBackground(new java.awt.Color(240, 240, 240));
        txt_pSex.setBorder(null);
        txt_pSex.setName("txt_pSex"); // NOI18N

        txt_pAge.setBackground(new java.awt.Color(240, 240, 240));
        txt_pAge.setBorder(null);
        txt_pAge.setName("txt_pAge"); // NOI18N

        txt_pID.setBackground(new java.awt.Color(240, 240, 240));
        txt_pID.setBorder(null);
        txt_pID.setName("txt_pID"); // NOI18N

        jScrollPane64.setName("jScrollPane64"); // NOI18N

        AllergyHeader.setColumns(20);
        AllergyHeader.setRows(5);
        AllergyHeader.setName("AllergyHeader"); // NOI18N
        jScrollPane64.setViewportView(AllergyHeader);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(txt_pName, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pSex, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pAge, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pIcNo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pID, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pRace)))
                .addGap(111, 111, 111)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_pBloodSex, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane64, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(btnStatus)))
                .addGap(101, 101, 101))
            .addComponent(jSeparator1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_pName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_pSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_pAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_pIcNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_pID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_pRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_pBloodSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane64, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jTabbedPane3.setName("jTabbedPane3"); // NOI18N

        jPanel10.setName("jPanel10"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jPanel6.setName("jPanel6"); // NOI18N

        jLabel17.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel17.setText("Chief Complaint");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText("Problem Status :");
        jLabel18.setName("jLabel18"); // NOI18N

        rbtn_cActive.setText("Active");
        rbtn_cActive.setName("rbtn_cActive"); // NOI18N

        rbtn_cInactive.setText("Inactive");
        rbtn_cInactive.setName("rbtn_cInactive"); // NOI18N

        txt_complaintSearch.setName("txt_complaintSearch"); // NOI18N

        lbl_complaintSearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintSearch.setText("Problem :");
        lbl_complaintSearch.setName("lbl_complaintSearch"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        lbx_complaintSearch.setName("lbx_complaintSearch"); // NOI18N
        jScrollPane4.setViewportView(lbx_complaintSearch);

        cbx_site.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Right", "Left" }));
        cbx_site.setName("cbx_site"); // NOI18N

        cbx_laterality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Right", "Left" }));
        cbx_laterality.setName("cbx_laterality"); // NOI18N

        lbl_laterality.setText("Laterality :");
        lbl_laterality.setName("lbl_laterality"); // NOI18N

        lbl_site.setText("Site :");
        lbl_site.setName("lbl_site"); // NOI18N

        cbx_cSeverity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Mild", "Moderate", "Severe" }));
        cbx_cSeverity.setToolTipText("");
        cbx_cSeverity.setName("cbx_cSeverity"); // NOI18N

        lbl_cSeverity.setText("Severity :");
        lbl_cSeverity.setName("lbl_cSeverity"); // NOI18N

        lbl_duration.setText("Duration :");
        lbl_duration.setName("lbl_duration"); // NOI18N

        txt_duration.setName("txt_duration"); // NOI18N

        cbx_duration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Minutes", "Hour", "Day", "Week", "Month", "Year" }));
        cbx_duration.setName("cbx_duration"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        txt_complaintComment.setColumns(20);
        txt_complaintComment.setLineWrap(true);
        txt_complaintComment.setRows(5);
        txt_complaintComment.setText(" ");
        txt_complaintComment.setName("txt_complaintComment"); // NOI18N
        jScrollPane5.setViewportView(txt_complaintComment);

        lbl_complaintComment.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintComment.setText("Comment:");
        lbl_complaintComment.setName("lbl_complaintComment"); // NOI18N

        jScrollPane20.setName("jScrollPane20"); // NOI18N

        jTextArea12.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea12.setColumns(20);
        jTextArea12.setRows(5);
        jTextArea12.setBorder(null);
        jTextArea12.setName("jTextArea12"); // NOI18N
        jScrollPane20.setViewportView(jTextArea12);

        jLabel19.setText("Previous Visit Date:");
        jLabel19.setName("jLabel19"); // NOI18N

        jScrollPane32.setName("jScrollPane32"); // NOI18N

        tbl_cc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Problem", "Severity", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_cc.setName("tbl_cc"); // NOI18N
        jScrollPane32.setViewportView(tbl_cc);

        btn_complaintClear.setText("Clear");
        btn_complaintClear.setName("btn_complaintClear"); // NOI18N

        btnSrcComplaint.setText("Search");
        btnSrcComplaint.setName("btnSrcComplaint"); // NOI18N

        btn_complaintAccept.setText("Accept");
        btn_complaintAccept.setName("btn_complaintAccept"); // NOI18N
        btn_complaintAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_complaintAcceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel17))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(lbl_complaintComment)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(btn_complaintAccept)
                                                .addGap(39, 39, 39)
                                                .addComponent(btn_complaintClear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(26, 26, 26)
                                .addComponent(rbtn_cActive)
                                .addGap(51, 51, 51)
                                .addComponent(rbtn_cInactive))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(lbl_complaintSearch)
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_complaintSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSrcComplaint))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(lbl_duration)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbx_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(lbl_cSeverity)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbx_cSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(143, 143, 143)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(lbl_site)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbx_site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(lbl_laterality)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbx_laterality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(rbtn_cActive)
                    .addComponent(rbtn_cInactive))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(jScrollPane20, 0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_complaintSearch)
                            .addComponent(txt_complaintSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSrcComplaint))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbx_cSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_cSeverity))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_duration)
                                    .addComponent(txt_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbx_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_site)
                                    .addComponent(cbx_site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_laterality)
                                    .addComponent(cbx_laterality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_complaintComment)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btn_complaintClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addComponent(jLabel19))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_complaintAccept)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("C.Complaints", jPanel10);

        jPanel34.setName("jPanel34"); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("History Of Present Illness");
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setText("Details :");
        jLabel21.setName("jLabel21"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        HPI_DetailstxtArea.setColumns(20);
        HPI_DetailstxtArea.setRows(5);
        HPI_DetailstxtArea.setName("HPI_DetailstxtArea"); // NOI18N
        jScrollPane3.setViewportView(HPI_DetailstxtArea);

        HPI_AcceptBtn.setText("Accept");
        HPI_AcceptBtn.setName("HPI_AcceptBtn"); // NOI18N
        HPI_AcceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPI_AcceptBtnActionPerformed(evt);
            }
        });

        HPI_ClrBtn.setText("Clear");
        HPI_ClrBtn.setName("HPI_ClrBtn"); // NOI18N

        jLabel22.setText("Previous History Of Present Illness :");
        jLabel22.setName("jLabel22"); // NOI18N

        jScrollPane33.setName("jScrollPane33"); // NOI18N

        tbl_HPI1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "No", "Details"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HPI1.setName("tbl_HPI1"); // NOI18N
        jScrollPane33.setViewportView(tbl_HPI1);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel20))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(HPI_AcceptBtn)
                        .addGap(18, 18, 18)
                        .addComponent(HPI_ClrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HPI_AcceptBtn)
                    .addComponent(HPI_ClrBtn))
                .addGap(40, 40, 40)
                .addComponent(jLabel22)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("HPI", jPanel34);

        jPanel12.setName("jPanel12"); // NOI18N

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        jPanel11.setName("jPanel11"); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Past Medical History");
        jLabel23.setName("jLabel23"); // NOI18N

        txt_PMHSearch.setName("txt_PMHSearch"); // NOI18N

        lbl_complaintSearch1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintSearch1.setText("Search :");
        lbl_complaintSearch1.setName("lbl_complaintSearch1"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        lbx_PMHSearch.setName("lbx_PMHSearch"); // NOI18N
        jScrollPane6.setViewportView(lbx_PMHSearch);

        jLabel24.setText("Diagnosis :");
        jLabel24.setName("jLabel24"); // NOI18N

        txt_PMHDiagnosis.setName("txt_PMHDiagnosis"); // NOI18N

        jLabel25.setText("Problem Status :");
        jLabel25.setName("jLabel25"); // NOI18N

        rbtn_cActive1.setText("Active");
        rbtn_cActive1.setName("rbtn_cActive1"); // NOI18N

        rbtn_cInactive1.setText("Inactive");
        rbtn_cInactive1.setName("rbtn_cInactive1"); // NOI18N

        jLabel26.setText("Comments :");
        jLabel26.setName("jLabel26"); // NOI18N

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        txt_PMHComment.setColumns(20);
        txt_PMHComment.setRows(5);
        txt_PMHComment.setName("txt_PMHComment"); // NOI18N
        jScrollPane7.setViewportView(txt_PMHComment);

        btnSrcComplaint1.setText("Search");
        btnSrcComplaint1.setName("btnSrcComplaint1"); // NOI18N
        btnSrcComplaint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcComplaint1ActionPerformed(evt);
            }
        });

        PMH_accBtn.setText("Accept");
        PMH_accBtn.setName("PMH_accBtn"); // NOI18N
        PMH_accBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMH_accBtnActionPerformed(evt);
            }
        });

        PMH_clearBtn.setText("Clear");
        PMH_clearBtn.setName("PMH_clearBtn"); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel23))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_complaintSearch1)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(rbtn_cActive1)
                                .addGap(39, 39, 39)
                                .addComponent(rbtn_cInactive1))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_PMHDiagnosis, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_PMHSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSrcComplaint1)))))
                .addContainerGap(115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(PMH_accBtn)
                .addGap(18, 18, 18)
                .addComponent(PMH_clearBtn)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel23)
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_PMHSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSrcComplaint1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbl_complaintSearch1)
                        .addGap(158, 158, 158)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txt_PMHDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(rbtn_cActive1))
                    .addComponent(rbtn_cInactive1))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PMH_accBtn)
                    .addComponent(PMH_clearBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane8.setViewportView(jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("PMH", jPanel12);

        jPanel35.setName("jPanel35"); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Family History");
        jLabel29.setName("jLabel29"); // NOI18N

        txt_FHSearch.setName("txt_FHSearch"); // NOI18N

        btnSrcSocialHistory1.setText("Search");
        btnSrcSocialHistory1.setName("btnSrcSocialHistory1"); // NOI18N
        btnSrcSocialHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcSocialHistory1ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel30.setText("Problem :");
        jLabel30.setName("jLabel30"); // NOI18N

        jScrollPane28.setName("jScrollPane28"); // NOI18N

        lbx_FHSearch.setName("lbx_FHSearch"); // NOI18N
        jScrollPane28.setViewportView(lbx_FHSearch);

        jLabel31.setText("Diagnosis :");
        jLabel31.setName("jLabel31"); // NOI18N

        txt_FHDiagnosis.setName("txt_FHDiagnosis"); // NOI18N

        jLabel32.setText("Relationship :");
        jLabel32.setName("jLabel32"); // NOI18N

        FH_Relationship.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FH_Relationship.setName("FH_Relationship"); // NOI18N

        jLabel33.setText("Comments :");
        jLabel33.setName("jLabel33"); // NOI18N

        jScrollPane10.setName("jScrollPane10"); // NOI18N

        FH_Comments.setColumns(20);
        FH_Comments.setRows(5);
        FH_Comments.setName("FH_Comments"); // NOI18N
        jScrollPane10.setViewportView(FH_Comments);

        FH_clrBtn.setText("Clear");
        FH_clrBtn.setName("FH_clrBtn"); // NOI18N

        FH_AccBtn.setText("Accept");
        FH_AccBtn.setName("FH_AccBtn"); // NOI18N
        FH_AccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH_AccBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FH_Relationship, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel33))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                            .addComponent(txt_FHDiagnosis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(txt_FHSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSrcSocialHistory1))
                            .addComponent(jScrollPane28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                        .addGap(101, 101, 101))))
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel29))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(FH_AccBtn)
                        .addGap(18, 18, 18)
                        .addComponent(FH_clrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txt_FHSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcSocialHistory1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txt_FHDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(FH_Relationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FH_clrBtn)
                    .addComponent(FH_AccBtn))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Family History", jPanel35);

        jPanel14.setName("jPanel14"); // NOI18N

        jScrollPane9.setName("jScrollPane9"); // NOI18N

        jPanel13.setName("jPanel13"); // NOI18N

        jScrollPane42.setName("jScrollPane42"); // NOI18N

        tbl_sh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Social History", "Date On Set"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sh.setName("tbl_sh"); // NOI18N
        jScrollPane42.setViewportView(tbl_sh);

        jScrollPane26.setName("jScrollPane26"); // NOI18N

        lbx_socialProblem.setName("lbx_socialProblem"); // NOI18N
        jScrollPane26.setViewportView(lbx_socialProblem);

        lbl_socialHistory.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_socialHistory.setText("Social History");
        lbl_socialHistory.setName("lbl_socialHistory"); // NOI18N

        txt_socialDate1.setName("txt_socialDate1"); // NOI18N

        jLabel27.setText("Previous Visit Date:");
        jLabel27.setName("jLabel27"); // NOI18N

        lbl_shdate.setName("lbl_shdate"); // NOI18N

        jScrollPane27.setName("jScrollPane27"); // NOI18N

        txt_socialComment.setColumns(20);
        txt_socialComment.setRows(5);
        txt_socialComment.setName("txt_socialComment"); // NOI18N
        jScrollPane27.setViewportView(txt_socialComment);

        jLabel67.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel67.setText("Comments : ");
        jLabel67.setName("jLabel67"); // NOI18N

        jLabel66.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel66.setText("Since When : ");
        jLabel66.setName("jLabel66"); // NOI18N

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel28.setText("Problem :");
        jLabel28.setName("jLabel28"); // NOI18N

        txt_socialProblem.setName("txt_socialProblem"); // NOI18N

        jScrollPane30.setBackground(new java.awt.Color(173, 182, 200));
        jScrollPane30.setName("jScrollPane30"); // NOI18N

        jTextArea15.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea15.setColumns(20);
        jTextArea15.setRows(5);
        jTextArea15.setBorder(null);
        jTextArea15.setName("jTextArea15"); // NOI18N
        jScrollPane30.setViewportView(jTextArea15);

        btnSrcSocialHistory.setText("Search");
        btnSrcSocialHistory.setName("btnSrcSocialHistory"); // NOI18N

        btn_sclAccept.setText("Accept");
        btn_sclAccept.setName("btn_sclAccept"); // NOI18N
        btn_sclAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sclAcceptActionPerformed(evt);
            }
        });

        btn_sclClear.setText("Clear");
        btn_sclClear.setName("btn_sclClear"); // NOI18N

        jScrollPane65.setBorder(null);
        jScrollPane65.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane65.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane65.setName("jScrollPane65"); // NOI18N

        jTextArea17.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea17.setColumns(20);
        jTextArea17.setRows(5);
        jTextArea17.setName("jTextArea17"); // NOI18N
        jScrollPane65.setViewportView(jTextArea17);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(359, 359, 359))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_socialHistory)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel66)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel28)
                                                .addComponent(jLabel67))
                                            .addGap(17, 17, 17)
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane27)
                                                .addComponent(txt_socialDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                    .addComponent(txt_socialProblem)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnSrcSocialHistory))
                                                .addComponent(jScrollPane26))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGap(145, 145, 145)
                                    .addComponent(btn_sclAccept)
                                    .addGap(69, 69, 69)
                                    .addComponent(btn_sclClear)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(lbl_shdate, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(3028, 3028, 3028))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3052, 3052, 3052))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(435, 435, 435)
                .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_socialHistory)
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txt_socialProblem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcSocialHistory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66)
                    .addComponent(txt_socialDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sclClear)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(btn_sclAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addGap(122, 122, 122)))
                .addComponent(lbl_shdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jScrollPane9.setViewportView(jPanel13);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Social History", jPanel14);

        jPanel36.setName("jPanel36"); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Blood Group / G6PD");
        jLabel34.setName("jLabel34"); // NOI18N

        jLabel35.setText("Blood Type :");
        jLabel35.setName("jLabel35"); // NOI18N

        comboBloodType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBloodType.setName("comboBloodType"); // NOI18N

        jLabel36.setText("Rhesus Type :");
        jLabel36.setName("jLabel36"); // NOI18N

        rbtn_Bpositive.setText("Positive");
        rbtn_Bpositive.setName("rbtn_Bpositive"); // NOI18N

        rbtn_Bnegative.setText("Negative");
        rbtn_Bnegative.setName("rbtn_Bnegative"); // NOI18N

        jLabel37.setText("G6PD Status :");
        jLabel37.setName("jLabel37"); // NOI18N

        rbtn_Bnormal.setText("Normal");
        rbtn_Bnormal.setName("rbtn_Bnormal"); // NOI18N

        rbtn_Bdeficient.setText("Deficient");
        rbtn_Bdeficient.setName("rbtn_Bdeficient"); // NOI18N

        jLabel38.setText("Comments :");
        jLabel38.setName("jLabel38"); // NOI18N

        jScrollPane11.setName("jScrollPane11"); // NOI18N

        txt_BldCmmnt.setColumns(20);
        txt_BldCmmnt.setRows(5);
        txt_BldCmmnt.setName("txt_BldCmmnt"); // NOI18N
        jScrollPane11.setViewportView(txt_BldCmmnt);

        BG_accBtn.setText("Accept");
        BG_accBtn.setName("BG_accBtn"); // NOI18N
        BG_accBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG_accBtnActionPerformed(evt);
            }
        });

        BG_clrBtn.setText("Clear");
        BG_clrBtn.setName("BG_clrBtn"); // NOI18N

        jScrollPane66.setBorder(null);
        jScrollPane66.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane66.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane66.setName("jScrollPane66"); // NOI18N

        jTextArea18.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea18.setColumns(20);
        jTextArea18.setRows(5);
        jTextArea18.setName("jTextArea18"); // NOI18N
        jScrollPane66.setViewportView(jTextArea18);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel34))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtn_Bpositive)
                                    .addComponent(rbtn_Bnormal))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtn_Bnegative)
                                    .addComponent(rbtn_Bdeficient)))
                            .addComponent(comboBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(BG_accBtn)
                        .addGap(30, 30, 30)
                        .addComponent(BG_clrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jScrollPane66, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(192, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(comboBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(rbtn_Bpositive)
                    .addComponent(rbtn_Bnegative))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(rbtn_Bnormal)
                    .addComponent(rbtn_Bdeficient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BG_clrBtn)
                    .addComponent(BG_accBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jScrollPane66, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Blood Group/G6PD", jPanel36);

        jPanel15.setName("jPanel15"); // NOI18N

        jScrollPane12.setName("jScrollPane12"); // NOI18N

        jPanel37.setName("jPanel37"); // NOI18N

        lbl_alldate.setName("lbl_alldate"); // NOI18N

        jScrollPane29.setBackground(new java.awt.Color(173, 182, 200));
        jScrollPane29.setName("jScrollPane29"); // NOI18N

        jTextArea14.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea14.setColumns(20);
        jTextArea14.setRows(5);
        jTextArea14.setBorder(null);
        jTextArea14.setName("jTextArea14"); // NOI18N
        jScrollPane29.setViewportView(jTextArea14);

        txt_allergyDate2.setName("txt_allergyDate2"); // NOI18N

        jLabel39.setText("Previous Visit Date:");
        jLabel39.setName("jLabel39"); // NOI18N

        jScrollPane24.setName("jScrollPane24"); // NOI18N

        lbx_allergySearch.setName("lbx_allergySearch"); // NOI18N
        jScrollPane24.setViewportView(lbx_allergySearch);

        lbl_allergy.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        lbl_allergy.setText("Allergy");
        lbl_allergy.setName("lbl_allergy"); // NOI18N

        lb_allergySearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lb_allergySearch.setText("Search:");
        lb_allergySearch.setName("lb_allergySearch"); // NOI18N

        jScrollPane40.setName("jScrollPane40"); // NOI18N

        tbl_all.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Allergy Name", "Date On Set"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_all.setName("tbl_all"); // NOI18N
        jScrollPane40.setViewportView(tbl_all);

        lbl_allergyComments.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_allergyComments.setText("Comments : ");
        lbl_allergyComments.setName("lbl_allergyComments"); // NOI18N

        lbl_allergyDate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_allergyDate.setText("Since When : ");
        lbl_allergyDate.setName("lbl_allergyDate"); // NOI18N

        jScrollPane25.setName("jScrollPane25"); // NOI18N

        txt_allergyComments.setColumns(20);
        txt_allergyComments.setRows(5);
        txt_allergyComments.setName("txt_allergyComments"); // NOI18N
        jScrollPane25.setViewportView(txt_allergyComments);

        txt_allergySearch.setName("txt_allergySearch"); // NOI18N

        btnSrcAllergy.setText("Search");
        btnSrcAllergy.setName("btnSrcAllergy"); // NOI18N

        btn_allergyAccept.setText("Accept");
        btn_allergyAccept.setName("btn_allergyAccept"); // NOI18N
        btn_allergyAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allergyAcceptActionPerformed(evt);
            }
        });

        btn_allergyClear.setText("Clear");
        btn_allergyClear.setName("btn_allergyClear"); // NOI18N

        jScrollPane67.setBorder(null);
        jScrollPane67.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane67.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane67.setName("jScrollPane67"); // NOI18N

        jTextArea19.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea19.setColumns(20);
        jTextArea19.setRows(5);
        jTextArea19.setName("jTextArea19"); // NOI18N
        jScrollPane67.setViewportView(jTextArea19);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_allergy)
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addComponent(lb_allergySearch)
                            .addGap(38, 38, 38)
                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                                    .addComponent(txt_allergySearch, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSrcAllergy))
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addComponent(lbl_allergyComments)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addComponent(lbl_allergyDate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_allergyDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addGap(209, 209, 209)
                            .addComponent(btn_allergyAccept)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_allergyClear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_alldate, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane67, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(lbl_allergy)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_allergySearch)
                            .addComponent(txt_allergySearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSrcAllergy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_allergyDate)
                            .addComponent(txt_allergyDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_allergyComments)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_allergyAccept)
                                .addComponent(btn_allergyClear))
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(lbl_alldate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                        .addComponent(jScrollPane67, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );

        jScrollPane12.setViewportView(jPanel37);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Allergy", jPanel15);

        jPanel16.setName("jPanel16"); // NOI18N

        jScrollPane13.setName("jScrollPane13"); // NOI18N

        jPanel38.setName("jPanel38"); // NOI18N

        jLabel40.setText("Previous Visit Date:");
        jLabel40.setName("jLabel40"); // NOI18N

        txt_immSearch.setName("txt_immSearch"); // NOI18N

        jLabel41.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel41.setText("Immunisation");
        jLabel41.setName("jLabel41"); // NOI18N

        jScrollPane34.setName("jScrollPane34"); // NOI18N

        tbl_imm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Immunisation Name", "Date Taken"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_imm.setName("tbl_imm"); // NOI18N
        jScrollPane34.setViewportView(tbl_imm);

        jScrollPane14.setName("jScrollPane14"); // NOI18N

        lbx_immSearch.setName("lbx_immSearch"); // NOI18N
        jScrollPane14.setViewportView(lbx_immSearch);

        lbl_immDate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_immDate.setText("Date:");
        lbl_immDate.setName("lbl_immDate"); // NOI18N

        lbl_immSearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_immSearch.setText("Search:");
        lbl_immSearch.setName("lbl_immSearch"); // NOI18N

        jScrollPane18.setName("jScrollPane18"); // NOI18N

        jTextArea10.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea10.setColumns(20);
        jTextArea10.setEditable(false);
        jTextArea10.setLineWrap(true);
        jTextArea10.setRows(5);
        jTextArea10.setName("jTextArea10"); // NOI18N
        jScrollPane18.setViewportView(jTextArea10);

        jScrollPane15.setName("jScrollPane15"); // NOI18N

        txt_immComment.setColumns(20);
        txt_immComment.setRows(5);
        txt_immComment.setName("txt_immComment"); // NOI18N
        jScrollPane15.setViewportView(txt_immComment);

        lbl_immComment.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_immComment.setText("Comment:");
        lbl_immComment.setName("lbl_immComment"); // NOI18N

        lbl_immdate.setName("lbl_immdate"); // NOI18N

        txt_immDate1.setName("txt_immDate1"); // NOI18N

        btnSrcImmunisation.setText("Search");
        btnSrcImmunisation.setName("btnSrcImmunisation"); // NOI18N

        btn_immAccept.setText("Accept");
        btn_immAccept.setName("btn_immAccept"); // NOI18N
        btn_immAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_immAcceptActionPerformed(evt);
            }
        });

        btn_immClear.setText("Clear");
        btn_immClear.setName("btn_immClear"); // NOI18N

        jScrollPane68.setBorder(null);
        jScrollPane68.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane68.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane68.setName("jScrollPane68"); // NOI18N

        jTextArea20.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea20.setColumns(20);
        jTextArea20.setRows(5);
        jTextArea20.setName("jTextArea20"); // NOI18N
        jScrollPane68.setViewportView(jTextArea20);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jScrollPane68, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel38Layout.createSequentialGroup()
                                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_immComment)
                                            .addComponent(lbl_immSearch))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                            .addComponent(txt_immDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                                                .addComponent(txt_immSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSrcImmunisation))))
                                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel38Layout.createSequentialGroup()
                                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(22, 22, 22)
                                            .addComponent(lbl_immdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_immDate)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGap(239, 239, 239)
                                .addComponent(btn_immAccept)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_immClear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addContainerGap(577, Short.MAX_VALUE))))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_immSearch)
                                    .addComponent(txt_immSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSrcImmunisation))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_immComment)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_immDate)
                                .addGap(19, 19, 19)
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_immAccept)
                                    .addComponent(btn_immClear)))
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_immDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(lbl_immdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane68, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jScrollPane13.setViewportView(jPanel38);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Immunization", jPanel16);

        jPanel17.setName("jPanel17"); // NOI18N

        jScrollPane16.setName("jScrollPane16"); // NOI18N

        jPanel39.setName("jPanel39"); // NOI18N

        lbl_dComments.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_dComments.setText("Comments : ");
        lbl_dComments.setName("lbl_dComments"); // NOI18N

        lbl_disDate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_disDate.setText("Since When : ");
        lbl_disDate.setName("lbl_disDate"); // NOI18N

        jScrollPane41.setName("jScrollPane41"); // NOI18N

        tbl_dis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Disability", "Date On Set"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dis.setName("tbl_dis"); // NOI18N
        jScrollPane41.setViewportView(tbl_dis);

        lbl_disdate.setName("lbl_disdate"); // NOI18N

        jLabel42.setText("Previous Visit Date:");
        jLabel42.setName("jLabel42"); // NOI18N

        jScrollPane21.setName("jScrollPane21"); // NOI18N

        lbx_disabilityType.setName("lbx_disabilityType"); // NOI18N
        jScrollPane21.setViewportView(lbx_disabilityType);

        lbl_disabilityType.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_disabilityType.setText("Disability Type :");
        lbl_disabilityType.setName("lbl_disabilityType"); // NOI18N

        txt_disabilityType.setName("txt_disabilityType"); // NOI18N

        lbl_disability.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        lbl_disability.setText("Disability");
        lbl_disability.setName("lbl_disability"); // NOI18N

        jScrollPane22.setName("jScrollPane22"); // NOI18N

        txt_dComments.setColumns(20);
        txt_dComments.setRows(5);
        txt_dComments.setName("txt_dComments"); // NOI18N
        jScrollPane22.setViewportView(txt_dComments);

        jScrollPane23.setBackground(new java.awt.Color(173, 182, 200));
        jScrollPane23.setName("jScrollPane23"); // NOI18N

        jTextArea13.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea13.setColumns(20);
        jTextArea13.setRows(5);
        jTextArea13.setBorder(null);
        jTextArea13.setName("jTextArea13"); // NOI18N
        jScrollPane23.setViewportView(jTextArea13);

        txt_dDate1.setName("txt_dDate1"); // NOI18N

        btnSrcDisability.setText("Search");
        btnSrcDisability.setName("btnSrcDisability"); // NOI18N

        btn_dAccept.setText("Accept");
        btn_dAccept.setName("btn_dAccept"); // NOI18N
        btn_dAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dAcceptActionPerformed(evt);
            }
        });

        btn_dClear.setText("Clear");
        btn_dClear.setName("btn_dClear"); // NOI18N

        jScrollPane69.setBorder(null);
        jScrollPane69.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane69.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane69.setName("jScrollPane69"); // NOI18N

        jTextArea21.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea21.setColumns(20);
        jTextArea21.setRows(5);
        jTextArea21.setName("jTextArea21"); // NOI18N
        jScrollPane69.setViewportView(jTextArea21);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel39Layout.createSequentialGroup()
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_disabilityType)
                                        .addComponent(lbl_disDate)
                                        .addComponent(lbl_dComments))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_dDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane22)
                                        .addGroup(jPanel39Layout.createSequentialGroup()
                                            .addComponent(txt_disabilityType, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnSrcDisability))
                                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel39Layout.createSequentialGroup()
                                    .addGap(181, 181, 181)
                                    .addComponent(btn_dAccept)
                                    .addGap(36, 36, 36)
                                    .addComponent(btn_dClear)))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel39Layout.createSequentialGroup()
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_disdate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(111, 111, 111)
                        .addComponent(jScrollPane69, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbl_disability)))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel39Layout.createSequentialGroup()
                                .addComponent(lbl_disability)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_disabilityType)
                                    .addComponent(txt_disabilityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSrcDisability))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_disDate)
                                    .addComponent(txt_dDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_dComments)
                                    .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_dAccept)
                            .addComponent(btn_dClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(lbl_disdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane16.setViewportView(jPanel39);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Disability", jPanel17);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Subjective", jPanel5);

        jPanel7.setName("jPanel7"); // NOI18N

        jTabbedPane2.setName("jTabbedPane2"); // NOI18N

        jPanel18.setName("jPanel18"); // NOI18N

        jScrollPane17.setName("jScrollPane17"); // NOI18N

        jPanel40.setName("jPanel40"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N

        jSeparator4.setName("jSeparator4"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel43.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel43.setText("Biometrics/Vital Sign");
        jLabel43.setName("jLabel43"); // NOI18N

        jLabel44.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel44.setText("Height");
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel45.setText("Temperature");
        jLabel45.setName("jLabel45"); // NOI18N

        jLabel46.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel46.setText("Head Circumference");
        jLabel46.setName("jLabel46"); // NOI18N

        jLabel47.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel47.setText("BMI");
        jLabel47.setName("jLabel47"); // NOI18N

        jLabel48.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel48.setText("Weight");
        jLabel48.setName("jLabel48"); // NOI18N

        jSeparator6.setName("jSeparator6"); // NOI18N

        txt_headCircumference.setName("txt_headCircumference"); // NOI18N

        txt_weight.setName("txt_weight"); // NOI18N

        jSeparator5.setName("jSeparator5"); // NOI18N

        txt_height.setName("txt_height"); // NOI18N

        jLabel49.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel49.setText("Pulse");
        jLabel49.setName("jLabel49"); // NOI18N

        txt_temperature.setName("txt_temperature"); // NOI18N

        txt_pulse.setName("txt_pulse"); // NOI18N

        jLabel50.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel50.setText("cm");
        jLabel50.setName("jLabel50"); // NOI18N

        txt_weightStatus.setEditable(false);
        txt_weightStatus.setName("txt_weightStatus"); // NOI18N

        jScrollPane19.setName("jScrollPane19"); // NOI18N

        jTextArea11.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea11.setColumns(20);
        jTextArea11.setEditable(false);
        jTextArea11.setLineWrap(true);
        jTextArea11.setRows(5);
        jTextArea11.setName("jTextArea11"); // NOI18N
        jScrollPane19.setViewportView(jTextArea11);

        jLabel55.setText("Weight Status");
        jLabel55.setName("jLabel55"); // NOI18N

        jScrollPane35.setName("jScrollPane35"); // NOI18N

        tbl_vs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Height", "Weight", "Pulse"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_vs.setName("tbl_vs"); // NOI18N
        jScrollPane35.setViewportView(tbl_vs);

        jLabel51.setText("Previous Visit Date:");
        jLabel51.setName("jLabel51"); // NOI18N

        lbl_vsdate.setName("lbl_vsdate"); // NOI18N

        jLabel52.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel52.setText("/min");
        jLabel52.setName("jLabel52"); // NOI18N

        jLabel53.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel53.setText("celcius");
        jLabel53.setName("jLabel53"); // NOI18N

        jLabel54.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel54.setText("kg/m2");
        jLabel54.setName("jLabel54"); // NOI18N

        jLabel56.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel56.setText("kg");
        jLabel56.setName("jLabel56"); // NOI18N

        jLabel57.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel57.setText("cm");
        jLabel57.setName("jLabel57"); // NOI18N

        txt_bmi.setEditable(false);
        txt_bmi.setName("txt_bmi"); // NOI18N

        btn_calculateBmi.setText("Calculate BMI");
        btn_calculateBmi.setName("btn_calculateBmi"); // NOI18N
        btn_calculateBmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculateBmiActionPerformed(evt);
            }
        });

        btn_vitalSignAccept.setText("Accept");
        btn_vitalSignAccept.setName("btn_vitalSignAccept"); // NOI18N
        btn_vitalSignAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vitalSignAcceptActionPerformed(evt);
            }
        });

        btn_vitalSignClear.setText("Clear");
        btn_vitalSignClear.setName("btn_vitalSignClear"); // NOI18N

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMaximum(210);
        jSlider1.setMinimum(140);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(175);
        jSlider1.setName("jSlider1"); // NOI18N

        jSlider2.setMajorTickSpacing(30);
        jSlider2.setMaximum(150);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setValue(70);
        jSlider2.setName("jSlider2"); // NOI18N

        jSlider3.setMajorTickSpacing(10);
        jSlider3.setMinimum(50);
        jSlider3.setPaintLabels(true);
        jSlider3.setPaintTicks(true);
        jSlider3.setToolTipText("");
        jSlider3.setValue(75);
        jSlider3.setName("jSlider3"); // NOI18N

        jSlider4.setMajorTickSpacing(1);
        jSlider4.setMaximum(42);
        jSlider4.setMinimum(37);
        jSlider4.setPaintLabels(true);
        jSlider4.setPaintTicks(true);
        jSlider4.setValue(39);
        jSlider4.setName("jSlider4"); // NOI18N

        jSlider5.setMajorTickSpacing(10);
        jSlider5.setMaximum(150);
        jSlider5.setMinimum(50);
        jSlider5.setPaintLabels(true);
        jSlider5.setPaintTicks(true);
        jSlider5.setValue(100);
        jSlider5.setName("jSlider5"); // NOI18N

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel40Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator3)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel40Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                            .addComponent(jLabel45)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_temperature, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel53)
                                            .addGap(18, 18, 18)
                                            .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(61, 61, 61))
                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel40Layout.createSequentialGroup()
                                                    .addComponent(jLabel47)
                                                    .addGap(37, 37, 37)
                                                    .addComponent(txt_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel54)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btn_calculateBmi)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLabel55)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txt_weightStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel40Layout.createSequentialGroup()
                                                    .addComponent(jLabel46)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txt_headCircumference, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel57)
                                                    .addGap(37, 37, 37)
                                                    .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel40Layout.createSequentialGroup()
                                                    .addComponent(jLabel49)
                                                    .addGap(81, 81, 81)
                                                    .addComponent(txt_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                            .addComponent(btn_vitalSignAccept)
                                                            .addGap(33, 33, 33)
                                                            .addComponent(btn_vitalSignClear))
                                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                            .addComponent(jLabel52)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(36, 36, 36)
                                                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGap(64, 64, 64)))))))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel50)
                                .addGap(95, 95, 95)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18)
                        .addComponent(txt_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel56)
                        .addGap(98, 98, 98)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lbl_vsdate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(txt_weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56))
                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txt_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(txt_weightStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(btn_calculateBmi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(txt_headCircumference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57))
                    .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jLabel53)
                        .addComponent(txt_temperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel49)
                                .addComponent(txt_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel52))
                            .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_vitalSignAccept)
                            .addComponent(btn_vitalSignClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(lbl_vsdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane17.setViewportView(jPanel40);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Vital Sign", jPanel18);

        jPanel19.setName("jPanel19"); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Phys.Exam", jPanel19);

        jPanel20.setName("jPanel20"); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Radiology Result", jPanel20);

        jPanel21.setName("jPanel21"); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Laboratory Result", jPanel21);

        jPanel22.setName("jPanel22"); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Observation Procedure", jPanel22);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Objective", jPanel7);

        jPanel8.setName("jPanel8"); // NOI18N

        jTabbedPane4.setName("jTabbedPane4"); // NOI18N

        jPanel23.setName("jPanel23"); // NOI18N

        jScrollPane31.setName("jScrollPane31"); // NOI18N

        jPanel41.setName("jPanel41"); // NOI18N

        jLabel58.setText("Previous Visit Date:");
        jLabel58.setName("jLabel58"); // NOI18N

        lbl_dSeverity.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_dSeverity.setText("Severity:");
        lbl_dSeverity.setName("lbl_dSeverity"); // NOI18N

        cbx_dSeverity.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cbx_dSeverity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Mild", "Normal", "Acute" }));
        cbx_dSeverity.setName("cbx_dSeverity"); // NOI18N

        lbl_ddate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_ddate.setText("Date:");
        lbl_ddate.setName("lbl_ddate"); // NOI18N

        txt_date1.setName("txt_date1"); // NOI18N

        jScrollPane36.setName("jScrollPane36"); // NOI18N

        jTextArea9.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea9.setColumns(20);
        jTextArea9.setEditable(false);
        jTextArea9.setLineWrap(true);
        jTextArea9.setRows(5);
        jTextArea9.setBorder(null);
        jTextArea9.setName("jTextArea9"); // NOI18N
        jScrollPane36.setViewportView(jTextArea9);

        jLabel59.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel59.setText("Diagnosis");
        jLabel59.setName("jLabel59"); // NOI18N

        lbl_diagnosisSearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_diagnosisSearch.setText("Search:");
        lbl_diagnosisSearch.setName("lbl_diagnosisSearch"); // NOI18N

        txt_diagnosisSearch.setName("txt_diagnosisSearch"); // NOI18N

        jScrollPane37.setName("jScrollPane37"); // NOI18N

        tbl_diag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Diagnosis Name", "Severity", "Date On Set"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_diag.setName("tbl_diag"); // NOI18N
        jScrollPane37.setViewportView(tbl_diag);

        jScrollPane38.setName("jScrollPane38"); // NOI18N

        lbx_diagnosisSearch.setName("lbx_diagnosisSearch"); // NOI18N
        jScrollPane38.setViewportView(lbx_diagnosisSearch);

        lbl_diagdate.setName("lbl_diagdate"); // NOI18N

        rbtn_dFinal.setText("Final");
        rbtn_dFinal.setName("rbtn_dFinal"); // NOI18N

        rbtn_dProvisional.setText("Provisional");
        rbtn_dProvisional.setName("rbtn_dProvisional"); // NOI18N

        btnSrcDiagnosis.setText("Search");
        btnSrcDiagnosis.setName("btnSrcDiagnosis"); // NOI18N

        btn_diagnosisAccept.setText("Accept");
        btn_diagnosisAccept.setName("btn_diagnosisAccept"); // NOI18N
        btn_diagnosisAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_diagnosisAcceptActionPerformed(evt);
            }
        });

        btn_diagnosisClear.setText("Clear");
        btn_diagnosisClear.setName("btn_diagnosisClear"); // NOI18N

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_diagnosisClear)
                                .addGroup(jPanel41Layout.createSequentialGroup()
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(lbl_diagdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_dSeverity)
                            .addComponent(lbl_ddate))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_dSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(btn_diagnosisAccept))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                                .addComponent(rbtn_dFinal)
                                .addGap(76, 76, 76)
                                .addComponent(rbtn_dProvisional)
                                .addGap(130, 130, 130))
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(lbl_diagnosisSearch)
                                .addGap(42, 42, 42)
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel41Layout.createSequentialGroup()
                                        .addComponent(jScrollPane38, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47))
                                    .addGroup(jPanel41Layout.createSequentialGroup()
                                        .addComponent(txt_diagnosisSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSrcDiagnosis)))))))
                .addGap(84, 84, 84))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel59)
                .addContainerGap(612, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel41Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jScrollPane36, 0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addGap(13, 13, 13)
                                .addComponent(rbtn_dFinal))
                            .addComponent(rbtn_dProvisional))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_diagnosisSearch)
                            .addComponent(txt_diagnosisSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSrcDiagnosis))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dSeverity)
                    .addComponent(cbx_dSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_ddate)
                    .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_diagnosisAccept)
                    .addComponent(btn_diagnosisClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(lbl_diagdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane31.setViewportView(jPanel41);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Diagnosis", jPanel23);

        jPanel24.setName("jPanel24"); // NOI18N

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("Progress Notes");
        jLabel60.setName("jLabel60"); // NOI18N

        jLabel61.setText("Notes :");
        jLabel61.setName("jLabel61"); // NOI18N

        jScrollPane39.setName("jScrollPane39"); // NOI18N

        txt_pNotes.setColumns(20);
        txt_pNotes.setRows(5);
        txt_pNotes.setName("txt_pNotes"); // NOI18N
        jScrollPane39.setViewportView(txt_pNotes);

        PN_accBtn.setText("Accept");
        PN_accBtn.setName("PN_accBtn"); // NOI18N
        PN_accBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PN_accBtnActionPerformed(evt);
            }
        });

        PN_clrBtn.setText("Clear");
        PN_clrBtn.setName("PN_clrBtn"); // NOI18N

        jScrollPane70.setName("jScrollPane70"); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setName("jTextArea2"); // NOI18N
        jScrollPane70.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jLabel61)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel60))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(264, 264, 264)
                                .addComponent(PN_accBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(PN_clrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 208, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addContainerGap(702, Short.MAX_VALUE)
                        .addComponent(jScrollPane70, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel60)
                .addGap(19, 19, 19)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addGap(43, 43, 43)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PN_accBtn)
                    .addComponent(PN_clrBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addComponent(jScrollPane70, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Progress Notes", jPanel24);

        jPanel25.setName("jPanel25"); // NOI18N

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Problem List", jPanel25);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane1.addTab("Assessment", jPanel8);

        jPanel9.setName("jPanel9"); // NOI18N

        jTabbedPane5.setName("jTabbedPane5"); // NOI18N

        jPanel27.setName("jPanel27"); // NOI18N

        jScrollPane46.setName("jScrollPane46"); // NOI18N

        jPanel26.setName("jPanel26"); // NOI18N

        jLabel9.setText("Radiology Request");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText("Problem");
        jLabel10.setName("jLabel10"); // NOI18N

        jScrollPane43.setName("jScrollPane43"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("jList1"); // NOI18N
        jScrollPane43.setViewportView(jList1);

        jLabel11.setText("Request Type");
        jLabel11.setName("jLabel11"); // NOI18N

        jScrollPane44.setName("jScrollPane44"); // NOI18N

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "X-Ray", "MRI", "CTScan", "Ultrasound" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.setName("jList2"); // NOI18N
        jScrollPane44.setViewportView(jList2);

        jLabel12.setText("Region");
        jLabel12.setName("jLabel12"); // NOI18N

        jScrollPane45.setName("jScrollPane45"); // NOI18N

        jList3.setName("jList3"); // NOI18N
        jScrollPane45.setViewportView(jList3);

        jLabel13.setText("Test to be performed");
        jLabel13.setName("jLabel13"); // NOI18N

        jTextField3.setName("jTextField3"); // NOI18N

        jLabel14.setText("Service Provider");
        jLabel14.setName("jLabel14"); // NOI18N

        jSeparator7.setName("jSeparator7"); // NOI18N

        jLabel15.setText("Health Facility");
        jLabel15.setName("jLabel15"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setName("jComboBox3"); // NOI18N

        jLabel62.setText("Location");
        jLabel62.setName("jLabel62"); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setName("jComboBox4"); // NOI18N

        jSeparator8.setName("jSeparator8"); // NOI18N

        jLabel63.setText("Appointment");
        jLabel63.setName("jLabel63"); // NOI18N

        jTextField4.setText("jTextField4");
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel64.setText("Priority");
        jLabel64.setName("jLabel64"); // NOI18N

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setName("jComboBox5"); // NOI18N

        jLabel65.setText("Patient Condition");
        jLabel65.setName("jLabel65"); // NOI18N

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setName("jComboBox6"); // NOI18N

        btn_RadReqOP.setText("Other Providers");
        btn_RadReqOP.setName("btn_RadReqOP"); // NOI18N

        btn_RadReqPrint.setText("Print");
        btn_RadReqPrint.setName("btn_RadReqPrint"); // NOI18N

        btn_RadReqAcc.setText("Accept");
        btn_RadReqAcc.setName("btn_RadReqAcc"); // NOI18N

        btn_RadReqClr.setText("Clear");
        btn_RadReqClr.setName("btn_RadReqClr"); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel26Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel26Layout.createSequentialGroup()
                                        .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addComponent(jLabel14)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(btn_RadReqOP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_RadReqPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_RadReqAcc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_RadReqClr))
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel26Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator8))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel26Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(87, 87, 87)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel62)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel26Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane45, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane44, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane43, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_RadReqOP)
                    .addComponent(btn_RadReqPrint)
                    .addComponent(btn_RadReqAcc)
                    .addComponent(btn_RadReqClr))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jScrollPane46.setViewportView(jPanel26);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane46)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane46, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Laboratory Request", jPanel27);

        jPanel28.setName("jPanel28"); // NOI18N

        jScrollPane49.setName("jScrollPane49"); // NOI18N

        lbx_instruction.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbx_instruction.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Nil", "If required", "As directed ", "Before meals", "After meals", "Every second day", "Left side", "Right side", "To both sides", "Other" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lbx_instruction.setName("lbx_instruction"); // NOI18N
        jScrollPane49.setViewportView(lbx_instruction);

        jScrollPane53.setName("jScrollPane53"); // NOI18N

        jPanel42.setName("jPanel42"); // NOI18N

        jLabel77.setText("Product Name");
        jLabel77.setName("jLabel77"); // NOI18N

        jScrollPane54.setName("jScrollPane54"); // NOI18N

        lbx_productNameUStockSearch.setName("lbx_productNameUStockSearch"); // NOI18N
        jScrollPane54.setViewportView(lbx_productNameUStockSearch);

        cbx_drugDduration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "1", "2", "3", "4", "5", "6" }));
        cbx_drugDduration.setName("cbx_drugDduration"); // NOI18N

        jLabel78.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel78.setText("Frequency:");
        jLabel78.setName("jLabel78"); // NOI18N

        txt_dStrength.setName("txt_dStrength"); // NOI18N

        btnSrcDrugs1.setText("Search");
        btnSrcDrugs1.setName("btnSrcDrugs1"); // NOI18N
        btnSrcDrugs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcDrugs1ActionPerformed(evt);
            }
        });

        jScrollPane55.setName("jScrollPane55"); // NOI18N

        lbx_frequency.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbx_frequency.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Nil ", "In the morning", "At night", "Daily", "Twice a day", "3 times a day", "4 times a day", "Four hourly", "Six hourly", "Eight hourly", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lbx_frequency.setName("lbx_frequency"); // NOI18N
        jScrollPane55.setViewportView(lbx_frequency);

        jLabel79.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel79.setText("Quantity:");
        jLabel79.setName("jLabel79"); // NOI18N

        jLabel80.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel80.setText("Dose:");
        jLabel80.setName("jLabel80"); // NOI18N

        txt_dose.setName("txt_dose"); // NOI18N

        jLabel81.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel81.setText("Duration :");
        jLabel81.setName("jLabel81"); // NOI18N

        jLabel82.setText("Instruction:");
        jLabel82.setName("jLabel82"); // NOI18N

        jScrollPane56.setName("jScrollPane56"); // NOI18N

        lbx_instruction2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbx_instruction2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Nil", "If required", "As directed ", "Before meals", "After meals", "Every second day", "Left side", "Right side", "To both sides", "Other" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lbx_instruction2.setName("lbx_instruction2"); // NOI18N
        jScrollPane56.setViewportView(lbx_instruction2);

        txt_drugSearch.setName("txt_drugSearch"); // NOI18N
        txt_drugSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_drugSearchActionPerformed(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel83.setText("Drug Treatment");
        jLabel83.setName("jLabel83"); // NOI18N

        jLabel84.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel84.setText("Search:");
        jLabel84.setName("jLabel84"); // NOI18N

        jScrollPane57.setName("jScrollPane57"); // NOI18N

        lbx_drugSearch.setName("lbx_drugSearch"); // NOI18N
        jScrollPane57.setViewportView(lbx_drugSearch);

        txt_drugForm.setEnabled(false);
        txt_drugForm.setName("txt_drugForm"); // NOI18N

        cbx_drugDuration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Day", "Week", "Month", " " }));
        cbx_drugDuration.setName("cbx_drugDuration"); // NOI18N
        cbx_drugDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_drugDurationActionPerformed(evt);
            }
        });

        jLabel85.setText("Previous Visit Date:");
        jLabel85.setName("jLabel85"); // NOI18N

        jScrollPane58.setName("jScrollPane58"); // NOI18N

        tbl_drug1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Drug Name", "Dosage", "Frequency"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_drug1.setName("tbl_drug1"); // NOI18N
        jScrollPane58.setViewportView(tbl_drug1);

        lbl_drgdate1.setName("lbl_drgdate1"); // NOI18N

        btn_drugAccept1.setText("Accept");
        btn_drugAccept1.setName("btn_drugAccept1"); // NOI18N
        btn_drugAccept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_drugAccept1ActionPerformed(evt);
            }
        });

        btn_drugClear1.setText("Clear");
        btn_drugClear1.setName("btn_drugClear1"); // NOI18N

        jScrollPane59.setName("jScrollPane59"); // NOI18N

        lbx_instruction3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbx_instruction3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Nil", "If required", "As directed ", "Before meals", "After meals", "Every second day", "Left side", "Right side", "To both sides", "Other" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lbx_instruction3.setName("lbx_instruction3"); // NOI18N
        jScrollPane59.setViewportView(lbx_instruction3);

        jScrollPane71.setName("jScrollPane71"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane71.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel42Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel84)
                                    .addComponent(jLabel77)
                                    .addComponent(jLabel80))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel42Layout.createSequentialGroup()
                                        .addComponent(cbx_drugDduration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbx_drugDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                                        .addComponent(txt_drugSearch)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSrcDrugs1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                                        .addComponent(txt_dose, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel79)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_dStrength, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_drugForm, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane57)
                                    .addComponent(jScrollPane54)))
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83)
                                    .addGroup(jPanel42Layout.createSequentialGroup()
                                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btn_drugAccept1)
                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                .addComponent(jLabel78)
                                                .addGap(31, 31, 31)
                                                .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel82)
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane59, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_drugClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel81))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane71, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel42Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(lbl_drgdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel84))
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_drugSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSrcDrugs1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane57, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77)
                    .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel80))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81)
                            .addComponent(cbx_drugDduration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_drugDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel42Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane71, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(txt_dStrength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_drugForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel82)
                    .addComponent(jLabel78)
                    .addComponent(jScrollPane55, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane59, 0, 0, Short.MAX_VALUE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_drugAccept1)
                    .addComponent(btn_drugClear1))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel85)
                    .addComponent(lbl_drgdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane58, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jScrollPane53.setViewportView(jPanel42);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
            .addComponent(jScrollPane53, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Drug Treatment", jPanel28);

        jPanel29.setName("jPanel29"); // NOI18N

        jLabel68.setText("Monitoring Request");
        jLabel68.setName("jLabel68"); // NOI18N

        jLabel69.setText("Problem");
        jLabel69.setName("jLabel69"); // NOI18N

        jScrollPane47.setName("jScrollPane47"); // NOI18N

        jList4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList4.setName("jList4"); // NOI18N
        jScrollPane47.setViewportView(jList4);

        jLabel70.setText("Request Cat");
        jLabel70.setName("jLabel70"); // NOI18N

        jScrollPane48.setName("jScrollPane48"); // NOI18N

        jList5.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList5.setName("jList5"); // NOI18N
        jScrollPane48.setViewportView(jList5);

        jLabel71.setText("Request Item");
        jLabel71.setName("jLabel71"); // NOI18N

        jScrollPane50.setName("jScrollPane50"); // NOI18N

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setName("jTextArea6"); // NOI18N
        jScrollPane50.setViewportView(jTextArea6);

        jLabel72.setText("Test to be perform");
        jLabel72.setName("jLabel72"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N

        jSeparator9.setName("jSeparator9"); // NOI18N

        jLabel73.setText("Service Provider");
        jLabel73.setName("jLabel73"); // NOI18N

        jLabel74.setText("Patient Condition");
        jLabel74.setName("jLabel74"); // NOI18N

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.setName("jComboBox7"); // NOI18N

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox8.setName("jComboBox8"); // NOI18N

        jTextField6.setName("jTextField6"); // NOI18N

        jLabel75.setText("Priority");
        jLabel75.setName("jLabel75"); // NOI18N

        jSeparator10.setName("jSeparator10"); // NOI18N

        jLabel76.setText("Appointment");
        jLabel76.setName("jLabel76"); // NOI18N

        jLabel86.setText("Location");
        jLabel86.setName("jLabel86"); // NOI18N

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox9.setName("jComboBox9"); // NOI18N

        jLabel87.setText("Health Facility");
        jLabel87.setName("jLabel87"); // NOI18N

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox10.setName("jComboBox10"); // NOI18N

        btn_MonOP.setText("Other Providers");
        btn_MonOP.setName("btn_MonOP"); // NOI18N

        btn_MonPrint.setText("Print");
        btn_MonPrint.setName("btn_MonPrint"); // NOI18N

        btn_MonAcc.setText("Accept");
        btn_MonAcc.setName("btn_MonAcc"); // NOI18N

        btn_MonClr.setText("Clear");
        btn_MonClr.setName("btn_MonClr"); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69)
                                    .addComponent(jLabel72))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70)
                                    .addComponent(jScrollPane48, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71)))
                            .addComponent(jTextField5)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75)
                                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel74)
                                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel87)
                                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(87, 87, 87)
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel86)
                                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(73, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btn_MonOP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_MonPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_MonAcc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_MonClr)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane50, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane48, 0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane47, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel73)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(jLabel75)
                    .addComponent(jLabel74))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_MonOP)
                    .addComponent(btn_MonPrint)
                    .addComponent(btn_MonAcc)
                    .addComponent(btn_MonClr))
                .addGap(110, 110, 110))
        );

        jTabbedPane5.addTab("Monitoring", jPanel29);

        jPanel30.setName("jPanel30"); // NOI18N

        jLabel88.setText("Management - Follow Up");
        jLabel88.setName("jLabel88"); // NOI18N

        jLabel89.setText("Appointment Doctor");
        jLabel89.setName("jLabel89"); // NOI18N

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Doctor]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox11.setName("jComboBox11"); // NOI18N

        jLabel90.setText("Appointment Date");
        jLabel90.setName("jLabel90"); // NOI18N

        jLabel91.setText("Comment");
        jLabel91.setName("jLabel91"); // NOI18N

        jScrollPane51.setName("jScrollPane51"); // NOI18N

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setName("jTextArea7"); // NOI18N
        jScrollPane51.setViewportView(jTextArea7);

        btn_FollAcc.setText("Accept");
        btn_FollAcc.setName("btn_FollAcc"); // NOI18N

        btn_FollClr.setText("Clear");
        btn_FollClr.setName("btn_FollClr"); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel88))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel89)
                                    .addComponent(jLabel91))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(btn_FollAcc)
                        .addGap(18, 18, 18)
                        .addComponent(btn_FollClr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(279, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel91)
                    .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_FollAcc)
                    .addComponent(btn_FollClr))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Follow Up", jPanel30);

        jPanel31.setName("jPanel31"); // NOI18N

        jLabel92.setText("Management - Referral");
        jLabel92.setName("jLabel92"); // NOI18N

        jLabel93.setText("Healthcare Facility Name");
        jLabel93.setName("jLabel93"); // NOI18N

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Health Facility]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox12.setName("jComboBox12"); // NOI18N

        jLabel94.setText("If others, please specify");
        jLabel94.setName("jLabel94"); // NOI18N

        jTextField7.setName("jTextField7"); // NOI18N

        jLabel95.setText("Discipline");
        jLabel95.setName("jLabel95"); // NOI18N

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Discipline]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox13.setName("jComboBox13"); // NOI18N

        jLabel96.setText("If others, please specify");
        jLabel96.setName("jLabel96"); // NOI18N

        jTextField8.setName("jTextField8"); // NOI18N

        jLabel97.setText("Doctor reffered to");
        jLabel97.setName("jLabel97"); // NOI18N

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Doctor]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox14.setName("jComboBox14"); // NOI18N

        jLabel98.setText("If others, please specify");
        jLabel98.setName("jLabel98"); // NOI18N

        jTextField9.setName("jTextField9"); // NOI18N

        jLabel99.setText("Appointment Date");
        jLabel99.setName("jLabel99"); // NOI18N

        jLabel100.setText("Status");
        jLabel100.setName("jLabel100"); // NOI18N

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Status]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox15.setName("jComboBox15"); // NOI18N

        jLabel101.setText("Comment");
        jLabel101.setName("jLabel101"); // NOI18N

        jScrollPane52.setName("jScrollPane52"); // NOI18N

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jTextArea8.setName("jTextArea8"); // NOI18N
        jScrollPane52.setViewportView(jTextArea8);

        btn_ReffAcc.setText("Accept");
        btn_ReffAcc.setName("btn_ReffAcc"); // NOI18N

        btn_ReffClr.setText("Clear");
        btn_ReffClr.setName("btn_ReffClr"); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel92))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel94)
                                    .addComponent(jLabel95)
                                    .addComponent(jLabel96)
                                    .addComponent(jLabel97)
                                    .addComponent(jLabel98)
                                    .addComponent(jLabel99)
                                    .addComponent(jLabel100)
                                    .addComponent(jLabel101))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane52)
                                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9)
                                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8)
                                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(btn_ReffAcc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_ReffClr)
                                        .addGap(63, 63, 63)))))))
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel92)
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel101)
                    .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ReffAcc)
                    .addComponent(btn_ReffClr))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Refferal", jPanel31);

        jPanel32.setName("jPanel32"); // NOI18N

        jLabel102.setText("Medical Procedure Request");
        jLabel102.setName("jLabel102"); // NOI18N

        jLabel103.setText("Problem");
        jLabel103.setName("jLabel103"); // NOI18N

        jScrollPane60.setName("jScrollPane60"); // NOI18N

        jList6.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList6.setName("jList6"); // NOI18N
        jScrollPane60.setViewportView(jList6);

        jLabel104.setText("Request Cat");
        jLabel104.setName("jLabel104"); // NOI18N

        jScrollPane61.setName("jScrollPane61"); // NOI18N

        jList7.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList7.setName("jList7"); // NOI18N
        jScrollPane61.setViewportView(jList7);

        jLabel105.setText("Request Item");
        jLabel105.setName("jLabel105"); // NOI18N

        jScrollPane62.setName("jScrollPane62"); // NOI18N

        jList8.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList8.setName("jList8"); // NOI18N
        jScrollPane62.setViewportView(jList8);

        jLabel106.setText("Test to be perform");
        jLabel106.setName("jLabel106"); // NOI18N

        jTextField10.setName("jTextField10"); // NOI18N

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox16.setName("jComboBox16"); // NOI18N

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox17.setName("jComboBox17"); // NOI18N

        jLabel107.setText("Patient Condition");
        jLabel107.setName("jLabel107"); // NOI18N

        jLabel108.setText("Service Provider");
        jLabel108.setName("jLabel108"); // NOI18N

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox18.setName("jComboBox18"); // NOI18N

        jLabel109.setText("Location");
        jLabel109.setName("jLabel109"); // NOI18N

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox19.setName("jComboBox19"); // NOI18N

        jLabel110.setText("Health Facility");
        jLabel110.setName("jLabel110"); // NOI18N

        jLabel111.setText("Priority");
        jLabel111.setName("jLabel111"); // NOI18N

        jTextField11.setName("jTextField11"); // NOI18N

        jLabel112.setText("Appointment");
        jLabel112.setName("jLabel112"); // NOI18N

        jSeparator11.setName("jSeparator11"); // NOI18N

        btn_ProcOP.setText("Other Providers");
        btn_ProcOP.setName("btn_ProcOP"); // NOI18N

        btn_ProcPrint.setText("Print");
        btn_ProcPrint.setName("btn_ProcPrint"); // NOI18N

        btn_ProcAcc.setText("Accept");
        btn_ProcAcc.setName("btn_ProcAcc"); // NOI18N

        btn_ProcClr.setText("Clear");
        btn_ProcClr.setName("btn_ProcClr"); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel102))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane60)
                                    .addComponent(jLabel103)
                                    .addComponent(jLabel106))))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel104)
                            .addComponent(jScrollPane61, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel105)
                            .addComponent(jScrollPane62, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel112)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel111)
                                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel107)
                                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel110)
                                            .addGap(214, 214, 214)
                                            .addComponent(jLabel109))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(87, 87, 87)
                                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(149, 149, 149))
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel108))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btn_ProcOP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ProcPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ProcAcc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ProcClr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel102)
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104)
                    .addComponent(jLabel105))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane62, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane61, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane60, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel106)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(jLabel109))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(jLabel111)
                    .addComponent(jLabel107))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ProcOP)
                    .addComponent(btn_ProcPrint)
                    .addComponent(btn_ProcAcc)
                    .addComponent(btn_ProcClr))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Procedure", jPanel32);

        jPanel33.setName("jPanel33"); // NOI18N

        jLabel113.setText("Ward Admission Form");
        jLabel113.setName("jLabel113"); // NOI18N

        jLabel114.setText("Admit to discipline ");
        jLabel114.setName("jLabel114"); // NOI18N

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Discipline]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox20.setName("jComboBox20"); // NOI18N

        jLabel115.setText("Patient is reffered");
        jLabel115.setName("jLabel115"); // NOI18N

        jLabel116.setText("from:");
        jLabel116.setName("jLabel116"); // NOI18N

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select Reffered from]", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox21.setName("jComboBox21"); // NOI18N

        jLabel117.setText("Reason");
        jLabel117.setName("jLabel117"); // NOI18N

        jScrollPane63.setName("jScrollPane63"); // NOI18N

        jTextArea16.setColumns(20);
        jTextArea16.setRows(5);
        jTextArea16.setName("jTextArea16"); // NOI18N
        jScrollPane63.setViewportView(jTextArea16);

        jLabel118.setText("Admitted Before");
        jLabel118.setName("jLabel118"); // NOI18N

        jRadioButton7.setText("Yes");
        jRadioButton7.setName("jRadioButton7"); // NOI18N

        jRadioButton8.setText("No");
        jRadioButton8.setName("jRadioButton8"); // NOI18N

        jLabel119.setText("Patient have to be");
        jLabel119.setName("jLabel119"); // NOI18N

        jLabel120.setText("admitted before:");
        jLabel120.setName("jLabel120"); // NOI18N

        btn_ATWReff.setText("Refferal");
        btn_ATWReff.setName("btn_ATWReff"); // NOI18N

        btn_ATWPrev.setText("Preview");
        btn_ATWPrev.setName("btn_ATWPrev"); // NOI18N

        btn_ATWCls.setText("Close");
        btn_ATWCls.setName("btn_ATWCls"); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel113))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel114, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel116, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel117, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel118, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel119, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jRadioButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton8))
                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox21, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox20, 0, 168, Short.MAX_VALUE))
                            .addComponent(jScrollPane63, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(btn_ATWReff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ATWPrev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ATWCls, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel113)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114)
                            .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel116)
                            .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel115)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel117)
                    .addComponent(jScrollPane63, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8))
                .addGap(29, 29, 29)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ATWReff)
                    .addComponent(btn_ATWPrev)
                    .addComponent(btn_ATWCls))
                .addGap(29, 29, 29))
        );

        jTabbedPane5.addTab("Admit to Ward", jPanel33);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Plan", jPanel9);

        btn_LHSviewPrevVisit.setText("View Previous Visit");
        btn_LHSviewPrevVisit.setName("btn_LHSviewPrevVisit"); // NOI18N

        btn_LHSPrintCrntVisitSum.setText("Print Current Visit Summary");
        btn_LHSPrintCrntVisitSum.setName("btn_LHSPrintCrntVisitSum"); // NOI18N

        btn_LHSletters.setText("Letters");
        btn_LHSletters.setName("btn_LHSletters"); // NOI18N

        btn_LHSReports.setText("Reports");
        btn_LHSReports.setName("btn_LHSReports"); // NOI18N

        btn_LHSAppend.setText("Append");
        btn_LHSAppend.setName("btn_LHSAppend"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_LHSviewPrevVisit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_LHSPrintCrntVisitSum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_LHSletters)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_LHSReports)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_LHSAppend)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LHSviewPrevVisit)
                    .addComponent(btn_LHSPrintCrntVisitSum)
                    .addComponent(btn_LHSletters)
                    .addComponent(btn_LHSReports)
                    .addComponent(btn_LHSAppend))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setName("jPanel4"); // NOI18N

        jLabel16.setText("Option :");
        jLabel16.setName("jLabel16"); // NOI18N

        ActiveRadioButton.setText("Active");
        ActiveRadioButton.setName("ActiveRadioButton"); // NOI18N

        inActiveRadioButton.setText("InActive");
        inActiveRadioButton.setName("inActiveRadioButton"); // NOI18N

        AllRadioButton.setText("All");
        AllRadioButton.setName("AllRadioButton"); // NOI18N

        ProblemRadioButton.setText("Problem");
        ProblemRadioButton.setName("ProblemRadioButton"); // NOI18N

        ProblemComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ProblemComboBox.setName("ProblemComboBox"); // NOI18N

        WellnessRadioButton.setText("Wellness");
        WellnessRadioButton.setName("WellnessRadioButton"); // NOI18N

        WellnessComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        WellnessComboBox.setName("WellnessComboBox"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ActiveRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inActiveRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AllRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                .addComponent(ProblemRadioButton)
                .addGap(18, 18, 18)
                .addComponent(ProblemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(WellnessRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WellnessComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ActiveRadioButton)
                    .addComponent(inActiveRadioButton)
                    .addComponent(AllRadioButton)
                    .addComponent(ProblemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProblemRadioButton)
                    .addComponent(WellnessRadioButton)
                    .addComponent(WellnessComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
        private void txt_pNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pNameActionPerformed

    }//GEN-LAST:event_txt_pNameActionPerformed

        private void btnSrcComplaint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcComplaint1ActionPerformed

        // TODO add your handling code here:}//GEN-LAST:event_btnSrcComplaint1ActionPerformed
        }
        private void btnSrcSocialHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcSocialHistory1ActionPerformed

        // TODO add your handling code here:}//GEN-LAST:event_btnSrcSocialHistory1ActionPerformed
        }       
            
        private void btnSrcDrugs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcDrugs1ActionPerformed

        // TODO add your handling code here:}//GEN-LAST:event_btnSrcDrugs1ActionPerformed
        }
        private void txt_drugSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_drugSearchActionPerformed

        // TODO add your handling code here:}//GEN-LAST:event_txt_drugSearchActionPerformed
        }
        private void cbx_drugDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_drugDurationActionPerformed

        // TODO add your handling code here:}//GEN-LAST:event_cbx_drugDurationActionPerformed
        }
        private void btn_complaintAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_complaintAcceptActionPerformed
        // TODO add your handling code here:
            jTextArea1.setText("");
        String cmmt = (String) txt_complaintComment.getText();
        String searchcbx = txt_complaintSearch.getText();
        String severity = (String) cbx_cSeverity.getSelectedItem();
        String site = (String) cbx_site.getSelectedItem();
        String durationtxt = (String) txt_duration.getText();
        String durationcbx = (String) cbx_duration.getSelectedItem();
        String lateralitycbx = (String) cbx_laterality.getSelectedItem();
        
         String selectedRbtn = "";
        if (rbtn_cActive.isSelected()) {
            selectedRbtn = "Active";
        }
        if (rbtn_cInactive.isSelected()) {
            selectedRbtn = "In Active";
        }
        

        if (v == 0) {
            if (tab1 > 0 || tab1 == 0) {
                jTextArea1.append("\nC.Complaint\n");
                jTextArea12.append("\n" + q1s + ") " + "Problems: " + searchcbx + ", \n" + "Severity: " + severity + ", Site: " + site + ", Duration: " + durationtxt + " " + durationcbx + ",\nLaterality: " + lateralitycbx + ", Comments: " + cmmt + ";\n");
                tab1s = "\n" + q1s + ") " + "Problems: " + searchcbx + ", \n" + "Severity: " + severity + ", Site: " + site + ", Duration: " + durationtxt + " " + durationcbx + ",\nLaterality: " + lateralitycbx + ", Comments: " + cmmt + ";\n";
                tsttab1s[q1s] = tab1s;
                jTextArea1.append(jTextArea12.getText());

                //to retrieve update value
                compsub[q1s][0] = searchcbx;
                //compsub[q1s][1] =cmmtType;
                compsub[q1s][1] = severity;
                compsub[q1s][2] = site;
                compsub[q1s][3] = durationtxt;
                compsub[q1s][4] = durationcbx;
                compsub[q1s][5] = lateralitycbx;
                compsub[q1s][6] = cmmt;
                compsub[q1s][7] = selectedRbtn;
                //to retrieve update value
                q1s++;

            }
            tab1 = tab1 + 1;
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
        } else {
            String update = "Problems: " + searchcbx + ", \n" + "Severity: " + severity + ", Site: " + site + ", Duration: " + durationtxt + " " + durationcbx + ",\nLaterality: " + lateralitycbx + ", Comments: " + cmmt + ";\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q1s; m++) {
                if (m == group1) {
                    //to retrieve update value
                    compsub[group1][0] = searchcbx;
                    compsub[group1][1] = severity;
                    compsub[group1][2] = site;
                    compsub[group1][3] = durationtxt;
                    compsub[group1][4] = durationcbx;
                    compsub[group1][5] = lateralitycbx;
                    compsub[group1][6] = cmmt;
                    compsub[group1][7] = selectedRbtn;
                    //to retrieve update value
                    tsttab1s[group1] = "\n" + m + ") " + update;
                }
            }
            jTextArea12.setText("");
            for (int p1 = 0; p1 < q1s; p1++) {
                if ("".equals(tsttab1s[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea12.append(tsttab1s[p1]);

            }

            jTextArea1.append("\nC.Complaint\n" + jTextArea12.getText());
            tab1s = "\nImmunization\n" + jTextArea10.getText();
            v = v - 1;
        }
        
           //**************************Print Existing Value*****************************
       
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
    }//GEN-LAST:event_btn_complaintAcceptActionPerformed
    String disabilitysub[][] = new String[100][7];
    String tsttab6[] = new String[100];
    int q4 = 0;
    String hpisub[][] = new String[100][1];
    String tsttab7[] = new String[100];
    int q5 = 0;
    private void HPI_AcceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPI_AcceptBtnActionPerformed
        // TODO add your handling code here:
        
        jTextArea1.setText("");
        String hpi_details = HPI_DetailstxtArea.getText();
        
        


        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
       
        

        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab2 > 0 || tab2 == 0) {
                jTextArea14.append("\n" + q5 + ") " + "Details: " + hpi_details + "\n");
                tab2s = "\n" + q5 + ") " + "Details: " + hpi_details +  "\n";
                tsttab2[q5] = tab2s;
                jTextArea1.append("\nHPI\n" + jTextArea14.getText());

                //to retrieve update value
                  hpisub[q5][0] = hpi_details;

                //to retrieve update value
              q5++;
            }

            tab2 = tab2 + 1;
            tab2s = "\nHPI\n" + jTextArea14.getText();
        } else {

            String update = "Details: " + hpi_details + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q5; m++) {
                if (m == group1) {
                    //to update value
                    hpisub[group1][0] = hpi_details;
                    //to update value
                    tsttab2[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea14.setText("");
            for (int p1 = 0; p1 < q5; p1++) {
                if ("".equals(tsttab2[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea14.append(tsttab2[p1]);
            }

            jTextArea1.append("\nHPI\n" + jTextArea14.getText());
            tab2s = "\nHPI\n" + jTextArea14.getText();
            v = v - 1;
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }



//        txt_allergySearch.setText("");
//        txt_allergyDate2.setDate(null);
//        txt_allergyComments.setText("");
//
//        listModel = new javax.swing.DefaultListModel();
//        listModel.clear();
//        lbx_allergySearch.setModel(listModel);
                                                    
   
    }//GEN-LAST:event_HPI_AcceptBtnActionPerformed
    String PMHsub[][] = new String[100][4];
    //String tsttab3[] = new String[100];
    int q6 = 0;
    private void PMH_accBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMH_accBtnActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        String PMHcmmt = (String) txt_PMHComment.getText();
        String PMHsearchcbx = txt_PMHSearch.getText();
        String PMHdiagnosis = txt_PMHDiagnosis.getText();
        
         String selectedRbtn = "";
        if (rbtn_cActive.isSelected()) {
            selectedRbtn = "Active";
        }
        if (rbtn_cInactive.isSelected()) {
            selectedRbtn = "In Active";
        }
         //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
      
       
        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab3 > 0 || tab3 == 0) {
                jTextArea15.append("\n" + q6 + ") " + "Problem: " + PMHsearchcbx + ", \n" + "Diagnosis: " + PMHdiagnosis + "Status:" + selectedRbtn + "Details: " + PMHcmmt + "\n");
                tab3s = "\n" + q6 + ") " + "Problem: " + PMHsearchcbx + ", \n" + "Diagnosis: " + PMHdiagnosis + "Status:" + selectedRbtn + "Details: " + PMHcmmt +  "\n";
                tsttab3[q6] = tab3s;
                jTextArea1.append("\nPMH\n" + jTextArea15.getText());

//                //to retrieve update value
//                allergysub[q5][0] = allergy_name;
//                allergysub[q5][1] = allergy_comments;
//                allergysub[q5][2] = allergy_date;
//                //to retrieve update value
              q6++;
            }

            tab3 = tab3 + 1;
            tab3s = "\nPMH\n" + jTextArea15.getText();
        } else {

            String update = "Problem: " + PMHsearchcbx + ", \n" + "Diagnosis: " + PMHdiagnosis + "Status:" + selectedRbtn + "Details: " + PMHcmmt + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q6; m++) {
                if (m == group1) {
                    //to update value
                    PMHsub[group1][0] = PMHsearchcbx;
                    PMHsub[group1][1] = PMHdiagnosis;
                    PMHsub[group1][2] = selectedRbtn;
                    PMHsub[group1][3] = PMHcmmt;
                    
                    //to update value
                    tsttab3[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea15.setText("");
            for (int p1 = 0; p1 < q6; p1++) {
                if ("".equals(tsttab3[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea15.append(tsttab3[p1]);
            }

            jTextArea1.append("\nPMH\n" + jTextArea15.getText());
            tab2s = "\nPMH\n" + jTextArea15.getText();
            v = v - 1;
        }
         if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }


//        txt_allergySearch.setText("");
//        txt_allergyDate2.setDate(null);
//        txt_allergyComments.setText("");
//
//        listModel = new javax.swing.DefaultListModel();
//        listModel.clear();
//        lbx_allergySearch.setModel(listModel);
        
    }//GEN-LAST:event_PMH_accBtnActionPerformed

     String FHsub[][] = new String[100][4];
    //String tsttab4[] = new String[100];
    int q7 = 0;
private void FH_AccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH_AccBtnActionPerformed
 // TODO add your handling code here:
    jTextArea1.setText("");
    String SearchFH = txt_FHSearch.getText();
    String FHRelationship = (String) FH_Relationship.getSelectedItem();
    String Diagnosis = txt_FHDiagnosis.getText();
    String FHCmmnt = (String) FH_Comments.getText();
    
     //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        
        
    if (v == 0) {
            if (tab4 > 0 || tab4 == 0) {
                jTextArea1.append("\nFamily History\n");
                jTextArea16.append("\n" + q7 + ") " + "Problems: " + SearchFH + ", \n" + "Relationship: " + FHRelationship + ", Diagnosis: " + Diagnosis + ",\nComments: " + FHCmmnt + ";\n");
                tab1s = "\n" + q7 + ") " + "Problems: " + SearchFH + ", \n" + "Relationship: " + FHRelationship + ", Diagnosis: " + Diagnosis + ",\nComments: " + FHCmmnt + ";\n";
                tsttab1s[q1s] = tab1s;
                jTextArea1.append(jTextArea16.getText());

                //to retrieve update value
                compsub[q7][0] = SearchFH;
                compsub[q7][1] = FHRelationship;
                compsub[q7][2] = Diagnosis;
                compsub[q7][3] = FHCmmnt;
                
                //to retrieve update value
                q7++;

            }
            tab4 = tab4 + 1;
            tab4s = "\nFamily History\n" + jTextArea16.getText();
        } else {
            String update = "Problems: " + SearchFH + ", \n" + "Relationship: " + FHRelationship + ", Diagnosis: " + Diagnosis + ",\nComments: " + FHCmmnt + ";\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q7; m++) {
                if (m == group1) {
                    //to retrieve update value
                    compsub[group1][0] = SearchFH;
                    compsub[group1][1] = FHRelationship;
                    compsub[group1][2] = Diagnosis;
                    compsub[group1][3] = FHCmmnt;
                    
                    //to retrieve update value
                    tsttab4[group1] = "\n" + m + ") " + update;
                }
            }
            jTextArea16.setText("");
            for (int p1 = 0; p1 < q7; p1++) {
                if ("".equals(tsttab4[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea16.append(tsttab4[p1]);

            }

            jTextArea1.append("\nFamily History\n" + jTextArea16.getText());
            tab4s = "\nFamily History\n" + jTextArea16.getText();
            v = v - 1;
        }
        
           //**************************Print Existing Value*****************************
       
        
        if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
    
    
}//GEN-LAST:event_FH_AccBtnActionPerformed
    String socialsub[][] = new String[100][4];
    //String tsttab5[] = new String[100];
    int q8 = 0;
    private void btn_sclAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sclAcceptActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        String scl_prob = txt_socialProblem.getText();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String scl_date = formatter.format(txt_socialDate1.getDate());

        //String scl_date = txt_socialDate.getText();
        String scl_comments = txt_socialComment.getText();
        
         if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
        
        if (v == 0) {
            if (tab5 > 0 || tab5 == 0) {
                jTextArea17.append("\n" + q8 + ") " + "Problem: " + scl_prob + ",\nComment: " + scl_comments + ", Date : " + scl_date + "\n");
                tab5s = "\n" + q8 + ") " + "Problem: " + scl_prob + ",\nComment: " + scl_comments + ", Date : " + scl_date + "\n";
                tsttab5[q8] = tab5s;
                jTextArea1.append("\nSocial Problem\n" + jTextArea17.getText());

                //to retrieve update value
                socialsub[q8][0] = scl_prob;
                socialsub[q8][1] = scl_comments;
                socialsub[q8][2] = scl_date;
                //to retrieve update value
                q8++;
            }

            tab5 = tab5 + 1;
            tab5s = "\nSocial Problem\n" + jTextArea17.getText();
        } else {

            String update = "Problem: " + scl_prob + ",\nComment: " + scl_comments + "Date : " + scl_date + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q8; m++) {
                if (m == group1) {
                    //to update value
                    socialsub[group1][0] = scl_prob;
                    socialsub[group1][1] = scl_comments;
                    socialsub[group1][2] = scl_date;
                    //to update value
                    tsttab5[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea17.setText("");
            for (int p1 = 0; p1 < q8; p1++) {
                if (tsttab5[p1] == "") {
                    p1 = p1 + 1;
                }

                jTextArea17.append(tsttab5[p1]);
            }

            jTextArea1.append("\nSocial Problem\n" + jTextArea17.getText());
            tab5s = "\nSocial Problem\n" + jTextArea17.getText();
            v = v - 1;
        }
        if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
    }//GEN-LAST:event_btn_sclAcceptActionPerformed
    String bldsub[][] = new String[100][4];
    //String tsttab5[] = new String[100];
    int q9 = 0;
    private void BG_accBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG_accBtnActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        String BloodType = (String) comboBloodType.getSelectedItem();
        String selectedRhesus = "";
        if (rbtn_Bpositive.isSelected()) {
            selectedRhesus = "Positive";
        }
        if (rbtn_Bnegative.isSelected()) {
            selectedRhesus = "Negative";
        }
        
        String selectedG6PD = "";
        if (rbtn_Bnormal.isSelected()) {
            selectedG6PD = "Normal";
        }
        if (rbtn_Bdeficient.isSelected()) {
            selectedG6PD = "Deficient";
        }
        
        String BloodCmnt = (String) txt_BldCmmnt.getText();
        
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
        
        if (v == 0) {
            if (tab6 > 0 || tab6 == 0) {
                jTextArea18.append("\n" + q9 + ") " + "Blood Type: " + BloodType + ",\nRhesus: " + selectedRhesus + ", G6PD : " + selectedG6PD + ", Comment: " + BloodCmnt + "\n");
                tab6s = "\n" + q9 + ") " + "Blood Type: " + BloodType + ",\nRhesus: " + selectedRhesus + ", G6PD : " + selectedG6PD + ", Comment: " + BloodCmnt + "\n";
                tsttab6[q9] = tab6s;
                jTextArea1.append("\nBlood Group\n" + jTextArea18.getText());

                //to retrieve update value
                bldsub[q9][0] = BloodType;
                bldsub[q9][1] = selectedRhesus;
                bldsub[q9][2] = selectedG6PD;
                bldsub[q9][3] = BloodCmnt;
                
                //to retrieve update value
                q9++;
            }

            tab6 = tab6 + 1;
            tab6s = "\nBlood Group\n" + jTextArea18.getText();
        } else {

            String update = "Blood Type: " + BloodType + ",\nRhesus: " + selectedRhesus + ", G6PD : " + selectedG6PD + ", Comment: " + BloodCmnt + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q9; m++) {
                if (m == group1) {
                    //to update value
                    bldsub[group1][0] = BloodType;
                    bldsub[group1][1] = selectedRhesus;
                    bldsub[group1][2] = selectedG6PD;
                    bldsub[group1][3] = BloodCmnt;
                    //to update value
                    tsttab6[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea18.setText("");
            for (int p1 = 0; p1 < q9; p1++) {
                if (tsttab6[p1] == "") {
                    p1 = p1 + 1;
                }

                jTextArea18.append(tsttab6[p1]);
            }

            jTextArea1.append("\nBlood Group\n" + jTextArea18.getText());
            tab6s = "\nBlood Group\n" + jTextArea18.getText();
            v = v - 1;
        }
        
        if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
    }//GEN-LAST:event_BG_accBtnActionPerformed
    String allergysub[][] = new String[100][3];
    //String tsttab6[] = new String[100];
    int q10 = 0;
    private void btn_allergyAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allergyAcceptActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        String allergy_name = txt_allergySearch.getText();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String allergy_date = formatter.format(txt_allergyDate2.getDate());

        //String allergy_date = txt_allergyDate.getText();
        String allergy_comments = txt_allergyComments.getText();

        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }

        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab7 > 0 || tab7 == 0) {
                jTextArea19.append("\n" + q10 + ") " + "Type: " + allergy_name + ", Comment: " + allergy_comments + ",\nDate : " + allergy_date + "\n");
                tab7s = "\n" + q10 + ") " + "Type: " + allergy_name + ", Comment: " + allergy_comments + ",\nDate : " + allergy_date + "\n";
                tsttab7[q10] = tab7s;
                jTextArea1.append("\nAllergy\n" + jTextArea19.getText());

                //to retrieve update value
                allergysub[q10][0] = allergy_name;
                allergysub[q10][1] = allergy_comments;
                allergysub[q10][2] = allergy_date;
                //to retrieve update value
                q10++;
            }

            tab7 = tab7 + 1;
            tab7s = "\nAllergy\n" + jTextArea19.getText();
        } else {

            String update = "Type: " + allergy_name + ", Comment: " + allergy_comments + ",\nDate : " + allergy_date + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q10; m++) {
                if (m == group1) {
                    //to update value
                    allergysub[group1][0] = allergy_name;
                    allergysub[group1][1] = allergy_comments;
                    allergysub[group1][2] = allergy_date;
                    //to update value
                    tsttab7[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea19.setText("");
            for (int p1 = 0; p1 < q10; p1++) {
                if ("".equals(tsttab7[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea19.append(tsttab7[p1]);
            }

            jTextArea1.append("\nAllergy\n" + jTextArea19.getText());
            tab7s = "\nAllergy\n" + jTextArea19.getText();
            v = v - 1;
        }

        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }


        txt_allergySearch.setText("");
        txt_allergyDate2.setDate(null);
        txt_allergyComments.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_allergySearch.setModel(listModel);
    }//GEN-LAST:event_btn_allergyAcceptActionPerformed
    //String immsub[][] = new String[100][4];
    String tsttab8[] = new String[100];
    int q11 = 0;
    private void btn_immAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_immAcceptActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        String immunType = (String) txt_immSearch.getText();
        String cmmt = txt_immComment.getText();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String immDate = formatter.format(txt_immDate1.getDate());

        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }

        if (v == 0) {
            if (tab8 > 0 || tab8 == 0) {
                jTextArea20.append("\n" + q11 + ") " + "Type: " + immunType + ",\nComment: " + cmmt + ", Date : " + immDate + "\n");
                tab8s = "\n" + q11 + ") " + "Type: " + immunType + ",\nComment: " + cmmt + ", Date : " + immDate + "\n";
                tsttab8[q11] = tab8s;
                jTextArea1.append("\nImmunization\n" + jTextArea20.getText());

                //to retrieve update value
                immsub[q11][0] = immunType;
                immsub[q11][1] = cmmt;
                immsub[q11][2] = immDate;
                //to retrieve update value
                q11++;
            }

            tab8 = tab8 + 1;
            tab8s = "\nImmunization\n" + jTextArea20.getText();
        } else {

            String update = "Type: " + immunType + ",\nComment: " + cmmt + "Date : " + immDate + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q11; m++) {
                if (m == group1) {
                    //to update value
                    immsub[group1][0] = immunType;
                    immsub[group1][1] = cmmt;
                    immsub[group1][2] = immDate;
                    //to update value
                    tsttab8[group1] = "\n" + m + ") " + update;
                }
            }
            jTextArea20.setText("");
            for (int p1 = 0; p1 < q11; p1++) {
                if ("".equals(tsttab8[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea20.append(tsttab8[p1]);

            }

            jTextArea1.append("\nImmunisation\n" + jTextArea20.getText());
            tab8s = "\nImmunization\n" + jTextArea20.getText();
            v = v - 1;
        }
        
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
        //**************************Print Existing Value*****************************

        txt_immSearch.setText("");
        txt_immComment.setText("");
        txt_immDate1.setDate(null);

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_immSearch.setModel(listModel);
    }//GEN-LAST:event_btn_immAcceptActionPerformed

    //String disabilitysub[][] = new String[100][4];
    String tsttab9[] = new String[100];
    private void btn_dAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dAcceptActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        String disability = txt_disabilityType.getText();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String d_date1 = formatter.format(txt_dDate1.getDate());

        String d_comments = txt_dComments.getText();
        
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        
        if (v == 0) {
            if (tab9 > 0 || tab9 == 0) {
                jTextArea21.append("\n" + q4 + ") " + "Type: " + disability + ", Comment: " + d_comments + ",\nDate : " + d_date1 + "\n");
                tab6s = "\n" + q4 + ") " + "Type: " + disability + ", Comment: " + d_comments + ",\nDate : " + d_date1 + "\n";
                tsttab9[q4] = tab9s;
                jTextArea1.append("\nDisability\n" + jTextArea21.getText());

                //to retrieve update value
                disabilitysub[q4][0] = disability;
                disabilitysub[q4][1] = d_comments;
                disabilitysub[q4][2] = d_date1;
                //to retrieve update value
                q4++;
            }
            tab9 = tab9 + 1;
            tab9s = "\nDisability\n" + jTextArea21.getText();
        } else {

            String update = "Type: " + disability + ", Comment: " + d_comments + ",\nDate : " + d_date1 + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q4; m++) {
                if (m == group1) {
                    //to update value
                    disabilitysub[group1][0] = disability;
                    disabilitysub[group1][1] = d_comments;
                    //==--disabilitysub[group1][2] =d_date;//==--
                    disabilitysub[group1][2] = d_date1;
                    //to update value
                    tsttab9[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea21.setText("");
            for (int p1 = 0; p1 < q4; p1++) {
                if ("".equals(tsttab9[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea21.append(tsttab9[p1]);

            }

            jTextArea1.append("\nDisability\n" + jTextArea21.getText());
            tab9s = "\nDisability\n" + jTextArea21.getText();
            v = v - 1;
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
    }//GEN-LAST:event_btn_dAcceptActionPerformed

    private void btn_calculateBmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculateBmiActionPerformed
        // TODO add your handling code here:
        double height = new Integer(txt_height.getText().trim()).intValue();
        double weight = new Integer(txt_weight.getText().trim()).intValue();
        double total = Math.round((weight / (height * height)) * 10000);
        txt_bmi.setText(total + "");
        if (total <= 18) {
            txt_weightStatus.setText("Underweight");
        } else if (total <= 24) {
            txt_weightStatus.setText("Normal weight");
        } else if (total <= 29) {
            txt_weightStatus.setText("Overweight");
        } else {
            txt_weightStatus.setText("Obesity");
        }
    }//GEN-LAST:event_btn_calculateBmiActionPerformed
    //String vtssub[][] = new String[100][4];
    String tsttab10[] = new String[100];
    int q12 = 0;
    private void btn_vitalSignAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vitalSignAcceptActionPerformed
        // TODO add your handling code here
        jTextArea1.setText("");

        String hei = txt_height.getText();
        String wei = txt_weight.getText();
        String hea = txt_headCircumference.getText();
        String tem = txt_temperature.getText();
        String pul = txt_pulse.getText();
        String bmi = txt_bmi.getText();
        String sta = txt_weightStatus.getText();
        
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        
        if (v == 0) {
            if (tab10 > 0 || tab10 == 0) {
                jTextArea11.append("\n" + q12 + ") " + "Height: " + hei + " cm, Weight: " + wei + " kg, BMI: " + bmi + " kg/m2,\nStatus: " + sta + ", Head circumference: " + hea + " cm,\nTemp: " + tem + " celcius, Pulse: " + pul + "/min" + "\n");
                tab10s = "\n" + q12 + ") " + "Height: " + hei + " cm, Weight: " + wei + " kg, BMI: " + bmi + " kg/m2,\nStatus: " + sta + ", Head circumference: " + hea + " cm,\nTemp: " + tem + " celcius, Pulse: " + pul + "/min" + "\n";
                tsttab10[q12] = tab10s;
                jTextArea1.append("\nVital Sign\n" + jTextArea11.getText());

                //to retrieve update value
                vtssub[q12][0] = hei;
                vtssub[q12][1] = wei;
                vtssub[q12][2] = bmi;
                vtssub[q12][3] = sta;
                vtssub[q12][4] = hea;
                vtssub[q12][5] = tem;
                vtssub[q12][6] = pul;
                //to retrieve update value
                q12++;
            }
            tab10 = tab10 + 1;
            tab10s = "\nVital Sign\n" + jTextArea11.getText();
        } else {
            String update = "Height: " + hei + " cm, Weight: " + wei + " kg, BMI: " + bmi + " kg/m2,\nStatus: " + sta + ", Head circumference: " + hea + " cm,\nTemp: " + tem + " celcius, Pulse: " + pul + "/min" + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q12; m++) {
                if (m == group1) {
                    //to update value
                    vtssub[group1][0] = hei;
                    vtssub[group1][1] = wei;
                    vtssub[group1][2] = bmi;
                    vtssub[group1][3] = sta;
                    vtssub[group1][4] = hea;
                    vtssub[group1][5] = tem;
                    vtssub[group1][6] = pul;
                    //to update value
                    tsttab10[group1] = "\n" + m + ") " + update;
                }
            }
            jTextArea11.setText("");
            for (int p1 = 0; p1 < q12; p1++) {
                if ("".equals(tsttab10[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea11.append(tsttab10[p1]);

            }

            jTextArea1.append("\nVital Sign\n" + jTextArea11.getText());
            tab10s = "\nVital Sign\n" + jTextArea11.getText();
            v = v - 1;
        }
        
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
        
        txt_height.setText("");
        txt_weight.setText("");
        txt_headCircumference.setText("");
        txt_temperature.setText("");
        txt_pulse.setText("");
        txt_bmi.setText("");
        txt_weightStatus.setText("");
    }//GEN-LAST:event_btn_vitalSignAcceptActionPerformed
    //String vtssub[][] = new String[100][4];
    String tsttab11[] = new String[100];
    int q13 = 0;
    private void btn_diagnosisAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_diagnosisAcceptActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");

        String diagType = (String) txt_diagnosisSearch.getText();
        String sev = (String) cbx_dSeverity.getSelectedItem();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String diagnosis_date = formatter.format(txt_date1.getDate());
        
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        
        if (v == 0) {

            if (tab11 > 0 || tab11 == 0) {
                jTextArea1.append("\nDiagnosis\n");

                jTextArea9.append("\n" + q13 + ") " + "Type: " + diagType + ", Severity: " + sev + ", Date: " + diagnosis_date + "\n");
                tab11s = "\n" + q13 + ") " + "Type: " + diagType + ", Severity: " + sev + ", Date: " + diagnosis_date + "\n";
                tsttab11[q13] = tab11s;
                jTextArea1.append(jTextArea9.getText());

                //to retrieve update value
                diagsub[q13][0] = diagType;
                diagsub[q13][1] = sev;
                diagsub[q13][2] = diagnosis_date;
                //to retrieve update value
                q13++;
            }

            tab11 = tab11 + 1;
            tab11s = "\nDiagnosis\n" + jTextArea9.getText();
        } else {
            String update = "Type: " + diagType + ", Severity: " + sev + ", Date: " + diagnosis_date + "\n";

            System.out.println("Diagnosis");

            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q13; m++) {
                if (m == group1) {
                    //to update value
                    diagsub[group1][0] = diagType;
                    diagsub[group1][1] = sev;
                    diagsub[group1][2] = diagnosis_date;
                    //to update value
                    tsttab11[group1] = "\n" + m + ") " + update;
                }
            }
            jTextArea9.setText("");
            for (int p1 = 0; p1 < q13; p1++) {
                if ("".equals(tsttab11[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea9.append(tsttab11[p1]);

            }

            jTextArea1.append("\nDiagnosis\n" + jTextArea9.getText());
            tab11s = "\nDiagnosis\n" + jTextArea9.getText();
            v = v - 1;
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
        //**********************************************************
        
        txt_diagnosisSearch.setText("");
        cbx_dSeverity.setSelectedIndex(0);
        txt_date1.setDate(null);

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_diagnosisSearch.setModel(listModel);

    }//GEN-LAST:event_btn_diagnosisAcceptActionPerformed
    String pnsub[][] = new String[100][4];
    String tsttab12[] = new String[100];
    int q14 = 0;
    private void PN_accBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PN_accBtnActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        
        String pNotes = txt_pNotes.getText();
        
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        
        if (v == 0) {
            if (tab12 > 0 || tab12 == 0) {
                jTextArea2.append("\n" + q14 + ") " + "Notes: " + pNotes + "\n");
                tab12s = "\n" + q14 + ") " + "Notes: " + pNotes +  "\n";
                tsttab12[q14] = tab12s;
                jTextArea1.append("\nProgress Notes\n" + jTextArea2.getText());

                //to retrieve update value
                  pnsub[q14][0] = pNotes;

                //to retrieve update value
              q14++;
            }

            tab12 = tab12 + 1;
            tab12s = "\nProgress Notes\n" + jTextArea2.getText();
        } else {

            String update = "Notes: " + pNotes + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q14; m++) {
                if (m == group1) {
                    //to update value
                    pnsub[group1][0] = pNotes;
                    //to update value
                    tsttab12[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea2.setText("");
            for (int p1 = 0; p1 < q14; p1++) {
                if ("".equals(tsttab12[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea2.append(tsttab12[p1]);
            }

            jTextArea1.append("\nProgress Notes\n" + jTextArea2.getText());
            tab12s = "\nProgress Notes\n" + jTextArea2.getText();
            v = v - 1;
        }
        
         if (tab13 > 0) {
            jTextArea1.append(tab13s);
        }
        
        
    }//GEN-LAST:event_PN_accBtnActionPerformed

    //String drgsub[][] = new String[100][4];
    String tsttab13[] = new String[100];
    int q15 = 0;
    private void btn_drugAccept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_drugAccept1ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");

        String l = (String) txt_dose.getText();
        String m = (String) txt_dStrength.getText();
        String dr = txt_drugSearch.getText();
        String doseT = (String) cbx_drugDduration.getSelectedItem();
        String freq = (String) lbx_frequency.getSelectedValue();
        String inst = (String) lbx_instruction3.getSelectedValue();
        int freq1 = lbx_frequency.getSelectedIndex();
        int inst1 = lbx_instruction3.getSelectedIndex();
        int p_name1 = lbx_productNameUStockSearch.getSelectedIndex();
        int drug_search = lbx_drugSearch.getSelectedIndex();

        String p_name = (String) lbx_productNameUStockSearch.getSelectedValue();
        String drug_form = (String) txt_drugForm.getText();
        String duration = (String) cbx_drugDuration.getSelectedItem();
        
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea1.append(tab1s);
        }
        if (tab2 > 0) {
            tab2s = "\nHPI\n" + jTextArea14.getText();
            jTextArea1.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nPMH\n" + jTextArea15.getText();
            jTextArea1.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea1.append(tab4s);
        }
	if (tab5 > 0) {
            jTextArea1.append(tab5s);
        }
	if (tab6 > 0) {
            jTextArea1.append(tab6s);
        }
         if (tab7 > 0) {
            jTextArea1.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea1.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea1.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea1.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea1.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea1.append(tab12s);
        }
        
        if (v == 0) {
            if (tab13 > 0 || tab13 == 0) {
                jTextArea3.append("\n" + q15 + ") " + "Type: " + dr + ",\nProduct Name: " + p_name + ",\nDose: " + l + ", Quantity: " + m + ", Drug Form: " + drug_form + ",\nDuration: " + doseT + " " + duration + ", Frequency: " + freq + ",\nInstruction: " + inst + ";\n");
                tab13s = "\n" + q15 + ") " + "Type: " + dr + ",\nProduct Name: " + p_name + ",\nDose: " + l + ", Quantity: " + m + ", Drug Form: " + drug_form + ",\nDuration: " + doseT + " " + duration + ", Frequency: " + freq + ",\nInstruction: " + inst + ";\n";
                tsttab13[q15] = tab5s;
                jTextArea1.append("\nDrugs\n" + jTextArea3.getText());

                //to retrieve update value
                drgsub[q15][0] = dr;
                drgsub[q15][1] = l;
                drgsub[q15][2] = m;
                drgsub[q15][3] = doseT;
                drgsub[q15][4] = freq;
                drgsub[q15][5] = inst;
                drgsub[q15][6] = p_name;
                drgsub[q15][7] = drug_form;
                drgsub[q15][8] = duration;
                drgsub1[q15][0] = freq1;
                drgsub1[q15][1] = inst1;
                drgsub1[q15][2] = p_name1;
                drgsub1[q15][3] = drug_search;
                //to retrieve update value
                q15++;
            }
            tab13 = tab13 + 1;
            tab13s = "\nDrugs\n" + jTextArea3.getText();
        } else {
            String update = "Type: " + dr + ",\nProduct Name: " + p_name + ",\nDose: " + l + ", Quantity: " + m + ", Drug Form: " + drug_form + ",\nDuration: " + doseT + " " + duration + ", Frequency: " + freq + ",\nInstruction: " + inst + ";\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int z = 0; z < q15; z++) {
                if (z == group1) {
                    //to update value
                    drgsub[group1][0] = dr;
                    drgsub[group1][1] = l;
                    drgsub[group1][2] = m;
                    drgsub[group1][3] = doseT;
                    drgsub[group1][4] = freq;
                    drgsub[group1][5] = inst;
                    drgsub[group1][6] = p_name;
                    drgsub[group1][7] = drug_form;
                    drgsub[group1][8] = duration;
                    drgsub1[group1][0] = freq1;
                    drgsub1[group1][1] = inst1;
                    drgsub1[group1][2] = p_name1;
                    drgsub1[group1][3] = drug_search;

                    //to update value
                    tsttab13[group1] = "\n" + z + ") " + update;
                }
            }
            jTextArea3.setText("");
            for (int p1 = 0; p1 < q15; p1++) {
                if ("".equals(tsttab13[p1])) {
                    p1 = p1 + 1;
                }
                jTextArea3.append(tsttab13[p1]);
            }

            jTextArea1.append("\nDrugs\n" + jTextArea3.getText());
            tab13s = "\nDrugs\n" + jTextArea3.getText();
            v = v - 1;
        }
        
    }//GEN-LAST:event_btn_drugAccept1ActionPerformed

    private void LHS_QueueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LHS_QueueBtnActionPerformed
        // TODO add your handling code here:
        que.setState(que.NORMAL);
        que.toFront();
        que.show();
    }//GEN-LAST:event_LHS_QueueBtnActionPerformed

//       //Online Indicator
//    public static void showOnline(){
//        lblStatus.setText("Online");
//        btnStatus.setBackground(Color.green);
//    }
//    //Offline Indicator
//    public static void showOffline(){
//        lblStatus.setText("Offline");
//        btnStatus.setBackground(Color.red);
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LHSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LHSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LHSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LHSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LHSFrame().setVisible(true);
            }
        });
    }
     private boolean status = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ActiveRadioButton;
    private javax.swing.JRadioButton AllRadioButton;
    private javax.swing.JTextArea AllergyHeader;
    private javax.swing.JButton BG_accBtn;
    private javax.swing.JButton BG_clrBtn;
    private javax.swing.JButton FH_AccBtn;
    private javax.swing.JTextArea FH_Comments;
    private javax.swing.JComboBox FH_Relationship;
    private javax.swing.JButton FH_clrBtn;
    private javax.swing.JButton HPI_AcceptBtn;
    private javax.swing.JButton HPI_ClrBtn;
    private javax.swing.JTextArea HPI_DetailstxtArea;
    private javax.swing.JButton LHS_DischargeBtn;
    private javax.swing.JButton LHS_MissingBtn;
    private javax.swing.JButton LHS_OnHoldBtn;
    private javax.swing.JButton LHS_QueueBtn;
    private javax.swing.JButton LHS_SearchBtn;
    private javax.swing.JButton LHS_nextBtn;
    private javax.swing.JButton PMH_accBtn;
    private javax.swing.JButton PMH_clearBtn;
    private javax.swing.JButton PN_accBtn;
    private javax.swing.JButton PN_clrBtn;
    private javax.swing.JComboBox ProblemComboBox;
    private javax.swing.JRadioButton ProblemRadioButton;
    private javax.swing.JComboBox WellnessComboBox;
    private javax.swing.JRadioButton WellnessRadioButton;
    private javax.swing.JButton btnSrcAllergy;
    private javax.swing.JButton btnSrcComplaint;
    private javax.swing.JButton btnSrcComplaint1;
    private javax.swing.JButton btnSrcDiagnosis;
    private javax.swing.JButton btnSrcDisability;
    private javax.swing.JButton btnSrcDrugs1;
    private javax.swing.JButton btnSrcImmunisation;
    private javax.swing.JButton btnSrcSocialHistory;
    private javax.swing.JButton btnSrcSocialHistory1;
    private javax.swing.JButton btnStatus;
    private javax.swing.JButton btn_ATWCls;
    private javax.swing.JButton btn_ATWPrev;
    private javax.swing.JButton btn_ATWReff;
    private javax.swing.JButton btn_FollAcc;
    private javax.swing.JButton btn_FollClr;
    private javax.swing.JButton btn_LHSAppend;
    private javax.swing.JButton btn_LHSPrintCrntVisitSum;
    private javax.swing.JButton btn_LHSReports;
    private javax.swing.JButton btn_LHSletters;
    private javax.swing.JButton btn_LHSviewPrevVisit;
    private javax.swing.JButton btn_MonAcc;
    private javax.swing.JButton btn_MonClr;
    private javax.swing.JButton btn_MonOP;
    private javax.swing.JButton btn_MonPrint;
    private javax.swing.JButton btn_ProcAcc;
    private javax.swing.JButton btn_ProcClr;
    private javax.swing.JButton btn_ProcOP;
    private javax.swing.JButton btn_ProcPrint;
    private javax.swing.JButton btn_RadReqAcc;
    private javax.swing.JButton btn_RadReqClr;
    private javax.swing.JButton btn_RadReqOP;
    private javax.swing.JButton btn_RadReqPrint;
    private javax.swing.JButton btn_ReffAcc;
    private javax.swing.JButton btn_ReffClr;
    private javax.swing.JButton btn_allergyAccept;
    private javax.swing.JButton btn_allergyClear;
    private javax.swing.JButton btn_calculateBmi;
    private javax.swing.JButton btn_complaintAccept;
    private javax.swing.JButton btn_complaintClear;
    private javax.swing.JButton btn_dAccept;
    private javax.swing.JButton btn_dClear;
    private javax.swing.JButton btn_diagnosisAccept;
    private javax.swing.JButton btn_diagnosisClear;
    private javax.swing.JButton btn_drugAccept1;
    private javax.swing.JButton btn_drugClear1;
    private javax.swing.JButton btn_immAccept;
    private javax.swing.JButton btn_immClear;
    private javax.swing.JButton btn_sclAccept;
    private javax.swing.JButton btn_sclClear;
    private javax.swing.JButton btn_vitalSignAccept;
    private javax.swing.JButton btn_vitalSignClear;
    public javax.swing.JComboBox cbx_cSeverity;
    public javax.swing.JComboBox cbx_dSeverity;
    public javax.swing.JComboBox cbx_drugDduration;
    public javax.swing.JComboBox cbx_drugDuration;
    public javax.swing.JComboBox cbx_duration;
    public javax.swing.JComboBox cbx_laterality;
    public javax.swing.JComboBox cbx_site;
    private javax.swing.JComboBox comboBloodType;
    private javax.swing.JRadioButton inActiveRadioButton;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox17;
    private javax.swing.JComboBox jComboBox18;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox20;
    private javax.swing.JComboBox jComboBox21;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JList jList5;
    private javax.swing.JList jList6;
    private javax.swing.JList jList7;
    private javax.swing.JList jList8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane48;
    private javax.swing.JScrollPane jScrollPane49;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane50;
    private javax.swing.JScrollPane jScrollPane51;
    private javax.swing.JScrollPane jScrollPane52;
    private javax.swing.JScrollPane jScrollPane53;
    private javax.swing.JScrollPane jScrollPane54;
    private javax.swing.JScrollPane jScrollPane55;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane58;
    private javax.swing.JScrollPane jScrollPane59;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane60;
    private javax.swing.JScrollPane jScrollPane61;
    private javax.swing.JScrollPane jScrollPane62;
    private javax.swing.JScrollPane jScrollPane63;
    private javax.swing.JScrollPane jScrollPane64;
    private javax.swing.JScrollPane jScrollPane65;
    private javax.swing.JScrollPane jScrollPane66;
    private javax.swing.JScrollPane jScrollPane67;
    private javax.swing.JScrollPane jScrollPane68;
    private javax.swing.JScrollPane jScrollPane69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane70;
    private javax.swing.JScrollPane jScrollPane71;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JSlider jSlider5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea10;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTextArea jTextArea12;
    private javax.swing.JTextArea jTextArea13;
    private javax.swing.JTextArea jTextArea14;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextArea16;
    private javax.swing.JTextArea jTextArea17;
    private javax.swing.JTextArea jTextArea18;
    private javax.swing.JTextArea jTextArea19;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea20;
    private javax.swing.JTextArea jTextArea21;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lb_allergySearch;
    public javax.swing.JLabel lbl_alldate;
    private javax.swing.JLabel lbl_allergy;
    private javax.swing.JLabel lbl_allergyComments;
    private javax.swing.JLabel lbl_allergyDate;
    private javax.swing.JLabel lbl_cSeverity;
    private javax.swing.JLabel lbl_complaintComment;
    private javax.swing.JLabel lbl_complaintSearch;
    private javax.swing.JLabel lbl_complaintSearch1;
    private javax.swing.JLabel lbl_dComments;
    private javax.swing.JLabel lbl_dSeverity;
    private javax.swing.JLabel lbl_ddate;
    public javax.swing.JLabel lbl_diagdate;
    private javax.swing.JLabel lbl_diagnosisSearch;
    private javax.swing.JLabel lbl_disDate;
    private javax.swing.JLabel lbl_disability;
    private javax.swing.JLabel lbl_disabilityType;
    public javax.swing.JLabel lbl_disdate;
    public javax.swing.JLabel lbl_drgdate1;
    private javax.swing.JLabel lbl_duration;
    private javax.swing.JLabel lbl_immComment;
    private javax.swing.JLabel lbl_immDate;
    private javax.swing.JLabel lbl_immSearch;
    public javax.swing.JLabel lbl_immdate;
    private javax.swing.JLabel lbl_laterality;
    public javax.swing.JLabel lbl_shdate;
    private javax.swing.JLabel lbl_site;
    private javax.swing.JLabel lbl_socialHistory;
    public javax.swing.JLabel lbl_vsdate;
    private javax.swing.JList lbx_FHSearch;
    private javax.swing.JList lbx_PMHSearch;
    private javax.swing.JList lbx_allergySearch;
    private javax.swing.JList lbx_complaintSearch;
    private javax.swing.JList lbx_diagnosisSearch;
    private javax.swing.JList lbx_disabilityType;
    private javax.swing.JList lbx_drugSearch;
    private javax.swing.JList lbx_frequency;
    private javax.swing.JList lbx_immSearch;
    private javax.swing.JList lbx_instruction;
    private javax.swing.JList lbx_instruction2;
    private javax.swing.JList lbx_instruction3;
    private javax.swing.JList lbx_productNameUStockSearch;
    private javax.swing.JList lbx_socialProblem;
    private javax.swing.JRadioButton rbtn_Bdeficient;
    private javax.swing.JRadioButton rbtn_Bnegative;
    private javax.swing.JRadioButton rbtn_Bnormal;
    private javax.swing.JRadioButton rbtn_Bpositive;
    public javax.swing.JRadioButton rbtn_cActive;
    public javax.swing.JRadioButton rbtn_cActive1;
    public javax.swing.JRadioButton rbtn_cInactive;
    public javax.swing.JRadioButton rbtn_cInactive1;
    private javax.swing.JRadioButton rbtn_dFinal;
    private javax.swing.JRadioButton rbtn_dProvisional;
    public javax.swing.JTable tbl_HPI1;
    public javax.swing.JTable tbl_all;
    public javax.swing.JTable tbl_cc;
    public javax.swing.JTable tbl_diag;
    public javax.swing.JTable tbl_dis;
    public javax.swing.JTable tbl_drug1;
    public javax.swing.JTable tbl_imm;
    public javax.swing.JTable tbl_sh;
    public javax.swing.JTable tbl_vs;
    private javax.swing.JTextArea txt_BldCmmnt;
    private javax.swing.JTextField txt_FHDiagnosis;
    public javax.swing.JTextField txt_FHSearch;
    private javax.swing.JTextArea txt_PMHComment;
    private javax.swing.JTextField txt_PMHDiagnosis;
    public javax.swing.JTextField txt_PMHSearch;
    public javax.swing.JTextArea txt_allergyComments;
    public com.toedter.calendar.JDateChooser txt_allergyDate2;
    public javax.swing.JTextField txt_allergySearch;
    public javax.swing.JTextField txt_bmi;
    public javax.swing.JTextArea txt_complaintComment;
    public javax.swing.JTextField txt_complaintSearch;
    public javax.swing.JTextArea txt_dComments;
    public com.toedter.calendar.JDateChooser txt_dDate1;
    public javax.swing.JTextField txt_dStrength;
    public com.toedter.calendar.JDateChooser txt_date1;
    public javax.swing.JTextField txt_diagnosisSearch;
    public javax.swing.JTextField txt_disabilityType;
    public javax.swing.JTextField txt_dose;
    public javax.swing.JTextField txt_drugForm;
    public javax.swing.JTextField txt_drugSearch;
    public javax.swing.JTextField txt_duration;
    public javax.swing.JTextField txt_headCircumference;
    public javax.swing.JTextField txt_height;
    public javax.swing.JTextArea txt_immComment;
    public com.toedter.calendar.JDateChooser txt_immDate1;
    public javax.swing.JTextField txt_immSearch;
    private javax.swing.JTextField txt_pAge;
    public javax.swing.JTextField txt_pBloodSex;
    private javax.swing.JTextField txt_pID;
    public javax.swing.JTextField txt_pIcNo;
    public javax.swing.JTextField txt_pName;
    private javax.swing.JTextArea txt_pNotes;
    public javax.swing.JTextField txt_pRace;
    private javax.swing.JTextField txt_pSex;
    public javax.swing.JTextField txt_pulse;
    public javax.swing.JTextArea txt_socialComment;
    public com.toedter.calendar.JDateChooser txt_socialDate1;
    public javax.swing.JTextField txt_socialProblem;
    public javax.swing.JTextField txt_temperature;
    public javax.swing.JTextField txt_weight;
    public javax.swing.JTextField txt_weightStatus;
    // End of variables declaration//GEN-END:variables
}
