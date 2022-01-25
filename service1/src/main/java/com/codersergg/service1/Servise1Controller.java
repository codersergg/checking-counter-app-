package com.codersergg.service1;

import com.codersergg.clients.service1.Service1Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("api/v1/service1")
public class Servise1Controller {

    @GetMapping(path = "{email}")
    public Service1Response getService1(@PathVariable("email") String email) {
        String responseGetService1 = "Service1 is provided " + email + " on " + LocalDateTime.now();
        log.info(responseGetService1);
        return new Service1Response(responseGetService1);
    }

}
