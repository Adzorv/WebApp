package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAccountDao extends CrudRepository<BusinessAccount, Integer> {

    List<BusinessAccount> findAllByAccountHolder(Customer customer);

}
