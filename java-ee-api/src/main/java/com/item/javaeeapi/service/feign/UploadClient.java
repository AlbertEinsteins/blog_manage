package com.item.javaeeapi.service.feign;

import com.item.javaeeapi.config.FeignMultipartConfig;
import com.item.javaeeapi.config.ServiceFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "blog-client",
        configuration = {FeignMultipartConfig.class, ServiceFeignConfig.class})
public interface UploadClient {
    @RequestMapping(value = "/user/upload", method = RequestMethod.POST)
    String uploadFile(@RequestPart("file") MultipartFile file) ;
}
