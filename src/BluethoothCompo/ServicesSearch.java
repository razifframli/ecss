package BluethoothCompo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.bluetooth.*;

/**
 *Search services
 */
public class ServicesSearch {

    static final UUID OBEX_FILE_TRANSFER = new UUID(0x1105);

    public static final Vector/*<String>*/ serviceFound = new Vector();
    public static  Vector<Vector<String>> viewVector = new Vector<Vector<String>>();
     public static Vector<String> data = new Vector<String>();

    public static void main(String[] args) throws IOException, InterruptedException {
       
        // First run RemoteDeviceDiscovery and use discoved device
        
        RemoteDeviceDiscovery.main(null);

        serviceFound.clear();

        UUID serviceUUID = OBEX_FILE_TRANSFER;
        if ((args != null) && (args.length > 0)) {
            serviceUUID = new UUID(args[0], false);
        }

        final Object serviceSearchCompletedEvent = new Object();

        DiscoveryListener listener = new DiscoveryListener() {

            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
            }

            public void inquiryCompleted(int discType) {
            }

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                for (int i = 0; i < servRecord.length; i++) {
                   System.out.println(servRecord[i].getHostDevice());
                    String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                    if (url == null) {
                        data.add(url);
                        System.out.println("print 4");
                        continue;

                    }
                    System.out.println("print 5");
                    serviceFound.add(url);
                    data.add(url);
                    DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
                    if (serviceName != null) {
                        System.out.println("service " + serviceName.getValue() + " found " + url);
                    } else {
                        System.out.println("service found " + url);
                    }
                }
               
            }

            public void serviceSearchCompleted(int transID, int respCode) {
                System.out.println("service search completed!");
                 
                 System.out.println("print 3");
                synchronized(serviceSearchCompletedEvent){
                    serviceSearchCompletedEvent.notifyAll();
                }
            }

        };

        UUID[] searchUuidSet = new UUID[] { serviceUUID };
        int[] attrIDs =  new int[] {
                0x0100 // Service name
        };

        for(Enumeration en = RemoteDeviceDiscovery.devicesDiscovered.elements(); en.hasMoreElements(); ) {
            RemoteDevice btDevice = (RemoteDevice)en.nextElement();

            synchronized(serviceSearchCompletedEvent) {
                System.out.println("search services on " + btDevice.getBluetoothAddress() + " " + btDevice.getFriendlyName(false));
                data = new Vector<String>();
                data.add(btDevice.getFriendlyName(false));
                System.out.println("print 1");
                System.out.println(btDevice.getFriendlyName(false));


                LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
                serviceSearchCompletedEvent.wait();
                viewVector.add(data);
                
                System.out.println("print 2");
            }
        }

    }

}