package sbs.baka.cheetah.canvas.cache;

import edu.ksu.canvas.model.*;
import edu.ksu.canvas.model.assignment.*;
import org.apache.commons.lang3.tuple.*;
import org.slf4j.*;
import sbs.baka.cheetah.canvas.*;
import sbs.baka.cheetah.canvas.wrapper.*;
import sbs.baka.cheetah.storage.filesys.FileSystem;
import sbs.baka.cheetah.util.*;
import sbs.baka.cheetah.util.parse.*;

import javax.management.modelmbean.*;
import java.io.*;
import java.io.File;
import java.lang.*;
import java.lang.Module;
import java.util.*;

public class CanvasCacher {

    private final Logger log = LoggerFactory.getLogger(CanvasCacher.class);

    private CanvasController api;

    private File cacheRoot;
    private File coursesRoot;

    public CanvasCacher(CanvasController canvasAPI) {
        this.api = canvasAPI;

        this.cacheRoot = new File(FileSystem.getInstance().getRoot().getAbsolutePath() + "\\" + "Canvas" + "\\" + "Cache");
        this.coursesRoot = new File(cacheRoot.getAbsolutePath() + "\\Courses\\");

        log.debug(!cacheRoot.exists() && cacheRoot.mkdirs() ? "Created cache root directory" : "Failed to create cache root dir.");
        log.debug(cacheRoot.exists() && coursesRoot.mkdirs() ? "Created courses root directory" : "Failed to create course root dir");
    }

    public <T extends BaseCanvasModel> List<T> getCachedCanvasObjects(Class<T> clazz) {
        File cacheDir = getCacheDirectoryFor(clazz);
        if(cacheDir != null && cacheDir.exists()) {
            File[] cacheFiles = cacheDir.listFiles((dir, name) -> name.endsWith("cobj"));
            if(cacheFiles != null) {
                List<T> cachedFiles = new ArrayList<>();

                for (File cacheFile : cacheFiles) {
                    Object deserialized = SerializeUtil.deserializeObject(cacheFile.getAbsolutePath());
                    if(deserialized != null && deserialized.getClass().isAssignableFrom(clazz)) {
                        T deserializedCanvasObj = (T) SerializeUtil.deserializeObject(cacheFile.getAbsolutePath());
                        cachedFiles.add(deserializedCanvasObj);
                    }
                }
                return cachedFiles;
            }
        }
        return Collections.emptyList();
    }

    //Returns the number of objects cached
    public <T extends BaseCanvasModel> ImmutablePair<Integer /*items cached size*/, Integer /*created cache size*/> cachedCanvasObjects(Class<T> clazz, List<T> data) {
        int itemsCached = 0, oldCacheDeleted = 0, bytesCleared = 0, bytesCached = 0;

        File cacheDir = getCacheDirectoryFor(clazz);
        File[] oldCacheFiles = cacheDir.listFiles();
        if(oldCacheFiles != null) {
            for (File oldCacheFile : oldCacheFiles) {
                if(oldCacheFile.delete()) {
                    bytesCleared += oldCacheFile.length();
                    oldCacheDeleted++;
                }
            }
            log.debug("Clearing cache for " + clazz.getSimpleName() + ". Deleted " + oldCacheDeleted + " files (" + (bytesCleared / 1024.00) + "kb).");
        }

        for(T obj : data) {
            String objChecksum = FileUtil.getChecksum((Serializable) obj);

            File cacheFile = new File(cacheDir.getAbsolutePath() + "\\" + objChecksum + ".cobj");

            //Cache never changed. No need to write...
            if(cacheFile.exists()) {
                continue;
            } else {
                try {
                    if(!cacheFile.createNewFile()) {
                        throw new IOException("Could not create cache file");
                    } else {
                        SerializeUtil.serializeObject(obj, cacheFile.getAbsolutePath());
                        itemsCached++;
                        bytesCached += cacheFile.length();
                    }
                } catch (IOException ex) { log.error("Failed to create a new cache file for an object"); }
            }
        }
        return new ImmutablePair<>(itemsCached, bytesCached);
    }

    private <T extends BaseCanvasModel> File getCacheDirectoryFor(Class<T> clazz) {
        String name = clazz.getSimpleName();
        if(!name.toLowerCase(Locale.ROOT).endsWith("s")) name = name + "s";

        File dir = new File(this.cacheRoot.getAbsolutePath() + "\\" + name);
        if(!dir.exists() && dir.mkdirs()) log.debug("Created subdirectory for canvas class " + clazz.getSimpleName());

        return dir;
    }

}
