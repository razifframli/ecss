package javaapplication1;
//java db
//package com.javaworkspace.connecthsqldb;

//import api.CISMessageFormatter;
import Bean.ConnectCSS;
import Bean.PhysicalExamBean;
import BluethoothCompo.bluetoothGUI;
import DBConnection.DBConnection;
import GUI.AppointmentList;
import GUI.GCS_GUI;
import GUI.Login;
import GUI.MainPage;
import GUI.PGCS_GUI;
import GUI.PrescriptionNote;
import GUI.Procedures_GUI;
import GUI.QueueList;
import Helper.FileReadWrite;
import Helper.J;
import Helper.S;
import Helper.Session;
import Process.MainRetrieval;
import api.LookupController;
import api.Patient;
import api.Queue;
import config.Config;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.Date;

//java db
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import library.*;
import oms.rmi.server.Message;


class ImagePanel extends JPanel {
    private Image img;
    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }
    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

class MyRectangleJPanel extends JPanel {
    private int x = 0;
    private int y = 0;
    private int w = 0;
    private int h = 0;
    public MyRectangleJPanel(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 0, 0, 100));
        g.fillRect(this.x, this.y, this.w, this.h);
    }
}

/**
 *
 * @author user
 */
public class Consultation extends javax.swing.JFrame {
    //count tab access

    public static String EpisodeTime;
    public static String IDPMS = "";
    static int tab1 = 0;
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
    public String tempQuery;
    //java.sql.Connection conn = Session.getCon_x();
    public static Statement st;
    public static PreparedStatement ps;
    public ResultSet rs;
    public javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
    public String ch;
    public String name;
    FileWriter outFile;
    boolean ctSpeak = false, ctText = false;
    double start, end;
    private String id;
    
    public static int row_count = 0;
    public static int max_row = 1000;
    public static String note_array[] = new String[max_row];
    public static String problemCode = "";
    public static String problemDesc = "";
    
    private DefaultComboBoxModel UniCBmodel;
    
    private static String time1 = "";
    private static String time2 = "";
    
    public static ArrayList<ArrayList<String>> rs_ref = new ArrayList<ArrayList<String>>();
    
    public void setPhysicalExamPage() {
        DefaultMutableTreeNode rootPE = new DefaultMutableTreeNode("Physical Examination");
        ArrayList<PhysicalExamBean> pe0 = DBConnection.getPhysicalExamAll(0);
        ArrayList<PhysicalExamBean> pe1 = DBConnection.getPhysicalExamAll(1);
        ArrayList<PhysicalExamBean> pe2 = DBConnection.getPhysicalExamAll(2);
        ArrayList<PhysicalExamBean> pe3 = DBConnection.getPhysicalExamAll(3);
        ArrayList<PhysicalExamBean> pe4 = DBConnection.getPhysicalExamAll(4);
        ArrayList<PhysicalExamBean> pe5 = DBConnection.getPhysicalExamAll(5);
        ArrayList<PhysicalExamBean> pe6 = DBConnection.getPhysicalExamAll(6);
        ArrayList<PhysicalExamBean> pe7 = DBConnection.getPhysicalExamAll(7);
        ArrayList<PhysicalExamBean> pe8 = DBConnection.getPhysicalExamAll(8);
        DefaultMutableTreeNode level0[] = new DefaultMutableTreeNode[pe0.size()];
        
        for (int i = 0; i < pe0.size(); i++) {
            String code_0 = pe0.get(i).getPe_cd();
            String name_0 = pe0.get(i).getPe_name();
            level0[i] = new DefaultMutableTreeNode(name_0);
            for (int j = 0; j < pe1.size(); j++) {
                String code_1 = pe1.get(j).getPe_cd();
                String code_1_parent = pe1.get(j).getPe_parent();
                String name_1 = pe1.get(j).getPe_name();
                if (code_0.equals(code_1_parent)) {
                    DefaultMutableTreeNode level1 = new DefaultMutableTreeNode(name_1);
                    for (int k = 0; k < pe2.size(); k++) {
                        String code_2 = pe2.get(k).getPe_cd();
                        String code_2_parent = pe2.get(k).getPe_parent();
                        String name_2 = pe2.get(k).getPe_name();
                        if (code_1.equals(code_2_parent)) {
                            DefaultMutableTreeNode level2 = new DefaultMutableTreeNode(name_2);
                            for (int k3 = 0; k3 < pe3.size(); k3++) {
                                String code_3 = pe3.get(k3).getPe_cd();
                                String code_3_parent = pe3.get(k3).getPe_parent();
                                String name_3 = pe3.get(k3).getPe_name();
                                if (code_2.equals(code_3_parent)) {
                                    DefaultMutableTreeNode level3 = new DefaultMutableTreeNode(name_3);
                                    for (int k4 = 0; k4 < pe4.size(); k4++) {
                                        String code_4 = pe4.get(k4).getPe_cd();
                                        String code_4_parent = pe4.get(k4).getPe_parent();
                                        String name_4 = pe4.get(k4).getPe_name();
                                        if (code_3.equals(code_4_parent)) {
                                            DefaultMutableTreeNode level4 = new DefaultMutableTreeNode(name_4);
                                            for (int k5 = 0; k5 < pe5.size(); k5++) {
                                                String code_5 = pe5.get(k5).getPe_cd();
                                                String code_5_parent = pe5.get(k5).getPe_parent();
                                                String name_5 = pe5.get(k5).getPe_name();
                                                if (code_4.equals(code_5_parent)) {
                                                    DefaultMutableTreeNode level5 = new DefaultMutableTreeNode(name_5);
                                                    for (int k6 = 0; k6 < pe6.size(); k6++) {
                                                        String code_6 = pe6.get(k6).getPe_cd();
                                                        String code_6_parent = pe6.get(k6).getPe_parent();
                                                        String name_6 = pe6.get(k6).getPe_name();
                                                        if (code_5.equals(code_6_parent)) {
                                                            DefaultMutableTreeNode level6 = new DefaultMutableTreeNode(name_6);
                                                            for (int k7 = 0; k7 < pe7.size(); k7++) {
                                                                String code_7 = pe7.get(k7).getPe_cd();
                                                                String code_7_parent = pe7.get(k7).getPe_parent();
                                                                String name_7 = pe7.get(k7).getPe_name();
                                                                if (code_6.equals(code_7_parent)) {
                                                                    DefaultMutableTreeNode level7 = new DefaultMutableTreeNode(name_7);
                                                                    for (int k8 = 0; k8 < pe8.size(); k8++) {
                                                                        String code_8 = pe8.get(k8).getPe_cd();
                                                                        String code_8_parent = pe8.get(k8).getPe_parent();
                                                                        String name_8 = pe8.get(k8).getPe_name();
                                                                        if (code_7.equals(code_8_parent)) {
                                                                            DefaultMutableTreeNode level8 = new DefaultMutableTreeNode(name_8);
                                                                            level7.add(level8);
                                                                        }
                                                                    }
                                                                    level6.add(level7);
                                                                }
                                                            }
                                                            level5.add(level6);
                                                        }
                                                    }
                                                    level4.add(level5);
                                                }
                                            }
                                            level3.add(level4);
                                        }
                                    }
                                    level2.add(level3);
                                }
                            }
                            level1.add(level2);
                        }
                    }
                    level0[i].add(level1);
                }
            }
            rootPE.add(level0[i]);
        }
        tree_physical_exam.setModel(null);
        tree_physical_exam.setModel(new DefaultTreeModel(rootPE));
    }
    
    public void setProcedurePage() {
        DefaultMutableTreeNode rootProcedure = new DefaultMutableTreeNode("Procedure");
        ArrayList<ArrayList<String>> data1 = DBConnection.getProcedure(1);
        ArrayList<ArrayList<String>> data2 = DBConnection.getProcedure(2);
        ArrayList<ArrayList<String>> data3 = DBConnection.getProcedure(3);
        DefaultMutableTreeNode level1[] = new DefaultMutableTreeNode[data1.size()];
        for (int i = 0; i < data1.size(); i++) {
            String code = data1.get(i).get(0);
            String name = data1.get(i).get(1);
            level1[i] = new DefaultMutableTreeNode(name);
            for (int j = 0; j < data2.size(); j++) {
                String code_1 = data2.get(j).get(0);
                String code_1_parent = data2.get(j).get(2);
                String name_1 = data2.get(j).get(1);
                if (code.equals(code_1_parent)) {
                    DefaultMutableTreeNode level2 = new DefaultMutableTreeNode(name_1);
                    for (int k = 0; k < data3.size(); k++) {
                        String code_2 = data3.get(k).get(0);
                        String code_2_parent = data3.get(k).get(2);
                        String name_2 = data3.get(k).get(1);
                        if (code_1.equals(code_2_parent)) {
                            DefaultMutableTreeNode level3 = new DefaultMutableTreeNode(name_2);
                            level2.add(level3);
                        }
                    }
                    level1[i].add(level2);
                }
            }
            rootProcedure.add(level1[i]);
        }
        tree_procedure.setModel(null);
        tree_procedure.setModel(new DefaultTreeModel(rootProcedure));
    }
    
    public void setNewPatientAlert(boolean stat) {
        if (stat) {
            lbl_new_patient.setText("* New Patient *");
        } else {
            lbl_new_patient.setText("");
        }
    }
    
