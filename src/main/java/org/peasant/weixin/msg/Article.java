/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import javax.xml.bind.annotation.XmlElement;

/**
 * 图文
 *
 * @author raymond
 */
public class Article {

    public Article(String title, String picUrl, String url, String description) {
        this.title = title;
        this.picUrl = picUrl;
        this.url = url;
        this.description = description;
    }

    public Article() {
    }

    private String title;
    private String picUrl;
    private String url;
    private String description;

    /**
     * Get the value of url
     *
     * @return the value of url
     */
    @XmlElement(name = "Url")
    public String getUrl() {
        return url;
    }

    /**
     * Set the value of url
     *
     * @param url new value of url
     * @return
     */
    public Article setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    @XmlElement(name = "Description")
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     * @return
     */
    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the value of picUrl
     *
     * @return the value of picUrl
     */
    @XmlElement(name = "PicUrl")
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * Set the value of picUrl
     *
     * @param picUrl new value of picUrl
     * @return
     */
    public Article setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    @XmlElement(name = "Title")
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     * @return
     */
    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

}
