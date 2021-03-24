package com.item.javaeerabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName: MailService
 * @Description 发送邮件服务
 * @Author: jff
 * @Date: 2019-11-07 17:37
 * @Version: 1.0
 **/
@Service
public class MailService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MailService.class) ;

    @Autowired
    JavaMailSender mailSender ;

    public void sendHtmlMsg(String from, String to, String htmlMsg) {
        MimeMessage msg = mailSender.createMimeMessage() ;

        MimeMessageHelper msgHelper = new MimeMessageHelper(msg) ;

        try {
            msg.setSubject("博客验证码") ;
            msgHelper.setText(htmlMsg, true) ;

            //发送消息
            msgHelper.setFrom(from) ;
            msgHelper.setTo(to) ;

            mailSender.send(msg) ;
        } catch (Exception e) {
            LOGGER.info("邮件发送异常[from:" + from +",to:" + to + "]") ;
        }
    }
}
