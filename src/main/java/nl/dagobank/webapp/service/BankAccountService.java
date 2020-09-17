package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness;
import nl.dagobank.webapp.controller.OpenPrivateBankAccountController;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.dto.SbiAverage;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankAccountService {

    static final BigDecimal BANKACCOUNT_BEGINBALANCE_GIFT = new BigDecimal(25);
    IbanGenerator ibanGenerator;
    BankAccountDao bankAccountDao;
    BusinessAccountDao businessAccountDao;


    @Autowired
    public BankAccountService( BankAccountDao bankAccountDao, BusinessAccountDao businessAccountDao, IbanGenerator ibanGenerator ) {
        this.bankAccountDao = bankAccountDao;
        this.businessAccountDao = businessAccountDao;
        this.ibanGenerator = ibanGenerator;
    }

    public BankAccount getBankAccountById( int id ) throws NoSuchElementException {
        return bankAccountDao.findById( id ).get();
    }

    public BankAccount findBankAccountByIban(String iban){
        return bankAccountDao.findByIban(iban);
    }

    public List<BankAccount> findAllByAccountHolder(Customer customer){
        return bankAccountDao.findAllByAccountHolder(customer);
    }

    public List<BankAccount> getAllAccountsFromCustomer( Customer customer) {
        List<BankAccount> bankaccounts = bankAccountDao.findAllByAccountHolderOrSecondaryAccountHoldersContains(customer, customer);
        List<BankAccount> sortedBankAccounts = new ArrayList<>();
        for ( BankAccount bankAccount : bankaccounts ) {
            if ( bankAccount.getAccountHolder().equals(customer) ) {
                sortedBankAccounts.add( bankAccount );
            }
        }
        for ( BankAccount bankAccount : bankaccounts ) {
            if ( !bankAccount.getAccountHolder().equals(customer) ) {
                sortedBankAccounts.add( bankAccount );
            }
        }
        return sortedBankAccounts;
    }




    public int getNumberOfBankAccountsOfCustomer( Customer customer ) {
        return bankAccountDao.findAllByAccountHolder(customer).size();
    }

    public boolean isCustomerSecondAccountHolder(Customer customer, BankAccount bankAccount ) {
        return bankAccountDao.findById( bankAccount.getId() ).get().getSecondaryAccountHolders().contains(customer);
    }


    public boolean isCustomerFirstOrSecundairyAccountHolder(Customer customer, BankAccount bankAccount){
       return ( bankAccount.getAccountHolder().equals(customer) || bankAccount.getSecondaryAccountHolders().contains(customer) );
    }

    public List<BalanceSumPerBusiness> getTop10Businesses() {
        return businessAccountDao.getSumBalance( PageRequest.of( 0, 10 ) );
    }

    public List<SbiAverage> getAverageBalancePerSector() {
        return businessAccountDao.getAverageBalancePerSector();
    }

    public PrivateAccount savePrivateAccount(PrivateAccount privateAccount){
        bankAccountDao.save(privateAccount);
        return privateAccount;
    }

    public boolean isCompanyValid(int kvkNumber) {
        return businessAccountDao.existsByKvkNumber(kvkNumber);
    }


    public void generateBankAccountNameAndPutInModel(Model model, ModelAndView modelAndView) {
        Customer user = (Customer) model.getAttribute("user");
        int aantalRekeningenVanUser = getNumberOfBankAccountsOfCustomer(user);
        String bankAccountName = user.getFullName() + "'s rekening " + (aantalRekeningenVanUser+1);
        modelAndView.addObject("bankAccountName", bankAccountName);
    }

    public void createAndSavePrivateAccount(String bankAccountName, Model model, PrivateAccount privateAccount) {
        Customer user = (Customer) model.getAttribute("user");
        privateAccount.setAccountHolder(user);
        privateAccount.setAccountName(bankAccountName);
        privateAccount.setBalance(BANKACCOUNT_BEGINBALANCE_GIFT);
        Iban iban = ibanGenerator.createIban();
        privateAccount.setIban(iban.toString());
        savePrivateAccount(privateAccount);
    }
}
