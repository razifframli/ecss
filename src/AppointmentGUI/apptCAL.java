/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * apptCAL.java
 *
 * Created on May 12, 2012, 5:04:41 PM
 */

package AppointmentGUI;

import GUI.MainPage;
import Helper.Session;
//import conndb.connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author WC
 */
public class apptCAL extends javax.swing.JPanel {
    private DefaultTableModel modelcal;
    private String[] colDate;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static Date dNow;
    static SimpleDateFormat formatter = new SimpleDateFormat("E dd MMM yyyy");
    SimpleDateFormat realformat = new SimpleDateFormat("dd/MM/yyyy");
    String dt;
    String[] pattern2 = {"dd/MM/yyyy", "dd-MM-yyyy", "E dd MMM yyyy"};
    static Vector<String> colValue;
    private JFrame frame = new JFrame("PMAS");
    private ApptCalender PatAppt;
    /** Creates new form apptCAL */



    public apptCAL(ApptCalender apptcal) {
//        try {
//              // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//               UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        }
//        catch (ClassNotFoundException e) {System.out.println("11");}
//
//        catch (InstantiationException e) {System.out.println("121");}
//
//        catch (IllegalAccessException e) {System.out.println("311");}
//
//        catch (UnsupportedLookAndFeelException e) {System.out.println("114");}
        this.PatAppt = apptcal;
        initComponents();
        gettodayDate();
//         try {
//               // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//               UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        }
//        catch (ClassNotFoundException e) {System.out.println("11");}
//
//        catch (InstantiationException e) {System.out.println("121");}
//
//        catch (IllegalAccessException e) {System.out.println("311");}
//
//        catch (UnsupportedLookAndFeelException e) {System.out.println("114");}

        modelcal = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        
           String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
            
//        for (int i=0; i<7; i++){
//            modelcal.addColumn(headers[i] + "date 1", test);
//
//                    }

        System.out.println(realYear +" "+ realMonth+" "  +realDay);
 
        refreshCalendar(currentMonth,currentYear);
       // todaydate();
        //tblCal.setModel(modelcal);
    
   
    }

    public  void refreshCalendar(int month, int year){

        //Variables

        modelcal = new DefaultTableModel();
        modelcal.setRowCount(16);
//        modelcal.setColumnCount(7);
        colDate = new String[7];
        String[] fill = {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,};
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        int nod, som; //Number Of Days, Start Of Month

        lblMonth.setText(months[month]);
        lblyear.setText(String.valueOf(currentYear));

        GregorianCalendar cal = new GregorianCalendar(year, month, 1);

        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        System.out.println(nod+" aa "+som);

        Iterator itr = DateUtils.iterator(dNow, DateUtils.RANGE_WEEK_CENTER);
       
        int i=0;
         while (itr.hasNext()) {

             Calendar gCal = (Calendar) itr.next();
             colDate[i] = realformat.format(gCal.getTime());
             dt = formatter.format(gCal.getTime());
             modelcal.addColumn(dt, fill);
             modelcal.addColumn("ID", fill);
           
             //display only
             System.out.println(dt);
             System.out.println(colDate[i]+"    weqeaca");

             i++;
         }
             getPatient(colDate);
          //modelcal.setValueAt("asdsadaasda", 15, 12);

//        for (int k=0; k<16; k++){
//            for (int j=0; j<7; j++){
//                modelcal.setValueAt(null, k, j);
//            }
//
//        }

        
        
        tblCal.setModel(modelcal);
        // hide column
        for(int col = 1 ; col < 14; col+=2){
            tblCal.getColumnModel().getColumn(col).setMinWidth(0);
            tblCal.getColumnModel().getColumn(col).setMaxWidth(0);
            tblCal.getColumnModel().getColumn(col).setWidth(0);
        }
       // tblCal.getCellRenderer(som, som);
//        tblCal.getColumnModel().getColumn(1).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(1).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(1).setWidth(0);
//        tblCal.getColumnModel().getColumn(3).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(3).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(3).setWidth(0);
//        tblCal.getColumnModel().getColumn(5).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(5).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(5).setWidth(0);
//        tblCal.getColumnModel().getColumn(7).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(7).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(7).setWidth(0);
//        tblCal.getColumnModel().getColumn(9).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(9).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(9).setWidth(0);
//        tblCal.getColumnModel().getColumn(11).setWidth(0);
//        tblCal.getColumnModel().getColumn(11).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(11).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(13).setMinWidth(0);
//        tblCal.getColumnModel().getColumn(13).setMaxWidth(0);
//        tblCal.getColumnModel().getColumn(13).setWidth(0);
//
        tblCal.getTableHeader().setBackground(new Color(223, 255, 223));
        tblCal.setCellSelectionEnabled(true);
        //tblCal.getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
        tblCal.getTableHeader().setPreferredSize(new Dimension(tblCal.getColumnModel().getTotalColumnWidth(),32));
        fixedTable.getTableHeader().setPreferredSize(new Dimension(tblCal.getColumnModel().getTotalColumnWidth(),32));

         tblCal.setDefaultRenderer(tblCal.getColumnClass(0), new tblCalendarRenderer());
     //  tblCal.getTableHeader().setDefaultRenderer(new tblheaderRenderer());

 }

    
    
