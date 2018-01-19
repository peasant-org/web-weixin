/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *图文消息
 * @author raymond
 */
@XmlRootElement(name = "xml")
public class PictureTextMsg extends ResponseMessageBase<PictureTextMsg> {

    @XmlElementWrapper(name = "Articles")
    @XmlElement(name = "item")
    public List<Article> articles = new LinkedList();

    @XmlElement(name = "ArticleCount")
    public int getArticleCount() {
        return this.articles.size();
    }

}
