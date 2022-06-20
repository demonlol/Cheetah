package sbs.baka.cheetah.util;

import org.slf4j.*;

import java.io.*;
import java.util.*;

/**
 * Created by Jan on 06/12/14.
 */
public class PropertiesReader {


    private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
    public static final String DEFAULT_VALUE = "";

    private Properties properties;
    private String fileName;

    /**
     * @param filePath - path to properties file
     */
    public PropertiesReader(String filePath) {
        this.fileName = filePath;
        try {
            properties = openPropertyFile(filePath);
        } catch (IOException e) {
            String message = "Could not open property file: " + filePath;
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    public String getProperty(String key) {
        String result = properties.getProperty(key);
        if (result == null) {
            logger.error("Could not find property " + key + " in file " + fileName);
        }
        return result;
    }

    private Properties openPropertyFile(String filePath) throws IOException {
        InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(filePath);
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }

    public Properties getProperties() {
        return new Properties(properties);
    }
}
