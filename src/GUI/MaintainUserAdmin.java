/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBConnection.DBConnection;
import Helper.J;
import Helper.S;
import Helper.Session;
import api.LookupController;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import library.Func;
import library.LongRunProcess;
import oms.rmi.server.Message;

/**
 *
 * @author End User
 */
public class MaintainUserAdmin extends javax.swing.JFrame {
    
    private static int num_cols = 6;
    private static ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    public void setListOfStaffs() {
        
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

                data = DBConnection.getImpl().getListOfStaffs(Session.getUser_id());

                System.out.println(".....Message Sent....");
            } catch (Exception e) {
                //offline
                data = DBConnection.getListOfStaffs(Session.getUser_id());
                J.o("Network Error", "Network Error!", 0);
                //e.printStackTrace();
            }
//        } else {
//            //offline
//            data = DBConnection.getListOfStaffs(Session.getUser_id());
//        }
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);
        
        try {
            S.oln("data.size():"+data.size());
            if (data.size() > 0) {
                resetRowTable(data.size());
                for (int i = 0; i < data.size(); i++) {
                    S.oln("data.get(i):"+data.get(i));
                    for (int j = 0; j < num_cols; j++) {
                        tbl_listofstaffs.getModel().setValueAt(data.get(i).get(j), i, j);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void resetRowTable(int row) {
        tbl_listofstaffs.setModel(new javax.swing.table.DefaultTableModel(
                new Object[row][num_cols],
                new String[]{
                    "User ID", "Staff Name", "Role", "Health Facility Code", "Discipline", "Sub-Discipline"
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
    
    private DefaultComboBoxModel UniCBmodel;
    public void getLookupTable() {
        try {
            LookupController CBoxloader = new LookupController();

            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0081", "Health Facility", true);
            cbx_hfc.setModel(UniCBmodel);

            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0041", "Gender", true);
            cbx_gender.setModel(UniCBmodel);

            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0050", "Occupation", true);
            cbx_occupation.setModel(UniCBmodel);
            
            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0072", "Discipline", true);
            cbx_discipline.setModel(UniCBmodel);
            
            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0071", "Sub Discipline", true);
            cbx_subdiscipline.setModel(UniCBmodel);
            
            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0042", "Role", true);
            cbx_role.setModel(UniCBmodel);
            
            UniCBmodel = new DefaultComboBoxModel();
            UniCBmodel = CBoxloader.getLookupReferences("0042", "Role", true);
            cmb_roles.setModel(UniCBmodel);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setTutupTab() {
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(2, false);
    }
    
    /**
     * Creates new form MaintainUserAdmin
     */
    public MaintainUserAdmin() {
        initComponents();
        
        setLocationRelativeTo(null);   //center the window
        
        setListOfStaffs();
        
        getLookupTable();
        
        clearStaffForm();
        
        setTutupTab();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cmb_modules = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        cmb_roles = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_listofstaffs = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_user_id = new javax.swing.JTextField();
        cbx_hfc = new javax.swing.JComboBox();
        txt_pwd1 = new javax.swing.JPasswordField();
        txt_pwd2 = new javax.swing.JPasswordField();
        txt_full_name = new javax.swing.JTextField();
        cbx_occupation = new javax.swing.JComboBox();
        dob_birth_date = new com.toedter.calendar.JDateChooser();
        cbx_gender = new javax.swing.JComboBox();
        txt_new_icno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_home_phone = new javax.swing.JTextField();
        txt_office_phone = new javax.swing.JTextField();
        txt_mobile_phone = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbx_role = new javax.swing.JComboBox();
        cbx_discipline = new javax.swing.JComboBox();
        cbx_subdiscipline = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        dob_start_date = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        dob_end_date = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinical Support System");

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(51, 51, 255));
        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Clinical Support System");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel24.setText("Roles : ");

        cmb_modules.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select Modules -" }));

        jLabel25.setText("Modules : ");

        cmb_roles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select Roles -" }));

        jButton8.setText("Assign");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_roles, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_modules, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cmb_roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cmb_modules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(333, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Assign Responsibilities To Role", jPanel1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("LIST OF STAFFS");

        tbl_listofstaffs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "Staff Name", "Role", "Health Facility Code", "Discipline", "Sub-Discipline"
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
        tbl_listofstaffs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_listofstaffsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_listofstaffs);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("FORM OF STAFF");

        jLabel3.setText("User ID : ");

        jLabel4.setText("Health Facility Code : ");

        jLabel5.setText("Password : ");

        jLabel6.setText("Full Name : ");

        jLabel7.setText("Occupation : ");

        jLabel8.setText("Birth Date : ");

        jLabel9.setText("Gender : ");

        jLabel10.setText("New IC No. : ");

        jLabel11.setText("Home Phone No. : ");

        jLabel12.setText("Office Phone No. : ");

        jLabel13.setText("Mobile Phone No. : ");

        jLabel14.setText("Re-enter Password : ");

        cbx_hfc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbx_occupation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dob_birth_date.setDateFormatString("dd/MM/yyyy");
        dob_birth_date.setMaxSelectableDate(new java.util.Date(253370739694000L));
        dob_birth_date.setMinSelectableDate(new java.util.Date(-62135794706000L));

        cbx_gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Example: 31/10/1989");

        jLabel16.setText("E-mail : ");

        jLabel18.setText("Role : ");

        jLabel19.setText("Discipline : ");

        jLabel20.setText("Sub-Discipline : ");

        cbx_role.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbx_discipline.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbx_subdiscipline.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Start Date : ");

        dob_start_date.setDateFormatString("dd/MM/yyyy");

        jLabel21.setText("Example: 31/10/1989");

        jLabel22.setText("End Date : ");

        dob_end_date.setDateFormatString("dd/MM/yyyy");

        jLabel23.setText("Example: 31/10/1989");

        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_user_id, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbx_hfc, 0, 176, Short.MAX_VALUE)
                                .addComponent(txt_pwd1)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9))
                                    .addGap(7, 7, 7))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel8))
                                    .addGap(44, 44, 44)))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(dob_birth_date, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel15))
                                .addComponent(txt_pwd2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbx_occupation, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_new_icno)
                                .addComponent(txt_full_name)
                                .addComponent(cbx_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txt_home_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_mobile_phone, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(txt_office_phone)
                                    .addComponent(txt_email)
                                    .addComponent(cbx_role, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbx_discipline, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbx_subdiscipline, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel22))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(dob_end_date, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(dob_start_date, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_user_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbx_hfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_pwd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_pwd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_full_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbx_occupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dob_birth_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_new_icno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_home_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_office_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_mobile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbx_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbx_discipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbx_subdiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dob_start_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel22))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dob_end_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(87, 87, 87)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Maintain Staff Details", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Assign Roles To Staff", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CodeMaintainance cm = new CodeMaintainance();
        cm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_addStaff() {
        
        //start connection
//        LongRunProcess.check_network2();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str_dob_birth_date;
        String str_dob_start_date;
        String str_dob_end_date;
        try {
            str_dob_birth_date = formatter.format(dob_birth_date.getDate());
            str_dob_start_date = formatter.format(dob_start_date.getDate());
            str_dob_end_date = formatter.format(dob_end_date.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }

        boolean isStaff = false;
//        if (Session.getPrev_stat()) {
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                isStaff = DBConnection.getImpl().isStaffs(Func.trim(txt_user_id.getText()));

            } catch (Exception e) {
                //offline
                isStaff = DBConnection.isStaffs(Func.trim(txt_user_id.getText()));
                J.o("Network Error", "Network Error!", 0);
                //e.printStackTrace();
            }
//        } else {
//            isStaff = DBConnection.isStaffs(Func.trim(txt_user_id.getText()));
//        }
        if (isStaff) {
            J.o("Staff Existed", "User ID has been used!", 0);
            return;
        }

        if (!String.valueOf(txt_pwd1.getPassword()).equals(String.valueOf(txt_pwd2.getPassword()))) {
            J.o("Mismatch Password", "Password doesn't match!", 0);
            return;
        }

        String data1[] = {
            Func.trim(txt_user_id.getText()),
            Func.trim(cbx_hfc.getSelectedItem().toString()),
            Func.trim(String.valueOf(txt_pwd2.getPassword())),
            Func.trim(txt_full_name.getText()),
            Func.trim(cbx_occupation.getSelectedItem().toString()),
            Func.trim(Func.datetosql(str_dob_birth_date)),
            Func.trim(cbx_gender.getSelectedItem().toString()),
            Func.trim(txt_new_icno.getText()),
            Func.trim(txt_home_phone.getText()),
            Func.trim(txt_office_phone.getText()),
            Func.trim(txt_mobile_phone.getText()),
            Func.trim("1"),
            Func.trim("1"),
            Func.trim(txt_email.getText()),
            Func.trim("Fixed"),
            Func.trim(Func.datetosql(str_dob_start_date)),
            Func.trim(Func.datetosql(str_dob_end_date))
        };
        String data2[] = {
            Func.trim(cbx_hfc.getSelectedItem().toString()),
            Func.trim(txt_user_id.getText()),
            Func.trim(cbx_role.getSelectedItem().toString()),
            Func.trim(cbx_discipline.getSelectedItem().toString()),
            Func.trim(cbx_subdiscipline.getSelectedItem().toString())
        };

        boolean stat = false;
        //online
//        if (Session.getPrev_stat()) {
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                stat = DBConnection.getImpl().addStaff(data1, data2);

            } catch (Exception e) {
                //offline
                //e.printStackTrace();
                J.o("Network Error", "Network Error!", 0);
            }
//        }

        //offline
        stat = DBConnection.addStaff(data1, data2);

        if (stat) {
            J.o("Add Success", "Add Staff Success ..", JOptionPane.INFORMATION_MESSAGE);
        } else {
            J.o("Add Failed", "Add Staff Failed!!", JOptionPane.ERROR_MESSAGE);
        }

        //end connection
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

        //refresh table list of staffs
        setListOfStaffs();
        clearStaffForm();
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        btn_addStaff();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void clearStaffForm() {
        txt_user_id.setText("");
        cbx_hfc.setSelectedIndex(0);
        txt_pwd1.setText("");
        txt_pwd2.setText("");
        txt_full_name.setText("");
        cbx_occupation.setSelectedIndex(0);
        dob_birth_date.setDate(null);
        cbx_gender.setSelectedIndex(0);
        txt_new_icno.setText("");
        txt_home_phone.setText("");
        txt_mobile_phone.setText("");
        txt_office_phone.setText("");
        txt_email.setText("");
        cbx_role.setSelectedIndex(0);
        cbx_discipline.setSelectedIndex(0);
        cbx_subdiscipline.setSelectedIndex(0);
        dob_start_date.setDate(null);
        dob_end_date.setDate(null);
    }
    
    private void tbl_listofstaffsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_listofstaffsMouseClicked
        // TODO add your handling code here:
        int row = tbl_listofstaffs.getSelectedRow();
        String user_id = "-";
        if(data.size() > 0) {
            try {
                user_id = data.get(row).get(0);
            } catch (Exception e) {
                clearStaffForm();
                e.printStackTrace();
            }
            
            ArrayList<ArrayList<String>> data_user = new ArrayList<ArrayList<String>>();
            
//            LongRunProcess.check_network2();
//            //online
//            if (Session.getPrev_stat()) {
                try {
                    // fire to server port 1099
//                    ArrayList<String> listOnline = Func.readXML("online");
//                    Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                    // search for myMessage service
//                    Message impl = (Message) myRegistry.lookup("myMessage");
                    // call server's method

                    data_user = DBConnection.getImpl().getStaffs(user_id);

                } catch (Exception e) {
                    //offline
                    //e.printStackTrace();
                    data_user = DBConnection.getStaffs(user_id);
                    J.o("Network Error", "Network Error!", 0);
                }
//            } else {
//                //offline
//                data_user = DBConnection.getStaffs(user_id);
//            }
//            Session.setPrev_stat(false);
//            Session.setCurr_stat(false);
            
            if(data_user.size() > 0) {
                
                txt_user_id.setText(data_user.get(0).get(0));
                Func.cmbSelectInput(cbx_hfc, data_user.get(0).get(1));
                txt_pwd1.setText(data_user.get(0).get(2));
                txt_pwd2.setText(data_user.get(0).get(2));
                txt_full_name.setText(data_user.get(0).get(3));
                Func.cmbSelectInput(cbx_occupation, data_user.get(0).get(4));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = dateFormat.parse(Func.sqltodate(data_user.get(0).get(5)));
                } catch (Exception ex) {
                    date = null;
                    ex.printStackTrace();
                }
                dob_birth_date.setDate(date);
                Func.cmbSelectInput(cbx_gender, data_user.get(0).get(6));
                txt_new_icno.setText(data_user.get(0).get(7));
                txt_home_phone.setText(data_user.get(0).get(8));
                txt_office_phone.setText(data_user.get(0).get(9));
                txt_mobile_phone.setText(data_user.get(0).get(10));
                txt_email.setText(data_user.get(0).get(13));
                Func.cmbSelectInput(cbx_role, data_user.get(0).get(19));
                Func.cmbSelectInput(cbx_role, data_user.get(0).get(20));
                Func.cmbSelectInput(cbx_role, data_user.get(0).get(21));
                try {
                    date = dateFormat.parse(Func.sqltodate(data_user.get(0).get(15)));
                } catch (Exception ex) {
                    date = null;
                    ex.printStackTrace();
                }
                dob_start_date.setDate(date);
                try {
                    date = dateFormat.parse(Func.sqltodate(data_user.get(0).get(16)));
                } catch (Exception ex) {
                    date = null;
                    ex.printStackTrace();
                }
                dob_end_date.setDate(date);
            }
        }
    }//GEN-LAST:event_tbl_listofstaffsMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            if (txt_user_id.getText().equals("")) {
                J.o("Invalid User ID", "Don't leave user id blank!", 0);
                return;
            } else {
                int ask = JOptionPane.showConfirmDialog(null, 
                        "Are you sure want to delete this staff?", 
                        "Delete Staff", 0);
                if(ask == 0) {
                    boolean stat = false;
//                    LongRunProcess.check_network2();
//                    //online
//                    if (Session.getPrev_stat()) {
                        try {
                            // fire to server port 1099
//                            ArrayList<String> listOnline = Func.readXML("online");
//                            Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                            // search for myMessage service
//                            Message impl = (Message) myRegistry.lookup("myMessage");
                            // call server's method

                            stat = DBConnection.getImpl().deleteStaff(txt_user_id.getText());

                        } catch (Exception e) {
                            //offline
                            //e.printStackTrace();
                            stat = DBConnection.deleteStaff(txt_user_id.getText());
                            J.o("Network Error", "Network Error!", 0);
                        }
//                    }
//                    Session.setPrev_stat(false);
//                    Session.setCurr_stat(false);
                    if(stat == false) {
                        stat = DBConnection.deleteStaff(txt_user_id.getText());
                    }
                    if(stat) {
                        J.o("Delete Success", "Delete Staff Success ..", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        J.o("Delete Failed", "Delete Staff Failed!!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return;
        }
        
        //refresh table list of staffs
        setListOfStaffs();
        clearStaffForm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_updateStaff() {
        
        //start connection
//        LongRunProcess.check_network2();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str_dob_birth_date;
        String str_dob_start_date;
        String str_dob_end_date;
        try {
            str_dob_birth_date = formatter.format(dob_birth_date.getDate());
            str_dob_start_date = formatter.format(dob_start_date.getDate());
            str_dob_end_date = formatter.format(dob_end_date.getDate());
        } catch (Exception e) {
            J.o("Invalid Date", "Invalid Date Format!", 0);
            return;
        }

        String user_id = Func.trim(txt_user_id.getText());

        if (!String.valueOf(txt_pwd1.getPassword()).equals(String.valueOf(txt_pwd2.getPassword()))) {
            J.o("Mismatch Password", "Password doesn't match!", 0);
            return;
        }

        String cols1[] = {
            "USER_ID",
            "HEALTH_FACILITY_CODE",
            "PASSWORD",
            "USER_NAME",
            "OCCUPATION_CODE",
            "BIRTH_DATE",
            "SEX_CODE",
            "NEW_ICNO",
            "HOME_PHONE",
            "OFFICE_PHONE",
            "MOBILE_PHONE",
            "LOGIN_STATUS",
            "USER_STATUS",
            "E_MAIL",
            "ID_CATEGORY_CODE",
            "START_DATE",
            "END_DATE"
        };
        String data1[] = {
            Func.trim(txt_user_id.getText()),
            Func.trim(cbx_hfc.getSelectedItem().toString()),
            Func.trim(String.valueOf(txt_pwd2.getPassword())),
            Func.trim(txt_full_name.getText()),
            Func.trim(cbx_occupation.getSelectedItem().toString()),
            Func.trim(Func.datetosql(str_dob_birth_date)),
            Func.trim(cbx_gender.getSelectedItem().toString()),
            Func.trim(txt_new_icno.getText()),
            Func.trim(txt_home_phone.getText()),
            Func.trim(txt_office_phone.getText()),
            Func.trim(txt_mobile_phone.getText()),
            Func.trim("1"),
            Func.trim("1"),
            Func.trim(txt_email.getText()),
            Func.trim("Fixed"),
            Func.trim(Func.datetosql(str_dob_start_date)),
            Func.trim(Func.datetosql(str_dob_end_date))
        };
        String cols2[] = {
            "HEALTH_FACILITY_CODE",
            "USER_ID",
            "ROLE_CODE",
            "DISCIPLINE_CODE",
            "SUBDISCIPLINE_CODE"
        };
        String data2[] = {
            Func.trim(cbx_hfc.getSelectedItem().toString()),
            Func.trim(txt_user_id.getText()),
            Func.trim(cbx_role.getSelectedItem().toString()),
            Func.trim(cbx_discipline.getSelectedItem().toString()),
            Func.trim(cbx_subdiscipline.getSelectedItem().toString())
        };

        boolean stat = false;
        //online
//        if (Session.getPrev_stat()) {
            try {
                // fire to server port 1099
//                ArrayList<String> listOnline = Func.readXML("online");
//                Registry myRegistry = LocateRegistry.getRegistry(listOnline.get(0), 1099);
//
//                // search for myMessage service
//                Message impl = (Message) myRegistry.lookup("myMessage");
                // call server's method

                stat = DBConnection.getImpl().updateStaff(user_id, cols1, data1, cols2, data2);

            } catch (Exception e) {
                //offline
                //e.printStackTrace();
                J.o("Network Error", "Network Error!", 0);
            }
//        }

        //offline
        stat = DBConnection.updateStaff(user_id, cols1, data1, cols2, data2);

        if (stat) {
            J.o("Update Success", "Update Staff Success ..", JOptionPane.INFORMATION_MESSAGE);
        } else {
            J.o("Update Failed", "Update Staff Failed!!", JOptionPane.ERROR_MESSAGE);
        }

        //end connection
//        Session.setPrev_stat(false);
//        Session.setCurr_stat(false);

        //refresh table list of staffs
        setListOfStaffs();
        clearStaffForm();
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        btn_updateStaff();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        btn_addStaff();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        btn_updateStaff();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        clearStaffForm();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MaintainUserAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintainUserAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintainUserAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintainUserAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MaintainUserAdmin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cbx_discipline;
    public static javax.swing.JComboBox cbx_gender;
    public static javax.swing.JComboBox cbx_hfc;
    public static javax.swing.JComboBox cbx_occupation;
    public static javax.swing.JComboBox cbx_role;
    public static javax.swing.JComboBox cbx_subdiscipline;
    private javax.swing.JComboBox cmb_modules;
    private javax.swing.JComboBox cmb_roles;
    public static com.toedter.calendar.JDateChooser dob_birth_date;
    public static com.toedter.calendar.JDateChooser dob_end_date;
    public static com.toedter.calendar.JDateChooser dob_start_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private static javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.Label label1;
    public static javax.swing.JTable tbl_listofstaffs;
    public static javax.swing.JTextField txt_email;
    public static javax.swing.JTextField txt_full_name;
    public static javax.swing.JTextField txt_home_phone;
    public static javax.swing.JTextField txt_mobile_phone;
    public static javax.swing.JTextField txt_new_icno;
    public static javax.swing.JTextField txt_office_phone;
    public static javax.swing.JPasswordField txt_pwd1;
    public static javax.swing.JPasswordField txt_pwd2;
    public static javax.swing.JTextField txt_user_id;
    // End of variables declaration//GEN-END:variables
}
