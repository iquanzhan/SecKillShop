package com.chengxiaoxiao.seckillshop.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }


    private static final String salt = "1a2b3c4d";

    /**
     * 用户填写的Password转换为Form的Password
     *
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String src = "" + salt.charAt(0) + salt.charAt(5) + salt.charAt(1) + inputPass + salt.charAt(7);
        return md5(src);
    }

    /**
     * form的Password转换为DB的Password
     *
     * @param formPass
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String src = "" + salt.charAt(0) + salt.charAt(5) + salt.charAt(1) + formPass + salt.charAt(7);
        return md5(src);
    }

    /**
     * input的Password转换为DB的Pass
     *
     * @param inputPass
     * @return
     */
    public static String inputPassToDBPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        String pass = inputPassToDBPass("123456", "123456789");
        System.out.println(pass);
    }

}
