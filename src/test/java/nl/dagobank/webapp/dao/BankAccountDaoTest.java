package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.*;
import static org.assertj.core.api.Assertions.*;
import nl.dagobank.webapp.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest(properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class BankAccountDaoTest {

    @Test
    public void testetest(){
        System.out.println("just a test");
    }

    public BankAccountDaoTest(){ super(); }

    Customer customer1 = new Customer();
    Customer customer2 = new Customer();
    Customer customer3 = new Customer();

    FullName fullNameCustomer1 = new FullName("Customer", "van", "1");
    FullName fullNameCustomer2 = new FullName("Customer", "de", "2");
    FullName fullNameCustomer3 = new FullName("Customer", "ter", "3");

    Address address = new Address("teststraat", 5, "","8888AA", "Testcity");


    PersonalDetails personalDetails = new PersonalDetails( LocalDate.of(1977,12,1),  111222333 );

    InlogCredentials inlogCredentialsCustomer1 = new InlogCredentials("Cus1", "cus1");
    InlogCredentials inlogCredentialsCustomer2 = new InlogCredentials("Cus2", "cus2");
    InlogCredentials inlogCredentialsCustomer3 = new InlogCredentials("Cus3", "cus3");

    BankAccount bankAccount1 = new PrivateAccount();
    BankAccount bankAccount2 = new PrivateAccount();
    BankAccount bankAccount3 = new PrivateAccount();
    BankAccount bankAccount4 = new PrivateAccount();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BankAccountDao bankAccountDao;


    @Test
    public void findAllByAccountHolderTest(){

        customer1.setFullName(fullNameCustomer1);
        customer2.setFullName(fullNameCustomer2);
        customer3.setFullName((fullNameCustomer3));

        customer1.setAddress(address);
        customer2.setAddress(address);
        customer3.setAddress(address);

        customer1.setPersonalDetails(personalDetails);
        customer2.setPersonalDetails(personalDetails);
        customer3.setPersonalDetails(personalDetails);



        customer1.setInlogCredentials(inlogCredentialsCustomer1);
        customer2.setInlogCredentials(inlogCredentialsCustomer2);
        customer3.setInlogCredentials(inlogCredentialsCustomer3);

        bankAccount1.setAccountName("Private Account 1");
       // bankAccount1.setId(1);
        bankAccount1.setBalance(new BigDecimal(25));
        bankAccount1.setIban("IBAN1111111111");

        bankAccount2.setAccountName("Private Account 2");
        //bankAccount2.setId(2);
        bankAccount2.setBalance(new BigDecimal(25));
        bankAccount2.setIban("IBAN2222222222");

        bankAccount3.setAccountName("Private Account 3");
       // bankAccount3.setId(3);
        bankAccount3.setBalance(new BigDecimal(25));
        bankAccount3.setIban("IBAN3333333333");

        bankAccount4.setAccountName("Private Account 4");
        //bankAccount4.setId(3);
        bankAccount4.setBalance(new BigDecimal(25));
        bankAccount4.setIban("IBAN4444444444");

        bankAccount1.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount2.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount3.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount4.setSecondaryAccountHolders(new ArrayList<>());

        bankAccount1.setAccountHolder(customer1);
        bankAccount2.setAccountHolder(customer2);
        bankAccount3.setAccountHolder(customer3);
        bankAccount4.setAccountHolder(customer1);

        bankAccount1.getSecondaryAccountHolders().add(customer2);
        bankAccount1.getSecondaryAccountHolders().add(customer3);
        bankAccount2.getSecondaryAccountHolders().add(customer1);

        entityManager.persist(customer1);
        entityManager.persist(customer2);
        entityManager.persist(customer3);
        entityManager.flush();

        entityManager.persist(bankAccount1);
        entityManager.persist(bankAccount2);
        entityManager.persist(bankAccount3);
        entityManager.persist(bankAccount4);
        entityManager.flush();

        List<BankAccount> found = bankAccountDao.findAllByAccountHolder(customer1);
        assertThat(found).as("findAllByAccountHolderTest").hasSize(2);
    }

}
