package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness;
import nl.dagobank.webapp.dao.dto.SbiAverage;
import nl.dagobank.webapp.domain.BusinessAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

//@DataJpaTest
//@RunWith( SpringRunner.class )
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest( properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" )
class BusinessAccountDaoTest {

    @Autowired
    BusinessAccountDao businessAccountDao;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CustomerDao customerDao;

    private List<Integer> amounts1, amounts2;


    @BeforeEach
    public void setUp() {
        BusinessAccount businessAccount1 = new BusinessAccount();
        BusinessAccount businessAccount2 = new BusinessAccount();
        BusinessAccount businessAccount3 = new BusinessAccount();
        BusinessAccount businessAccount4 = new BusinessAccount();

        //Sector A => Company1
        businessAccount1.setSbiCode( "A" );
        businessAccount2.setSbiCode( "A" );
        businessAccount3.setSbiCode( "A" );
        businessAccount1.setBusinessName( "Company1" );
        businessAccount2.setBusinessName( "Company1" );
        businessAccount3.setBusinessName( "Company1" );

        //Sector B => Company2
        businessAccount4.setBusinessName( "Company2" );
        businessAccount4.setSbiCode( "B" );

        //Set Balance
        amounts1 = new ArrayList<>( Arrays.asList( 100, 200, 300 ) );
        amounts2 = new ArrayList<>( Arrays.asList( 400 ) );

        businessAccount1.setBalance( new BigDecimal( amounts1.get( 0 ) ) );
        businessAccount2.setBalance( new BigDecimal( amounts1.get( 1 ) ) );
        businessAccount3.setBalance( new BigDecimal( amounts1.get( 2 ) ) );
        businessAccount4.setBalance( new BigDecimal( amounts2.get( 0 ) ) );

        entityManager.persist( businessAccount1 );
        entityManager.persist( businessAccount2 );
        entityManager.persist( businessAccount3 );
        entityManager.persist( businessAccount4 );
        entityManager.flush();
    }


    @Test
    void getSumBalance() {
        //Calculate sum
        BigDecimal sumCompany1 = new BigDecimal( amounts1.stream().mapToDouble( i -> i ).sum() );
        BigDecimal sumCompany2 = new BigDecimal( amounts2.stream().mapToDouble( i -> i ).sum() );

        List<BalanceSumPerBusiness> sums = businessAccountDao.getSumBalance( PageRequest.of( 0, 10 ) );
        assertThat( sums )
                .isNotNull()
                .isNotEmpty()
                .hasSize( 2 );
        assertThat( sums.get( 0 ).getBalance() )
                .isGreaterThan( sums.get( 1 ).getBalance() );
        assertThat( sumCompany1.equals( sums.get( 0 ).getBalance() ) );
        assertThat( sumCompany2.equals( sums.get( 1 ).getBalance() ) );
    }

    @Test
    void getAverageBalanceBySbiCode() {
        BigDecimal averageSectorA = new BigDecimal( amounts1.stream().mapToDouble( i -> i ).average().orElse( 0.0 ) );
        BigDecimal averageSectorB = new BigDecimal( amounts2.stream().mapToDouble( i -> i ).average().orElse( 0.0 ) );

        List<SbiAverage> result = businessAccountDao.getAverageBalancePerSector();
        assertThat( result )
                .isNotNull()
                .isNotEmpty()
                .hasSize( 2 );
        assertThat( averageSectorA.equals( result.get( 1 ).getBalanceAverage() ) );
        assertThat( averageSectorB.equals( result.get( 0 ).getBalanceAverage() ) );
    }
}