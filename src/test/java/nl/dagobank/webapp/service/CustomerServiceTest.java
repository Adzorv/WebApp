package nl.dagobank.webapp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {


    @Test
    void checkifBSNIsCorrectTest() {
        CustomerService customerService = new CustomerService();
        assertFalse(customerService.checkIfBSNIsCorrect(012345));
        assertFalse(customerService.checkIfBSNIsCorrect(101234567));
        assertTrue(customerService.checkIfBSNIsCorrect(138335011));

    }
}