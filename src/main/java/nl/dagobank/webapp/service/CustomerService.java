package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService( CustomerDao customerDao ) {
        super();
        this.customerDao = customerDao;
    }
}