    public void getLookupTable() {
        try {
            LookupController CBoxloader = new LookupController();
            
            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0007", "Family Relationship", true);
            FH_Relationship.setModel(UniCBmodel);
            
            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getDataCmbx("Doctor", false);
            cbx_referral_doctor.setModel(UniCBmodel);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static final String IMG_PATH = "assets/human-anatomy.png";
    private static int saiz_kotak = 10;
    private static JPanel kotak[] = new JPanel[saiz_kotak];
    private static ImagePanel panel;
    private static boolean isAnatomy[] = new boolean[saiz_kotak];

    public static void addImage() {
        try {
            panel = new ImagePanel(new ImageIcon(IMG_PATH).getImage());
            jPanel69.add(panel);
            jPanel69.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setKotak() {
        for(int i = 0; i < saiz_kotak; i++) {
            kotak[i] = new MyRectangleJPanel(0, 0, 0, 0);
            kotak[i].setSize(600, 400);
            kotak[i].setBackground(new Color(100, 100, 100, 0));
            jPanel69.add(kotak[i]);
            isAnatomy[i] = false;
        }
    }
    
    public static int sizeLR = 7;
    public static JCheckBox cbxPK[] = new JCheckBox[sizeLR];
    public static JCheckBox cbxKL[] = new JCheckBox[sizeLR];
    public static JCheckBox cbxHM[] = new JCheckBox[sizeLR];
    public static int sizeLRPK = 0;
    public static int sizeLRKL = 0;
    public static int sizeLRHM = 0;

    
    
    public void setTutup() {
        jTabbedPane5.setEnabledAt(2, false);
        jTabbedPane5.setEnabledAt(3, false);
        //jTabbedPane5.setEnabledAt(4, false);
        jTabbedPane6.setEnabledAt(2, false);
        jTabbedPane7.setEnabledAt(0, false);
        jTabbedPane7.setEnabledAt(1, false);
        jTabbedPane7.setEnabledAt(3, false);
        jTabbedPane7.setEnabledAt(4, false);
        jTabbedPane7.setEnabledAt(7, false);
//        lbl_previous_visit_date.setVisible(false);
//        Consultation.tbl_vts.setVisible(false);
    }
    
    private int max_ref_tbl = 50;
    private int col_ref_tbl = 5;

    private void clearTblRef() {
        for (int j = 0; j < max_ref_tbl; j++) {
            for (int k = 0; k < col_ref_tbl; k++) {
//                tbl_referral_list.getModel().setValueAt("", j, k);
            }
        }
    }

    private void getReferral() {
        clearTblRef();
//        if (Session.getPrev_stat()) {
            try {
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//                Message impl = (Message) myRegistry.lookup("myMessage");
//                ArrayList<ArrayList<String>> getRef = DBConnection.getImpl().getReferral(Session.getUser_id());
//                if (getRef.size() > 0) {
//                    for (int j = 0; j < getRef.size() && j < max_ref_tbl; j++) {
//                        tbl_referral_list.getModel().setValueAt(getRef.get(j).get(1), j, 0);
//                        tbl_referral_list.getModel().setValueAt(getRef.get(j).get(0), j, 1);
//                        tbl_referral_list.getModel().setValueAt(getRef.get(j).get(15), j, 2);
//                        tbl_referral_list.getModel().setValueAt(getRef.get(j).get(13), j, 3);
//                        tbl_referral_list.getModel().setValueAt(getRef.get(j).get(11), j, 4);
//                    }
//                }
//                Consultation.showOnline();
            } catch (Exception e) {
//                Consultation.showOffline();
                //J.o("Network Offline", "Network to Server is Offline!!", 0);
                //e.printStackTrace();
            }
//        } else {
//            Consultation.showOffline();
//            J.o("Network Offline", "Network to Server is Offline!!", 0);
//        }
    }
    
    public void setPanel() {
        Laboratory_Request lab_req = new Laboratory_Request(this);
        jScrollPane23.add(lab_req.pnl_lab_req_1);
        jScrollPane23.revalidate();
    }
    
    
    public void loadDrug() {
        try {
            int num_cols = 25;
            String sql = "SELECT * "
                    + "FROM PIS_MDC2 ";
            String params[] = {};
            ArrayList<ArrayList<String>> data1 = DBConnection.getImpl().getQuery(sql, num_cols, params);

            String sql_delete = "DELETE FROM PIS_MDC2 ";
            PreparedStatement ps_delete = Session.getCon_x(1000).prepareStatement(sql_delete);
            ps_delete.execute();

            for (int j = 0; j < data1.size(); j++) {
                //System.out.println("DRUG "+j+": "+data1.get(i)+"\n");
                String UD_MDC_CODE = data1.get(j).get(0);
                int num_cols1 = data1.get(j).size();
//                String sql1 = "SELECT * "
//                        + "FROM PIS_MDC2 "
//                        + "WHERE UD_MDC_CODE = ? ";
//                PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
//                ps1.setString(1, UD_MDC_CODE);
//                ResultSet rs1 = ps1.executeQuery();
//                if (!rs1.next()) {
                S.oln("Drug code " + UD_MDC_CODE + " not in the local list.. Adding it..");
                String params1 = "";
                for (int k = 0; k < num_cols1 - 1; k++) {
                    params1 += "'" + Func.trim(data1.get(j).get(k)) + "',";
                }
                params1 += "'" + Func.trim(data1.get(j).get(num_cols1 - 1)) + "'";
                String sql2 = "INSERT INTO PIS_MDC2 VALUES(" + params1 + ")";
                System.out.println("\n\nsql2:\n"+sql2+"\n\n");
                PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
                ps2.execute();
//                }
            }
            S.oln("Done sync drug.. Alhamdulillah..");
        } catch (Exception e) {
            S.oln("Cannot sync drug! Network down!!");
            e.printStackTrace();
        }
    }
    
    private static void setDropDownTime() {
        time_to_hour.removeAllItems();
        time_to_minute.removeAllItems();
        time_to_second.removeAllItems();
        for (int i = 0; i < 24; i++) {
            String time = (i < 10) ? ("0"+i) : (""+i);
            time_to_hour.addItem(time);
        }
        for (int i = 0; i < 60; i++) {
            String time = (i < 10) ? ("0"+i) : (""+i);
            time_to_minute.addItem(time);
            time_to_second.addItem(time);
        }
        
        String time_now[] = Func.getTimeNow().split(":");
        Func.cmbSelectInput(time_to_hour, time_now[0]);
        Func.cmbSelectInput(time_to_minute, time_now[1]);
        Func.cmbSelectInput(time_to_second, time_now[2]);
    }

    /** Creates new form Consultation */
    public Consultation() {
        //LongRunProcess.check_network2();
        initComponents();
        
        setDropDownTime();
        
        loadDrug();
        
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        setKotak();
        addImage();
        
        
        setTutup();
        
        getLookupTable();
        
        getReferral();
        
        for(int jkl = 0; jkl < max_row; jkl++) {
            note_array[jkl] = "";
        }
        
        //main menu enabled
        btn_exit.setEnabled(true);
        
        //new NoteConsultation().setVisible(true);
        jTextArea7.setVisible(false);
        
        /*
         * Temporary disable the EHR button.
         */
        //btnView.setVisible(false);
//        jScrollPane15.setVisible(false);
        
        /*
         * Checking Network
         */
//        final LongRunProcess lrp = new LongRunProcess();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                lrp.execute();
//            }
//        });
        
        // ==================== database connection ============================
        try {
            st = Session.getCon_x(100).createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x();
        }
        
        //blood type
        //txtBloodType.setText(txt_pBloodSex.getText());
        Func.cmbSelectInput(cmbBloodType, txt_pBloodSex.getText());

        //Create Date
        java.sql.Date Adesso = new java.sql.Date(new java.util.Date().getTime());
        lbl_date.setText("Date: " + String.valueOf(Adesso));
        //Create Time
        java.sql.Time Adesso2 = new java.sql.Time(new java.util.Date().getTime());
        lbl_time.setText("Time: " + Adesso2);

        //***************************to Center jFrame1 in the screen*************************
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        //jFrame1.setSize(width/2, height/2);
        jFrame3.setSize(450, 135);
        jFrame1.setSize(999, 500);
        jFrame2.setSize(655, 300);


        // here's the part where i center the jframe on screen
        jFrame3.setLocationRelativeTo(null);
        jFrame1.setLocationRelativeTo(null);
        jFrame2.setLocationRelativeTo(null);

        //***********************************************************************************
        Date date = new Date();
        tfieldDate.setDate(date);

        //********************************************************************************
        //get popup menu at combo box according to Character type at textbox
        //*
        //**************************************************************************************************************

        
        
        
        //check network
//        System.out.println("pingggggggggg");
//        LongRunProcess.check_network2();
//        System.out.println("result prev: "+Session.getPrev_stat());
//        System.out.println("result curr: "+Session.getCurr_stat());
        //online
//        if (Session.getPrev_stat()) {
//            showOnline();
//        } else { //offline
//            showOffline();
//        }
//        //close network
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

        setProcedurePage();
        setPhysicalExamPage();
        
        setPanel();
        
        CheckNewPatient.active = true;
        CheckNewPatient cnp = new CheckNewPatient(this);
        Thread tr = new Thread(cnp);
        tr.start();
        
        buttonGroup3.add(rbtn_gen_search);
        buttonGroup3.add(rbtn_per_search);
        buttonGroup5.add(rbtn_gen_search_dgs);
        buttonGroup5.add(rbtn_per_search_dgs);
        btn_plus.setEnabled(true);
        btn_substract.setEnabled(false);
        btn_plus_dgs.setEnabled(true);
        btn_substract_dgs.setEnabled(false);
        Searching.searchStatus = 1;
        Searching.searchStatus_dgs = 1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jFrame3 = new javax.swing.JFrame();
        jComboBox10 = new javax.swing.JComboBox();
        btn_dUpdate = new javax.swing.JButton();
        btn_dDelete = new javax.swing.JButton();
        jComboBox11 = new javax.swing.JComboBox();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane39 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel13 = new javax.swing.JLabel();
        tfieldDate = new com.toedter.calendar.JDateChooser();
        jScrollPane43 = new javax.swing.JScrollPane();
        tblQueue1 = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        lbl_pIcNo1 = new javax.swing.JLabel();
        txt_pIcNo1 = new javax.swing.JTextField();
        lbl_pName1 = new javax.swing.JLabel();
        txt_pName1 = new javax.swing.JTextField();
        btn_pHistoryOk2 = new javax.swing.JButton();
        jFrame2 = new javax.swing.JFrame();
        jScrollPane44 = new javax.swing.JScrollPane();
        tblQueue = new javax.swing.JTable();
        btn_pHistoryOk1 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        rbtn_grp_mcts = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        rbtn_grp_diagnosis = new javax.swing.ButtonGroup();
        txt_allergyDate3 = new com.toedter.calendar.JDateChooser();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_sPatient = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jLabel54 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tbl_cc = new javax.swing.JTable();
        jPanel47 = new javax.swing.JPanel();
        lbl_complaintSearch = new javax.swing.JLabel();
        txt_complaintSearch = new javax.swing.JTextField();
        btnSrcComplaint = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lbx_complaintSearch = new javax.swing.JList();
        cbx_cSeverity = new javax.swing.JComboBox();
        lbl_cSeverity = new javax.swing.JLabel();
        lbl_duration = new javax.swing.JLabel();
        txt_duration = new javax.swing.JTextField();
        cbx_duration = new javax.swing.JComboBox();
        lbl_laterality = new javax.swing.JLabel();
        lbl_site = new javax.swing.JLabel();
        cbx_site = new javax.swing.JComboBox();
        cbx_laterality = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_complaintComment = new javax.swing.JTextArea();
        lbl_complaintComment = new javax.swing.JLabel();
        btn_complaintAccept = new javax.swing.JButton();
        btn_complaintClear = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea12 = new javax.swing.JTextArea();
        lbl_ccdate = new javax.swing.JLabel();
        rbtn_gen_search = new javax.swing.JRadioButton();
        rbtn_per_search = new javax.swing.JRadioButton();
        btn_substract = new javax.swing.JButton();
        btn_plus = new javax.swing.JButton();
        jScrollPane76 = new javax.swing.JScrollPane();
        jPanel19 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jScrollPane46 = new javax.swing.JScrollPane();
        tbl_HPI1 = new javax.swing.JTable();
        jPanel49 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane45 = new javax.swing.JScrollPane();
        HPI_DetailstxtArea = new javax.swing.JTextArea();
        btn_HPIAccpt = new javax.swing.JButton();
        HPI_ClrBtn = new javax.swing.JButton();
        jScrollPane67 = new javax.swing.JScrollPane();
        jTextArea18 = new javax.swing.JTextArea();
        jScrollPane77 = new javax.swing.JScrollPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbl_pmh = new javax.swing.JTable();
        jScrollPane68 = new javax.swing.JScrollPane();
        jTextArea19 = new javax.swing.JTextArea();
        jPanel51 = new javax.swing.JPanel();
        lbl_complaintSearch1 = new javax.swing.JLabel();
        txt_PMHSearch = new javax.swing.JTextField();
        btnSrcComplaint1 = new javax.swing.JButton();
        jScrollPane47 = new javax.swing.JScrollPane();
        lbx_PMHSearch = new javax.swing.JList();
        rbtn_cActive1 = new javax.swing.JRadioButton();
        rbtn_cInactive1 = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane48 = new javax.swing.JScrollPane();
        txt_PMHComment = new javax.swing.JTextArea();
        PMH_AccptrBtn = new javax.swing.JButton();
        PMH_clearBtn = new javax.swing.JButton();
        jScrollPane78 = new javax.swing.JScrollPane();
        jPanel21 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jScrollPane79 = new javax.swing.JScrollPane();
        tbl_fmh = new javax.swing.JTable();
        jPanel53 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txt_FHSearch = new javax.swing.JTextField();
        btnSrcSocialHistory1 = new javax.swing.JButton();
        jScrollPane49 = new javax.swing.JScrollPane();
        lbx_FHSearch = new javax.swing.JList();
        jLabel52 = new javax.swing.JLabel();
        FH_Relationship = new javax.swing.JComboBox();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane50 = new javax.swing.JScrollPane();
        FH_Comments = new javax.swing.JTextArea();
        FH_accptBtn = new javax.swing.JButton();
        FH_clrBtn = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane38 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_socialProblem = new javax.swing.JTextField();
        btnSrcSocialHistory = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        lbx_socialProblem = new javax.swing.JList();
        txt_socialDate1 = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        txt_socialComment = new javax.swing.JTextArea();
        btn_sclAccept = new javax.swing.JButton();
        btn_sclClear = new javax.swing.JButton();
        lbl_shdate = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane42 = new javax.swing.JScrollPane();
        tbl_sh = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        rbtn_Bpositive = new javax.swing.JRadioButton();
        rbtn_Bnegative = new javax.swing.JRadioButton();
        rbtn_Bnormal = new javax.swing.JRadioButton();
        rbtn_Bdeficient = new javax.swing.JRadioButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane51 = new javax.swing.JScrollPane();
        txt_BldCmmnt = new javax.swing.JTextArea();
        BG_accptBtn = new javax.swing.JButton();
        BG_clrBtn = new javax.swing.JButton();
        jScrollPane70 = new javax.swing.JScrollPane();
        jTextArea21 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        cmbBloodType = new javax.swing.JComboBox();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane37 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        lb_allergySearch = new javax.swing.JLabel();
        txt_allergySearch = new javax.swing.JTextField();
        btnSrcAllergy = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        lbx_allergySearch = new javax.swing.JList();
        txt_allergyDate2 = new com.toedter.calendar.JDateChooser();
        lbl_allergyDate = new javax.swing.JLabel();
        lbl_allergyComments = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txt_allergyComments = new javax.swing.JTextArea();
        btn_allergyAccept = new javax.swing.JButton();
        btn_allergyClear = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        lbl_immdate = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jScrollPane34 = new javax.swing.JScrollPane();
        tbl_imu = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea10 = new javax.swing.JTextArea();
        jPanel60 = new javax.swing.JPanel();
        lbl_immSearch = new javax.swing.JLabel();
        txt_immSearch = new javax.swing.JTextField();
        btnSrcImmunisation = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        lbx_immSearch = new javax.swing.JList();
        lbl_immComment = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txt_immComment = new javax.swing.JTextArea();
        lbl_immDate = new javax.swing.JLabel();
        txt_immDate1 = new com.toedter.calendar.JDateChooser();
        btn_immAccept = new javax.swing.JButton();
        btn_immClear = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane36 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        lbl_disdate = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        lbl_disabilityType = new javax.swing.JLabel();
        txt_disabilityType = new javax.swing.JTextField();
        btnSrcDisability = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        lbx_disabilityType = new javax.swing.JList();
        lbl_disDate = new javax.swing.JLabel();
        lbl_dComments = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        txt_dComments = new javax.swing.JTextArea();
        btn_dAccept = new javax.swing.JButton();
        btn_dClear = new javax.swing.JButton();
        txt_dDate1 = new com.toedter.calendar.JDateChooser();
        jPanel62 = new javax.swing.JPanel();
        jScrollPane41 = new javax.swing.JScrollPane();
        tbl_dab = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        btn_vitalSignClear = new javax.swing.JButton();
        btn_vitalSignAccept = new javax.swing.JButton();
        jPanel77 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txt_gcs_result = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txt_gcs_points = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jScrollPane55 = new javax.swing.JScrollPane();
        tbl_gcs = new javax.swing.JTable();
        jPanel78 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        txt_pgcs_result = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txt_pgcs_points = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jScrollPane56 = new javax.swing.JScrollPane();
        tbl_pgcs = new javax.swing.JTable();
        jPanel79 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_systolic2 = new javax.swing.JTextField();
        txt_systolic1 = new javax.swing.JTextField();
        txt_systolic = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txt_diastolic = new javax.swing.JTextField();
        txt_diastolic1 = new javax.swing.JTextField();
        txt_diastolic2 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        txt_pulse = new javax.swing.JTextField();
        txt_pulse1 = new javax.swing.JTextField();
        txt_pulse2 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanel80 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        txt_respiratory_rate = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jPanel81 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        txt_oxygen_saturation = new javax.swing.JTextField();
        jPanel82 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txt_temperature = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jSlider4 = new javax.swing.JSlider();
        jPanel83 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_pain_result = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        cbx_pain_scale = new javax.swing.JComboBox();
        jPanel84 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txt_height = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel32 = new javax.swing.JLabel();
        txt_weight = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jLabel33 = new javax.swing.JLabel();
        txt_bmi = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        btn_calculateBmi = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        txt_weightStatus = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_headCircumference = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jSlider3 = new javax.swing.JSlider();
        jLabel63 = new javax.swing.JLabel();
        txt_blood_glucose = new javax.swing.JTextField();
        lbl_vsdate = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane82 = new javax.swing.JScrollPane();
        jPanel69 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_peparu_kanan = new javax.swing.JTextField();
        txt_jantung = new javax.swing.JTextField();
        txt_tekak = new javax.swing.JTextField();
        txt_bahu_kanan = new javax.swing.JTextField();
        txt_bahu_kiri = new javax.swing.JTextField();
        txt_perut = new javax.swing.JTextField();
        txt_kepala = new javax.swing.JTextField();
        txt_hidung = new javax.swing.JTextField();
        txt_mulut = new javax.swing.JTextField();
        txt_telinga_kanan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane57 = new javax.swing.JScrollPane();
        tree_physical_exam = new javax.swing.JTree();
        jButton11 = new javax.swing.JButton();
        jScrollPane59 = new javax.swing.JScrollPane();
        txt_pe_comments = new javax.swing.JTextArea();
        jLabel64 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane35 = new javax.swing.JScrollPane();
        jPanel68 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane58 = new javax.swing.JScrollPane();
        jPanel71 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tbl_dgs = new javax.swing.JTable();
        jPanel64 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lbl_ddate = new javax.swing.JLabel();
        txt_date1 = new com.toedter.calendar.JDateChooser();
        lbl_diagnosisSearch = new javax.swing.JLabel();
        txt_diagnosisSearch = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        lbx_diagnosisSearch = new javax.swing.JList();
        lbl_dSeverity = new javax.swing.JLabel();
        cbx_dSeverity = new javax.swing.JComboBox();
        lbl_site1 = new javax.swing.JLabel();
        dbx_site = new javax.swing.JComboBox();
        lbl_laterality1 = new javax.swing.JLabel();
        dbx_laterality = new javax.swing.JComboBox();
        lbl_complaintComment1 = new javax.swing.JLabel();
        jScrollPane75 = new javax.swing.JScrollPane();
        txt_diagComment = new javax.swing.JTextArea();
        btn_diagnosisAccept = new javax.swing.JButton();
        btn_diagnosisClear = new javax.swing.JButton();
        btnSrcDiagnosis = new javax.swing.JButton();
        lbl_diagdate = new javax.swing.JLabel();
        rbtn_gen_search_dgs = new javax.swing.JRadioButton();
        rbtn_per_search_dgs = new javax.swing.JRadioButton();
        btn_plus_dgs = new javax.swing.JButton();
        btn_substract_dgs = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane52 = new javax.swing.JScrollPane();
        txt_pNotes = new javax.swing.JTextArea();
        PN_accptBtn = new javax.swing.JButton();
        PN_clrBtn = new javax.swing.JButton();
        jScrollPane71 = new javax.swing.JScrollPane();
        jTextArea22 = new javax.swing.JTextArea();
        jPanel34 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        lbl_drugNameOListSearch = new javax.swing.JLabel();
        txt_drugNameOListSearch = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbl_productname = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        stock_qty = new javax.swing.JTextField();
        lbl_productNameOList1 = new javax.swing.JLabel();
        txt_productNameOList = new javax.swing.JTextField();
        jPanel74 = new javax.swing.JPanel();
        lbl_dosageOList = new javax.swing.JLabel();
        txt_drugstrength = new javax.swing.JTextField();
        lbl_quantityOList = new javax.swing.JLabel();
        txt_quantityOList = new javax.swing.JTextField();
        lbl_frequencyOList = new javax.swing.JLabel();
        cb_frequencyOList = new javax.swing.JComboBox();
        lbl_durationOList = new javax.swing.JLabel();
        cb_durationOList = new javax.swing.JComboBox();
        cb_durationTypeOList = new javax.swing.JComboBox();
        cb_instructionOList = new javax.swing.JComboBox();
        lbl_instructionOList = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_dosageFormOList = new javax.swing.JTextField();
        jScrollPane53 = new javax.swing.JScrollPane();
        txt_caution = new javax.swing.JTextArea();
        btn_drugAccept = new javax.swing.JButton();
        btn_drugClear = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        txt_packagetype = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        tbl_drug = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane60 = new javax.swing.JScrollPane();
        jPanel43 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        cbx_referral_doctor = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_referral_comment = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane62 = new javax.swing.JScrollPane();
        jPanel44 = new javax.swing.JPanel();
        btn_ProcAcc = new javax.swing.JButton();
        pnl_procedure_1 = new javax.swing.JPanel();
        jScrollPane54 = new javax.swing.JScrollPane();
        tree_procedure = new javax.swing.JTree();
        jPanel42 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        jPanel70 = new javax.swing.JPanel();
        jPanel72 = new javax.swing.JPanel();
        date_from = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        date_to = new com.toedter.calendar.JDateChooser();
        btn_generate_mc = new javax.swing.JButton();
        MC_accptBtn = new javax.swing.JButton();
        MC_clrBtn = new javax.swing.JButton();
        btn_generate_timeslip = new javax.swing.JButton();
        jPanel85 = new javax.swing.JPanel();
        rbtn_mcts_mc = new javax.swing.JRadioButton();
        rbtn_mcts_ts = new javax.swing.JRadioButton();
        time_from = new javax.swing.JTextField();
        time_to_hour = new javax.swing.JComboBox();
        time_to_minute = new javax.swing.JComboBox();
        time_to_second = new javax.swing.JComboBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_note_1 = new javax.swing.JTable();
        btn_discharge = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_hold = new javax.swing.JButton();
        btn_missing = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel76 = new javax.swing.JPanel();
        lbl_pName = new javax.swing.JLabel();
        txt_pName = new javax.swing.JTextField();
        txt_pPmiNo = new javax.swing.JTextField();
        lbl_pPmiNo = new javax.swing.JLabel();
        lbl_pIcNo = new javax.swing.JLabel();
        txt_pIcNo = new javax.swing.JTextField();
        lbl_pRace = new javax.swing.JLabel();
        txt_pRace = new javax.swing.JTextField();
        lbl_pSex = new javax.swing.JLabel();
        txt_pSex = new javax.swing.JLabel();
        lbl_pBdate = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        lbl_pStatus = new javax.swing.JLabel();
        txt_pStatus = new javax.swing.JTextField();
        lbl_pBloodSex = new javax.swing.JLabel();
        txt_pBloodSex = new javax.swing.JTextField();
        lbl_pBloodSex1 = new javax.swing.JLabel();
        lbl_g6pd = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbx_allergy = new javax.swing.JComboBox();
        btnStatus = new javax.swing.JButton();
        lbl_time = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lbl_new_patient = new javax.swing.JLabel();
        btn_viewHistory = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MItem_local = new javax.swing.JCheckBoxMenuItem();
        MItem_drives = new javax.swing.JCheckBoxMenuItem();
        menuCheckBluetooth = new javax.swing.JCheckBoxMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        MItem_Import = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jFrame3.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C.Complaints", "Diagnosis", "Immunisation", "Vital Sign", "Drugs", "Disability", "Allergy", "Social History", " " }));
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        btn_dUpdate.setText("Update");
        btn_dUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dUpdateActionPerformed(evt);
            }
        });

        btn_dDelete.setText("Delete");
        btn_dDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dDeleteActionPerformed(evt);
            }
        });

        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btn_dUpdate)
                .addGap(18, 18, 18)
                .addComponent(btn_dDelete)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dUpdate)
                    .addComponent(btn_dDelete)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane31.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane39.setViewportView(jTable2);

        label1.setBackground(new java.awt.Color(204, 204, 204));
        label1.setText("PATIENT QUEUE LIST");

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel13.setText("Date");

        tfieldDate.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tfieldDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfieldDateMouseClicked(evt);
            }
        });
        tfieldDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfieldDatePropertyChange(evt);
            }
        });

        tblQueue1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PMI_NO", "NAME", "EPISODE_TIME", "CONSULTATION_ROOM", "DOCTOR", "STATUS"
            }
        ));
        tblQueue1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQueue1MouseClicked(evt);
            }
        });
        jScrollPane43.setViewportView(tblQueue1);

        btnClose.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lbl_pIcNo1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pIcNo1.setText("New IC No:");

        txt_pIcNo1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pIcNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_pName1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pName1.setText("Name:");

        txt_pName1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pName1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_pHistoryOk2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btn_pHistoryOk2.setText("OK");
        btn_pHistoryOk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pHistoryOk2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 1266, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lbl_pName1)
                .addGap(51, 51, 51)
                .addComponent(txt_pName1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_pIcNo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_pIcNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addGap(45, 45, 45)
                .addComponent(tfieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(416, 416, 416))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(1183, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(btn_pHistoryOk2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(531, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_pIcNo1)
                                .addComponent(lbl_pName1)
                                .addComponent(txt_pName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_pIcNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(btnClose)
                        .addContainerGap(67, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pHistoryOk2)
                        .addGap(199, 199, 199))))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1266, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tblQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PMI NO", "PROBLEM", "DATE"
            }
        ));
        tblQueue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQueueMouseClicked(evt);
            }
        });
        jScrollPane44.setViewportView(tblQueue);

        btn_pHistoryOk1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btn_pHistoryOk1.setText("OK");
        btn_pHistoryOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pHistoryOk1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btn_pHistoryOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
            .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE)))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(btn_pHistoryOk1)
                .addContainerGap())
            .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(jTable3);

        txt_allergyDate3.setDateFormatString("dd/MM/yyyy");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinical Information System");
        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(204, 204, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1178, 1120));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel1.setText("Biodata");

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel8.setText("Problem Summary");

        btn_sPatient.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_sPatient.setText("Select Patient");
        btn_sPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sPatientMouseClicked(evt);
            }
        });
        btn_sPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sPatientActionPerformed(evt);
            }
        });

        btn_exit.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_exit.setText("Main Menu");
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
        });
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(173, 182, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(64, 64, 64), new java.awt.Color(0, 0, 0), new java.awt.Color(128, 128, 128), new java.awt.Color(192, 192, 192)));
        jPanel2.setPreferredSize(new java.awt.Dimension(1008, 502));

        jTextArea7.setColumns(20);
        jTextArea7.setEditable(false);
        jTextArea7.setLineWrap(true);
        jTextArea7.setRows(5);
        jTextArea7.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        jScrollPane1.setViewportView(jTextArea7);

        jLabel54.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel54.setText("Consultation Notes");

        jTabbedPane2.setBackground(new java.awt.Color(173, 182, 200));

        jTabbedPane4.setBackground(new java.awt.Color(173, 182, 200));

        jPanel3.setBackground(new java.awt.Color(173, 182, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel46.setBackground(new java.awt.Color(173, 182, 200));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous Visit Date"));

        tbl_cc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Problem", "Severity", "Site", "Duration", "Laterality", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_cc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ccMouseClicked(evt);
            }
        });
        jScrollPane32.setViewportView(tbl_cc);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane32, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel47.setBackground(new java.awt.Color(173, 182, 200));
        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Complaint"));

        lbl_complaintSearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintSearch.setText("Problem :");

        txt_complaintSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_complaintSearchKeyReleased(evt);
            }
        });

        btnSrcComplaint.setText("Search");
        btnSrcComplaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcComplaintActionPerformed(evt);
            }
        });

        lbx_complaintSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_complaintSearchMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lbx_complaintSearch);

        cbx_cSeverity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Mild", "Moderate", "Severe" }));
        cbx_cSeverity.setToolTipText("");

        lbl_cSeverity.setText("Severity :");

        lbl_duration.setText("Duration :");

        cbx_duration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Minutes", "Hour", "Day", "Week", "Month", "Year" }));

        lbl_laterality.setText("Laterality :");

        lbl_site.setText("Site : ");

        cbx_site.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Right", "Left" }));

        cbx_laterality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Right", "Left" }));

        txt_complaintComment.setColumns(20);
        txt_complaintComment.setLineWrap(true);
        txt_complaintComment.setRows(5);
        txt_complaintComment.setText(" ");
        jScrollPane5.setViewportView(txt_complaintComment);

        lbl_complaintComment.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintComment.setText("Comment:");

        btn_complaintAccept.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_complaintAccept.setText("Accept");
        btn_complaintAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_complaintAcceptActionPerformed(evt);
            }
        });

        btn_complaintClear.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_complaintClear.setText("Clear");
        btn_complaintClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_complaintClearActionPerformed(evt);
            }
        });

        jScrollPane20.setBackground(new java.awt.Color(173, 182, 200));

        jTextArea12.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea12.setColumns(20);
        jTextArea12.setRows(5);
        jScrollPane20.setViewportView(jTextArea12);

        rbtn_gen_search.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_gen_search.setSelected(true);
        rbtn_gen_search.setText("Generic Search");
        rbtn_gen_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtn_gen_searchMouseClicked(evt);
            }
        });

        rbtn_per_search.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_per_search.setText("Personalized Search");
        rbtn_per_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtn_per_searchMouseClicked(evt);
            }
        });

        btn_substract.setText("-");
        btn_substract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_substractActionPerformed(evt);
            }
        });

        btn_plus.setText("+");
        btn_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_complaintComment, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_duration, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_cSeverity, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_complaintSearch, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_duration)
                                    .addComponent(cbx_cSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addComponent(cbx_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_laterality, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_site, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbx_laterality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txt_complaintSearch)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSrcComplaint, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel47Layout.createSequentialGroup()
                                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(rbtn_per_search, javax.swing.GroupLayout.PREFERRED_SIZE, 142, Short.MAX_VALUE)
                                            .addComponent(rbtn_gen_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn_plus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_substract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btn_complaintAccept, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_complaintClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(lbl_ccdate, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_complaintSearch)
                    .addComponent(txt_complaintSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcComplaint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn_gen_search, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addComponent(btn_plus, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_substract, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbtn_per_search, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_complaintAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_complaintClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_cSeverity)
                    .addComponent(cbx_cSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_site)
                    .addComponent(cbx_site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_laterality)
                            .addComponent(cbx_laterality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_duration))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_complaintComment)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(lbl_ccdate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("C.Complaints", jPanel18);

        jPanel19.setBackground(new java.awt.Color(173, 182, 200));
        jPanel19.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel48.setBackground(new java.awt.Color(173, 182, 200));
        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Present Illness"));

        tbl_HPI1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
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
        tbl_HPI1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HPI1MouseClicked(evt);
            }
        });
        jScrollPane46.setViewportView(tbl_HPI1);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane46)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel49.setBackground(new java.awt.Color(173, 182, 200));
        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "History of Present Illness"));

        jLabel22.setText("Details :");

        HPI_DetailstxtArea.setColumns(20);
        HPI_DetailstxtArea.setRows(5);
        HPI_DetailstxtArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                HPI_DetailstxtAreaKeyReleased(evt);
            }
        });
        jScrollPane45.setViewportView(HPI_DetailstxtArea);

        btn_HPIAccpt.setText("Accept");
        btn_HPIAccpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HPIAccptActionPerformed(evt);
            }
        });

        HPI_ClrBtn.setText("Clear");
        HPI_ClrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPI_ClrBtnActionPerformed(evt);
            }
        });

        jScrollPane67.setBackground(new java.awt.Color(173, 182, 200));

        jTextArea18.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea18.setColumns(20);
        jTextArea18.setRows(5);
        jScrollPane67.setViewportView(jTextArea18);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(HPI_ClrBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_HPIAccpt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane67, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel49Layout.createSequentialGroup()
                                .addComponent(btn_HPIAccpt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPI_ClrBtn))
                            .addComponent(jLabel22))
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane67, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane76.setViewportView(jPanel19);

        jTabbedPane4.addTab("HPI", jScrollPane76);

        jPanel20.setBackground(new java.awt.Color(173, 182, 200));
        jPanel20.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel50.setBackground(new java.awt.Color(173, 182, 200));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Past Medical History"));

        tbl_pmh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Problem", "Status", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pmh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pmhMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tbl_pmh);

        jScrollPane68.setBackground(new java.awt.Color(173, 182, 200));

        jTextArea19.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea19.setColumns(20);
        jTextArea19.setRows(5);
        jScrollPane68.setViewportView(jTextArea19);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane68, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane68, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel51.setBackground(new java.awt.Color(173, 182, 200));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Past Medical History"));

        lbl_complaintSearch1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintSearch1.setText("Find PMH : ");

        txt_PMHSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PMHSearchKeyReleased(evt);
            }
        });

        btnSrcComplaint1.setText("Search");
        btnSrcComplaint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcComplaint1ActionPerformed(evt);
            }
        });

        lbx_PMHSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_PMHSearchMouseClicked(evt);
            }
        });
        jScrollPane47.setViewportView(lbx_PMHSearch);

        rbtn_cActive1.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_cActive1.setText("Active");

        rbtn_cInactive1.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_cInactive1.setText("Inactive");

        jLabel27.setText("Problem Status :");

        jLabel28.setText("Comments :");

        txt_PMHComment.setColumns(20);
        txt_PMHComment.setRows(5);
        jScrollPane48.setViewportView(txt_PMHComment);

        PMH_AccptrBtn.setText("Accept");
        PMH_AccptrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMH_AccptrBtnActionPerformed(evt);
            }
        });

        PMH_clearBtn.setText("Clear");
        PMH_clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMH_clearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_complaintSearch1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(rbtn_cActive1)
                        .addGap(18, 18, 18)
                        .addComponent(rbtn_cInactive1))
                    .addComponent(jScrollPane47, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(txt_PMHSearch)
                    .addComponent(jScrollPane48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSrcComplaint1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PMH_AccptrBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PMH_clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_complaintSearch1)
                    .addComponent(txt_PMHSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcComplaint1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(PMH_AccptrBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PMH_clearBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtn_cActive1)
                        .addComponent(jLabel27))
                    .addComponent(rbtn_cInactive1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jScrollPane48, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jScrollPane77.setViewportView(jPanel20);

        jTabbedPane4.addTab("PMH", jScrollPane77);

        jPanel21.setBackground(new java.awt.Color(173, 182, 200));
        jPanel21.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel52.setBackground(new java.awt.Color(173, 182, 200));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Family Health History"));

        tbl_fmh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Problem", "Relationship", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_fmh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_fmhMouseClicked(evt);
            }
        });
        jScrollPane79.setViewportView(tbl_fmh);

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane79, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane79, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel53.setBackground(new java.awt.Color(173, 182, 200));
        jPanel53.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Family Health History"));

        jLabel48.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel48.setText("Problem :");

        txt_FHSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_FHSearchKeyReleased(evt);
            }
        });

        btnSrcSocialHistory1.setText("Search");
        btnSrcSocialHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcSocialHistory1ActionPerformed(evt);
            }
        });

        lbx_FHSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_FHSearchMouseClicked(evt);
            }
        });
        jScrollPane49.setViewportView(lbx_FHSearch);

        jLabel52.setText("Relationship :");

        FH_Relationship.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel53.setText("Comments :");

        FH_Comments.setColumns(20);
        FH_Comments.setRows(5);
        jScrollPane50.setViewportView(FH_Comments);

        FH_accptBtn.setText("Accept");
        FH_accptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH_accptBtnActionPerformed(evt);
            }
        });

        FH_clrBtn.setText("Clear");
        FH_clrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH_clrBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane49, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(jScrollPane50)
                    .addComponent(txt_FHSearch)
                    .addComponent(FH_Relationship, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FH_accptBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSrcSocialHistory1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FH_clrBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel48)
                    .addComponent(txt_FHSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcSocialHistory1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(FH_accptBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FH_clrBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel52)
                    .addComponent(FH_Relationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 143, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jScrollPane78.setViewportView(jPanel21);

        jTabbedPane4.addTab("Family History", jScrollPane78);

        jPanel22.setBackground(new java.awt.Color(173, 182, 200));

        jScrollPane38.setBackground(new java.awt.Color(173, 182, 200));
        jScrollPane38.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane38.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane38.setPreferredSize(new java.awt.Dimension(642, 300));

        jPanel13.setBackground(new java.awt.Color(173, 182, 200));
        jPanel13.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel54.setBackground(new java.awt.Color(173, 182, 200));
        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Social History"));

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel18.setText("Problem :");

        txt_socialProblem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_socialProblemKeyReleased(evt);
            }
        });

        btnSrcSocialHistory.setText("Search");
        btnSrcSocialHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcSocialHistoryActionPerformed(evt);
            }
        });

        lbx_socialProblem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_socialProblemMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(lbx_socialProblem);

        txt_socialDate1.setDateFormatString("dd/MM/yyyy");

        jLabel66.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel66.setText("Since When : ");

        jLabel67.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel67.setText("Comments : ");

        txt_socialComment.setColumns(20);
        txt_socialComment.setRows(5);
        jScrollPane27.setViewportView(txt_socialComment);

        btn_sclAccept.setText("Accept");
        btn_sclAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sclAcceptActionPerformed(evt);
            }
        });

        btn_sclClear.setText("Clear");
        btn_sclClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sclClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_shdate, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(txt_socialProblem)
                            .addComponent(txt_socialDate1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_sclAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSrcSocialHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_sclClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel18)
                    .addComponent(txt_socialProblem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcSocialHistory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(btn_sclAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_sclClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel66)
                    .addComponent(txt_socialDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(lbl_shdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel55.setBackground(new java.awt.Color(173, 182, 200));
        jPanel55.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Social History"));

        tbl_sh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Problem", "Since When", "Comments"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_shMouseClicked(evt);
            }
        });
        jScrollPane42.setViewportView(tbl_sh);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane42, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane38.setViewportView(jPanel13);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Social History", jPanel22);

        jPanel23.setBackground(new java.awt.Color(173, 182, 200));
        jPanel23.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel56.setBackground(new java.awt.Color(173, 182, 200));
        jPanel56.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Blood Group / G6PD"));

        jLabel57.setText("Blood Type :");

        jLabel58.setText("Rhesus Type :");

        rbtn_Bpositive.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_Bpositive.setText("Positive");

        rbtn_Bnegative.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_Bnegative.setText("Negative");

        rbtn_Bnormal.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_Bnormal.setText("Normal");

        rbtn_Bdeficient.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_Bdeficient.setText("Deficient");

        jLabel59.setText("G6PD Status :");

        jLabel60.setText("Comments :");

        txt_BldCmmnt.setColumns(20);
        txt_BldCmmnt.setRows(5);
        txt_BldCmmnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BldCmmntKeyReleased(evt);
            }
        });
        jScrollPane51.setViewportView(txt_BldCmmnt);

        BG_accptBtn.setText("Accept");
        BG_accptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG_accptBtnActionPerformed(evt);
            }
        });

        BG_clrBtn.setText("Clear");
        BG_clrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG_clrBtnActionPerformed(evt);
            }
        });

        jScrollPane70.setBackground(new java.awt.Color(173, 182, 200));

        jTextArea21.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea21.setColumns(20);
        jTextArea21.setRows(5);
        jScrollPane70.setViewportView(jTextArea21);

        jButton9.setText("Change");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        cmbBloodType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AB", "A", "B", "O" }));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel56Layout.createSequentialGroup()
                                .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BG_accptBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BG_clrBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel56Layout.createSequentialGroup()
                                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtn_Bpositive)
                                    .addComponent(rbtn_Bnormal))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtn_Bnegative)
                                    .addComponent(rbtn_Bdeficient)))
                            .addGroup(jPanel56Layout.createSequentialGroup()
                                .addComponent(cmbBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jButton9))))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jScrollPane70, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel57)
                    .addComponent(jButton9)
                    .addComponent(cmbBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel58)
                    .addComponent(rbtn_Bpositive)
                    .addComponent(rbtn_Bnegative))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel59)
                    .addComponent(rbtn_Bnormal)
                    .addComponent(rbtn_Bdeficient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel60)
                    .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(BG_accptBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BG_clrBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane70, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Blood Group/G6PD", jPanel23);

        jPanel24.setBackground(new java.awt.Color(173, 182, 200));

        jScrollPane37.setBackground(new java.awt.Color(173, 182, 200));
        jScrollPane37.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane37.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel12.setBackground(new java.awt.Color(173, 182, 200));
        jPanel12.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel58.setBackground(new java.awt.Color(173, 182, 200));
        jPanel58.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Allergy"));

        lb_allergySearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lb_allergySearch.setText("Search:");

        txt_allergySearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_allergySearchKeyReleased(evt);
            }
        });

        btnSrcAllergy.setText("Search");
        btnSrcAllergy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcAllergyActionPerformed(evt);
            }
        });

        lbx_allergySearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_allergySearchMouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(lbx_allergySearch);

        txt_allergyDate2.setDateFormatString("dd/MM/yyyy");

        lbl_allergyDate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_allergyDate.setText("Since When : ");

        lbl_allergyComments.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_allergyComments.setText("Comments : ");

        txt_allergyComments.setColumns(20);
        txt_allergyComments.setRows(5);
        jScrollPane25.setViewportView(txt_allergyComments);

        btn_allergyAccept.setText("Accept");
        btn_allergyAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allergyAcceptActionPerformed(evt);
            }
        });

        btn_allergyClear.setText("Clear");
        btn_allergyClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allergyClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_allergySearch, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_allergyDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_allergyComments, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane25)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(txt_allergySearch)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(txt_allergyDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSrcAllergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_allergyAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_allergyClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_allergySearch)
                    .addComponent(txt_allergySearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcAllergy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(btn_allergyAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_allergyClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_allergyDate)
                    .addComponent(txt_allergyDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_allergyComments)
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 142, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jScrollPane37.setViewportView(jPanel12);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Allergy", jPanel24);

        jPanel25.setBackground(new java.awt.Color(173, 182, 200));

        jPanel5.setBackground(new java.awt.Color(173, 182, 200));
        jPanel5.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel59.setBackground(new java.awt.Color(173, 182, 200));
        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Immunisation"));

        tbl_imu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Immunisation Name", "Comment", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_imu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_imuMouseClicked(evt);
            }
        });
        jScrollPane34.setViewportView(tbl_imu);

        jTextArea10.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea10.setColumns(20);
        jTextArea10.setEditable(false);
        jTextArea10.setLineWrap(true);
        jTextArea10.setRows(5);
        jScrollPane18.setViewportView(jTextArea10);

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane34)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel60.setBackground(new java.awt.Color(173, 182, 200));
        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Immunisation"));

        lbl_immSearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_immSearch.setText("Search:");

        txt_immSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_immSearchKeyReleased(evt);
            }
        });

        btnSrcImmunisation.setText("Search");
        btnSrcImmunisation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcImmunisationActionPerformed(evt);
            }
        });

        lbx_immSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_immSearchMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(lbx_immSearch);

        lbl_immComment.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_immComment.setText("Comment:");

        txt_immComment.setColumns(20);
        txt_immComment.setRows(5);
        jScrollPane10.setViewportView(txt_immComment);

        lbl_immDate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_immDate.setText("Date:");

        txt_immDate1.setDateFormatString("dd/MM/yyyy");

        btn_immAccept.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_immAccept.setText("Accept");
        btn_immAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_immAcceptActionPerformed(evt);
            }
        });

        btn_immClear.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_immClear.setText("Clear");
        btn_immClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_immClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbl_immSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(txt_immSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSrcImmunisation)
                    .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_immAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_immClear, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_immComment)
                    .addComponent(lbl_immDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(txt_immDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(208, Short.MAX_VALUE))
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addGap(111, 111, 111))))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_immSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_immSearch)
                    .addComponent(btnSrcImmunisation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(btn_immAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_immClear)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_immComment))
                .addGap(4, 4, 4)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_immDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_immDate))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lbl_immdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 44, Short.MAX_VALUE)
                        .addComponent(lbl_immdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane8.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Immunization", jPanel25);

        jPanel26.setBackground(new java.awt.Color(173, 182, 200));

        jScrollPane36.setBackground(new java.awt.Color(173, 182, 200));
        jScrollPane36.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane36.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel11.setBackground(new java.awt.Color(173, 182, 200));
        jPanel11.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel61.setBackground(new java.awt.Color(173, 182, 200));
        jPanel61.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Disability"));

        lbl_disabilityType.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_disabilityType.setText("Disability Type :");

        txt_disabilityType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_disabilityTypeKeyReleased(evt);
            }
        });

        btnSrcDisability.setText("Search");
        btnSrcDisability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcDisabilityActionPerformed(evt);
            }
        });

        lbx_disabilityType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_disabilityTypeMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(lbx_disabilityType);

        lbl_disDate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_disDate.setText("Since When : ");

        lbl_dComments.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_dComments.setText("Comments : ");

        txt_dComments.setColumns(20);
        txt_dComments.setRows(5);
        jScrollPane22.setViewportView(txt_dComments);

        btn_dAccept.setText("Accept");
        btn_dAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dAcceptActionPerformed(evt);
            }
        });

        btn_dClear.setText("Clear");
        btn_dClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dClearActionPerformed(evt);
            }
        });

        txt_dDate1.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_disabilityType, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_disDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_dComments, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_disabilityType)
                    .addComponent(jScrollPane22)
                    .addComponent(jScrollPane21)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addComponent(txt_dDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSrcDisability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(82, 82, 82))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_disabilityType)
                    .addComponent(txt_disabilityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcDisability))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addComponent(btn_dAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_dClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_disDate)
                    .addComponent(txt_dDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dComments))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel62.setBackground(new java.awt.Color(173, 182, 200));
        jPanel62.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Disability"));

        tbl_dab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Disability", "Since When", "Comments"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dabMouseClicked(evt);
            }
        });
        jScrollPane41.setViewportView(tbl_dab);

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(lbl_disdate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_disdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );

        jScrollPane36.setViewportView(jPanel11);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane36, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane36, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Disability", jPanel26);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Subjective", jPanel14);

        jPanel15.setBackground(new java.awt.Color(173, 182, 200));

        jTabbedPane5.setBackground(new java.awt.Color(173, 182, 200));
        jTabbedPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane5MouseClicked(evt);
            }
        });

        jScrollPane11.setPreferredSize(new java.awt.Dimension(625, 2000));

        jPanel6.setBackground(new java.awt.Color(173, 182, 200));
        jPanel6.setPreferredSize(new java.awt.Dimension(623, 2000));

        jLabel30.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel30.setText("Biometrics/Vital Sign");

        btn_vitalSignClear.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_vitalSignClear.setText("Clear");
        btn_vitalSignClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_vitalSignClearMouseClicked(evt);
            }
        });
        btn_vitalSignClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vitalSignClearActionPerformed(evt);
            }
        });

        btn_vitalSignAccept.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_vitalSignAccept.setText("Accept");
        btn_vitalSignAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vitalSignAcceptActionPerformed(evt);
            }
        });

        jPanel77.setBackground(new java.awt.Color(173, 182, 200));
        jPanel77.setBorder(javax.swing.BorderFactory.createTitledBorder("Glasgow Coma Scale"));

        jButton10.setText("Insert Glasgow Coma Scale");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel36.setText("Result : ");

        txt_gcs_result.setEditable(false);

        jLabel42.setText("Points : ");

        txt_gcs_points.setEditable(false);

        jButton12.setText("Clear");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tbl_gcs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Response", "Point"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane55.setViewportView(tbl_gcs);

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel77Layout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_gcs_result, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_gcs_points, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txt_gcs_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txt_gcs_points, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel78.setBackground(new java.awt.Color(173, 182, 200));
        jPanel78.setBorder(javax.swing.BorderFactory.createTitledBorder("Pediatric Glasgow Coma Scale (PGCS)"));

        jButton13.setText("Insert Pediatric Glasgow Coma Scale");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel96.setText("Result : ");

        txt_pgcs_result.setEditable(false);

        jLabel97.setText("Points : ");

        txt_pgcs_points.setEditable(false);

        jButton15.setText("Clear");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        tbl_pgcs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Response", "Point"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane56.setViewportView(tbl_pgcs);

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel78Layout.createSequentialGroup()
                            .addComponent(jLabel96)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_pgcs_result, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel78Layout.createSequentialGroup()
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pgcs_points, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel78Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel96)
                            .addComponent(txt_pgcs_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97)
                            .addComponent(txt_pgcs_points, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel79.setBackground(new java.awt.Color(173, 182, 200));
        jPanel79.setBorder(javax.swing.BorderFactory.createTitledBorder("Blood Pressure"));

        jLabel17.setText("Blood Pressure Lying");

        jLabel15.setText("Blood Pressure Standing");

        jLabel3.setText("Blood Pressure Sitting");

        jLabel26.setText("Systolic : ");

        jLabel29.setText("Systolic : ");

        jLabel43.setText("Systolic : ");

        txt_systolic2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_systolic2KeyReleased(evt);
            }
        });

        txt_systolic1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_systolic1KeyReleased(evt);
            }
        });

        txt_systolic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_systolicKeyReleased(evt);
            }
        });

        jLabel44.setText("mmHg");

        jLabel47.setText("mmHg");

        jLabel51.setText("mmHg");

        jLabel56.setText("Diastolic : ");

        jLabel49.setText("Diastolic : ");

        jLabel45.setText("Diastolic : ");

        txt_diastolic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_diastolicKeyReleased(evt);
            }
        });

        txt_diastolic1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_diastolic1KeyReleased(evt);
            }
        });

        txt_diastolic2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_diastolic2KeyReleased(evt);
            }
        });

        jLabel81.setText("mmHg");

        jLabel50.setText("mmHg");

        jLabel46.setText("mmHg");

        jLabel84.setText("Pulse : ");

        jLabel85.setText("Pulse : ");

        jLabel92.setText("Pulse : ");

        txt_pulse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pulseKeyReleased(evt);
            }
        });

        txt_pulse1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pulse1KeyReleased(evt);
            }
        });

        txt_pulse2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pulse2KeyReleased(evt);
            }
        });

        jLabel93.setText("mmHg");

        jLabel94.setText("mmHg");

        jLabel95.setText("mmHg");

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel79Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_systolic1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel47))
                            .addGroup(jPanel79Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_systolic, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44))
                            .addGroup(jPanel79Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_systolic2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel51)))))
                .addGap(26, 26, 26)
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_diastolic1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50))
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_diastolic, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46))
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_diastolic2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel81)))
                .addGap(18, 18, 18)
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pulse1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel94))
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel93))
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pulse2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel95)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel26)
                            .addComponent(txt_systolic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(txt_diastolic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel29)
                            .addComponent(txt_systolic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel49)
                            .addComponent(txt_diastolic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel43)
                            .addComponent(txt_systolic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)
                            .addComponent(jLabel56)
                            .addComponent(txt_diastolic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81)))
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(txt_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel85)
                            .addComponent(txt_pulse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92)
                            .addComponent(txt_pulse2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel80.setBackground(new java.awt.Color(173, 182, 200));
        jPanel80.setBorder(javax.swing.BorderFactory.createTitledBorder("Respiratory Rate"));

        jLabel72.setText("Repiratory Rate :");

        jLabel73.setText("breaths / min");

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_respiratory_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txt_respiratory_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel81.setBackground(new java.awt.Color(173, 182, 200));
        jPanel81.setBorder(javax.swing.BorderFactory.createTitledBorder("Oxygen Saturation"));

        jLabel74.setText("Oxygen Saturation :");

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_oxygen_saturation, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txt_oxygen_saturation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel82.setBackground(new java.awt.Color(173, 182, 200));
        jPanel82.setBorder(javax.swing.BorderFactory.createTitledBorder("Body Temperature"));

        jLabel35.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel35.setText("Temperature");

        txt_temperature.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_temperatureKeyReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel41.setText("celcius");

        jSlider4.setBackground(new java.awt.Color(173, 182, 200));
        jSlider4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jSlider4.setMajorTickSpacing(1);
        jSlider4.setMaximum(42);
        jSlider4.setMinimum(37);
        jSlider4.setPaintLabels(true);
        jSlider4.setPaintTicks(true);
        jSlider4.setValue(39);
        jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider4StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_temperature, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(txt_temperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41))
                    .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel83.setBackground(new java.awt.Color(173, 182, 200));
        jPanel83.setBorder(javax.swing.BorderFactory.createTitledBorder("Pan Scale"));

        jLabel75.setText("Pain Scale :");

        jLabel9.setText("Result :");

        txt_pain_result.setEditable(false);
        txt_pain_result.setText("No Pain");

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Pain-Scale-Wong-Baker.jpg"))); // NOI18N

        cbx_pain_scale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cbx_pain_scale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_pain_scaleItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbx_pain_scale, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_pain_result, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(cbx_pain_scale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_pain_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );

        jPanel84.setBackground(new java.awt.Color(173, 182, 200));
        jPanel84.setBorder(javax.swing.BorderFactory.createTitledBorder("Others"));

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel31.setText("Height :");

        txt_height.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_heightActionPerformed(evt);
            }
        });
        txt_height.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_heightKeyReleased(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel37.setText("cm");

        jSlider1.setBackground(new java.awt.Color(173, 182, 200));
        jSlider1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jSlider1.setMajorTickSpacing(20);
        jSlider1.setMaximum(210);
        jSlider1.setMinimum(140);
        jSlider1.setMinorTickSpacing(10);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(175);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel32.setText("Weight :");

        txt_weight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_weightKeyReleased(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel38.setText("kg");

        jSlider2.setBackground(new java.awt.Color(173, 182, 200));
        jSlider2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jSlider2.setMajorTickSpacing(30);
        jSlider2.setMaximum(150);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setValue(70);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel33.setText("BMI :");

        txt_bmi.setEditable(false);

        jLabel39.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel39.setText("kg/m2");

        btn_calculateBmi.setText("Calculate BMI");
        btn_calculateBmi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_calculateBmiMouseClicked(evt);
            }
        });
        btn_calculateBmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculateBmiActionPerformed(evt);
            }
        });

        jLabel55.setText("Weight Status :");

        txt_weightStatus.setEditable(false);

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel34.setText("Head Circumference :");

        txt_headCircumference.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_headCircumferenceKeyReleased(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel40.setText("cm");

        jSlider3.setBackground(new java.awt.Color(173, 182, 200));
        jSlider3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jSlider3.setMajorTickSpacing(10);
        jSlider3.setMinimum(50);
        jSlider3.setPaintLabels(true);
        jSlider3.setPaintTicks(true);
        jSlider3.setValue(75);
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider3StateChanged(evt);
            }
        });

        jLabel63.setText("Blood Glucose :");

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_calculateBmi))
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel55)
                            .addComponent(jLabel63))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_weightStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel84Layout.createSequentialGroup()
                                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_blood_glucose, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel84Layout.createSequentialGroup()
                                        .addComponent(txt_headCircumference, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel40)))
                                .addGap(18, 18, 18)
                                .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lbl_vsdate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txt_weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)))
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel39)
                    .addComponent(btn_calculateBmi))
                .addGap(10, 10, 10)
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txt_weightStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(txt_headCircumference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40))
                    .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(txt_blood_glucose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_vsdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel84, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(57, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(btn_vitalSignAccept)
                .addGap(31, 31, 31)
                .addComponent(btn_vitalSignClear)
                .addGap(0, 355, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_vitalSignAccept)
                    .addComponent(btn_vitalSignClear))
                .addContainerGap(671, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Vital Sign", jPanel27);

        jPanel28.setBackground(new java.awt.Color(173, 182, 200));
        jPanel28.setPreferredSize(new java.awt.Dimension(623, 800));

        jPanel69.setBackground(new java.awt.Color(173, 182, 200));
        jPanel69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel69MouseClicked(evt);
            }
        });

        jPanel66.setBackground(new java.awt.Color(173, 182, 200));

        jLabel2.setText("Throat : ");

        jLabel4.setText("Heart : ");

        jLabel5.setText("Right Lung : ");

        jLabel6.setText("Right Shoulder : ");

        jLabel7.setText("Left Shoulder : ");

        jLabel10.setText("Stomache : ");

        jLabel11.setText("Head : ");

        jLabel16.setText("Nose : ");

        jLabel20.setText("Mouth : ");

        jLabel21.setText("Right Ear : ");

        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tekak, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_jantung, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_bahu_kanan, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel66Layout.createSequentialGroup()
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel66Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_bahu_kiri)
                                    .addComponent(txt_perut)
                                    .addComponent(txt_kepala)
                                    .addComponent(txt_hidung)
                                    .addComponent(txt_mulut)))
                            .addGroup(jPanel66Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel66Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_telinga_kanan, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_peparu_kanan, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tekak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jantung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_peparu_kanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bahu_kanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bahu_kiri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_perut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kepala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hidung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mulut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(4, 4, 4)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txt_telinga_kanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(173, 182, 200));

        jScrollPane57.setViewportView(tree_physical_exam);

        jButton11.setText("Accept");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        txt_pe_comments.setColumns(20);
        txt_pe_comments.setRows(5);
        jScrollPane59.setViewportView(txt_pe_comments);

        jLabel64.setText("Comments :-");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane57, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64)
                    .addComponent(jScrollPane59, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane57, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane59, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );

        jScrollPane82.setViewportView(jPanel69);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jScrollPane82, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane82, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Phys.Exam", jPanel28);
        jPanel28.getAccessibleContext().setAccessibleParent(jScrollPane3);

        jPanel29.setBackground(new java.awt.Color(173, 182, 200));
        jPanel29.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Radiology Result", jPanel29);

        jPanel30.setBackground(new java.awt.Color(173, 182, 200));
        jPanel30.setPreferredSize(new java.awt.Dimension(623, 582));

        jScrollPane35.setBackground(new java.awt.Color(173, 182, 200));

        jPanel68.setBackground(new java.awt.Color(173, 182, 200));

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 992, Short.MAX_VALUE)
        );

        jScrollPane35.setViewportView(jPanel68);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jScrollPane35)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane35, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Laboratory Result", jPanel30);

        jPanel31.setBackground(new java.awt.Color(173, 182, 200));
        jPanel31.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel71.setBackground(new java.awt.Color(173, 182, 200));

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );

        jScrollPane58.setViewportView(jPanel71);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane58, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane58, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Observation Procedure", jPanel31);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Objective", jPanel15);

        jPanel16.setBackground(new java.awt.Color(173, 182, 200));

        jTabbedPane6.setBackground(new java.awt.Color(173, 182, 200));

        jPanel32.setBackground(new java.awt.Color(173, 182, 200));

        jPanel4.setBackground(new java.awt.Color(173, 182, 200));
        jPanel4.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel63.setBackground(new java.awt.Color(173, 182, 200));
        jPanel63.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Diagnosis"));

        tbl_dgs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Type", "Date", "Diagnosis", "Severity", "Site", "Laterality", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dgs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dgsMouseClicked(evt);
            }
        });
        jScrollPane33.setViewportView(tbl_dgs);

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane33, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel64.setBackground(new java.awt.Color(173, 182, 200));
        jPanel64.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Diagnosis"));

        jLabel127.setText("Type:");

        jRadioButton1.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_grp_diagnosis.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Final");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_grp_diagnosis.add(jRadioButton2);
        jRadioButton2.setText("Provisional");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });

        lbl_ddate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_ddate.setText("Date:");

        txt_date1.setDateFormatString("dd/MM/yyyy");

        lbl_diagnosisSearch.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_diagnosisSearch.setText("Search:");

        txt_diagnosisSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_diagnosisSearchKeyReleased(evt);
            }
        });

        lbx_diagnosisSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbx_diagnosisSearchMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(lbx_diagnosisSearch);

        lbl_dSeverity.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_dSeverity.setText("Severity:");

        cbx_dSeverity.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cbx_dSeverity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Select]", "Mild", "Normal", "Acute" }));

        lbl_site1.setText("Site :");

        dbx_site.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Right", "Left" }));

        lbl_laterality1.setText("Laterality :");

        dbx_laterality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "Right", "Left" }));

        lbl_complaintComment1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_complaintComment1.setText("Comment:");

        txt_diagComment.setColumns(20);
        txt_diagComment.setLineWrap(true);
        txt_diagComment.setRows(5);
        txt_diagComment.setText(" ");
        jScrollPane75.setViewportView(txt_diagComment);

        btn_diagnosisAccept.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_diagnosisAccept.setText("Accept");
        btn_diagnosisAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_diagnosisAcceptActionPerformed(evt);
            }
        });

        btn_diagnosisClear.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_diagnosisClear.setText("Clear");
        btn_diagnosisClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_diagnosisClearActionPerformed(evt);
            }
        });

        btnSrcDiagnosis.setText("Search");
        btnSrcDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcDiagnosisActionPerformed(evt);
            }
        });

        rbtn_gen_search_dgs.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_gen_search_dgs.setSelected(true);
        rbtn_gen_search_dgs.setText("Generic Search");
        rbtn_gen_search_dgs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtn_gen_search_dgsMouseClicked(evt);
            }
        });

        rbtn_per_search_dgs.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_per_search_dgs.setText("Personalized Search");
        rbtn_per_search_dgs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtn_per_search_dgsMouseClicked(evt);
            }
        });

        btn_plus_dgs.setText("+");
        btn_plus_dgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plus_dgsActionPerformed(evt);
            }
        });

        btn_substract_dgs.setText("-");
        btn_substract_dgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_substract_dgsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(lbl_site1)
                        .addGap(18, 18, 18)
                        .addComponent(dbx_site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel127, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_ddate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_diagnosisSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_dSeverity, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_laterality1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_complaintComment1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_diagdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel64Layout.createSequentialGroup()
                                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel64Layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton2))
                                    .addComponent(cbx_dSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dbx_laterality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane75)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                    .addComponent(txt_diagnosisSearch)
                                    .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSrcDiagnosis)
                                    .addComponent(btn_diagnosisAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_diagnosisClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbtn_per_search_dgs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(rbtn_gen_search_dgs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_substract_dgs, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_plus_dgs, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_ddate)
                    .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_diagnosisSearch)
                    .addComponent(txt_diagnosisSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrcDiagnosis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addComponent(btn_plus_dgs, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_substract_dgs, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn_gen_search_dgs, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel64Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(rbtn_per_search_dgs, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_diagnosisAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_diagnosisClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dSeverity)
                    .addComponent(cbx_dSeverity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbx_site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_site1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_laterality1)
                    .addComponent(dbx_laterality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_complaintComment1)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane75, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_diagdate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Diagnosis", jPanel32);

        jPanel33.setBackground(new java.awt.Color(173, 182, 200));
        jPanel33.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel65.setBackground(new java.awt.Color(173, 182, 200));
        jPanel65.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Progress Notes"));

        jLabel62.setText("Notes :");

        txt_pNotes.setColumns(20);
        txt_pNotes.setRows(5);
        txt_pNotes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pNotesKeyReleased(evt);
            }
        });
        jScrollPane52.setViewportView(txt_pNotes);

        PN_accptBtn.setText("Accept");
        PN_accptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PN_accptBtnActionPerformed(evt);
            }
        });

        PN_clrBtn.setText("Clear");
        PN_clrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PN_clrBtnActionPerformed(evt);
            }
        });

        jScrollPane71.setBackground(new java.awt.Color(173, 182, 200));

        jTextArea22.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea22.setColumns(20);
        jTextArea22.setRows(5);
        jScrollPane71.setViewportView(jTextArea22);

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(PN_accptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PN_clrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane71, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addGap(43, 43, 43)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane71, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PN_clrBtn)
                        .addComponent(PN_accptBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Progress Notes", jPanel33);

        jPanel34.setBackground(new java.awt.Color(173, 182, 200));
        jPanel34.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Problem List", jPanel34);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Assessment", jPanel16);

        jPanel17.setBackground(new java.awt.Color(173, 182, 200));

        jTabbedPane7.setBackground(new java.awt.Color(173, 182, 200));

        jPanel35.setBackground(new java.awt.Color(173, 182, 200));
        jPanel35.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Radiology Request", jPanel35);

        jPanel36.setBackground(new java.awt.Color(173, 182, 200));
        jPanel36.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("Laboratory Request", jPanel36);

        jPanel37.setBackground(new java.awt.Color(173, 182, 200));

        jPanel7.setBackground(new java.awt.Color(173, 182, 200));
        jPanel7.setPreferredSize(new java.awt.Dimension(623, 800));

        jPanel45.setBackground(new java.awt.Color(173, 182, 200));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbl_drugNameOListSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_drugNameOListSearch.setText("Search :");

        txt_drugNameOListSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_drugNameOListSearchKeyReleased(evt);
            }
        });

        tbl_productname.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_productname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productnameMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tbl_productname);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Stock Quantity :");

        stock_qty.setEditable(false);

        lbl_productNameOList1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_productNameOList1.setText("Trade Name :");

        txt_productNameOList.setEditable(false);

        jPanel74.setBackground(new java.awt.Color(173, 182, 200));
        jPanel74.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbl_dosageOList.setText("Drug Strength :");

        txt_drugstrength.setEditable(false);

        lbl_quantityOList.setText("Quantity :");

        lbl_frequencyOList.setText("Frequency :");

        cb_frequencyOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "In the morning", "At night", "Daily", "Twice a day", "3 times a day", "4 times a day", "2 hourly", "4 hourly", "6 hourly", "8 hourly", "Immedietly", "As needed" }));

        lbl_durationOList.setText("Duration :");

        cb_durationOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6" }));

        cb_durationTypeOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Day", "Week", "Month" }));

        cb_instructionOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "If required", "As directed", "Before meals", "After meals", "Every second day", "Left side", "Right side", "To both sides", "Other" }));

        lbl_instructionOList.setText("Instruction :");

        jLabel25.setText("Cautionary :");

        txt_caution.setColumns(5);
        txt_caution.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_caution.setLineWrap(true);
        txt_caution.setRows(5);
        txt_caution.setWrapStyleWord(true);
        jScrollPane53.setViewportView(txt_caution);

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_dosageOList)
                    .addComponent(lbl_quantityOList)
                    .addComponent(lbl_frequencyOList)
                    .addComponent(lbl_durationOList)
                    .addComponent(lbl_instructionOList)
                    .addComponent(jLabel25))
                .addGap(28, 28, 28)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                        .addComponent(cb_durationOList, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_durationTypeOList, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(cb_instructionOList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_frequencyOList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane53)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_quantityOList, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(txt_drugstrength, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_dosageFormOList, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_dosageOList)
                    .addComponent(txt_drugstrength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dosageFormOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quantityOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_quantityOList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_frequencyOList)
                    .addComponent(cb_frequencyOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_durationOList)
                    .addComponent(cb_durationOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_durationTypeOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_instructionOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_instructionOList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_drugAccept.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_drugAccept.setText("Accept");
        btn_drugAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_drugAcceptActionPerformed(evt);
            }
        });

        btn_drugClear.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_drugClear.setText("Clear");
        btn_drugClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_drugClearActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("Packaging Type :");

        txt_packagetype.setEditable(false);

        jButton5.setText("<html><center>\nDownload<br />\nDrug</center>\n</html>");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_drugNameOListSearch)
                            .addComponent(jLabel24)
                            .addComponent(lbl_productNameOList1))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_productNameOList, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txt_drugNameOListSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(stock_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_packagetype, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_drugAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_drugClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_drugNameOListSearch)
                    .addComponent(txt_drugNameOListSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_drugAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_drugClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stock_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel61)
                    .addComponent(txt_packagetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_productNameOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_productNameOList1))
                .addGap(18, 18, 18)
                .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(173, 182, 200));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Previous History of Drug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tbl_drug.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Active Ingredient", "Product Name", "Quantity", "Frequency", "Duration", "Instruction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_drug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_drugMouseClicked(evt);
            }
        });
        jScrollPane29.setViewportView(tbl_drug);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane29)
                .addGap(22, 22, 22))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jScrollPane12.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Drug Treatment", jPanel37);

        jPanel38.setBackground(new java.awt.Color(173, 182, 200));
        jPanel38.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Monitoring", jPanel38);

        jPanel39.setBackground(new java.awt.Color(173, 182, 200));
        jPanel39.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Follow Up", jPanel39);

        jPanel40.setBackground(new java.awt.Color(173, 182, 200));

        jScrollPane60.setPreferredSize(new java.awt.Dimension(623, 582));

        jPanel43.setBackground(new java.awt.Color(173, 182, 200));

        jPanel8.setBackground(new java.awt.Color(173, 182, 200));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Second Opinion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        cbx_referral_doctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Please Select a Doctor --" }));

        jLabel12.setText("To Doctor : ");

        jLabel83.setText("Comment : ");

        txt_referral_comment.setColumns(20);
        txt_referral_comment.setRows(5);
        jScrollPane2.setViewportView(txt_referral_comment);

        jButton4.setText("Submit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbx_referral_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbx_referral_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(883, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(489, Short.MAX_VALUE))
        );

        jScrollPane60.setViewportView(jPanel43);

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane60, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane60, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Second Opinion", jPanel40);

        jPanel41.setBackground(new java.awt.Color(173, 182, 200));

        jPanel44.setBackground(new java.awt.Color(173, 182, 200));
        jPanel44.setPreferredSize(new java.awt.Dimension(623, 582));

        btn_ProcAcc.setText("Accept");
        btn_ProcAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcAccActionPerformed(evt);
            }
        });

        pnl_procedure_1.setBackground(new java.awt.Color(173, 182, 200));
        pnl_procedure_1.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedure"));

        jScrollPane54.setViewportView(tree_procedure);

        javax.swing.GroupLayout pnl_procedure_1Layout = new javax.swing.GroupLayout(pnl_procedure_1);
        pnl_procedure_1.setLayout(pnl_procedure_1Layout);
        pnl_procedure_1Layout.setHorizontalGroup(
            pnl_procedure_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_procedure_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane54, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_procedure_1Layout.setVerticalGroup(
            pnl_procedure_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_procedure_1Layout.createSequentialGroup()
                .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_procedure_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ProcAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btn_ProcAcc)
                .addContainerGap(540, Short.MAX_VALUE))
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(pnl_procedure_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane62.setViewportView(jPanel44);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jScrollPane62, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane62, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Procedure", jPanel41);

        jPanel42.setBackground(new java.awt.Color(173, 182, 200));
        jPanel42.setPreferredSize(new java.awt.Dimension(623, 582));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Admit to Ward", jPanel42);

        jPanel67.setBackground(new java.awt.Color(173, 182, 200));

        jPanel70.setBackground(new java.awt.Color(173, 182, 200));

        jPanel72.setBackground(new java.awt.Color(173, 182, 200));
        jPanel72.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medical Certification (MC) and Time Slip", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        date_from.setDateFormatString("dd/MM/yyyy");

        jLabel19.setText("Date From : ");

        jLabel23.setText("Date To : ");

        date_to.setDateFormatString("dd/MM/yyyy");

        btn_generate_mc.setText("Generate MC");
        btn_generate_mc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generate_mcActionPerformed(evt);
            }
        });

        MC_accptBtn.setText("Accept");
        MC_accptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MC_accptBtnActionPerformed(evt);
            }
        });

        MC_clrBtn.setText("Clear");
        MC_clrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MC_clrBtnActionPerformed(evt);
            }
        });

        btn_generate_timeslip.setText("Generate Time Slip");
        btn_generate_timeslip.setEnabled(false);
        btn_generate_timeslip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generate_timeslipActionPerformed(evt);
            }
        });

        jPanel85.setBackground(new java.awt.Color(173, 182, 200));

        rbtn_mcts_mc.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_grp_mcts.add(rbtn_mcts_mc);
        rbtn_mcts_mc.setSelected(true);
        rbtn_mcts_mc.setText("MC");
        rbtn_mcts_mc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtn_mcts_mcMouseClicked(evt);
            }
        });

        rbtn_mcts_ts.setBackground(new java.awt.Color(173, 182, 200));
        rbtn_grp_mcts.add(rbtn_mcts_ts);
        rbtn_mcts_ts.setText("Time Slip");
        rbtn_mcts_ts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtn_mcts_tsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtn_mcts_mc)
                    .addComponent(rbtn_mcts_ts))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtn_mcts_mc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtn_mcts_ts)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        time_from.setEnabled(false);

        time_to_hour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00" }));

        time_to_minute.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00" }));

        time_to_second.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00" }));

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_generate_mc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel72Layout.createSequentialGroup()
                                .addComponent(MC_accptBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MC_clrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_generate_timeslip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(date_to, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(date_from, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel72Layout.createSequentialGroup()
                                .addComponent(time_to_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time_to_minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time_to_second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(time_from))))
                .addContainerGap(372, Short.MAX_VALUE))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(date_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(time_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(time_to_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(time_to_minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(time_to_second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MC_accptBtn)
                            .addComponent(MC_clrBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(btn_generate_mc)
                        .addGap(12, 12, 12)
                        .addComponent(btn_generate_timeslip))
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );

        jScrollPane30.setViewportView(jPanel70);

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("MC & Time Slip", jPanel67);

        jTabbedPane7.setSelectedIndex(2);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Plan", jPanel17);

        tbl_note_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Note", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_note_1.setShowHorizontalLines(false);
        tbl_note_1.setShowVerticalLines(false);
        tbl_note_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_note_1MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbl_note_1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_discharge.setText("Discharge");
        btn_discharge.setEnabled(false);
        btn_discharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dischargeActionPerformed(evt);
            }
        });

        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_hold.setText("On Hold");
        btn_hold.setEnabled(false);
        btn_hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_holdActionPerformed(evt);
            }
        });

        btn_missing.setText("Missing");
        btn_missing.setEnabled(false);
        btn_missing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_missingActionPerformed(evt);
            }
        });

        jButton3.setText("Print Prescription Slip");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel76.setBackground(new java.awt.Color(204, 204, 255));

        lbl_pName.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pName.setText("Name:");

        txt_pName.setEditable(false);
        txt_pName.setBackground(new java.awt.Color(204, 204, 255));
        txt_pName.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_pName.setBorder(null);
        txt_pName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pNameActionPerformed(evt);
            }
        });

        txt_pPmiNo.setEditable(false);
        txt_pPmiNo.setBackground(new java.awt.Color(204, 204, 255));
        txt_pPmiNo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pPmiNo.setBorder(null);

        lbl_pPmiNo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pPmiNo.setText("PMI No:");

        lbl_pIcNo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pIcNo.setText("New IC No:");

        txt_pIcNo.setEditable(false);
        txt_pIcNo.setBackground(new java.awt.Color(204, 204, 255));
        txt_pIcNo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pIcNo.setBorder(null);

        lbl_pRace.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pRace.setText("Race:");

        txt_pRace.setEditable(false);
        txt_pRace.setBackground(new java.awt.Color(204, 204, 255));
        txt_pRace.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pRace.setBorder(null);

        lbl_pSex.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pSex.setText("Gender:");

        txt_pSex.setBackground(new java.awt.Color(255, 255, 255));
        txt_pSex.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pSex.setPreferredSize(new java.awt.Dimension(40, 14));

        lbl_pBdate.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pBdate.setText("Birth Date:");

        lblDOB.setBackground(new java.awt.Color(255, 255, 255));
        lblDOB.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblDOB.setPreferredSize(new java.awt.Dimension(40, 14));

        lbl_pStatus.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pStatus.setText("Marital Status:");

        txt_pStatus.setEditable(false);
        txt_pStatus.setBackground(new java.awt.Color(204, 204, 255));
        txt_pStatus.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pStatus.setBorder(null);

        lbl_pBloodSex.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pBloodSex.setText("Blood Type:");

        txt_pBloodSex.setEditable(false);
        txt_pBloodSex.setBackground(new java.awt.Color(204, 204, 255));
        txt_pBloodSex.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_pBloodSex.setBorder(null);

        lbl_pBloodSex1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_pBloodSex1.setText("G6PD Status:");

        lbl_g6pd.setEditable(false);
        lbl_g6pd.setBackground(new java.awt.Color(204, 204, 255));
        lbl_g6pd.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_g6pd.setBorder(null);

        jLabel14.setText("Allergy:");

        cbx_allergy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- No Allergy --" }));

        lbl_time.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_time.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_time.setText("jLabel52");

        lbl_date.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_date.setText("jLabel53");

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_pName)
                            .addComponent(lbl_pPmiNo))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_pPmiNo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pName, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_pRace)
                            .addComponent(lbl_pIcNo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pIcNo)
                            .addComponent(txt_pRace))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_pSex)
                            .addComponent(lbl_pBdate))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(txt_pSex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(lbl_pStatus)
                        .addGap(1, 1, 1)
                        .addComponent(txt_pStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(lbl_pBloodSex)
                        .addGap(18, 18, 18)
                        .addComponent(txt_pBloodSex)))
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(lbl_pBloodSex1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_allergy, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_g6pd, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel76Layout.createSequentialGroup()
                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_time, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_time)
                        .addContainerGap())
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel76Layout.createSequentialGroup()
                                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_pName)
                                    .addComponent(txt_pName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_pPmiNo)
                                    .addComponent(txt_pPmiNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_pRace)
                                    .addGroup(jPanel76Layout.createSequentialGroup()
                                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_pIcNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_pIcNo)
                                            .addComponent(lbl_pStatus)
                                            .addComponent(txt_pStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_pRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_pBloodSex)
                                            .addComponent(txt_pBloodSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel76Layout.createSequentialGroup()
                                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_pSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_pSex)
                                    .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_pBloodSex1)
                                        .addComponent(lbl_g6pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_pBdate)
                                    .addComponent(jLabel14)
                                    .addComponent(cbx_allergy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );

        lbl_new_patient.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_new_patient.setForeground(new java.awt.Color(255, 0, 0));
        lbl_new_patient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_new_patient.setText("* New Patient *");

        btn_viewHistory.setText("View History");
        btn_viewHistory.setEnabled(false);
        btn_viewHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(btn_sPatient)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_hold, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_missing, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_viewHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(115, 115, 115)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_discharge, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(108, 108, 108)
                            .addComponent(btn_exit))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_new_patient, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_discharge)
                    .addComponent(btn_sPatient)
                    .addComponent(btn_exit)
                    .addComponent(btn_next)
                    .addComponent(btn_hold)
                    .addComponent(btn_missing)
                    .addComponent(jButton3)
                    .addComponent(btn_viewHistory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_new_patient)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        MItem_local.setSelected(true);
        MItem_local.setText("Save to Local Database");
        jMenu1.add(MItem_local);

        MItem_drives.setSelected(true);
        MItem_drives.setText("Save to Drives");
        jMenu1.add(MItem_drives);

        menuCheckBluetooth.setSelected(true);
        menuCheckBluetooth.setText("Save to Bluetooth");
        jMenu1.add(menuCheckBluetooth);
        jMenu1.add(jSeparator7);

        MItem_Import.setText("Import Data");
        MItem_Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MItem_ImportActionPerformed(evt);
            }
        });
        jMenu1.add(MItem_Import);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private Vector<Vector<String>> data;
    private Vector<String> header;    String tsttab1s[] = new String[100];
    int q1s = 0;
    String compsub[][] = new String[100][9];
    String tsttab2[] = new String[100];
    int q = 0;
    String diagsub[][] = new String[100][14];
    String tsttab3[] = new String[100];
    int q1 = 0;
    String immsub[][] = new String[100][4];
    String tsttab4[] = new String[100];
    int q2 = 0;
    String vtssub[][] = new String[100][7];
    String tsttab5[] = new String[100];
    int q3 = 0;
    String drgsub[][] = new String[100][9];
    int drgsub1[][] = new int[100][4];    int i = 0;
    int a1 = 0;
    int i1 = 0;
    private void btn_dUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dUpdateActionPerformed
        // TODO add your handling code here:
        v = v + 1;//complited for tab 2,3,4,5 pls refer
        jFrame3.dispose();
        System.out.println("Value of V: " + v);
}//GEN-LAST:event_btn_dUpdateActionPerformed

    private void btn_dDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dDeleteActionPerformed
        // TODO add your handling code here:
        //delete button
        String TabName = (String) jComboBox10.getSelectedItem();

        if ("C.Complaints".equals(TabName)) {
            System.out.println("C.Complaints");

            jTextArea7.setText("");

            int group1 = (Integer) jComboBox11.getSelectedItem();
            for (int m = 0; m < q1s; m++) {
                if (m == group1) {
                    tsttab1s[group1] = "";
                    System.out.println("after tsttab1s[" + group1 + "] = " + tsttab1s[group1] + "--\n");
                }
            }
            jTextArea12.setText("");
            for (int p1 = 0; p1 < q1s; p1++) {
                if ("".equals(tsttab1s[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea12.append(tsttab1s[p1]);
            }

            jTextArea7.append("\nC.Complaint\n" + jTextArea12.getText());
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();

            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }

            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Diagnosis".equals(TabName)) {
            System.out.println("Diagnosis");
            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }

            int group1 = (Integer) jComboBox11.getSelectedItem();
            System.out.println("Group 1 is : " + group1);

            //for delete in tab2
            for (int m = 0; m < q; m++) {
                System.out.println("before tsttab2[" + group1 + "] = " + tsttab2[group1] + "--\n");
                if (m == group1) {
                    tsttab2[group1] = "";
                    System.out.println("after tsttab2[" + group1 + "] = " + tsttab2[group1] + "--\n");
                }
            }
//            jTextArea9.setText("");
//            for (int p1 = 0; p1 < q; p1++) {
//                if ("".equals(tsttab2[p1])) {
//                    System.out.println("Got it");
//                    p1 = p1 + 1;
//                }
//
//                jTextArea9.append(tsttab2[p1]);
//
//            }
//
//            jTextArea7.append("\nDiagnosis\n" + jTextArea9.getText());
//            tab2s = "\nDiagnosis\n" + jTextArea9.getText();


            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }
            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Immunisation".equals(TabName)) {
            System.out.println("Immunisation");

            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }
            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q1; m++) {
                if (m == group1) {
                    tsttab3[group1] = "";
                }
            }
            jTextArea10.setText("");
            for (int p1 = 0; p1 < q1; p1++) {
                if ("".equals(tsttab3[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea10.append(tsttab3[p1]);

            }

            jTextArea7.append("\nImmunisation\n" + jTextArea10.getText());
            tab3s = "\nImmunization\n" + jTextArea10.getText();

            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Vital Sign".equals(TabName)) {
            System.out.println("Vital Sign");

            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }
            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }
            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q2; m++) {
                if (m == group1) {
                    tsttab4[group1] = "";
                }
            }
            //jTextArea11.setText("");
            for (int p1 = 0; p1 < q2; p1++) {
                if ("".equals(tsttab4[p1])) {
                    p1 = p1 + 1;
                }

                //jTextArea11.append(tsttab4[p1]);
            }

            //jTextArea7.append("\nVital Sign\n" + jTextArea11.getText());
            //tab4s = "\nVital Sign\n" + jTextArea11.getText();

            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Drugs".equals(TabName)) {
            System.out.println("Drugs");

            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }
            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }
            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q3; m++) {
                if (m == group1) {
                    tsttab5[group1] = "";
                }
            }
