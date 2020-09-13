package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    TransactionDao transactionDao;

    @Autowired
    public TransferService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public boolean getFundsFromSendingAccount(BigDecimal amount, BankAccount bankAccount) {
        if (bankAccount.getBalance().compareTo(amount) >= 0) {
            bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
            return true;
        } else {
            return false;
        }
    }

    public void putFundsInReceivingAccount(BankAccount bankAccount, BigDecimal amount) {
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
    }
}