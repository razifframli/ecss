package GUI;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Registration.java
 *
 * Created on Feb 19, 2011, 3:13:47 PM
 */
/**
 *
 * @author Windows
 */

import DBConnection.DBConnection;
import DBConnection.PMI;
import Helper.J;
import Helper.S;
import Helper.Session;
import api.*;
import java.awt.Color;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.*;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;

public class Registration extends javax.swing.JFrame {

    private DefaultComboBoxModel UniCBmodel;
    String id = null;
    AppointmentList apptlistPage = new AppointmentList(this);
    ReferralDetail referralPage = new ReferralDetail(this);
    private int selectedTabIndex;
    private String dataPDI;
    private String dataNOK;
    private String dataEMP;
    private String dataFMI;
    private String dataINS;
    /* boolean for tab changes validation*/
    private boolean regSaveStatus = true;
    private boolean pmiSaveStatus = true;
    private boolean empSaveStatus = true;
    private boolean nokSaveStatus = true;
    private boolean fmiSaveStatus = true;
    private boolean insSaveStatus = true;
    
    public String[][] tbl_nok_array = new String[50][19];
    public String[][] tbl_employ_array = new String[50][10];
    public String[][] tbl_family_array = new String[50][6];
    public String[][] tbl_insurance_array = new String[50][7];

    private void setDefault() {
        Func.cmbSelectInput(cboxDisciplineRegistration, "Outpatient Discipline");
        Func.cmbSelectInput(cboxVisitTypeRegistration, "Walk-in");
        Func.cmbSelectInput(cboxEligibilityCategoryRegistration, "Government");
        Func.cmbSelectInput(cboxPatientCategoryRegistration, "General outpatient");
        Func.cmbSelectInput(cboxPatientCategoryRegistration, "General Outpatient");
        Func.cmbSelectInput(cboxEligibilityTypeRegistration, "Student");
        rbQueue.setSelected(true);
        rbDoctor.setSelected(false);
        rbConsultationRoom.setSelected(false);
        cboxConsultationRoom.setEnabled(false);
        cboxDoctor.setEnabled(false);
        cboxQueue.setEnabled(true);
        Func.cmbSelectInput(cboxQueue, "Common Queue");
        Func.cmbSelectInput(cboxPriorityGroupRegistration, "Normal");
        btnOnlineSearch.setVisible(false);
    }
    
    /** Creates new form Registration */
    public Registration() {

        initComponents();
        
        //hide button close
        btnClosePatientBiodata.setVisible(false);
        //hide some nextofkin field
        tfieldNextOfKinSequenceNo.setVisible(false);
        //hide some employment field
        label70.setVisible(false);
        tfieldEmploymentSequenceNo.setVisible(false);
        //hide some family field
        jLabel78.setVisible(false);
        tfieldFamilySequenceNo.setVisible(false);
        //hide some medical field
        tfieldmedicalseqno.setVisible(false);
        tfieldIDSearchRegister.setEditable(false);
        
        final int increment = 5;
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(increment);

        /*combobox selection*/
        JComboBox.KeySelectionManager manager =
                new JComboBox.KeySelectionManager() {

                    long lastKeyTime = 0;
                    String pattern = "";

                    public int selectionForKey(char aKey, ComboBoxModel model) {
                        // Find index of selected item
                        int selIx = 01;
                        Object sel = model.getSelectedItem();
                        if (sel != null) {
                            for (int i = 0; i < model.getSize(); i++) {
                                if (sel.equals(model.getElementAt(i))) {
                                    selIx = i;
                                    break;
                                }
                            }
                        }

                        // Get the current time
                        long curTime = System.currentTimeMillis();

                        // If last key was typed less than 300 ms ago, append to current pattern
                        if (curTime - lastKeyTime < 300) {
                            pattern += ("" + aKey).toLowerCase();
                        } else {
                            pattern = ("" + aKey).toLowerCase();
                        }

                        // Save current time
                        lastKeyTime = curTime;

                        // Search forward from current selection
                        for (int i = selIx + 1; i < model.getSize(); i++) {
                            String s = model.getElementAt(i).toString().toLowerCase();
                            if (s.startsWith(pattern)) {
                                return i;
                            }
                        }

                        // Search from top to current selection
                        for (int i = 0; i < selIx; i++) {
                            if (model.getElementAt(i) != null) {
                                String s = model.getElementAt(i).toString().toLowerCase();
                                if (s.startsWith(pattern)) {
                                    return i;
                                }
                            }
                        }
                        return -1;
                    }
                };

        /*load combobox*/
        LoadRegisterTabCB();
        LoadPMITabCB();
        LoadEmploymentTab();
        LoadNextKinTab();
        LoadMedicalTab();
        LoadFamilyTab();
        
        /*hide button if necessary*/
        button_new();

        selectedTabIndex = tabpanel.getSelectedIndex();

        btnGrpQueue.add(rbConsultationRoom);
        btnGrpQueue.add(rbQueue);
        btnGrpQueue.add(rbDoctor);
        //rbConsultationRoom.setSelected(true);

        btnGrpSearchICRegister.add(rbNewRegister);
        btnGrpSearchICRegister.add(rbOldRegister);
        rbNewRegister.setSelected(true);

        setDefault();
    }
    
    public void generateAutogeneratePMI() {
        String[] autogeneratePMI = {};
        Patient autogenerate = new Patient(this);
        
        try {
            autogeneratePMI = autogenerate.getAutogeneratePMI();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfieldPMINoPMI.setText("PMS" + autogeneratePMI[0]);
        tfieldPmiEmployment.setText("PMS" + autogeneratePMI[0]);
        tfieldPmiNextOfKin.setText("PMS" + autogeneratePMI[0]);
        tfieldPmiFamily.setText("PMS" + autogeneratePMI[0]);
        tfieldPmiInsurance.setText("PMS" + autogeneratePMI[0]);
    }
    
    public void generateAutogeneratedPMI(String ic) {
        String pmino_new = "";
        
        pmino_new = getPMI(ic);
        
        tfieldPMINoPMI.setText(pmino_new);
        tfieldPmiEmployment.setText(pmino_new);
        tfieldPmiNextOfKin.setText(pmino_new);
        tfieldPmiFamily.setText(pmino_new);
        tfieldPmiInsurance.setText(pmino_new);
    }
    
    public static String getPMI(String ic) {
        String pmino_new = "";

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

                pmino_new = DBConnection.getImpl().getPMI(ic);

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                
                PMI pmitemp = new PMI();
                pmino_new = pmitemp.getPMI(ic);
                
                //offline
                e.printStackTrace();

            }
//        } else {
//            //offline
//
//            
//        }
        
        return pmino_new;
    }
    
