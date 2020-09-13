package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.backingbeans.RegistrationForm;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class
Customer extends User {
    private String firstName;
    private String prefix;
    private String lastName;
    private String streetName;
    private int houseNumber;
    private String houseNumberAnnex;
    private String postCode;
    private String city;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private int bsn;
    private String userName;
    private String password;



    public Customer(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails, UserInlogCredentials userInlogCredentials, UserPersonalDetails userPersonalDetails) {
        super(userFullName, userAddress, userContactDetails, userPersonalDetails, userInlogCredentials);
    }

    public Customer() {
    }
    public Customer(RegistrationForm registrationForm, String userName, String password) {
        this.firstName = registrationForm.getFirstName();
        this.prefix = registrationForm.getPrefix();
        this.lastName = registrationForm.getLastName();
        this.streetName = registrationForm.getStreetName();
        this.houseNumber = registrationForm.getHouseNumber();
        this.houseNumberAnnex = registrationForm.getHouseNumberAnnex();
        this.postCode = registrationForm.getPostCode();
        this.city = registrationForm.getCity();
        this.phoneNumber = registrationForm.getPhoneNumber();
        this.email = registrationForm.getEmail();
        this.birthDate = registrationForm.getBirthDate();
        this.bsn = registrationForm.getBsn();
        this.userName = userName;
        this.password = password;
    }



    public Customer create() {
        UserFullName userFullName = new UserFullName(firstName, prefix, lastName);
        UserAddress userAddress = new UserAddress(streetName, houseNumber, houseNumberAnnex, postCode, city);
        UserContactDetails userContactDetails = new UserContactDetails(phoneNumber, email);
        UserInlogCredentials userInlogCredentials = new UserInlogCredentials(userName, password);
        UserPersonalDetails userPersonalDetails = new UserPersonalDetails(birthDate, bsn);

        return new Customer(userFullName, userAddress, userContactDetails, userInlogCredentials, userPersonalDetails);
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
