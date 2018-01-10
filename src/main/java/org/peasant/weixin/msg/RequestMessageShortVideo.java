/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 * <p>
 * 小视频消息</p>
 *
 * @author raymond
 */
public class RequestMessageShortVideo extends RequestMessageMediaBase {

    public static final String TYPE = RequestMessageBase.SHORTVIDEO;
    private String thumbMediaId;

    public RequestMessageShortVideo(String thumbMediaId, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, mediaID, toUserName, fromUserName, createTime, msgId);
    }

    /**
     * Get the value of thumbMediaId
     *
     * @return the value of thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * Set the value of thumbMediaId
     *
     * @param thumbMediaId new value of thumbMediaId
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
