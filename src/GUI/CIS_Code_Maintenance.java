/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Bean.CIS_Procedure;
import Bean.ComboItem;
import Bean.PhysicalExamBean;
import DBConnection.DBConnection;
import Helper.J;
import java.util.ArrayList;
import library.Func;

/**
 *
 * @author End User
 */
public class CIS_Code_Maintenance extends javax.swing.JFrame {

    private final static int rowSize = 100;
    
    /**
     * Creates new form CIS_Code_Maintenance
     */
    public CIS_Code_Maintenance() {
        initComponents();
        clearField();
        refreshTblProcedure();
        refreshTblPhysicalExam();
        defaultFunction();
        refreshComboBox();
    }
    
    public void refreshComboBox() {
        procedure_1_parent.removeAllItems();
        ArrayList<ArrayList<String>> data = DBConnection.getProcedure(1);
        for (int i = 0; i < data.size(); i++) {
            procedure_1_parent.addItem(data.get(i).get(0)+"|"+data.get(i).get(1));
        }
        procedure_2_parent.removeAllItems();
        ArrayList<ArrayList<String>> data2 = DBConnection.getProcedure(2);
        for (int i = 0; i < data2.size(); i++) {
            procedure_2_parent.addItem(data2.get(i).get(0)+"|"+data2.get(i).get(1));
        }
        
        cbx_parent_1.removeAllItems();
        ArrayList<PhysicalExamBean> pe_0 = DBConnection.getPhysicalExamAll(0);
        for (int i = 0; i < pe_0.size(); i++) {
            cbx_parent_1.addItem(new ComboItem(pe_0.get(i).getPe_name(), pe_0.get(i).getPe_cd()));
        }
        cbx_parent_2.removeAllItems();
        ArrayList<PhysicalExamBean> pe_1 = DBConnection.getPhysicalExamAll(1);
        for (int i = 0; i < pe_1.size(); i++) {
            cbx_parent_2.addItem(new ComboItem(pe_1.get(i).getPe_name(), pe_1.get(i).getPe_cd()));
        }
        cbx_parent_3.removeAllItems();
        ArrayList<PhysicalExamBean> pe_2 = DBConnection.getPhysicalExamAll(2);
        for (int i = 0; i < pe_2.size(); i++) {
            cbx_parent_3.addItem(new ComboItem(pe_2.get(i).getPe_name(), pe_2.get(i).getPe_cd()));
        }
        cbx_parent_4.removeAllItems();
        ArrayList<PhysicalExamBean> pe_3 = DBConnection.getPhysicalExamAll(3);
        for (int i = 0; i < pe_3.size(); i++) {
            cbx_parent_4.addItem(new ComboItem(pe_3.get(i).getPe_name(), pe_3.get(i).getPe_cd()));
        }
        cbx_parent_5.removeAllItems();
        ArrayList<PhysicalExamBean> pe_4 = DBConnection.getPhysicalExamAll(4);
        for (int i = 0; i < pe_4.size(); i++) {
            cbx_parent_5.addItem(new ComboItem(pe_4.get(i).getPe_name(), pe_4.get(i).getPe_cd()));
        }
        cbx_parent_6.removeAllItems();
        ArrayList<PhysicalExamBean> pe_5 = DBConnection.getPhysicalExamAll(5);
        for (int i = 0; i < pe_5.size(); i++) {
            cbx_parent_6.addItem(new ComboItem(pe_5.get(i).getPe_name(), pe_5.get(i).getPe_cd()));
        }
        cbx_parent_7.removeAllItems();
        ArrayList<PhysicalExamBean> pe_6 = DBConnection.getPhysicalExamAll(6);
        for (int i = 0; i < pe_6.size(); i++) {
            cbx_parent_7.addItem(new ComboItem(pe_6.get(i).getPe_name(), pe_6.get(i).getPe_cd()));
        }
    }
    
    public void defaultFunction() {
        btn_procedure_delete.setEnabled(false);
        btn_procedure_update.setEnabled(false);
        btn_procedure_add.setEnabled(true);
        procedure_cd.setEditable(true);
        btn_procedure_delete_1.setEnabled(false);
        btn_procedure_update_1.setEnabled(false);
        btn_procedure_add_1.setEnabled(true);
        procedure_1_cd.setEditable(true);
        btn_procedure_delete_2.setEnabled(false);
        btn_procedure_update_2.setEnabled(false);
        btn_procedure_add_2.setEnabled(true);
        procedure_2_cd.setEditable(true);
        
        btn_pe_delete_0.setEnabled(false);
        btn_pe_update_0.setEnabled(false);
        btn_pe_add_0.setEnabled(true);
        txt_pe_cd_0.setEditable(true);
        btn_pe_delete_1.setEnabled(false);
        btn_pe_update_1.setEnabled(false);
        btn_pe_add_1.setEnabled(true);
        txt_pe_cd_1.setEditable(true);
        btn_pe_delete_2.setEnabled(false);
        btn_pe_update_2.setEnabled(false);
        btn_pe_add_2.setEnabled(true);
        txt_pe_cd_2.setEditable(true);
        btn_pe_delete_3.setEnabled(false);
        btn_pe_update_3.setEnabled(false);
        btn_pe_add_3.setEnabled(true);
        txt_pe_cd_3.setEditable(true);
        btn_pe_delete_4.setEnabled(false);
        btn_pe_update_4.setEnabled(false);
        btn_pe_add_4.setEnabled(true);
        txt_pe_cd_4.setEditable(true);
        btn_pe_delete_5.setEnabled(false);
        btn_pe_update_5.setEnabled(false);
        btn_pe_add_5.setEnabled(true);
        txt_pe_cd_5.setEditable(true);
        btn_pe_delete_6.setEnabled(false);
        btn_pe_update_6.setEnabled(false);
        btn_pe_add_6.setEnabled(true);
        txt_pe_cd_6.setEditable(true);
        btn_pe_delete_7.setEnabled(false);
        btn_pe_update_7.setEnabled(false);
        btn_pe_add_7.setEnabled(true);
        txt_pe_cd_7.setEditable(true);
    }
    
