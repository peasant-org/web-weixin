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
 * 语音消息</p>
 * Format 语音格式，如amr，speex等 Recognition语音识别结果，UTF8编码
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageVoice extends RequestMessageMediaBase {

    public static final String TYPE = VOICE;

    private String recognition;

    public RequestMessageVoice() {
    }

    public RequestMessageVoice(String recognition, String format, String mediaID, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, mediaID, toUserName, fromUserName, createTime, msgId);
        this.recognition = recognition;
        this.format = format;
    }

    /**
     * Get the value of recognition
     *
     * @return the value of recognition
     */
    @XmlElement(name = "Recognition")
    public String getRecognition() {
        return recognition;
    }

    /**
     * Set the value of recognition
     *
     * @param recognition new value of recognition
     */
    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    private String format;

    /**
     * Get the value of format
     *
     * @return the value of format
     */
    @XmlElement(name = "Format")
    public String getFormat() {
        return format;
    }

    /**
     * Set the value of format
     *
     * @param format new value of format
     */
    public void setFormat(String format) {
        this.format = format;
    }

}
