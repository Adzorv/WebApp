package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    Customer findByUserPersonalDetailsBsn(int bsn);

    Customer findAllByUserAddressCity(String city);

    boolean existsByUserInlogCredentialsUserName(String userName);

    Optional<Customer> findByUserInlogCredentialsUserName(String userName);

}


