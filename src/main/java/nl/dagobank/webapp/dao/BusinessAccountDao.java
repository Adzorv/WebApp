package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BusinessAccountDao extends CrudRepository<BusinessAccount, Integer> {

    List<BusinessAccount> findAllByAccountHolder( Customer customer );

    List<BusinessAccount> findAllBySecondaryAccountHoldersContaining( Customer customer );

    List<BusinessAccount> findAllBySecondaryAccountHoldersContains( Customer customer );

    List<BusinessAccount> findAllByAccountHolderOrSecondaryAccountHoldersContains( Customer customer, Customer customer2 );

    List<BusinessAccount> findAllBySecondaryAccountHolders( Customer customer );

    @Query( "SELECT AVG(b.balance) from BankAccount b" )
    BigDecimal getAverageBalance();


    @Query( "SELECT new nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness( b.businessName, SUM(b.balance) ) " +
                    "FROM BusinessAccount AS b GROUP BY b.businessName, b.balance ORDER BY b.balance DESC" )
    List<BalanceSumPerBusiness> getSumBalance( Pageable pageable );

    @Query( "SELECT ba.sbiCode as sbiCode, AVG(ba.balance) AS balanceAverage FROM BusinessAccount AS ba WHERE sbiCode IS NOT NULL GROUP BY sbiCode ORDER BY balanceAverage DESC")
    List<SbiAverage> getAverageBalancePerSector();
}
