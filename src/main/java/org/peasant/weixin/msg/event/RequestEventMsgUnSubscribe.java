/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg.event;

/**
 * Latitude	地理位置纬度 Longitude	地理位置经度 Precision	地理位置精度
 *
 * @author raymond
 */
public class RequestEventMsgUnSubscribe extends RequestEventMsgBase {

    public static final String EVENT_TYPE = "unsubscribe";

    public RequestEventMsgUnSubscribe(String toUserName, String fromUserName, long createTime, long msgId) {
        super(toUserName, fromUserName, createTime, msgId);
    }
}
