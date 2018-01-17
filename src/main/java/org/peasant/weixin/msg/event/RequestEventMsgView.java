/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 点击菜单跳转链接时的事件推送
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestEventMsgView extends RequestEventMsgBase {

    /**
     *
     * @param eventKey
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgId
     */
    public RequestEventMsgView(String eventKey, String toUserName, String fromUserName, long createTime, long msgId) {
        super(RequestEventMsgBase.EVENT_VIEW, toUserName, fromUserName, createTime, msgId);
        this.eventKey = eventKey;
    }

    public final static String EVENT_TYPE = "VIEW";

    private String eventKey;

    /**
     * Get the value of eventKey
     *
     * @return the value of eventKey
     */
    @XmlElement(name = "EventKey")
    public String getEventKey() {
        return eventKey;
    }

    /**
     * Set the value of eventKey
     *
     * @param eventKey new value of eventKey
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

}
