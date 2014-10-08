package BluethoothCompo;

import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.obex.*;

public class ObexPutClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        String serverURL = null; // = "btgoep://0019639C4007:6";
        if ((args != null) && (args.length > 0)) {
            serverURL = args[0];
        }
        if (serverURL == null) {
            String[] searchArgs = null;
            // Connect to OBEXPutServer from examples
            // searchArgs = new String[] { "11111111111111111111111111111123" };
            ServicesSearch.main(searchArgs);
            if (ServicesSearch.serviceFound.size() == 0) {
                System.out.println("OBEX service not found");
                return;
            }
            // Select the first service found
           System.out.println("Size" +ServicesSearch.serviceFound.size());
            serverURL = (String)ServicesSearch.serviceFound.lastElement();
            System.out.println("1123" + serverURL );
        }
        System.out.println("Connecting to " + serverURL);
        Connection conn= Connector.open("btgoep://6C83363F52E1:3");
        ClientSession clientSession = (ClientSession)conn ;
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
        byte data[] = "This is a sample helath Record".getBytes( "ISO-8859-1" );
        OutputStream os = putOperation.openOutputStream();
        System.out.println(data);
        os.write(data);
        os.close();
        System.out.println("Connecting 3");
        putOperation.close();

        clientSession.disconnect(null);

        clientSession.close();
    }


    }
