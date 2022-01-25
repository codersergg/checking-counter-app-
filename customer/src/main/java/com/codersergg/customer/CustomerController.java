package com.codersergg.customer;

import com.codersergg.clients.service1.Service1Response;
import com.codersergg.customer.exeption.IllegalRequestDataException;
import com.codersergg.customer.request.CustomerRegistrationRequest;
import com.codersergg.customer.request.Service1Reques;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("registerCustomer()");
        String email = customerRegistrationRequest.email();

        boolean isEmailRegistered = findByEmailIgnoreCase(email);
        if (isEmailRegistered) {
            log.info(email + " email is already registered");
            throw new IllegalRequestDataException(
                    email + " email is already registered");
        }

        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @PostMapping("service1")
    public void getServise1(@RequestBody Service1Reques service1Request) {
        log.info("getServise1()");

        String email = service1Request.email();

        boolean isEmailRegistered = findByEmailIgnoreCase(email);
        if (!isEmailRegistered) {
            log.info("email: " + email + " not registered");
            throw new IllegalRequestDataException("email: " + email + " not registered");
        }
        customerService.getService1(email);
    }

    private boolean findByEmailIgnoreCase(String email) {
        log.info("checking email registration {}", email);
        return customerService.findByEmailIgnoreCase(email);
    }

}
