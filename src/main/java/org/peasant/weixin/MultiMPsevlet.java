/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第一次接入微信公众平台，响应微信服务器发来的token验证，以接入平台。支持多个公众号同时接入，
 * 使用{@link WeixinMPconfig} 对各个众号的参数进行配置。
 * 此Servelt的URL pattern必须配置成以通配符形式，如：“your_servlet_path/* ”。
 * 在公众号开发配置里的服务器URL配置成url_to_your_servlet_path/公众号的微信号
 * @author raymond
 */
@javax.servlet.annotation.WebServlet(name = "MultiMPsevlet", urlPatterns = {"/weixins/*"},
        initParams = {
            @WebInitParam(name = "token", value = "whatever")},
        description = "第一次接入微信公众平台，响应微信服务器发来的token验证，以接入平台")
public class MultiMPsevlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.此句必须删除，否则造成失败
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        String gid = req.getPathInfo();
        if (gid != null) {
            gid = gid.substring(1, gid.length() - 1);
            if(WeixinUtils.checkSignature(WeixinUtils.getToken(gid), req)){
                
                resp.setCharacterEncoding("UTF-8");
                //resp.getWriter().print(echostr);
                resp.getWriter().write(echostr);
                resp.getWriter().close();
                
            }
        }

    }

}
