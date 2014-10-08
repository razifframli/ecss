/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author BHEBEG
 */
public class DGSInfo {
    
    private String segmentTerminator="<c>";
    private String fieldSeparator = "|";
    private String encodingCharacters = "^";
    private String diagnosisType;
    private String diagnosisTypeDesc;
    private String diagnosisStatus;
    private String diagnosisDate;
    private String readCode;
    private String readDescription;
    private String diagnosisCode;
    private String diagnosisDesc;
    private String severityCode;
    private String severityDesc;
    private String siteCode;
    private String siteDesc;
    private String episodeCityCode;
    private String lateralityCode;
    private String lateralityDesc;
    private String causativeCode;
    private String causativeDesc;
    private String comment;
    private String txnDate;
    private String status;
    private String encounterDate;
    private String hfcCode;
    private String doctorID;
    private String doctorName;
    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat CIDsdf = new SimpleDateFormat ("ddMMyyyHHmmss");
    
    /*constructor*/
    
    public DGSInfo(){
        
        Date dNow = new Date();
        
        
    
    }

    /**
     * @return the diagnosisType
     */
    public String getDiagnosisType() {
        return diagnosisType;
    }

    /**
     * @param diagnosisType the diagnosisType to set
     */
    public void setDiagnosisType(String diagnosisType) {
        this.diagnosisType = diagnosisType;
    }

    /**
     * @return the diagnosisTypeDesc
     */
    public String getDiagnosisTypeDesc() {
        return diagnosisTypeDesc;
    }

    /**
     * @param diagnosisTypeDesc the diagnosisTypeDesc to set
     */
    public void setDiagnosisTypeDesc(String diagnosisTypeDesc) {
        this.diagnosisTypeDesc = diagnosisTypeDesc;
    }

    /**
     * @return the diagnosisStatus
     */
    public String getDiagnosisStatus() {
        return diagnosisStatus;
    }

    /**
     * @param diagnosisStatus the diagnosisStatus to set
     */
    public void setDiagnosisStatus(String diagnosisStatus) {
        this.diagnosisStatus = diagnosisStatus;
    }

    /**
     * @return the diagnosisDate
     */
    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    /**
     * @param diagnosisDate the diagnosisDate to set
     */
    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    /**
     * @return the readCode
     */
    public String getReadCode() {
        return readCode;
    }

    /**
     * @param readCode the readCode to set
     */
    public void setReadCode(String readCode) {
        this.readCode = readCode;
    }

    /**
     * @return the readDescription
     */
    public String getReadDescription() {
        return readDescription;
    }

    /**
     * @param readDescription the readDescription to set
     */
    public void setReadDescription(String readDescription) {
        this.readDescription = readDescription;
    }

    /**
     * @return the diagnosisCode
     */
    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    /**
     * @param diagnosisCode the diagnosisCode to set
     */
    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    /**
     * @return the diagnosisDesc
     */
    public String getDiagnosisDesc() {
        return diagnosisDesc;
    }

    /**
     * @param diagnosisDesc the diagnosisDesc to set
     */
    public void setDiagnosisDesc(String diagnosisDesc) {
        this.diagnosisDesc = diagnosisDesc;
    }

    /**
     * @return the severityCode
     */
    public String getSeverityCode() {
        return severityCode;
    }

    /**
     * @param severityCode the severityCode to set
     */
    public void setSeverityCode(String severityCode) {
        this.severityCode = severityCode;
    }

    /**
     * @return the severityDesc
     */
    public String getSeverityDesc() {
        return severityDesc;
    }

    /**
     * @param severityDesc the severityDesc to set
     */
    public void setSeverityDesc(String severityDesc) {
        this.severityDesc = severityDesc;
    }

    /**
     * @return the siteCode
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * @param siteCode the siteCode to set
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    /**
     * @return the siteDesc
     */
    public String getSiteDesc() {
        return siteDesc;
    }

    /**
     * @param siteDesc the siteDesc to set
     */
    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
    }

    /**
     * @return the episodeCityCode
     */
    public String getEpisodeCityCode() {
        return episodeCityCode;
    }

    /**
     * @param episodeCityCode the episodeCityCode to set
     */
    public void setEpisodeCityCode(String episodeCityCode) {
        this.episodeCityCode = episodeCityCode;
    }

    /**
     * @return the lateralityCode
     */
    public String getLateralityCode() {
        return lateralityCode;
    }

    /**
     * @param lateralityCode the lateralityCode to set
     */
    public void setLateralityCode(String lateralityCode) {
        this.lateralityCode = lateralityCode;
    }

    /**
     * @return the lateralityDesc
     */
    public String getLateralityDesc() {
        return lateralityDesc;
    }

    /**
     * @param lateralityDesc the lateralityDesc to set
     */
    public void setLateralityDesc(String lateralityDesc) {
        this.lateralityDesc = lateralityDesc;
    }

    /**
     * @return the causativeCode
     */
    public String getCausativeCode() {
        return causativeCode;
    }

    /**
     * @param causativeCode the causativeCode to set
     */
    public void setCausativeCode(String causativeCode) {
        this.causativeCode = causativeCode;
    }

    /**
     * @return the causativeDesc
     */
    public String getCausativeDesc() {
        return causativeDesc;
    }

    /**
     * @param causativeDesc the causativeDesc to set
     */
    public void setCausativeDesc(String causativeDesc) {
        this.causativeDesc = causativeDesc;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the txnDate
     */
    public String getTxnDate() {
        return txnDate;
    }

    /**
     * @param txnDate the txnDate to set
     */
    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the encounterDate
     */
    public String getEncounterDate() {
        return encounterDate;
    }

    /**
     * @param encounterDate the encounterDate to set
     */
    public void setEncounterDate(String encounterDate) {
        this.encounterDate = encounterDate;
    }

    /**
     * @return the hfcCode
     */
    public String getHfcCode() {
        return hfcCode;
    }

    /**
     * @param hfcCode the hfcCode to set
     */
    public void setHfcCode(String hfcCode) {
        this.hfcCode = hfcCode;
    }

    /**
     * @return the doctorID
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * @param doctorID the doctorID to set
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
