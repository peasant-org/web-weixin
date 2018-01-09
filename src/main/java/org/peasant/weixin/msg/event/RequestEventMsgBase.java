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
public class RequestEventMsgBase extends RequestMessageBase {

    public final static String TYPE = "event";

    private String eventType;

    public RequestEventMsgBase(String eventType, String toUserName, String fromUserName, long createTime, long msgId) {
        super(toUserName, fromUserName, createTime, msgId);
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
