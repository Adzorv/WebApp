package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.BankAccount;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Random;

@Service
public class IbanGenerator {
    String iban;

    /*@Autowired
    public CustomerDao customerDao;*/

    public IbanGenerator() {
    }

    public Iban createIban(){
        Iban iban = Iban.random(CountryCode.NL);
        iban = Iban.random();
        iban = new Iban.Builder()
                .countryCode(CountryCode.NL)
                .bankCode("DAGO")
                .buildRandom();
        return iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "IbanGenerator{" +
                "iban='" + iban + '\'' +
                '}';
    }
}
