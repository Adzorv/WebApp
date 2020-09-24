package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.util.TestDataGenerator;
import nl.dagobank.webapp.util.generator.BsnGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataUserService {

    CustomerService customerService;

    @Autowired
    public TestDataUserService(CustomerService customerService) {
        this.customerService = customerService;
    }

    void createAndSaveUsersWithNameAdressAndBSN(int numberOfUsers) {
        TestDataGenerator generator = new TestDataGenerator();
        final int LENGTH_OF_PASSWORD = 3;
        List<String> firstNames = generator.readFirstnamesFromFile(numberOfUsers);
        List<String> prefixesOfName = generator.readPrefixFromFile(numberOfUsers);
        List<String> surnames = generator.readLastNameFromFile(numberOfUsers);
        List<Address> adresses = generator.readAdressesFromFile(numberOfUsers);
        BsnGenerator bsnGenerator = new BsnGenerator(111222333);
        UsernameGenerator usernameGenerator = new UsernameGenerator();
        PasswordGenerator passwordGenerator = new PasswordGenerator();


        for (int i = 0; i < numberOfUsers; i++) {
            PersonalDetails personalDetails = new PersonalDetails(generator.getRandomBirthDate(18, 100), bsnGenerator.next());

            String firstName = firstNames.get(getRandomIndex(firstNames.size()));
            int indexForSurname = getRandomIndex(surnames.size());
            String prefix = prefixesOfName.get(indexForSurname);
            String surname = surnames.get(indexForSurname);

            FullName fullName = new FullName(firstName, prefix, surname);

            Customer customer = new Customer();
            customer.setFullName(fullName);
            customer.setAddress(adresses.get(i));
            customer.setPersonalDetails(personalDetails);

            String userName = usernameGenerator.createUsername(customer.getFullName().getFirstName(), customer.getFullName().getLastName());
            String password = passwordGenerator.generate(LENGTH_OF_PASSWORD);
            InlogCredentials credentials = new InlogCredentials(userName, password);
            customer.setInlogCredentials(credentials);

            customer = customerService.saveCustomerWithReturn(customer);

            String telephonNumber = generator.generateTelephoneNumberFromUniqueId(customer.getId());
            String email = generator.generateRandomEmailFromMailPrefix(userName);
            ContactDetails contactDetails = new ContactDetails(telephonNumber, email);
            customer.setContactDetails(contactDetails);

            customer = customerService.saveCustomerWithReturn(customer);
        }
    }

        private int getRandomIndex(int size){
                return (int)(Math.random() * size);
        }


}
