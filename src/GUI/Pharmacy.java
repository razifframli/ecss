/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Pharmacy.java
 *
 * Created on Mar 13, 2011, 1:28:11 AM
 */

package GUI;

//connection db & others
import Bean.DrugOrderBean;
import Bean.PatientBean;
import DBConnection.DBConnection;
import Helper.S;
import Helper.Session;

import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import net.proteanit.sql.DbUtils;
import java.sql.SQLException;
import api.pis;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.util.ArrayList;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;
import api.Patient;
import java.util.List;
//import javaapplication1.Consultation;
import javaapplication1.ExtractEHR;
import library.Q;
import Helper.J;
import Process.MainRetrieval;
import api.Queue;
import api.LookupController;
import com.itextpdf.text.log.SysoLogger;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.DateFormat;
import javaapplication1.DriversLocation;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import javaapplication1.PDFiText;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jxl.CellType;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author phoebe
 */
public class Pharmacy extends javax.swing.JFrame{

//STAFF LOGIN SESSION
    
    private String id;
    private String staffName;
    private String staffID;
    
    //PATIENT INFORMATION - SEARCH
    private String pmiNo;
    private String patientIC;
    private String patientId;
    private String idNo;
    private String patientName;
    private String race;
    private String blood;
    private String bod;
    private String gender;
    
    public static SearchData data;
    
    //MDC column
    public String orderno;
    private String mdcCode;
    private String drugName;
    private String productName;
    private String mdcDesc;
    private String ingredientCode;
    private String cautionaryCode;
    private String dfc;//dosage form code
    private String dosageForm;//def dosage yg nilainya sama dgn drug strength
    private String routeCode;
    private String advisoryCode;
    private String atcCode;
    private String mdcStatus;//option 1
    private String status;//option 2
    private String drug_strength;
    private String stockQty;
    private String locationCode;
    
    //manage ATC
    private String atcCode1;
    private String atcDesc;
    private String categoryCode;
    private String atcStatus;
    
    private String uomCode;
    private String drugStrength;
    private String packingSize;
    private String formCode;
    private String dosage;
    private String frequency;
    private String drugStatus;
    private String active_ingredient;
    
    //manage drug
    private double drugQty;
    private String urlAdd;
    private String st;
    private String stockUom;
    private String packingUom;
    private String supplier;
    private String contactNo;
    private String supplierID;
    private String stockDate;
    private String route;
    private String duration;
    private String qtyPerTime;
    //supplier
    private String supid;
    private String supname;
    private String supcontact;
    //dispnse
    private String product;
    private String strength;
    private String qtysup;
    //private String medicFormCode;
    private int frequency1;
    //private double qtyPerTime1;
    private double qtyPerTime1;
    private int duration1;
    //private double totalQty;
    
    private String durationType;
    private String instruction;
    private static String INSTRUCTIONS = new String();
    public static int row1;
    public static int row2;
    //order
    private String productCode;
    private String orderStatus;
    
    private String totalQty1;
    private String qtydispensed;
    private int totalQty;
    private int qtydispensed1;
    
    private String ddate;
    private String EpisodeTime;
    private String IDPMS;
    String oNo;
    private boolean oSM;
    private boolean oSD;
    String oTo;
    private int spubNo;
    String kiBy;
    //String toi;
    private int toi;
    String sOUM;
    String dOUM;
    String hfc;
    String oF;
    private Timestamp ec;
    private Timestamp ed;
    private Timestamp oDate;

    private static ArrayList<ArrayList<String>> om = new ArrayList<ArrayList<String>>();
    private String oD;
    private String lc;
    private String ad;
    private String dB;
//    ArrayList data2;
    
    private static int max_row_drug = 100;
    private static int max_col_drug = 7;
    
    /**
     * NEW VAR DRUG 
     * - add new drug /update existing drug
     * - update stock drug
     */
    String dmdc;
    String datc;
    String dtraden;
    String dgnrn;
    String droute;
    String ddosage;
    String dstrength;
    String dstockqty;
    String dloccode;
    String dstatus;
    String dpackaging;
    String dpackagingType;
    String dpriceppack;
    String dcostp;
    String dsellp;
    String dLqty;
    String dLqtyt;
    String dLfreq;
    String dLduration;
    String dLdurationType;
    String dLadvisory;
    String dLcaution;
    String dLexpdate;
    String dLclassification;
    

    
    /** Creates new form Pharmacy */
    public Pharmacy() {
        if(null == btn_PrintLabel.getText())
        {
            btn_PrintLabel.setEnabled(false);
        }
        else
        {
            btn_PrintLabel.setEnabled(true);
        }
        initComponents();
        S.oln("Hello CIS 1 cannot use");
        
    }
//    public Pharmacy(String pmino){
//        initComponents();
//        pmiNo = pmino;
//        data2 = new ArrayList();
//    }
    
    private void clearSearch() {
        rb_newOList2.setSelected(true);
        rb_oldOList2.setSelected(false);
        cpyFile_history = "-";
    }
    
    private void filterOrderMaster(String txtIC)
    {
        int omIndex = om.indexOf(txtIC);
        if(omIndex != -1)
        {
            tbl_patientInQueue.getModel().setValueAt(om.get(omIndex).get(1), 1, 0);//pmino
            tbl_patientInQueue.getModel().setValueAt(om.get(omIndex).get(17), 1, 1);//pname
            tbl_patientInQueue.getModel().setValueAt(om.get(omIndex).get(5), 1, 2);//odate
            tbl_patientInQueue.getModel().setValueAt(om.get(omIndex).get(2), 1, 3);//loca code
            tbl_patientInQueue.getModel().setValueAt(om.get(omIndex).get(3), 1, 4);//arrival date
            tbl_patientInQueue.getModel().setValueAt(om.get(omIndex).get(6), 1, 5);//doc's name
        }
    }
    
    private void getOrderMaster(int stat, String pmi_no, String order_no) {
        clearQueue();
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	
                
                om = DBConnection.getImpl().getOrderMasterAll(stat, pmi_no, order_no);
                S.oln("Get Order Master");
                showOnline();
                for (int i = 0; i < 30 && i < om.size(); i++) {
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(1), i, 0);//pmino
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(17), i, 1);//pname
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(5), i, 2);//odate
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(2), i, 3);//loca code
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(3), i, 4);//arrival date
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(6), i, 5);//doc's name
                }


                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                S.oln("-- Offline --");
                //guna current date, why? bcoz nk retrieve curr date je unless..
                om = DBConnection.getPatientInQueueOff(stat, pmi_no, order_no);//getPatientInQueue
                showOffline();
                for (int i = 0; i < 30 && i < om.size(); i++) {
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(1), i, 0);//pmino
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(17), i, 1);//pname
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(5), i, 2);//odate
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(2), i, 3);//loca code
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(3), i, 4);//arrival date
                    tbl_patientInQueue.getModel().setValueAt(om.get(i).get(6), i, 5);//doc's name
                }
                System.err.println("get order master error :"+e);
                e.printStackTrace();
            }
