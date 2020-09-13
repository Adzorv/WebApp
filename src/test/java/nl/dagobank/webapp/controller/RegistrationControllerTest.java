package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.CustomerFactory;
import nl.dagobank.webapp.service.PasswordGenerator;
import nl.dagobank.webapp.service.UsernameGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationControllerTest {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    UsernameGenerator usernameGenerator;
    @Autowired
    PasswordGenerator passwordGenerator;
   /* @Autowired
    CustomerFactory customerFactory;*/

    Customer customer;
    Customer customer2;
    LocalDate date;

    public RegistrationControllerTest() {
    }

    /*@BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setFirstName("Jan");
        customer.setPrefix("van de");
        customer.setLastName("Jansen");
        customer.setStreetName("Overtoom");
        customer.setHouseNumber(23);
        customer.setHouseNumberAnnex("2 hoog");
        customer.setPostCode("1014AA");
        customer.setCity("Amsterdam");
        customer.setPhoneNumber("0612345678");
        date = LocalDate.of(2000, 11, 11);
        customer.setBirthDate(date);
        customer.setEmail("janj@gmail.com");
        customer.setBsn(138335011);
        String password = passwordGenerator.generate(10);
        customer.setPassword(password);
        String userName = usernameGenerator.createUsername(customer.getFirstName(), customer.getLastName());
        customer.setUserName(userName);

*/
       /* customer2 = new Customer();
        customer2.setFirstName("Joop");
        customer2.setPrefix("van de");
        customer2.setLastName("Veer");
        customer2.setStreetName("Singel");
        customer2.setHouseNumber(2);
        customer2.setHouseNumberAnnex("bg");
        customer2.setPostCode("1011AZ");
        customer2.setCity("Amsterdam");
        customer2.setPhoneNumber("0624345678");
        customer.setBirthDate(LocalDate.of(2000,11,1));
        customer2.setEmail("joop@gmail.com");
        customer2.setBsn(135076569);*///je kunt niet 2x registreren met hetzelfde bsn dus geeft het een foutmelding ??/**/
       /* password = passwordGenerator.generate(10);
        customer.setPassword(password);
        userName = usernameGenerator.createUsername(customer.getFirstName(), customer.getLastName());
        customer.setUserName(userName);

    }*/

    @AfterEach
    void tearDown() {
        //customerDao.deleteAll();
    }

    @Test
    void registrationPageHandle() {
    }

    @Test
    void testRegistrationHandler() {
        customerDao.save(customer);
        assertNotNull(customerDao.findById(customer.getId()));
        assertTrue(customerDao.findByBsn(135076569).getUserAddress().getPostCode().equals("1014AA"));
        assertTrue(customerDao.existsByUserName("JanJan001"));
        List<Customer> customerList = Arrays.asList(customerDao.findAllByCity("Amsterdam"));
        assertTrue(customerList.size() == 1);

        //customerDao.save(customer2); //FIXme: how te testen voor foutmelding?
        assertTrue(customerDao.findById(customer2.getId()).equals(Optional.empty()));


    }

    @Test
    void testRegistrationFailedHandler() {
    }

    @Test
    void testSwitchBSNValue() {
    }
}