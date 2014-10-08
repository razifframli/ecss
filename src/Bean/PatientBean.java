/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author phoebe
 */
public class PatientBean {
    private String pmiNo;
    private String patientName;
    private String odate;
    private String loccode;
    private String adate;
    private String doc;

    public PatientBean(String pmiNo, String patientName, String odate, String loccode, String adate, String doc) {
        this.pmiNo = pmiNo;
        this.patientName = patientName;
        this.odate = odate;
        this.loccode = loccode;
        this.adate = adate;
        this.doc = doc;
    }


    /**
     * @return the pmiNo
     */
    public String getPmiNo()
    {
        return pmiNo;
    }

   
    public void setPmiNo(String pmiNo)
    {
        this.pmiNo = pmiNo;
    }
    
    public String getPname()
    {
        return patientName;
    }

   
    public void setPname(String patientName)
    {
        this.patientName = patientName;
    }

    
    public String getOdate()
    {
        return odate;
    }

    /**
     * @param dispenseStatus the dispenseStatus to set
     */
    public void setOdate(String odate)
    {
        this.odate = odate;
    }

    /**
     * @return the dispenseStatus
     */
    public String getLoccode()
    {
        return loccode;
    }

    /**
     * @param dispenseStatus the dispenseStatus to set
     */
    public void setLoccode(String loccode)
    {
        this.loccode = loccode;
    }
    public String getAdate()
    {
        return adate;
    }

    /**
     * @param dispenseStatus the dispenseStatus to set
     */
    public void setAdate(String adate)
    {
        this.adate = adate;
    }
    
    public String getDoc()
    {
        return doc;
    }

    /**
     * @param dispenseStatus the dispenseStatus to set
     */
    public void setDoc(String doc)
    {
        this.doc = doc;
    }
}
