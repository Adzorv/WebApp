package nl.dagobank.webapp.service;

import lombok.RequiredArgsConstructor;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BankAccountHolderTokenDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean existsValidToken(Customer customer, String iban, String connectionCode){
        return (bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanAndConnectionCode(customer, iban, connectionCode).size() > 0);
    }

    public List<BankAccountHolderToken> getValidTokens(Customer customer, String iban, String connectionCode){
        return bankAccountHolderTokenDao.findAllByBecomingSecundaryAccountHolderAndAccountToAdd_IbanAndConnectionCode(customer, iban, connectionCode);
    }

    public void deleteTokens(List<BankAccountHolderToken> bankAccountHolderTokens){
        bankAccountHolderTokenDao.deleteAll(bankAccountHolderTokens);
    }




}
