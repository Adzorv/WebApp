package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.*;
import org.iban4j.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {
    boolean existsByIban(String iban);
    List<BankAccount> findAllByAccountHolder(User accountHolder);
    List<BankAccount> findAllByAccountHolderOrSecondaryAccountHoldersContains(Customer customer, Customer customer2);
    BankAccount findByIban(String iban);
}
//fixme: duplicate code

