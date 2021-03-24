package com.item.javaee.controller;

import com.item.javaee.service.AdminService;
import com.item.javaee.utils.JsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AdminController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-03 11:59
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService ;

    @Value("${server.port}")
    private String port ;

    @GetMapping("/list")
    public Object getMenus() {
        return JsonBuilder.buildJson(this.adminService.getMenu(), 200, "左侧边栏菜单") ;
    }
}