//        } else {
//            //offline
//            S.oln("-- Offline --");
//            //guna current date, why? bcoz nk retrieve curr date je unless..
//            om = DBConnection.getPatientInQueueOff(stat, pmi_no, order_no);//getPatientInQueue
//            
//            for (int i = 0; i < 30 && i < om.size(); i++) {
//                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(1), i, 0);//pmino
//                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(17), i, 1);//pname
//                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(5), i, 2);//odate
//                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(2), i, 3);//loca code
//                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(3), i, 4);//arrival date
//                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(6), i, 5);//doc's name
//            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
    }
    
    private void clearQueue() {
        //clear
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 6; j++) {
                tbl_patientInQueue.getModel().setValueAt("", i, j);
            }
        }
    }
    
    private void getOrderMasterOff(int stat, String pmi_no, String order_no) {
        clearQueue();
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
           
//        } else {
            //offline
            S.oln("-- Offline --");

            om = DBConnection.getPatientInQueueOff(stat, pmi_no, order_no);

            for (int i = 0; i < 30 && i < om.size(); i++) {
                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(1), i, 0);//pmino
                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(17), i, 1);//pname
                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(5), i, 2);//odate
                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(2), i, 3);//loca code
                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(3), i, 4);//arrival date
                tbl_patientInQueue.getModel().setValueAt(om.get(i).get(6), i, 5);//doc's name
            }
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
    }

    /*Pharmacy constructor which accept value*/
    public Pharmacy(String id, String staffName) {
        initComponents();
        
        getOrderMaster(1,""," ");
        
        data = new SearchData();//this is for search patient at tab 0
        //****************************DISPLAY FOR TAB 0************************************
    
        generateNo();   
        
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        
        arrival_date.setText(""+timestamp);
       
        order_date1.setText("Order Date : "+timestamp);
       
        //****************************DISPLAY FOR TAB 3************************************
    
        UpdateTbl();
        fillcombo();
        
        S.oln("haha1");
       
        //SUPPLIER ID GENERATE
        String[] AutogenerateSPL = {};
        pis autogenerate = new pis();
        try {
            AutogenerateSPL = autogenerate.getAutogenerateSPL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //SUPPLIER ID GENERATE SHOW
        txt_supid.setText("SPL" + AutogenerateSPL[0]);  
        System.out.println("Hello CIS 2");
        
        
        //****************************DISPLAY FOR * TABs ************************************
        this.id = Session.getUser_id();
        this.staffName = Session.getUser_name();

        //display user name and id on each page
       
        txt_userNameOList.setText(Session.getUser_id());
        txt_userIDOList.setText(Session.getUser_name());

        //txt_userNameDrugOrder.setText(staffName);
        //txt_userIDDrugOrder.setText(id);
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        Spatient = new javax.swing.JDialog();
        Spatient_panel = new javax.swing.JPanel();
        lbl_pmiNoSearchOList2 = new java.awt.Label();
        lbl_idTypeSearchOList2 = new java.awt.Label();
        cb_idTypeSearch1OList2 = new javax.swing.JComboBox();
        txt_pmiNoSearch1OList2 = new javax.swing.JTextField();
        lbl_icNoSearchOList2 = new java.awt.Label();
        txt_icNoSearch1OList2 = new javax.swing.JTextField();
        lbl_idNoSearchOList2 = new java.awt.Label();
        txt_idNoSearch1OList2 = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        rb_newOList2 = new javax.swing.JRadioButton();
        rb_oldOList2 = new javax.swing.JRadioButton();
        btn_searchOList2 = new javax.swing.JButton();
        btn_readMyCard2 = new javax.swing.JButton();
        btn_ok2 = new javax.swing.JButton();
        lbl_searchPatientOList1 = new java.awt.Label();
        jPanel29 = new javax.swing.JPanel();
        lbl_userInfoUpdateStock3 = new java.awt.Label();
        jPanel31 = new javax.swing.JPanel();
        txt_dmdc_code = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        txt_dproduct_name = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        txt_dmdc_desc = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        txt_dingredient_code = new javax.swing.JTextArea();
        txt_dcaution_code = new javax.swing.JTextField();
        txt_dform_code = new javax.swing.JTextField();
        txt_ddef_dosage = new javax.swing.JTextField();
        txt_droute_code = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        txt_dadvisory_code = new javax.swing.JTextField();
        txt_datc_code = new javax.swing.JTextField();
        txt_ddrug_strength = new javax.swing.JTextField();
        txt_dstock_qty = new javax.swing.JTextField();
        txt_dloc_code = new javax.swing.JTextField();
        combox_supname = new javax.swing.JComboBox();
        btn_dsave = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btn_dcancel = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tpnl_pharmacy = new javax.swing.JTabbedPane();
        pnl_drugOrder = new javax.swing.JPanel();
        tab_drugOrder = new javax.swing.JTabbedPane();
        pnl_patientList = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_patientInQueue = new javax.swing.JTable();
        btn_refresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txt_search_OrderNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_search = new javax.swing.JButton();
        jLblIC = new javax.swing.JLabel();
        txt_search_pol1 = new javax.swing.JTextField();
        pnl_patientDrugOrder = new javax.swing.JPanel();
        lbl_patientInfo = new java.awt.Label();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbl_drugList = new javax.swing.JTable();
        btn_dispense = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btn_PrintLabel = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_drugOrder = new javax.swing.JTable();
        lbl_doctor = new javax.swing.JLabel();
        txt_doctor = new javax.swing.JTextField();
        jPanel37 = new javax.swing.JPanel();
        lbl_patientName = new javax.swing.JLabel();
        lbl_pmiNo = new javax.swing.JLabel();
        txt_pmiNo = new javax.swing.JTextField();
        txt_patientName = new javax.swing.JTextField();
        lbl_icNo = new javax.swing.JLabel();
        txt_icNo = new javax.swing.JTextField();
        lbl_sex = new javax.swing.JLabel();
        txt_sex = new javax.swing.JTextField();
        lbl_race = new javax.swing.JLabel();
        lbl_birthDate = new javax.swing.JLabel();
        txt_birthDate = new javax.swing.JTextField();
        txt_race = new javax.swing.JTextField();
        lbl_allergy = new javax.swing.JLabel();
        lbl_bloodType = new javax.swing.JLabel();
        txt_bloodType = new javax.swing.JTextField();
        cbAllergy = new javax.swing.JComboBox();
        jPanel38 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        order_no2 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        order_date2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        loc_code = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        arrival_date = new javax.swing.JTextField();
        pnl_prepareOrderList = new javax.swing.JPanel();
        lbl_prepareDrugOrderOList = new java.awt.Label();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbl_drugOList = new javax.swing.JTable();
        btn_editOList = new javax.swing.JButton();
        btn_deleteOList = new javax.swing.JButton();
        btn_newOrder = new javax.swing.JButton();
        btn_submitOList = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        lbl_pmiNoOList = new javax.swing.JLabel();
        lbl_patientNameOList = new javax.swing.JLabel();
        txt_patientNameOList = new javax.swing.JTextField();
        lbl_idNoOList = new javax.swing.JLabel();
        txt_idNoOList = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_raceOList = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_bodOList = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_genderOList = new javax.swing.JTextField();
        txt_bloodOList = new javax.swing.JTextField();
        txt_pmiNoOList1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        lbl_drugNameOListSearch = new javax.swing.JLabel();
        txt_drugNameOListSearch = new javax.swing.JTextField();
        btn_clearOList = new javax.swing.JButton();
        stock_qty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_productname = new javax.swing.JTable();
        lbl_productNameOList = new javax.swing.JLabel();
        txt_productNameOList = new javax.swing.JTextField();
        btn_saveOList = new javax.swing.JButton();
        btn_cancelOList = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
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
        jLabel4 = new javax.swing.JLabel();
        txt_dosageFormOList = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_caution = new javax.swing.JTextArea();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_DrugHist = new javax.swing.JTable();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbl_aller = new javax.swing.JTable();
        order_date1 = new javax.swing.JLabel();
        order_no = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel27 = new javax.swing.JLabel();
        pnl_convertAndManage = new javax.swing.JPanel();
        tpnl_manageDCode = new javax.swing.JTabbedPane();
        pnl_atc = new javax.swing.JPanel();
        lbl_searchDrugATC = new java.awt.Label();
        lbl_drugATCDetails = new java.awt.Label();
        jPanel5 = new javax.swing.JPanel();
        lbl_atcCode = new javax.swing.JLabel();
        txt_atcCode = new javax.swing.JTextField();
        lbl_atcDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_atcDescription = new javax.swing.JTextArea();
        txt_categoryCode = new javax.swing.JTextField();
        lbl_categoryCode = new javax.swing.JLabel();
        lbl_status = new javax.swing.JLabel();
        pnl_statusRB = new javax.swing.JPanel();
        rbt_activeATC = new javax.swing.JRadioButton();
        rbt_inactiveATC = new javax.swing.JRadioButton();
        btn_updateATC = new javax.swing.JButton();
        btn_cancelATC = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbl_atc = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jtfatc = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTatc = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        lbl_atcCodeSearch = new javax.swing.JLabel();
        pnl_mdc = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_cancelMDC = new javax.swing.JButton();
        btn_updateMDC = new javax.swing.JButton();
        btn_addmdc = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tbl_mdc = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jtdrugS2 = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jT_S3 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbl_searchDrugMDC = new java.awt.Label();
        jPanel18 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbl_drugNameMDC = new javax.swing.JLabel();
        txt_drugNameMDC = new javax.swing.JTextField();
        lbl_dosageForm = new javax.swing.JLabel();
        lbl_mdcCode = new javax.swing.JLabel();
        txt_mdcCode = new javax.swing.JTextField();
        lbl_ingredientCode = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txt_ingredientCode = new javax.swing.JTextArea();
        lbl_drugRoute = new javax.swing.JLabel();
        txt_drugRoute = new javax.swing.JTextField();
        lbl_drugStrength = new javax.swing.JLabel();
        txt_drugStrength = new javax.swing.JTextField();
        cdosage_form = new javax.swing.JComboBox();
        lbl_stockQtyUStock1 = new javax.swing.JLabel();
        txt_stockQty = new javax.swing.JTextField();
        lbl_locationCodeUStock1 = new javax.swing.JLabel();
        txt_locCode = new javax.swing.JTextField();
        lbl_supplierUStock1 = new javax.swing.JLabel();
        cb_supplierUStock = new javax.swing.JComboBox();
        lbl_statusMDC = new javax.swing.JLabel();
        rbt_activeMDC = new javax.swing.JRadioButton();
        rbt_inactiveMDC = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        dpack1 = new javax.swing.JTextField();
        cdpack2 = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        txt_costPrice = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_sellprice = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        d_priceppack = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        lbl_quantityOList1 = new javax.swing.JLabel();
        txt_Lqty = new javax.swing.JTextField();
        lbl_frequencyOList1 = new javax.swing.JLabel();
        cLfrequency = new javax.swing.JComboBox();
        lbl_durationOList1 = new javax.swing.JLabel();
        cLduration = new javax.swing.JComboBox();
        cLdurationType = new javax.swing.JComboBox();
        cInstruction = new javax.swing.JComboBox();
        lbl_instructionOList1 = new javax.swing.JLabel();
        lbl_cautionary = new javax.swing.JLabel();
        txt_cautionary = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cClassification = new javax.swing.JComboBox();
        txt_expdate = new com.toedter.calendar.JDateChooser();
        cLqtyT = new javax.swing.JComboBox();
        pnl_import = new javax.swing.JPanel();
        lbl_browseFileConvert = new java.awt.Label();
        jPanel25 = new javax.swing.JPanel();
        lbl_importFile = new javax.swing.JLabel();
        txt_importFile = new javax.swing.JTextField();
        btn_browse = new javax.swing.JButton();
        btn_importATC = new javax.swing.JButton();
        btn_importMDC = new javax.swing.JButton();
        lbl_browseFileConvert1 = new java.awt.Label();
        jPanel28 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_supplier = new javax.swing.JTable();
        lbl_userInfoUpdateStock2 = new java.awt.Label();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txt_supid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txt_supname = new javax.swing.JTextArea();
        txt_supcontact = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_supdel = new javax.swing.JButton();
        btn_supCancel = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_supSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDaily = new javax.swing.JButton();
        btnMonthly = new javax.swing.JButton();
        btnYearly = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        lbl_userNameOList = new javax.swing.JLabel();
        txt_userNameOList = new javax.swing.JTextField();
        jToolBar3 = new javax.swing.JToolBar();
        lbl_userIDOList = new javax.swing.JLabel();
        txt_userIDOList = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        btn_mainPageOList = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        btnStatus = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        Spatient.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Spatient.setTitle("Pharmacy : Search Patient");
        Spatient.setMinimumSize(new java.awt.Dimension(1016, 180));
        Spatient.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        Spatient.setResizable(false);
        Spatient.add(Spatient_panel);
        Spatient.pack();

        Spatient_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        Spatient_panel.setPreferredSize(new java.awt.Dimension(1066, 301));

        lbl_pmiNoSearchOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbl_pmiNoSearchOList2.setText("PMI No.");

        lbl_idTypeSearchOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbl_idTypeSearchOList2.setText("Id Type");

        cb_idTypeSearch1OList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        cb_idTypeSearch1OList2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Type", "Police", "Army", "Foreigner" }));

        txt_pmiNoSearch1OList2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pmiNoSearch1OList2EnablePMIReferral(evt);
            }
        });

        lbl_icNoSearchOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbl_icNoSearchOList2.setText("IC No.");

        txt_icNoSearch1OList2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_icNoSearch1OList2EnableICReferral(evt);
            }
        });

        lbl_idNoSearchOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbl_idNoSearchOList2.setText("Identification No.");

        txt_idNoSearch1OList2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idNoSearch1OList2EnableIDReferral(evt);
            }
        });

        jPanel34.setBackground(new java.awt.Color(204, 204, 255));

        rb_newOList2.setBackground(new java.awt.Color(204, 204, 255));
        rb_newOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        rb_newOList2.setSelected(true);
        rb_newOList2.setText("New");
        rb_newOList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb_newOList2MouseClicked(evt);
            }
        });

        rb_oldOList2.setBackground(new java.awt.Color(204, 204, 255));
        rb_oldOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        rb_oldOList2.setText("Old");
        rb_oldOList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb_oldOList2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb_newOList2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_oldOList2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_newOList2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_oldOList2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btn_searchOList2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        btn_searchOList2.setText("Search");
        btn_searchOList2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchOList2ActionPerformed(evt);
            }
        });

        btn_readMyCard2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        btn_readMyCard2.setText("Read MyKAD Info");
        btn_readMyCard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_readMyCard2ActionPerformed(evt);
            }
        });

        btn_ok2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        btn_ok2.setText("OK & Close");
        btn_ok2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Spatient_panelLayout = new javax.swing.GroupLayout(Spatient_panel);
        Spatient_panel.setLayout(Spatient_panelLayout);
        Spatient_panelLayout.setHorizontalGroup(
            Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Spatient_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Spatient_panelLayout.createSequentialGroup()
                        .addComponent(lbl_idTypeSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_idTypeSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lbl_idNoSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idNoSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Spatient_panelLayout.createSequentialGroup()
                        .addComponent(lbl_pmiNoSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pmiNoSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lbl_icNoSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(txt_icNoSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ok2)
                    .addComponent(btn_searchOList2)
                    .addComponent(btn_readMyCard2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Spatient_panelLayout.setVerticalGroup(
            Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Spatient_panelLayout.createSequentialGroup()
                .addGroup(Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_pmiNoSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_pmiNoSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_icNoSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_icNoSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_searchOList2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Spatient_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_idTypeSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_idTypeSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_idNoSearchOList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_idNoSearch1OList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_readMyCard2))
                .addGap(18, 18, 18)
                .addComponent(btn_ok2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SpatientLayout = new javax.swing.GroupLayout(Spatient.getContentPane());
        Spatient.getContentPane().setLayout(SpatientLayout);
        SpatientLayout.setHorizontalGroup(
            SpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Spatient_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                .addContainerGap())
        );
        SpatientLayout.setVerticalGroup(
            SpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Spatient_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_searchPatientOList1.setBackground(new java.awt.Color(204, 204, 204));
        lbl_searchPatientOList1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_searchPatientOList1.setText(" Search Patient");

        jPanel29.setBackground(new java.awt.Color(173, 182, 200));

        lbl_userInfoUpdateStock3.setBackground(new java.awt.Color(204, 204, 204));
        lbl_userInfoUpdateStock3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_userInfoUpdateStock3.setText("New Drug");

        txt_dmdc_code.setToolTipText("");

        jScrollPane14.setAutoscrolls(true);

        txt_dproduct_name.setColumns(20);
        txt_dproduct_name.setLineWrap(true);
        txt_dproduct_name.setRows(5);
        txt_dproduct_name.setWrapStyleWord(true);
        txt_dproduct_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane14.setViewportView(txt_dproduct_name);

        txt_dmdc_desc.setColumns(20);
        txt_dmdc_desc.setLineWrap(true);
        txt_dmdc_desc.setRows(5);
        txt_dmdc_desc.setText("null");
        txt_dmdc_desc.setWrapStyleWord(true);
        jScrollPane16.setViewportView(txt_dmdc_desc);

        txt_dingredient_code.setColumns(20);
        txt_dingredient_code.setLineWrap(true);
        txt_dingredient_code.setRows(5);
        txt_dingredient_code.setWrapStyleWord(true);
        jScrollPane19.setViewportView(txt_dingredient_code);

        txt_dcaution_code.setText("null");

        txt_ddef_dosage.setText("null");

        txt_droute_code.setText("null");

        jLabel8.setText("Ud Mdc Code :");

        jLabel9.setText("Product Name :");

        jLabel10.setText("Ud Mdc Description :");

        jLabel11.setText("Active Ingredient :");

        jLabel12.setText("Def Cautionary Code :");

        jLabel13.setText("Dosage Form :");

        jLabel14.setText("Def Dosage :");

        jLabel15.setText("Def Route Code :");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txt_dmdc_code)
                    .addComponent(jScrollPane14)
                    .addComponent(jScrollPane19)
                    .addComponent(txt_dcaution_code)
                    .addComponent(txt_dform_code)
                    .addComponent(txt_ddef_dosage)
                    .addComponent(txt_droute_code))
                .addGap(86, 86, 86))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dmdc_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dcaution_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dform_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ddef_dosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_droute_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addComponent(jLabel11))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        txt_dadvisory_code.setText("null");

        btn_dsave.setText("Save");
        btn_dsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dsaveActionPerformed(evt);
            }
        });

        jLabel16.setText("Def Advisory :");

        jLabel17.setText("Ud Atc Code :");

        jLabel20.setText("Stock Quantity :");

        jLabel21.setText("Location Code :");

        jLabel22.setText("Supplier Name :");

        btn_dcancel.setText("Cancel");
        btn_dcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dcancelActionPerformed(evt);
            }
        });

        jLabel18.setText("Drug Strength :");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(btn_dsave)
                        .addGap(18, 18, 18)
                        .addComponent(btn_dcancel))
                    .addComponent(txt_dadvisory_code, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_supname, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_datc_code, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_dloc_code, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addComponent(txt_dstock_qty, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_ddrug_strength, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(31, 31, 31))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dadvisory_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_datc_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ddrug_strength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dstock_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dloc_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combox_supname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dsave)
                    .addComponent(btn_dcancel))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_userInfoUpdateStock3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(lbl_userInfoUpdateStock3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(670, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pharmacy Information System");

        jScrollPane12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tpnl_pharmacy.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        label2.setBackground(new java.awt.Color(51, 51, 255));
        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText(" Patients");

        tbl_patientInQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PMI No.", "Name", "Order Date", "Location Code", "Arrival Date", "Doctor's Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_patientInQueue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_patientInQueueMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_patientInQueue);

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jLabel2.setText("PMI No :");

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        jLblIC.setText("Order No :");

        txt_search_pol1.setText("PMS");
        txt_search_pol1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_pol1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_search_pol1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLblIC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search_OrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btn_search)
                    .addComponent(txt_search_pol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblIC)
                    .addComponent(txt_search_OrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_patientListLayout = new javax.swing.GroupLayout(pnl_patientList);
        pnl_patientList.setLayout(pnl_patientListLayout);
        pnl_patientListLayout.setHorizontalGroup(
            pnl_patientListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl_patientListLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(pnl_patientListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_patientListLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272)
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1007, Short.MAX_VALUE))
        );
        pnl_patientListLayout.setVerticalGroup(
            pnl_patientListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_patientListLayout.createSequentialGroup()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_patientListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(437, Short.MAX_VALUE))
        );

        tab_drugOrder.addTab("Patient Order List", pnl_patientList);

        lbl_patientInfo.setBackground(new java.awt.Color(51, 51, 255));
        lbl_patientInfo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_patientInfo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_patientInfo.setText(" Patient Info");

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List Drug To Dispense", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        //int rowIndex = tbl_drugList.getSelectedRow();
        tbl_drugList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Drug Code", "Drug Desc", "Instruction", "Qty Order", "Qty Supply", "Qty Dispensed", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_drugList.getTableHeader().setReorderingAllowed(false);
        tbl_drugList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_drugListMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tbl_drugList);

        btn_dispense.setText("Dispense");
        btn_dispense.setEnabled(false);
        btn_dispense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dispenseActionPerformed(evt);
            }
        });

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btn_PrintLabel.setText("Print Label");
        btn_PrintLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(btn_dispense)
                        .addGap(18, 18, 18)
                        .addComponent(btn_PrintLabel)
                        .addGap(413, 413, 413))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dispense)
                    .addComponent(jButton5)
                    .addComponent(btn_PrintLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List Drug Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        int rowIndex = tbl_drugOrder.getSelectedRow();
        tbl_drugOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Drug Code", "Drug Desc", "Instruction", "Qty Order", "Qty Supply", "Qty Dispensed", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_drugOrder.getTableHeader().setReorderingAllowed(false);
        tbl_drugOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_drugOrderMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_drugOrder);

        lbl_doctor.setText("Order By :");

        txt_doctor.setEditable(false);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(lbl_doctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_doctor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Patient Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbl_patientName.setText("Patient Name :");

        lbl_pmiNo.setText("PMI No :");

        txt_pmiNo.setEditable(false);
        txt_pmiNo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txt_patientName.setEditable(false);
        txt_patientName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lbl_icNo.setText("IC No :");

        txt_icNo.setEditable(false);
        txt_icNo.setBorder(null);

        lbl_sex.setText("Gender :");

        txt_sex.setEditable(false);
        txt_sex.setBorder(null);

        lbl_race.setText("Race :");

        lbl_birthDate.setText("Birth Date :");

        txt_birthDate.setEditable(false);
        txt_birthDate.setBorder(null);

        txt_race.setEditable(false);
        txt_race.setBorder(null);

        lbl_allergy.setText("Allergy :");

        lbl_bloodType.setText("Blood Type :");

        txt_bloodType.setEditable(false);
        txt_bloodType.setBorder(null);

        cbAllergy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- No Allergy --" }));
        cbAllergy.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_patientName)
                    .addComponent(lbl_pmiNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_pmiNo)
                    .addComponent(txt_patientName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(lbl_icNo)
                        .addGap(18, 18, 18)
                        .addComponent(txt_icNo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(lbl_sex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_birthDate)
                    .addComponent(lbl_race))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_race)
                    .addComponent(txt_birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_bloodType)
                    .addComponent(lbl_allergy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_bloodType, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(cbAllergy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_patientName)
                    .addComponent(txt_patientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_icNo)
                    .addComponent(txt_icNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_birthDate)
                    .addComponent(txt_birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bloodType)
                    .addComponent(txt_bloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_pmiNo)
                    .addComponent(txt_pmiNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_sex)
                    .addComponent(txt_sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_race)
                    .addComponent(txt_race, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_allergy)
                    .addComponent(cbAllergy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel42.setText("Order No :");

        order_no2.setBackground(new java.awt.Color(240, 240, 240));
        order_no2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel43.setText("Order Date :");

        order_date2.setBackground(new java.awt.Color(240, 240, 240));

        jLabel44.setText("Loc Code :");

        loc_code.setBackground(new java.awt.Color(240, 240, 240));

        jLabel45.setText("Arrival Date :");

        arrival_date.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(order_no2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(order_date2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loc_code, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arrival_date, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel42)
                    .addComponent(order_no2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(order_date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(loc_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(arrival_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_patientDrugOrderLayout = new javax.swing.GroupLayout(pnl_patientDrugOrder);
        pnl_patientDrugOrder.setLayout(pnl_patientDrugOrderLayout);
        pnl_patientDrugOrderLayout.setHorizontalGroup(
            pnl_patientDrugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_patientInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl_patientDrugOrderLayout.createSequentialGroup()
                .addGroup(pnl_patientDrugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_patientDrugOrderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnl_patientDrugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_patientDrugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnl_patientDrugOrderLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(749, Short.MAX_VALUE))
        );
        pnl_patientDrugOrderLayout.setVerticalGroup(
            pnl_patientDrugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_patientDrugOrderLayout.createSequentialGroup()
                .addComponent(lbl_patientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        tab_drugOrder.addTab("Patient Drug Dispense", pnl_patientDrugOrder);

        pnl_prepareOrderList.setAutoscrolls(true);
        pnl_prepareOrderList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnl_prepareOrderList.setMinimumSize(new java.awt.Dimension(1271, 919));

        lbl_prepareDrugOrderOList.setBackground(new java.awt.Color(51, 51, 255));
        lbl_prepareDrugOrderOList.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_prepareDrugOrderOList.setForeground(new java.awt.Color(255, 255, 255));
        lbl_prepareDrugOrderOList.setText(" Prepare Drug Order");

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug Order Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jScrollPane17.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbl_drugOList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Drug Code", "Trade Name", "Frequency", "Route", "Drug Form", "Strength", "Dosage", "Order OUM", "Duration (Day)", "Qty Order", "Qty Supply", "Qty Dispense", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_drugOList.getTableHeader().setReorderingAllowed(false);
        jScrollPane17.setViewportView(tbl_drugOList);

        btn_editOList.setText("Edit");
        btn_editOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editOListActionPerformed(evt);
            }
        });

        btn_deleteOList.setText("Delete");
        btn_deleteOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteOListActionPerformed(evt);
            }
        });

        btn_newOrder.setText("New Order List");
        btn_newOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newOrderActionPerformed(evt);
            }
        });

        btn_submitOList.setText("Prescribe");
        btn_submitOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitOListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane17)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGap(0, 617, Short.MAX_VALUE)
                        .addComponent(btn_editOList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_deleteOList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_newOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_submitOList)
                        .addGap(239, 239, 239))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_submitOList)
                            .addComponent(btn_newOrder)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_editOList)
                            .addComponent(btn_deleteOList))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Patient Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbl_pmiNoOList.setText("PMI No :");

        lbl_patientNameOList.setText("Name :");

        txt_patientNameOList.setEditable(false);
        txt_patientNameOList.setBorder(null);
        txt_patientNameOList.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_patientNameOList.setText(patientName);

        lbl_idNoOList.setText("ID No :");

        txt_idNoOList.setEditable(false);
        txt_idNoOList.setBorder(null);
        txt_idNoOList.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_idNoOList.setText(patientIC);

        jLabel23.setText("Race :");

        txt_raceOList.setEditable(false);
        txt_raceOList.setBorder(null);
        txt_raceOList.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_raceOList.setText(race);

        jLabel24.setText("Birth Date :");

        txt_bodOList.setEditable(false);
        txt_bodOList.setBorder(null);
        txt_bodOList.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_bodOList.setText(bod);

        jLabel25.setText("Gender :");

        jLabel26.setText("Blood Type : ");

        txt_genderOList.setEditable(false);
        txt_genderOList.setBorder(null);
        txt_genderOList.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_genderOList.setText(gender);

        txt_bloodOList.setEditable(false);
        txt_bloodOList.setBorder(null);
        txt_bloodOList.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_bloodOList.setText(blood);

        txt_pmiNoOList1.setEditable(false);
        txt_pmiNoOList1.setBackground(new java.awt.Color(240, 240, 240));
        txt_pmiNoOList1.setBorder(null);
        txt_pmiNoOList1.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txt_pmiNoOList1.setText(pmiNo);

        jButton6.setText("Search Offline");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_patientNameOList, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_pmiNoOList, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(txt_pmiNoOList1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lbl_idNoOList)
                        .addGap(18, 18, 18)
                        .addComponent(txt_idNoOList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(txt_genderOList, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(txt_bloodOList, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(txt_raceOList, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(txt_bodOList, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_patientNameOList, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_pmiNoOList)
                            .addComponent(lbl_idNoOList)
                            .addComponent(txt_idNoOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(txt_genderOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(txt_bloodOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txt_raceOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pmiNoOList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(txt_bodOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_patientNameOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_patientNameOList))
                        .addGap(64, 64, 64))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search Drug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbl_drugNameOListSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_drugNameOListSearch.setText("Search :");

        txt_drugNameOListSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_drugNameOListSearchKeyReleased(evt);
            }
        });

        btn_clearOList.setText("Clear");
        btn_clearOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearOListActionPerformed(evt);
            }
        });

        stock_qty.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Stock Quantity :");

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
        tbl_productname.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_productname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productnameMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_productname);

        lbl_productNameOList.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_productNameOList.setText("Trade Name :");

        txt_productNameOList.setEditable(false);

        btn_saveOList.setText("Accept");
        btn_saveOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveOListActionPerformed(evt);
            }
        });

        btn_cancelOList.setText("Cancel");
        btn_cancelOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelOListActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug Details"));

        lbl_dosageOList.setText("Drug Strength :");

        txt_drugstrength.setEditable(false);

        lbl_quantityOList.setText("Quantity :");

        lbl_frequencyOList.setText("Frequency :");

        cb_frequencyOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "In the morning", "At night", "Daily", "Twice a day", "3 times a day", "4 times a day", "2 hourly", "4 hourly", "6 hourly", "8 hourly", "Immedietly", "As needed" }));

        lbl_durationOList.setText("Duration :");

        cb_durationOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        cb_durationTypeOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Day", "Week", "Month" }));

        cb_instructionOList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "If required", "As directed", "Before meals", "After meals", "Every second day", "Left side", "Right side", "To both sides", "Other" }));

        lbl_instructionOList.setText("Instruction :");

        jLabel4.setText("Cautionary :");

        txt_caution.setColumns(5);
        txt_caution.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_caution.setLineWrap(true);
        txt_caution.setRows(5);
        txt_caution.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txt_caution);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_dosageOList)
                    .addComponent(lbl_quantityOList)
                    .addComponent(lbl_frequencyOList)
                    .addComponent(lbl_durationOList)
                    .addComponent(lbl_instructionOList)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(cb_durationOList, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_durationTypeOList, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(cb_instructionOList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_frequencyOList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_quantityOList, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txt_drugstrength, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_dosageFormOList, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_dosageOList)
                    .addComponent(txt_drugstrength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dosageFormOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quantityOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_quantityOList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_frequencyOList)
                    .addComponent(cb_frequencyOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_durationOList)
                    .addComponent(cb_durationOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_durationTypeOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_instructionOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_instructionOList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 33, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_drugNameOListSearch)
                            .addComponent(jLabel5)
                            .addComponent(lbl_productNameOList))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_productNameOList, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txt_drugNameOListSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_saveOList)
                    .addComponent(btn_cancelOList)
                    .addComponent(btn_clearOList))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(stock_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_drugNameOListSearch)
                    .addComponent(txt_drugNameOListSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clearOList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stock_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_productNameOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_productNameOList))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(btn_saveOList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancelOList)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbl_DrugHist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Drug Code", "Drug Name", "Qty Dispensed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_DrugHist);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Patient Allergy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbl_aller.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Allergy Code", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane22.setViewportView(tbl_aller);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        order_date1.setText("Order Date");

        order_no.setText("jLabel2");

        jLabel27.setText("Order No:");

        javax.swing.GroupLayout pnl_prepareOrderListLayout = new javax.swing.GroupLayout(pnl_prepareOrderList);
        pnl_prepareOrderList.setLayout(pnl_prepareOrderListLayout);
        pnl_prepareOrderListLayout.setHorizontalGroup(
            pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                .addGroup(pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                                        .addGroup(pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                                                .addGap(220, 220, 220)
                                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_prepareOrderListLayout.createSequentialGroup()
                                                .addComponent(jLabel27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(order_no, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(order_date1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_prepareDrugOrderOList, javax.swing.GroupLayout.PREFERRED_SIZE, 1261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(702, Short.MAX_VALUE))
        );
        pnl_prepareOrderListLayout.setVerticalGroup(
            pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(lbl_prepareDrugOrderOList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(order_date1)
                    .addComponent(order_no)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_prepareOrderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_prepareOrderListLayout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        tab_drugOrder.addTab(" Drug Order", pnl_prepareOrderList);

        javax.swing.GroupLayout pnl_drugOrderLayout = new javax.swing.GroupLayout(pnl_drugOrder);
        pnl_drugOrder.setLayout(pnl_drugOrderLayout);
        pnl_drugOrderLayout.setHorizontalGroup(
            pnl_drugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_drugOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab_drugOrder)
                .addContainerGap())
        );
        pnl_drugOrderLayout.setVerticalGroup(
            pnl_drugOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_drugOrder, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tpnl_pharmacy.addTab("PATIENT", pnl_drugOrder);

        tpnl_manageDCode.setBackground(new java.awt.Color(173, 182, 200));

        lbl_searchDrugATC.setBackground(new java.awt.Color(51, 51, 255));
        lbl_searchDrugATC.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_searchDrugATC.setForeground(new java.awt.Color(255, 255, 255));
        lbl_searchDrugATC.setText(" Search Drug");

        lbl_drugATCDetails.setBackground(new java.awt.Color(51, 51, 255));
        lbl_drugATCDetails.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_drugATCDetails.setForeground(new java.awt.Color(255, 255, 255));
        lbl_drugATCDetails.setText(" Drug's ATC Details");

        lbl_atcCode.setText("ATC Code :");

        lbl_atcDescription.setText("ATC Description :");

        txa_atcDescription.setColumns(20);
        txa_atcDescription.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txa_atcDescription.setRows(5);
        jScrollPane1.setViewportView(txa_atcDescription);

        lbl_categoryCode.setText("Category Code :");

        lbl_status.setText("Status :");

        buttonGroup1.add(rbt_activeATC);
        rbt_activeATC.setText("Active");

        buttonGroup1.add(rbt_inactiveATC);
        rbt_inactiveATC.setText("Inactive");

        javax.swing.GroupLayout pnl_statusRBLayout = new javax.swing.GroupLayout(pnl_statusRB);
        pnl_statusRB.setLayout(pnl_statusRBLayout);
        pnl_statusRBLayout.setHorizontalGroup(
            pnl_statusRBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_statusRBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbt_activeATC)
                .addGap(12, 12, 12)
                .addComponent(rbt_inactiveATC)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pnl_statusRBLayout.setVerticalGroup(
            pnl_statusRBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_statusRBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_statusRBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbt_inactiveATC)
                    .addComponent(rbt_activeATC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_updateATC.setText("Update");
        btn_updateATC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateATCActionPerformed(evt);
            }
        });

        btn_cancelATC.setText("Cancel");
        btn_cancelATC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelATCActionPerformed(evt);
            }
        });

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_atcCode)
                    .addComponent(lbl_atcDescription)
                    .addComponent(lbl_categoryCode)
                    .addComponent(lbl_status))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_statusRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(txt_atcCode)
                        .addComponent(txt_categoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cancelATC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_updateATC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_updateATC))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_atcCode)
                            .addComponent(txt_atcCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_atcDescription)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_categoryCode)
                    .addComponent(txt_categoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_status)
                    .addComponent(pnl_statusRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelATC))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(153, 204, 255));

        tbl_atc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_atc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_atcMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tbl_atc);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search ATC"));

        jtfatc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfatcKeyReleased(evt);
            }
        });

        jTatc.setModel(new javax.swing.table.DefaultTableModel(
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
                "ATC Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTatc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTatcMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTatc);

        jButton9.setText("Clear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lbl_atcCodeSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_atcCodeSearch.setText("ATC Code :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lbl_atcCodeSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfatc)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfatc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(lbl_atcCodeSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnl_atcLayout = new javax.swing.GroupLayout(pnl_atc);
        pnl_atc.setLayout(pnl_atcLayout);
        pnl_atcLayout.setHorizontalGroup(
            pnl_atcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_searchDrugATC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_drugATCDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 1998, Short.MAX_VALUE)
            .addGroup(pnl_atcLayout.createSequentialGroup()
                .addGroup(pnl_atcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_atcLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_atcLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(757, Short.MAX_VALUE))
        );
        pnl_atcLayout.setVerticalGroup(
            pnl_atcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_atcLayout.createSequentialGroup()
                .addComponent(lbl_searchDrugATC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_drugATCDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_atcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(369, Short.MAX_VALUE))
        );

        tpnl_manageDCode.addTab("ATC", pnl_atc);

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));

        btn_cancelMDC.setText("Cancel");
        btn_cancelMDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelMDCActionPerformed(evt);
            }
        });

        btn_updateMDC.setText("Update");
        btn_updateMDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateMDCActionPerformed(evt);
            }
        });

        btn_addmdc.setText("Add");
        btn_addmdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addmdcActionPerformed(evt);
            }
        });

        btn_del.setText("Delete");
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        jButton4.setText("Export to Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton10.setText("truncate");
        jButton10.setEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_updateMDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelMDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_addmdc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4)
                    .addComponent(jButton10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_addmdc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_updateMDC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_del)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelMDC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(101, 101, 101)
                .addComponent(jButton10)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(153, 204, 255));

        tbl_mdc.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_mdc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mdcMouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(tbl_mdc);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search Drug"));

        jtdrugS2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtdrugS2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtdrugS2KeyReleased(evt);
            }
        });

        jT_S3.setModel(new javax.swing.table.DefaultTableModel(
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
                "Product Name"
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
        jT_S3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_S3MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jT_S3);

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Product Name :");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addComponent(jtdrugS2))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton7)
                    .addComponent(jtdrugS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_searchDrugMDC.setBackground(new java.awt.Color(51, 51, 255));
        lbl_searchDrugMDC.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_searchDrugMDC.setForeground(new java.awt.Color(255, 255, 255));
        lbl_searchDrugMDC.setText(" Search Drug");
        jScrollPane2.setViewportView(lbl_searchDrugMDC);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug Details"));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drug Information"));

        lbl_drugNameMDC.setText("Trade Name :");

        txt_drugNameMDC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_dosageForm.setText("Dosage Form :");

        lbl_mdcCode.setText("Drug Code :");

        txt_mdcCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_ingredientCode.setText("Generic Name :");

        txt_ingredientCode.setColumns(20);
        txt_ingredientCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txt_ingredientCode.setLineWrap(true);
        txt_ingredientCode.setRows(5);
        txt_ingredientCode.setWrapStyleWord(true);
        txt_ingredientCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane8.setViewportView(txt_ingredientCode);

        lbl_drugRoute.setText("Drug Route :");

        txt_drugRoute.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_drugStrength.setText("Drug Strength :");

        txt_drugStrength.setToolTipText("Drug Strength : how much of active ingredient is present in EACH dosage");
        txt_drugStrength.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cdosage_form.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "AEROSOL", "AEROSOL, FOAM", "AEROSOL, METERED", "AEROSOL, POWDER", "AEROSOL, SPRAY", "BAR, CHEWABLE", "BEAD", "CAPSULE", "CAPSULE, COATED", "CAPSULE, COATED PELLETS", "CAPSULE, COATED, EXTENDED RELEASE", "CAPSULE, DELAYED RELEASE", "CAPSULE, DELAYED RELEASE PELLETS", "CAPSULE, EXTENDED RELEASE", "CAPSULE, FILM COATED, EXTENDED RELEASE", "CAPSULE, GELATIN COATED", "CAPSULE, LIQUID FILLED", "CELLULAR SHEET", "CLOTH", "CONCENTRATE", "CREAM", "CREAM, AUGMENTED", "CRYSTAL", "DISC", "DOUCHE", "DRESSING", "DRUG DELIVERY SYSTEM", "ELIXIR", "EMULSION", "ENEMA", "EXTRACT", "FIBER, EXTENDED RELEASE", "FILM", "FILM, EXTENDED RELEASE", "FILM, SOLUBLE", "FOR SOLUTION", "FOR SUSPENSION", "FOR SUSPENSION, EXTENDED RELEASE", "GAS", "GEL", "GEL, DENTIFRICE", "GEL, METERED", "GLOBULE", "GRANULE", "GRANULE, DELAYED RELEASE", "GRANULE, EFFERVESCENT", "GRANULE, FOR SOLUTION", "GRANULE, FOR SUSPENSION", "GRANULE, FOR SUSPENSION, EXTENDED RELEASE", "GUM, CHEWING", "IMPLANT", "INHALANT", "INJECTABLE, LIPOSOMAL", "INJECTION", "INJECTION, EMULSION", "INJECTION, LIPID COMPLEX", "INJECTION, POWDER, FOR SOLUTION", "INJECTION, POWDER, FOR SUSPENSION", "INJECTION, POWDER, FOR SUSPENSION, EXTENDED RELEASE", "INJECTION, POWDER, LYOPHILIZED, FOR LIPOSOMAL SUSPENSION", "INJECTION, POWDER, LYOPHILIZED, FOR SOLUTION", "INJECTION, POWDER, LYOPHILIZED, FOR SUSPENSION", "INJECTION, POWDER, LYOPHILIZED, FOR SUSPENSION, EXTENDED RELEASE", "INJECTION, SOLUTION", "INJECTION, SOLUTION, CONCENTRATE", "INJECTION, SUSPENSION", "INJECTION, SUSPENSION, EXTENDED RELEASE", "INJECTION, SUSPENSION, LIPOSOMAL", "INJECTION, SUSPENSION, SONICATED", "INSERT", "INSERT, EXTENDED RELEASE", "INTRAUTERINE DEVICE", "IRRIGANT", "JELLY", "KIT", "LINIMENT", "LIPSTICK", "LIQUID", "LIQUID, EXTENDED RELEASE", "LOTION", "LOTION, AUGMENTED", "LOTION/SHAMPOO", "LOZENGE", "MOUTHWASH", "OIL", "OINTMENT", "OINTMENT, AUGMENTED", "PASTE", "PASTE, DENTIFRICE", "PASTILLE", "PATCH", "PATCH, EXTENDED RELEASE", "PATCH, EXTENDED RELEASE, ELECTRICALLY CONTROLLED", "PELLET", "PELLET, IMPLANTABLE", "PELLETS, COATED, EXTENDED RELEASE", "PILL", "PLASTER", "POULTICE", "POWDER", "POWDER, DENTIFRICE", "POWDER, FOR SOLUTION", "POWDER, FOR SUSPENSION", "POWDER, METERED", "RING", "RINSE", "SALVE", "SHAMPOO", "SHAMPOO, SUSPENSION", "SOAP", "SOLUTION", "SOLUTION, CONCENTRATE", "SOLUTION, FOR SLUSH", "SOLUTION, GEL FORMING / DROPS", "SOLUTION, GEL FORMING, EXTENDED RELEASE", "SOLUTION/ DROPS", "SPONGE", "SPRAY", "SPRAY, METERED", "SPRAY, SUSPENSION", "STICK", "STRIP", "SUPPOSITORY", "SUPPOSITORY, EXTENDED RELEASE", "SUSPENSION", "SUSPENSION, EXTENDED RELEASE", "SUSPENSION/ DROPS", "SWAB", "SYRUP", "TABLET", "TABLET, CHEWABLE", "TABLET, COATED", "TABLET, COATED PARTICLES", "TABLET, DELAYED RELEASE", "TABLET, DELAYED RELEASE PARTICLES", "TABLET, EFFERVESCENT", "TABLET, EXTENDED RELEASE", "TABLET, FILM COATED", "TABLET, FILM COATED, EXTENDED RELEASE", "TABLET, FOR SOLUTION", "TABLET, FOR SUSPENSION", "TABLET, MULTILAYER", "TABLET, MULTILAYER, EXTENDED RELEASE", "TABLET, ORALLY DISINTEGRATING", "TABLET, ORALLY DISINTEGRATING, DELAYED RELEASE", "TABLET, SOLUBLE", "TABLET, SUGAR COATED", "TAMPON", "TAPE", "TINCTURE", "TROCHE", "WAFER" }));
        cdosage_form.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_stockQtyUStock1.setText("Stock Quantity :");

        txt_stockQty.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_locationCodeUStock1.setText("Location Code :");

        txt_locCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_supplierUStock1.setText("Supplier :");

        cb_supplierUStock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default Supplier" }));
        cb_supplierUStock.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_statusMDC.setText("Status :");

        buttonGroup3.add(rbt_activeMDC);
        rbt_activeMDC.setSelected(true);
        rbt_activeMDC.setText("Active");

        buttonGroup3.add(rbt_inactiveMDC);
        rbt_inactiveMDC.setText("Inactive");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_statusMDC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_supplierUStock1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_locationCodeUStock1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_stockQtyUStock1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_drugStrength, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_dosageForm, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_drugRoute, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_ingredientCode, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_drugNameMDC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_mdcCode, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_supplierUStock, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_locCode)
                            .addComponent(txt_stockQty)
                            .addComponent(txt_drugStrength)
                            .addComponent(cdosage_form, 0, 1, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(rbt_activeMDC)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbt_inactiveMDC))
                                    .addComponent(txt_mdcCode, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 72, Short.MAX_VALUE)))
                        .addContainerGap(83, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_drugRoute, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(txt_drugNameMDC, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_mdcCode)
                    .addComponent(txt_mdcCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_drugNameMDC)
                    .addComponent(txt_drugNameMDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ingredientCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_drugRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_drugRoute))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dosageForm)
                    .addComponent(cdosage_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_drugStrength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_drugStrength))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_stockQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_stockQtyUStock1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_locationCodeUStock1)
                    .addComponent(txt_locCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_supplierUStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_supplierUStock1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_statusMDC)
                    .addComponent(rbt_activeMDC)
                    .addComponent(rbt_inactiveMDC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Purchase"));

        jLabel34.setText("Packaging :");

        cdpack2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "AMP", "AP", "BAG", "BLPK", "BOT", "BOTAP", "BOTDIS", "BOTDR", "BOTGL", "BOTPL", "BOTPU", "BOTSPR", "BOTUD", "BOX", "BOXUD", "CAN", "CSTR", "CRTN", "CTG", "CASE", "CELLO", "CTR", "CUP", "CUPUD", "CYL", "DEW", "DLPK", "DSPK", "DRUM", "INHL", "INHLRE", "JAR", "JUG", "KIT", "NS", "PKG", "PKGCOM", "PKT", "POU", "SUPSACK", "SYR", "SYRGL", "SYRPL", "TABMIND", "TANK", "TRAY", "TUBE", "TUBEAP", "VIAL", "VIALDIS", "VIALGL", "VIALMD", "VIALPAT", "VIALPHR", "VIALPIG", "VIALPL", "VIALSD", "VIALSU" }));

        jLabel31.setText("Purchase Price :");

        txt_costPrice.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setText("Sell Price :");

        txt_sellprice.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setText("Price per Pack :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dpack1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cdpack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_costPrice)
                    .addComponent(txt_sellprice)
                    .addComponent(d_priceppack))
                .addGap(146, 146, 146))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel34)
                    .addComponent(dpack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdpack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d_priceppack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel31)
                    .addComponent(txt_costPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel32)
                    .addComponent(txt_sellprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Label Information"));

        lbl_quantityOList1.setText("Dosage :");

        txt_Lqty.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_frequencyOList1.setText("Frequency :");

        cLfrequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "In the morning", "At night", "Daily", "Twice a day", "3 times a day", "4 times a day", "2 hourly", "4 hourly", "6 hourly", "8 hourly", "Immedietly", "As needed" }));
        cLfrequency.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_durationOList1.setText("Duration :");

        cLduration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        cLdurationType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Day", "Week", "Month" }));

        cInstruction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "After meals", "As directed", "Before meals", "Both eye", "Every second day", "Gargle", "If required", "Immediately", "Left eye", "Left side", "Other", "Puff", "Right eye", "Right side", "To both sides" }));

        lbl_instructionOList1.setText("Instruction :");

        lbl_cautionary.setText("Cautionary :");

        jLabel30.setText("Expired Date :");

        jLabel33.setText("Classification :");

        cClassification.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Antacid/ Anti Spasmodic", "Anti Diarrheal", "Anti Dyspepsia", "Anti- Gout Agents", "Anti- Obesity", "Anti-Ashmatic & Bronchodilator", "Antibiotic", "Antiemetic / Anti Vertigo", "Anti-fungal", "Antihelmintic", "Anti-Histamine", "Antiseptic", "Anti-viral", "Cough & Cold Preparations", "Creams & Ointment", "Drugs Used in Substance  Dependence ", "Eye/Ear Drop", "Haermorrhoids", "Injection", "IV Drips", "Laxatives", "Lozenges", "Mucolytics Agents", "Nebulizer", "Nose prep", "NSAIDs", "Oral prep", "Oral Steroids", "Others", "Peripheral vasodilators/  migraine drug", "Shampoo", "Urinary Preparation", "Vitamin & supplements" }));
        cClassification.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_expdate.setDateFormatString("dd/MM/yyyy");
        txt_expdate.setMaxSelectableDate(new java.util.Date(253370739713000L));
        txt_expdate.setMinSelectableDate(new java.util.Date(-62135794687000L));

        cLqtyT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ml", "garg", "supp", "puff", " " }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel30)
                    .addComponent(lbl_cautionary)
                    .addComponent(lbl_instructionOList1)
                    .addComponent(lbl_durationOList1)
                    .addComponent(lbl_frequencyOList1)
                    .addComponent(lbl_quantityOList1))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(cClassification, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txt_Lqty, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cLqtyT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(cLduration, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(cLdurationType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cLfrequency, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cInstruction, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_expdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_cautionary, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(92, 92, 92))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_quantityOList1)
                    .addComponent(txt_Lqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLqtyT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_frequencyOList1)
                    .addComponent(cLfrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_durationOList1)
                    .addComponent(cLduration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLdurationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_instructionOList1)
                    .addComponent(cInstruction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_cautionary)
                    .addComponent(txt_cautionary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel30)
                    .addComponent(txt_expdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel33)
                    .addComponent(cClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_mdcLayout = new javax.swing.GroupLayout(pnl_mdc);
        pnl_mdc.setLayout(pnl_mdcLayout);
        pnl_mdcLayout.setHorizontalGroup(
            pnl_mdcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(pnl_mdcLayout.createSequentialGroup()
                .addGroup(pnl_mdcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_mdcLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_mdcLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_mdcLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(777, Short.MAX_VALUE))
            .addGroup(pnl_mdcLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 697, Short.MAX_VALUE))
        );
        pnl_mdcLayout.setVerticalGroup(
            pnl_mdcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_mdcLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnl_mdcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_mdcLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_mdcLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnl_manageDCode.addTab("MDC", pnl_mdc);

        lbl_browseFileConvert.setBackground(new java.awt.Color(51, 51, 255));
        lbl_browseFileConvert.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_browseFileConvert.setForeground(new java.awt.Color(255, 255, 255));
        lbl_browseFileConvert.setText(" Browse File Import ATC");

        lbl_importFile.setText("Import File :");

        btn_browse.setText("Browse");
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        btn_importATC.setText("Import Drug ATC");
        btn_importATC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importATCActionPerformed(evt);
            }
        });

        btn_importMDC.setText("Import Drug MDC");
        btn_importMDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importMDCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_importFile)
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(btn_importMDC)
                        .addGap(18, 18, 18)
                        .addComponent(btn_importATC))
                    .addComponent(txt_importFile, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_browse)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_importFile)
                    .addComponent(txt_importFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_browse))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_importATC)
                    .addComponent(btn_importMDC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_browseFileConvert1.setBackground(new java.awt.Color(51, 51, 255));
        lbl_browseFileConvert1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_browseFileConvert1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_browseFileConvert1.setText(" Browse File Import MDC");

        jLabel3.setText("Import Fie :");

        jButton2.setText("Browse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Import Drug MDC");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(333, 333, 333))
        );

        javax.swing.GroupLayout pnl_importLayout = new javax.swing.GroupLayout(pnl_import);
        pnl_import.setLayout(pnl_importLayout);
        pnl_importLayout.setHorizontalGroup(
            pnl_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_browseFileConvert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_browseFileConvert1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl_importLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1407, Short.MAX_VALUE))
        );
        pnl_importLayout.setVerticalGroup(
            pnl_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_importLayout.createSequentialGroup()
                .addComponent(lbl_browseFileConvert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(lbl_browseFileConvert1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(579, Short.MAX_VALUE))
        );

        tpnl_manageDCode.addTab("Import", pnl_import);

        tbl_supplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_supplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_supplierMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbl_supplier);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_userInfoUpdateStock2.setBackground(new java.awt.Color(51, 51, 255));
        lbl_userInfoUpdateStock2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbl_userInfoUpdateStock2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_userInfoUpdateStock2.setText("Supplier Detail");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Add, Edit Supplier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel19.setText("Supplier Id :");

        txt_supid.setEditable(false);

        jLabel6.setText("Supplier Name :");

        txt_supname.setColumns(20);
        txt_supname.setLineWrap(true);
        txt_supname.setRows(5);
        txt_supname.setWrapStyleWord(true);
        jScrollPane9.setViewportView(txt_supname);

        jLabel7.setText("Contact No :");

        btn_supdel.setText("Delete");
        btn_supdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supdelActionPerformed(evt);
            }
        });

        btn_supCancel.setText("Cancel");
        btn_supCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supCancelActionPerformed(evt);
            }
        });

        btn_edit.setText("Update");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_supSave.setText("Save");
        btn_supSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel19))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9)
                    .addComponent(txt_supcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_supid, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_supCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_supdel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_supSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_supid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_supcontact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_supSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_supCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_supdel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_userInfoUpdateStock2, javax.swing.GroupLayout.DEFAULT_SIZE, 1998, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(968, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(lbl_userInfoUpdateStock2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(617, Short.MAX_VALUE))
        );

        tpnl_manageDCode.addTab("Add Supplier", jPanel13);

        btnDaily.setText("Daily");
        btnDaily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDailyActionPerformed(evt);
            }
        });

        btnMonthly.setText("Monthly");
        btnMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonthlyActionPerformed(evt);
            }
        });

        btnYearly.setText("Yearly");
        btnYearly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYearlyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnDaily, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(318, 318, 318)
                .addComponent(btnMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(330, 330, 330)
                .addComponent(btnYearly, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1141, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaily, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnYearly, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(921, Short.MAX_VALUE))
        );

        tpnl_manageDCode.addTab("Dispensed Report", jPanel2);

        javax.swing.GroupLayout pnl_convertAndManageLayout = new javax.swing.GroupLayout(pnl_convertAndManage);
        pnl_convertAndManage.setLayout(pnl_convertAndManageLayout);
        pnl_convertAndManageLayout.setHorizontalGroup(
            pnl_convertAndManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnl_manageDCode)
        );
        pnl_convertAndManageLayout.setVerticalGroup(
            pnl_convertAndManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnl_manageDCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );

        tpnl_pharmacy.addTab("INVENTORY", pnl_convertAndManage);

        jScrollPane12.setViewportView(tpnl_pharmacy);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "User Info"));
        jPanel19.setPreferredSize(new java.awt.Dimension(953, 53));

        jToolBar1.setBackground(new java.awt.Color(153, 255, 153));
        jToolBar1.setRollover(true);

        lbl_userNameOList.setText("User Name :");
        jToolBar1.add(lbl_userNameOList);

        txt_userNameOList.setEditable(false);
        jToolBar1.add(txt_userNameOList);

        jToolBar3.setRollover(true);

        lbl_userIDOList.setText("User ID :");
        jToolBar3.add(lbl_userIDOList);

        txt_userIDOList.setEditable(false);
        jToolBar3.add(txt_userIDOList);

        jToolBar2.setRollover(true);

        btn_mainPageOList.setText("Main Page");
        btn_mainPageOList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mainPageOListActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_mainPageOList);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 119, Short.MAX_VALUE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    private void btn_mainPageOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mainPageOListActionPerformed
        // TODO add your handling code here:
        //back to main page and close this form
        MainPage mainPage = new MainPage(id);
        mainPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_mainPageOListActionPerformed

    private void btn_cancelMDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelMDCActionPerformed
        // TODO add your handling code here:
        //clear textfield
        txt_mdcCode.setText("");
        //catc.setSelectedItem("-");
        txt_drugNameMDC.setText("");
        txt_ingredientCode.setText("");
        txt_drugRoute.setText("");
        cdosage_form.setSelectedItem("-");
        txt_drugStrength.setText("");
        txt_stockQty.setText("");
        txt_locCode.setText("");
        rbt_inactiveMDC.setSelected(false);
        rbt_activeMDC.setSelected(false);
        dpack1.setText("");
        cdpack2.setSelectedItem("-");
        d_priceppack.setText("");
        txt_costPrice.setText("");
        txt_sellprice.setText("");
        txt_Lqty.setText("");
        cLqtyT.setSelectedItem("-");
        cLfrequency.setSelectedItem("-");
        cLduration.setSelectedItem("-");
        cLdurationType.setSelectedItem("-");
        cInstruction.setSelectedItem("-");
        txt_cautionary.setText("");
        txt_expdate.setDate(null);
        cClassification.setSelectedItem("");
}//GEN-LAST:event_btn_cancelMDCActionPerformed

    private void btn_updateMDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateMDCActionPerformed
        // TODO add your handling code here:
        if(txt_mdcCode.getText().equals("")) {
            //popup windows search drug first
            JOptionPane.showMessageDialog(btn_updateMDC, "Please search for a drug MDC information to update!");
        } else {
            //get input from textfield
            dmdc = txt_mdcCode.getText();
            datc = "UTeM";
            dtraden = txt_drugNameMDC.getText();
            dgnrn = txt_ingredientCode.getText();
            droute = txt_drugRoute.getText();
            ddosage = (String) cdosage_form.getSelectedItem();
            dstrength = txt_drugStrength.getText();
            dstockqty = txt_stockQty.getText();//double
            dloccode = txt_locCode.getText();

            if (rbt_activeMDC.getSelectedObjects() != null) {
                dstatus = "TRUE";
            } 
            else {
                dstatus = "FALSE";
            }
            dpackaging = dpack1.getText();
            dpackagingType = (String) cdpack2.getSelectedItem();
            dpriceppack = d_priceppack.getText();//double
            dcostp = txt_costPrice.getText();//double
            dsellp = txt_sellprice.getText();//double
            dLqty = txt_Lqty.getText();//double
            dLqtyt = (String)cLqtyT.getSelectedItem();
            dLfreq = (String) cLfrequency.getSelectedItem();
            dLduration = (String) cLduration.getSelectedItem();//numeric
            dLdurationType = (String) cLdurationType.getSelectedItem();
            dLadvisory = (String) cInstruction.getSelectedItem();
            dLcaution = txt_cautionary.getText();
            if(txt_expdate.getDate() != null && !txt_expdate.getDate().equals(""))
            {
                Format formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                dLexpdate = formatter2.format(txt_expdate.getDate());//date
            }
            else
            {
                dLexpdate = "";
            }
            dLclassification = (String) cClassification.getSelectedItem();
            supname = (String) cb_supplierUStock.getSelectedItem();

            //call data from PIS_MDC
            try {
                String sql="UPDATE PIS_MDC2 SET "
                        + "UD_MDC_CODE = ?,UD_ATC_CODE = ?, D_TRADE_NAME = ?,D_GNR_NAME = ?,"
                        + "D_ROUTE_CODE = ?,D_FORM_CODE = ?,D_STRENGTH = ?,D_ADVISORY_CODE = ?,D_STOCK_QTY = ?," 
                        + "D_QTY = ?,D_QTYT = ?, D_DURATION = ?,D_DURATIONT = ?,D_FREQUENCY = ?,D_CAUTION_CODE = ?,"
                        + "D_EXP_DATE = ?, D_CLASSIFICATION = ?,STATUS= ?, D_LOCATION_CODE = ?," 
                        + "D_SELL_PRICE = ?, D_COST_PRICE = ?,D_PACKAGING = ?,D_PACKAGINGT = ?,D_PRICE_PPACK = ? "
                        + "WHERE UD_MDC_CODE = ?";

                //prepare sql query and execute it
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, dmdc);
                ps.setString(2, datc);
                ps.setString(3, dtraden);
                ps.setString(4, dgnrn);
                ps.setString(5, droute);
                ps.setString(6, ddosage);
                ps.setString(7, dstrength);
                ps.setString(8, dLadvisory);
                ps.setDouble(9, Double.parseDouble(dstockqty));
                ps.setDouble(10, Double.parseDouble(dLqty));//d
                ps.setString(11, dLqtyt);
                ps.setInt(12, Integer.parseInt(dLduration));//n
                ps.setString(13, dLdurationType );
                ps.setString(14, dLfreq );
                ps.setString(15, dLcaution );
                ps.setString(16, dLexpdate );//date
                ps.setString(17, dLclassification );
                ps.setString(18, dstatus);
                ps.setString(19, dloccode);
                ps.setDouble(20, Double.parseDouble(dsellp));//d
                ps.setDouble(21, Double.parseDouble(dcostp));//d
                ps.setInt(22, Integer.parseInt(dpackaging));
                ps.setString(23, dpackagingType);
                ps.setDouble(24, Double.parseDouble(dpriceppack));
                ps.setString(25, dmdc);
                //update data
                ps.executeUpdate();
                
                try {
                    String [] arrPS = new String[25];
                    arrPS[0]=(dmdc);
                    arrPS[1]=("Utem");
                    arrPS[2]=(dtraden);
                    arrPS[3]=(dgnrn);
                    arrPS[4]=(droute);
                    arrPS[5]=(ddosage);
                    arrPS[6]=(dstrength);
                    arrPS[7]=(dLadvisory);
                    arrPS[8]=(dstockqty);
                    arrPS[9]=(dLqty);
                    arrPS[10]=(dLqtyt);
                    arrPS[11]=(dLduration);
                    arrPS[12]=(dLdurationType);
                    arrPS[13]=(dLfreq);
                    arrPS[14]=(dLcaution);
                    arrPS[15]=(dLexpdate);
                    arrPS[16]=(dLclassification);
                    arrPS[17]=(dstatus);
                    arrPS[18]=(dloccode);
                    arrPS[19]=(dsellp);
                    arrPS[20]=(dcostp);
                    arrPS[21]=(dpackaging);
                    arrPS[22]=(dpackagingType);
                    arrPS[23]=(dpriceppack);
                    arrPS[24]=(dmdc);
                    
                    Boolean bool = DBConnection.getImpl().setQuery(sql, arrPS);
                    String ggr = "";
                } catch (Exception e) {
                    System.out.println("got error.."+e.getMessage());
                    JOptionPane.showMessageDialog(null, "Unable to save at central Server. Please try again soon");
                }
                
                //popup windows update success
                JOptionPane.showMessageDialog(btn_updateMDC, "Update success!");
                
                String sql1 = "INSERT INTO PIS_PRODUCT_SUPPLIER (Update_Stock_Date, Staff_ID, Supplier_ID, UD_MDC_Code) VALUES (?,?,?,?)";

                //prepare sql query and execute it
                PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
                ps1.setTimestamp(1, sqlDate);
                ps1.setString(2, id);
                ps1.setString(3, supid);
                ps1.setString(4, dmdc);

                
                //update data
                ps1.execute();

                //clear textfield
                txt_mdcCode.setText("");
                //catc.setSelectedItem("-");
                txt_drugNameMDC.setText("");
                txt_ingredientCode.setText("");
                txt_drugRoute.setText("");
                cdosage_form.setSelectedItem("-");
                txt_drugStrength.setText("");
                txt_stockQty.setText("");
                txt_locCode.setText("");
                rbt_inactiveMDC.setSelected(false);
                rbt_activeMDC.setSelected(false);
                dpack1.setText("");
                cdpack2.setSelectedItem("-");
                d_priceppack.setText("");
                txt_costPrice.setText("");
                txt_sellprice.setText("");
                txt_Lqty.setText("");
                cLqtyT.setSelectedItem("-");
                cLfrequency.setSelectedItem("-");
                cLduration.setSelectedItem("-");
                cLdurationType.setSelectedItem("-");
                cInstruction.setSelectedItem("-");
                txt_cautionary.setText("");
                txt_expdate.setDate(null);
                cClassification.setSelectedItem("");
            } catch(Exception e) {
                System.out.println("update pis mdc"+e);
            }
        }
        UpdateTbl();
}//GEN-LAST:event_btn_updateMDCActionPerformed

    private void btn_cancelATCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelATCActionPerformed
        // TODO add your handling code here:
        //clear textfield
        txt_atcCode.setText("");
        txa_atcDescription.setText("");
        txt_categoryCode.setText("");
        rbt_activeATC.setSelected(false);
        rbt_inactiveATC.setSelected(false);
}//GEN-LAST:event_btn_cancelATCActionPerformed

    private void btn_updateATCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateATCActionPerformed
        // TODO add your handling code here:
        if(txt_atcCode.getText().equals("")) {
            //popup windows search drug first
            JOptionPane.showMessageDialog(btn_updateATC, "Please search for a drug ATC information to update!");
        } else {
            
            //get input from textfield
            atcCode = txt_atcCode.getText();
            atcDesc = txa_atcDescription.getText();
            categoryCode = txt_categoryCode.getText();
            if(rbt_activeATC.getSelectedObjects() != null)
            {
                status = "TRUE";
            }
            else
            {
                status = "FALSE";
            }

            //call data from PIS_ATC
            try {
                

                String sql="UPDATE PIS_ATC SET UD_ATC_Desc = ?, Category_Code = ?, Status = ? where UD_ATC_Code = ?";

                //prepare sql query and execute it
                //PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1,atcDesc);
                ps.setString(2,categoryCode);
                ps.setString(3,status);
                ps.setString(4,atcCode);
                //update data
                ps.executeUpdate();

                //popup windows update success
                JOptionPane.showMessageDialog(btn_updateATC, "Update success!");

                //clear textfield
                txt_atcCode.setText("");
                txa_atcDescription.setText("");
                txt_categoryCode.setText("");
                rbt_activeATC.setSelected(false);
                rbt_inactiveATC.setSelected(false);
            } catch(Exception e) {
                System.out.println("update atc E "+e);
            }
        }
        UpdateTbl();
}//GEN-LAST:event_btn_updateATCActionPerformed

    private void btn_dispenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dispenseActionPerformed
        // TODO add your handling code here:
        
        if (txt_pmiNo.getText().equals("")) {
            //popup windows search drug first
            JOptionPane.showMessageDialog(pnl_patientDrugOrder, "Please select a patient to dispense!"); //set pnl_patientDrugOrder to display dialog box in the center of pnl_patientDrugOrder
        } else {
            
//            LongRunProcess.check_network2();
//            if (Session.getPrev_stat()) {
                //online
                try {
                    // fire to server port 1099
//                    ArrayList<String> listOnline = Func.readXML("online");
//                    Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                    // search for myMessage service
//                    Message impl = (Message) myRegistry.lookup("myMessage");

                    //select dispense master based on order no
                    oNo = order_no2.getText();
                    oD = order_date2.getText();
                    lc = loc_code.getText();
                    ad = arrival_date.getText();
                    dB = txt_userIDOList.getText();//dispensed by
                    id = txt_doctor.getText();
                    ArrayList<String> dm1 = DBConnection.getImpl().getDispenseMaster(oNo);
                    //if not exist
                    if (dm1.size() <= 0) {
                        //insert dispense master
                        java.sql.Timestamp dispenseDate = new java.sql.Timestamp(new java.util.Date().getTime());
                        String dmData1[] = {oNo, oD, lc, ad, id, id, id, id};
                        DBConnection.getImpl().insertDispenseMaster(dmData1, dispenseDate, false);
                    }
                    //insert dispense detail AND update order detail
                    for (int i = 0; i < row2; i++) {
                        String drugCode = tbl_drugList.getValueAt(i, 0).toString();
                        String inst = tbl_drugList.getValueAt(i, 2).toString();
                        
                        ArrayList<String> pod = DBConnection.getImpl().getOrderDetail(oNo, drugCode);
                        String ddData1[] = {oNo, drugCode, inst,pod.get(4)};
                        
                        int d_qty = 0;
                        
                        try {
                            d_qty = Integer.parseInt(tbl_drugList.getValueAt(i, 5).toString());
                            instruction = String.valueOf(tbl_drugList.getValueAt(i, 2).toString());
                        } catch (Exception eex) {
                            d_qty = 0;
                        }
                        DBConnection.getImpl().insertDispenseDetail(ddData1, d_qty, true);
                        DBConnection.getImpl().updateOrderDetail(d_qty, oNo, drugCode);
                    }
                    //check status all order detail
                    boolean odStatus = DBConnection.getImpl().isOrderDetail(oNo);
                    //if all status true
                    if (odStatus) {
                        //update order master status AND dispense master status
                        DBConnection.getImpl().updateOrderMaster(oNo, 1);
                        DBConnection.getImpl().updateDispensedMaster(oNo, 1);
                    }

                    System.out.println(".....Dispense Sent....");
                } catch (Exception e) {
//                    e.printStackTrace();
                    //offline
                    //select dispense master based on order no
                    oNo = order_no2.getText();
                    oD = order_date2.getText();
                    lc = loc_code.getText();
                    ad = arrival_date.getText();
                    dB = txt_userIDOList.getText();//dispensed by
                    id = txt_doctor.getText();

                    //check in tbl Dispense Master 1
                    ArrayList<String> dm1 = DBConnection.getDispenseMaster(oNo);
                    //if not exist then insert
                    if (dm1.size() <= 0) {
                        //insert dispense master
                        java.sql.Timestamp dispenseDate = new java.sql.Timestamp(new java.util.Date().getTime());
                        String dmData1[] = {oNo, oD, lc, ad, dB, id, id, id};
                        DBConnection.insertDispenseMaster(dmData1, dispenseDate, false);

                        S.oln(oNo + oD + lc + ad + dB + id + id + id);

                    }
                    //insert dispense detail AND update order detail
                    for (int i = 0; i < row2; i++) {
                        String drugCode = tbl_drugList.getValueAt(i, 0).toString();
                        String inst = tbl_drugList.getValueAt(i, 2).toString();

                        ArrayList<String> pod = DBConnection.getOrderDetail(oNo, drugCode);
                        String ddData1[] = {oNo, drugCode, inst, pod.get(4)};

                        int d_qty = 0;

                        try {

                            d_qty = Integer.parseInt(tbl_drugList.getValueAt(i, 5).toString());
                            instruction = String.valueOf(tbl_drugList.getValueAt(i, 2).toString());
                        } catch (Exception eex) {
                            d_qty = 0;
                        }
                        DBConnection.insertDispenseDetail(ddData1, d_qty, true);
                        DBConnection.updateOrderDetail(d_qty, oNo, drugCode);

                    }
                    //check status all order detail
                    boolean odStatus = DBConnection.isOrderDetail(oNo);
                    //if all status true
                    if (odStatus) {
                        //update order master status AND dispense master status
                        DBConnection.updateOrderMaster(oNo, true);
                        DBConnection.updateDispensedMaster(oNo, true);
                    }
                }
//            } //end of online
//            else { 

//            } //end of offline
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);

            JOptionPane.showMessageDialog(null, "Drugs have been dispensed!");
        }//else

        resetTable();
        clearQueue();
        tab_drugOrder.setSelectedIndex(0);
        getQueue();
    }//GEN-LAST:event_btn_dispenseActionPerformed

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
        // TODO add your handling code here:
        //open file chooser
        javax.swing.JFileChooser FC=new JFileChooser(".");
        FC.showOpenDialog(this);
        FC.setCurrentDirectory(new File("."));
        

        //get selected file address
        File url=FC.getSelectedFile();
        System.out.println (url);
        urlAdd=String.valueOf(url);
        txt_importFile.setText(urlAdd);
    }//GEN-LAST:event_btn_browseActionPerformed

    private void btn_importATCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_importATCActionPerformed
        // TODO add your handling code here:
        //get address
        String address = txt_importFile.getText();
        int rowNo1 = 0;

        try
        {
            Workbook book = Workbook.getWorkbook(new File(address)); //"D:\\ATC.xls"
            Sheet sheet = book.getSheet(0);
            //set waiting cursor
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //get number of row in excel
            rowNo1 = sheet.getRows();
            //delete old table
            try {
                String sql="DROP TABLE PIS_ATC";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                //delete table
                ps.execute();

            } catch(Exception e) {
                System.out.println("drop tbl atc"+e);
            }

            //create new table
            try {
                  
                String sql="CREATE TABLE PIS_ATC (UD_ATC_Code varchar(15) PRIMARY KEY, UD_ATC_Desc varchar(200), Category_Code varchar(100), Status Boolean)";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                //create table
                ps.execute();

            } catch(Exception e) {
                System.out.println("create tbl atc "+e);
            }

            //get data from excel
            for(int j=1; j<rowNo1; j++)
            {
                Cell cell1 = sheet.getCell(0, j);
                Cell cell2 = sheet.getCell(1, j);
                Cell cell3 = sheet.getCell(2, j);
                Cell cell4 = sheet.getCell(3, j);
                String result1 = cell1.getContents();
                String result2 = cell2.getContents();
                String result3 = cell3.getContents();
                String result4 = cell4.getContents();

                if(result4.equals("0"))
                {
                    result4 = "False";
                }
                else
                {
                    result4 = "True";
                }

                String atcCode2 = result1;
                String atcDesc1 = result2;
                String categoryCode1 = result3;
                String status1 = result4;

                //insert new data
                try {
                      

                    String sql="INSERT INTO PIS_ATC (UD_ATC_Code, UD_ATC_Desc, Category_Code, Status) VALUES (?, ?, ?, ?)";

                    //prepare sql query and execute it
                    //PreparedStatement ps = conn.prepareStatement(sql);
                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                    ps.setString(1,atcCode2);
                    ps.setString(2,atcDesc1);
                    ps.setString(3,categoryCode1);
                    ps.setString(4,status1);

                    //update data
                    ps.executeUpdate();

                } catch(Exception e) {
                    System.out.println("I atc"+e);
                }
            }
            //reset cursor
            setCursor(Cursor.getDefaultCursor());

            //popup windows import success
            JOptionPane.showMessageDialog(pnl_import, "File import successfully!");
            book.close();
        }
        catch(Exception e)
        {
            System.out.println("I atc 2"+e);
        }
    }//GEN-LAST:event_btn_importATCActionPerformed

    private void btn_clearOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearOListActionPerformed
        // TODO add your handling code here:
        //clear text field search
        txt_drugNameOListSearch.setText("");
        for (int i = 0; i < 50; i++) {
            tbl_productname.getModel().setValueAt("", i, 0);
        }
        
        //reset text field
        txt_productNameOList.setText("");
        txt_drugstrength.setText("");
        cb_frequencyOList.setSelectedItem("-");
        cb_durationOList.setSelectedItem("-");
        cb_durationTypeOList.setSelectedItem("-");
        txt_quantityOList.setText("");
        cb_instructionOList.setSelectedItem("-");
        stock_qty.setText("");

    }//GEN-LAST:event_btn_clearOListActionPerformed

    private void btn_cancelOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelOListActionPerformed
        txt_drugNameOListSearch.setText("");
        txt_drugstrength.setText("");
        txt_quantityOList.setText("");
        cb_durationOList.setSelectedIndex(0);
        cb_durationTypeOList.setSelectedIndex(0);
        cb_frequencyOList.setSelectedIndex(0);
        cb_instructionOList.setSelectedIndex(0);
        txt_caution.setText("");
        txt_dosageFormOList.setText("");


        
        //lst_drugNameOListSearch.setModel(listModel);
        for(int i = 0; i < 50; i++) {
            tbl_productname.getModel().setValueAt("", i, 0);
        }
        
        
    }//GEN-LAST:event_btn_cancelOListActionPerformed
