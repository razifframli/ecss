/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.report_ICD10.pharmacy;

/**
 *
 * @author umarmukhtar
 */
public class MainPharmacyReport {
    
    public static void runReport() {
        
        new Report1().setVisible(true);
    }
    
    public static void main(String[] args) {
        
        MainPharmacyReport.runReport();
    }
}
