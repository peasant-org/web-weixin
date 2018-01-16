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
public class RequestEventMsgClick extends RequestEventMsgBase {

    public RequestEventMsgClick(String toUserName, String fromUserName, long createTime, long msgId) {
        super(RequestEventMsgBase.EVENT_CLICK, toUserName, fromUserName, createTime, msgId);
    }

}
