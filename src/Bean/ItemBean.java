/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author Jessica
 */
public class ItemBean {
    private String itemCode;
    private String itemName;
    private String itemPrice;
    private int itemUnit;

    public ItemBean(String itemName,String itemPrice)
    {
        this.itemName = itemName;
        //this.itemUnit = 1;
        this.itemPrice = itemPrice;
    }
    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemPrice
     */
    public String getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice the itemPrice to set
     */
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * @return the itemUnit
     */
    public int getItemUnit() {
        return itemUnit;
    }

    /**
     * @param itemUnit the itemUnit to set
     */
    public void setItemUnit(int itemUnit) {
        this.itemUnit = 1;
    }

}
