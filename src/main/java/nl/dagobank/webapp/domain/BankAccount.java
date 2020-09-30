package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.backingbeans.AccountInfo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity

public abstract class BankAccount {

    @Id
    @GeneratedValue
    private int id;
    private String accountName;

    @OneToOne
    private Customer accountHolder;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Customer> secondaryAccountHolders;

    private String iban;
    private BigDecimal balance;

    public static final int START_BALANCE = 25;

    public BankAccount( AccountInfo accountInfo, Customer accountHolder ) {
        this.accountName = accountInfo.getAccountName();
        this.accountHolder = accountHolder;
        this.secondaryAccountHolders = new ArrayList<>();
        this.iban = accountInfo.getIban();
        this.balance = new BigDecimal( START_BALANCE );
    }

    public BankAccount() {
        this(new AccountInfo("", ""), null );
    }

    @Override
    public String toString() {
        return String.format("%s, %s rekeninghouder:[%s] Saldo:  € %.2f ", accountName, iban, accountHolder.getFullName(), balance );
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


    public void setSecondaryAccountHolders( List<Customer> secondaryAccountHolders ) {
        this.secondaryAccountHolders = secondaryAccountHolders;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id
                && Objects.equals( accountName, that.accountName )
                && Objects.equals( accountHolder, that.accountHolder )
                && Objects.equals( iban, that.iban )
                && Objects.equals( balance, that.balance )
                ;
    }

}
