/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author phoebe
 */
public class StaffBean {

    private String staffID;
    private String staffName;
    private String staffPassword;

    public StaffBean(String staffID, String staffPassword, String staffName)
    {
        this.staffID = staffID;
        this.staffPassword = staffPassword;
        this.staffName = staffName;
    }

    /**
     * @return the staffID
     */
    public String getStaffID()
    {
        return staffID;
    }

    /**
     * @param staffID the staffID to set
     */
    public void setStaffID(String staffID)
    {
        this.staffID = staffID;
    }

    /**
     * @return the staffPassword
     */
    public String getStaffPassword()
    {
        return staffPassword;
    }

    /**
     * @param staffPassword the staffPassword to set
     */
    public void setStaffPassword(String staffPassword)
    {
        this.staffPassword = staffPassword;
    }

    /**
     * @return the staffName
     */
    public String getstaffName()
    {
        return staffName;
    }

    /**
     * @param staffName the staffName to set
     */
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
}
