package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.dao.dto.SumTransactionsPerBusiness;
import nl.dagobank.webapp.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByDebitAccount( BankAccount debitAccount );

    List<Transaction> findAllByCreditAccount( BankAccount creditAccount );

    List<Transaction> findAllByDebitAccountOrCreditAccountOrderByDate( BankAccount debitAccount, BankAccount creditAccount );

    @Query( value = "SELECT COUNT(*) AS sumTransactions, BA.business_name AS businessName, " +
            "sum(CASE WHEN BA.id = T.credit_account_id THEN 1 ELSE 0 END) AS creditCount, " +
            "sum(CASE WHEN BA.id = T.debit_account_id THEN 1 ELSE 0 END) AS debitCount " +
            "FROM bank_account BA JOIN transaction T ON BA.id = T.credit_account_id OR BA.id = T.debit_account_id " +
            "WHERE dtype = 'BusinessAccount'" +
            "GROUP BY BA.business_name", nativeQuery = true )
    List<SumTransactionsPerBusiness> findSumOfTransactsionsPerBusinessAccount( Pageable pageable );

//    @Query(value = "SELECT c.year AS yearComment, COUNT(c.*) AS totalComment "
//            + "FROM comment AS c GROUP BY c.year ORDER BY c.year DESC", nativeQuery = true)
//    List<ICommentCount> countTotalCommentsByYearNative();

}

