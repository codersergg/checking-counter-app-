package com.codersergg.clients.checklimit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("checklimit")
public interface CheckLimitClient {

    @GetMapping(path = "api/v1/check-limit/{customerId}")
    CheckLimitResponse isExceeded(@PathVariable("customerId") Integer customerId);
}
