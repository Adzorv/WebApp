package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.LoginAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoginAttemptDao extends CrudRepository<LoginAttempt, Integer> {
    Optional<LoginAttempt> findByCustomer( Customer customer );
}