    //dunnid
    public void todaydate(){
     //   try {
            dNow = new Date();
         //   String[] pattern2 = {"dd/MM/yyyy", "dd-MM-yyyy"};
          //  SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd");
         //   String dt = formatter.format(dNow);
          //  Date Dnow1 = DateUtils.parseDate(dt, pattern2);

         Iterator itr = DateUtils.iterator(dNow, DateUtils.RANGE_WEEK_CENTER);

         while (itr.hasNext()) {
         Calendar gCal = (Calendar) itr.next();
         dt = formatter.format(gCal.getTime());
         modelcal.addColumn(dt);
         System.out.println(dt);

         }
//        } catch (ParseException ex) {
//            Logger.getLogger(apptCAL.class.getName()).log(Level.SEVERE, null, ex);
//        }

 }

    //get today date
    public void gettodayDate(){

        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

         dNow = new Date();


    }


    public void getPatient(String[] calDate){
        try {
            String name= null;
            String appID= null;
            String slot= null;
            int rw;
            colValue= new Vector<String>();
            //Connection hconn = connection.HSQLconnect();
            String sql="SELECT * FROM PMS_APPOINTMENT_LIST where Appointment_Date=?";
                int col =0;
               
            for(int index =0; index< calDate.length ; index++ ){
               
                System.out.println(calDate[index]);
                PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
                ps.setString(1,calDate[index]);

                 ResultSet rs = ps.executeQuery();

                 while(rs.next()){

                   appID= rs.getString(1);
                   slot= rs.getString("APPOINTMENT_TIME");
                   name = rs.getString(5);
                   rw =timeToRow(slot);
                   System.out.println(slot+" qweqwewq");
                   modelcal.setValueAt(appID, rw, col+1);
                   modelcal.setValueAt(name, rw, col);
                   colValue.add(name);
                 }
                col+=2;
            }
          //hconn.close();



//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(apptCAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(apptCAL.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public int timeToRow(String tmslot){
        int row=0;
        int timeslot= Integer.valueOf(tmslot);
       if(timeslot == 800){ row= 0; }
       else if (timeslot == 830) { row= 1;}
       else if (timeslot ==900) { row= 2;}
       else if (timeslot ==930) { row= 3;}
       else if (timeslot ==1000) { row= 4;}
       else if (timeslot ==1030) { row= 5;}
       else if (timeslot ==1100) { row= 6;}
       else if (timeslot ==1130) { row= 7;}
       else if (timeslot ==1200) { row= 8;}
       else if (timeslot ==1230) { row= 9;}
       else if (timeslot ==1400) { row= 10;}
       else if (timeslot ==1430) { row= 11;}
       else if (timeslot ==1500) { row= 12;}
       else if (timeslot ==1530) { row= 13;}
       else if (timeslot ==1600) { row= 14;}
       else if (timeslot ==1630) { row= 15;}

       return row;

    }

       public String rowtoTime(int row){
        String slottime="0";

       if(row == 0){ slottime = "0800"; }
        else if (row == 1){ slottime = "0830"; }
        else if (row == 2){ slottime = "0900"; }
        else if (row == 3){ slottime = "0930"; }
        else if (row == 4){ slottime = "1000"; }
        else if (row == 5){ slottime = "1030"; }
        else if (row == 6){ slottime = "1100"; }
        else if (row == 7){ slottime = "1130"; }
        else if (row == 8){ slottime = "1200"; }
        else if (row == 9){ slottime = "1230"; }
        else if (row == 10){ slottime = "1400"; }
        else if (row == 11){ slottime = "1430"; }
        else if (row == 12){ slottime = "1500"; }
        else if (row == 13){ slottime = "1530"; }
        else if (row == 14){ slottime = "1600"; }
        else if (row == 15){ slottime = "1630"; }

       return slottime;

    }
   public static int getCurrentMonth() {
        return currentMonth;
    }

    public static int getCurrentYear() {
        return currentYear;
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnPrevWeek = new javax.swing.JButton();
        lblWeek = new javax.swing.JLabel();
        btnNextWeek = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnToday = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnPrevMont = new javax.swing.JButton();
        lblMonth = new javax.swing.JLabel();
        btnNextMonth = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fixedTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCal = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jPanel5 = new javax.swing.JPanel();
        btnPrevYear = new javax.swing.JButton();
        lblyear = new javax.swing.JLabel();
        btnNextYear = new javax.swing.JButton();
        btnBackMain = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(800, 700));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1160, 612));

        jPanel2.setBackground(new java.awt.Color(222, 240, 253));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnPrevWeek.setText("<<");
        btnPrevWeek.setPreferredSize(new java.awt.Dimension(55, 30));
        btnPrevWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevWeekActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrevWeek);

        lblWeek.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblWeek.setText("Week");
        jPanel2.add(lblWeek);

        btnNextWeek.setText(">>");
        btnNextWeek.setPreferredSize(new java.awt.Dimension(55, 30));
        btnNextWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextWeekActionPerformed(evt);
            }
        });
        jPanel2.add(btnNextWeek);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnToday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnToday.setText("Today");
        btnToday.setPreferredSize(new java.awt.Dimension(71, 30));
        btnToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodayActionPerformed(evt);
            }
        });
        jPanel3.add(btnToday);

        jPanel1.setBackground(new java.awt.Color(222, 240, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnPrevMont.setText("<<");
        btnPrevMont.setMaximumSize(new java.awt.Dimension(55, 30));
        btnPrevMont.setMinimumSize(new java.awt.Dimension(55, 30));
        btnPrevMont.setPreferredSize(new java.awt.Dimension(55, 30));
        btnPrevMont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevMontActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrevMont);

        lblMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMonth.setText("month");
        jPanel1.add(lblMonth);

        btnNextMonth.setText(">> ");
        btnNextMonth.setPreferredSize(new java.awt.Dimension(55, 30));
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });
        jPanel1.add(btnNextMonth);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Patient Appointment");

        jPanel4.setMaximumSize(new java.awt.Dimension(1083, 438));
        jPanel4.setMinimumSize(new java.awt.Dimension(1083, 438));

        fixedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0800"},
                {"0830"},
                {"0900"},
                {"0930"},
                {"1000"},
                {"1030"},
                {"1100"},
                {"1130"},
                {"1200"},
                {"1230"},
                {"1400"},
                {"1430"},
                {"1500"},
                {"1530"},
                {"1600"},
                {"1630"}
            },
            new String [] {
                "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fixedTable.setEnabled(false);
        fixedTable.setFocusable(false);
        fixedTable.setMaximumSize(new java.awt.Dimension(10, 400));
        fixedTable.setMinimumSize(new java.awt.Dimension(10, 400));
        fixedTable.setPreferredSize(new java.awt.Dimension(10, 400));
        fixedTable.setRowHeight(25);
        jScrollPane1.setViewportView(fixedTable);

        tblCal.setModel(new javax.swing.table.DefaultTableModel(
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
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tblCal.setColumnSelectionAllowed(true);
        tblCal.setRowHeight(25);
        tblCal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCal.getTableHeader().setResizingAllowed(false);
        tblCal.getTableHeader().setReorderingAllowed(false);
        tblCal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCalMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCal);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(222, 240, 253));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnPrevYear.setText("<<");
        btnPrevYear.setPreferredSize(new java.awt.Dimension(55, 30));
        btnPrevYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevYearActionPerformed(evt);
            }
        });
        jPanel5.add(btnPrevYear);

        lblyear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblyear.setText("Year");
        jPanel5.add(lblyear);

        btnNextYear.setText(">>");
        btnNextYear.setPreferredSize(new java.awt.Dimension(55, 30));
        btnNextYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextYearActionPerformed(evt);
            }
        });
        jPanel5.add(btnNextYear);

        btnBackMain.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBackMain.setText("Main Menu");
        btnBackMain.setPreferredSize(new java.awt.Dimension(71, 30));
        btnBackMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackMainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(297, 297, 297)
                            .addComponent(btnBackMain, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(113, 113, 113)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnBackMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevMontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevMontActionPerformed
        // TODO add your handling code here:
             dNow=DateUtils.addMonths(dNow, -1);

            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }

            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
    }//GEN-LAST:event_btnPrevMontActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        // TODO add your handling code here:
        dNow=DateUtils.addMonths(dNow, 1);

           if (currentMonth == 11){ //Foward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Foward one month
                currentMonth += 1;
            }
        refreshCalendar(currentMonth, currentYear);


    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodayActionPerformed
        // TODO add your handling code here:
        dNow=new Date();
        gettodayDate();
       refreshCalendar(currentMonth, currentYear);

    }//GEN-LAST:event_btnTodayActionPerformed

    private void btnPrevWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevWeekActionPerformed
        // TODO add your handling code here:
        dNow=DateUtils.addDays(dNow, -7);
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        currentMonth= Integer.valueOf(sdf.format(dNow))-1;
        currentYear=Integer.valueOf(sdfy.format(dNow));
        refreshCalendar(currentMonth, currentYear);


    }//GEN-LAST:event_btnPrevWeekActionPerformed

    private void btnNextWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextWeekActionPerformed
        // TODO add your handling code here:
        dNow=DateUtils.addDays(dNow, 7);
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        currentMonth= Integer.valueOf(sdf.format(dNow))-1;
        currentYear=Integer.valueOf(sdfy.format(dNow));

        refreshCalendar(currentMonth, currentYear);

    }//GEN-LAST:event_btnNextWeekActionPerformed

    private void tblCalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCalMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                JTable target = (JTable)evt.getSource();
                int Indexrow = target.getSelectedRow();
                int Indexcol = target.getSelectedColumn();
                String getdate= modelcal.getColumnName(Indexcol);
                String selvalue =(String) target.getValueAt(Indexrow,Indexcol+1);
                String slottime= rowtoTime(Indexrow);
                Date datechosen = DateUtils.parseDate(getdate, pattern2);
                System.out.println(datechosen+" from click");
                if(DateUtils.isSameDay(datechosen, new Date()) || datechosen.compareTo(new Date()) >= 0)
                {
                    PatientDetail patD= new PatientDetail(this, selvalue, slottime, datechosen , getdate);
                    patD.setVisible(true);
                }
                else if (selvalue != null){
                    PatientDetail patD= new PatientDetail(this, selvalue, slottime, datechosen, getdate );
                    patD.setVisible(true);
                   System.out.println(selvalue+" from clicasdadasak"); 
                }
                else{
                 JOptionPane.showMessageDialog(frame,
                                "Cant make appointment on past date",
                                "PMAS error",
                                JOptionPane.OK_OPTION);
                
                }    
                
            } catch (ParseException ex) {
                Logger.getLogger(apptCAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_tblCalMouseClicked

    private void btnNextYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextYearActionPerformed
         currentYear+=1;
         dNow=DateUtils.addYears(dNow, 1);
         refreshCalendar(currentMonth, currentYear);
    }//GEN-LAST:event_btnNextYearActionPerformed

    private void btnPrevYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevYearActionPerformed
        // TODO add your handling code here:
          currentYear -=1;
          dNow=DateUtils.addYears(dNow, -1);
          refreshCalendar(currentMonth, currentYear);
        
    }//GEN-LAST:event_btnPrevYearActionPerformed

    private void btnBackMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackMainActionPerformed
        // TODO add your handling code here:
        MainPage MP = new MainPage();
        MP.show();
        PatAppt.dispose();
        
        
    }//GEN-LAST:event_btnBackMainActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackMain;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnNextWeek;
    private javax.swing.JButton btnNextYear;
    private javax.swing.JButton btnPrevMont;
    private javax.swing.JButton btnPrevWeek;
    private javax.swing.JButton btnPrevYear;
    private javax.swing.JButton btnToday;
    private javax.swing.JTable fixedTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblWeek;
    private javax.swing.JLabel lblyear;
    private javax.swing.JTable tblCal;
    // End of variables declaration//GEN-END:variables


