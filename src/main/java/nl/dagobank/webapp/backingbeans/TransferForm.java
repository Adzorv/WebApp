package nl.dagobank.webapp.backingbeans;

import nl.dagobank.webapp.domain.FullName;

public class TransferForm {
    private double amount;
    private FullName fullName;
    private String IBAN;
    private String paymentCode;
    private String description;

    public TransferForm() {super(); }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public FullName getUserFullName() {
        return fullName;
    }

    public void setUserFullName(FullName fullName) {
        this.fullName = fullName;
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