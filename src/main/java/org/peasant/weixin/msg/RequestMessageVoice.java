/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 * <p>
 * 语音消息</p>
 * Format 语音格式，如amr，speex等 Recognition语音识别结果，UTF8编码
 *
 * @author raymond
 */
public class RequestMessageVoice extends RequestMessageMediaBase {

    public static final String TYPE = VOICE;

    private String recognition;

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
