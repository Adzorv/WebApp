package nl.dagobank.webapp.backingbeans;

import java.util.Arrays;
import java.util.List;

public class OpenBusinessAccountForm {
    private String businessName;
    private Integer kvkNumber;
    private String sbiCode;
    public static List<String> sbiCodes = Arrays.asList("Maak een keuze...", "A: Landbouw, bosbouw en Visserij", "B: Winning van Delftstoffen ",
            "C: Industrie", "D: Productie en Distributie van en handel in energie ",
            "E: Winning en distributie van water", "F: Bouwnijverheid", "G: Groot- en detailhandel", "H: Vervoer en opslag");
    private String bankAccountName;


    public OpenBusinessAccountForm() {
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(Integer kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public String getSbiCode() {
        return sbiCode;
    }

    public void setSbiCode(String sbiCode) {
        this.sbiCode = sbiCode;
    }

    public List<String> getSbiCodes() {
        return sbiCodes;
    }

    public void setSbiCodes(List<String> sbiCodes) {
     this.sbiCodes = sbiCodes;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
}
