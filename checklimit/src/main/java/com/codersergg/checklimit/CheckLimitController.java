package com.codersergg.checklimit;

import com.codersergg.clients.checklimit.CheckLimitResponse;
import org.springframework.web.bind.annotation.*;

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
        return new CheckLimitResponse(isExceeded);
    }
}
