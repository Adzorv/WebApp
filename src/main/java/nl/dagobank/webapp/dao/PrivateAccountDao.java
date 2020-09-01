package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.PrivateAccount;
import org.iban4j.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateAccountDao extends CrudRepository<PrivateAccount, Integer> {
    boolean existsByIban(Iban iban);
}

