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
public interface InterfaceRMI extends Remote {

        
        public Map getPMI(String icNo) throws RemoteException;
		
	
}
