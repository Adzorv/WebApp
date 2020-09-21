package nl.dagobank.webapp.backingbeans;

import java.math.BigDecimal;

public interface BalanceSumPerBusiness {

    BigDecimal getBalance();
    String getBusinessName();

//    public BalanceSumPerBusiness( String businessName, BigDecimal balance ) {
//        this.balance = balance;
//        this.businessName = businessName;
//    }
//
//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void setBalance( BigDecimal balance ) {
//        this.balance = balance;
//    }
//
//    public String getBusinessName() {
//        return businessName;
//    }
//
//    public void setBusinessName( String businessName ) {
//        this.businessName = businessName;
//    }
//
//    @Override
//    public String toString() {
//        return "BalanceSumPerBusiness{" +
//                "balance=" + balance +
//                ", businessName='" + businessName + '\'' +
//                '}';
//    }
}
