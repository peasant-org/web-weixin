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
public class WeixinUtils {

    public static String getTokenByAppId(String appId) {
        return WeixinMPconfig.getMPconfigByAppId(appId).getToken();
    }

    public static String getToken(String id) {
        return WeixinMPconfig.getMPconfig(id).getToken();
    }

    public static String getAccessToken(String appId) {
        return AccessTokenCentralPool.getAccessToken(WeixinMPconfig.getMPconfigByAppId(appId));
    }

}
