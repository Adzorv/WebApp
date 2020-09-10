package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
public abstract class BankAccount {

    @Id
    @GeneratedValue
    private int id;
    private String accountName;

    @OneToOne
    private Customer accountHolder;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Customer> secondaryAccountHolders;

    private String iban;
    private BigDecimal balance;

    private final BigDecimal BANKACCOUNT_BEGINBALANCE_GIFT = new BigDecimal("25");


    public BankAccount(int id, String accountName, Customer accountHolder, BigDecimal balance) {
        this.id = id;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.balance = BANKACCOUNT_BEGINBALANCE_GIFT;
    }

    public BankAccount(){
    }

    @Override
    public String toString() {
        return String.format("%s, %s rekeninghouder:[%s] Saldo:  %.2f €", accountName, iban, accountHolder.getFirstName(),balance );
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

}
