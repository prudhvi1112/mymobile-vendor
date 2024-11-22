package com.vendor.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vendor.dto.UserDataDto;


@FeignClient(name = "user-register-service",url = "${user.register.service.url}")
public interface UserRegisterClient {

    @GetMapping("/getVendor/{id}")
    UserDataDto getUserData(@PathVariable("id") String vendorId);
}
