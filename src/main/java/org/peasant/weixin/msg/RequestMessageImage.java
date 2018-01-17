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
 * 图片消息</p>
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageImage extends RequestMessageMediaBase {

    public RequestMessageImage() {
    }

    public RequestMessageImage(String url, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, mediaID, toUserName, fromUserName, createTime, msgId);
        this.url = url;
    }

    public static final String TYPE = RequestMessageMediaBase.IMAGE;
    private String url;

    /**
     * Get the value of url
     *
     * @return the value of url
     */
    @XmlElement(name = "PicUrl")
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
