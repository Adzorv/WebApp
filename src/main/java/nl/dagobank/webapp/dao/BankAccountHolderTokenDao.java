package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountHolderTokenDao extends CrudRepository<BankAccountHolderToken, Integer> {

    List<BankAccountHolderToken> findAllByBecomingSecundaryAccountHolder(Customer customer);
    List<BankAccountHolderToken> findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanAndConnectionCode(Customer customer, String iban, String connectionCode);
    List<BankAccountHolderToken> findAllByBecomingSecundaryAccountHolderAndAccountToAdd_Iban(Customer customer, String iban);
}
