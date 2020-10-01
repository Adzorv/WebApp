package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.Business;
import nl.dagobank.webapp.dao.*;
import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.util.TestDataGenerator;
import nl.dagobank.webapp.util.generator.BsnGenerator;
import nl.dagobank.webapp.util.generator.BusinessGenerator;
import nl.dagobank.webapp.util.generator.IbanIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class TestDataService {

    CustomerService customerService;
    CustomerDao customerDao;
    EmployeeDao employeeDao;
    BankAccountDao bankAccountDao;
    TransactionDao transactionDao;
    BusinessAccountDao businessAccountDao;
    PrivateAccountDao privateAccountDao;
    BankAccountHolderTokenDao bankAccountHolderTokenDao;
    LoginAttemptDao loginAttemptDao;
    TransactionService transactionService;

    Logger LOG = LogManager.getFormatterLogger(TestDataService.class);

    @Autowired
    public TestDataService(
            CustomerService customerService,
            CustomerDao customerDao,
            EmployeeDao employeeDao,
            BankAccountDao bankAccountDao,
            TransactionDao transactionDao,
            BusinessAccountDao businessAccountDao,
            PrivateAccountDao privateAccountDao,
            BankAccountHolderTokenDao bankAccountHolderTokenDao,
            LoginAttemptDao loginAttemptDao,
            TransactionService transactionService
    ) {
        this.customerService = customerService;
        this.customerDao = customerDao;
        this.employeeDao = employeeDao;
        this.bankAccountDao = bankAccountDao;
        this.transactionDao = transactionDao;
        this.businessAccountDao = businessAccountDao;
        this.privateAccountDao = privateAccountDao;
        this.bankAccountHolderTokenDao = bankAccountHolderTokenDao;
        this.loginAttemptDao = loginAttemptDao;
        this.transactionService = transactionService;
    }


    public void deleteAllLoginAttempts() {
        loginAttemptDao.deleteAll();
    }

    public void deleteAllBankAccountHolderTokens() {
        bankAccountHolderTokenDao.deleteAll();
    }

    public void deleteAllTransactions() {
        transactionDao.deleteAll();
    }

    public void deleteAllBusinessAccounts() {
        businessAccountDao.deleteAll();
    }

    public void deleteAllPrivateAccounts() {
        privateAccountDao.deleteAll();
    }

    public void deleteAllCustomers() {
        customerDao.deleteAll();
    }

    public void deleteAllEmployees() {
        employeeDao.deleteAll();
    }

    public void deleteAllBankAccounts() {
        deleteAllBankAccountHolderTokens();
        deleteAllTransactions();
        deleteAllBusinessAccounts();
        deleteAllPrivateAccounts();
    }

    public void deleteAllUsersData() {
        deleteAllLoginAttempts();
        deleteAllBankAccounts();
        deleteAllCustomers();
        deleteAllEmployees();
    }


    public void createStandardTestUsers() {
        if (!employeeDao.existsByRole("HoofdMKB")) {
            Employee hoofdMkb = new Employee();
            hoofdMkb.setRole("HoofdMKB");
            hoofdMkb.setFullName(new FullName("Klaus", "van", "Kogel"));
            hoofdMkb.setInlogCredentials(new InlogCredentials("hmkb", "hmkb"));
            hoofdMkb.setPersonalDetails(new PersonalDetails(LocalDate.now(), 111222333));
            hoofdMkb.setAddress(new Address("mkbstraat", 25, "A", "8271 AG", "Houten"));
            hoofdMkb.setContactDetails(new ContactDetails("0614736580", "hmkb@dagobank.nl"));
            employeeDao.save(hoofdMkb);
        }

        if (!employeeDao.existsByRole("HoofdParticulier")) {
            Employee hoofdParticulier = new Employee();
            hoofdParticulier.setRole("HoofdParticulier");
            hoofdParticulier.setFullName(new FullName("Rudolf", "de", "Gek"));
            hoofdParticulier.setInlogCredentials(new InlogCredentials("hp", "hp"));
            hoofdParticulier.setPersonalDetails(new PersonalDetails(LocalDate.now(), 111222335));
            hoofdParticulier.setAddress(new Address("particulierstraat", 25, "A", "8888 LS", "Houten"));
            hoofdParticulier.setContactDetails(new ContactDetails("0614736570", "hp@dagobank.nl"));
            employeeDao.save(hoofdParticulier);
        }

        if (!customerDao.existsByInlogCredentialsUserName("test")) {
            Customer testCustomer = new Customer();
            testCustomer.setFullName(new FullName("Jan", "", "Jansen"));
            testCustomer.setInlogCredentials(new InlogCredentials("test", "test"));
            testCustomer.setPersonalDetails(new PersonalDetails(LocalDate.now().minusYears(50), 111222334));
            testCustomer.setAddress(new Address("teststraat", 11, "B", "8888 UU", "Testplaats"));
            customerDao.save(testCustomer);
        }
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

        for (int i = 0; i < numberOfUsers; i++) {
            if (i % 50 == 0) {
                LOG.info(i + " Customers saved to database...");
            }
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

    public void giveUsersPrivateBankAccounts() {
        Iterator<Customer> allCustomers = customerDao.findAll().iterator();
        IbanIterator ibanIterator = new IbanIterator();
        // This method iterates over all customers
        // It finds all customers that do not have any private accounts
        // Then it adds as much private accounts as the customers last digit of its BSN devided by 2
        while (allCustomers.hasNext()) {
            Customer customer = allCustomers.next();
            List<PrivateAccount> allPrivateAccounts = privateAccountDao.findAllByAccountHolder(customer);
            if (allPrivateAccounts.size() == 0) {
                int bsn = customer.getPersonalDetails().getBsn();
                int lastNumberOfBsn = bsn % 10;
                int numberOfBankAccounts = lastNumberOfBsn / 2 + 1;
                for (int i = 0; i < numberOfBankAccounts; i++) {
                    PrivateAccount privateAccount = generateRandomPrivateAccount(customer, ibanIterator, i + 1);
                    privateAccountDao.save(privateAccount);
                }
                LOG.info(lastNumberOfBsn + " PrivateAccounts created for Customer " + customer.getId());
            }
        }
    }



    public PrivateAccount generateRandomPrivateAccount( Customer customer, IbanIterator ibanIterator, int numberInName) {
        PrivateAccount account = new PrivateAccount();
        int randomNum = ThreadLocalRandom.current().nextInt( 25, 2000 + 1 );
        BigDecimal balance = new BigDecimal( randomNum );

        account.setBalance( balance );
        account.setAccountName( "Betaalrekening " + (numberInName));
        account.setIban( ibanIterator.next().toString() );
        account.setAccountHolder( customer );

        return account;
    }

    public void giveUsersBusinessBankAccounts() {
        Iterator<Customer> allCustomers = customerDao.findAll().iterator();
        IbanIterator ibanIterator = new IbanIterator( 7000000000L );
        BusinessGenerator businessGenerator = new BusinessGenerator( 11122233 );

        while ( allCustomers.hasNext() ) {
            Customer customer = allCustomers.next();
            if ( customer.getId() % 5 == 0 ) {
                int lastNumberOfBsn = customer.getPersonalDetails().getBsn() % 10;
                Business mainBusiness = businessGenerator.next();
                businessAccountDao.save(generateRandomBusinessAccount( customer, mainBusiness, ibanIterator ));
                /*for ( int i = 0 ; i < lastNumberOfBsn ; i++ ) {
                    if ( i % 3 == 0 ) {
                        BusinessAccount businessAccount = generateRandomBusinessAccount( customer, businessGenerator.next(), ibanIterator );
                        businessAccountDao.save( businessAccount );
                    } else {
                        BusinessAccount businessAccount = generateRandomBusinessAccount( customer, mainBusiness, ibanIterator );
                        businessAccountDao.save( businessAccount );
                    }
                }*/
                LOG.info( "BusinessAccount created for Customer " + customer.getId() );
            }
        }
    }

    private BusinessAccount generateRandomBusinessAccount( Customer customer, Business business, IbanIterator ibanIterator ) {
        BusinessAccount account = new BusinessAccount( business.getBusinessName(), business.getKvkNumber(), business.getSbiCode() );
        account.setAccountName( "zakelijke rekening" );
        account.setIban( ibanIterator.next().toString() );
        account.setBalance( new BigDecimal( ThreadLocalRandom.current().nextInt( 25, 2000 + 1 ) ) );
        account.setAccountHolder( customer );
        return account;
    }

    public void generateTransactions() {
        List<BankAccount> allAccounts = bankAccountDao.findAll();
        int ceiling = allAccounts.size();
        for ( BankAccount account : allAccounts ) {
            int randomAmountOfTransactions = ThreadLocalRandom.current().nextInt( 1, 10 );
            for ( int i = 0 ; i < randomAmountOfTransactions ; i++ ) {
                int randomId = ThreadLocalRandom.current().nextInt( 0, ceiling );
                BigDecimal randomAmmount = new BigDecimal( ThreadLocalRandom.current().nextInt( 10, 10000 ) );
                if ( allAccounts.get(randomId).getId() != account.getId() ) {
                    transactionService.performTransaction( account, allAccounts.get( randomId ), randomAmmount, "transactie" );
                }
            }
        }
    }

    public void addRandomSecondaryBankAccountHolders() {
        List<BankAccount> allAccounts = bankAccountDao.findAll();
        int ceiling = allAccounts.size();
        boolean addAccountHolder = true;
        for (BankAccount account : allAccounts) {
            if (addAccountHolder) {
                int randomId = ThreadLocalRandom.current().nextInt(0, ceiling-1);
                account.getSecondaryAccountHolders().add(allAccounts.get(randomId).getAccountHolder());
                allAccounts.get(randomId).getSecondaryAccountHolders().add(account.getAccountHolder());
                bankAccountDao.save(account);
                addAccountHolder = false;
                LOG.info("Add secondary account holder voor user " + account.getAccountHolder().getFullName());
            } else {
                addAccountHolder = true;
            }
        }
    }


        private int getRandomIndex(int size){
                return (int)(Math.random() * size);
        }


}
