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
public class RequestEventMsgClick extends RequestEventMsgBase {
    
    public RequestEventMsgClick(String eventType, String toUserName, String fromUserName, long createTime, long msgId) {
        super(eventType, toUserName, fromUserName, createTime, msgId);
    }
    
}
