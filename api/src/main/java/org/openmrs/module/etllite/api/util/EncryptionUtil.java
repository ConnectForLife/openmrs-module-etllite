package org.openmrs.module.etllite.api.util;

import org.openmrs.util.Security;

public class EncryptionUtil {

    /**
     * This method takes plain text as input and return the base64 encoded string
     *
     * @param text plain text to be encrypted, if text is null or empty then encrypt method will
     *             return null
     * @return base 64 encoded string
     */
    public String encryptAsString(String text) {
        return Security.encrypt(text);
    }

    /**
     * This method takes base 64 encoded string as input and return the decrypted text
     *
     * @param encryptedText base 64 encoded text
     * @return decrypted plain text, null if the text to be decrypted is null.
     */
    public String decryptAsString(String encryptedText) {
        return Security.decrypt(encryptedText);
    }

}
