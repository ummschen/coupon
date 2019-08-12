package com.coupon.api.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.*;

public class MD5Util {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h","i", "j", "k", "L", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "i",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    /**
     * 生成8位数的uuid
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 生成含有随机盐的秘钥
     */
    public static Map<String,String> encryption(String coupon) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        coupon = md5Hex(coupon + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = coupon.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = coupon.charAt(i / 3 * 2 + 1);
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("salt",salt);
        map.put("encryption",new String(cs));
        return map;
    }


    /**
     * 校验秘钥是否正确
     */
    public static boolean verify(String coupon, String encryption) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = encryption.charAt(i);
            cs1[i / 3 * 2 + 1] = encryption.charAt(i + 2);
            cs2[i / 3] = encryption.charAt(i + 1);
        }
        String salt = new String(cs2);
        System.out.println("salt======" + salt);
        return md5Hex(coupon + salt).equals(new String(cs1));
    }

    /**
     * 校验券码秘钥是否正确
     */
    public static boolean verify(String coupon, String salt, String encryption) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = encryption.charAt(i);
            cs1[i / 3 * 2 + 1] = encryption.charAt(i + 2);
        }
        return md5Hex(coupon + salt).equals(new String(cs1));
    }


    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }



  /*  public static void main(String[] args) {
        Map<String,String> password = encryption("123456");
        System.out.println(password);

        System.out.println(verify("123456", password.get("encryption")));
        System.out.println(verify("123456", password.get("salt"), password.get("encryption")));
    }*/



}