package sbs.baka.cheetah.social.media.snapchat.android.adb.cmds;

public enum ADBCommand {

    //Adb command helper

    //Operate similar to IntelliJ's auto completion
    //i.e, with parameters,
    //     highlight the parameter's section (with a custom type of blink perhaps),
    //     Enter? or arrow key? onto next (if any) parameter

    //adb                       *popup avaliable devices* *arrow keys/mouse to navigate*
    //adb sh                    *popup avaliable devices* *arrow keys/mouse to navigate*
//        shell
    //adb r                    *popup avaliable devices* *arrow keys/mouse to navigate*
//        root
//        reconnect
//        reboot
//        remount
//        restore
//
//
    //adb -s <...>               *popup avaliable devices* *arrow keys/mouse to navigate*
    //adb -s emulator-5555 <...> *popup all available commands that match the input
//                         shell
//
//
//
//
//
//
//
//
//
    //NORMAL COMMANDS
    USB("usb",""),
    DEVICES("devices","","-l"),
    CONNECT("connect <ip_address_of_device>", ""),
    KILL_SERVER("kill-server",""),
    START_SERVER("start-server",""),
    REBOOT("reboot",""),
    ROOT("root",""),
    UNROOT("unroot",""),

    //SHELL COMMANDS
    DUMP_UIAUTOMATOR("shell uiautomator dump", "")
    ;

    private String command, description; //frequent uses?
    private String[] optional;

    ADBCommand(String command, String description, String... optional) {
        this.command = command;
        this.description = description;
        this.optional = optional;
    }

