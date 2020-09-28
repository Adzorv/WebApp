package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.dto.SumBalancePrivateAccounts;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import org.iban4j.Iban;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateAccountDao extends CrudRepository<PrivateAccount, Integer> {
    boolean existsByIban(Iban iban);

    @Query( "SELECT pa.accountHolder AS accountHolder, SUM(pa.balance) AS sumBalance FROM PrivateAccount AS pa GROUP BY accountHolder")
    List<SumBalancePrivateAccounts> getSumBalancePerPrivateAccount( Pageable pageable);

    List<PrivateAccount> findAllByAccountHolder( Customer customer );
}





//public interface SumBalancePrivateAccounts {
//    BigDecimal getSumBalance();
//    String getNameAccountHolder();
//}


//    @Query( "SELECT AVG(b.balance) from BankAccount b" )
//    BigDecimal getAverageBalance();
//
//
//    @Query( "SELECT new nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness( b.businessName, SUM(b.balance) ) " +
//            "FROM BusinessAccount AS b GROUP BY b.businessName, b.balance ORDER BY b.balance DESC" )
//    List<BalanceSumPerBusiness> getSumBalance(Pageable pageable );
//
//    @Query( "SELECT ba.sbiCode as sbiCode, AVG(ba.balance) AS balanceAverage FROM BusinessAccount AS ba WHERE sbiCode IS NOT NULL GROUP BY sbiCode ORDER BY balanceAverage DESC") //TODO ORDER BY
//    List<SbiAverage> getAverageBalancePerSector();

