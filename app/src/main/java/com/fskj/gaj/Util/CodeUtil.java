package com.fskj.gaj.Util;


import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Code 工具
 *
 * @author Administrator
 */
public class CodeUtil {

    public static String md5(String str) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(str.getBytes());
            byte[] arrayOfByte = localMessageDigest.digest();
            StringBuffer localStringBuffer = new StringBuffer();
            for (int i = 0; i < arrayOfByte.length; i++) {
                int j = 0xFF & arrayOfByte[i];
                if (j < 16)
                    localStringBuffer.append("0");
                localStringBuffer.append(Integer.toHexString(j));
            }
            return localStringBuffer.toString();
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
            return "" + System.currentTimeMillis();
        }
    }


    //*********************** String 与 Hex 之间的转换 ，支持中文
    /*
  * 16进制数字字符集
  */
    private static String hexString = "0123456789ABCDEF";

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return "0000"+sb.toString();
    }

    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) {
        String hex=bytes;
        while(hex.length()%4>0){
            hex="0"+hex;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                hex.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < hex.length(); i += 2)
            baos.write((hexString.indexOf(hex.charAt(i)) << 4 | hexString
                    .indexOf(hex.charAt(i + 1))));
        return new String(baos.toByteArray()).trim();
    }
    /**
     * 获得一个uuid
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }
}
