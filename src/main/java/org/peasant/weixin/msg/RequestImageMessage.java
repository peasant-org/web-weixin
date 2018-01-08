/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 * <p>
 * 图片消息</p>
 *
 * @author raymond
 */
public class RequestImageMessage extends RequestMessageMediaBase {

    public RequestImageMessage(String url, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(mediaID, toUserName, fromUserName, createTime, msgId);
        this.url = url;
    }

    public static final String TYPE = "image";
    private String url;

    /**
     * Get the value of url
     *
     * @return the value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the value of url
     *
     * @param url new value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
