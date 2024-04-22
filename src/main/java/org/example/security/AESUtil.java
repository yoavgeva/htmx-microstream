package org.example.security;

import io.quarkus.logging.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

public class AESUtil {

    public static Optional<String> encrypt(String plainText, String key) {
        SecretKeySpec secretKey = generateKey(key);

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes());
            return Optional.of(Base64.getEncoder().encodeToString(encryptedTextBytes));
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                 NoSuchPaddingException e) {
            Log.error("Error encrypting text", e);
            return Optional.empty();
        }

    }

    public static Optional<String> decrypt(String encryptedText, String key) {
        SecretKeySpec secretKey = generateKey(key);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedTextBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return Optional.of(new String(decryptedTextBytes));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException e) {
            Log.error("Error decrypting text", e);
            return Optional.empty();
        }


    }

    private static SecretKeySpec generateKey(String key) {
        byte[] keyBytes = key.getBytes();
        if (keyBytes.length != 32) {
            throw new IllegalArgumentException("Key length must be 32 bytes (256 bits) for AES 256-bit encryption.");
        }
        return new SecretKeySpec(keyBytes, "AES");
    }
}


