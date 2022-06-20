package sbs.baka.cheetah.util.encryption;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface KeyEncryption {

    String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException;
    String encrypt(String dataToEncrypt, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException;
    SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength)  throws NoSuchAlgorithmException, InvalidKeySpecException;

}
