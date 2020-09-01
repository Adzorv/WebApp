package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    Customer findByBsn(int bsn);

    Customer findAllByCity(String city);

    boolean existsByUserName(String userName);

    Optional<Customer> findByUserName(String userName);

}