//            txt_drugList.setText("");
//            for (int p1 = 0; p1 < q3; p1++) {
//                if ("".equals(tsttab5[p1])) {
//                    p1 = p1 + 1;
//                }
//
//                txt_drugList.append(tsttab5[p1]);
//            }
//
//            jTextArea7.append("\nDrugs\n" + txt_drugList.getText());
//            tab5s = "\nDrugs\n" + txt_drugList.getText();

            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Disability".equals(TabName)) {
            System.out.println("Disability");

            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }
            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }
            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q4; m++) {
                if (m == group1) {
                    tsttab6[group1] = "";
                }
            }
            //jTextArea13.setText("");
            for (int p1 = 0; p1 < q4; p1++) {
                if ("".equals(tsttab6[p1])) {
                    p1 = p1 + 1;
                }

                //jTextArea13.append(tsttab6[p1]);
            }

            //jTextArea7.append("\nDisability\n" + jTextArea13.getText());
            //tab6s = "\nDisability\n" + jTextArea13.getText();

            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Allergy".equals(TabName)) {
            System.out.println("Allergy");

            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }
            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }
            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q5; m++) {
                if (m == group1) {
                    tsttab7[group1] = "";
                }
            }
//            jTextArea14.setText("");
//            for (int p1 = 0; p1 < q5; p1++) {
//                if ("".equals(tsttab7[p1])) {
//                    p1 = p1 + 1;
//                }
//
//                jTextArea14.append(tsttab7[p1]);
//            }
//
//            jTextArea7.append("\nAllergy\n" + jTextArea14.getText());
//            tab7s = "\nAllergy\n" + jTextArea14.getText();

            if (tab8 > 0) {
                jTextArea7.append(tab8s);
            }
        }

        if ("Social History".equals(TabName)) {
            System.out.println("Social History");

            jTextArea7.setText("");
            if (tab1 > 0) {
                jTextArea7.append(tab1s);
            }
            if (tab2 > 0) {
                jTextArea7.append(tab2s);
            }
            if (tab3 > 0) {
                jTextArea7.append(tab3s);
            }
            if (tab4 > 0) {
                jTextArea7.append(tab4s);
            }
            if (tab5 > 0) {
                jTextArea7.append(tab5s);
            }
            if (tab6 > 0) {
                jTextArea7.append(tab6s);
            }
            if (tab7 > 0) {
                jTextArea7.append(tab7s);
            }
            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q6; m++) {
                if (m == group1) {
                    tsttab8[group1] = "";
                }
            }
            //jTextArea15.setText("");
            for (int p1 = 0; p1 < q6; p1++) {
                if ("".equals(tsttab8[p1])) {
                    p1 = p1 + 1;
                }

                //jTextArea15.append(tsttab8[p1]);
            }

            //jTextArea7.append("\nSocial Problem\n" + jTextArea15.getText());
            //tab8s = "\nSocial Problem\n" + jTextArea15.getText();
        }
    }//GEN-LAST:event_btn_dDeleteActionPerformed
