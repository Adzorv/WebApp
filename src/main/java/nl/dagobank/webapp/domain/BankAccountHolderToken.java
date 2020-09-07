package nl.dagobank.webapp.domain;

import javax.persistence.*;

@Entity
public class BankAccountHolderToken {

    @Id
    @GeneratedValue
    int id;

    @OneToOne
    Customer becomingSecundaryAccountHolder;
    String connectionCode;

    @OneToOne
    PrivateAccount accountToAdd;


    public BankAccountHolderToken() {
    }

    public BankAccountHolderToken(Customer becomingSecundaryAccountHolder, String connectionCode, PrivateAccount accountToAdd) {
        this.becomingSecundaryAccountHolder = becomingSecundaryAccountHolder;
        this.connectionCode = connectionCode;
        this.accountToAdd = accountToAdd;
    }

    public Customer getBecomingSecundaryAccountHolder() {
        return becomingSecundaryAccountHolder;
    }


    public int getId() {
        return id;
    }

    public String getConnectionCode() {
        return connectionCode;
    }

    public PrivateAccount getAccountToAdd() {
        return accountToAdd;
    }
}
