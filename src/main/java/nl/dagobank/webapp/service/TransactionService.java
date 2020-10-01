package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Transaction;
import nl.dagobank.webapp.dto.SumTransactionsPerBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    TransactionDao transactionDao;
    BankAccountDao bankAccountDao;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    public TransactionService(TransactionDao transactionDao,
                              BankAccountDao bankAccountDao) {
        this.transactionDao = transactionDao;
        this.bankAccountDao = bankAccountDao;
    }

    public List<SumTransactionsPerBusiness> getTop10SumTransactions() {
        return transactionDao.findSumOfTransactsionsPerBusinessAccount(PageRequest.of(0, 10));
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionDao.findAll();
    }

    public void getFundsFromSendingAccount(BigDecimal amount, BankAccount bankAccount) {
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        bankAccountDao.save(bankAccount);
    }

    public boolean checkIfEnoughBalance(BigDecimal amount, BankAccount bankAccount) {
        return (bankAccount.getBalance().compareTo(amount) >= 0);
    }

    /*public boolean checkIfIbanExists(BankAccount receiver) {
        if (bankAccountDao.existsByIban(receiver.getIban())) {
            return true;
        }else{
            return false;
        }
    }*/

    public void putFundsInReceivingAccount(BankAccount bankAccount, BigDecimal amount) {
        BigDecimal updatedBalance = bankAccount.getBalance();
        updatedBalance = updatedBalance.add(amount);
        bankAccount.setBalance(updatedBalance);
        bankAccountDao.save(bankAccount);
    }

    public void createAndSaveTransaction(BankAccount sender, BankAccount receiver, BigDecimal amount, String
            description, LocalDate date) {
        Transaction transfer = new Transaction(sender, receiver, amount, description, date);
        transactionDao.save(transfer);
    }

    public boolean performTransaction(BankAccount sender, BankAccount receiver, BigDecimal amount, String
            description) {
        if (!checkIfEnoughBalance(amount, sender) || receiver == null) {
            return false;
        } else {
            getFundsFromSendingAccount(amount, sender);
            putFundsInReceivingAccount(receiver, amount);
            createAndSaveTransaction(sender, receiver, amount, description, LocalDate.now());
            return true;
        }
    }
}