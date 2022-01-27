package com.codersergg.checklimit;

import com.codersergg.clients.checklimit.UsedLimitResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/check-limit")
public class CheckLimitController {

    private final CheckLimitService checkLimitService;

    @GetMapping(path = "{customerId}")
    public UsedLimitResponse getUsedLimitCustomer(@PathVariable("customerId") Integer customerId) {
        log.info("isExceeded");
        return checkLimitService.getUsedLimitCustomer(customerId);
    }
}
