/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentGUI;

//import conndb.connection;
import Helper.Session;
import java.awt.Paint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author WC
 */
public class patientAppointment {
    private Vector<String> details;
    
    public Vector<String> getAppointment(String ID) throws ClassNotFoundException, SQLException{
            details = new Vector<String>();
            
            //Connection hconn = connection.HSQLconnect();
            String sql="SELECT AL.*, PB.NEW_IC_NO FROM PMS_APPOINTMENT_LIST AL,PMS_PATIENT_BIODATA PB  where APPOINTMENT_ID = ? and AL.PMI_NO = PB.PMI_NO";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                for(int i=0;i<11;i++) {
                    details.add(rs.getString(i+1));
                }
            
            }
            //hconn.close();
        return details;
    }
    
    
        public String getAutoAppointID() throws ClassNotFoundException, SQLException {

        String AutogenerateID = new String();
//        Connection hconn = connection.HSQLconnect();
        String sql = "SELECT APPOINTMENT_ID FROM PMS_APPOINTMENT_LIST";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        String id = null;


        while(rs.next()){
            //for(int i=0;i<1;i++)
             {
                 id = rs.getString(1);
             }

             int num = Integer.parseInt(id.substring(1,id.length()));
             num += 1;

             String formatted = String.format("%05d",num);
             AutogenerateID = formatted;
        }

        //hconn.close();
        return AutogenerateID;

    }
        
        public boolean deleteAppointment(String appID) throws ClassNotFoundException, SQLException{
            boolean status=false;
        
//            Connection hconn = connection.HSQLconnect();
            String sql = "Delete From PMS_APPOINTMENT_LIST where APPOINTMENT_ID = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, appID);
            int s =ps.executeUpdate();
            if(s>0){
                status = true;                
            }else{
                status=false;       
            }
        
            //hconn.close();
        
            return status;
        
        
        }
    
        
        public boolean makeAppointment(Vector<String> patient) throws ClassNotFoundException, SQLException{
            boolean makeStat=false;
//            Connection Hconn = connection.HSQLconnect();
            String sql = "INSERT INTO PMS_APPOINTMENT_LIST(APPOINTMENT_ID,APPOINTMENT_DATE,APPOINTMENT_TIME,PMI_NO,PATIENT_NAME,"
                    + "DOCTOR_NAME,LOCATION_NAME,DISCIPLINE,SUBDISCIPLINE,APPOINTMENT_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, patient.elementAt(0));
            ps.setString(2, patient.elementAt(1));
            ps.setString(3,patient.elementAt(2));
            ps.setString(4, patient.elementAt(3));
            ps.setString(5, patient.elementAt(4));
            ps.setString(6, patient.elementAt(5));
            ps.setString(7, patient.elementAt(6));
            ps.setString(8,patient.elementAt(7));
            ps.setString(9, patient.elementAt(8));
            ps.setString(10,patient.elementAt(9));
            int m = ps.executeUpdate();

            if(m>0){
               makeStat = true;                
            }else{
               makeStat=false;       
            }
            //Hconn.close();
         return makeStat;
        }
        
        public boolean updateAppointment(Vector<String> patient) throws ClassNotFoundException, SQLException{
            boolean updatestat=false;
            //Connection Hconn = connection.HSQLconnect();
            String sql = "Update PMS_APPOINTMENT_LIST set APPOINTMENT_DATE = ?,APPOINTMENT_TIME = ?,PMI_NO=?,PATIENT_NAME=?,"
                    + "DOCTOR_NAME=?,LOCATION_NAME=?,DISCIPLINE=?,SUBDISCIPLINE=?,APPOINTMENT_TYPE=? where APPOINTMENT_ID = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(10, patient.elementAt(0));
            ps.setString(1, patient.elementAt(1));
            ps.setString(2,patient.elementAt(2));
            ps.setString(3, patient.elementAt(3));
            ps.setString(4, patient.elementAt(4));
            ps.setString(5, patient.elementAt(5));
            ps.setString(6, patient.elementAt(6));
            ps.setString(7,patient.elementAt(7));
            ps.setString(8, patient.elementAt(8));
            ps.setString(9,patient.elementAt(9));
            int m = ps.executeUpdate();

            if(m>0){
               updatestat = true;                
            }else{
               updatestat=false;       
            }
        
            //Hconn.close();
            return updatestat;
        }
    
        
   public Vector<String> getpatientInfoPMI(String PMI) throws ClassNotFoundException, SQLException{
         Vector<String> patinfo = new Vector<String>();
         
        // Connection Hconn = connection.HSQLconnect();
         String sql= "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO FROM PMS_PATIENT_BIODATA WHERE PMI_NO=Upper(?)";
         PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
         ps.setString(1, PMI);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             patinfo.add(rs.getString(1));
             patinfo.add(rs.getString(2));
             patinfo.add(rs.getString(3));
         }
        
      // Hconn.close();
       return patinfo;      
   }
       
   
     public Vector<String> getpatientInfoIC(String IC) throws ClassNotFoundException, SQLException{
         Vector<String> patinfobyic = new Vector<String>();
         
         //Connection Hconn = connection.HSQLconnect();
         String sql= "SELECT PMI_NO,PATIENT_NAME,NEW_IC_NO FROM PMS_PATIENT_BIODATA WHERE NEW_IC_NO=? or OLD_IC_NO = ?";
         PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
         ps.setString(1,IC);
         ps.setString(2,IC);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             patinfobyic.add(rs.getString(1));
             patinfobyic.add(rs.getString(2));
             patinfobyic.add(rs.getString(3));
         }
        
       //Hconn.close();
       return patinfobyic;      
   }
       
       
        
}
