/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Latitude	地理位置纬度 Longitude	地理位置经度 Precision	地理位置精度
 *
 * @author raymond
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RequestEventMsgReportLocation extends RequestEventMsgBase {

    public RequestEventMsgReportLocation(double latitude, double longitude, String precision, String toUserName, String fromUserName, long createTime, long msgId) {
        super(RequestEventMsgBase.EVENT_LOCATION, toUserName, fromUserName, createTime, msgId);
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

    public final static String EVENT_TYPE = "LOCATION";
    /*Latitude 地理位置纬度           
     */
    private double latitude;

    /*Longitude 地理位置经度    
     */
    private double longitude;

    /* Precision 地理位置精度    
     */
    private String precision;

    /**
     *
     * Get the value of precision
     *
     * @return the value of precision
     */
    @XmlElement(name = "Precision")
    public String getPrecision() {
        return precision;
    }

    /**
     * Set the value of precision
     *
     * @param precision new value of precision
     */
    public void setPrecision(String precision) {
        this.precision = precision;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    @XmlElement(name = "Longitude")
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    @XmlElement(name = "Latitude")
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
