package nl.dagobank.webapp.backingbeans;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class RegistrationForm {
    private String firstName;
    private String prefix;
    private String lastName;
    private String phoneNumber;
    private String streetName;
    private Integer houseNumber;
    private String houseNumberAnnex;
    private String postCode;
    private String city;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private Integer bsn;

    public RegistrationForm() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAnnex() {
        return houseNumberAnnex;
    }

    public void setHouseNumberAnnex(String houseNumberAnnex) {
        this.houseNumberAnnex = houseNumberAnnex;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthday) {
       this.birthDate = birthday;
    }

    public Integer getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }
}