// to display data at table for drug history
//    private void DrugHist(){
//        
//        //get data from text field
//        pmiNo = txt_pmiNoOList1.getText();//txt_pmiNoOList2
//        
//       try{
//            //pls fixed how to get based on pmi_no
//        String sql ="SELECT a.dispensed_date,b.drug_item_code,b.drug_item_desc,b.qty_ordered,b.qty_dispensed "
//                + "FROM PIS_DISPENSE_MASTER a,PIS_ORDER_DETAIL b,PIS_ORDER_MASTER c"
//                + "WHERE a.order_no = b.order_no AND b.order_no = c.order_no,c.PMI_NO = ?";
//        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//        ps.setString(1,pmiNo);
//        ResultSet results = ps.executeQuery();
//        
//        while(results.next())
//        {
//            //read data get from database to all fields
//            ddate = results.getString("dispensed_date");
//            productCode = results.getString("drug_item_code");
//            productName = results.getString("drug_item_desc");
//            qtyPerTime = results.getString("qty_ordered");
//            qtydispensed = results.getString("QTY_DISPENSED");
//        }
//        
//        tbl_DrugHist.setModel(DbUtils.resultSetToTableModel(results));
//        
//        }catch (Exception e){
//             JOptionPane.showMessageDialog(null, "line 5638 Ex: DrugHist() "+e);       
//        }
//   } 
    public void generateNo(){
        getAutogeneratePIS();
    }
    public void getAutogeneratePIS(){
        String[] AutogeneratePIS = {};
        pis autog = new pis();
        System.out.println("Hello autogen oNo");
        
        try {
            AutogeneratePIS = autog.getAutogeneratePIS();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        }
        order_no.setText(AutogeneratePIS[0]);
    }
        
    
    private void btn_submitOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitOListActionPerformed
        // TODO add your handling code here:
        
        //offline   
        if (tbl_drugOList.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Please complete the prescription!");
        } else {
            //reset all value to zero
            dmdc = null;
            dtraden = null;
            dLfreq = null;
            droute = null;
            ddosage = null;
            dstrength = null;
            dosage = null;
            dLadvisory = null;
            dLduration = null;
            dLqty = null;
            totalQty1 = null;
            qtydispensed1 = 0;
            orderStatus = "NEW";

            //get data from text field
            pmiNo = txt_pmiNoOList1.getText();
            oNo = order_no.getText();//get orderno
            id = txt_userIDOList.getText();
            staffName = txt_userNameOList.getText();
            qtysup = null;

            //            dispenseStatus = "False";
            oSD = false;
            oSM = false;
            oTo = "Pharmacy";
            spubNo = 0;
            kiBy = null;
            toi = 0;
            sOUM = null;
            dOUM = null;
            hfc = Session.getHfc_code();
            oF = Session.getDiscipline();
            
//            LongRunProcess.check_network2();
//            if (Session.getPrev_stat()) {
//        //online
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
                    
                    /* start rmi */
                    
                    DBConnection.getImpl().addAUTOGENERATE_ONO(oNo);
                    
                    DBConnection.getImpl().addPIS_ORDER_MASTER(oNo, pmiNo, hfc, ec, ed, oDate, id, oF, oTo, spubNo, toi, oSM);
                    
                    //clear fill
                    txt_productNameOList.setText("");
                    txt_drugstrength.setText("");
                    cb_frequencyOList.setSelectedItem("-");
                    cb_durationOList.setSelectedItem("-");
                    cb_durationTypeOList.setSelectedItem("-");
                    txt_quantityOList.setText("");
                    cb_instructionOList.setSelectedItem("-");
                    stock_qty.setText("");
                    
                    //pop up message box
                    JOptionPane.showMessageDialog(null, "Prescription Done!");

                    //get total drug(row) in drug order list
                    int rowCount = tbl_drugOList.getRowCount();

                    //get data from each row of table
                    for (int i = 0; i < rowCount; i++) {
                        dmdc = (String) tbl_drugOList.getValueAt(i, 0);
                        dtraden = (String) tbl_drugOList.getValueAt(i, 1);
                        dLfreq = (String) tbl_drugOList.getValueAt(i, 2);
                        droute = (String) tbl_drugOList.getValueAt(i, 3);
                        ddosage = (String) tbl_drugOList.getValueAt(i, 4);
                        dstrength = (String) tbl_drugOList.getValueAt(i, 5);
                        dLqty = (String) tbl_drugOList.getValueAt(i, 6);
                        qtyPerTime1 = Double.parseDouble(dLqty);
                        dLadvisory = (String) tbl_drugOList.getValueAt(i, 7);

                        dLduration = (String) tbl_drugOList.getValueAt(i, 8);
                        duration1 = Integer.parseInt(dLduration);

                        totalQty1 = (String) tbl_drugOList.getValueAt(i, 9);
                        qtysup = (String) tbl_drugOList.getValueAt(i, 10);
                        qtydispensed = (String) tbl_drugOList.getValueAt(i, 11);
                        qtydispensed1 = Integer.parseInt(qtydispensed);
                        orderStatus = (String) tbl_drugOList.getValueAt(i, 12);

                        DBConnection.getImpl().addPIS_ORDER_DETAIL(oNo, dmdc, dtraden, dLfreq, 
                                droute, ddosage, dstrength, dLqty, dLadvisory, 
                                duration1, orderStatus, qtyPerTime1, totalQty, 
                                sOUM, qtydispensed1, dOUM, oSD);
                    }
                    //call print
                    PrescriptionNote prescriptionNote = new PrescriptionNote();
                    prescriptionNote.setVisible(true);
                    prescriptionNote.setPrescription(oNo, pmiNo, staffName);

                    //clear text field after prescript
                    //txt_pmiNoSearch1OList.setText("");
                    txt_pmiNoOList1.setText("");
                    txt_patientNameOList.setText("");
                    txt_idNoOList.setText("");
                    txt_raceOList.setText("");
                    txt_bloodOList.setText("");
                    txt_bodOList.setText("");
                    txt_genderOList.setText("");

                    resetTable();


                    //reset text field
                    txt_pmiNoOList1.setText("");
                    txt_patientNameOList.setText("");
                    txt_idNoOList.setText("");
                    txt_productNameOList.setText("");
                    txt_drugstrength.setText("");
                    cb_frequencyOList.setSelectedItem("-");
                    cb_durationOList.setSelectedItem("-");
                    cb_durationTypeOList.setSelectedItem("-");
                    txt_quantityOList.setText("");
                    txt_drugNameOListSearch.setText("");
                    cb_instructionOList.setSelectedItem("-");
                    stock_qty.setText("");

                    //pharmacy ID GENERATE
                    String[] AutogeneratePIS = {};
                    pis autog = new pis();
                    System.out.println("Hello autogen oNo");

                    try {
                        AutogeneratePIS = autog.getAutogeneratePIS();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    order_no.setText(AutogeneratePIS[0]);


                    //lst_drugNameOListSearch.setModel(listModel);
                    for (int i = 0; i < 50; i++) {
                        tbl_productname.getModel().setValueAt("", i, 0);
                    }
                    
                    /* end rmi */

                    System.out.println(".....Message Sent....");
                } catch (Exception e) {
//                    e.printStackTrace();
                    //comment 17052013 1040PM

                    //insert into tbl autogenerate ono
                    try {
                        String sql = "INSERT INTO AUTOGENERATE_ONO"
                                + "(ORDER_NO)"
                                + "VALUES (?)";
                        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                        ps.setString(1, oNo);

                        //close & update
                        ps.executeUpdate();
                        ps.close();

                    } catch (Exception ee) {
                        System.out.println("insert generateono" + ee);

                    }
                    //insert value into table PMS_Patient_Medication
                    try {
                    //order status for boolean
                        //EDIT SQL 13 MAY 2013
                        String sql = "INSERT INTO PIS_ORDER_MASTER "
                                + "(order_no, pmi_no,health_facility_code,episode_code,encounter_date,"
                                + "order_date, order_by,order_from, order_to, "
                                + "hfc_from, hfc_to, spub_no, keyin_by, "
                                + "total_order, status) "
                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    //String sql="Insert into PMS_Patient_Medication (Medication_Form_Code,PMI_No,Dispense_Status,Staff_ID,Dispense_Date) Values (?,?,?,?,?)";

                        //prepare sql query and execute it
                        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                        //pls enable var after decl

                        ps.setString(1, oNo);
                        S.oln(oNo);
                        ps.setString(2, pmiNo);
                        S.oln(pmiNo);
                        ps.setString(3, hfc);
                        S.oln(hfc);
                        // java.sql.Timestamp ec;
                        ec = new java.sql.Timestamp(new java.util.Date().getTime());
                        ps.setTimestamp(4, ec);
                        S.oln("" + ec);
                        //java.sql.Timestamp ed;
                        ed = new java.sql.Timestamp(new java.util.Date().getTime());
                        ps.setTimestamp(5, ed);
                        S.oln("" + ed);

                        //get time now
                        oDate = new java.sql.Timestamp(new java.util.Date().getTime());
                        ps.setTimestamp(6, oDate);
                        S.oln("" + oDate);
                        ps.setString(7, id);//Session.getUser_id()
                        S.oln(id);
                        //ps.setString(7,oBy);//id staff
                        ps.setString(8, oF);
                        S.oln(oF);
                        ps.setString(9, oTo);
                        S.oln(oTo);
                        ps.setString(10, hfc);//hfcF
                        S.oln(hfc);
                        ps.setString(11, oF + oTo);
                        S.oln(oF + oTo);
                        //ps.setString(12,oNo);//D : spubNo
                        ps.setInt(12, spubNo);//D : spubNo
                        S.oln(spubNo);
                        ps.setString(13, id);//D: kiBy
                        S.oln(id);
                        //ps.setString(14,toi);
                        ps.setInt(14, toi);
                        S.oln(toi);
                        ps.setBoolean(15, oSM);//true false
                        S.oln("" + oSM);

                        ps.executeUpdate();
                        ps.close();

                        System.out.println("sucess insert PIS_OM ");
                        //clear fill
                        txt_productNameOList.setText("");
                        txt_drugstrength.setText("");
                        cb_frequencyOList.setSelectedItem("-");
                        cb_durationOList.setSelectedItem("-");
                        cb_durationTypeOList.setSelectedItem("-");
                        txt_quantityOList.setText("");
                        cb_instructionOList.setSelectedItem("-");
                        stock_qty.setText("");

                        //pop up message box
                        JOptionPane.showMessageDialog(null, "Prescription Done!");
                    } catch (Exception ee) {
                        System.out.println("Error insert PIS_OM " + ee);
                    }

                    //get total drug(row) in drug order list
                    int rowCount = tbl_drugOList.getRowCount();

                    //get data from each row of table
                    for (int i = 0; i < rowCount; i++) {
                        dmdc = (String) tbl_drugOList.getValueAt(i, 0);
                        dtraden = (String) tbl_drugOList.getValueAt(i, 1);
                        dLfreq = (String) tbl_drugOList.getValueAt(i, 2);
                        droute = (String) tbl_drugOList.getValueAt(i, 3);
                        ddosage = (String) tbl_drugOList.getValueAt(i, 4);
                        dstrength = (String) tbl_drugOList.getValueAt(i, 5);
                        dLqty = (String) tbl_drugOList.getValueAt(i, 6);
                        qtyPerTime1 = Double.parseDouble(dLqty);
                        dLadvisory = (String) tbl_drugOList.getValueAt(i, 7);

                        dLduration = (String) tbl_drugOList.getValueAt(i, 8);
                        duration1 = Integer.parseInt(dLduration);

                        totalQty1 = (String) tbl_drugOList.getValueAt(i, 9);
                        qtysup = (String) tbl_drugOList.getValueAt(i, 10);
                        qtydispensed = (String) tbl_drugOList.getValueAt(i, 11);
                        qtydispensed1 = Integer.parseInt(qtydispensed);
                        orderStatus = (String) tbl_drugOList.getValueAt(i, 12);

                        //insert value into table PMS_Drug_Dispense
                        try {

                            String sql = "INSERT INTO PIS_ORDER_DETAIL "
                                    + "(order_no,drug_item_code,drug_item_desc,"
                                    + "drug_frequency,drug_route,drug_form,drug_strength,"
                                    + "drug_dosage,order_oum,duration,order_status ,qty_ordered,"
                                    + "qty_supplied,supplied_oum,qty_dispensed,dispense_oum,status) "
                                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                            //prepare sql query and execute it txt_dosageOList
                            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                            ps.setString(1, oNo);
                            ps.setString(2, dmdc);
                            ps.setString(3, dtraden);
                            ps.setString(4, dLfreq);
                            ps.setString(5, droute);
                            ps.setString(6, ddosage);
                            ps.setString(7, dstrength);
                            ps.setString(8, dLqty);
                            ps.setString(9, dLadvisory);
                            ps.setInt(10, duration1);
                            ps.setString(11, orderStatus);//default; new : full partial outstanding
                            ps.setDouble(12, qtyPerTime1);
                            ps.setInt(13, totalQty);
                            ps.setString(14, sOUM);
                            ps.setInt(15, qtydispensed1);
                            ps.setString(16, dOUM);
                            ps.setBoolean(17, oSD);//default false

                            System.out.println("sucess insert PIS_OD");

                            ps.executeUpdate();
                            ps.close();
                        } catch (Exception ex) {
                            System.out.println("Error insert PIS_OD 2" + ex);
                        }

                    }
                    //call print
                    PrescriptionNote prescriptionNote = new PrescriptionNote();
                    prescriptionNote.setVisible(true);
                    prescriptionNote.setPrescription(oNo, pmiNo, staffName);

                //clear text field after prescript
                    //txt_pmiNoSearch1OList.setText("");
                    txt_pmiNoOList1.setText("");
                    txt_patientNameOList.setText("");
                    txt_idNoOList.setText("");
                    txt_raceOList.setText("");
                    txt_bloodOList.setText("");
                    txt_bodOList.setText("");
                    txt_genderOList.setText("");

                    resetTable();

                    //reset text field
                    txt_pmiNoOList1.setText("");
                    txt_patientNameOList.setText("");
                    txt_idNoOList.setText("");
                    txt_productNameOList.setText("");
                    txt_drugstrength.setText("");
                    cb_frequencyOList.setSelectedItem("-");
                    cb_durationOList.setSelectedItem("-");
                    cb_durationTypeOList.setSelectedItem("-");
                    txt_quantityOList.setText("");
                    txt_drugNameOListSearch.setText("");
                    cb_instructionOList.setSelectedItem("-");
                    stock_qty.setText("");

                    //pharmacy ID GENERATE
                    String[] AutogeneratePIS = {};
                    pis autog = new pis();
                    System.out.println("Hello autogen oNo");

                    try {
                        AutogeneratePIS = autog.getAutogeneratePIS();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    order_no.setText(AutogeneratePIS[0]);

                    //lst_drugNameOListSearch.setModel(listModel);
                    for (int i = 0; i < 50; i++) {
                        tbl_productname.getModel().setValueAt("", i, 0);
                    }

                    //end of offline
                }
                
//    //end of online
//            } else {
//// offline
//                
//            }
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
                
//    //end of online
//            } else {
//// offline
//                
//            }
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
        }
    }//GEN-LAST:event_btn_submitOListActionPerformed

    private void btn_saveOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveOListActionPerformed
        // TODO add your handling code here:
        //create a vector to store data
        Vector<String> data = new Vector<String>();

        //get data from text field
        dtraden = txt_productNameOList.getText();//product name
        ddosage = txt_dosageFormOList.getText();//dosage form : CAPSULE
        dLqty = txt_quantityOList.getText();
        dLfreq = (String) cb_frequencyOList.getSelectedItem();//frequency
        strength = txt_drugstrength.getText();//250mg - strength
        dLduration = (String) cb_durationOList.getSelectedItem();//1 week = 7 days
        dLdurationType = (String) cb_durationTypeOList.getSelectedItem();//week
        dLadvisory = (String) cb_instructionOList.getSelectedItem();
        dLcaution = txt_caution.getText();
        
        qtydispensed = "0";
        qtysup = "0";
