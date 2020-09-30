package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.backingbeans.AccountInfo;
import nl.dagobank.webapp.backingbeans.Business;
import nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"businessName", "kvkNumber", "sbiCode"})})
public class BusinessAccount extends BankAccount {
    private String businessName;
    private int kvkNumber;
    private String sbiCode;

    public BusinessAccount() {
        super();
    }

    public BusinessAccount(OpenBusinessAccountForm openBusinessAccountForm) {
        this(
                openBusinessAccountForm.getBusinessName(),
                openBusinessAccountForm.getKvkNumber(),
                openBusinessAccountForm.getSbiCode()
        );

    }

    public BusinessAccount( AccountInfo accountInfo, Customer accountHolder, String businessName, int kvkNumber, String sbiCode ) {
        super( accountInfo, accountHolder );
        this.businessName = businessName;
        this.kvkNumber = kvkNumber;
        this.sbiCode = sbiCode;
    }

    public BusinessAccount( String businessName, int kvkNumber, String sbiCode ) {
        super();
        this.businessName = businessName;
        this.kvkNumber = kvkNumber;
        this.sbiCode = sbiCode;
    }

    public BusinessAccount( AccountInfo accountInfo, Customer accountHolder, Business business ) {
        super( accountInfo, accountHolder );
        this.businessName = business.getBusinessName();
        this.kvkNumber = business.getKvkNumber();
        this.sbiCode = business.getSbiCode();
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
        return String.format("Bedrijfsnaam : %s\nKvKnummer : %s\nSBI-code :  %s\n%s",businessName, kvkNumber, sbiCode, super.toString());
    }

}
