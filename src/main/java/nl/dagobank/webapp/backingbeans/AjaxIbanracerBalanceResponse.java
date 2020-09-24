package nl.dagobank.webapp.backingbeans;

import java.math.BigDecimal;

public class AjaxIbanracerBalanceResponse {

    private BigDecimal balance;

    public AjaxIbanracerBalanceResponse() {
        super();
    }

    public AjaxIbanracerBalanceResponse( BigDecimal balance ) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance( BigDecimal balance ) {
        this.balance = balance;
    }
}
