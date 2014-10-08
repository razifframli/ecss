package InterfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * This interface defines a remote object
 * to be hosted on a server
 */

/**
 * @author z600
 *
 */
public interface InterfaceRMI2 extends Remote {

	public int UpdatePatientToCentral(String Mstatus, String National) throws RemoteException; 
	

	public Map getPMI(String icNo) throws RemoteException;
	
}
