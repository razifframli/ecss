/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import Bean.snomed;
import Database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author BHEBEG
 */
public class snomedcontroller {
    
    DbConnection dbCtrl;
    Connection conn;
    
    //controller
    public snomedcontroller(){
    
    dbCtrl = new DbConnection();
    
    }
    
    //this method is to add snomed c.complaints
    public int addSnomedCComplaints(snomed Snomed) throws Exception{
		//Establish Database Connection
		Connection conn = DbConnection.doConnection();
		//System.out.println("test1");
		int status=0;
		try {
			// SQL Statement for registering new lecturer
			String sql = "INSERT INTO SNOMEDCCOMPLAINTS VALUES(?,?)";
			//System.out.println("test2");
			// create prepared statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			//System.out.println("test3");
			//set Parameter
			ps.setString(1, Snomed.getSnomedID());
			ps.setString(2, Snomed.getSnomedDesc());

			//System.out.println("test4");
			//execute SQL Statement
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}finally{
			conn.close();
		}
		return status;
	}
    
    public int addSnomedAllergy(snomed Snomed) throws Exception{
		//Establish Database Connection
		Connection conn = DbConnection.doConnection();
		//System.out.println("test1");
		int status=0;
		try {
			// SQL Statement for registering new lecturer
			String sql = "INSERT INTO SNOMEDALLERGY VALUES(?,?)";
			//System.out.println("test2");
			// create prepared statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			//System.out.println("test3");
			//set Parameter
			ps.setString(1, Snomed.getSnomedID());
			ps.setString(2, Snomed.getSnomedDesc());

			//System.out.println("test4");
			//execute SQL Statement
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}finally{
			conn.close();
		}
		return status;
	}
    
    public int addSnomedSH(snomed Snomed) throws Exception{
		//Establish Database Connection
		Connection conn = DbConnection.doConnection();
		//System.out.println("test1");
		int status=0;
		try {
			// SQL Statement for registering new lecturer
			String sql = "INSERT INTO SNOMEDSH VALUES(?,?)";
			//System.out.println("test2");
			// create prepared statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			//System.out.println("test3");
			//set Parameter
			ps.setString(1, Snomed.getSnomedID());
			ps.setString(2, Snomed.getSnomedDesc());

			//System.out.println("test4");
			//execute SQL Statement
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}finally{
			conn.close();
		}
		return status;
	}
    public int addSnomedImmunization(snomed Snomed) throws Exception{
		//Establish Database Connection
		Connection conn = DbConnection.doConnection();
		//System.out.println("test1");
		int status=0;
		try {
			// SQL Statement for registering new lecturer
			String sql = "INSERT INTO SNOMEDIMMUNISATION VALUES(?,?)";
			//System.out.println("test2");
			// create prepared statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			//System.out.println("test3");
			//set Parameter
			ps.setString(1, Snomed.getSnomedID());
			ps.setString(2, Snomed.getSnomedDesc());

			//System.out.println("test4");
			//execute SQL Statement
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}finally{
			conn.close();
		}
		return status;
	}
    public int addSnomedDiagnosis(snomed Snomed) throws Exception{
		//Establish Database Connection
		Connection conn = DbConnection.doConnection();
		//System.out.println("test1");
		int status=0;
		try {
			// SQL Statement for registering new lecturer
			String sql = "INSERT INTO SNOMEDDIAGNOSIS VALUES(?,?)";
			//System.out.println("test2");
			// create prepared statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			//System.out.println("test3");
			//set Parameter
			ps.setString(1, Snomed.getSnomedID());
			ps.setString(2, Snomed.getSnomedDesc());

			//System.out.println("test4");
			//execute SQL Statement
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}finally{
			conn.close();
		}
		return status;
	}
    public int addSnomedDisability(snomed Snomed) throws Exception{
		//Establish Database Connection
		Connection conn = DbConnection.doConnection();
		//System.out.println("test1");
		int status=0;
		try {
			// SQL Statement for registering new lecturer
			String sql = "INSERT INTO SNOMEDDISABILITY VALUES(?,?)";
			//System.out.println("test2");
			// create prepared statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			//System.out.println("test3");
			//set Parameter
			ps.setString(1, Snomed.getSnomedID());
			ps.setString(2, Snomed.getSnomedDesc());

			//System.out.println("test4");
			//execute SQL Statement
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}finally{
			conn.close();
		}
		return status;
	}
    
    
}
