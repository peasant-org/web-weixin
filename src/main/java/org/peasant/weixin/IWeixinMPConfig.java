/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

/**
 *
 * @author raymond
 */
public interface IWeixinMPConfig {

    /**
     * 消息加解密方式
     */
    public static enum MsgEncodingType {

        /**
         * 明文
         */
        CLEAR,
        /**
         * 兼容模式，明文与密文共存
         */
        CLEAR_CIPHER,
        /**
         * 密文
         */
        CIPHER;

        public static MsgEncodingType getMsgEncodingType(String t) {
            switch (t) {
                case "clear":
                    return CLEAR;
                case "both":
                    return CLEAR_CIPHER;
                case "cipher":
                    return CIPHER;
            }
            return CLEAR;
        }
    }

    /**
     *
     * @return 微信公众号的微信号
     */
    String getId();

    /**
     *
     * @return 公众号开发者AppId
     */
    String getAppId();

    /**
     *
     * @return 公众号开发者AppSecret
     */
    String getAppSecret();

    /**
     *
     * @return 消息加解密密钥
     */
    String getEncodingAESKey();

    /**
     *
     * @return 令牌
     */
    String getToken();

    /**
     *
     * @return 消息加解密方式
     */
    MsgEncodingType getEncodingType();
    
        /**
     *
     * @return 消息以JSON Object定义的菜单
     */
    String getMenuJSON();

}
