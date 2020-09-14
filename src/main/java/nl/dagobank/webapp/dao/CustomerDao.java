package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    Customer findByPersonalDetailsBsn( int bsn);

    Customer findAllByAddressCity( String city);

    boolean existsByInlogCredentialsUserName( String userName);

    Optional<Customer> findByInlogCredentialsUserName( String userName);

}


