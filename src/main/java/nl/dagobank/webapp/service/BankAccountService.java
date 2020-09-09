package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<BankAccount> findAllByAccountHolder(Customer customer){
        return bankAccountDao.findAllByAccountHolder(customer);
    }

    public List<BankAccount> getAllAccountsFromCustomer(Customer customer){
        List<BankAccount> bankaccounts = bankAccountDao.findAllByAccountHolderOrSecondaryAccountHoldersContains(customer, customer);
        List<BankAccount> sortedBankAccounts = new ArrayList<>();
        for (BankAccount bankAccount : bankaccounts) {
            if (bankAccount.getAccountHolder().equals(customer)){
                sortedBankAccounts.add(bankAccount);
            }
        }
        for (BankAccount bankAccount : bankaccounts) {
            if (!bankAccount.getAccountHolder().equals(customer)){
                sortedBankAccounts.add(bankAccount);
            }
        }
        return sortedBankAccounts;
    }

    public int getNumberOfBankAccountsOfCustomer(Customer customer){
        return bankAccountDao.findAllByAccountHolder(customer).size();
    }

    public boolean isCustomerSecondAccountHolder(Customer customer, BankAccount bankAccount){
        return bankAccountDao.findById(bankAccount.getId()).get().getSecondaryAccountHolders().contains(customer);
    }


}
