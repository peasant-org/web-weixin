/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * 推送消息基类</p>
 * ToUserName开发者微信号 FromUserName 发送方帐号（一个OpenID） CreateTime 消息创建时间 （整型） MsgType
 * text 1、关于重试的消息排重，推荐使用msgid排重。
 * 2、微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。假如服务器无法保证在五秒内处理并回复，
 * 可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。详情请见“发送消息-被动回复消息”。
 * 3、如果开发者需要对用户消息在5秒内立即做出回应，即使用“发送消息-被动回复消息”接口向用户被动回复消息时，可以在
 * 公众平台官网的开发者中心处设置消息加密。开启加密后，用户发来的消息和开发者回复的消息都会被加密（但开发者通过客服
 * 接口等API调用形式向用户发送消息，则不受影响）。关于消息加解密的详细说明，请见“发送消息-被动回复消息加解密说明”。
 *
 *
 * @author raymond
 * @param <T>
 */
//@XmlRootElement(name = "xml") //不能使用此注释类，否则同name的子类无法被Unmarsheller恢复
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageBase<T extends RequestMessageBase> extends MessageBase<T> {

    public RequestMessageBase() {
    }

    public RequestMessageBase(String type, String toUserName, String fromUserName, long createTime, long msgId) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgId = msgId;
        this.msgType = type;
    }
    private long msgId;

    @XmlElement(name = "MsgId")
    public long getMsgId() {
        return msgId;
    }

    public RequestMessageBase setMsgId(long MsgId) {
        this.msgId = MsgId;
        return this;
    }

}
