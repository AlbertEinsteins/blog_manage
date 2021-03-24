package com.item.javaee.service.impl;

import com.item.javaee.entity.User;
import com.item.javaee.repository.UserRepository;
import com.item.javaee.service.UserService;
import com.item.javaee.vo.QueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-06 12:31
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Override
    public boolean saveUser(User user) {
        userRepository.save(user) ;
        return true ;
    }

    @Override
    public User login(User user) {
        User u = findByUserName(user.getUsername()) ;
        if(u != null) {
            if(u.getPassword().equals(user.getPassword())) {
                return u ;
            }
        }
        return null ;
    }

    @Override
    public User findByUserName(String userName) {
        Specification<User> specification =
                (root, query, queryBuilder) -> queryBuilder.equal(root.get("username"), userName);

        List<User> userList = userRepository.findAll(specification) ;

        if(userList.size() != 0) {
            return userList.get(0) ;
        }
        return null ;
    }

    @Override
    public Page<User> findPageUsersByCondition(QueryVo vo) {

        //分页
        Pageable pageable = PageRequest.of(vo.getPagenum() - 1, vo.getPagesize()) ;
        //分页条件
        if(null != vo.getKey() && !"".equals(vo.getKey().trim())) {
            User user = new User();
            user.setUsername(vo.getKey());
            //匹配 %name%
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());
            Example<User> cond = Example.of(user, matcher);
            return this.userRepository.findAll(cond, pageable) ;
        }
        else {
            return this.userRepository.findAll(pageable) ;
        }
    }

    @Override
    public boolean addUser(User user) {
        //另username=name
        user.setName(user.getUsername()) ;
        //priorityid=2; represents 普通用户
        user.setPriorityId(2) ;
        this.userRepository.save(user) ;
        return true ;
    }

    @Override
    public User findById(Integer id) {
        return this.userRepository.findById(id).get() ;
    }

    @Override
    public User updateById(User user) {
        User existUser = userRepository.findById(user.getUserId()).orElse(new User()) ;
        BeanUtils.copyProperties(user, existUser) ;

        return this.userRepository.save(existUser) ;
    }
}
