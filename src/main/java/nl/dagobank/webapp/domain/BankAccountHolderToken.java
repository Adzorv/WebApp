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
    BankAccount accountToAdd;


    public BankAccountHolderToken() {
    }

    public BankAccountHolderToken(Customer becomingSecundaryAccountHolder, String connectionCode, BankAccount accountToAdd) {
        this.becomingSecundaryAccountHolder = becomingSecundaryAccountHolder;
        this.connectionCode = connectionCode;
        this.accountToAdd = accountToAdd;
    }

    @Override
    public String toString() {
        return "BankAccountHolderToken{" +
                "id=" + id +
                ", becomingSecundaryAccountHolder=" + becomingSecundaryAccountHolder +
                ", connectionCode='" + connectionCode + '\'' +
                ", accountToAdd=" + accountToAdd +
                '}';
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

    public BankAccount getAccountToAdd() {
        return accountToAdd;
    }
}
