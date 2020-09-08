package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.*;
import org.iban4j.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer> {

    // Alle bij en afschrijvingen per iban
//    List<Transaction> findAllByIban(Iban iban);

//    List<Transaction> findAllByAccountName(String accountName);


    List<Transaction> findAllByDebitAccount(BankAccount debitAccount );
    List<Transaction> findAllByCreditAccount(BankAccount creditAccount );


//    List<Email> findBy.....(List<String> emails, List<String> pinCodes);

//    List<Email> findByEmailIdInAndPincodeIn(List<String> emails, List<String> pinCodes);





}