    public String getCommand() { return command; }
    public String getDescription() { return description; }
    public String[] getOptional() { return optional; }
    public boolean hasOptional() { return optional != null && optional.length > 0; }
//    public int getWildcardPlaceholderCou - for the <> (required) and [optional] parameters
}

    /*
adb -s emulator-5554 shell uiautomator dump /sdcard/view.xml
adb -s emulator-5554 shell pull /sdcard/view.xml

UI xml automater




Why don't you do the following:

adb shell uiautomator dump

adb pull /sdcard/window_dump.xml

If you directly want to read out from shell you can execute following command from adb shell after dumping the XML
adb shell cat /sdcard/window_dump.xml


bluestacks path: C:\Program Files\BlueStacks_nxt
package:/data/app/com.snapchat.android-1/base.apk=0

adb connect 127.0.0.1:5555



== Devices
adb usb
adb devices   //show devices attached
adb devices -l //devices (product/model)
adb connect ip_address_of_device

adb kill-server
adb start-server

adb reboot
adb root //restarts adb with root permissions
adb unroot //restarts adb with root permissions


adb reconnect                  Kick current connection from host side and make it reconnect.
adb reconnect device           Kick current connection from device side and make it reconnect.




adb get-state                - prints: offline | bootloader | device
adb get-serialno             - prints: <serial-number>
adb get-devpath              - prints: <device-path>
adb remount                  - remounts the /system, /vendor (if present) and /oem (if present) partitions on the device read-write
adb reboot [bootloader|recovery]
           - reboots the device, optionally into the bootloader or recovery program.
adb reboot sideload          - reboots the device into the sideload mode in recovery program (adb root required).
adb reboot sideload-auto-reboot
           - reboots into the sideload mode, then reboots automatically after the sideload regardless of the result.
adb sideload <file>          - sideloads the given package
adb root                     - restarts the adbd daemon with root permissions
adb unroot                   - restarts the adbd daemon without root permissions
adb usb                      - restarts the adbd daemon listening on USB
adb tcpip <port>             - restarts the adbd daemon listening on TCP on the specified port



adb get-state                - prints: offline | bootloader | device
adb get-serialno             - prints: <serial-number>
adb get-devpath              - prints: <device-path>
adb remount                  - remounts the /system, /vendor (if present) and /oem (if present) partitions on the device read-write
adb reboot [bootloader|recovery]
           - reboots the device, optionally into the bootloader or recovery program.
adb reboot sideload          - reboots the device into the sideload mode in recovery program (adb root required).
adb reboot sideload-auto-reboot
           - reboots into the sideload mode, then reboots automatically after the sideload regardless of the result.
adb sideload <file>          - sideloads the given package
adb root                     - restarts the adbd daemon with root permissions
adb unroot                   - restarts the adbd daemon without root permissions
adb usb                      - restarts the adbd daemon listening on USB
adb tcpip <port>             - restarts the adbd daemon listening on TCP on the specified port

adb restore <file>           - restore device contents from the <file> backup archive

adb emu <command>            - run emulator console command

adb shell [-e escape] [-n] [-Tt] [-x] [command]

adb logcat [ <filter-spec> ]





















adb -e install path/to/app.apk

== Key event
adb shell input keyevent 3 // Home btn
adb shell input keyevent 4 // Back btn
adb shell input keyevent 5 // Call
adb shell input keyevent 6 // End call
adb shell input keyevent 26  // Turn Android device ON and OFF. It will toggle device to on/off status.
adb shell input keyevent 27 // Camera
adb shell input keyevent 64 // Open browser
adb shell input keyevent 66 // Enter
adb shell input keyevent 67 // Delete (backspace)
adb shell input keyevent 207 // Contacts
adb shell input keyevent 220 / 221 // Brightness down/up
adb shell input keyevent 277 / 278 /279 // Cut/Copy/Paste

== Screenshot
adb shell screencap -p /sdcard/screenshot.png

// Emulate device
adb shell wm size 2048x1536
adb shell wm density 288
// And reset to default
adb shell wm size reset
adb shell wm density reset

== Files
adb push [source] [destination]    // Copy files from your computer to your phone.
adb pull [device file location] [local file location] // Copy files from your phone to your computer.

== Get device android version
adb shell getprop ro.build.version.release



list only user packages | set out through pipe, each seperated by new lines, beginning with
adb shell pm list packages -3"|cut -f 2 -d ":
adb shell getprop ro.build.version.release
adb push [source] [destination]    // Copy files from your computer to your phone.
adb pull [device file location] [local file location] // Copy files from your phone to your computer.
adb shell wm size 2048x1536
adb shell wm density 288
// And reset to default
adb shell wm size reset
adb shell wm density reset
adb shell screencap -p /sdcard/screenshot.png
adb shell input keyevent <key> // Home btn




























/data/data/<package>/databases (app databases)
/data/data/<package>/shared_prefs/ (shared preferences)
/data/app (apk installed by user)
/system/app (pre-installed APK files)
/mmt/asec (encrypted apps) (App2SD)
/mmt/emmc (internal SD Card)
/mmt/adcard (external/Internal SD Card)
/mmt/adcard/external_sd (external SD Card)

adb shell ls (list directory contents)
adb shell ls -s (print size of each file)
adb shell ls -R (list subdirectories recursively)
adb push <local> <remote> (copy file/dir to device)
adb pull <remote> <local> (copy file/dir from device)
run-as <package> cat <file> (access the private package files)
adb get-stat?? (print device state)
adb get-serialno (get the serial number)
adb shell dumpsys iphonesybinfo (get the IMEI)
adb shell netstat (list TCP connectivity)
adb shell pwd (print current working directory)
adb shell dumpsys battery (battery status)
adb shell pm list features (list phone features)
adb shell service list (list all services)
adb shell dumpsys activity <package>/<activity> (activity info)
adb shell ps (print process status)
adb shell wm size (displays the current screen resolution)
dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp' (print current app's opened activity)
adb shell list packages (list package names)
adb shell list packages -r (list package name + path to apks)
adb shell list packages -3 (list third party package names)
adb shell list packages -s (list only system packages)
adb shell list packages -u (list package names + uninstalled)
adb shell dumpsys package packages (list info on all apps)
adb shell dump <name> (list info on one package)
adb shell path <package> (path to the apk file)
adb shell dumpsys battery set level <n> (change the level from 0 to 100)
adb shell dumpsys battery set status<n> (change the level to unknown, charging, discharging, not charging or full)
adb shell dumpsys battery reset (reset the battery)
adb shell dumpsys battery set usb <n> (change the status of USB connection. ON or OFF)
adb shell wm size WxH (sets the resolution to WxH)
adb reboot-recovery (reboot device into recovery mode)
adb reboot fastboot (reboot device into recovery mode)
adb shell screencap -p "/path/to/screenshot.png" (capture screenshot)
adb shell screenrecord "/path/to/record.mp4" (record device screen)
adb backup -apk -all -f backup.ab (backup settings and apps)
adb backup -apk -shared -all -f backup.ab (backup settings, apps and shared storage)
adb backup -apk -nosystem -all -f backup.ab (backup only non-system apps)
adb restore backup.ab (restore a previous backup)
adb shell am start -a android.intent.action.VIEW -d URL (open URL)
adb shell am start -t image/* -a android.intent.action.VIEW (opens gallery)
adb shell am start|startservice|broadcast <INTENT>[<COMPONENT>]
-a <ACTION> e.g. android.intent.action.VIEW
-c <CATEGORY> e.g. android.intent.category.LAUNCHER (start activity intent)
adb logcat [options] [filter] [filter] (view device log)
adb shell permissions groups (list permission groups definitions)
adb shell list permissions -g -r (list permissions details)

     */