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
public class CCN {
    
        private String segmentId="CCN";
	private String segmentTerminator="<c>";
	private String fieldSeparator = "|";
	private String encodingCharacters = "^";
        private String episodeDate;
        
        private String symptomCode;
        private String symptomDesc;
        private String severityCd;
        private String severityDesc;
        private String duration;
        private String UOMCode;
        private String UOMDesc;
        private String bodySiteCd;
        private String bodySiteDesc;
        private String lateralityCd;
        private String lateralityDesc;
        private String comments;
        private String txnDate;
        private String status;
        private String encounterDate;
        private String hfcCode;
        private String doctorId;
        private String doctorName;
        
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat CIDsdf = new SimpleDateFormat ("ddMMyyyHHmmss");
        
        public CCN(String _sympCd, String _sympDesc, String _sevCod, String _sevDesc, String _duration, String _BodySiteCd
                ,String _BodySiteDesc, String _latCd, String _latDesc, String _cmnt, String _status,  String _drId, String _drName){
            
            Date dNow = new Date();
            this.episodeDate = sdf.format(dNow);
            this.encounterDate = sdf.format(dNow);
            this.symptomCode = _sympCd;
            this.symptomDesc = _sympDesc;
            this.severityCd = _sevCod;
            this.severityDesc = _sevDesc;
            this.duration = _duration;
//            this.UOMCode = _UOMCd;
//            this.UOMDesc = _UOMDesc;
            this.bodySiteCd = _BodySiteCd;
            this.bodySiteDesc = _BodySiteDesc;
            this.lateralityCd = _latCd;
            this.lateralityDesc = _latDesc;
            this.comments = _cmnt;
//            this.txnDate = _txnDate;
            this.status = _status;
//            this.hfcCode = _hfcCode;
            this.doctorId = _drId;
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
     * @return the symptomCode
     */
    public String getSymptomCode() {
        return symptomCode;
    }

    /**
     * @param symptomCode the symptomCode to set
     */
    public void setSymptomCode(String symptomCode) {
        this.symptomCode = symptomCode;
    }

    /**
     * @return the symptomDesc
     */
    public String getSymptomDesc() {
        return symptomDesc;
    }

    /**
     * @param symptomDesc the symptomDesc to set
     */
    public void setSymptomDesc(String symptomDesc) {
        this.symptomDesc = symptomDesc;
    }

    /**
     * @return the severityCd
     */
    public String getSeverityCd() {
        return severityCd;
    }

    /**
     * @param severityCd the severityCd to set
     */
    public void setSeverityCd(String severityCd) {
        this.severityCd = severityCd;
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
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

//    /**
//     * @return the UOMCode
//     */
//    public String getUOMCode() {
//        return UOMCode;
//    }
//
//    /**
//     * @param UOMCode the UOMCode to set
//     */
//    public void setUOMCode(String UOMCode) {
//        this.UOMCode = UOMCode;
//    }

    /**
     * @return the UOMDesc
     */
//    public String getUOMDesc() {
//        return UOMDesc;
//    }
//
//    /**
//     * @param UOMDesc the UOMDesc to set
//     */
//    public void setUOMDesc(String UOMDesc) {
//        this.UOMDesc = UOMDesc;
//    }

    /**
     * @return the bodySiteCd
     */
    public String getBodySiteCd() {
        return bodySiteCd;
    }

    /**
     * @param bodySiteCd the bodySiteCd to set
     */
    public void setBodySiteCd(String bodySiteCd) {
        this.bodySiteCd = bodySiteCd;
    }

    /**
     * @return the bodySiteDesc
     */
    public String getBodySiteDesc() {
        return bodySiteDesc;
    }

    /**
     * @param bodySiteDesc the bodySiteDesc to set
     */
    public void setBodySiteDesc(String bodySiteDesc) {
        this.bodySiteDesc = bodySiteDesc;
    }

    /**
     * @return the lateralityCd
     */
    public String getLateralityCd() {
        return lateralityCd;
    }

    /**
     * @param lateralityCd the lateralityCd to set
     */
    public void setLateralityCd(String lateralityCd) {
        this.lateralityCd = lateralityCd;
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
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the txnDate
     */
//    public String getTxnDate() {
//        return txnDate;
//    }
//
//    /**
//     * @param txnDate the txnDate to set
//     */
//    public void setTxnDate(String txnDate) {
//        this.txnDate = txnDate;
//    }

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
//    public String getHfcCode() {
//        return hfcCode;
//    }
//
//    /**
//     * @param hfcCode the hfcCode to set
//     */
//    public void setHfcCode(String hfcCode) {
//        this.hfcCode = hfcCode;
//    }

    /**
     * @return the doctorId
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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
    
    public String getCCN(){
        
        String fullCCN = null;
        StringBuffer CCNBuff = new StringBuffer();
        
        CCNBuff.append(segmentId);
        CCNBuff.append(fieldSeparator);
        CCNBuff.append(episodeDate);
        CCNBuff.append(fieldSeparator);
        CCNBuff.append(symptomCode);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(symptomDesc);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(severityCd);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(severityDesc);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(duration);
        CCNBuff.append(encodingCharacters);
//        CCNBuff.append(UOMCode);
//        CCNBuff.append(encodingCharacters);
//        CCNBuff.append(UOMDesc);
//        CCNBuff.append(encodingCharacters);
        CCNBuff.append(bodySiteCd);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(bodySiteDesc);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(lateralityCd);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(lateralityDesc);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(comments);
        CCNBuff.append(encodingCharacters);
//        CCNBuff.append(txnDate);
//        CCNBuff.append(encodingCharacters);
        CCNBuff.append(status);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(encounterDate);
        CCNBuff.append(encodingCharacters);
//        CCNBuff.append(hfcCode);
//        CCNBuff.append(encodingCharacters);
        CCNBuff.append(doctorId);
        CCNBuff.append(encodingCharacters);
        CCNBuff.append(doctorName);
        CCNBuff.append(fieldSeparator);
        CCNBuff.append(segmentTerminator);
        
        if(CCNBuff != null){
            
            fullCCN = CCNBuff.toString();
        }
   
        return fullCCN;
    }
    
}
