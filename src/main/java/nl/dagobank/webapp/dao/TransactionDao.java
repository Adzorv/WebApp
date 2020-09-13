package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.*;
import org.iban4j.Iban;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByDebitAccount( BankAccount debitAccount );

    List<Transaction> findAllByCreditAccount( BankAccount creditAccount );

    List<Transaction> findAllByDebitAccountOrCreditAccountOrderByDate( BankAccount debitAccount, BankAccount creditAccount );

    @Query( "SELECT COUNT(*), BA.id FROM bank_account BA JOIN transaction T ON BA.id = T.credit_account_id OR BA.id = T.debit_account_id GROUP BY BA.business_name" )
    List<> findSumOfTransactsionsPerBusinessAccount( Pageable pageAble );

//    @Query(value = "SELECT c.year AS yearComment, COUNT(c.*) AS totalComment "
//            + "FROM comment AS c GROUP BY c.year ORDER BY c.year DESC", nativeQuery = true)
//    List<ICommentCount> countTotalCommentsByYearNative();

}

