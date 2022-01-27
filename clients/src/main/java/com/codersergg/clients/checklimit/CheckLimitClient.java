package com.codersergg.clients.checklimit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("check-limit")
public interface CheckLimitClient {

    @GetMapping(path = "api/v1/check-limit/{customerId}")
    UsedLimitResponse getUsedLimitCustomer(@PathVariable("customerId") Integer customerId);
}
