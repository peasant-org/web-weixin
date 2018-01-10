/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 * <p>
 * 多媒体消息基类 MediaId 。</p>
 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
 *
 * @author raymond
 */
public class RequestMessageMediaBase extends RequestMessageBase {

    private String mediaID;

    protected RequestMessageMediaBase(String type, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(type, toUserName, fromUserName, createTime, msgId);
        this.mediaID = mediaID;
    }

    /**
     * Get the value of mediaID
     *
     * @return the value of mediaID
     */
    public String getMediaID() {
        return mediaID;
    }

    /**
     * Set the value of mediaID
     *
     * @param mediaID new value of mediaID
     */
    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

}