//Combo box to select category number
    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox11ActionPerformed

        String TabName = (String) jComboBox10.getSelectedItem();
        int rowcount = jComboBox11.getItemCount();

        if ("C.Complaints".equals(TabName) && rowcount > 0) {
            System.out.println("C.Complaints");

            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q1s; m++) {
                if (m == group1) {
                    txt_complaintSearch.setText(compsub[group1][0]);
                    cbx_cSeverity.setSelectedItem(compsub[group1][1]);
                    cbx_site.setSelectedItem(compsub[group1][2]);
                    txt_duration.setText(compsub[group1][3]);
                    cbx_duration.setSelectedItem(compsub[group1][4]);
                    cbx_laterality.setSelectedItem(compsub[group1][5]);
                    txt_complaintComment.setText(compsub[group1][6]);
//                    if ("Active".equals(compsub[group1][7])) {
//                        rbtn_active.setSelected(true);
//                    } else {
//                        rbtn_inactive.setSelected(true);
//                    }
                }
            }
        }
        if ("Diagnosis".equals(TabName) && rowcount > 0) {
            System.out.println("Diagnosis");

            int group2 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q; m++) {
                if (m == group2) {
                    Date date = null;
                    txt_diagnosisSearch.setText(diagsub[group2][0]);
                    cbx_dSeverity.setSelectedItem(diagsub[group2][1]);

                    String stringDate = diagsub[group2][2];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = sdf.parse(diagsub[group2][2]);
                        txt_date1.setDate(date);
                    } catch (Exception e) {
                    }
                }
            }
        }
        if ("Immunisation".equals(TabName) && rowcount > 0) {
            System.out.println("Immunisation");

            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q1; m++) {
                if (m == group1) {
                    Date date = null;
                    txt_immSearch.setText(immsub[group1][0]);
                    txt_immComment.setText(immsub[group1][1]);

                    String stringDate = immsub[group1][2];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = sdf.parse(immsub[group1][2]);
                        txt_immDate1.setDate(date);
                    } catch (Exception e) {
                    }
                }
            }
        }

        if ("Vital Sign".equals(TabName) && rowcount > 0) {
            System.out.println("Vital Sign");

            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q2; m++) {
                if (m == group1) {
                    txt_height.setText(vtssub[group1][0]);
                    txt_weight.setText(vtssub[group1][1]);
                    txt_headCircumference.setText(vtssub[group1][4]);
                    txt_temperature.setText(vtssub[group1][5]);
                    txt_pulse.setText(vtssub[group1][6]);
                    txt_bmi.setText(vtssub[group1][2]);
                    txt_weightStatus.setText(vtssub[group1][3]);
                }
            }

        }

        if ("Drugs".equals(TabName) && rowcount > 0) {
            System.out.println("Drugs");
            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q3; m++) {
                if (m == group1) {
                    txt_drugNameOListSearch.setText(drgsub[group1][0]);
//                    txt_dosageOList.setText(drgsub[group1][1]);
                    txt_quantityOList.setText(drgsub[group1][2]);
                    cb_durationOList.setSelectedItem(drgsub[group1][3]);
                    cb_frequencyOList.setSelectedIndex(drgsub1[group1][0]);
                    cb_instructionOList.setSelectedIndex(drgsub1[group1][1]);
                    //lbx_productNameUStockSearch.setSelectedIndex(drgsub1[group1][2]);
                    //lbx_drugSearch.setSelectedIndex(drgsub1[group1][3]);

                    javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
                    listModel.addElement(drgsub[group1][6]);
                    //lst_productNameOList.setModel(listModel);

                    txt_dosageFormOList.setText(drgsub[group1][7]);
                    cb_durationTypeOList.setSelectedItem(drgsub[group1][8]);
                }
            }
        }

        if ("Disability".equals(TabName) && rowcount > 0) {
            System.out.println("Disability");
            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q4; m++) {
                if (m == group1) {
                    Date date = null;
                    txt_disabilityType.setText(disabilitysub[group1][0]);
                    txt_dComments.setText(disabilitysub[group1][1]);
                    //==--txt_dDate.setText(disabilitysub[group1][2]);//==--
                    String stringDate = disabilitysub[group1][2];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = sdf.parse(disabilitysub[group1][2]);
                        txt_dDate1.setDate(date);
                    } catch (Exception e) {
                    }
                }
            }
        }

        if ("Allergy".equals(TabName) && rowcount > 0) {
            System.out.println("Allergy");
            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q5; m++) {
                if (m == group1) {
                    Date date = null;
                    txt_allergySearch.setText(allergysub[group1][0]);
                    txt_allergyComments.setText(allergysub[group1][1]);
                    //txt_allergyDate.setText(allergysub[group1][2]);
                    String stringDate = allergysub[group1][2];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = sdf.parse(allergysub[group1][2]);
                        txt_allergyDate2.setDate(date);
                    } catch (Exception e) {
                    }
                }
            }
        }


        if ("Social History".equals(TabName) && rowcount > 0) {
            System.out.println("Social History");
            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q6; m++) {
                if (m == group1) {
                    Date date = null;
                    txt_socialProblem.setText(socialsub[group1][0]);
                    txt_socialComment.setText(socialsub[group1][1]);
                    //txt_socialDate.setText(socialsub[group1][2]);
                    String stringDate = socialsub[group1][2];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = sdf.parse(socialsub[group1][2]);
                        txt_socialDate1.setDate(date);
                    } catch (Exception e) {
                    }
                }
            }
        }

}//GEN-LAST:event_jComboBox11ActionPerformed
//combo box to select tab type
    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
        String TabName = (String) jComboBox10.getSelectedItem();

        if ("C.Complaints".equals(TabName)) {
            jComboBox11.removeAllItems();

            for (int m = 0; m < q1s; m++) {
                if ("".equals(tsttab1s[m])) {
                } else {
                    jComboBox11.addItem(m);
                    System.out.println("see the value : " + m);
                }
            }
            jTabbedPane4.setSelectedIndex(0);
        }
        if ("Diagnosis".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q; m++) {
                if ("".equals(tsttab2[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(1);
        }
        if ("Immunisation".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q1; m++) {
                if ("".equals(tsttab3[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(2);
        }
        if ("Vital Sign".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q2; m++) {
                if ("".equals(tsttab4[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(3);
        }
        if ("Drugs".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q3; m++) {
                if ("".equals(tsttab5[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(4);
        }
        if ("Disability".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q4; m++) {
                if ("".equals(tsttab6[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(5);
        }
        if ("Allergy".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q5; m++) {
                if ("".equals(tsttab7[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(6);
        }
        if ("Social History".equals(TabName)) {
            jComboBox11.removeAllItems();
            for (int m = 0; m < q6; m++) {
                if ("".equals(tsttab8[m])) {
                } else {
                    jComboBox11.addItem(m);
                }
            }
            jTabbedPane4.setSelectedIndex(7);
        }
    }//GEN-LAST:event_jComboBox10ActionPerformed

    public String getSeverity(String severe) {
        if(severe.contains("Mild")) {
            return "01";
        } else if(severe.contains("Moderate")) {
            return "02";
        } else if(severe.contains("Severe")) {
            return "03";
        } else {
            return "00";
        }
    }
    
    public String getSide(String side) {
        if (side.contains("Right")) {
            return "01";
        } else if (side.contains("Left")) {
            return "02";
        } else {
            return "00";
        }
    } 
    
    public String getLaterality(String laterality) {
        if (laterality.contains("Right")) {
            return "01";
        } else if (laterality.contains("Left")) {
            return "02";
        } else {
            return "00";
        }
    }
    
    protected String getDrugQuantity(String x, String y, String z) {
        int t = 0;
        try {
            int a = Integer.parseInt(x);
            int b = Integer.parseInt(y);
            int c = Integer.parseInt(z);
            t = a*b*c;
        } catch (Exception e) {
        }
        return String.valueOf(t);
    }
    
    protected String getDayDrugCode(String duration) {
        int val = 0;
        try {
            String durData[] = duration.split(" ");
            val = Integer.parseInt(durData[0]);
            //calculate duration in day
            if (durData[1].equals("Week")) {
                val *= 7;
            } else if (durData[1].equals("Month")) {
                val *= 30;
            }
        } catch (Exception e) {
        }
        return String.valueOf(val);
    }
    
    protected static String getFrequencyCode(String frequency) {
        String str = "0";
        if ((frequency.equals("In the morning")) || (frequency.equals("At night")) || (frequency.equals("Daily"))) {
            str = "1";
        } else if (frequency.equals("Twice a day")) {
            str = "2";
        } else if ((frequency.equals("3 times a day")) || (frequency.equals("8 hourly"))) {
            str = "3";
        } else if ((frequency.equals("4 times a day")) || (frequency.equals("6 hourly"))) {
            str = "4";
        } else {
            str = "6";
        }
        return str;
    }
    
    private String checkNull(Object str) {
        System.out.println("....check str...:" + str);
        if (str == null) {
            str = "";
        }
        return (String) str;
    }

    private void setCalcBMI(double height, double weight) {
        double total = Math.round((weight / (height * height)) * 10000);
        txt_bmi.setText(total + "");
        txt_weightStatus.setText(calcBMI(height, weight));
    }
    
    private String calcBMI(double height, double weight) {
        double total = calcBMI_Math(height, weight);
        if (total < 18.5) {
            return "Underweight";
        } else if (total <= 23.9) {
            return "Healthy weight";
        } else if (total <= 26.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
    
    private double calcBMI_Math(double height, double weight) {
        return Math.round((weight / (height * height)) * 10000);
    }
    
    private void accept_button_pem() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_physical_exam.getLastSelectedPathComponent();
        if (node == null) {
            J.o("Not Selected", "Please select the physical examination!", 0);
        } else {
            String pe_name = (String) node.getUserObject();
            
            String pe = Func.getPhysicalExaminationLink(pe_name);
            String comments = txt_pe_comments.getText();
            txt_pe_comments.setText("");
            
            String data[] = {"Physical Examination",
                "Physical Exam Name: " + Func.trim(pe),
                "Comments: " + Func.trim(comments)
            };
            setData(data, 14); //14 for PEM
        }
//        String data[] = {"Physical Examination",
//            "Throat: " + Func.trim(txt_tekak.getText()),
//            "Heart: " + Func.trim(txt_jantung.getText()),
//            "Right Lung: " + Func.trim(txt_peparu_kanan.getText()),
//            "Right Shoulder: " + Func.trim(txt_bahu_kanan.getText()),
//            "Left Shoulder: " + Func.trim(txt_bahu_kiri.getText()),
//            "Stomache: " + Func.trim(txt_perut.getText()),
//            "Head: " + Func.trim(txt_kepala.getText()),
//            "Nose: " + Func.trim(txt_hidung.getText()),
//            "Mouth: " + Func.trim(txt_mulut.getText()),
//            "Right Ear: " + Func.trim(txt_telinga_kanan.getText())
//        };
//        setData(data, 14); //14 for Physical Exam
//        
//        txt_tekak.setText("");
//        txt_jantung.setText("");
//        txt_peparu_kanan.setText("");
//        txt_bahu_kanan.setText("");
//        txt_bahu_kiri.setText("");
//        txt_perut.setText("");
//        txt_kepala.setText("");
//        txt_hidung.setText("");
//        txt_mulut.setText("");
//        txt_telinga_kanan.setText("");
    }
    
    private void accept_button_vts() {
        jTextArea7.setText("");

        String hei = txt_height.getText();
        String wei = txt_weight.getText();
        String hea = txt_headCircumference.getText();
        String tem = txt_temperature.getText();
        String pul = txt_pulse.getText();
        String pul1 = txt_pulse1.getText();
        String pul2 = txt_pulse2.getText();
        String bmi = txt_bmi.getText();
        String sta = txt_weightStatus.getText();
        String sys = txt_systolic.getText();
        String dis = txt_diastolic.getText();
        String sys1 = txt_systolic1.getText();
        String dis1 = txt_diastolic1.getText();
        String sys2 = txt_systolic2.getText();
        String dis2 = txt_diastolic2.getText();
        
        String gcs_result = txt_gcs_result.getText();
        String gcs_points = txt_gcs_points.getText();
        String pgcs_result = txt_pgcs_result.getText();
        String pgcs_points = txt_pgcs_points.getText();
        String respiratory_rate = txt_respiratory_rate.getText();
        String oxygen_saturation = txt_oxygen_saturation.getText();
        String pain_scale = (String) cbx_pain_scale.getSelectedItem();
        
        String blood_glucose = txt_blood_glucose.getText();

        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            //tab2s = "\nDiagnosis\n" + jTextArea9.getText();
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nImmunization\n" + jTextArea10.getText();
            jTextArea7.append(tab3s);
        }
        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab4 > 0 || tab4 == 0) {
//                jTextArea11.append("\n" + (q2+1) + ") " + 
//                        "Height: " + hei + " cm, "
//                        + "Weight: " + wei + " kg, "
//                        + "BMI: " + bmi + " kg/m2,\n"
//                        + "Status: " + sta + ", "
//                        + "Head circumference: " + hea + " cm,\n"
//                        + "Temp: " + tem + " celcius, "
//                        + "Pulse: " + pul + "/min" + "\n");
                tab4s = "\n" + (q2+1) + ") " + "Height: " + hei + " cm, Weight: " + wei + " kg, BMI: " + bmi + " kg/m2,\nStatus: " + sta + ", Head circumference: " + hea + " cm,\nTemp: " + tem + " celcius, Pulse: " + pul + "/min" + "\n";
                tsttab4[q2] = tab4s;
                //jTextArea7.append("\nVital Sign\n" + jTextArea11.getText());
                
                String data[] = {"Vital Sign",
                    "GCS Points: " + Func.trim(gcs_points),
                    "GCS Result: " + Func.trim(gcs_result),
                    "PGCS Points: " + Func.trim(pgcs_points),
                    "PGCS Result: " + Func.trim(pgcs_result),
                    "Respiratory Rate: " + Func.trim(respiratory_rate),
                    "Oxygen Saturation: " + Func.trim(oxygen_saturation),
                    "Pain Scale: " + Func.trim(pain_scale),
                    "Height: " + Func.trim(hei) + " cm",
                    "Weight: " + Func.trim(wei) + " kg",
                    "BMI: " + Func.trim(bmi) + " kg/m2",
                    "Status: " + Func.trim(sta),
                    "Head circumference: " + Func.trim(hea) + " cm",
                    "Temp: " + Func.trim(tem) + " celcius",
                    "Systolic Sitting: " + Func.trim(sys) + " mmHg",
                    "Diastolic Sitting: " + Func.trim(dis) + " mmHg",
                    "Pulse: " + Func.trim(pul) + " /min",
                    "Systolic Standing: " + Func.trim(sys1) + " mmHg",
                    "Diastolic Standing: " + Func.trim(dis1) + " mmHg",
                    "Pulse: " + Func.trim(pul1) + " /min",
                    "Systolic Lying: " + Func.trim(sys2) + " mmHg",
                    "Diastolic Lying: " + Func.trim(dis2) + " mmHg",
                    "Pulse: " + Func.trim(pul2) + " /min",
                    "Blood Glucose: " + Func.trim(blood_glucose)
                };
                setData(data, 10); //10 for Vital Sign

                //to retrieve update value
                vtssub[q2][0] = hei;
                vtssub[q2][1] = wei;
                vtssub[q2][2] = bmi;
                vtssub[q2][3] = sta;
                vtssub[q2][4] = hea;
                vtssub[q2][5] = tem;
                vtssub[q2][6] = pul;
                //to retrieve update value
                q2++;
            }
            tab4 = tab4 + 1;
            //tab4s = "\nVital Sign\n" + jTextArea11.getText();
        } else {
            String update = "Height: " + hei + " cm, Weight: " + wei + " kg, BMI: " + bmi + " kg/m2,\nStatus: " + sta + ", Head circumference: " + hea + " cm,\nTemp: " + tem + " celcius, Pulse: " + pul + "/min" + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int m = 0; m < q2; m++) {
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
                    tsttab4[group1] = "\n" + m + ") " + update;
                }
            }
            //jTextArea11.setText("");
            for (int p1 = 0; p1 < q2; p1++) {
                if ("".equals(tsttab4[p1])) {
                    p1 = p1 + 1;
                }

                //jTextArea11.append(tsttab4[p1]);

            }

            //jTextArea7.append("\nVital Sign\n" + jTextArea11.getText());
            //tab4s = "\nVital Sign\n" + jTextArea11.getText();
            v = v - 1;
        }
        //**************************Print Existing Value*****************************
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }
        //**************************Print Existing Value*****************************
        txt_height.setText("");
        txt_weight.setText("");
        txt_headCircumference.setText("");
        txt_temperature.setText("");
        txt_pulse.setText("");
        txt_pulse1.setText("");
        txt_pulse2.setText("");
        txt_bmi.setText("");
        txt_weightStatus.setText("");
        txt_systolic.setText("");
        txt_diastolic.setText("");
        txt_systolic1.setText("");
        txt_diastolic1.setText("");
        txt_systolic2.setText("");
        txt_diastolic2.setText("");
    }
    
    
    private void accept_btn_imu() {
        jTextArea7.setText("");
        String immunType = (String) txt_immSearch.getText();
        
        if (!Searching.isSearchImmune(immunType)) {
            J.o("Invalid", "Invalid Immunisation code!", 0);
            return;
        }
        
        String cmmt = txt_immComment.getText();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String immDate;
        try {
            immDate = formatter.format(txt_immDate1.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }
        String immID;
        String immSnomedID = "";
        
        String data[] = {"Immunisation",
            "Type: " + Func.trim(immunType),
            "Comment: " + Func.trim(cmmt),
            "Date: " + Func.trim(immDate)
        };
        setData(data, 8); //8 for Immunisation

        try {
            tempQuery = "SELECT SNOMEDID FROM SNOMEDIMMUNISATION where SNOMEDDESC like '%" + immunType + "%' ";
            rs = st.executeQuery(tempQuery);
            while (rs.next()) {
                immID = rs.getString("SNOMEDID");
                //System.out.println(immID);
                immSnomedID = immID;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        //System.out.println(SnomedID);

        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            //tab2s = "\nDiagnosis\n" + jTextArea9.getText();
            jTextArea7.append(tab2s);
        }
        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab3 > 0 || tab3 == 0) {
                jTextArea10.append("\n" + (q1 + 1) + ") "
                        + "Type: " + immunType
                        + ",\nComment: " + cmmt
                        + ", Date : " + immDate + "\n");
                tab3s = "\n" + (q1 + 1) + ") " + "Type: " + immunType + ",\nComment: " + cmmt + ", Date : " + immDate + "\n";
                tsttab3[q1] = tab3s;
                jTextArea7.append("\nImmunization\n" + jTextArea10.getText());

                //to retrieve update value
                immsub[q1][0] = immSnomedID;
                immsub[q1][1] = immunType;
                immsub[q1][2] = immDate;
                immsub[q1][3] = cmmt;

                //to retrieve update value
                q1++;
            }

            tab3 = tab3 + 1;
            tab3s = "\nImmunization\n" + jTextArea10.getText();
        } else {

            String update = "Type: " + immunType + ",\nComment: " + cmmt + "Date : " + immDate + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q1; m++) {
                if (m == group1) {
                    //to update value
                    immsub[group1][0] = immSnomedID;
                    immsub[group1][1] = immunType;
                    immsub[group1][2] = immDate;
                    immsub[group1][3] = cmmt;
                    //to update value
                    tsttab3[group1] = "\n" + m + ") " + update;
                }
            }
            jTextArea10.setText("");
            for (int p1 = 0; p1 < q1; p1++) {
                if ("".equals(tsttab3[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea10.append(tsttab3[p1]);

            }

            jTextArea7.append("\nImmunisation\n" + jTextArea10.getText());
            tab3s = "\nImmunization\n" + jTextArea10.getText();
            v = v - 1;
        }
        //**************************Print Existing Value*****************************
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }
        //**************************Print Existing Value*****************************

        txt_immSearch.setText("");
        txt_immComment.setText("");
        txt_immDate1.setDate(null);

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_immSearch.setModel(listModel);
    }
    private void accept_btn_dgs() {
        jTextArea7.setText("");

        String diagType = (String) txt_diagnosisSearch.getText();
        
        if (!Searching.isSearchDiagnosis(diagType)) {
            J.o("Invalid", "Invalid Diagnosis code!", 0);
            return;
        }

        String sev = (String) cbx_dSeverity.getSelectedItem();

        String sevCd = "";
        if (sev == "Mild") {
            sevCd = "sv001";
        }
        if (sev == "Normal") {
            sevCd = "sv002";
        }
        if (sev == "Acute") {
            sevCd = "sv003";
        }

        String diagTypeRBtn = "";
        String diagTypeCd = "";
        if (jRadioButton1.isSelected()) {
            diagTypeRBtn = "Final";
            diagTypeCd = "tp001";
        }
        if (jRadioButton2.isSelected()) {
            diagTypeRBtn = "Provisional";
            diagTypeCd = "tp002";
        }

        String diagStatus = "Active";
        String diagStatuscd = "st001";
//        if (rbtn_active.isSelected()) {
//            diagStatus = "Active";
//            diagStatuscd = "st001";
//        }
//        if (rbtn_inactive.isSelected()) {
//            diagStatus = "Inactive";
//            diagStatuscd = "st002";
//        }

        String lat = (String) dbx_laterality.getSelectedItem();
        String latCd = "";
        if (lat == "Right") {
            latCd = "lR";
        }
        if (lat == "Left") {
            latCd = "lL";
        }

        String site = (String) dbx_site.getSelectedItem();
        String siteCd = "";
        if (lat == "Right") {
            siteCd = "sR";
        }
        if (lat == "Left") {
            siteCd = "sL";
        }

        String cmnt = txt_diagComment.getText();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String diagnosis_date;
        try {
            diagnosis_date = formatter.format(txt_date1.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }

        String dSnomedId = "";
        try {
            tempQuery = "SELECT SNOMEDID "
                    + "FROM SNOMEDDIAGNOSIS "
                    + "WHERE SNOMEDDESC "
                    + "LIKE '%" + diagType + "%' ";
            rs = st.executeQuery(tempQuery);
            while (rs.next()) {
                String ID = rs.getString("SNOMEDID");
                //System.out.println(ID);
                dSnomedId = ID;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea7.append(tab1s);
        }
        //**************************Print Existing Value*****************************
        if (v == 0) {

            if (tab2 > 0 || tab2 == 0) {
                jTextArea7.append("\nDiagnosis\n");

//                jTextArea9.append("\n" + (q + 1) + ") "
//                        + "Type: " + diagType + ", "
//                        + "Severity: " + sev + ", "
//                        + "Site: " + site + ", "
//                        + "Laterality: " + lat + ", "
//                        + "Comment: " + cmnt + ", "
//                        + "Date: " + diagnosis_date + "\n");
//                tab2s = "\n" + (q + 1) + ") " + "Type: " + diagType + ", Severity: " + sev + ", Site: " + site + ", Laterality: " + lat + ", Comment: " + cmnt + ", Date: " + diagnosis_date + "\n";
//                tsttab2[q] = tab2s;
//                jTextArea7.append(jTextArea9.getText());

                String data[] = {"Diagnosis",
                    "Type: " + Func.trim(diagTypeRBtn),
                    "Type Code: " + Func.trim(diagTypeCd),
                    "Diagnosis: " + Func.trim(diagType),
                    "Severity: " + Func.trim(sev),
                    "Site: " + Func.trim(site),
                    "Laterality: " + Func.trim(lat),
                    "Comment: " + Func.trim(cmnt),
                    "Date: " + Func.trim(diagnosis_date)
                };
                setData(data, 11); //11 for Diagnosis

                //to retrieve update value
//                diagsub[q][0] = diagType;
//                diagsub[q][1] = sev;
//                diagsub[q][2] = diagnosis_date;

                diagsub[q][0] = diagTypeCd;
                diagsub[q][1] = diagTypeRBtn;
                diagsub[q][2] = diagStatus;
                diagsub[q][3] = diagnosis_date;
                diagsub[q][4] = dSnomedId;
                diagsub[q][5] = diagType;
                diagsub[q][6] = sevCd;
                diagsub[q][7] = sev;
                diagsub[q][8] = siteCd;
                diagsub[q][9] = site;
                diagsub[q][10] = latCd;
                diagsub[q][11] = lat;
                diagsub[q][12] = cmnt;
                //to retrieve update value
                q++;
            }

            tab2 = tab2 + 1;
            //tab2s = "\nDiagnosis\n" + jTextArea9.getText();
        } else {
            String update = "Type: " + diagType + ", Severity: " + sev + ", Site: " + site + ", Laterality: " + lat + ", Comment: " + cmnt + ", Date: " + diagnosis_date + "\n";

            System.out.println("Diagnosis");

            int group1 = (Integer) jComboBox11.getSelectedItem();

            for (int m = 0; m < q; m++) {
                if (m == group1) {
                    //to update value
                    diagsub[group1][0] = diagTypeCd;
                    diagsub[group1][1] = diagTypeRBtn;
                    diagsub[group1][2] = diagTypeRBtn;
                    diagsub[group1][3] = diagStatus;
                    diagsub[group1][4] = diagnosis_date;
                    diagsub[group1][5] = dSnomedId;
                    diagsub[group1][6] = diagType;
                    diagsub[group1][7] = sevCd;
                    diagsub[group1][8] = sev;
                    diagsub[group1][9] = siteCd;
                    diagsub[group1][10] = site;
                    diagsub[group1][11] = latCd;
                    diagsub[group1][12] = lat;
                    diagsub[group1][13] = cmnt;

//                    
//                    diagsub[group1][0] = diagType;
//                    diagsub[group1][1] = sev;
//                    diagsub[group1][2] = diagnosis_date;
                    //to update value
                    tsttab2[group1] = "\n" + m + ") " + update;
                }
            }
//            jTextArea9.setText("");
//            for (int p1 = 0; p1 < q; p1++) {
//                if ("".equals(tsttab2[p1])) {
//                    p1 = p1 + 1;
//                }
//
//                jTextArea9.append(tsttab2[p1]);
//
//            }
//
//            jTextArea7.append("\nDiagnosis\n" + jTextArea9.getText());
//            tab2s = "\nDiagnosis\n" + jTextArea9.getText();
            v = v - 1;
        }
        //**************************Print Existing Value*****************************
        if (tab3 > 0) {
            tab3s = "\nImmunization\n" + jTextArea10.getText();
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }
        //**************************Print Existing Value*****************************
        txt_diagnosisSearch.setText("");
        cbx_dSeverity.setSelectedIndex(0);
        txt_date1.setDate(null);

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_diagnosisSearch.setModel(listModel);
    }
    private void accept_button_HPI() {
        // TODO add your handling code here:

        jTextArea7.setText("");
        String hpi_details = HPI_DetailstxtArea.getText();
        String hpidata;

        if (tab1 > 0) {
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }

        if (v == 0) {
            if (tab9 > 0 || tab9 == 0) {
                jTextArea18.append("\n" + (q7+1) + ") " + "Details: " + hpi_details + "\n");
                tab9s = "\n" + (q7+1) + ") " + "Details: " + hpi_details + "\n";
                tsttab9[q7] = tab9s;
                jTextArea7.append("\nHPI\n" + jTextArea18.getText());
                
                String data[] = {"History Of Present Illness",
                    "Details: " + Func.trim(hpi_details)
                };
                setData(data, 2); //2 for HPI

                //to retrieve update value
                hpisub[q7][0] = hpi_details;

                //to retrieve update value
                q7++;
            }

            tab9 = tab9 + 1;
            tab9s = "\nHPI\n" + jTextArea18.getText();
        } else {

            String update = "Details: " + hpi_details + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q7; m++) {
                if (m == group1) {
                    //to update value
                    hpisub[group1][0] = hpi_details;
                    //to update value
                    tsttab9[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea18.setText("");
            for (int p1 = 0; p1 < q7; p1++) {
                if ("".equals(tsttab9[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea18.append(tsttab9[p1]);
            }

            jTextArea7.append("\nHPI\n" + jTextArea18.getText());
            tab9s = "\nHPI\n" + jTextArea18.getText();
            v = v - 1;
        }

        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }
        HPI_DetailstxtArea.setText("");
    }
    
    public static void setData(String data[], int tab_type) {
//        J.o("haha", ""+data.length, 1);
        tbl_note_1.getModel().setValueAt("", row_count, 0);
        tbl_note_1.getModel().setValueAt("", row_count, 1);
        note_array[row_count] = "";
        row_count++;
        for (int i = 0; i < data.length; i++) {
            String t[] = data[i].split(": ");
            tbl_note_1.getModel().setValueAt(t[0], row_count, 0);
            try {
                tbl_note_1.getModel().setValueAt(t[1], row_count, 1);
            } catch (Exception e) {
                tbl_note_1.getModel().setValueAt("", row_count, 1);
            }
            note_array[Consultation.row_count] = data[i];
            row_count++;
        }
        tbl_note_1.getModel().setValueAt("EDIT", row_count, 0);
        tbl_note_1.getModel().setValueAt("DELETE", row_count, 1);
        note_array[row_count] = data.length + "|" + tab_type;
        row_count++;
    }
    
    private void accept_button_ccomplaint() {
        jTextArea7.setText("");
        String cmmt = (String) txt_complaintComment.getText();
        String searchcbx = txt_complaintSearch.getText();
        if (!Searching.isSearchCCN1(searchcbx)) {
            J.o("Invalid", "Invalid Chief Complaint", 0);
            return;
        }
        String severity = (String) cbx_cSeverity.getSelectedItem();
        String site = (String) cbx_site.getSelectedItem();
        String durationtxt = (String) txt_duration.getText();
        String durationcbx = (String) cbx_duration.getSelectedItem();
        String lateralitycbx = (String) cbx_laterality.getSelectedItem();
        String ID;
        String SnomedID = "";

        String selectedRbtn = "Active";
//        if (rbtn_active.isSelected()) {
//            selectedRbtn = "Active";
//        }
//        if (rbtn_inactive.isSelected()) {
//            selectedRbtn = "In Active";
//        }

        try {
            //tempQuery = "SELECT SNOMEDID FROM SNOMEDCCOMPLAINTS where SNOMEDDESC like '%" + searchcbx + "%' ";
            tempQuery = "SELECT CCOMPLAINT_ID FROM CCOMPLAINT_TYPE where CCOMPLAINT_NAME like '%" + searchcbx + "%' ";
            rs = st.executeQuery(tempQuery);
            while (rs.next()) {
                ID = rs.getString("CCOMPLAINT_ID");
                //System.out.println(ID);
                SnomedID = ID;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        System.out.println("v: "+v);
        if (v == 0) {
            if (tab1 > 0 || tab1 == 0) {
                jTextArea7.append("\nC.Complaint\n");
                
                jTextArea12.append("\n" + (q1s+1) + ") " + "Problems: " 
                        + searchcbx + ", \n" + "Severity: " + severity 
                        + ", Site: " + site + ", Duration: " + durationtxt 
                        + " " + durationcbx + ",\nLaterality: " + lateralitycbx 
                        + ", Comments: " + cmmt + ";\n");
                
                String data[] = {"C.Complaint",
                    "Problems: " + Func.trim(searchcbx),
                    "Severity: " + Func.trim(severity),
                    "Site: " + Func.trim(site),
                    "Duration: " + Func.trim(durationtxt)+" "+Func.trim(durationcbx),
                    "Laterality: " + Func.trim(lateralitycbx),
                    "Comments: " + Func.trim(cmmt)
                };
                setData(data, 1); //1 for c.complaint
                
                tab1s = "\n" + (q1s+1) + ") " + "Problems: " + searchcbx + ", \n" 
                        + "Severity: " + severity + ", Site: " + site 
                        + ", Duration: " + durationtxt + " " + durationcbx 
                        + ",\nLaterality: " + lateralitycbx + ", Comments: " 
                        + cmmt + ";\n";
                tsttab1s[q1s] = tab1s;
                jTextArea7.append(jTextArea12.getText());

                //to retrieve update value
                compsub[q1s][0] = SnomedID;
                compsub[q1s][1] = searchcbx; // cmmtType;
                compsub[q1s][2] = severity;
                compsub[q1s][3] = site;
                compsub[q1s][4] = durationtxt;
                compsub[q1s][5] = durationcbx;
                compsub[q1s][6] = lateralitycbx;
                compsub[q1s][7] = cmmt;
                compsub[q1s][8] = selectedRbtn;
                
                for (int i = 0; i <= 8; i++) {
                    compsub[q1s][i] = Func.trim(compsub[q1s][i]);
                }

                //to retrieve update value
                q1s++;

            }
            tab1 = tab1 + 1;
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
        } else {
            String update = "Problems: " + searchcbx + ", \n" + "Severity: " 
                    + severity + ", Site: " + site + ", Duration: " 
                    + durationtxt + " " + durationcbx + ",\nLaterality: " 
                    + lateralitycbx + ", Comments: " + cmmt + ";\n";

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

            String _sympCd = "DF-10352";
            String _sevCod = "01";
            String siteCode = "01";
            String _latCd = "01";
            String _drId = "dr001";
            String _drName = "dr.dhana";


            String _duration = durationtxt + " " + durationcbx;

//            CCN passCCN = new CCN(_sympCd, searchcbx, _sevCod, severity, _duration, siteCode, site, _latCd, lateralitycbx, cmmt,  selectedRbtn, _drId, _drName);
//            CISMessageFormatter ccnFormat = new CISMessageFormatter();


            jTextArea7.append("\nC.Complaint\n" + jTextArea12.getText());
            tab1s = "\nImmunization\n" + jTextArea10.getText();
            v = v - 1;
        }

        //**************************Print Existing Value*****************************
        if (tab2 > 0) {
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }
        //**************************Print Existing Value*****************************
        
        //reset all the field in chief complaint
        txt_complaintSearch.setText("");
        txt_complaintComment.setText("");
        cbx_cSeverity.setSelectedIndex(0);
        cbx_site.setSelectedIndex(0);
        txt_duration.setText("");
        cbx_duration.setSelectedIndex(0);
        cbx_laterality.setSelectedIndex(0);
//        rbtn_active.setSelected(true);
    }
        
    
    String disabilitysub[][] = new String[100][7];
    String tsttab6[] = new String[100];
    int q4 = 0;
    String allergysub[][] = new String[100][4];
    String tsttab7[] = new String[100];
    int q5 = 0;
    
    private void accept_btn_alg() {
        jTextArea7.setText("");
        String allergy_name = txt_allergySearch.getText();

        if (!Searching.isSearchAllergy1(allergy_name)) {
            J.o("Invalid", "Invalid Allergy code!", 0);
            return;
        }
        
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String allergy_date = "00/00/0000";
        try {
            allergy_date = formatter.format(txt_allergyDate2.getDate());
        } catch (Exception e) {
//            J.o("Invalid Date", "Invalid Date Format!", 0);
//            return;
        }

        //String allergy_date = txt_allergyDate.getText();
        String allergy_comments = txt_allergyComments.getText();

        String algSnomedId = "";

        try {
            tempQuery = "SELECT RA_CODE FROM READCODE_ALLERGY "
                    + "where UCASE(RA_DESC) like UCASE('%" + allergy_name + "%') ";
            rs = st.executeQuery(tempQuery);
            while (rs.next()) {
                algSnomedId = rs.getString("RA_CODE");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
        String data[] = {"Allergy",
            "Type: " + Func.trim(allergy_name),
            "Comment: " + Func.trim(allergy_comments),
            "Date: " + Func.trim(allergy_date)
        };
        setData(data, 7); //7 for Allergy
        
        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            //tab2s = "\nDiagnosis\n" + jTextArea9.getText();
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nImmunization\n" + jTextArea10.getText();
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }

        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab7 > 0 || tab7 == 0) {
//                jTextArea14.append("\n" + (q5 + 1) + ") "
//                        + "Type: " + allergy_name
//                        + ", Comment: " + allergy_comments
//                        + ",\nDate : " + allergy_date + "\n");
//                tab7s = "\n" + (q5 + 1) + ") " + "Type: " + allergy_name + ", Comment: " + allergy_comments + ",\nDate : " + allergy_date + "\n";
//                tsttab7[q5] = tab7s;
//                jTextArea7.append("\nAllergy\n" + jTextArea14.getText());

                //to retrieve update value
                allergysub[q5][0] = algSnomedId;
                allergysub[q5][1] = allergy_name;
                allergysub[q5][2] = allergy_date;
                allergysub[q5][3] = allergy_comments;

                //to retrieve update value
                q5++;
            }

            tab7 = tab7 + 1;
//            tab7s = "\nAllergy\n" + jTextArea14.getText();
        } else {

            String update = "Type: " + allergy_name + ", Comment: " + allergy_comments + ",\nDate : " + allergy_date + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q5; m++) {
                if (m == group1) {
                    //to update value
                    allergysub[group1][0] = algSnomedId;
                    allergysub[group1][1] = allergy_name;
                    allergysub[group1][2] = allergy_date;
                    allergysub[group1][3] = allergy_comments;
                    //to update value
                    tsttab7[group1] = "\n" + m + ") " + update;
                }
            }


//            jTextArea14.setText("");
//            for (int p1 = 0; p1 < q5; p1++) {
//                if ("".equals(tsttab7[p1])) {
//                    p1 = p1 + 1;
//                }
//
//                jTextArea14.append(tsttab7[p1]);
//            }
//
//            jTextArea7.append("\nAllergy\n" + jTextArea14.getText());
//            tab7s = "\nAllergy\n" + jTextArea14.getText();
            v = v - 1;
        }

        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }


        txt_allergySearch.setText("");
        txt_allergyDate2.setDate(null);
        txt_allergyComments.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_allergySearch.setModel(listModel);
    }    String socialsub[][] = new String[100][4];
    String tsttab8[] = new String[100];
    int q6 = 0;
    
    private void accept_btn_soh() {
        jTextArea7.setText("");
        String scl_prob = txt_socialProblem.getText();

        if (!Searching.isSearchSH1(scl_prob)) {
            J.o("Invalid", "Invalid Social History code!", 0);
            return;
        }
        
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String scl_date = "00/00/000";
        try {
            scl_date = formatter.format(txt_socialDate1.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }

        //String scl_date = txt_socialDate.getText();
        String scl_comments = txt_socialComment.getText();
        String scl_snomedID = "";
        try {
            tempQuery = "SELECT SNOMEDID FROM SNOMEDSH where SNOMEDDESC like '%" + scl_prob + "%' ";
            rs = st.executeQuery(tempQuery);
            while (rs.next()) {
                scl_snomedID = rs.getString("SNOMEDID");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }


        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            //tab2s = "\nDiagnosis\n" + jTextArea9.getText();
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nImmunization\n" + jTextArea10.getText();
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }

        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab8 > 0 || tab8 == 0) {
                //jTextArea15.append("\n" + (q6 + 1) + ") "
                        //+ "Problem: " + scl_prob
                        //+ ",\nComment: " + scl_comments
                        //+ ", Date : " + scl_date + "\n");
                tab8s = "\n" + (q6 + 1) + ") " + "Problem: " + scl_prob + ",\nComment: " + scl_comments + ", Date : " + scl_date + "\n";
                tsttab8[q6] = tab8s;
                //jTextArea7.append("\nSocial Problem\n" + jTextArea15.getText());

                String data[] = {"Social History",
                    "Problem: " + Func.trim(scl_prob),
                    "Comment: " + Func.trim(scl_comments),
                    "Date: " + Func.trim(scl_date)
                };
                setData(data, 5); //5 for SH

                //to retrieve update value
                socialsub[q6][0] = scl_snomedID;
                socialsub[q6][1] = scl_prob;
                socialsub[q6][2] = scl_date;
                socialsub[q6][3] = scl_comments;

                //to retrieve update value
                q6++;
            }

            tab8 = tab8 + 1;
            //tab8s = "\nSocial Problem\n" + jTextArea15.getText();
        } else {

            String update = "Problem: " + scl_prob + ",\nComment: " + scl_comments + "Date : " + scl_date + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q6; m++) {
                if (m == group1) {
                    //to update value
                    socialsub[group1][0] = scl_snomedID;
                    socialsub[group1][1] = scl_prob;
                    socialsub[group1][2] = scl_date;
                    socialsub[group1][3] = scl_comments;

                    //to update value
                    tsttab8[group1] = "\n" + m + ") " + update;
                }
            }


            //jTextArea15.setText("");
            for (int p1 = 0; p1 < q6; p1++) {
                if (tsttab8[p1] == "") {
                    p1 = p1 + 1;
                }

                //jTextArea15.append(tsttab8[p1]);
            }

            //jTextArea7.append("\nSocial Problem\n" + jTextArea15.getText());
            //tab8s = "\nSocial Problem\n" + jTextArea15.getText();
            v = v - 1;
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }

        txt_socialProblem.setText("");
        txt_socialDate1.setDate(null);
        txt_socialComment.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_socialProblem.setModel(listModel);
    }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        //Friza ammend 12/10

//        ehr = new FormattedMessage();
//        thread = new Thread(ehr);
//        thread.start();

    }//GEN-LAST:event_formWindowActivated

    private void MItem_ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MItem_ImportActionPerformed

        try {
            TextFileReader read = new TextFileReader(this);
            read.ImportData();
            JOptionPane.showMessageDialog(null, "Patient record is imported from thumb drive.");
            //ProgressBar gui = new ProgressBar(this);
            //gui.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_MItem_ImportActionPerformed

    
    private void accept_btn_dab() {
        jTextArea7.setText("");
        String disability = txt_disabilityType.getText();
        
        if (!Searching.isSearchDAB1(disability)) {
            J.o("Invalid", "Invalid Disability code!", 0);
            return;
        }
        
        //==--String d_date = txt_dDate.getText();//==--

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String d_date1 = "00/00/0000";
        try {
            d_date1 = formatter.format(txt_dDate1.getDate());
        } catch (Exception e) {
//            J.o("Invalid Date", "Invalid Date Format!", 0);
//            return;
        }

        String d_comments = txt_dComments.getText();
        String disSnomedID = "";

        try {
            tempQuery = "SELECT SNOMEDID FROM SNOMEDDIASBILITY where SNOMEDDESC like '%" + disability + "%' ";
            rs = st.executeQuery(tempQuery);
            while (rs.next()) {
                String disID = rs.getString("SNOMEDID");
                System.out.println(disID);
                disSnomedID = disID;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }


        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            tab1s = "\nC.Complaint\n" + jTextArea12.getText();
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            //tab2s = "\nDiagnosis\n" + jTextArea9.getText();
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            tab3s = "\nImmunization\n" + jTextArea10.getText();
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }

        //**************************Print Existing Value*****************************

        if (v == 0) {
            if (tab6 > 0 || tab6 == 0) {
                //jTextArea13.append("\n" + (q4 + 1) + ") "
                        //+ "Type: " + disability
                        //+ ", Comment: " + d_comments
                        //+ ",\nDate : " + d_date1 + "\n");
                tab6s = "\n" + (q4 + 1) + ") " + "Type: " + disability + ", Comment: " + d_comments + ",\nDate : " + d_date1 + "\n";
                tsttab6[q4] = tab6s;
                //jTextArea7.append("\nDisability\n" + jTextArea13.getText());

                String data[] = {"Disability",
                    "Type: " + Func.trim(disability),
                    "Comment: " + Func.trim(d_comments),
                    "Date: " + Func.trim(d_date1)
                };
                setData(data, 9); //9 for Disability

                //to retrieve update value
                disabilitysub[q4][0] = disSnomedID;
                disabilitysub[q4][1] = disability;
                disabilitysub[q4][2] = d_date1;
                //to retrieve update value
                q4++;
            }
            tab6 = tab6 + 1;
            //tab6s = "\nDisability\n" + jTextArea13.getText();
        } else {

            String update = "Type: " + disability + ", Comment: " + d_comments + ",\nDate : " + d_date1 + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q4; m++) {
                if (m == group1) {
                    //to update value
                    disabilitysub[group1][0] = disSnomedID;
                    disabilitysub[group1][1] = disability;
                    //==--disabilitysub[group1][2] =d_date;//==--
                    disabilitysub[group1][2] = d_date1;
                    //to update value
                    tsttab6[group1] = "\n" + m + ") " + update;
                }
            }


            //jTextArea13.setText("");
            for (int p1 = 0; p1 < q4; p1++) {
                if ("".equals(tsttab6[p1])) {
                    p1 = p1 + 1;
                }

                //jTextArea13.append(tsttab6[p1]);

            }

            //jTextArea7.append("\nDisability\n" + jTextArea13.getText());
            //tab6s = "\nDisability\n" + jTextArea13.getText();
            v = v - 1;
        }

        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }

        txt_disabilityType.setText("");
        txt_dDate1.setDate(null);
        txt_dComments.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_disabilityType.setModel(listModel);
    }
    private void tfieldDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfieldDateMouseClicked
}//GEN-LAST:event_tfieldDateMouseClicked

    private void tfieldDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfieldDatePropertyChange
//        if (tfieldDate.getDate() != null) {
//            Vector queueinfo = new Vector();
//            Queue queue1 = new Queue();
//            //try
//            {
//
//                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
//                String date = formatter.format(tfieldDate.getDate());
//                try {
//                    queueinfo = queue1.getQueueList(date);
//
//                    if (queueinfo == null) {
//                        JOptionPane.showMessageDialog(null, "Patient information is not existed in the database. Please proceed to Patient Master Index Form to fill in the information!!!", "Error", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        data = queue1.getQueueList(date);
//                        System.out.println("wat is the data : " + data);
//                        header = new Vector<String>();
//                        header.add("PMI_NO");
//                        header.add("NAME");
//                        header.add("EPISODE_TIME");
//                        header.add("CONSULTATION_ROOM");
//                        header.add("DOCTOR");
//                        header.add("STATUS");
//
//                        tblQueue1.setModel(new javax.swing.table.DefaultTableModel(data, header) {
//
//                            @Override
//                            public boolean isCellEditable(int row, int column) {
//                                return false;
//                            }
//                        });
//
//                    }
//
//                } catch (Exception ex) {
//                    Logger.getLogger(AppointmentList.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        }
}//GEN-LAST:event_tfieldDatePropertyChange
    private String selectedAppointment;
    public String getSelectedAppointment() {
        return selectedAppointment;
    }
    private void tblQueue1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQueue1MouseClicked
        // TODO add your handling code here:
        int rowIndex = tblQueue1.getSelectedRow();
        selectedAppointment = (String) tblQueue1.getValueAt(rowIndex, 0);
        //selectedAppointmentType =(String) tblAppointment.getValueAt(rowIndex,4);

        System.out.println("The value is : " + selectedAppointment);
        //setSelectedAppointment(selectedAppointment);
}//GEN-LAST:event_tblQueue1MouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCloseActionPerformed

    private void btn_pHistoryOk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pHistoryOk2ActionPerformed
        // TODO add your handling code here:

        String newPmiNo = txt_pPmiNo.getText();
        String newPName = txt_pName.getText();
        String newPIcNo = txt_pIcNo.getText();
        String newPRace = txt_pRace.getText();
        String newPSex = txt_pSex.getText();
        String newDob = lblDOB.getText();
        String newSex = txt_pBloodSex.getText();
        String newStatus = txt_pStatus.getText();

        //create a new form
        Consultation con = new Consultation();
        con.setVisible(true);
        this.dispose();
        con.txt_pPmiNo.setText(newPmiNo);
        con.txt_pName.setText(newPName);
        con.txt_pIcNo.setText(newPIcNo);
        con.txt_pRace.setText(newPRace);
        con.txt_pSex.setText(newPSex);
        con.lblDOB.setText(newDob);
        con.txt_pBloodSex.setText(newSex);
        con.txt_pStatus.setText(newStatus);
        jFrame2.setVisible(false);
}//GEN-LAST:event_btn_pHistoryOk2ActionPerformed

    private void tblQueueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQueueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblQueueMouseClicked

    private void btn_pHistoryOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pHistoryOk1ActionPerformed
        // TODO add your handling code here:
        jFrame2.setVisible(false);
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_btn_pHistoryOk1ActionPerformed

    private void txt_pNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pNameActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
            DriversLocation driverLocation = new DriversLocation();
            Timestamp date = timestamp;

            String PMI = txt_pPmiNo.getText();
            String name = txt_pName.getText();
            String IC = txt_pIcNo.getText();
            String race = txt_pRace.getText();
            String sex = txt_pSex.getText();
            String DOB = lblDOB.getText();
            String blood = txt_pBloodSex.getText();
            String pstatus = txt_pStatus.getText();

            String header = "MSH|^~|CIS^T12109|" + "<cr>" + "\n";
            String patientInfo = "PDI|" + PMI + "|" + name + "^" + IC + "^" + race + "^" + sex + "^" + DOB + "^" + blood + "^" + pstatus + "^" + "|" + "<cr>" + "\n";
            String msgs[] = new String[200];
            for (int zz = 0; zz < 200; zz++) {
                msgs[zz] = "";
            }

            boolean stat_dto = false;

            for (int zz = 0, ii = 0; zz < max_row && ii < 200; zz++, ii++) {
                try {
                    S.oln(note_array[zz]);
                    if (note_array[zz].equals("Drugs")) {
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
                            tempQuery = "SELECT UD_MDC_CODE "
                                    + "FROM PIS_MDC2 "
                                    + "WHERE UCASE(D_GNR_NAME) LIKE UCASE(?) "
                                    + "OR UCASE(D_TRADE_NAME) LIKE UCASE(?) ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + ActiveIngredient + "%");
                            ps.setString(2, "%" + ProductName + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                UD_MDC_Code = rs.getString("UD_MDC_CODE");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        
                        String qty_drug = Quantity;
                        if (packType.equals("CAP") || packType.equals("TAB")) {
                            qty_drug = getDrugQuantity(getFrequencyCode(Frequency), Quantity, getDayDrugCode(Duration));
                        }
                        
                        String data[] = {
                            ProblemCode + "^" + ProblemDesc + "^" + ProblemCode,
                            UD_MDC_Code + "^" + ActiveIngredient + "^" + UD_MDC_Code,
                            "" + "^" + DrugForm + "^" + "",
                            "" + "^" + "" + "^" + "",
                            "" + "^" + Frequency + "^" + "",
                            getFrequencyCode(Frequency),
                            Quantity,
                            Dose,
                            "" + "^" + "" + "^" + Dose,
                            getDayDrugCode(Duration),
                            qty_drug,
                            "" + "^" + "" + "^" + "",
                            Instruction,
                            "" + "^" + Session.getHfc_code() + "^"
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

            if(stat_dto) {
                PDFiText.createPrescription("assets/Presription_.pdf", data_temp);
            } else {
                J.o("No Drug Order", "No drug had been ordered!\nPlease order drug first.", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_missingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_missingActionPerformed
        try {
            this.IDPMS = this.IDPMS.equals("") ? "-" : this.IDPMS;
            this.EpisodeTime = this.EpisodeTime.equals("") ? "-" : this.EpisodeTime;

            String sql = "UPDATE PMS_EPISODE SET STATUS = 'Missing' "
            + "WHERE PMI_NO = ? AND EPISODE_TIME = ?";
            Q.sPs(sql);
            Q.s(1, this.IDPMS);
            Q.s(2, this.EpisodeTime);
            Q.gPs().execute();

            String sql2 = "SELECT PMI_NO,NAME,EPISODE_TIME,CONSULTATION_ROOM,"
            + "DOCTOR,STATUS FROM PMS_EPISODE "
            + "WHERE STATUS NOT LIKE 'Discharge' "
            + "AND STATUS NOT LIKE 'Consult' "
            + "AND STATUS NOT LIKE 'Hold' "
            + "ORDER BY EPISODE_TIME ASC "
            + "LIMIT 1";
            Q.sRs(sql2);

            if (Q.gRs().next()) {
                String idpms = Q.gRs().getString("PMI_NO");
                String time = Q.gRs().getString("EPISODE_TIME");

                String sql_new = "UPDATE PMS_EPISODE SET STATUS = 'Consult' "
                + "WHERE PMI_NO = ? AND EPISODE_TIME = ?";
                Q.sPs(sql_new);
                Q.s(1, idpms);
                Q.s(2, time);
                Q.gPs().execute();

                this.setSelectedAppointment(idpms, time);
            } else {
                J.o("Queue", ".. No more queue ..", 1);
                this.setSelectedAppointment("-", "-");
                this.IDPMS = "-";
                this.EpisodeTime = "-";
            }
            this.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_missingActionPerformed

    private void btn_holdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_holdActionPerformed

        String message = "Are you sure want to Hold this data?";
        String title = "Consultation";
        int reply = JOptionPane.showConfirmDialog(null, message, title,
            JOptionPane.YES_NO_OPTION);
        if (reply != JOptionPane.YES_OPTION) {
            return;
        }
        vph.setVisible(false);
        que.setVisible(false);
        
        // set hold status
        Queue updatequeue = new Queue();
        updatequeue.updateStatusEpisode(txt_pPmiNo.getText(), EpisodeTime, "Hold", "");
        
        hold();
    }//GEN-LAST:event_btn_holdActionPerformed

    // declare start time variable
    private static long startTime_x1 = System.currentTimeMillis();
    
    // start time method
    public static void startingTime() {
        startTime_x1 = System.currentTimeMillis();
    }
    
    // end time method
    public static void endingTime(String title) {
        // end time
        long endTime = System.currentTimeMillis();
        long diffTime = endTime - startTime_x1;
        boolean status = DBConnection.captureResponseTime(title, diffTime);

        System.out.println("Title: " + title);
        System.out.println("Status Response Time: " + status);
        System.out.println("Diff Time: " + diffTime);
    }
    
    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        //        try {
            //            String sql = "SELECT PMI_NO,NAME,EPISODE_TIME,CONSULTATION_ROOM,"
            //                    + "DOCTOR,STATUS FROM PMS_EPISODE "
            //                    + "WHERE STATUS NOT LIKE 'Discharge' "
            //                    + "AND STATUS NOT LIKE 'Consult' "
            //                    + "AND STATUS NOT LIKE 'Hold' "
            //                    + "ORDER BY EPISODE_TIME ASC "
            //                    + "LIMIT 1";
            //            Q.sRs(sql);
            //
            //            String idpms = "-";
            //            String time = "-";
            //
            //            if(Q.gRs().next()) {
                //                idpms = Q.gRs().getString("PMI_NO");
                //                time = Q.gRs().getString("EPISODE_TIME");
                //
                //                String sql_new = "UPDATE PMS_EPISODE SET STATUS = 'Consult' "
                //                        + "WHERE PMI_NO = ? AND EPISODE_TIME = ?";
                //                Q.sPs(sql_new);
                //                Q.s(1, idpms);
                //                Q.s(2, time);
                //                Q.gPs().execute();
                //            } else {
                //                J.o("Queue", "..End Of Waiting Queue..", 1);
                //            }
            //            this.setSelectedAppointment(idpms, time);
            //            this.setVisible(true);
            //        } catch (SQLException ex) {
            //            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
            //        }
        
        // start time
        long startTime = System.currentTimeMillis();
        
        // process
        try {
            Queue queue1 = new Queue();
            Vector<Vector<String>> data = queue1.getQueueNameList("", 1);
            String idpms = "-";
            String time = "-";
            String status = "-";
            boolean found = false;
            if (data.size() > 0) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).size() > 0) {
                        status = data.get(i).get(5);
                        System.out.println("vector:-\n|" + data.get(i));
                        if (status.equals("Waiting")) {
                            idpms = data.get(i).get(0);
                            time = data.get(i).get(2);
                            found = true;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                System.out.println("Status Found: |"+found+"|");
                if (found) {
                    this.setSelectedAppointment(idpms, time);
                    this.setVisible(true);
                } else {
                    J.o("No Patient", "No Patient Waiting ..", 1);
                }
            } else {
                J.o("No Patient", "No Patient Waiting ..", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // end time
        long endTime = System.currentTimeMillis();
        long diffTime = endTime - startTime;
        String title = "SELECT NEXT PATIENT FROM QUEUE";
        boolean status = DBConnection.captureResponseTime(title, diffTime);
        
        System.out.println("Title: " + title);
        System.out.println("Status Response Time: " + status);
        System.out.println("Diff Time: " + diffTime);
        
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_dischargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dischargeActionPerformed

        if (checkPatient()) {
            return;
        }
        if (checkDiagnosis().equals("|")) {
            return;
        }
        
        Consultation_subcode.btn_discharge(this);
    }//GEN-LAST:event_btn_dischargeActionPerformed

    private void tbl_note_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_note_1MouseClicked
        // TODO add your handling code here:
        int index_row = tbl_note_1.getSelectedRow();
        int index_col = tbl_note_1.getSelectedColumn();
        String data2 = tbl_note_1.getModel().getValueAt(index_row, index_col).toString();
        tbl_note_1.setToolTipText(data2);
        if (data2.equals("DELETE")) {
            int stat = JOptionPane.showConfirmDialog(null, "Are you sure want "
                + "to delete this?", "Delete Note", 2);
            if (stat == 0) {
                String data[] = note_array[index_row].split("\\|");
                switch (Integer.parseInt(data[1])) {
                    case 16: {
                        btn_generate_mc.setEnabled(false);
                        MC_accptBtn.setEnabled(true);
                    }
                    break;
                    case 17: {
                        btn_generate_timeslip.setEnabled(true);
                    }
                    break;
                }
                index_row = delete_note(index_row);
            }
        } else if (data2.equals("EDIT")) {
            int stat = JOptionPane.showConfirmDialog(null, "Are you sure want "
                + "to edit this?", "Edit Note", 2);
            if (stat == 0) {
                String data[] = note_array[index_row].split("\\|");
                int index_temp = index_row - 1;
                S.oln(data[0]+"|"+data[1]);
                switch (Integer.parseInt(data[1])) {
                    case 1: { //c.complaint
                        String comment = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        String laterality = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        String duration = tbl_note_1.getValueAt(index_temp, 1).toString().split(" ")[0];
                        String duration2 = tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[1];
                        String side = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        String severe = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        String problem = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        txt_complaintSearch.setText(problem);
                        cbx_cSeverity.setSelectedItem(severe);
                        txt_complaintComment.setText(comment);
                        txt_duration.setText(duration);
                        cbx_duration.setSelectedItem(duration2);
                        cbx_site.setSelectedItem(side);
                        cbx_laterality.setSelectedItem(laterality);
                    }
                    break;
                    case 2: {
                        HPI_DetailstxtArea.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 3: {
                        txt_PMHComment.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        String rbtn_temp = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        if(rbtn_temp.equals("Active")) {
                            rbtn_cActive1.setSelected(true);
                            rbtn_cInactive1.setSelected(false);
                        } else if(rbtn_temp.equals("In Active")) {
                            rbtn_cActive1.setSelected(false);
                            rbtn_cInactive1.setSelected(true);
                        }
                        //[0305 01042013] txt_PMHDiagnosis.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_PMHSearch.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 4: {
                        FH_Comments.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        //[0347 01042013] txt_FHDiagnosis.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        FH_Relationship.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_FHSearch.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 5: {
                        String testDate = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                        Date date = null;
                        try {
                            date = formatter.parse(testDate);
                        } catch (Exception e) {
                            date = null;
                        }
                        txt_socialDate1.setDate(date);
                        txt_socialComment.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_socialProblem.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 6: {
                        txt_BldCmmnt.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        String rbtn_temp = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        if (rbtn_temp.equals("Normal")) {
                            rbtn_Bnormal.setSelected(true);
                            rbtn_Bdeficient.setSelected(false);
                        } else if (rbtn_temp.equals("Deficient")) {
                            rbtn_Bnormal.setSelected(false);
                            rbtn_Bdeficient.setSelected(true);
                        }
                        rbtn_temp = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        if (rbtn_temp.equals("Positive")) {
                            rbtn_Bpositive.setSelected(true);
                            rbtn_Bnegative.setSelected(false);
                        } else if (rbtn_temp.equals("Negative")) {
                            rbtn_Bpositive.setSelected(false);
                            rbtn_Bnegative.setSelected(true);
                        }
                        //                        txtBloodType.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        Func.cmbSelectInput(cmbBloodType, tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 7: {
                        String testDate = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                        Date date = null;
                        try {
                            date = formatter.parse(testDate);
                        } catch (Exception e) {
                            date = null;
                        }
                        txt_allergyDate2.setDate(date);
                        txt_allergyComments.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_allergySearch.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 8: {
                        String testDate = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                        Date date = null;
                        try {
                            date = formatter.parse(testDate);
                        } catch (Exception e) {
                            date = null;
                        }
                        txt_immDate1.setDate(date);
                        txt_immComment.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_immSearch.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 9: {
                        String testDate = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                        Date date = null;
                        try {
                            date = formatter.parse(testDate);
                        } catch (Exception e) {
                            date = null;
                        }
                        txt_dDate1.setDate(date);
                        txt_dComments.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_disabilityType.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 10: {
                        txt_blood_glucose.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_pulse2.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_diastolic2.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_systolic2.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_pulse1.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_diastolic1.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_systolic1.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_pulse.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_diastolic.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_systolic.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_temperature.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_headCircumference.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_weightStatus.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_bmi.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_weight.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_height.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        Func.cmbSelectInput(cbx_pain_scale, tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        set_pain_result(Integer.parseInt((String) cbx_pain_scale.getSelectedItem()));
                        txt_oxygen_saturation.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_respiratory_rate.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_pgcs_result.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_pgcs_points.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_gcs_result.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                        txt_gcs_points.setText(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[0]);
                    }
                    break;
                    case 11: {
                        String testDate = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                        Date date = null;
                        try {
                            date = formatter.parse(testDate);
                        } catch (Exception e) {
                            date = null;
                        }
                        txt_date1.setDate(date);
                        txt_diagComment.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        dbx_laterality.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        dbx_site.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        cbx_dSeverity.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_diagnosisSearch.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 12: {
                        txt_pNotes.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 13: {
                        txt_packagetype.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_caution.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        cb_instructionOList.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        cb_frequencyOList.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        cb_durationOList.setSelectedItem(tbl_note_1.getValueAt(index_temp, 1).toString().split(" ")[0]);
                        cb_durationTypeOList.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString().split(" ")[1]);
                        txt_dosageFormOList.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_quantityOList.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_drugstrength.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        tbl_productname.getModel().setValueAt(tbl_note_1.getValueAt(index_temp--, 1).toString(), 0, 0);
                        txt_drugNameOListSearch.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                    }
                    break;
                    case 14: {
//                        txt_telinga_kanan.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_mulut.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_hidung.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_kepala.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_perut.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_bahu_kiri.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_bahu_kanan.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_peparu_kanan.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_jantung.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
//                        txt_tekak.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        txt_pe_comments.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        String pe = tbl_note_1.getValueAt(index_temp--, 1).toString();
                    }
                    break;
                    case 15: {
                        Laboratory_Request.txt_notes1.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        Laboratory_Request.cbx_sub_discipline1.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        Laboratory_Request.cbx_discipline1.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        Laboratory_Request.cbx_health_facility1.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        Laboratory_Request.txt_patient_condition1.setText(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        Laboratory_Request.cbx_priority.setSelectedItem(tbl_note_1.getValueAt(index_temp--, 1).toString());
                        String test = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        problemDesc = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        problemCode = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        String type = tbl_note_1.getValueAt(index_temp--, 1).toString();
                        if(type.contains("Chemist Patology")) {
                            for (int i = 0; i < sizeLRPK; i++) {
                                if(cbxPK[i].getText().contains(test)) {
                                    cbxPK[i].setSelected(true);
                                }
                            }
                        } else if(type.contains("Clinical")) {
                            for (int i = 0; i < sizeLRKL; i++) {
                                if(cbxKL[i].getText().contains(test)) {
                                    cbxKL[i].setSelected(true);
                                }
                            }
                        } else if(type.contains("Hematology")) {
                            for (int i = 0; i < sizeLRHM; i++) {
                                if(cbxHM[i].getText().contains(test)) {
                                    cbxHM[i].setSelected(true);
                                }
                            }
                        }
                    }
                    break;
                    case 16: {
                        btn_generate_mc.setEnabled(false);
                        MC_accptBtn.setEnabled(true);
                    }
                    break;
                    case 17: {
                        btn_generate_timeslip.setEnabled(true);
                    }
                    break;
                }
                index_row = delete_note(index_row);
            }
        } else if(index_row == 1) {
            J.o("Consultation Notes", data2, 1);
        }
    }//GEN-LAST:event_tbl_note_1MouseClicked

    private void btn_generate_timeslipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generate_timeslipActionPerformed
        // TODO add your handling code here:

        setMC_TimeSlip(2);
    }//GEN-LAST:event_btn_generate_timeslipActionPerformed

    private void MC_clrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MC_clrBtnActionPerformed
        // TODO add your handling code here:
        date_from.setDate(null);
        date_to.setDate(null);
    }//GEN-LAST:event_MC_clrBtnActionPerformed

    private static boolean isGenerateMCTS = false;
    
    private void MC_accptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MC_accptBtnActionPerformed
        if(checkPatient()) return;
        if(checkProblem().equals("|")) return;
        accept_button_mc();
    }//GEN-LAST:event_MC_accptBtnActionPerformed

    private void btn_generate_mcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generate_mcActionPerformed
        // TODO add your handling code here:

        setMC_TimeSlip(1);
    }//GEN-LAST:event_btn_generate_mcActionPerformed

    private void btn_ProcAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcAccActionPerformed
        // TODO add your handling code here:
        if(checkPatient()) return;
        if (checkProblem().equals("|")) {
            return;
        }
        accept_btn_pos();
    }//GEN-LAST:event_btn_ProcAccActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        cbx_referral_doctor.setSelectedIndex(0);
        txt_referral_comment.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(checkPatient()) return;
        String cbx_doctor = cbx_referral_doctor.getSelectedItem().toString();
        String comment = txt_referral_comment.getText();
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        if (cbx_referral_doctor.getSelectedIndex() == 0) {
            J.o("Error", "Please select a doctor!", 0);
            return;
        } else {
            String message = "Are you sure want to refer this patient to "+cbx_doctor+" ?";
            String title = "Referral";
            int reply = JOptionPane.showConfirmDialog(null, message, title,
                JOptionPane.YES_NO_OPTION);
            if (reply != JOptionPane.YES_OPTION) {
                return;
            }
            ArrayList<String> ref = new ArrayList<String>();
            ref.add(txt_pPmiNo.getText());
            ref.add(this.EpisodeTime);
            ref.add(Session.getUser_id());
            ref.add(Session.getHfc_code());
            ref.add(Session.getDiscipline());
            ref.add(Session.getSubdiscipline());
            ArrayList<String> getDoctor = new ArrayList<String>();
            for (int j = 0; j < rs_ref.size(); j++) {
                if (rs_ref.get(j).get(3).equals(cbx_doctor)) {
                    getDoctor = rs_ref.get(j);
                }
            }
            if (getDoctor.size() > 0) {
                ref.add(getDoctor.get(0));
                ref.add(getDoctor.get(1));
                ref.add(getDoctor.get(20));
                ref.add(getDoctor.get(21));
                ref.add(timestamp+"");
                ref.add(comment);
                System.out.println("ref:"+ref);
                //                LongRunProcess.check_network2();
                //                if (Session.getPrev_stat()) { //online
                    try {
                        //                        ArrayList<String> listOnline = Func.readXML("online");
                        //                        Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
                        //                        Message impl = (Message) myRegistry.lookup("myMessage");
                        
                        Queue updatequeue = new Queue();
                        updatequeue.updateStatusEpisode(txt_pPmiNo.getText(), EpisodeTime, "Second Opinion", cbx_doctor);
                        
                        boolean stat = DBConnection.getImpl().addReferral(ref);
                        Consultation.showOnline();
                        if (stat) {
                            J.o("Referral Patient Success", "Referral Patient Success ..", 1);
                            cbx_referral_doctor.setSelectedIndex(0);
                            txt_referral_comment.setText("");
                            hold();
                        } else {
                            J.o("Referral Patient Failed", "Referral Patient Failed!!", 0);
                        }
                    } catch (Exception e) {
                        Consultation.showOffline();
                        J.o("Network Offline", "Network to Server is Offline!!", 0);
                        e.printStackTrace();
                    }
                    //                } else {
                    //                    J.o("Referral Patient Failed", "Referral Patient Failed!!\nNetwork is down ..", 0);
                    //                }
            } else {
                J.o("Invalid Doctor", "This doctor is not exist!", 0);
                return;
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tbl_drugMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_drugMouseClicked
        if(checkPatient()) return;

        try {
            if (checkPatient()) {
                return;
            }
            int rowIndex = tbl_drug.getSelectedRow();
            String act = (String) tbl_drug.getValueAt(rowIndex, 0);
            String pro = (String) tbl_drug.getValueAt(rowIndex, 1);
            String qty = (String) tbl_drug.getValueAt(rowIndex, 2);
            String fre = (String) tbl_drug.getValueAt(rowIndex, 3);
            int dur = Integer.parseInt(tbl_drug.getValueAt(rowIndex, 4).toString());
            String ins = (String) tbl_drug.getValueAt(rowIndex, 5);

            txt_drugNameOListSearch.setText(act);
            setDrugAct();

            setProductNameTbl(pro);

            cb_frequencyOList.setSelectedItem(fre);
            cb_instructionOList.setSelectedItem(ins);

            int fre2 = Integer.parseInt(getFrequencyCode(fre));
            int new_qty = (Integer.parseInt(qty) / fre2) / dur;
            txt_quantityOList.setText(""+new_qty);

            if(dur % 30 == 0) {
                dur /= 30;
                cb_durationOList.setSelectedItem("" + dur);
                cb_durationTypeOList.setSelectedItem("Month");
            } else if(dur % 7 == 0) {
                dur /= 7;
                cb_durationOList.setSelectedItem("" + dur);
                cb_durationTypeOList.setSelectedItem("Week");
            } else {
                cb_durationOList.setSelectedItem("" + dur);
                cb_durationTypeOList.setSelectedItem("Day");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tbl_drugMouseClicked

    private void btn_drugClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_drugClearActionPerformed
        // TODO add your handling code here:
        txt_drugNameOListSearch.setText("");
        txt_drugstrength.setText("");
        txt_quantityOList.setText("");
        txt_dosageFormOList.setText("");
        cb_durationOList.setSelectedIndex(0);
        cb_durationTypeOList.setSelectedIndex(0);
        cb_frequencyOList.setSelectedIndex(0);
        cb_instructionOList.setSelectedIndex(0);

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        //lst_drugNameOListSearch.setModel(listModel);
        for (int i = 0; i < 50; i++) {
            tbl_productname.getModel().setValueAt("", i, 0);
        }
        //lst_productNameOList.setModel(listModel);
        //tfield_productname.setText("");
    }//GEN-LAST:event_btn_drugClearActionPerformed

    private void btn_drugAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_drugAcceptActionPerformed

        if (checkPatient()) {
            return;
        }
        if (checkProblem().equals("|")) {
            return;
        }
        accept_btn_dto();
    }//GEN-LAST:event_btn_drugAcceptActionPerformed

    private void tbl_productnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productnameMouseClicked
        // TODO add your handling code here:
        int index = tbl_productname.getSelectedRow();
        String st = tbl_productname.getModel().getValueAt(index, 0).toString();
        String ud_mdc_code = arr_tbl_productname.get(index);
        currentIndex_tbl_productname = index;
        System.out.println("|"+ud_mdc_code+"|umaq");
//        try {
//            ResultSet results = DBConnection.getImpl().getDrugCIS(st);
//            
//            clearDrugFields();
//            
//            if (results.next()) {
//                getDetailProductName(results);
//            } 
////            else {
////                getDetailProductName("");
////            }
//        } catch (Exception e) {
            try {
//                String sql = "SELECT * "
//                        + "FROM PIS_MDC2 "
//                        + "WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
                String sql = "SELECT * "
                        + "FROM PIS_MDC2 "
                        + "WHERE UCASE(UD_MDC_CODE) = UCASE(?)";
                //            String sql = "SELECT * FROM PIS_MDC WHERE DRUG_PRODUCT_NAME = ?";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                ps.setString(1, st);
                ps.setString(1, ud_mdc_code);
                ResultSet results = ps.executeQuery();
                
                clearDrugFields();
                
                if (results.next()) {
                    getDetailProductName(results);
                } 
//                else {
//                    getDetailProductName("");
//                }
            } catch (Exception ex) {
                S.oln("MDC 12" + ex.getMessage());
            }
//        }
    }//GEN-LAST:event_tbl_productnameMouseClicked

    private void txt_drugNameOListSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_drugNameOListSearchKeyReleased
        if (checkPatient()) {
            return;
        }

        startingTime();
        
        String dtraden = txt_drugNameOListSearch.getText();
        if (dtraden.equals("")) {
            for (int i = 0; i < 50; i++) {
                tbl_productname.getModel().setValueAt("", i, 0);
            }
        } else {
            //tbl_productname
//            try {
//                
//                for (int i = 0; i < 50; i++) {
//                    tbl_productname.getModel().setValueAt("", i, 0);
//                }
//                
////                String sql = "SELECT D_TRADE_NAME "
////                            + "FROM PIS_MDC2 "
////                            + "WHERE UCASE(D_TRADE_NAME) LIKE UCASE(?) "
////                            + "OR UCASE(D_GNR_NAME) LIKE UCASE(?)";
////                String params[] = {"%"+dtraden+"%", "%"+dtraden+"%"};
////                
////                ArrayList<ArrayList<String>> results = DBConnection.getImpl().getQuery(sql, 1, params);
////                for (int i = 0; i < results.size(); i++) {
////                    tbl_productname.getModel().setValueAt(results.get(i).get(0), i, 0);
////                }
//                
//                ResultSet results = DBConnection.getImpl().getDrugCIS(dtraden);
//                for (int i = 0; results.next() && i < 50; i++) {
//                    tbl_productname.getModel().setValueAt(results.getString("D_TRADE_NAME"), i, 0);
//                }
//                
//            } catch (Exception e) {
//                e.printStackTrace();
                try {
                    for (int i = 0; i < 50; i++) {
                        tbl_productname.getModel().setValueAt("", i, 0);
                    }
                    String sql = "SELECT * "
                            + "FROM PIS_MDC2 "
                            + "WHERE UCASE(D_TRADE_NAME) LIKE UCASE(?) "
                            + "OR UCASE(D_GNR_NAME) LIKE UCASE(?)";
                    //                    String sql = "SELECT * FROM PIS_MDC2 WHERE D_TRADE_NAME LIKE ?";
                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                    ps.setString(1, "%" + dtraden + "%");
                    //                    ps.setString(2, dgnrn.toUpperCase() + "%");
                    ps.setString(2, "%" + dtraden + "%");
                    ResultSet results = ps.executeQuery();
                    arr_tbl_productname.removeAll(arr_tbl_productname);
                    for (int i = 0; results.next() && i < 50; i++) {
                        tbl_productname.getModel().setValueAt(results.getString("D_TRADE_NAME"), i, 0);
                        arr_tbl_productname.add(results.getString("UD_MDC_CODE"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//            }
        }
        
        endingTime("SEARCH DRUG");
    }//GEN-LAST:event_txt_drugNameOListSearchKeyReleased

    ArrayList<String> arr_tbl_productname = new ArrayList<String>();
    int currentIndex_tbl_productname = -1;
    
    private void PN_accptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PN_accptBtnActionPerformed
        // TODO add your handling code here:

        if(checkPatient()) return;
        jTextArea7.setText("");
        String pNotes = txt_pNotes.getText();

        if (tab1 > 0) {
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (v == 0) {
            if (tab13 > 0 || tab13 == 0) {
                jTextArea22.append("\n" + (q11+1) + ") " +
                    "Notes: " + pNotes + "\n");
                tab13s = "\n" + (q11+1) + ") " + "Notes: " + pNotes + "\n";
                tsttab12[q11] = tab13s;
                jTextArea7.append("\nProgress Notes\n" + jTextArea22.getText());

                String data[] = {"Progress Notes",
                    "Notes: " + Func.trim(pNotes)
                };
                setData(data, 12); //12 for Progress Notes

                //to retrieve update value
                pnsub[q11][0] = pNotes;

                //to retrieve update value
                q11++;
            }

            tab13 = tab13 + 1;
            tab13s = "\nProgress Notes\n" + jTextArea22.getText();
        } else {

            String update = "Notes: " + pNotes + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q11; m++) {
                if (m == group1) {
                    //to update value
                    pnsub[group1][0] = pNotes;
                    //to update value
                    tsttab13[group1] = "\n" + m + ") " + update;
                }
            }

            jTextArea22.setText("");
            for (int p1 = 0; p1 < q11; p1++) {
                if ("".equals(tsttab13[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea22.append(tsttab13[p1]);
            }

            jTextArea7.append("\nProgress Notes\n" + jTextArea22.getText());
            tab13s = "\nProgress Notes\n" + jTextArea22.getText();
            v = v - 1;
        }
    }//GEN-LAST:event_PN_accptBtnActionPerformed

    private void txt_pNotesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pNotesKeyReleased
        if(checkPatient()) return;
    }//GEN-LAST:event_txt_pNotesKeyReleased

    private void btnSrcDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcDiagnosisActionPerformed
//        if(checkPatient()) return;
        Searching.searchDiagnosis(this);
    }//GEN-LAST:event_btnSrcDiagnosisActionPerformed

    private void btn_diagnosisClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_diagnosisClearActionPerformed
        // TODO add your handling code here:
        txt_diagnosisSearch.setText("");
        cbx_dSeverity.setSelectedIndex(0);
        txt_date1.setDate(null);

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        lbx_diagnosisSearch.setModel(listModel);
    }//GEN-LAST:event_btn_diagnosisClearActionPerformed

    private void btn_diagnosisAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_diagnosisAcceptActionPerformed
        if(checkPatient()) return;
        // TODO add your handling code here:
        accept_btn_dgs();
    }//GEN-LAST:event_btn_diagnosisAcceptActionPerformed

    private void txt_diagnosisSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diagnosisSearchKeyReleased
        // TODO add your handling code here:
//        if(checkPatient()) return;
        Searching.searchDiagnosis(this);
    }//GEN-LAST:event_txt_diagnosisSearchKeyReleased

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        // TODO add your handling code here:
        jRadioButton1.setSelected(false);
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        // TODO add your handling code here:
        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void tbl_dgsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dgsMouseClicked
        try {
            if (checkPatient()) {
                return;
            }
            int rowIndex = tbl_dgs.getSelectedRow();
            String type = (String) tbl_dgs.getValueAt(rowIndex, 0);
            String date1 = (String) tbl_dgs.getValueAt(rowIndex, 1);
            String diagnosis = (String) tbl_dgs.getValueAt(rowIndex, 2);
            String severity = (String) tbl_dgs.getValueAt(rowIndex, 3);
            String site = (String) tbl_dgs.getValueAt(rowIndex, 4);
            String laterality = (String) tbl_dgs.getValueAt(rowIndex, 5);
            String comment = (String) tbl_dgs.getValueAt(rowIndex, 6);
            if(type.equals("Final")) {
                jRadioButton1.setSelected(true);
                jRadioButton2.setSelected(false);
            } else {
                jRadioButton1.setSelected(false);
                jRadioButton2.setSelected(true);
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(date1);
            txt_date1.setDate(date);
            txt_diagnosisSearch.setText(diagnosis);
            cbx_dSeverity.setSelectedItem(severity);
            dbx_site.setSelectedItem(site);
            dbx_laterality.setSelectedItem(laterality);
            txt_diagComment.setText(comment);
        } catch (Exception ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_dgsMouseClicked

    private void jPanel69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel69MouseClicked

        System.out.println(evt.getX() + "," + evt.getY());
        int pos = 0;
        int x = evt.getX();
        int y = evt.getY();
        int data[] = {0, 0, 0, 0};
        boolean cek = true;
        if ((x >= 82 && y >= 91) && (x <= 126 && y <= 120)) {
            //JOptionPane.showMessageDialog(null, "Tekak");
            data[0] = 82;
            data[1] = 91;
            data[2] = 126-82;
            data[3] = 120-91;
            pos = 0;
            txt_tekak.setFocusable(true);
        } else if ((x >= 105 && y >= 188) && (x <= 123 && y <= 217)) {
            //JOptionPane.showMessageDialog(null, "Jantung");
            data[0] = 105;
            data[1] = 188;
            data[2] = 123-105;
            data[3] = 217-188;
            pos = 1;
            txt_jantung.setFocusable(true);
        } else if ((x >= 65 && y >= 163) && (x <= 103 && y <= 225)) {
            //JOptionPane.showMessageDialog(null, "Peparu Kanan");
            data[0] = 65;
            data[1] = 163;
            data[2] = 103-65;
            data[3] = 225-163;
            pos = 2;
            txt_peparu_kanan.setFocusable(true);
        } else if ((x >= 19 && y >= 146) && (x <= 40 && y <= 173)) {
            //JOptionPane.showMessageDialog(null, "Bahu Kanan");
            data[0] = 19;
            data[1] = 146;
            data[2] = 40-19;
            data[3] = 173-146;
            pos = 3;
            txt_bahu_kanan.setFocusable(true);
        } else if ((x >= 162 && y >= 146) && (x <= 188 && y <= 173)) {
            //JOptionPane.showMessageDialog(null, "Bahu Kiri");
            data[0] = 162;
            data[1] = 146;
            data[2] = 188-162;
            data[3] = 173-146;
            pos = 4;
            txt_bahu_kiri.setFocusable(true);
        } else if ((x >= 46 && y >= 281) && (x <= 154 && y <= 349)) {
            //JOptionPane.showMessageDialog(null, "Perut");
            data[0] = 46;
            data[1] = 281;
            data[2] = 154-46;
            data[3] = 349-281;
            pos = 5;
            txt_perut.setFocusable(true);
        } else if ((x >= 75 && y >= 10) && (x <= 131 && y <= 33)) {
            //JOptionPane.showMessageDialog(null, "Kepala");
            data[0] = 75;
            data[1] = 10;
            data[2] = 131-75;
            data[3] = 33-10;
            pos = 6;
            txt_kepala.setFocusable(true);
        } else if ((x >= 97 && y >= 53) && (x <= 111 && y <= 62)) {
            //JOptionPane.showMessageDialog(null, "Hidung");
            data[0] = 97;
            data[1] = 53;
            data[2] = 111-97;
            data[3] = 62-53;
            pos = 7;
            txt_hidung.setFocusable(true);
        } else if ((x >= 93 && y >= 70) && (x <= 115 && y <= 82)) {
            //JOptionPane.showMessageDialog(null, "Mulut");
            data[0] = 93;
            data[1] = 70;
            data[2] = 115-93;
            data[3] = 82-70;
            pos = 8;
            txt_mulut.setFocusable(true);
        } else if ((x >= 62 && y >= 47) && (x <= 75 && y <= 69)) {
            //JOptionPane.showMessageDialog(null, "Telinga Kanan");
            data[0] = 62;
            data[1] = 47;
            data[2] = 75-62;
            data[3] = 69-47;
            pos = 9;
            txt_telinga_kanan.setFocusable(true);
        } else {
            cek = false;
        }
        if(cek) {
            jPanel69.remove(kotak[pos]);
            jPanel69.remove(panel);
            repaint();
            if (isAnatomy[pos]) {
                kotak[pos] = new MyRectangleJPanel(0, 0, 0, 0);
                isAnatomy[pos] = false;
            } else {
                kotak[pos] = new MyRectangleJPanel(data[0], data[1], data[2], data[3]);
                isAnatomy[pos] = true;
            }
            kotak[pos].setSize(600, 400);
            kotak[pos].setBackground(new Color(100, 100, 100, 0));
            jPanel69.add(kotak[pos]);
            addImage();
        }
    }//GEN-LAST:event_jPanel69MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txt_tekak.setText("");
        txt_jantung.setText("");
        txt_peparu_kanan.setText("");
        txt_bahu_kanan.setText("");
        txt_bahu_kiri.setText("");
        txt_perut.setText("");
        txt_kepala.setText("");
        txt_hidung.setText("");
        txt_mulut.setText("");
        txt_telinga_kanan.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (checkPatient()) {
            return;
        }
        accept_button_pem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_pulseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pulseKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pulseKeyReleased

    private void txt_pulse1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pulse1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pulse1KeyReleased

    private void txt_pulse2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pulse2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pulse2KeyReleased

    private void txt_diastolic2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diastolic2KeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_diastolic2, 0, 200);
    }//GEN-LAST:event_txt_diastolic2KeyReleased

    private void txt_systolic2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolic2KeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_systolic2, 0, 300);
    }//GEN-LAST:event_txt_systolic2KeyReleased

    private void txt_diastolic1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diastolic1KeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_diastolic1, 0, 200);
    }//GEN-LAST:event_txt_diastolic1KeyReleased

    private void txt_systolic1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolic1KeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_systolic1, 0, 300);
    }//GEN-LAST:event_txt_systolic1KeyReleased

    private void txt_diastolicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diastolicKeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_diastolic, 0, 200);
    }//GEN-LAST:event_txt_diastolicKeyReleased

    private void txt_systolicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolicKeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_systolic, 0, 300);
    }//GEN-LAST:event_txt_systolicKeyReleased

    private void btn_calculateBmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculateBmiActionPerformed
        // TODO add your handling code here:
        double height = Double.parseDouble(txt_height.getText().trim());
        double weight = Double.parseDouble(txt_weight.getText().trim());
        setCalcBMI(height, weight);
    }//GEN-LAST:event_btn_calculateBmiActionPerformed

    private void btn_calculateBmiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_calculateBmiMouseClicked

    }//GEN-LAST:event_btn_calculateBmiMouseClicked

    private void btn_vitalSignAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vitalSignAcceptActionPerformed

        if(checkPatient()) return;
        boolean A = false;
        boolean B = false;
        boolean A1 = false;
        boolean B1 = false;
        boolean A2 = false;
        boolean B2 = false;
        boolean C = false;
        boolean C1 = false;
        boolean C2 = false;
        A = txt_height.getText().equals("");
        B = txt_weight.getText().equals("");
        if((A && !B) || (!A && B)){
            J.o("Blank", "Don't leave height/weight blank!!", 0);
            return;
        }
        A = txt_systolic.getText().equals("");
        B = txt_diastolic.getText().equals("");
        C = txt_pulse.getText().equals("");
        A1 = txt_systolic1.getText().equals("");
        B1 = txt_diastolic1.getText().equals("");
        C1 = txt_pulse1.getText().equals("");
        A2 = txt_systolic2.getText().equals("");
        B2 = txt_diastolic2.getText().equals("");
        C2 = txt_pulse2.getText().equals("");
        if(!((!A && !B && !C) || (A && B && C))) {
            J.o("Blank", "Don't leave systolic/diastolic/pulse blank!!", 0);
            return;
        }
        if(!((!A1 && !B1 && !C1) || (A1 && B1 && C1))) {
            J.o("Blank", "Don't leave systolic/diastolic/pulse blank!!", 0);
            return;
        }
        if(!((!A2 && !B2 && !C2) || (A2 && B2 && C2))) {
            J.o("Blank", "Don't leave systolic/diastolic/pulse blank!!", 0);
            return;
        }
        accept_button_vts();

    }//GEN-LAST:event_btn_vitalSignAcceptActionPerformed

    private void btn_vitalSignClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vitalSignClearActionPerformed
        // TODO add your handling code here:
        txt_height.setText("");
        txt_weight.setText("");
        txt_bmi.setText("");
        txt_weightStatus.setText("");
        txt_headCircumference.setText("");
        txt_temperature.setText("");
        txt_pulse.setText("");
        txt_pulse1.setText("");
        txt_pulse2.setText("");
        txt_systolic.setText("");
        txt_systolic1.setText("");
        txt_systolic2.setText("");
        txt_diastolic.setText("");
        txt_diastolic1.setText("");
        txt_diastolic2.setText("");
    }//GEN-LAST:event_btn_vitalSignClearActionPerformed

    private void btn_vitalSignClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_vitalSignClearMouseClicked

    }//GEN-LAST:event_btn_vitalSignClearMouseClicked

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        // TODO add your handling code here:
        txt_weight.setText("" + jSlider2.getValue());
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        if(checkPatient()) return;
        txt_height.setText("" + jSlider1.getValue());
    }//GEN-LAST:event_jSlider1StateChanged

    private void txt_weightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_weightKeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_weight, 0, 1000);
    }//GEN-LAST:event_txt_weightKeyReleased

    private void txt_heightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_heightKeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_height, 0, 400);
    }//GEN-LAST:event_txt_heightKeyReleased

    private void txt_heightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_heightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_heightActionPerformed

    private void tbl_dabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dabMouseClicked
        try {
            if (checkPatient()) {
                return;
            }
            int rowIndex = tbl_dab.getSelectedRow();
            String problem = (String) tbl_dab.getValueAt(rowIndex, 0);
            String tarikh = (String) tbl_dab.getValueAt(rowIndex, 1);
            String comment = (String) tbl_dab.getValueAt(rowIndex, 2);
            txt_disabilityType.setText(problem);
            try {
                if (tarikh.equals("")) {
                    tarikh = "0000-00-00";
                }
            } catch (Exception eex) {
                tarikh = "0000-00-00";
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(tarikh);
            txt_dDate1.setDate(date);
            txt_dComments.setText(comment);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tbl_dabMouseClicked

    private void btn_dClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dClearActionPerformed
        // TODO add your handling code here:
        txt_disabilityType.setText("");
        txt_dDate1.setDate(null);
        txt_dComments.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        lbx_disabilityType.setModel(listModel);
    }//GEN-LAST:event_btn_dClearActionPerformed

    private void btn_dAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dAcceptActionPerformed
        // TODO add your handling code here:

        if(checkPatient()) return;
        accept_btn_dab();
    }//GEN-LAST:event_btn_dAcceptActionPerformed

    private void lbx_disabilityTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_disabilityTypeMouseClicked
        // TODO add your handling code here:
        txt_disabilityType.setText(lbx_disabilityType.getSelectedValue().toString());
    }//GEN-LAST:event_lbx_disabilityTypeMouseClicked

    private void btnSrcDisabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcDisabilityActionPerformed
        if(checkPatient()) return;
        Searching.searchDAB1(this);
    }//GEN-LAST:event_btnSrcDisabilityActionPerformed

    private void btn_immClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_immClearActionPerformed
        // TODO add your handling code here:
        txt_immSearch.setText("");
        txt_immComment.setText("");
        txt_immDate1.setDate(null);

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        lbx_immSearch.setModel(listModel);
    }//GEN-LAST:event_btn_immClearActionPerformed

    private void btn_immAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_immAcceptActionPerformed

        if(checkPatient()) return;
        accept_btn_imu();
    }//GEN-LAST:event_btn_immAcceptActionPerformed

    private void lbx_immSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_immSearchMouseClicked
        // TODO add your handling code here:
        txt_immSearch.setText(lbx_immSearch.getSelectedValue().toString());
    }//GEN-LAST:event_lbx_immSearchMouseClicked

    private void btnSrcImmunisationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcImmunisationActionPerformed
        if(checkPatient()) return;
        Searching.searchImmune(this);
    }//GEN-LAST:event_btnSrcImmunisationActionPerformed

    private void txt_immSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_immSearchKeyReleased
        if(checkPatient()) return;
        Searching.searchImmune(this);
    }//GEN-LAST:event_txt_immSearchKeyReleased

    private void tbl_imuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_imuMouseClicked
        try {
            if (checkPatient()) {
                return;
            }
            int rowIndex = tbl_imu.getSelectedRow();
            String problem = (String) tbl_imu.getValueAt(rowIndex, 0);
            String comment = (String) tbl_imu.getValueAt(rowIndex, 1);
            String tarikh = (String) tbl_imu.getValueAt(rowIndex, 2);
            txt_immSearch.setText(problem);
            txt_immComment.setText(comment);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(tarikh);
            txt_immDate1.setDate(date);
        } catch (Exception ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_imuMouseClicked

    private void btn_allergyClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allergyClearActionPerformed
        // TODO add your handling code here:
        txt_allergySearch.setText("");
        txt_allergyDate2.setDate(null);
        txt_allergyComments.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        lbx_allergySearch.setModel(listModel);
    }//GEN-LAST:event_btn_allergyClearActionPerformed

    private void btn_allergyAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allergyAcceptActionPerformed
        // TODO add your handling code here:
        if(checkPatient()) return;
        accept_btn_alg();
    }//GEN-LAST:event_btn_allergyAcceptActionPerformed

    private void lbx_allergySearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_allergySearchMouseClicked
        // TODO add your handling code here:
        txt_allergySearch.setText(lbx_allergySearch.getSelectedValue().toString());
    }//GEN-LAST:event_lbx_allergySearchMouseClicked

    private void btnSrcAllergyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcAllergyActionPerformed
        if(checkPatient()) return;
        Searching.searchAllergy1(this);
    }//GEN-LAST:event_btnSrcAllergyActionPerformed

    private void txt_allergySearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_allergySearchKeyReleased
        if(checkPatient()) return;
        Searching.searchAllergy1(this);
    }//GEN-LAST:event_txt_allergySearchKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(checkPatient()) return;

        String pmino = txt_pPmiNo.getText();
        String blood = cmbBloodType.getSelectedItem().toString();
        boolean stat_online = false;
        boolean stat_offline = false;

        ArrayList<String> column = new ArrayList<String>();
        column.add("BLOOD_TYPE");

        ArrayList<String> data = new ArrayList<String>();
        data.add(blood);

        //LongRunProcess.check_network2();
        //if (Session.getPrev_stat()) { //online
            try {
                //                ArrayList<String> listOnline = Func.readXML("online");
                //                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
                //                Message impl = (Message) myRegistry.lookup("myMessage");
                stat_online = DBConnection.getImpl().updatePmsPatientBiodata(pmino, column, data);
            } catch (Exception e) { //offline
                e.printStackTrace();
            }
            //}
        stat_offline = DBConnection.updatePmsPatientBiodata(pmino, column, data);

        if (stat_online && stat_offline) {
            J.o("Update Success", "Update Success ..", 1);
        } else if (!(stat_online && stat_offline)) {
            J.o("Update Failed", "Update Failed!!", 0);
        } else {
            if (stat_online) {
                J.o("Update Online Success", "Update Online Success ..", 1);
            } else {
                J.o("Update Online Failed", "Update Online Failed!!", 0);
            }
            if (stat_offline) {
                J.o("Update Offline Success", "Update Offline Success ..", 1);
            } else {
                J.o("Update Offline Failed", "Update Offline Failed!!", 0);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void BG_clrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG_clrBtnActionPerformed
        // TODO add your handling code here:
        txt_BldCmmnt.setText("");
        rbtn_Bpositive.setSelected(true);
        rbtn_Bnegative.setSelected(false);
        rbtn_Bnormal.setSelected(true);
        rbtn_Bdeficient.setSelected(false);
    }//GEN-LAST:event_BG_clrBtnActionPerformed

    private void BG_accptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG_accptBtnActionPerformed
        // TODO add your handling code here:
        if(checkPatient()) return;
        jTextArea7.setText("");
        //        String BloodType = (String) txtBloodType.getText();
        String BloodType = (String) cmbBloodType.getSelectedItem();
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
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }

        if (v == 0) {
            if (tab12 > 0 || tab12 == 0) {
                jTextArea21.append("\n" + (q10+1) + ") " +
                    "Blood Type: " + BloodType +
                    ",\nRhesus: " + selectedRhesus +
                    ", G6PD : " + selectedG6PD +
                    ", Comment: " + BloodCmnt + "\n");
                tab12s = "\n" + (q10+1) + ") " + "Blood Type: " + BloodType + ",\nRhesus: " + selectedRhesus + ", G6PD : " + selectedG6PD + ", Comment: " + BloodCmnt + "\n";
                tsttab12[q10] = tab12s;
                jTextArea7.append("\nBlood Group\n" + jTextArea21.getText());

                String data[] = {"Blood Group / G6PD",
                    "Blood Type: " + Func.trim(BloodType),
                    "Rhesus: " + Func.trim(selectedRhesus),
                    "G6PD: " + Func.trim(selectedG6PD),
                    "Comment: " + Func.trim(BloodCmnt)
                };
                setData(data, 6); //6 for Blood Group

                //to retrieve update value
                bldsub[q10][0] = BloodType;
                bldsub[q10][1] = selectedRhesus;
                bldsub[q10][2] = selectedG6PD;
                bldsub[q10][3] = BloodCmnt;

                //to retrieve update value
                q10++;
            }

            tab12 = tab12 + 1;
            tab12s = "\nBlood Group\n" + jTextArea21.getText();
        } else {

            String update = "Blood Type: " + BloodType + ",\nRhesus: " + selectedRhesus + ", G6PD : " + selectedG6PD + ", Comment: " + BloodCmnt + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q10; m++) {
                if (m == group1) {
                    //to update value
                    bldsub[group1][0] = BloodType;
                    bldsub[group1][1] = selectedRhesus;
                    bldsub[group1][2] = selectedG6PD;
                    bldsub[group1][3] = BloodCmnt;
                    //to update value
                    tsttab12[group1] = "\n" + m + ") " + update;
                }
            }

            jTextArea21.setText("");
            for (int p1 = 0; p1 < q10; p1++) {
                if (tsttab12[p1] == "") {
                    p1 = p1 + 1;
                }

                jTextArea21.append(tsttab12[p1]);
            }

            jTextArea7.append("\nBlood Group\n" + jTextArea21.getText());
            tab12s = "\nBlood Group\n" + jTextArea21.getText();
            v = v - 1;
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }

    }//GEN-LAST:event_BG_accptBtnActionPerformed

    private void txt_BldCmmntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BldCmmntKeyReleased
        if(checkPatient()) return;
    }//GEN-LAST:event_txt_BldCmmntKeyReleased

    private void tbl_shMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_shMouseClicked
        try {
            if (checkPatient()) {
                return;
            }
            int rowIndex = tbl_sh.getSelectedRow();
            String problem = (String) tbl_sh.getValueAt(rowIndex, 0);
            String since = (String) tbl_sh.getValueAt(rowIndex, 1);
            String comment = (String) tbl_sh.getValueAt(rowIndex, 2);
            S.oln("SOH: "+problem+"|"+since+"|"+comment);
            txt_socialProblem.setText(problem);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(since);
            txt_socialDate1.setDate(date);
            txt_socialComment.setText(comment);
        } catch (Exception ex) {
            Logger.getLogger(Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_shMouseClicked

    private void btn_sclClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sclClearActionPerformed
        // TODO add your handling code here:
        txt_socialProblem.setText("");
        txt_socialDate1.setDate(null);
        txt_socialComment.setText("");

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        lbx_socialProblem.setModel(listModel);
    }//GEN-LAST:event_btn_sclClearActionPerformed

    private void btn_sclAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sclAcceptActionPerformed
        // TODO add your handling code here:

        if(checkPatient()) return;
        accept_btn_soh();
    }//GEN-LAST:event_btn_sclAcceptActionPerformed

    private void lbx_socialProblemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_socialProblemMouseClicked
        // TODO add your handling code here:
        txt_socialProblem.setText(lbx_socialProblem.getSelectedValue().toString());
    }//GEN-LAST:event_lbx_socialProblemMouseClicked

    private void btnSrcSocialHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcSocialHistoryActionPerformed
        if(checkPatient()) return;
        Searching.searchSH1(this);
    }//GEN-LAST:event_btnSrcSocialHistoryActionPerformed

    private void txt_socialProblemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_socialProblemKeyReleased
        if(checkPatient()) return;
        Searching.searchSH1(this);
    }//GEN-LAST:event_txt_socialProblemKeyReleased

    private void FH_clrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH_clrBtnActionPerformed
        // TODO add your handling code here:
        txt_FHSearch.setText("");
        //[0347 01042013] txt_FHDiagnosis.setText("");
        FH_Comments.setText("");
    }//GEN-LAST:event_FH_clrBtnActionPerformed

    private void FH_accptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH_accptBtnActionPerformed
        if(checkPatient()) return;
        // TODO add your handling code here:

        accept_btn_FH();
    }//GEN-LAST:event_FH_accptBtnActionPerformed

    private void lbx_FHSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_FHSearchMouseClicked
        String li = lbx_FHSearch.getSelectedValue().toString();
        txt_FHSearch.setText(li);
    }//GEN-LAST:event_lbx_FHSearchMouseClicked

    private void btnSrcSocialHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcSocialHistory1ActionPerformed
        if(checkPatient()) return;
        Searching.searchFH1(this);
    }//GEN-LAST:event_btnSrcSocialHistory1ActionPerformed

    private void txt_FHSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_FHSearchKeyReleased
        if(checkPatient()) return;
        Searching.searchFH1(this);
    }//GEN-LAST:event_txt_FHSearchKeyReleased

    private void tbl_fmhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_fmhMouseClicked
        if (checkPatient()) {
            return;
        }
        int rowIndex = tbl_cc.getSelectedRow();
        String problem = (String) tbl_cc.getValueAt(rowIndex, 0);
        String relationship = (String) tbl_cc.getValueAt(rowIndex, 1);
        String comment = (String) tbl_cc.getValueAt(rowIndex, 2);
        txt_FHSearch.setText(problem);
        FH_Relationship.setSelectedItem(relationship);
        FH_Comments.setText(comment);
    }//GEN-LAST:event_tbl_fmhMouseClicked

    private void PMH_clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMH_clearBtnActionPerformed
        // TODO add your handling code here:
        txt_PMHSearch.setText("");
        //[0304 01042013] txt_PMHDiagnosis.setText("");
        txt_PMHComment.setText("");
        rbtn_cActive1.setSelected(true);
        rbtn_cInactive1.setSelected(false);
    }//GEN-LAST:event_PMH_clearBtnActionPerformed

    private void PMH_AccptrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMH_AccptrBtnActionPerformed

        if(checkPatient()) return;
        // TODO add your handling code here:
        accept_button_PMH();
    }//GEN-LAST:event_PMH_AccptrBtnActionPerformed

    private void lbx_PMHSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_PMHSearchMouseClicked
        String li = lbx_PMHSearch.getSelectedValue().toString();
        txt_PMHSearch.setText(li);
    }//GEN-LAST:event_lbx_PMHSearchMouseClicked

    private void btnSrcComplaint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcComplaint1ActionPerformed
        if(checkPatient()) return;
        Searching.searchPMH1(this);
    }//GEN-LAST:event_btnSrcComplaint1ActionPerformed

    private void txt_PMHSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PMHSearchKeyReleased
        if(checkPatient()) return;
        Searching.searchPMH1(this);
    }//GEN-LAST:event_txt_PMHSearchKeyReleased

    private void tbl_pmhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pmhMouseClicked
        if (checkPatient()) {
            return;
        }
        int rowIndex = tbl_pmh.getSelectedRow();
        String problem = (String) tbl_pmh.getValueAt(rowIndex, 0);
        String status = (String) tbl_pmh.getValueAt(rowIndex, 1);
        String comment = (String) tbl_pmh.getValueAt(rowIndex, 2);
        txt_PMHSearch.setText(problem);
        if(status.equals("Active")) {
            rbtn_cActive1.setSelected(true);
            rbtn_cInactive1.setSelected(false);
        } else {
            rbtn_cActive1.setSelected(false);
            rbtn_cInactive1.setSelected(true);
        }
        txt_PMHComment.setText(comment);
    }//GEN-LAST:event_tbl_pmhMouseClicked

    private void HPI_ClrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPI_ClrBtnActionPerformed
        // TODO add your handling code here:
        HPI_DetailstxtArea.setText("");
    }//GEN-LAST:event_HPI_ClrBtnActionPerformed

    private void btn_HPIAccptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HPIAccptActionPerformed
        // TODO add your handling code here:

        if(checkPatient()) return;
        accept_button_HPI();

    }//GEN-LAST:event_btn_HPIAccptActionPerformed

    private void HPI_DetailstxtAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HPI_DetailstxtAreaKeyReleased
        if(checkPatient()) return;
    }//GEN-LAST:event_HPI_DetailstxtAreaKeyReleased

    private void tbl_HPI1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HPI1MouseClicked
        if(checkPatient()) return;
        int rowIndex = tbl_HPI1.getSelectedRow();
        String details = (String) tbl_HPI1.getValueAt(rowIndex, 1);
        HPI_DetailstxtArea.setText(details);
    }//GEN-LAST:event_tbl_HPI1MouseClicked

    private void btn_complaintClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_complaintClearActionPerformed
        // TODO add your handling code here:
        txt_complaintComment.setText("");
        txt_duration.setText("");
        txt_complaintSearch.setText("");
        cbx_cSeverity.setSelectedIndex(0);
        cbx_site.setSelectedIndex(0);
        cbx_duration.setSelectedIndex(0);
        cbx_laterality.setSelectedIndex(0);
        cbx_cSeverity.setSelectedIndex(0);

        listModel = new javax.swing.DefaultListModel();
        listModel.addElement(null);
        lbx_complaintSearch.setModel(listModel);
    }//GEN-LAST:event_btn_complaintClearActionPerformed

    private void btn_complaintAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_complaintAcceptActionPerformed

        if(checkPatient()) return;
        accept_button_ccomplaint();

        //reset drug name list box
        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        lbx_complaintSearch.setModel(listModel);
    }//GEN-LAST:event_btn_complaintAcceptActionPerformed

    private void lbx_complaintSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_complaintSearchMouseClicked
        // TODO add your handling code here:
        txt_complaintSearch.setText(lbx_complaintSearch.getSelectedValue().toString());
    }//GEN-LAST:event_lbx_complaintSearchMouseClicked

    private void btnSrcComplaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcComplaintActionPerformed
