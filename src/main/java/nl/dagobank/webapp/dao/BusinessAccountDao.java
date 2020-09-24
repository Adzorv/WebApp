package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.dao.dto.BalanceSumPerBusiness;
import nl.dagobank.webapp.dao.dto.SbiAverage;
import nl.dagobank.webapp.domain.BusinessAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BusinessAccountDao extends CrudRepository<BusinessAccount, Integer> {

    Boolean existsByKvkNumber(int kvkNumber);

    @Query( "SELECT AVG(b.balance) from BankAccount b" )
    BigDecimal getAverageBalance();


    @Query( "SELECT b.businessName as businessName, SUM(b.balance) as balance " +
                    "FROM BusinessAccount AS b GROUP BY businessName ORDER BY balance DESC" )
    List<BalanceSumPerBusiness> getSumBalance( Pageable pageable );

    @Query( "SELECT ba.sbiCode as sbiCode, AVG(ba.balance) AS balanceAverage FROM BusinessAccount AS ba WHERE sbiCode IS NOT NULL GROUP BY sbiCode ORDER BY balanceAverage DESC")
    List<SbiAverage> getAverageBalancePerSector();
}
