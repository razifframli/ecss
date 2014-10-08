/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author phoebe
 */
public class DrugOrderBean {
    private String productCode;
    private String productName;
    private String frequency;
    private String route;
    private String dosageForm;
    private String strength;
    private String dosage;
    private String instruction;
    private String duration;
    private String qtyPerTime;
    private String totalQty;
    private String qtydispensed;
    private String orderStatus;

    public DrugOrderBean(String productCode, String productName, String frequency, String route, String dosageForm,String strength, String dosage, String instruction, String duration,String qtyPerTime,String totalQty,String qtydispensed,String orderStatus) 
    {
        this.productCode = productCode;
        this.productName = productName;
        this.frequency = frequency;
        this.route = route;
        this.dosageForm = dosageForm;
        this.strength = strength;
        this.dosage = dosage;
        this.instruction = instruction;
        this.duration = duration;
        this.qtyPerTime = qtyPerTime;
        this.totalQty = totalQty;
        this.qtydispensed = qtydispensed;
        this.orderStatus = orderStatus;
    }

    

    /**
     * @return the drugName
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * @param drugName the drugName to set
     */
    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    /**
     * @return the mdcCode
     */
    public String getProductName()
    {
        return productName;
    }

    /**
     * @param mdcCode the mdcCode to set
     */
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    /**
     * @return the dosage
     */
    public String getFrequency()
    {
        return frequency;
    }

    /**
     * @param dosage the dosage to set
     */
    public void setFrequency(String frequency)
    {
        this.frequency = frequency;
    }

    /**
     * @return the dosageForm
     */
    public String getRoute()
    {
        return route;
    }

    /**
     * @param dosageForm the dosageForm to set
     */
    public void setRoute(String route)
    {
        this.route = route;
    }

    /**
     * @return the dosageForm
     */
    public String getDosageForm()
    {
        return dosageForm;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setDosageForm(String dosageForm)
    {
        this.dosageForm = dosageForm;
    }

    /**
     * @return the route
     */
    public String getStrength()
    {
        return strength;
    }

    /**
     * @param route the route to set
     */
    public void setStrength(String strength)
    {
        this.strength = strength;
    }

    /**
     * @return the duration
     */
    public String getDosage()
    {
        return dosage;
    }

    /**
     * @param duration the duration to set
     */
    public void setDosage(String dosage)
    {
        this.dosage = dosage;
    }
public String getInstruction()
    {
        return instruction;
    }

    /**
     * @param instruction the totalQty to set
     */
    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }
        /**
     * @return the qtyPerTime
     */
    public String getDuration()
    {
        return duration;
    }

    /**
     * @param qtyPerTime the qtyPerTime to set
     */
    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    public String getQtyPerTime()
    {
        return qtyPerTime;
    }

    /**
     * @param qtyPerTime the qtyPerTime to set
     */
    public void setQtyPerTime(String qtyPerTime)
    {
        this.qtyPerTime = qtyPerTime;
    }

    /**
     * @return the stockQty
     */
    public String getTotalQty()
    {
        return totalQty;
    }

    /**
     * @param stockQty the stockQty to set
     */
    public void setTotalQty(String totalQty)
    {
        this.totalQty = totalQty;
    }

    /**
     * @return the totalQty
     */
    public String getQtydispensed()
    {
        return qtydispensed;
    }

    /**
     * @param totalQty the totalQty to set
     */
    public void setQtydispensed(String qtydispensed)
    {
        this.qtydispensed = qtydispensed;
    }

        /**
     * @return the instruction
     */
    public String getOrderStatus()
    {
        return orderStatus;
    }

    /**
     * @param totalQty the totalQty to set
     */
    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }
}
