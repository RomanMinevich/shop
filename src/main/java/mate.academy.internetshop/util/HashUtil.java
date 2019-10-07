package mate.academy.internetshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;

public class HashUtil {
    private static final int SALT_LENGTH = 16;
    private static final Logger log = Logger.getLogger(HashUtil.class);

    public static byte[] createSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public static String hashPassword(byte[] salt, String password) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);
            for (Byte b : digest.digest(password.getBytes())) {
                hash.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException exception) {
            log.error("Couldn't hash password");
        }
        return hash.toString();
    }
}
