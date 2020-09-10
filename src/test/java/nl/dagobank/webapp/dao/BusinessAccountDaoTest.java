package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

//@DataJpaTest
//@RunWith( SpringRunner.class )
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class BusinessAccountDaoTest {

    @Autowired
    BusinessAccountDao businessAccountDao;

//    @Autowired
//    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerDao customerDao;


    @BeforeEach
    public void setUp() {

    }

    @Test
    void findAllByAccountHolder() {
    }


    @Test
    void findAllBySecondaryAccountHoldersContaining() {
//        Customer customer = new Customer();
//        customer.setBsn(602081269);
//        String password = "test";
//        customer.setPassword(password);
//        String userName = "businessaccountdao";
//        customer.setUserName(userName);
//
//        Customer customer2 = new Customer();
//        customer2.setBsn(277131741);
//        password = "test";
//        customer2.setPassword(password);
//        userName = "businessaccountdao2";
//        customer2.setUserName(userName);
//
//        Customer customer3 = new Customer();
//        customer3.setBsn(277131741);
//        password = "test";
//        customer3.setPassword(password);
//        userName = "businessaccountdao3";
//        customer3.setUserName(userName);
//
//        testEntityManager.persist( customer );
////        testEntityManager.persist( customer2 );
////        testEntityManager.persist( customer3 );
//        testEntityManager.flush();
//
//        Customer found = customerDao.findByBsn( 602081269 );
//        System.out.println(customer.getBsn());
//        System.out.println(found);
//
//        assertThat(found.getBsn()).isEqualTo( customer.getBsn() );


        Optional<BusinessAccount> bankAccountOptional = businessAccountDao.findById( 31 );
        BusinessAccount ba;
        if ( bankAccountOptional.isPresent() ) {
            ba = bankAccountOptional.get();
            System.out.println( ba.getSecondaryAccountHolders() );
            Customer customer = customerDao.findById( 1 ).get();

            List<BusinessAccount> accounts = businessAccountDao.findAllByAccountHolderOrSecondaryAccountHoldersContains( customer, customer );

            System.out.println( accounts );

            assertThat(accounts).contains( ba );
        } else {
            fail();
        }




//        Customer customer = customerDao.findById( 30 ).get();
//        System.out.println( customer );
//        List<BusinessAccount> accounts = businessAccountDao.findAllBySecondaryAccountHoldersContains( customer );
//
//        assertThat( accounts ).isNotEmpty();
//
//        List<BusinessAccount> accounts2 = businessAccountDao.findAllBySecondaryAccountHoldersContainsOrAccountHolderIs( customer, customer );
//        assertThat( accounts2 ).isNotEmpty();
//
//        assertThat( accounts.size() ).isLessThan( accounts2.size() );

    }

    @Test
    void getAverageBalance() {
        BigDecimal average = businessAccountDao.getAverageBalance();
        assertThat(average)
                .isNotNull()
                .isNotZero();
    }

    @Test
    void getSumBalance() {
        List<BalanceSumPerBusiness> sums = businessAccountDao.getSumBalance( PageRequest.of(0,10) );
        assertThat(sums)
                .isNotNull()
                .isNotEmpty();

        sums.forEach( System.out::println );
    }
}