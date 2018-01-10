/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg.event;

import org.peasant.weixin.msg.RequestMessageBase;

/**
 *
 * @author raymond
 */
public abstract class RequestEventMsgBase extends RequestMessageBase {

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
