package com.codersergg.clients.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service1")
public interface Service1Client {

    @GetMapping(path = "api/v1/service1/{customerId}")
    ServiceResponse getService1(@PathVariable("customerId") Integer customerId);

}
