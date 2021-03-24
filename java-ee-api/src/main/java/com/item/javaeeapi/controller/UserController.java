package com.item.javaeeapi.controller;

import com.item.javaee.entity.User;
import com.item.javaeeapi.service.feign.UploadClient;
import com.item.javaeeapi.service.feign.UserClient;
import com.item.javaeeapi.vo.QueryVo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UserController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-05 16:29
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserClient userClient ;

    @Autowired
    UploadClient uploadClient ;

    @GetMapping("/list")
    public Object getUserList(QueryVo queryVo) {
        return userClient.getUserList(queryVo) ;
    }

    @GetMapping("/exist")
    public Object isUserExist(@RequestParam("username")String username) {
        return userClient.isExist(username) ;
    }

    @RequestMapping("/add")
    public Object addUser(User user) {
        return this.userClient.addUser(user) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable(name = "id") Integer id) {
        return this.userClient.findUserById(id) ;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        return this.uploadClient.uploadFile(file) ;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String modifyUserById(@RequestBody User user)  {
        return this.userClient.modifyUserById(user) ;
    }

}
