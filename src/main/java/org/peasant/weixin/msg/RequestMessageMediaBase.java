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
 * 多媒体消息基类 MediaId 。</p>
 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
 *
 * @author raymond
 */
//@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageMediaBase extends RequestMessageBase {

    private String mediaID;

    public RequestMessageMediaBase() {
    }

    protected RequestMessageMediaBase(String type, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(type, toUserName, fromUserName, createTime, msgId);
        this.mediaID = mediaID;
    }

    /**
     * Get the value of mediaID
     *
     * @return the value of mediaID
     */
    @XmlElement(name = "MediaId")
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
