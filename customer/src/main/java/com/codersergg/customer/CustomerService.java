package com.codersergg.customer;

import com.codersergg.clients.checklimit.CheckLimitClient;
import com.codersergg.clients.checklimit.CheckLimitResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final CheckLimitClient checkLimitClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email().toLowerCase())
                .build();
        customerRepository.saveAndFlush(customer);

        CheckLimitResponse checkLimitResponse = checkLimitClient.isExceeded(customer.getId());

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