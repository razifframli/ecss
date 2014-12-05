/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.rmi.server;

import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public interface Message extends Remote {
    
    // update pms
    boolean isUpdatePatientBiodata(String[] Biodata) throws RemoteException;
            
    // reset password
    String getPassword(String icno, String userid) throws RemoteException;
    
    // change password
    boolean changePassword(String userid, String pwd) throws RemoteException;
    
    // update pms_patient_biodata
    boolean updatePmsPatientBiodata(String pmino, ArrayList<String> column, ArrayList<String> data) throws RemoteException;
    
    // referral tab
    ArrayList<ArrayList<String>> getDoctors(String user_id) throws RemoteException;
    ArrayList<String> getDoctor(String user_name) throws RemoteException;
    boolean addReferral(ArrayList<String> data) throws RemoteException;
    ArrayList<ArrayList<String>> getReferral(String user_id) throws RemoteException;
    boolean delReferral(String pmino, String episodetime) throws RemoteException;
    
    //calling system rmi
    void setCall(String nama) throws RemoteException;
    void destroyCall(String nama) throws RemoteException;
    
    //consultation queue
    boolean isConsult(String pmino) throws RemoteException;
    String[] simplifyCheckBiodata(String pmiNo) throws RemoteException;
    
    //Pharmacy order drug
    void addAUTOGENERATE_ONO(String oNo) throws RemoteException;
    void addPIS_ORDER_MASTER(String oNo, String pmiNo, String hfc, Timestamp ec, 
            Timestamp ed, Timestamp oDate, String id, String oF, String oTo, 
            int spubNo, int toi, boolean oSM) throws RemoteException;
    void addPIS_ORDER_DETAIL(String oNo, String dmdc, String dtraden, 
            String dLfreq, String droute, String ddosage, String dstrength,
            String dLqty, String dLadvisory, int duration1, String orderStatus,
            double qtyPerTime1, int totalQty, String sOUM, int qtydispensed1,
            String dOUM, boolean oSD) throws RemoteException;
    ResultSet getPrescriptionNote(String pmiNo) throws RemoteException;
    ResultSet getPrescriptionNote2(String oNo) throws RemoteException;
    ResultSet getAUTOGENERATE_ONO() throws RemoteException;
    
    //search MC
    String getPMINo(String search, String idtype, int type) throws RemoteException;
    
    //code report
    ArrayList<ArrayList<String>> getReport(int type, String date1, String date2) throws RemoteException;
    ArrayList<ArrayList<String>> getReportICD10(String date) throws RemoteException;
    String getICD10SortReport(String date) throws RemoteException;
    
    //code maintain administrator
    ArrayList<ArrayList<String>> getListOfStaffs(String user_id) throws RemoteException;
    ArrayList<ArrayList<String>> getStaffs(String user_id) throws RemoteException;
    boolean addStaff(String data1[], String data2[]) throws RemoteException;
    boolean deleteStaff(String user_id) throws RemoteException;
    boolean isStaffs(String user_id) throws RemoteException;
    boolean updateStaff(String user_id, String cols1[], String data1[], String cols2[], String data2[]) throws RemoteException;
    ArrayList<String> getStaffLogin(String user_id, String password) throws RemoteException;
    
    ArrayList<ArrayList<String>> getQuery(String strSQL, int col) throws RemoteException;
    
    //get new pmi no
    String getPMI(String ic) throws RemoteException;
    
    String[] getBiodata(String pmiNo) throws RemoteException;
    
    //dispense code
    ArrayList<String> getDispenseMaster(String orderNo) throws RemoteException;
    boolean insertDispenseMaster(String [] data1, java.sql.Timestamp data2, boolean data3) throws RemoteException;
    ArrayList<String> getOrderDetail(String orderNo, String drugCode) throws RemoteException;
    boolean insertDispenseDetail(String[] data1, int data2, boolean data3) throws RemoteException;
    boolean updateOrderDetail(int qtyDispensed, String orderNo, String drugCode) throws RemoteException;
    boolean isOrderDetail(String orderNo) throws RemoteException;
    boolean updateOrderMaster(String orderNo, int status) throws RemoteException;
    boolean updateDispensedMaster(String orderNo, int status) throws RemoteException;
    
    void insertD(String [] dispense) throws RemoteException;
    void insertOrder(String [] order)throws RemoteException;
    
    void updateStatEpisode(String PMINumber, String TimeEpisode, String status, String doctor) 
            throws RemoteException;
    void updateStatEpisode2(String PMINumber, String TimeEpisode, String now) 
            throws RemoteException;
    
    Vector getQueueNameList(String name, String hfcCode) throws RemoteException;
    
    String [] getAutoGen(int stat) throws RemoteException;
    
    String [] getBio(int stat, String ic, String type, int num_col) throws RemoteException;
    
    ArrayList<String> getEHRRecords(String pmiNo) throws RemoteException;

    void sayHello(String name) throws RemoteException;
    
    String insertEHRCentral(int status, String pmi, String data) throws RemoteException;
    
    List getEHRLatest7(String pmi)  throws RemoteException;
    
    String insertPMS(String _Hl7mgs) throws RemoteException;
    
    List getPMS(String IC) throws RemoteException;
    
    List getPMSByOldIC(String oldIC) throws RemoteException;
    
    List getPMSByID(String ID, String type) throws RemoteException;
    
    List getPMSByPMINo(String PMI) throws RemoteException;

    String insertDTO(String PMI, String dataDTO) throws RemoteException;
    
    ArrayList<ArrayList<String>> getOrderMasterAll(int stat, String pmi_no, String order_no) throws RemoteException;
    
    void insertPatientBiodata(String [] biodata) throws RemoteException;
    
    void insertRegCreateQ(String [] queue) throws RemoteException;

    ArrayList<String> getPatientBiodata(String selectedPmiNo) throws RemoteException;

    ArrayList<String> getPisOrderMaster(String selectedPmiNo, String orderDate) throws RemoteException;

    ArrayList<ArrayList<String>> getDrugOrderDetail(String text) throws RemoteException;
    
    boolean deleteAppointment(String appID) throws RemoteException;
    
    Vector<String> getpatientInfoPMI(String PMI) throws RemoteException;
    
    Vector<String> getpatientInfoIC(String IC) throws RemoteException;
    
    boolean makeAppointment(Vector<String> patient) throws RemoteException;
    
    boolean updateAppointment(Vector<String> patient) throws RemoteException;
    
    TableModel getDispensedDrug(String strSql, String prepStatement[]) throws RemoteException; //get list of dispensed drug -- Hariz 20141203
}
