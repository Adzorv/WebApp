//TODO nullpointer
/*package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsernameGeneratorTest {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    CustomerService customerService;
    @Autowired
    UsernameGenerator usernameGenerator;
    @Autowired
    PasswordGenerator passwordGenerator;


    @Test
    void createUsername() {

        String expected = "JanTes001";
        String actual = usernameGenerator.createUsername("Tessa", "Janssen");
        assertEquals(expected, actual);

        expected = "LuiRos001";
        actual = usernameGenerator.createUsername("Rose", "Luifje");
        assertEquals(expected, actual);

        expected = "GasYox001";
        actual = usernameGenerator.createUsername("Yo", "Gasselt");
        assertEquals(expected, actual);

        expected = "JanTex001";
        actual = usernameGenerator.createUsername("Te", "Janssen");
        assertEquals(expected, actual);

        expected = "JaxTes001";
        actual = usernameGenerator.createUsername("Tessa", "Ja");
        assertEquals(expected, actual);

        expected = "TopLil001";
        actual = usernameGenerator.createUsername("Lilore", "Topaz");
        assertEquals(expected, actual);

        expected = "TeiMar001";
        actual = usernameGenerator.createUsername("Martje", "Teissen");
        assertEquals(expected, actual);

        Customer customer = new Customer();
        customer.getInlogCredentials().setUserName("TeiMar001");
        customerService.saveCustomer(customer);

        expected = "TeiMar002";
        actual = usernameGenerator.createUsername("Martha", "Teil");
        assertEquals(expected, actual);

        expected = "UpxMox001";
        actual = usernameGenerator.createUsername("Mo", "Up");
        assertEquals(expected, actual);

    }

    @Test
        //fixme: how to test randomly created Strings?
    void createPassword() {
        for (int i = 0; i < 10; i++) {
            String actual = passwordGenerator.generate(10);
            System.out.println(actual);
        }
    }
}*/
