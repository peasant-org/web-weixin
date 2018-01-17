/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import javax.xml.bind.annotation.XmlElement;

/**
 * 所有消息的基类，包括推送的消息，回复用户（响应微信服务器推送）的消息
 *
 * @author raymond
 */
public class MessageBase {

    public static final String IMAGE = "image";
    public static final String TEXT = "text";
    public static final String SHORTVIDEO = "shortvideo";
    public static final String VIDEO = "video";
    public static final String LOCATION = "location";
    public static final String LINK = "link";
    public static final String VOICE = "voice";
    public static final String EVENT = "event";
    protected String toUserName;
    protected String fromUserName;
    protected long createTime;
    protected String msgType;

    public MessageBase() {
    }

    @XmlElement(name = "ToUserName")
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlElement(name = "FromUserName")
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @XmlElement(name = "CreateTime")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long CreateTime) {
        this.createTime = CreateTime;
    }

    @XmlElement(name = "MsgType")
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
