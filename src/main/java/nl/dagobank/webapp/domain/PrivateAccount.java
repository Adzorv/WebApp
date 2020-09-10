package nl.dagobank.webapp.domain;

import javax.persistence.Entity;

@Entity
public class PrivateAccount extends BankAccount {


    @Override
    public String toString() {
        return "Particulier rekening: " + super.toString();
    }
}
