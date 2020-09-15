package nl.dagobank.webapp.backingbeans;

import java.math.BigDecimal;

public class TransferForm {
    private BigDecimal amount;
    //@Embedded
    //private UserFullName userFullName;
    private String userFullName;
    private String IBAN;
    private String paymentCode;
    private String description;

    public TransferForm() {super(); }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

//    public UserFullName getUserFullName() {
//        return userFullName;
//    }


    public String getUserFullName() {
        return userFullName;
    }

    // public void setUserFullName(UserFullName userFullName) {
//        this.userFullName = userFullName;
//    }
//


    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}