//        if(checkPatient()) return;
        Searching.searchCCN1(this);
    }//GEN-LAST:event_btnSrcComplaintActionPerformed

    private void txt_complaintSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_complaintSearchKeyReleased
        // TODO add your handling code here:
//        if(checkPatient()) return;
        Searching.searchCCN1(this);
    }//GEN-LAST:event_txt_complaintSearchKeyReleased

    private void tbl_ccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ccMouseClicked
        if(checkPatient()) return;
        int rowIndex = tbl_cc.getSelectedRow();
        String problem = (String) tbl_cc.getValueAt(rowIndex, 0);
        String severe = (String) tbl_cc.getValueAt(rowIndex, 1);
        String comment = (String) tbl_cc.getValueAt(rowIndex, 2);
        String duration = (String) tbl_cc.getValueAt(rowIndex, 3);
        String side = (String) tbl_cc.getValueAt(rowIndex, 4);
        String laterality = (String) tbl_cc.getValueAt(rowIndex, 5);
        txt_complaintSearch.setText(problem);
        cbx_cSeverity.setSelectedItem(severe);
        txt_complaintComment.setText(comment);
        txt_duration.setText(duration);
        cbx_site.setSelectedItem(side);
        cbx_laterality.setSelectedItem(laterality);
    }//GEN-LAST:event_tbl_ccMouseClicked

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed

