package nl.dagobank.webapp.domain;

public class Iban {
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
