/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BluethoothCompo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.obex.*;
/**
 *
 * @author WC
 */


public class transferFile {



    public void putFile(String serverURL, String patientEHR){
        try {

            System.out.println("Connecting to " + serverURL);
            Connection conn = Connector.open(serverURL);
            ClientSession clientSession = (ClientSession) conn;
            HeaderSet hsConnectReply = clientSession.connect(null);
            System.out.println("Connecting 1");
            if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
                System.out.println("Failed to connect");
                return;
            }
            System.out.println("Connecting 2");
            HeaderSet hsOperation = clientSession.createHeaderSet();
            hsOperation.setHeader(HeaderSet.NAME, "HealthRecord.txt");
            hsOperation.setHeader(HeaderSet.TYPE, "binary");
            //Create PUT Operation
            Operation putOperation = clientSession.put(hsOperation);
            // Send some text to server
            byte[] data = patientEHR.getBytes("UTF-8");
            OutputStream os = putOperation.openOutputStream();
            System.out.println(data);
            os.write(data);
            os.close();
            System.out.println("Connecting 3");
            putOperation.close();
            clientSession.disconnect(null);
            clientSession.close();
        } catch (IOException ex) {
            Logger.getLogger(transferFile.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
