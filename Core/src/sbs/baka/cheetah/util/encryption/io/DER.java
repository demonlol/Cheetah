package sbs.baka.cheetah.util.encryption.io;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Deprecated
public class DER implements IOEncryption {

    public DER() {
//        randomKey = randomKey.substring(0, 16);
//        keyBytes = randomKey.getBytes();
//        key = new SecretKeySpec(keyBytes, "AES");
//        paramSpec = new IvParameterSpec(iv);
//        ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
//        in = new FileInputStream(srcFile);
//        out = new FileOutputStream(encryptedFile);
//        out = new CipherOutputStream(out, ecipher);
//        int numRead = 0;
//        while ((numRead = in.read(buf)) >= 0) {
//            out.write(buf, 0, numRead);
//        }
//        in.close();
//        out.flush();
//        out.close();
    }

    @Override
    public boolean decrypt(String inputFilename, String outputFilename, byte[] key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return false;
    }

    @Override
    public boolean encrypt(String inputFilename, String outputFilename, byte[] key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return false;
    }

    @Override
    public byte[] generateKey() {
        return new byte[0];
    }
}
