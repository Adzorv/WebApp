package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BankAccountHolderTokenDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountHolderTokenService {

    BankAccountHolderToken bankAccountHolderToken;
    CustomerDao customerDao;
    BankAccountDao bankAccountDao;
    BankAccountHolderTokenDao bankAccountHolderTokenDao;

    @Autowired
    public BankAccountHolderTokenService(CustomerDao customerDao, BankAccountDao bankAccountDao, BankAccountHolderTokenDao bankAccountHolderTokenDao) {
        super();
        this.bankAccountDao = bankAccountDao;
        this.customerDao = customerDao;
        this.bankAccountHolderTokenDao = bankAccountHolderTokenDao;
    }

    public void saveBankAccountHolderToken(BankAccountHolderToken bankAccountHolderToken){
        bankAccountHolderTokenDao.save(bankAccountHolderToken);
    }

}
