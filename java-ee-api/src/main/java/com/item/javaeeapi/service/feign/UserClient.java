package com.item.javaeeapi.service.feign;

import com.item.javaee.entity.User;
import com.item.javaeeapi.vo.QueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "blog-client")
public interface UserClient {

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    String getUserList(@RequestBody QueryVo queryVo) ;

    @GetMapping(value = "/user/exist")
    String isExist(@RequestParam(name = "username") String username) ;

    @PostMapping(value = "/user/add")
    String addUser(@RequestBody User user) ;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    String findUserById(@PathVariable(name = "id") Integer id) ;


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    String modifyUserById(@RequestBody User user) ;
}
