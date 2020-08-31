package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.BankAccount;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Service
public class IbanGenerator {

    private String countryCode = "NL";
    private String bankCode = "DAGO";
    private int checkNumber;
    private int accountNumber;

    private final int DUMMY_CHECKNUMBER = 99;



    public IbanGenerator(int accountNumber) {
        this.accountNumber = accountNumber;
        this.checkNumber = DUMMY_CHECKNUMBER;
    }

    public IbanGenerator() {
        this(1234567890);
    }

    @Override
    public String toString() {
        return countryCode + checkNumber + bankCode + accountNumber;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


}
