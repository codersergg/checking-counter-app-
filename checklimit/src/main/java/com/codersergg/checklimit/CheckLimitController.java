package com.codersergg.checklimit;

import com.codersergg.clients.checklimit.CheckLimitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/check-limit")
public class CheckLimitController {

    private final CheckLimitService checkLimitService;

    public CheckLimitController(CheckLimitService checkLimitService) {
        this.checkLimitService = checkLimitService;
    }

    @GetMapping(path = "{customerId}")
    public CheckLimitResponse isExceeded(@PathVariable("customerId") Integer customerId) {
        boolean isExceeded = checkLimitService.isCheckedCustomer(customerId);
        log.info("Limit is exceeded: " + isExceeded);
        return new CheckLimitResponse(isExceeded);
    }
}
