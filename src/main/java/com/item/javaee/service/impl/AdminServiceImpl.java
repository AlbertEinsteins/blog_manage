package com.item.javaee.service.impl;

import com.item.javaee.entity.Admin;
import com.item.javaee.repository.AdminRepository;
import com.item.javaee.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: AdminServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-03 12:05
 * @Version: 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Override
    public List<Admin> getMenu() {
        List<Admin> parent = adminRepository.findByAdminPid(0) ;
        for (Admin admin : parent) {
            List<Admin> childs = adminRepository.findByAdminPid(admin.getAdminId()) ;

            admin.setChildren(childs) ;
        }
        return parent ;
    }
}
