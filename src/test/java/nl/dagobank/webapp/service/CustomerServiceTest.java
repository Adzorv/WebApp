package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    void checkifBSNIsCorrectTest() {
        assertFalse(customerService.checkIfBSNIsCorrect(012345));
        assertFalse(customerService.checkIfBSNIsCorrect(101234567));
        assertTrue(customerService.checkIfBSNIsCorrect(138335011));

    }
}