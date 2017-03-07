package com.its.its.presenter.security;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by BiLac on 3/8/2017.
 */

public class EncryptDecrypt {
    private static MessageDigest md;
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            md = null;
        }
    }

    private static byte[] md5(String source) {
        byte[] bytes = source.getBytes();
        byte[] digest = md.digest(bytes);
        return digest;
    }

    public static String encrypt(String input, String key) {
        byte[] ivbytes = "sixteenbyteslong".getBytes();
        IvParameterSpec ips = new IvParameterSpec(ivbytes);
        byte[] keybytes = md5(key);
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(keybytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, ips);
            byte[] ptext = input.getBytes();
            crypted = cipher.doFinal(ptext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Hex.encodeHex(crypted));
    }

    public static String decrypt(String input, String key) {
        IvParameterSpec ips = new IvParameterSpec("sixteenbyteslong".getBytes());
        byte[] keybytes = md5(key);
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(keybytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey, ips);
            output = cipher.doFinal(Hex.decodeHex(input.toCharArray()));
        } catch (Exception e) {

        }
        return new String(output);
    }
}
