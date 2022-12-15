package com.bdp.idmapping.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 15:42
 * @Description: com.bdp.idmapping.utils
 * @version: 1.0
 */
public class DESTools {
    private static final Logger logger = LoggerFactory.getLogger(DESTools.class);
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int KEY_LENGTH = 24;
    private static final String ALGORITHM_NAME = "DESede";

    public DESTools() {

    }

    public static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKey deskey = new SecretKeySpec(key, "DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(1, deskey);
        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte[] key, byte[] src) throws Exception {
        SecretKey deskey = new SecretKeySpec(key, "DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(2, deskey);
        return cipher.doFinal(src);
    }

    public static byte[] getKey(String keyStr, String charsetName) throws Exception {
        if (StringUtils.isBlank(charsetName)) {
            charsetName = "UTF-8";
        }
        byte[] key = keyStr.getBytes(charsetName);
        return getBytesByLength(key, 24, (byte) 48);
    }

    private static byte[] getBytesByLength(byte[] key, int length, byte defaultByte) {
        if (key.length == length) {
            return key;
        }
        byte[] result = new byte[length];
        int defaultCount;
        if (key.length > length) {
            for (defaultCount = 0; defaultCount < length; ++defaultCount) {
                result[defaultCount] = key[defaultCount];
            }
        } else {
            defaultCount = length - key.length;
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

    public static String encryptStr(String key, String content, String charsetName) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (StringUtils.isBlank(charsetName)) {
            charsetName = "UTF-8";
        }
        try {
            byte[] result = encrypt(getKey(key, charsetName), content.getBytes(charsetName));
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            StringBuilder error = new StringBuilder();
            error.append("key:").append(key).append(",").append("content:").append(content).append(",").append("charsetName").append(charsetName);
            logger.warn("加密失败," + error.toString(), e);
            return "";
        }
    }

    public static String decryptStr(String key, String content, String charsetName) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (StringUtils.isBlank(charsetName)) {
            charsetName = "UTF-8";
        }
        try {
            byte[] result = decrypt(getKey(key, charsetName), Base64.decodeBase64(content.getBytes(charsetName)));
            return new String(result, charsetName);
        } catch (Exception e) {
            StringBuilder error = new StringBuilder();
            error.append("key:").append(key).append(",").append("content:").append(content).append(",").append("charsetName").append(charsetName);
            logger.warn("解密失败," + error.toString(), e);
            return "";
        }
    }
}
