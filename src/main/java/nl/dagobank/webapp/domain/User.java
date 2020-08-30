package nl.dagobank.webapp.domain;


import nl.dagobank.webapp.service.PasswordGenerator;
import nl.dagobank.webapp.service.UsernameGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public abstract class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String firstName;
    @Column
    private String prefix;
    @Column
    private String lastName;
    @Column
    private String phoneNumber;
    @Column
    private String streetName;
    @Column
    private int houseNumber;
    @Column
    private String houseNumberAnnex;
    @Column
    private String postCode;
    @Column
    private String city;
    @Column
    private String email;
    @Column
    private Date birthDate;
    @Column
    private int bsn;
    @Column
    private String userName;
    @Column
    private String password;


    public User(){}

    public User(String firstName, String prefix, String lastName, String phoneNumber, String streetName, int houseNumber,
                String houseNumberAnnex, String postCode, String city,String email, Date birthDate, int bsn, String userName, String password) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.houseNumberAnnex = houseNumberAnnex;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
        this.birthDate = birthDate;
        this.bsn = bsn;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", prefix='" + prefix + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix( String prefix ) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName( String streetName ) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber( int houseNumber ) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAnnex() {
        return houseNumberAnnex;
    }

    public void setHouseNumberAnnex( String houseNumberAnnex ) {
        this.houseNumberAnnex = houseNumberAnnex;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode( String postCode ) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate( Date birthDate ) {
        this.birthDate = birthDate;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn( int bsn ) {
        this.bsn = bsn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
