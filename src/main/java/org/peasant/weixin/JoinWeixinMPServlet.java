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
 *
 * @author raymond
 */
@javax.servlet.annotation.WebServlet(name = "JoinWeixinPPServlet", urlPatterns = {"/wxMsgEvent"},
        initParams = {
            @WebInitParam(name = "token", value = "whatever")},
        description = "第一次接入微信公众平台，响应微信服务器发来的token验证，以接入平台")
public class JoinWeixinMPServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.此句必须删除，否则造成失败
            String signature = req.getParameter("signature");
            String timestamp = req.getParameter("timestamp");
            String nonce = req.getParameter("nonce");
            String echostr = req.getParameter("echostr");
            String cs;
            cs = MessageDiegetor.getSortedSHA1(getInitParameter("token"), timestamp, nonce);
            
            if (signature.equals(cs)) {
                resp.setCharacterEncoding("UTF-8");
                //resp.getWriter().print(echostr);
                resp.getWriter().write(echostr);
                resp.getWriter().close();

            }
        } catch (AesException ex) {
            Logger.getLogger(JoinWeixinMPServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
