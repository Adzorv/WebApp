package nl.dagobank.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoginAttempt {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private String id;

    @OneToOne
    private Customer customer;
    @Column
    private int failedAttempts;
    @Column
    private LocalDateTime lastAttempt;

    public LoginAttempt( Customer customer ) {
        super();
        this.customer = customer;
    }

    public LoginAttempt() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts( int failedAttempts ) {
        this.failedAttempts = failedAttempts;
    }

    public LocalDateTime getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt( LocalDateTime lastAttempt ) {
        this.lastAttempt = lastAttempt;
    }
}
