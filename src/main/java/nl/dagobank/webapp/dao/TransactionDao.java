package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.*;
import org.iban4j.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByDebitAccount(BankAccount debitAccount );
    List<Transaction> findAllByCreditAccount(BankAccount creditAccount );

    List<Transaction> findAllByDebitAccountOrCreditAccountOrderByDate(BankAccount debitAccount, BankAccount creditAccount);

}

