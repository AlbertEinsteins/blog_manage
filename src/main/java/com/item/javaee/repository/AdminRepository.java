package com.item.javaee.repository;

import com.item.javaee.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    //根据pid查找
    List<Admin> findByAdminPid(Integer pid) ;
}
