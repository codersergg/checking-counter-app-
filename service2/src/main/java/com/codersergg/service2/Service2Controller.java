package com.codersergg.service2;

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
@RequestMapping("api/v1/service2")
public class Service2Controller {

    public final Service2Service service2Service;

    @GetMapping(path = "{customerId}")
    public ServiceResponse getService1(@PathVariable("customerId") Integer customerId) {
        LocalDateTime createdAt = LocalDateTime.now();
        String responseGetService1 = "Service1 is provided to the Customer with id: " + customerId + " on " + createdAt;
        log.info(responseGetService1);
        ServiceResponse response = new ServiceResponse(
                customerId,
                4,
                "Service2",
                createdAt,
                responseGetService1);
        service2Service.sendMessage2CheckLimit(response);
        return response;
    }

}