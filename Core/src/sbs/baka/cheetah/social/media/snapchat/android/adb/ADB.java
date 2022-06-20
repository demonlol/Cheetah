package sbs.baka.cheetah.social.media.snapchat.android.adb;

import java.io.*;

public class ADB {

    public static final String USER_INSTALLED_PACKAGES = "shell pm list packages -3\"|cut -f 2 -d \":";
    public static final String UIAUTOMATOR_DUMP = "shell uiautomator dump";

/*
//From file
adb shell uiautomator dump
adb pull /sdcard/window_dump.xml

//Read from shell
adb shell uiautomator dump
adb shell cat /sdcard/window_dump.xml
*/


    private final PopularEmulators emulator;

    public ADB(PopularEmulators emulator) {
        this.emulator = emulator;
    }

    //-s emulator-5556 shell input tap 50 50
    public String execute(String...commands) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(getAdbFile().getAbsolutePath());
        for (String command : commands)
            pb.command().add(command);

        pb.redirectErrorStream(true);

        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        StringBuilder builder = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString().trim();
    }
    public File getAdbFile() {
        File dir = new File(emulator.getPath());
        if(dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                String fn = file.getName();
                if(fn.contains("adb") && fn.endsWith(".exe")) return file;
            }
        }
        return null;
    }
//  900W x 1600H (withinin the emulator I assume) dpi = 400, windows dpi = GraphicsEnvironment...,

//    adb -s 127.0.0.1 shell uiautomator dump
//    adb -s 127.0.0.1 shell cat /sdcard/window_dump.xml

//    adb -s 127.0.0.1 shell uiautomator dump | adb -s 127.0.0.1 shell cat /sdcard/window_dump.xml

//    am start -n yourpackagename/.activityname
//    adb shell am start -n com.example.demo/com.example.test.MainActivity


    //start with injtent
    /*
    # specifying the action and data uri
adb shell am start -a "android.intent.action.VIEW" -d "http://developer.android.com"

# specifying the action, mime type and an extra string
adb shell am start -a "android.intent.action.SEND" --es "android.intent.extra.TEXT" "Hello World" -t "text/plain"

# specifying an explicit component name
adb shell am start -n "com.example.application/.MainActivity"
     */

    //he takes a 'screenshot'
    //selects the lement he wants
    //copies the 'resource_id' of the node
    //puts it in his "If_Element_Visisble" input,
    //presumably takes screenshots constantly to check?

    //touch xy
    //find element
    //find elements by name
    //find element by attribute (resource-id, class, etc)

}
