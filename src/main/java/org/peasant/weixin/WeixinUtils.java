/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

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

    public static boolean checkSignature(String token, HttpServletRequest req) {
        try {
            //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.此句必须删除，否则造成失败
            String signature = req.getParameter("signature");
            String timestamp = req.getParameter("timestamp");
            String nonce = req.getParameter("nonce");

            String cs;
            cs = MessageDiegetor.getSortedSHA1(token, timestamp, nonce);

            return signature.equals(cs);
        } catch (AesException ex) {
            Logger.getLogger(JoinWeixinMPServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
