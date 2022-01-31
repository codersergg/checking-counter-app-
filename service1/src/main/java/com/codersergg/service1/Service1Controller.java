package com.codersergg.service1;

import com.codersergg.clients.services.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/service1")
public class Service1Controller {

    public final Service1Service service1Service;

    @GetMapping(path = "{customerId}")
    public ServiceResponse getService1(@PathVariable("customerId") Integer customerId) {
        LocalDateTime createdAt = LocalDateTime.now();
        String responseGetService1 = "Service1 is provided to the Customer with id: " + customerId + " on " + createdAt;
        log.info(responseGetService1);
        ServiceResponse response = new ServiceResponse(
                customerId,
                1,
                "Service1",
                createdAt,
                responseGetService1);
        service1Service.sendMessage2CheckLimit(response);
        return response;
    }

}