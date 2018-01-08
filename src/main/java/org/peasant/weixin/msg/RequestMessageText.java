/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 * <p>文本消息</p>
 * Content 文本消息内容
 * @author raymond
 */
public class RequestMessageText extends RequestMessageBase{

    public RequestMessageText(String content, String toUserName, String fromUserName, long createTime, long msgId) {
        super(toUserName, fromUserName, createTime, msgId);
        this.content = content;
    }
    public static final String TYPE = "text";
    private final String msgType= TYPE;

    private String content;

    /**
     * Get the value of content
     *
     * @return the value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the value of content
     *
     * @param content new value of content
     */
    public void setContent(String content) {
        this.content = content;
    }

    
}
