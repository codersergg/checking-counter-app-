package com.codersergg.customer;

import com.codersergg.clients.checklimit.CheckLimitClient;
import com.codersergg.clients.checklimit.UsedLimitResponse;
import com.codersergg.clients.services.Service1Client;
import com.codersergg.clients.services.Service2Client;
import com.codersergg.clients.services.ServiceResponse;
import com.codersergg.customer.exeption.IllegalRequestDataException;
import com.codersergg.customer.request.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Service1Client service1Client;
    private final Service2Client service2Client;
    private final CheckLimitClient checkLimitClient;

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        log.info("registerCustomer()");
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email().toLowerCase())
                .build();
        return customerRepository.saveAndFlush(customer);
    }

    public ServiceResponse getService1(Optional<Customer> optionalCustomer) {
        log.info("getServiсe1()");
        UsedLimitResponse usedLimitCustomer = getUsedLimitCustomer(optionalCustomer.get().getId());
        if (usedLimitCustomer.limit() + 1 >= 10) {
            log.info("The limit is used. Current limit: {}", usedLimitCustomer.limit());
            throw new IllegalRequestDataException("The limit is used. Current limit: " + usedLimitCustomer.limit());
        }
        return service1Client.getService1(optionalCustomer.get().getId());
    }

    public ServiceResponse getService2(Optional<Customer> optionalCustomer) {
        log.info("getServiсe2()");
        UsedLimitResponse usedLimitCustomer = getUsedLimitCustomer(optionalCustomer.get().getId());
        if (usedLimitCustomer.limit() + 4 >= 10) {
            log.info("The limit is used. Current limit: {}", usedLimitCustomer.limit());
            throw new IllegalRequestDataException("The limit is used. Current limit: " + usedLimitCustomer.limit());
        }
        return service2Client.getService2(optionalCustomer.get().getId());
    }

    public Optional<Customer> findByEmailIgnoreCase(String email) {
        log.info("find Customer by email: {}", email);
        String emailIgnoreCase = email.toLowerCase();
        return customerRepository.findCustomerByEmail(emailIgnoreCase);
    }

    public UsedLimitResponse getUsedLimitCustomer(Integer customerId) {
        log.info("get Limit Customer with id: {}", customerId);
        return checkLimitClient.getUsedLimitCustomer(customerId);
    }
}