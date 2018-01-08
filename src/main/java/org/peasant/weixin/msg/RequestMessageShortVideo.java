/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 *<p>小视频消息</p>
 * @author raymond
 */
public class RequestMessageShortVideo extends RequestMessageVideo {

    public static final String TYPE = "shortvideo";

    public RequestMessageShortVideo(String thumbMediaId, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(thumbMediaId, mediaID, toUserName, fromUserName, createTime, msgId);
    }


 
}
