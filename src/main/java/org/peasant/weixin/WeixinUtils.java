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
import java.nio.charset.StandardCharsets;
import java.time.Clock;
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
import org.peasant.weixin.msg.Article;
import static org.peasant.weixin.msg.MessageBase.*;
import org.peasant.weixin.msg.PictureTextMsg;
import org.peasant.weixin.msg.RequestMessageBase;
import org.peasant.weixin.msg.RequestMessageImage;
import org.peasant.weixin.msg.RequestMessageLocation;
import org.peasant.weixin.msg.RequestMessageShortVideo;
import org.peasant.weixin.msg.RequestMessageText;
import org.peasant.weixin.msg.RequestMessageURLink;
import org.peasant.weixin.msg.RequestMessageVideo;
import org.peasant.weixin.msg.event.RequestEventMsgBase;
import org.peasant.weixin.msg.event.RequestEventMsgClick;
import org.peasant.weixin.msg.event.RequestEventMsgReportLocation;
import org.peasant.weixin.msg.event.RequestEventMsgSubscribe;
import org.peasant.weixin.msg.event.RequestEventMsgUnSubscribe;
import org.peasant.weixin.msg.event.RequestEventMsgView;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author raymond
 */
public class WeixinUtils {

    public static final String WEIXIN_API_URL = "https://api.weixin.qq.com/cgi-bin";
    public static final String ACCESSTOKEN_API_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String CHARSET = StandardCharsets.UTF_8.name();
    public static final String MENU_CREATE_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    public static final String MENU_GET_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    public static final String MENU_DELETE_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
    public static boolean debug = true;

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

    public static String getMenuFromMPserver4MPid(String mpId) {
        return HttpUtils.sendGet(MENU_CREATE_URL_PREFIX + getAccessToken(mpId), null, CHARSET);
    }

    public static String getMenuFromMPserver4AppId(String appId) {
        return HttpUtils.sendGet(MENU_CREATE_URL_PREFIX + getAccessTokenByAppID(appId), null, CHARSET);
    }

    public static String deleteMenuInMPserver4MPid(String mpId) {
        return HttpUtils.sendGet(MENU_DELETE_URL_PREFIX + getAccessToken(mpId), null, CHARSET);
    }

    public static boolean createMenuInMPserver4For(String mpId) {
        return createMenuInMPserver(WeixinMPconfig.getMPconfig(mpId).getAppId(), WeixinMPconfig.getMPconfig(mpId).getMenuJSON());

    }

    public static boolean createMenuInMPserver4AppId(String appId) {

        return createMenuInMPserver(appId, WeixinMPconfig.getMPconfigByAppId(appId).getMenuJSON());
    }

    public static boolean createMenuInMPserver(String appId, String jsonmenu) {
        Map<String, String> ps = new HashMap<>();
        ps.put("Content-Type", ContentTypes.JSON);

        String r = "";
        try {
            r = HttpUtils.sendPost3(MENU_CREATE_URL_PREFIX + getAccessTokenByAppID(appId), null, new ByteArrayInputStream(jsonmenu.getBytes(CHARSET)), null, CHARSET);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeixinUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonObject j = convertJsonStr2jsonObject(r);
        Logger.getLogger(WeixinUtils.class.getName()).log(Level.INFO, "为APPID:{0}创建菜单, 执行结果:{1}", new Object[]{appId, j.toString()});
        return 0 == j.getInt("errcode");
    }

    public static <T extends RequestMessageBase> T parseMsgXml(String xml) {
        RequestMessageBase rmb = Utils.xml2Bean(RequestMessageText.class, xml);
        if (null != rmb) {
            switch (rmb.getMsgType()) {
                case RequestMessageBase.EVENT:
                    RequestEventMsgBase rem = Utils.xml2Bean(RequestEventMsgClick.class, xml);
                    switch (rem.getEventType()) {
                        case RequestEventMsgBase.EVENT_CLICK:
                            rem = Utils.xml2Bean(RequestEventMsgClick.class, xml);
                            break;
                        case RequestEventMsgBase.EVENT_LOCATION:
                            rem = Utils.xml2Bean(RequestEventMsgReportLocation.class, xml);
                            break;
                        case RequestEventMsgBase.EVENT_SUBSCRIBE:
                            rem = Utils.xml2Bean(RequestEventMsgSubscribe.class, xml);
                            break;
                        case RequestEventMsgBase.EVENT_UNSUBSCRIBE:
                            rem = Utils.xml2Bean(RequestEventMsgUnSubscribe.class, xml);
                            break;
                        case RequestEventMsgBase.EVENT_VIEW:
                            rem = Utils.xml2Bean(RequestEventMsgView.class, xml);
                            break;
                        default:
                            rmb = rem;
                    }
                    rmb = rem;
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
                    rmb = Utils.xml2Bean(RequestMessageText.class, xml);
                    break;

                case RequestMessageBase.VOICE:
                    rmb = Utils.xml2Bean(RequestMessageVideo.class, xml);
                    break;

            }
        }
        return (T) rmb;

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
        String r = "";
        switch (reqmsg.getMsgType()) {
            case RequestMessageBase.EVENT:
                RequestEventMsgBase rem = (RequestEventMsgBase) reqmsg;
                switch (rem.getEventType()) {
                    case RequestEventMsgBase.EVENT_CLICK:
                        PictureTextMsg ptm = new PictureTextMsg();
                        ptm.setCreateTime(Clock.systemUTC().millis())
                                .setFromUserName(reqmsg.getToUserName())
                                .setToUserName(reqmsg.getFromUserName())
                                .setMsgType(RESPONSE_MSG_NEWS);
                        ptm.articles.add(new Article("寄快递", "http://dawnrise.cn/express/img/newexpress.jpg", "http://dawnrise.cn/express/delivernew.jsp?customer="+ptm.getToUserName(), "点击进入面对面寄件，下订单"));
                        r = Utils.bean2xml(ptm);
                        break;
                }
                break;
            case RequestMessageBase.IMAGE:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));
                break;
            case RequestMessageBase.LINK:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));

                break;
            case RequestMessageBase.LOCATION:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));

                break;
            case RequestMessageBase.SHORTVIDEO:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));

                break;
            case RequestMessageBase.VIDEO:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));

                break;
            case RequestMessageBase.TEXT:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));

                break;
            case RequestMessageBase.VOICE:
                r = Utils.bean2xml(new RequestMessageText(reqmsg.getMsgType(), reqmsg.getFromUserName(), reqmsg.getToUserName(), System.currentTimeMillis(), 0));

                break;

        }

        return r;
    }

    /**
     *
     * @param xmlMsg
     * @return
     */
    public static String handleReqMsgXML(String xmlMsg) {
        return handleReqMsg(parseMsgXml(xmlMsg));
    }

    public static JsonObject convertJsonStr2jsonObject(String str) {
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
