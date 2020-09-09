package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAccountDao extends CrudRepository<BusinessAccount, Integer> {

    List<BusinessAccount> findAllByAccountHolder(Customer customer);
    List<BusinessAccount> findAllBySecondaryAccountHoldersContaining(Customer customer);
    List<BusinessAccount> findAllBySecondaryAccountHoldersContains(Customer customer);
    List<BusinessAccount> findAllBySecondaryAccountHoldersContainsOrAccountHolderIs(Customer customer, Customer customer2);

    List<BusinessAccount> findAllBySecondaryAccountHolders(Customer customer);

}
