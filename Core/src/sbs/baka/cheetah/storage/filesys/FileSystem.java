package sbs.baka.cheetah.storage.filesys;


import sbs.baka.cheetah.usb.*;

import java.io.*;
import java.security.*;

public class FileSystem {

    private static FileSystem fileSys;
    public static FileSystem getInstance() {
        File rootFile, usbDir;
        if(USB.isExecutingFromUSB() && ((usbDir = USB.getDirectory()) != null)) {
            rootFile = new File(usbDir + "\\Cheetah");
        } else {
            rootFile = new File(System.getenv("APPDATA") + "\\Cheetah");
        }

        if(fileSys == null) {
            if(!rootFile.exists()) {
                System.out.println((rootFile.mkdirs() ? "Created" : "Failed to create") + " new FileSystem root directory (" + rootFile.getAbsolutePath() + ").");
            }
            if(!rootFile.isDirectory()) throw new InvalidParameterException();

            fileSys = new FileSystem(rootFile);
        }
        return fileSys;
    }

    private File root;

    private FileSystem(File root) {
        this.root = root;
    }

    public File getRoot() {
        return root;
    }
}