//        String message = "Are you sure want to navigate to Main Menu?";
//        String title = "Consultation";
//        int reply = JOptionPane.showConfirmDialog(null, message, title,
//            JOptionPane.YES_NO_OPTION);
//        if (reply == JOptionPane.YES_OPTION) {
            CheckNewPatient.active = false;
            System.out.println("\nSign Out from Consultation ");
            MainPage mainPage = new MainPage(id);
            mainPage.setVisible(true);
            this.vph.dispose();
            this.dispose();
//        }
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked

    }//GEN-LAST:event_btn_exitMouseClicked

    protected QueueList que = new QueueList(this);
    
    private void btn_sPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sPatientActionPerformed

        // start time
        long startTime = System.currentTimeMillis();
        
        //LongRunProcess.change_status_network();
        que.setVisible(false);
        que.setState(que.NORMAL);
        que.toFront();
        que.setVisible(true);
        if(que.isVisible()) {
            que.list_Queue(1, "");
        }
        
        // end time
        long endTime = System.currentTimeMillis();
        long diffTime = endTime - startTime;
        String title = "LIST PATIENTS FROM QUEUE";
        boolean status = DBConnection.captureResponseTime(title, diffTime);
        
        System.out.println("Title: " + title);
        System.out.println("Status Response Time: " + status);
        System.out.println("Diff Time: " + diffTime);
        
        //jFrame1.setVisible(true);
    }//GEN-LAST:event_btn_sPatientActionPerformed

    private void btn_sPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sPatientMouseClicked

    }//GEN-LAST:event_btn_sPatientMouseClicked

    public static GCS_GUI gcs_gui = new GCS_GUI();
    public static PGCS_GUI pgcs_gui = new PGCS_GUI();
    
    public void clearTableGCS() {
        for (int i = 0; i < tbl_gcs.getModel().getRowCount(); i++) {
            for (int j = 0; j < tbl_gcs.getModel().getColumnCount(); j++) {
                tbl_gcs.getModel().setValueAt("", i, j);
            }
        }
    }
    
    public void clearTablePGCS() {
        for (int i = 0; i < tbl_pgcs.getModel().getRowCount(); i++) {
            for (int j = 0; j < tbl_pgcs.getModel().getColumnCount(); j++) {
                tbl_pgcs.getModel().setValueAt("", i, j);
            }
        }
    }
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        gcs_gui.setParent(this);
        gcs_gui.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        txt_gcs_points.setText("");
        txt_gcs_result.setText("");
        clearTableGCS();
    }//GEN-LAST:event_jButton12ActionPerformed

    public static void resizeTbl_gcs(int num_rows) {
        Object obj[][] = new Object[num_rows][2];
        tbl_gcs.setModel(new javax.swing.table.DefaultTableModel(
            obj,
            new String [] {
                "Response", "Point"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public static void resizeTbl_pgcs(int num_rows) {
        Object obj[][] = new Object[num_rows][2];
        tbl_pgcs.setModel(new javax.swing.table.DefaultTableModel(
            obj,
            new String [] {
                "Response", "Point"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        txt_pgcs_points.setText("");
        txt_pgcs_result.setText("");
        clearTablePGCS();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        pgcs_gui.setParent(this);
        pgcs_gui.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txt_temperatureKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_temperatureKeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_temperature, 0, 100);
    }//GEN-LAST:event_txt_temperatureKeyReleased

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider4StateChanged
        // TODO add your handling code here:
        txt_temperature.setText("" + jSlider4.getValue());
    }//GEN-LAST:event_jSlider4StateChanged

    private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider3StateChanged
        // TODO add your handling code here:
        txt_headCircumference.setText("" + jSlider3.getValue());
    }//GEN-LAST:event_jSlider3StateChanged

    private void txt_headCircumferenceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_headCircumferenceKeyReleased
        if(checkPatient()) return;
        checkNumber(2, txt_headCircumference, 0, 200);
    }//GEN-LAST:event_txt_headCircumferenceKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if (checkPatient()) {
            return;
        }
        accept_button_pem();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void set_pain_result(int pain_scale) {
        String pain_result = "No Pain.";
        switch (pain_scale) {
            case 1:
            case 2:
                pain_result = "Mild";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                pain_result = "Moderate";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                pain_result = "Severe";
                break;
        }
        txt_pain_result.setText(pain_result);
    }
    
    private void cbx_pain_scaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_pain_scaleItemStateChanged
        // TODO add your handling code here:
        int pain_scale = Integer.parseInt((String) cbx_pain_scale.getSelectedItem());
        set_pain_result(pain_scale);
    }//GEN-LAST:event_cbx_pain_scaleItemStateChanged

    private void lbx_diagnosisSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbx_diagnosisSearchMouseClicked
        // TODO add your handling code here:
        txt_diagnosisSearch.setText((String) lbx_diagnosisSearch.getSelectedValue());
    }//GEN-LAST:event_lbx_diagnosisSearchMouseClicked

    private void txt_disabilityTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_disabilityTypeKeyReleased
        // TODO add your handling code here:
        if(checkPatient()) return;
        Searching.searchDAB1(this);
    }//GEN-LAST:event_txt_disabilityTypeKeyReleased

    private void rbtn_mcts_mcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtn_mcts_mcMouseClicked
        // TODO add your handling code here:
        rbtn_mcts_ts.setSelected(false);
//        if (isGenerateMCTS) {
            btn_generate_mc.setEnabled(true);
            btn_generate_timeslip.setEnabled(false);
