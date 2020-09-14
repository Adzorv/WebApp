package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm;
import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.service.IbanGenerator;
import org.iban4j.Iban;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"businessName", "kvkNumber", "sbiCode"})})
public class BusinessAccount extends BankAccount {
    private String businessName;
    private int kvkNumber;
    private String sbiCode;

    public BusinessAccount() {
    }

    public BusinessAccount(OpenBusinessAccountForm openBusinessAccountForm) {
        super();
        this.businessName = openBusinessAccountForm.getBusinessName();
        this.kvkNumber = openBusinessAccountForm.getKvkNumber();
        this.sbiCode = openBusinessAccountForm.getSbiCode();



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
        return "Zakelijke rekening met Bedrijfsnaam : " + businessName + " KvKnummer : " + kvkNumber + " SBI-code :  " + sbiCode + " " + super.toString();
    }

}