    public void refreshTblPhysicalExam() {
        // level system
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 3; j++) {
                tbl_pe_0.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_0 = DBConnection.getPhysicalExamAll(0);
        for (int i = 0; i < pe_0.size(); i++) {
            tbl_pe_0.getModel().setValueAt(pe_0.get(i).getPe_cd(), i, 0);
            tbl_pe_0.getModel().setValueAt(pe_0.get(i).getPe_name(), i, 1);
            tbl_pe_0.getModel().setValueAt(pe_0.get(i).getPe_status(), i, 2);
        }
        
        // level 1
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_1.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_1 = DBConnection.getPhysicalExamAll(1);
        for (int i = 0; i < pe_1.size(); i++) {
            tbl_pe_1.getModel().setValueAt(pe_1.get(i).getPe_cd(), i, 0);
            tbl_pe_1.getModel().setValueAt(pe_1.get(i).getPe_name(), i, 1);
            tbl_pe_1.getModel().setValueAt(pe_1.get(i).getPe_parent(), i, 2);
            tbl_pe_1.getModel().setValueAt(pe_1.get(i).getPe_status(), i, 3);
        }
        
        // level 2
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_2.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_2 = DBConnection.getPhysicalExamAll(2);
        for (int i = 0; i < pe_2.size(); i++) {
            tbl_pe_2.getModel().setValueAt(pe_2.get(i).getPe_cd(), i, 0);
            tbl_pe_2.getModel().setValueAt(pe_2.get(i).getPe_name(), i, 1);
            tbl_pe_2.getModel().setValueAt(pe_2.get(i).getPe_parent(), i, 2);
            tbl_pe_2.getModel().setValueAt(pe_2.get(i).getPe_status(), i, 3);
        }
        
        // level 3
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_3.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_3 = DBConnection.getPhysicalExamAll(3);
        for (int i = 0; i < pe_3.size(); i++) {
            tbl_pe_3.getModel().setValueAt(pe_3.get(i).getPe_cd(), i, 0);
            tbl_pe_3.getModel().setValueAt(pe_3.get(i).getPe_name(), i, 1);
            tbl_pe_3.getModel().setValueAt(pe_3.get(i).getPe_parent(), i, 2);
            tbl_pe_3.getModel().setValueAt(pe_3.get(i).getPe_status(), i, 3);
        }
        
        // level 4
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_4.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_4 = DBConnection.getPhysicalExamAll(4);
        for (int i = 0; i < pe_4.size(); i++) {
            tbl_pe_4.getModel().setValueAt(pe_4.get(i).getPe_cd(), i, 0);
            tbl_pe_4.getModel().setValueAt(pe_4.get(i).getPe_name(), i, 1);
            tbl_pe_4.getModel().setValueAt(pe_4.get(i).getPe_parent(), i, 2);
            tbl_pe_4.getModel().setValueAt(pe_4.get(i).getPe_status(), i, 3);
        }
        
        // level 5
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_5.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_5 = DBConnection.getPhysicalExamAll(5);
        for (int i = 0; i < pe_5.size(); i++) {
            tbl_pe_5.getModel().setValueAt(pe_5.get(i).getPe_cd(), i, 0);
            tbl_pe_5.getModel().setValueAt(pe_5.get(i).getPe_name(), i, 1);
            tbl_pe_5.getModel().setValueAt(pe_5.get(i).getPe_parent(), i, 2);
            tbl_pe_5.getModel().setValueAt(pe_5.get(i).getPe_status(), i, 3);
        }
        
        // level 6
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_6.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_6 = DBConnection.getPhysicalExamAll(6);
        for (int i = 0; i < pe_6.size(); i++) {
            tbl_pe_6.getModel().setValueAt(pe_6.get(i).getPe_cd(), i, 0);
            tbl_pe_6.getModel().setValueAt(pe_6.get(i).getPe_name(), i, 1);
            tbl_pe_6.getModel().setValueAt(pe_6.get(i).getPe_parent(), i, 2);
            tbl_pe_6.getModel().setValueAt(pe_6.get(i).getPe_status(), i, 3);
        }
        
        // level 7
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < 4; j++) {
                tbl_pe_7.getModel().setValueAt("", i, j);
            }
        }
        ArrayList<PhysicalExamBean> pe_7 = DBConnection.getPhysicalExamAll(7);
        for (int i = 0; i < pe_7.size(); i++) {
            tbl_pe_7.getModel().setValueAt(pe_7.get(i).getPe_cd(), i, 0);
            tbl_pe_7.getModel().setValueAt(pe_7.get(i).getPe_name(), i, 1);
            tbl_pe_7.getModel().setValueAt(pe_7.get(i).getPe_parent(), i, 2);
            tbl_pe_7.getModel().setValueAt(pe_7.get(i).getPe_status(), i, 3);
        }
    }
    
    public void refreshTblProcedure() {
        for (int i = 0; i < rowSize; i++) {
            tbl_procedure.getModel().setValueAt("", i, 0);
            tbl_procedure.getModel().setValueAt("", i, 1);
            tbl_procedure.getModel().setValueAt("", i, 2);
        }
        ArrayList<ArrayList<String>> data = DBConnection.getProcedure(1);
        for (int i = 0; i < data.size() && i < rowSize; i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                tbl_procedure.getModel().setValueAt(data.get(i).get(j), i, j);
            }
        }
        
        for (int i = 0; i < rowSize; i++) {
            tbl_procedure_1.getModel().setValueAt("", i, 0);
            tbl_procedure_1.getModel().setValueAt("", i, 1);
            tbl_procedure_1.getModel().setValueAt("", i, 2);
            tbl_procedure_1.getModel().setValueAt("", i, 3);
        }
        ArrayList<ArrayList<String>> data_1 = DBConnection.getProcedure(2);
        for (int i = 0; i < data_1.size() && i < rowSize; i++) {
            tbl_procedure_1.getModel().setValueAt(data_1.get(i).get(0), i, 0);
            tbl_procedure_1.getModel().setValueAt(data_1.get(i).get(1), i, 1);
            ArrayList<String> getParent = DBConnection.getProcedureDetail(1, data_1.get(i).get(2));
            String parent = getParent.size() > 0 ? getParent.get(0)+"|"+getParent.get(1) : "|";
            tbl_procedure_1.getModel().setValueAt(parent, i, 2);
            tbl_procedure_1.getModel().setValueAt(data_1.get(i).get(3), i, 3);
        }
        
        for (int i = 0; i < rowSize; i++) {
            tbl_procedure_2.getModel().setValueAt("", i, 0);
            tbl_procedure_2.getModel().setValueAt("", i, 1);
            tbl_procedure_2.getModel().setValueAt("", i, 2);
            tbl_procedure_2.getModel().setValueAt("", i, 3);
        }
        ArrayList<ArrayList<String>> data_2 = DBConnection.getProcedure(3);
        for (int i = 0; i < data_2.size() && i < rowSize; i++) {
            tbl_procedure_2.getModel().setValueAt(data_2.get(i).get(0), i, 0);
            tbl_procedure_2.getModel().setValueAt(data_2.get(i).get(1), i, 1);
            ArrayList<String> getParent = DBConnection.getProcedureDetail(2, data_2.get(i).get(2));
            String parent = getParent.size() > 0 ? getParent.get(0)+"|"+getParent.get(1) : "|";
            tbl_procedure_2.getModel().setValueAt(parent, i, 2);
            tbl_procedure_2.getModel().setValueAt(data_2.get(i).get(3), i, 3);
        }
    }
    
    public void clearField() {
        procedure_cd.setText("");
        procedure_name.setText("");
        status.setSelectedIndex(0);
        procedure_cd.setEditable(true);
        procedure_1_cd.setText("");
        procedure_1_name.setText("");
        status_1.setSelectedIndex(0);
        procedure_1_cd.setEditable(true);
        procedure_2_cd.setText("");
        procedure_2_name.setText("");
        status_2.setSelectedIndex(0);
        procedure_2_cd.setEditable(true);
        
        txt_pe_cd_0.setText("");
        txt_pe_name_0.setText("");
        cbx_pe_status_0.setSelectedIndex(0);
        txt_pe_cd_0.setEditable(true);
        txt_pe_cd_1.setText("");
        txt_pe_name_1.setText("");
        cbx_pe_status_1.setSelectedIndex(0);
        txt_pe_cd_1.setEditable(true);
        txt_pe_cd_2.setText("");
        txt_pe_name_2.setText("");
        cbx_pe_status_2.setSelectedIndex(0);
        txt_pe_cd_2.setEditable(true);
        txt_pe_cd_3.setText("");
        txt_pe_name_3.setText("");
        cbx_pe_status_3.setSelectedIndex(0);
        txt_pe_cd_3.setEditable(true);
        txt_pe_cd_4.setText("");
        txt_pe_name_4.setText("");
        cbx_pe_status_4.setSelectedIndex(0);
        txt_pe_cd_4.setEditable(true);
        txt_pe_cd_5.setText("");
        txt_pe_name_5.setText("");
        cbx_pe_status_5.setSelectedIndex(0);
        txt_pe_cd_5.setEditable(true);
        txt_pe_cd_6.setText("");
        txt_pe_name_6.setText("");
        cbx_pe_status_6.setSelectedIndex(0);
        txt_pe_cd_6.setEditable(true);
        txt_pe_cd_7.setText("");
        txt_pe_name_7.setText("");
        cbx_pe_status_7.setSelectedIndex(0);
        txt_pe_cd_7.setEditable(true);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_pe_cd_0 = new javax.swing.JTextField();
        txt_pe_name_0 = new javax.swing.JTextField();
        cbx_pe_status_0 = new javax.swing.JComboBox();
        btn_pe_add_0 = new javax.swing.JButton();
        btn_pe_update_0 = new javax.swing.JButton();
        btn_pe_delete_0 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbl_pe_0 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_pe_cd_1 = new javax.swing.JTextField();
        txt_pe_name_1 = new javax.swing.JTextField();
        cbx_pe_status_1 = new javax.swing.JComboBox();
        btn_pe_add_1 = new javax.swing.JButton();
        btn_pe_update_1 = new javax.swing.JButton();
        btn_pe_delete_1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbx_parent_1 = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbl_pe_1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_pe_cd_2 = new javax.swing.JTextField();
        txt_pe_name_2 = new javax.swing.JTextField();
        cbx_pe_status_2 = new javax.swing.JComboBox();
        btn_pe_add_2 = new javax.swing.JButton();
        btn_pe_update_2 = new javax.swing.JButton();
        btn_pe_delete_2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        cbx_parent_2 = new javax.swing.JComboBox();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbl_pe_2 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_pe_cd_3 = new javax.swing.JTextField();
        txt_pe_name_3 = new javax.swing.JTextField();
        cbx_pe_status_3 = new javax.swing.JComboBox();
        btn_pe_add_3 = new javax.swing.JButton();
        btn_pe_update_3 = new javax.swing.JButton();
        btn_pe_delete_3 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        cbx_parent_3 = new javax.swing.JComboBox();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbl_pe_3 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txt_pe_cd_4 = new javax.swing.JTextField();
        txt_pe_name_4 = new javax.swing.JTextField();
        cbx_pe_status_4 = new javax.swing.JComboBox();
        btn_pe_add_4 = new javax.swing.JButton();
        btn_pe_update_4 = new javax.swing.JButton();
        btn_pe_delete_4 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        cbx_parent_4 = new javax.swing.JComboBox();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbl_pe_4 = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_pe_cd_5 = new javax.swing.JTextField();
        txt_pe_name_5 = new javax.swing.JTextField();
        cbx_pe_status_5 = new javax.swing.JComboBox();
        btn_pe_add_5 = new javax.swing.JButton();
        btn_pe_update_5 = new javax.swing.JButton();
        btn_pe_delete_5 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        cbx_parent_5 = new javax.swing.JComboBox();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbl_pe_5 = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_pe_cd_6 = new javax.swing.JTextField();
        txt_pe_name_6 = new javax.swing.JTextField();
        cbx_pe_status_6 = new javax.swing.JComboBox();
        btn_pe_add_6 = new javax.swing.JButton();
        btn_pe_update_6 = new javax.swing.JButton();
        btn_pe_delete_6 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        cbx_parent_6 = new javax.swing.JComboBox();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbl_pe_6 = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_pe_cd_7 = new javax.swing.JTextField();
        txt_pe_name_7 = new javax.swing.JTextField();
        cbx_pe_status_7 = new javax.swing.JComboBox();
        btn_pe_add_7 = new javax.swing.JButton();
        btn_pe_update_7 = new javax.swing.JButton();
        btn_pe_delete_7 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        cbx_parent_7 = new javax.swing.JComboBox();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbl_pe_7 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        procedure_cd = new javax.swing.JTextField();
        procedure_name = new javax.swing.JTextField();
        status = new javax.swing.JComboBox();
        btn_procedure_add = new javax.swing.JButton();
        btn_procedure_update = new javax.swing.JButton();
        btn_procedure_delete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_procedure = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        procedure_1_cd = new javax.swing.JTextField();
        procedure_1_name = new javax.swing.JTextField();
        status_1 = new javax.swing.JComboBox();
        btn_procedure_add_1 = new javax.swing.JButton();
        btn_procedure_update_1 = new javax.swing.JButton();
        btn_procedure_delete_1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        procedure_1_parent = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_procedure_1 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        procedure_2_cd = new javax.swing.JTextField();
        procedure_2_name = new javax.swing.JTextField();
        status_2 = new javax.swing.JComboBox();
        btn_procedure_add_2 = new javax.swing.JButton();
        btn_procedure_update_2 = new javax.swing.JButton();
        btn_procedure_delete_2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        procedure_2_parent = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbl_procedure_2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinical Support System");

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(51, 51, 255));
        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Clinical Support System");

        jTabbedPane1.addTab("Subjective", jScrollPane1);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam System"));

        jLabel13.setText("Physical Exam Code :");

        jLabel14.setText("Physical Exam Name :");

        jLabel15.setText("Status :");

        cbx_pe_status_0.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_0.setText("Add");
        btn_pe_add_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_0ActionPerformed(evt);
            }
        });

        btn_pe_update_0.setText("Update");
        btn_pe_update_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_0ActionPerformed(evt);
            }
        });

        btn_pe_delete_0.setText("Delete");
        btn_pe_delete_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txt_pe_cd_0))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pe_name_0, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_pe_status_0, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btn_pe_add_0, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_pe_update_0)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_pe_delete_0)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_pe_cd_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_pe_name_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(cbx_pe_status_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_0)
                    .addComponent(btn_pe_update_0)
                    .addComponent(btn_pe_delete_0))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam System"));

        tbl_pe_0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "Name", "Status"
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
        tbl_pe_0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_0MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbl_pe_0);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(394, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jScrollPane13.setViewportView(jPanel15);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 108, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("System Level", jPanel10);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 1"));

        jLabel16.setText("Physical Exam Code :");

        jLabel17.setText("Physical Exam Name :");

        jLabel18.setText("Status :");

        cbx_pe_status_1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_1.setText("Add");
        btn_pe_add_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_1ActionPerformed(evt);
            }
        });

        btn_pe_update_1.setText("Update");
        btn_pe_update_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_1ActionPerformed(evt);
            }
        });

        btn_pe_delete_1.setText("Delete");
        btn_pe_delete_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_1ActionPerformed(evt);
            }
        });

        jLabel19.setText("System Level :");

        cbx_parent_1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel18))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel20Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_1))
                                .addComponent(cbx_parent_1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_pe_cd_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_pe_name_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(cbx_pe_status_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_1)
                    .addComponent(btn_pe_update_1)
                    .addComponent(btn_pe_delete_1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 1"));

        tbl_pe_1.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_1MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tbl_pe_1);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Level 1", jPanel11);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 2"));

        jLabel20.setText("Physical Exam Code :");

        jLabel21.setText("Physical Exam Name :");

        jLabel22.setText("Status :");

        cbx_pe_status_2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_2.setText("Add");
        btn_pe_add_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_2ActionPerformed(evt);
            }
        });

        btn_pe_update_2.setText("Update");
        btn_pe_update_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_2ActionPerformed(evt);
            }
        });

        btn_pe_delete_2.setText("Delete");
        btn_pe_delete_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_2ActionPerformed(evt);
            }
        });

        jLabel23.setText("Level 1 :");

        cbx_parent_2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel22))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel22Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_2))
                                .addComponent(cbx_parent_2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_pe_cd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_pe_name_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(cbx_pe_status_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_2)
                    .addComponent(btn_pe_update_2)
                    .addComponent(btn_pe_delete_2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 2"));

        tbl_pe_2.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_2MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tbl_pe_2);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 94, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Level 2", jPanel12);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 3"));

        jLabel24.setText("Physical Exam Code :");

        jLabel25.setText("Physical Exam Name :");

        jLabel26.setText("Status :");

        cbx_pe_status_3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_3.setText("Add");
        btn_pe_add_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_3ActionPerformed(evt);
            }
        });

        btn_pe_update_3.setText("Update");
        btn_pe_update_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_3ActionPerformed(evt);
            }
        });

        btn_pe_delete_3.setText("Delete");
        btn_pe_delete_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_3ActionPerformed(evt);
            }
        });

        jLabel27.setText("Level 2 :");

        cbx_parent_3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_3, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_3))
                                .addComponent(cbx_parent_3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel27))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txt_pe_cd_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txt_pe_name_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(cbx_pe_status_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_3)
                    .addComponent(btn_pe_update_3)
                    .addComponent(btn_pe_delete_3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 3"));

        tbl_pe_3.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_3MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tbl_pe_3);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 94, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Level 3", jPanel13);

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 4"));

        jLabel28.setText("Physical Exam Code :");

        jLabel29.setText("Physical Exam Name :");

        jLabel30.setText("Status :");

        cbx_pe_status_4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_4.setText("Add");
        btn_pe_add_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_4ActionPerformed(evt);
            }
        });

        btn_pe_update_4.setText("Update");
        btn_pe_update_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_4ActionPerformed(evt);
            }
        });

        btn_pe_delete_4.setText("Delete");
        btn_pe_delete_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_4ActionPerformed(evt);
            }
        });

        jLabel31.setText("Level 3 :");

        cbx_parent_4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel31Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel31Layout.createSequentialGroup()
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29)
                                .addComponent(jLabel30))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_4, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel31Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_4))
                                .addComponent(cbx_parent_4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txt_pe_cd_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txt_pe_name_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(cbx_pe_status_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_4)
                    .addComponent(btn_pe_update_4)
                    .addComponent(btn_pe_delete_4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 4"));

        tbl_pe_4.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_4MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbl_pe_4);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 94, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Level 4", jPanel14);

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 5"));

        jLabel32.setText("Physical Exam Code :");

        jLabel33.setText("Physical Exam Name :");

        jLabel34.setText("Status :");

        cbx_pe_status_5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_5.setText("Add");
        btn_pe_add_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_5ActionPerformed(evt);
            }
        });

        btn_pe_update_5.setText("Update");
        btn_pe_update_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_5ActionPerformed(evt);
            }
        });

        btn_pe_delete_5.setText("Delete");
        btn_pe_delete_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_5ActionPerformed(evt);
            }
        });

        jLabel35.setText("Level 4 :");

        cbx_parent_5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel33)
                                .addComponent(jLabel34))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_5, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_5))
                                .addComponent(cbx_parent_5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel35))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txt_pe_cd_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_pe_name_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(cbx_pe_status_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_5)
                    .addComponent(btn_pe_update_5)
                    .addComponent(btn_pe_delete_5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 5"));

        tbl_pe_5.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_5MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tbl_pe_5);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Level 5", jPanel27);

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 6"));

        jLabel36.setText("Physical Exam Code :");

        jLabel37.setText("Physical Exam Name :");

        jLabel38.setText("Status :");

        cbx_pe_status_6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_6.setText("Add");
        btn_pe_add_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_6ActionPerformed(evt);
            }
        });

        btn_pe_update_6.setText("Update");
        btn_pe_update_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_6ActionPerformed(evt);
            }
        });

        btn_pe_delete_6.setText("Delete");
        btn_pe_delete_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_6ActionPerformed(evt);
            }
        });

        jLabel39.setText("Level 5 :");

        cbx_parent_6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel38Layout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_6, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel38Layout.createSequentialGroup()
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel37)
                                .addComponent(jLabel38))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_6, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel38Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_6))
                                .addComponent(cbx_parent_6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel39))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_pe_cd_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txt_pe_name_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(cbx_pe_status_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_6)
                    .addComponent(btn_pe_update_6)
                    .addComponent(btn_pe_delete_6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 6"));

        tbl_pe_6.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_6MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tbl_pe_6);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Level 6", jPanel28);

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 7"));

        jLabel40.setText("Physical Exam Code :");

        jLabel41.setText("Physical Exam Name :");

        jLabel42.setText("Status :");

        cbx_pe_status_7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_pe_add_7.setText("Add");
        btn_pe_add_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_add_7ActionPerformed(evt);
            }
        });

        btn_pe_update_7.setText("Update");
        btn_pe_update_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_update_7ActionPerformed(evt);
            }
        });

        btn_pe_delete_7.setText("Delete");
        btn_pe_delete_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pe_delete_7ActionPerformed(evt);
            }
        });

        jLabel43.setText("Level 6 :");

        cbx_parent_7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel40Layout.createSequentialGroup()
                            .addComponent(jLabel40)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pe_cd_7, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel40Layout.createSequentialGroup()
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel41)
                                .addComponent(jLabel42))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_pe_name_7, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(cbx_pe_status_7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel40Layout.createSequentialGroup()
                                    .addComponent(btn_pe_add_7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_update_7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_pe_delete_7))
                                .addComponent(cbx_parent_7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel43))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txt_pe_cd_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txt_pe_name_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_parent_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(cbx_pe_status_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pe_add_7)
                    .addComponent(btn_pe_update_7)
                    .addComponent(btn_pe_delete_7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder("Physical Exam Level 7"));

        tbl_pe_7.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Parent", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pe_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pe_7MouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tbl_pe_7);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(361, 361, 361))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Level 7", jPanel29);

        jScrollPane6.setViewportView(jTabbedPane5);

        jTabbedPane3.addTab("Physical Examination", jScrollPane6);

        jScrollPane2.setViewportView(jTabbedPane3);

        jTabbedPane1.addTab("Objective", jScrollPane2);
        jTabbedPane1.addTab("Assessment", jScrollPane3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedure Code Level 1"));

        jLabel2.setText("Procedure Code : ");

        jLabel3.setText("Procedure Name : ");

        jLabel4.setText("Status : ");

        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_procedure_add.setText("Add");
        btn_procedure_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_addActionPerformed(evt);
            }
        });

        btn_procedure_update.setText("Update");
        btn_procedure_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_updateActionPerformed(evt);
            }
        });

        btn_procedure_delete.setText("Delete");
        btn_procedure_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(procedure_cd)
                        .addComponent(procedure_name, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_procedure_add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(status, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_procedure_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_procedure_delete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(procedure_cd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(procedure_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_procedure_add)
                    .addComponent(btn_procedure_update)
                    .addComponent(btn_procedure_delete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedures"));

        tbl_procedure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Procedure Code", "Procedure Name", "Status"
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
        tbl_procedure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_procedureMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_procedure);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(454, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel1);

        jTabbedPane4.addTab("Level 1", jScrollPane7);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedure Code Level 2"));

        jLabel5.setText("Procedure Code : ");

        jLabel6.setText("Procedure Name : ");

        jLabel7.setText("Status :");

        status_1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_procedure_add_1.setText("Add");
        btn_procedure_add_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_add_1ActionPerformed(evt);
            }
        });

        btn_procedure_update_1.setText("Update");
        btn_procedure_update_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_update_1ActionPerformed(evt);
            }
        });

        btn_procedure_delete_1.setText("Delete");
        btn_procedure_delete_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_delete_1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Procedure Code 1 :");

        procedure_1_parent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_procedure_add_1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_procedure_update_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_procedure_delete_1))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(procedure_1_cd)
                        .addComponent(procedure_1_name, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addComponent(procedure_1_parent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(status_1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(procedure_1_cd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(procedure_1_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(procedure_1_parent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(status_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_procedure_add_1)
                    .addComponent(btn_procedure_update_1)
                    .addComponent(btn_procedure_delete_1))
                .addGap(12, 12, 12))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedures"));

        tbl_procedure_1.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Procedure Code", "Procedure Name", "Level 1", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_procedure_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_procedure_1MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbl_procedure_1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(450, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane8.setViewportView(jPanel4);

        jTabbedPane4.addTab("Level 2", jScrollPane8);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedure Code Level 3"));

        jLabel9.setText("Procedure Code : ");

        jLabel10.setText("Procedure Name : ");

        jLabel11.setText("Status :");

        status_2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btn_procedure_add_2.setText("Add");
        btn_procedure_add_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_add_2ActionPerformed(evt);
            }
        });

        btn_procedure_update_2.setText("Update");
        btn_procedure_update_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_update_2ActionPerformed(evt);
            }
        });

        btn_procedure_delete_2.setText("Delete");
        btn_procedure_delete_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procedure_delete_2ActionPerformed(evt);
            }
        });

        jLabel12.setText("Procedure Code 2 :");

        procedure_2_parent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(procedure_2_parent, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(procedure_2_cd)
                                .addComponent(procedure_2_name, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btn_procedure_add_2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_procedure_update_2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_procedure_delete_2))
                            .addComponent(status_2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(procedure_2_cd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(procedure_2_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(procedure_2_parent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(status_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_procedure_add_2)
                    .addComponent(btn_procedure_update_2)
                    .addComponent(btn_procedure_delete_2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedures"));

        tbl_procedure_2.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Procedure Code", "Procedure Name", "Level 2", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_procedure_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_procedure_2MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tbl_procedure_2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(450, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane9.setViewportView(jPanel7);

        jTabbedPane4.addTab("Level 3", jScrollPane9);

        jScrollPane5.setViewportView(jTabbedPane4);

        jTabbedPane2.addTab("Procedure", jScrollPane5);

        jScrollPane4.setViewportView(jTabbedPane2);

        jTabbedPane1.addTab("Plan", jScrollPane4);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("CIS Code Maintenance");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
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

    private void btn_procedure_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_addActionPerformed
        // TODO add your handling code here:
        String cd = procedure_cd.getText();
        String name = procedure_name.getText();
        String stat = (String) status.getSelectedItem();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        cIS_Procedure.setProcedure_name(name);
        cIS_Procedure.setStatus(stat);
        boolean addstat = DBConnection.addProcedure(1, cIS_Procedure);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_addActionPerformed

    private void tbl_procedureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_procedureMouseClicked
        // TODO add your handling code here:
        int index = tbl_procedure.getSelectedRow();
        procedure_cd.setText((String) tbl_procedure.getModel().getValueAt(index, 0));
        procedure_name.setText((String) tbl_procedure.getModel().getValueAt(index, 1));
        Func.cmbSelectInput(status, (String) tbl_procedure.getModel().getValueAt(index, 2));
        btn_procedure_add.setEnabled(false);
        btn_procedure_update.setEnabled(true);
        btn_procedure_delete.setEnabled(true);
        procedure_cd.setEditable(false);
    }//GEN-LAST:event_tbl_procedureMouseClicked

    private void btn_procedure_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_updateActionPerformed
        // TODO add your handling code here:
        String cd = procedure_cd.getText();
        String name = procedure_name.getText();
        String stat = (String) status.getSelectedItem();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        cIS_Procedure.setProcedure_name(name);
        cIS_Procedure.setStatus(stat);
        boolean updatestat = DBConnection.updateProcedure(1, cIS_Procedure);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_updateActionPerformed

    private void btn_procedure_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_deleteActionPerformed
        // TODO add your handling code here:
        String cd = procedure_cd.getText();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        boolean deletestat = DBConnection.deleteProcedure(1, cIS_Procedure);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_deleteActionPerformed

    private void btn_procedure_add_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_add_1ActionPerformed
        // TODO add your handling code here:
        String cd = procedure_1_cd.getText();
        String name = procedure_1_name.getText();
        String parent = ((String) procedure_1_parent.getSelectedItem()).split("\\|")[0];
        String stat = (String) status_1.getSelectedItem();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        cIS_Procedure.setProcedure_name(name);
        cIS_Procedure.setProcedure_parent(parent);
        cIS_Procedure.setStatus(stat);
        boolean addstat = DBConnection.addProcedure(2, cIS_Procedure);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_add_1ActionPerformed

    private void btn_procedure_update_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_update_1ActionPerformed
        // TODO add your handling code here:
        String cd = procedure_1_cd.getText();
        String name = procedure_1_name.getText();
        String parent = ((String) procedure_1_parent.getSelectedItem()).split("\\|")[0];
        String stat = (String) status_1.getSelectedItem();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        cIS_Procedure.setProcedure_name(name);
        cIS_Procedure.setProcedure_parent(parent);
        cIS_Procedure.setStatus(stat);
        boolean updatestat = DBConnection.updateProcedure(2, cIS_Procedure);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_update_1ActionPerformed

    private void btn_procedure_delete_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_delete_1ActionPerformed
        // TODO add your handling code here:
        String cd = procedure_1_cd.getText();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        boolean deletestat = DBConnection.deleteProcedure(2, cIS_Procedure);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_delete_1ActionPerformed

    private void tbl_procedure_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_procedure_1MouseClicked
        // TODO add your handling code here:
        int index = tbl_procedure_1.getSelectedRow();
        procedure_1_cd.setText((String) tbl_procedure_1.getModel().getValueAt(index, 0));
        procedure_1_name.setText((String) tbl_procedure_1.getModel().getValueAt(index, 1));
        Func.cmbSelectInput(procedure_1_parent, (String) tbl_procedure_1.getModel().getValueAt(index, 2));
        Func.cmbSelectInput(status_1, (String) tbl_procedure_1.getModel().getValueAt(index, 3));
        btn_procedure_add_1.setEnabled(false);
        btn_procedure_update_1.setEnabled(true);
        btn_procedure_delete_1.setEnabled(true);
        procedure_1_cd.setEditable(false);
    }//GEN-LAST:event_tbl_procedure_1MouseClicked

    private void btn_procedure_add_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_add_2ActionPerformed
        // TODO add your handling code here:
        String cd = procedure_2_cd.getText();
        String name = procedure_2_name.getText();
        String parent = ((String) procedure_2_parent.getSelectedItem()).split("\\|")[0];
        String stat = (String) status_2.getSelectedItem();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        cIS_Procedure.setProcedure_name(name);
        cIS_Procedure.setProcedure_parent(parent);
        cIS_Procedure.setStatus(stat);
        boolean addstat = DBConnection.addProcedure(3, cIS_Procedure);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_add_2ActionPerformed

    private void btn_procedure_update_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_update_2ActionPerformed
        // TODO add your handling code here:
        String cd = procedure_2_cd.getText();
        String name = procedure_2_name.getText();
        String parent = ((String) procedure_2_parent.getSelectedItem()).split("\\|")[0];
        String stat = (String) status_2.getSelectedItem();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        cIS_Procedure.setProcedure_name(name);
        cIS_Procedure.setProcedure_parent(parent);
        cIS_Procedure.setStatus(stat);
        boolean updatestat = DBConnection.updateProcedure(3, cIS_Procedure);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_update_2ActionPerformed

    private void btn_procedure_delete_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procedure_delete_2ActionPerformed
        // TODO add your handling code here:
        String cd = procedure_2_cd.getText();
        CIS_Procedure cIS_Procedure = new CIS_Procedure();
        cIS_Procedure.setProcedure_cd(cd);
        boolean deletestat = DBConnection.deleteProcedure(3, cIS_Procedure);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblProcedure();
        refreshComboBox();
    }//GEN-LAST:event_btn_procedure_delete_2ActionPerformed

    private void tbl_procedure_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_procedure_2MouseClicked
        // TODO add your handling code here:
        int index = tbl_procedure_2.getSelectedRow();
        procedure_2_cd.setText((String) tbl_procedure_2.getModel().getValueAt(index, 0));
        procedure_2_name.setText((String) tbl_procedure_2.getModel().getValueAt(index, 1));
        Func.cmbSelectInput(procedure_2_parent, (String) tbl_procedure_2.getModel().getValueAt(index, 2));
        Func.cmbSelectInput(status_2, (String) tbl_procedure_2.getModel().getValueAt(index, 3));
        btn_procedure_add_2.setEnabled(false);
        btn_procedure_update_2.setEnabled(true);
        btn_procedure_delete_2.setEnabled(true);
        procedure_2_cd.setEditable(false);
    }//GEN-LAST:event_tbl_procedure_2MouseClicked

    private void btn_pe_add_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_0ActionPerformed
        // TODO add your handling code here:
        String pe_cd_0 = txt_pe_cd_0.getText();
        String pe_name_0 = txt_pe_name_0.getText();
        String pe_status_0 = (String) cbx_pe_status_0.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_0);
        pe.setPe_name(pe_name_0);
        pe.setPe_status(pe_status_0);
        boolean addstat = DBConnection.addPhysicalExam(0, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_0ActionPerformed

    private void btn_pe_update_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_0ActionPerformed
        // TODO add your handling code here:
        String pe_cd_0 = txt_pe_cd_0.getText();
        String pe_name_0 = txt_pe_name_0.getText();
        String pe_status_0 = (String) cbx_pe_status_0.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_0);
        pe.setPe_name(pe_name_0);
        pe.setPe_status(pe_status_0);
        boolean updatestat = DBConnection.updatePhysicalExam(0, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_0ActionPerformed

    private void btn_pe_delete_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_0ActionPerformed
        // TODO add your handling code here:
        String pe_cd_0 = txt_pe_cd_0.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_0);
        boolean deletestat = DBConnection.deletePhysicalExam(0, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_0ActionPerformed

    private void tbl_pe_0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_0MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_0.getSelectedRow();
        String pe_cd_0 = (String) tbl_pe_0.getModel().getValueAt(index, 0);
        String pe_name_0 = (String) tbl_pe_0.getModel().getValueAt(index, 1);
        String pe_status_0 = (String) tbl_pe_0.getModel().getValueAt(index, 2);
        txt_pe_cd_0.setText(pe_cd_0);
        txt_pe_name_0.setText(pe_name_0);
        Func.cmbSelectInput(cbx_pe_status_0, pe_status_0);
        btn_pe_add_0.setEnabled(false);
        btn_pe_delete_0.setEnabled(true);
        btn_pe_update_0.setEnabled(true);
        txt_pe_cd_0.setEditable(false);
    }//GEN-LAST:event_tbl_pe_0MouseClicked

    private void btn_pe_add_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_1ActionPerformed
        // TODO add your handling code here:
        String pe_cd_1 = txt_pe_cd_1.getText();
        String pe_name_1 = txt_pe_name_1.getText();
        Object item = cbx_parent_1.getSelectedItem();
        String pe_cd_0 = ((ComboItem)item).getValue();
        String pe_status_1 = (String) cbx_pe_status_1.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        
        pe.setPe_cd(pe_cd_0+"^"+pe_cd_1);
        pe.setPe_name(pe_name_1);
        pe.setPe_parent(pe_cd_0);
        pe.setPe_status(pe_status_1);
        boolean addstat = DBConnection.addPhysicalExam(1, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_1ActionPerformed

    private void btn_pe_update_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_1ActionPerformed
        // TODO add your handling code here:
        String pe_cd_1 = txt_pe_cd_1.getText();
        String pe_name_1 = txt_pe_name_1.getText();
        Object item = cbx_parent_1.getSelectedItem();
        String pe_parent_1 = ((ComboItem)item).getValue();
        String pe_status_1 = (String) cbx_pe_status_1.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_1);
        pe.setPe_name(pe_name_1);
        pe.setPe_parent(pe_parent_1);
        pe.setPe_status(pe_status_1);
        boolean updatestat = DBConnection.updatePhysicalExam(1, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_1ActionPerformed

    private void btn_pe_delete_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_1ActionPerformed
        // TODO add your handling code here:
        String pe_cd_1 = txt_pe_cd_1.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_1);
        boolean deletestat = DBConnection.deletePhysicalExam(1, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_1ActionPerformed

    private void tbl_pe_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_1MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_1.getSelectedRow();
        String pe_cd_1 = (String) tbl_pe_1.getModel().getValueAt(index, 0);
        String pe_name_1 = (String) tbl_pe_1.getModel().getValueAt(index, 1);
        String pe_parent_1 = (String) tbl_pe_1.getModel().getValueAt(index, 2);
        String pe_status_1 = (String) tbl_pe_1.getModel().getValueAt(index, 3);
        txt_pe_cd_1.setText(pe_cd_1);
        txt_pe_name_1.setText(pe_name_1);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_1);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(0, pe_temp1);
        Func.cmbSelectInput(cbx_parent_1, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_1, pe_status_1);
        btn_pe_add_1.setEnabled(false);
        btn_pe_delete_1.setEnabled(true);
        btn_pe_update_1.setEnabled(true);
        txt_pe_cd_1.setEditable(false);
    }//GEN-LAST:event_tbl_pe_1MouseClicked

    private void btn_pe_add_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_2ActionPerformed
        // TODO add your handling code here:
        String pe_cd_2 = txt_pe_cd_2.getText();
        String pe_name_2 = txt_pe_name_2.getText();
        Object item = cbx_parent_2.getSelectedItem();
        String pe_cd_1 = ((ComboItem)item).getValue();
        String pe_status_2 = (String) cbx_pe_status_2.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        
        pe.setPe_cd(pe_cd_1+"^"+pe_cd_2);
        pe.setPe_name(pe_name_2);
        pe.setPe_parent(pe_cd_1);
        pe.setPe_status(pe_status_2);
        boolean addstat = DBConnection.addPhysicalExam(2, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_2ActionPerformed

    private void btn_pe_update_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_2ActionPerformed
        // TODO add your handling code here:
        String pe_cd_2 = txt_pe_cd_2.getText();
        String pe_name_2 = txt_pe_name_2.getText();
        Object item = cbx_parent_2.getSelectedItem();
        String pe_parent_2 = ((ComboItem)item).getValue();
        String pe_status_2 = (String) cbx_pe_status_2.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_2);
        pe.setPe_name(pe_name_2);
        pe.setPe_parent(pe_parent_2);
        pe.setPe_status(pe_status_2);
        boolean updatestat = DBConnection.updatePhysicalExam(2, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_2ActionPerformed

    private void btn_pe_delete_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_2ActionPerformed
        // TODO add your handling code here:
        String pe_cd_2 = txt_pe_cd_2.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_2);
        boolean deletestat = DBConnection.deletePhysicalExam(2, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_2ActionPerformed

    private void tbl_pe_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_2MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_2.getSelectedRow();
        String pe_cd_2 = (String) tbl_pe_2.getModel().getValueAt(index, 0);
        String pe_name_2 = (String) tbl_pe_2.getModel().getValueAt(index, 1);
        String pe_parent_2 = (String) tbl_pe_2.getModel().getValueAt(index, 2);
        String pe_status_2 = (String) tbl_pe_2.getModel().getValueAt(index, 3);
        txt_pe_cd_2.setText(pe_cd_2);
        txt_pe_name_2.setText(pe_name_2);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_2);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(1, pe_temp1);
        Func.cmbSelectInput(cbx_parent_2, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_2, pe_status_2);
        btn_pe_add_2.setEnabled(false);
        btn_pe_delete_2.setEnabled(true);
        btn_pe_update_2.setEnabled(true);
        txt_pe_cd_2.setEditable(false);
    }//GEN-LAST:event_tbl_pe_2MouseClicked

    private void btn_pe_add_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_3ActionPerformed
        // TODO add your handling code here:
        String pe_cd_3 = txt_pe_cd_3.getText();
        String pe_name_3 = txt_pe_name_3.getText();
        Object item = cbx_parent_3.getSelectedItem();
        String pe_cd_2 = ((ComboItem)item).getValue();
        String pe_status_3 = (String) cbx_pe_status_3.getSelectedItem();
        
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_2+"^"+pe_cd_3);
        pe.setPe_name(pe_name_3);
        pe.setPe_parent(pe_cd_2);
        pe.setPe_status(pe_status_3);
        boolean addstat = DBConnection.addPhysicalExam(3, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_3ActionPerformed

    private void btn_pe_update_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_3ActionPerformed
        // TODO add your handling code here:
        String pe_cd_3 = txt_pe_cd_3.getText();
        String pe_name_3 = txt_pe_name_3.getText();
        Object item = cbx_parent_3.getSelectedItem();
        String pe_parent_3 = ((ComboItem)item).getValue();
        String pe_status_3 = (String) cbx_pe_status_3.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_3);
        pe.setPe_name(pe_name_3);
        pe.setPe_parent(pe_parent_3);
        pe.setPe_status(pe_status_3);
        boolean updatestat = DBConnection.updatePhysicalExam(3, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_3ActionPerformed

    private void btn_pe_delete_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_3ActionPerformed
        // TODO add your handling code here:
        String pe_cd_3 = txt_pe_cd_3.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_3);
        boolean deletestat = DBConnection.deletePhysicalExam(3, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_3ActionPerformed

    private void tbl_pe_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_3MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_3.getSelectedRow();
        String pe_cd_3 = (String) tbl_pe_3.getModel().getValueAt(index, 0);
        String pe_name_3 = (String) tbl_pe_3.getModel().getValueAt(index, 1);
        String pe_parent_3 = (String) tbl_pe_3.getModel().getValueAt(index, 2);
        String pe_status_3 = (String) tbl_pe_3.getModel().getValueAt(index, 3);
        txt_pe_cd_3.setText(pe_cd_3);
        txt_pe_name_3.setText(pe_name_3);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_3);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(2, pe_temp1);
        Func.cmbSelectInput(cbx_parent_3, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_3, pe_status_3);
        btn_pe_add_3.setEnabled(false);
        btn_pe_delete_3.setEnabled(true);
        btn_pe_update_3.setEnabled(true);
        txt_pe_cd_3.setEditable(false);
    }//GEN-LAST:event_tbl_pe_3MouseClicked

    private void btn_pe_add_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_4ActionPerformed
        // TODO add your handling code here:
        String pe_cd_4 = txt_pe_cd_4.getText();
        String pe_name_4 = txt_pe_name_4.getText();
        Object item = cbx_parent_4.getSelectedItem();
        String pe_cd_3 = ((ComboItem)item).getValue();
        String pe_status_4 = (String) cbx_pe_status_4.getSelectedItem();
        
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_3+"^"+pe_cd_4);
        pe.setPe_name(pe_name_4);
        pe.setPe_parent(pe_cd_3);
        pe.setPe_status(pe_status_4);
        boolean addstat = DBConnection.addPhysicalExam(4, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_4ActionPerformed

    private void btn_pe_update_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_4ActionPerformed
        // TODO add your handling code here:
        String pe_cd_4 = txt_pe_cd_4.getText();
        String pe_name_4 = txt_pe_name_4.getText();
        Object item = cbx_parent_4.getSelectedItem();
        String pe_parent_4 = ((ComboItem)item).getValue();
        String pe_status_4 = (String) cbx_pe_status_4.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_4);
        pe.setPe_name(pe_name_4);
        pe.setPe_parent(pe_parent_4);
        pe.setPe_status(pe_status_4);
        boolean updatestat = DBConnection.updatePhysicalExam(4, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_4ActionPerformed

    private void btn_pe_delete_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_4ActionPerformed
        // TODO add your handling code here:
        String pe_cd_4 = txt_pe_cd_4.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_4);
        boolean deletestat = DBConnection.deletePhysicalExam(4, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_4ActionPerformed

    private void tbl_pe_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_4MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_4.getSelectedRow();
        String pe_cd_4 = (String) tbl_pe_4.getModel().getValueAt(index, 0);
        String pe_name_4 = (String) tbl_pe_4.getModel().getValueAt(index, 1);
        String pe_parent_4 = (String) tbl_pe_4.getModel().getValueAt(index, 2);
        String pe_status_4 = (String) tbl_pe_4.getModel().getValueAt(index, 3);
        txt_pe_cd_4.setText(pe_cd_4);
        txt_pe_name_4.setText(pe_name_4);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_4);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(3, pe_temp1);
        Func.cmbSelectInput(cbx_parent_4, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_4, pe_status_4);
        btn_pe_add_4.setEnabled(false);
        btn_pe_delete_4.setEnabled(true);
        btn_pe_update_4.setEnabled(true);
        txt_pe_cd_4.setEditable(false);
    }//GEN-LAST:event_tbl_pe_4MouseClicked

    private void btn_pe_add_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_5ActionPerformed
        // TODO add your handling code here:
        String pe_cd_5 = txt_pe_cd_5.getText();
        String pe_name_5 = txt_pe_name_5.getText();
        Object item = cbx_parent_5.getSelectedItem();
        String pe_cd_4 = ((ComboItem)item).getValue();
        String pe_status_5 = (String) cbx_pe_status_5.getSelectedItem();
        
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_4+"^"+pe_cd_5);
        pe.setPe_name(pe_name_5);
        pe.setPe_parent(pe_cd_4);
        pe.setPe_status(pe_status_5);
        boolean addstat = DBConnection.addPhysicalExam(5, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_5ActionPerformed

    private void btn_pe_update_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_5ActionPerformed
        // TODO add your handling code here:
        String pe_cd_5 = txt_pe_cd_5.getText();
        String pe_name_5 = txt_pe_name_5.getText();
        Object item = cbx_parent_5.getSelectedItem();
        String pe_parent_5 = ((ComboItem)item).getValue();
        String pe_status_5 = (String) cbx_pe_status_5.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_5);
        pe.setPe_name(pe_name_5);
        pe.setPe_parent(pe_parent_5);
        pe.setPe_status(pe_status_5);
        boolean updatestat = DBConnection.updatePhysicalExam(5, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_5ActionPerformed

    private void btn_pe_delete_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_5ActionPerformed
        // TODO add your handling code here:
        String pe_cd_5 = txt_pe_cd_5.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_5);
        boolean deletestat = DBConnection.deletePhysicalExam(5, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_5ActionPerformed

    private void tbl_pe_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_5MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_5.getSelectedRow();
        String pe_cd_5 = (String) tbl_pe_5.getModel().getValueAt(index, 0);
        String pe_name_5 = (String) tbl_pe_5.getModel().getValueAt(index, 1);
        String pe_parent_5 = (String) tbl_pe_5.getModel().getValueAt(index, 2);
        String pe_status_5 = (String) tbl_pe_5.getModel().getValueAt(index, 3);
        txt_pe_cd_5.setText(pe_cd_5);
        txt_pe_name_5.setText(pe_name_5);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_5);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(4, pe_temp1);
        Func.cmbSelectInput(cbx_parent_5, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_5, pe_status_5);
        btn_pe_add_5.setEnabled(false);
        btn_pe_delete_5.setEnabled(true);
        btn_pe_update_5.setEnabled(true);
        txt_pe_cd_5.setEditable(false);
    }//GEN-LAST:event_tbl_pe_5MouseClicked

    private void btn_pe_add_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_6ActionPerformed
        // TODO add your handling code here:
        String pe_cd_6 = txt_pe_cd_6.getText();
        String pe_name_6 = txt_pe_name_6.getText();
        Object item = cbx_parent_6.getSelectedItem();
        String pe_cd_5 = ((ComboItem)item).getValue();
        String pe_status_6 = (String) cbx_pe_status_6.getSelectedItem();
        
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_5+"^"+pe_cd_6);
        pe.setPe_name(pe_name_6);
        pe.setPe_parent(pe_cd_5);
        pe.setPe_status(pe_status_6);
        boolean addstat = DBConnection.addPhysicalExam(6, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_6ActionPerformed

    private void btn_pe_update_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_6ActionPerformed
        // TODO add your handling code here:
        String pe_cd_6 = txt_pe_cd_6.getText();
        String pe_name_6 = txt_pe_name_6.getText();
        Object item = cbx_parent_6.getSelectedItem();
        String pe_parent_6 = ((ComboItem)item).getValue();
        String pe_status_6 = (String) cbx_pe_status_6.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_6);
        pe.setPe_name(pe_name_6);
        pe.setPe_parent(pe_parent_6);
        pe.setPe_status(pe_status_6);
        boolean updatestat = DBConnection.updatePhysicalExam(6, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_6ActionPerformed

    private void btn_pe_delete_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_6ActionPerformed
        // TODO add your handling code here:
        String pe_cd_6 = txt_pe_cd_6.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_6);
        boolean deletestat = DBConnection.deletePhysicalExam(6, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_6ActionPerformed

    private void tbl_pe_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_6MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_6.getSelectedRow();
        String pe_cd_6 = (String) tbl_pe_6.getModel().getValueAt(index, 0);
        String pe_name_6 = (String) tbl_pe_6.getModel().getValueAt(index, 1);
        String pe_parent_6 = (String) tbl_pe_6.getModel().getValueAt(index, 2);
        String pe_status_6 = (String) tbl_pe_6.getModel().getValueAt(index, 3);
        txt_pe_cd_6.setText(pe_cd_6);
        txt_pe_name_6.setText(pe_name_6);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_6);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(5, pe_temp1);
        Func.cmbSelectInput(cbx_parent_6, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_6, pe_status_6);
        btn_pe_add_6.setEnabled(false);
        btn_pe_delete_6.setEnabled(true);
        btn_pe_update_6.setEnabled(true);
        txt_pe_cd_6.setEditable(false);
    }//GEN-LAST:event_tbl_pe_6MouseClicked

    private void btn_pe_add_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_add_7ActionPerformed
        // TODO add your handling code here:
        String pe_cd_7 = txt_pe_cd_7.getText();
        String pe_name_7 = txt_pe_name_7.getText();
        Object item = cbx_parent_7.getSelectedItem();
        String pe_cd_6 = ((ComboItem)item).getValue();
        String pe_status_7 = (String) cbx_pe_status_7.getSelectedItem();
        
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_6+"^"+pe_cd_7);
        pe.setPe_name(pe_name_7);
        pe.setPe_parent(pe_cd_6);
        pe.setPe_status(pe_status_7);
        boolean addstat = DBConnection.addPhysicalExam(7, pe);
        if (addstat) {
            J.o("Success", "Add Success ..", 1);
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_add_7ActionPerformed

    private void btn_pe_update_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_update_7ActionPerformed
        // TODO add your handling code here:
        String pe_cd_7 = txt_pe_cd_7.getText();
        String pe_name_7 = txt_pe_name_7.getText();
        Object item = cbx_parent_7.getSelectedItem();
        String pe_parent_7 = ((ComboItem)item).getValue();
        String pe_status_7 = (String) cbx_pe_status_7.getSelectedItem();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_7);
        pe.setPe_name(pe_name_7);
        pe.setPe_parent(pe_parent_7);
        pe.setPe_status(pe_status_7);
        boolean updatestat = DBConnection.updatePhysicalExam(7, pe);
        if (updatestat) {
            J.o("Success", "Update Success ..", 1);
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_update_7ActionPerformed

    private void btn_pe_delete_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pe_delete_7ActionPerformed
        // TODO add your handling code here:
        String pe_cd_7 = txt_pe_cd_7.getText();
        PhysicalExamBean pe = new PhysicalExamBean();
        pe.setPe_cd(pe_cd_7);
        boolean deletestat = DBConnection.deletePhysicalExam(7, pe);
        if (deletestat) {
            J.o("Success", "Delete Success ..", 1);
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
        defaultFunction();
        clearField();
        refreshTblPhysicalExam();
        refreshComboBox();
    }//GEN-LAST:event_btn_pe_delete_7ActionPerformed

    private void tbl_pe_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pe_7MouseClicked
        // TODO add your handling code here:
        int index = tbl_pe_7.getSelectedRow();
        String pe_cd_7 = (String) tbl_pe_7.getModel().getValueAt(index, 0);
        String pe_name_7 = (String) tbl_pe_7.getModel().getValueAt(index, 1);
        String pe_parent_7 = (String) tbl_pe_7.getModel().getValueAt(index, 2);
        String pe_status_7 = (String) tbl_pe_7.getModel().getValueAt(index, 3);
        txt_pe_cd_7.setText(pe_cd_7);
        txt_pe_name_7.setText(pe_name_7);
        PhysicalExamBean pe_temp1 = new PhysicalExamBean();
        pe_temp1.setPe_cd(pe_parent_7);
        PhysicalExamBean pe_temp2 = DBConnection.getPhysicalExam(6, pe_temp1);
        Func.cmbSelectInput(cbx_parent_7, pe_temp2.getPe_name());
        Func.cmbSelectInput(cbx_pe_status_7, pe_status_7);
        btn_pe_add_7.setEnabled(false);
        btn_pe_delete_7.setEnabled(true);
        btn_pe_update_7.setEnabled(true);
        txt_pe_cd_7.setEditable(false);
    }//GEN-LAST:event_tbl_pe_7MouseClicked

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
            java.util.logging.Logger.getLogger(CIS_Code_Maintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CIS_Code_Maintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CIS_Code_Maintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CIS_Code_Maintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CIS_Code_Maintenance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JButton btn_pe_add_0;
    protected static javax.swing.JButton btn_pe_add_1;
    protected static javax.swing.JButton btn_pe_add_2;
    protected static javax.swing.JButton btn_pe_add_3;
    protected static javax.swing.JButton btn_pe_add_4;
    protected static javax.swing.JButton btn_pe_add_5;
    protected static javax.swing.JButton btn_pe_add_6;
    protected static javax.swing.JButton btn_pe_add_7;
    protected static javax.swing.JButton btn_pe_delete_0;
    protected static javax.swing.JButton btn_pe_delete_1;
    protected static javax.swing.JButton btn_pe_delete_2;
    protected static javax.swing.JButton btn_pe_delete_3;
    protected static javax.swing.JButton btn_pe_delete_4;
    protected static javax.swing.JButton btn_pe_delete_5;
    protected static javax.swing.JButton btn_pe_delete_6;
    protected static javax.swing.JButton btn_pe_delete_7;
    protected static javax.swing.JButton btn_pe_update_0;
    protected static javax.swing.JButton btn_pe_update_1;
    protected static javax.swing.JButton btn_pe_update_2;
    protected static javax.swing.JButton btn_pe_update_3;
    protected static javax.swing.JButton btn_pe_update_4;
    protected static javax.swing.JButton btn_pe_update_5;
    protected static javax.swing.JButton btn_pe_update_6;
    protected static javax.swing.JButton btn_pe_update_7;
    private javax.swing.JButton btn_procedure_add;
    public static javax.swing.JButton btn_procedure_add_1;
    public static javax.swing.JButton btn_procedure_add_2;
    private javax.swing.JButton btn_procedure_delete;
    public static javax.swing.JButton btn_procedure_delete_1;
    public static javax.swing.JButton btn_procedure_delete_2;
    private javax.swing.JButton btn_procedure_update;
    public static javax.swing.JButton btn_procedure_update_1;
    public static javax.swing.JButton btn_procedure_update_2;
    protected static javax.swing.JComboBox cbx_parent_1;
    protected static javax.swing.JComboBox cbx_parent_2;
    protected static javax.swing.JComboBox cbx_parent_3;
    protected static javax.swing.JComboBox cbx_parent_4;
    protected static javax.swing.JComboBox cbx_parent_5;
    protected static javax.swing.JComboBox cbx_parent_6;
    protected static javax.swing.JComboBox cbx_parent_7;
    protected static javax.swing.JComboBox cbx_pe_status_0;
    protected static javax.swing.JComboBox cbx_pe_status_1;
    protected static javax.swing.JComboBox cbx_pe_status_2;
    protected static javax.swing.JComboBox cbx_pe_status_3;
    protected static javax.swing.JComboBox cbx_pe_status_4;
    protected static javax.swing.JComboBox cbx_pe_status_5;
    protected static javax.swing.JComboBox cbx_pe_status_6;
    protected static javax.swing.JComboBox cbx_pe_status_7;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private java.awt.Label label1;
    public static javax.swing.JTextField procedure_1_cd;
    public static javax.swing.JTextField procedure_1_name;
    public static javax.swing.JComboBox procedure_1_parent;
    public static javax.swing.JTextField procedure_2_cd;
    public static javax.swing.JTextField procedure_2_name;
    public static javax.swing.JComboBox procedure_2_parent;
    public static javax.swing.JTextField procedure_cd;
    public static javax.swing.JTextField procedure_name;
    public static javax.swing.JComboBox status;
    public static javax.swing.JComboBox status_1;
    public static javax.swing.JComboBox status_2;
    protected static javax.swing.JTable tbl_pe_0;
    protected static javax.swing.JTable tbl_pe_1;
    protected static javax.swing.JTable tbl_pe_2;
    protected static javax.swing.JTable tbl_pe_3;
    protected static javax.swing.JTable tbl_pe_4;
    protected static javax.swing.JTable tbl_pe_5;
    protected static javax.swing.JTable tbl_pe_6;
    protected static javax.swing.JTable tbl_pe_7;
    public static javax.swing.JTable tbl_procedure;
    public static javax.swing.JTable tbl_procedure_1;
    public static javax.swing.JTable tbl_procedure_2;
    protected static javax.swing.JTextField txt_pe_cd_0;
    protected static javax.swing.JTextField txt_pe_cd_1;
    protected static javax.swing.JTextField txt_pe_cd_2;
    protected static javax.swing.JTextField txt_pe_cd_3;
    protected static javax.swing.JTextField txt_pe_cd_4;
    protected static javax.swing.JTextField txt_pe_cd_5;
    protected static javax.swing.JTextField txt_pe_cd_6;
    protected static javax.swing.JTextField txt_pe_cd_7;
    protected static javax.swing.JTextField txt_pe_name_0;
    protected static javax.swing.JTextField txt_pe_name_1;
    protected static javax.swing.JTextField txt_pe_name_2;
    protected static javax.swing.JTextField txt_pe_name_3;
    protected static javax.swing.JTextField txt_pe_name_4;
    protected static javax.swing.JTextField txt_pe_name_5;
    protected static javax.swing.JTextField txt_pe_name_6;
    protected static javax.swing.JTextField txt_pe_name_7;
    // End of variables declaration//GEN-END:variables
}
