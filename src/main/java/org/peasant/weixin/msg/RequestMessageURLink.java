/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 链接消息 Title 消息标题 Description 消息描述 Url 消息链接
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageURLink extends RequestMessageBase {

    public static final String TYPE = LINK;

    private String title;

    private String description;

    private String url;

    public RequestMessageURLink() {
    }

    public RequestMessageURLink(String title, String description, String url, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, toUserName, fromUserName, createTime, msgId);
        this.title = title;
        this.description = description;
        this.url = url;
    }

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

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
