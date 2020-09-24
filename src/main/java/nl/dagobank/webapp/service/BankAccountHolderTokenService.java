package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountHolderTokenDao;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountHolderTokenService {

    private final BankAccountHolderTokenDao bankAccountHolderTokenDao;

    @Autowired
    public BankAccountHolderTokenService(BankAccountHolderTokenDao bankAccountHolderTokenDao) {
        super();
        this.bankAccountHolderTokenDao = bankAccountHolderTokenDao;
    }

    public void saveBankAccountHolderToken(BankAccountHolderToken bankAccountHolderToken){
        bankAccountHolderTokenDao.save(bankAccountHolderToken);
    }

    public boolean existsValidToken(Customer customer, String iban, String connectionCode){
        return (bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanAndConnectionCode(customer, iban, connectionCode).size() > 0);
    }

    public List<BankAccountHolderToken> getValidTokens(Customer customer, String iban){
        return bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolderAndAccountToAdd_Iban(customer, iban);
    }

    public void deleteTokens(List<BankAccountHolderToken> bankAccountHolderTokens){
        bankAccountHolderTokenDao.deleteAll(bankAccountHolderTokens);
    }




}
