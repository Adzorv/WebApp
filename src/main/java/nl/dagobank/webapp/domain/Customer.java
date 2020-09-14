package nl.dagobank.webapp.domain;

import javax.persistence.*;

@Entity
public class
Customer extends User {


    public Customer(FullName fullName, Address address, ContactDetails contactDetails, InlogCredentials inlogCredentials, PersonalDetails personalDetails) {
        super(fullName, address, contactDetails, personalDetails, inlogCredentials);
    }

    public Customer() {
    }


}



