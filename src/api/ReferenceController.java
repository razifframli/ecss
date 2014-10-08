
package api;

import Helper.Session;
import DBConnection.DBConnection;
//import conndb.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author WC
 */
public class ReferenceController {
    
    //get Master table data
    public Vector getMasterlookup() throws SQLException, ClassNotFoundException{
    
        Vector<Vector<String>> MasterLookup= new Vector<Vector<String>>();
        Vector<String> MasterDET= new Vector<String>();

        //Connection conn = connection.HSQLconnect();
        String sql="SELECT * From LOOKUP_MASTER";
        //PreparedStatement ps1 = Session.getCon_x(1000).prepareStatement(sql1);
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
                    MasterDET=new Vector();
                    MasterDET.add(rs.getString(1));
                    MasterDET.add(rs.getString(2));
                    MasterLookup.add(MasterDET);
                }

        //conn.close();
        return MasterLookup;
    }
    
    
    //insert new Master references
    public boolean insertMasterLookup(String Code, String Desc) throws ClassNotFoundException, SQLException{
        boolean Status = false;
        
        //Connection conn = connection.HSQLconnect();
        String sql="Insert INTO  LOOKUP_MASTER (MASTER_REF_CODE, DESCRIPTION) Values (?,?)";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, Code);
        ps.setString(2, Desc);
        int m = ps.executeUpdate();

            if(m>0){
               Status = true;                
            }else{
               Status=false;       
            }
            
            
            //conn.close();

        return Status;
    }
    
    
    //update Master References
    public boolean updateMaaster(String Mcode,String Mdesc) throws ClassNotFoundException, SQLException{
        boolean upstat=false;
        //Connection conn = connection.HSQLconnect();
        String sql="UPDATE LOOKUP_MASTER SET DESCRIPTION = ? WHERE MASTER_REF_CODE = ?";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, Mdesc);
        ps.setString(2, Mcode);
         int s = ps.executeUpdate();

            if(s>0){
               upstat = true;                
            }else{
               upstat=false;       
            }
        
            //conn.close();
    
        return upstat;
    }
    
    //get Detail table data
    public Vector getDetailLookup(String mastCode) throws ClassNotFoundException, SQLException{
    
        Vector<Vector<String>> DetailLookup= new Vector<Vector<String>>();
        Vector<String> DET= new Vector<String>();
        //Connection conn = connection.HSQLconnect();
        String sql="SELECT * From LOOKUP_DETAIL where MASTER_REF_CODE=? ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, mastCode);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
                    DET=new Vector();
                    DET.add(rs.getString(1));
                    DET.add(rs.getString(2));
                    DET.add(rs.getString(3));
                    DetailLookup.add(DET);
                }

        //conn.close();
        return DetailLookup;
    }
    
    
    
    public Vector<String> searchMasterRef(String masterCode) throws ClassNotFoundException, SQLException{
    
        Vector<String> mastDet= new Vector<String>();
        //Connection conn = connection.HSQLconnect();
        String sql="SELECT * From LOOKUP_MASTER where MASTER_REF_CODE=? ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, masterCode);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
                 mastDet=new Vector();
                 mastDet.add(rs.getString(1));
                 mastDet.add(rs.getString(2)); 
        }
        
        //conn.close();
        return mastDet;
    
    }
    
    
    public boolean InsertDetailLookup(Vector<String> details) throws ClassNotFoundException, SQLException{
        boolean inStat=false;
        
        //Connection conn = connection.HSQLconnect();
        String sql="Insert INTO  LOOKUP_DETAIL (MASTER_REF_CODE,DETAIL_REF_CODE, DESCRIPTION) Values (?,?,?)";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, details.elementAt(0));
        ps.setString(2, details.elementAt(1));
        ps.setString(3, details.elementAt(2));
         int m = ps.executeUpdate();
            if(m>0){
               inStat = true;                
            }else{
               inStat=false;       
            }   
            
            //conn.close();
        return inStat;
    }
    
    
    
    public boolean deleteMaster(String MasterCode) throws SQLException, ClassNotFoundException{
        boolean delMStat = false;
          
            //Connection hconn = connection.HSQLconnect();
            String sql = "Delete From LOOKUP_MASTER where MASTER_REF_CODE = ?";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, MasterCode);
            int s =ps.executeUpdate();
            if(s>0){
               delMStat = true;                
            }else{
                delMStat=false;       
            }
        
            //hconn.close();
    
    
        return delMStat;
               
    }
    
    
    public boolean DeleteDetailLK(String MasCode, String DetCode) throws SQLException, ClassNotFoundException{
        boolean delDStat = false;
          
            //Connection hconn = connection.HSQLconnect();
            String sql = "Delete From LOOKUP_DETAIL where MASTER_REF_CODE = ? and DETAIL_REF_CODE = ? ";
            PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
            ps.setString(1, MasCode);
            ps.setString(2, DetCode);
            int s =ps.executeUpdate();
            if(s>0){
               delDStat = true;                
            }else{
                delDStat=false;       
            }
        
            //hconn.close();
    
    
        return delDStat;
    }
    
    
    
    public String getAutoMasterID() throws ClassNotFoundException, SQLException{
        String AutoMasterCode= null;
        int ID;
        
        //Connection conn = connection.HSQLconnect();
        String sql="SELECT * From LOOKUP_MASTER";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            AutoMasterCode= rs.getString(1);
        }
        if(AutoMasterCode == null){
        ID = 1;
        }else{
        ID = Integer.valueOf(AutoMasterCode) + 1;}
        
        String format = String.format("%%0%dd", 4);
        AutoMasterCode= String.format(format, ID);
       // conn.close();
        return AutoMasterCode;
    }
    
    public boolean CheckDetID(String Mcode, String DetCode) throws SQLException, ClassNotFoundException{
        boolean Check = false;
    
        //Connection conn = connection.HSQLconnect();
        String sql="SELECT * From LOOKUP_DETAIL WHERE MASTER_REF_CODE = ? and DETAIL_REF_CODE = ? ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, Mcode);
        ps.setString(2, DetCode);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Check = true;
            
        }
    
        //conn.close();
        return Check;
    }
    
    //update Detail changes
    public boolean updateDetails(Vector<String>  UPdetails) throws ClassNotFoundException, SQLException{
        boolean upDetStat= false;
        //Connection conn = connection.HSQLconnect();
        String sql="UPDATE LOOKUP_DETAIL SET DESCRIPTION = ? WHERE MASTER_REF_CODE = ? and DETAIL_REF_CODE = ? ";
        PreparedStatement ps = Session.getCon_x(1000).prepareStatement(sql);
        ps.setString(1, UPdetails.elementAt(2));
        ps.setString(2, UPdetails.elementAt(0));
        ps.setString(3, UPdetails.elementAt(1));
         int s = ps.executeUpdate();
            if(s>0){
                upDetStat= true;
            }else{
                 upDetStat= false;
            }
        //conn.close();
        return  upDetStat;
    }

}
