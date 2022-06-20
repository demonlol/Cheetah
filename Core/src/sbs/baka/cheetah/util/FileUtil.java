package sbs.baka.cheetah.util;

import org.apache.commons.codec.digest.*;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import java.util.zip.*;

public class FileUtil {

    private static String getChecksum(File file) throws IOException {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); return null; }

        FileInputStream fis = new FileInputStream(file);

        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = fis.read(byteArray)) != -1) digest.update(byteArray, 0, bytesCount);

        fis.close();

        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));

        return sb.toString();
    }

    public static String getChecksum(Serializable object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(baos.toByteArray());
            StringBuilder hexBuilder = new StringBuilder();
            for (byte aByte : thedigest) hexBuilder.append(String.format("%02x", aByte));
            oos.close();
            baos.close();
            return hexBuilder.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getChecksum(String directoryPath, boolean includeHiddenFiles) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) throw new IllegalArgumentException("Not a directory");
        Vector<FileInputStream> fileStreams = new Vector<>();
        collectFiles(directory, fileStreams, includeHiddenFiles);
        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(fileStreams.elements())) {
            return DigestUtils.md5Hex(sequenceInputStream);
        }
    }

    private static void collectFiles(File directory, List<FileInputStream> fileInputStreams, boolean includeHiddenFiles) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.sort(files, Comparator.comparing(File::getName));
            for (File file : files) {
                if (includeHiddenFiles || !Files.isHidden(file.toPath())) {
                    if (file.isDirectory())
                        collectFiles(file, fileInputStreams, includeHiddenFiles);
                    else
                        fileInputStreams.add(new FileInputStream(file));
                }
            }
        }
    }

}
