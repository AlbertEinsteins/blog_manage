package com.item.javaee.controller;

import com.item.javaee.domain.RabbitMqMailMsg;
import com.item.javaee.service.RabbitMqService;
import com.item.javaee.utils.JsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: LoginController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-05 19:35
 * @Version: 1.0
 **/
@Controller
public class LoginController {

    //返回登陆页面
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login/login" ;
    }


    //返回注册页面
    @RequestMapping("/memberRegistryPage")
    public String registryPage() {
        return "login/toMemberRegist" ;
    }

    //切换到登陆页面
    @RequestMapping("/memberLoginPage")
    public String memberLoginPage() {
        return "login/toMemberLogin" ;
    }

    @Autowired
    RabbitMqService rabbitMqService ;

    @Value("${mail.from}")
    private String mailFrom ;

    //返回6位注册码，并将注册码填入session, 键名为code
    @RequestMapping("/getCode")
    @ResponseBody
    public Map getCode(HttpSession session,
                       @RequestParam(name = "email") String sendTo) {

        String code = UUID.randomUUID().toString().substring(0, 6) ;
        session.setAttribute("code", code) ;

        //发送邮件体到消息队列
        String msg = "<div>" +
                "<p>您好，您在博客申请的验证码:</p>" +
                "<p style='color: blue; text-align: center;'>" + code + "</p>" +
                "</div>" ;

        rabbitMqService.convertAndSend(new RabbitMqMailMsg(msg, mailFrom, sendTo)) ;

        return JsonBuilder.buildJson(code, 0, null) ;
    }
}
