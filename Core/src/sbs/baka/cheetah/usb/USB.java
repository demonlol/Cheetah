package sbs.baka.cheetah.usb;

import net.samuelcampos.usbdrivedetector.*;
import net.samuelcampos.usbdrivedetector.events.*;
import sbs.baka.cheetah.util.*;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;

public class USB {

    private static USBDeviceDetectorManager usbDetector;
    private static List<IUSBDriveListener> connectListeners, disconnectListeners;

    private static boolean blockingEDT = false;
    private static Thread blockingThread = null;

    static {
        usbDetector = new USBDeviceDetectorManager();
        connectListeners = new ArrayList<>();
        disconnectListeners = new ArrayList<>();

        usbDetector.setPollingInterval(5000);

        if(isExecutingFromUSB()) {
            usbDetector.addDriveListener(e -> {
                if (e.getEventType() == DeviceEventType.REMOVED && isExecutingFromUSB()) {
                    JOptionPane.showConfirmDialog(null, "The removable device Cheetah was running off of\nwas disconnected. Please reconnect your device to resume.", "USB Disconnected", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null /*new ImageIcon("")*/);
                    instantiateAndStartBlock();
                } else if (e.getEventType() == DeviceEventType.REMOVED) {
                    blockingEDT = false;
                    try {
                        blockingThread.join();
                    } catch (InterruptedException ex) {
                    }
                }
            });
        }

        usbDetector.addDriveListener(e -> {
            if(e.getEventType() == DeviceEventType.CONNECTED)
                connectListeners.forEach(d -> d.usbDriveEvent(new USBStorageEvent(e.getStorageDevice(), e.getEventType())));
            else if(e.getEventType() == DeviceEventType.REMOVED)
                disconnectListeners.forEach(d -> d.usbDriveEvent(new USBStorageEvent(e.getStorageDevice(), e.getEventType())));
        });
    }

    public static void addDisconnectListener(IUSBDriveListener listener) { disconnectListeners.add(listener); }
    public static void addConnectListener(IUSBDriveListener listener) { connectListeners.add(listener); }
    public static void removeDisconnectListener(IUSBDriveListener listener) { disconnectListeners.remove(listener); }
    public static void removeConnectListener(IUSBDriveListener listener) { connectListeners.remove(listener); }

    public static boolean isExecutingFromUSB() {
        if(EnvironmentUtil.getJarFilePath() == null) {
            new NullPointerException("Failed to locate jar path...").printStackTrace();
            return false;
        }

        for (USBStorageDevice device : usbDetector.getRemovableDevices()) {
            String devicePath = device.getRootDirectory().getAbsolutePath().substring(0, 3), jarPath = EnvironmentUtil.getJarFilePath().getAbsolutePath().substring(0, 3);

            if(devicePath.equals(jarPath))
                return true;
        }
        return false;
    }

    public static File getDirectory() {
        for (USBStorageDevice removableDevice : usbDetector.getRemovableDevices()) {
            return removableDevice.getRootDirectory();
        }
        return null;
    }

    private static void instantiateAndStartBlock() {
        blockingEDT = true;

        blockingThread = new Thread(() -> {
            while(blockingEDT) {
                try {
                    SwingUtilities.invokeAndWait(() -> { try { Thread.sleep(Long.MAX_VALUE); } catch(InterruptedException ex) {} } );
                } catch (InterruptedException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        blockingThread.start();
    }

}
