package sbs.baka.cheetah.social.media.snapchat.android.adb;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class BluestackInstallation {

    public boolean isEmulatorRootExistent() { return new File(getEmulatorRootDirectory()).exists(); }

    public String getEmulatorRootDirectory() {
        File defaultDir = new File("C:\\Program Files\\BlueStacks_nxt\\");
        if (defaultDir.exists()) return defaultDir.getAbsolutePath();
        return "";
    }

    public String getEmulatorProcessName() { return "HD-Player.exe"; }

    public Dimension getIconDimensions() {
        BufferedImage bi = getBufferedIcon();
        return new Dimension(bi.getWidth(), bi.getHeight());
    }
    public BufferedImage getBufferedIcon() {
        try {
            return ImageIO.read(new FileInputStream("C:\\Program Files\\BlueStacks_nxt\\Assets\\BlueStacks.ico"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Image getIcon(double scale) {
        Dimension d = getIconDimensions();
        return getImageIcon((int) Math.round(d.getHeight() * scale), (int) Math.round(d.getHeight() * scale));
    }
    public Image getImageIcon(int w, int h) {
        try {
            BufferedImage bi = ImageIO.read(new FileInputStream("C:\\Program Files\\BlueStacks_nxt\\Assets\\BlueStacks.ico"));
            return bi.getScaledInstance(w, h, bi.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getEmulatorName() { return "BlueStacks"; }

    public File getAdbFile() {
        for (File file : new File[]{
                new File(getEmulatorRootDirectory() + "HD-ADB.exe"),
                new File(getEmulatorRootDirectory() + "adb.exe")}) {
            if (file.exists())
                return file;
        }
        return null;
    }

    public Rectangle getEmulatorWindowBounds() {
        return null;
    }

    public String getEmulatorExecutable() {
        return getEmulatorRootDirectory() + getEmulatorProcessName();
    }

}
