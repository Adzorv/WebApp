package nl.dagobank.webapp.service;

import org.springframework.stereotype.Service;

@Service
public class Iban {
    private String iban;

    public Iban() {
    }

    public String createIban(){
        return "";
    }

    public String getIban() {
        return iban;
    }

    public void setIban( String iban ) {
        this.iban = iban;
    }
}
