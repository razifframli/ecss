/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author BHEBEG
 */
public class DGS {
    
        private String segmentId="DGS";
	private String segmentTerminator="<c>";
	private String fieldSeparator = "|";
	private String encodingCharacters = "^";
        private String episodeDate;
        private ArrayList diagnosisInfo;
        
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
        private String episodeCityDesc;
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
        
    public DGS(String _diagnosisType, String _diagnosisTypeDesc, String _diagnosisStatus, String _readCode, String _readDesc, String _diagnosisCode, String _diagnosisDesc
            , String _sevCod, String _sevDesc, String _siteCode, String _siteDesc,String _epsdCtCode, String _epsdCtDesc, String _latCode, String _latDesc, String _causCode, String _causDesc, String _cmnt
            , String _txnDate, String _status, String _hfcCode, String _drId, String _drName){
        
        Date dNow = new Date();
        this.episodeDate = sdf.format(dNow);
        this.encounterDate = sdf.format(dNow);
        this.diagnosisDate = sdf.format(dNow);
        this.diagnosisType = _diagnosisType;
        this.diagnosisTypeDesc = _diagnosisTypeDesc;
        this.diagnosisStatus = _diagnosisStatus;
        this.readCode = _readCode;
        this.readDescription = _readDesc;
        this.diagnosisCode = _diagnosisCode;
        this.diagnosisDesc = _diagnosisDesc;
        this.severityCode = _sevCod;
        this.severityDesc = _sevDesc;
        this.siteCode = _siteCode;
        this.siteDesc = _siteDesc;
        this.episodeCityCode = _epsdCtCode;
        this.episodeCityDesc = _epsdCtDesc;
        this.lateralityCode = _latCode;
        this.lateralityDesc = _latDesc;
        this.causativeCode = _causCode;
        this.causativeDesc = _causDesc;
        this.comment = _cmnt;
        this.txnDate = _txnDate;
        this.status = _status;
        this.hfcCode = _hfcCode;
        this.doctorID = _drId;
        this.doctorName = _drName;
    }

    /**
     * @return the episodeDate
     */
    public String getEpisodeDate() {
        return episodeDate;
    }

    /**
     * @param episodeDate the episodeDate to set
     */
    public void setEpisodeDate(String episodeDate) {
        this.episodeDate = episodeDate;
    }

    /**
     * @return the diagnosisInfo
     */
    public ArrayList getDiagnosisInfo() {
        return diagnosisInfo;
    }

    /**
     * @param diagnosisInfo the diagnosisInfo to set
     */
    public void setDiagnosisInfo(ArrayList diagnosisInfo) {
        this.diagnosisInfo = diagnosisInfo;
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

    /**
     * @return the episodeCityDesc
     */
    public String getEpisodeCityDesc() {
        return episodeCityDesc;
    }

    /**
     * @param episodeCityDesc the episodeCityDesc to set
     */
    public void setEpisodeCityDesc(String episodeCityDesc) {
        this.episodeCityDesc = episodeCityDesc;
    }
    
    public String getDGS(){
        
        String fullDGS = null;
        StringBuffer DGSBuff = new StringBuffer();
        
        DGSBuff.append(segmentId);
        DGSBuff.append(fieldSeparator);
        DGSBuff.append(episodeDate);
        DGSBuff.append(fieldSeparator);
        
        DGSBuff.append(diagnosisType);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(diagnosisTypeDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(diagnosisStatus);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(diagnosisDate);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(readCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(readDescription);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(diagnosisCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(diagnosisDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(severityCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(severityDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(siteCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(siteDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(episodeCityCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(episodeCityDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(lateralityCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(lateralityDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(causativeCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(causativeDesc);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(comment);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(txnDate);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(status);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(encounterDate);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(hfcCode);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(doctorID);
        DGSBuff.append(encodingCharacters);
        DGSBuff.append(doctorName);
        DGSBuff.append(fieldSeparator);
        DGSBuff.append(segmentTerminator);


        
        
        if(DGSBuff != null){
            
            fullDGS = DGSBuff.toString();
        }
        
        
        return fullDGS;
    }
}
