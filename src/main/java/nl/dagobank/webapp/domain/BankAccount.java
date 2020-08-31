package nl.dagobank.webapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
public abstract class BankAccount {

    @Id
    @GeneratedValue
    private int id;

    private String accountName;
    //private Customer accountHolder;
    private String accountHolder;
    //private List<Customer> secondaryAccountHolders;

    private String iban;

    private BigDecimal balance;
    //private List<Transaction> transations;

    private final BigDecimal BANKACCOUNT_BEGINBALANCE_GIFT = new BigDecimal("25");
    private final String TESTIBAN = "NL58INGB0687603749";

    public BankAccount(int id, String accountName, String accountHolder, BigDecimal balance) {
        this.id = id;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.iban = TESTIBAN;
        this.balance = BANKACCOUNT_BEGINBALANCE_GIFT;
    }

    public BankAccount(){
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

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    //public List<Customer> getSecondaryAccountHolders() {
        //return secondaryAccountHolders;
    //}

//    public void setSecondaryAccountHolders(List<Customer> secondaryAccountHolders) {
//        this.secondaryAccountHolders = secondaryAccountHolders;
//    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

//    public List<Transaction> getTransations() {
//        return transations;
//    }

//    public void setTransations(List<Transaction> transations) {
//        this.transations = transations;
//    }
}
