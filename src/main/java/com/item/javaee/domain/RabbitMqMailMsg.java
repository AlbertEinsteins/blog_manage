package com.item.javaee.domain;

/**
 * @ClassName: RabbitMqMailMsg
 * @Description 注册时，发送的邮件实体到消息队列中
 * @Author: jff
 * @Date: 2019-11-07 13:24
 * @Version: 1.0
 **/
public class RabbitMqMailMsg {
    private String htmlMsg ;        //发送的消息体
    private String from ;           //发送方
    private String to ;             //接收方

    public RabbitMqMailMsg() {
    }

    public RabbitMqMailMsg(String htmlMsg, String from, String to) {
        this.htmlMsg = htmlMsg;
        this.from = from;
        this.to = to;
    }

    public String getHtmlMsg() {
        return htmlMsg;
    }

    public void setHtmlMsg(String htmlMsg) {
        this.htmlMsg = htmlMsg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "RabbitMqMailMsg{" +
                "htmlMsg='" + htmlMsg + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
