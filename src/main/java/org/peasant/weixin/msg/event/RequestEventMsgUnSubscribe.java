/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestEventMsgUnSubscribe extends RequestEventMsgBase {

    public static final String EVENT_TYPE = "unsubscribe";

    public RequestEventMsgUnSubscribe(String toUserName, String fromUserName, long createTime, long msgId) {
        super(RequestEventMsgBase.EVENT_UNSUBSCRIBE, toUserName, fromUserName, createTime, msgId);
    }
}
