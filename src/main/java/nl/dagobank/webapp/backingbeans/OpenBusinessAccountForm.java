package nl.dagobank.webapp.backingbeans;

import java.util.Arrays;
import java.util.List;

public class OpenBusinessAccountForm {
    private String businessName;
    private int kvkNumber;
    private String sbiCode;
    public static List<String> sbiCodes = Arrays.asList("Maak een keuze...", "A: Landbouw, bosbouw en Visserij", "B: Winning van Delftstoffen ",
            "C: Industrie", "D: Productie en Distributie van en handel in electriciteit, aardgas, stoom en gekoelde lucht ",
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

    public List<String> getSbiCodes() {
        return sbiCodes;
    }

    public void setSbiCodes(List<String> sbiCodes) {
     this.sbiCodes = sbiCodes;
    }//fixme: how to get the right value into db

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
}
