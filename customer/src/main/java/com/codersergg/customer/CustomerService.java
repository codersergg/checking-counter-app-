package com.codersergg.customer;

import com.codersergg.clients.checklimit.CheckLimitClient;
import com.codersergg.clients.checklimit.CheckLimitResponse;
import com.codersergg.clients.service1.Service1Client;
import com.codersergg.clients.service1.Service1Response;
import com.codersergg.customer.request.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Service1Client service1Client;
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

    public void getService1(String email) {
        if(customerRepository.findCustomerByEmail(email).isEmpty()) {
            throw new IllegalStateException("user " + email + "{} not found");
        }
        service1Client.getService1(email);
    }


    public boolean findByEmailIgnoreCase(String email) {
        String emailIgnoreCase = email.toLowerCase();
        Optional<Customer> customer = customerRepository.findCustomerByEmail(emailIgnoreCase);
        return customer.isPresent();
    }
}