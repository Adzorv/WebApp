package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.service.Iban;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.math.BigDecimal;
import java.util.List;

@Entity
public abstract class BankAccount {

    @Id
    @GeneratedValue
    private int id;

    private String accountName;
    private Customer accountHolder;
    private List<Customer> secondaryAccountHolders;
    private Iban iban;
    private BigDecimal balance;
    private List<Transaction> transations;

    private final BigDecimal BANKACCOUNT_BEGINBALANCE_GIFT = new BigDecimal("25");
    private final Iban TESTIBAN = new Iban("NL58INGB0687603749");

    public BankAccount(int id, String accountName, Customer accountHolder, BigDecimal balance) {
        this.id = id;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.iban = TESTIBAN;
        this.balance = BANKACCOUNT_BEGINBALANCE_GIFT;
    }

    public BankAccount(){
        this.id = 0;
        this.accountName ="testaccountname";
        this.accountHolder = null;
        this.iban = TESTIBAN;
        this.balance = BANKACCOUNT_BEGINBALANCE_GIFT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<Customer> getSecondaryAccountHolders() {
        return secondaryAccountHolders;
    }

    public void setSecondaryAccountHolders(List<Customer> secondaryAccountHolders) {
        this.secondaryAccountHolders = secondaryAccountHolders;
    }

    public Iban getIban() {
        return iban;
    }

    public void setIban(Iban iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransations() {
        return transations;
    }

    public void setTransations(List<Transaction> transations) {
        this.transations = transations;
    }
}
