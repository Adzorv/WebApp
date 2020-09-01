package nl.dagobank.webapp.service;

import org.iban4j.Iban;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IbanGeneratorTest {

    @Autowired
    IbanGenerator ibanGenerator;

    @Test
    void createIbanTest() {
            for (int i = 0; i < 10; i++) {
                Iban actual = ibanGenerator.createIban();
                System.out.println(actual.toString());
            }
    }
}