package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    boolean findByBsn(int bsn);

}


