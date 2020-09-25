package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.util.TestDataGenerator;
import nl.dagobank.webapp.util.generator.BsnGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class TestDataUserService {

    CustomerService customerService;
    CustomerDao customerDao;
    EmployeeDao employeeDao;

    @Autowired
    public TestDataUserService(CustomerService customerService, CustomerDao customerDao, EmployeeDao employeeDao) {
        this.customerService = customerService;
        this.customerDao = customerDao;
        this.employeeDao = employeeDao;
    }

    public void createStandardTestUsers(){
        Employee hoofdMkb = new Employee();
        hoofdMkb.setRole("HoofdMKB");
        hoofdMkb.setInlogCredentials(new InlogCredentials("HoofdMKB","hmkb"));
        hoofdMkb.setPersonalDetails(new PersonalDetails(LocalDate.now(), 111222333));
        hoofdMkb.setAddress(new Address("mkbstraat", 25, "A", "8271 AG", "Houten"));
        employeeDao.save(hoofdMkb);

        Customer testCustomer = new Customer();
        testCustomer.setInlogCredentials(new InlogCredentials("test", "test"));
        testCustomer.setPersonalDetails(new PersonalDetails(LocalDate.now().minusYears(50), 111222334));
        testCustomer.setAddress(new Address("teststraat", 11, "B", "8888 UU","Testplaats"));
        customerDao.save(testCustomer);

    }

    public void createAndSaveUsers(int numberOfUsers) {
        TestDataGenerator generator = new TestDataGenerator();
        final int LENGTH_OF_PASSWORD = 3;
        List<String> firstNames = generator.readFirstnamesFromFile(numberOfUsers);
        List<String> prefixesOfName = generator.readPrefixFromFile(numberOfUsers);
        List<String> surnames = generator.readLastNameFromFile(numberOfUsers);
        List<Address> adresses = generator.readAdressesFromFile(numberOfUsers);
        BsnGenerator bsnGenerator = new BsnGenerator(111222333);
        UsernameGenerator usernameGenerator = new UsernameGenerator(customerDao);
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        Logger LOG = LogManager.getFormatterLogger(TestDataUserService.class);

        for (int i = 0; i < numberOfUsers; i++) {
            if( i % 100 == 0){ LOG.info( i + " Customers saved to database..." ); }

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
