package org.example.security;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AESUtilsTest {


    @Test
    public void testEncrypt()  {
        String key = "12345678901234567890123456789012";
        String plainText = "hello";
        Optional<String> encryptedText = AESUtil.encrypt(plainText, key);
        assertNotEquals(plainText, encryptedText.get());
        Optional<String> decryptText = AESUtil.decrypt(encryptedText.get(), key);
        assertEquals(plainText, decryptText.get());


    }

    @Test
    public void testInvalidKeyLength() {
        String invalidKey = "123456789"; // Key length is not 32 bytes
        String plainText = "hello";

        assertThrows(IllegalArgumentException.class, () -> AESUtil.encrypt(plainText, invalidKey));
        assertThrows(IllegalArgumentException.class, () -> AESUtil.decrypt("invalidEncryptedText", invalidKey));
    }


}
