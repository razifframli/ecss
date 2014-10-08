/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author WC
 */
public class MSH {
    
    private String segmentId="MSH";
	private String segmentTerminator="<cr>";
	private String fieldSeparator = "|";
	private String encodingCharacters = "^";
	private String sendingApplication;
	private String sendingFacility;
	private String receivingApplication="OMS";
	private String receivingFacility ;
        private String dateTimeOfMsg;
        private String MessageType= "ST";
        private String messageControlID;
        SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyyyHH:mm:ss");
        SimpleDateFormat CIDsdf = new SimpleDateFormat ("ddMMyyyHHmmss");
        
    
    /*Constructor*/
    public MSH( String _sendingApp, String _sendingFac, String _TranXcode , String _receivingFac ){
        
        Date dNow = new Date( );
        this.sendingApplication=_sendingApp +"^"+ _TranXcode;
        this.dateTimeOfMsg = sdf.format(dNow);
        this.messageControlID = CIDsdf.format(dNow);
        this.sendingFacility = _sendingFac;
        this.receivingFacility = _receivingFac;
        
    }
    


     public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String MessageType) {
        this.MessageType = MessageType;
    }

    public String getDateTimeOfMsg() {
        return dateTimeOfMsg;
    }

    public void setDateTimeOfMsg(String dateTimeOfMsg) {
        this.dateTimeOfMsg = dateTimeOfMsg;
    }

    public String getEncodingCharacters() {
        return encodingCharacters;
    }

    public void setEncodingCharacters(String encodingCharacters) {
        this.encodingCharacters = encodingCharacters;
    }

    public String getFieldSeparator() {
        return fieldSeparator;
    }

    public void setFieldSeparator(String fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }

    public String getMessageControlID() {
        return messageControlID;
    }

    public void setMessageControlID(String messageControlID) {
        this.messageControlID = messageControlID;
    }

    public String getReceivingApplication() {
        return receivingApplication;
    }

    public void setReceivingApplication(String receivingApplication) {
        this.receivingApplication = receivingApplication;
    }

    public String getReceivingFacility() {
        return receivingFacility;
    }

    public void setReceivingFacility(String receivingFacility) {
        this.receivingFacility = receivingFacility;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public String getSegmentTerminator() {
        return segmentTerminator;
    }

    public void setSegmentTerminator(String segmentTerminator) {
        this.segmentTerminator = segmentTerminator;
    }

    public String getSendingApplication() {
        return sendingApplication;
    }

    public void setSendingApplication(String sendingApplication) {
        this.sendingApplication = sendingApplication;
    }
    
    public String getMSH(){
        
        String resultMSH = new String();
        StringBuffer resultBF = new StringBuffer();
        
        resultBF.append(segmentId);
        resultBF.append(fieldSeparator);
        resultBF.append(encodingCharacters);
        resultBF.append(fieldSeparator);
        resultBF.append(sendingApplication);
        resultBF.append(fieldSeparator);
        resultBF.append(sendingFacility);
        resultBF.append(fieldSeparator);
        resultBF.append(receivingApplication);
        resultBF.append(fieldSeparator);
        resultBF.append(receivingFacility);
        resultBF.append(fieldSeparator);
        resultBF.append(dateTimeOfMsg);
        resultBF.append(fieldSeparator);
        resultBF.append(MessageType);
        resultBF.append(fieldSeparator);
        resultBF.append(messageControlID);
        resultBF.append(segmentTerminator);
        
        if (resultBF != null) {
            resultMSH = resultBF.toString();
        }
        
        return resultMSH;

    }
}
