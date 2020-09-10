package nl.dagobank.webapp.domain;

import javax.persistence.Entity;

@Entity
public class BusinessAccount extends BankAccount {
    private String businessName;
    private int kvkNumber;
    private String sbiCode;


    public BusinessAccount() {
        super();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(int kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public String getSbiCode() {
        return sbiCode;
    }

    public void setSbiCode(String sbiCode) {
        this.sbiCode = sbiCode;
    }

    @Override
    public String toString() {
        return "Zakelijke rekening: "  + businessName + " " + kvkNumber + " " + sbiCode + " " + super.toString();
    }

}
