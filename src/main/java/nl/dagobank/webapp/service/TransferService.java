package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransferService {

    @Autowired
    TransactionDao transactionDao;
    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    public TransferService() {    }

    public void getFundsFromSendingAccount(BigDecimal amount, BankAccount bankAccount) {
            bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
            bankAccountDao.save(bankAccount);
    }

    public boolean checkBalanceBeforeTransfer(BigDecimal amount, BankAccount bankAccount) {
        if (bankAccount.getBalance().compareTo(amount) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void putFundsInReceivingAccount(BankAccount bankAccount, BigDecimal amount) {
        BigDecimal updatedBalance = bankAccount.getBalance();
        updatedBalance = updatedBalance.add(amount);
        bankAccount.setBalance(updatedBalance);
        bankAccountDao.save(bankAccount);
    }

    public void createAndSaveTransaction(BankAccount sender, BankAccount receiver, BigDecimal amount, String description, LocalDate date) {
        Transaction transfer = new Transaction(sender, receiver, amount, description, date);
        transactionDao.save(transfer);
    }
}