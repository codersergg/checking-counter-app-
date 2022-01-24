package com.codersergg.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email().toLowerCase())
                .build();
        customerRepository.saveAndFlush(customer);
        CheckLimitResponse checkLimitResponse = restTemplate.getForObject(
                "http://CHECKLIMIT/api/v1/check-limit/{customerId}",
                CheckLimitResponse.class,
                customer.getId()
        );

        if (checkLimitResponse.isExceeded()) {
            throw new IllegalStateException("exceeded");
        }

        customerRepository.save(customer);
    }

    public boolean findByEmailIgnoreCase(String email) {
        String emailIgnoreCase = email.toLowerCase();
        Optional<Customer> customer = customerRepository.findCustomerByEmail(emailIgnoreCase);
        return customer.isPresent();
    }
}