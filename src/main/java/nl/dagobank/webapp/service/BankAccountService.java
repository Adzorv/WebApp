package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    BankAccountDao bankAccountDao;

    @Autowired
    public BankAccountService(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public BankAccount getBankAccountById(int id){
        return bankAccountDao.findById(id).get();
    }

    public int getNumberOfBankAccountsOfCustomer(Customer customer){
        return bankAccountDao.findAllByAccountHolder(customer).size();
    }

    public boolean isCustomerSecondAccountHolder(Customer customer, BankAccount bankAccount){
        return bankAccountDao.findById(bankAccount.getId()).get().getSecondaryAccountHolders().contains(customer);
    }
}
