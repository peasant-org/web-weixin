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
import org.peasant.weixin.msg.RequestMessageBase;

/**
 *
 * @author raymond
 */
//@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public abstract class RequestEventMsgBase extends RequestMessageBase {

    public RequestEventMsgBase() {
    }

    public final static String EVENT_CLICK = "CLICK";
    public final static String EVENT_VIEW = "VIEW";
    public final static String EVENT_LOCATION = "LOCATION";
    public final static String EVENT_SUBSCRIBE = "subcribe";
    public final static String EVENT_UNSUBSCRIBE = "unsubcribe";

    public final static String TYPE = EVENT;

    private String eventType;

    protected RequestEventMsgBase(String eventType, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, toUserName, fromUserName, createTime, msgId);
        this.eventType = eventType;
    }

    /**
     * Get the value of eventType
     *
     * @return the value of eventType
     */
    @XmlElement(name="Event")
    public String getEventType() {
        return eventType;
    }

    /**
     * Set the value of eventType
     *
     * @param eventType new value of eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

}
