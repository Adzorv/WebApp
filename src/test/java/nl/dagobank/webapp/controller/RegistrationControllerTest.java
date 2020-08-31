package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.PasswordGenerator;
import nl.dagobank.webapp.service.UsernameGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationControllerTest {
    @Autowired
    CustomerDao customerDao;

    public RegistrationControllerTest() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registrationPageHandle() {
    }

    @Test
    void testRegistrationHandler() {
        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setPrefix("van de");
        customer.setLastName("Jansen");
        customer.setStreetName("Overtoom");
        customer.setHouseNumber(23);
        customer.setHouseNumberAnnex("2 hoog");
        customer.setPostCode("1014AA");
        customer.setCity("Amsterdam");
        customer.setPhoneNumber("0612345678");
        //customer.setBirthDate('2010/02/11');//FIXME: how to input date?
        customer.setEmail("janj@gmail.com");
        customer.setBsn(135076569);
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generate(10);
        customer.setPassword(password);
       /* UsernameGenerator usernameGenerator = new UsernameGenerator();
        String userName = usernameGenerator.createUsername(customer.getFirstName(), customer.getLastName());
        customer.setUserName(userName);//FIXME: gives a nullpointerexception for customerDao why?
*/
        customerDao.save(customer);

        assertNotNull(customerDao.findById(customer.getId()));
        assertTrue(customerDao.findByBsn(135076569).getPostCode().equals("1014AA"));
        //assertTrue(customerDao.existsByUserName("JanJan001"));
        List<Customer> customerList = Arrays.asList(customerDao.findAllByCity("Amsterdam"));
        assertTrue(customerList.size() == 1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jan");
        customer2.setPrefix("van de");
        customer2.setLastName("Jansen");
        customer2.setStreetName("Overtoom");
        customer2.setHouseNumber(23);
        customer2.setHouseNumberAnnex("2 hoog");
        customer2.setPostCode("1014AA");
        customer2.setCity("Amsterdam");
        customer2.setPhoneNumber("0612345678");
        //customer.setBirthDate('2010/02/11');//FIXME: how to input date?
        customer2.setEmail("janj@gmail.com");
        customer2.setBsn(135076569);//je kunt niet 2x registreren met hetzelfde bsn

        assertTrue(customerDao.findById(customer2.getId()).equals(Optional.empty()));

        //customerDao.delete(customer);


    }

    @Test
    void testRegistrationFailedHandler() {
    }

    @Test
    void testSwitchBSNValue() {
    }
}