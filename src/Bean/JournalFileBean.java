/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author End User
 */
public class JournalFileBean {
    private String centralCode;
    private String pmiNo;
    private String txnDate;
    private String txnDataBlob;
    private String statusSync;
    private int statusDischarge;

    public String getCentralCode() {
        return centralCode;
    }

    public void setCentralCode(String centralCode) {
        this.centralCode = centralCode;
    }

    public String getPmiNo() {
        return pmiNo;
    }

    public void setPmiNo(String pmiNo) {
        this.pmiNo = pmiNo;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnDataBlob() {
        return txnDataBlob;
    }

    public void setTxnDataBlob(String txnDataBlob) {
        this.txnDataBlob = txnDataBlob;
    }

    public String getStatusSync() {
        return statusSync;
    }

    public void setStatusSync(String statusSync) {
        this.statusSync = statusSync;
    }

    public int getStatusDischarge() {
        return statusDischarge;
    }

    public void setStatusDischarge(int statusDischarge) {
        this.statusDischarge = statusDischarge;
    }
}
