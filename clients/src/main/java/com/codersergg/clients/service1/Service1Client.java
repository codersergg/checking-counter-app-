package com.codersergg.clients.service1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service1")
public interface Service1Client {

    @GetMapping(path = "api/v1/service1/{email}")
    Service1Response getService1(@PathVariable("email") String email);
}
