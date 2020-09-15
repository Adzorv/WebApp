package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne//sending
    private BankAccount debitAccount;
    @OneToOne//receiving
    private BankAccount creditAccount;
    private BigDecimal amount;
    private String description;
    private LocalDate date;

    public Transaction(BankAccount sender, BankAccount receiver, BigDecimal amount, String description, LocalDate date) {
        this.date = date;
        this.debitAccount = sender;
        this.creditAccount = receiver;
        this.amount = amount;
        this.description = description;
    }

    public Transaction() {
        super();
    }

    public BankAccount getDebitAccount() {
        return debitAccount;
    }

    public BankAccount getCreditAccount() {
        return creditAccount;
    }

    public void setDebitAccount(PrivateAccount debitAccount) {
        this.debitAccount = debitAccount;
    }

    public void setCreditAccount(PrivateAccount creditAccount) {
        this.creditAccount = creditAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return String.format("%s %s", creditAccount.getAccountHolder().getFullName(), creditAccount.getIban());
    }
}