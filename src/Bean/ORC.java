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
public class ORC {
    
    private String fieldSeparator = "|";
    private String segmentTerminator="<cr>";
    private String segmentID = "ORC" ;
    private String TranXCode;
    private String placerOrder;
    private String orderDateTime;
    private String episodeDateTime;                               
    private String encounterDateTime;
    private String orderingProviderDesignation;      
    private String orderingDepartment;
    private String orderingHealthFac;
    private String providerDepartment;
    private String providerHealthfFac;
    SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyyyHH:mm:ss");
    SimpleDateFormat CIDsdf = new SimpleDateFormat ("ddMMyyyHHmmss");
    
    
    public ORC(String _tranxCode, String _orderingProviderDes, String _ordDept, String _ordHF, String _provDept, String _provHF)
    {
    
    Date dNow = new Date();
    this.TranXCode = _tranxCode;
    this.orderDateTime = sdf.format(dNow);
    this.episodeDateTime = sdf.format(dNow);
    this.encounterDateTime= sdf.format(dNow);
    this.placerOrder = _ordHF+CIDsdf.format(dNow);
    this.orderingProviderDesignation = _orderingProviderDes;
    this.orderingDepartment = _ordDept;
    this.orderingHealthFac = _ordHF;
    this.providerDepartment = _provDept;
    this.providerHealthfFac= _provHF;

    
    }
    
    
     public String getTranXCode() {
        return TranXCode;
    }

    public void setTranXCode(String TranXCode) {
        this.TranXCode = TranXCode;
    }

    public String getEncounterDateTime() {
        return encounterDateTime;
    }

    public void setEncounterDateTime(String encounterDateTime) {
        this.encounterDateTime = encounterDateTime;
    }

    public String getEpisodeDateTime() {
        return episodeDateTime;
    }

    public void setEpisodeDateTime(String episodeDateTime) {
        this.episodeDateTime = episodeDateTime;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderingDepartment() {
        return orderingDepartment;
    }

    public void setOrderingDepartment(String orderingDepartment) {
        this.orderingDepartment = orderingDepartment;
    }

    public String getOrderingHealthFac() {
        return orderingHealthFac;
    }

    public void setOrderingHealthFac(String orderingHealthFac) {
        this.orderingHealthFac = orderingHealthFac;
    }

    public String getOrderingProviderDesignation() {
        return orderingProviderDesignation;
    }

    public void setOrderingProviderDesignation(String orderingProviderDesignation) {
        this.orderingProviderDesignation = orderingProviderDesignation;
    }

    public String getPlacerOrder() {
        return placerOrder;
    }

    public void setPlacerOrder(String placerOrder) {
        this.placerOrder = placerOrder;
    }

    public String getProviderDepartment() {
        return providerDepartment;
    }

    public void setProviderDepartment(String providerDepartment) {
        this.providerDepartment = providerDepartment;
    }

    public String getProviderHealthfFac() {
        return providerHealthfFac;
    }

    public void setProviderHealthfFac(String providerHealthfFac) {
        this.providerHealthfFac = providerHealthfFac;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(String segmentID) {
        this.segmentID = segmentID;
    }
    
    public String getFieldSeparator() {
        return fieldSeparator;
    }

    public void setFieldSeparator(String fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }
    
    public String getORC(){
        
        String fullORC = null;
        StringBuffer ORCbuff = new StringBuffer();
        
        
        ORCbuff.append(segmentID);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(TranXCode);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(placerOrder);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(orderDateTime);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(episodeDateTime);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(encounterDateTime);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(orderingProviderDesignation);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(orderingDepartment);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(orderingHealthFac);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(providerDepartment);
        ORCbuff.append(fieldSeparator);
        ORCbuff.append(providerHealthfFac);
        ORCbuff.append(segmentTerminator);
        
        
        if (ORCbuff != null) {
            fullORC = ORCbuff.toString();
        }

        return fullORC;
    }
}
