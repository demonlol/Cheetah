package sbs.baka.cheetah.util.encryption.io;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCBC256 implements IOEncryption {

    private static AESCBC256 instance;
    public static AESCBC256 getInstance() { return instance == null ? (instance = new AESCBC256()) : instance; }
    private AESCBC256() {}

    public boolean encrypt(String inputFilename, String outputFilename, byte[] key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        // generate random iv
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try (FileInputStream in = new FileInputStream(inputFilename);
             FileOutputStream out = new FileOutputStream(outputFilename);
             CipherOutputStream encryptedOutputStream = new CipherOutputStream(out, cipher);) {
            out.write(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] buffer = new byte[8096];
            int nread;
            while ((nread = in.read(buffer)) > 0) {
                encryptedOutputStream.write(buffer, 0, nread);
            }
            encryptedOutputStream.flush();
        }
        if (new File(outputFilename).exists()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public byte[] generateKey() {
        byte[] encryptionKey = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(encryptionKey);
//        System.out.println("encryption key in base64 encoding: " + base64Encoding(encryptionKey));
        return encryptionKey;
    }

    public boolean decrypt(String inputFilename, String outputFilename, byte[] key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] iv = new byte[16];
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try (FileInputStream in = new FileInputStream(inputFilename); CipherInputStream cipherInputStream = new CipherInputStream(in, cipher); FileOutputStream out = new FileOutputStream(outputFilename)) {
            byte[] buffer = new byte[8192];
            in.read(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            int nread;
            while ((nread = cipherInputStream.read(buffer)) > 0) {
                out.write(buffer, 0, nread);
            }
            out.flush();
        }
        if (new File(outputFilename).exists()) {
            return true;
        } else {
            return false;
        }
    }

    private String base64Encoding(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }
}