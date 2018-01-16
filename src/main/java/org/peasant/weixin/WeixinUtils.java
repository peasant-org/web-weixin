/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.peasant.util.Utils;
import org.peasant.util.web.HttpUtils;
import org.peasant.util.web.ContentTypes;
import org.peasant.weixin.msg.RequestMessageBase;
import org.peasant.weixin.msg.RequestMessageImage;
import org.peasant.weixin.msg.RequestMessageLocation;
import org.peasant.weixin.msg.RequestMessageShortVideo;
import org.peasant.weixin.msg.RequestMessageText;
import org.peasant.weixin.msg.RequestMessageURLink;
import org.peasant.weixin.msg.RequestMessageVideo;
import org.peasant.weixin.msg.event.RequestEventMsgBase;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author raymond
 */
public class WeixinUtils {

    public static final String WEIXIN_API_URL = "https://api.weixin.qq.com/cgi-bin";
    public static final String ACCESSTOKEN_API_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String CHARSET = "UTF-8";
    public static final String MENU_CREATE_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    public static final String MENU_GET_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    public static final String MENU_DELETE_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";

    public static String getTokenByAppId(String appId) {
        return WeixinMPconfig.getMPconfigByAppId(appId).getToken();
    }

    public static String getToken(String id) {
        return WeixinMPconfig.getMPconfig(id).getToken();
    }

    public static String getAccessTokenByAppID(String appId) {
        return AccessTokenCentralPool.getAccessToken(WeixinMPconfig.getMPconfigByAppId(appId));
    }

    public static String getAccessToken(String mpId) {
        return AccessTokenCentralPool.getAccessToken(WeixinMPconfig.getMPconfig(mpId));
    }

    public static String getMenuFor(String mpId) {
        return HttpUtils.sendGet(MENU_CREATE_URL_PREFIX + getAccessToken(mpId), null, CHARSET);
    }

    public static String getMenuForAppId(String appId) {
        return HttpUtils.sendGet(MENU_CREATE_URL_PREFIX + getAccessTokenByAppID(appId), null, CHARSET);
    }

    public static String deleteMenuFor(String mpId) {
        return HttpUtils.sendGet(MENU_DELETE_URL_PREFIX + getAccessToken(mpId), null, CHARSET);
    }

    public static boolean createMenuFor(String mpId) {
        return createMenu(WeixinMPconfig.getMPconfig(mpId).getAppId(), WeixinMPconfig.getMPconfig(mpId).getMenuJSON());

    }

    public static boolean createMenu(String appId) {

        return createMenu(appId, WeixinMPconfig.getMPconfigByAppId(appId).getMenuJSON());
    }

    public static boolean createMenu(String appId, String jsonmenu) {
        Map<String, String> ps = new HashMap<>();
        ps.put("Content-Type", ContentTypes.JSON);

        String r = "";
        try {
            r = HttpUtils.sendPost3(MENU_CREATE_URL_PREFIX + getAccessTokenByAppID(appId), null, new ByteArrayInputStream(jsonmenu.getBytes(CHARSET)), null, CHARSET);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeixinUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonObject j = convertStr2jsonObject(r);
        Logger.getLogger(WeixinUtils.class.getName()).log(Level.INFO, "为APPID:{0}创建菜单, 执行结果:{1}", new Object[]{appId, j.toString()});
        return 0 == j.getInt("errcode");
    }

    public static RequestMessageBase parseMsgXml(String xml) {
        RequestMessageBase rmb = Utils.xml2Bean(RequestMessageBase.class, xml);
        if (null != rmb) {
            switch (rmb.getMsgType()) {
                case RequestMessageBase.EVENT:
                    rmb = Utils.xml2Bean(RequestEventMsgBase.class, xml);
                    break;
                case RequestMessageBase.IMAGE:
                    rmb = Utils.xml2Bean(RequestMessageImage.class, xml);
                    break;
                case RequestMessageBase.LINK:
                    rmb = Utils.xml2Bean(RequestMessageURLink.class, xml);
                    break;
                case RequestMessageBase.LOCATION:
                    rmb = Utils.xml2Bean(RequestMessageLocation.class, xml);
                    break;
                case RequestMessageBase.SHORTVIDEO:
                    rmb = Utils.xml2Bean(RequestMessageShortVideo.class, xml);
                    break;
                case RequestMessageBase.VIDEO:
                    rmb = Utils.xml2Bean(RequestMessageVideo.class, xml);
                    break;
                case RequestMessageBase.TEXT:
                    return (RequestMessageText) Utils.xml2Bean(RequestMessageText.class, xml);

                case RequestMessageBase.VOICE:
                    rmb = Utils.xml2Bean(RequestMessageVideo.class, xml);
                    break;

            }
        }
        return rmb;

    }

    /**
     *
     * @param <T>
     * @param reqmsg
     * @return
     */
    public static <T extends RequestMessageBase> String handleReqMsg(T reqmsg) {
        if (null == reqmsg) {
            return "";
        }
        switch (reqmsg.getMsgType()) {
            case RequestMessageBase.EVENT:

                break;
            default:
                String f = reqmsg.getToUserName();
                reqmsg.setToUserName(reqmsg.getFromUserName());
                reqmsg.setFromUserName(f);
                return Utils.bean2xml(reqmsg);
//            case RequestMessageBase.IMAGE:
//
//                break;
//            case RequestMessageBase.LINK:
//
//                break;
//            case RequestMessageBase.LOCATION:
//
//                break;
//            case RequestMessageBase.SHORTVIDEO:
//
//                break;
//            case RequestMessageBase.VIDEO:
//
//                break;
//            case RequestMessageBase.TEXT:
//
//                break;
//            case RequestMessageBase.VOICE:
//
//                break;

        }

        return "";
    }

    /**
     *
     * @param xmlMsg
     * @return
     */
    public static String handleReqMsgXML(String xmlMsg) {
        return handleReqMsg(parseMsgXml(xmlMsg));
    }

    public static JsonObject convertStr2jsonObject(String str) {
        if (null == str) {
            return null;
        }
        JsonObject jo;
        try (JsonReader jr = Json.createReader(new StringReader(str))) {
            jo = jr.readObject();
            jr.close();
        }
        return jo;

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
