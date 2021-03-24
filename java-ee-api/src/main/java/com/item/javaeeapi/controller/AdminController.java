package com.item.javaeeapi.controller;

import com.item.javaeeapi.service.feign.AdminClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AdminController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-04 20:44
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    AdminClient adminClient ;

    @GetMapping("/list")
    public Object getMenuList() {
        return adminClient.getMenus();
    }
}
