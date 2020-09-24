package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.Address;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.util.TestDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataUserService {



    CustomerService customerService;
    TestDataGenerator generator;


    void createAndSaveUsersWithNameAdressAndBSN(int numberOfUsers){
        List<String> firstNames = generator.readFirstnamesFromFile(numberOfUsers);
        List<String> prefixesOfName = generator.readPrefixFromFile(numberOfUsers);
        List<String> surnames = generator.readLastNameFromFile(numberOfUsers);
        List<Address> adresses = generator.readAdressesFromFile(numberOfUsers);

        for (int i = 0; i < numberOfUsers; i++) {




            Customer customer = new Customer();


        }

    }

}
