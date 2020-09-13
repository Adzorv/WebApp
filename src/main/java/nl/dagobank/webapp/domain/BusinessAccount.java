package nl.dagobank.webapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"businessName", "kvkNumber", "sbiCode"})})
public class BusinessAccount extends BankAccount {
    /*@Column(name = "businessName")*/
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
        return "Zakelijke rekening: " + businessName + " " + kvkNumber + " " + sbiCode + " " + super.toString();
    }

}
