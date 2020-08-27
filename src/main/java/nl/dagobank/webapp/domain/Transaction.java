package com.dagobank.webapp.dagobank.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private BankAccount debitAccount;
    private BankAccount creditAccount;
    private BigDecimal amount;
    private String description;
    private Date date;

    public Transaction() {
        super();
    }

    public BankAccount getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount( BankAccount debitAccount ) {
        this.debitAccount = debitAccount;
    }

    public BankAccount getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount( BankAccount creditAccount ) {
        this.creditAccount = creditAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount( BigDecimal amount ) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }
}
