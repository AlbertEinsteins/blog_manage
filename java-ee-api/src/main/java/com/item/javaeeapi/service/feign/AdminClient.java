package com.item.javaeeapi.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: AdminClient
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-04 21:57
 * @Version: 1.0
 **/
@FeignClient(name = "blog-client")
public interface AdminClient {

    @GetMapping("/admin/list")
    String getMenus() ;
}
