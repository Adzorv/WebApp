package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerBuilder {
    Customer customer;
    public CustomerBuilder() {
    }

    public Customer createCustomer() {
        return customer;
    }
}