//        }
    }//GEN-LAST:event_rbtn_mcts_mcMouseClicked

    private void rbtn_mcts_tsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtn_mcts_tsMouseClicked
        // TODO add your handling code here:
        rbtn_mcts_mc.setSelected(false);
        btn_generate_mc.setEnabled(false);
        btn_generate_timeslip.setEnabled(true);
    }//GEN-LAST:event_rbtn_mcts_tsMouseClicked

    protected ViewPatientHistory vph = new ViewPatientHistory();
    
    private void btn_viewHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewHistoryActionPerformed
        // TODO add your handling code here:
        vph.setVisible(false);
        vph.setData(txt_pPmiNo.getText());
        vph.setVisible(true);
    }//GEN-LAST:event_btn_viewHistoryActionPerformed

    private void rbtn_gen_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtn_gen_searchMouseClicked
        // TODO add your handling code here:
        btn_plus.setEnabled(true);
        btn_substract.setEnabled(false);
        Searching.searchStatus = 1;
    }//GEN-LAST:event_rbtn_gen_searchMouseClicked

    private void rbtn_per_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtn_per_searchMouseClicked
        // TODO add your handling code here:
        btn_plus.setEnabled(false);
        btn_substract.setEnabled(true);
        Searching.searchStatus = 2;
    }//GEN-LAST:event_rbtn_per_searchMouseClicked

    private void btn_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plusActionPerformed
        // TODO add your handling code here:
        try {
            String searchcbx = txt_complaintSearch.getText();
            if (!Searching.isSearchCCN1(searchcbx)) {
                //J.o("Invalid", "Invalid Chief Complaint", 0);
                //return;
                
                // 1. get timestamp [year:month:day:hour:minute:seconds].
                Calendar n = Calendar.getInstance();
                String code = n.get(Calendar.YEAR)+""
                        //+n.get(Calendar.MONTH)+""
                        //+n.get(Calendar.DAY_OF_MONTH)+""
                        +n.get(Calendar.HOUR_OF_DAY)+""
                        +n.get(Calendar.MINUTE)+""
                        +n.get(Calendar.SECOND)+""
                        +n.get(Calendar.MILLISECOND)+"";
                
                // 2. append code 'S' with timestamp.
                String last_seq_num = "CCN"+code;
                
                // 3. add into CIS_PERSONALIZED_CODE.
                String sql = "INSERT INTO CIS_PERSONALIZED_CODE VALUES(?, ?) ";
                PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
                ps2.setString(1, last_seq_num);
                ps2.setString(2, searchcbx);
                ps2.execute();
                J.o("Add Success", "Add Success ..", 1);
                
            } else {
                String sql = "SELECT * FROM READCODE_CHIEF_COMPLAINT "
                        + "where UCASE(RCC_DESC) = UCASE(?) order by RCC_DESC ";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, searchcbx);
                ResultSet rs = ps.executeQuery();
                String code = "";
                if (rs.next()) {
                    code = rs.getString(1);
                    sql = "INSERT INTO CIS_PERSONALIZED_CODE VALUES(?, ?) ";
                    PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
                    ps2.setString(1, code);
                    ps2.setString(2, searchcbx);
                    ps2.execute();
                    J.o("Add Success", "Add Success ..", 1);
                } else {
                    J.o("Add Fail", "Add Fail !!", 0);
                }
            }
        } catch (Exception e) {
            J.o("Database Error", "Database error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_btn_plusActionPerformed

    private void btn_substractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_substractActionPerformed
        // TODO add your handling code here:
        try {
            String searchcbx = txt_complaintSearch.getText();
            if (!Searching.isSearchCCN1(searchcbx)) {
                J.o("Invalid", "Invalid Chief Complaint", 0);
                return;
            } else {
                String sql = "SELECT * FROM READCODE_CHIEF_COMPLAINT "
                        + "where UCASE(RCC_DESC) = UCASE(?) order by RCC_DESC ";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, searchcbx);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    sql = "DELETE FROM CIS_PERSONALIZED_CODE WHERE CPC_DESC = ? ";
                    PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
                    ps2.setString(1, searchcbx);
                    ps2.execute();
                    J.o("Remove Success", "Remove Success ..", 1);
                } else {
                    J.o("Remove Fail", "Remove Fail !!", 0);
                }
            }
        } catch (Exception e) {
            J.o("Database Error", "Database error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_btn_substractActionPerformed

    private void PN_clrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PN_clrBtnActionPerformed
        // TODO add your handling code here:
        txt_pNotes.setText("");
    }//GEN-LAST:event_PN_clrBtnActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        startingTime();
        loadDrug();
        endingTime("SYNC DRUG FROM CENTRAL SERVER");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTabbedPane5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane5MouseClicked
        // TODO add your handling code here:
        if(checkPatient()) { 
            jTabbedPane5.setSelectedIndex(0);
            return;
        }
        if (jTabbedPane5.getSelectedIndex() == 4) {
            jTabbedPane5.setSelectedIndex(0);
            new ObservationProcedure(txt_pPmiNo.getText()).setVisible(true);
        }
    }//GEN-LAST:event_jTabbedPane5MouseClicked

    private void rbtn_gen_search_dgsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtn_gen_search_dgsMouseClicked
        // TODO add your handling code here:
        btn_plus_dgs.setEnabled(true);
        btn_substract_dgs.setEnabled(false);
        Searching.searchStatus_dgs = 1;
    }//GEN-LAST:event_rbtn_gen_search_dgsMouseClicked

    private void rbtn_per_search_dgsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtn_per_search_dgsMouseClicked
        // TODO add your handling code here:
        btn_plus_dgs.setEnabled(false);
        btn_substract_dgs.setEnabled(true);
        Searching.searchStatus_dgs = 2;
    }//GEN-LAST:event_rbtn_per_search_dgsMouseClicked

    static File fi = new File(Login.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    static String par = fi.getParent()+"/";
//    static String par = "";
    public static final String fileNameDGS = par+"DGS";
    
    private void btn_plus_dgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plus_dgsActionPerformed
        // TODO add your handling code here:
        try {
            String searchcbx = txt_diagnosisSearch.getText();
            if (!Searching.isSearchDiagnosis(searchcbx)) {
                J.o("Invalid", "Invalid Diagnosis!", 0);
                return;
                
                // 1. get timestamp [year:month:day:hour:minute:seconds].
//                Calendar n = Calendar.getInstance();
//                String code = n.get(Calendar.YEAR)+""
//                        //+n.get(Calendar.MONTH)+""
//                        //+n.get(Calendar.DAY_OF_MONTH)+""
//                        +n.get(Calendar.HOUR_OF_DAY)+""
//                        +n.get(Calendar.MINUTE)+""
//                        +n.get(Calendar.SECOND)+""
//                        +n.get(Calendar.MILLISECOND)+"";
                
                // 2. append code 'S' with timestamp.
//                String last_seq_num = "DGS"+code;
                
                // 3. add into DGS file.
//                FileReadWrite frw = new FileReadWrite(this.fileNameDGS);
//                ArrayList<String> dgss = frw.read();
//                dgss.add(last_seq_num + "|" + searchcbx);
//                for (int j = 0; j < dgss.size(); j++) {
//                    boolean append = !(j == 0);
//                    frw.write(dgss.get(j), append);
//                    frw.write(true);
//                }
//                
//                J.o("Add Success", "Add Success ..", 1);
                
            } else {
                
                String sql = "SELECT * FROM icd10_codes "
                        + "where UCASE(icd10_desc) like UCASE(?) order by icd10_desc ";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, searchcbx);
                ResultSet rs = ps.executeQuery();
                String code = "";
                if (rs.next()) {
                    code = rs.getString(1);
//                    sql = "INSERT INTO CIS_PERSONALIZED_CODE VALUES(?, ?) ";
//                    PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
//                    ps2.setString(1, code);
//                    ps2.setString(2, searchcbx);
//                    ps2.execute();
                    
                    FileReadWrite frw = new FileReadWrite(this.fileNameDGS);
                    ArrayList<String> dgss = frw.read();
                    dgss.add(code + "|" + searchcbx);
                    for (int j = 0; j < dgss.size(); j++) {
                        boolean append = !(j == 0);
                        frw.write(dgss.get(j), append);
                        frw.write(true);
                    }
                    
                    J.o("Add Success", "Add Success ..", 1);
                } else {
                    J.o("Add Fail", "Add Fail !!", 0);
                }
            }
        } catch (Exception e) {
            J.o("Database Error", "Database error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_btn_plus_dgsActionPerformed

    private void btn_substract_dgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_substract_dgsActionPerformed
        // TODO add your handling code here:
        try {
            String searchcbx = txt_diagnosisSearch.getText();
            if (!Searching.isSearchDiagnosis(searchcbx)) {
                J.o("Invalid", "Invalid Diagnosis!", 0);
                return;
            } else {
                String sql = "SELECT * FROM icd10_codes "
                        + "where UCASE(icd10_desc) like UCASE(?) order by icd10_desc ";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, searchcbx);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String code = rs.getString(1);
//                    sql = "DELETE FROM CIS_PERSONALIZED_CODE WHERE CPC_DESC = ? ";
//                    PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql);
//                    ps2.setString(1, searchcbx);
//                    ps2.execute();
                    
                    FileReadWrite frw = new FileReadWrite(this.fileNameDGS);
                    ArrayList<String> dgss = frw.read();
                    int index = -1;
                    for (int j = 0; j < dgss.size(); j++) {
                        String codeFile = dgss.get(j).split("\\|")[0];
                        if (codeFile.equals(code)) {
                            index = j;
                        }
                    }
                    if (index != -1) {
                        dgss.remove(index);
                    }
                    for (int j = 0; j < dgss.size(); j++) {
                        boolean append = !(j == 0);
                        frw.write(dgss.get(j), append);
                        frw.write(true);
                    }
                    
                    J.o("Remove Success", "Remove Success ..", 1);
                } else {
                    J.o("Remove Fail", "Remove Fail !!", 0);
                }
            }
        } catch (Exception e) {
            J.o("Database Error", "Database error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_btn_substract_dgsActionPerformed

    public boolean checkPatient() {
        String pmiNo = txt_pName.getText();
        if (pmiNo.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a patient!");
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkPatient2() {
        String pmiNo = txt_pName.getText();
        if (pmiNo.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public String checkDiagnosis() {
        problemCode = "";
        problemDesc = "";
        for (int i = 0; i < max_row; i++) {
            if (note_array[i].equals("Diagnosis")) {
                //J.o("title", note_array[i + 3], 0);
                problemDesc = note_array[i + 3].split(": ")[1];
                try {
//                    tempQuery = "SELECT RCC_CODE FROM READCODE_CHIEF_COMPLAINT "
//                                    + "where UCASE(RCC_DESC) like UCASE(?) ";
                    tempQuery = "SELECT * FROM icd10_codes "
                            + "where UCASE(icd10_desc) like UCASE(?) ";
                    ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                    ps.setString(1, "%" + problemDesc + "%");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        problemCode = rs.getString("icd10_code");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        String probs = "|";
        if (problemCode.equals("") && problemDesc.equals("")) {
            JOptionPane.showMessageDialog(null, "Please insert patient's diagnosis!");
        } else {
            probs = problemCode + "|" + problemDesc;
        }
        return probs;
    }
    
    public String checkProblem() {
        problemCode = "";
        problemDesc = "";
        for(int i = 0; i < max_row; i++) {
            if(note_array[i].contains("C.Complaint")) {
                problemDesc = note_array[i+1].split(": ")[1];
                try {
                    tempQuery = "SELECT RCC_CODE FROM READCODE_CHIEF_COMPLAINT "
                                    + "where UCASE(RCC_DESC) like UCASE(?) ";
//                    tempQuery = "SELECT * FROM icd10_codes "
//                            + "where UCASE(icd10_desc) like UCASE(?) ";
                    ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                    ps.setString(1, "%"+problemDesc+"%");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        problemCode = rs.getString("RCC_CODE");
//                        problemCode = rs.getString("icd10_code");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                break;
            }
            if(note_array[i].equals("Diagnosis")) {
                problemDesc = note_array[i+3].split(": ")[1];
                try {
//                    tempQuery = "SELECT RCC_CODE FROM READCODE_CHIEF_COMPLAINT "
//                                    + "where UCASE(RCC_DESC) like UCASE(?) ";
                    tempQuery = "SELECT * FROM icd10_codes "
                            + "where UCASE(icd10_desc) like UCASE(?) ";
                    ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                    ps.setString(1, "%"+problemDesc+"%");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        problemCode = rs.getString("icd10_code");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        String probs = "|";
        if (problemCode.equals("") && problemDesc.equals("")) {
            JOptionPane.showMessageDialog(null, "Please insert either patient's"
                    + " complains or patient's diagnosis!");
        } else {
            probs = problemCode + "|" + problemDesc;
        }
        return probs;
    }
            String hpisub[][] = new String[100][3];
    String tsttab9[] = new String[100];
    int q7 = 0;        String PMHsub[][] = new String[100][3];
    String tsttab10[] = new String[100];
    int q8 = 0;
    
    private void accept_button_PMH() {
        jTextArea7.setText("");
        String PMHcmmt = (String) txt_PMHComment.getText();
        String PMHsearchcbx = txt_PMHSearch.getText();
        if (!Searching.isSearchPMH1(PMHsearchcbx)) {
            J.o("Invalid", "Invalid PMH Search!", 0);
            return;
        }
        //[0302 01042013] String PMHdiagnosis = txt_PMHDiagnosis.getText();
        String selectedRbtn = "";
        if (rbtn_cActive1.isSelected()) {
            selectedRbtn = "Active";
        }
        if (rbtn_cInactive1.isSelected()) {
            selectedRbtn = "In Active";
        }

        if (tab1 > 0) {
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        if (tab5 > 0) {
            jTextArea7.append(tab5s);
        }
        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (v == 0) {
            if (tab10 > 0 || tab10 == 0) {
                jTextArea19.append("\n" + (q8 + 1) + ") "
                        + "Problem: " + PMHsearchcbx
                        //[0303 01042013] + ", \n" + "Diagnosis: " + PMHdiagnosis 
                        + "Status:" + selectedRbtn
                        + "Details: " + PMHcmmt + "\n");
                tab10s = "\n" + (q8 + 1) + ") " + "Problem: " + PMHsearchcbx + ","
                        //[0304 01042013] + " \n" + "Diagnosis: " + PMHdiagnosis 
                        + "Status:" + selectedRbtn + "Details: " + PMHcmmt + "\n";
                tsttab10[q8] = tab10s;
                jTextArea7.append("\nPMH\n" + jTextArea19.getText());

                String data[] = {"Past Medical History",
                    "Problem: " + Func.trim(PMHsearchcbx),
                    //[0303 01042013] "Diagnosis: " + Func.trim(PMHdiagnosis),
                    "Status: " + Func.trim(selectedRbtn),
                    "Details: " + Func.trim(PMHcmmt)
                };
                setData(data, 3); //3 for PMH

                //to retrieve update value
//                PMHsub[q8][0] = PMHsearchcbx;
//                PMHsub[q8][1] = "-"; //[0304 01042013] PMHdiagnosis;
//                PMHsub[q8][2] = selectedRbtn;
//                PMHsub[q8][3] = PMHcmmt;

                //to retrieve update value
                q8++;
            }

            tab10 = tab10 + 1;
            tab10s = "\nPMH\n" + jTextArea19.getText();
        } else {

            String update = "Problem: " + PMHsearchcbx + ", \n"
                    //[0305 01042013] + "Diagnosis: " + PMHdiagnosis 
                    + "Status:" + selectedRbtn + "Details: " + PMHcmmt + "\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();
            //for delete in tab2
            for (int m = 0; m < q8; m++) {
                if (m == group1) {
                    //to update value
                    PMHsub[group1][0] = PMHsearchcbx;
                    PMHsub[group1][1] = "-"; //[0304 01042013] PMHdiagnosis;
                    PMHsub[group1][2] = selectedRbtn;
                    PMHsub[group1][3] = PMHcmmt;

                    //to update value
                    tsttab10[group1] = "\n" + m + ") " + update;
                }
            }


            jTextArea19.setText("");
            for (int p1 = 0; p1 < q6; p1++) {
                if ("".equals(tsttab10[p1])) {
                    p1 = p1 + 1;
                }

                jTextArea19.append(tsttab10[p1]);
            }

            jTextArea7.append("\nPMH\n" + jTextArea19.getText());
            tab10s = "\nPMH\n" + jTextArea19.getText();
            v = v - 1;
        }

        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }

        txt_PMHComment.setText("");
        txt_PMHSearch.setText("");
        //[0302 01042013] txt_PMHDiagnosis.setText("");

    }    String FHsub[][] = new String[100][3];
    String tsttab11[] = new String[100];
    int q9 = 0;
    
    private void accept_btn_FH() {
        jTextArea7.setText("");
        String SearchFH = txt_FHSearch.getText();
        if (!Searching.isSearchFH1(SearchFH)) {
            J.o("Invalid", "Invalid FH Code!", 0);
            return;
        }
        String FHRelationship = FH_Relationship.getSelectedItem().toString();
        //[0347 01042013] String Diagnosis = txt_FHDiagnosis.getText();
        String FHCmmnt = (String) FH_Comments.getText();
        
        String data[] = {"Family History",
            "Problems: " + Func.trim(SearchFH),
            "Relationship: " + Func.trim(FHRelationship),
            //[0347 01042013] "Diagnosis: " + Func.trim(Diagnosis),
            "Comments: " + Func.trim(FHCmmnt)
        };
        setData(data, 4); //4 for FH

//        if (tab1 > 0) {
//            jTextArea7.append(tab1s);
//        }
//        if (tab2 > 0) {
//            jTextArea7.append(tab2s);
//        }
//        if (tab3 > 0) {
//            jTextArea7.append(tab3s);
//        }
//        if (tab4 > 0) {
//            jTextArea7.append(tab4s);
//        }
//        if (tab5 > 0) {
//            jTextArea7.append(tab5s);
//        }
//        if (tab6 > 0) {
//            jTextArea7.append(tab6s);
//        }
//        if (tab7 > 0) {
//            jTextArea7.append(tab7s);
//        }
//        if (tab8 > 0) {
//            jTextArea7.append(tab8s);
//        }
//        if (tab9 > 0) {
//            jTextArea7.append(tab9s);
//        }
//        if (tab10 > 0) {
//            jTextArea7.append(tab10s);
//        }
//
//        if (v == 0) {
//            if (tab11 > 0 || tab11 == 0) {
//                jTextArea7.append("\nFamily History\n");
//                //jTextArea20.append("\n" + (q9 + 1) + ") "
//                        //+ "Problems: " + SearchFH
//                        //+ ", \n" + "Relationship: " + FHRelationship
//                        //+ //[0347 01042013] ", Diagnosis: " + Diagnosis + 
//                        //",\nComments: " + FHCmmnt + ";\n");
//                tab11s = "\n" + (q9 + 1) + ") " + "Problems: " + SearchFH + ", \n" + "Relationship: " + FHRelationship + ", "
//                        //[0347 01042013] + "Diagnosis: " + Diagnosis + ","
//                        + "\nComments: " + FHCmmnt + ";\n";
//                tsttab11[q9] = tab11s;
//                //jTextArea7.append(jTextArea20.getText());
//
//
//                //to retrieve update value
//                FHsub[q9][0] = SearchFH;
//                FHsub[q9][1] = FHRelationship;
//                FHsub[q9][2] = "-"; //[0347 01042013] Diagnosis;
//                FHsub[q9][3] = FHCmmnt;
//
//                //to retrieve update value
//                q7++;
//
//            }
//            tab11 = tab11 + 1;
//            //tab11s = "\nFamily History\n" + jTextArea20.getText();
//        } else {
//            String update = "Problems: " + SearchFH + ", \n" + "Relationship: " + FHRelationship
//                    //[0347 01042013] + ", Diagnosis: " + Diagnosis 
//                    + ",\nComments: " + FHCmmnt + ";\n";
//
//            int group1 = (Integer) jComboBox11.getSelectedItem();
//            //for delete in tab2
//            for (int m = 0; m < q9; m++) {
//                if (m == group1) {
//                    //to retrieve update value
//                    FHsub[group1][0] = SearchFH;
//                    FHsub[group1][1] = FHRelationship;
//                    FHsub[group1][2] = "-"; //[0347 01042013] Diagnosis;
//                    FHsub[group1][3] = FHCmmnt;
//
//                    //to retrieve update value
//                    tsttab11[group1] = "\n" + m + ") " + update;
//                }
//            }
//            //jTextArea20.setText("");
//            for (int p1 = 0; p1 < q9; p1++) {
//                if ("".equals(tsttab11[p1])) {
//                    p1 = p1 + 1;
//                }
//
//                //jTextArea20.append(tsttab11[p1]);
//
//            }
//
//            //jTextArea7.append("\nFamily History\n" + jTextArea20.getText());
//            //tab11s = "\nFamily History\n" + jTextArea20.getText();
//            v = v - 1;
//        }
//
//        if (tab12 > 0) {
//            jTextArea7.append(tab12s);
//        }
//        if (tab13 > 0) {
//            jTextArea7.append(tab13s);
//        }
        txt_FHSearch.setText("");
        //[0347 01042013] txt_FHDiagnosis.setText("");
        FH_Comments.setText("");
        FH_Relationship.setSelectedIndex(0);


    }    String bldsub[][] = new String[100][4];
    String tsttab12[] = new String[100];
    int q10 = 0;    String pnsub[][] = new String[100][3];
    String tsttab13[] = new String[100];
    int q11 = 0;
    private void hold() {
        try {
//            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
            DriversLocation driverLocation = new DriversLocation();
//            Timestamp date = timestamp;
            
            Date date1 = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(date1) + " " + Consultation.EpisodeTime;

            String PMI = txt_pPmiNo.getText();
            String name = txt_pName.getText();
            String IC = txt_pIcNo.getText();
            String race = txt_pRace.getText();
            String sex = txt_pSex.getText();
            String DOB = lblDOB.getText();
            String blood = txt_pBloodSex.getText();
            String pstatus = txt_pStatus.getText();

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
                Func.destroyPatientQueue(PMI);
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + searchcbx + "%");
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                SnomedID = rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            SnomedID,
                            searchcbx,
                            getSeverity(severity),
                            severity,
                            durationtxt + " " + durationcbx,
                            "",
                            "",
                            getSide(site),
                            site,
                            getLaterality(lateralitycbx),
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Problem + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ProblemID = rs.getString("icd10_code");
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Problem + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ProblemID = rs.getString("icd10_code");
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
                            tempQuery = "SELECT RSH_CODE FROM READCODE_SOCIAL_HISTORY "
                                    + "WHERE RSH_DESC LIKE ?";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Problem + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ProblemID = rs.getString("RSH_CODE");
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
                            tem,
                            sys,
                            dis,
                            sys2,
                            dis2,
                            sys1,
                            dis1,
                            wei,
                            hei,
                            hea,
                            respiratory_rate,
                            "",
                            pul+","+pul1+","+pul2,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name(),
                            
                            gcs_points,
                            gcs_result,
                            pgcs_points,
                            pgcs_result,
                            oxygen_saturation,
                            pain_scale,
                            
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Type + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ID = rs.getString("icd10_code");
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Type + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ID = rs.getString("icd10_code");
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Type + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ID = rs.getString("icd10_code");
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Diagnosis + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ID = rs.getString("icd10_code");
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
                            tempQuery = "SELECT UD_MDC_CODE "
                                    + "FROM PIS_MDC2 "
                                    + "WHERE UCASE(D_GNR_NAME) LIKE UCASE(?) "
                                    + "OR UCASE(D_TRADE_NAME) LIKE UCASE(?) ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + ActiveIngredient + "%");
                            ps.setString(2, "%" + ProductName + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                UD_MDC_Code = rs.getString("UD_MDC_CODE");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        
                        String qty_drug = Quantity;
                        if (packType.equals("CAP") || packType.equals("TAB")) {
                            qty_drug = getDrugQuantity(getFrequencyCode(Frequency), Quantity, getDayDrugCode(Duration));
                        }
                        
                        String data[] = {
                            ProblemCode + "^" + ProblemDesc + "^" + ProblemCode,
                            UD_MDC_Code + "^"+ ProductName + "-" + ActiveIngredient + "^" + UD_MDC_Code,
                            "" + "^" + DrugForm + "^" + "",
                            "" + "^" + "" + "^" + "",
                            "" + "^" + Frequency + "^" + "",
                            getFrequencyCode(Frequency),
                            Quantity,
                            Dose,
                            "" + "^" + "" + "^" + Dose,
                            getDayDrugCode(Duration),
                            qty_drug,
                            "" + "^" + "" + "^" + "",
                            Instruction,
                            "" + "^" + Session.getHfc_code() + "^"
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
                        String pe_exam_2 = pe_exam_line.split(Func.SEPARATOR_LINK)[pe_exam_line.split(Func.SEPARATOR_LINK).length-1];
                        String pe_exam = pe_exam_line;
                        String pe_cd = "";
                        for (int level = 1; level <= Func.NUM_LEVEL_PHYSICAL_EXAMINATION; level++) {
                            PhysicalExamBean pe = new PhysicalExamBean();
                            pe.setPe_name(pe_exam_2);
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
//                        String timeFrom = note_array[zz++].split(": ")[1];
//                        String timeTo = note_array[zz++].split(": ")[1];
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
                            "",
                            "",
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
                        String procedure_cd = note_array[zz++].split(": ")[1];
                        String procedure_desc_line = note_array[zz++].split(": ")[1];
                        
                        String procedure_desc = procedure_desc_line.split(Func.SEPARATOR_LINK)[procedure_desc_line.split(Func.SEPARATOR_LINK).length-1];
//                        String procedure_cd = "";
//                        for (int level = 1; level <= Func.NUM_LEVEL_PROCEDURE; level++) {
//                            ArrayList<String> procedure_detail = DBConnection.getProcedureDetail2(level, procedure_desc);
//                            if (procedure_detail.size() > 0) {
//                                procedure_cd = procedure_detail.get(0);
//                            }
//                        }
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
                if ((MItem_local.isSelected() == true)
                        && (MItem_drives.isSelected() == true)) {
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
                            ehr.insertJournal(2, header, patientInfo,
                                    msgs,
                                    PMI, date);
                            driverLocation.insertToDrive(2, header, patientInfo,
                                    msgs);

                            //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    msg_discharge += "Patient record is saved into local database and thumb drive.\n";
                } //Formatted message store into thumb drives by invoked insertToDrive method
                else if (MItem_drives.isSelected() == true) {
                    System.out.println("external");
                    if (msgs.length > 0) {
                        System.out.println("external inside");
                        driverLocation.insertToDrive(2, header, patientInfo,
                                msgs);
                        //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
                    }

                    msg_discharge += "Patient record is saved into thumb drive.\n";
                } //Formatted Message store into Journal File
                else if (MItem_local.isSelected() == true) {
                    System.out.println("local");

                    if (msgs.length > 0) {
                        ehr.insertJournal(2, header, patientInfo,
                                msgs, PMI, date);
                        //Friza
                        //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);

                        //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
                    }
                    msg_discharge += "Patient record is saved into local database.\n";
                }
                lblStatus.setText("Online");

                S.oln("Online - #########  Insert into Server #########");

                if (msgs.length > 0) {
                    //Insert to local after Discharge
                    //ehr.insertCentral(header, patientInfo, (String)enumTab1.nextElement(), (String)enumTab2.nextElement(),(String)enumTab3.nextElement(),(String)enumTab4.nextElement(),(String)enumTab5.nextElement(),(String)enumTab6.nextElement(),(String)enumTab7.nextElement(),(String)enumTab8.nextElement(), PMI);
                    // ehr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);

                    //ehr.insertJournal(header, patientInfo, checkNull(enumTab1.nextElement()), checkNull(enumTab2.nextElement()), checkNull(enumTab3.nextElement()), checkNull(enumTab4.nextElement()), checkNull(enumTab5.nextElement()), checkNull(enumTab6.nextElement()), checkNull(enumTab7.nextElement()), checkNull(enumTab8.nextElement()), PMI);
                    //Friza - insert CIS to server-MySql
                    //umar - cek setiap enumTab.nextElement tu tngok ada data atau x.. 
                    ehr.insertCentral(2, header, patientInfo,
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
//                    //  ehr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
//                    //     driverLocation.insertToDrive(header, patientInfo, (String) enumTabA.nextElement(), (String) enumTabB.nextElement(), (String) enumTabC.nextElement(), (String) enumTabD.nextElement(), (String) enumTabE.nextElement(), (String) enumTabF.nextElement(), (String) enumTabG.nextElement(), (String) enumTabH.nextElement());
//                    if (msgs.length > 0) {
//                        System.out.println("Insert both..");
//                        try {
//
//                            //Friza - insert CIS to server-MySql
//                            //ehr.insertCentral(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
//                            System.out.println("After insert local..");
//
//                            //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
//                            ehr.insertJournal(2, header, patientInfo,
//                                    msgs,
//                                    PMI);
//                            driverLocation.insertToDrive(2, header, patientInfo,
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
//                        driverLocation.insertToDrive(2, header, patientInfo,
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
//                        ehr.insertJournal(2, header, patientInfo,
//                                msgs, PMI);
//                        //Friza
//                        //saveEhr.insertJournal(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), PMI);
//
//                        //ehr.formatMsg(header, patientInfo, (String) enumTab1.nextElement(), (String) enumTab2.nextElement(), (String) enumTab3.nextElement(), (String) enumTab4.nextElement(), (String) enumTab5.nextElement(), (String) enumTab6.nextElement(), (String) enumTab7.nextElement(), (String) enumTab8.nextElement(), (String) enumTab9.nextElement(), (String) enumTab12.nextElement());
//                    }
//                    msg_discharge += "Patient record is saved into local database.\n";
//                }
//            }

            J.o("On Hold", msg_discharge, 1);

            row_count = 0;
            setBtnOn();

//            Queue updatequeue = new Queue();
//            updatequeue.updateStatusEpisode(txt_pPmiNo.getText(), EpisodeTime, "Hold");

//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
            //Session.setCon_x();

            CheckNewPatient.active = false;
            new Consultation().setVisible(true);
            this.dispose();

        } catch (Exception ex) {
            System.out.println("\n\nerror: " + ex + "\n\n");
            ex.printStackTrace();
        }
    }
    
    public static void setDrugAct() {
        drugName = txt_drugNameOListSearch.getText();
        if (drugName.equals("")) {
            for (int i = 0; i < 50; i++) {
                tbl_productname.getModel().setValueAt("", i, 0);
            }
        } else {
            try {
                for (int i = 0; i < 50; i++) {
                    tbl_productname.getModel().setValueAt("", i, 0);
                }
//                String sql = "SELECT * "
//                        + "FROM PIS_MDC "
//                        + "WHERE ACTIVE_INGREDIENT_CODE LIKE ? "
//                        + "OR DRUG_PRODUCT_NAME LIKE ? ";
                String sql = "SELECT * FROM PIS_MDC2 WHERE D_TRADE_NAME LIKE ? OR D_GNR_NAME LIKE ?";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, drugName.toUpperCase() + "%");
                ps.setString(2, drugName.toUpperCase() + "%");
                ResultSet results = ps.executeQuery();
                for (int i = 0; results.next() && i < 50; i++) {
//                    tbl_productname.getModel().setValueAt(results.getString("Drug_Product_Name"), i, 0);
                    tbl_productname.getModel().setValueAt(results.getString("D_TRADE_NAME"), i, 0);
                }
            } catch (Exception ex) {
                S.oln(ex.getMessage());
            }
        }
    }
    
    private void accept_btn_dto() {
        jTextArea7.setText("");

        String l = (String) txt_drugstrength.getText();
        String m = (String) txt_quantityOList.getText();

        String dr = txt_drugNameOListSearch.getText();
        String ud_mdc_code = arr_tbl_productname.get(currentIndex_tbl_productname);

        try {
            String product_name_x = txt_productNameOList.getText();
//            String ayat = "SELECT D_GNR_NAME "
//                    + "FROM PIS_MDC2 "
//                    + "WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
            String ayat = "SELECT D_GNR_NAME "
                    + "FROM PIS_MDC2 "
                    + "WHERE UCASE(UD_MDC_CODE) = UCASE(?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(ayat);
//            ps.setString(1, product_name_x);
            ps.setString(1, ud_mdc_code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dr = rs.getString("D_GNR_NAME");
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            S.oln(ex.getMessage());
            ex.printStackTrace();
        }

        String cautionary = txt_caution.getText();
        
        String doseT = (String) cb_durationOList.getSelectedItem();
        String freq = (String) cb_frequencyOList.getSelectedItem();
        String inst = (String) cb_instructionOList.getSelectedItem();
        int freq1 = cb_frequencyOList.getSelectedIndex();
        int inst1 = cb_instructionOList.getSelectedIndex();
        //int p_name1 = lst_productNameOList.getSelectedIndex();
        int p_name1 = tbl_productname.getSelectedRow();
        //int drug_search = lst_drugNameOListSearch.getSelectedIndex();
        int drug_search = tbl_productname.getSelectedRow();

        //String p_name = (String) lst_drugNameOListSearch.getSelectedValue();
        String p_name = txt_productNameOList.getText();
        String drug_form = (String) txt_dosageFormOList.getText();
        String duration = (String) cb_durationTypeOList.getSelectedItem();
        
        String pack_type = txt_packagetype.getText();

        //String data[] = p_name.split(";");

        //txt_dosageOList.setText(data[1]);
//        txt_dosageOList.setEditable(false);

        //**************************Print Existing Value*****************************
        if (tab1 > 0) {
            jTextArea7.append(tab1s);
        }
        if (tab2 > 0) {
            jTextArea7.append(tab2s);
        }
        if (tab3 > 0) {
            jTextArea7.append(tab3s);
        }
        if (tab4 > 0) {
            jTextArea7.append(tab4s);
        }
        //**************************Print Existing Value*****************************
        if (v == 0) {
            if (tab5 > 0 || tab5 == 0) {
//                txt_drugList.append("\n" + (q3 + 1) + ") " + "Type: " + dr + ",\nProduct Name: " + p_name + ",\nDose: " + l + ", Quantity: " + m + ", Drug Form: " + drug_form + ",\nDuration: " + doseT + " " + duration + ", Frequency: " + freq + ",\nInstruction: " + inst + ";\n");
//                tab5s = "\n" + (q3 + 1) + ") " + "Type: " + dr + ",\nProduct Name: " + p_name + ",\nDose: " + l + ", Quantity: " + m + ", Drug Form: " + drug_form + ",\nDuration: " + doseT + " " + duration + ", Frequency: " + freq + ",\nInstruction: " + inst + ";\n";
//                tsttab5[q3] = tab5s;
//                jTextArea7.append("\nDrugs\n" + txt_drugList.getText());

                String data[] = {"Drugs",
                    "Problem Code: " + Func.trim(problemCode),
                    "Problem Desc: " + Func.trim(problemDesc),
                    "Active Ingredient: " + Func.trim(dr),
                    "Product Name: " + Func.trim(p_name),
                    "Dose: " + Func.trim(l),
                    "Quantity: " + Func.trim(m),
                    "Drug Form: " + Func.trim(drug_form),
                    "Duration: " + Func.trim(doseT) + " " + Func.trim(duration),
                    "Frequency: " + Func.trim(freq),
                    "Instruction: " + Func.trim(inst),
                    "Cautionary: " + Func.trim(cautionary),
                    "Pack Type: " + Func.trim(pack_type),
                    "UD MDC Code: " + Func.trim(ud_mdc_code)
                };
                setData(data, 13); //1 for c.complaint

                //to retrieve update value
                drgsub[q3][0] = dr;
                drgsub[q3][1] = l;
                drgsub[q3][2] = m;
                drgsub[q3][3] = doseT;
                drgsub[q3][4] = freq;
                drgsub[q3][5] = inst;
                drgsub[q3][6] = p_name;
                drgsub[q3][7] = drug_form;
                drgsub[q3][8] = duration;
                drgsub1[q3][0] = freq1;
                drgsub1[q3][1] = inst1;
                drgsub1[q3][2] = p_name1;
                drgsub1[q3][3] = drug_search;

                for (int i = 0; i <= 8; i++) {
                    drgsub[q3][i] = Func.trim(drgsub[q3][i]);
                }

                //to retrieve update value
                q3++;
            }
            tab5 = tab5 + 1;
//            tab5s = "\nDrugs\n" + txt_drugList.getText();
        } else {
            String update = "Type: " + dr + ",\nProduct Name: " + p_name + ",\nDose: " + l + ", Quantity: " + m + ", Drug Form: " + drug_form + ",\nDuration: " + doseT + " " + duration + ", Frequency: " + freq + ",\nInstruction: " + inst + ";\n";

            int group1 = (Integer) jComboBox11.getSelectedItem();

            //for delete in tab2
            for (int z = 0; z < q3; z++) {
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
                    tsttab5[group1] = "\n" + (z + 1) + ") " + update;
                }
            }
//            txt_drugList.setText("");
//            for (int p1 = 0; p1 < q3; p1++) {
//                if ("".equals(tsttab5[p1])) {
//                    p1 = p1 + 1;
//                }
//                txt_drugList.append(tsttab5[p1]);
//            }
//
//            jTextArea7.append("\nDrugs\n" + txt_drugList.getText());
//            tab5s = "\nDrugs\n" + txt_drugList.getText();
            v = v - 1;
        }

        if (tab6 > 0) {
            jTextArea7.append(tab6s);
        }
        if (tab7 > 0) {
            jTextArea7.append(tab7s);
        }
        if (tab8 > 0) {
            jTextArea7.append(tab8s);
        }
        if (tab9 > 0) {
            jTextArea7.append(tab9s);
        }
        if (tab10 > 0) {
            jTextArea7.append(tab10s);
        }
        if (tab11 > 0) {
            jTextArea7.append(tab11s);
        }
        if (tab12 > 0) {
            jTextArea7.append(tab12s);
        }
        if (tab13 > 0) {
            jTextArea7.append(tab13s);
        }

        txt_drugstrength.setText("");
        txt_quantityOList.setText("");
        txt_drugNameOListSearch.setText("");
        cb_durationOList.setSelectedItem("-");

        txt_dosageFormOList.setText("");
        cb_durationTypeOList.setSelectedItem("-");
        cb_frequencyOList.setSelectedItem("-");
        cb_instructionOList.setSelectedItem("-");

        listModel = new javax.swing.DefaultListModel();
        listModel.clear();
        //lst_drugNameOListSearch.setModel(listModel);
        for (int i = 0; i < 50; i++) {
            tbl_productname.getModel().setValueAt("", i, 0);
        }
        //lst_productNameOList.setModel(listModel);
        //tfield_productname.setText("");
    }
    private void checkNumber(int type, JTextField jtf, int start, int end) {
        String a = jtf.getText();
        try {
            switch (type) {
                case 1: // check integer
                    int b = Integer.parseInt(a);
                    if (!(b >= start && b <= end)) {
                        jtf.setText("");
                    }
                    break;
                case 2: // check double
                    double b2 = Double.parseDouble(a);
                    if (!(b2 >= start && b2 <= end)) {
                        jtf.setText("");
                    }
                    break;
                default:
                    jtf.setText("");
                    break;
            }
        } catch (Exception e) {
            jtf.setText("");
        }
    }
    
    public static void clearDrugFields() {
        //clear all text field
        txt_productNameOList.setText("");
        txt_drugstrength.setText("");
        txt_dosageFormOList.setText("");
        cb_frequencyOList.setSelectedItem("-");
        txt_quantityOList.setText("");
        cb_durationOList.setSelectedItem("-");
        cb_durationTypeOList.setSelectedItem("-");
        cb_instructionOList.setSelectedItem("-");

        txt_packagetype.setText("");

        stock_qty.setText("");
    }
    
    public static void getDetailProductName(ResultSet results) {
//        product = productName;

        

        /*
         * search data base on the drug product choosed
         */
        //call data from PIS_MDC
        try {
//            String sql = "SELECT * FROM PIS_MDC where DRUG_PRODUCT_NAME = ?";
//            String sql = "SELECT * "
//                    + "FROM PIS_MDC2 "
//                    + "WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
//            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//            ps.setString(1, product);
//            ResultSet results = ps.executeQuery();
//            if (results.next()) {
                String dtraden = results.getString("D_TRADE_NAME");//
                Double stock_qty1 = results.getDouble("D_STOCK_QTY");//Stock_Qty
                String dstockqty = Double.toString(stock_qty1);
                String dstrength = results.getString("D_STRENGTH");
                String ddosage = results.getString("D_FORM_CODE");
                int dLqty1 = results.getInt("D_QTY");
                String dLqty = Integer.toString(dLqty1);
                String dLqtyt = results.getString("D_QTYT");
                String dLfreq = results.getString("D_FREQUENCY");
                String dLduration = results.getString("D_DURATION");
                String dLdurationType = results.getString("D_DURATIONT");
                String dLadvisory = results.getString("D_ADVISORY_CODE");
                String dLcaution = results.getString("D_CAUTION_CODE");
                String dLexpdate = results.getString("D_EXP_DATE");
                String dLclassification = results.getString("D_CLASSIFICATION");
                
                String dPackageType = results.getString("D_PACKAGINGT");
                
                txt_productNameOList.setText(dtraden);
                stock_qty.setText(dstockqty);
                txt_drugstrength.setText(dstrength);
                txt_dosageFormOList.setText(ddosage);
                txt_quantityOList.setText(dLqty);
                cb_frequencyOList.setSelectedItem(dLfreq);
                cb_durationOList.setSelectedItem(dLduration);
                cb_durationTypeOList.setSelectedItem(dLdurationType);
                cb_instructionOList.setSelectedItem(dLadvisory);
                
                txt_packagetype.setText(dPackageType);
                
                txt_caution.setText(dLcaution);

                if (stock_qty1 <= 0) {
                    JOptionPane.showMessageDialog(null, "Drug stock quantity is low " + stock_qty1
                            + "\nPlease choose another product name");

                    txt_productNameOList.setText("");
                    stock_qty.setText("");
                    txt_drugstrength.setText("");
                    txt_dosageFormOList.setText("");
                    txt_quantityOList.setText("");
                    cb_frequencyOList.setSelectedItem("");
                    cb_durationOList.setSelectedItem("");
                    cb_durationTypeOList.setSelectedItem("");
                    cb_instructionOList.setSelectedItem("");
                    
                    txt_packagetype.setText("");

                    txt_caution.setText("");
                }


//            }
            //clean the results and data
//            results.close();
//            ps.close();
        } catch (Exception e1) {
            S.oln(e1.getMessage());
            e1.printStackTrace();
        }
    }
    
    public static void setProductNameTbl(String pn) {
        try {
//            String sql = "SELECT * FROM PIS_MDC WHERE DRUG_PRODUCT_NAME = ?";
            String sql = "SELECT * "
                    + "FROM PIS_MDC2 "
                    + "WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, pn);
            ResultSet results = ps.executeQuery();
            
            clearDrugFields();
            
            if (results.next()) {
//                getDetailProductName(results.getString("Drug_Product_Name"));
                getDetailProductName(results);
            } 
//            else {
//                getDetailProductName("");
//            }
        } catch (Exception ex) {
            S.oln(ex.getMessage());
        }
    }
    
    public static void sorting(int index_data) {
        for (int i = index_data; i < max_row - 1; i++) {
            tbl_note_1.getModel().setValueAt(tbl_note_1.getModel().getValueAt(i + 1, 0), i, 0);
            tbl_note_1.getModel().setValueAt(tbl_note_1.getModel().getValueAt(i + 1, 1), i, 1);
            note_array[i] = note_array[i + 1];
        }
        tbl_note_1.getModel().setValueAt("", max_row - 1, 0);
        tbl_note_1.getModel().setValueAt("", max_row - 1, 1);
        note_array[max_row - 1] = "";
    }
    
    public static int delete_note(int index_row_1) {
        String data[] = note_array[index_row_1].split("\\|");
        S.oln(data[0]+"|"+data[1]);
        int data_length = Integer.parseInt(data[0]) + 1;
        for (int i = data_length; i >= 0; i--) {
            tbl_note_1.getModel().setValueAt("", index_row_1, 0);
            tbl_note_1.getModel().setValueAt("", index_row_1, 1);
            note_array[index_row_1] = "";
            sorting(index_row_1);
            index_row_1--;
            row_count--;
        }
        return index_row_1;
    }
    
    private void setAnatomy(JTextField txt_ob) {
        txt_ob.setText("");
        txt_ob.requestFocus();
    }
    
    private void clear_lio() {
        for(int i = 0; i < sizeLRPK; i++) {
            cbxPK[i].setSelected(false);
        }
        for(int i = 0; i < sizeLRKL; i++) {
            cbxKL[i].setSelected(false);
        }
        for(int i = 0; i < sizeLRHM; i++) {
            cbxHM[i].setSelected(false);
        }
        Laboratory_Request.cbx_priority.setSelectedIndex(0);
        Laboratory_Request.txt_patient_condition1.setText("");
        Laboratory_Request.cbx_health_facility1.setSelectedIndex(0);
        Laboratory_Request.cbx_discipline1.setSelectedIndex(0);
        Laboratory_Request.cbx_sub_discipline1.setSelectedIndex(0);
        Laboratory_Request.txt_notes1.setText("");
    }
        
    private void setMC_TimeSlip(int tanda) {
        try {
            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
            DriversLocation driverLocation = new DriversLocation();
            Timestamp date = timestamp;

            String PMI = txt_pPmiNo.getText();
            String name = txt_pName.getText();
            String IC = txt_pIcNo.getText();
            String race = txt_pRace.getText();
            String sex = txt_pSex.getText();
            String DOB = lblDOB.getText();
            String blood = txt_pBloodSex.getText();
            String pstatus = txt_pStatus.getText();

            String header = "MSH|^~|CIS^T12109|" + "<cr>" + "\n";
            String patientInfo = "PDI|" + PMI + "|" + name + "^" + IC + "^" + race + "^" + sex + "^" + DOB + "^" + blood + "^" + pstatus + "^" + "|" + "<cr>" + "\n";
            String msgs[] = new String[200];
            for (int zz = 0; zz < 200; zz++) {
                msgs[zz] = "";
            }

            boolean stat_mc1 = false;
            boolean stat_mc2 = false;

            for (int zz = 0, ii = 0; zz < max_row && ii < 200; zz++, ii++) {
                try {
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
                            tempQuery = "SELECT * FROM icd10_codes "
                                    + "where icd10_desc like ? ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + searchcbx + "%");
                            rs = ps.executeQuery();
                            if (rs.next()) {
//                                SnomedID = rs.getString("RCC_ID");
                                SnomedID = rs.getString("icd10_code");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                        String data[] = {
                            SnomedID,
                            searchcbx,
                            getSeverity(severity),
                            severity,
                            durationtxt + " " + durationcbx,
                            "",
                            "",
                            getSide(site),
                            site,
                            getLaterality(lateralitycbx),
                            lateralitycbx,
                            cmmt,
                            date.toString(),
                            selectedRbtn,
                            date.toString(),
                            Session.getHfc_code(),
                            Session.getUser_id(),
                            Session.getUser_name()
                        };
                        msgs[ii] = "CCN|" + date + "|";
                        for (int jj = 0; jj < data.length; jj++) {
                            msgs[ii] += data[jj] + "^";
                        }
                        msgs[ii] += "|" + "<cr>" + "\n";
                        S.oln(msgs[ii]);

                        stat_mc1 = true;

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
                            tempQuery = "SELECT RCC_CODE FROM READCODE_CHIEF_COMPLAINT "
                                    + "where UCASE(RCC_DESC) like UCASE(?) ";
                            ps = Session.getCon_x(1000).prepareStatement(tempQuery);
                            ps.setString(1, "%" + Diagnosis + "%");
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                ID = rs.getString("RCC_CODE");
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

                        stat_mc2 = true;
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

            if (stat_mc1 || stat_mc2) {

                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                //Format formatter2 = new SimpleDateFormat("HH:mm:ss");
                String date_from1 = "";
                String date_to1 = "";
                try {
                    date_from1 = formatter.format(date_from.getDate());
                    date_to1 = formatter.format(date_to.getDate());
                } catch (Exception e) {
                    J.o("Invalid Date", "Invalid Date Format!", 0);
                    return;
                }
                
                time2 = Func.getTimeNow();
                
                try {
                    time2 = time_to_hour.getSelectedItem().toString() + ":"
                            + time_to_minute.getSelectedItem().toString() + ":"
                            + time_to_second.getSelectedItem().toString();
                    //J.o("", time2, 0);
                } catch (Exception e) {
                }

                ArrayList<String> masa = new ArrayList<String>();
                masa.add(time1);
                masa.add(time2);
                masa.add(date_from1);
                masa.add(date_to1);
                
                switch (tanda) {
                    case 1: {
                        PDFiText.createMC("assets/MC_.pdf", data_temp, masa);
                    } break;
                    case 2: {
                        PDFiText.createTimeSlip("assets/TimeSlip_.pdf", data_temp, masa);
                        //btn_generate_timeslip.setEnabled(false);
                        if(checkPatient()) return;
                        if(checkProblem().equals("|")) return;
                        accept_button_timeslip();
                    } break;
                }
            } else {
                J.o("No Complaint", "No complaint/diagnosis has been made!\n"
                        + "Please made the complaint/diagnosis for this patient first..", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void accept_button_timeslip() {
                
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date_from1 = "";
        String date_to1 = "";
        try {
            date_from1 = formatter.format(date_from.getDate());
            date_to1 = formatter.format(date_to.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }

        //btn_generate_timeslip.setEnabled(false);
        
        String data[] = {"Time Slip (TS)",
            "Diagnosis Code: " + Func.trim(problemCode),
            "Diagnosis Desc: " + Func.trim(problemDesc),
            "Time From: " + Func.trim(time1),
            "Time To: " + Func.trim(time2)
        };
        setData(data, 17); //17 for mc
    }
    
    private void accept_button_mc() {
                
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date_from1 = "";
        String date_to1 = "";
        try {
            date_from1 = formatter.format(date_from.getDate());
            date_to1 = formatter.format(date_to.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }

//        btn_generate_mc.setEnabled(true);
//        isGenerateMCTS = true;
//        MC_accptBtn.setEnabled(false);
        
        String data[] = {"Medical Certification (MC)",
            "Diagnosis Code: " + Func.trim(problemCode),
            "Diagnosis Desc: " + Func.trim(problemDesc),
            "Date From: " + Func.trim(date_from1),
            "Date To: " + Func.trim(date_to1)
        };
        setData(data, 16); //16 for mc
    }
        
    private void accept_btn_pos() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_procedure.getLastSelectedPathComponent();
        if (node == null) {
            J.o("Not Selected", "Please select the procedure!", 0);
        } else {
            String procedure_name = (String) node.getUserObject();
            
            String procedure_cd = "-";
            for (int j = 0; j < Func.NUM_LEVEL_PROCEDURE; j++) {
                ArrayList<String> temp = DBConnection.getProcedureDetail2(j+1, procedure_name);
                if (temp.size() > 0) {
                    procedure_cd = temp.get(0);
                }
            }
            
            String procedure = Func.getProcedureLink(procedure_name);
            
            String data[] = {"Procedure Order",
                "Problem Code: " + Func.trim(problemCode),
                "Problem Desc: " + Func.trim(problemDesc),
                "Procedure Code: " + Func.trim(procedure_cd),
                "Procedure: " + Func.trim(procedure)
            };
            setData(data, 18); //18 for POS
        }
    }
        
    static String drugName = "-";
    static String product = "-";
    static String stockQty = "0.00";    
    public void resetTable() {
        //table hpi
//        tbl_HPI1.setModel(new javax.swing.table.DefaultTableModel(
//                new Object[][]{
//                    {null, null},
//                    {null, null},
//                    {null, null},
//                    {null, null},
//                    {null, null},
//                    {null, null},
//                    {null, null}
//                },
//                new String[]{
//                    "No", "Details"
//                }) {
//
//            boolean[] canEdit = new boolean[]{
//                false, true
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit[columnIndex];
//            }
//        });
//        //table cc
//        tbl_cc.setModel(new javax.swing.table.DefaultTableModel(
//                new Object[][]{
//                    {null, null, null, null, null, null},
//                    {null, null, null, null, null, null},
//                    {null, null, null, null, null, null},
//                    {null, null, null, null, null, null},
//                    {null, null, null, null, null, null},
//                    {null, null, null, null, null, null},
//                    {null, null, null, null, null, null}
//                },
//                new String[]{
//                    "Problem", "Severity", "Comment", "Duration", "Side", "Laterality"
//                }) {
//
//            boolean[] canEdit = new boolean[]{
//                false, false, false, false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit[columnIndex];
//            }
//        });
//        //table vts
//        tbl_vts.setModel(new javax.swing.table.DefaultTableModel(
//                new Object[][]{
//                    {null, null, null, null, null, null, null},
//                    {null, null, null, null, null, null, null},
//                    {null, null, null, null, null, null, null},
//                    {null, null, null, null, null, null, null},
//                    {null, null, null, null, null, null, null},
//                    {null, null, null, null, null, null, null},
//                    {null, null, null, null, null, null, null}
//                },
//                new String[]{
//                    "Height", "Weight", "Pulse", "BMI", "Weight Status", "Head Circumference", "Temperature"
//                }) {
//
//            Class[] types = new Class[]{
//                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
//            };
//            boolean[] canEdit = new boolean[]{
//                false, false, false, false, false, false, false
//            };
//
//            public Class getColumnClass(int columnIndex) {
//                return types[columnIndex];
//            }
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit[columnIndex];
//            }
//        });
    }
    
    private void setBtnOff() {
        btn_sPatient.setEnabled(false);
        btn_next.setEnabled(false);
//        tbl_referral_list.setEnabled(false);
        btn_exit.setEnabled(false);
        
        btn_hold.setEnabled(true);
        btn_discharge.setEnabled(true);
        btn_viewHistory.setEnabled(true);
    }
    
    protected void setBtnOn() {
        btn_sPatient.setEnabled(true);
        btn_next.setEnabled(true);
//        tbl_referral_list.setEnabled(true);
        btn_exit.setEnabled(true);
        
        btn_hold.setEnabled(false);
        btn_discharge.setEnabled(false);
        btn_viewHistory.setEnabled(false);
    }
    
    public void setSelectedAppointment(String selectedAppointment, String selectedTime) {
        
//        time1 = Func.getTimeNow();
        String timeStart = selectedTime;
        timeStart.replaceAll("\\s+","");
        time1 = timeStart;
        time_from.setText(time1);
        //J.o("test time", "|"+selectedTime+"|"+time1+"|", 0);
        
        jTextArea7.setText("");
        
        String[] AppointmentInfo = {};
        this.EpisodeTime = selectedTime;
        this.IDPMS = selectedAppointment;
        Patient appointment = new Patient(null);
        
//        LongRunProcess.change_status_network2();

        try {
            //
            AppointmentInfo = appointment.getAppointmentBiodata(selectedAppointment, selectedTime);
            //Friza getEHR

        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
        
        try {
            if (AppointmentInfo[0].equals("") && AppointmentInfo[0].length() == 0) {
                J.o("Invalid Patient", "Invalid patient!\n"
                        + "This patient currently been consulted or no patient with this ID.\n"
                        + "Please select another patient ..", 0);
                btn_exit.setEnabled(true);
                return;
            }
        } catch (Exception e) {
            J.o("Invalid Patient", "Invalid patient!\n"
                    + "This patient currently been consulted or no patient with this ID.\n"
                    + "Please select another patient ..", 0);
            btn_exit.setEnabled(true);
            e.printStackTrace();
            return;
        }

        System.out.println("wat to print 0: : " + AppointmentInfo[0]);
        System.out.println("wat to print 1: : " + AppointmentInfo[1]);
        System.out.println("wat to print 2: : " + AppointmentInfo[2]);

        String str_pdi = AppointmentInfo[0] + "|" + AppointmentInfo[2] + "|" 
                + Session.getUser_name() + "|" +Session.getData_user().get(17);
        
        try {
            Queue updatequeue = new Queue();
            updatequeue.updateStatusEpisode(selectedAppointment, selectedTime, "Consult", "");
            setBtnOff();
        } catch (Exception e) {
            return;
        }

        txt_pPmiNo.setText(AppointmentInfo[0]);
        txt_pName.setText(AppointmentInfo[2]);
        txt_pIcNo.setText(AppointmentInfo[4]);
        txt_pRace.setText(AppointmentInfo[13]);
        txt_pSex.setText(AppointmentInfo[11]);
        lblDOB.setText(AppointmentInfo[10]);
        txt_pBloodSex.setText(AppointmentInfo[16]);
//        txtBloodType.setText(txt_pBloodSex.getText());
        Func.cmbSelectInput(cmbBloodType, txt_pBloodSex.getText());
        txt_pStatus.setText(AppointmentInfo[12]);

        System.out.println(".... Extract EHR... ");

        /**
         * umar - 04012013 - start new code *
         */
        String pmiNo = selectedAppointment;
        String pid = null;
        try {
            
            String sql = "";
            String sql_history = "";
            String cpyFile = "";
            String cpyFile_history = "";
            String status = "1";
            String coldata[] = new String[100]; //100, maximum number of column
//            if (Session.getPrev_stat()) {
                //Read BLOB from EHR_Central
                System.err.println();
                System.err.println("Server Online");
                System.out.println("Start invoke remote server");
                try {
                    // fire to server port 1099
//                    ArrayList<String> listOnline = Func.readXML("online");
//                    Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                    // search for myMessage service
//                    Message impl = (Message) myRegistry.lookup("myMessage");
                    // call server's method			
                    //impl.sayHello("..Friza ");
                    
//                    DBConnection.getImpl().sayHello("UMAR");

                    ArrayList<String> arData = DBConnection.getImpl().getEHRRecords(pmiNo, 1); // get PMS by IC
                    cpyFile = arData.get(0);
                    status = arData.get(1);
                    cpyFile_history = arData.get(2);
                    
                    ArrayList<ArrayList<String>> dataLatest = DBConnection.getImpl().getEHRLatestEpisode(pmiNo, ViewPatientHistory.NUM_LATEST); // limit 7 episode
                    if (dataLatest.size() > 0) {
                        boolean statusTruncate = DBConnection.truncateJournalFile(pmiNo);
                        if (statusTruncate) {
                            for (int j = 0; j < dataLatest.size(); j++) {
                                String centralCode = dataLatest.get(j).get(0);
                                String pmiNoTemp = dataLatest.get(j).get(1);
                                String txnDate = dataLatest.get(j).get(2);
                                String txnDataBlob = dataLatest.get(j).get(3);
//                                J.o(pmiNoTemp, txnDataBlob, 2);
                                String statusDischarge = dataLatest.get(j).get(4);
                                boolean statusInsertJournalFile = DBConnection.insertJournalFile(centralCode, pmiNoTemp, txnDate, txnDataBlob, statusDischarge);
                            }
                        }
                    }
                    
                    System.out.println("UMAQ cpyFile:"+cpyFile);
                    System.out.println("UMAQ status:"+status);
                    System.out.println("UMAQ cpyFile_history:"+cpyFile_history);

                    System.out.println(".....Message Sent....");
                } catch (Exception e) {
                    S.oln("UMAQ ERROR:"+e.getMessage());
                    e.printStackTrace();
                    //If RMI Error, get data from Local Database.
                    //Read BLOB from Journal_File
                    sql = "SELECT TXNDATA, STATUS2 "
                            + "FROM PUBLIC.JOURNAL_FILE "
                            + "WHERE PMI_NO LIKE ? "
                            + "ORDER BY TXNCODE DESC "
                            + "LIMIT 1";
                    sql_history = "SELECT TXNDATA "
                            + "FROM PUBLIC.JOURNAL_FILE "
                            + "WHERE PMI_NO LIKE ? "
                            + "AND STATUS2 = 1 "
                            + "ORDER BY TXNCODE DESC "
                            + "LIMIT 7";
                    coldata[0] = "TXNDATA";
                    coldata[1] = "STATUS2";
                    /**
                     * keluar pada form *
                     */
                    Q.ps = Session.getCon_x(100).prepareStatement(sql);
                    Q.ps.setString(1, pmiNo);
                    Q.rs = Q.ps.executeQuery();
                    if (Q.rs.next()) {
                        cpyFile = Q.rs.getString(coldata[0]);
                        status = Q.rs.getString(coldata[1]);
                    }

                    /**
                     * keluar pada 7 history *
                     */
                    Q.ps = Session.getCon_x(100).prepareStatement(sql_history);
                    Q.ps.setString(1, pmiNo);
                    Q.rs = Q.ps.executeQuery();
                    while (Q.rs.next()) {
                        cpyFile_history += Q.rs.getString(coldata[0]);
                    }
                }
//            } else {
//                //Read BLOB from Journal_File
//                sql = "SELECT TXNDATA, STATUS2 "
//                        + "FROM PUBLIC.JOURNAL_FILE "
//                        + "WHERE PMI_NO LIKE ? "
//                        + "ORDER BY TXNDATE DESC "
//                        + "LIMIT 1";
//                sql_history = "SELECT TXNDATA "
//                        + "FROM PUBLIC.JOURNAL_FILE "
//                        + "WHERE PMI_NO LIKE ? "
//                        + "AND STATUS2 = 1 "
//                        + "ORDER BY TXNDATE DESC "
//                        + "LIMIT 7";
//                coldata[0] = "TXNDATA";
//                coldata[1] = "STATUS2";
//                /**
//                 * keluar pada form *
//                 */
//                Q.ps = Session.getCon_x(100).prepareStatement(sql);
//                Q.ps.setString(1, pmiNo);
//                Q.rs = Q.ps.executeQuery();
//                if (Q.rs.next()) {
//                    cpyFile = Q.rs.getString(coldata[0]);
//                    status = Q.rs.getString(coldata[1]);
//                }
//
//                /**
//                 * keluar pada 7 history *
//                 */
//                Q.ps = Session.getCon_x(100).prepareStatement(sql_history);
//                Q.ps.setString(1, pmiNo);
//                Q.rs = Q.ps.executeQuery();
//                while (Q.rs.next()) {
//                    cpyFile_history += Q.rs.getString(coldata[0]);
//                }
//            }
//            
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
            
            S.oln("*[cpyFile: "+cpyFile+"]*");
            S.oln("*[status: "+status+"]*");
            S.oln("*[cpyFile_history: "+cpyFile_history+"]*");
            
            String tokenLine[] = cpyFile.split(">");
            System.out.println("Token number : " + tokenLine.length);
            
            String tokenLine_history[] = cpyFile_history.split(">");
            System.out.println("Token number : " + tokenLine_history.length);
            
            resetTable();
            jTextArea7.setText("");
            
            //S.oln("cpyFile_history: |---|*"+cpyFile_history+"*|---|");
            
            int data_limit = 100;
            
            /**
             * latest 7 history *
             */
            int rows_tbl = 7;
            MainRetrieval mr[] = new MainRetrieval[data_limit];
            for (int i = 0; i < data_limit; i++) {
                mr[i] = new MainRetrieval();
                mr[i].startProcess(cpyFile_history);
            }
            String msg[][][] = new String[data_limit][data_limit][data_limit];
            int row[] = new int[data_limit];
            msg[0] = mr[0].getData("CCN");
            row[0] = mr[0].getRowNums();
            msg[1] = mr[1].getData("HPI");
            row[1] = mr[1].getRowNums();
            msg[2] = mr[2].getData("PMH");
            row[2] = mr[2].getRowNums();
            msg[3] = mr[3].getData("FMH");
            row[3] = mr[3].getRowNums();
            msg[4] = mr[4].getData("SOH");
            row[4] = mr[4].getRowNums();
            msg[5] = mr[5].getData("ALG");
            row[5] = mr[5].getRowNums();
            msg[6] = mr[6].getData("IMU");
            row[6] = mr[6].getRowNums();
            msg[7] = mr[7].getData("DAB");
            row[7] = mr[7].getRowNums();
            msg[8] = mr[8].getData("VTS");
            row[8] = mr[8].getRowNums();
            msg[9] = mr[9].getData("DGS");
            row[9] = mr[9].getRowNums();
            msg[10] = mr[10].getData("DTO");
            row[10] = mr[10].getRowNums();
            try {
                if (row[0] > 0) {
                    for (int i = 0; i < row[0] && i < rows_tbl; i++) {
                        tbl_cc.getModel().setValueAt(msg[0][i][2], i, 0);
                        tbl_cc.getModel().setValueAt(msg[0][i][4], i, 1);
                        tbl_cc.getModel().setValueAt(msg[0][i][9], i, 2);
                        tbl_cc.getModel().setValueAt(msg[0][i][5], i, 3);
                        tbl_cc.getModel().setValueAt(msg[0][i][11], i, 4);
                        tbl_cc.getModel().setValueAt(msg[0][i][12], i, 5);
                    }
                }
                if (row[1] > 0) {
                    for (int i = 0; i < row[1] && i < rows_tbl; i++) {
                        tbl_HPI1.getModel().setValueAt((i + 1), i, 0);
                        tbl_HPI1.getModel().setValueAt(msg[1][i][1], i, 1);
                    }
                }
                if (row[2] > 0) {
                    for (int i = 0; i < row[2] && i < rows_tbl; i++) {
                        tbl_pmh.getModel().setValueAt(msg[2][i][2], i, 0);
                        tbl_pmh.getModel().setValueAt(msg[2][i][3], i, 1);
                        tbl_pmh.getModel().setValueAt(msg[2][i][6], i, 2);
                    }
                }
                if (row[3] > 0) {
                    for (int i = 0; i < row[3] && i < rows_tbl; i++) {
                        tbl_fmh.getModel().setValueAt(msg[3][i][5], i, 0);
                        tbl_fmh.getModel().setValueAt(msg[3][i][1], i, 1);
                        tbl_fmh.getModel().setValueAt(msg[3][i][7], i, 2);
                    }
                }
                if (row[4] > 0) {
                    for (int i = 0; i < row[4] && i < rows_tbl; i++) {
                        tbl_sh.getModel().setValueAt(msg[4][i][2], i, 0);
                        tbl_sh.getModel().setValueAt(msg[4][i][8], i, 1);
                        tbl_sh.getModel().setValueAt(msg[4][i][12], i, 2);
                    }
                }
                if (row[5] > 0) {
                    String alg_str = "";
                    for (int i = 0; i < row[5] && i < rows_tbl; i++) {
//                        tbl_alg.getModel().setValueAt(msg[5][i][2], i, 0);
//                        tbl_alg.getModel().setValueAt(msg[5][i][3], i, 1);
//                        tbl_alg.getModel().setValueAt(msg[5][i][4], i, 2);
                        alg_str += msg[5][i][2] + "|";
                    }
                    LookupController CBoxloader = new LookupController();
                    UniCBmodel = new DefaultComboBoxModel();
                    UniCBmodel = CBoxloader.getAllergy(alg_str);
                    cbx_allergy.setModel(UniCBmodel);
                    Font font = new Font("Tahoma", Font.BOLD, 13);
                    jLabel14.setFont(font);
                    jLabel14.setForeground(Color.RED);
                } else {
                    Font font = new Font("Tahoma", Font.PLAIN, 11);
                    jLabel14.setFont(font);
                    jLabel14.setForeground(Color.BLACK);
                }
                if (row[6] > 0) {
                    for (int i = 0; i < row[6] && i < rows_tbl; i++) {
                        tbl_imu.getModel().setValueAt(msg[6][i][2], i, 0);
                        tbl_imu.getModel().setValueAt(msg[6][i][4], i, 1);
                        tbl_imu.getModel().setValueAt(msg[6][i][3], i, 2);
                    }
                }
                if (row[7] > 0) {
                    for (int i = 0; i < row[7] && i < rows_tbl; i++) {
                        tbl_dab.getModel().setValueAt(msg[7][i][2], i, 0);
                        tbl_dab.getModel().setValueAt(msg[7][i][3], i, 1);
                        tbl_dab.getModel().setValueAt("", i, 2);
                    }
                }
                if (row[8] > 0) {
                    for (int i = 0; i < row[8] && i < rows_tbl; i++) {
                        try {
                            double height = Double.parseDouble(msg[8][i][9]);
                            double weight = Double.parseDouble(msg[8][i][8]);
                            double bmi = calcBMI_Math(height, weight);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][9], i, 0);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][8], i, 1);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][13], i, 2);
//                            tbl_vts.getModel().setValueAt("" + bmi, i, 3);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][10], i, 4);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][1], i, 5);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][2] + "," + msg[8][i][6] + "," + msg[8][i][4], i, 6);
//                            tbl_vts.getModel().setValueAt("" + msg[8][i][3] + "," + msg[8][i][7] + "," + msg[8][i][5], i, 7);
                        } catch (Exception e) {
                        }
                    }
                }
                if (row[9] > 0) {
                    for (int i = 0; i < row[9] && i < rows_tbl; i++) {
                        tbl_dgs.getModel().setValueAt(msg[9][i][2], i, 0);
                        tbl_dgs.getModel().setValueAt(msg[9][i][4], i, 1);
                        tbl_dgs.getModel().setValueAt(msg[9][i][8], i, 2);
                        tbl_dgs.getModel().setValueAt(msg[9][i][10], i, 3);
                        tbl_dgs.getModel().setValueAt(msg[9][i][12], i, 4);
                        tbl_dgs.getModel().setValueAt(msg[9][i][16], i, 5);
                        tbl_dgs.getModel().setValueAt(msg[9][i][19], i, 6);
                    }
                }
                if (row[10] > 0) {
                    for (int i = 0; i < row[10] && i < rows_tbl; i++) {
                        String ud_mdc_code = msg[10][i][4];
                        String product_name = DBConnection.getProductNameDrug(ud_mdc_code);
                        tbl_drug.getModel().setValueAt(msg[10][i][5], i, 0);
                        tbl_drug.getModel().setValueAt(product_name, i, 1);
                        tbl_drug.getModel().setValueAt(msg[10][i][23], i, 2);
                        tbl_drug.getModel().setValueAt(msg[10][i][14], i, 3);
                        tbl_drug.getModel().setValueAt(msg[10][i][22], i, 4);
                        tbl_drug.getModel().setValueAt(msg[10][i][27], i, 5);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println("STATUS SELECT EHR: "+status);
            System.out.println("CPYFILE: "+cpyFile);
            
            //for (int i = tokenLine.countTokens(); i > 0; i--) {
            if(status.equals("2")) {
                
                MainRetrieval mr2[] = new MainRetrieval[data_limit];
                for (int i = 0; i < data_limit; i++) {
                    mr2[i] = new MainRetrieval();
                    mr2[i].startProcess(cpyFile);
                }
                String msg2[][][] = new String[data_limit][data_limit][data_limit];
                int row2[] = new int[data_limit];
                msg2[0] = mr2[0].getData("HPI");
                row2[0] = mr2[0].getRowNums();
                msg2[1] = mr2[1].getData("CCN");
                row2[1] = mr2[1].getRowNums();
                msg2[2] = mr2[2].getData("PMH");
                row2[2] = mr2[2].getRowNums();
                msg2[3] = mr2[3].getData("FMH");
                row2[3] = mr2[3].getRowNums();
                msg2[4] = mr2[4].getData("SOH");
                row2[4] = mr2[4].getRowNums();
                msg2[5] = mr2[5].getData("ALG");
                row2[5] = mr2[5].getRowNums();
                msg2[6] = mr2[6].getData("IMU");
                row2[6] = mr2[6].getRowNums();
                msg2[7] = mr2[7].getData("DAB");
                row2[7] = mr2[7].getRowNums();
                msg2[8] = mr2[8].getData("VTS");
                row2[8] = mr2[8].getRowNums();
                msg2[9] = mr2[9].getData("DGS");
                row2[9] = mr2[9].getRowNums();
                msg2[10] = mr2[10].getData("DTO");
                row2[10] = mr2[10].getRowNums();
                
                for (int i = 0; i < row2[1]; i++) {
                    try {
                        txt_complaintSearch.setText(msg2[1][i][2]);
                        txt_duration.setText(msg2[1][i][5]);
                        txt_complaintComment.setText(msg2[1][i][12]);
                        cbx_cSeverity.setSelectedItem(msg2[1][i][4]);
                        cbx_site.setSelectedItem(msg2[1][i][9]);
                        cbx_laterality.setSelectedItem(msg2[1][i][11]);
                        accept_button_ccomplaint();
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[0]; i++) {
                    try {
                        HPI_DetailstxtArea.setText(msg2[0][i][1]);
                        accept_button_HPI();
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[2]; i++) {
                    try {
                        txt_PMHSearch.setText(msg2[2][i][2]);
                        if (msg2[2][i][3].equals("Active")) {
                            rbtn_cActive1.setSelected(true);
                            rbtn_cInactive1.setSelected(false);
                        } else {
                            rbtn_cActive1.setSelected(false);
                            rbtn_cInactive1.setSelected(true);
                        }
                        txt_PMHComment.setText(msg2[2][i][6]);
                        accept_button_PMH();
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[3]; i++) {
                    try {
                        txt_FHSearch.setText(msg2[3][i][5]);
                        Func.cmbSelectInput(FH_Relationship, msg2[3][i][1]);
                        FH_Comments.setText(msg2[3][i][7]);
                        accept_btn_FH();
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[4]; i++) {
                    try {
//                        txt_socialProblem.setText(msg2[4][i][2]);
//                        try {
//                            txt_socialDate1.setDate((Date) new SimpleDateFormat(
//                                    Func.DATE_FORMAT_1).parse(msg2[4][i][8]));
//                        } catch (Exception e) {
//                            try {
//                                txt_socialDate1.setDate((Date) new SimpleDateFormat(
//                                        Func.DATE_FORMAT_2).parse(msg2[4][i][8]));
//                            } catch (Exception ee) {
//                            }
//                        }
//                        txt_socialComment.setText(msg2[4][i][12]);
//                        accept_btn_soh();
                        String data[] = {"Social History",
                            "Problem: " + Func.trim(msg2[4][i][2]),
                            "Comment: " + Func.trim(msg2[4][i][12]),
                            "Date: " + Func.trim(msg2[4][i][8])
                        };
                        setData(data, 5); //5 for SH
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[5]; i++) {
                    try {
//                        txt_allergySearch.setText(msg2[5][i][2]);
//                        try {
//                            txt_allergyDate2.setDate((Date) new SimpleDateFormat(
//                                    Func.DATE_FORMAT_1).parse(msg2[5][i][3]));
//                        } catch (Exception e) {
//                            try {
//                                txt_allergyDate2.setDate((Date) new SimpleDateFormat(
//                                        Func.DATE_FORMAT_2).parse(msg2[5][i][3]));
//                            } catch (Exception ee) {
//                            }
//                        }
//                        txt_allergyComments.setText(msg2[5][i][4]);
//                        accept_btn_alg();
                        String data[] = {"Allergy",
                            "Type: " + Func.trim(msg2[5][i][2]),
                            "Comment: " + Func.trim(msg2[5][i][4]),
                            "Date: " + Func.trim(msg2[5][i][3])
                        };
                        setData(data, 7); //7 for Allergy
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[6]; i++) {
                    try {
//                        txt_immSearch.setText(msg2[6][i][2]);
//                        txt_immComment.setText(msg2[6][i][4]);
//                        try {
//                            txt_immDate1.setDate((Date) new SimpleDateFormat(
//                                    Func.DATE_FORMAT_1).parse(msg2[6][i][3]));
//                        } catch (Exception e) {
//                            try {
//                                txt_immDate1.setDate((Date) new SimpleDateFormat(
//                                        Func.DATE_FORMAT_2).parse(msg2[6][i][3]));
//                            } catch (Exception ee) {
//                            }
//                        }
//                        accept_btn_imu();
                        String data[] = {"Immunisation",
                            "Type: " + Func.trim(msg2[6][i][2]),
                            "Comment: " + Func.trim(msg2[6][i][4]),
                            "Date: " + Func.trim(msg2[6][i][3])
                        };
                        setData(data, 8); //8 for Immunisation
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[7]; i++) {
                    try {
//                        txt_disabilityType.setText(msg2[7][i][2]);
//                        try {
//                            txt_dDate1.setDate((Date) new SimpleDateFormat(
//                                    Func.DATE_FORMAT_1).parse(msg2[7][i][3]));
//                        } catch (Exception e) {
//                            try {
//                                txt_dDate1.setDate((Date) new SimpleDateFormat(
//                                        Func.DATE_FORMAT_2).parse(msg2[7][i][3]));
//                            } catch (Exception ee) {
//                            }
//                        }
//                        txt_dComments.setText("");
//                        accept_btn_dab();
                        String data[] = {"Disability",
                            "Type: " + Func.trim(msg2[7][i][2]),
                            "Comment: " + Func.trim(""),
                            "Date: " + Func.trim(msg2[7][i][3])
                        };
                        setData(data, 9); //9 for Disability
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[8]; i++) {
                    try {
//                        txt_height.setText(msg2[8][i][9]);
//                        txt_weight.setText(msg2[8][i][8]);
//                        txt_systolic.setText(msg2[8][i][2]);
//                        txt_diastolic.setText(msg2[8][i][3]);
//                        txt_systolic1.setText(msg2[8][i][6]);
//                        txt_diastolic1.setText(msg2[8][i][7]);
//                        txt_systolic2.setText(msg2[8][i][4]);
//                        txt_diastolic2.setText(msg2[8][i][5]);
//                        txt_pulse.setText(msg2[8][i][13]);
//                        txt_pulse1.setText(msg2[8][i][13]);
//                        txt_pulse2.setText(msg2[8][i][13]);
//                        txt_headCircumference.setText(msg2[8][i][10]);
//                        txt_temperature.setText(msg2[8][i][1]);
//                        calcBMI();
//                        accept_button_vts();
                        /*
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
                        */
                        String pulse[] = msg2[8][i][13].split(",");
                        String pul = (pulse.length >= 1) ? (pulse[0]) : ("");
                        String pul1 = (pulse.length >= 2) ? (pulse[1]) : ("");
                        String pul2 = (pulse.length >= 3) ? (pulse[2]) : ("");
                        String data[] = {"Vital Sign",
                            "GCS Points: " + Func.trim(msg2[8][i][26]),
                            "GCS Result: " + Func.trim(msg2[8][i][27]),
                            "PGCS Points: " + Func.trim(msg2[8][i][28]),
                            "PGCS Result: " + Func.trim(msg2[8][i][29]),
                            "Respiratory Rate: " + Func.trim(msg2[8][i][11]),
                            "Oxygen Saturation: " + Func.trim(msg2[8][i][30]),
                            "Pain Scale: " + Func.trim(msg2[8][i][31]),
                            "Height: " + Func.trim(msg2[8][i][9]) + " cm",
                            "Weight: " + Func.trim(msg2[8][i][8]) + " kg",
                            "BMI: " + Func.trim(""+calcBMI_Math(Double.parseDouble(msg2[8][i][9]), Double.parseDouble(msg2[8][i][8]))) + " kg/m2",
                            "Status: " + Func.trim(calcBMI(Double.parseDouble(msg2[8][i][9]), Double.parseDouble(msg2[8][i][8]))),
                            "Head circumference: " + Func.trim(msg2[8][i][10]) + " cm",
                            "Temp: " + Func.trim(msg2[8][i][1]) + " celcius",
                            "Systolic Sitting: " + Func.trim(msg2[8][i][2]) + " mmHg",
                            "Diastolic Sitting: " + Func.trim(msg2[8][i][3]) + " mmHg",
                            "Pulse: " + Func.trim(pul) + " /min",
                            "Systolic Standing: " + Func.trim(msg2[8][i][6]) + " mmHg",
                            "Diastolic Standing: " + Func.trim(msg2[8][i][7]) + " mmHg",
                            "Pulse: " + Func.trim(pul1) + " /min",
                            "Systolic Lying: " + Func.trim(msg2[8][i][4]) + " mmHg",
                            "Diastolic Lying: " + Func.trim(msg2[8][i][5]) + " mmHg",
                            "Pulse: " + Func.trim(pul2) + " /min",
                            "Blood Glucose: " + Func.trim(msg2[8][i][32])
                        };
                        setData(data, 10); //10 for Vital Sign
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[9]; i++) {
                    try {
//                        if (msg2[9][i][2].equals("Final")) {
//                            jRadioButton1.setSelected(true);
//                            jRadioButton2.setSelected(false);
//                        } else {
//                            jRadioButton1.setSelected(false);
//                            jRadioButton2.setSelected(true);
//                        }
//                        try {
//                            txt_date1.setDate((Date) new SimpleDateFormat(
//                                    Func.DATE_FORMAT_1).parse(msg2[9][i][4]));
//                        } catch (Exception e) {
//                            try {
//                                txt_date1.setDate((Date) new SimpleDateFormat(
//                                        Func.DATE_FORMAT_2).parse(msg2[9][i][4]));
//                            } catch (Exception ee) {
//                            }
//                        }
//                        txt_diagnosisSearch.setText(msg2[9][i][8]);
//                        cbx_dSeverity.setSelectedItem(msg2[9][i][10]);
//                        dbx_site.setSelectedItem(msg2[9][i][12]);
//                        dbx_laterality.setSelectedItem(msg2[9][i][16]);
//                        txt_diagComment.setText(msg2[9][i][19]);
//                        accept_btn_dgs();
                        String data[] = {"Diagnosis",
                            "Type: " + Func.trim(msg2[9][i][2]),
                            "Type Code: " + Func.trim(msg2[9][i][1]),
                            "Diagnosis: " + Func.trim(msg2[9][i][8]),
                            "Severity: " + Func.trim(msg2[9][i][10]),
                            "Site: " + Func.trim(msg2[9][i][12]),
                            "Laterality: " + Func.trim(msg2[9][i][16]),
                            "Comment: " + Func.trim(msg2[9][i][19]),
                            "Date: " + Func.trim(msg2[9][i][4])
                        };
                        setData(data, 11); //11 for Diagnosis
                    } catch (Exception e) {
                    }
                }
                for (int i = 0; i < row2[10]; i++) {
                    try {
                        txt_drugNameOListSearch.setText(msg2[10][i][5]);
                        cb_frequencyOList.setSelectedItem(msg2[10][i][14]);
                        cb_durationOList.setSelectedItem(msg2[10][i][22].split(" ")[0]);
                        cb_durationTypeOList.setSelectedItem(msg2[10][i][22].split(" ")[1]);
                        txt_quantityOList.setText(msg2[10][i][23]);
                        cb_instructionOList.setSelectedItem(msg2[10][i][34]);
                        accept_btn_dto();
                    } catch (Exception e) {
                    }
                }
                
                msg2[11] = mr2[11].getData("BLD");
                row2[11] = mr2[11].getRowNums();
                for (int i = 0; i < row2[11]; i++) {
                    try {
                        String data[] = {"Blood Group / G6PD",
                            "Blood Type: " + Func.trim(msg2[11][i][0]),
                            "Rhesus: " + Func.trim(msg2[11][i][1]),
                            "G6PD: " + Func.trim(msg2[11][i][2]),
                            "Comment: " + Func.trim(msg2[11][i][3])
                        };
                        setData(data, 6); //6 for Blood Group
                    } catch (Exception e) {
                    }
                }
                
                msg2[12] = mr2[12].getData("PEM");
                row2[12] = mr2[12].getRowNums();
                for (int i = 0; i < row2[12]; i++) {
                    try {
                        String data[] = {"Physical Examination",
                            "Physical Exam Name: " + Func.trim(msg2[12][i][4]),
                            "Comments: " + Func.trim(msg2[12][i][6])
                        };
                        setData(data, 14); //14 for PEM
                    } catch (Exception e) {
                    }
                }
                
                msg2[13] = mr2[13].getData("PNT");
                row2[13] = mr2[13].getRowNums();
                for (int i = 0; i < row2[13]; i++) {
                    try {
                        String data[] = {"Progress Notes",
                            "Notes: " + Func.trim(msg2[13][i][1])
                        };
                        setData(data, 12); //12 for Progress Notes
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            S.oln("Error 10428: "+e.getMessage());
            e.printStackTrace();
        }
        /**
         * umar - 04012013 - end new code *
         */

        //offline:
        //1. Read from local/thumb
        
        //3. Display at screen/all tab

        Func.callPatient(str_pdi);
    }

    //Online Indicator
    public static void showOnline() {
        lblStatus.setText("Online");
        btnStatus.setBackground(Color.green);
    }
    //Offline Indicator

    public static void showOffline() {
        lblStatus.setText("Offline");
        btnStatus.setBackground(Color.red);
    }

//    public void voice() {
//        System.out.println("Start Speaking:");
//        Result result = recognizer.recognize();
//        if (result != null) {
//            String resultText = result.getBestFinalResultNoFiller();
//            //System.out.println("You said: " + resultText + '\n');
//            jTextVoice.setText(resultText);
//        } else {
//            System.out.println("I can't hear what you said.\n");
//        }
//    }
//    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
//    private static String subject = "QUEUEDHANA";
//       public void sentQueue(String msg) throws JMSException{
//           
//           // URL of the JMS server. DEFAULT_BROKER_URL will just mean
//    // that JMS server is on localhost
//           
//   // private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
//
//    // Name of the queue we will be sending messages to
//    
//
//           // Getting JMS connection from the server and starting it
//        
//         //failover://tcp://localhost:61616 192.168.1.3 10.73.30.245
//         //wifi : failover://tcp://192.168.1.3:61616
//        ConnectionFactory connectionFactory =
//            new ActiveMQConnectionFactory("failover://tcp://10.73.25.254:61616");
//        javax.jms.Connection connection = connectionFactory.createConnection();//  .createConnection();
//        connection.start();
//
//        // JMS messages are sent and received using a Session. We will
//        // create here a non-transactional session object. If you want
//        // to use transactions you should set the first parameter to 'true'
//        Session session = connection.createSession(false,
//            Session.AUTO_ACKNOWLEDGE);
//
//        // Destination represents here our queue 'TESTQUEUE' on the
//        // JMS server. You don't have to do anything special on the
//        // server to create it, it will be created automatically.
//        Destination destination = session.createQueue(subject);
//
//        // MessageProducer is used for sending messages (as opposed
//        // to MessageConsumer which is used for receiving them)
//        MessageProducer producer = session.createProducer(destination);
//
//        // We will send a small text message saying 'Hello' in Japanese
//        TextMessage message = session.createTextMessage(msg);
// //System.out.println("こんにちは - From dhana");
//        // Here we are sending the message!
//        producer.send(message);
//        System.out.println("Sent message '" + message.getText() + "'");
//
//        connection.close();
//       }
    /***************************************************************************************************************************************
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Consultation().setVisible(true);
            }
        });
    }
    //Self declaration variables
//    public static Recognizer recognizer;
//    public static ResultSpeech rSpeech;
//    public static ConfigurationManager cm;    
    //private boolean status = false;
    protected EHR_Central ehr = new EHR_Central();
    //private SaveEHR saveEhr = new SaveEHR(); //Friza 12/10/2012
    private Thread thread;
    private PatientRecordGUI table;
    private boolean tableActive = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BG_accptBtn;
    private javax.swing.JButton BG_clrBtn;
    public static javax.swing.JTextArea FH_Comments;
    public static javax.swing.JComboBox FH_Relationship;
    private javax.swing.JButton FH_accptBtn;
    private javax.swing.JButton FH_clrBtn;
    private javax.swing.JButton HPI_ClrBtn;
    public static javax.swing.JTextArea HPI_DetailstxtArea;
    private javax.swing.JButton MC_accptBtn;
    private javax.swing.JButton MC_clrBtn;
    protected javax.swing.JMenuItem MItem_Import;
    protected javax.swing.JCheckBoxMenuItem MItem_drives;
    protected javax.swing.JCheckBoxMenuItem MItem_local;
    private javax.swing.JButton PMH_AccptrBtn;
    private javax.swing.JButton PMH_clearBtn;
    private javax.swing.JButton PN_accptBtn;
    private javax.swing.JButton PN_clrBtn;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSrcAllergy;
    private javax.swing.JButton btnSrcComplaint;
    private javax.swing.JButton btnSrcComplaint1;
    private javax.swing.JButton btnSrcDiagnosis;
    private javax.swing.JButton btnSrcDisability;
    private javax.swing.JButton btnSrcImmunisation;
    private javax.swing.JButton btnSrcSocialHistory;
    private javax.swing.JButton btnSrcSocialHistory1;
    private static javax.swing.JButton btnStatus;
    private javax.swing.JButton btn_HPIAccpt;
    private javax.swing.JButton btn_ProcAcc;
    private javax.swing.JButton btn_allergyAccept;
    private javax.swing.JButton btn_allergyClear;
    private javax.swing.JButton btn_calculateBmi;
    private javax.swing.JButton btn_complaintAccept;
    private javax.swing.JButton btn_complaintClear;
    private javax.swing.JButton btn_dAccept;
    private javax.swing.JButton btn_dClear;
    private javax.swing.JButton btn_dDelete;
    private javax.swing.JButton btn_dUpdate;
    private javax.swing.JButton btn_diagnosisAccept;
    private javax.swing.JButton btn_diagnosisClear;
    private javax.swing.JButton btn_discharge;
    private javax.swing.JButton btn_drugAccept;
    private javax.swing.JButton btn_drugClear;
    public static javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_generate_mc;
    private javax.swing.JButton btn_generate_timeslip;
    private javax.swing.JButton btn_hold;
    private javax.swing.JButton btn_immAccept;
    private javax.swing.JButton btn_immClear;
    private javax.swing.JButton btn_missing;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_pHistoryOk1;
    private javax.swing.JButton btn_pHistoryOk2;
    public static javax.swing.JButton btn_plus;
    public static javax.swing.JButton btn_plus_dgs;
    private javax.swing.JButton btn_sPatient;
    private javax.swing.JButton btn_sclAccept;
    private javax.swing.JButton btn_sclClear;
    public static javax.swing.JButton btn_substract;
    public static javax.swing.JButton btn_substract_dgs;
    protected static javax.swing.JButton btn_viewHistory;
    private javax.swing.JButton btn_vitalSignAccept;
    private javax.swing.JButton btn_vitalSignClear;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    protected static javax.swing.JComboBox cb_durationOList;
    protected static javax.swing.JComboBox cb_durationTypeOList;
    protected static javax.swing.JComboBox cb_frequencyOList;
    protected static javax.swing.JComboBox cb_instructionOList;
    public static javax.swing.JComboBox cbx_allergy;
    public static javax.swing.JComboBox cbx_cSeverity;
    public static javax.swing.JComboBox cbx_dSeverity;
    public static javax.swing.JComboBox cbx_duration;
    public static javax.swing.JComboBox cbx_laterality;
    private javax.swing.JComboBox cbx_pain_scale;
    public static javax.swing.JComboBox cbx_referral_doctor;
    public static javax.swing.JComboBox cbx_site;
    public static javax.swing.JComboBox cmbBloodType;
    public static com.toedter.calendar.JDateChooser date_from;
    public static com.toedter.calendar.JDateChooser date_to;
    public static javax.swing.JComboBox dbx_laterality;
    public static javax.swing.JComboBox dbx_site;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel127;
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
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
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
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    public static javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
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
    private javax.swing.JScrollPane jScrollPane62;
    private javax.swing.JScrollPane jScrollPane67;
    private javax.swing.JScrollPane jScrollPane68;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane70;
    private javax.swing.JScrollPane jScrollPane71;
    private javax.swing.JScrollPane jScrollPane75;
    private javax.swing.JScrollPane jScrollPane76;
    private javax.swing.JScrollPane jScrollPane77;
    private javax.swing.JScrollPane jScrollPane78;
    private javax.swing.JScrollPane jScrollPane79;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane82;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    protected static javax.swing.JTabbedPane jTabbedPane2;
    protected static javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea10;
    private javax.swing.JTextArea jTextArea12;
    private javax.swing.JTextArea jTextArea18;
    private javax.swing.JTextArea jTextArea19;
    private javax.swing.JTextArea jTextArea21;
    private javax.swing.JTextArea jTextArea22;
    public javax.swing.JTextArea jTextArea7;
    private java.awt.Label label1;
    private javax.swing.JLabel lb_allergySearch;
    public javax.swing.JLabel lblDOB;
    protected static javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lbl_allergyComments;
    private javax.swing.JLabel lbl_allergyDate;
    private javax.swing.JLabel lbl_cSeverity;
    public javax.swing.JLabel lbl_ccdate;
    private javax.swing.JLabel lbl_complaintComment;
    private javax.swing.JLabel lbl_complaintComment1;
    private javax.swing.JLabel lbl_complaintSearch;
    private javax.swing.JLabel lbl_complaintSearch1;
    private javax.swing.JLabel lbl_dComments;
    private javax.swing.JLabel lbl_dSeverity;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_ddate;
    public javax.swing.JLabel lbl_diagdate;
    private javax.swing.JLabel lbl_diagnosisSearch;
    private javax.swing.JLabel lbl_disDate;
    private javax.swing.JLabel lbl_disabilityType;
    public javax.swing.JLabel lbl_disdate;
    private javax.swing.JLabel lbl_dosageOList;
    private javax.swing.JLabel lbl_drugNameOListSearch;
    private javax.swing.JLabel lbl_duration;
    private javax.swing.JLabel lbl_durationOList;
    private javax.swing.JLabel lbl_frequencyOList;
    public javax.swing.JTextField lbl_g6pd;
    private javax.swing.JLabel lbl_immComment;
    private javax.swing.JLabel lbl_immDate;
    private javax.swing.JLabel lbl_immSearch;
    public javax.swing.JLabel lbl_immdate;
    private javax.swing.JLabel lbl_instructionOList;
    private javax.swing.JLabel lbl_laterality;
    private javax.swing.JLabel lbl_laterality1;
    public static javax.swing.JLabel lbl_new_patient;
    private javax.swing.JLabel lbl_pBdate;
    private javax.swing.JLabel lbl_pBloodSex;
    private javax.swing.JLabel lbl_pBloodSex1;
    private javax.swing.JLabel lbl_pIcNo;
    private javax.swing.JLabel lbl_pIcNo1;
    private javax.swing.JLabel lbl_pName;
    private javax.swing.JLabel lbl_pName1;
    private javax.swing.JLabel lbl_pPmiNo;
    private javax.swing.JLabel lbl_pRace;
    private javax.swing.JLabel lbl_pSex;
    private javax.swing.JLabel lbl_pStatus;
    private javax.swing.JLabel lbl_productNameOList1;
    private javax.swing.JLabel lbl_quantityOList;
    public javax.swing.JLabel lbl_shdate;
    private javax.swing.JLabel lbl_site;
    private javax.swing.JLabel lbl_site1;
    private javax.swing.JLabel lbl_time;
    public javax.swing.JLabel lbl_vsdate;
    public static javax.swing.JList lbx_FHSearch;
    public static javax.swing.JList lbx_PMHSearch;
    public static javax.swing.JList lbx_allergySearch;
    public static javax.swing.JList lbx_complaintSearch;
    public static javax.swing.JList lbx_diagnosisSearch;
    public static javax.swing.JList lbx_disabilityType;
    public static javax.swing.JList lbx_immSearch;
    public static javax.swing.JList lbx_socialProblem;
    private javax.swing.JPanel mainPanel;
    protected javax.swing.JCheckBoxMenuItem menuCheckBluetooth;
    public static javax.swing.JPanel pnl_procedure_1;
    public static javax.swing.JRadioButton rbtn_Bdeficient;
    public static javax.swing.JRadioButton rbtn_Bnegative;
    public static javax.swing.JRadioButton rbtn_Bnormal;
    public static javax.swing.JRadioButton rbtn_Bpositive;
    public static javax.swing.JRadioButton rbtn_cActive1;
    public static javax.swing.JRadioButton rbtn_cInactive1;
    public static javax.swing.JRadioButton rbtn_gen_search;
    public static javax.swing.JRadioButton rbtn_gen_search_dgs;
    private javax.swing.ButtonGroup rbtn_grp_diagnosis;
    private javax.swing.ButtonGroup rbtn_grp_mcts;
    private javax.swing.JRadioButton rbtn_mcts_mc;
    private javax.swing.JRadioButton rbtn_mcts_ts;
    public static javax.swing.JRadioButton rbtn_per_search;
    public static javax.swing.JRadioButton rbtn_per_search_dgs;
    protected static javax.swing.JTextField stock_qty;
    private javax.swing.JTable tblQueue;
    private javax.swing.JTable tblQueue1;
    public static javax.swing.JTable tbl_HPI1;
    public javax.swing.JTable tbl_cc;
    public javax.swing.JTable tbl_dab;
    public javax.swing.JTable tbl_dgs;
    public javax.swing.JTable tbl_drug;
    public javax.swing.JTable tbl_fmh;
    public static javax.swing.JTable tbl_gcs;
    public javax.swing.JTable tbl_imu;
    public static javax.swing.JTable tbl_note_1;
    public static javax.swing.JTable tbl_pgcs;
    public javax.swing.JTable tbl_pmh;
    public static javax.swing.JTable tbl_productname;
    public javax.swing.JTable tbl_sh;
    private com.toedter.calendar.JDateChooser tfieldDate;
    public static javax.swing.JTextField time_from;
    public static javax.swing.JComboBox time_to_hour;
    public static javax.swing.JComboBox time_to_minute;
    public static javax.swing.JComboBox time_to_second;
    public static javax.swing.JTree tree_physical_exam;
    public static javax.swing.JTree tree_procedure;
    public static javax.swing.JTextArea txt_BldCmmnt;
    public static javax.swing.JTextField txt_FHSearch;
    public static javax.swing.JTextArea txt_PMHComment;
    public static javax.swing.JTextField txt_PMHSearch;
    public static javax.swing.JTextArea txt_allergyComments;
    public static com.toedter.calendar.JDateChooser txt_allergyDate2;
    public static com.toedter.calendar.JDateChooser txt_allergyDate3;
    public static javax.swing.JTextField txt_allergySearch;
    public static javax.swing.JTextField txt_bahu_kanan;
    private javax.swing.JTextField txt_bahu_kiri;
    public static javax.swing.JTextField txt_blood_glucose;
    public static javax.swing.JTextField txt_bmi;
    protected static javax.swing.JTextArea txt_caution;
    public static javax.swing.JTextArea txt_complaintComment;
    public static javax.swing.JTextField txt_complaintSearch;
    public static javax.swing.JTextArea txt_dComments;
    public static com.toedter.calendar.JDateChooser txt_dDate1;
    public static com.toedter.calendar.JDateChooser txt_date1;
    public static javax.swing.JTextArea txt_diagComment;
    public static javax.swing.JTextField txt_diagnosisSearch;
    public javax.swing.JTextField txt_diastolic;
    public javax.swing.JTextField txt_diastolic1;
    public javax.swing.JTextField txt_diastolic2;
    public static javax.swing.JTextField txt_disabilityType;
    protected static javax.swing.JTextField txt_dosageFormOList;
    public static javax.swing.JTextField txt_drugNameOListSearch;
    protected static javax.swing.JTextField txt_drugstrength;
    public static javax.swing.JTextField txt_duration;
    public static javax.swing.JTextField txt_gcs_points;
    public static javax.swing.JTextField txt_gcs_result;
    public javax.swing.JTextField txt_headCircumference;
    public static javax.swing.JTextField txt_height;
    private javax.swing.JTextField txt_hidung;
    public static javax.swing.JTextArea txt_immComment;
    public static com.toedter.calendar.JDateChooser txt_immDate1;
    public static javax.swing.JTextField txt_immSearch;
    public static javax.swing.JTextField txt_jantung;
    private javax.swing.JTextField txt_kepala;
    private javax.swing.JTextField txt_mulut;
    protected static javax.swing.JTextField txt_oxygen_saturation;
    public javax.swing.JTextField txt_pBloodSex;
    public javax.swing.JTextField txt_pIcNo;
    public javax.swing.JTextField txt_pIcNo1;
    public javax.swing.JTextField txt_pName;
    public javax.swing.JTextField txt_pName1;
    private javax.swing.JTextArea txt_pNotes;
    public javax.swing.JTextField txt_pPmiNo;
    public javax.swing.JTextField txt_pRace;
    public javax.swing.JLabel txt_pSex;
    public javax.swing.JTextField txt_pStatus;
    protected static javax.swing.JTextField txt_packagetype;
    private javax.swing.JTextField txt_pain_result;
    protected static javax.swing.JTextArea txt_pe_comments;
    public static javax.swing.JTextField txt_peparu_kanan;
    private javax.swing.JTextField txt_perut;
    public static javax.swing.JTextField txt_pgcs_points;
    public static javax.swing.JTextField txt_pgcs_result;
    protected static javax.swing.JTextField txt_productNameOList;
    public javax.swing.JTextField txt_pulse;
    public javax.swing.JTextField txt_pulse1;
    public javax.swing.JTextField txt_pulse2;
    protected static javax.swing.JTextField txt_quantityOList;
    public static javax.swing.JTextArea txt_referral_comment;
    protected static javax.swing.JTextField txt_respiratory_rate;
    public static javax.swing.JTextArea txt_socialComment;
    public static com.toedter.calendar.JDateChooser txt_socialDate1;
    public static javax.swing.JTextField txt_socialProblem;
    public javax.swing.JTextField txt_systolic;
    public javax.swing.JTextField txt_systolic1;
    public javax.swing.JTextField txt_systolic2;
    public static javax.swing.JTextField txt_tekak;
    private javax.swing.JTextField txt_telinga_kanan;
    public javax.swing.JTextField txt_temperature;
    public static javax.swing.JTextField txt_weight;
    public javax.swing.JTextField txt_weightStatus;
    // End of variables declaration//GEN-END:variables
}
