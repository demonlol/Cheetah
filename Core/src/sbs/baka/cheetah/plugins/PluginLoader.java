package sbs.baka.cheetah.plugins;

import org.slf4j.*;
import sbs.baka.cheetah.plugins.err.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class PluginLoader {

    private static final Logger log = LoggerFactory.getLogger(PluginLoader.class);

    private static List<CheetahPlugin> PLUGINS = null;

    public static void Load() throws PluginLoadException {
        PLUGINS = new LinkedList<>();

        File pluginsDir = CheetahPlugin.getPluginsFolder();
        if(pluginsDir.exists() && pluginsDir.isDirectory()) {

            File[] files = pluginsDir.listFiles();
            if(files != null) {
                for (File file : pluginsDir.listFiles()) {
                    if (!file.getName().endsWith("jar")) continue;

                    //Load class/jar files
                    URLClassLoader cl;
                    try {
                        cl = new URLClassLoader(new URL[]{file.toURI().toURL()}, PluginLoader.class.getClassLoader());
                    } catch (MalformedURLException e) {
                        throw new PluginLoadException("Failed to load plugin at '" + file.getAbsolutePath() + "'\n\n" + e.getMessage() + ".");
                    }

                    //Find class path in plugin.yml
                    String classpath = null;
                    File metadata = new File(file.getAbsolutePath() + "\\plugin.yml");
                    if (!metadata.exists())
                        throw new PluginLoadException("Failed to locate metadata for plugin " + file.getName() + ".");
                    else {
                        try {
                            classpath = Files.readAllLines(Paths.get(metadata.toURI())).get(0);
                            if (classpath == null || classpath.trim().isEmpty())
                                throw new PluginLoadException("Failed to load plugin " + file.getName() + "'s metadata.");
                        } catch (IOException e) {
                            throw new PluginLoadException("An unexpected I/O error occurred while loading plugin metadata from plugin " + file.getName() + "...");
                        }
                    }

                    //Load plugin class.
                    Class loadedClass = null;
                    try {
                        loadedClass = cl.loadClass(classpath);
                    } catch (ClassNotFoundException e) {
                        throw new PluginLoadException("Failed to locate plugin at classpath: '" + file.getAbsolutePath() + "'\n\n" + e.getMessage() + ".");
                    }

                    //Instantiate plugin
                    CheetahPlugin pluginInstance;
                    try {
                        pluginInstance = (CheetahPlugin) loadedClass.newInstance();
                    } catch (InstantiationException e) {
                        throw new PluginLoadException("Failed to instantiate plugin " + file.getName() + "..." + e.getMessage() + ".");
                    } catch (IllegalAccessException e) {
                        throw new PluginLoadException("Illegal access to plugin " + file.getName() + "'s main class..." + e.getMessage() + ".");
                    }

                    PLUGINS.add(pluginInstance);
                    log.debug(" - Loaded plugin " + file.getName() + ".");
                }
            } else {
                log.error("Plugin folder's listFiles() are null...");
            }
        } else {
            log.error("Failed to access plugins folder.");
        }
    }

    public static void Reload() throws PluginLoadException {

    }

}
