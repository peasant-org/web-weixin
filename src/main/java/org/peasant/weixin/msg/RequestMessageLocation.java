/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * 地理位置消息</p>
 * Location_X 地理位置维度 Location_Y 地理位置经度 \p Scale 地图缩放大小 Label 地理位置信息
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestMessageLocation extends RequestMessageBase {

    public static final String TYPE = RequestMessageBase.LOCATION;

    private String location_X;

    private String location_Y;

    private String scale;
    private String label;

    public RequestMessageLocation() {
    }

    public RequestMessageLocation(String location_X, String location_Y, String scale, String label, String toUserName, String fromUserName, long createTime, long msgId) {
        super(TYPE, toUserName, fromUserName, createTime, msgId);
        this.location_X = location_X;
        this.location_Y = location_Y;
        this.scale = scale;
        this.label = label;
    }

    /**
     * Get the value of label
     *
     * @return the value of label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the value of label
     *
     * @param label new value of label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Get the value of scale
     *
     * @return the value of scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * Set the value of scale
     *
     * @param scale new value of scale
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * Get the value of location_X
     *
     * @return the value of location_X
     */
    public String getLocation_X() {
        return location_X;
    }

    /**
     * Set the value of location_X
     *
     * @param location_X new value of location_X
     */
    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    /**
     * Get the value of location_Y
     *
     * @return the value of location_Y
     */
    public String getLocation_Y() {
        return location_Y;
    }

    /**
     * Set the value of location_Y
     *
     * @param location_Y new value of location_Y
     */
    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

}
