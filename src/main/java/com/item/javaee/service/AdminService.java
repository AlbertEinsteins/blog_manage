package com.item.javaee.service;

import com.item.javaee.entity.Admin;

import java.util.List;

public interface AdminService {

    //以树形返回数据 | 通过chilren关联
    List<Admin> getMenu() ;
}
