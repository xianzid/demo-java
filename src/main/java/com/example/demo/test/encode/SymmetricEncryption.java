package com.example.demo.test.encode;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;

/**
 * 对称加密算法，使用AES算法
 */
public class SymmetricEncryption {
    private final String ALGORITHM="AES";
    private final String CHARSET="UTF-8";
    public byte[] encryption(String key){
        byte[] bytes = create128Bit(key);
//        Cipher.getInstance();
        return new byte[]{0};
    }

    private byte[] create128Bit(String key){
        byte[] bytes = new byte[0];
        if (null == key){
            return bytes;
        }

        try {
            bytes = key.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
