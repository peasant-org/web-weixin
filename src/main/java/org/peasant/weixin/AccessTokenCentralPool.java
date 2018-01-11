/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.peasant.net.HTTP;

/**
 *
 * @author raymond
 */
@Singleton
public class AccessTokenCentralPool {

    public static final String WEIXIN_API_URL = "https://api.weixin.qq.com/cgi-bin";
    public static final String ACCESSTOKEN_API_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String CHARSET = "UTF-8";
    public static final String ACCESSTOKEN_PARAM = "grant_type=client_credential";
    public static final String ACCESSTOKEN_KEY = "access_token";
    public static final String ACCESSTOKEN_EXPIRES_KEY = "expires_in";

    long refreshInterval = 7100;
    private static final Timer t = new Timer("AccessToke刷新定时器");

    static Map<String, String> cache = new HashMap<>();

    public static String getAccessToken(IWeixinMPConfig cfg) {
        String at = cache.get(cfg.getAppId());
        if (at == null) {
            Object[] rs = fecthAccessToken(cfg);
            if (null != rs) {
                cache.put(cfg.getAppId(), (String) rs[0]);
            }
            t.schedule(new AccessTokenRefresher(cfg), (long) rs[1] * 1000 - 5000, (long) rs[1] * 1000 - 5000);

        }

        return at;
    }

    /**
     * 向微信服务器获取AccessToken,此方法线程安全
     *
     * @param cfg
     * @return
     */
    protected static Object[] fecthAccessToken(IWeixinMPConfig cfg) {
        String url = ACCESSTOKEN_API_URL + "?" + ACCESSTOKEN_PARAM + "&appid=" + cfg.getAppId() + "&secret=" + cfg.getAppSecret();
        String result = HTTP.sendGet(url, null, CHARSET);
        if (null != result && !"".equals(result)) {
            JsonReader jr = Json.createReader(new StringReader(result));
            JsonObject jo = jr.readObject();
            if (jo.containsKey(ACCESSTOKEN_KEY)) {
                return new Object[]{jo.getString(ACCESSTOKEN_KEY), jo.getInt(ACCESSTOKEN_EXPIRES_KEY)};
            }
        }
        return null;
    }

    public static class AccessTokenRefresher extends TimerTask {

        private final IWeixinMPConfig cfg;

        public AccessTokenRefresher(IWeixinMPConfig cfg) {
            this.cfg = cfg;
        }

        @Override
        public void run() {
            Object[] rs = fecthAccessToken(cfg);
            if (null != rs) {
                cache.put(cfg.getAppId(), (String) rs[0]);
            }
        }

    }
}
