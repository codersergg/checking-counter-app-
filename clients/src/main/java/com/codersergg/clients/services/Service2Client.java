package com.codersergg.clients.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service2")
public interface Service2Client {

    @GetMapping(path = "api/v1/service2/{customerId}")
    ServiceResponse getService2(@PathVariable("customerId") Integer customerId);
}
