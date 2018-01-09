/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg.event;

/**
 *
 * @author raymond
 */
public class RequestEventMsgSubscribe extends RequestEventMsgBase {

    public static final String EVENT_TYPE = "subscribe";

    public RequestEventMsgSubscribe(String toUserName, String fromUserName, long createTime, long msgId) {
        super(toUserName, fromUserName, createTime, msgId);
    }

}
