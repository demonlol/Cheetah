package sbs.baka.cheetah.bluetooth.obex;

import com.intel.bluetooth.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.bluetooth.*;

/**
 *
 * Minimal Services Search example.
 */
public class ServicesSearch {

    static final UUID OBEX_FILE_TRANSFER = new UUID(0x1106);
    static final UUID OBEX_OBJECT_PUSH = new UUID(0x1105);
    static final UUID SDP = new UUID(0x0001);
    static final UUID RFCOMM = new UUID(0x0003);
    static final UUID OBEX = new UUID(0x0008);
    static final UUID HTTP = new UUID(0x000C);
    static final UUID L2CAP = new UUID(0x0100);
    static final UUID BNEP = new UUID(0x000F);
    static final UUID SERIAL_PORT = new UUID(0x1101);
    static final UUID SERVICE_DISCOVERY_SERVER_SERVICE_CLASS_ID = new UUID(0x1105);
    static final UUID BROWSE_GROUP_DESCRIPTOR_SERVICE_CLASS_ID = new UUID(0x1001);
    static final UUID PUBLIC_BROWSE_GROUP = new UUID(0x1002);
    static final UUID PERSONAL_AREA_NETWORKING_USER = new UUID(0x1115);
    static final UUID NETWORK_ACCESS_POINT = new UUID(0x1116);
    static final UUID GROUP_NETWORK = new UUID(0x1117);

    public static final Vector/*<String>*/ serviceFound = new Vector();

    public static void main(String[] args) throws IOException, InterruptedException {

        // First run RemoteDeviceDiscovery and use discoved device
        RemoteDeviceDiscovery.main(null);

        serviceFound.clear();

        UUID serviceUUID = OBEX_OBJECT_PUSH;
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
                    String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                    if (url == null) {
                        continue;
                    }
                    serviceFound.add(url);
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
                LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
                serviceSearchCompletedEvent.wait();
            }
        }

    }

}