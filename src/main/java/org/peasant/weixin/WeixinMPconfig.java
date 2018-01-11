/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * 实现从/mp_config.properties读取公众号开发者参数的配置信息
 *
 * @author raymond
 */
public class WeixinMPconfig implements IWeixinMPConfig {

    public static final String APPID_KEY = "AppID";
    public static final String APPSECRET_KEY = "AppSecret";
    public static final String TOKEN_KEY = "Token";
    public static final String ENCODINGAESKEY_KEY = "EncodingAESKey";
    public static final String ENCODINGTYPE_KEY = "MsgEncodingType";
    public static final String MENU_KEY = "Menu";
    private static Properties configs;
    private static HashMap<String, WeixinMPconfig> configmapByID = new HashMap();
    private static HashMap<String, WeixinMPconfig> configmapByAppId = new HashMap();

    /**
     * 实现从/mp_config.properties读取配置信息
     *
     * @param mpId 公众号的微信号
     * @return
     */
    public static IWeixinMPConfig getMPconfig(String mpId) {

        if (null == configs) {
            loadConfigFromPropertiesFile();
        }
        return configmapByID.get(mpId);

    }

    /**
     * 实现从/mp_config.properties读取配置信息
     */
    protected static void loadConfigFromPropertiesFile() {
        configs = new Properties();
        try {
            configs.load(WeixinMPconfig.class.getResourceAsStream("/mp_config.properties"));
            for (Entry<Object, Object> e : configs.entrySet()) {
                String k = (String) e.getKey();
                String v = (String) e.getValue();
                String[] ks = k.split("\\.");
                if (ks.length == 2) {
                    WeixinMPconfig cfg = configmapByID.getOrDefault(ks[0], new WeixinMPconfig());
                    switch (ks[1]) {
                        case APPID_KEY:
                            cfg.setAppID(v);
                            break;
                        case APPSECRET_KEY:
                            cfg.setAppSecret(v);
                            break;
                        case TOKEN_KEY:
                            cfg.setToken(v);
                            break;
                        case ENCODINGAESKEY_KEY:
                            cfg.setEncodingAESKey(v);
                            break;
                        case ENCODINGTYPE_KEY:
                            cfg.setEncodingType(MsgEncodingType.getMsgEncodingType(v));
                            break;
                        case MENU_KEY:
                            JsonObject jo;

                            try (JsonReader jr = Json.createReader(WeixinMPconfig.class.getResourceAsStream(v))) {
                                jo = jr.readObject();
                                jr.close();
                            }
                            if (null != jo) {
                                cfg.setMenuJSON(jo.toString());
                            }
                    }
                    configmapByID.put(ks[0], cfg);
                    if (cfg.getAppId() != null && !cfg.getAppId().equals("")) {
                        configmapByAppId.put(cfg.getAppId(), cfg);
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(WeixinMPconfig.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static IWeixinMPConfig getMPconfigByAppId(String appId) {
        if (null == configs) {
            loadConfigFromPropertiesFile();
        }
        return configmapByAppId.get(appId);
    }

    public WeixinMPconfig() {
    }

    public WeixinMPconfig(String appID, String appSecret, String token, String encodingAESKey, MsgEncodingType encodingType) {
        this.appID = appID;
        this.appSecret = appSecret;
        this.token = token;
        this.encodingAESKey = encodingAESKey;
        this.encodingType = encodingType;
    }
    private String id;
    private String appID;
    private String appSecret;

    private String token;
    private String encodingAESKey;
    private MsgEncodingType encodingType;

    private String menuJSON;

    @Override
    public String getMenuJSON() {
        return menuJSON;
    }

    public void setMenuJSON(String menuJSON) {
        this.menuJSON = menuJSON;
    }

    @Override
    public MsgEncodingType getEncodingType() {
        return encodingType;
    }

    public WeixinMPconfig setEncodingType(MsgEncodingType encodingType) {
        if (encodingType != null) {
            this.encodingType = encodingType;
        } else {
            this.encodingType = MsgEncodingType.CLEAR;
        }
        return this;
    }

    @Override
    public String getAppId() {
        return appID;
    }

    public WeixinMPconfig setAppID(String appID) {
        this.appID = appID;
        return this;
    }

    @Override
    public String getAppSecret() {
        return appSecret;
    }

    public WeixinMPconfig setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    @Override
    public String getToken() {
        return token;
    }

    public WeixinMPconfig setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public WeixinMPconfig setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    public WeixinMPconfig setID(String id) {
        this.id = id;
        return this;
    }

}
