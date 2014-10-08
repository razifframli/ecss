/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientRMI;

import InterfaceRMI.InterfaceRMI2;
import java.rmi.Naming;

/**
 *
 * @author WC
 */
public class UpdatePatientRMI {
    
    public int UpdatePatCentral(String mstat, String nat) {
		// TODO Auto-generated method stub
                
		/*Define interface object*/
		InterfaceRMI2 myClient;
                int status= 0;
		
		try {
			/* Lookup for server object in registry 
			 * Cast to remoter interface rmi */
			myClient = (InterfaceRMI2)Naming.lookup("//10.115.10.64/MyServer");
			
                        status = myClient.UpdatePatientToCentral(mstat, nat);
			
			 
			
			/* Display result */
 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
                System.out.println("return from server :"+ status);
                return status;
	}
    
    
    
    
}
