/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 这是一个对消息计算哈希值的便捷类，提供采用各种算法对消息计算哈希值的便捷方法，如SHA1,MD5
 *
 * @author raymond
 */
public class MessageDiegetor {

    public static final String SHA1 = "SHA-1";
    public static final String MD5 = "MD5";

    /**
     * 用SHA1算法生成消息摘要，先用@param strs进行字典序排序
     *
     * @param strs
     * @return 安全签名
     * @throws AesException
     */
    public static String getSortedSHA1(String... strs) throws AesException {

        return getMessageDiest(true, SHA1, strs);
    }

    /**
     *
     * @param strs
     * @return
     * @throws AesException
     */
    public static String getSHA1(String... strs) throws AesException {
        return getMessageDiest(true, SHA1, strs);
    }

    /**
     *
     * @param sort 是否需要进行字典序排序
     * @param algorithm 算法
     * @param strs 
     * @return
     * @throws AesException
     */
    protected static String getMessageDiest(boolean sort, String algorithm, String... strs) throws AesException {
        try {
            StringBuilder sb = new StringBuilder();

            if (sort) {
                Arrays.sort(strs);
            }

            for (String str : strs) {
                sb.append(str);
            }

            String str = sb.toString();
            // 使用指定算法，计算哈希值
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());

            byte[] digest = md.digest();

            StringBuilder hexstr = new StringBuilder();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }

    }
}
