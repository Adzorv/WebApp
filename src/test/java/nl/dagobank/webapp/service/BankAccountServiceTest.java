package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankAccountServiceTest {

    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    CustomerDao customerDao;

    @Autowired
    BankAccountDao bankAccountDao;


    @BeforeEach
    void setUp(){

    }

    @Test
    void getAllAccountHoldersTest(){
        Customer customer = customerDao.findByUserInlogCredentialsUserName("seb").get();

        //BankAccount bankAccount = bankAccountDao.findById(16).get();

        //System.out.println(bankAccount.getSecondaryAccountHolders());
        //boolean present = bankAccountService.isCustomerSecondAccountHolder(customer, bankAccount);

        //Assertions.assertTrue(present);

        System.out.println(bankAccountService.getAllAccountsFromCustomer(customer));
    }
}
