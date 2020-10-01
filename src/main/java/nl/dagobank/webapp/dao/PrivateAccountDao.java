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

    @Query( "SELECT pa.accountHolder AS accountHolder, SUM(pa.balance) AS sumBalance FROM PrivateAccount AS pa GROUP BY accountHolder ORDER BY sumBalance DESC")
    List<SumBalancePrivateAccounts> getSumBalancePerPrivateAccount( Pageable pageable);

    List<PrivateAccount> findAllByAccountHolder( Customer customer );
}


