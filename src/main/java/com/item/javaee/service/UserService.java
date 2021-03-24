package com.item.javaee.service;

import com.item.javaee.entity.User;
import com.item.javaee.vo.QueryVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    //用户注册
    boolean saveUser(User user) ;

    /**
     * 用户输入账号密码进行登录，如果存在返回对象的所有属性；否则返回null
     * @param user 只包含有账号和密码属性
     * @return User | null
     */
    User login(User user) ;

    //根据用户名查号对象
    User findByUserName(String userName) ;

    //分页查询用户
    Page<User> findPageUsersByCondition(QueryVo vo) ;


    //添加用户
    boolean addUser(User user) ;

    //根据id查找
    User findById(Integer id) ;

    //根据id修改
    User updateById(User user) ;
}
