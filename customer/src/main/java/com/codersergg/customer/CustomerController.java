package com.codersergg.customer;

import com.codersergg.clients.checklimit.CustomerEmailRequest;
import com.codersergg.clients.checklimit.UsedLimitResponse;
import com.codersergg.clients.services.ServiceResponse;
import com.codersergg.customer.exeption.IllegalRequestDataException;
import com.codersergg.customer.request.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("registerCustomer()");
        String email = customerRegistrationRequest.email();
        checkCustomerAvailability(customerService.findByEmailIgnoreCase(email).isPresent(), email + " email is already registered");

        log.info("new customer registration {}", customerRegistrationRequest);
        return customerService.registerCustomer(customerRegistrationRequest);
    }

    @PostMapping("service1")
    public ServiceResponse getServise1(@RequestBody CustomerEmailRequest customerEmailRequest) {
        log.info("getServiсe1()");
        String email = customerEmailRequest.email();
        Optional<Customer> optionalCustomer = customerService.findByEmailIgnoreCase(email);
        checkCustomerAvailability(optionalCustomer.isEmpty(), "email: " + email + " not registered");

        log.info("{} get Service1 Customer with email: ", email);
        return customerService.getService1(customerService.findByEmailIgnoreCase(email));
    }

    @PostMapping("service2")
    public ServiceResponse getServise2(@RequestBody CustomerEmailRequest customerEmailRequest) {
        log.info("getServiсe2()");
        String email = customerEmailRequest.email();
        Optional<Customer> optionalCustomer = customerService.findByEmailIgnoreCase(email);
        checkCustomerAvailability(optionalCustomer.isEmpty(), "email: " + email + " not registered");

        log.info("{} get Service2 Customer with email: ", email);
        return customerService.getService2(customerService.findByEmailIgnoreCase(email));
    }

    @PostMapping("check-limit")
    public UsedLimitResponse getUsedLimitCustomer(@RequestBody CustomerEmailRequest customerEmailRequest) {
        log.info("getLimitCustomer()");
        String email = customerEmailRequest.email();
        Optional<Customer> optionalCustomer = customerService.findByEmailIgnoreCase(email);
        checkCustomerAvailability(optionalCustomer.isEmpty(), "email: " + email + " not registered");

        log.info("{} get Customer limit with email: ", email);
        return customerService.getUsedLimitCustomer(optionalCustomer.get().getId());
    }

    private void checkCustomerAvailability(boolean customerService, String message) {
        if (customerService) {
            log.info(message);
            throw new IllegalRequestDataException(message);
        }
    }

}