//        static class tblCalendarRenderer extends DefaultTableCellRenderer{
//
//        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
//
//            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if (column == 0 || column== 6){ //Week-end
//
//               setBackground(new Color(255, 220, 220));
////
//         }
////
////            else{ //Week
////
////                setBackground(new Color(255, 255, 255));
////
////            }
//
//          //  if (value != null){
////                 System.out.println(value+" value fromcell");
////                for(int j=0; j< colValue.size(); j++)
////                {
////                    if(value==colValue.elementAt(j)){
////                        System.out.println(colValue.elementAt(j));
////                         setBackground(new Color(255, 255, 163));
////                    }
////                }
//           // }
//
//            setBorder(BorderFactory.createLineBorder(new Color(212,217,216), 1));
//
//            setForeground(Color.black);
//
//            return this;
//
//        }
//    }

        static class tblCalendarRenderer extends DefaultTableCellRenderer{

        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            if (row % 2 == 0){ //odd row

                setBackground(new Color(237, 243, 254));

            }

            else{ //Week

                setBackground(new Color(255, 255, 255));

            }

            if (value != null){
              for(int j=0; j< colValue.size(); j++){
                if (value==colValue.elementAt(j)){ //Today

                    setBackground(new Color(240, 252, 127));
              }
              }

            }
            if (table.isCellSelected(row, column)){
                  setBackground(new Color(227,179,214));}
            
       

           setBorder(BorderFactory.createLineBorder(new Color(212,217,216), 1));

            setForeground(Color.black);

            return this;

        }

    }

         static class tblheaderRenderer extends DefaultTableCellRenderer{

        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
//            if (column == 0 || column == 6){ //Week-end
//
//                setBackground(new Color(255, 255, 255));
//
//            }
//
//            else{ //Week
//
//                setBackground(new Color(255, 255, 255));
//
//            }
//
//            if (value != null){
//              for(int j=0; j< colValue.size(); j++){
//                if (value==colValue.elementAt(j)){ //Today
//
//                    setBackground(new Color(240, 252, 127));
//              }
//              }
//
//            }
//            if (table.isCellSelected(row, column)){
//                  setBackground(new Color(227,179,214));}



            

            setForeground(Color.black);

            return this;

        }

    }


}
