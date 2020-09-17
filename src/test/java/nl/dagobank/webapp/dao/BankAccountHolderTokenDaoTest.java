package nl.dagobank.webapp.dao;

import static org.assertj.core.api.Assertions.*;
import nl.dagobank.webapp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest(properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class BankAccountHolderTokenDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BankAccountHolderTokenDao bankAccountHolderTokenDao;

    Customer customer = new Customer();
    FullName fullNameCustomer = new FullName("Customer", "ter", "3");
    Address address = new Address("teststraat", 5, "","8888AA", "Testcity");
    PersonalDetails personalDetails = new PersonalDetails( LocalDate.of(1977,12,1),  111222333 );
    InlogCredentials inlogCredentialsCustomer = new InlogCredentials("Cus3", "cus3");
    BankAccount bankAccount = new PrivateAccount();
    BankAccountHolderToken token;

    @BeforeEach
    public void setup(){
        customer.setFullName(fullNameCustomer);
        customer.setAddress(address);
        customer.setPersonalDetails(personalDetails);
        customer.setInlogCredentials(inlogCredentialsCustomer);
        bankAccount.setAccountName("Private Account 2");
        bankAccount.setBalance(new BigDecimal(25));
        bankAccount.setIban("IBAN2222222222");
        bankAccount.setSecondaryAccountHolders(new ArrayList<>());

        entityManager.persist(customer);
        entityManager.flush();

        entityManager.persist(bankAccount);
        entityManager.flush();

        token = new BankAccountHolderToken(customer, "11111", bankAccount);
        entityManager.persist(token);
        entityManager.flush();
    }

    @Test
    void findAllByBecomingSecundaryAccountHolderTest(){
        List<BankAccountHolderToken> found = bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolder(customer);
        assertThat(found).hasSize(1);
    }

    @Test
    void findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanAndConnectionCodeTest(){
        List<BankAccountHolderToken> found = bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanAndConnectionCode(
                customer,
                bankAccount.getIban(),
                "11111");
        assertThat(found).hasSize(1);
    }

    @Test
    void findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanTest(){
        List<BankAccountHolderToken> found = bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolderAndAccountToAdd_Iban(
                customer,
                bankAccount.getIban());
        assertThat(found).hasSize(1);
    }
}
