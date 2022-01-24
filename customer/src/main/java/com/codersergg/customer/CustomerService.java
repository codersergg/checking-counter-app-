package com.codersergg.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email().toLowerCase())
                .build();
        customerRepository.save(customer);
    }

    public boolean findByEmailIgnoreCase(String email) {
        String emailIgnoreCase = email.toLowerCase();
        Optional<Customer> customer = customerRepository.findCustomerByEmail(emailIgnoreCase);
        return customer.isPresent();
    }
}