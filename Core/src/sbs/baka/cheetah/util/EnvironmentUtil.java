package sbs.baka.cheetah.util;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

public class EnvironmentUtil {

    public static File getJarFilePath() {
        try {
            CodeSource codeSource = EnvironmentUtil.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            return new File(jarFile.getParentFile().getPath());
        } catch (URISyntaxException e) {}
        return null;
    }

}
