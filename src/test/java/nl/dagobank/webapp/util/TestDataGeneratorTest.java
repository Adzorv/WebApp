package nl.dagobank.webapp.util;

import nl.dagobank.webapp.domain.Address;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.TestDataUserService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDataGeneratorTest {

    TestDataGenerator generator = new TestDataGenerator();
    static final int NUMBER_OF_NAMES = 10;

    List<String> names;

    @Test
    void readFirstnamesFromFileTest(){
        names = generator.readFirstnamesFromFile(NUMBER_OF_NAMES);

        System.out.println("names");
        System.out.println(names.size());
        System.out.println(names);
    }



    @Test
    void readLastNameFromFileTest(){
        names = generator.readLastNameFromFile(NUMBER_OF_NAMES);

        System.out.println("lastName");
        System.out.println(names.size());
        System.out.println(names);
    }

    @Test
    void readPrefixFromFileTest(){
        names = generator.readPrefixFromFile(NUMBER_OF_NAMES);

        System.out.println("prefix");
        System.out.println(names.size());
        System.out.println(names);
    }
    //int found = names.size();
    @Test
    void readAdressesFromFileTest(){
        List<Address> adresses = generator.readAdressesFromFile(NUMBER_OF_NAMES);

        System.out.println("adresses");
        System.out.println(adresses.size());
        System.out.println(adresses);
    }

}
