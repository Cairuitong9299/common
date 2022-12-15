package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESTools {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final int KEY_LENGTH = 24;

    private static final String ALGORITHM_NAME = "DESede";


    public static String encryptStr(String key, String content, String charsetName) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (StringUtils.isBlank(charsetName)) {
            charsetName = DEFAULT_CHARSET;
        }
        byte[] result;
        try {
            result = encrypt(getKey(key, charsetName), content.getBytes(charsetName));
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(src);
    }

    public static byte[] getKey(String keyStr, String charsetName) throws Exception {
        if (StringUtils.isBlank(charsetName)) {
            charsetName = DEFAULT_CHARSET;
        }
        byte[] key = keyStr.getBytes(charsetName);
        return getBytesByLength(key, KEY_LENGTH, (byte) '0');
    }

    private static byte[] getBytesByLength(byte[] key, int length, byte defaultByte) {
        if (key.length == length) {
            return key;
        } else {
            byte[] result = new byte[length];
            if (key.length > length) {
                for (int i = 0; i < length; i++) {
                    result[i] = key[i];
                }
            } else {
                int defaultCount = length - key.length;
                for (int i = 0; i < length; i++) {
                    if (i < defaultCount) {
                        result[i] = defaultByte;
                    } else {
                        result[i] = key[i - defaultCount];
                    }
                }
            }
            return result;
        }

    }
}
