/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import java.util.Date;

/**
 *
 * @author raymond
 */
public abstract class RequestMessageBase {
   

    private String toUserName;
    private String fromUserName;
    private long createTime;
    private final String msgType= "base";
    private long  msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long CreateTime) {
        this.createTime = CreateTime;
    }

    public String getMsgType() {
        return msgType;
    }



    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long MsgId) {
        this.msgId = MsgId;
    }


            
}
