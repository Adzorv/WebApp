package nl.dagobank.webapp.domain;

import java.math.BigDecimal;
import java.util.List;

public abstract class BankAccount {
    private int id;
    private String accountName;
    private Customer accountHolder;
    private List<Customer> secondaryAccountHolders;
    private Iban iban;
    private BigDecimal balance;
    private List<Transaction> transations;

    public BankAccount() {
        super();
    }
}
