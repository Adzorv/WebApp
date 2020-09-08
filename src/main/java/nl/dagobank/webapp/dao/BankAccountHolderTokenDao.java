package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.BankAccountHolderToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountHolderTokenDao extends CrudRepository<BankAccountHolderToken, Integer> {
}
