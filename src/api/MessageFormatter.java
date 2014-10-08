
package api;

import Bean.MSH;
import Bean.ORC;
import Bean.PDI;

/**
 *
 * @author WC
 * this file is to create HL7 string format message
 */
public class MessageFormatter {
    
    private String header;
    private String stringORC;
    private String stringPDI;
    private String formattedMSG;
    
    public String buildMSG(String _pdi, String _TranXcode, String _sendingApp, String _sendingFac,
            String _receivingFac, String _orderingProviderDes,String _ordDept, String _provDept ){
        
        
       // MHS ( String _sendingApp, String _sendingFac, String _TranXcode , String _receivingFac )
       // ORC (String _tranxCode, String _orderingProviderDes, String _ordDept, String _ordHF, String _provDept, String _provHF)
        MSH msgHeader = new MSH(_sendingApp, _sendingFac, _TranXcode ,  _receivingFac);
        ORC msgORC = new ORC(_TranXcode, _orderingProviderDes, _ordDept, _sendingApp, _provDept, _receivingFac);
        PDI pdi = new PDI(_pdi);
        
        header = msgHeader.getMSH();
        stringORC= msgORC.getORC();
        stringPDI=pdi.getMsgPDI();
        
        formattedMSG = header +"\n"+ stringORC +"\n"+ stringPDI;
    
        return formattedMSG;
    }
    
    //new builder for testing
    public String buildCIS(String header,String patientInfo,String msgCC,String msgDIAG,String msgIMM,
            String msgVS,String msgDRUG,String msgDIS,String msgALG,String msgSH){
    
    formattedMSG = header+patientInfo+msgCC+msgDIAG+msgIMM+msgVS+msgDRUG+msgDIS+msgALG+msgSH;
    
    return formattedMSG;
    }

    
    
}
