package nl.dagobank.webapp.backingbeans;

import java.math.BigDecimal;

public class BalanceSumCount {

    private BigDecimal balance;
    private String businessName;

    public BalanceSumCount( String businessName, BigDecimal balance, ) {
        this.balance = balance;
        this.businessName = businessName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance( BigDecimal balance ) {
        this.balance = balance;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName( String businessName ) {
        this.businessName = businessName;
    }

    @Override
    public String toString() {
        return "BalanceSumCount{" +
                "balance=" + balance +
                ", businessName='" + businessName + '\'' +
                '}';
    }
}