    public void generateAutogenerateFamily() {
        String[] autogenerateFSNo = {};
        Patient autogenerateFS = new Patient(this);

        try {
            autogenerateFSNo = autogenerateFS.getAutogenerateFSNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldFamilySequenceNo.setText("FS" + autogenerateFSNo[0]);
    }
    
    public void generateAutogenerateNOK() {
        String[] autogenerateNOKNo = {};
        Patient autogenerateNOK = new Patient(this);

        try {
            autogenerateNOKNo = autogenerateNOK.getAutogenerateNOKNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldNextOfKinSequenceNo.setText("NOKS" + autogenerateNOKNo[0]);
    }
    
    public void generateAutogenerateEmployment() {
        String[] autogenerateESNo = {};
        Patient autogenerateES = new Patient(this);

        try {
            autogenerateESNo = autogenerateES.getAutogenerateESNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldEmploymentSequenceNo.setText("ES" + autogenerateESNo[0]);
    }
    
    public void generateAutogenerateReceipt() {
        String[] autogenerateRecNo = {};
        Patient autogenerateRec = new Patient(this);

        try {
            autogenerateRecNo = autogenerateRec.getAutogenerateRecNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfieldReceiptNo.setText("REC" + autogenerateRecNo[0]);
    }
    
    public void generateAllAutogenerateNo(String ic) {
        LongRunProcess.check_network2();
        
        //generateAutogeneratePMI();
        generateAutogeneratedPMI(ic);
        
        generateAutogenerateFamily();
        generateAutogenerateNOK();
        generateAutogenerateEmployment();
        generateAutogenerateReceipt();
        
        Session.setPrev_stat(false);
        Session.setCurr_stat(false);
    }

    public Registration(String id) {
        this.id = id;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpQueue = new javax.swing.ButtonGroup();
        btnGrpPoliceCase = new javax.swing.ButtonGroup();
        btnGrpChildGuardian = new javax.swing.ButtonGroup();
        btnGrpSearchICFamily = new javax.swing.ButtonGroup();
        btnGrpSearchICInsurance = new javax.swing.ButtonGroup();
        btnGrpSearchICNOK = new javax.swing.ButtonGroup();
        btnGrpSearchICEmployment = new javax.swing.ButtonGroup();
        btnGrpSearchICPMI = new javax.swing.ButtonGroup();
        btnGrpSearchICRegister = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        tabpanel = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfieldPMISearchRegister = new javax.swing.JTextField();
        cboxIdTypeRegister = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfieldICSearchRegister = new javax.swing.JTextField();
        tfieldIDSearchRegister = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        rbNewRegister = new javax.swing.JRadioButton();
        rbOldRegister = new javax.swing.JRadioButton();
        btnSearchPatient = new javax.swing.JButton();
        btnReadMyKadRegister = new javax.swing.JButton();
        btnOnlineSearch = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfieldPMINoRegistration = new javax.swing.JTextField();
        tfieldNewICNoRegistration = new javax.swing.JTextField();
        cboxIdTypeRegistration = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfieldNameRegistration = new javax.swing.JTextField();
        tfieldOldICNoRegistration = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfieldIdNoRegistration = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tfieldRegistrationNo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboxVisitTypeRegistration = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        cboxEligibilityCategoryRegistration = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        cboxDisciplineRegistration = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cboxPatientCategoryRegistration = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        cboxEmergencyTypeRegistration = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cboxEligibilityTypeRegistration = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        pnlSelectQueue = new javax.swing.JPanel();
        rbConsultationRoom = new javax.swing.JRadioButton();
        rbQueue = new javax.swing.JRadioButton();
        rbDoctor = new javax.swing.JRadioButton();
        cboxConsultationRoom = new javax.swing.JComboBox();
        cboxQueue = new javax.swing.JComboBox();
        cboxDoctor = new javax.swing.JComboBox();
        jPanel23 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        cboxPriorityGroupRegistration = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tfieldChargeRate = new javax.swing.JTextField();
        tfieldPaymentAmount = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        tfieldReceiptNo = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        cboxPaymentMode = new javax.swing.JComboBox();
        btnRegister = new javax.swing.JButton();
        btnReferralDetails = new javax.swing.JButton();
        btnAppointmentList = new javax.swing.JButton();
        btnPrintReceipt = new javax.swing.JButton();
        btnClearRegistration = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tabPMI = new javax.swing.JPanel();
        pnl_IC_PersonMasterIndex = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        tfieldPMINoPMI = new javax.swing.JTextField();
        tfieldPatientName = new javax.swing.JTextField();
        tfieldNewICNoPatient = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cboxIdTypePatient = new javax.swing.JComboBox();
        cboxEligibilityCategoryPatient = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        tfieldDOBPatient = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        cboxMaritalStatusPatient = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        cboxNationalityPatient = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        tfieldTempPMINo = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        cboxTitle = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        tfieldOldICNoPatient = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tfieldIdNoPatient = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        cboxEligibilityTypePatient = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        cboxSexPatient = new javax.swing.JComboBox();
        jLabel51 = new javax.swing.JLabel();
        cboxRacePatient = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        cboxReligionPatient = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        cboxOrganDonorPatient = new javax.swing.JComboBox();
        cboxAllergyPatient = new javax.swing.JComboBox();
        cboxBloodTypePatient = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        cboxBloodRhesusPatient = new javax.swing.JComboBox();
        cboxChronicDiseasePatient = new javax.swing.JComboBox();
        jPanel30 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        tfieldHomeAddress = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        cboxDistrictCode = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        cboxTownCode = new javax.swing.JComboBox();
        jLabel64 = new javax.swing.JLabel();
        cboxPostcode = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        cboxStatePatient = new javax.swing.JComboBox();
        tfieldHomephonePatient = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        cboxCountryPatient = new javax.swing.JComboBox();
        jLabel66 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        tfieldPostalAddressPatient = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        cboxPostalCountryPatient = new javax.swing.JComboBox();
        cboxPostalPostcode = new javax.swing.JComboBox();
        cboxPostalDistrict = new javax.swing.JComboBox();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        tfieldHandphoneNoPatient = new javax.swing.JTextField();
        cboxPostalStatePatient = new javax.swing.JComboBox();
        cboxPostalTown = new javax.swing.JComboBox();
        btnSavePatientBiodata = new javax.swing.JButton();
        btnUpdatePatientBiodata = new javax.swing.JButton();
        btnClearPatientBiodata = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnClosePatientBiodata = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        label69 = new java.awt.Label();
        tfieldPmiEmployment = new javax.swing.JTextField();
        label70 = new java.awt.Label();
        tfieldEmploymentSequenceNo = new javax.swing.JTextField();
        label71 = new java.awt.Label();
        tfieldEmployerCode = new javax.swing.JTextField();
        label72 = new java.awt.Label();
        tfieldEmployerName = new javax.swing.JTextField();
        label73 = new java.awt.Label();
        tfieldOccupationCode = new javax.swing.JTextField();
        tfieldJoinedDate = new com.toedter.calendar.JDateChooser();
        cboxIncomeRange = new javax.swing.JComboBox();
        cboxHealthFacilityEmployment = new javax.swing.JComboBox();
        tfieldCreatedDate = new com.toedter.calendar.JDateChooser();
        cboxEmploymentStatus = new javax.swing.JComboBox();
        label76 = new java.awt.Label();
        label75 = new java.awt.Label();
        label5 = new java.awt.Label();
        label4 = new java.awt.Label();
        label74 = new java.awt.Label();
        btnSaveEmployment = new javax.swing.JButton();
        btnUpdateEmploymentInfo = new javax.swing.JButton();
        btnClearEmployment = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_employ = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        label19 = new java.awt.Label();
        btnReadMyKadNOK = new javax.swing.JButton();
        pnl_IC_NOK = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        label44 = new java.awt.Label();
        tfieldPmiNextOfKin = new javax.swing.JTextField();
        label45 = new java.awt.Label();
        cboxNOKRelationship = new javax.swing.JComboBox();
        label47 = new java.awt.Label();
        label48 = new java.awt.Label();
        tfieldNewICNo = new javax.swing.JTextField();
        label46 = new java.awt.Label();
        cboxIdTypeNOK = new javax.swing.JComboBox();
        tfieldDOB = new com.toedter.calendar.JDateChooser();
        label7 = new java.awt.Label();
        tfieldOccupationNOK = new javax.swing.JTextField();
        label52 = new java.awt.Label();
        tfieldIdNoNOK = new javax.swing.JTextField();
        label51 = new java.awt.Label();
        tfieldOldICNo = new javax.swing.JTextField();
        label50 = new java.awt.Label();
        tfieldNextOfKinName = new javax.swing.JTextField();
        label82 = new java.awt.Label();
        tfieldHpNoNOK = new javax.swing.JTextField();
        label83 = new java.awt.Label();
        tfieldHomephoneNoNOK = new javax.swing.JTextField();
        label84 = new java.awt.Label();
        tfieldEmailNOK = new javax.swing.JTextField();
        tfieldNextOfKinSequenceNo = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        cboxTownNOK = new javax.swing.JComboBox();
        cboxCountryNOK = new javax.swing.JComboBox();
        label78 = new java.awt.Label();
        label77 = new java.awt.Label();
        cboxPostcodeNOK = new javax.swing.JComboBox();
        label79 = new java.awt.Label();
        jLabel77 = new javax.swing.JLabel();
        cboxDistrictNOK = new javax.swing.JComboBox();
        cboxStateNOK = new javax.swing.JComboBox();
        label81 = new java.awt.Label();
        jScrollPane3 = new javax.swing.JScrollPane();
        tareaAddressNOK = new javax.swing.JTextArea();
        label80 = new java.awt.Label();
        btnSaveNOK = new javax.swing.JButton();
        btnUpdateNokInfo = new javax.swing.JButton();
        btnClearNOK = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_nok = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_family = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        tfieldPmiFamily = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        tfieldFamilySequenceNo = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        cboxFamilyRelationship = new javax.swing.JComboBox();
        jLabel75 = new javax.swing.JLabel();
        tareaFamilyMemberPMI = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        tareaFamilyMemberName = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        tfieldOccupationFamily = new javax.swing.JTextField();
        btnSaveFamilyInfo = new javax.swing.JButton();
        btnUpdateFamilyInfo = new javax.swing.JButton();
        btnClearFamily = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_insurance = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        label92 = new java.awt.Label();
        tfieldPmiInsurance = new javax.swing.JTextField();
        label93 = new java.awt.Label();
        cboxInsuranceCompanyName = new javax.swing.JComboBox();
        label94 = new java.awt.Label();
        tfieldPolicyNo = new javax.swing.JTextField();
        label95 = new java.awt.Label();
        tfieldMaturityDate = new com.toedter.calendar.JDateChooser();
        label6 = new java.awt.Label();
        cboxHealthFacilityInsurance = new javax.swing.JComboBox();
        label96 = new java.awt.Label();
        CboxPolicyStatus = new javax.swing.JComboBox();
        btnSaveInsurance = new javax.swing.JButton();
        btnUpdateInsurance = new javax.swing.JButton();
        btnClearInsuranceInfo = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        tfieldmedicalseqno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1343, 815));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1343, 815));

        tabpanel.setBackground(new java.awt.Color(255, 255, 255));
        tabpanel.setForeground(new java.awt.Color(0, 51, 153));
        tabpanel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabpanel.setName(""); // NOI18N
        tabpanel.setPreferredSize(new java.awt.Dimension(1343, 829));
        tabpanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabpanelStateChanged(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(1343, 829));

        jPanel9.setMaximumSize(new java.awt.Dimension(1389, 934));
        jPanel9.setMinimumSize(new java.awt.Dimension(1389, 934));
        jPanel9.setPreferredSize(new java.awt.Dimension(1389, 934));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SEARCH PATIENT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("PMI No.");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Id Type");

        tfieldPMISearchRegister.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldPMISearchRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfieldPMISearchRegisterMouseClicked(evt);
            }
        });
        tfieldPMISearchRegister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EnablePMIRegister(evt);
            }
        });

        cboxIdTypeRegister.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxIdTypeRegister.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Type", "Police", "Army", "Foreigner", "Matric No." }));
        cboxIdTypeRegister.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxIdTypeRegisterItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("IC No.");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Identification No.");

        tfieldICSearchRegister.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldICSearchRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfieldICSearchRegisterMouseClicked(evt);
            }
        });
        tfieldICSearchRegister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EnableICRegister(evt);
            }
        });

        tfieldIDSearchRegister.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldIDSearchRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfieldIDSearchRegisterMouseClicked(evt);
            }
        });
        tfieldIDSearchRegister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EnableIDRegister(evt);
            }
        });

        rbNewRegister.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbNewRegister.setSelected(true);
        rbNewRegister.setText("New");

        rbOldRegister.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbOldRegister.setText("Old");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(rbNewRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbOldRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOldRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbNewRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnSearchPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnSearchPatient.setText("Search");
        btnSearchPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPatientActionPerformed(evt);
            }
        });

        btnReadMyKadRegister.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnReadMyKadRegister.setText("Read MyKAD Info");
        btnReadMyKadRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadMyKadRegisterActionPerformed(evt);
            }
        });

        btnOnlineSearch.setText("Look Online");
        btnOnlineSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnlineSearchActionPerformed(evt);
            }
        });

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(88, 88, 88)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldPMISearchRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxIdTypeRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldICSearchRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldIDSearchRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchPatient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReadMyKadRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOnlineSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldICSearchRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfieldPMISearchRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton7))
                .addGap(7, 7, 7)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnReadMyKadRegister)
                    .addComponent(btnSearchPatient)
                    .addComponent(tfieldIDSearchRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboxIdTypeRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnOnlineSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1200, 100));

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Basic Info"));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("PMI No.");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("New IC No.");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setText("Id Type");

        tfieldPMINoRegistration.setEditable(false);
        tfieldPMINoRegistration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldPMINoRegistration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeablePmiRegister(evt);
            }
        });

        tfieldNewICNoRegistration.setEditable(false);
        tfieldNewICNoRegistration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldNewICNoRegistration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableNewIcRegister(evt);
            }
        });

        cboxIdTypeRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxIdTypeRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Type", "-", "Police", "Army", "Foreigner" }));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Name");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("Old IC No.");

        tfieldNameRegistration.setEditable(false);
        tfieldNameRegistration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldNameRegistration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableNameRegister(evt);
            }
        });

        tfieldOldICNoRegistration.setEditable(false);
        tfieldOldICNoRegistration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldOldICNoRegistration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangableOldIcRegister(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setText("Identification No.");

        tfieldIdNoRegistration.setEditable(false);
        tfieldIdNoRegistration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(98, 98, 98)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldPMINoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldNewICNoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxIdTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(38, 38, 38)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldNameRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldOldICNoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldIdNoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(tfieldPMINoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tfieldNameRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(tfieldNewICNoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldOldICNoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(cboxIdTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldIdNoRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Registration Info"));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setText("Registration No.");

        tfieldRegistrationNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel13.setText("Visit Type *");

        cboxVisitTypeRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxVisitTypeRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Visit Type", "Walk-in", "Appointment", "Referral" }));

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel15.setText("Eligibility Category");

        cboxEligibilityCategoryRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxEligibilityCategoryRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Egibibility Category", "Public", "Private", "Government", "Socso" }));

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel17.setText("Discipline *");

        cboxDisciplineRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxDisciplineRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Discipline", "Outpatient Discipline", "Inpatient Discipline" }));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setText("Patient Category *");

        cboxPatientCategoryRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPatientCategoryRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Patient Category", "General outpatient", "Specialist outpatient" }));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setText("Emergency Type");

        cboxEmergencyTypeRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxEmergencyTypeRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Emergency Type", "-", "Accident", "Heart Attack", "Coma" }));

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel16.setText("Eligibility Type");

        cboxEligibilityTypeRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxEligibilityTypeRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Eligibility Type", "-", "Pensioner" }));

        jLabel19.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel19.setText("Please select the following option : *");

        rbConsultationRoom.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbConsultationRoom.setText("Consultation Room");
        rbConsultationRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnableConsulationRoom(evt);
            }
        });

        rbQueue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbQueue.setSelected(true);
        rbQueue.setText("Queue");
        rbQueue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnableQueue(evt);
            }
        });

        rbDoctor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbDoctor.setText("Doctor");
        rbDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnableDoctor(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectQueueLayout = new javax.swing.GroupLayout(pnlSelectQueue);
        pnlSelectQueue.setLayout(pnlSelectQueueLayout);
        pnlSelectQueueLayout.setHorizontalGroup(
            pnlSelectQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rbConsultationRoom)
            .addComponent(rbQueue)
            .addComponent(rbDoctor)
        );
        pnlSelectQueueLayout.setVerticalGroup(
            pnlSelectQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectQueueLayout.createSequentialGroup()
                .addComponent(rbConsultationRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbQueue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbDoctor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cboxConsultationRoom.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxConsultationRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Consultation Room", "Consultation Room 1", "Consultation Room 2", "Consultation Room 3", "Consultation Room 4", "Consultation Room 5" }));

        cboxQueue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxQueue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Queue", "Common Queue", " " }));

        cboxDoctor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxDoctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Doctor", "Dr.Jeff", "Dr.Angeline", "Dr.Ranjit", "Dr.Chung", "Dr.Lee" }));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(pnlSelectQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxConsultationRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxDisciplineRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxEligibilityCategoryRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxVisitTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldRegistrationNo, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addComponent(jLabel16))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(550, 550, 550)
                        .addComponent(jLabel14))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(550, 550, 550)
                        .addComponent(jLabel12))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(662, 662, 662)
                        .addComponent(cboxEligibilityTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(662, 662, 662)
                        .addComponent(cboxEmergencyTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(662, 662, 662)
                        .addComponent(cboxPatientCategoryRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxPatientCategoryRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(tfieldRegistrationNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxEmergencyTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(cboxVisitTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxEligibilityTypeRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(cboxEligibilityCategoryRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(cboxDisciplineRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel19)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pnlSelectQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cboxConsultationRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(cboxQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Registration Detail"));

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel20.setText("Priority Group *");

        cboxPriorityGroupRegistration.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPriorityGroupRegistration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Priority Group", "Normal", "Elderly" }));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(78, 78, 78)
                .addComponent(cboxPriorityGroupRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(497, 497, 497))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(cboxPriorityGroupRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "FEE COLLECTION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel30.setText("Charge Rate");

        jLabel32.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel32.setText("Payment Amount");

        jLabel33.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel33.setText("RM");

        jLabel31.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel31.setText("RM");

        tfieldChargeRate.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel81.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel81.setText("Receipt No");

        tfieldReceiptNo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tfieldReceiptNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableReceiptNo(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel82.setText("Payment Mode");

        cboxPaymentMode.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPaymentMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Payment Mode", "Cash", "Credit" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel31)
                        .addGap(12, 12, 12)
                        .addComponent(tfieldChargeRate, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel33)
                        .addGap(12, 12, 12)
                        .addComponent(tfieldPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxPaymentMode, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldReceiptNo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(tfieldChargeRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82)
                    .addComponent(cboxPaymentMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(tfieldPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81)
                    .addComponent(tfieldReceiptNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnReferralDetails.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReferralDetails.setText("Referral Details");
        btnReferralDetails.setEnabled(false);
        btnReferralDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReferralDetailsActionPerformed(evt);
            }
        });

        btnAppointmentList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAppointmentList.setText("Appointment List");
        btnAppointmentList.setEnabled(false);
        btnAppointmentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppointmentListActionPerformed(evt);
            }
        });

        btnPrintReceipt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrintReceipt.setText("Print Receipt");
        btnPrintReceipt.setEnabled(false);
        btnPrintReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReceiptActionPerformed(evt);
            }
        });

        btnClearRegistration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearRegistration.setText("Clear");
        btnClearRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRegistrationActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton1.setText("Back To Main Page");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegister)
                .addGap(13, 13, 13)
                .addComponent(btnReferralDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnAppointmentList, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnPrintReceipt)
                .addGap(17, 17, 17)
                .addComponent(btnClearRegistration)
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addGap(212, 212, 212))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegister)
                    .addComponent(btnReferralDetails)
                    .addComponent(btnAppointmentList)
                    .addComponent(btnPrintReceipt)
                    .addComponent(btnClearRegistration)
                    .addComponent(jButton1))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1200, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(617, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        tabpanel.addTab("Registration", jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(1343, 775));

        tabPMI.setEnabled(false);
        tabPMI.setMaximumSize(new java.awt.Dimension(1343, 775));
        tabPMI.setPreferredSize(new java.awt.Dimension(1343, 811));

        javax.swing.GroupLayout pnl_IC_PersonMasterIndexLayout = new javax.swing.GroupLayout(pnl_IC_PersonMasterIndex);
        pnl_IC_PersonMasterIndex.setLayout(pnl_IC_PersonMasterIndexLayout);
        pnl_IC_PersonMasterIndexLayout.setHorizontalGroup(
            pnl_IC_PersonMasterIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );
        pnl_IC_PersonMasterIndexLayout.setVerticalGroup(
            pnl_IC_PersonMasterIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "PATIENT INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel38.setText("PMI No.");

        jLabel40.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel40.setText("Name");

        tfieldPMINoPMI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldPMINoPMI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeablePmiBiodata(evt);
            }
        });

        tfieldPatientName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldPatientName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableNameBiodata(evt);
            }
        });

        tfieldNewICNoPatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldNewICNoPatient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableNewICBiodata(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel42.setText("New IC No.");

        jLabel44.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel44.setText("Id Type");

        cboxIdTypePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxIdTypePatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Type", "-", "Police", "Army", "Foreigner" }));

        cboxEligibilityCategoryPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxEligibilityCategoryPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Eligibility Category", "Public", "Private", "Government", "Sosco" }));

        jLabel48.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel48.setText("Eligibility Category");

        jLabel46.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel46.setText("Date of Birth");

        tfieldDOBPatient.setDateFormatString("yyyy-MM-dd");

        jLabel50.setText("Example: 1989-10-31");

        cboxMaritalStatusPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxMaritalStatusPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Marital Status", "Single", "Married" }));

        jLabel54.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel54.setText("Marital Status");

        jLabel52.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel52.setText("Nationality");

        cboxNationalityPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxNationalityPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Nationality", "Malaysian", "Singaporean", "American" }));

        jLabel39.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel39.setText("Temporary PMI No.");

        tfieldTempPMINo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel41.setText("Title");

        cboxTitle.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxTitle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Title", "Mr", "Mrs", "Ms" }));

        jLabel43.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel43.setText("Old IC No.");

        tfieldOldICNoPatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldOldICNoPatient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableOldIcBiodata(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel45.setText("Identification No.");

        tfieldIdNoPatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel49.setText("Eligibility Type");

        cboxEligibilityTypePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxEligibilityTypePatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Eligibility Type", "-", "Pensioner" }));

        jLabel47.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel47.setText("Gender");

        cboxSexPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxSexPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Sex", "Male", "Female" }));

        jLabel51.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel51.setText("Race");

        cboxRacePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxRacePatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Race", "Malay", "Chinese", "Indian", "Others" }));

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel29.setText("Religion");

        cboxReligionPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxReligionPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Region", "Buddha", "Islam", "Kristian", "Hindu", " " }));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel40)
                    .addComponent(jLabel42)
                    .addComponent(jLabel44)
                    .addComponent(jLabel48)
                    .addComponent(jLabel46)
                    .addComponent(jLabel54)
                    .addComponent(jLabel52))
                .addGap(66, 66, 66)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfieldPMINoPMI, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldNewICNoPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxIdTypePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxEligibilityCategoryPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(tfieldDOBPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cboxMaritalStatusPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxNationalityPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel41)
                    .addComponent(jLabel43)
                    .addComponent(jLabel45)
                    .addComponent(jLabel49)
                    .addComponent(jLabel47)
                    .addComponent(jLabel51)
                    .addComponent(jLabel29))
                .addGap(28, 28, 28)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldTempPMINo)
                    .addComponent(cboxTitle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfieldOldICNoPatient)
                    .addComponent(tfieldIdNoPatient)
                    .addComponent(cboxEligibilityTypePatient, 0, 244, Short.MAX_VALUE)
                    .addComponent(cboxSexPatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboxRacePatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboxReligionPatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(88, 88, 88))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(tfieldPMINoPMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39))
                    .addComponent(tfieldTempPMINo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(cboxTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42)
                                .addComponent(jLabel43)
                                .addComponent(tfieldOldICNoPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfieldNewICNoPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(jLabel45)
                                .addComponent(tfieldIdNoPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboxIdTypePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxEligibilityCategoryPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49)
                            .addComponent(cboxEligibilityTypePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(cboxSexPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxRacePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxReligionPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfieldDOBPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboxMaritalStatusPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel51)))
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxNationalityPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52)
                            .addComponent(jLabel29))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MEDICAL INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel53.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel53.setText("Blood Type");

        jLabel59.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel59.setText("Allergy");

        jLabel55.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel55.setText("Organ Donor");

        cboxOrganDonorPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxOrganDonorPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        cboxAllergyPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxAllergyPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        cboxBloodTypePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxBloodTypePatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Blood Type", "A", "B", "AB", "O" }));

        jLabel58.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel58.setText("Blood Rhesus");

        jLabel60.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel60.setText("Chronic Disease");

        cboxBloodRhesusPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxBloodRhesusPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Blood Rhesus", "Positive", "Negative" }));

        cboxChronicDiseasePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxChronicDiseasePatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addComponent(jLabel59)
                    .addComponent(jLabel55))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxBloodTypePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxAllergyPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxOrganDonorPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel60))
                .addGap(41, 41, 41)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboxBloodRhesusPatient, 0, 246, Short.MAX_VALUE)
                    .addComponent(cboxChronicDiseasePatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel59)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxBloodTypePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58)
                            .addComponent(cboxBloodRhesusPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(cboxChronicDiseasePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxAllergyPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxOrganDonorPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "PATIENT CONTACT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel56.setText("Home Address");

        tfieldHomeAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel63.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel63.setText("District");

        cboxDistrictCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboxDistrictCode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select District", "-", "MELAKA TENGAH", "ALOR GAJAH", "JASIN", "JOHOR BAHRU", "KOTA TINGGI", "SEGAMAT", "BINTULU", "TAIPING", "PEKAN", "SEREMBAN" }));

        jLabel62.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel62.setText("Town");

        cboxTownCode.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxTownCode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Town", "-", "MELACCA TOWN", "JOHOR BAHRU", "SEREMBAN", "SHAH ALAM", "KUALA LUMPUR", "IPOH", "KANGAR", "ALOR SETAR", "KOTA BAHRU", "KOT KINABALU", "KUALA TERENGGANU", "KUCHING", "TAIPING", "BINTULU", "PEKAN", "VICTORIA", "PUTRAJAYA", "GEORGE TOWN" }));

        jLabel64.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel64.setText("Postcode");

        cboxPostcode.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostcode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Postcode", "-", "08100", "75000", "75060", "76000", "81100", "80250", "97000", "34000", "70200", "26690" }));

        jLabel65.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel65.setText("State");

        cboxStatePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        tfieldHomephonePatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel57.setText("Phone No. (Home)");

        cboxCountryPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxCountryPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Country", "MALAYSIA", "SINGAPORE", "THAILAND", "VIETNAM" }));

        jLabel66.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel66.setText("Country");

        jLabel61.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel61.setText("Postal Address");

        tfieldPostalAddressPatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel67.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel67.setText("District");

        jLabel68.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel68.setText("Postcode");

        jLabel69.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel69.setText("Country");

        cboxPostalCountryPatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostalCountryPatient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Country", "MALAYSIA", "SINGAPORE", "THAILAND", "VIETNAM" }));

        cboxPostalPostcode.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostalPostcode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Postcode", "-", "08100", "75000", "75060", "76000", "81100", "80250", "97000", "34000", "26690", "70200" }));

        cboxPostalDistrict.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostalDistrict.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select District", "-", "MELAKA TENGAH", "ALOR GAJAH", "JASIN", "JOHOR BAHRU", "KOTA TINGGI", "SEGAMAT", "BINTULU", "TAIPING", "PEKAN", "SEREMBAN" }));

        jLabel70.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel70.setText("Town");

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText("State");

        jLabel72.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel72.setText("Handphone No.");

        cboxPostalStatePatient.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostalStatePatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboxPostalStatePatientMouseEntered(evt);
            }
        });

        cboxPostalTown.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostalTown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Town", "MELACCA TOWN", "JOHOR BAHRU", "SEREMBAN", "SHAH ALAM", "KUALA LUMPUR", "IPOH", "KANGAR", "ALOR SETAR", "KOTA BAHRU", "KOT KINABALU", "KUALA TERENGGANU", "KUCHING", "TAIPING", "BINTULU", "PEKAN", "VICTORIA", "PUTRAJAYA", "GEORGE TOWN" }));

        btnSavePatientBiodata.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSavePatientBiodata.setText("Save");
        btnSavePatientBiodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePatientBiodataActionPerformed(evt);
            }
        });

        btnUpdatePatientBiodata.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdatePatientBiodata.setText("Update");
        btnUpdatePatientBiodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePatientBiodataActionPerformed(evt);
            }
        });

        btnClearPatientBiodata.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearPatientBiodata.setText("Clear");
        btnClearPatientBiodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearPatientBiodataActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Main Page");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnClosePatientBiodata.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClosePatientBiodata.setText("Close");
        btnClosePatientBiodata.setEnabled(false);
        btnClosePatientBiodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClosePatientBiodataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(btnClearPatientBiodata)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(btnSavePatientBiodata)
                .addGap(18, 18, 18)
                .addComponent(btnUpdatePatientBiodata)
                .addGap(18, 18, 18)
                .addComponent(btnClosePatientBiodata)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboxPostcode, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboxDistrictCode, javax.swing.GroupLayout.Alignment.LEADING, 0, 180, Short.MAX_VALUE))
                                    .addComponent(cboxCountryPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(228, 228, 228)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel65)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel62)))
                            .addComponent(tfieldHomeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64)
                            .addComponent(jLabel66)
                            .addComponent(jLabel61)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxPostalDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxPostalPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxPostalCountryPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(232, 232, 232)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70)
                                    .addComponent(jLabel71)
                                    .addComponent(jLabel72))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboxPostalStatePatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfieldHandphoneNoPatient)
                                    .addComponent(cboxStatePatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfieldHomephonePatient)
                                    .addComponent(cboxPostalTown, 0, 176, Short.MAX_VALUE)
                                    .addComponent(cboxTownCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(tfieldPostalAddressPatient))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel56)
                    .addComponent(tfieldHomeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel63)
                    .addComponent(cboxDistrictCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(cboxTownCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel64)
                    .addComponent(cboxPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(cboxStatePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel66)
                    .addComponent(cboxCountryPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(tfieldHomephonePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel61)
                    .addComponent(tfieldPostalAddressPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel67)
                    .addComponent(cboxPostalDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70)
                    .addComponent(cboxPostalTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel68)
                    .addComponent(cboxPostalPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxPostalStatePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel69)
                    .addComponent(cboxPostalCountryPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72)
                    .addComponent(tfieldHandphoneNoPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearPatientBiodata)
                    .addComponent(jButton2)
                    .addComponent(btnSavePatientBiodata)
                    .addComponent(btnUpdatePatientBiodata)
                    .addComponent(btnClosePatientBiodata))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabPMILayout = new javax.swing.GroupLayout(tabPMI);
        tabPMI.setLayout(tabPMILayout);
        tabPMILayout.setHorizontalGroup(
            tabPMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPMILayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(tabPMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(pnl_IC_PersonMasterIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        tabPMILayout.setVerticalGroup(
            tabPMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPMILayout.createSequentialGroup()
                .addGroup(tabPMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPMILayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(pnl_IC_PersonMasterIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabPMILayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tabPMI, javax.swing.GroupLayout.PREFERRED_SIZE, 1322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(684, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPMI, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabpanel.addTab("Patient Master Index", jPanel3);

        jPanel24.setPreferredSize(new java.awt.Dimension(1003, 388));

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "EMPLOYMENT INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        label69.setText("PMI No.");

        tfieldPmiEmployment.setEditable(false);
        tfieldPmiEmployment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label70.setText("Employment Sequence No.");

        tfieldEmploymentSequenceNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldEmploymentSequenceNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangableESNO(evt);
            }
        });

        label71.setText("Employer Code");

        tfieldEmployerCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label72.setText("Employer Name");

        tfieldEmployerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label73.setText("Occupation");

        tfieldOccupationCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cboxIncomeRange.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxIncomeRange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Income Range", "RM1000 And Below", "RM1000-RM1500", "RM1500-RM2000", "RM2000-RM3000", "RM3000-RM5000", "RM5000 And Above" }));

        cboxHealthFacilityEmployment.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxHealthFacilityEmployment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Health Facility", "Hospital Sultanah Aminah Johor Bahru", "Melaka General Hospital", "Hospital Kuala Lumpur", "Hospital Ipoh", "Hospital Pekan", "Hospital Jasin" }));

        cboxEmploymentStatus.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxEmploymentStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Status", "Contract", "Permanent", "Probition" }));

        label76.setText("Status");

        label75.setText("Created Date");

        label5.setText("Health Facility");

        label4.setText("Income Range");

        label74.setText("Joined Date");

        btnSaveEmployment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveEmployment.setText("Save");
        btnSaveEmployment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEmploymentActionPerformed(evt);
            }
        });

        btnUpdateEmploymentInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdateEmploymentInfo.setText("Update");
        btnUpdateEmploymentInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateEmploymentInfoActionPerformed(evt);
            }
        });

        btnClearEmployment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearEmployment.setText("Clear");
        btnClearEmployment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearEmploymentActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Main Page");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxEmploymentStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfieldCreatedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfieldJoinedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfieldPmiEmployment)
                            .addComponent(tfieldEmploymentSequenceNo)
                            .addComponent(tfieldEmployerCode)
                            .addComponent(tfieldEmployerName)
                            .addComponent(tfieldOccupationCode)
                            .addComponent(cboxIncomeRange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxHealthFacilityEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnSaveEmployment)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateEmploymentInfo)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearEmployment)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldPmiEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldEmploymentSequenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldEmployerCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldEmployerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldOccupationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldJoinedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxIncomeRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxHealthFacilityEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldCreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxEmploymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSaveEmployment)
                    .addComponent(btnUpdateEmploymentInfo)
                    .addComponent(btnClearEmployment)
                    .addComponent(jButton3))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List of Employment"));

        tbl_employ.setModel(new javax.swing.table.DefaultTableModel(
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
                "Employer Name", "Occupation"
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
        tbl_employ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_employMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_employ);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel24, 0, 1313, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(683, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(308, Short.MAX_VALUE))
        );

        tabpanel.addTab("Employment", jPanel19);

        label19.setBackground(new java.awt.Color(153, 153, 255));
        label19.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label19.setText("SEARCH PATIENT");

        btnReadMyKadNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnReadMyKadNOK.setText("Read MyKAD Info");
        btnReadMyKadNOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadMyKadNOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_IC_NOKLayout = new javax.swing.GroupLayout(pnl_IC_NOK);
        pnl_IC_NOK.setLayout(pnl_IC_NOKLayout);
        pnl_IC_NOKLayout.setHorizontalGroup(
            pnl_IC_NOKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );
        pnl_IC_NOKLayout.setVerticalGroup(
            pnl_IC_NOKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReadMyKadNOK)
                .addGap(506, 506, 506)
                .addComponent(pnl_IC_NOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_IC_NOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReadMyKadNOK))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "NEXT OF KIND", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Next of Kin Information"));

        label44.setText("PMI No.");

        tfieldPmiNextOfKin.setEditable(false);
        tfieldPmiNextOfKin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label45.setText("Relationship");

        cboxNOKRelationship.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxNOKRelationship.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select NextOfKin Relationship", "Father", "Mother", "Brother", "Sister", "Husband", "Wife" }));

        label47.setText("New IC No.");

        label48.setText("Id Type");

        tfieldNewICNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldNewICNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfieldNewICNoActionPerformed(evt);
            }
        });

        label46.setText("Date of Birth");

        cboxIdTypeNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxIdTypeNOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Type", "-", "Police", "Army", "Foreigner" }));

        label7.setText("Occupation");

        tfieldOccupationNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        label52.setText("Identification No.");

        tfieldIdNoNOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label51.setText("Old IC No.");

        tfieldOldICNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label50.setText("NextOfKin Name");

        tfieldNextOfKinName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label82.setText("Handphone No.");

        tfieldHpNoNOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label83.setText("Homephone No.");

        tfieldHomephoneNoNOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label84.setText("E_mail");

        tfieldEmailNOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tfieldNextOfKinSequenceNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldNextOfKinSequenceNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfieldNextOfKinSequenceNoUnchangeableNokSequenceNo(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldPmiNextOfKin)
                    .addComponent(cboxNOKRelationship, 0, 223, Short.MAX_VALUE)
                    .addComponent(tfieldNewICNo)
                    .addComponent(cboxIdTypeNOK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfieldDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfieldHpNoNOK)
                    .addComponent(tfieldEmailNOK))
                .addGap(21, 21, 21)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldOldICNo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldIdNoNOK, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldOccupationNOK, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldHomephoneNoNOK, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldNextOfKinName, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(419, 419, 419)
                .addComponent(tfieldNextOfKinSequenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(label44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldPmiNextOfKin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldNextOfKinSequenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(label45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxNOKRelationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldNextOfKinName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(label47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldNewICNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldOldICNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(label48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxIdTypeNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldIdNoNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(label46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldOccupationNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfieldHpNoNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfieldHomephoneNoNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfieldEmailNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Address"));

        cboxTownNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxTownNOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Town", "-", "BEDONG", "MELACCA TOWN", "JOHOR BAHRU", "SEREMBAN", "SHAH ALAM", "KUALA LUMPUR", "IPOH", "KANGAR", "ALOR SETAR", "KOTA BAHRU", "KOT KINABALU", "KUALA TERENGGANU", "KUCHING", "TAIPING", "BINTULU", "PEKAN", "VICTORIA", "PUTRAJAYA", "GEORGE TOWN" }));

        cboxCountryNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxCountryNOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Country", "MALAYSIA", "SINGAPORE", "THAILAND", "PERLIS" }));

        label78.setText("District");

        label77.setText("Address");

        cboxPostcodeNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxPostcodeNOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Postcode", "-", "08100", "75000", "75060", "76000", "81100", "80250", "97000", "34000", "70200", "26690" }));

        label79.setText("Postcode");

        jLabel77.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel77.setText("Town");

        cboxDistrictNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxDistrictNOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select District", "-", "MELAKA TENGAH", "ALOR GAJAH", "JASIN", "JOHOR BAHRU", "KOTA TINGGI", "SEGAMAT", "BINTULU", "TAIPING", "PEKAN", "SEREMBAN", "BEDONG" }));

        cboxStateNOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxStateNOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select State", "-", "JOHOR", "MELAKA", "NEGERI SEMBILAN", "KUALA LUMPUR", "SELANGOR", "PERAK", "PULAU PENANG", "KEDAH", "PERLIS", "TERENGGANU", "KELANTAN", "SABAH", "SARAWAK", "LABUAN", "PUTRAJAYA", "WILAYAH PERSEKUTUAN", "PAHANG" }));

        label81.setText("Country");

        tareaAddressNOK.setColumns(20);
        tareaAddressNOK.setRows(5);
        jScrollPane3.setViewportView(tareaAddressNOK);

        label80.setText("State");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboxCountryNOK, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxDistrictNOK, javax.swing.GroupLayout.Alignment.LEADING, 0, 191, Short.MAX_VALUE)
                            .addComponent(cboxPostcodeNOK, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxTownNOK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxStateNOK, 0, 192, Short.MAX_VALUE)))
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxDistrictNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77)
                    .addComponent(cboxTownNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxPostcodeNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxStateNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxCountryNOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSaveNOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveNOK.setText("Save");
        btnSaveNOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNOKActionPerformed(evt);
            }
        });

        btnUpdateNokInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdateNokInfo.setText("Update");
        btnUpdateNokInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNokInfoActionPerformed(evt);
            }
        });

        btnClearNOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearNOK.setText("Clear");
        btnClearNOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNOKActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Main Page");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(btnSaveNOK)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateNokInfo)
                                .addGap(187, 187, 187))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(btnClearNOK)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)))
                        .addGap(229, 229, 229))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(btnClearNOK)
                    .addComponent(btnSaveNOK)
                    .addComponent(btnUpdateNokInfo))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List of Next of Kin"));

        tbl_nok.setModel(new javax.swing.table.DefaultTableModel(
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
                "Name", "IC. No."
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
        tbl_nok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nokMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_nok);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(709, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(243, 243, 243))
        );

        tabpanel.addTab("Next Of Kin", jPanel7);

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List of Family"));

        tbl_family.setModel(new javax.swing.table.DefaultTableModel(
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
                "Family Member's Name", "Relationship"
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
        tbl_family.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_familyMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_family);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "FAMILY INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel76.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel76.setText("PMI No.");

        tfieldPmiFamily.setEditable(false);
        tfieldPmiFamily.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldPmiFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfieldPmiFamilyActionPerformed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel78.setText("Family Sequence No.");

        tfieldFamilySequenceNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfieldFamilySequenceNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UnchangeableFamilySequenceNo(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel74.setText("Family Relationship");

        cboxFamilyRelationship.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxFamilyRelationship.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Family Relationship", "Husband", "Wife", "Father", "Mother", "Brother", "Sister", "Brother in law", "Sister in law" }));
        cboxFamilyRelationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxFamilyRelationshipActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel75.setText("PMI No Family");

        jLabel79.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel79.setText("Family Member's Name");

        jLabel80.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel80.setText("Occupation");

        tfieldOccupationFamily.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        btnSaveFamilyInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveFamilyInfo.setText("Save");
        btnSaveFamilyInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFamilyInfoActionPerformed(evt);
            }
        });

        btnUpdateFamilyInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdateFamilyInfo.setText("Update");
        btnUpdateFamilyInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateFamilyInfoActionPerformed(evt);
            }
        });

        btnClearFamily.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearFamily.setText("Clear");
        btnClearFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFamilyActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setText("Main Page");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveFamilyInfo)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateFamilyInfo)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearFamily)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel78)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75)
                            .addComponent(jLabel79)
                            .addComponent(jLabel80))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfieldPmiFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfieldFamilySequenceNo)
                            .addComponent(cboxFamilyRelationship, 0, 294, Short.MAX_VALUE)
                            .addComponent(tareaFamilyMemberPMI)
                            .addComponent(tareaFamilyMemberName)
                            .addComponent(tfieldOccupationFamily))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(tfieldPmiFamily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(tfieldFamilySequenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(cboxFamilyRelationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(tareaFamilyMemberPMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(tareaFamilyMemberName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(tfieldOccupationFamily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveFamilyInfo)
                    .addComponent(btnUpdateFamilyInfo)
                    .addComponent(btnClearFamily)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(589, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(307, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(423, Short.MAX_VALUE))
        );

        tabpanel.addTab("Family", jPanel4);

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List of Medical Insurance"));

        tbl_insurance.setModel(new javax.swing.table.DefaultTableModel(
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
                "Insurance Company", "Policy No."
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
        tbl_insurance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_insuranceMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_insurance);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MEDICAL INSURANCE INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        label92.setText("PMI No.");

        tfieldPmiInsurance.setEditable(false);
        tfieldPmiInsurance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label93.setText("Insurance Company");

        cboxInsuranceCompanyName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxInsuranceCompanyName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Insurance Company", "ING Health Insurance", "Takaful Malaysia", "Great Eastern Life", "Asia Life (M) Berhad", " " }));

        label94.setText("Policy No.");

        tfieldPolicyNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        label95.setText("Maturity Date");

        label6.setText("Health Facility");

        cboxHealthFacilityInsurance.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxHealthFacilityInsurance.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Health Facility", "Hospital Sultanah Aminah Johor Bahru", "Melaka General Hospital", "Hospital Kuala Lumpur", "Hospital Ipoh", "Hospital Pekan", "Hospital Jasin" }));

        label96.setText("Policy Status");

        CboxPolicyStatus.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CboxPolicyStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Policy Status", "Active", "Terminate" }));
        CboxPolicyStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxPolicyStatusActionPerformed(evt);
            }
        });

        btnSaveInsurance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveInsurance.setText("Save");
        btnSaveInsurance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInsuranceActionPerformed(evt);
            }
        });

        btnUpdateInsurance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdateInsurance.setText("Update");
        btnUpdateInsurance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInsuranceActionPerformed(evt);
            }
        });

        btnClearInsuranceInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearInsuranceInfo.setText("Clear");
        btnClearInsuranceInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearInsuranceInfoActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton6.setText("Main Page");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tfieldmedicalseqno.setEditable(false);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboxHealthFacilityInsurance, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(tfieldMaturityDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfieldPolicyNo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel38Layout.createSequentialGroup()
                        .addComponent(tfieldPmiInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfieldmedicalseqno, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboxInsuranceCompanyName, javax.swing.GroupLayout.Alignment.LEADING, 0, 196, Short.MAX_VALUE)
                    .addComponent(CboxPolicyStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(btnSaveInsurance)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateInsurance)
                .addGap(18, 18, 18)
                .addComponent(btnClearInsuranceInfo)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldPmiInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldmedicalseqno, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxInsuranceCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldPolicyNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfieldMaturityDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxHealthFacilityInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboxPolicyStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveInsurance)
                    .addComponent(btnUpdateInsurance)
                    .addComponent(btnClearInsuranceInfo)
                    .addComponent(jButton6))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 219, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(675, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        tabpanel.addTab("Medical Insurance", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 2011, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabpanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabpanelStateChanged

        boolean tabChange = true;

        if (tabpanel.getSelectedIndex() != selectedTabIndex) {

            //first tab REG
            if (selectedTabIndex == 0) {
                System.out.println(tfieldNameRegistration.getText());

                if (!tfieldNameRegistration.getText().isEmpty() && regSaveStatus == false) {
                    tabChange = false;

                }
            } // 2nd tab PMI
            else if (selectedTabIndex == 1) {
                if (!tfieldPatientName.getText().isEmpty() && pmiSaveStatus == false) {
                    tabChange = false;
                }
            } //3rd tab EMP
            else if (selectedTabIndex == 2) {
                if (!tfieldPmiEmployment.getText().isEmpty() && empSaveStatus == false) {
                    tabChange = false;
                }
            } //4th tab NOK
            else if (selectedTabIndex == 3) {
                if (!tfieldPmiNextOfKin.getText().isEmpty() && nokSaveStatus == false) {
                    tabChange = false;
                }
            } // 5th tab FMI
            else if (selectedTabIndex == 4) {
                if (!tfieldPmiFamily.getText().isEmpty() && fmiSaveStatus == false) {
                    tabChange = false;
                }
            } //6th tab INS
            else if (selectedTabIndex == 5) {
                if (!tfieldPmiInsurance.getText().isEmpty() && insSaveStatus == false) {
                    tabChange = false;
                }
            }


            if (tabChange == true) {

                selectedTabIndex = tabpanel.getSelectedIndex();
            } else {


                int n = JOptionPane.showConfirmDialog(null,
                        "Are you sure you wan to navigate away from this tab",
                        "Confirm Dialog Options",
                        JOptionPane.YES_NO_OPTION);//Button appearing on JOptionpane

                if (n == 0) {

                    selectedTabIndex = tabpanel.getSelectedIndex();


                } else {

                    tabpanel.setSelectedIndex(selectedTabIndex);


                }
            }
        }



        System.out.println(selectedTabIndex + " tab");
    }//GEN-LAST:event_tabpanelStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        MainPage page = new MainPage();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    //clear patient medical insurance information
    private void btnClearInsuranceInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearInsuranceInfoActionPerformed
        // TODO add your handling code here:

        btnSaveInsurance.setEnabled(true);
        btnUpdateInsurance.setEnabled(false);

        //tfieldPmiInsurance.setText("");
        cboxInsuranceCompanyName.setSelectedIndex(0);
        tfieldPolicyNo.setText("");
        tfieldMaturityDate.setDate(null);
        cboxHealthFacilityInsurance.setSelectedIndex(0);
        CboxPolicyStatus.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearInsuranceInfoActionPerformed

    //go to appolintment list page        
    //update medical insurance information
    private void btnUpdateInsuranceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInsuranceActionPerformed
        // TODO add your handling code here:

        boolean update = true;
        String[] insurance = {"", "", "", "", "", "", ""};
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String maturitydate = formatter.format(tfieldMaturityDate.getDate());
        insurance[0] = tfieldPmiInsurance.getText();
        insurance[1] = tfieldmedicalseqno.getText();
        insurance[2] = cboxInsuranceCompanyName.getSelectedItem().toString();
        insurance[3] = tfieldPolicyNo.getText();
        //Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        //String maturitydate = formatter.format(tfieldMaturityDate.getDate());
        insurance[4] = maturitydate;
        insurance[5] = cboxHealthFacilityInsurance.getSelectedItem().toString();
        insurance[6] = CboxPolicyStatus.getSelectedItem().toString();


        for (int i = 0; i < 7; i++) {
            if (insurance[i].length() == 0) {
                update = false;
            }
        }

        if (update == true) {
            try {
                if (JOptionPane.showConfirmDialog(null, "Are you sure want to update information of Patient's Medical Insurance?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                    try {
                        dataINS = "INS|" + createFormat(insurance) + "\n";
                        new Patient(this).updatePatientInsuranceInfo(insurance);
                        insSaveStatus = true;
                    } catch (SQLException ex) {
                        Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Patient's Medical Insurance Information updated Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        showTableMedical();
    }//GEN-LAST:event_btnUpdateInsuranceActionPerformed

    //save patient medical insurance into database
    private void btnSaveInsuranceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveInsuranceActionPerformed
        // TODO add your handling code here:
        boolean save = true;
        String[] insuranceDetail = {"", "", "", "", "", "", ""};

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String maturitydate = "-";
        try {
            maturitydate = formatter.format(tfieldMaturityDate.getDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            maturitydate = "-";
        }

        insuranceDetail[0] = tfieldPmiInsurance.getText();
        insuranceDetail[1] = tfieldmedicalseqno.getText();
        insuranceDetail[2] = cboxInsuranceCompanyName.getSelectedItem().toString();
        insuranceDetail[3] = tfieldPolicyNo.getText();
        //Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        //String maturitydate = formatter.format(tfieldMaturityDate.getDate());
        insuranceDetail[4] = maturitydate;
        insuranceDetail[5] = cboxHealthFacilityInsurance.getSelectedItem().toString();
        insuranceDetail[6] = CboxPolicyStatus.getSelectedItem().toString();


        for (int i = 0; i < 7; i++) {
            if (insuranceDetail[i].length() == 0) {
                save = false;
            }
        }

        if (save == true) {
            try {
                if (JOptionPane.showConfirmDialog(null, "Are you sure want to save information of Patient's Medical Insurance?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                    try {
                        dataINS = "INS|" + createFormat(insuranceDetail) + "\n";
                        new Patient(this).addPatientInsuranceInfo(insuranceDetail);
                        insSaveStatus = true;
                    } catch (SQLException ex) {
                        Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Patient's Medical Insurance Information saved Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //clear saved patient's insurance information
        //tfieldPmiInsurance.setText("");
        cboxInsuranceCompanyName.setSelectedIndex(0);
        tfieldPolicyNo.setText("");
        tfieldMaturityDate.setDate(null);
        cboxHealthFacilityInsurance.setSelectedIndex(0);
        CboxPolicyStatus.setSelectedIndex(0);

        showTableMedical();
    }//GEN-LAST:event_btnSaveInsuranceActionPerformed

    //read MyKAD information into person master index form        
    //read MyKAD information using card reader
    private void CboxPolicyStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxPolicyStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboxPolicyStatusActionPerformed

    private void tbl_insuranceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_insuranceMouseClicked
        // TODO add your handling code here:

        btnSaveInsurance.setEnabled(false);
        btnUpdateInsurance.setEnabled(true);

        int index = tbl_insurance.getSelectedRow();
        String InsuranceInfo1[] = tbl_insurance_array[index];

        tfieldPmiInsurance.setText(tfieldPMINoPMI.getText());
        if (!InsuranceInfo1[1].equals("-")) {
            tfieldmedicalseqno.setText(InsuranceInfo1[1]);
        }
        cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo1[2]);
        tfieldPolicyNo.setText(InsuranceInfo1[3]);

        DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
        Date getMaturityDateInsurance = null;
        try {
            getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo1[4]);
        } catch (ParseException ex) {
            S.oln(ex.getMessage());
        }
        tfieldMaturityDate.setDate(getMaturityDateInsurance);
        cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo1[5]);
        CboxPolicyStatus.setSelectedItem(InsuranceInfo1[6]);
    }//GEN-LAST:event_tbl_insuranceMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        MainPage page = new MainPage();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    //clear patient family information
    private void btnClearFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFamilyActionPerformed
        // TODO add your handling code here:

        btnSaveFamilyInfo.setEnabled(true);
        btnUpdateFamilyInfo.setEnabled(false);

        //tfieldPmiFamily.setText("");
        //tfieldFamilySequenceNo.setText("");
        tareaFamilyMemberName.setText("");
        cboxFamilyRelationship.setSelectedIndex(0);
        tareaFamilyMemberPMI.setText("");
        tfieldOccupationFamily.setText("");

        String[] autogenerateFSNo = {};
        Patient autogenerateFS = new Patient(this);

        try {
            autogenerateFSNo = autogenerateFS.getAutogenerateFSNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldFamilySequenceNo.setText("FS" + autogenerateFSNo[0]);
    }//GEN-LAST:event_btnClearFamilyActionPerformed

    //update patient's family information
    private void btnUpdateFamilyInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateFamilyInfoActionPerformed
        // TODO add your handling code here:

        String[] family = {"", "", "", "", "", ""};

        family[0] = tfieldPmiFamily.getText();
        family[1] = tfieldFamilySequenceNo.getText();
        family[2] = cboxFamilyRelationship.getSelectedItem().toString();
        family[3] = tareaFamilyMemberPMI.getText();
        family[4] = tareaFamilyMemberName.getText();
        family[5] = tfieldOccupationFamily.getText();

        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Update patient's Family Information?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                dataFMI = "FMI|" + createFormat(family) + "\n";
                new Patient(this).updatePatientFamilyInfo(family);
                fmiSaveStatus = true;
                JOptionPane.showMessageDialog(null, "Patient Family Information Updated Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        showTableFamily();
    }//GEN-LAST:event_btnUpdateFamilyInfoActionPerformed

    //save patient family information into database
    private void btnSaveFamilyInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveFamilyInfoActionPerformed
        // TODO add your handling code here:
        boolean save = true;
        String[] saveFamilyInfo = {"", "", "", "", "", ""};

        saveFamilyInfo[0] = tfieldPmiFamily.getText();
        saveFamilyInfo[1] = new Timestamp(System.currentTimeMillis()).toString(); //tfieldFamilySequenceNo.getText();
        saveFamilyInfo[2] = cboxFamilyRelationship.getSelectedItem().toString();
        saveFamilyInfo[3] = tareaFamilyMemberPMI.getText();
        saveFamilyInfo[4] = tareaFamilyMemberName.getText();
        saveFamilyInfo[5] = tfieldOccupationFamily.getText();

        for (int i = 0; i <= 5; i++) {
            saveFamilyInfo[i] = Func.trim(saveFamilyInfo[i]);
        }

        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Save patient's Family Information?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                dataFMI = "FMI|" + createFormat(saveFamilyInfo) + "\n";
                new Patient(this).addPatientFamilyInfo(saveFamilyInfo);
                fmiSaveStatus = true;
                JOptionPane.showMessageDialog(null, "Patient Family Information Saved Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                //this.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //clear saved patient's family information
        //tfieldPmiFamily.setText("");
        //tfieldFamilySequenceNo.setText("");
        tareaFamilyMemberName.setText("");
        cboxFamilyRelationship.setSelectedIndex(0);
        tareaFamilyMemberPMI.setText("");
        tfieldOccupationFamily.setText("");

        String[] autogenerateFSNo = {};
        Patient autogenerateFS = new Patient(this);

        try {
            autogenerateFSNo = autogenerateFS.getAutogenerateFSNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldFamilySequenceNo.setText("FS" + autogenerateFSNo[0]);

        showTableFamily();
    }//GEN-LAST:event_btnSaveFamilyInfoActionPerformed

    private void cboxFamilyRelationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxFamilyRelationshipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxFamilyRelationshipActionPerformed

    /*
    //set appointment detail into textfield
    public void setSelectedAppointmentType(String selectedAppointmentType, String tab)
    {
    cboxVisitTypeRegistration.setSelectedItem(selectedAppointmentType);
    }
    
    
    //set referral detail(Using New IC) into textfield
    public void setSelectedNewIC(String selectedNewIC, String tab)
    {
    String[] ReferralInfo={};
    Patient referral  = new Patient(this);
    
    try {
    ReferralInfo = referral.getReferralBiodataUsingNewIC(selectedNewIC);
    } catch (ClassNotFoundException ex) {
    System.out.println(ex);
    } catch (SQLException ex) {
    System.out.println(ex);
    }
    
    tfieldPMINoRegistration.setText(ReferralInfo[0]);
    tfieldNameRegistration.setText(ReferralInfo[2]);
    tfieldNewICNoRegistration.setText(ReferralInfo[4]);
    tfieldOldICNoRegistration.setText(ReferralInfo[5]);
    cboxIdTypeRegistration.setSelectedItem(ReferralInfo[6]);
    tfieldIdNoRegistration.setText(ReferralInfo[7]);
    cboxEligibilityCategoryRegistration.setSelectedItem(ReferralInfo[8]);
    cboxEligibilityTypeRegistration.setSelectedItem(ReferralInfo[9]);
    cboxVisitTypeRegistration.setSelectedItem("Referral");
    if (tfieldNewICNoRegistration.getText() != null && (tfieldNewICNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("1.00");
    tfieldPaymentAmount.setText("1.00");
    }
    // if (tfieldOldICNoRegistration.getText() != null && (tfieldOldICNoRegistration.getText().length() != 0))
    //{
    //  tfieldChargeRate.setText("1.00");
    //tfieldPaymentAmount.setText("1.00");
    //}
    if (tfieldIdNoRegistration.getText() != null && (tfieldIdNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("5.00");
    tfieldPaymentAmount.setText("5.00");
    }
    }
    
    
    //set referral detail(Using Old IC) into textfield
    public void setSelectedOldIC(String selectedOldIC, String tab)
    {
    String[] ReferralInfo1={};
    Patient referralOldIC  = new Patient(this);
    
    try {
    ReferralInfo1 = referralOldIC.getReferralBiodataUsingOldIC(selectedOldIC);
    } catch (ClassNotFoundException ex) {
    System.out.println(ex);
    } catch (SQLException ex) {
    System.out.println(ex);
    }
    
    tfieldPMINoRegistration.setText(ReferralInfo1[0]);
    tfieldNameRegistration.setText(ReferralInfo1[2]);
    tfieldNewICNoRegistration.setText(ReferralInfo1[4]);
    tfieldOldICNoRegistration.setText(ReferralInfo1[5]);
    cboxIdTypeRegistration.setSelectedItem(ReferralInfo1[6]);
    tfieldIdNoRegistration.setText(ReferralInfo1[7]);
    cboxEligibilityCategoryRegistration.setSelectedItem(ReferralInfo1[8]);
    cboxEligibilityTypeRegistration.setSelectedItem(ReferralInfo1[9]);
    cboxVisitTypeRegistration.setSelectedItem("Referral");
    if (tfieldNewICNoRegistration.getText() != null && (tfieldNewICNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("1.00");
    tfieldPaymentAmount.setText("1.00");
    }
    if (tfieldOldICNoRegistration.getText() != null && (tfieldOldICNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("1.00");
    tfieldPaymentAmount.setText("1.00");
    }
    if (tfieldIdNoRegistration.getText() != null && (tfieldIdNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("5.00");
    tfieldPaymentAmount.setText("5.00");
    }
    }
    
    
    //set referral detail(Using ID) into textfield
    public void setSelectedID(String selectedID, String tab)
    {
    String[] ReferralInfo2={};
    Patient referralID  = new Patient(this);
    
    try {
    ReferralInfo2 = referralID.getReferralBiodataUsingID(selectedID);
    } catch (ClassNotFoundException ex) {
    System.out.println(ex);
    } catch (SQLException ex) {
    System.out.println(ex);
    }
    
    tfieldPMINoRegistration.setText(ReferralInfo2[0]);
    tfieldNameRegistration.setText(ReferralInfo2[2]);
    tfieldNewICNoRegistration.setText(ReferralInfo2[4]);
    tfieldOldICNoRegistration.setText(ReferralInfo2[5]);
    cboxIdTypeRegistration.setSelectedItem(ReferralInfo2[6]);
    tfieldIdNoRegistration.setText(ReferralInfo2[7]);
    cboxEligibilityCategoryRegistration.setSelectedItem(ReferralInfo2[8]);
    cboxEligibilityTypeRegistration.setSelectedItem(ReferralInfo2[9]);
    cboxVisitTypeRegistration.setSelectedItem("Referral");
    if (tfieldNewICNoRegistration.getText() != null && (tfieldNewICNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("1.00");
    tfieldPaymentAmount.setText("1.00");
    }
    if (tfieldOldICNoRegistration.getText() != null && (tfieldOldICNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("1.00");
    tfieldPaymentAmount.setText("1.00");
    }
    if (tfieldIdNoRegistration.getText() != null && (tfieldIdNoRegistration.getText().length() != 0))
    {
    tfieldChargeRate.setText("5.00");
    tfieldPaymentAmount.setText("5.00");
    }
    }
     */
    //to make family sequence number unchangeable
    private void UnchangeableFamilySequenceNo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableFamilySequenceNo
        // TODO add your handling code here:
        tfieldFamilySequenceNo.setEditable(false);
    }//GEN-LAST:event_UnchangeableFamilySequenceNo

    private void tfieldPmiFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfieldPmiFamilyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfieldPmiFamilyActionPerformed

    private void tbl_familyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_familyMouseClicked
        // TODO add your handling code here:
        btnSaveFamilyInfo.setEnabled(false);
        btnUpdateFamilyInfo.setEnabled(true);

        int index = tbl_family.getSelectedRow();
        String FamilyInfo1[] = tbl_family_array[index];

        tfieldPmiFamily.setText(tfieldPMINoPMI.getText());
        if (!FamilyInfo1[1].equals("-")) {
            tfieldFamilySequenceNo.setText(FamilyInfo1[1]);
        }
        cboxFamilyRelationship.setSelectedItem(FamilyInfo1[2]);
        tareaFamilyMemberPMI.setText(FamilyInfo1[3]);
        tareaFamilyMemberName.setText(FamilyInfo1[4]);
        tfieldOccupationFamily.setText(FamilyInfo1[5]);
    }//GEN-LAST:event_tbl_familyMouseClicked

    private void tbl_nokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nokMouseClicked
        // TODO add your handling code here:

        btnSaveNOK.setEnabled(false);
        btnUpdateNokInfo.setEnabled(true);

        int index = tbl_nok.getSelectedRow();
        String saveNextOfKinInfo[] = tbl_nok_array[index];

        tfieldPmiNextOfKin.setText(saveNextOfKinInfo[0]);
        tfieldNextOfKinSequenceNo.setText(saveNextOfKinInfo[1]);
        cboxNOKRelationship.setSelectedItem(saveNextOfKinInfo[2]);
        tfieldNextOfKinName.setText(saveNextOfKinInfo[3]); // = tfieldNextOfKinName.getText();
        tfieldNewICNo.setText(saveNextOfKinInfo[4]);// = tfieldNewICNo.getText();
        tfieldOldICNo.setText(saveNextOfKinInfo[5]);// = tfieldOldICNo.getText();
        cboxIdTypeNOK.setSelectedItem(saveNextOfKinInfo[6]);// = cboxIdTypeNOK.getSelectedItem().toString();
        tfieldIdNoNOK.setText(saveNextOfKinInfo[7]);// = tfieldIdNoNOK.getText();
        Format formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        String dobNOK = "-";
        try {
            dobNOK = formatter1.format(saveNextOfKinInfo[8]);
        } catch (Exception ex) {
            ex.printStackTrace();
            dobNOK = "-";
        }
        try {
            tfieldDOB.setDate(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(dobNOK));// = dobNOK;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        tfieldOccupationNOK.setText(saveNextOfKinInfo[9]);// = tfieldOccupationNOK.getText();
        tareaAddressNOK.setText(saveNextOfKinInfo[10]);// = tareaAddressNOK.getText();
        cboxDistrictNOK.setSelectedItem(saveNextOfKinInfo[11]);// = cboxDistrictNOK.getSelectedItem().toString();
        cboxTownNOK.setSelectedItem(saveNextOfKinInfo[12]);// = cboxTownNOK.getSelectedItem().toString();
        cboxPostcodeNOK.setSelectedItem(saveNextOfKinInfo[13]);// = cboxPostcodeNOK.getSelectedItem().toString();
        cboxStateNOK.setSelectedItem(saveNextOfKinInfo[14]);// = cboxStateNOK.getSelectedItem().toString();
        cboxCountryNOK.setSelectedItem(saveNextOfKinInfo[15]);// = cboxCountryNOK.getSelectedItem().toString();
        tfieldHpNoNOK.setText(saveNextOfKinInfo[16]);// = tfieldHpNoNOK.getText();
        tfieldHomephoneNoNOK.setText(saveNextOfKinInfo[17]);// = tfieldHomephoneNoNOK.getText();
        tfieldEmailNOK.setText(saveNextOfKinInfo[18]);// = tfieldEmailNOK.getText();
    }//GEN-LAST:event_tbl_nokMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MainPage page = new MainPage();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnClearNOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNOKActionPerformed
        // TODO add your handling code here:

        btnSaveNOK.setEnabled(true);
        btnUpdateNokInfo.setEnabled(false);

        //tfieldPmiNextOfKin.setText("");
        //tfieldNextOfKinSequenceNo.setText("");
        cboxNOKRelationship.setSelectedIndex(0);
        tfieldNextOfKinName.setText("");
        tfieldNewICNo.setText("");
        tfieldOldICNo.setText("");
        cboxIdTypeNOK.setSelectedIndex(0);
        tfieldIdNoNOK.setText("");
        tfieldDOB.setDate(null);
        tareaAddressNOK.setText("");
        cboxDistrictNOK.setSelectedIndex(0);
        cboxTownNOK.setSelectedIndex(0);
        cboxPostcodeNOK.setSelectedIndex(0);
        cboxStateNOK.setSelectedIndex(0);
        cboxCountryNOK.setSelectedIndex(0);
        tfieldHpNoNOK.setText("");
        tfieldHomephoneNoNOK.setText("");
        tfieldEmailNOK.setText("");
        tfieldOccupationNOK.setText("");

        String[] autogenerateNOKNo = {};
        Patient autogenerateNOK = new Patient(this);

        try {
            autogenerateNOKNo = autogenerateNOK.getAutogenerateNOKNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldNextOfKinSequenceNo.setText("NOKS" + autogenerateNOKNo[0]);
    }//GEN-LAST:event_btnClearNOKActionPerformed

    //update patient's next of kin information
    private void btnUpdateNokInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNokInfoActionPerformed
        // TODO add your handling code here:

        String[] NextOfKinInfo = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        NextOfKinInfo[0] = tfieldPmiNextOfKin.getText();
        NextOfKinInfo[1] = tfieldNextOfKinSequenceNo.getText();
        NextOfKinInfo[2] = cboxNOKRelationship.getSelectedItem().toString();
        NextOfKinInfo[3] = tfieldNextOfKinName.getText();
        NextOfKinInfo[4] = tfieldNewICNo.getText();
        NextOfKinInfo[5] = tfieldOldICNo.getText();
        NextOfKinInfo[6] = cboxIdTypeNOK.getSelectedItem().toString();
        NextOfKinInfo[7] = tfieldIdNoNOK.getText();
        Format formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        String dobNOK = "-";
        try {
            dobNOK = formatter1.format(tfieldDOB.getDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            dobNOK = "-";
        }
        NextOfKinInfo[8] = dobNOK;
        NextOfKinInfo[9] = tfieldOccupationNOK.getText();
        NextOfKinInfo[10] = tareaAddressNOK.getText();
        NextOfKinInfo[11] = cboxDistrictNOK.getSelectedItem().toString();
        NextOfKinInfo[12] = cboxTownNOK.getSelectedItem().toString();
        NextOfKinInfo[13] = cboxPostcodeNOK.getSelectedItem().toString();
        NextOfKinInfo[14] = cboxStateNOK.getSelectedItem().toString();
        NextOfKinInfo[15] = cboxCountryNOK.getSelectedItem().toString();
        NextOfKinInfo[16] = tfieldHpNoNOK.getText();
        NextOfKinInfo[17] = tfieldHomephoneNoNOK.getText();
        NextOfKinInfo[18] = tfieldEmailNOK.getText();

        for (int i = 0; i <= 18; i++) {
            NextOfKinInfo[i] = Func.trim(NextOfKinInfo[i]);
        }

        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Update patient's NextOfKin Information?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                dataNOK = "NOK|" + createFormat(NextOfKinInfo) + "\n";
                new Patient(this).updatePatientNextOfKinInfo(NextOfKinInfo);
                nokSaveStatus = true;
                JOptionPane.showMessageDialog(null, "Patient NextOfKin Information Updated Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        showTableNOK();
    }//GEN-LAST:event_btnUpdateNokInfoActionPerformed

    //add and save patient next of kin information into database
    private void btnSaveNOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNOKActionPerformed
        // TODO add your handling code here:
        boolean save = true;
        String[] saveNextOfKinInfo = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        saveNextOfKinInfo[0] = tfieldPmiNextOfKin.getText();
        saveNextOfKinInfo[1] = new Timestamp(System.currentTimeMillis()).toString(); //tfieldNextOfKinSequenceNo.getText();
        saveNextOfKinInfo[2] = cboxNOKRelationship.getSelectedItem().toString();
        saveNextOfKinInfo[3] = tfieldNextOfKinName.getText();
        saveNextOfKinInfo[4] = tfieldNewICNo.getText();
        saveNextOfKinInfo[5] = tfieldOldICNo.getText();
        saveNextOfKinInfo[6] = cboxIdTypeNOK.getSelectedItem().toString();
        saveNextOfKinInfo[7] = tfieldIdNoNOK.getText();
        Format formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        String dobNOK = "-";
        try {
            dobNOK = formatter1.format(tfieldDOB.getDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            dobNOK = "-";
        }
        saveNextOfKinInfo[8] = dobNOK;
        saveNextOfKinInfo[9] = tfieldOccupationNOK.getText();
        saveNextOfKinInfo[10] = tareaAddressNOK.getText();
        saveNextOfKinInfo[11] = cboxDistrictNOK.getSelectedItem().toString();
        saveNextOfKinInfo[12] = cboxTownNOK.getSelectedItem().toString();
        saveNextOfKinInfo[13] = cboxPostcodeNOK.getSelectedItem().toString();
        saveNextOfKinInfo[14] = cboxStateNOK.getSelectedItem().toString();
        saveNextOfKinInfo[15] = cboxCountryNOK.getSelectedItem().toString();
        saveNextOfKinInfo[16] = tfieldHpNoNOK.getText();
        saveNextOfKinInfo[17] = tfieldHomephoneNoNOK.getText();
        saveNextOfKinInfo[18] = tfieldEmailNOK.getText();

        for (int i = 0; i <= 18; i++) {
            saveNextOfKinInfo[i] = Func.trim(saveNextOfKinInfo[i]);
        }

        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Save patient's NextOfKin Information?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                dataNOK = "NOK|" + createFormat(saveNextOfKinInfo) + "\n";
                new Patient(this).addPatientNextOfKinInfo(saveNextOfKinInfo);
                nokSaveStatus = true;
                JOptionPane.showMessageDialog(null, "Patient NextOfKin Information Saved Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                //this.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //clear saved patient's next of kin information
        //tfieldPmiNextOfKin.setText("");
        tfieldNextOfKinSequenceNo.setText("");
        cboxNOKRelationship.setSelectedIndex(0);
        tfieldNextOfKinName.setText("");
        tfieldNewICNo.setText("");
        tfieldOldICNo.setText("");
        cboxIdTypeNOK.setSelectedIndex(0);
        tfieldIdNoNOK.setText("");
        tfieldDOB.setDate(null);
        tareaAddressNOK.setText("");
        cboxDistrictNOK.setSelectedIndex(0);
        cboxTownNOK.setSelectedIndex(0);
        cboxPostcodeNOK.setSelectedIndex(0);
        cboxStateNOK.setSelectedIndex(0);
        cboxCountryNOK.setSelectedIndex(0);
        tfieldHpNoNOK.setText("");
        tfieldHomephoneNoNOK.setText("");
        tfieldEmailNOK.setText("");
        tfieldOccupationNOK.setText("");


        generateAutogenerateNOK();
        showTableNOK();
    }//GEN-LAST:event_btnSaveNOKActionPerformed

    private void tfieldNextOfKinSequenceNoUnchangeableNokSequenceNo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfieldNextOfKinSequenceNoUnchangeableNokSequenceNo
        // TODO add your handling code here:
        tfieldNextOfKinSequenceNo.setEditable(false);
    }//GEN-LAST:event_tfieldNextOfKinSequenceNoUnchangeableNokSequenceNo

    private void tfieldNewICNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfieldNewICNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfieldNewICNoActionPerformed

    private void btnReadMyKadNOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadMyKadNOKActionPerformed
        // TODO add your handling code here:

        MyKad mykad = new MyKad();
        mykad.start();
        mykad.useJPN();
        mykad.readData();

        tfieldNextOfKinName.setText(mykad.name);
        tfieldNewICNo.setText(mykad.ic);

        DateFormat formatterPatientNOK = new SimpleDateFormat("yyyy-MM-dd");
        Date getBirthDateNOK = null;
        try {
            getBirthDateNOK = formatterPatientNOK.parse(mykad.dob);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOB.setDate(getBirthDateNOK);
        tareaAddressNOK.setText(mykad.address1 + mykad.address2 + mykad.address3);

        cboxStateNOK.setSelectedItem(mykad.state);
        cboxTownNOK.setSelectedItem(mykad.city);
        mykad.stop();
    }//GEN-LAST:event_btnReadMyKadNOKActionPerformed

    private void tbl_employMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_employMouseClicked
        // TODO add your handling code here:
        btnSaveEmployment.setEnabled(false);
        btnUpdateEmploymentInfo.setEnabled(true);

        int index = tbl_employ.getSelectedRow();
        String EmploymentInfo1[] = tbl_employ_array[index];
        tfieldPmiEmployment.setText(tfieldPMINoPMI.getText());
        if (!EmploymentInfo1[1].equals("-")) {
            tfieldEmploymentSequenceNo.setText(EmploymentInfo1[1]);
        }
        tfieldEmployerCode.setText(EmploymentInfo1[2]);
        tfieldEmployerName.setText(EmploymentInfo1[3]);
        tfieldOccupationCode.setText(EmploymentInfo1[4]);

        DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
        Date getJoinedDateEmployment = null;

        try {
            System.out.println(EmploymentInfo1[5] + "asdadasdadasdadadadadsadadadasdsdadasdasdadasdasdasdasdasda");

            getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo1[5]);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                getJoinedDateEmployment = formatterEmployment.parse("01/01/1970");
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        tfieldJoinedDate.setDate(getJoinedDateEmployment);

        cboxIncomeRange.setSelectedItem(EmploymentInfo1[6]);
        cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo1[7]);

        Date getCreatedDateEmployment = null;
        try {
            getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo1[8]);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                getCreatedDateEmployment = formatterEmployment.parse("01/01/1970");
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

        tfieldCreatedDate.setDate(getCreatedDateEmployment);
        cboxEmploymentStatus.setSelectedItem(EmploymentInfo1[9]);
    }//GEN-LAST:event_tbl_employMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        MainPage page = new MainPage();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnClearEmploymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearEmploymentActionPerformed
        // TODO add your handling code here:

        btnSaveEmployment.setEnabled(true);
        btnUpdateEmploymentInfo.setEnabled(false);

        //tfieldPmiEmployment.setText("");
        //tfieldEmploymentSequenceNo.setText("");
        tfieldEmployerCode.setText("");
        tfieldEmployerName.setText("");
        tfieldOccupationCode.setText("");
        tfieldJoinedDate.setDate(null);
        tfieldCreatedDate.setDate(null);
        cboxHealthFacilityEmployment.setSelectedIndex(0);
        cboxIncomeRange.setSelectedIndex(0);
        cboxEmploymentStatus.setSelectedIndex(0);

        String[] autogenerateESNo = {};
        Patient autogenerateES = new Patient(this);

        try {
            autogenerateESNo = autogenerateES.getAutogenerateESNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldEmploymentSequenceNo.setText("ES" + autogenerateESNo[0]);
    }//GEN-LAST:event_btnClearEmploymentActionPerformed

    //update patient's employment information
    private void btnUpdateEmploymentInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateEmploymentInfoActionPerformed
        // TODO add your handling code here:

        String[] EmploymentInfo = {"", "", "", "", "", "", "", "", "", ""};
        Format formatter3 = new SimpleDateFormat("dd/MM/yyyy");
        String createdate = formatter3.format(tfieldCreatedDate.getDate());
        String joineddate = formatter3.format(tfieldJoinedDate.getDate());
        EmploymentInfo[0] = tfieldPmiEmployment.getText();
        EmploymentInfo[1] = tfieldEmploymentSequenceNo.getText();
        EmploymentInfo[2] = tfieldEmployerCode.getText();
        EmploymentInfo[3] = tfieldEmployerName.getText();
        EmploymentInfo[4] = tfieldOccupationCode.getText();
        EmploymentInfo[5] = joineddate;
        EmploymentInfo[6] = cboxIncomeRange.getSelectedItem().toString();
        EmploymentInfo[7] = cboxHealthFacilityEmployment.getSelectedItem().toString();
        EmploymentInfo[8] = createdate;
        EmploymentInfo[9] = cboxEmploymentStatus.getSelectedItem().toString();

        for (int i = 0; i <= 9; i++) {
            EmploymentInfo[i] = Func.trim(EmploymentInfo[i]);
        }

        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Update patient's Employment Information?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                dataEMP = "EMP|" + createFormat(EmploymentInfo) + "\n";
                System.out.println(dataEMP);
                new Patient(this).updatePatientEmploymentInfo(EmploymentInfo);
                empSaveStatus = true;
                JOptionPane.showMessageDialog(null, "Patient Employment Information Updated Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                //this.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        showTableEmploy();
    }//GEN-LAST:event_btnUpdateEmploymentInfoActionPerformed

    //add and save patient employment information into database
    private void btnSaveEmploymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEmploymentActionPerformed
        // TODO add your handling code here:
        boolean save = true;
        String[] saveEmploymentInfo = {"", "", "", "", "", "", "", "", "", ""};

        Format formatter3 = new SimpleDateFormat("dd/MM/yyyy");
        String createdate = "-";
        String joineddate = "-";
        try {
            createdate = formatter3.format(tfieldCreatedDate.getDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            createdate = "-";
        }
        try {
            joineddate = formatter3.format(tfieldJoinedDate.getDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            joineddate = "-";
        }

        saveEmploymentInfo[0] = tfieldPmiEmployment.getText();
        saveEmploymentInfo[1] = new Timestamp(System.currentTimeMillis()).toString(); //tfieldEmploymentSequenceNo.getText();
        saveEmploymentInfo[2] = tfieldEmployerCode.getText();
        saveEmploymentInfo[3] = tfieldEmployerName.getText();
        saveEmploymentInfo[4] = tfieldOccupationCode.getText();
        //Format formatter3 = new SimpleDateFormat("dd/MM/yyyy");
        //String joineddate = formatter3.format(tfieldJoinedDate.getDate());
        saveEmploymentInfo[5] = joineddate;
        //Format formatter4 = new SimpleDateFormat("dd/MM/yyyy");
        //String createdate = formatter4.format(tfeildCreatedDate.getDate());
        saveEmploymentInfo[6] = cboxIncomeRange.getSelectedItem().toString();
        saveEmploymentInfo[7] = cboxHealthFacilityEmployment.getSelectedItem().toString();
        saveEmploymentInfo[8] = createdate;
        saveEmploymentInfo[9] = cboxEmploymentStatus.getSelectedItem().toString();

        for (int i = 0; i <= 9; i++) {
            saveEmploymentInfo[i] = Func.trim(saveEmploymentInfo[i]);
        }

        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Save patient's Employment Information?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                dataEMP = "EMP" + createFormat(saveEmploymentInfo) + "\n";
                new Patient(this).addPatientEmploymentInfo(saveEmploymentInfo);
                empSaveStatus = true;
                JOptionPane.showMessageDialog(null, "Patient Employment Information Saved Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                //this.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //clear saved patient's employment detail
        //tfieldPmiEmployment.setText("");
        //tfieldEmploymentSequenceNo.setText("");
        tfieldEmployerCode.setText("");
        tfieldEmployerName.setText("");
        tfieldOccupationCode.setText("");
        tfieldJoinedDate.setDate(null);
        tfieldCreatedDate.setDate(null);
        cboxHealthFacilityEmployment.setSelectedIndex(0);
        cboxIncomeRange.setSelectedIndex(0);
        cboxEmploymentStatus.setSelectedIndex(0);


        String[] autogenerateESNo = {};
        Patient autogenerateES = new Patient(this);

        try {
            autogenerateESNo = autogenerateES.getAutogenerateESNo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tfieldEmploymentSequenceNo.setText("ES" + autogenerateESNo[0]);

        showTableEmploy();
    }//GEN-LAST:event_btnSaveEmploymentActionPerformed

    //make employment sequence no unchangable
    private void UnchangableESNO(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangableESNO
        // TODO add your handling code here:
        tfieldEmploymentSequenceNo.setEditable(false);
    }//GEN-LAST:event_UnchangableESNO

    //close patient registration form
    private void btnClosePatientBiodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClosePatientBiodataActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnClosePatientBiodataActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        MainPage page = new MainPage();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnClearPatientBiodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearPatientBiodataActionPerformed
        // TODO add your handling code here:
        //tfieldPMINoPMI.setText("");
        tfieldTempPMINo.setText("");
        tfieldPatientName.setText("");
        cboxTitle.setSelectedIndex(0);
        tfieldNewICNoPatient.setText("");
        tfieldOldICNoPatient.setText("");
        cboxIdTypePatient.setSelectedIndex(0);
        tfieldIdNoPatient.setText("");
        cboxEligibilityCategoryPatient.setSelectedIndex(0);
        cboxEligibilityTypePatient.setSelectedIndex(0);
        tfieldDOBPatient.setDate(null);
        cboxSexPatient.setSelectedIndex(0);
        cboxRacePatient.setSelectedIndex(0);
        cboxNationalityPatient.setSelectedIndex(0);
        cboxReligionPatient.setSelectedIndex(0);
        cboxBloodTypePatient.setSelectedIndex(0);
        cboxBloodRhesusPatient.setSelectedIndex(0);
        cboxMaritalStatusPatient.setSelectedIndex(0);
        cboxAllergyPatient.setSelectedIndex(0);
        cboxOrganDonorPatient.setSelectedIndex(0);
        cboxChronicDiseasePatient.setSelectedIndex(0);
        tfieldHomeAddress.setText("");
        cboxDistrictCode.setSelectedIndex(0);
        cboxTownCode.setSelectedIndex(0);
        cboxPostcode.setSelectedIndex(0);
        cboxStatePatient.setSelectedIndex(0);
        cboxCountryPatient.setSelectedIndex(0);
        tfieldHomephonePatient.setText("");
        tfieldPostalAddressPatient.setText("");
        cboxPostalDistrict.setSelectedIndex(0);
        cboxPostalTown.setSelectedIndex(0);
        cboxPostalPostcode.setSelectedIndex(0);
        cboxPostalStatePatient.setSelectedIndex(0);
        cboxPostalCountryPatient.setSelectedIndex(0);
        tfieldHandphoneNoPatient.setText("");

        String[] autogeneratePMI = {};
        Patient autogenerate = new Patient(this);

        try {
            autogeneratePMI = autogenerate.getAutogeneratePMI();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfieldPMINoPMI.setText("PMS" + autogeneratePMI[0]);
    }//GEN-LAST:event_btnClearPatientBiodataActionPerformed

    //update patient biodata
    private void btnUpdatePatientBiodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePatientBiodataActionPerformed
        // TODO add your handling code here:

        String[] PatientBiodata = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        PatientBiodata[0] = tfieldPMINoPMI.getText();                                   // pmi no
        PatientBiodata[1] = tfieldTempPMINo.getText();                                  // temp pmi
        PatientBiodata[2] = tfieldPatientName.getText();                                // pat name
        PatientBiodata[3] = cboxTitle.getSelectedItem().toString();                     // pat title
        PatientBiodata[4] = tfieldNewICNoPatient.getText();                             // pat new ic
        PatientBiodata[5] = tfieldOldICNoPatient.getText();                             // pat old ic
        PatientBiodata[6] = cboxIdTypePatient.getSelectedItem().toString();             // Id type
        PatientBiodata[7] = tfieldIdNoPatient.getText();                                // ID type number
        PatientBiodata[8] = cboxEligibilityCategoryPatient.getSelectedItem().toString();
        PatientBiodata[9] = cboxEligibilityTypePatient.getSelectedItem().toString();
        Format formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String dobPMI = formatter2.format(tfieldDOBPatient.getDate());
        PatientBiodata[10] = dobPMI;
        PatientBiodata[11] = cboxSexPatient.getSelectedItem().toString();
        PatientBiodata[12] = cboxMaritalStatusPatient.getSelectedItem().toString();
        PatientBiodata[13] = cboxRacePatient.getSelectedItem().toString();
        PatientBiodata[14] = cboxNationalityPatient.getSelectedItem().toString();
        PatientBiodata[15] = cboxReligionPatient.getSelectedItem().toString();
        PatientBiodata[16] = cboxBloodTypePatient.getSelectedItem().toString();
        PatientBiodata[17] = cboxBloodRhesusPatient.getSelectedItem().toString();
        PatientBiodata[18] = cboxAllergyPatient.getSelectedItem().toString();
        PatientBiodata[19] = cboxChronicDiseasePatient.getSelectedItem().toString();
        PatientBiodata[20] = cboxOrganDonorPatient.getSelectedItem().toString();
        PatientBiodata[21] = tfieldHomeAddress.getText();
        PatientBiodata[22] = cboxDistrictCode.getSelectedItem().toString();
        PatientBiodata[23] = cboxTownCode.getSelectedItem().toString();
        PatientBiodata[24] = cboxPostcode.getSelectedItem().toString();
        PatientBiodata[25] = cboxStatePatient.getSelectedItem().toString();
        PatientBiodata[26] = cboxCountryPatient.getSelectedItem().toString();
        PatientBiodata[27] = tfieldHomephonePatient.getText();
        PatientBiodata[28] = tfieldPostalAddressPatient.getText();
        PatientBiodata[29] = cboxPostalDistrict.getSelectedItem().toString();
        PatientBiodata[30] = cboxPostalTown.getSelectedItem().toString();
        PatientBiodata[31] = cboxPostalPostcode.getSelectedItem().toString();
        PatientBiodata[32] = cboxPostalStatePatient.getSelectedItem().toString();
        PatientBiodata[33] = cboxPostalCountryPatient.getSelectedItem().toString();
        PatientBiodata[34] = tfieldHandphoneNoPatient.getText();

        //String patMSG = createFormat(PatientBiodata);

        String patientInfo = "PDI|" + PatientBiodata[0] + "|" + PatientBiodata[2] + "|" + PatientBiodata[5] + "|" + PatientBiodata[4] + "|"
                + PatientBiodata[6] + "|" + PatientBiodata[7] + "|" + PatientBiodata[11] + "|" + PatientBiodata[10] + "|" + PatientBiodata[13]
                + "|" + PatientBiodata[12] + "|" + PatientBiodata[15] + "|" + PatientBiodata[14] + "|" + PatientBiodata[21] + "|" + PatientBiodata[28]
                + "|" + PatientBiodata[23] + "|" + PatientBiodata[22] + "|" + PatientBiodata[25] + "|" + PatientBiodata[26] + "|" + PatientBiodata[24] + "|"
                + PatientBiodata[27] + "|" + PatientBiodata[34] + "|" + "<cr>" + "\n";

        System.out.println("looped data" + dataPDI);
        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to Update patient's biodata?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                //RMI Code
                /*
                 * UpdatePatientRMI upC = new UpdatePatientRMI();
                 * upC.UpdatePatCentral(data, PatientBiodata[14]);
                 */

                /*
                 * // JMS Code SenderJMS sendJMS= new SenderJMS();
                sendJMS.SendQue(data);
                 */

                /*
                 * testing HL7
                 */
                dataPDI = "PDI|" + createFormat(PatientBiodata) + "\n";
                MessageFormatter msgFm = new MessageFormatter();
                String HL7msg = msgFm.buildMSG(dataPDI, "T12108", "PMS", "HUTeM", "HUTeM", "Eric", "Registraion", "Registration");
                System.out.println("HL7 == " + HL7msg);

                if(new Patient(this).updatePatientBiodata(PatientBiodata)) {
                    pmiSaveStatus = true;
                    JOptionPane.showMessageDialog(null, "Patient Biodata Updated Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    pmiSaveStatus = false;
                    J.o("Failed", "Patient Biodata Updated Failed!", 0);
                }

            }
            //            } catch (JMSException ex) {
            //            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdatePatientBiodataActionPerformed

    //add and save patient biodata into database
    private void btnSavePatientBiodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePatientBiodataActionPerformed
        // TODO add your handling code here:
        boolean save = true;
        String[] savePatientBiodata = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        //J.o("Test", "Tarikh: "+tfieldDOBPatient.getDate(), 1);
        //define ArrayList to hold Integer objects

        savePatientBiodata[0] = tfieldPMINoPMI.getText();
        tfieldPMINoRegistration.setText(tfieldPMINoPMI.getText());
        savePatientBiodata[1] = tfieldTempPMINo.getText();
        savePatientBiodata[2] = tfieldPatientName.getText();
        tfieldNameRegistration.setText(tfieldPatientName.getText());
        savePatientBiodata[3] = cboxTitle.getSelectedItem().toString();
        savePatientBiodata[4] = tfieldNewICNoPatient.getText();
        tfieldNewICNoRegistration.setText(tfieldNewICNoPatient.getText());
        savePatientBiodata[5] = tfieldOldICNoPatient.getText();
        tfieldOldICNoRegistration.setText(tfieldOldICNoPatient.getText());
        savePatientBiodata[6] = cboxIdTypePatient.getSelectedItem().toString();
        savePatientBiodata[7] = tfieldIdNoPatient.getText();
        savePatientBiodata[8] = cboxEligibilityCategoryPatient.getSelectedItem().toString();
        cboxEligibilityCategoryRegistration.setSelectedItem(cboxEligibilityCategoryPatient.getSelectedItem().toString());
        savePatientBiodata[9] = cboxEligibilityTypePatient.getSelectedItem().toString();
        cboxEligibilityTypeRegistration.setSelectedItem(cboxEligibilityTypePatient.getSelectedItem().toString());
        Format formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String dobPMI = "-";
        try {
            dobPMI = formatter2.format(tfieldDOBPatient.getDate());
        } catch (Exception ex) {
            ex.printStackTrace();
            dobPMI = "-";
        }
        //String dobPMI = tfieldDOBPatient.getDate() == null ? "-" : tfieldDOBPatient.getDate().toString();
        savePatientBiodata[10] = dobPMI;
        savePatientBiodata[11] = cboxSexPatient.getSelectedItem().toString();
        savePatientBiodata[12] = cboxMaritalStatusPatient.getSelectedItem().toString();
        savePatientBiodata[13] = cboxRacePatient.getSelectedItem().toString();
        savePatientBiodata[14] = cboxNationalityPatient.getSelectedItem().toString();
        savePatientBiodata[15] = cboxReligionPatient.getSelectedItem().toString();
        savePatientBiodata[16] = cboxBloodTypePatient.getSelectedItem().toString();
        savePatientBiodata[17] = cboxBloodRhesusPatient.getSelectedItem().toString();
        savePatientBiodata[18] = cboxAllergyPatient.getSelectedItem().toString();
        savePatientBiodata[19] = cboxChronicDiseasePatient.getSelectedItem().toString();
        savePatientBiodata[20] = cboxOrganDonorPatient.getSelectedItem().toString();
        savePatientBiodata[21] = tfieldHomeAddress.getText();
        savePatientBiodata[22] = cboxDistrictCode.getSelectedItem().toString();
        savePatientBiodata[23] = cboxTownCode.getSelectedItem().toString();
        savePatientBiodata[24] = cboxPostcode.getSelectedItem().toString();
        savePatientBiodata[25] = cboxStatePatient.getSelectedItem().toString();
        savePatientBiodata[26] = cboxCountryPatient.getSelectedItem().toString();
        savePatientBiodata[27] = tfieldHomephonePatient.getText();
        savePatientBiodata[28] = tfieldPostalAddressPatient.getText();
        savePatientBiodata[29] = cboxPostalDistrict.getSelectedItem().toString();
        savePatientBiodata[30] = cboxPostalTown.getSelectedItem().toString();
        savePatientBiodata[31] = cboxPostalPostcode.getSelectedItem().toString();
        savePatientBiodata[32] = cboxPostalStatePatient.getSelectedItem().toString();
        savePatientBiodata[33] = cboxPostalCountryPatient.getSelectedItem().toString();
        savePatientBiodata[34] = tfieldHandphoneNoPatient.getText();

        if (dobPMI.equals("-")) {

            J.o("Don't Blank!", "Please insert your Date of Birth!", 0);
            tfieldDOBPatient.requestFocusInWindow();

        } else {

            for (int i = 0; i <= 34; i++) {
                savePatientBiodata[i] = Func.trim(savePatientBiodata[i]);
            }

            try {
                if (JOptionPane.showConfirmDialog(null, "Are you sure want to SAVE patient's biodata?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {

                    if (new Patient(this).addPatientBiodata(savePatientBiodata)) {
                        dataFMI = " PDI|" + createFormat(savePatientBiodata) + "\n";
                        pmiSaveStatus = true;
                        JOptionPane.showMessageDialog(null, "Patient Biodata Saved Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        pmiSaveStatus = false;
                        JOptionPane.showMessageDialog(null, "Patient Biodata Not Saved!", "Failed!!", 0);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //clear saved patient biodata
            tfieldPMINoPMI.setText("");
            tfieldTempPMINo.setText("");
            tfieldPatientName.setText("");
            cboxTitle.setSelectedIndex(0);
            tfieldNewICNoPatient.setText("");
            tfieldOldICNoPatient.setText("");
            cboxIdTypePatient.setSelectedIndex(0);
            tfieldIdNoPatient.setText("");
            cboxEligibilityCategoryPatient.setSelectedIndex(0);
            cboxEligibilityTypePatient.setSelectedIndex(0);
            tfieldDOBPatient.setDate(null);
            cboxSexPatient.setSelectedIndex(0);
            cboxRacePatient.setSelectedIndex(0);
            cboxNationalityPatient.setSelectedIndex(0);
            cboxReligionPatient.setSelectedIndex(0);
            cboxBloodTypePatient.setSelectedIndex(0);
            cboxBloodRhesusPatient.setSelectedIndex(0);
            cboxMaritalStatusPatient.setSelectedIndex(0);
            cboxAllergyPatient.setSelectedIndex(0);
            cboxOrganDonorPatient.setSelectedIndex(0);
            cboxChronicDiseasePatient.setSelectedIndex(0);
            tfieldHomeAddress.setText("");
            cboxDistrictCode.setSelectedIndex(0);
            cboxTownCode.setSelectedIndex(0);
            cboxPostcode.setSelectedIndex(0);
            cboxStatePatient.setSelectedIndex(0);
            cboxCountryPatient.setSelectedIndex(0);
            tfieldHomephonePatient.setText("");
            tfieldPostalAddressPatient.setText("");
            cboxPostalDistrict.setSelectedIndex(0);
            cboxPostalTown.setSelectedIndex(0);
            cboxPostalPostcode.setSelectedIndex(0);
            cboxPostalStatePatient.setSelectedIndex(0);
            cboxPostalCountryPatient.setSelectedIndex(0);
            tfieldHandphoneNoPatient.setText("");

//            String[] autogeneratePMI = {};
//            Patient autogenerate = new Patient(this);
//
//            try {
//                autogeneratePMI = autogenerate.getAutogeneratePMI();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            tfieldPMINoPMI.setText("PMS" + autogeneratePMI[0]);

            //Set tab to Registration
            tabpanel.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnSavePatientBiodataActionPerformed

    private void cboxPostalStatePatientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxPostalStatePatientMouseEntered
        if (cboxPostalStatePatient.getSelectedItem() != null) {
            System.out.println("loaded");

        } else {

            LookupController CBState = new LookupController();
            UniCBmodel = new DefaultComboBoxModel();
            try {
                UniCBmodel = CBState.getLookupReferences("0002", "State", false);
                cboxPostalStatePatient.setModel(UniCBmodel);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboxPostalStatePatientMouseEntered

    //to make patient old ic unchangeable
    private void UnchangeableOldIcBiodata(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableOldIcBiodata
        // TODO add your handling code here:
        //tfieldOldICNoPatient.setEditable(false);
    }//GEN-LAST:event_UnchangeableOldIcBiodata

    //to make patient new ic unchangeable
    private void UnchangeableNewICBiodata(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableNewICBiodata
        // TODO add your handling code here:
        //tfieldNewICNoPatient.setEditable(false);
    }//GEN-LAST:event_UnchangeableNewICBiodata

    //to make name unchangeable
    private void UnchangeableNameBiodata(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableNameBiodata
        // TODO add your handling code here:
        //tfieldPatientName.setEditable(false);
    }//GEN-LAST:event_UnchangeableNameBiodata

    //to make pmino unchangeable
    private void UnchangeablePmiBiodata(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeablePmiBiodata
        // TODO add your handling code here:
        tfieldPMINoPMI.setEditable(false);
    }//GEN-LAST:event_UnchangeablePmiBiodata

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MainPage page = new MainPage();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnClearRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRegistrationActionPerformed
        // TODO add your handling code here:
        tfieldPMINoRegistration.setText("");
        tfieldNameRegistration.setText("");
        tfieldNewICNoRegistration.setText("");
        tfieldOldICNoRegistration.setText("");
        cboxIdTypeRegistration.setSelectedIndex(0);
        tfieldIdNoRegistration.setText("");
        tfieldRegistrationNo.setText("");
        cboxPatientCategoryRegistration.setSelectedIndex(0);
        cboxVisitTypeRegistration.setSelectedIndex(0);
        cboxEmergencyTypeRegistration.setSelectedIndex(0);
        cboxEligibilityCategoryRegistration.setSelectedIndex(0);
        cboxEligibilityTypeRegistration.setSelectedIndex(0);
        cboxDisciplineRegistration.setSelectedIndex(0);
        cboxConsultationRoom.setSelectedIndex(0);
        cboxQueue.setSelectedIndex(0);
        cboxDoctor.setSelectedIndex(0);
        cboxPriorityGroupRegistration.setSelectedIndex(0);
        tfieldChargeRate.setText("");
        tfieldPaymentAmount.setText("");
        //tfieldReceiptNo.setText("");
        cboxPaymentMode.setSelectedIndex(0);
        rbConsultationRoom.setSelected(true);
        cboxConsultationRoom.setEnabled(true);
        cboxDoctor.setEnabled(false);
        cboxQueue.setEnabled(false);
    }//GEN-LAST:event_btnClearRegistrationActionPerformed

    //go to print receipt page
    private void btnPrintReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReceiptActionPerformed
        // TODO add your handling code here:
        //new PrintReceipt().setVisible(true);

        /*
         * PrinterJob job = PrinterJob.getPrinterJob(); boolean doPrint =
         * job.printDialog(); if(doPrint) { try { job.print();
         * }catch(PrinterException e) { System.out.print(e); }
            }
         */


        //save data into txt
        File file = new File("C:/Users/WC/Desktop/testing/A1.txt");

        if (file.exists()) {

            try {
                FileWriter fw = new FileWriter(file); //not (file, true) to overwrite
                //fw.flush();
                //fw.flush();

                //fw.append("\n                                    Receipt                                     ");
                //fw.append("\n--------------------------------------------------------------------------------\n");

                fw.write("------------------------------------------");
                fw.flush();
                fw.append("\r\n          Clinic DR. Khanapi        \n");
                fw.append("\r\n    Lebuh Ayer Keroh, Ayer Keroh, 78000   \n");
                fw.append("\r\n           Melaka, Malaysia              \n");
                fw.append("\r\n           TEL: 06-77777777              \r\n");
                fw.append("------------------------------------------\r\n");

                String pmi = tfieldPMINoRegistration.getText();
                fw.append("\r\nPMI No         : " + pmi + "\n");

                String name = tfieldNameRegistration.getText();
                fw.append("\r\nPatient Name   : " + name + "\n");

                String chargeRate = tfieldChargeRate.getText();
                fw.append("\r\nCharge Rate    : RM " + chargeRate + "\n");

                String paymentAmount = tfieldPaymentAmount.getText();
                fw.append("\r\nPayment Amount : RM " + paymentAmount + "\n");

                String paymentMode = cboxPaymentMode.getSelectedItem().toString();
                fw.append("\r\nPayment Mode   : " + paymentMode + "\n");

                String receiptNo = tfieldReceiptNo.getText();
                fw.append("\r\nReceipt No     : " + receiptNo + "\n");

                Calendar today = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String now = dateFormat.format(today.getTime());
                fw.append("\r\nDate           : " + now + " \n");


                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
                String now1 = dateFormat1.format(today.getTime());
                fw.append("\r\nTime           : " + now1 + " \r\n");
                fw.append("\r\n------------------------------------------\r\n");
                fw.append("            Thank You               \r\n");
                fw.append("------------------------------------------\r\n");

                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //print
            String text = null;
            String command = "C:/Users/WC/Desktop/testing/a.exe"; //exe,bat文件名OR DOS命令
            try {
                Process proc = Runtime.getRuntime().exec("notepad C:/Users/WC/Desktop/testing/A1.txt");
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                while ((text = in.readLine()) != null) {
                    System.out.println(text);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }



            //save receipt no into database
            String[] saveReceiptNo = {""};
            saveReceiptNo[0] = tfieldReceiptNo.getText();
            try {
                new Patient(this).addReceiptNo(saveReceiptNo);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }

            //autogenerate receipt no from database
            String[] autogenerateRecNo = {};
            Patient autogenerateRec = new Patient(this);

            try {
                autogenerateRecNo = autogenerateRec.getAutogenerateRecNo();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }

            tfieldReceiptNo.setText("REC" + autogenerateRecNo[0]);

        }

    }//GEN-LAST:event_btnPrintReceiptActionPerformed

    //go to appolintment list page
    private void btnAppointmentListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppointmentListActionPerformed
        // TODO add your handling code here:
        //new AppointmentList().setVisible(true);
        apptlistPage.setState(apptlistPage.NORMAL);
        apptlistPage.toFront();
        apptlistPage.setVisible(true);
    }//GEN-LAST:event_btnAppointmentListActionPerformed

    //go to referral detail page
    private void btnReferralDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReferralDetailsActionPerformed
        // TODO add your handling code here:
        referralPage.setState(referralPage.NORMAL);
        referralPage.toFront();
        referralPage.setVisible(true);

        // new ReferralDetail().setVisible(true);
    }//GEN-LAST:event_btnReferralDetailsActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:

        setDefault();
        
        boolean register = true;
        String[] registerPatient = {"", "", "", "", "", "", "", "", "", "", "", 
            "", "", "", "", "", "", "", "", "", "", 
            "", "", "", "", "", "", "", "", ""};
        String pmiNo = tfieldPMINoRegistration.getText();
        if (pmiNo.equals("") || pmiNo.equals("-") || pmiNo.equals(" ")) {
            J.o("Blank Information", "Please use a proper PMI No.!", 0);
            return;
        }

        registerPatient[0] = tfieldPMINoRegistration.getText();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String now = dateFormat.format(today.getTime());
        registerPatient[1] = now;
        registerPatient[2] = tfieldNameRegistration.getText();
        registerPatient[3] = tfieldNewICNoRegistration.getText();
        registerPatient[4] = tfieldOldICNoRegistration.getText();
        registerPatient[5] = cboxIdTypeRegistration.getSelectedItem().toString();
        registerPatient[6] = tfieldIdNoRegistration.getText();
        registerPatient[7] = tfieldRegistrationNo.getText();
        registerPatient[8] = cboxPatientCategoryRegistration.getSelectedItem().toString();
        registerPatient[9] = cboxVisitTypeRegistration.getSelectedItem().toString();
        registerPatient[10] = cboxEmergencyTypeRegistration.getSelectedItem().toString();
        registerPatient[11] = cboxEligibilityCategoryRegistration.getSelectedItem().toString();
        registerPatient[12] = cboxEligibilityTypeRegistration.getSelectedItem().toString();
        registerPatient[13] = cboxDisciplineRegistration.getSelectedItem().toString();


        if (rbConsultationRoom.isSelected()) {
            registerPatient[15] = cboxConsultationRoom.getSelectedItem().toString();
        } else if (rbQueue.isSelected()) {
            registerPatient[16] = cboxQueue.getSelectedItem().toString();
        } else if (rbDoctor.isSelected()) {
            registerPatient[17] = cboxDoctor.getSelectedItem().toString();
        }

        registerPatient[18] = cboxPriorityGroupRegistration.getSelectedItem().toString();

//            if (rbYesPolice.isSelected()) {
//                registerPatient[19] = rbYesPolice.getText();
//            } else {
//                registerPatient[19] = rbNoPolice.getText();
//            }

//            registerPatient[20] = cboxCommunicableDiseaseRegistration.getSelectedItem().toString();
//            registerPatient[21] = cboxNaturalDisasterRegistration.getSelectedItem().toString();
//            registerPatient[22] = cboxDocumentTypeRegistration.getSelectedItem().toString();


//            if (rbYesChildGuardian.isSelected()) {
//                registerPatient[23] = rbYesChildGuardian.getText();
//            } else {
//                registerPatient[23] = rbNoChildGuardian.getText();
//            }

//            registerPatient[24] = tfieldReferenceNo.getText();
//            registerPatient[25] = tfieldGroupGuardian.getText();


        Format formatterGL = new SimpleDateFormat("dd/MM/yyyy");
//            if (cboxDocumentTypeRegistration.getSelectedItem().toString().equals("GL")) {
//                String GL = formatterGL.format(tfieldGLExpiryDate.getDate());
//                registerPatient[26] = GL;
//            }

        //registerPatient[27] = cboxHealthFacility.getSelectedItem().toString();
        Calendar today1 = Calendar.getInstance();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(" HH:mm:ss");

        String now1 = "-";
        try {
            now1 = dateFormat1.format(today.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
            now1 = "-";
        }

        registerPatient[27] = now1;
        registerPatient[28] = "Waiting";
        registerPatient[29] = Session.getHfc_code();

        for (int i = 0; i <= 28; i++) {
            registerPatient[i] = Func.trim(registerPatient[i]);
        }

        boolean stat1 = false;

        if ((rbConsultationRoom.isSelected() && isFill(registerPatient[15]))
                || (rbQueue.isSelected() && isFill(registerPatient[16]))
                || (rbDoctor.isSelected() && isFill(registerPatient[17]))) {
            stat1 = true;
        }

        if (!stat1 || !(isFill(registerPatient[8]) && isFill(registerPatient[9])
                && isFill(registerPatient[13]) && isFill(registerPatient[18]))) {

            colorCompulsory();
            J.o("Error!", "Please fill in the compulsory field!", 0);

        } else {
            try {
                if (JOptionPane.showConfirmDialog(null, "Are you sure want to REGISTER PATIENT?", "", JOptionPane.OK_CANCEL_OPTION) == 0) {
                    new Patient(this).registerAndCreateQueue(registerPatient);

                    colorCompulsory();

//                    LongRunProcess.check_network();
//
//                    if (Session.getPrev_stat()) {
//                        //formatter
//                        String maintainPat = dataPDI + dataEMP + dataNOK + dataFMI + dataINS;
//                        MessageFormatter msgFm = new MessageFormatter();
//                        String HL7msg = msgFm.buildMSG(maintainPat, "T12108", "PMS", "HUTeM", "HUTeM", "Eric", "Registraion", "Registration");
//                        System.out.println("HL7 == " + HL7msg);
//
//                        //Friza : Insert to Server
//                        try {
//                            // fire to server port 1099
//                            ArrayList<String> listOnline = Func.readXML("online");
//                            Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                            // search for myMessage service
//                            Message impl = (Message) myRegistry.lookup("myMessage");
//
//                            // call server's method	
//                            //String pmi = "PMS10003";
//                            //String IC = "891031075331";
//
//                            System.out.println("Insert PMS - PMI : " + tfieldPMINoRegistration.getText());
//
//                            String str = impl.insertPMS(HL7msg);
//                            System.out.println("Output : " + str);
//                            System.out.println("Message Sent");
//
//                        } catch (Exception ex) {
//                            JOptionPane.showMessageDialog(null,
//                                    ex.getMessage(),
//                                    "RMI Error", JOptionPane.ERROR_MESSAGE);
//                            ex.printStackTrace();
//                        }
//                    }
//                    Session.setPrev_stat(false);
//                    Session.setCurr_stat(false);
//                    Session.setCon_x();

                    JOptionPane.showMessageDialog(null, "Patient Has Been Register Successfully", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    setDefault();
                    if (cboxVisitTypeRegistration.getSelectedItem().toString().equals("Referral")) {
                        Referral.deleteEmailReferral(registerPatient[2]);
                    }
                    if (cboxVisitTypeRegistration.getSelectedItem().toString().equals("Appointment")) {
                        Appointment.deleteAppointment(registerPatient[0]);
                    }
                }
            } catch (Exception ex) {
                setDefault();
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "RMI Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            /*
             * //clear registered detail tfieldPMINoRegistration.setText("");
             * tfieldNameRegistration.setText("");
             * tfieldNewICNoRegistration.setText("");
             * tfieldOldICNoRegistration.setText("");
             * cboxIdTypeRegistration.setSelectedIndex(0);
             * tfieldIdNoRegistration.setText("");
             * tfieldRegistrationNo.setText("");
             * cboxPatientCategoryRegistration.setSelectedIndex(0);
             * cboxVisitTypeRegistration.setSelectedIndex(0);
             * cboxEmergencyTypeRegistration.setSelectedIndex(0);
             * cboxEligibilityCategoryRegistration.setSelectedIndex(0);
             * cboxEligibilityTypeRegistration.setSelectedIndex(0);
             * cboxDisciplineRegistration.setSelectedIndex(0);
             * cboxSubdisciplineRegistration.setSelectedIndex(0);
             * cboxConsultationRoom.setSelectedIndex(0);
             * cboxQueue.setSelectedIndex(0); cboxDoctor.setSelectedIndex(0);
             * cboxPriorityGroupRegistration.setSelectedIndex(0);
             * cboxCommunicableDiseaseRegistration.setSelectedIndex(0);
             * cboxNaturalDisasterRegistration.setSelectedIndex(0);
             * cboxDocumentTypeRegistration.setSelectedIndex(0);
             * tfieldReferenceNo.setText(""); tfieldGroupGuardian.setText("");
             * tfieldGLExpiryDate.setDate(null); tfieldChargeRate.setText("");
             * tfieldPaymentAmount.setText("");
             * cboxPaymentMode.setSelectedIndex(0);
             * rbConsultationRoom.setSelected(true);
             * rbNoChildGuardian.setSelected(true);
             * rbNoPolice.setSelected(true);
             * cboxConsultationRoom.setEnabled(true);
             * cboxDoctor.setEnabled(false); cboxQueue.setEnabled(false);
             */
        }
        setDefault();
    }//GEN-LAST:event_btnRegisterActionPerformed

    //to make payment amount unchageable
    private void UnchangeableReceiptNo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableReceiptNo
        // TODO add your handling code here:
        tfieldReceiptNo.setEditable(false);
    }//GEN-LAST:event_UnchangeableReceiptNo

    //to enable doctor combobox only
    private void EnableDoctor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnableDoctor
        // TODO add your handling code here:
        if (rbDoctor.isSelected()) {
            cboxConsultationRoom.setEnabled(false);
            cboxDoctor.setEnabled(true);
            cboxQueue.setEnabled(false);
        } else {
            cboxConsultationRoom.setEnabled(true);
            cboxDoctor.setEnabled(true);
            cboxQueue.setEnabled(true);
        }
    }//GEN-LAST:event_EnableDoctor

    //to enable queue combobox only
    private void EnableQueue(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnableQueue
        // TODO add your handling code here:
        if (rbQueue.isSelected()) {
            cboxConsultationRoom.setEnabled(false);
            cboxDoctor.setEnabled(false);
            cboxQueue.setEnabled(true);
        } else {
            cboxConsultationRoom.setEnabled(true);
            cboxDoctor.setEnabled(true);
            cboxQueue.setEnabled(true);
        }
    }//GEN-LAST:event_EnableQueue

    //to enable consultation room combobox only
    private void EnableConsulationRoom(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnableConsulationRoom
        // TODO add your handling code here:
        if (rbConsultationRoom.isSelected()) {
            cboxConsultationRoom.setEnabled(true);
            cboxDoctor.setEnabled(false);
            cboxQueue.setEnabled(false);
        } else {
            cboxConsultationRoom.setEnabled(true);
            cboxDoctor.setEnabled(true);
            cboxQueue.setEnabled(true);
        }
    }//GEN-LAST:event_EnableConsulationRoom

    //to make patient Old IC unchangeable
    private void UnchangableOldIcRegister(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangableOldIcRegister
        // TODO add your handling code here:
        tfieldOldICNoRegistration.setEditable(false);
    }//GEN-LAST:event_UnchangableOldIcRegister

    //to make patient name unachangble
    private void UnchangeableNameRegister(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableNameRegister
        // TODO add your handling code here:
        tfieldNameRegistration.setEditable(false);
    }//GEN-LAST:event_UnchangeableNameRegister

    // to make patient New Ic unchangeable
    private void UnchangeableNewIcRegister(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeableNewIcRegister
        // TODO add your handling code here:
        tfieldNewICNoRegistration.setEditable(false);
    }//GEN-LAST:event_UnchangeableNewIcRegister

    //to make patient pmino unchageable
    private void UnchangeablePmiRegister(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UnchangeablePmiRegister
        // TODO add your handling code here:
        tfieldPMINoRegistration.setEditable(false);
    }//GEN-LAST:event_UnchangeablePmiRegister

    private void btnOnlineSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnlineSearchActionPerformed
        // TODO add your handling code here:
        getOnlinePMI(tfieldICSearchRegister.getText());
    }//GEN-LAST:event_btnOnlineSearchActionPerformed

    private void setIC(MyKad mykad) {
        
        tfieldPatientName.setText(mykad.name);
        
        if (mykad.race.equalsIgnoreCase("CINA")) {
            Func.cmbSelectInput(cboxRacePatient, "Chinese");
        }
        if (mykad.race.equalsIgnoreCase("MELAYU")) {
            Func.cmbSelectInput(cboxRacePatient, "Malay");
        }
        if (mykad.race.equalsIgnoreCase("INDIA")) {
            Func.cmbSelectInput(cboxRacePatient, "India");
        }

        Func.cmbSelectInput(cboxPostalCountryPatient, "MALAYSIA");

        Func.cmbSelectInput(cboxReligionPatient, mykad.religion);
        if (mykad.religion.equalsIgnoreCase("BUDDHA")) {
            Func.cmbSelectInput(cboxReligionPatient, "Buddha");
        }
        if (mykad.religion.equalsIgnoreCase("KRISTIAN")) {
            Func.cmbSelectInput(cboxReligionPatient, "Kristian");
        }
        if (mykad.religion.equalsIgnoreCase("ISLAM")) {
            Func.cmbSelectInput(cboxReligionPatient, "Islam");
        }
        if (mykad.religion.equalsIgnoreCase("HINDU")) {
            Func.cmbSelectInput(cboxReligionPatient, "Hindu");
        }

        //cboxSexPatient.setSelectedItem(mykad.gender);
        if (mykad.gender.equalsIgnoreCase("L")) {
            Func.cmbSelectInput(cboxSexPatient, "Male");
        }
        if (mykad.gender.equalsIgnoreCase("P")) {
            Func.cmbSelectInput(cboxSexPatient, "Female");
        }

        tfieldHomeAddress.setText(mykad.address1 + ", " + mykad.address2 + ", " + mykad.address3);

        Func.cmbSelectInput(cboxPostcode, mykad.poscode);
        Func.cmbSelectInput(cboxStatePatient, mykad.state);
        Func.cmbSelectInput(cboxTownCode, mykad.city);
        Func.cmbSelectInput(cboxCountryPatient, "MALAYSIA");

        // cboxNationalityPatient.setSelectedItem(mykad.citizenship);
        if (mykad.citizenship.equalsIgnoreCase("WARGANEGARA")) {
            Func.cmbSelectInput(cboxNationalityPatient, "Malaysian");
        }

//        DateFormat formatterPatientBiodata = new SimpleDateFormat("dd/MM/yyyy");
//        Date getBirthDate = null;
//        try {
//            getBirthDate = formatterPatientBiodata.parse(mykad.dob);
//        } catch (Exception ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        tfieldDOBPatient.setDate(getBirthDate);
        
        try {
            String t1[] = mykad.dob.split("-");
            String t2 = t1[2] + "/" + t1[1] + "/" + t1[0];
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(t2);
            tfieldDOBPatient.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void btnReadMyKadRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadMyKadRegisterActionPerformed
        // TODO add your handling code here:

        MyKad mykad = new MyKad();
        mykad.start();
        mykad.useJPN();
        mykad.readData();
        
        tfieldICSearchRegister.setText(mykad.ic);
        searchRegister();
        
        setIC(mykad);


        /*
         * if(mykad.oldIc.equalsIgnoreCase("")) {
         * tfieldOldICNoRegistration.setText(" "); } else {
         * tfieldOldICNoRegistration.setText(mykad.oldIc);
            }
         */


//        String[] RegisterInfoMyKad = {};
//        String[] BiodataInfoMyKad = {};
//        String[] EmploymentInfoMyKad = {};
//        String[] NOKInfoMyKad = {};
//        String[] FamilyInfoMyKad = {};
//        String[] InsuranceInfoMyKad = {};
//
//        Patient patientMyKad = new Patient(this);
//
//        try {
//            RegisterInfoMyKad = patientMyKad.getRegisterBiodataUsingMyKad(mykad.ic);
//            BiodataInfoMyKad = patientMyKad.getBiodataInfoUsingMyKad(mykad.ic);
//            EmploymentInfoMyKad = patientMyKad.getEmploymentInfoUsingMyKad(mykad.ic);
//            NOKInfoMyKad = patientMyKad.getNOKInfoUsingMyKad(mykad.ic);
//            FamilyInfoMyKad = patientMyKad.getFamilyInfoUsingMyKad(mykad.ic);
//            InsuranceInfoMyKad = patientMyKad.getInsuranceInfoUsingMyKad(mykad.ic);
//
//            if (RegisterInfoMyKad[4] == null) {
//                JOptionPane.showMessageDialog(null, "This patient is new patient. Please proceed to Patient Master Index Form to fill in information !!!", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
//
//                tfieldPatientName.setText(mykad.name);
//                tfieldNewICNoPatient.setText(mykad.ic);
//                tfieldOldICNoPatient.setText(mykad.oldIc);
//                if (mykad.oldIc.equalsIgnoreCase("")) {
//                    tfieldOldICNoPatient.setText("-");
//                } else {
//                    tfieldOldICNoPatient.setText(mykad.oldIc);
//                }
//
//                if (mykad.race.equalsIgnoreCase("CINA")) {
//                    cboxRacePatient.setSelectedItem("Chinese");
//                }
//                if (mykad.race.equalsIgnoreCase("MALAY")) {
//                    cboxRacePatient.setSelectedItem("Malay");
//                }
//                if (mykad.race.equalsIgnoreCase("INDIA")) {
//                    cboxRacePatient.setSelectedItem("India");
//                }
//
//                cboxPostalCountryPatient.setSelectedItem("MALAYSIA");
//
//                cboxReligionPatient.setSelectedItem(mykad.religion);
//                if (mykad.religion.equalsIgnoreCase("BUDDHA")) {
//                    cboxReligionPatient.setSelectedItem("Buddha");
//                }
//                if (mykad.religion.equalsIgnoreCase("KRISTIAN")) {
//                    cboxReligionPatient.setSelectedItem("Kristian");
//                }
//                if (mykad.religion.equalsIgnoreCase("ISLAM")) {
//                    cboxReligionPatient.setSelectedItem("Islam");
//                }
//                if (mykad.religion.equalsIgnoreCase("HINDU")) {
//                    cboxReligionPatient.setSelectedItem("Hindu");
//                }
//
//                //cboxSexPatient.setSelectedItem(mykad.gender);
//                if (mykad.gender.equalsIgnoreCase("L")) {
//                    cboxSexPatient.setSelectedItem("Male");
//                }
//                if (mykad.gender.equalsIgnoreCase("P")) {
//                    cboxSexPatient.setSelectedItem("Female");
//                }
//
//                tfieldHomeAddress.setText(mykad.address1 + mykad.address2 + mykad.address3);
//
//                cboxPostcode.setSelectedItem(mykad.poscode);
//                cboxStatePatient.setSelectedItem(mykad.state);
//                cboxTownCode.setSelectedItem(mykad.city);
//                cboxCountryPatient.setSelectedItem("MALAYSIA");
//
//                // cboxNationalityPatient.setSelectedItem(mykad.citizenship);
//                if (mykad.citizenship.equalsIgnoreCase("WARGANEGARA")) {
//                    cboxNationalityPatient.setSelectedItem("Malaysian");
//                }
//
//                DateFormat formatterPatientBiodata = new SimpleDateFormat("yyyy-MM-dd");
//                Date getBirthDate = null;
//                try {
//                    getBirthDate = formatterPatientBiodata.parse(mykad.dob);
//                } catch (ParseException ex) {
//                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                tfieldDOBPatient.setDate(getBirthDate);
//            } else {
//                //match mykad information with database and show patient information on registration screen
//                tfieldPMINoRegistration.setText(RegisterInfoMyKad[0]);
//                tfieldNameRegistration.setText(RegisterInfoMyKad[1]);
//                tfieldNewICNoRegistration.setText(RegisterInfoMyKad[2]);
//                tfieldOldICNoRegistration.setText(RegisterInfoMyKad[3]);
//                cboxIdTypeRegistration.setSelectedItem(RegisterInfoMyKad[4]);
//                tfieldIdNoRegistration.setText(RegisterInfoMyKad[5]);
//                cboxEligibilityCategoryRegistration.setSelectedItem(RegisterInfoMyKad[6]);
//                cboxEligibilityTypeRegistration.setSelectedItem(RegisterInfoMyKad[7]);
//                cboxVisitTypeRegistration.setSelectedItem("Walk-in");
//                tfieldChargeRate.setText("1.00");
//                tfieldPaymentAmount.setText("1.00");
//
//                //match mykad information with database and show patient biodata on patient master index
//                tfieldPMINoPMI.setText(BiodataInfoMyKad[0]);
//                tfieldTempPMINo.setText(BiodataInfoMyKad[1]);
//                tfieldPatientName.setText(BiodataInfoMyKad[2]);
//                cboxTitle.setSelectedItem(BiodataInfoMyKad[3]);
//                tfieldNewICNoPatient.setText(BiodataInfoMyKad[4]);
//                tfieldOldICNoPatient.setText(BiodataInfoMyKad[5]);
//                cboxIdTypePatient.setSelectedItem(BiodataInfoMyKad[6]);
//                tfieldIdNoPatient.setText(BiodataInfoMyKad[7]);
//                cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfoMyKad[8]);
//                cboxEligibilityTypePatient.setSelectedItem(BiodataInfoMyKad[9]);
//
//                DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
//                Date getDOB = null;
//                try {
//                    getDOB = formatterBiodata.parse(BiodataInfoMyKad[10]);
//                } catch (ParseException ex) {
//                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                tfieldDOBPatient.setDate(getDOB);
//
//                cboxSexPatient.setSelectedItem(BiodataInfoMyKad[11]);
//                cboxMaritalStatusPatient.setSelectedItem(BiodataInfoMyKad[12]);
//                cboxRacePatient.setSelectedItem(BiodataInfoMyKad[13]);
//                cboxNationalityPatient.setSelectedItem(BiodataInfoMyKad[14]);
//                cboxReligionPatient.setSelectedItem(BiodataInfoMyKad[15]);
//                cboxBloodTypePatient.setSelectedItem(BiodataInfoMyKad[16]);
//                cboxBloodRhesusPatient.setSelectedItem(BiodataInfoMyKad[17]);
//                cboxAllergyPatient.setSelectedItem(BiodataInfoMyKad[18]);
//                cboxChronicDiseasePatient.setSelectedItem(BiodataInfoMyKad[19]);
//                cboxOrganDonorPatient.setSelectedItem(BiodataInfoMyKad[20]);
//                tfieldHomeAddress.setText(BiodataInfoMyKad[21]);
//                cboxDistrictCode.setSelectedItem(BiodataInfoMyKad[22]);
//                cboxTownCode.setSelectedItem(BiodataInfoMyKad[23]);
//                cboxPostcode.setSelectedItem(BiodataInfoMyKad[24]);
//                cboxStatePatient.setSelectedItem(BiodataInfoMyKad[25]);
//                cboxCountryPatient.setSelectedItem(BiodataInfoMyKad[26]);
//                tfieldHomephonePatient.setText(BiodataInfoMyKad[27]);
//                tfieldPostalAddressPatient.setText(BiodataInfoMyKad[28]);
//                cboxPostalDistrict.setSelectedItem(BiodataInfoMyKad[29]);
//                cboxPostalTown.setSelectedItem(BiodataInfoMyKad[30]);
//                cboxPostalPostcode.setSelectedItem(BiodataInfoMyKad[31]);
//                cboxPostalStatePatient.setSelectedItem(BiodataInfoMyKad[32]);
//                cboxPostalCountryPatient.setSelectedItem(BiodataInfoMyKad[33]);
//                tfieldHandphoneNoPatient.setText(BiodataInfoMyKad[34]);
//
//                //match mykad information with database and show patient's employment information on employment screen
//                tfieldPmiEmployment.setText(EmploymentInfoMyKad[0]);
//                tfieldEmploymentSequenceNo.setText(EmploymentInfoMyKad[1]);
//                tfieldEmployerCode.setText(EmploymentInfoMyKad[2]);
//                tfieldEmployerName.setText(EmploymentInfoMyKad[3]);
//                tfieldOccupationCode.setText(EmploymentInfoMyKad[4]);
//
//                DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
//                Date getJoinedDateEmployment = null;
//
//                try {
//                    getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfoMyKad[5]);
//                } catch (ParseException ex) {
//                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                tfieldJoinedDate.setDate(getJoinedDateEmployment);
//
//                cboxIncomeRange.setSelectedItem(EmploymentInfoMyKad[6]);
//                cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfoMyKad[7]);
//
//                Date getCreatedDateEmployment = null;
//
//                try {
//                    getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfoMyKad[8]);
//                } catch (ParseException ex) {
//                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                tfieldCreatedDate.setDate(getCreatedDateEmployment);
//
//                cboxEmploymentStatus.setSelectedItem(EmploymentInfoMyKad[9]);
//
//                //match mykad information with database and show patient's next of kin information on next of kin screen
//                tfieldPmiNextOfKin.setText(NOKInfoMyKad[0]);
//                tfieldNextOfKinSequenceNo.setText(NOKInfoMyKad[1]);
//                cboxNOKRelationship.setSelectedItem(NOKInfoMyKad[2]);
//                tfieldNextOfKinName.setText(NOKInfoMyKad[3]);
//                tfieldNewICNo.setText(NOKInfoMyKad[4]);
//                tfieldOldICNo.setText(NOKInfoMyKad[5]);
//                cboxIdTypeNOK.setSelectedItem(NOKInfoMyKad[6]);
//                tfieldIdNoNOK.setText(NOKInfoMyKad[7]);
//
//                DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
//                Date getdobNOK = null;
//                try {
//                    //System.out.println(nokInfo[8]);
//                    getdobNOK = formatterNOK.parse(NOKInfoMyKad[8]);
//                    //System.out.println(getdobNOK);
//                } catch (ParseException ex) {
//                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                tfieldDOB.setDate(getdobNOK);
//                tfieldOccupationNOK.setText(NOKInfoMyKad[9]);
//                tareaAddressNOK.setText(NOKInfoMyKad[10]);
//                cboxDistrictNOK.setSelectedItem(NOKInfoMyKad[11]);
//                cboxTownNOK.setSelectedItem(NOKInfoMyKad[12]);
//                cboxPostcodeNOK.setSelectedItem(NOKInfoMyKad[13]);
//                cboxStateNOK.setSelectedItem(NOKInfoMyKad[14]);
//                cboxCountryNOK.setSelectedItem(NOKInfoMyKad[15]);
//                tfieldHpNoNOK.setText(NOKInfoMyKad[16]);
//                tfieldHomephoneNoNOK.setText(NOKInfoMyKad[17]);
//                tfieldEmailNOK.setText(NOKInfoMyKad[18]);
//
//                //match mykad information with database and show patient's family information on family screen
//                tfieldPmiFamily.setText(FamilyInfoMyKad[0]);
//                tfieldFamilySequenceNo.setText(FamilyInfoMyKad[1]);
//                cboxFamilyRelationship.setSelectedItem(FamilyInfoMyKad[2]);
//                tareaFamilyMemberPMI.setText(FamilyInfoMyKad[3]);
//                tareaFamilyMemberName.setText(FamilyInfoMyKad[4]);
//                tfieldOccupationFamily.setText(FamilyInfoMyKad[5]);
//
//                //match mykad information with database and show patient's medical insurance information on insurance screen
//                tfieldPmiInsurance.setText(InsuranceInfoMyKad[0]);
//                cboxInsuranceCompanyName.setSelectedItem(InsuranceInfoMyKad[1]);
//                tfieldPolicyNo.setText(InsuranceInfoMyKad[2]);
//
//                DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
//                Date getMaturityDateInsurance = null;
//                try {
//                    getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfoMyKad[3]);
//                } catch (ParseException ex) {
//                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                tfieldMaturityDate.setDate(getMaturityDateInsurance);
//                cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfoMyKad[4]);
//                CboxPolicyStatus.setSelectedItem(InsuranceInfoMyKad[5]);
//
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        }

        mykad.stop();
    }//GEN-LAST:event_btnReadMyKadRegisterActionPerformed

    public static int num_sii = 7;
    
    public static boolean isRecognizedPatient = false;
    public static String numCheckDigit = "";
    
    private void searchRegister() {
        setDefault();

        // search by PMI No.
        if (tfieldPMISearchRegister.getText() != null && (tfieldPMISearchRegister.getText().length() != 0)) {
            String[] RegisterInfo = {};
            String[] BiodataInfo = {};
            String[] EmploymentInfo = {};
            String[] NOKInfo = {};
            String[] FamilyInfo = {};
            String[] InsuranceInfo = {};

            Patient patientRegister = new Patient(this);

            try {
                RegisterInfo = patientRegister.getBiodata(tfieldPMISearchRegister.getText());
                if (RegisterInfo[0] != null) {
                    BiodataInfo = trim(patientRegister.getBiodata(tfieldPMISearchRegister.getText()));
                    EmploymentInfo = trim(patientRegister.getEmploymentDetailUsingNewIC(tfieldPMISearchRegister.getText()));
                    NOKInfo = trim(patientRegister.getNokDetailUsingNewIC(tfieldPMISearchRegister.getText()));
                    FamilyInfo = trim(patientRegister.getFamilyDetailUsingNewIC(tfieldPMISearchRegister.getText()));
                    InsuranceInfo = trim(patientRegister.getInsuranceDetailUsingNewIC(tfieldPMISearchRegister.getText()));
                }

                if (RegisterInfo[0] == null) {
//                    JOptionPane.showMessageDialog(null, "Patient biodata is not existed in the database.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    
                    J.o("Patient not exist", "Patient is not existed!", 0);
                    
                    //Set tab to Registration

//                    String ic = JOptionPane.showInputDialog("Please key in the patient's identification number:-");
//                    
//                    if(ic != null && ic.length() != 0 && ic != "") {
//                        String[] ri1 = {};
//                        Patient pr1 = new Patient(this);
//                        ri1 = pr1.getRegisterBiodataUsingNewIC(ic);
//                        
//                        if (ri1[4] == null) {
//                            clearForm();
//                            generateAllAutogenerateNo(ic);
//                            tabpanel.setSelectedIndex(1);
//                            button_new();
//                        } else {
//                            JOptionPane.showMessageDialog(null, "This identification number already been used!!", "ID Number Been Used", 0);
//                        }
//                    }
                    
                } else {
                    button_old();
                    setsaveStatus(false);
                    //show patient information on registration screen
                    tfieldPMINoRegistration.setText(RegisterInfo[0]);
                    tfieldNameRegistration.setText(RegisterInfo[2]);
                    tfieldNewICNoRegistration.setText(RegisterInfo[4]);
                    tfieldOldICNoRegistration.setText(RegisterInfo[5]);
                    //cboxIdTypeRegistration.setSelectedItem(RegisterInfo[6]);
                    cboxIdTypeRegistration.getModel().setSelectedItem(RegisterInfo[6]);
                    tfieldIdNoRegistration.setText(RegisterInfo[7]);
                    //cboxEligibilityCategoryRegistration.setSelectedItem(RegisterInfo[8]);
                    //cboxEligibilityTypeRegistration.setSelectedItem(RegisterInfo[9]);
                    cboxEligibilityCategoryRegistration.getModel().setSelectedItem(RegisterInfo[8]);
                    cboxEligibilityTypeRegistration.getModel().setSelectedItem(RegisterInfo[9]);
                    //cboxVisitTypeRegistration.setSelectedItem("Walk-in");
                    cboxVisitTypeRegistration.getModel().setSelectedItem("Walk-in");
                    tfieldChargeRate.setText("1.00");
                    tfieldPaymentAmount.setText("1.00");
                    //if(tfieldIdNoRegistration.getText().length() != 0)
                    //{
                    // tfieldChargeRate.setText("5.00");
                    //tfieldPaymentAmount.setText("5.00");
                    //}

                    //show patient's biodata on patient master index screen
                    tfieldPMINoPMI.setText(BiodataInfo[0]);
                    tfieldTempPMINo.setText(BiodataInfo[1]);
                    tfieldPatientName.setText(BiodataInfo[2]);
                    // cboxTitle.setSelectedItem(BiodataInfo[3]);
                    cboxTitle.getModel().setSelectedItem(BiodataInfo[3]);
                    tfieldNewICNoPatient.setText(BiodataInfo[4]);
                    tfieldOldICNoPatient.setText(BiodataInfo[5]);
                    //cboxIdTypePatient.setSelectedItem(BiodataInfo[6]);
                    cboxIdTypePatient.getModel().setSelectedItem(BiodataInfo[6]);
                    cboxEligibilityCategoryPatient.getModel().setSelectedItem(BiodataInfo[8]);
                    cboxEligibilityTypePatient.getModel().setSelectedItem(BiodataInfo[9]);
                    tfieldIdNoPatient.setText(BiodataInfo[7]);
                    // cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo[8]);
                    // cboxEligibilityTypePatient.setSelectedItem(BiodataInfo[9]);

                    DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
                    Date getDOB = null;
                    try {
                        getDOB = formatterBiodata.parse(BiodataInfo[10]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOBPatient.setDate(getDOB);

                    /*
                     * new model
                     */
                    cboxSexPatient.getModel().setSelectedItem(BiodataInfo[11]);
                    cboxMaritalStatusPatient.getModel().setSelectedItem(BiodataInfo[12]);
                    cboxRacePatient.getModel().setSelectedItem(BiodataInfo[13]);
                    cboxNationalityPatient.getModel().setSelectedItem(BiodataInfo[14]);
                    cboxReligionPatient.getModel().setSelectedItem(BiodataInfo[15]);
                    cboxStatePatient.getModel().setSelectedItem(BiodataInfo[25]);
                    cboxCountryPatient.getModel().setSelectedItem(BiodataInfo[26]);
                    cboxPostalStatePatient.getModel().setSelectedItem(BiodataInfo[32]);
                    cboxPostalCountryPatient.getModel().setSelectedItem(BiodataInfo[33]);

//                        cboxSexPatient.setSelectedItem(BiodataInfo[11]);
//                        cboxMaritalStatusPatient.setSelectedItem(BiodataInfo[12]);
//                        cboxRacePatient.setSelectedItem(BiodataInfo[13]);
//                        cboxNationalityPatient.setSelectedItem(BiodataInfo[14]);
//                        cboxReligionPatient.setSelectedItem(BiodataInfo[15]);
                    cboxBloodTypePatient.setSelectedItem(BiodataInfo[16]);
                    cboxBloodRhesusPatient.setSelectedItem(BiodataInfo[17]);
                    cboxAllergyPatient.setSelectedItem(BiodataInfo[18]);
                    cboxChronicDiseasePatient.setSelectedItem(BiodataInfo[19]);
                    cboxOrganDonorPatient.setSelectedItem(BiodataInfo[20]);
                    tfieldHomeAddress.setText(BiodataInfo[21]);
                    cboxDistrictCode.setSelectedItem(BiodataInfo[22]);
                    cboxTownCode.setSelectedItem(BiodataInfo[23]);
                    cboxPostcode.setSelectedItem(BiodataInfo[24]);
                    // cboxStatePatient.setSelectedItem(BiodataInfo[25]);
                    // cboxCountryPatient.setSelectedItem(BiodataInfo[26]);
                    tfieldHomephonePatient.setText(BiodataInfo[27]);
                    tfieldPostalAddressPatient.setText(BiodataInfo[28]);
                    cboxPostalDistrict.setSelectedItem(BiodataInfo[29]);
                    cboxPostalTown.setSelectedItem(BiodataInfo[30]);
                    cboxPostalPostcode.setSelectedItem(BiodataInfo[31]);
                    // cboxPostalStatePatient.setSelectedItem(BiodataInfo[32]);
                    // cboxPostalCountryPatient.setSelectedItem(BiodataInfo[33]);
                    tfieldHandphoneNoPatient.setText(BiodataInfo[34]);

                    //show patient's employment information on employment screen
                    tfieldPmiEmployment.setText(EmploymentInfo[0]);
                    tfieldEmploymentSequenceNo.setText(EmploymentInfo[1]);
                    tfieldEmployerCode.setText(EmploymentInfo[2]);
                    tfieldEmployerName.setText(EmploymentInfo[3]);
                    tfieldOccupationCode.setText(EmploymentInfo[4]);

                    DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
                    Date getJoinedDateEmployment = null;

                    try {
                        getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo[5]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldJoinedDate.setDate(getJoinedDateEmployment);

                    cboxIncomeRange.setSelectedItem(EmploymentInfo[6]);
                    cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo[7]);

                    Date getCreatedDateEmployment = null;
                    try {
                        getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo[8]);
                    } catch (ParseException ex) {
                        
                    }

                    tfieldCreatedDate.setDate(getCreatedDateEmployment);
                    cboxEmploymentStatus.setSelectedItem(EmploymentInfo[9]);

                    //show patient's next of kin  information on next of kin screen
                    tfieldPmiNextOfKin.setText(NOKInfo[0]);
                    tfieldNextOfKinSequenceNo.setText(NOKInfo[1]);
                    cboxNOKRelationship.setSelectedItem(NOKInfo[2]);
                    tfieldNextOfKinName.setText(NOKInfo[3]);
                    tfieldNewICNo.setText(NOKInfo[4]);
                    tfieldOldICNo.setText(NOKInfo[5]);
                    cboxIdTypeNOK.setSelectedItem(NOKInfo[6]);
                    tfieldIdNoNOK.setText(NOKInfo[7]);

                    DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
                    Date getdobNOK = null;
                    try {
                        //System.out.println(nokInfo[8]);
                        getdobNOK = formatterNOK.parse(NOKInfo[8]);
                        //System.out.println(getdobNOK);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOB.setDate(getdobNOK);
                    tfieldOccupationNOK.setText(NOKInfo[9]);
                    tareaAddressNOK.setText(NOKInfo[10]);
                    cboxDistrictNOK.setSelectedItem(NOKInfo[11]);
                    cboxTownNOK.setSelectedItem(NOKInfo[12]);
                    cboxPostcodeNOK.setSelectedItem(NOKInfo[13]);
                    cboxStateNOK.setSelectedItem(NOKInfo[14]);
                    cboxCountryNOK.setSelectedItem(NOKInfo[15]);
                    tfieldHpNoNOK.setText(NOKInfo[16]);
                    tfieldHomephoneNoNOK.setText(NOKInfo[17]);
                    tfieldEmailNOK.setText(NOKInfo[18]);

                    //show patient's family information on family screen

                    tfieldPmiFamily.setText(FamilyInfo[0]);
                    tfieldFamilySequenceNo.setText(FamilyInfo[1]);
                    cboxFamilyRelationship.setSelectedItem(FamilyInfo[2]);
                    tareaFamilyMemberPMI.setText(FamilyInfo[3]);
                    tareaFamilyMemberName.setText(FamilyInfo[4]);
                    tfieldOccupationFamily.setText(FamilyInfo[5]);

                    //show patient's medical insurance information on insurance screen
                    tfieldPmiInsurance.setText(InsuranceInfo[0]);
                    cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo[1]);
                    tfieldPolicyNo.setText(InsuranceInfo[2]);

                    DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
                    Date getMaturityDateInsurance = null;
                    try {
                        getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo[3]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldMaturityDate.setDate(getMaturityDateInsurance);
                    cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo[4]);
                    CboxPolicyStatus.setSelectedItem(InsuranceInfo[5]);

                }
            } catch (ClassNotFoundException ex) {
                
            } catch (SQLException ex) {
                
            }
        }

        //SEARCH USING NEW IC NO
        if ((tfieldICSearchRegister.getText() != null) && (rbNewRegister.isSelected() == true) && (tfieldICSearchRegister.getText().length() != 0) && (tfieldICSearchRegister.getText() != "")) {
            String[] RegisterInfo1 = {};
            String[] BiodataInfo1 = {};
            String[] EmploymentInfo1 = {};
            String[] NOKInfo1 = {};
            String[] FamilyInfo1 = {};
            String[] InsuranceInfo1 = {};

            Patient patientRegister1 = new Patient(this);

            try {
                RegisterInfo1 = patientRegister1.getRegisterBiodataUsingNewIC(tfieldICSearchRegister.getText());
                if (RegisterInfo1[4] != null) {
                    BiodataInfo1 = trim(patientRegister1.getBiodataUsingNewIC(RegisterInfo1[0]));
                    EmploymentInfo1 = trim(patientRegister1.getEmploymentDetailUsingNewIC(RegisterInfo1[0]));
                    NOKInfo1 = trim(patientRegister1.getNokDetailUsingNewIC(RegisterInfo1[0]));
                    FamilyInfo1 = trim(patientRegister1.getFamilyDetailUsingNewIC(RegisterInfo1[0]));
                    InsuranceInfo1 = trim(patientRegister1.getInsuranceDetailUsingNewIC(RegisterInfo1[0]));
                }

                try {
                    // id no
                    for (int i = 0; i <= 26; i++) {
                        S.oln(i+" umaq:"+RegisterInfo1[i]);
                    }
                } catch (Exception e) {
                    J.o("", "error dowh", 0);
                }
                
                if (RegisterInfo1[4] == null) {
                    
                    if (isRecognizedPatient == false) {
                        tabpanel.setSelectedIndex(0);
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(null, "Patient biodata is not existed in the database."
                            + "\nPlease proceed to Patient Master Index Form to fill in information !!!", "New Patient", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    
                    //Set tab to Registration

                    String ic = tfieldICSearchRegister.getText();
                    clearForm();
                    generateAllAutogenerateNo(numCheckDigit);

                    tabpanel.setSelectedIndex(1);
                    tfieldNewICNoPatient.setText(tfieldICSearchRegister.getText());
                    
                    button_new();
                    
                    // id no
                    for (int i = 0; i <= 26; i++) {
                        S.oln(i+" shikin:"+RegisterInfo1[i]);
                    }
                    
                    // date of birth
                    try {
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(RegisterInfo1[18]);
                        tfieldDOBPatient.setDate(date1);
                    } catch (Exception e) {
                        System.out.println("Date Parse Error: "+e.getMessage());
                    }
                    
                    // name
                    tfieldPatientName.setText(RegisterInfo1[17]);
                    
                    // id type
                    if (RegisterInfo1[12].equals("0")) {
                        Func.cmbSelectInput(cboxIdTypePatient, "Staff No.");
                    } else {
                        Func.cmbSelectInput(cboxIdTypePatient, "Matric No.");
                    }
                    if (RegisterInfo1[11].equals("F")) {
                        Func.cmbSelectInput(cboxIdTypePatient, "Foreigner");
                    }
                    if (RegisterInfo1[11].equals("F")) {
                        tfieldIdNoPatient.setText(RegisterInfo1[9]);
                    } else {
                        tfieldIdNoPatient.setText(RegisterInfo1[10]);
                    }
                    // date of birth
                    tfieldDOBPatient.setDate(null);
                    // gender
                    if (RegisterInfo1[19].equals("M") || RegisterInfo1[19].equals("L")) {
                        Func.cmbSelectInput(cboxSexPatient, "Male");
                    } else if (RegisterInfo1[19].equals("F") || RegisterInfo1[19].equals("P")) {
                        Func.cmbSelectInput(cboxSexPatient, "Female");
                    }
                    // race
                    Func.cmbSelectInput(cboxRacePatient, RegisterInfo1[20]);
                    // nationality
                    Func.cmbSelectInput(cboxNationalityPatient, RegisterInfo1[21]);
                    // home address
                    tfieldHomeAddress.setText(RegisterInfo1[22]);
                    // home address 2
                    tfieldPostalAddressPatient.setText(RegisterInfo1[23]);
                    // postcode
                    Func.cmbSelectInput(cboxPostcode, RegisterInfo1[24]);
                    Func.cmbSelectInput(cboxPostalPostcode, RegisterInfo1[24]);
                    // country
                    Func.cmbSelectInput(cboxCountryPatient, RegisterInfo1[25]);
                    Func.cmbSelectInput(cboxPostalCountryPatient, RegisterInfo1[25]);
                    // phone no
                    tfieldHomephonePatient.setText(RegisterInfo1[26]);
                    tfieldHandphoneNoPatient.setText(RegisterInfo1[26]);
                    
                } else {
                    
                    
                    button_old();
                    setsaveStatus(false);
                    //show patient information on registration screen
                    tfieldPMINoRegistration.setText(RegisterInfo1[0]);
                    tfieldNameRegistration.setText(RegisterInfo1[1]);
                    tfieldNewICNoRegistration.setText(RegisterInfo1[2]);
                    tfieldOldICNoRegistration.setText(RegisterInfo1[3]);
                    // cboxIdTypeRegistration.setSelectedItem(RegisterInfo1[4]);
                    cboxIdTypeRegistration.getModel().setSelectedItem(RegisterInfo1[4]);
                    tfieldIdNoRegistration.setText(RegisterInfo1[5]);
                    //cboxEligibilityCategoryRegistration.setSelectedItem(RegisterInfo1[6]);
                    //cboxEligibilityTypeRegistration.setSelectedItem(RegisterInfo1[7]);
                    cboxEligibilityCategoryRegistration.getModel().setSelectedItem(RegisterInfo1[6]);
                    cboxEligibilityTypeRegistration.getModel().setSelectedItem(RegisterInfo1[7]);
                    cboxVisitTypeRegistration.getModel().setSelectedItem("Walk-in");
                    tfieldChargeRate.setText("1.00");
                    tfieldPaymentAmount.setText("1.00");



                    /*
                     * show patient's biodata on patient master index screen
                     */
                    tfieldPMINoPMI.setText(BiodataInfo1[0]);
                    tfieldTempPMINo.setText(BiodataInfo1[1]);
                    tfieldPatientName.setText(BiodataInfo1[2]);
                    //cboxTitle.setSelectedItem(BiodataInfo1[3]);
                    cboxTitle.getModel().setSelectedItem(BiodataInfo1[3]);
                    tfieldNewICNoPatient.setText(BiodataInfo1[4]);
                    tfieldOldICNoPatient.setText(BiodataInfo1[5]);
                    //cboxIdTypePatient.setSelectedItem(BiodataInfo1[6]);
                    cboxIdTypePatient.getModel().setSelectedItem(BiodataInfo1[6]);
                    tfieldIdNoPatient.setText(BiodataInfo1[7]);
                    //cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo1[8]);
                    cboxEligibilityCategoryPatient.getModel().setSelectedItem(BiodataInfo1[8]);
                    //cboxEligibilityTypePatient.setSelectedItem(BiodataInfo1[9]);
                    cboxEligibilityTypePatient.getModel().setSelectedItem(BiodataInfo1[9]);

                    DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
                    Date getDOB = null;
                    try {
                        getDOB = formatterBiodata.parse(BiodataInfo1[10]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOBPatient.setDate(getDOB);

                    /*
                     * new model
                     */
                    cboxSexPatient.getModel().setSelectedItem(BiodataInfo1[11]);
                    cboxMaritalStatusPatient.getModel().setSelectedItem(BiodataInfo1[12]);
                    cboxRacePatient.getModel().setSelectedItem(BiodataInfo1[13]);
                    cboxNationalityPatient.getModel().setSelectedItem(BiodataInfo1[14]);
                    cboxReligionPatient.getModel().setSelectedItem(BiodataInfo1[15]);
                    cboxStatePatient.getModel().setSelectedItem(BiodataInfo1[25]);
                    cboxCountryPatient.getModel().setSelectedItem(BiodataInfo1[26]);
                    cboxPostalStatePatient.getModel().setSelectedItem(BiodataInfo1[32]);
                    cboxPostalCountryPatient.getModel().setSelectedItem(BiodataInfo1[33]);

                    // cboxSexPatient.setSelectedItem(BiodataInfo1[11]);
                    // cboxMaritalStatusPatient.setSelectedItem(BiodataInfo1[12]);
                    // cboxRacePatient.setSelectedItem(BiodataInfo1[13]);
                    // cboxNationalityPatient.setSelectedItem(BiodataInfo1[14]);
                    // cboxReligionPatient.setSelectedItem(BiodataInfo1[15]);
                    cboxBloodTypePatient.setSelectedItem(BiodataInfo1[16]);
                    cboxBloodRhesusPatient.setSelectedItem(BiodataInfo1[17]);
                    cboxAllergyPatient.setSelectedItem(BiodataInfo1[18]);
                    cboxChronicDiseasePatient.setSelectedItem(BiodataInfo1[19]);
                    cboxOrganDonorPatient.setSelectedItem(BiodataInfo1[20]);
                    tfieldHomeAddress.setText(BiodataInfo1[21]);
                    cboxDistrictCode.setSelectedItem(BiodataInfo1[22]);
                    cboxTownCode.setSelectedItem(BiodataInfo1[23]);
                    cboxPostcode.setSelectedItem(BiodataInfo1[24]);
                    //cboxStatePatient.setSelectedItem(BiodataInfo1[25]);
                    //cboxCountryPatient.setSelectedItem(BiodataInfo1[26]);
                    tfieldHomephonePatient.setText(BiodataInfo1[27]);
                    tfieldPostalAddressPatient.setText(BiodataInfo1[28]);
                    cboxPostalDistrict.setSelectedItem(BiodataInfo1[29]);
                    cboxPostalTown.setSelectedItem(BiodataInfo1[30]);
                    cboxPostalPostcode.setSelectedItem(BiodataInfo1[31]);
                    //cboxPostalStatePatient.setSelectedItem(BiodataInfo1[32]);

                    System.out.println(BiodataInfo1[32] + "testing state");
                    // cboxPostalCountryPatient.setSelectedItem(BiodataInfo1[33]);
                    tfieldHandphoneNoPatient.setText(BiodataInfo1[34]);

                    //show patient's employment information on employment screen
                    showTableEmploy();
                    tfieldPmiEmployment.setText(tfieldPMINoPMI.getText());
                    if (!EmploymentInfo1[1].equals("-")) {
                        tfieldEmploymentSequenceNo.setText(EmploymentInfo1[1]);
                    }
                    tfieldEmployerCode.setText(EmploymentInfo1[2]);
                    tfieldEmployerName.setText(EmploymentInfo1[3]);
                    tfieldOccupationCode.setText(EmploymentInfo1[4]);

                    DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
                    Date getJoinedDateEmployment = null;

                    try {
                        System.out.println(EmploymentInfo1[5] + "asdadasdadasdadadadadsadadadasdsdadasdasdadasdasdasdasdasda");

                        getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo1[5]);
                    } catch (Exception ex) {
                        
                        try {
                            getJoinedDateEmployment = formatterEmployment.parse("01/01/1970");
                        } catch (Exception ex1) {
                            
                        }
                    }
                    tfieldJoinedDate.setDate(getJoinedDateEmployment);

                    cboxIncomeRange.setSelectedItem(EmploymentInfo1[6]);
                    cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo1[7]);

                    Date getCreatedDateEmployment = null;
                    try {
                        getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo1[8]);
                    } catch (Exception ex) {
                        
                        try {
                            getCreatedDateEmployment = formatterEmployment.parse("01/01/1970");
                        } catch (Exception ex1) {
                            
                        }
                    }

                    tfieldCreatedDate.setDate(getCreatedDateEmployment);
                    cboxEmploymentStatus.setSelectedItem(EmploymentInfo1[9]);

                    //show patient's next of kin information on next of kin screen
                    showTableNOK();
                    tfieldPmiNextOfKin.setText(tfieldPMINoPMI.getText());
                    if (!NOKInfo1[1].equals("-")) {
                        tfieldNextOfKinSequenceNo.setText(NOKInfo1[1]);
                    }
                    cboxNOKRelationship.setSelectedItem(NOKInfo1[2]);
                    tfieldNextOfKinName.setText(NOKInfo1[3]);
                    tfieldNewICNo.setText(NOKInfo1[4]);
                    tfieldOldICNo.setText(NOKInfo1[5]);
                    cboxIdTypeNOK.setSelectedItem(NOKInfo1[6]);
                    tfieldIdNoNOK.setText(NOKInfo1[7]);
                    DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
                    Date getdobNOK = null;
                    try {
                        //System.out.println(nokInfo[8]);
                        getdobNOK = formatterNOK.parse(NOKInfo1[8]);
                        //System.out.println(getdobNOK);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOB.setDate(getdobNOK);
                    tfieldOccupationNOK.setText(NOKInfo1[9]);
                    tareaAddressNOK.setText(NOKInfo1[10]);
                    cboxDistrictNOK.setSelectedItem(NOKInfo1[11]);
                    cboxTownNOK.setSelectedItem(NOKInfo1[12]);
                    cboxPostcodeNOK.setSelectedItem(NOKInfo1[13]);
                    cboxStateNOK.setSelectedItem(NOKInfo1[14]);
                    cboxCountryNOK.setSelectedItem(NOKInfo1[15]);
                    tfieldHpNoNOK.setText(NOKInfo1[16]);
                    tfieldHomephoneNoNOK.setText(NOKInfo1[17]);
                    tfieldEmailNOK.setText(NOKInfo1[18]);

                    //show patient's family information on family screen
                    showTableFamily();
                    tfieldPmiFamily.setText(tfieldPMINoPMI.getText());
                    if (!FamilyInfo1[1].equals("-")) {
                        tfieldFamilySequenceNo.setText(FamilyInfo1[1]);
                    }
                    cboxFamilyRelationship.setSelectedItem(FamilyInfo1[2]);
                    tareaFamilyMemberPMI.setText(FamilyInfo1[3]);
                    tareaFamilyMemberName.setText(FamilyInfo1[4]);
                    tfieldOccupationFamily.setText(FamilyInfo1[5]);

                    //show patient's medical insurance information on insurance screen
                    showTableMedical();
                    tfieldPmiInsurance.setText(tfieldPMINoPMI.getText());
                    if (!InsuranceInfo1[1].equals("-")) {
                        tfieldmedicalseqno.setText(InsuranceInfo1[1]);
                    }
                    cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo1[2]);
                    tfieldPolicyNo.setText(InsuranceInfo1[3]);

                    DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
                    Date getMaturityDateInsurance = null;
                    try {
                        getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo1[4]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldMaturityDate.setDate(getMaturityDateInsurance);
                    cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo1[5]);
                    CboxPolicyStatus.setSelectedItem(InsuranceInfo1[6]);

                }
                setDefault();
            } catch (Exception ex) {
                setDefault();
                ex.printStackTrace();
            }
        }

        //SEARCH USING OLD IC NO
        if ((tfieldICSearchRegister.getText() != null) && (rbOldRegister.isSelected() == true) && (tfieldICSearchRegister.getText().length() != 0) && (tfieldICSearchRegister.getText() != "")) {
            String[] RegisterInfo2 = {};
            String[] BiodataInfo2 = {};
            String[] EmploymentInfo2 = {};
            String[] NOKInfo2 = {};
            String[] FamilyInfo2 = {};
            String[] InsuranceInfo2 = {};

            Patient patientRegister2 = new Patient(this);

            try {
                RegisterInfo2 = patientRegister2.getRegisterBiodataUsingOldIC(tfieldICSearchRegister.getText());
                if (RegisterInfo2[0] != null) {
                    BiodataInfo2 = trim(patientRegister2.getBiodataUsingNewIC(RegisterInfo2[0]));
                    EmploymentInfo2 = trim(patientRegister2.getEmploymentDetailUsingNewIC(RegisterInfo2[0]));
                    NOKInfo2 = trim(patientRegister2.getNokDetailUsingNewIC(RegisterInfo2[0]));
                    FamilyInfo2 = trim(patientRegister2.getFamilyDetailUsingNewIC(RegisterInfo2[0]));
                    InsuranceInfo2 = trim(patientRegister2.getInsuranceDetailUsingNewIC(RegisterInfo2[0]));
                }

                if (RegisterInfo2[0] == null) {
                    
                    if (isRecognizedPatient == false) {
                        tabpanel.setSelectedIndex(0);
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(null, "Patient biodata is not existed in the database.\nPlease proceed to Patient Master Index Form to fill in information !!!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    //Set tab to Registration

                    String ic = tfieldICSearchRegister.getText();
                    clearForm();
                    generateAllAutogenerateNo(numCheckDigit);

                    tabpanel.setSelectedIndex(1);
                    tfieldOldICNoPatient.setText(tfieldICSearchRegister.getText());
                    button_new();
                    
                    // id no
                    for (int i = 0; i <= 26; i++) {
                        S.oln(i + " shikin:" + RegisterInfo2[i]);
                    }

                    // date of birth
                    try {
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(RegisterInfo2[18]);
                        tfieldDOBPatient.setDate(date1);
                    } catch (Exception e) {
                        System.out.println("Date Parse Error: " + e.getMessage());
                    }

                    // name
                    tfieldPatientName.setText(RegisterInfo2[17]);

                    // id type
                    if (RegisterInfo2[12].equals("0")) {
                        Func.cmbSelectInput(cboxIdTypePatient, "Staff No.");
                    } else {
                        Func.cmbSelectInput(cboxIdTypePatient, "Matric No.");
                    }
                    if (RegisterInfo2[11].equals("F")) {
                        Func.cmbSelectInput(cboxIdTypePatient, "Foreigner");
                    }
                    if (RegisterInfo2[11].equals("F")) {
                        tfieldIdNoPatient.setText(RegisterInfo2[9]);
                    } else {
                        tfieldIdNoPatient.setText(RegisterInfo2[10]);
                    }
                    // date of birth
                    tfieldDOBPatient.setDate(null);
                    // gender
                    if (RegisterInfo2[19].equals("M") || RegisterInfo2[19].equals("L")) {
                        Func.cmbSelectInput(cboxSexPatient, "Male");
                    } else if (RegisterInfo2[19].equals("F") || RegisterInfo2[19].equals("P")) {
                        Func.cmbSelectInput(cboxSexPatient, "Female");
                    }
                    // race
                    Func.cmbSelectInput(cboxRacePatient, RegisterInfo2[20]);
                    // nationality
                    Func.cmbSelectInput(cboxNationalityPatient, RegisterInfo2[21]);
                    // home address
                    tfieldHomeAddress.setText(RegisterInfo2[22]);
                    // home address 2
                    tfieldPostalAddressPatient.setText(RegisterInfo2[23]);
                    // postcode
                    Func.cmbSelectInput(cboxPostcode, RegisterInfo2[24]);
                    Func.cmbSelectInput(cboxPostalPostcode, RegisterInfo2[24]);
                    // country
                    Func.cmbSelectInput(cboxCountryPatient, RegisterInfo2[25]);
                    Func.cmbSelectInput(cboxPostalCountryPatient, RegisterInfo2[25]);
                    // phone no
                    tfieldHomephonePatient.setText(RegisterInfo2[26]);
                    tfieldHandphoneNoPatient.setText(RegisterInfo2[26]);
                    
                } else {
                    button_old();
                    setsaveStatus(false);
                    //show patient information on registration screen
                    tfieldPMINoRegistration.setText(RegisterInfo2[0]);
                    tfieldNameRegistration.setText(RegisterInfo2[1]);
                    tfieldNewICNoRegistration.setText(RegisterInfo2[2]);
                    tfieldOldICNoRegistration.setText(RegisterInfo2[3]);
                    //cboxIdTypeRegistration.setSelectedItem(RegisterInfo2[4]);
                    cboxIdTypeRegistration.getModel().setSelectedItem(RegisterInfo2[4]);
                    tfieldIdNoRegistration.setText(RegisterInfo2[5]);
                    //cboxEligibilityCategoryRegistration.setSelectedItem(RegisterInfo2[6]);
                    //cboxEligibilityTypeRegistration.setSelectedItem(RegisterInfo2[7]);
                    cboxEligibilityCategoryRegistration.getModel().setSelectedItem(RegisterInfo2[6]);
                    cboxEligibilityTypeRegistration.getModel().setSelectedItem(RegisterInfo2[7]);
                    //cboxVisitTypeRegistration.setSelectedItem("Walk-in");
                    cboxVisitTypeRegistration.getModel().setSelectedItem("Walk-in");
                    tfieldChargeRate.setText("1.00");
                    tfieldPaymentAmount.setText("1.00");

                    //show patient biodata on patient master index screen
                    tfieldPMINoPMI.setText(BiodataInfo2[0]);
                    tfieldTempPMINo.setText(BiodataInfo2[1]);
                    tfieldPatientName.setText(BiodataInfo2[2]);
                    // cboxTitle.setSelectedItem(BiodataInfo2[3]);
                    cboxTitle.getModel().setSelectedItem(BiodataInfo2[3]);
                    tfieldNewICNoPatient.setText(BiodataInfo2[4]);
                    tfieldOldICNoPatient.setText(BiodataInfo2[5]);
                    //cboxIdTypePatient.setSelectedItem(BiodataInfo2[6]);
                    cboxIdTypePatient.getModel().setSelectedItem(BiodataInfo2[6]);
                    tfieldIdNoPatient.setText(BiodataInfo2[7]);
                    //cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo2[8]);
                    //cboxEligibilityTypePatient.setSelectedItem(BiodataInfo2[9]);
                    cboxEligibilityCategoryPatient.getModel().setSelectedItem(BiodataInfo2[8]);
                    cboxEligibilityTypePatient.getModel().setSelectedItem(BiodataInfo2[9]);

                    DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
                    Date getDOB = null;
                    try {
                        getDOB = formatterBiodata.parse(BiodataInfo2[10]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOBPatient.setDate(getDOB);

                    /*
                     * new model
                     */
                    cboxSexPatient.getModel().setSelectedItem(BiodataInfo2[11]);
                    cboxMaritalStatusPatient.getModel().setSelectedItem(BiodataInfo2[12]);
                    cboxRacePatient.getModel().setSelectedItem(BiodataInfo2[13]);
                    cboxNationalityPatient.getModel().setSelectedItem(BiodataInfo2[14]);
                    cboxReligionPatient.getModel().setSelectedItem(BiodataInfo2[15]);
                    cboxStatePatient.getModel().setSelectedItem(BiodataInfo2[25]);
                    cboxCountryPatient.getModel().setSelectedItem(BiodataInfo2[26]);
                    cboxPostalStatePatient.getModel().setSelectedItem(BiodataInfo2[32]);
                    cboxPostalCountryPatient.getModel().setSelectedItem(BiodataInfo2[33]);

//                        cboxSexPatient.setSelectedItem(BiodataInfo2[11]);
//                        cboxMaritalStatusPatient.setSelectedItem(BiodataInfo2[12]);
//                        cboxRacePatient.setSelectedItem(BiodataInfo2[13]);
//                        cboxNationalityPatient.setSelectedItem(BiodataInfo2[14]);
//                        cboxReligionPatient.setSelectedItem(BiodataInfo2[15]);
                    cboxBloodTypePatient.setSelectedItem(BiodataInfo2[16]);
                    cboxBloodRhesusPatient.setSelectedItem(BiodataInfo2[17]);
                    cboxAllergyPatient.setSelectedItem(BiodataInfo2[18]);
                    cboxChronicDiseasePatient.setSelectedItem(BiodataInfo2[19]);
                    cboxOrganDonorPatient.setSelectedItem(BiodataInfo2[20]);
                    tfieldHomeAddress.setText(BiodataInfo2[21]);
                    cboxDistrictCode.setSelectedItem(BiodataInfo2[22]);
                    cboxTownCode.setSelectedItem(BiodataInfo2[23]);
                    cboxPostcode.setSelectedItem(BiodataInfo2[24]);
                    //cboxStatePatient.setSelectedItem(BiodataInfo2[25]);
                    // cboxCountryPatient.setSelectedItem(BiodataInfo2[26]);
                    tfieldHomephonePatient.setText(BiodataInfo2[27]);
                    tfieldPostalAddressPatient.setText(BiodataInfo2[28]);
                    cboxPostalDistrict.setSelectedItem(BiodataInfo2[29]);
                    cboxPostalTown.setSelectedItem(BiodataInfo2[30]);
                    cboxPostalPostcode.setSelectedItem(BiodataInfo2[31]);
                    // cboxPostalStatePatient.setSelectedItem(BiodataInfo2[32]);
                    // cboxPostalCountryPatient.setSelectedItem(BiodataInfo2[33]);
                    tfieldHandphoneNoPatient.setText(BiodataInfo2[34]);


                    //show patient's employment information on employment screen
                    tfieldPmiEmployment.setText(EmploymentInfo2[0]);
                    tfieldEmploymentSequenceNo.setText(EmploymentInfo2[1]);
                    tfieldEmployerCode.setText(EmploymentInfo2[2]);
                    tfieldEmployerName.setText(EmploymentInfo2[3]);
                    tfieldOccupationCode.setText(EmploymentInfo2[4]);

                    DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
                    Date getJoinedDateEmployment = null;

                    try {
                        getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo2[5]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldJoinedDate.setDate(getJoinedDateEmployment);

                    cboxIncomeRange.setSelectedItem(EmploymentInfo2[6]);
                    cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo2[7]);

                    Date getCreatedDateEmployment = null;
                    try {
                        getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo2[8]);
                    } catch (ParseException ex) {
                        
                    }

                    tfieldCreatedDate.setDate(getCreatedDateEmployment);
                    cboxEmploymentStatus.setSelectedItem(EmploymentInfo2[9]);

                    //show patient's next of kin  information on next of kin screen
                    tfieldPmiNextOfKin.setText(NOKInfo2[0]);
                    tfieldNextOfKinSequenceNo.setText(NOKInfo2[1]);
                    cboxNOKRelationship.setSelectedItem(NOKInfo2[2]);
                    tfieldNextOfKinName.setText(NOKInfo2[3]);
                    tfieldNewICNo.setText(NOKInfo2[4]);
                    tfieldOldICNo.setText(NOKInfo2[5]);
                    cboxIdTypeNOK.setSelectedItem(NOKInfo2[6]);
                    tfieldIdNoNOK.setText(NOKInfo2[7]);

                    DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
                    Date getdobNOK = null;
                    try {
                        //System.out.println(nokInfo[8]);
                        getdobNOK = formatterNOK.parse(NOKInfo2[8]);
                        //System.out.println(getdobNOK);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOB.setDate(getdobNOK);
                    tfieldOccupationNOK.setText(NOKInfo2[9]);
                    tareaAddressNOK.setText(NOKInfo2[10]);
                    cboxDistrictNOK.setSelectedItem(NOKInfo2[11]);
                    cboxTownNOK.setSelectedItem(NOKInfo2[12]);
                    cboxPostcodeNOK.setSelectedItem(NOKInfo2[13]);
                    cboxStateNOK.setSelectedItem(NOKInfo2[14]);
                    cboxCountryNOK.setSelectedItem(NOKInfo2[15]);
                    tfieldHpNoNOK.setText(NOKInfo2[16]);
                    tfieldHomephoneNoNOK.setText(NOKInfo2[17]);
                    tfieldEmailNOK.setText(NOKInfo2[18]);

                    //show patient's family information on family screen
                    tfieldPmiFamily.setText(FamilyInfo2[0]);
                    tfieldFamilySequenceNo.setText(FamilyInfo2[1]);
                    cboxFamilyRelationship.setSelectedItem(FamilyInfo2[2]);
                    tareaFamilyMemberPMI.setText(FamilyInfo2[3]);
                    tareaFamilyMemberName.setText(FamilyInfo2[4]);
                    tfieldOccupationFamily.setText(FamilyInfo2[5]);

                    //show patient's medical insurance information on insurance screen
                    tfieldPmiInsurance.setText(InsuranceInfo2[0]);
                    cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo2[1]);
                    tfieldPolicyNo.setText(InsuranceInfo2[2]);

                    DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
                    Date getMaturityDateInsurance = null;
                    try {
                        getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo2[3]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldMaturityDate.setDate(getMaturityDateInsurance);
                    cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo2[4]);
                    CboxPolicyStatus.setSelectedItem(InsuranceInfo2[5]);

                }
            } catch (ClassNotFoundException ex) {
                
            } catch (SQLException ex) {
                
            }
        }

        //SEARCH USING ID TYPE AND ID NO
        if ((tfieldIDSearchRegister.getText() != null) && (cboxIdTypeRegister.getSelectedIndex() != 0) && (tfieldIDSearchRegister.getText().length() != 0) && (tfieldIDSearchRegister.getText() != "")) {
            String[] RegisterInfo3 = {};
            String[] BiodataInfo3 = {};
            String[] EmploymentInfo3 = {};
            String[] NOKInfo3 = {};
            String[] FamilyInfo3 = {};
            String[] InsuranceInfo3 = {};

            Patient patientRegister3 = new Patient(this);
            try {
                RegisterInfo3 = patientRegister3.getRegisterBiodataUsingID(tfieldIDSearchRegister.getText(), cboxIdTypeRegister.getSelectedItem().toString());
                if (RegisterInfo3[0] != null) {
                    BiodataInfo3 = trim(patientRegister3.getBiodataUsingNewIC(RegisterInfo3[0]));
                    EmploymentInfo3 = trim(patientRegister3.getEmploymentDetailUsingNewIC(RegisterInfo3[0]));
                    NOKInfo3 = trim(patientRegister3.getNokDetailUsingNewIC(RegisterInfo3[0]));
                    FamilyInfo3 = trim(patientRegister3.getFamilyDetailUsingNewIC(RegisterInfo3[0]));
                    InsuranceInfo3 = trim(patientRegister3.getInsuranceDetailUsingNewIC(RegisterInfo3[0]));
                }

                if (RegisterInfo3[0] == null) {
                    
                    if (isRecognizedPatient == false) {
                        tabpanel.setSelectedIndex(0);
                        return;
                    }
                    
                    String idx = tfieldIDSearchRegister.getText();
                    String typex = cboxIdTypeRegister.getSelectedItem().toString();
                    
                    JOptionPane.showMessageDialog(null, "Patient biodata is not existed in the database. Please proceed to Patient Master Index Form to fill in information !!!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    //Set tab to Registration
                    
                    clearForm();
                    generateAllAutogenerateNo(numCheckDigit);

                    tabpanel.setSelectedIndex(1);
                    tfieldIdNoPatient.setText(idx);
                    Func.cmbSelectInput(cboxIdTypePatient, typex);
                    
                    button_new();
                    
                    // id no
                    for (int i = 0; i <= 26; i++) {
                        S.oln(i+" shikin:"+RegisterInfo3[i]);
                    }
                    
                    // date of birth
                    try {
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(RegisterInfo3[18]);
                        tfieldDOBPatient.setDate(date1);
                    } catch (Exception e) {
                        System.out.println("Date Parse Error: "+e.getMessage());
                    }
                    
                    // name
                    tfieldPatientName.setText(RegisterInfo3[17]);
                    
                    // id type
                    if (RegisterInfo3[12].equals("0")) {
                        Func.cmbSelectInput(cboxIdTypePatient, "Staff No.");
                    } else {
                        Func.cmbSelectInput(cboxIdTypePatient, "Matric No.");
                    }
                    if (RegisterInfo3[11].equals("F")) {
                        Func.cmbSelectInput(cboxIdTypePatient, "Foreigner");
                    }
                    if (RegisterInfo3[11].equals("F")) {
                        tfieldIdNoPatient.setText(RegisterInfo3[9]);
                    } else {
                        tfieldIdNoPatient.setText(RegisterInfo3[10]);
                        tfieldNewICNoPatient.setText(RegisterInfo3[9]);
                    }
                    // date of birth
                    tfieldDOBPatient.setDate(null);
                    // gender
                    if (RegisterInfo3[19].equals("M") || RegisterInfo3[19].equals("L")) {
                        Func.cmbSelectInput(cboxSexPatient, "Male");
                    } else if (RegisterInfo3[19].equals("F") || RegisterInfo3[19].equals("P")) {
                        Func.cmbSelectInput(cboxSexPatient, "Female");
                    }
                    // race
                    Func.cmbSelectInput(cboxRacePatient, RegisterInfo3[20]);
                    // nationality
                    Func.cmbSelectInput(cboxNationalityPatient, RegisterInfo3[21]);
                    // home address
                    tfieldHomeAddress.setText(RegisterInfo3[22]+", "+RegisterInfo3[20]+", "+RegisterInfo3[21]);
                    // home address 2
                    tfieldPostalAddressPatient.setText(RegisterInfo3[23]);
                    // postcode
                    Func.cmbSelectInput(cboxPostcode, RegisterInfo3[24]);
                    Func.cmbSelectInput(cboxPostalPostcode, RegisterInfo3[24]);
                    // country
                    Func.cmbSelectInput(cboxCountryPatient, RegisterInfo3[25]);
                    Func.cmbSelectInput(cboxPostalCountryPatient, RegisterInfo3[25]);
                    // phone no
                    tfieldHomephonePatient.setText(RegisterInfo3[26]);
                    tfieldHandphoneNoPatient.setText(RegisterInfo3[26]);
                    
                } else {
                    button_old();
                    setsaveStatus(false);
                    //show patient information on registration screen
                    tfieldPMINoRegistration.setText(RegisterInfo3[0]);
                    tfieldNameRegistration.setText(RegisterInfo3[1]);
                    tfieldNewICNoRegistration.setText(RegisterInfo3[2]);
                    tfieldOldICNoRegistration.setText(RegisterInfo3[3]);
                    cboxIdTypeRegistration.setSelectedItem(RegisterInfo3[4]);
                    cboxIdTypeRegistration.getModel().setSelectedItem(RegisterInfo3[4]);
                    tfieldIdNoRegistration.setText(RegisterInfo3[5]);
                    //cboxEligibilityCategoryRegistration.setSelectedItem(RegisterInfo3[6]);
                    //cboxEligibilityTypeRegistration.setSelectedItem(RegisterInfo3[7]);
                    cboxEligibilityCategoryRegistration.getModel().setSelectedItem(RegisterInfo3[6]);
                    cboxEligibilityTypeRegistration.getModel().setSelectedItem(RegisterInfo3[7]);
                    //cboxVisitTypeRegistration.setSelectedItem("Walk-in");
                    cboxVisitTypeRegistration.getModel().setSelectedItem("Walk-in");
                    tfieldChargeRate.setText("5.00");
                    tfieldPaymentAmount.setText("5.00");

                    //show patient biodata on patient master index screen
                    tfieldPMINoPMI.setText(BiodataInfo3[0]);
                    tfieldTempPMINo.setText(BiodataInfo3[1]);
                    tfieldPatientName.setText(BiodataInfo3[2]);
                    // cboxTitle.setSelectedItem(BiodataInfo3[3]);
                    cboxTitle.getModel().setSelectedItem(BiodataInfo3[3]);
                    tfieldNewICNoPatient.setText(BiodataInfo3[4]);
                    tfieldOldICNoPatient.setText(BiodataInfo3[5]);
                    //cboxIdTypePatient.setSelectedItem(BiodataInfo3[6]);
                    cboxIdTypePatient.getModel().setSelectedItem(BiodataInfo3[6]);
                    tfieldIdNoPatient.setText(BiodataInfo3[7]);
                    //cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo3[8]);
                    //cboxEligibilityTypePatient.setSelectedItem(BiodataInfo3[9]);
                    cboxEligibilityCategoryPatient.getModel().setSelectedItem(BiodataInfo3[8]);
                    cboxEligibilityTypePatient.getModel().setSelectedItem(BiodataInfo3[9]);

                    DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
                    Date getDOB = null;
                    try {
                        getDOB = formatterBiodata.parse(BiodataInfo3[10]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOBPatient.setDate(getDOB);

                    /*
                     * new model
                     */
                    cboxSexPatient.getModel().setSelectedItem(BiodataInfo3[11]);
                    cboxMaritalStatusPatient.getModel().setSelectedItem(BiodataInfo3[12]);
                    cboxRacePatient.getModel().setSelectedItem(BiodataInfo3[13]);
                    cboxNationalityPatient.getModel().setSelectedItem(BiodataInfo3[14]);
                    cboxReligionPatient.getModel().setSelectedItem(BiodataInfo3[15]);
                    cboxStatePatient.getModel().setSelectedItem(BiodataInfo3[25]);
                    cboxCountryPatient.getModel().setSelectedItem(BiodataInfo3[26]);
                    cboxPostalStatePatient.getModel().setSelectedItem(BiodataInfo3[32]);
                    cboxPostalCountryPatient.getModel().setSelectedItem(BiodataInfo3[33]);

//                        cboxSexPatient.setSelectedItem(BiodataInfo3[11]);
//                        cboxMaritalStatusPatient.setSelectedItem(BiodataInfo3[12]);
//                        cboxRacePatient.setSelectedItem(BiodataInfo3[13]);
//                        cboxNationalityPatient.setSelectedItem(BiodataInfo3[14]);
//                        cboxReligionPatient.setSelectedItem(BiodataInfo3[15]);
                    cboxBloodTypePatient.setSelectedItem(BiodataInfo3[16]);
                    cboxBloodRhesusPatient.setSelectedItem(BiodataInfo3[17]);
                    cboxAllergyPatient.setSelectedItem(BiodataInfo3[18]);
                    cboxChronicDiseasePatient.setSelectedItem(BiodataInfo3[19]);
                    cboxOrganDonorPatient.setSelectedItem(BiodataInfo3[20]);
                    tfieldHomeAddress.setText(BiodataInfo3[21]);
                    cboxDistrictCode.setSelectedItem(BiodataInfo3[22]);
                    cboxTownCode.setSelectedItem(BiodataInfo3[23]);
                    cboxPostcode.setSelectedItem(BiodataInfo3[24]);
                    //cboxStatePatient.setSelectedItem(BiodataInfo3[25]);
                    //cboxCountryPatient.setSelectedItem(BiodataInfo3[26]);
                    tfieldHomephonePatient.setText(BiodataInfo3[27]);
                    tfieldPostalAddressPatient.setText(BiodataInfo3[28]);
                    cboxPostalDistrict.setSelectedItem(BiodataInfo3[29]);
                    cboxPostalTown.setSelectedItem(BiodataInfo3[30]);
                    cboxPostalPostcode.setSelectedItem(BiodataInfo3[31]);
                    //cboxPostalStatePatient.setSelectedItem(BiodataInfo3[32]);
                    //cboxPostalCountryPatient.setSelectedItem(BiodataInfo3[33]);
                    tfieldHandphoneNoPatient.setText(BiodataInfo3[34]);

                    //show patient's employment information on employment screen
                    tfieldPmiEmployment.setText(EmploymentInfo3[0]);
                    tfieldEmploymentSequenceNo.setText(EmploymentInfo3[1]);
                    tfieldEmployerCode.setText(EmploymentInfo3[2]);
                    tfieldEmployerName.setText(EmploymentInfo3[3]);
                    tfieldOccupationCode.setText(EmploymentInfo3[4]);

                    DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
                    Date getJoinedDateEmployment = null;

                    try {
                        getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo3[5]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldJoinedDate.setDate(getJoinedDateEmployment);

                    cboxIncomeRange.setSelectedItem(EmploymentInfo3[6]);
                    cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo3[7]);

                    Date getCreatedDateEmployment = null;
                    try {
                        getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo3[8]);
                    } catch (ParseException ex) {
                        
                    }

                    tfieldCreatedDate.setDate(getCreatedDateEmployment);
                    cboxEmploymentStatus.setSelectedItem(EmploymentInfo3[9]);

                    //show patient's next of kin  information on next of kin screen
                    tfieldPmiNextOfKin.setText(NOKInfo3[0]);
                    tfieldNextOfKinSequenceNo.setText(NOKInfo3[1]);
                    cboxNOKRelationship.setSelectedItem(NOKInfo3[2]);
                    tfieldNextOfKinName.setText(NOKInfo3[3]);
                    tfieldNewICNo.setText(NOKInfo3[4]);
                    tfieldOldICNo.setText(NOKInfo3[5]);
                    cboxIdTypeNOK.setSelectedItem(NOKInfo3[6]);
                    tfieldIdNoNOK.setText(NOKInfo3[7]);

                    DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
                    Date getdobNOK = null;
                    try {
                        //System.out.println(nokInfo[8]);
                        getdobNOK = formatterNOK.parse(NOKInfo3[8]);
                        //System.out.println(getdobNOK);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldDOB.setDate(getdobNOK);
                    tfieldOccupationNOK.setText(NOKInfo3[9]);
                    tareaAddressNOK.setText(NOKInfo3[10]);
                    cboxDistrictNOK.setSelectedItem(NOKInfo3[11]);
                    cboxTownNOK.setSelectedItem(NOKInfo3[12]);
                    cboxPostcodeNOK.setSelectedItem(NOKInfo3[13]);
                    cboxStateNOK.setSelectedItem(NOKInfo3[14]);
                    cboxCountryNOK.setSelectedItem(NOKInfo3[15]);
                    tfieldHpNoNOK.setText(NOKInfo3[16]);
                    tfieldHomephoneNoNOK.setText(NOKInfo3[17]);
                    tfieldEmailNOK.setText(NOKInfo3[18]);

                    //show patient's family information on family screen
                    tfieldPmiFamily.setText(FamilyInfo3[0]);
                    tfieldFamilySequenceNo.setText(FamilyInfo3[1]);
                    cboxFamilyRelationship.setSelectedItem(FamilyInfo3[2]);
                    tareaFamilyMemberPMI.setText(FamilyInfo3[3]);
                    tareaFamilyMemberName.setText(FamilyInfo3[4]);
                    tfieldOccupationFamily.setText(FamilyInfo3[5]);

                    //show patient's medical insurance information on insurance screen
                    tfieldPmiInsurance.setText(InsuranceInfo3[0]);
                    cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo3[1]);
                    tfieldPolicyNo.setText(InsuranceInfo3[2]);

                    DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
                    Date getMaturityDateInsurance = null;
                    try {
                        getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo3[3]);
                    } catch (ParseException ex) {
                        
                    }
                    tfieldMaturityDate.setDate(getMaturityDateInsurance);
                    cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo3[4]);
                    CboxPolicyStatus.setSelectedItem(InsuranceInfo3[5]);


                }
            } catch (ClassNotFoundException ex) {
                
            } catch (SQLException ex) {
                
            }
        }

        if (tfieldPMISearchRegister.getText().length() == 0 && (tfieldICSearchRegister.getText().length() == 0) && (tfieldIDSearchRegister.getText().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Please key in PMI No or IC No or Identification No to continue searching process!!!", "Error", JOptionPane.INFORMATION_MESSAGE);
            //Set tab to Registration
            tabpanel.setSelectedIndex(1);
        }
    }
    
    private void btnSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPatientActionPerformed
        // TODO add your handling code here:

        searchRegister();
    }//GEN-LAST:event_btnSearchPatientActionPerformed

    private void EnableIDRegister(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnableIDRegister
        // TODO add your handling code here:
        if (tfieldIDSearchRegister.getText().length() <= 1) {
            clearForm();
        }
        if (tfieldIDSearchRegister.getText().length() != 0) {
            tfieldPMISearchRegister.setEditable(false);
            tfieldICSearchRegister.setEditable(false);
        } else {
            tfieldPMISearchRegister.setEditable(true);
            tfieldICSearchRegister.setEditable(true);
        }
        tfieldPMISearchRegister.setText("");
        tfieldICSearchRegister.setText("");
        setDefault();
    }//GEN-LAST:event_EnableIDRegister

    private void EnableICRegister(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnableICRegister
        // TODO add your handling code here:
        if (tfieldICSearchRegister.getText().length() <= 1) {
            clearForm();
        }
        if (tfieldICSearchRegister.getText().length() != 0) {
            tfieldPMISearchRegister.setEditable(false);
            tfieldIDSearchRegister.setEditable(false);
        } else {
            tfieldPMISearchRegister.setEditable(true);
            tfieldIDSearchRegister.setEditable(true);
        }
        tfieldPMISearchRegister.setText("");
        tfieldIDSearchRegister.setText("");
        cboxIdTypeRegister.setSelectedIndex(0);
        setDefault();
    }//GEN-LAST:event_EnableICRegister

    private void EnablePMIRegister(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnablePMIRegister
        // TODO add your handling code here:
        if (tfieldPMISearchRegister.getText().length() <= 1) {
            clearForm();
        }
        if (tfieldPMISearchRegister.getText().length() != 0) {
            tfieldICSearchRegister.setEditable(false);
            tfieldIDSearchRegister.setEditable(false);
        } else {
            tfieldICSearchRegister.setEditable(true);
            tfieldIDSearchRegister.setEditable(true);
        }
        tfieldICSearchRegister.setText("");
        tfieldIDSearchRegister.setText("");
        cboxIdTypeRegister.setSelectedIndex(0);
        setDefault();
    }//GEN-LAST:event_EnablePMIRegister

    private void cboxIdTypeRegisterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxIdTypeRegisterItemStateChanged
        // TODO add your handling code here:
        if (cboxIdTypeRegister.getSelectedIndex() == 0) {
            tfieldIDSearchRegister.setEditable(false);
        } else {
            tfieldIDSearchRegister.setEditable(true);
        }
    }//GEN-LAST:event_cboxIdTypeRegisterItemStateChanged

    private void tfieldICSearchRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfieldICSearchRegisterMouseClicked
        // TODO add your handling code here:
        tfieldICSearchRegister.setEditable(true);
        tfieldIDSearchRegister.setEditable(false);
        tfieldPMISearchRegister.setEditable(false);
        cboxIdTypeRegister.setSelectedIndex(0);
    }//GEN-LAST:event_tfieldICSearchRegisterMouseClicked

    private void tfieldPMISearchRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfieldPMISearchRegisterMouseClicked
        // TODO add your handling code here:
        tfieldICSearchRegister.setEditable(false);
        tfieldIDSearchRegister.setEditable(false);
        tfieldPMISearchRegister.setEditable(true);
        cboxIdTypeRegister.setSelectedIndex(0);
    }//GEN-LAST:event_tfieldPMISearchRegisterMouseClicked

    private void tfieldIDSearchRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfieldIDSearchRegisterMouseClicked
        // TODO add your handling code here:
        tfieldICSearchRegister.setEditable(false);
        tfieldIDSearchRegister.setEditable(true);
        tfieldPMISearchRegister.setEditable(false);
    }//GEN-LAST:event_tfieldIDSearchRegisterMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        clearSearch();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void clearSearch() {
        tfieldPMISearchRegister.setText("");
        tfieldICSearchRegister.setText("");
        tfieldIDSearchRegister.setText("");
        cboxIdTypeRegister.setSelectedIndex(0);
        rbNewRegister.setSelected(true);
        rbOldRegister.setSelected(false);
    }
    
    public void clearForm() {
        
        //tab 0
        tfieldPMINoRegistration.setText("");
        tfieldNameRegistration.setText("");
        tfieldNewICNoRegistration.setText("");
        tfieldOldICNoRegistration.setText("");
        cboxIdTypeRegistration.setSelectedIndex(0);
        tfieldIdNoRegistration.setText("");
        tfieldRegistrationNo.setText("");
        cboxPatientCategoryRegistration.setSelectedIndex(0);
        cboxVisitTypeRegistration.setSelectedIndex(0);
        cboxEmergencyTypeRegistration.setSelectedIndex(0);
        cboxEligibilityCategoryRegistration.setSelectedIndex(0);
        cboxEligibilityTypeRegistration.setSelectedIndex(0);
        cboxDisciplineRegistration.setSelectedIndex(0);
        cboxConsultationRoom.setSelectedIndex(0);
        cboxQueue.setSelectedIndex(0);
        cboxDoctor.setSelectedIndex(0);
        cboxPriorityGroupRegistration.setSelectedIndex(0);
        tfieldChargeRate.setText("");
        tfieldPaymentAmount.setText("");
        //tfieldReceiptNo.setText("");
        cboxPaymentMode.setSelectedIndex(0);
        rbConsultationRoom.setSelected(true);
        cboxConsultationRoom.setEnabled(true);
        cboxDoctor.setEnabled(false);
        cboxQueue.setEnabled(false);
        //tab 1
        tfieldTempPMINo.setText("");
        tfieldPatientName.setText("");
        cboxTitle.setSelectedIndex(0);
        tfieldNewICNoPatient.setText("");
        tfieldOldICNoPatient.setText("");
        cboxIdTypePatient.setSelectedIndex(0);
        tfieldIdNoPatient.setText("");
        cboxEligibilityCategoryPatient.setSelectedIndex(0);
        cboxEligibilityTypePatient.setSelectedIndex(0);
        tfieldDOBPatient.setDate(null);
        cboxSexPatient.setSelectedIndex(0);
        cboxRacePatient.setSelectedIndex(0);
        cboxNationalityPatient.setSelectedIndex(0);
        cboxReligionPatient.setSelectedIndex(0);
        cboxBloodTypePatient.setSelectedIndex(0);
        cboxBloodRhesusPatient.setSelectedIndex(0);
        cboxMaritalStatusPatient.setSelectedIndex(0);
        cboxAllergyPatient.setSelectedIndex(0);
        cboxOrganDonorPatient.setSelectedIndex(0);
        cboxChronicDiseasePatient.setSelectedIndex(0);
        tfieldHomeAddress.setText("");
        cboxDistrictCode.setSelectedIndex(0);
        cboxTownCode.setSelectedIndex(0);
        cboxPostcode.setSelectedIndex(0);
        cboxStatePatient.setSelectedIndex(0);
        cboxCountryPatient.setSelectedIndex(0);
        tfieldHomephonePatient.setText("");
        tfieldPostalAddressPatient.setText("");
        cboxPostalDistrict.setSelectedIndex(0);
        cboxPostalTown.setSelectedIndex(0);
        cboxPostalPostcode.setSelectedIndex(0);
        cboxPostalStatePatient.setSelectedIndex(0);
        cboxPostalCountryPatient.setSelectedIndex(0);
        tfieldHandphoneNoPatient.setText("");

        String[] autogeneratePMI = {};
        Patient autogenerate = new Patient(this);

//        try {
//            autogeneratePMI = autogenerate.getAutogeneratePMI();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        tfieldPMINoPMI.setText("PMS" + autogeneratePMI[0]);
//        tfieldPmiEmployment.setText("PMS" + autogeneratePMI[0]);
//        tfieldPmiNextOfKin.setText("PMS" + autogeneratePMI[0]);
//        tfieldPmiFamily.setText("PMS" + autogeneratePMI[0]);
//        tfieldPmiInsurance.setText("PMS" + autogeneratePMI[0]);
        //tab 2
        //tfieldPmiEmployment.setText("");
        //tfieldEmploymentSequenceNo.setText("");
        tfieldEmployerCode.setText("");
        tfieldEmployerName.setText("");
        tfieldOccupationCode.setText("");
        tfieldJoinedDate.setDate(null);
        tfieldCreatedDate.setDate(null);
        cboxHealthFacilityEmployment.setSelectedIndex(0);
        cboxIncomeRange.setSelectedIndex(0);
        cboxEmploymentStatus.setSelectedIndex(0);

        String[] autogenerateESNo = {};
        Patient autogenerateES = new Patient(this);

//        try {
//            autogenerateESNo = autogenerateES.getAutogenerateESNo();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        tfieldEmploymentSequenceNo.setText("ES" + autogenerateESNo[0]);
        //tab 3
        //tfieldPmiNextOfKin.setText("");
        //tfieldNextOfKinSequenceNo.setText("");
        cboxNOKRelationship.setSelectedIndex(0);
        tfieldNextOfKinName.setText("");
        tfieldNewICNo.setText("");
        tfieldOldICNo.setText("");
        cboxIdTypeNOK.setSelectedIndex(0);
        tfieldIdNoNOK.setText("");
        tfieldDOB.setDate(null);
        tareaAddressNOK.setText("");
        cboxDistrictNOK.setSelectedIndex(0);
        cboxTownNOK.setSelectedIndex(0);
        cboxPostcodeNOK.setSelectedIndex(0);
        cboxStateNOK.setSelectedIndex(0);
        cboxCountryNOK.setSelectedIndex(0);
        tfieldHpNoNOK.setText("");
        tfieldHomephoneNoNOK.setText("");
        tfieldEmailNOK.setText("");
        tfieldOccupationNOK.setText("");

        String[] autogenerateNOKNo = {};
        Patient autogenerateNOK = new Patient(this);

//        try {
//            autogenerateNOKNo = autogenerateNOK.getAutogenerateNOKNo();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        tfieldNextOfKinSequenceNo.setText("NOKS" + autogenerateNOKNo[0]);
        //tab 4
        //tfieldPmiFamily.setText("");
        tfieldFamilySequenceNo.setText("");
        tareaFamilyMemberName.setText("");
        cboxFamilyRelationship.setSelectedIndex(0);
        tareaFamilyMemberPMI.setText("");
        tfieldOccupationFamily.setText("");

        String[] autogenerateFSNo = {};
        Patient autogenerateFS = new Patient(this);

//        try {
//            autogenerateFSNo = autogenerateFS.getAutogenerateFSNo();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        tfieldFamilySequenceNo.setText("FS" + autogenerateFSNo[0]);
        //tab 5
        //tfieldPmiInsurance.setText("");
        cboxInsuranceCompanyName.setSelectedIndex(0);
        tfieldPolicyNo.setText("");
        tfieldMaturityDate.setDate(null);
        cboxHealthFacilityInsurance.setSelectedIndex(0);
        CboxPolicyStatus.setSelectedIndex(0);
        
    }
        
    //to enable pmino textfield family only        
    //to enable icno textfield family only
    //to enable idno textfield family only        
    //to enable pmino textfield next of kin only        
    //to enable icno textfield next of kin only        
    //to enable idno textfield next of kin only        
    //to enable pmino textfield employment only        
    //to enable icno textfield employment only
    //to enable idno textfield employment only        
    //to enable pmino textfield insurance only        
    //to enable icno textfield insurance only        
    //to enable idno textfield insurance only        
    //to enable pmino textfield person master index only        
    //to enable icno textfield person master index only        
    //to enable idno textfield person master index only        
    //to enable pmino textfield register only        
    //to enable icno textfield register only        
    //to enable idno textfield register only
    //register patient and create patient queue
        
        public String setExceptional(String data) {
            if(data.contains("Walk-in")) {
                return "Walk in";
            } else {
                return data;
            }
        }
        
        public boolean isFill(String data) {
            data = setExceptional(data);
            return (!data.contains("Select") && !data.contains("-"));
        }
        
        public void colorCompulsory() {
            if(!isFill(cboxPatientCategoryRegistration.getSelectedItem().toString())) {
                jLabel12.setForeground(Color.RED); 
            } else {
                jLabel12.setForeground(Color.BLACK);
            }
            if(!isFill(cboxVisitTypeRegistration.getSelectedItem().toString())) {
                jLabel13.setForeground(Color.RED); 
            } else {
                jLabel13.setForeground(Color.BLACK);
            }
            if(!isFill(cboxDisciplineRegistration.getSelectedItem().toString())) {
                jLabel17.setForeground(Color.RED); 
            } else {
                jLabel17.setForeground(Color.BLACK);
            }
            if(!isFill(cboxPriorityGroupRegistration.getSelectedItem().toString())) {
                jLabel20.setForeground(Color.RED); 
            } else {
                jLabel20.setForeground(Color.BLACK);
            }
            if (rbConsultationRoom.isSelected()) {
                if (!isFill(cboxConsultationRoom.getSelectedItem().toString())) {
                    rbConsultationRoom.setForeground(Color.RED);
                } else {
                    rbConsultationRoom.setForeground(Color.BLACK);
                }
            } else if (rbQueue.isSelected()) {
                if (!isFill(cboxQueue.getSelectedItem().toString())) {
                    rbQueue.setForeground(Color.RED);
                } else {
                    rbQueue.setForeground(Color.BLACK);
                }
            } else if (rbDoctor.isSelected()) {
                if (!isFill(cboxDoctor.getSelectedItem().toString())) {
                    rbDoctor.setForeground(Color.RED);
                } else {
                    rbDoctor.setForeground(Color.BLACK);
                }
            }
        }
        
    //read patient employment detail through MyKad        
    //read patient's family information through mykad        
    //read patient medical insurance information from database
    //set appointment detail into textfield
    public void setSelectedAppointment(String selectedAppointment, String tab) {
        String[] AppointmentInfo = {};
        String[] BiodataInfo = {};
        String[] EmploymentInfo = {};
        String[] NOKInfo = {};
        String[] FamilyInfo = {};
        String[] InsuranceInfo = {};

        Patient appointment = new Patient(this);

        try {
            AppointmentInfo = appointment.getAppointmentBiodata(selectedAppointment, "");

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        tfieldPMINoRegistration.setText(AppointmentInfo[0]);
        tfieldNameRegistration.setText(AppointmentInfo[2]);
        tfieldNewICNoRegistration.setText(AppointmentInfo[4]);
        tfieldOldICNoRegistration.setText(AppointmentInfo[5]);
        cboxIdTypeRegistration.setSelectedItem(AppointmentInfo[6]);
        tfieldIdNoRegistration.setText(AppointmentInfo[7]);
        cboxEligibilityCategoryRegistration.setSelectedItem(AppointmentInfo[8]);
        cboxEligibilityTypeRegistration.setSelectedItem(AppointmentInfo[9]);
        cboxVisitTypeRegistration.setSelectedItem("Appointment");
        if (tfieldNewICNoRegistration.getText() != null && (tfieldNewICNoRegistration.getText().length() != 1)) {
            tfieldChargeRate.setText("1.00");
            tfieldPaymentAmount.setText("1.00");
        }
        if (tfieldOldICNoRegistration.getText() != null && (tfieldOldICNoRegistration.getText().length() != 1)) {
            tfieldChargeRate.setText("1.00");
            tfieldPaymentAmount.setText("1.00");
        }
        if (tfieldIdNoRegistration.getText() != null && (tfieldIdNoRegistration.getText().length() != 1)) {
            tfieldChargeRate.setText("1.00");
            tfieldPaymentAmount.setText("1.00");
        }

        try {
            BiodataInfo = appointment.getBiodata(tfieldPMINoRegistration.getText());
            EmploymentInfo = appointment.getEmploymentDetail(tfieldPMINoRegistration.getText());
            NOKInfo = appointment.getNokDetail(tfieldPMINoRegistration.getText());
            FamilyInfo = appointment.getFamilyDetail(tfieldPMINoRegistration.getText());
            InsuranceInfo = appointment.getInsuranceDetail(tfieldPMINoRegistration.getText());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //show patient's biodata on patient master index screen
        tfieldPMINoPMI.setText(BiodataInfo[0]);
        tfieldTempPMINo.setText(BiodataInfo[1]);
        tfieldPatientName.setText(BiodataInfo[2]);
        cboxTitle.setSelectedItem(BiodataInfo[3]);
        tfieldNewICNoPatient.setText(BiodataInfo[4]);
        tfieldOldICNoPatient.setText(BiodataInfo[5]);
        cboxIdTypePatient.setSelectedItem(BiodataInfo[6]);
        tfieldIdNoPatient.setText(BiodataInfo[7]);
        cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo[8]);
        cboxEligibilityTypePatient.setSelectedItem(BiodataInfo[9]);

        DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
        Date getDOB = null;
        try {
            getDOB = formatterBiodata.parse(BiodataInfo[10]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOBPatient.setDate(getDOB);

        cboxSexPatient.setSelectedItem(BiodataInfo[11]);
        cboxMaritalStatusPatient.setSelectedItem(BiodataInfo[12]);
        cboxRacePatient.setSelectedItem(BiodataInfo[13]);
        cboxNationalityPatient.setSelectedItem(BiodataInfo[14]);
        cboxReligionPatient.setSelectedItem(BiodataInfo[15]);
        cboxBloodTypePatient.setSelectedItem(BiodataInfo[16]);
        cboxBloodRhesusPatient.setSelectedItem(BiodataInfo[17]);
        cboxAllergyPatient.setSelectedItem(BiodataInfo[18]);
        cboxChronicDiseasePatient.setSelectedItem(BiodataInfo[19]);
        cboxOrganDonorPatient.setSelectedItem(BiodataInfo[20]);
        tfieldHomeAddress.setText(BiodataInfo[21]);
        cboxDistrictCode.setSelectedItem(BiodataInfo[22]);
        cboxTownCode.setSelectedItem(BiodataInfo[23]);
        cboxPostcode.setSelectedItem(BiodataInfo[24]);
        cboxStatePatient.setSelectedItem(BiodataInfo[25]);
        cboxCountryPatient.setSelectedItem(BiodataInfo[26]);
        tfieldHomephonePatient.setText(BiodataInfo[27]);
        tfieldPostalAddressPatient.setText(BiodataInfo[28]);
        cboxPostalDistrict.setSelectedItem(BiodataInfo[29]);
        cboxPostalTown.setSelectedItem(BiodataInfo[30]);
        cboxPostalPostcode.setSelectedItem(BiodataInfo[31]);
        cboxPostalStatePatient.setSelectedItem(BiodataInfo[32]);
        cboxPostalCountryPatient.setSelectedItem(BiodataInfo[33]);
        tfieldHandphoneNoPatient.setText(BiodataInfo[34]);

        //show patient's employment information on employment screen
        tfieldPmiEmployment.setText(EmploymentInfo[0]);
        tfieldEmploymentSequenceNo.setText(EmploymentInfo[1]);
        tfieldEmployerCode.setText(EmploymentInfo[2]);
        tfieldEmployerName.setText(EmploymentInfo[3]);
        tfieldOccupationCode.setText(EmploymentInfo[4]);

        DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
        Date getJoinedDateEmployment = null;

        try {
            getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo[5]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldJoinedDate.setDate(getJoinedDateEmployment);

        cboxIncomeRange.setSelectedItem(EmploymentInfo[6]);
        cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo[7]);

        Date getCreatedDateEmployment = null;
        try {
            getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo[8]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfieldCreatedDate.setDate(getCreatedDateEmployment);
        cboxEmploymentStatus.setSelectedItem(EmploymentInfo[9]);

        //show patient's next of kin  information on next of kin screen
        tfieldPmiNextOfKin.setText(NOKInfo[0]);
        tfieldNextOfKinSequenceNo.setText(NOKInfo[1]);
        cboxNOKRelationship.setSelectedItem(NOKInfo[2]);
        tfieldNextOfKinName.setText(NOKInfo[3]);
        tfieldNewICNo.setText(NOKInfo[4]);
        tfieldOldICNo.setText(NOKInfo[5]);
        cboxIdTypeNOK.setSelectedItem(NOKInfo[6]);
        tfieldIdNoNOK.setText(NOKInfo[7]);

        DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
        Date getdobNOK = null;
        try {
            //System.out.println(nokInfo[8]);
            getdobNOK = formatterNOK.parse(NOKInfo[8]);
            //System.out.println(getdobNOK);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOB.setDate(getdobNOK);
        tfieldOccupationNOK.setText(NOKInfo[9]);
        tareaAddressNOK.setText(NOKInfo[10]);
        cboxDistrictNOK.setSelectedItem(NOKInfo[11]);
        cboxTownNOK.setSelectedItem(NOKInfo[12]);
        cboxPostcodeNOK.setSelectedItem(NOKInfo[13]);
        cboxStateNOK.setSelectedItem(NOKInfo[14]);
        cboxCountryNOK.setSelectedItem(NOKInfo[15]);
        tfieldHpNoNOK.setText(NOKInfo[16]);
        tfieldHomephoneNoNOK.setText(NOKInfo[17]);
        tfieldEmailNOK.setText(NOKInfo[18]);

        //show patient's family information on family screen
        tfieldPmiFamily.setText(FamilyInfo[0]);
        tfieldFamilySequenceNo.setText(FamilyInfo[1]);
        cboxFamilyRelationship.setSelectedItem(FamilyInfo[2]);
        tareaFamilyMemberPMI.setText(FamilyInfo[3]);
        tareaFamilyMemberName.setText(FamilyInfo[4]);
        tfieldOccupationFamily.setText(FamilyInfo[5]);

        //show patient's medical insurance information on insurance screen
        tfieldPmiInsurance.setText(InsuranceInfo[0]);
        cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo[1]);
        tfieldPolicyNo.setText(InsuranceInfo[2]);

        DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
        Date getMaturityDateInsurance = null;
        try {
            getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo[3]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldMaturityDate.setDate(getMaturityDateInsurance);
        cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo[4]);
        CboxPolicyStatus.setSelectedItem(InsuranceInfo[5]);
    }

    //set referral detail into textfield
    public void setSelectedName(String selectedName, String tab) {
        String[] ReferralInfo = {};
        String[] BiodataInfo = {};
        String[] EmploymentInfo = {};
        String[] NOKInfo = {};
        String[] FamilyInfo = {};
        String[] InsuranceInfo = {};

        Patient referral = new Patient(this);

        try {
            ReferralInfo = referral.getReferralBiodata(selectedName);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        tfieldPMINoRegistration.setText(ReferralInfo[0]);
        tfieldNameRegistration.setText(ReferralInfo[2]);
        tfieldNewICNoRegistration.setText(ReferralInfo[4]);
        tfieldOldICNoRegistration.setText(ReferralInfo[5]);
        cboxIdTypeRegistration.setSelectedItem(ReferralInfo[6]);
        tfieldIdNoRegistration.setText(ReferralInfo[7]);
        cboxEligibilityCategoryRegistration.setSelectedItem(ReferralInfo[8]);
        cboxEligibilityTypeRegistration.setSelectedItem(ReferralInfo[9]);
        cboxVisitTypeRegistration.setSelectedItem("Referral");
        if (tfieldNewICNoRegistration.getText() != null && (tfieldNewICNoRegistration.getText().length() != 1)) {
            tfieldChargeRate.setText("1.00");
            tfieldPaymentAmount.setText("1.00");
        }
        if (tfieldOldICNoRegistration.getText() != null && (tfieldOldICNoRegistration.getText().length() != 1)) {
            tfieldChargeRate.setText("1.00");
            tfieldPaymentAmount.setText("1.00");
        }
        if (tfieldIdNoRegistration.getText() != null && (tfieldIdNoRegistration.getText().length() != 1)) {
            tfieldChargeRate.setText("5.00");
            tfieldPaymentAmount.setText("5.00");
        }


        try {
            BiodataInfo = referral.getBiodata(tfieldPMINoRegistration.getText());
            EmploymentInfo = referral.getEmploymentDetail(tfieldPMINoRegistration.getText());
            NOKInfo = referral.getNokDetail(tfieldPMINoRegistration.getText());
            FamilyInfo = referral.getFamilyDetail(tfieldPMINoRegistration.getText());
            InsuranceInfo = referral.getInsuranceDetail(tfieldPMINoRegistration.getText());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }


        //show patient's biodata on patient master index screen
        tfieldPMINoPMI.setText(BiodataInfo[0]);
        tfieldTempPMINo.setText(BiodataInfo[1]);
        tfieldPatientName.setText(BiodataInfo[2]);
        cboxTitle.setSelectedItem(BiodataInfo[3]);
        tfieldNewICNoPatient.setText(BiodataInfo[4]);
        tfieldOldICNoPatient.setText(BiodataInfo[5]);
        cboxIdTypePatient.setSelectedItem(BiodataInfo[6]);
        tfieldIdNoPatient.setText(BiodataInfo[7]);
        cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo[8]);
        cboxEligibilityTypePatient.setSelectedItem(BiodataInfo[9]);

        DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
        Date getDOB = null;
        try {
            getDOB = formatterBiodata.parse(BiodataInfo[10]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOBPatient.setDate(getDOB);

        cboxSexPatient.setSelectedItem(BiodataInfo[11]);
        cboxMaritalStatusPatient.setSelectedItem(BiodataInfo[12]);
        cboxRacePatient.setSelectedItem(BiodataInfo[13]);
        cboxNationalityPatient.setSelectedItem(BiodataInfo[14]);
        cboxReligionPatient.setSelectedItem(BiodataInfo[15]);
        cboxBloodTypePatient.setSelectedItem(BiodataInfo[16]);
        cboxBloodRhesusPatient.setSelectedItem(BiodataInfo[17]);
        cboxAllergyPatient.setSelectedItem(BiodataInfo[18]);
        cboxChronicDiseasePatient.setSelectedItem(BiodataInfo[19]);
        cboxOrganDonorPatient.setSelectedItem(BiodataInfo[20]);
        tfieldHomeAddress.setText(BiodataInfo[21]);
        cboxDistrictCode.setSelectedItem(BiodataInfo[22]);
        cboxTownCode.setSelectedItem(BiodataInfo[23]);
        cboxPostcode.setSelectedItem(BiodataInfo[24]);
        cboxStatePatient.setSelectedItem(BiodataInfo[25]);
        cboxCountryPatient.setSelectedItem(BiodataInfo[26]);
        tfieldHomephonePatient.setText(BiodataInfo[27]);
        tfieldPostalAddressPatient.setText(BiodataInfo[28]);
        cboxPostalDistrict.setSelectedItem(BiodataInfo[29]);
        cboxPostalTown.setSelectedItem(BiodataInfo[30]);
        cboxPostalPostcode.setSelectedItem(BiodataInfo[31]);
        cboxPostalStatePatient.setSelectedItem(BiodataInfo[32]);
        cboxPostalCountryPatient.setSelectedItem(BiodataInfo[33]);
        tfieldHandphoneNoPatient.setText(BiodataInfo[34]);

        //show patient's employment information on employment screen
        tfieldPmiEmployment.setText(EmploymentInfo[0]);
        tfieldEmploymentSequenceNo.setText(EmploymentInfo[1]);
        tfieldEmployerCode.setText(EmploymentInfo[2]);
        tfieldEmployerName.setText(EmploymentInfo[3]);
        tfieldOccupationCode.setText(EmploymentInfo[4]);

        DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
        Date getJoinedDateEmployment = null;

        try {
            getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo[5]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldJoinedDate.setDate(getJoinedDateEmployment);

        cboxIncomeRange.setSelectedItem(EmploymentInfo[6]);
        cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo[7]);

        Date getCreatedDateEmployment = null;
        try {
            getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo[8]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfieldCreatedDate.setDate(getCreatedDateEmployment);
        cboxEmploymentStatus.setSelectedItem(EmploymentInfo[9]);

        //show patient's next of kin  information on next of kin screen
        tfieldPmiNextOfKin.setText(NOKInfo[0]);
        tfieldNextOfKinSequenceNo.setText(NOKInfo[1]);
        cboxNOKRelationship.setSelectedItem(NOKInfo[2]);
        tfieldNextOfKinName.setText(NOKInfo[3]);
        tfieldNewICNo.setText(NOKInfo[4]);
        tfieldOldICNo.setText(NOKInfo[5]);
        cboxIdTypeNOK.setSelectedItem(NOKInfo[6]);
        tfieldIdNoNOK.setText(NOKInfo[7]);

        DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
        Date getdobNOK = null;
        try {
            //System.out.println(nokInfo[8]);
            getdobNOK = formatterNOK.parse(NOKInfo[8]);
            //System.out.println(getdobNOK);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOB.setDate(getdobNOK);
        tfieldOccupationNOK.setText(NOKInfo[9]);
        tareaAddressNOK.setText(NOKInfo[10]);
        cboxDistrictNOK.setSelectedItem(NOKInfo[11]);
        cboxTownNOK.setSelectedItem(NOKInfo[12]);
        cboxPostcodeNOK.setSelectedItem(NOKInfo[13]);
        cboxStateNOK.setSelectedItem(NOKInfo[14]);
        cboxCountryNOK.setSelectedItem(NOKInfo[15]);
        tfieldHpNoNOK.setText(NOKInfo[16]);
        tfieldHomephoneNoNOK.setText(NOKInfo[17]);
        tfieldEmailNOK.setText(NOKInfo[18]);

        //show patient's family information on family screen
        tfieldPmiFamily.setText(FamilyInfo[0]);
        tfieldFamilySequenceNo.setText(FamilyInfo[1]);
        cboxFamilyRelationship.setSelectedItem(FamilyInfo[2]);
        tareaFamilyMemberPMI.setText(FamilyInfo[3]);
        tareaFamilyMemberName.setText(FamilyInfo[4]);
        tfieldOccupationFamily.setText(FamilyInfo[5]);

        //show patient's medical insurance information on insurance screen
        tfieldPmiInsurance.setText(InsuranceInfo[0]);
        cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo[1]);
        tfieldPolicyNo.setText(InsuranceInfo[2]);

        DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
        Date getMaturityDateInsurance = null;
        try {
            getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo[3]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldMaturityDate.setDate(getMaturityDateInsurance);
        cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo[4]);
        CboxPolicyStatus.setSelectedItem(InsuranceInfo[5]);

    }

        public void button_new() {
            btnSavePatientBiodata.setEnabled(true);
            btnUpdatePatientBiodata.setEnabled(false);
            btnClearPatientBiodata.setEnabled(true);
//            btnSaveEmployment.setEnabled(true);
//            btnUpdateEmploymentInfo.setEnabled(false);
//            btnClearEmployment.setEnabled(true);
//            btnSaveNOK.setEnabled(true);
//            btnUpdateNokInfo.setEnabled(false);
//            btnClearNOK.setEnabled(true);
//            btnSaveFamilyInfo.setEnabled(true);
//            btnUpdateFamilyInfo.setEnabled(false);
//            btnClearFamily.setEnabled(true);
//            btnSaveInsurance.setEnabled(true);
//            btnUpdateInsurance.setEnabled(false);
//            btnClearInsuranceInfo.setEnabled(true);
        }
        
        public void button_old() {
            btnSavePatientBiodata.setEnabled(false);
            btnUpdatePatientBiodata.setEnabled(true);
            btnClearPatientBiodata.setEnabled(false);
//            btnSaveEmployment.setEnabled(false);
//            btnUpdateEmploymentInfo.setEnabled(true);
//            btnClearEmployment.setEnabled(false);
//            btnSaveNOK.setEnabled(false);
//            btnUpdateNokInfo.setEnabled(true);
//            btnClearNOK.setEnabled(false);
//            btnSaveFamilyInfo.setEnabled(false);
//            btnUpdateFamilyInfo.setEnabled(true);
//            btnClearFamily.setEnabled(false);
//            btnSaveInsurance.setEnabled(false);
//            btnUpdateInsurance.setEnabled(true);
//            btnClearInsuranceInfo.setEnabled(false);
        }
        
        public String[] trim(String[] data) {
            for(int i = 0; i < data.length; i++) {
                data[i] = Func.trim(data[i]);
            }
            return data;
        }
        
        public void showTableNOK() {
            ArrayList<String> al_nextofkin = Patient.getTableNOK(tfieldPMINoPMI.getText());
            int size_nok = 19;
            if (al_nextofkin.size() > 0) {
                for (int j = 0; j < al_nextofkin.size() / size_nok; j++) {
                    tbl_nok.getModel().setValueAt(al_nextofkin.get(j * size_nok + 3), j, 0);
                    tbl_nok.getModel().setValueAt(al_nextofkin.get(j * size_nok + 4), j, 1);
                    for (int k = 0; k < size_nok; k++) {
                        tbl_nok_array[j][k] = al_nextofkin.get(j * size_nok + k);
                    }
                }
            } else {
                for (int j = 0; j < 50; j++) {
                    tbl_nok.getModel().setValueAt("", j, 0);
                    tbl_nok.getModel().setValueAt("", j, 1);
                    for (int i = 0; i < size_nok; i++) {
                        tbl_nok_array[j][i] = "";
                    }
                }
            }
        }
        
        public void showTableFamily() {
            ArrayList<String> al_family = Patient.getTableFamily(tfieldPMINoPMI.getText());
            int size_family = 6;
            if (al_family.size() > 0) {
                for (int j = 0; j < al_family.size() / size_family; j++) {
                    tbl_family.getModel().setValueAt(al_family.get(j * size_family + 4), j, 0);
                    tbl_family.getModel().setValueAt(al_family.get(j * size_family + 2), j, 1);
                    for (int k = 0; k < size_family; k++) {
                        tbl_family_array[j][k] = al_family.get(j * size_family + k);
                    }
                }
            } else {
                for (int j = 0; j < 50; j++) {
                    tbl_family.getModel().setValueAt("", j, 0);
                    tbl_family.getModel().setValueAt("", j, 1);
                    for (int i = 0; i < size_family; i++) {
                        tbl_family_array[j][i] = "";
                    }
                }
            }
        }
        
        public void showTableMedical() {
            ArrayList<String> al_medical = Patient.getTableMedical(tfieldPMINoPMI.getText());
            int size_medical = 7;
            if (al_medical.size() > 0) {
                for (int j = 0; j < al_medical.size() / size_medical; j++) {
                    tbl_insurance.getModel().setValueAt(al_medical.get(j * size_medical + 2), j, 0);
                    tbl_insurance.getModel().setValueAt(al_medical.get(j * size_medical + 3), j, 1);
                    for (int k = 0; k < size_medical; k++) {
                        tbl_insurance_array[j][k] = al_medical.get(j * size_medical + k);
                    }
                }
            } else {
                for (int j = 0; j < 50; j++) {
                    tbl_insurance.getModel().setValueAt("", j, 0);
                    tbl_insurance.getModel().setValueAt("", j, 1);
                    for (int i = 0; i < size_medical; i++) {
                        tbl_insurance_array[j][i] = "";
                    }
                }
            }
        }
        
        public void showTableEmploy() {
            ArrayList<String> al_employ = Patient.getTableEmploy(tfieldPMINoPMI.getText());
            int size_employ = 10;
            if (al_employ.size() > 0) {
                for (int j = 0; j < al_employ.size() / size_employ; j++) {
                    tbl_employ.getModel().setValueAt(al_employ.get(j * size_employ + 3), j, 0);
                    tbl_employ.getModel().setValueAt(al_employ.get(j * size_employ + 4), j, 1);
                    for (int k = 0; k < size_employ; k++) {
                        tbl_employ_array[j][k] = al_employ.get(j * size_employ + k);
                    }
                }
            } else {
                for (int j = 0; j < 50; j++) {
                    tbl_employ.getModel().setValueAt("", j, 0);
                    tbl_employ.getModel().setValueAt("", j, 1);
                    for (int i = 0; i < size_employ; i++) {
                        tbl_employ_array[j][i] = "";
                    }
                }
            }
        }
        
    public void setSelectedPatient(String tab) {
        tabPMI.setVisible(true);
    }

    /* Loading all combo box in register tab */
    public void LoadRegisterTabCB() {
        try {
            LookupController CBoxloader = new LookupController();
            UniCBmodel = new DefaultComboBoxModel();

            UniCBmodel = CBoxloader.getLookupReferences("0012", "ID Type", false);
            cboxIdTypeRegister.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0012", "ID Type", true);
            cboxIdTypeRegistration.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0033", "Patient Category", false);
            cboxPatientCategoryRegistration.setModel(UniCBmodel);
            Func.cmbSelectInput(cboxPatientCategoryRegistration, "General Outpatient");

            UniCBmodel = CBoxloader.getLookupReferences("0022", "Visit Type", false);
            cboxVisitTypeRegistration.setModel(UniCBmodel);
            
            UniCBmodel = CBoxloader.getLookupReferences("0070", "Emergency Type", false);
            cboxEmergencyTypeRegistration.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0063", "Eligibility Category", false);
            cboxEligibilityCategoryRegistration.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0034", "Eligibility Type", false);
            cboxEligibilityTypeRegistration.setModel(UniCBmodel);
            
            UniCBmodel = CBoxloader.getLookupReferences("0072", "Discipline", false);
            cboxDisciplineRegistration.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0036", "Piority Group", false);
            cboxPriorityGroupRegistration.setModel(UniCBmodel);
            Func.cmbSelectInput(cboxPriorityGroupRegistration, "Normal");

            UniCBmodel = CBoxloader.getLookupReferences("0018", "Payment Mode", true);
            cboxPaymentMode.setModel(UniCBmodel);

            rbConsultationRoom.setSelected(false);
            rbQueue.setSelected(true);
            rbDoctor.setSelected(false);
            if (rbQueue.isSelected()) {
                cboxConsultationRoom.setEnabled(false);
                cboxDoctor.setEnabled(false);
                cboxQueue.setEnabled(true);
            } else {
                cboxConsultationRoom.setEnabled(true);
                cboxDoctor.setEnabled(true);
                cboxQueue.setEnabled(true);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*load PMI screen combo box*/
    public void LoadPMITabCB() {
        try {
            if (cboxPostalStatePatient.getSelectedItem() != null) {
                System.out.println("loaded");

            } else {
                LookupController CBoxloader = new LookupController();
                UniCBmodel = new DefaultComboBoxModel();

                UniCBmodel = CBoxloader.getLookupReferences("0012", "ID Type", true);
                cboxIdTypePatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0063", "Eligibility Category", false);
                cboxEligibilityCategoryPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0034", "Eligibility Type", false);
                cboxEligibilityTypePatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0041", "Gender", false);
                cboxSexPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0004", "Race", false);
                cboxRacePatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0006", "Marital Status", false);
                cboxMaritalStatusPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0011", "Nationality", false);
                cboxNationalityPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0005", "Religion", false);
                cboxReligionPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0002", "State", false);
                cboxPostalStatePatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0002", "State", false);
                cboxStatePatient.setModel(UniCBmodel);


                UniCBmodel = CBoxloader.getLookupReferences("0001", "Country", false);
                cboxCountryPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0001", "Country", false);
                cboxPostalCountryPatient.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0074", "Blood Type", false);
                cboxBloodTypePatient.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0075", "Allergy Status", false);
                cboxAllergyPatient.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0017", "Blood Rhesus", false);
                cboxBloodRhesusPatient.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0076", "Chronic Disease Status", false);
                cboxChronicDiseasePatient.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0077", "Organ Donor Status", false);
                cboxOrganDonorPatient.setModel(UniCBmodel);

                UniCBmodel = CBoxloader.getLookupReferences("0026", "Title", true);
                cboxTitle.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0078", "District", false);
                cboxDistrictCode.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0078", "District", false);
                cboxPostalDistrict.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0079", "Postcode", false);
                cboxPostcode.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0079", "Postcode", false);
                cboxPostalPostcode.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0003", "Town", false);
                cboxTownCode.setModel(UniCBmodel);
                
                UniCBmodel = CBoxloader.getLookupReferences("0003", "Town", false);
                cboxPostalTown.setModel(UniCBmodel);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*load Employment screen combo box*/
    public void LoadEmploymentTab() {
        try {
            LookupController CBoxloader = new LookupController();
            UniCBmodel = new DefaultComboBoxModel();

            UniCBmodel = CBoxloader.getLookupReferences("0028", "Income Range", true);
            cboxIncomeRange.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0081", "Health Facility Code", true);
            cboxHealthFacilityEmployment.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0082", "Employment Status", true);
            cboxEmploymentStatus.setModel(UniCBmodel);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*load Next of Kin screen combo box*/
    public void LoadNextKinTab() {
        try {
            LookupController CBoxloader = new LookupController();
            UniCBmodel = new DefaultComboBoxModel();

            UniCBmodel = CBoxloader.getLookupReferences("0015", "NextOfKin Relationship", true);
            cboxNOKRelationship.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0012", "ID Type", true);
            cboxIdTypeNOK.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0078", "District", false);
            cboxDistrictNOK.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0079", "Postcode", false);
            cboxPostcodeNOK.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0001", "Country", false);
            cboxCountryNOK.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0002", "State", false);
            cboxStateNOK.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0003", "Town", false);
            cboxTownNOK.setModel(UniCBmodel);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*load Family screen combo box*/
    public void LoadFamilyTab() {
        try {
            LookupController CBoxloader = new LookupController();
            UniCBmodel = new DefaultComboBoxModel();

            UniCBmodel = CBoxloader.getLookupReferences("0007", "Family Relationship", true);
            cboxFamilyRelationship.setModel(UniCBmodel);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*load Medical screen combo box*/
    public void LoadMedicalTab() {
        try {
            LookupController CBoxloader = new LookupController();
            UniCBmodel = new DefaultComboBoxModel();

            UniCBmodel = CBoxloader.getLookupReferences("0083", "Insurance Company", true);
            cboxInsuranceCompanyName.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0081", "Health Facility Code", true);
            cboxHealthFacilityInsurance.setModel(UniCBmodel);

            UniCBmodel = CBoxloader.getLookupReferences("0058", "Policy Status", true);
            CboxPolicyStatus.setModel(UniCBmodel);
        } catch (Exception ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*get pdi, nok, emp, fmi, ins */
    public String createFormat(String[] details) {

        String cFm = new String();

        for (int i = 0; i < details.length; i++) {
            if (details[i].toString().equalsIgnoreCase("-")) {
                cFm += "|";
            } else {
                cFm += details[i] + "|";
            }
        }

        return cFm + "<cr>";
    }

//method to set 
    public void setsaveStatus(boolean stats) {

        regSaveStatus = stats;
        pmiSaveStatus = stats;
        empSaveStatus = stats;
        nokSaveStatus = stats;
        fmiSaveStatus = stats;
        insSaveStatus = stats;


    }

    public void getOnlinePMI(String IcNo) {

        try {
            // fire to server port 1099
//            ArrayList<String> listOnline = Func.readXML("online");
//            Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//            // search for myMessage service
//            Message impl = (Message) myRegistry.lookup("myMessage");

            // call server's method	
            //String pmi = "PMS10003";
            //String IC = "891031075331";
            List s = DBConnection.getImpl().getPMS(IcNo);
            displayPMI(s);

            for (int i = 0; i < s.size(); i++) {
                System.out.println(s.get(i));
            }

            System.out.println("Message Sent");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "RMI Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void displayPMI(List listData) {
        
        S.oln("listData.get(0): "+listData.get(0));

        List<String> pdi = (ArrayList<String>) listData.get(0);
        String[] RegisterInfo = pdi.toArray(new String[pdi.size()]);
        String[] BiodataInfo = pdi.toArray(new String[pdi.size()]);

        List<String> emp = (ArrayList<String>) listData.get(1);
        String[] EmploymentInfo = emp.toArray(new String[emp.size()]);

        List<String> nok = (ArrayList<String>) listData.get(2);
        String[] NOKInfo = nok.toArray(new String[nok.size()]);

        List<String> fmi = (ArrayList<String>) listData.get(3);
        String[] FamilyInfo = fmi.toArray(new String[fmi.size()]);

        List<String> ins = (ArrayList<String>) listData.get(4);
        String[] InsuranceInfo = ins.toArray(new String[ins.size()]);



        //show patient information on registration screen
        tfieldPMINoRegistration.setText(RegisterInfo[0]);
        tfieldNameRegistration.setText(RegisterInfo[2]);
        tfieldNewICNoRegistration.setText(RegisterInfo[4]);
        tfieldOldICNoRegistration.setText(RegisterInfo[5]);
        //cboxIdTypeRegistration.setSelectedItem(RegisterInfo[6]);
        cboxIdTypeRegistration.getModel().setSelectedItem(RegisterInfo[6]);
        tfieldIdNoRegistration.setText(RegisterInfo[7]);
        //cboxEligibilityCategoryRegistration.setSelectedItem(RegisterInfo[8]);
        //cboxEligibilityTypeRegistration.setSelectedItem(RegisterInfo[9]);
        cboxEligibilityCategoryRegistration.getModel().setSelectedItem(RegisterInfo[8]);
        cboxEligibilityTypeRegistration.getModel().setSelectedItem(RegisterInfo[9]);
        //cboxVisitTypeRegistration.setSelectedItem("Walk-in");
        cboxVisitTypeRegistration.getModel().setSelectedItem("Walk-in");
        tfieldChargeRate.setText("1.00");
        tfieldPaymentAmount.setText("1.00");
        //if(tfieldIdNoRegistration.getText().length() != 0)
        //{
        // tfieldChargeRate.setText("5.00");
        //tfieldPaymentAmount.setText("5.00");
        //}

        //show patient's biodata on patient master index screen
        tfieldPMINoPMI.setText(BiodataInfo[0]);
        tfieldTempPMINo.setText(BiodataInfo[1]);
        tfieldPatientName.setText(BiodataInfo[2]);
        // cboxTitle.setSelectedItem(BiodataInfo[3]);
        cboxTitle.getModel().setSelectedItem(BiodataInfo[3]);
        tfieldNewICNoPatient.setText(BiodataInfo[4]);
        tfieldOldICNoPatient.setText(BiodataInfo[5]);
        //cboxIdTypePatient.setSelectedItem(BiodataInfo[6]);
        cboxIdTypePatient.getModel().setSelectedItem(BiodataInfo[6]);
        cboxEligibilityCategoryPatient.getModel().setSelectedItem(BiodataInfo[8]);
        cboxEligibilityTypePatient.getModel().setSelectedItem(BiodataInfo[9]);
        tfieldIdNoPatient.setText(BiodataInfo[7]);
        // cboxEligibilityCategoryPatient.setSelectedItem(BiodataInfo[8]);
        // cboxEligibilityTypePatient.setSelectedItem(BiodataInfo[9]);

        DateFormat formatterBiodata = new SimpleDateFormat("dd/MM/yyyy");
        Date getDOB = null;
        try {
            getDOB = formatterBiodata.parse(BiodataInfo[10]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOBPatient.setDate(getDOB);

        /*new model*/
        cboxSexPatient.getModel().setSelectedItem(BiodataInfo[11]);
        cboxMaritalStatusPatient.getModel().setSelectedItem(BiodataInfo[12]);
        cboxRacePatient.getModel().setSelectedItem(BiodataInfo[13]);
        cboxNationalityPatient.getModel().setSelectedItem(BiodataInfo[14]);
        cboxReligionPatient.getModel().setSelectedItem(BiodataInfo[15]);
        cboxStatePatient.getModel().setSelectedItem(BiodataInfo[25]);
        cboxCountryPatient.getModel().setSelectedItem(BiodataInfo[26]);
        cboxPostalStatePatient.getModel().setSelectedItem(BiodataInfo[32]);
        cboxPostalCountryPatient.getModel().setSelectedItem(BiodataInfo[33]);

//                        cboxSexPatient.setSelectedItem(BiodataInfo[11]);
//                        cboxMaritalStatusPatient.setSelectedItem(BiodataInfo[12]);
//                        cboxRacePatient.setSelectedItem(BiodataInfo[13]);
//                        cboxNationalityPatient.setSelectedItem(BiodataInfo[14]);
//                        cboxReligionPatient.setSelectedItem(BiodataInfo[15]);
        cboxBloodTypePatient.setSelectedItem(BiodataInfo[16]);
        cboxBloodRhesusPatient.setSelectedItem(BiodataInfo[17]);
        cboxAllergyPatient.setSelectedItem(BiodataInfo[18]);
        cboxChronicDiseasePatient.setSelectedItem(BiodataInfo[19]);
        cboxOrganDonorPatient.setSelectedItem(BiodataInfo[20]);
        tfieldHomeAddress.setText(BiodataInfo[21]);
        cboxDistrictCode.setSelectedItem(BiodataInfo[22]);
        cboxTownCode.setSelectedItem(BiodataInfo[23]);
        cboxPostcode.setSelectedItem(BiodataInfo[24]);
        // cboxStatePatient.setSelectedItem(BiodataInfo[25]);
        // cboxCountryPatient.setSelectedItem(BiodataInfo[26]);
        tfieldHomephonePatient.setText(BiodataInfo[27]);
        tfieldPostalAddressPatient.setText(BiodataInfo[28]);
        cboxPostalDistrict.setSelectedItem(BiodataInfo[29]);
        cboxPostalTown.setSelectedItem(BiodataInfo[30]);
        cboxPostalPostcode.setSelectedItem(BiodataInfo[31]);
        // cboxPostalStatePatient.setSelectedItem(BiodataInfo[32]);
        // cboxPostalCountryPatient.setSelectedItem(BiodataInfo[33]);
        tfieldHandphoneNoPatient.setText(BiodataInfo[34]);

        //show patient's employment information on employment screen
        tfieldPmiEmployment.setText(EmploymentInfo[0]);
        tfieldEmploymentSequenceNo.setText(EmploymentInfo[1]);
        tfieldEmployerCode.setText(EmploymentInfo[2]);
        tfieldEmployerName.setText(EmploymentInfo[3]);
        tfieldOccupationCode.setText(EmploymentInfo[4]);

        DateFormat formatterEmployment = new SimpleDateFormat("dd/MM/yyyy");
        Date getJoinedDateEmployment = null;

        try {
            getJoinedDateEmployment = formatterEmployment.parse(EmploymentInfo[5]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldJoinedDate.setDate(getJoinedDateEmployment);

        cboxIncomeRange.setSelectedItem(EmploymentInfo[6]);
        cboxHealthFacilityEmployment.setSelectedItem(EmploymentInfo[7]);

        Date getCreatedDateEmployment = null;
        try {
            getCreatedDateEmployment = formatterEmployment.parse(EmploymentInfo[8]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfieldCreatedDate.setDate(getCreatedDateEmployment);
        cboxEmploymentStatus.setSelectedItem(EmploymentInfo[9]);

        //show patient's next of kin  information on next of kin screen
        tfieldPmiNextOfKin.setText(NOKInfo[0]);
        tfieldNextOfKinSequenceNo.setText(NOKInfo[1]);
        cboxNOKRelationship.setSelectedItem(NOKInfo[2]);
        tfieldNextOfKinName.setText(NOKInfo[3]);
        tfieldNewICNo.setText(NOKInfo[4]);
        tfieldOldICNo.setText(NOKInfo[5]);
        cboxIdTypeNOK.setSelectedItem(NOKInfo[6]);
        tfieldIdNoNOK.setText(NOKInfo[7]);

        DateFormat formatterNOK = new SimpleDateFormat("dd/MM/yyyy");
        Date getdobNOK = null;
        try {
            //System.out.println(nokInfo[8]);
            getdobNOK = formatterNOK.parse(NOKInfo[8]);
            //System.out.println(getdobNOK);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldDOB.setDate(getdobNOK);
        tfieldOccupationNOK.setText(NOKInfo[9]);
        tareaAddressNOK.setText(NOKInfo[10]);
        cboxDistrictNOK.setSelectedItem(NOKInfo[11]);
        cboxTownNOK.setSelectedItem(NOKInfo[12]);
        cboxPostcodeNOK.setSelectedItem(NOKInfo[13]);
        cboxStateNOK.setSelectedItem(NOKInfo[14]);
        cboxCountryNOK.setSelectedItem(NOKInfo[15]);
        tfieldHpNoNOK.setText(NOKInfo[16]);
        tfieldHomephoneNoNOK.setText(NOKInfo[17]);
        tfieldEmailNOK.setText(NOKInfo[18]);

        //show patient's family information on family screen

        tfieldPmiFamily.setText(FamilyInfo[0]);
        tfieldFamilySequenceNo.setText(FamilyInfo[1]);
        cboxFamilyRelationship.setSelectedItem(FamilyInfo[2]);
        tareaFamilyMemberPMI.setText(FamilyInfo[3]);
        tareaFamilyMemberName.setText(FamilyInfo[4]);
        tfieldOccupationFamily.setText(FamilyInfo[5]);

        //show patient's medical insurance information on insurance screen
        tfieldPmiInsurance.setText(InsuranceInfo[0]);
        cboxInsuranceCompanyName.setSelectedItem(InsuranceInfo[1]);
        tfieldPolicyNo.setText(InsuranceInfo[2]);

        DateFormat formatterInsurance = new SimpleDateFormat("dd/MM/yyyy");
        Date getMaturityDateInsurance = null;
        try {
            getMaturityDateInsurance = formatterInsurance.parse(InsuranceInfo[3]);
        } catch (ParseException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfieldMaturityDate.setDate(getMaturityDateInsurance);
        cboxHealthFacilityInsurance.setSelectedItem(InsuranceInfo[4]);
        CboxPolicyStatus.setSelectedItem(InsuranceInfo[5]);





    }
    //retrieve town information from database and display in town combobox

    //save patient medical insirance into database     }
    //close patient master index tab    
    //close family info tab    
    //close next of kin tab
    //close Employment tab    
    //clear next of kin infomation    
    //clear person master index information    
    //clear registration information
    // public void setFamilyInfo() throws ClassNotFoundException, SQLException {
    // Patient family = new Patient(this);
    //String[] familyInfo = family.addPatientFamilyInfo(familyinfo);
    // tfieldPmiFamily.setText(familyInfo[1]);
    // tfieldFamilySequenceNo.setText(familyInfo[2]);
    // tareaFamilyMemberName.setText(familyInfo[3]);
    // }
    //save patient family information into database    
    //save patient nect of kin information into database
    //save patient biodata into database    
    //save patient employment into database    
    //delete patient's family information from database        
    //delete patient's medical insurance information from database        
    //  delete paient's next of kin information from database        
    //delete patient master index information from database
    //save patient employment information into database
    //clear employment tab information
    //close employment tab
    //save patient next of kin information into database
    //clear patient next of kin information
    //close next of kin tab    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Registration().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CboxPolicyStatus;
    private javax.swing.JButton btnAppointmentList;
    private javax.swing.JButton btnClearEmployment;
    private javax.swing.JButton btnClearFamily;
    private javax.swing.JButton btnClearInsuranceInfo;
    private javax.swing.JButton btnClearNOK;
    private javax.swing.JButton btnClearPatientBiodata;
    private javax.swing.JButton btnClearRegistration;
    private javax.swing.JButton btnClosePatientBiodata;
    private javax.swing.ButtonGroup btnGrpChildGuardian;
    private javax.swing.ButtonGroup btnGrpPoliceCase;
    private javax.swing.ButtonGroup btnGrpQueue;
    private javax.swing.ButtonGroup btnGrpSearchICEmployment;
    private javax.swing.ButtonGroup btnGrpSearchICFamily;
    private javax.swing.ButtonGroup btnGrpSearchICInsurance;
    private javax.swing.ButtonGroup btnGrpSearchICNOK;
    private javax.swing.ButtonGroup btnGrpSearchICPMI;
    private javax.swing.ButtonGroup btnGrpSearchICRegister;
    private javax.swing.JButton btnOnlineSearch;
    private javax.swing.JButton btnPrintReceipt;
    private javax.swing.JButton btnReadMyKadNOK;
    private javax.swing.JButton btnReadMyKadRegister;
    private javax.swing.JButton btnReferralDetails;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSaveEmployment;
    private javax.swing.JButton btnSaveFamilyInfo;
    private javax.swing.JButton btnSaveInsurance;
    private javax.swing.JButton btnSaveNOK;
    private javax.swing.JButton btnSavePatientBiodata;
    private javax.swing.JButton btnSearchPatient;
    private javax.swing.JButton btnUpdateEmploymentInfo;
    private javax.swing.JButton btnUpdateFamilyInfo;
    private javax.swing.JButton btnUpdateInsurance;
    private javax.swing.JButton btnUpdateNokInfo;
    private javax.swing.JButton btnUpdatePatientBiodata;
    private javax.swing.JComboBox cboxAllergyPatient;
    private javax.swing.JComboBox cboxBloodRhesusPatient;
    private javax.swing.JComboBox cboxBloodTypePatient;
    private javax.swing.JComboBox cboxChronicDiseasePatient;
    private javax.swing.JComboBox cboxConsultationRoom;
    private javax.swing.JComboBox cboxCountryNOK;
    private javax.swing.JComboBox cboxCountryPatient;
    private javax.swing.JComboBox cboxDisciplineRegistration;
    private javax.swing.JComboBox cboxDistrictCode;
    private javax.swing.JComboBox cboxDistrictNOK;
    private javax.swing.JComboBox cboxDoctor;
    private javax.swing.JComboBox cboxEligibilityCategoryPatient;
    private javax.swing.JComboBox cboxEligibilityCategoryRegistration;
    private javax.swing.JComboBox cboxEligibilityTypePatient;
    private javax.swing.JComboBox cboxEligibilityTypeRegistration;
    private javax.swing.JComboBox cboxEmergencyTypeRegistration;
    private javax.swing.JComboBox cboxEmploymentStatus;
    private javax.swing.JComboBox cboxFamilyRelationship;
    private javax.swing.JComboBox cboxHealthFacilityEmployment;
    private javax.swing.JComboBox cboxHealthFacilityInsurance;
    private javax.swing.JComboBox cboxIdTypeNOK;
    private javax.swing.JComboBox cboxIdTypePatient;
    private javax.swing.JComboBox cboxIdTypeRegister;
    private javax.swing.JComboBox cboxIdTypeRegistration;
    private javax.swing.JComboBox cboxIncomeRange;
    private javax.swing.JComboBox cboxInsuranceCompanyName;
    private javax.swing.JComboBox cboxMaritalStatusPatient;
    private javax.swing.JComboBox cboxNOKRelationship;
    private javax.swing.JComboBox cboxNationalityPatient;
    private javax.swing.JComboBox cboxOrganDonorPatient;
    private javax.swing.JComboBox cboxPatientCategoryRegistration;
    private javax.swing.JComboBox cboxPaymentMode;
    private javax.swing.JComboBox cboxPostalCountryPatient;
    private javax.swing.JComboBox cboxPostalDistrict;
    private javax.swing.JComboBox cboxPostalPostcode;
    private javax.swing.JComboBox cboxPostalStatePatient;
    private javax.swing.JComboBox cboxPostalTown;
    private javax.swing.JComboBox cboxPostcode;
    private javax.swing.JComboBox cboxPostcodeNOK;
    private javax.swing.JComboBox cboxPriorityGroupRegistration;
    private javax.swing.JComboBox cboxQueue;
    private javax.swing.JComboBox cboxRacePatient;
    private javax.swing.JComboBox cboxReligionPatient;
    private javax.swing.JComboBox cboxSexPatient;
    private javax.swing.JComboBox cboxStateNOK;
    private javax.swing.JComboBox cboxStatePatient;
    private javax.swing.JComboBox cboxTitle;
    private javax.swing.JComboBox cboxTownCode;
    private javax.swing.JComboBox cboxTownNOK;
    private javax.swing.JComboBox cboxVisitTypeRegistration;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private java.awt.Label label19;
    private java.awt.Label label4;
    private java.awt.Label label44;
    private java.awt.Label label45;
    private java.awt.Label label46;
    private java.awt.Label label47;
    private java.awt.Label label48;
    private java.awt.Label label5;
    private java.awt.Label label50;
    private java.awt.Label label51;
    private java.awt.Label label52;
    private java.awt.Label label6;
    private java.awt.Label label69;
    private java.awt.Label label7;
    private java.awt.Label label70;
    private java.awt.Label label71;
    private java.awt.Label label72;
    private java.awt.Label label73;
    private java.awt.Label label74;
    private java.awt.Label label75;
    private java.awt.Label label76;
    private java.awt.Label label77;
    private java.awt.Label label78;
    private java.awt.Label label79;
    private java.awt.Label label80;
    private java.awt.Label label81;
    private java.awt.Label label82;
    private java.awt.Label label83;
    private java.awt.Label label84;
    private java.awt.Label label92;
    private java.awt.Label label93;
    private java.awt.Label label94;
    private java.awt.Label label95;
    private java.awt.Label label96;
    private javax.swing.JPanel pnlSelectQueue;
    private javax.swing.JPanel pnl_IC_NOK;
    private javax.swing.JPanel pnl_IC_PersonMasterIndex;
    private javax.swing.JRadioButton rbConsultationRoom;
    private javax.swing.JRadioButton rbDoctor;
    private javax.swing.JRadioButton rbNewRegister;
    private javax.swing.JRadioButton rbOldRegister;
    private javax.swing.JRadioButton rbQueue;
    private javax.swing.JPanel tabPMI;
    public javax.swing.JTabbedPane tabpanel;
    private javax.swing.JTextArea tareaAddressNOK;
    private javax.swing.JTextField tareaFamilyMemberName;
    private javax.swing.JTextField tareaFamilyMemberPMI;
    private javax.swing.JTable tbl_employ;
    private javax.swing.JTable tbl_family;
    private javax.swing.JTable tbl_insurance;
    private javax.swing.JTable tbl_nok;
    private javax.swing.JTextField tfieldChargeRate;
    private com.toedter.calendar.JDateChooser tfieldCreatedDate;
    private com.toedter.calendar.JDateChooser tfieldDOB;
    private com.toedter.calendar.JDateChooser tfieldDOBPatient;
    private javax.swing.JTextField tfieldEmailNOK;
    private javax.swing.JTextField tfieldEmployerCode;
    private javax.swing.JTextField tfieldEmployerName;
    private javax.swing.JTextField tfieldEmploymentSequenceNo;
    private javax.swing.JTextField tfieldFamilySequenceNo;
    private javax.swing.JTextField tfieldHandphoneNoPatient;
    private javax.swing.JTextField tfieldHomeAddress;
    private javax.swing.JTextField tfieldHomephoneNoNOK;
    private javax.swing.JTextField tfieldHomephonePatient;
    private javax.swing.JTextField tfieldHpNoNOK;
    private javax.swing.JTextField tfieldICSearchRegister;
    private javax.swing.JTextField tfieldIDSearchRegister;
    private javax.swing.JTextField tfieldIdNoNOK;
    private javax.swing.JTextField tfieldIdNoPatient;
    private javax.swing.JTextField tfieldIdNoRegistration;
    private com.toedter.calendar.JDateChooser tfieldJoinedDate;
    private com.toedter.calendar.JDateChooser tfieldMaturityDate;
    private javax.swing.JTextField tfieldNameRegistration;
    private javax.swing.JTextField tfieldNewICNo;
    private javax.swing.JTextField tfieldNewICNoPatient;
    private javax.swing.JTextField tfieldNewICNoRegistration;
    private javax.swing.JTextField tfieldNextOfKinName;
    private javax.swing.JTextField tfieldNextOfKinSequenceNo;
    private javax.swing.JTextField tfieldOccupationCode;
    private javax.swing.JTextField tfieldOccupationFamily;
    private javax.swing.JTextField tfieldOccupationNOK;
    private javax.swing.JTextField tfieldOldICNo;
    private javax.swing.JTextField tfieldOldICNoPatient;
    private javax.swing.JTextField tfieldOldICNoRegistration;
    private javax.swing.JTextField tfieldPMINoPMI;
    private javax.swing.JTextField tfieldPMINoRegistration;
    private javax.swing.JTextField tfieldPMISearchRegister;
    private javax.swing.JTextField tfieldPatientName;
    private javax.swing.JTextField tfieldPaymentAmount;
    private javax.swing.JTextField tfieldPmiEmployment;
    private javax.swing.JTextField tfieldPmiFamily;
    private javax.swing.JTextField tfieldPmiInsurance;
    private javax.swing.JTextField tfieldPmiNextOfKin;
    private javax.swing.JTextField tfieldPolicyNo;
    private javax.swing.JTextField tfieldPostalAddressPatient;
    private javax.swing.JTextField tfieldReceiptNo;
    private javax.swing.JTextField tfieldRegistrationNo;
    private javax.swing.JTextField tfieldTempPMINo;
    private javax.swing.JTextField tfieldmedicalseqno;
    // End of variables declaration//GEN-END:variables
}
