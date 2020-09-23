package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.exception.customerAlreadySecondaryAccountHolderException;
import nl.dagobank.webapp.exception.noValidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeServiceBankAccountHolderToken_BancAccount {

    private BankAccountService bankAccountService;
    private BankAccountHolderTokenService bankAccountHolderTokenService;

    @Autowired
    public FacadeServiceBankAccountHolderToken_BancAccount(BankAccountService bankAccountService, BankAccountHolderTokenService bankAccountHolderTokenService) {
        this.bankAccountService = bankAccountService;
        this.bankAccountHolderTokenService = bankAccountHolderTokenService;
    }

    public void addUserAsSecondaryAccountHolder(Customer customer, String iban, String code) throws noValidTokenException, customerAlreadySecondaryAccountHolderException{
        if (!bankAccountHolderTokenService.existsValidToken(customer, iban, code)){
            throw new noValidTokenException("for the given user, iban and code no valid token was found");
        } else {
            List<BankAccountHolderToken> validTokens = bankAccountHolderTokenService.getValidTokens(customer, iban);
            BankAccount bankAccountToAddCustomer = validTokens.get(0).getAccountToAdd();
            boolean isUserAlreadySeconderyAccountHolder = bankAccountService.isCustomerSecondAccountHolder(customer, bankAccountToAddCustomer);
            bankAccountHolderTokenService.deleteTokens(validTokens);
            if (isUserAlreadySeconderyAccountHolder) {
                throw new customerAlreadySecondaryAccountHolderException("User " + customer.getFullName() +" is already a secondary account holder");
            } else {
                bankAccountToAddCustomer.getSecondaryAccountHolders().add(customer);
                bankAccountService.savePrivateAccount((PrivateAccount)bankAccountToAddCustomer);
            }
        }
    }
}