//        qtysup = stock_qty.getText();
        orderStatus = "New";
        
        
        //identify frequency
        if ((dLfreq.equals("In the morning")) || (dLfreq.equals("At night")) || (dLfreq.equals("Daily"))) {
            frequency1 = 1;
        } else if (dLfreq.equals("Twice a day")) {
            frequency1 = 2;
        } else if ((dLfreq.equals("3 times a day")) || (dLfreq.equals("8 hourly"))) {
            frequency1 = 3;
        } else if ((dLfreq.equals("4 times a day")) || (dLfreq.equals("6 hourly"))) {
            frequency1 = 4;
        } else if ((dLfreq.equals("12 times a day")) || (dLfreq.equals("2 hourly"))) {
            frequency1 = 12;
        } else {
            frequency1 = 6;
        }

        //change string to double and integer
        try {
            qtyPerTime1 = Double.parseDouble(dLqty);
        } catch (Exception e) {
            qtyPerTime1 = 0.0;
        }
        try {
            duration1 = Integer.parseInt(dLduration);
        } catch (Exception e) {
            duration1 = 0;
        }

        //calculate duration in day
        if (dLdurationType.equals("Week")) {
            duration1 = duration1 * 7;
            dLduration = Integer.toString(duration1);
        } else if (dLdurationType.equals("Month")) {
            duration1 = duration1 * 30;
            dLduration = Integer.toString(duration1);
        } else {
            dLduration = Integer.toString(duration1);
        }

        //calculate total dosage
        totalQty = (int) (frequency1 * qtyPerTime1 * duration1);
        //change to string
        totalQty1 = Double.toString(totalQty);
        //add data into vector
        data.addElement(dmdc);
        data.addElement(dtraden);
        data.addElement(dLfreq);
        data.addElement(droute);//xde dlm tbl
        data.addElement(ddosage);//capsule etc
        data.addElement(dstrength);//yg ni dh sama cm dosage
        data.addElement(dLqty);//qty_order - dosage
        data.addElement(dLadvisory);//adakah ini order OUM??
        data.addElement(dLduration);
        data.addElement(totalQty1);//qty_supply
        data.addElement(qtysup);
        data.addElement(qtydispensed);
        data.addElement(orderStatus);//order_status

        //write data into table
        ((DefaultTableModel) tbl_drugOList.getModel()).addRow(data);

        //clear text field search
        txt_drugNameOListSearch.setText("");

        //reset drug name list box
        for (int i = 0; i < 50; i++) {
            tbl_productname.getModel().setValueAt("", i, 0);
        }
        //reset text field
        txt_productNameOList.setText("");
        txt_drugstrength.setText("");
        cb_frequencyOList.setSelectedItem("-");
        cb_durationOList.setSelectedItem("-");
        cb_durationTypeOList.setSelectedItem("-");
        txt_quantityOList.setText("");
        cb_instructionOList.setSelectedItem("-");
        stock_qty.setText("");
        txt_caution.setText("");
        txt_dosageFormOList.setText("");


//        if((dLduration.equals("-"))||(dLdurationType.equals("-")))
//        {
//            //show message box
//            JOptionPane.showMessageDialog(null, "Please select duration number and type!");
//        }
//        else
//        {
//            if(dLfreq.equals("-"))
//            {
//                //show message box
//                JOptionPane.showMessageDialog(null, "Please select frequency!");
//            }
//            else
//            {
//                if(dLadvisory.equals("-"))
//                {
//                    //show message box
//                    JOptionPane.showMessageDialog(null, "Please select instruction!");
//                }
//                else
//                {
//                    //cut from here to above
//                }
//            }
//        }       
    }//GEN-LAST:event_btn_saveOListActionPerformed
//ini line ats ni punya original
    //get detail data from table PIS_MDC
