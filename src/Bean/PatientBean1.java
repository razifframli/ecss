/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import AccessDB.AccessDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jessica
 */
public class PatientBean1 {
    private String PmiNo;
    private String name;
    private String newIcNo;
    private String oldIcNo;
    private String IdType;
    private String IdNo;
    private String registNo;
    private String patientCategory;
    private String visitType;
    private String emergentType;
    private String eligibleCategory;
    private String eligibleType;
    private String discipline;
    private String subDiscipline;

    public PatientBean1(String PmiNo,String name,String newIcNo){
        this.PmiNo = PmiNo;
        this.name = name;
        this.newIcNo = newIcNo;
    }

     public PatientBean1()
     {}
    /**
     * @return the PmiNo
     */
    public String getPmiNo() {
        return PmiNo;
    }

    /**
     * @param PmiNo the PmiNo to set
     */
    public void setPmiNo(String PmiNo) {
        this.PmiNo = PmiNo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the newIcNo
     */
    public String getNewIcNo() {
        return newIcNo;
    }

    /**
     * @param newIcNo the newIcNo to set
     */
    public void setNewIcNo(String newIcNo) {
        this.newIcNo = newIcNo;
    }

    /**
     * @return the oldIcNo
     */
    public String getOldIcNo() {
        return oldIcNo;
    }

    /**
     * @param oldIcNo the oldIcNo to set
     */
    public void setOldIcNo(String oldIcNo) {
        this.oldIcNo = oldIcNo;
    }

    /**
     * @return the IdType
     */
    public String getIdType() {
        return IdType;
    }

    /**
     * @param IdType the IdType to set
     */
    public void setIdType(String IdType) {
        this.IdType = IdType;
    }

    /**
     * @return the IdNo
     */
    public String getIdNo() {
        return IdNo;
    }

    /**
     * @param IdNo the IdNo to set
     */
    public void setIdNo(String IdNo) {
        this.IdNo = IdNo;
    }

    /**
     * @return the registNo
     */
    public String getRegistNo() {
        return registNo;
    }

    /**
     * @param registNo the registNo to set
     */
    public void setRegistNo(String registNo) {
        this.registNo = registNo;
    }

    /**
     * @return the patientCategory
     */
    public String getPatientCategory() {
        return patientCategory;
    }

    /**
     * @param patientCategory the patientCategory to set
     */
    public void setPatientCategory(String patientCategory) {
        this.patientCategory = patientCategory;
    }

    /**
     * @return the visitType
     */
    public String getVisitType() {
        return visitType;
    }

    /**
     * @param visitType the visitType to set
     */
    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    /**
     * @return the emergentType
     */
    public String getEmergentType() {
        return emergentType;
    }

    /**
     * @param emergentType the emergentType to set
     */
    public void setEmergentType(String emergentType) {
        this.emergentType = emergentType;
    }

    /**
     * @return the eligibleCategory
     */
    public String getEligibleCategory() {
        return eligibleCategory;
    }

    /**
     * @param eligibleCategory the eligibleCategory to set
     */
    public void setEligibleCategory(String eligibleCategory) {
        this.eligibleCategory = eligibleCategory;
    }

    /**
     * @return the eligibleType
     */
    public String getEligibleType() {
        return eligibleType;
    }

    /**
     * @param eligibleType the eligibleType to set
     */
    public void setEligibleType(String eligibleType) {
        this.eligibleType = eligibleType;
    }

    /**
     * @return the discipline
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * @param discipline the discipline to set
     */
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    /**
     * @return the subDiscipline
     */
    public String getSubDiscipline() {
        return subDiscipline;
    }

    /**
     * @param subDiscipline the subDiscipline to set
     */
    public void setSubDiscipline(String subDiscipline) {
        this.subDiscipline = subDiscipline;
    }

    public String[] getPatientUsingPMI(String pmiNo) throws ClassNotFoundException, SQLException
    {
        String[] patientDetails = new String[64];
        Connection conn = AccessDB.getConnInstance();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA , PMS_EPISODE WHERE "
                + "PMS_PATIENT_BIODATA.PMI_NO=? AND PMS_EPISODE.PMI_NO=? AND PMS_EPISODE.STATUS=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, pmiNo);
        ps.setString(2, pmiNo);
        ps.setString(3, "Inpatient");
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<64;i++)
             {
                System.out.print(rs.getString(i+1));
                patientDetails[i]=rs.getString(i+1);
             }
        }
        //conn.close();
        return patientDetails;
    }

    public String[] getPatientUsingIC(String icNo) throws ClassNotFoundException, SQLException
    {
        String[] patientDetails = new String[64];
        Connection conn = AccessDB.getConnInstance();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA , PMS_EPISODE WHERE "
                + "PMS_PATIENT_BIODATA.NEW_IC_NO=? AND PMS_EPISODE.NEW_IC_NO=? AND PMS_EPISODE.STATUS=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, icNo);
        ps.setString(2, icNo);
        ps.setString(3, "Inpatient");
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<64;i++)
             {
                System.out.print(rs.getString(i+1));
                patientDetails[i]=rs.getString(i+1);
             }
        }
        //conn.close();
        return patientDetails;
    }

    public String[] getPatientUsingID(String idNo) throws ClassNotFoundException, SQLException
    {
        String[] patientDetails = new String[64];
        Connection conn = AccessDB.getConnInstance();
        String sql = "SELECT * FROM PMS_PATIENT_BIODATA  , PMS_EPISODE WHERE "
                + "PMS_PATIENT_BIODATA.ID_NO=? AND PMS_EPISODE.ID_NO=? AND PMS_EPISODE.STATUS=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idNo);
        ps.setString(2, idNo);
        ps.setString(3, "Inpatient");
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            for(int i=0;i<64;i++)
             {
                System.out.print(rs.getString(i+1));
                patientDetails[i]=rs.getString(i+1);
             }
        }
        //conn.close();
        return patientDetails;
    }

}
