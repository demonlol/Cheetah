package sbs.baka.cheetah.roku;

import com.jaku.api.*;
import com.jaku.core.*;
import com.jaku.model.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class RokuRemote {

    private static final String ROKU_DEVICE_IP_ADDRESS = "<device ip address>";

    public static void main(String [] args) {
        try {
            for (Device discoverDevice : DeviceRequests.discoverDevices()) {
                for (Method method : discoverDevice.getClass().getMethods()) {
                    if(method.getName().startsWith("get")) {
                        System.out.println(method.invoke(discoverDevice, null));
                    }
                }
                System.out.println("\n\n\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static boolean press(KeypressKeyValues k) {
        try {
            KeyRequests.keyupRequest(ROKU_DEVICE_IP_ADDRESS, k);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void queryApps() throws IOException {
        List<Channel> channels = QueryRequests.queryAppsRequest(ROKU_DEVICE_IP_ADDRESS);

        for (Channel channel: channels) {
            System.out.println(channel.getTitle());
        }
    }

    private static void queryActiveApp() throws IOException {
        List<Channel> channels = QueryRequests.queryActiveAppRequest(ROKU_DEVICE_IP_ADDRESS);

        for (Channel channel: channels) {
            System.out.println(channel.getId());
        }
    }

    private static void queryDeviceInfo() throws IOException {
        Device device = QueryRequests.queryDeviceInfo(ROKU_DEVICE_IP_ADDRESS);

        System.out.println(device.getCountry());
    }

    private static void launchAppId() throws IOException {
        LaunchRequests.launchAppIdRequest(ROKU_DEVICE_IP_ADDRESS, "1457");
    }

    private static void queryIcon() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(QueryRequests.queryIconRequest(ROKU_DEVICE_IP_ADDRESS, "1457")));

        System.out.println(bufferedImage.getWidth());

        File outputfile = new File("<some path>");
        ImageIO.write(bufferedImage, "jpeg", outputfile);
    }

}
