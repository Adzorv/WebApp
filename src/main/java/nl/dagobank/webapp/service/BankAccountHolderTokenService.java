package nl.dagobank.webapp.service;

import lombok.RequiredArgsConstructor;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BankAccountHolderTokenDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import org.springframework.stereotype.Service;

@Service
public class BankAccountHolderTokenService {

    private final BankAccountHolderTokenDao bankAccountHolderTokenDao;

    public BankAccountHolderTokenService(BankAccountHolderTokenDao bankAccountHolderTokenDao) {
        super();
        this.bankAccountHolderTokenDao = bankAccountHolderTokenDao;
    }

    public void saveBankAccountHolderToken(BankAccountHolderToken bankAccountHolderToken){
        bankAccountHolderTokenDao.save(bankAccountHolderToken);
    }

}
