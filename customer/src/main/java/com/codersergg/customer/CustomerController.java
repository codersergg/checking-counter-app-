package com.codersergg.customer;

import com.codersergg.customer.exeption.IllegalRequestDataException;
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

        log.info("checking email registration {}", customerRegistrationRequest.email());
        boolean isEmailRegistered = findByEmailIgnoreCase(customerRegistrationRequest.email());

        if (isEmailRegistered) {
            log.info(customerRegistrationRequest.email() + " email is already registered");
            throw new IllegalRequestDataException(
                    customerRegistrationRequest.email() + " email is already registered");
        }
        else {
            log.info("new customer registration {}", customerRegistrationRequest);
            customerService.registerCustomer(customerRegistrationRequest);
        }
    }

    public boolean findByEmailIgnoreCase(String email) {
        return customerService.findByEmailIgnoreCase(email);
    }

}
