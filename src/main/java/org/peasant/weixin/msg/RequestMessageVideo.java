/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * 视频消息</p>
 * ThumbMediaId 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageVideo extends RequestMessageMediaBase {

    public static final String TYPE = VIDEO;

    private String thumbMediaId;

    public RequestMessageVideo() {
    }

    public RequestMessageVideo(String thumbMediaId, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, mediaID, toUserName, fromUserName, createTime, msgId);
        this.thumbMediaId = thumbMediaId;
    }

    /**
     * Get the value of thumbMediaId
     *
     * @return the value of thumbMediaId
     */
    @XmlElement(name = "ThumbMediaId")
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
