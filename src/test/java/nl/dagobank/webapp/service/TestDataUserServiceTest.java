//TODO write real test
package nl.dagobank.webapp.service;

import org.iban4j.Iban;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDataUserServiceTest {

    @Autowired
    TestDataUserService service;

    @Test
    void createAndSaveUsersTest() {
    service.createAndSaveUsers(5000);

    }

}
