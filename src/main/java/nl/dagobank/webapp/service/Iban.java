package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.BankAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Iban {

    @Id
    @GeneratedValue
    private int id;

    private String iban;

    public Iban( String iban ) {

        this.iban = iban;

    }

    public Iban() {
        super();
    }

    public String getIban() {
        return iban;
    }

    public void setIban( String iban ) {
        this.iban = iban;
    }
}
