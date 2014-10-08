/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PatienDetial.java
 *
 * Created on May 14, 2012, 2:56:57 PM
 */

package AppointmentGUI;

import DBConnection.DBConnection;
import Helper.S;
import Helper.Session;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import conndb.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author WC
 */
public class PatientDetail extends javax.swing.JFrame {
    private Date sdate;
    private String datestr;
    private apptCAL apptCAL= null;
    private String appointID;
    private String timeslot;
    private String chosentime;
    DefaultComboBoxModel timemodel = new DefaultComboBoxModel();
    DefaultComboBoxModel Docmodel = new DefaultComboBoxModel();
    Vector<String> patdet;
    String[] pattern2 = {"dd/MM/yyyy", "dd-MM-yyyy"};
    private JFrame frame = new JFrame("PMAS");


    /** Creates new form PatienDetial */
   public PatientDetail() {
        initComponents();
    }

    public PatientDetail(apptCAL apptc, String ID, String sTime, Date sdate, String dateST) {
        initComponents();
       this.apptCAL = apptc;
       this.appointID= ID;
       this.timeslot=sTime;
       this.sdate= sdate;
       this.datestr=dateST;
       if(appointID == null ){lbltitle.setText("New Appointment");}
       else{lbltitle.setText("Edit Appointment");}
    }

    public void setAtt(){

    if(lbltitle.getText()== "New Appointment" ){ 
       //lbltitle.setText("New Appointment");
        panelSearch.setVisible(true);
        btnDEL.setVisible(false);
        tboxAtype.setText("Walk-In");
        getdoctor();
       
        freshappt();
        
    }
    else{
       // lbltitle.setText("Edit Appointment");
        tboxApptID.setText(appointID);
        panelSearch.setVisible(false);
        btnDEL.setVisible(true);
        getdoctor();
        setDetails();
        }
    }
    
