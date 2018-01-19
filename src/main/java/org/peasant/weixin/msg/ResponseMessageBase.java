/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peasant.weixin.msg;

/**
 * 回复消息的基类
 *
 * @author raymond
 * @param <T>
 */
public class ResponseMessageBase<T extends ResponseMessageBase> extends MessageBase<T> {

    public ResponseMessageBase() {
    }

}
