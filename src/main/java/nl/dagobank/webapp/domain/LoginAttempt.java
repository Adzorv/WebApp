package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoginAttempt {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id;

    @OneToOne
    private Customer customer;
    @Column
    private int failedAttempts;
    @Column
    private LocalDateTime timeAtLastLoginAttempt;

    public LocalDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil( LocalDateTime blockedUntil ) {
        this.blockedUntil = blockedUntil;
    }

    @Column
    private LocalDateTime blockedUntil;

    public LoginAttempt( Customer customer) {
        super();
        this.customer = customer;
        this.failedAttempts = 1;
        this.timeAtLastLoginAttempt = LocalDateTime.now();
    }

    public LoginAttempt() {
        super();
    }

    public boolean isBlocked() {
        return blockedUntil != null && blockedUntil.isAfter( LocalDateTime.now() );
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer) {
        this.customer = customer;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void incrementFailedAttempts() {
        failedAttempts++;
    }

    public void resetFailedAttempts() {
        failedAttempts = 1;
    }

    @Override
    public String toString() {
        return "LoginAttempt: " +
                "\nUser= " + customer +
                "\nfailedAttempts=" + failedAttempts +
                "\ntimeAtLastLoginAttempt=" + timeAtLastLoginAttempt;
    }
}
