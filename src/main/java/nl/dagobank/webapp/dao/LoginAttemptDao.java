package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptDao extends CrudRepository<LoginAttemptDao, Integer> {
    LoginAttemptDao findByCustomer( Customer customer );
}
