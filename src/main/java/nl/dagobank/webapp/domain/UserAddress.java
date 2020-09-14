package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.backingbeans.RegistrationForm;

import javax.persistence.Entity;

public class UserAddress {
    private String streetName;
    private int houseNumber;
    private String houseNumberAnnex;
    private String postCode;
    private String city;

    public UserAddress(String streetName, int houseNumber, String houseNumberAnnex, String postCode, String city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.houseNumberAnnex = houseNumberAnnex;
        this.postCode = postCode;
        this.city = city;
    }

    public UserAddress() {
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

    @Override
    public String toString() {
        return String.format(streetName + " " + houseNumber + " " + houseNumberAnnex +  " " + postCode + " " + city);

    }
}
