///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package api;
//
//import Bean.MSH;
//import Bean.ORC;
//import Bean.PDI;
//import Bean.DGS;
//import Bean.CCN;
///**
// *
// * @author BHEBEG
// */
//public class CISMessageFormatter {
//    
//    private String header;
//    private String stringORC;
//    private String stringPDI;
//    private String stringDGS;
//    private String stringCCN;
//    private String formattedMSG;
//    
//    public String (String _pdi, String _TranXcode, String _sendingApp, String _sendingFac,
//            String _receivingFac, String _orderingProviderDes,String _ordDept, String _provDept, CCN setCCN){
//        
//        
//       // MHS ( String _sendingApp, String _sendingFac, String _TranXcode , String _receivingFac )
//       // ORC (String _tranxCode, String _orderingProviderDes, String _ordDept, String _ordHF, String _provDept, String _provHF)
//        /* DGS(String _diagnosisType, String _diagnosisTypeDesc, String _diagnosisStatus, String _readCode, String _readDesc, String _diagnosisCode, String _diagnosisDesc
//            , String _sevCod, String _sevDesc, String _siteCode, String _siteDesc,String _epsdCtCode, String _epsdCtDesc, String _latCode, String _latDesc, String _causCode, String _causDesc, String _cmnt
//            , String _txnDate, String _status, String _hfcCode, String _drId, String _drName) */
//        /* public CCN(String _sympCd, String _sympDesc, String _sevCd, String _sevDesc, String _duration, String _UOMCd, String _UOMDesc, String _BodySiteCd
//                ,String _BodySiteDesc, String _latCd, String _latDesc, String _cmnt, String _txnDate, String _status, String _hfcCode, String _drId, String _drName)*/
//        
//        MSH msgHeader = new MSH(_sendingApp, _sendingFac, _TranXcode ,  _receivingFac);
//        ORC msgORC = new ORC(_TranXcode, _orderingProviderDes, _ordDept, _sendingApp, _provDept, _receivingFac);
//        PDI pdi = new PDI(_pdi);
//        DGS msgDGS = new DGS(_diagnosisType, _diagnosisTypeDesc, _diagnosisStatus, _readCode, _readDesc, _diagnosisCode, _diagnosisDesc, _sevCod, _sevDesc, _siteCode, _siteDesc, _epsdCtCode, _epsdCtDesc, _latCode, _latDesc, _causCode, _causDesc, _cmnt, _txnDate, _status, _hfcCode, _drId, _drName);
//        CCN msgCCN = new CCN( _sympCd, _sympDesc, _sevCod, _sevDesc, _duration, _UOMCd, _UOMDesc, _BodySiteCd, _BodySiteDesc, _latCd, _latDesc,_cmnt, _txnDate, _status, _hfcCode, _drId, _drName);
//        
//        
//        
//        header = msgHeader.getMSH();
//        stringORC= msgORC.getORC();
//        stringPDI=pdi.getMsgPDI();
//        
//        formattedMSG = header +"\n"+ stringORC +"\n"+ stringPDI;
//    
//        return formattedMSG;
//    }
//    
//}