//                    try
//                    {
//                       String sql="SELECT * FROM PIS_MDC2 where D_TRADE_NAME = ?";
//
//                        //prepare sql query and execute it
//                        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                        ps.setString(1,dtraden);
//
//                        ResultSet results = ps.executeQuery();
//
//                        while(results.next())
//                        {
//                            //create String objects to store data of results
//                            dmdc = results.getString("UD_MDC_CODE");//may 10, 2013
//                            dtraden = results.getString("D_TRADE_NAME");
//                            droute = results.getString("D_ROUTE_CODE");
//                            ddosage = results.getString("D_FORM_CODE");
//                            //calculate total dosage
//                            totalQty = (int) (frequency1*qtyPerTime1*duration1);
//                            //change to string
//                            totalQty1 = Double.toString(totalQty);
//
//                            //add data into vector
//                            data.addElement(dmdc);
//                            data.addElement(dtraden);
//                            data.addElement(dLfreq);
//                            data.addElement(droute);//xde dlm tbl
//                            data.addElement(ddosage);//capsule etc
//                            data.addElement(dstrength);//yg ni dh sama cm dosage
//                            data.addElement(dLqty);//qty_order - dosage
//                            data.addElement(dLadvisory);//adakah ini order OUM??
//                            data.addElement(dLduration);
//                            data.addElement(totalQty1);//qty_supply
//                            data.addElement(qtysup);
//                            data.addElement(qtydispensed);
//                            data.addElement(orderStatus);//order_status
//
//                            //write data into table
//                            ((DefaultTableModel)tbl_drugOList.getModel()).addRow(data);
//                        }
//                        //clear text field search
//                        txt_drugNameOListSearch.setText("");
//
//                        //reset drug name list box
//                        for (int i = 0; i < 50; i++) {
//                            tbl_productname.getModel().setValueAt("", i, 0);
//                        }
//                        //reset text field
//                        txt_productNameOList.setText("");
//                        txt_drugstrength.setText("");
//                        cb_frequencyOList.setSelectedItem("-");
//                        cb_durationOList.setSelectedItem("-");
//                        cb_durationTypeOList.setSelectedItem("-");
//                        txt_quantityOList.setText("");
//                        cb_instructionOList.setSelectedItem("-");
//                        stock_qty.setText("");
//                        txt_caution.setText("");
//                        txt_dosageFormOList.setText("");                       
//                        
//                        //clean the results and data
//                        results.close();
//                        ps.close();
//                    }
//                    catch(Exception e)
//                    {
//                        System.out.println("pis mdc 4"+e);
//                    }
    private void btn_editOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editOListActionPerformed
        // TODO add your handling code here:
        //get selected row number
        int rowNo = tbl_drugOList.getSelectedRow();

        productName = (String) tbl_drugOList.getValueAt(rowNo, 1);
        frequency = (String) tbl_drugOList.getValueAt(rowNo, 2);
        route = (String) tbl_drugOList.getValueAt(rowNo, 3);
        dosageForm = (String) tbl_drugOList.getValueAt(rowNo, 4);
        strength = (String) tbl_drugOList.getValueAt(rowNo, 5);
        dosage = (String) tbl_drugOList.getValueAt(rowNo, 6);
        instruction = (String) tbl_drugOList.getValueAt(rowNo, 7);
        duration = (String) tbl_drugOList.getValueAt(rowNo, 8);
        qtyPerTime = (String) tbl_drugOList.getValueAt(rowNo, 9);
        orderStatus = (String) tbl_drugOList.getValueAt(rowNo, 12);

        //identify the type
        duration1 = Integer.parseInt(duration);
        if(duration1<7)
        {
            durationType = "Day";
        }
        else if((duration1>6)&&(duration1<30))
        {
            duration1 = duration1/7;
            duration = Integer.toString(duration1);
            durationType = "Week";
        }
        else
        {
            duration1 = duration1/30;
            duration = Integer.toString(duration1);
            durationType = "Month";
        }

        //insert value into text field
        txt_productNameOList.setText(productName);//txt_productNameOList
        cb_frequencyOList.setSelectedItem(frequency);
        txt_dosageFormOList.setText(dosageForm);
        txt_drugstrength.setText(dosage);
        cb_instructionOList.setSelectedItem(instruction);
        cb_durationOList.setSelectedItem(duration);
        txt_quantityOList.setText(qtyPerTime);
        cb_durationTypeOList.setSelectedItem(durationType);
        

        //delete selected row in JTable
        ((DefaultTableModel)tbl_drugOList.getModel()).removeRow(rowNo);
    }//GEN-LAST:event_btn_editOListActionPerformed

    private void txt_drugNameOListSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_drugNameOListSearchKeyReleased
        //if(checkPatient()) return;
        //get pmiNo selected
       String pmiNo = txt_pmiNoOList1.getText();

        if (pmiNo.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a patient!");
        } else {
            
            dtraden = txt_drugNameOListSearch.getText();
            if (dtraden.equals("")) {
                for(int i = 0; i < 50; i++) {
                    tbl_productname.getModel().setValueAt("", i, 0);
                }
            } else {
                //tbl_productname
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
                    ps.setString(1, dtraden+ "%");
//                    ps.setString(2, dgnrn.toUpperCase() + "%");
                    ps.setString(2, dtraden + "%");
                    ResultSet results = ps.executeQuery();
                    for (int i = 0; results.next() && i < 50; i++) {
                        tbl_productname.getModel().setValueAt(results.getString("D_TRADE_NAME"), i, 0);
                    }

                } catch (Exception ex) {
                    S.oln("search mdc patient" + ex.getMessage());
                }
            }   
        }
    }//GEN-LAST:event_txt_drugNameOListSearchKeyReleased
   private void UpdateTbl(){
       try{
            
        String sql ="SELECT * FROM PIS_SUPPLIER ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ResultSet results = ps.executeQuery();
        
        tbl_supplier.setModel(DbUtils.resultSetToTableModel(results));
        
        }catch (Exception e){
             JOptionPane.showMessageDialog(null, "UpdateTbl() "+e);       
        }
      // tbl_atc
       try{
            
        String sql ="SELECT * FROM PIS_ATC ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ResultSet results = ps.executeQuery();
        
        tbl_atc.setModel(DbUtils.resultSetToTableModel(results));
        
        }catch (Exception e){
             JOptionPane.showMessageDialog(null, "UpdateTbl() "+e);       
        }
//       //tbl_mdc
//       try{
//            
//        String sql ="SELECT * FROM PIS_MDC ";
//        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//        ResultSet results = ps.executeQuery();
//        
//        tbl_mdc.setModel(DbUtils.resultSetToTableModel(results));
//        
//        }catch (Exception e){
//             JOptionPane.showMessageDialog(null, "UpdateTbl() "+e);       
//        }
       //tbl_mdc2
       try{
            
        String sql ="SELECT * FROM PIS_MDC2 ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ResultSet results = ps.executeQuery();
        
        tbl_mdc.setModel(DbUtils.resultSetToTableModel(results));
        
        }catch (Exception e){
             JOptionPane.showMessageDialog(null, "UpdateTbl() "+e);       
        }
       
   } 
    private void btn_supSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supSaveActionPerformed
        // TODO add your handling code here:
     
    if(txt_supname.getText() == null) {
        JOptionPane.showMessageDialog(btn_supSave, "Please insert supplier name!");
    } else {
        
        supid = txt_supid.getText();
        supname = txt_supname.getText();
        supcontact = txt_supcontact.getText();
        
        try{
         String sql = "INSERT INTO PIS_SUPPLIER (SUPPLIER_ID, SUPPLIER_NAME, CONTACT_NO)VALUES(?,?,?)";
         
         PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
         ps.setString(1,supid);
         ps.setString(2,supname);
         ps.setString(3,supcontact);
         
         ps.execute();
         
         //clear supplier data
        //txt_supid.setText("");
        txt_supname.setText("");
        txt_supcontact.setText("");
        
        System.out.println("ADD NEW SUPPLIER " +sql);
         JOptionPane.showMessageDialog(null, "Supplier Information Saved!"
                 + "Supplier Id :" + supid);
     }catch (Exception e){
         JOptionPane.showMessageDialog(null, e);
     }
        
}  
    
    String[] AutogenerateSPL = {};
    pis autogenerate = new pis();

    try {
        AutogenerateSPL = autogenerate.getAutogenerateSPL();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
    }
    txt_supid.setText("SPL" + AutogenerateSPL[0]);
    
    UpdateTbl();
    }//GEN-LAST:event_btn_supSaveActionPerformed

    private void btn_supCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supCancelActionPerformed
        // TODO add your handling code here:
        
        //clear supplier data
        txt_supid.setText("");
        txt_supname.setText("");
        txt_supcontact.setText("");
        
        
    String[] AutogenerateSPL = {};
    pis autogenerate = new pis();

    try {
        AutogenerateSPL = autogenerate.getAutogenerateSPL();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
    }
         txt_supid.setText("SPL" + AutogenerateSPL[0]);
           
    }//GEN-LAST:event_btn_supCancelActionPerformed

    private void btn_dcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dcancelActionPerformed
        // TODO add your handling code here:
        /*
         * variable
    private String mdcCode;
    private String drugName;//not inuse
    private String productName;
    private String mdcDesc;
    private String ingredientCode;
    private String cautionaryCode;
    private String dfc;//dosage form code
    private String dosageForm;//def dosage yg nilainya sama dgn drug strength
    private String routeCode;
    private String advisoryCode;
    private String atcCode;
    private String mdcStatus;//option 1
    private String status;//option 2
    private String drug_strength;
    private String stockQty;
    private String locationCode;
    
         */
    txt_dmdc_code.setText("");
    txt_dproduct_name.setText("");
    txt_dmdc_desc.setText("null");
    txt_dingredient_code.setText("");
    txt_dcaution_code.setText("null");
    txt_dform_code.setText("");
    txt_ddef_dosage.setText("null");
    txt_droute_code.setText("null");
    txt_dadvisory_code.setText("null");
    txt_datc_code.setText("");
    //rbt_active.setSelected(false);;//.setSelected(false);
    //rbt_inactive.setSelected(false);
    txt_ddrug_strength.setText("");
    txt_dstock_qty.setText("");
    txt_dloc_code.setText("");
    combox_supname.setSelectedItem("-");
    
    
    //fillcombo();
        
    }//GEN-LAST:event_btn_dcancelActionPerformed
    private void fillcombo(){
    
            
        try{
            String sql = "SELECT * FROM PIS_SUPPLIER";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            
            //ps.setString(1,supname);
            System.out.println("bigbang filecombo () 1.0####### SQL "+sql);
            
            ResultSet results = ps.executeQuery();
            
            while(results.next()){
                supname = results.getString("SUPPLIER_NAME");
                combox_supname.addItem(supname);
                cb_supplierUStock.addItem(supname);
                supid = results.getString("SUPPLIER_ID");
                supcontact = results.getString("CONTACT_NO");
               
            }
            //results.close();
            //ps.close();
                     
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"file combo "+ e);
        }
        
        try{
            String sql2 = "SELECT UD_ATC_CODE FROM PIS_ATC ";
//            String sql2 = "SELECT UD_ATC_CODE FROM PIS_ATC a, PIS_MDC b "
//                    + "WHERE UD_ATC_CODE.a = UD_ATC_CODE.b ";
            PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                atcCode = rs.getString("UD_ATC_CODE");
                //catc.addItem(atcCode);
            }
            
        }catch(Exception d){
            d.printStackTrace();
        }
}
    private void btn_dsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dsaveActionPerformed
        // TODO add your handling code here:
  if(txt_dmdc_code.getText() == null) {
        JOptionPane.showMessageDialog(btn_dsave, "Please insert ud mdc code!");
    } else {
          
            //set get to fields
            mdcCode =txt_dmdc_code.getText();
            productName =txt_dproduct_name.getText();
            mdcDesc= txt_dmdc_desc.getText();
            ingredientCode= txt_dingredient_code.getText();
            cautionaryCode =txt_dcaution_code.getText();
            dfc= txt_dform_code.getText();
            dosageForm =txt_ddef_dosage.getText();
            routeCode =txt_droute_code.getText();
            advisoryCode= txt_dadvisory_code.getText();
            atcCode =txt_datc_code.getText();
            /*
             if(rbt_active.getSelectedObjects() != null)
            {
                status = "TRUE";
            }
            else
            {
                status = "FALSE";
            }*/
            //rbt_active.setText("");//mdcStatus
            //rbt_inactive.setText("");
            drug_strength =txt_ddrug_strength.getText();
            stockQty =txt_dstock_qty.getText();
            locationCode =txt_dloc_code.getText();
            supname = (String)combox_supname.getSelectedItem();
           
         //fillcombo();
        /*
            //get data from PIS_SUPPLIER
        try
        {
            String sql1="SELECT Supplier_ID FROM PIS_SUPPLIER where Supplier_Name = ?";

            //prepare sql query and execute it
            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
            ps1.setString(1,supname);

            ResultSet results1 = ps1.executeQuery();

            while(results1.next())
            {
                //create String objects to store data of results
                supid = results1.getString("Supplier_ID");
                
            }
            //clean the results and data
            results1.close();
            ps1.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
     */
        try{
            /*
         * 
         *String sql = "INSERT INTO PMS_EMPLOYMENT (PMI_NO,EMPLOYMENT_SEQ_NO,EMPLOYER_CODE,EMPLOYER_NAME,OCCUPATION_CODE,JOINED_DATE,INCOME_RANGE_CODE,HEALTH_FACILITY,CREATE_DATE,EMPLOYMENT_STATUS) VALUES ('" + employmentinfo[0] + "','" + employmentinfo[1] + "','" + employmentinfo[2] + "','" + employmentinfo[3] + "','" + employmentinfo[4] + "','" + employmentinfo[5] + "','" + employmentinfo[6] + "','" + employmentinfo[7] + "','" + employmentinfo[8] + "','" + employmentinfo[9] + "')";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
       
       * String sql2 = "INSERT INTO AUTOGENERATE_ESNO (EMPLOYMENT_SEQ_NO) VALUES ('" + employmentinfo[1] + "')";
        PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
        
        * ps.executeUpdate();
        ps2.executeUpdate();
        *
         */
            status = "REGISTERED";
            
            
            String sql= "INSERT INTO PIS_MDC (UD_MDC_CODE, DRUG_PRODUCT_NAME, UD_MDC_DESC, ACTIVE_INGREDIENT_CODE, DEF_CAUTIONARY_CODE, DOSAGE_FORM_CODE,DEF_DOSAGE, DEF_ROUTE_CODE, DEF_ADVISORY_CODE, UD_ATC_CODE, STATUS, DRUG_STRENGTH, STOCK_QTY, LOCATION_CODE)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            
            String sql1 = "INSERT INTO PIS_PRODUCT_SUPPLIER(SUPPLIER_ID, UD_MDC_CODE, UPDATE_STOCK_DATE, STAFF_ID) VALUES (?,?,?,?)";
            PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
          
            //insert into pis_mdc
            ps.setString(1,mdcCode);
            ps.setString(2,productName);
            ps.setString(3,mdcDesc);
            ps.setString(4,ingredientCode);
            ps.setString(5,cautionaryCode);
            ps.setString(6,dfc);
            ps.setString(7,dosageForm);
            ps.setString(8,routeCode);
            ps.setString(9,advisoryCode);
            ps.setString(10,atcCode);
            ps.setString(11,status);
            ps.setString(12,drug_strength);
            ps.setString(13,stockQty);
            ps.setString(14,locationCode);
            
            //insert into pis_product_supplier
            ps1.setString(1,supid);
            ps1.setString(2,mdcCode);
            ps1.setTimestamp(3,sqlDate);
            ps1.setString(4,id);
          

         System.out.println("ADD NEW DRUG :"+sql);
         System.out.println("ADD NEW DRUG :"+sql1);
            //execute sql
            ps.executeUpdate();
            ps1.executeUpdate();
            
            
            //clear field
            txt_dmdc_code.setText("");
            txt_dproduct_name.setText("");
            txt_dmdc_desc.setText("");
            txt_dingredient_code.setText("");
            txt_dcaution_code.setText("");
            txt_dform_code.setText("");
            txt_ddef_dosage.setText("");
            txt_droute_code.setText("");
            txt_dadvisory_code.setText("");
            txt_datc_code.setText("");
            //rbt_active.setSelected(false);;//.setSelected(false);
           // rbt_inactive.setSelected(false);
            txt_ddrug_strength.setText("");
            txt_dstock_qty.setText("");
            txt_dloc_code.setText("");
            combox_supname.setSelectedItem("-");
            
            JOptionPane.showMessageDialog(null, "New drug insert! "+ mdcCode);
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null, "btn save "+e1);
        }
      }
       fillcombo(); 
       UpdateTbl();
       
    }//GEN-LAST:event_btn_dsaveActionPerformed

    private void tbl_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supplierMouseClicked
        // TODO add your handling code here:
        int row = tbl_supplier.getSelectedRow();
        String selectedSup = (String) tbl_supplier.getValueAt(row, 0);
        
        try{
            String sql="SELECT * FROM PIS_SUPPLIER WHERE SUPPLIER_ID = ?";

            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1,selectedSup);
            ResultSet result = ps.executeQuery();

            while(result.next())
            {
               //read data get from database to all fields
               txt_supid.setText(result.getString("SUPPLIER_ID"));
               txt_supname.setText(result.getString("SUPPLIER_NAME"));
               txt_supcontact.setText(result.getString("CONTACT_NO"));
              
            }
            //clean the results and data
            ps.close();
            result.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"click from tbl supplier "+ e); 
        }
    }//GEN-LAST:event_tbl_supplierMouseClicked

    private void btn_supdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supdelActionPerformed
        // TODO add your handling code here:
        
        String sql ="DELETE FROM PIS_SUPPLIER WHERE SUPPLIER_ID = ?";
        try{
            
            int row = tbl_supplier.getSelectedRow(); 
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, txt_supid.getText());
            
            //((DefaultTableModel)tbl_supplier.getModel()).removeRow(row);
            ps.execute();
            ps.close();
            
             //clear supplier data
            txt_supid.setText("");
            txt_supname.setText("");
            txt_supcontact.setText("");
            
            
            JOptionPane.showMessageDialog(null,"Deleted "+ supid); 
        }catch(Exception e){
            
           JOptionPane.showMessageDialog(null,"del from Supplier "+ e); 
        }
        
        
        UpdateTbl();
    }//GEN-LAST:event_btn_supdelActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        
         //get selected row number
       // int row = tbl_supplier.getSelectedRow();
        
        //supid = (String) tbl_supplier.getValueAt(row, 0);
        //supname = (String) tbl_supplier.getValueAt(row, 1);
        //supcontact = (String) tbl_supplier.getValueAt(row, 2);
        
        //insert value into text field
        supid = txt_supid.getText();
        supname = txt_supname.getText();
        supcontact = txt_supcontact.getText();
         try {
                //String sql="UPDATE PIS_MDC_PHARMACY SET MDC_Stock_Qty = ?, MDC_Stock_Uom = ?, Std_Packing_Size = ?, Std_Packing_Uom = ?, DRUG_STRENGTH = ? where UD_MDC_Code = ?";
                String sql="UPDATE PIS_SUPPLIER SET SUPPLIER_NAME = ? , CONTACT_NO = ? where SUPPLIER_ID = ?";

                //prepare sql query and execute it
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1,supname);
                ps.setString(2,supcontact);
                ps.setString(3,supid);
               
                //update data
                ps.executeUpdate();
                
                //clear textfield and combo box
                txt_supid.setText("");
                txt_supname.setText("");
                txt_supcontact.setText("");
                
                //popup windows update success
                JOptionPane.showMessageDialog(btn_edit, "Supplier info update success!");

            } catch(Exception e) {
                System.out.println("p sup"+e);
            }
        

        //delete selected row in JTable
        //((DefaultTableModel)tbl_supplier.getModel()).removeRow(row);
        
        UpdateTbl();
    }//GEN-LAST:event_btn_editActionPerformed

    private void tbl_drugOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_drugOrderMouseClicked
        //get the clicked row
        int r = tbl_drugOrder.getSelectedRow();
        int c = tbl_drugOrder.getSelectedColumn();
        
        try {
            if (!tbl_drugOrder.getValueAt(r, c).equals("")) {
                //transfer
                for (int i = 0; i < max_col_drug; i++) {
                    tbl_drugList.setValueAt(tbl_drugOrder.getValueAt(r, i), row2, i);
                }
                row2++;

                //sorting
                for (int i = r; i < max_row_drug - 1; i++) {
                    for (int j = 0; j < max_col_drug; j++) {
                        tbl_drugOrder.setValueAt(tbl_drugOrder.getValueAt(i + 1, j), i, j);
                    }
                }
                for (int j = 0; j < max_col_drug; j++) {
                    tbl_drugOrder.setValueAt("", row1, j);
                }
                row1--;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbl_drugOrderMouseClicked

    private void tbl_drugListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_drugListMouseClicked
        //get the clicked row
        int r = tbl_drugList.getSelectedRow();
        int c = tbl_drugList.getSelectedColumn();

        try {
            if (!tbl_drugList.getValueAt(r, c).equals("") && c != 5) {
                //transfer
                for (int i = 0; i < max_col_drug; i++) {
                    tbl_drugOrder.setValueAt(tbl_drugList.getValueAt(r, i), row1, i);
                }
                row1++;

                //sorting
                for (int i = r; i < max_row_drug - 1; i++) {
                    for (int j = 0; j < max_col_drug; j++) {
                        tbl_drugList.setValueAt(tbl_drugList.getValueAt(i + 1, j), i, j);
                    }
                }
                for (int j = 0; j < max_col_drug; j++) {
                    tbl_drugList.setValueAt("", row2, j);
                }
                row2--;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbl_drugListMouseClicked
public void setSelectedAppointment(String selectedAppointment, String selectedTime) {
        
        //jTextArea7.setText("");
        
        String[] AppointmentInfo = {};
        this.EpisodeTime = selectedTime;
        this.IDPMS = selectedAppointment;
        Patient appointment = new Patient(null);

        try {
            //
            AppointmentInfo = appointment.getAppointmentBiodata(selectedAppointment, selectedTime);
            //Friza getEHR
            
        } catch (Exception ex) {
            System.out.println("appointmnt umar "+ex);
        }

        txt_pmiNoOList1.setText(AppointmentInfo[0]);
        txt_patientNameOList.setText(AppointmentInfo[2]);
        txt_idNoOList.setText(AppointmentInfo[4]);
        txt_raceOList.setText(AppointmentInfo[13]);
        txt_genderOList.setText(AppointmentInfo[11]);
        txt_bodOList.setText(AppointmentInfo[10]);
        txt_bloodOList.setText(AppointmentInfo[16]);
//        txtBloodType.setText(txt_pBloodSex.getText());
//        txt_pStatus.setText(AppointmentInfo[12]);

        System.out.println("....frizaaa.... Extract EHR... ");
        
        String sql_new = "UPDATE PMS_EPISODE SET STATUS = 'Consult' "
                + "WHERE PMI_NO = ? AND EPISODE_TIME = ?";
        Q.sPs(sql_new);
        Q.s(1, selectedAppointment);
        Q.s(2, selectedTime);
        try {
            Q.gPs().execute();
        } catch (SQLException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        }


        //Select 7 latest episode to display - Friza
        //online:
        //1. Get clob from ehr_central
        ExtractEHR extractEHR = new ExtractEHR();
        List list = null;
        try {
            list = extractEHR.getEHRLatest7(txt_pmiNoOList1.getText());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        }
         /**
         * umar - 04012013 - start new code *
         */
        pmiNo = selectedAppointment;
        String pid = null;
        try {
            
//            LongRunProcess.check_network2();
            
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
                    
                    DBConnection.getImpl().sayHello("UMAR");

                    ArrayList<String> arData = DBConnection.getImpl().getEHRRecords(pmiNo, 1); // get PMS by IC
                    cpyFile = arData.get(0);
                    status = arData.get(1);
                    cpyFile_history = arData.get(2);

                    System.out.println(".....Message Sent....");
                } catch (Exception e) {
                    S.oln("local reg"+e.getMessage());
                    //If RMI Error, get data from Local Database.
                    //Read BLOB from Journal_File
                    sql = "SELECT TXNDATA, STATUS2 "
                            + "FROM PUBLIC.JOURNAL_FILE "
                            + "WHERE PMI_NO LIKE ? "
                            + "ORDER BY TXNDATE DESC "
                            + "LIMIT 1";
                    sql_history = "SELECT TXNDATA "
                            + "FROM PUBLIC.JOURNAL_FILE "
                            + "WHERE PMI_NO LIKE ? "
                            + "AND STATUS2 = 1 "
                            + "ORDER BY TXNDATE DESC "
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
       
            
            //S.oln("cpyFile_history: |---|*"+cpyFile_history+"*|---|");
            
            /** latest 7 history **/
            int rows_tbl = 7;
            MainRetrieval mr[] = new MainRetrieval[100];
            for(int i = 0; i < 100; i++) {
                mr[i] = new MainRetrieval();
                mr[i].startProcess(cpyFile_history);
            }
            String msg[][][] = new String[100][100][100];
            msg[5] = mr[5].getData("ALG");
            msg[10] = mr[10].getData("DTO");
           
            for(int i = 0; i < mr[5].getRowNums() && i < rows_tbl; i++) {
                tbl_aller.getModel().setValueAt(msg[5][i][2], i, 0);
                tbl_aller.getModel().setValueAt(msg[5][i][7], i, 1);
                //tbl_aller.getModel().setValueAt(msg[5][i][4], i, 2);
            }
           
            for(int i = 0; i < mr[10].getRowNums() && i < rows_tbl; i++) {
                tbl_DrugHist.getModel().setValueAt(msg[10][i][5], i, 0);
                tbl_DrugHist.getModel().setValueAt(msg[10][i][14], i, 1);
                tbl_DrugHist.getModel().setValueAt(msg[10][i][22], i, 2);
                tbl_DrugHist.getModel().setValueAt(msg[10][i][23], i, 3);
                //tbl_DrugHist.getModel().setValueAt(msg[10][i][34], i, 4);
            }
            
            
            //for (int i = tokenLine.countTokens(); i > 0; i--) {
            if(status.equals("2")) {
                
                for (int i = 0; i < tokenLine.length; i++) {

                    String line = tokenLine[i].equals("")?"-":tokenLine[i]; //insert each line into string
                    System.out.println("line: "+line);
                    //StringTokenizer column = new StringTokenizer(line, "|");// get on off status from header word
                    String column[] = line.split("\\|");
                    int k = 0;
                    String TableType = column[k++].equals("")?"-":column[k-1];//1st column is table name
                    System.out.println("TableType: ="+TableType+"=");

                    
                    if (TableType.contains("DTO")) {
                        S.oln("--> DTO <--");
                        String dataField = ""; try { dataField=column[k++].equals("")?"-":column[k-1]; } catch(Exception e) { dataField=""; S.oln("DTO umar"+e);} 

                        //StringTokenizer tokenData = new StringTokenizer(dataField, "^");
                        String tokenData[] = dataField.split("\\^");
                        int k1 = 0;

                        productCode = ""; try { productCode=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { productCode=""; S.oln("DTO umar 1"+e);} 
                        productName = ""; try { productName=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { productName=""; S.oln("DTO umar 2"+e);} 
                        dosage = ""; try { dosage=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { dosage="";S.oln("DTO umar 3"+e); } 
                        totalQty1 = ""; try { totalQty1=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { totalQty1="";S.oln("DTO umar 4"+e); } 
                        dosageForm = ""; try { dosageForm=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { dosageForm="";S.oln("DTO umar 5"+e); } 
                        duration = ""; try { duration=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { duration=""; S.oln("DTO umar 6"+e);} 
                        frequency = ""; try { frequency=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { frequency=""; S.oln("DTO umar 7"+e);} 
                        instruction = ""; try { instruction=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { instruction="";S.oln("DTO umar 7"+e); } 

                        String date = ""; try { date=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { date=""; S.oln("DTO umar 8"+e);} 
                        
                    }  else if (TableType.contains("ALG")) {
                        S.oln("--> ALG <--");
                        String dataField = ""; try { dataField=column[k++].equals("")?"-":column[k-1]; } catch(Exception e) { dataField=""; S.oln("DTO umar 9"+e);} 

                        //StringTokenizer tokenData = new StringTokenizer(dataField, "^");
                        String tokenData[] = dataField.split("\\^");
                        int k1 = 0;

                        String ALGname = ""; try { ALGname=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { ALGname="";S.oln("DTO umar 10"+e); } 
                        String ALGdate = ""; try { ALGdate=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { ALGdate=""; S.oln("DTO umar 11"+e);} 
                        
                        String ALGcom = ""; try { ALGcom=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { ALGcom=""; S.oln("DTO umar 12"+e);} 
                        String date = ""; try { date=tokenData[k1++].equals("")?"-":tokenData[k1-1]; } catch(Exception e) { date=""; S.oln("DTO umar 13"+e);} 
                        

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

    }
public void resetTable(){
    //reset tbl order
    tbl_drugOList.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {

    },
    new String [] {
        "Drug Code", "Drug Item Desc", "Frequency", "Route", "Drug Form", "Strength", "Dosage", "Order OUM", "Duration (Day)", "Qty Order", "Qty Supply", "Qty Dispense", "Status"
    }
) {
    boolean[] canEdit = new boolean [] {
        false, true, false, false, false, false, false, false, false, false, false, false, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});

tbl_drugOList.getTableHeader().setReorderingAllowed(false);

//clear form
txt_patientName.setText("");
txt_pmiNo.setText("");
txt_icNo.setText("");
txt_sex.setText("");
txt_birthDate.setText("");
txt_race.setText("");
txt_bloodType.setText("");
cbAllergy.setSelectedItem("");
order_no2.setText("");
order_date2.setText("");
loc_code.setText("");
arrival_date.setText("");
txt_doctor.setText("");

jScrollPane17.setViewportView(tbl_drugOList);
    
    for(int i = 0; i < 100; i++) {
        for(int j = 0; j < 7; j++) {
            tbl_drugOrder.getModel().setValueAt("", i, j);
            tbl_drugList.getModel().setValueAt("", i, j);
        }
    }
    
    row1 = 0;
    row2 = 0;
   
    
    
}// private void ShowAllergy(){
//       try{
//            
//        String sql ="SELECT * FROM PIS_SUPPLIER";//pls bg nama alergy history
//        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//        ResultSet results = ps.executeQuery();
//        
//        tbl_aller.setModel(DbUtils.resultSetToTableModel(results));
//        tbl_allergy2.setModel(DbUtils.resultSetToTableModel(results));
//        
//        }catch (Exception e){
//             JOptionPane.showMessageDialog(null, "ShowAllergy() "+e);       
//        }
//   }
    private void txt_pmiNoSearch1OList2EnablePMIReferral(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pmiNoSearch1OList2EnablePMIReferral
        // TODO add your handling code here:
        if(txt_pmiNoSearch1OList2.getText().length() != 0) {
            txt_icNoSearch1OList2.setEditable(false);
            txt_idNoSearch1OList2.setEditable(false);
            cb_idTypeSearch1OList2.setEnabled(false);
        }else {
            txt_icNoSearch1OList2.setEditable(true);
            txt_idNoSearch1OList2.setEditable(true);
            cb_idTypeSearch1OList2.setEnabled(true);
        }
    }//GEN-LAST:event_txt_pmiNoSearch1OList2EnablePMIReferral

    private void txt_icNoSearch1OList2EnableICReferral(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_icNoSearch1OList2EnableICReferral
        // TODO add your handling code here:
        if(txt_icNoSearch1OList2.getText().length() != 0) {
            txt_pmiNoSearch1OList2.setEditable(false);
            txt_idNoSearch1OList2.setEditable(false);
            cb_idTypeSearch1OList2.setEnabled(false);
        }else {
            txt_pmiNoSearch1OList2.setEditable(true);
            txt_idNoSearch1OList2.setEditable(true);
            cb_idTypeSearch1OList2.setEnabled(true);
        }
    }//GEN-LAST:event_txt_icNoSearch1OList2EnableICReferral

    private void txt_idNoSearch1OList2EnableIDReferral(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idNoSearch1OList2EnableIDReferral
        // TODO add your handling code here:
         if(txt_idNoSearch1OList2.getText().length() != 0) {
            txt_pmiNoSearch1OList2.setEditable(false);
            txt_icNoSearch1OList2.setEditable(false);
        }else {
            txt_pmiNoSearch1OList2.setEditable(true);
            txt_icNoSearch1OList2.setEditable(true);
        }
    }//GEN-LAST:event_txt_idNoSearch1OList2EnableIDReferral

    private String cpyFile_history = "-";
    
    private void searchPatientNetwork(String search, String idtype, int type) throws SQLException {
//        LongRunProcess.check_network2();

        boolean offline = false;
        
        String[] AppointmentInfo = {};
        Patient appointment = new Patient(null);

//        if (Session.getPrev_stat()) {
            //Read BLOB from EHR_Central
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method			
                //impl.sayHello("..Friza ");

                DBConnection.getImpl().sayHello("UMAR");

                pmiNo = DBConnection.getImpl().getPMINo(search, idtype, type);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                offline = true;
            }
//        } else {
//            offline = true;
//        }

        if (offline) {
            pmiNo = DBConnection.getPMINo(search, idtype, type);
        }
        
        try {
            AppointmentInfo = appointment.getAppointmentBiodata(pmiNo, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        /**
         * txt_pPmiNo.setText(AppointmentInfo[0]);
         * txt_pName.setText(AppointmentInfo[2]);
         * txt_pIcNo.setText(AppointmentInfo[4]);
         * txt_pRace.setText(AppointmentInfo[13]);
         * txt_pSex.setText(AppointmentInfo[11]);
         * lblDOB.setText(AppointmentInfo[10]);
         * txt_pBloodSex.setText(AppointmentInfo[16]);
         * txtBloodType.setText(txt_pBloodSex.getText());
         * txt_pStatus.setText(AppointmentInfo[12]);
         */
        
        if (AppointmentInfo[0].equals("") && AppointmentInfo[0].length() == 0) {
            J.o("Invalid Patient", "Invalid patient!\nNo patient with this ID ..", 0);
            return;
        }

        txt_pmiNoOList1.setText(AppointmentInfo[0]);
        txt_patientNameOList.setText(AppointmentInfo[2]);
        txt_idNoOList.setText(AppointmentInfo[7]);
        txt_raceOList.setText(AppointmentInfo[13]);
        txt_bloodOList.setText(AppointmentInfo[16]);
        txt_bodOList.setText(AppointmentInfo[10]);
        txt_genderOList.setText(AppointmentInfo[11]);

//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
    }
    
    private void searchPatient() {
        pmiNo = txt_pmiNoSearch1OList2.getText();
        patientIC = txt_icNoSearch1OList2.getText();
        patientId = (String) cb_idTypeSearch1OList2.getSelectedItem();
        idNo = txt_idNoSearch1OList2.getText();

        //SEARCH USING PMI NO
        if (txt_pmiNoSearch1OList2.getText() != null && (txt_pmiNoSearch1OList2.getText().length() != 0)) {
            try {
                searchPatientNetwork(txt_pmiNoSearch1OList2.getText(), "", 1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //SEARCH USING NEW IC NO
        if ((txt_icNoSearch1OList2.getText() != null) && (rb_newOList2.isSelected() == true) && (txt_icNoSearch1OList2.getText().length() != 0) && (txt_icNoSearch1OList2.getText() != "")) {
            try {
                searchPatientNetwork(txt_icNoSearch1OList2.getText(), "", 2);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //SEARCH USING OLD IC NO
        if ((txt_icNoSearch1OList2.getText() != null) && (rb_oldOList2.isSelected() == true) && (txt_icNoSearch1OList2.getText().length() != 0) && (txt_icNoSearch1OList2.getText() != "")) {
            try {
                searchPatientNetwork(txt_icNoSearch1OList2.getText(), "", 3);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //SEARCH USING ID TYPE AND ID NO
        if ((txt_idNoSearch1OList2.getText() != null) && (cb_idTypeSearch1OList2.getSelectedIndex() != 0) && (txt_idNoSearch1OList2.getText().length() != 0) && (txt_idNoSearch1OList2.getText() != "")) {
            try {
                searchPatientNetwork(txt_idNoSearch1OList2.getText(), cb_idTypeSearch1OList2.getSelectedItem().toString(), 4);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (txt_pmiNoSearch1OList2.getText().length() == 0 && (txt_icNoSearch1OList2.getText().length() == 0) && (txt_idNoSearch1OList2.getText().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Please key in PMI No or IC No or Identification No to continue searching process!!!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

        Pharmacy.data.setpmiNo(txt_pmiNoOList1.getText());
        Pharmacy.data.setpatientName(txt_patientNameOList.getText());
        Pharmacy.data.setpatientIC(txt_idNoOList.getText());
        Pharmacy.data.setrace(txt_raceOList.getText());
        Pharmacy.data.setblood(txt_bloodOList.getText());
        Pharmacy.data.setbod(txt_bodOList.getText());
        Pharmacy.data.setgender(txt_genderOList.getText());

        //reset value
        txt_pmiNoOList1.setText("");
        txt_patientNameOList.setText("");
        txt_idNoOList.setText("");
        txt_raceOList.setText("");
        txt_bloodOList.setText("");
        txt_bodOList.setText("");
        txt_genderOList.setText("");

        txt_pmiNoSearch1OList2.setText("");
        txt_icNoSearch1OList2.setText("");
        cb_idTypeSearch1OList2.setSelectedItem("");
        txt_idNoSearch1OList2.setText("");


        Spatient.dispose();
    }
    
    private void btn_searchOList2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchOList2ActionPerformed
        // TODO add your handling code here:
        
        searchPatient();
        
//         try {
//            
//            pmiNo = txt_pmiNoSearch1OList2.getText();
//            patientIC = txt_icNoSearch1OList2.getText();
//            patientId = (String) cb_idTypeSearch1OList2.getSelectedItem();
//            idNo = txt_idNoSearch1OList2.getText();
//            
//                        
//            if (txt_pmiNoSearch1OList2.getText() != null && (txt_pmiNoSearch1OList2.getText().length() != 0))
//            {
//                try
//                {
//                    String sql="SELECT * FROM PMS_PATIENT_BIODATA WHERE PMI_NO = ? LIMIT 1";
//
//                    //prepare sql query and execute it
//                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                    ps.setString(1,pmiNo);
//
//                    ResultSet results = ps.executeQuery();
//
//                    while(results.next())
//                    {
//                        //create String objects to store data of results
//                        pmiNo = results.getString("PMI_No");
//                        patientName = results.getString("Patient_Name");
//                        patientIC = results.getString("New_IC_No");
//                        
//                        //add more info 7/2/2013 String
//                        race = results.getString("Race_Code");
//                        blood = results.getString("Blood_Type");
//                        bod = results.getString("Birth_Date");
//                        gender = results.getString("SEX_CODE");
//                       
//                        //set value into text field
//                        txt_pmiNoOList1.setText(pmiNo);
//                        txt_patientNameOList.setText(patientName);
//                        txt_idNoOList.setText(patientIC);
//                        
//                        //add more info 7/2/2013 to txt field
//                        txt_raceOList.setText(race);  
//                        txt_bloodOList.setText(blood);
//                        txt_bodOList.setText(bod);
//                        txt_genderOList.setText(gender);
//                    }
//                    //clean the results and data
//                    results.close();
//                    ps.close();
//                }
//                catch(Exception e1)
//                {
//                    System.out.println("PMS P Bio"+e1);
//                }
//            }
//
//           if (txt_icNoSearch1OList2.getText() != null && rb_newOList2.isSelected() == true && (txt_icNoSearch1OList2.getText().length() != 0)) 
//           {
//               try
//                {
//                      
//
//                    String sql="SELECT * FROM PMS_PATIENT_BIODATA where NEW_IC_NO = ? LIMIT 1";
//
//                    //prepare sql query and execute it
//                    //PreparedStatement ps = conn.prepareStatement(sql);
//                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                    ps.setString(1,patientIC);
//
//                    ResultSet results = ps.executeQuery();
//
//                    while(results.next())
//                    {
//                        //create String objects to store data of results
//                        pmiNo = results.getString("PMI_No");
//                        patientName = results.getString("Patient_Name");
//                        patientIC = results.getString("New_IC_No");
//                        
//                        //add more info 7/2/2013 String
//                        race = results.getString("Race_Code");
//                        blood = results.getString("Blood_Type");
//                        bod = results.getString("Birth_Date");
//                        gender = results.getString("SEX_CODE");
//                        
////                        if(patientIC.equals("-"))
////                        {
////                            patientIC = results.getString("Old_IC_No");
////
////                            if(patientIC.equals("-"))
////                            {
////                                patientIC = results.getString("ID_NO");
////                                //select radio button
////                                rbt_foreignerOList2.setSelected(true);
////                            }
////                            else
////                            {
////                                //select radio button
////                                rbt_oldICOList2.setSelected(true);
////                            }
////                        }
////                        else
////                        {
////                            //select radio button
////                            rdb_newICOList2.setSelected(true);
////                        }
//
//                        //set value into text field
//                        txt_pmiNoOList1.setText(pmiNo);
//                        txt_patientNameOList.setText(patientName);
//                        txt_idNoOList.setText(patientIC);
//                        
//                        //add more info 7/2/2013 to txt field
//                        txt_raceOList.setText(race);  
//                        txt_bloodOList.setText(blood);
//                        txt_bodOList.setText(bod);
//                        txt_genderOList.setText(gender);
//                    }
//                    //clean the results and data
//                    results.close();
//                    ps.close();
//                }
//                catch(Exception e1)
//                {
//                    System.out.println("PMS P B 2"+e1);
//                }
//           }
//
//           if(txt_icNoSearch1OList2.getText() != null && rb_oldOList2.isSelected() == true  && (txt_icNoSearch1OList2.getText().length() != 0))
//           {
//                try
//                {
//                      
//
//                    String sql="SELECT * FROM PMS_PATIENT_BIODATA where OLD_IC_NO = ? LIMIT 1";
//
//                    //prepare sql query and execute it
//                    //PreparedStatement ps = conn.prepareStatement(sql);
//                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                    ps.setString(1,patientIC);
//
//                    ResultSet results = ps.executeQuery();
//
//                    while(results.next())
//                    {
//                        //create String objects to store data of results
//                        pmiNo = results.getString("PMI_No");
//                        patientName = results.getString("Patient_Name");
//                        patientIC = results.getString("New_IC_No");
//                        //add more info 7/2/2013 String
//                        race = results.getString("Race_Code");
//                        blood = results.getString("Blood_Type");
//                        bod = results.getString("Birth_Date");
//                        gender = results.getString("SEX_CODE");
//                        
////                        if(patientIC.equals("-"))
////                        {
////                            patientIC = results.getString("Old_IC_No");
////
////                            if(patientIC.equals("-"))
////                            {
////                                patientIC = results.getString("ID_NO");
////                                //select radio button
////                                rbt_foreignerOList2.setSelected(true);
////                            }
////                            else
////                            {
////                                //select radio button
////                                rbt_oldICOList2.setSelected(true);
////                            }
////                        }
////                        else
////                        {
////                            //select radio button
////                            rdb_newICOList2.setSelected(true);
////                        }
//
//                      //set value into text field
//                        txt_pmiNoOList1.setText(pmiNo);
//                        txt_patientNameOList.setText(patientName);
//                        txt_idNoOList.setText(patientIC);
//                        
//                        //add more info 7/2/2013 to txt field
//                        txt_raceOList.setText(race);  
//                        txt_bloodOList.setText(blood);
//                        txt_bodOList.setText(bod);
//                        txt_genderOList.setText(gender);
//                    }
//                    //clean the results and data
//                    results.close();
//                    ps.close();
//                }
//                catch(Exception e1)
//                {
//                    System.out.println("PMS P B 3"+e1);
//                }
//           }
//
//
//            if (txt_idNoSearch1OList2.getText() != null && (cb_idTypeSearch1OList2.getSelectedItem().toString() != null) && (txt_idNoSearch1OList2.getText().length() != 0))
//            {
//                try
//                {
//                      
//
//                    String sql="SELECT * FROM PMS_PATIENT_BIODATA where ID_TYPE = ? AND ID_NO = ? LIMIT 1";
//
//                    //prepare sql query and execute it
//                    //PreparedStatement ps = conn.prepareStatement(sql);
//                    PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//                    ps.setString(1,patientIC);
//                    ps.setString(2,patientIC);
//
//                    ResultSet results = ps.executeQuery();
//
//                    while(results.next())
//                    {
//                        //create String objects to store data of results
//                        pmiNo = results.getString("PMI_No");
//                        patientName = results.getString("Patient_Name");
//                        patientIC = results.getString("New_IC_No");
//                        
//                        //add more info 7/2/2013 String
//                        race = results.getString("Race_Code");
//                        blood = results.getString("Blood_Type");
//                        bod = results.getString("Birth_Date");
//                        gender = results.getString("SEX_CODE");
////                        if(patientIC.equals("-"))
////                        {
////                            patientIC = results.getString("Old_IC_No");
////
////                            if(patientIC.equals("-"))
////                            {
////                                patientIC = results.getString("ID_NO");
////                                //select radio button
////                                rbt_foreignerOList2.setSelected(true);
////                            }
////                            else
////                            {
////                                //select radio button
////                                rbt_oldICOList2.setSelected(true);
////                            }
////                        }
////                        else
////                        {
////                            //select radio button
////                            rdb_newICOList2.setSelected(true);
////                        }
//
//                        //set value into text field
//                        txt_pmiNoOList1.setText(pmiNo);
//                        txt_patientNameOList.setText(patientName);
//                        txt_idNoOList.setText(patientIC);
//                        
//                        //add more info 7/2/2013 to txt field
//                        txt_raceOList.setText(race);  
//                        txt_bloodOList.setText(blood);
//                        txt_bodOList.setText(bod);
//                        txt_genderOList.setText(gender);
//                    }
//                    //clean the results and data
//                    results.close();
//                    ps.close();
//                }
//                catch(Exception e1)
//                {
//                    System.out.println("PMS P B 4 "+e1);
//                }
//            }
//
//            if (txt_pmiNoSearch1OList2.getText().length() == 0 && (txt_icNoSearch1OList2.getText().length() == 0) && (txt_idNoSearch1OList2.getText().length() == 0)) {
//                JOptionPane.showMessageDialog(null, "Please key in PMI No or IC No or Identification No to continue searching process!!!", "Error",JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch (Exception ex) {
//            System.err.println("PMS P B 5"+ex);
//        }
    }//GEN-LAST:event_btn_searchOList2ActionPerformed

    private void btn_readMyCard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_readMyCard2ActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        //get connection
//        final Connection conn = DBConnection.getConnInstance();
        
//        MyKad mykad = new MyKad();
//        mykad.start();
//        mykad.useJPN();
//        mykad.readData();
//
//        patientIC = mykad.ic;
//        try
//        {
//              
//
//            String sql="SELECT * FROM PMS_PATIENT_BIODATA where NEW_IC_NO = ?";
//
//            //prepare sql query and execute it
//            //PreparedStatement ps = conn.prepareStatement(sql);
//            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
//            ps.setString(1,patientIC);
//
//            ResultSet results = ps.executeQuery();
//
//            while(results.next())
//            {
//                //create String objects to store data of results
//                pmiNo = results.getString("PMI_No");
//                patientName = results.getString("Patient_Name");
//                patientIC = results.getString("New_IC_No");
//                //add more info 7/2/2013 String
//                race = results.getString("Race_Code");
//                blood = results.getString("Blood_Type");
//                bod = results.getString("Birth_Date");
//                gender = results.getString("SEX_CODE");
//                
////                if(patientIC.equals("-"))
////                {
////                    patientIC = results.getString("Old_IC_No");
////
////                    if(patientIC.equals("-"))
////                    {
////                        patientIC = results.getString("ID_NO");
////                        //select radio button
////                        rbt_foreignerOList2.setSelected(true);
////                    }
////                    else
////                    {
////                        //select radio button
////                        rbt_oldICOList2.setSelected(true);
////                    }
////                }
////                else
////                {
////                    //select radio button
////                    rdb_newICOList2.setSelected(true);
////                }
//
//                //set value into text field
//                txt_pmiNoOList1.setText(pmiNo);
//                txt_patientNameOList.setText(patientName);
//                txt_idNoOList.setText(patientIC);
//
//                //add more info 7/2/2013 to txt field
//                txt_raceOList.setText(race);
//                txt_bloodOList.setText(blood);
//                txt_bodOList.setText(bod);
//                txt_genderOList.setText(gender);
//            }
//            //clean the results and data
//            results.close();
//            ps.close();
//        }
//        catch(Exception e)
//        {
//           System.err.print("PMS P B 7"+e);
//        }
//
//        mykad.stop();
        
        MyKad mykad = new MyKad();
        mykad.start();
        mykad.useJPN();
        mykad.readData();

        clearSearch();

        txt_pmiNoSearch1OList2.setText(mykad.ic);
        rb_newOList2.setSelected(true);
        rb_oldOList2.setSelected(false);

        searchPatient();

        mykad.stop();
    }//GEN-LAST:event_btn_readMyCard2ActionPerformed
//this is search patient at order drug tab (0)
    class SearchData {
        private String pmiNo;
        private String patientName;
        private String patientIC;
        private String race;
        private String blood;
        private String bod;
        private String gender;
        
        
        public void setpmiNo(String pmiNo){
            this.pmiNo = pmiNo;
        }
        public String getpmiNo(){
            return pmiNo;
        }
        public void setpatientName(String patientName){
             this.patientName = patientName;
        }
         public String getpatientName(){
            return patientName;
        }
         public void setpatientIC(String patientIC){
             this.patientIC = patientIC;
        }
         public String getpatientIC(){
            return patientIC;
        }
         public void setrace(String race){
            this.race = race;
        }
         public String getrace(){
            return race;
        }
         private void setblood(String blood){
            this.blood = blood;
        }
         private String getblood(){
            return blood;
        }
         public void setbod(String bod){
            this.bod = bod;
        }
         public String getbod(){
            return bod;
        }
         public void setgender(String gender){
            this.gender = gender;
        }
         public String getgender(){
            return gender;
        }

    }
    //this is where data will be displayed after ok & close button click
    private void btn_ok2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok2ActionPerformed
       
        Pharmacy.data.setpmiNo(txt_pmiNoOList1.getText());
        Pharmacy.data.setpatientName(txt_patientNameOList.getText());
        Pharmacy.data.setpatientIC(txt_idNoOList.getText());
        Pharmacy.data.setrace(txt_raceOList.getText());
        Pharmacy.data.setblood(txt_bloodOList.getText());
        Pharmacy.data.setbod(txt_bodOList.getText());
        Pharmacy.data.setgender(txt_genderOList.getText());
        
        //reset value
        txt_pmiNoOList1.setText("");
        txt_patientNameOList.setText("");
        txt_idNoOList.setText("");
        txt_raceOList.setText("");  
        txt_bloodOList.setText("");
        txt_bodOList.setText("");
        txt_genderOList.setText("");
        
        txt_pmiNoSearch1OList2.setText("");
        txt_icNoSearch1OList2.setText("");
        cb_idTypeSearch1OList2.setSelectedItem("");
        txt_idNoSearch1OList2.setText("");
        
        
        Spatient.dispose();
        
        
        
    }//GEN-LAST:event_btn_ok2ActionPerformed
    public void getDetailSup2(String drugCode){
        mdcCode = drugCode;
        //clear field
        cb_supplierUStock.setSelectedItem("-");
        
        try {
            String sql = "SELECT * FROM PIS_PRODUCT_SUPPLIER where UD_MDC_CODE = ?";

            // prepare sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, mdcCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //create String objects to store data of results
                supplierID = rs.getString("Supplier_ID");

                //call data from PIS_SUPPLIER
                try {
                    String sql2 = "SELECT * FROM PIS_SUPPLIER where Supplier_ID = ?";

                    //prepare sql query and execute it
                    PreparedStatement ps2 = Session.getCon_x(1000).prepareStatement(sql2);
                    ps2.setString(1, supplierID);

                    ResultSet rs2 = ps2.executeQuery();

                    while (rs2.next()) {
                        //create String objects to store data of results
                        supname = rs2.getString("Supplier_Name");
                        cb_supplierUStock.addItem(supname);
                    }
                    //clean the results and data
                    rs2.close();
                    ps2.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            rs.close();
            ps.close();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    
    public void getDetailATC(String atcC){
        atcCode = atcC;
         /*
         * CLEAR ALL FIELD
         */
        txt_atcCode.setText("");
        txa_atcDescription.setText("");
        txt_categoryCode.setText("");
        rbt_activeATC.setSelected(false);
        rbt_inactiveATC.setSelected(false);
        
        
        /*
         * search data base on the drug product choosed
         */
        //call data from PIS_ATC
        try {
            String sql = "SELECT * FROM PIS_ATC where UD_ATC_Code = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, atcCode);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                //rs 1
                atcC = results.getString("UD_ATC_Code");
                atcDesc = results.getString("UD_ATC_Desc");
                categoryCode = results.getString("Category_Code");
                atcStatus = results.getString("Status");
                if (atcStatus.equals("TRUE") || atcStatus.equals("1")) {
                    rbt_activeATC.setSelected(true);
                } else {
                    rbt_inactiveATC.setSelected(true);
                }

                //set rs 1 into field
                txt_atcCode.setText(atcC);
                txa_atcDescription.setText(atcDesc);
                txt_categoryCode.setText(categoryCode);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void getDetailProductName(String productName) {
        product = productName;
        /*
         * CLEAR ALL FIELD
         */

        //clear all text field 1 - drug order
        txt_drugstrength.setText("");
        txt_quantityOList.setText("");
        cb_frequencyOList.setSelectedItem("-");
        cb_durationOList.setSelectedItem("-");
        cb_durationTypeOList.setSelectedItem("-");
        cb_instructionOList.setSelectedItem("-");
        txt_caution.setText("");
        stock_qty.setText("");
        txt_productNameOList.setText("");

        
        //reset all text field 3
        txt_drugNameMDC.setText("");
        txt_mdcCode.setText("");
        txt_ingredientCode.setText("");
        txt_cautionary.setText("");
        cdosage_form.setSelectedItem("-");
        txt_drugRoute.setText("");
//        txt_atcCodeMDC.setText("");//
//        catc.setSelectedItem("-");
        rbt_activeMDC.setSelected(false);
        rbt_inactiveMDC.setSelected(false);
        txt_drugStrength.setText("");

        /*
         * search data base on the drug product choosed
         */
        //call data from PIS_MDC
        try {
            String sql = "SELECT * "
                    + "FROM PIS_MDC2 "
                    + "WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
//            String sql = "SELECT * FROM PIS_MDC where DRUG_PRODUCT_NAME = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, product);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                //rs 1
                dtraden = results.getString("D_TRADE_NAME");//
                Double stock_qty1 = results.getDouble("D_STOCK_QTY");//Stock_Qty
                dstockqty = Double.toString(stock_qty1);
                dstrength = results.getString("D_STRENGTH");
                ddosage = results.getString("D_FORM_CODE");
                int dLqty1 = results.getInt("D_QTY");
                dLqty = Integer.toString(dLqty1);
                dLqtyt = results.getString("D_QTYT");
                dLfreq = results.getString("D_FREQUENCY");
                dLduration = results.getString("D_DURATION");
                dLdurationType = results.getString("D_DURATIONT");
                dLadvisory = results.getString("D_ADVISORY_CODE");
                dLcaution = results.getString("D_CAUTION_CODE");
                dLexpdate = results.getString("D_EXP_DATE");
                dLclassification = results.getString("D_CLASSIFICATION");
                //rs 2
                dmdc = results.getString("UD_MDC_CODE");
                dgnrn = results.getString("D_GNR_NAME");
                dstrength = results.getString("D_STRENGTH");
                dloccode = results.getString("D_LOCATION_CODE");

                //rs 3
                //mdcDesc = results.getString("UD_MDC_Desc");
                dLcaution = results.getString("D_CAUTION_CODE");
                droute = results.getString("D_ROUTE_CODE");
                dLadvisory = results.getString("D_ADVISORY_CODE");
                datc = results.getString("UD_ATC_CODE");
                dstatus = results.getString("STATUS");
                dpackaging = results.getString("D_PACKAGING");
                dpackagingType = results.getString("D_PACKAGINGT");
                dpriceppack = String.valueOf(results.getDouble("D_PRICE_PPACK"));
                dcostp = String.valueOf(results.getDouble("D_COST_PRICE"));
                dsellp = String.valueOf(results.getDouble("D_SELL_PRICE"));
                
                //set value into rs 1
                txt_productNameOList.setText(dtraden);
                stock_qty.setText(dstockqty);
                txt_drugstrength.setText(dstrength);
                txt_dosageFormOList.setText(ddosage);
                txt_quantityOList.setText(dLqty);
                cb_frequencyOList.setSelectedItem(dLfreq);
                cb_durationOList.setSelectedItem(dLduration);
                cb_durationTypeOList.setSelectedItem(dLdurationType);
                cb_instructionOList.setSelectedItem(dLadvisory);
                
                txt_caution.setText(dLcaution);
                
                //change boolean into active and inactive for display
                if (dstatus.equals("TRUE") || dstatus.equals("REGISTERED")) {
                    rbt_activeMDC.setSelected(true);
                    //mdcStatus = "Active";
                } else {
                    rbt_inactiveMDC.setSelected(true);
                    //mdcStatus = "Inactive";
                }


                //set value rs 3
                txt_drugNameMDC.setText(productName);
                txt_mdcCode.setText(dmdc);
                txt_ingredientCode.setText(dgnrn);
                txt_cautionary.setText(dLcaution);
                cdosage_form.setSelectedItem(ddosage);
                txt_drugRoute.setText(droute);
                //txt_atcCodeMDC.setText(atcCode);
                txt_stockQty.setText(dstockqty);
//                catc.setSelectedItem(datc);
                txt_drugStrength.setText(dstrength);
                txt_Lqty.setText(dLqty);
                cLfrequency.setSelectedItem(dLfreq);
                cInstruction.setSelectedItem(dLadvisory);
                cLduration.setSelectedItem(dLduration);
                cLdurationType.setSelectedItem(dLdurationType);
                txt_expdate.setDateFormatString(dLexpdate);
                cClassification.setSelectedItem(dLclassification);
                
                dpack1.setText(dpackaging);
                cdpack2.setSelectedItem(dpackagingType);
                d_priceppack.setText(dpriceppack); 
                txt_costPrice.setText(dcostp);
                txt_sellprice.setText(dsellp);

                if (stock_qty1 <= 0) {
                    JOptionPane.showMessageDialog(null, "Drug stock quantity is low " + stock_qty1);

                    //clear all text field 1
                    txt_productNameOList.setText("");
                    txt_drugstrength.setText("");
                    cb_frequencyOList.setSelectedItem("-");
                    cb_durationOList.setSelectedItem("-");
                    cb_durationTypeOList.setSelectedItem("-");
                    txt_quantityOList.setText("");
                    cb_instructionOList.setSelectedItem("-");
                    stock_qty.setText("");
                    
                    //clear all text field 2
                    
                    cb_supplierUStock.setSelectedItem("-");
                    
                    //reset all text field 3
                    txt_drugNameMDC.setText("");
                    txt_mdcCode.setText("");
                    txt_ingredientCode.setText("");
                    txt_cautionary.setText("");
                    cdosage_form.setSelectedItem("-");
                    txt_drugRoute.setText("");
                    //txt_atcCodeMDC.setText("");
//                    catc.setSelectedItem("-");
                    rbt_activeMDC.setSelected(false);
                    rbt_inactiveMDC.setSelected(false);
                    txt_drugStrength.setText("");
                }
            }
            //clean the results and data
            results.close();
            ps.close();
        } catch (Exception e1) {
            S.oln("mdc 11"+e1.getMessage());
        }
    }

    private void tbl_productnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productnameMouseClicked
        // TODO add your handling code here:
        int index = tbl_productname.getSelectedRow();
        String st = tbl_productname.getModel().getValueAt(index, 0).toString();
        try {
            String sql = "SELECT * "
                    + "FROM PIS_MDC2 "
                    + "WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
//            String sql = "SELECT * FROM PIS_MDC WHERE DRUG_PRODUCT_NAME = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, st);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                getDetailProductName(results.getString("D_TRADE_NAME"));
            } else {
                getDetailProductName("");
            }
        } catch (Exception ex) {
            S.oln("MDC 12"+ex.getMessage());
        }
    }//GEN-LAST:event_tbl_productnameMouseClicked

    private void btn_newOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newOrderActionPerformed
        // TODO add your handling code here:
        //reset table by create a new table
        tbl_drugOList = new javax.swing.JTable();
        //set table row and column
        tbl_drugOList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Drug Code", "Trade Name", "Frequency", "Route", "Drug Form", "Strength", "Dosage", "Order OUM", "Duration (Day)", "Qty Order", "Qty Supply", "Qty Dispense", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //not allow user to reorder table
        tbl_drugOList.getTableHeader().setReorderingAllowed(false);

        jScrollPane17.setViewportView(tbl_drugOList);

        //reset text field
        txt_pmiNoOList1.setText("");
        txt_patientNameOList.setText("");
        txt_idNoOList.setText("");
        txt_productNameOList.setText("");
        txt_drugstrength.setText("");
        cb_frequencyOList.setSelectedItem("-");
        cb_durationOList.setSelectedItem("-");
        cb_durationTypeOList.setSelectedItem("-");
        txt_quantityOList.setText("");
        txt_drugNameOListSearch.setText("");
        cb_instructionOList.setSelectedItem("-");
        stock_qty.setText("");
        txt_caution.setText("");
        txt_dosageFormOList.setText("");

        
        txt_raceOList.setText("");  
        txt_bloodOList.setText("");
        txt_bodOList.setText("");
        txt_genderOList.setText("");
        
        //add new field
        


        //reset drug name list box
       for (int i = 0; i < 50; i++) {
            tbl_productname.getModel().setValueAt("", i, 0);
        }
    }//GEN-LAST:event_btn_newOrderActionPerformed

    private void btn_deleteOListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteOListActionPerformed
        // TODO add your handling code here:
        
            int rowNo = tbl_drugOList.getSelectedRow();

        //delete selected row in JTablecnblue official web site
        ((DefaultTableModel)tbl_drugOList.getModel()).removeRow(rowNo);
    }//GEN-LAST:event_btn_deleteOListActionPerformed

    private void btn_importMDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_importMDCActionPerformed
        // TODO add your handling code here:
        //get address
    String address = txt_importFile.getText();
    int rowNo1 = 0;

    try
    {
        Workbook book = Workbook.getWorkbook(new File(address)); //"D:\\MDC.xls"txt_drugNameMDCSearch
        Sheet sheet = book.getSheet(0);

        //waiting cursor
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //get number of row in excel
        rowNo1 = sheet.getRows();

////        //delete old table
////        try {
////            String sql="DROP TABLE IF EXISTS PIS_MDC2";
////
////            //prepare sql query and execute it
////            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
////            //delete table
////            ps.execute();
////
////        } catch(Exception e) {
////            System.out.println(e);
////        }
////
////        //create new table
////        try {
////
////           String sql="CREATE TABLE IF NOT EXISTS PIS_MDC2(UD_MDC_CODE VARCHAR(25) PRIMARY KEY,UD_ATC_CODE VARCHAR(15),"
////                   + "D_TRADE_NAME VARCHAR(200),D_GNR_NAME VARCHAR(600) DEFAULT 'NULL',"
////                   + "D_ROUTE_CODE VARCHAR(200) DEFAULT 'NULL',D_FORM_CODE VARCHAR(100)DEFAULT 'NULL',"
////                   + "D_STRENGTH VARCHAR(100) DEFAULT 'NULL',D_ADVISORY_CODE VARCHAR(200) DEFAULT 'NULL',"
////                   + "D_STOCK_QTY DOUBLE DEFAULT '0.00',D_QTY DOUBLE DEFAULT '0.00',D_QTYT VARCHAR(50) DEFAULT 'NULL',D_DURATION NUMERIC(100) DEFAULT 'NULL',"
////                   + "D_DURATIONT VARCHAR(100) DEFAULT 'NULL',D_FREQUENCY VARCHAR(200)DEFAULT 'NULL',"
////                   + "D_CAUTION_CODE VARCHAR(200)DEFAULT 'NULL',D_EXP_DATE VARCHAR(10) DEFAULT 'NULL',"
////                   + "D_CLASSIFICATION VARCHAR(200) DEFAULT 'NULL',STATUS VARCHAR(20),D_LOCATION_CODE VARCHAR(4) DEFAULT 'P',"
////                   + "D_SELL_PRICE DOUBLE DEFAULT '0.00',D_COST_PRICE DOUBLE DEFAULT '0.00',"
////                   + "D_PACKAGING NUMERIC(100),D_PACKAGINGT VARCHAR(100)DEFAULT 'NULL',D_PRICE_PPACK DOUBLE DEFAULT '0.00')";
////
////            //prepare sql query and execute it
////            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
////
////            //create table
////            ps.execute();
////
////        } catch(Exception e) {
////            System.out.println("create tbl PISMDC2: "+e);
////        }
        

        //get data from excel
        for(int j=1; j<rowNo1; j++)
        {
            String dLqty1 = "0.00";
                      
            Cell cell1 = sheet.getCell(0, j);
            Cell cell2 = sheet.getCell(1, j);
            Cell cell3 = sheet.getCell(2, j);
            Cell cell4 = sheet.getCell(3, j);
            Cell cell5 = sheet.getCell(4, j);
            Cell cell6 = sheet.getCell(5, j);
            Cell cell7 = sheet.getCell(6, j);
            Cell cell8 = sheet.getCell(7, j);
            Cell cell9 = sheet.getCell(8, j);
            Cell cell10 = sheet.getCell(9, j);
            Cell cell11 = sheet.getCell(10, j);
            Cell cell12 = sheet.getCell(11, j);
            Cell cell13 = sheet.getCell(12, j);
            Cell cell14 = sheet.getCell(13, j);
            Cell cell15 = sheet.getCell(14, j);
            Cell cell16 = sheet.getCell(15, j);
            Cell cell17 = sheet.getCell(16, j);
            Cell cell18 = sheet.getCell(17, j);
            Cell cell19 = sheet.getCell(18, j);
            Cell cell20 = sheet.getCell(19, j);
            Cell cell21 = sheet.getCell(20, j);
            Cell cell22 = sheet.getCell(21, j);
            Cell cell23 = sheet.getCell(22, j);
            Cell cell24 = sheet.getCell(23, j);
            
            String result1 = cell1.getContents();
            String result2 = cell2.getContents();
            String result3 = cell3.getContents();
            String result4 = cell4.getContents();
            String result5 = cell5.getContents();
            String result6 = cell6.getContents();
            String result7 = cell7.getContents();
            String result8 = cell8.getContents();
            String result9 = cell9.getContents();
            String result10 = cell10.getContents();
            String result11 = cell11.getContents();
            String result12 = cell12.getContents();
            String result13 = cell13.getContents();
            String result14 = cell14.getContents();
            String result15 = cell15.getContents();
            String result16 = cell16.getContents();
            String result17 = cell17.getContents();
            String result18 = cell18.getContents();
            String result19 = cell19.getContents();
            String result20 = cell20.getContents();
            String result21 = cell21.getContents();
            String result22 = cell22.getContents();
            String result23 = cell23.getContents();
            String result24 = cell24.getContents();

            String dmdc1 = result1;
            String datc1 = result2;
            String dtraden1 = result3;
            String dgnrn1 = result4;
            String droute1 = result5;
            String ddosage1 = result6;
            String dstrength1 = result7;
            String dLadvisory1 = result8;
            String dstockqty1 = result9;
            dLqty1 = result10;
            String dLqtyt1 = result11;
            String dLduration1 = result12;
            String dLdurationType1 = result13;
            String dLfreq1 = result14;
            String dLcaution1 = result15;
            String dLexpdate1 = result16;
            String dLclassification1 = result17;
            String dstatus1 = result18;
            String dloccode1 = result19;
            String dsellp1 = result20;
            String dcostp1 = result21;
            String dpackaging1 = result22;
            String dpackagingType1 = result23;
            String dpriceppack1 = result24;
            
            if(dLqty1.equals("")){
                dLqty1 = "0.00";
            }
            if(dstockqty1.equals("")){
                dstockqty1 = "0.00";
            }
            if(dLcaution1.equals("")){
                dLcaution1 = "NULL";
            }
            if((dLduration1.equals("-"))||(dLduration1.equals(" "))){
                dLduration1 = "0";
            }
            if (dpackaging1.equals("")){
                dpackaging1 = "0";
            }
            if (dsellp1.equals("")){
                dsellp1 = "0.00";
            }
            if (dcostp1.equals("")){
                dcostp1 = "0.00";
            }
            if (dpriceppack1.equals("")){
                dpriceppack1 = "0.00";
            }
            
            
            //insert new data
            try {
                String sql="INSERT INTO PIS_MDC2 "
                        + "(UD_MDC_CODE,UD_ATC_CODE, D_TRADE_NAME,D_GNR_NAME,D_ROUTE_CODE,D_FORM_CODE,D_STRENGTH,D_ADVISORY_CODE,D_STOCK_QTY,D_QTY,D_QTYT,D_DURATION,D_DURATIONT,D_FREQUENCY,D_CAUTION_CODE, D_EXP_DATE, D_CLASSIFICATION, STATUS, D_LOCATION_CODE, D_SELL_PRICE, D_COST_PRICE,D_PACKAGING,D_PACKAGINGT,D_PRICE_PPACK) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                //prepare sql query and execute it
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, dmdc1);
                ps.setString(2, datc1);
                ps.setString(3, dtraden1);
                ps.setString(4, dgnrn1);
                ps.setString(5, droute1);
                ps.setString(6, ddosage1);
                ps.setString(7, dstrength1);
                ps.setString(8, dLadvisory1);
                ps.setDouble(9, Double.parseDouble(dstockqty1));
                ps.setDouble(10, Double.parseDouble(dLqty1));//d
                ps.setString (11,dLqtyt1 );
                ps.setInt(12, Integer.parseInt(dLduration1));//n
                ps.setString(13, dLdurationType1);
                ps.setString(14, dLfreq1);
                ps.setString(15, dLcaution1);
                ps.setString(16, dLexpdate1);//date
                ps.setString(17, dLclassification1);
                ps.setString(18, dstatus1);
                ps.setString(19, dloccode1);
                ps.setDouble(20, Double.parseDouble(dsellp1));//d
                ps.setDouble(21, Double.parseDouble(dcostp1));//d
                ps.setInt(22, Integer.parseInt(dpackaging1));
                ps.setString(23, dpackagingType1);
                ps.setDouble(24, Double.parseDouble(dpriceppack1));

                //update data
                ps.executeUpdate();

            } catch(Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        //reset cursor
        setCursor(Cursor.getDefaultCursor());

        //popup windows import success
        JOptionPane.showMessageDialog(pnl_import, "File import successfully!");
        book.close();
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
    }//GEN-LAST:event_btn_importMDCActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String s = new String();  
        StringBuilder sb = new StringBuilder();  
      
        try  
        {  
            FileReader fr = new FileReader(new File("db/script.sql"));//src/GUI  
            BufferedReader br = new BufferedReader(fr);  

            while((s = br.readLine()) != null)  
            {  
                sb.append(s);  
            }  
            br.close();  

            // here is our splitter ! We use ";" as a delimiter for each request  
            // then we are sure to have well formed statements  
            String[] inst = sb.toString().split(";");  

            Statement st = Session.getCon_x(1000).createStatement(); 
            //preparedStatement ps = Session.getCon_x().prepareStatement(sql);

            for(int i = 0; i<inst.length; i++)  
            {  
                // we ensure that there is no spaces before or after the request string  
                // in order to not execute empty statements  
                if(!inst[i].trim().equals(""))  
                {  
                    st.executeUpdate(inst[i]);  
                    System.out.println(">>"+inst[i]);
                    
                    br.close();
                } 
                
            } JOptionPane.showMessageDialog(null, "Success import data into PIS_MDC2 in HSQLDB\n"); 
        }  
        catch(Exception e)  
        {  
            JOptionPane.showMessageDialog(null, "Error import data into PIS_MDC2 in HSQLDB\n");
            System.out.println("Error PIS_MDC2: "+e.toString());  
            e.printStackTrace();  
            //System.out.println(sb.toString()); 
          
        }  
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int i = 1;
        try {
            //String source = "C:/PSMII_v7.3/data_mdc.csv";
            String source = "assets/pis_mdc2.csv";
            //directory where file will be copied
            //String target ="lib/userdata"; //C:/PSMII_v7.3/dist/lib/userdata
            String target ="db/";
                   
            javax.swing.JFileChooser FC2=new JFileChooser("assets/");
            FC2.showOpenDialog(this);
            FC2.setCurrentDirectory(new File("db/"));
            
            FileInputStream fis = new FileInputStream(FC2.getSelectedFile());
            
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
                   
            while(rowIterator.hasNext())
            {
                i++;
                Row row = rowIterator.next();     
                if(!ispkPISMDC2exist(row.getCell(0).toString()))
                {
                    ArrayList<String> strArr = new ArrayList<String>();
                    for (int n = row.getFirstCellNum() ;  n < row.getLastCellNum() && n < 24; n++)
                    {
                        if(n == 8 || n == 9 || n == 11 || n == 19 || n == 20 || n == 21 || n == 23)
                        {
                            if(row.getCell(n) ==null )
                            {
                                strArr.add("1");
                            }
                            else if(row.getCell(n).toString().equals(""))
                            {
                                strArr.add("1");
                            }
                            else
                            {
                                strArr.add(row.getCell(n).toString());
                            }
                        }
                        else if( n == 15 && row.getCell(n)!=null && !row.getCell(n).toString().equals("") ) // to convert date so that length will be fix to 10
                        {
                            Calendar cal = Calendar.getInstance();                           
                            cal.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(row.getCell(n).toString()));
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            dLexpdate = sdf.format(cal.getTime());//date
                            strArr.add(dLexpdate);
                        }
                        else
                        {
                            strArr.add(row.getCell(n)==null ? "" :row.getCell(n).toString());
                        }
                    }
                    fnInsertPISMDC2(strArr);
                    //String ggrr = "";
                }
            }
            //name of source file
            //File file = new File(source);
            //File sourceFile = new File(file.getAbsoluteFile()+source);
            //File sourceFile = new File(file.getAbsoluteFile()+filename);
//            File sourceFile = new File(source);
//            String name = sourceFile.getName();
//          
//            File targetFile = new File(target+name);
//            System.out.println("Copying file from " + sourceFile.getName() +" to "+source+ " from Java Program");
            //JOptionPane.showMessageDialog(btn_updateUStock, "Please select a drug to update its stock data!");
           
            //copy file from one location to other
//            FileUtils.copyFile(sourceFile, targetFile);
            
            JOptionPane.showMessageDialog(null, "Import Data From XLS done");
            System.out.println("Copying of file from Java program is completed\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to Import. Please ensure file XLS Excel 2003 only");
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sqlEx)
        {
            System.out.println(sqlEx+".Stuck at line :"+i);
        } catch(ParseException parEx)
        {
            System.out.println(parEx);
        }
        
//       //TEST 2
//        
//        javax.swing.JFileChooser FC = new JFileChooser(".");
//        FC.showOpenDialog(this);
//        FC.setCurrentDirectory(new File("."));
//
//
//        //get selected file address
//        File url = FC.getSelectedFile();
//        System.out.println(url);
//        urlAdd = String.valueOf(url);
//        jTextField1.setText(urlAdd);
        
//       //TEST 2
//        
//        javax.swing.JFileChooser FC = new JFileChooser(".");
//        FC.showOpenDialog(this);
//        FC.setCurrentDirectory(new File("."));
//
//
//        //get selected file address
//        File url = FC.getSelectedFile();
//        System.out.println(url);
//        urlAdd = String.valueOf(url);
//        jTextField1.setText(urlAdd);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    //fn to insert to tbl PIS_MDC2  -- Hariz 20141229
    private void fnInsertPISMDC2 ( ArrayList<String> arrData  ) throws SQLException
    {
  
        String sql = "INSERT INTO PIS_MDC2 "
                        + "(UD_MDC_CODE,UD_ATC_CODE, D_TRADE_NAME,D_GNR_NAME,D_ROUTE_CODE,"
                        + "D_FORM_CODE,D_STRENGTH,D_ADVISORY_CODE,D_STOCK_QTY,D_QTY,D_QTYT,"
                        + "D_DURATION,D_DURATIONT,D_FREQUENCY,D_CAUTION_CODE, D_EXP_DATE, D_CLASSIFICATION,"
                        + "STATUS, D_LOCATION_CODE, D_SELL_PRICE, D_COST_PRICE,D_PACKAGING,D_PACKAGINGT,"
                        + "D_PRICE_PPACK )"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        //prepare sql query and execute it
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        
        if(arrData.size() != 24) //IMPORTANT..If more table column added, need to change this also meh..
        {
            int arrSize = arrData.size();
            for(; arrSize < 24 ; arrSize++)
            {
                if(arrSize == 8 || arrSize == 9 || arrSize == 11 || arrSize == 19 || arrSize == 20 || arrSize == 21 || arrSize == 23)
                {
                    arrData.add("1"); 
                }
                else
                {
                   arrData.add(""); 
                }
                
            }
            
        }
        for(int i = 1 ; i <=  arrData.size() ; i++)
        {
//            if(i == 9 || i == 10 || i == 12 || i == 20 || i == 21 || i == 22 || i == 24)
//            {
//                ps.setDouble(i, Double.parseDouble(arrData.get(i-1)));
//            }
//            else
            {
              ps.setString(i, arrData.get(i-1));  
            }
            
        }
              

        ps.execute();
        
        try {
            String [] fixArrData = new String[arrData.size()];
            fixArrData = arrData.toArray(fixArrData);
            Boolean bool = DBConnection.getImpl().setQuery(sql, fixArrData);
            String ej = "ehhhh";
            
        } catch (Exception e) {
        }
    }
    
   
    private boolean ispkPISMDC2exist(String ud_mdc_code) throws SQLException
    {
        String sql = "Select UD_MDC_CODE from PIS_MDC2 where UD_MDC_CODE = ?";
        
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1,ud_mdc_code);
        
        ResultSet rs = ps.executeQuery();
        boolean boo = rs.next();
        return boo;
        
    }
     //fn to insert to tbl PIS_MDC2  -- Hariz 20141229 END
    
    private void tbl_patientInQueueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_patientInQueueMouseClicked

        resetTable();
        
        int index_row = tbl_patientInQueue.getSelectedRow();
        int index_col = tbl_patientInQueue.getSelectedColumn();
        
        try {
            if(!tbl_patientInQueue.getValueAt(index_row, index_col).equals("")) {
                tab_drugOrder.setSelectedIndex(1);
            }
        } catch (Exception e) {
        }
        
//        txt_pmiNo.setText(om.get(index_row).get(1));
//        txt_patientName.setText(om.get(index_row).get(34));
//        txt_icNo.setText(om.get(index_row).get(36));
//        txt_sex.setText(om.get(index_row).get(43));
//        txt_birthDate.setText(om.get(index_row).get(42));
//        txt_race.setText(om.get(index_row).get(45));
//        txt_bloodType.setText(om.get(index_row).get(48));

        order_no2.setText(om.get(index_row).get(0));//4
        order_date2.setText(om.get(index_row).get(5));
        loc_code.setText(om.get(index_row).get(2));//location code
        arrival_date.setText(om.get(index_row).get(3));
        
        txt_userNameOList.setText(Session.getUser_name());//show at text field
        
        String selectedPmiNo = (String) tbl_patientInQueue.getValueAt(index_row, 0);
        String orderDate = (String) tbl_patientInQueue.getValueAt(index_row, 2);
        //set formcode into textfield
        txt_pmiNo.setText(selectedPmiNo);
        
        /**
         * ini get dr online
         */
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            //online - 19062013
            
            System.err.println();
            System.err.println("Server Online");
            System.out.println("Start invoke remote server");
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method	

                ArrayList<String> pb = DBConnection.getImpl().getPatientBiodata(selectedPmiNo);
                if (pb.size() > 0) {
                    //read data get from database to all fields
                    txt_patientName.setText(pb.get(0));
                    txt_pmiNo.setText(pb.get(1));
                    txt_icNo.setText(pb.get(2));
                    txt_sex.setText(pb.get(3));
                    txt_birthDate.setText(pb.get(4));
                    txt_race.setText(pb.get(5));
                    txt_bloodType.setText(pb.get(6));
                    cbAllergy.setSelectedItem(pb.get(7));
                }

                //call data from table PMS_PATIENT_MEDICATION
                ArrayList<String> pisom = DBConnection.getImpl().getPisOrderMaster(selectedPmiNo, orderDate);
                if (pisom.size() > 0) {
                    //read data get from database to all fields
                    txt_doctor.setText(pisom.get(0));
                    order_no2.setText(pisom.get(1));
                    order_date2.setText(pisom.get(2));
                    loc_code.setText(pisom.get(3));
                }

                ArrayList<ArrayList<String>> od = DBConnection.getImpl().getDrugOrderDetail(order_no2.getText());

                row1 = 0;
                row2 = 0;

                //put od data into drug order
                for (int i = 0; i < od.size(); i++) {
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(0), row1, 0); //0
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(1), row1, 1); //1
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(7), row1, 2); //7
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(10), row1, 3); //9
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(13), row1, 4); //13
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(9), row1, 5); //11
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(12), row1, 6); //12
                    row1++;
                }

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                System.err.println("get order master error :" + e);
                
                //offline - 08072013
                /**
                 * test letak sini panggil offline db
                 */
            //get pmino
                //call data from table PMS_PATIENT_BIODATA
                ArrayList<String> pb = DBConnection.getPatientBiodata(selectedPmiNo);
                if (pb.size() > 0) {
                    //read data get from database to all fields
                    txt_patientName.setText(pb.get(0));
                    txt_pmiNo.setText(pb.get(1));
                    txt_icNo.setText(pb.get(2));
                    txt_sex.setText(pb.get(3));
                    txt_birthDate.setText(pb.get(4));
                    txt_race.setText(pb.get(5));
                    txt_bloodType.setText(pb.get(6));
                    cbAllergy.setSelectedItem(pb.get(7));
                }

                //call data from table PMS_PATIENT_MEDICATION
                ArrayList<String> pisom = DBConnection.getPisOrderMaster(selectedPmiNo, orderDate);
                if (pisom.size() > 0) {
                    //read data get from database to all fields
                    txt_doctor.setText(pisom.get(0));
                    order_no2.setText(pisom.get(1));
                    order_date2.setText(pisom.get(2));
                    loc_code.setText(pisom.get(3));
                }

                ArrayList<ArrayList<String>> od = DBConnection.getDrugOrderDetail(order_no2.getText());

                row1 = 0;
                row2 = 0;

                //put od data into drug order
                for (int i = 0; i < od.size(); i++) {
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(0), row1, 0);
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(1), row1, 1);
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(7), row1, 2);
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(10), row1, 3);//9
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(13), row1, 4);
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(11), row1, 5);//11
                    tbl_drugOrder.getModel().setValueAt(od.get(i).get(12), row1, 6);
                    row1++;
                }

                pnl_patientDrugOrder = new javax.swing.JPanel();
                pnl_patientDrugOrder.setVisible(true);
                pnl_patientList.setVisible(false);
                //e.printStackTrace();
            }

//        } else {
//            
//
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

    }//GEN-LAST:event_tbl_patientInQueueMouseClicked

    private void getQueue() {
//        LongRunProcess.check_network2(); 
//        if(Session.getPrev_stat()){
            try{
                getOrderMaster(1,"","");
            }catch(Exception zz){
                getOrderMasterOff(1,"","");
                //zz.printStackTrace();
            }
            
//        }
//        else{
//            
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
    }
    
    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
////         getOrderMaster(1,"","");
       getQueue();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        
        clearQueue();
//        LongRunProcess.check_network2();
//        if (Session.getPrev_stat()) {
            S.oln("-- Online Search--");
            //ambil current date
            if(txt_search_OrderNo.getText().equals(""))
            {
                getOrderMaster(2, txt_search_pol1.getText(), "");
            }
            else
            {
                filterOrderMaster(txt_search_OrderNo.getText());
            }
//        } else {
//            
//            try{
//                //buat search yg false tp bkn current time
//                S.oln("-- Offline Search--");
//                getOrderMasterOff(2, txt_search_pol.getText(), "");
//
//                //keluarkn pd tbl then blh click
//                
//            }catch(Exception ex){
//                System.out.println(ex);
//            }
//            
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
    }//GEN-LAST:event_btn_searchActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         
    Spatient.addWindowListener(new WindowAdapter(){
        public void windowClosed(WindowEvent e){
           txt_pmiNoOList1.setText(Pharmacy.data.getpmiNo());
           txt_patientNameOList.setText(Pharmacy.data.getpatientName());
           txt_idNoOList.setText(Pharmacy.data.getpatientIC());
           txt_raceOList.setText(Pharmacy.data.getrace());
           txt_bloodOList.setText(Pharmacy.data.getblood());
           txt_bodOList.setText(Pharmacy.data.getbod());
           txt_genderOList.setText(Pharmacy.data.getgender());
        }
    });
    Spatient.setVisible(true);
    Spatient.getContentPane().add(Spatient_panel);
    Spatient.pack();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        resetTable();
        tab_drugOrder.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null,"Clear all data ", "Cancel",JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        //get input from textfield
        atcCode = txt_atcCode.getText();
        atcDesc = txa_atcDescription.getText();
        categoryCode = txt_categoryCode.getText();
        if (rbt_activeATC.getSelectedObjects() != null) {
            status = "TRUE";
        } else {
            status = "FALSE";
        }
        
        try {
            
            String sql = "INSERT INTO PIS_ATC (UD_ATC_Code, UD_ATC_Desc, Category_Code, Status) VALUES (?,?,?,?) ";

            //prepare sql query and execute it
            //PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, atcCode);
            ps.setString(2, atcDesc);
            ps.setString(3, categoryCode);
            ps.setString(4, status);
            //update data
            ps.executeUpdate();

            //popup windows update success
            JOptionPane.showMessageDialog(btn_updateATC, "INSERT success!");

            //clear textfield
            txt_atcCode.setText("");
            txa_atcDescription.setText("");
            txt_categoryCode.setText("");
            rbt_activeATC.setSelected(false);
            rbt_inactiveATC.setSelected(false);
        } catch (Exception e) {
            System.out.println("Fail INSERT atc E " + e);
        }
        UpdateTbl();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtdrugS2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtdrugS2KeyReleased
        // TODO add your handling code here:
        dtraden = jtdrugS2.getText();

        if (dtraden.equals("")) {
            for (int i = 0; i < 50; i++) {
                jT_S3.getModel().setValueAt("", i, 0);
            }
        } else {
            try {

                for (int i = 0; i < 50; i++) {
                    jT_S3.getModel().setValueAt("", i, 0);
                }
//                    String sql = "SELECT * FROM PIS_MDC2 WHERE D_TRADE_NAME LIKE ? OR D_GNR_NAME LIKE ?";
                String sql = "SELECT * "
                        + "FROM PIS_MDC2 "
                        + "WHERE UCASE(D_TRADE_NAME) LIKE UCASE(?)";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, dtraden.toUpperCase() + "%");
                //ps.setString(2, dgnrn.toUpperCase() + "%");
                ResultSet results = ps.executeQuery();
                for (int i = 0; results.next() && i < 50; i++) {
                    jT_S3.getModel().setValueAt(results.getString("D_TRADE_NAME"), i, 0);
                    //System.out.println("test umar mukhtar");
                }

            } catch (Exception ex) {
                S.oln("search mdc 2" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jtdrugS2KeyReleased

    private void jT_S3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_S3MouseClicked
        // TODO add your handling code here:
        int index = jT_S3.getSelectedRow();
        String st = jT_S3.getModel().getValueAt(index, 0).toString();
        try {
//            String sql = "SELECT * FROM PIS_MDC WHERE DRUG_PRODUCT_NAME = ?";
            String sql = "SELECT * FROM PIS_MDC2 WHERE UCASE(D_TRADE_NAME) = UCASE(?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, st);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                getDetailProductName(results.getString("D_TRADE_NAME"));
            } else {
                getDetailProductName("");
            }
        } catch (Exception ex) {
            S.oln("MDC 12" + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jT_S3MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //clear text field search
        jtdrugS2.setText("");
        for (int i = 0; i < 50; i++) {
            jT_S3.getModel().setValueAt("", i, 0);
        }
        
        //clear textfield
        txt_drugNameMDC.setText("");
        txt_mdcCode.setText("");
        txt_ingredientCode.setText("");
        txt_cautionary.setText("");
        cdosage_form.setSelectedItem("");
        txt_drugRoute.setText("");
        //txt_atcCodeMDC.setText("");
        rbt_activeMDC.setSelected(false);
        rbt_inactiveMDC.setSelected(false);
        txt_drugStrength.setText("");
        
        cdosage_form.setSelectedIndex(0);
        txt_stockQty.setText("");
        txt_locCode.setText("");
        cb_supplierUStock.setSelectedIndex(0);
        dpack1.setText("");
        cdpack2.setSelectedIndex(0);
        d_priceppack.setText("");
        txt_costPrice.setText("");
        txt_sellprice.setText("");
        txt_Lqty.setText("");
        cLqtyT.setSelectedIndex(0);
        cLfrequency.setSelectedIndex(0);
        cLduration.setSelectedIndex(0);
        cLdurationType.setSelectedIndex(0);
        cInstruction.setSelectedIndex(0);
        txt_cautionary.setText("");
        txt_expdate.setDate(null);
        cClassification.setSelectedIndex(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        String sql = "DELETE FROM PIS_ATC WHERE UD_ATC_Code = ?";
        try {

            int row = tbl_atc.getSelectedRow();
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, txt_atcCode.getText());

            //((DefaultTableModel)tbl_supplier.getModel()).removeRow(row);
            ps.execute();
            ps.close();

            //clear textfield
            txt_atcCode.setText("");
            txa_atcDescription.setText("");
            txt_categoryCode.setText("");
            rbt_activeATC.setSelected(false);
            rbt_inactiveATC.setSelected(false);


            JOptionPane.showMessageDialog(null, "Success Deleted ");
        } catch (Exception e) {
            e.printStackTrace();
          }
        UpdateTbl();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbl_atcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_atcMouseClicked
        // TODO add your handling code here:
        int row = tbl_atc.getSelectedRow();
        String selectedATC = (String) tbl_atc.getValueAt(row, 0);

        try {
            String sql = "SELECT * FROM PIS_ATC WHERE UD_ATC_Code = ?";

            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, selectedATC);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                //read data get from database to all fields
                txt_atcCode.setText(result.getString("UD_ATC_Code"));
                txa_atcDescription.setText(result.getString("UD_ATC_Desc"));
                txt_categoryCode.setText(result.getString("Category_Code"));
                
                atcStatus = result.getString("Status");
                if (atcStatus.equals("TRUE") || atcStatus.equals("1")) {
                    rbt_activeATC.setSelected(true);
                    //atcStatus = "Active";
                } else {
                    rbt_inactiveATC.setSelected(true);
                    //atcStatus = "Inactive";
                }

            }
            //clean the results and data
            ps.close();
            result.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbl_atcMouseClicked

    private void jtfatcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfatcKeyReleased
        // TODO add your handling code here:
       
        atcCode = jtfatc.getText();

        if (atcCode.equals("")) {
            for (int i = 0; i < 50; i++) {
                jTatc.getModel().setValueAt("", i, 0);//jTatc
            }
        } else {
            try {
                for (int i = 0; i < 50; i++) {
                    jTatc.getModel().setValueAt("", i, 0);
                }
                String sql = "SELECT * "
                        + "FROM PIS_ATC "
                        + "WHERE UCASE(UD_ATC_Code) LIKE UCASE(?) ";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, atcCode.toUpperCase() + "%");
                ResultSet results = ps.executeQuery();
                for (int i = 0; results.next() && i < 50; i++) {
                    jTatc.getModel().setValueAt(results.getString("UD_ATC_Code"), i, 0);
                }

            } catch (Exception ex) {
                S.oln("search atc 2" + ex.getMessage());
            }
                //online - coding RMI

        }
    }//GEN-LAST:event_jtfatcKeyReleased

    private void jTatcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTatcMouseClicked
        // TODO add your handling code here:
        int index = jTatc.getSelectedRow();
        String st = jTatc.getModel().getValueAt(index, 0).toString();
        try {
            String sql = "SELECT * FROM PIS_ATC WHERE UD_ATC_Code = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, st);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                getDetailATC(results.getString("UD_ATC_Code"));
            } else {
                getDetailATC("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jTatcMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        //clear text field search
        jtfatc.setText("");
        for (int i = 0; i < 50; i++) {
            jTatc.getModel().setValueAt("", i, 0);
        }
       
        //clear textfield
        txt_atcCode.setText("");
        txa_atcDescription.setText("");
        txt_categoryCode.setText("");
        rbt_activeATC.setSelected(false);
        rbt_inactiveATC.setSelected(false);
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tbl_mdcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mdcMouseClicked
        // TODO add your handling code here:
        int row = tbl_mdc.getSelectedRow();
        String selectedMDC = (String) tbl_mdc.getValueAt(row, 0);

        try {
//            String sql = "SELECT * FROM PIS_MDC WHERE UD_MDC_CODE = ?";
            String sql = "SELECT * FROM PIS_MDC2 WHERE UD_MDC_CODE = ? ";//CHANGE TABLE //ORDER BY STATUS,UD_MDC_CODE

            //prepare the sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, selectedMDC);
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                //read data get from database to all fields
                txt_mdcCode.setText(results.getString("UD_MDC_CODE"));
//                catc.setSelectedItem(results.getString("UD_ATC_CODE"));
                txt_drugNameMDC.setText(results.getString("D_TRADE_NAME"));
                txt_ingredientCode.setText(results.getString("D_GNR_NAME"));
                txt_drugRoute.setText(results.getString("D_ROUTE_CODE"));
                cdosage_form.setSelectedItem(results.getString("D_FORM_CODE"));
                txt_drugStrength.setText(results.getString("D_STRENGTH"));
                txt_stockQty.setText(results.getString("D_STOCK_QTY"));
                txt_locCode.setText(results.getString("D_LOCATION_CODE"));
                dpack1.setText(results.getString("D_PACKAGING"));
                cdpack2.setSelectedItem(results.getString("D_PACKAGINGT"));
                d_priceppack.setText(results.getString("D_PRICE_PPACK"));
                txt_costPrice.setText(results.getString("D_COST_PRICE"));
                txt_sellprice.setText(results.getString("D_SELL_PRICE"));
                txt_Lqty.setText(results.getString("D_QTY"));
                cLfrequency.setSelectedItem(results.getString("D_FREQUENCY"));
                cLduration.setSelectedItem(results.getString("D_DURATION"));
                cLdurationType.setSelectedItem(results.getString("D_DURATIONT"));
                cInstruction.setSelectedItem(results.getString("D_ADVISORY_CODE"));
                txt_cautionary.setText(results.getString("D_CAUTION_CODE"));
                //txt_expdate.setText(results.getString("D_EXP_DATE"));
                
                DateFormat formatD = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date getED = null;
                try {
                    getED = formatD.parse(results.getString("D_EXP_DATE"));
                } catch (ParseException ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }
                txt_expdate.setDate(getED);
                cClassification.setSelectedItem(results.getString("D_CLASSIFICATION"));//D_CLASSIFICATION
                
                dstatus = results.getString("STATUS");
                if (dstatus.equals("TRUE") || dstatus.equals("REGISTERED")) {
                    rbt_activeMDC.setSelected(true);
                    //mdcStatus = "Active";
                } else {
                    rbt_inactiveMDC.setSelected(true);
                    //mdcStatus = "Inactive";
                }

            }
            //clean the results and data
            ps.close();
            results.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbl_mdcMouseClicked

    private void btn_addmdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addmdcActionPerformed
// TODO add your handling code here:
        if (txt_mdcCode.getText().equals("")) {
            //popup windows search drug first
            JOptionPane.showMessageDialog(btn_addmdc, "Please enter product to add!");
        } else if(txt_expdate.getDate() == null) 
        { 
            JOptionPane.showMessageDialog(btn_addmdc, "Please select expired date!");
        }
        else {

            //get input from textfield
            dmdc = txt_mdcCode.getText();
            datc = "UTeM";
            dtraden = txt_drugNameMDC.getText();
            dgnrn = txt_ingredientCode.getText();
            droute = txt_drugRoute.getText();
            ddosage = (String)cdosage_form.getSelectedItem();
            dstrength = txt_drugStrength.getText();
            dstockqty = txt_stockQty.getText().equals("")? "0" : txt_stockQty.getText();//double
            dloccode = txt_locCode.getText();
            
            if (rbt_activeMDC.getSelectedObjects() != null) {
                dstatus = "TRUE";
            } else {
                dstatus = "FALSE";
            }
            dpackaging = dpack1.getText().equals("")? "0" :dpack1.getText();
            dpackagingType = (String)cdpack2.getSelectedItem();
            dpriceppack = d_priceppack.getText().equals("") ? "0" : d_priceppack.getText() ;//double
            dcostp = txt_costPrice.getText().equals("")? "0" : txt_costPrice.getText() ;//double
            dsellp = txt_sellprice.getText().equals("") ? "0" : txt_sellprice.getText();//double
            dLqty = txt_Lqty.getText().equals("") ? "0" : txt_Lqty.getText();//double
            dLqtyt = (String)cLqtyT.getSelectedItem();
            dLfreq = (String)cLfrequency.getSelectedItem();
            dLduration = (String)cLduration.getSelectedItem();//numeric
            dLdurationType = (String)cLdurationType.getSelectedItem();
            dLadvisory= (String)cInstruction.getSelectedItem();
            dLcaution = txt_cautionary.getText();
                        
            Format formatter2 = new SimpleDateFormat("dd/MM/yyyy");
//            String dLexpdate = "-";
//            try {
//                dLexpdate = formatter2.format(txt_expdate.getDate());
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                dLexpdate = "-";
//            }
            dLexpdate = formatter2.format(txt_expdate.getDate());//date
            dLclassification = (String)cClassification.getSelectedItem();
            //insert into product supplier
            supname = (String) cb_supplierUStock.getSelectedItem();

            //call data from PIS_MDC
            try {

                String sql = "INSERT INTO PIS_MDC2 "
                        + "(UD_MDC_CODE,UD_ATC_CODE, D_TRADE_NAME,D_GNR_NAME,D_ROUTE_CODE,"
                        + "D_FORM_CODE,D_STRENGTH,D_ADVISORY_CODE,D_STOCK_QTY,D_QTY,D_QTYT,"
                        + "D_DURATION,D_DURATIONT,D_FREQUENCY,D_CAUTION_CODE, D_EXP_DATE, D_CLASSIFICATION,"
                        + "STATUS, D_LOCATION_CODE, D_SELL_PRICE, D_COST_PRICE,D_PACKAGING,D_PACKAGINGT,"
                        + "D_PRICE_PPACK )"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


                //prepare sql query and execute it
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, dmdc);
                ps.setString(2, "UTeM");
                ps.setString(3, dtraden);
                ps.setString(4, dgnrn);
                ps.setString(5, droute);
                ps.setString(6, ddosage);
                ps.setString(7, dstrength);
                ps.setString(8, dLadvisory );
                ps.setString(9, dstockqty );
                ps.setString(10, dLqty);//d
                ps.setString(11, dLqtyt);
                ps.setString(12, dLduration );//n
                ps.setString(13, dLdurationType );
                ps.setString(14, dLfreq );
                ps.setString(15, dLcaution );
                ps.setString(16, dLexpdate );//date
                ps.setString(17, dLclassification );
                ps.setString(18, dstatus);
                ps.setString(19, dloccode);
                ps.setString(20, dsellp);//d
                ps.setString(21, dcostp);//d
                ps.setString(22, dpackaging);
                ps.setString(23, dpackagingType);
                ps.setString(24, dpriceppack);
                
                //update data
                ps.execute();
                
                try {
                    String [] arrPS = new String[24];
                    arrPS[0]=(dmdc);
                    arrPS[1]=("Utem");
                    arrPS[2]=(dtraden);
                    arrPS[3]=(dgnrn);
                    arrPS[4]=(droute);
                    arrPS[5]=(ddosage);
                    arrPS[6]=(dstrength);
                    arrPS[7]=(dLadvisory);
                    arrPS[8]=(dstockqty);
                    arrPS[9]=(dLqty);
                    arrPS[10]=(dLqtyt);
                    arrPS[11]=(dLduration);
                    arrPS[12]=(dLdurationType);
                    arrPS[13]=(dLfreq);
                    arrPS[14]=(dLcaution);
                    arrPS[15]=(dLexpdate);
                    arrPS[16]=(dLclassification);
                    arrPS[17]=(dstatus);
                    arrPS[18]=(dloccode);
                    arrPS[19]=(dsellp);
                    arrPS[20]=(dcostp);
                    arrPS[21]=(dpackaging);
                    arrPS[22]=(dpackagingType);
                    arrPS[23]=(dpriceppack);
                    
                    Boolean bool = DBConnection.getImpl().setQuery(sql, arrPS);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Unable to save data to central server. Please save again when online");
                }
                
                String sql1 = "INSERT INTO PIS_PRODUCT_SUPPLIER (Update_Stock_Date, Staff_ID, Supplier_ID, UD_MDC_Code) VALUES (?,?,?,?)";

                //prepare sql query and execute it
                PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
                ps1.setTimestamp(1, sqlDate);
                ps1.setString(2, id);
                ps1.setString(3, supid);
                ps1.setString(4, dmdc);

                
                
                //update data
                ps1.execute();

                System.out.println(dstrength);
                System.out.println(dstockqty);
                System.out.println(dloccode);
                System.out.println(dstatus);
                System.out.println(dpackaging);
                System.out.println(dpackagingType);
                System.out.println(dpriceppack);
                System.out.println(dcostp);
                System.out.println(dsellp);
                System.out.println(dLqty);
                System.out.println(dLfreq);
                System.out.println(dLduration);
                System.out.println(dLdurationType);
                System.out.println(dLadvisory);
                System.out.println(dLcaution);
                System.out.println(dLexpdate);
                System.out.println(dLclassification);


                //popup windows update success
                JOptionPane.showMessageDialog(btn_addmdc, "Insert success!");
                System.out.println("Insert success");

            //clear textfield
            txt_mdcCode.setText("");
//            catc.setSelectedItem("-");
            txt_drugNameMDC.setText("");
            txt_ingredientCode.setText("");
            txt_drugRoute.setText("");
            cdosage_form.setSelectedItem("-");
            txt_drugStrength.setText("");
            txt_stockQty.setText("");
            txt_locCode.setText("");
            rbt_inactiveMDC.setSelected(false);
            rbt_activeMDC.setSelected(false);
            dpack1.setText("");
            cdpack2.setSelectedItem("-");
            d_priceppack.setText("");
            txt_costPrice.setText("");
            txt_sellprice.setText("");
            txt_Lqty.setText("");
            cLfrequency.setSelectedItem("-");
            cLduration.setSelectedItem("-");
            cLdurationType.setSelectedItem("-");
            cInstruction.setSelectedItem("-");
            txt_cautionary.setText("");
            txt_expdate.setDate(null);
            cClassification.setSelectedItem("");
                
            } catch (Exception e) {
                System.out.println("insert pis mdc2" + e);
                e.printStackTrace();
            }
        }
        UpdateTbl();
        fillcombo();
    }//GEN-LAST:event_btn_addmdcActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        // TODO add your handling code here:
        String sql ="DELETE FROM PIS_MDC2 WHERE UD_MDC_CODE = ?";
        try{
            
            int row = tbl_mdc.getSelectedRow(); 
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, txt_mdcCode.getText());
            
            ps.execute();
            ps.close();
            
            try {
                String [] arr = new String [1];
                arr[0] = txt_mdcCode.getText();
                boolean bool = DBConnection.getImpl().setQuery(sql, arr);
            } catch (Exception e) {
                
            }
            String drugName = txt_drugNameMDC.getText();
            //clear textfield
            txt_mdcCode.setText("");
//            catc.setSelectedItem("-");
            txt_drugNameMDC.setText("");
            txt_ingredientCode.setText("");
            txt_drugRoute.setText("");
            cdosage_form.setSelectedItem("-");
            txt_drugStrength.setText("");
            txt_stockQty.setText("");
            txt_locCode.setText("");
            rbt_inactiveMDC.setSelected(false);
            rbt_activeMDC.setSelected(false);
            dpack1.setText("");
            cdpack2.setSelectedItem("-");
            d_priceppack.setText("");
            txt_costPrice.setText("");
            txt_sellprice.setText("");
            txt_Lqty.setText("");
            cLqtyT.setSelectedItem("-");
            cLfrequency.setSelectedItem("-");
            cLduration.setSelectedItem("-");
            cLdurationType.setSelectedItem("-");
            cInstruction.setSelectedItem("-");
            txt_cautionary.setText("");
            txt_expdate.setDate(null);
            cClassification.setSelectedItem("");
            
            
            JOptionPane.showMessageDialog(null,"Deleted "+ drugName); 
        }catch(Exception e){
            
           JOptionPane.showMessageDialog(null,"del from pis mdc2 "+ e); 
        }
        
        
        UpdateTbl();
    }//GEN-LAST:event_btn_delActionPerformed
public void toExcel(JTable tbl_mdc, File file){
		try{
			TableModel model = tbl_mdc.getModel();
			FileWriter excel = new FileWriter(file);

			for(int i = 0; i < model.getColumnCount(); i++){
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i,j).toString()+"\t");
				}
				excel.write("\n");
			}

			excel.close();
		}catch(IOException e){ System.out.println(e); }
	}
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(Pharmacy.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();

            int len = filename.length();
            String ext = "";
            String file = "";

            if (len > 4) {
                ext = filename.substring(len - 4, len);
            }

            if (ext.equals(".xls")) {
                file = path + "\\" + filename;
            } else {
                file = path + "\\" + filename + ".xls";
            }
            toExcel(tbl_mdc, new File(file));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        //delete old table
        try {
            String sql="TRUNCATE TABLE PIS_MDC2";

            //prepare sql query and execute it
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            //delete table
            ps.execute();

        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void rb_newOList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_newOList2MouseClicked
        // TODO add your handling code here:
        rb_oldOList2.setSelected(false);
        rb_newOList2.setSelected(true);
    }//GEN-LAST:event_rb_newOList2MouseClicked

    private void rb_oldOList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_oldOList2MouseClicked
        // TODO add your handling code here:
        rb_newOList2.setSelected(false);
        rb_oldOList2.setSelected(true);
    }//GEN-LAST:event_rb_oldOList2MouseClicked

    private void btn_PrintLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintLabelActionPerformed
        // TODO add your handling code here:
        String orderNo = order_no2.getText();
        String patientName = txt_patientName.getText();
        String orderDate = order_date2.getText();
        try
        {
        PDFiText.createPrescriptionLabel("assets/prescLabel_.pdf", patientName, orderDate,orderNo);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
                
        btn_dispense.setEnabled(true);
    }//GEN-LAST:event_btn_PrintLabelActionPerformed

    private void txt_search_pol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_pol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_pol1ActionPerformed
    
    //get list of dispensed drug -- Hariz 20141203
    private void btnDailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDailyActionPerformed
        // TODO add your handling code here:
        String strSql = "Select DISTINCT(DRUG_ITEM_CODE), SUM(DISPENSED_QTY) as QTY from servercis.pis_dispense_detail PDS, servercis.pis_dispense_master PDM " +
                        "where PDM.order_no = PDS.order_no and DATE(PDM.DISPENSED_DATE) = DATE(NOW())" +
                        "group by PDS.Drug_item_code";
        
        fnCreateXLS(strSql, "Daily_Dispensed_Drug.xls");
             
    }//GEN-LAST:event_btnDailyActionPerformed

    private void btnYearlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYearlyActionPerformed
        // TODO add your handling code here:
        String strSql = "Select DISTINCT(DRUG_ITEM_CODE), SUM(DISPENSED_QTY) as QTY from servercis.pis_dispense_detail PDS, servercis.pis_dispense_master PDM " +
                        "where PDM.order_no = PDS.order_no and PDM.dispensed_date = YEAR(CURDATE())" +
                        "group by PDS.Drug_item_code";
       
        fnCreateXLS(strSql,"Yearly_Dispensed_Drug.xls");
        
    }//GEN-LAST:event_btnYearlyActionPerformed

    private void btnMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonthlyActionPerformed
        // TODO add your handling code here:
        String strSql = "Select DISTINCT(DRUG_ITEM_CODE), SUM(DISPENSED_QTY) as QTY from servercis.pis_dispense_detail PDS, servercis.pis_dispense_master PDM " +
                        "where PDM.order_no = PDS.order_no and MONTH(DISPENSED_DATE) = MONTH(CURDATE()) and YEAR(DISPENSED_DATE) = YEAR(CURDATE())" +
                        "group by PDS.Drug_item_code";
        
        fnCreateXLS(strSql,"Monthly_Dispensed_Drug.xls");
    }//GEN-LAST:event_btnMonthlyActionPerformed
   
    void fnCreateXLS(String strSQL , String fName)
    {
         JTable jtblYearly = new JTable();
        TableModel tm = null;
        String prepStmtn[] = { };
        try
        {
            tm = DBConnection.getImpl().getDispensedDrug(strSQL, prepStmtn);  
            jtblYearly.setModel(tm);
            System.out.println("Yeay!");
           
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }     
        
        JFileChooser jfc = new JFileChooser();
        jfc.setSelectedFile(new File(fName));
        int option = jfc.showSaveDialog(Pharmacy.this);
        if(option == JFileChooser.APPROVE_OPTION)
        {
            String fileName = jfc.getSelectedFile().getName();
            String filePath = jfc.getSelectedFile().getParentFile().getPath();
            String ext = "";
            String fileToSave = "";
            int nameLength = fileName.length();
            
            if(nameLength > 4)
            {
                ext = fileName.substring(nameLength - 4, nameLength);
            }
            
            if(ext.equals(".xls"))
            {
                fileToSave = filePath + "\\" + fileName;
            }
            else
            {
                fileToSave  = filePath + "\\" + fileName + ".xls";
            }
            toExcel(jtblYearly, new File(fileToSave));
        }
    }
    //get list of dispensed drug -- Hariz 20141203 END
    
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
  
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pharmacy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Spatient;
    private javax.swing.JPanel Spatient_panel;
    private javax.swing.JTextField arrival_date;
    private javax.swing.JButton btnDaily;
    private javax.swing.JButton btnMonthly;
    private static javax.swing.JButton btnStatus;
    private javax.swing.JButton btnYearly;
    private javax.swing.JButton btn_PrintLabel;
    private javax.swing.JButton btn_addmdc;
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_cancelATC;
    private javax.swing.JButton btn_cancelMDC;
    private javax.swing.JButton btn_cancelOList;
    private javax.swing.JButton btn_clearOList;
    private javax.swing.JButton btn_dcancel;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_deleteOList;
    private javax.swing.JButton btn_dispense;
    private javax.swing.JButton btn_dsave;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_editOList;
    private javax.swing.JButton btn_importATC;
    private javax.swing.JButton btn_importMDC;
    private javax.swing.JButton btn_mainPageOList;
    private javax.swing.JButton btn_newOrder;
    private javax.swing.JButton btn_ok2;
    private javax.swing.JButton btn_readMyCard2;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_saveOList;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_searchOList2;
    private javax.swing.JButton btn_submitOList;
    private javax.swing.JButton btn_supCancel;
    private javax.swing.JButton btn_supSave;
    private javax.swing.JButton btn_supdel;
    private javax.swing.JButton btn_updateATC;
    private javax.swing.JButton btn_updateMDC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox cClassification;
    private javax.swing.JComboBox cInstruction;
    private javax.swing.JComboBox cLduration;
    private javax.swing.JComboBox cLdurationType;
    private javax.swing.JComboBox cLfrequency;
    private javax.swing.JComboBox cLqtyT;
    private javax.swing.JComboBox cbAllergy;
    private javax.swing.JComboBox cb_durationOList;
    private javax.swing.JComboBox cb_durationTypeOList;
    private javax.swing.JComboBox cb_frequencyOList;
    private javax.swing.JComboBox cb_idTypeSearch1OList2;
    private javax.swing.JComboBox cb_instructionOList;
    private javax.swing.JComboBox cb_supplierUStock;
    private javax.swing.JComboBox cdosage_form;
    private javax.swing.JComboBox cdpack2;
    private javax.swing.JComboBox combox_supname;
    private javax.swing.JTextField d_priceppack;
    private javax.swing.JTextField dpack1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblIC;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jT_S3;
    private javax.swing.JTable jTatc;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JTextField jtdrugS2;
    private javax.swing.JTextField jtfatc;
    private java.awt.Label label2;
    protected static javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lbl_allergy;
    private javax.swing.JLabel lbl_atcCode;
    private javax.swing.JLabel lbl_atcCodeSearch;
    private javax.swing.JLabel lbl_atcDescription;
    private javax.swing.JLabel lbl_birthDate;
    private javax.swing.JLabel lbl_bloodType;
    private java.awt.Label lbl_browseFileConvert;
    private java.awt.Label lbl_browseFileConvert1;
    private javax.swing.JLabel lbl_categoryCode;
    private javax.swing.JLabel lbl_cautionary;
    private javax.swing.JLabel lbl_doctor;
    private javax.swing.JLabel lbl_dosageForm;
    private javax.swing.JLabel lbl_dosageOList;
    private java.awt.Label lbl_drugATCDetails;
    private javax.swing.JLabel lbl_drugNameMDC;
    private javax.swing.JLabel lbl_drugNameOListSearch;
    private javax.swing.JLabel lbl_drugRoute;
    private javax.swing.JLabel lbl_drugStrength;
    private javax.swing.JLabel lbl_durationOList;
    private javax.swing.JLabel lbl_durationOList1;
    private javax.swing.JLabel lbl_frequencyOList;
    private javax.swing.JLabel lbl_frequencyOList1;
    private javax.swing.JLabel lbl_icNo;
    private java.awt.Label lbl_icNoSearchOList2;
    private javax.swing.JLabel lbl_idNoOList;
    private java.awt.Label lbl_idNoSearchOList2;
    private java.awt.Label lbl_idTypeSearchOList2;
    private javax.swing.JLabel lbl_importFile;
    private javax.swing.JLabel lbl_ingredientCode;
    private javax.swing.JLabel lbl_instructionOList;
    private javax.swing.JLabel lbl_instructionOList1;
    private javax.swing.JLabel lbl_locationCodeUStock1;
    private javax.swing.JLabel lbl_mdcCode;
    private java.awt.Label lbl_patientInfo;
    private javax.swing.JLabel lbl_patientName;
    private javax.swing.JLabel lbl_patientNameOList;
    private javax.swing.JLabel lbl_pmiNo;
    private javax.swing.JLabel lbl_pmiNoOList;
    private java.awt.Label lbl_pmiNoSearchOList2;
    private java.awt.Label lbl_prepareDrugOrderOList;
    private javax.swing.JLabel lbl_productNameOList;
    private javax.swing.JLabel lbl_quantityOList;
    private javax.swing.JLabel lbl_quantityOList1;
    private javax.swing.JLabel lbl_race;
    private java.awt.Label lbl_searchDrugATC;
    private java.awt.Label lbl_searchDrugMDC;
    private java.awt.Label lbl_searchPatientOList1;
    private javax.swing.JLabel lbl_sex;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_statusMDC;
    private javax.swing.JLabel lbl_stockQtyUStock1;
    private javax.swing.JLabel lbl_supplierUStock1;
    private javax.swing.JLabel lbl_userIDOList;
    private java.awt.Label lbl_userInfoUpdateStock2;
    private java.awt.Label lbl_userInfoUpdateStock3;
    private javax.swing.JLabel lbl_userNameOList;
    private javax.swing.JTextField loc_code;
    private javax.swing.JLabel order_date1;
    private javax.swing.JTextField order_date2;
    private javax.swing.JLabel order_no;
    private javax.swing.JTextField order_no2;
    private javax.swing.JPanel pnl_atc;
    private javax.swing.JPanel pnl_convertAndManage;
    private javax.swing.JPanel pnl_drugOrder;
    private javax.swing.JPanel pnl_import;
    private javax.swing.JPanel pnl_mdc;
    private javax.swing.JPanel pnl_patientDrugOrder;
    private javax.swing.JPanel pnl_patientList;
    private javax.swing.JPanel pnl_prepareOrderList;
    private javax.swing.JPanel pnl_statusRB;
    private javax.swing.JRadioButton rb_newOList2;
    private javax.swing.JRadioButton rb_oldOList2;
    private javax.swing.JRadioButton rbt_activeATC;
    private javax.swing.JRadioButton rbt_activeMDC;
    private javax.swing.JRadioButton rbt_inactiveATC;
    private javax.swing.JRadioButton rbt_inactiveMDC;
    private javax.swing.JTextField stock_qty;
    private javax.swing.JTabbedPane tab_drugOrder;
    private javax.swing.JTable tbl_DrugHist;
    private javax.swing.JTable tbl_aller;
    private javax.swing.JTable tbl_atc;
    public static javax.swing.JTable tbl_drugList;
    private javax.swing.JTable tbl_drugOList;
    public static javax.swing.JTable tbl_drugOrder;
    private javax.swing.JTable tbl_mdc;
    private static javax.swing.JTable tbl_patientInQueue;
    public javax.swing.JTable tbl_productname;
    private javax.swing.JTable tbl_supplier;
    private javax.swing.JTabbedPane tpnl_manageDCode;
    private javax.swing.JTabbedPane tpnl_pharmacy;
    private javax.swing.JTextArea txa_atcDescription;
    private javax.swing.JTextField txt_Lqty;
    private javax.swing.JTextField txt_atcCode;
    private javax.swing.JTextField txt_birthDate;
    private javax.swing.JTextField txt_bloodOList;
    private javax.swing.JTextField txt_bloodType;
    private javax.swing.JTextField txt_bodOList;
    private javax.swing.JTextField txt_categoryCode;
    private javax.swing.JTextArea txt_caution;
    private javax.swing.JTextField txt_cautionary;
    private javax.swing.JTextField txt_costPrice;
    private javax.swing.JTextField txt_dadvisory_code;
    private javax.swing.JTextField txt_datc_code;
    private javax.swing.JTextField txt_dcaution_code;
    private javax.swing.JTextField txt_ddef_dosage;
    private javax.swing.JTextField txt_ddrug_strength;
    private javax.swing.JTextField txt_dform_code;
    private javax.swing.JTextArea txt_dingredient_code;
    private javax.swing.JTextField txt_dloc_code;
    private javax.swing.JTextField txt_dmdc_code;
    private javax.swing.JTextArea txt_dmdc_desc;
    private javax.swing.JTextField txt_doctor;
    private javax.swing.JTextField txt_dosageFormOList;
    private javax.swing.JTextArea txt_dproduct_name;
    private javax.swing.JTextField txt_droute_code;
    private javax.swing.JTextField txt_drugNameMDC;
    private javax.swing.JTextField txt_drugNameOListSearch;
    private javax.swing.JTextField txt_drugRoute;
    private javax.swing.JTextField txt_drugStrength;
    private javax.swing.JTextField txt_drugstrength;
    private javax.swing.JTextField txt_dstock_qty;
    private com.toedter.calendar.JDateChooser txt_expdate;
    private javax.swing.JTextField txt_genderOList;
    private javax.swing.JTextField txt_icNo;
    private javax.swing.JTextField txt_icNoSearch1OList2;
    private javax.swing.JTextField txt_idNoOList;
    private javax.swing.JTextField txt_idNoSearch1OList2;
    private javax.swing.JTextField txt_importFile;
    private javax.swing.JTextArea txt_ingredientCode;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_mdcCode;
    private javax.swing.JTextField txt_patientName;
    private javax.swing.JTextField txt_patientNameOList;
    private javax.swing.JTextField txt_pmiNo;
    private javax.swing.JTextField txt_pmiNoOList1;
    private javax.swing.JTextField txt_pmiNoSearch1OList2;
    private javax.swing.JTextField txt_productNameOList;
    private javax.swing.JTextField txt_quantityOList;
    private javax.swing.JTextField txt_race;
    private javax.swing.JTextField txt_raceOList;
    private javax.swing.JTextField txt_search_OrderNo;
    private javax.swing.JTextField txt_search_pol1;
    private javax.swing.JTextField txt_sellprice;
    private javax.swing.JTextField txt_sex;
    private javax.swing.JTextField txt_stockQty;
    private javax.swing.JTextField txt_supcontact;
    private javax.swing.JTextField txt_supid;
    private javax.swing.JTextArea txt_supname;
    private javax.swing.JTextField txt_userIDOList;
    private javax.swing.JTextField txt_userNameOList;
    // End of variables declaration//GEN-END:variables

}
