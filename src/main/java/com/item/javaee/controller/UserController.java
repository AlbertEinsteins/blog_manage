package com.item.javaee.controller;

import com.item.javaee.entity.User;
import com.item.javaee.service.UserService;
import com.item.javaee.utils.FastDfsUtils;
import com.item.javaee.utils.JsonBuilder;
import com.item.javaee.vo.QueryVo;
import org.csource.common.MyException;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-06 00:41
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class) ;

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest req, User user) {
        User existUser = userService.login(user);
        if (existUser == null) { //登陆失败
            req.setAttribute("errorMsg", "登陆失败,账号或密码不正确");
            return "login/login";
        }

        req.getSession().setAttribute("curUser", existUser);
        return "redirect:/";
    }

    //注册成功，返回注册页面，返回注册码
    //0 -- 成功
    //-1 -- 失败
    @RequestMapping("/registry")
    @ResponseBody
    public Map registry(User user, HttpSession session, String code) {
        //检验验证码的正确性
        String existCode = (String) session.getAttribute("code");

        //existcode有可能为空
        if (code.equals(existCode)) {
            userService.saveUser(user);
            return JsonBuilder.buildJson(user, 0, null);
        }
        //登陆失败
        return JsonBuilder.buildJson(user, -1, null);
    }

    @PostMapping("/list")
    @ResponseBody
    public Object getUserList(@RequestBody QueryVo queryVo) {
        Map<String, Object> data = new HashMap<>();

        Page<User> userPage = this.userService.findPageUsersByCondition(queryVo);
        //更新错误的页数, 这里的页数是从0开始的
        queryVo.setPagenum(userPage.getNumber() + 1) ;
        data.put("data", userPage.getContent()) ;
        data.put("total", userPage.getTotalElements());
        data.put("queryVo", queryVo) ;

        return JsonBuilder.buildJson(data, 200, "用户列表");
    }

    //判断用户名是否存在
    @GetMapping("/exist")
    @ResponseBody
    public Object isUserExist(String username) {
        User existUser = this.userService.findByUserName(username) ;
        if(existUser != null) {
            return JsonBuilder.buildJson(null, 300, "用户已存在!") ;
        }
        return JsonBuilder.buildJson(null, 200, "用户不存在!") ;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Object addUser(@RequestBody User user) {
        this.userService.addUser(user) ;
        return JsonBuilder.buildJson(null, 200, "添加成功!") ;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Object getUserById(@PathVariable(name = "id") Integer id) {
        User user = this.userService.findById(id) ;
        if(user != null) {
            return JsonBuilder.buildJson(user, 200, "成功") ;
        }
        return JsonBuilder.buildJson(null, 0, "查询失败") ;
    }


    @Value("${fastdfs.ip}")
    private String fdfsPrefix ;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadAvator(@RequestPart("file") MultipartFile file) {

        //获取后缀名
        String originName = file.getOriginalFilename() ;
        String ext = originName.substring(originName.indexOf(".") + 1) ;

        String pathExt = null ;
        try {
            pathExt = FastDfsUtils.upload(file.getBytes(), ext) ;
        } catch (IOException e) {
            LOGGER.info("上传失败, IO异常");
            return JsonBuilder.buildJson(null, 0, "上传失败, IO异常") ;
        } catch (MyException e) {
            LOGGER.info("上传失败, fastdfs内部异常") ;
            return JsonBuilder.buildJson(null, 0, "上传失败, fastdfs内部异常") ;
        }

        return JsonBuilder.buildJson(fdfsPrefix + pathExt, 200, "上传成功") ;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object modifyUser(@RequestBody User user)  {
        return JsonBuilder.buildJson(this.userService.updateById(user), 200, "修改成功") ;
    }
}
