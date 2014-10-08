/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientRMI;


import InterfaceRMI.InterfaceRMI;
import java.rmi.Naming;
import java.util.Map;

/**
 *
 * @author WC
 */
public class ObtainPMI {
    
    
    public Map getServerPMI(String ICno) {
		// TODO Auto-generated method stub

		/*Define interface object*/
		InterfaceRMI myClient;
		Map mapPMI = null;
		try {
			/* Lookup for server object in registry 
			 * Cast to remoter interface rmi */
			myClient = (InterfaceRMI)Naming.lookup("//192.168.1.38/MyServer");
			
			mapPMI = myClient.getPMI(ICno);
                        String[] PDI = (String[])mapPMI.get("PDI"); 
                        
                        for(int i = 0; i<PDI.length ; i++){
			System.out.println("wow ----" +PDI[i] );
                        }        
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
                return mapPMI;
	}
    
    
}
