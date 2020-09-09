package nl.dagobank.webapp.domain;

import javax.persistence.Entity;

@Entity
public class BusinessAccount extends BankAccount {
    private String businessName;
    private int kvkNumber;
    private String sbiCode;


    public BusinessAccount() {
        super();
    }

}
