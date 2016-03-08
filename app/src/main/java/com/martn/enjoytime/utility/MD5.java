package com.martn.enjoytime.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title: Juyixia
 * Package: com.lefu.juyixia.utils
 * Description: ("MD5相关的操作")
 * Date 2015/7/24 15:58
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class MD5 {

    private static final String CHALLENGE_CODE = "~!@#$%^&*()_+";

    /**
     * md5单向加密
     * @param orgString
     * @return
     */
    public static String md5(String orgString) {
        StringBuilder stringBuilder;

        byte[] md5Word = null;
        try {
            md5Word = MessageDigest.getInstance("MD5").digest(orgString.getBytes("UTF-8"));
            stringBuilder = new StringBuilder(md5Word.length * 2);

            for (int i = 0; i < md5Word.length; i++) {
                int k = md5Word[i];
                if ((k & 0xFF) < 16)
                    stringBuilder.append("0");
                stringBuilder.append(Integer.toHexString(k & 0xFF));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        return stringBuilder.toString();
    }


    /**
     * 输入文字加密
     * @param orgWord
     * @return
     */
    public static String password(String orgWord) {
        return md5(CHALLENGE_CODE + orgWord).toUpperCase();
    }
}
