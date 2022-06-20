package sbs.baka.cheetah.plugins;

import org.slf4j.*;
import sbs.baka.cheetah.storage.filesys.FileSystem;

import java.io.*;

public abstract class CheetahPlugin {

    private static final Logger log = LoggerFactory.getLogger(CheetahPlugin.class);

    public CheetahPlugin() {
        File path = getConfigPath();

        if(!path.exists() && path.isDirectory() && path.mkdirs()) {
            log.debug("Created plugin directory for " + getMainClass() + " at " + path.getAbsolutePath());
        } else {
            log.debug("Failed to create plugin directory for " + getMainClass() + " at " + path.getAbsolutePath());
        }
    }

    //Called when a plugin is loaded into memory
    public abstract void onLoad();
    //Called when application is closed
    public abstract void onExit();

    //When plugin is enabled
    public abstract void onEnable();
    //When plugin is disabled
    public abstract void onDisable();

    public abstract String getMainClass();
    public abstract String getName();
    public abstract String getVersion();
    public abstract String getAuthor();
    public abstract String getDescription();

    public File getConfigPath() { return new File(FileSystem.getInstance().getRoot().getAbsolutePath() + "\\Plugins\\" + getMainClass().substring(getMainClass().lastIndexOf('.'))); }

    public static File getPluginsFolder() {
        File f = new File(FileSystem.getInstance().getRoot().getAbsolutePath() + "\\Plugins");
        if(!f.exists() && f.mkdirs()) log.debug("Created root plugins folder.");
        return f;
    }

}
