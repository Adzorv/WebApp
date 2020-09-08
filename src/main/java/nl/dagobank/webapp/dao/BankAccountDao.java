package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.User;
import org.iban4j.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {
    boolean existsByIban(Iban iban);
    List<BankAccount> findAllByAccountHolder(User accountHolder);





}

