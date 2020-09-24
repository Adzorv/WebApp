package nl.dagobank.webapp.controller;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

public class IbanIterator {

    private Iban currentIban;
    private long startingNumber;

    public IbanIterator( long startingNumber ) {
        super();
        this.startingNumber = startingNumber;
        creatIban();
    }

    public IbanIterator() {
        this.startingNumber = 1000000000L;
        creatIban();
    }

    private void creatIban() {
        currentIban = new Iban.Builder()
                .countryCode( CountryCode.NL )
                .bankCode( "DAGO" ).accountNumber( createAccountNumber() ).build();
    }

    private String createAccountNumber() {
        return String.valueOf( startingNumber++ );
    }

    public Iban next() {
        creatIban();
        return currentIban;
    }



}
