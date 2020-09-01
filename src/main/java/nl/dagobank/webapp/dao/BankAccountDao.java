package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import org.iban4j.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {
    boolean existsByIban(Iban iban);
    BankAccount findAllByAccountHolder(String accountHolder);
}