    // new appointment
    public void freshappt(){
        try {
            patientAppointment newAP = new patientAppointment();
            appointID= newAP.getAutoAppointID();
            settimeCB(datestr);
            CBtime.setModel(timemodel);
            tboxApptID.setText("A"+appointID);
            DatePicker.setDate(sdate);
            CBtime.setSelectedItem(timeslot);
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    
    //exist appointment
    public void setDetails(){
        try {
          
          patdet = new Vector<String>();
          patientAppointment PA= new patientAppointment();
          patdet = PA.getAppointment(appointID);
          
          //set value to all tbox and cbox
          
          tboxPMI.setText(patdet.elementAt(3));
          tboxPname.setText(patdet.elementAt(4));
          tboxIC.setText(patdet.lastElement());
          tboxAtype.setText(patdet.elementAt(9));
          
          chosentime=patdet.elementAt(2);
         // timemodel.addElement(chosentime);
          settimeCB(patdet.elementAt(1));
          Date date1 = DateUtils.parseDate(patdet.elementAt(1), pattern2);
          DatePicker.setDate(date1);
          CBdiscipline.setSelectedItem(patdet.elementAt(7));
          CBtime.setModel(timemodel);
          CBtime.setSelectedItem(chosentime);
          CBsubdiscipline.setSelectedItem(patdet.elementAt(8));
          for(int i = 0; i < Cblocation.getItemCount(); i++){
            if(patdet.elementAt(6).equalsIgnoreCase(Cblocation.getItemAt(i).toString())){
               Cblocation.setSelectedIndex(i); 
            }
          
          }
          for(int y = 0 ; y < CBdoc.getItemCount(); y++){
              if(patdet.elementAt(5).equalsIgnoreCase(CBdoc.getItemAt(y).toString())){
               CBdoc.setSelectedIndex(y); 
              }      
          }
       //   if(){
              
           //    Docmodel.addElement(patdet.elementAt(5));   
          //  }
          //CBdoc.addItem(patdet.elementAt(5));    
       
          
          
        } catch (ParseException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    //set time combobox
    public void settimeCB(String sdate){
        try {
            Vector<String> timetook = new Vector<String>();       
            String[] allTime ={" ", "0800", "0830", "0900", "0930", "1000", "1030","1100", "1130", "1200",
                                "1230", "1400", "1430", "1500", "1530", "1600", "1630"};
           
                //Connection hconn = connection.HSQLconnect();
                String sql="SELECT APPOINTMENT_TIME FROM PMS_APPOINTMENT_LIST where APPOINTMENT_DATE = ?";
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1, sdate);
                ResultSet rs = ps.executeQuery();
                while(rs.next()==true){
                   timetook.add(rs.getString(1));
                   
                }
            
            if(timetook.isEmpty()==true){
              for(int i = 0 ; i< allTime.length; i++) 
                { timemodel.addElement(allTime[i]); }

           
            }else{
             for(int i = 0 ; i< allTime.length; i++) 
                { timemodel.addElement(allTime[i]); }
                for(int i = 0 ; i< timemodel.getSize(); i++){              
                 for(int y =0 ;y <timetook.size(); y++)
                 {  
                     if(timetook.elementAt(y).equals(timemodel.getElementAt(i))&& 
                             !timemodel.getElementAt(i).equals(chosentime)){
                       
                         timemodel.removeElementAt(i);
                        
                     }
                  
                 }    
               }
            }
        
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    
    //set doctor combobox
    public void getdoctor(){
        try {
            Docmodel.addElement("");
            Docmodel.addElement("Outpatient Doctor");
            //Connection hconn = connection.HSQLconnect();
            String sql="SELECT DOCTOR_NAME FROM PMS_DOCTORINFORMATION ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Docmodel.addElement(rs.getString(1));
            
            }
          //hconn.close();
          CBdoc.setModel(Docmodel);
            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        panelSearch = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        tboxSearchPMI = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tboxSearchIC = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboxIdTypeRegister = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        tboxsearchID = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        rbNewRegister = new javax.swing.JRadioButton();
        rbOldRegister = new javax.swing.JRadioButton();
        btnSearch = new javax.swing.JButton();
        btnMykad = new javax.swing.JButton();
        btnDEL = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tboxApptID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tboxPMI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CBtime = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DatePicker = new com.toedter.calendar.JDateChooser();
        CBdoc = new javax.swing.JComboBox();
        tboxIC = new javax.swing.JTextField();
        tboxPname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tboxAtype = new javax.swing.JTextField();
        Cblocation = new javax.swing.JComboBox();
        CBsubdiscipline = new javax.swing.JComboBox();
        CBdiscipline = new javax.swing.JComboBox();
        lbltitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(222, 240, 253));
        setForeground(java.awt.Color.white);
        setPreferredSize(new java.awt.Dimension(1299, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "APPOINMENT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1279, 500));

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        panelSearch.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search Patient"));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("PMI Number :");

        tboxSearchPMI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("I/C No :");

        tboxSearchIC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("ID type :");

        cboxIdTypeRegister.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cboxIdTypeRegister.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Type", "Police", "Army", "Foreigner" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("ID no. :");

        tboxsearchID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        buttonGroup1.add(rbNewRegister);
        rbNewRegister.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        rbNewRegister.setSelected(true);
        rbNewRegister.setText("New");

        buttonGroup1.add(rbOldRegister);
        rbOldRegister.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
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
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNewRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbOldRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnMykad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnMykad.setText("MyKad");
        btnMykad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMykadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSearchLayout = new javax.swing.GroupLayout(panelSearch);
        panelSearch.setLayout(panelSearchLayout);
        panelSearchLayout.setHorizontalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tboxSearchPMI)
                    .addComponent(cboxIdTypeRegister, 0, 179, Short.MAX_VALUE))
                .addGap(18, 43, Short.MAX_VALUE)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tboxSearchIC, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(tboxsearchID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSearchLayout.createSequentialGroup()
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnMykad)))
                .addGap(57, 57, 57))
        );
        panelSearchLayout.setVerticalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchLayout.createSequentialGroup()
                        .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(tboxSearchPMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tboxSearchIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnMykad)
                            .addComponent(btnSearch)
                            .addComponent(tboxsearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxIdTypeRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        btnDEL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDEL.setText("Delete");
        btnDEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDELActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Appointment Number :");

        tboxApptID.setEditable(false);
        tboxApptID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Patient Details"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("PMI Number :");

        tboxPMI.setEditable(false);
        tboxPMI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Appointment Time :");

        CBtime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Patient Name :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("I/C No :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Doctor :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Appointment Date :");

        DatePicker.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        CBdoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tboxIC.setEditable(false);
        tboxIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tboxPname.setEditable(false);
        tboxPname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Discipline :");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Subdiscipline");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Location :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Appointment Type :");

        tboxAtype.setEditable(false);
        tboxAtype.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Cblocation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Cblocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Clinic 1", "Clinic 2", "Clinic 3", "Clinic 4", "Ward 1", "Ward 2", "Ward 3", "Ward 4" }));

        CBsubdiscipline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBsubdiscipline.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Outpatient", "Ophthalomology", "Urology", "Oncology" }));

        CBdiscipline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBdiscipline.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Outpatient", "Inpatient" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tboxPMI)
                    .addComponent(tboxPname)
                    .addComponent(tboxIC)
                    .addComponent(CBdoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBtime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CBdiscipline, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CBsubdiscipline, 0, 259, Short.MAX_VALUE)
                    .addComponent(Cblocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tboxAtype))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CBtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tboxPMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tboxPname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBdiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tboxIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(CBsubdiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cblocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tboxAtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDEL)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tboxApptID, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tboxApptID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(panelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSave)
                    .addComponent(btnDEL)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        lbltitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltitle.setText("Patient Details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(447, 447, 447)
                        .addComponent(lbltitle, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbltitle)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(986, 628));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        setAtt();
    }//GEN-LAST:event_formWindowOpened

    private void btnDELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDELActionPerformed
        
        int n = JOptionPane.showConfirmDialog(frame,
                "Are you sure you wan to delete the appointment?",
                "Confirm Dialog Options",
                JOptionPane.YES_NO_OPTION //Button appearing on JOptionpane
                );
        
        try {
            //online
            if (n == 0) {

                boolean stat = true;
                
                stat = DBConnection.getImpl().deleteAppointment(appointID);
                
                if (stat == true) {
                    System.out.println("Done delete");
                    apptCAL.refreshCalendar(apptCAL.getCurrentMonth(), apptCAL.getCurrentYear());
                    JOptionPane.showMessageDialog(frame,
                            "Appointment Have been deleted",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                } else {
                    apptCAL.refreshCalendar(apptCAL.getCurrentMonth(), apptCAL.getCurrentYear());
                    JOptionPane.showMessageDialog(frame,
                            "Failed to delete appointment",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("failed");
                }
            }
        } catch (Exception e) {
            try {

                if (n == 0) {
                    boolean stat = true;
                    patientAppointment PAdel = new patientAppointment();
                    stat = PAdel.deleteAppointment(appointID);

                    if (stat == true) {
                        System.out.println("Done delete");
                        apptCAL.refreshCalendar(apptCAL.getCurrentMonth(), apptCAL.getCurrentYear());
                        JOptionPane.showMessageDialog(frame,
                                "Appointment Have been deleted",
                                "PMAS Notification",
                                JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                    } else {
                        apptCAL.refreshCalendar(apptCAL.getCurrentMonth(), apptCAL.getCurrentYear());
                        JOptionPane.showMessageDialog(frame,
                                "Failed to delete appointment",
                                "PMAS Notification",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("failed");
                    }
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

       

//    Session.setPrev_stat(false);
//    Session.setCurr_stat(false);
    }//GEN-LAST:event_btnDELActionPerformed

    private void btnMykadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMykadActionPerformed
        try {

            Vector<String> Myinfo = new Vector<String>();
            MyKad mykad = new MyKad();
            mykad.start();
            mykad.useJPN();
            mykad.readData();
            patientAppointment patinfo = new patientAppointment();
            Myinfo = patinfo.getpatientInfoIC( mykad.ic);

            if(!Myinfo.isEmpty()){
                tboxPMI.setText(Myinfo.elementAt(0));
                tboxPname.setText(Myinfo.elementAt(1));
                tboxIC.setText(Myinfo.elementAt(2));
            }else{
                JOptionPane.showMessageDialog(frame, "No patient record. Please register patient before making appointment",
                    "PMAS Notification",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnMykadActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
//        LongRunProcess.check_network2();//ping shj
//        if (Session.getPrev_stat()) {
            //online
            try {
                Vector<String> info = new Vector<String>();

                if (!tboxSearchPMI.getText().equals("")) {
                    // fire to server port 1099
//                        ArrayList<String> listOnline = Func.readXML("online");
//                        Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                        // search for myMessage service
//                        Message impl = (Message) myRegistry.lookup("myMessage");
                    info = DBConnection.getImpl().getpatientInfoPMI(tboxSearchPMI.getText());
                    S.oln("online search");

                }
                if (!tboxSearchIC.getText().equals("")) {
                    
                    // fire to server port 1099
//                        ArrayList<String> listOnline = Func.readXML("online");
//                        Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                        // search for myMessage service
//                        Message impl = (Message) myRegistry.lookup("myMessage");
                    info = DBConnection.getImpl().getpatientInfoIC(tboxSearchIC.getText());
                    S.oln("online search");

                }

                if (!info.isEmpty()) {
                    tboxPMI.setText(info.elementAt(0));
                    tboxPname.setText(info.elementAt(1));
                    tboxIC.setText(info.elementAt(2));
                } else {
                    JOptionPane.showMessageDialog(frame, "No patient record. Please register patient before making appointment",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }catch(Exception z){
//                z.printStackTrace();
                //offline
                try {
                    Vector<String> info = new Vector<String>();

                    if (!tboxSearchPMI.getText().equals("")) {

                        patientAppointment patinfo = new patientAppointment();
                        info = patinfo.getpatientInfoPMI(tboxSearchPMI.getText());
                        S.oln("offline search");

                    }
                    if (!tboxSearchIC.getText().equals("")) {

                        patientAppointment patinfo = new patientAppointment();
                        info = patinfo.getpatientInfoIC(tboxSearchIC.getText());
                        S.oln("offline search");

                    }

                    if (!info.isEmpty()) {
                        tboxPMI.setText(info.elementAt(0));
                        tboxPname.setText(info.elementAt(1));
                        tboxIC.setText(info.elementAt(2));
                    } else {
                        JOptionPane.showMessageDialog(frame, "No patient record. Please register patient before making appointment",
                                "PMAS Notification",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } 
            
//        }else{
//            
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (tboxIC.getText().equals("") || tboxPMI.getText().equals("") || tboxPname.getText().equals("")) {
            JOptionPane.showMessageDialog(frame,
                    "Please select patient by using the search function.",
                    "PMAS Notification",
                    JOptionPane.WARNING_MESSAGE);

        }
        else{
            
            try {
                //online - coding RMI
                Vector<String> data = new Vector<String>();
                boolean AppStat = false;
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateSEL = formatter.format(DatePicker.getDate());
                data.add(tboxApptID.getText());
                data.add(dateSEL);
                data.add(String.valueOf(CBtime.getSelectedItem()));
                data.add(tboxPMI.getText());
                data.add(tboxPname.getText());
                data.add(String.valueOf(CBdoc.getSelectedItem()));
                data.add(String.valueOf(Cblocation.getSelectedItem()));
                data.add(String.valueOf(CBdiscipline.getSelectedItem()));
                data.add(String.valueOf(CBsubdiscipline.getSelectedItem()));
                data.add(tboxAtype.getText());
//                try {
                if (lbltitle.getText() == "New Appointment") {
                    AppStat = DBConnection.getImpl().makeAppointment(data);

                } else {

                    AppStat = DBConnection.getImpl().updateAppointment(data);

                }
//                } 
//catch (ClassNotFoundException ex) {
//                    Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SQLException ex) {
//                    Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
//                }
                if (AppStat == true) {
                    apptCAL.refreshCalendar(apptCAL.getCurrentMonth(), apptCAL.getCurrentYear());
                    JOptionPane.showMessageDialog(frame,
                            "Data have been successfully saved.",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Failed save Data.",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                
                Vector<String> data = new Vector<String>();
                boolean AppStat = false;
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateSEL = formatter.format(DatePicker.getDate());
                data.add(tboxApptID.getText());
                data.add(dateSEL);
                data.add(String.valueOf(CBtime.getSelectedItem()));
                data.add(tboxPMI.getText());
                data.add(tboxPname.getText());
                data.add(String.valueOf(CBdoc.getSelectedItem()));
                data.add(String.valueOf(Cblocation.getSelectedItem()));
                data.add(String.valueOf(CBdiscipline.getSelectedItem()));
                data.add(String.valueOf(CBsubdiscipline.getSelectedItem()));
                data.add(tboxAtype.getText());
                try {
                    if (lbltitle.getText() == "New Appointment") {

                        patientAppointment APPmake = new patientAppointment();
                        AppStat = APPmake.makeAppointment(data);

                    } else {
                        patientAppointment APPmake = new patientAppointment();
                        AppStat = APPmake.updateAppointment(data);

                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (AppStat == true) {
                    apptCAL.refreshCalendar(apptCAL.getCurrentMonth(), apptCAL.getCurrentYear());
                    JOptionPane.showMessageDialog(frame,
                            "Data have been successfully saved.",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Failed save Data.",
                            "PMAS Notification",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            }
            
            //online if
//            LongRunProcess.check_network2();//ping shj
//            if (Session.getPrev_stat()) {
//                
//            } 
//            
//            //offline else
//            else {

                

//            }
            
        }//if else end
       
//    Session.setPrev_stat(false);
//    Session.setCurr_stat(false);


    }//GEN-LAST:event_btnSaveActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBdiscipline;
    private javax.swing.JComboBox CBdoc;
    private javax.swing.JComboBox CBsubdiscipline;
    private javax.swing.JComboBox CBtime;
    private javax.swing.JComboBox Cblocation;
    private com.toedter.calendar.JDateChooser DatePicker;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDEL;
    private javax.swing.JButton btnMykad;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboxIdTypeRegister;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JRadioButton rbNewRegister;
    private javax.swing.JRadioButton rbOldRegister;
    private javax.swing.JTextField tboxApptID;
    private javax.swing.JTextField tboxAtype;
    private javax.swing.JTextField tboxIC;
    private javax.swing.JTextField tboxPMI;
    private javax.swing.JTextField tboxPname;
    private javax.swing.JTextField tboxSearchIC;
    private javax.swing.JTextField tboxSearchPMI;
    private javax.swing.JTextField tboxsearchID;
    // End of variables declaration//GEN-END:variables

}
