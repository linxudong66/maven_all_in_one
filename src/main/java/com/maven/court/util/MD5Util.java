package com.maven.court.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * ��������ַ���ִ��MD5����
     * @param source
     * @return
     */
    public static String encode(String source) {

        // 1.�ж������ַ����Ƿ���Ч
        if (source == null || "".equals(source)) {
            throw new RuntimeException("���ڼ��ܵ����Ĳ���Ϊ��");
        }

        // 2.�����㷨����
        String algorithm = "md5";

        // 3.��ȡMessageDigest����
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 4.��ȡ�����ַ�����Ӧ���ֽ�����
        byte[] input = source.getBytes();

        // 5.ִ�м���
        byte[] output = messageDigest.digest(input);

        // 6.����BigInteger����
        int signum = 1;
        BigInteger bigInteger = new BigInteger(signum, output);

        // 7.����16���ƽ�bigInteger��ֵת��Ϊ�ַ���
        int radix = 16;
        String encoded = bigInteger.toString(radix).toUpperCase();

        return encoded;
    }

}